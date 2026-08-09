/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package ml.miron.captcha.spring.boot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link SmartCaptchaAutoConfiguration}.
 *
 * <p>Verifies the auto-configuration activates under the expected conditions
 * and exposes its declared beans.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("SmartCaptchaAutoConfiguration Tests")
class SmartCaptchaAutoConfigurationTest {

    private final ApplicationContextRunner runner = new ApplicationContextRunner();

    @Test
    @DisplayName("Auto-configuration class can be instantiated")
    void testInstantiation() {
        SmartCaptchaAutoConfiguration configuration = new SmartCaptchaAutoConfiguration();
        assertThat(configuration).isNotNull();
    }

    @Test
    @DisplayName("Auto-configuration loads and registers all beans")
    void testLoadsAllBeans() {
        runner.withUserConfiguration(SmartCaptchaAutoConfiguration.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(SmartCaptchaAutoConfiguration.class);
                    assertThat(context).hasBean("imageCaptchaServlet");
                    assertThat(context).hasBean("audioCaptchaServlet");
                    assertThat(context).hasBean("refreshCaptchaFilter");
                });
    }

    @Test
    @DisplayName("Properties are properly bound")
    void testPropertiesBinding() {
        runner.withUserConfiguration(SmartCaptchaAutoConfiguration.class)
                .withPropertyValues(
                        "smartcaptcha.height=80",
                        "smartcaptcha.width=200",
                        "smartcaptcha.image-pattern=/captcha/image",
                        "smartcaptcha.audio-pattern=/captcha/audio",
                        "smartcaptcha.refresh-pattern=/captcha/refresh"
                )
                .run(context -> {
                    assertThat(context).hasSingleBean(SmartCaptchaProperties.class);
                    SmartCaptchaProperties props = context.getBean(SmartCaptchaProperties.class);
                    assertThat(props.getHeight()).isEqualTo(80);
                    assertThat(props.getWidth()).isEqualTo(200);
                });
    }
}
