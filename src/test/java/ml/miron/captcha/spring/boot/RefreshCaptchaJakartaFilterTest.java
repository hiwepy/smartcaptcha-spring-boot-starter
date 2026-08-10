package ml.miron.captcha.spring.boot;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link RefreshCaptchaJakartaFilter}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("RefreshCaptchaJakartaFilter Tests")
class RefreshCaptchaJakartaFilterTest {

    @Test
    @DisplayName("Constructor creates non-null instance")
    void testConstructor() {
        RefreshCaptchaJakartaFilter filter = new RefreshCaptchaJakartaFilter();
        assertThat(filter).isNotNull();
    }

    @Test
    @DisplayName("doFilter removes captcha from session and continues chain")
    void testDoFilter() throws ServletException, IOException {
        RefreshCaptchaJakartaFilter filter = new RefreshCaptchaJakartaFilter();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        FilterChain chain = mock(FilterChain.class);

        when(request.getSession()).thenReturn(session);

        filter.doFilter(request, response, chain);

        verify(session).removeAttribute("captcha");
        verify(chain).doFilter(request, response);
    }

    @Test
    @DisplayName("doFilter continues chain even for non-HTTP requests")
    void testDoFilterNonHttp() throws ServletException, IOException {
        RefreshCaptchaJakartaFilter filter = new RefreshCaptchaJakartaFilter();

        jakarta.servlet.ServletRequest request = mock(jakarta.servlet.ServletRequest.class);
        jakarta.servlet.ServletResponse response = mock(jakarta.servlet.ServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        filter.doFilter(request, response, chain);

        verify(chain).doFilter(request, response);
    }

    @Test
    @DisplayName("init and destroy do not throw")
    void testInitDestroy() throws ServletException {
        RefreshCaptchaJakartaFilter filter = new RefreshCaptchaJakartaFilter();
        filter.init(null);
        filter.destroy();
    }
}
