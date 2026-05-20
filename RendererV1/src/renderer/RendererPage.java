package renderer;

import application.App;
// So I can use Java Swing GUI Components
import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class RendererPage extends JPanel {
    // Dimensions of the render
    private int width;
    private int height;

    // This is the reference to the main application,
    // allowing communication for features like a game pause menu
    App mainApp;

    // The raw colour for each pixel, represented as hexadecimal
    int[] pixels;
    // Encapsulates the raw pixel data so it can be used by Java Swing
    BufferedImage frameBuffer;

    // Does the rendering calculations
    Renderer renderer;

    // A Scene is a collection of 3D objects
    Scene mainScene;

    // Demonstraight rendering functionality
    Object3D cube;

    // Used for the viewpoint of rendering
    Camera camera;

    public RendererPage(App mainApp, int width, int height) {
        // Store the reference to the main app
        this.mainApp = mainApp;
        
        // Set the dimensions of the renderer
        this.width = width;
        this.height = height;

        // Create a new image canvas, used to display in Swing
        frameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Traverse down the hierarchy and grab a reference to the raw pixel data
        pixels = ((DataBufferInt) frameBuffer.getRaster().getDataBuffer()).getData();
        
        // Set up a new camera
        camera = new Camera();
        camera.setCoordinate(new Vector(1,0,0));
        
        // Set up the cube's vertices
        cube = new Object3D();
        cube.vertices = new Point3D[] {
            new Point3D(0,0,0), new Point3D(1,0,0),
            new Point3D(0,1,0), new Point3D(1,1,0),
            new Point3D(0,0,1), new Point3D(1,0,1),
            new Point3D(0,1,1), new Point3D(1,1,1),
        };
        
        // Create a new scene
        mainScene = new Scene(new Object3D[]{cube});
        
        // Set up a new renderer
        renderer = new Renderer(pixels, width, height, mainScene, camera);

        render();
    }

    /**
     * Renders the actual frame.
     */
    public void render() {
        // Ask the renderer to render
        renderer.render();

        // Tell Swing to redraw this JPanel
        repaint();
    }

    /**
     * This allows the renderer to change the default behaviour when Swing redraws a component.
     */
    @Override
    public void paintComponent(Graphics g) {
        // Ensures nothing is left behind from the previous frame
        super.paintComponent(g);

        // Pass the raw pixel data to Swing to display on the monitor
        g.drawImage(frameBuffer, 0, 0, getWidth(), getHeight(), null);
    }
}
