package ml.miron.captcha.spring.boot;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link ImageCaptchaServlet}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("ImageCaptchaServlet Tests")
class ImageCaptchaServletTest {

    @Test
    @DisplayName("Constructor creates non-null instance")
    void testConstructor() {
        SmartCaptchaProperties props = new SmartCaptchaProperties();
        ImageCaptchaServlet servlet = new ImageCaptchaServlet(props);
        assertThat(servlet).isNotNull();
    }

    @Test
    @DisplayName("doGet writes image to response and sets session attribute")
    void testDoGet() throws ServletException, IOException {
        SmartCaptchaProperties props = new SmartCaptchaProperties();
        ImageCaptchaServlet servlet = new ImageCaptchaServlet(props);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        when(request.getSession()).thenReturn(session);
        when(response.getOutputStream()).thenReturn(new jakarta.servlet.ServletOutputStream() {
            @Override
            public boolean isReady() { return true; }
            @Override
            public void setWriteListener(jakarta.servlet.WriteListener listener) {}
            @Override
            public void write(int b) { outputStream.write(b); }
        });

        servlet.doGet(request, response);

        verify(response).setContentType("image/png");
        verify(response).setHeader("Cache-Control", "no-cache, no-store");
        verify(session).setAttribute(eq("captcha"), anyString());
        assertThat(outputStream.size()).isGreaterThan(0);
    }
}
