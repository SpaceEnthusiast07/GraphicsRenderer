package src.application;

// So I can build Java Swing GUIs
import javax.swing.*;
// So I can use the different layout managers
import java.awt.*;
// So I can recognise events such as button clicks and keyboard key presses
import java.awt.event.*;

public class App extends JFrame implements ActionListener {
    // Represents the JFrame's content pane
    Container contentPane;

    // Sectioning off the app into a menu bar and main content area
    JPanel menuBar;
    JPanel mainContentArea;

    // Menu bar buttons
    JButton homePageButton;
    JButton secondPageButton;

    HomePage homePage;
    SecondPage secondPage;
    
    public App() {
        // Set the title of the window
        super("First Java Swing GUI");
        // Set the window size in pixels
        setSize(1920, 1080);
        // Closes the app when the user clicks the close button
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        contentPane = getContentPane();

        // Initialise the panels and buttons
        menuBar = new JPanel();
        menuBar.setBackground(new Color(45, 64, 75));
        mainContentArea = new JPanel();
        mainContentArea.setBackground(new Color(100,67,78));
        homePageButton = new JButton("Home Page");
        secondPageButton = new JButton("Second Page");
        
        // Add the buttons to the menu bar
        menuBar.add(homePageButton);
        menuBar.add(secondPageButton);

        secondPageButton.addActionListener(this);

        // Add the menu bar and main content area panels to the window before it is rendered
        add("North", menuBar);
        add("Center", mainContentArea);

        homePage = new HomePage();
        secondPage = new SecondPage();
        secondPage.setVisible(false);
        mainContentArea.add(homePage);
        mainContentArea.add(secondPage);

        // Ensures the window is visible to the user
        super.setVisible(true);
    }

    public static void main(String[] args) {
        App app = new App();
    }

    public void actionPerformed(ActionEvent event) {
        // If the second page button is clicked, load the second page
        if (event.getSource() == secondPageButton) {
            // Hide the home page
            homePage.setVisible(false);
            secondPage.setVisible(true);
        }
        // If the home page button is clicked, load the home page
        if (event.getSource() == homePageButton) {
            // Hide the home page
            homePage.setVisible(false);
        }
    }
}