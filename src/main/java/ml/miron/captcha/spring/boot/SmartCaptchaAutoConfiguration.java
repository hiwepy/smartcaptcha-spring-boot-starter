package ml.miron.captcha.spring.boot;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServlet;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ml.miron.captcha.image.Captcha;

/**
 * Auto-configuration for SmartCaptcha.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass({ Captcha.class })
@EnableConfigurationProperties(SmartCaptchaProperties.class)
public class SmartCaptchaAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "imageCaptchaServlet")
    /**
     * <p>Image captcha servlet.</p>
     * @param properties
     * @return the image captcha servlet
     */
    public ServletRegistrationBean<HttpServlet> imageCaptchaServlet(SmartCaptchaProperties properties) {
        ServletRegistrationBean<HttpServlet> registrationBean = new ServletRegistrationBean<>();
        registrationBean.setServlet(new ImageCaptchaServlet(properties));
        registrationBean.addUrlMappings(properties.getImagePattern());
        return registrationBean;
    }

    @Bean
    @ConditionalOnMissingBean(name = "audioCaptchaServlet")
    /**
     * <p>Audio captcha servlet.</p>
     * @param properties
     * @return the audio captcha servlet
     */
    public ServletRegistrationBean<HttpServlet> audioCaptchaServlet(SmartCaptchaProperties properties) {
        ServletRegistrationBean<HttpServlet> registrationBean = new ServletRegistrationBean<>();
        registrationBean.setServlet(new AudioCaptchaServlet());
        registrationBean.addUrlMappings(properties.getAudioPattern());
        return registrationBean;
    }

    @Bean
    @ConditionalOnMissingBean(name = "refreshCaptchaFilter")
    /**
     * <p>Refresh captcha filter.</p>
     * @param properties
     * @return the refresh captcha filter
     */
    public FilterRegistrationBean<Filter> refreshCaptchaFilter(SmartCaptchaProperties properties) {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RefreshCaptchaJakartaFilter());
        registrationBean.addUrlPatterns(properties.getRefreshPattern());
        registrationBean.setEnabled(true);
        return registrationBean;
    }

}
