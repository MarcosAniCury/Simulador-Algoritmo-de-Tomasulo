

import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;
import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.common.base.Charsets;
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.browser.callback.InjectJsCallback;
import com.teamdev.jxbrowser.browser.callback.InjectJsCallback.Response;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.frame.Frame;
import com.teamdev.jxbrowser.js.JsAccessible;
import com.teamdev.jxbrowser.js.JsObject;
import com.teamdev.jxbrowser.navigation.event.FrameLoadFinished;
import com.teamdev.jxbrowser.view.swing.BrowserView;

import Controller.TomasuloController;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public final class App {

    public static void main(String[] args) {
        TomasuloController tomasuloController = new TomasuloController();
        tomasuloController.runTomasulo(null);

        Engine engine = Engine.newInstance(
                EngineOptions.newBuilder(HARDWARE_ACCELERATED)
                        .licenseKey("1BNDHFSC1G4QUT3RPDBJ1TFBPY6B2KFMVJNHOZ3VSLM4XCAPP3IAHVZYR9IL8WIQXKF645")
                        .build());

        Browser browser = engine.newBrowser();

        SwingUtilities.invokeLater(() -> {
            BrowserView view = BrowserView.newInstance(browser);

            JFrame frame = new JFrame("Tomasulo");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.add(view, BorderLayout.CENTER);
            frame.setSize(1280, 720);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        browser.set(InjectJsCallback.class, params -> {

            Frame frame = params.frame();
            String window = "window";
            JsObject jsObject = frame.executeJavaScript(window);
            if (jsObject == null) {
                throw new IllegalStateException(
                        format("'%s' JS object not found", window));
            }
            jsObject.putProperty("java", new JavaObject());
            return Response.proceed();
        });

        browser.navigation().on(FrameLoadFinished.class, event -> {
            String javaScript = load("observer.js");
            event.frame().executeJavaScript(javaScript);
        });

        String html = load("index.html");
        String base64Html = Base64.getEncoder().encodeToString(html.getBytes(UTF_8));
        String dataUrl = "data:text/html;base64," + base64Html;
        browser.navigation().loadUrl(dataUrl);

    }

    /**
     * Loads a resource content as a string.
     */
    private static String load(String resourceFile) {

        System.out.println(resourceFile);

        URL url = App.class.getResource(resourceFile);

        try (Scanner scanner = new Scanner(url.openStream(),
                Charsets.UTF_8.toString())) {

            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";

        } catch (IOException e) {
            throw new IllegalStateException("Unable to load resource " +
                    resourceFile, e);
        }
    }

    /**
     * The object observing DOM changes.
     *
     * <p>
     * The class and methods that are invoked from JavaScript code must be public.
     */
    public static class JavaObject {

        @SuppressWarnings("unused") // invoked by callback processing code.
        @JsAccessible
        public void onDomChanged(String innerHtml) {
            System.out.println("DOM node changed: " + innerHtml);
        }
    }
}
