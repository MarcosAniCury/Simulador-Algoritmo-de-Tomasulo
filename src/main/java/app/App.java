package app;

import static com.teamdev.jxbrowser.engine.RenderingMode.OFF_SCREEN;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;

public class App{
    public static void main(String[] args){
        // Initialize Chromium.
        Engine engine = Engine.newInstance(
            EngineOptions.newBuilder(OFF_SCREEN)
            .licenseKey("1BNDHFSC1G4QUT3RPDBJ1TFBPY6B2KFMVJNHOZ3VSLM4XCAPP3IAHVZYR9IL8WIQXKF645")
            .build()
        );

        // Create a Browser instance.
        Browser browser = engine.newBrowser();

        // Load a web page and wait until it is loaded completely.
        browser.navigation().loadUrlAndWait("https://html5test.com/");

        // Print HTML of the loaded web page.
        browser.mainFrame().ifPresent(frame -> System.out.println(frame.html()));

        // Shutdown Chromium and release allocated resources.
        engine.close();
    }
}
