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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link SmartCaptchaProperties}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("SmartCaptchaProperties Tests")
class SmartCaptchaPropertiesTest {

    @Test
    @DisplayName("Default constructor creates non-null instance with defaults")
    void testDefaultInstance() {
        SmartCaptchaProperties props = new SmartCaptchaProperties();
        assertThat(props).isNotNull();
        assertThat(props.getImagePattern()).isEqualTo("/captcha.jpg");
        assertThat(props.getRefreshPattern()).isEqualTo("/captcha.html");
        assertThat(props.getAudioPattern()).isEqualTo("/audio.wav");
        assertThat(props.getAudioPath()).isNull();
        assertThat(props.getWidth()).isEqualTo(200);
        assertThat(props.getHeight()).isEqualTo(50);
    }

    @Test
    @DisplayName("PREFIX constant has expected value")
    void testPREFIXConstant() {
        assertThat(SmartCaptchaProperties.PREFIX).isEqualTo("smart-captcha");
    }

    @Test
    @DisplayName("imagePattern getter/setter works correctly")
    void testImagePatternGetterSetter() {
        SmartCaptchaProperties props = new SmartCaptchaProperties();
        props.setImagePattern("/custom/image");
        assertThat(props.getImagePattern()).isEqualTo("/custom/image");
    }

    @Test
    @DisplayName("refreshPattern getter/setter works correctly")
    void testRefreshPatternGetterSetter() {
        SmartCaptchaProperties props = new SmartCaptchaProperties();
        props.setRefreshPattern("/custom/refresh");
        assertThat(props.getRefreshPattern()).isEqualTo("/custom/refresh");
    }

    @Test
    @DisplayName("audioPattern getter/setter works correctly")
    void testAudioPatternGetterSetter() {
        SmartCaptchaProperties props = new SmartCaptchaProperties();
        props.setAudioPattern("/custom/audio");
        assertThat(props.getAudioPattern()).isEqualTo("/custom/audio");
    }

    @Test
    @DisplayName("audioPath getter/setter works correctly")
    void testAudioPathGetterSetter() {
        SmartCaptchaProperties props = new SmartCaptchaProperties();
        props.setAudioPath("/sounds");
        assertThat(props.getAudioPath()).isEqualTo("/sounds");
    }

    @Test
    @DisplayName("width getter/setter works correctly")
    void testWidthGetterSetter() {
        SmartCaptchaProperties props = new SmartCaptchaProperties();
        props.setWidth(300);
        assertThat(props.getWidth()).isEqualTo(300);
    }

    @Test
    @DisplayName("height getter/setter works correctly")
    void testHeightGetterSetter() {
        SmartCaptchaProperties props = new SmartCaptchaProperties();
        props.setHeight(100);
        assertThat(props.getHeight()).isEqualTo(100);
    }
}
