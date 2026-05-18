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

    // These two panels are used to section the app off into a menu and main content area
    JPanel menuBar;
    JPanel mainContentArea;

    // These are the two buttons within the menu
    JButton homePageButton;
    JButton pauseMenuButton;

    // These represent the two pre-build pages
    HomePage homePage;
    PauseMenuPage pauseMenuPage;
    
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
        mainContentArea = new JPanel();
        homePageButton = new JButton("Home Page");
        pauseMenuButton = new JButton("Pause Menu");
        
        // Configure the menu and content panels 
        menuBar.setBackground(new Color(45, 64, 75));
        mainContentArea.setBackground(new Color(100,67,78));
        add("North", menuBar);
        add("Center", mainContentArea);

        // Add the menu buttons to the menu panel
        menuBar.add(homePageButton);
        menuBar.add(pauseMenuButton);

        // Allow the menu buttons to generate button click events
        pauseMenuButton.addActionListener(this);
        homePageButton.addActionListener(this);

        // Set up the two pre-built pages
        homePage = new HomePage();
        pauseMenuPage = new PauseMenuPage();
        pauseMenuPage.setVisible(false);
        mainContentArea.add(homePage);
        mainContentArea.add(pauseMenuPage);

        // Sets up the key bindings
        setUpKeyBindings();

        // Ensures the window is visible to the user
        this.setVisible(true);
    }

    public static void main(String[] args) {
        App app = new App();
    }

    /**
     * Handles button click events.
     */
    public void actionPerformed(ActionEvent event) {
        // Load the home page if the home page button is clicked
        if (event.getSource() == homePageButton) {
            // Hide the pause menu
            pauseMenuPage.setVisible(false);
            // Display the home page
            homePage.setVisible(true);
        }

        // Load the second page if the second page button is clicked
        if (event.getSource() == pauseMenuButton) {
            // Hide the home page
            homePage.setVisible(false);
            // Display the second page
            pauseMenuPage.setVisible(true);
        }
    }

    public void setUpKeyBindings() {
        // The InputMap is used to map physical keystrokes to "action names"
        // WHEN_IN_FOCUSED_WINDOW tells Swing to ignore which component is in focus when the key is pressed
        InputMap inputMap = mainContentArea.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        // The ActionMap is used to map "action names" to actual functions to execute
        ActionMap actionMap = mainContentArea.getActionMap();

        // Define key strokes for the movement keys - relates to the graphics rendering
        KeyStroke wKey = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0);
        KeyStroke aKey = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0);
        KeyStroke sKey = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0);
        KeyStroke dKey = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0);
        KeyStroke escKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

        // Specify, in the InputMap, which KeyStrokes map to which "action names"
        inputMap.put(wKey, "move_forward");
        inputMap.put(aKey, "move_left");
        inputMap.put(sKey, "move_backward");
        inputMap.put(dKey, "move_right");
        inputMap.put(escKey, "show_game_menu");

        // Define the function that are called when a specific key is pressed
        actionMap.put("move_forward", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                homePage.keyPressLabel.setText("w key pressed");
            }
        });
        actionMap.put("move_left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                homePage.keyPressLabel.setText("a key pressed");
            }
        });
        actionMap.put("move_backward", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                homePage.keyPressLabel.setText("s key pressed");
            }
        });
        actionMap.put("move_right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                homePage.keyPressLabel.setText("d key pressed");
            }
        });
        actionMap.put("show_game_menu", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // If home page is visible, switch to pause menu
                if (homePage.isVisible()) {
                    // Hide the home page
                    homePage.setVisible(false);
                    // Display the second page
                    pauseMenuPage.setVisible(true);
                }
                // Else, if the pause menu is visible, switch to the home page
                else if (pauseMenuPage.isVisible()) {
                    // Hide the pause menu
                    pauseMenuPage.setVisible(false);
                    // Display the home page
                    homePage.setVisible(true);
                }
            }
        });
    }
}