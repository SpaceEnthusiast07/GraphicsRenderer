package renderer;

import application.App;
// So I can use Java Swing GUI Components
import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class RendererPage extends JPanel {
    // Dimensions of the render
    int width;
    int height;
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
    // The system time when the last frame was completed
    long lastTime;

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
        
        // Set up a new cube
        cube = new Object3D();
        cube.centre = new Point3D(0.0, 0.0, 2.5);
        cube.vertices = new Point3D[] {
            new Point3D(-0.5, -0.5, 2.0), new Point3D(0.5, -0.5, 2.0),
            new Point3D(-0.5, -0.5, 3.0), new Point3D(0.5, -0.5, 3.0),
            new Point3D(-0.5, 0.5, 2.0), new Point3D(0.5, 0.5, 2.0),
            new Point3D(-0.5, 0.5, 3.0), new Point3D(0.5, 0.5, 3.0)
        };
        cube.edges = new Edge[] {
            new Edge(0,1), new Edge(1,3), new Edge(3, 2), new Edge(2,0),
            new Edge(4,5), new Edge(5, 7), new Edge(7, 6), new Edge(6, 4),
            new Edge(0,4), new Edge(1, 5), new Edge(3, 7), new Edge(2, 6)
        };

        // Create a new scene
        mainScene = new Scene(new Object3D[]{cube});
        
        // Set up a new renderer
        renderer = new Renderer(pixels, width, height, mainScene, camera);

        // Capture the time just before the first frame is rendered
        this.lastTime = System.nanoTime();
        
        render();
    }

    /**
     * Renders the actual frame.
     */
    public void render() {
        // Capture the current time
        long currentTime = System.nanoTime();

        // Convert the different between the last frame and this one into a fraction of 1 second
        double deltaTime = (currentTime - lastTime) / 1000000000.0;

        // Update the time of the last frame
        lastTime = currentTime;

        // Define a rotation speed, 1.5 radians per second
        double rotationSpeed = 1.3;

        // Calculate the exact angle to turn the cube based on the time between this frame and the last
        double deltaTheta = rotationSpeed * deltaTime;

        // Rotate the cube in it's local x-axis
        rotateObjectInYAxis(cube, deltaTheta);

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

    /**
     * Rotates a 3D object locally about the global x-axis.
     * @param object The 3D object to rotate.
     * @param theta The angle to rotate the object by.
     */
    public void rotateObjectInXAxis(Object3D object, double theta) {
        // The rotation matrix differs from the standard because I am using a different axes layout
        Point3D tempVertex = new Point3D(0,0,0);
        // Iterate through each vertex, rotating each in the x-axis
        for(Point3D vertex : object.vertices) {
            // Translate the vertex so the object's centre is at the origin
            vertex.x = vertex.x - object.centre.x;
            vertex.y = vertex.y - object.centre.y;
            vertex.z = vertex.z - object.centre.z;

            // Calculate the new y and z components of the vertex
            tempVertex.y = (vertex.y * Math.cos(theta)) + (vertex.z * Math.sin(theta));
            tempVertex.z = (-1 * vertex.y * Math.sin(theta)) + (vertex.z * Math.cos(theta));
            // Assign the new vertex components back to the original
            vertex.y = tempVertex.y;
            vertex.z = tempVertex.z;

            // Translate the vertex back
            vertex.x = vertex.x + object.centre.x;
            vertex.y = vertex.y + object.centre.y;
            vertex.z = vertex.z + object.centre.z;
        }
    }

    /**
     * Rotates a 3D object locally about the global y-axis.
     * @param object The 3D object to rotate.
     * @param theta The angle to rotate the object by.
     */
    public void rotateObjectInYAxis(Object3D object, double theta) {
        // The rotation matrix differs from the standard because I am using a different axes layout
        Point3D tempVertex = new Point3D(0,0,0);
        // Iterate through each vertex, rotating each in the y-axis
        for(Point3D vertex : object.vertices) {
            // Translate the vertex so the object's centre is at the origin
            vertex.x = vertex.x - object.centre.x;
            vertex.y = vertex.y - object.centre.y;
            vertex.z = vertex.z - object.centre.z;

            // Calculate the new x and z components of the vertex
            tempVertex.x = (vertex.x * Math.cos(theta)) - (vertex.z * Math.sin(theta));
            tempVertex.z = (vertex.x * Math.sin(theta)) + (vertex.z * Math.cos(theta));
            // Assign the new vertex components back to the original
            vertex.x = tempVertex.x;
            vertex.z = tempVertex.z;

            // Translate the vertex back
            vertex.x = vertex.x + object.centre.x;
            vertex.y = vertex.y + object.centre.y;
            vertex.z = vertex.z + object.centre.z;
        }
    }

    /**
     * Rotates a 3D object locally about the global z-axis.
     * @param object The 3D object to rotate.
     * @param theta The angle to rotate the object by.
     */
    public void rotateObjectInZAxis(Object3D object, double theta) {
        // The rotation matrix differs from the standard because I am using a different axes layout
        Point3D tempVertex = new Point3D(0,0,0);
        // Iterate through each vertex, rotating each in the z-axis
        for(Point3D vertex : object.vertices) {
            // Translate the vertex so the object's centre is at the origin
            vertex.x = vertex.x - object.centre.x;
            vertex.y = vertex.y - object.centre.y;
            vertex.z = vertex.z - object.centre.z;

            // Calculate the new x and y components of the vertex
            tempVertex.x = (vertex.x * Math.cos(theta)) + (vertex.y * Math.sin(theta));
            tempVertex.y = (-1 * vertex.x * Math.sin(theta)) + (vertex.y * Math.cos(theta));
            // Assign the new vertex components back to the original
            vertex.x = tempVertex.x;
            vertex.y = tempVertex.y;

            // Translate the vertex back
            vertex.x = vertex.x + object.centre.x;
            vertex.y = vertex.y + object.centre.y;
            vertex.z = vertex.z + object.centre.z;
        }
    }
}
