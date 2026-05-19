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
    
    public App() {
        // Set up the windows properties
        super("Renderer Version 1");
        setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get the content pane of the window so we can add components to it
        contentPane = getContentPane();

        // Create a new renderer page
        RendererPage renderer = new RendererPage(this, 800, 500);

        // Add the renderer page to the window
        contentPane.add(renderer);

        // Maximise the window when created
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Ensure the window is visible to the user
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}
