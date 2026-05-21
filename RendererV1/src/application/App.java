package application;

// So I can access the renderer
import renderer.*;
// So I can use Java Swing GUI Components
import javax.swing.*;
// So I can get the content pane of the window
import java.awt.Container;

public class App extends JFrame {
    // The content pane used to add components to
    Container contentPane;
    // This is the canvas for the renderer to display on
    RendererPage rendererPage;
    
    public App() {
        // Set up the windows properties
        super("Renderer Version 1");
        setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get the content pane of the window so we can add components to it
        contentPane = getContentPane();

        // Create a new renderer page
        rendererPage = new RendererPage(this, 1920, 1080);

        // Add the renderer page to the window
        contentPane.add(rendererPage);

        // Maximise the window when created
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Ensure the window is visible to the user
        this.setVisible(true);
    }

    public static void main(String[] args) {
        App app = new App();

        // Game loop
        while(true) {
            app.rendererPage.render();
            //System.out.printf("%dx%d\n", app.contentPane.getWidth(), app.contentPane.getHeight());
        }
    }
}
