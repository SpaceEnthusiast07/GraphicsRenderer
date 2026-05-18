package src.application;

// So I can use the Java Swing GUI components
import javax.swing.*;
// So I can use the different layout managers
//import java.awt.*;

public class PauseMenuPage extends JPanel {
    JLabel pageInfoLabel;
    
    public PauseMenuPage() {
        // Create a new label
        pageInfoLabel = new JLabel("This is the game menu, shown when the game is paused.");

        // Add the label to the panel
        add(pageInfoLabel);
    }
}
