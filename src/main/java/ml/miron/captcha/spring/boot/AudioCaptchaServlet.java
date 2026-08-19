package ml.miron.captcha.spring.boot;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ml.miron.captcha.audio.AudioCaptcha;
import ml.miron.captcha.audio.Sample;

import java.io.IOException;

/**
 * Jakarta-compatible servlet that serves audio captchas.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class AudioCaptchaServlet extends HttpServlet {

    private static final String CAPTCHA_SESSION_KEY = "captcha";
    private static final long serialVersionUID = 1L;

    @Override
    /**
     * <p>Performs get.</p>
     * @param req
     * @param resp
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AudioCaptcha audioCaptcha = new AudioCaptcha.Builder()
                .addAnswer()
                .addNoise()
                .addVoice()
                .build();

        String answer = audioCaptcha.getAnswer();
        req.getSession().setAttribute(CAPTCHA_SESSION_KEY, answer);

        resp.setContentType("audio/wav");
        resp.setHeader("Cache-Control", "no-cache, no-store");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", 0);

        Sample sample = audioCaptcha.getChallenge();
        byte[] audio = sample.asByteArray();
        ServletOutputStream out = resp.getOutputStream();
        out.write(audio);
        out.flush();
    }
}
