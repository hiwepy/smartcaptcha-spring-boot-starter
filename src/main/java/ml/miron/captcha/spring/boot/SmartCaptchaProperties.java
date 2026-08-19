package ml.miron.captcha.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = SmartCaptchaProperties.PREFIX)
/**
 * <p>Configuration properties for SmartCaptcha.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class SmartCaptchaProperties {

	public static final String PREFIX = "smart-captcha";

	/** 图片验证码访问路径 **/
	private String imagePattern = "/captcha.jpg";
	/** 图片验证码刷新路径 **/
	private String refreshPattern = "/captcha.html";
	/** 语音验证码访问路径 **/
	private String audioPattern = "/audio.wav";
	/** 语音存储的路径 */
	private String audioPath;
	private int width = 200;
	private int height = 50;

    /**
     * <p>Returns the image pattern.</p>
     * @return the get image pattern
     */
	public String getImagePattern() {
		return imagePattern;
	}

    /**
     * <p>Sets the image pattern.</p>
     * @param imagePattern
     */
	public void setImagePattern(String imagePattern) {
		this.imagePattern = imagePattern;
	}
	
    /**
     * <p>Returns the refresh pattern.</p>
     * @return the get refresh pattern
     */
	public String getRefreshPattern() {
		return refreshPattern;
	}

    /**
     * <p>Sets the refresh pattern.</p>
     * @param refreshPattern
     */
	public void setRefreshPattern(String refreshPattern) {
		this.refreshPattern = refreshPattern;
	}

    /**
     * <p>Returns the audio pattern.</p>
     * @return the get audio pattern
     */
	public String getAudioPattern() {
		return audioPattern;
	}

    /**
     * <p>Sets the audio pattern.</p>
     * @param audioPattern
     */
	public void setAudioPattern(String audioPattern) {
		this.audioPattern = audioPattern;
	}

    /**
     * <p>Returns the audio path.</p>
     * @return the get audio path
     */
	public String getAudioPath() {
		return audioPath;
	}

    /**
     * <p>Sets the audio path.</p>
     * @param audioPath
     */
	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}

    /**
     * <p>Returns the width.</p>
     * @return the get width
     */
	public int getWidth() {
		return width;
	}

    /**
     * <p>Sets the width.</p>
     * @param width
     */
	public void setWidth(int width) {
		this.width = width;
	}

    /**
     * <p>Returns the height.</p>
     * @return the get height
     */
	public int getHeight() {
		return height;
	}

    /**
     * <p>Sets the height.</p>
     * @param height
     */
	public void setHeight(int height) {
		this.height = height;
	}

}
