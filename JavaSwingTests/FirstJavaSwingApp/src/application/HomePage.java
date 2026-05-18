package src.application;

// So I can use the Java Swing GUI components
import javax.swing.*;
// So I can use the different layout managers
//import java.awt.*;

public class HomePage extends JPanel{
    JLabel pageInfoLabel;
    JLabel keyPressLabel;
    
    public HomePage() {
        // Create the two labels
        pageInfoLabel = new JLabel("This is the home page");
        keyPressLabel = new JLabel("Press any key");

        // Add them both to the page
        add(pageInfoLabel);
        add(keyPressLabel);
    }
}
