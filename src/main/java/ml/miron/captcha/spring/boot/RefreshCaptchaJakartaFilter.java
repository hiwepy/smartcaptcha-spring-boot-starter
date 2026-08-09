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
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class RefreshCaptchaJakartaFilter implements Filter {

    private static final String CAPTCHA_SESSION_KEY = "captcha";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpReq = (HttpServletRequest) request;
            httpReq.getSession().removeAttribute(CAPTCHA_SESSION_KEY);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
