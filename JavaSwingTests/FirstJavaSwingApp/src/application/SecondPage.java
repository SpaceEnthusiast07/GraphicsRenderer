package src.application;

// So I can use the Java Swing GUI components
import javax.swing.*;
// So I can use the different layout managers
import java.awt.*;

public class SecondPage extends JPanel {
    JLabel lbl1;
    
    public SecondPage() {
        // Create a new label
        lbl1 = new JLabel("This is the second page");

        add(lbl1);
    }
}
