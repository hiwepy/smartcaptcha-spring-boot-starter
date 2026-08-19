package ml.miron.captcha.spring.boot;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ml.miron.captcha.image.Captcha;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Jakarta-compatible servlet that serves image captchas.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class ImageCaptchaServlet extends HttpServlet {

    private static final String CAPTCHA_SESSION_KEY = "captcha";
    private static final long serialVersionUID = 1L;

    private final SmartCaptchaProperties properties;

    public ImageCaptchaServlet(SmartCaptchaProperties properties) {
        this.properties = properties;
    }

    @Override
    /**
     * <p>Performs get.</p>
     * @param req
     * @param resp
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int height = properties.getHeight();
        int width = properties.getWidth();

        Captcha captcha = new Captcha.Builder(width, height)
                .addText()
                .addBackground()
                .addNoise()
                .build();

        String answer = captcha.getAnswer();
        req.getSession().setAttribute(CAPTCHA_SESSION_KEY, answer);

        resp.setContentType("image/png");
        resp.setHeader("Cache-Control", "no-cache, no-store");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", 0);

        BufferedImage image = captcha.getImage();
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(image, "png", out);
        out.flush();
    }
}
