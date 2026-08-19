package ml.miron.captcha.spring.boot;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * Jakarta-compatible filter that invalidates the captcha session on refresh.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class RefreshCaptchaJakartaFilter implements Filter {

    private static final String CAPTCHA_SESSION_KEY = "captcha";

    @Override
    /**
     * <p>Initializes the init.</p>
     * @param filterConfig
     */
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    /**
     * <p>Performs filter.</p>
     * @param request
     * @param response
     * @param chain
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpReq = (HttpServletRequest) request;
            httpReq.getSession().removeAttribute(CAPTCHA_SESSION_KEY);
        }
        chain.doFilter(request, response);
    }

    @Override
    /**
     * <p>Destroy.</p>
     */
    public void destroy() {
    }
}
