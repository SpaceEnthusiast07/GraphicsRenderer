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

    // Demonstraight rendering functionality
    Vertex[] cube;

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
        cube = new Vertex[8];
        cube[0] = new Vertex(0,0,0);
        cube[1] = new Vertex(1,0,0);
        cube[2] = new Vertex(0,1,0);
        cube[3] = new Vertex(1,1,0);
        cube[4] = new Vertex(0,0,1);
        cube[5] = new Vertex(1,0,1);
        cube[6] = new Vertex(0,1,1);
        cube[7] = new Vertex(1,1,1);

        render();
    }

    /**
     * Resets all pixels to black.
     */
    public void clearFrame() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0x000000;
        }
    }

    /**
     * Given a pixel coordinate, set its colour to the provided colour.
     * @param x The x-coordinate of the pixel.
     * @param y The y-coordinate of the pixel.
     * @param colour The colour to assign to this pixel.
     */
    public void setPixel(int x, int y, int colour) {
        // Ensure the provided coordinates are within the bounds of the display
        if (x >= 0 && x < width && y >= 0 && y < height) {
            // Converts a 2D coordinate to a 1D array index
            int pixelIndex = (y * width) + x;
            pixels[pixelIndex] = colour;
        }
    }

    /**
     * Renders the actual frame.
     */
    public void render() {
        clearFrame();

        // Set the middle pixel to white
        setPixel(width/2, height/2, 0xFFFFFF);

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
