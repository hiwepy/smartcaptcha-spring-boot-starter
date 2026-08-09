package ml.miron.captcha.spring.boot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link AudioCaptchaServlet}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("AudioCaptchaServlet Tests")
class AudioCaptchaServletTest {

    @Test
    @DisplayName("Constructor creates non-null instance")
    void testConstructor() {
        AudioCaptchaServlet servlet = new AudioCaptchaServlet();
        assertThat(servlet).isNotNull();
    }
}
