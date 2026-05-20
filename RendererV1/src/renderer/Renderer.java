package renderer;

public class Renderer {
    // The raw pixel data
    private int[] pixels;
    // Keeps track of the depth of each pixel
    private float[] zDepthBuffer;
    // The width of the screen in pixels
    private int width;
    // The height of the screen in pixels
    private int height;
    // The collection of objects to render
    private Scene scene;
    // Temporary reference to an object during the process of rendering
    private Object3D[] objects;
    // Used to render the world from a specific view point
    private Camera camera;
    // The 2D coordinates of the projected 3D vertex in relative screen space
    private Point2D relativeScreenSpaceCoord;
    // The actual pixel coordinates of the projected 3D vertex
    private Point2D pixelCoord;

    /**
     * Initialises a new renderer object.
     * @param pixels The raw array of pixels colours.
     * @param width The width of the screen in pixels.
     * @param height The height of the screen in pixels.
     * @param scene The collection of 3D objects to render.
     */
    public Renderer(int[] pixels, int width, int height, Scene scene, Camera camera) {
        this.pixels = pixels;
        this.width = width;
        this.height = height;
        this.scene = scene;
        this.camera = camera;

        // Initialise each pixels depth to the largest int value
        initialiseZDepthBuffer();
    }

    public void render() {
        // Start with a blank screen
        clearFrame();

        // Grab the collection of objects to render
        objects = scene.getObjects();

        // Iterate through each object and render it
        for (Object3D object : objects) {
            // Duplicate the object so the original remains in world space and unaffected
            object = object.clone();

            // Transform the object from world space to camera space
            transformToCameraSpace(object);

            // Project each vertex of the object onto the screen
            for (Point3D vertex : object.vertices) {
                // Project the current vertex to relative screen space
                relativeScreenSpaceCoord = projectVertex(vertex);

                // Convert relative screen space to actual pixel coordinates
                pixelCoord = convertToPixelCoordinate(relativeScreenSpaceCoord);

                // Only paint this pixel if the vertex that maps to it is closer than the
                // current vertex at that pixel
                if (vertex.z < zDepthBuffer[(int)((pixelCoord.y*width)+pixelCoord.x)]) {
                    // Set that pixel's colour to white (0xFFFFFF)
                    setPixel((int)pixelCoord.x, (int)pixelCoord.y, 0xFFFFFF);

                    // Update the z depth buffer with the new pixel depth information
                    zDepthBuffer[(int)((pixelCoord.y*width)+pixelCoord.x)] = vertex.z;
                }
            }
        }
    }

    /**
     * Initialises the depth for each pixel to the float max value.
     */
    private void initialiseZDepthBuffer() {
        // Create a new array to hold each pixels depth
        zDepthBuffer = new float[pixels.length];

        // Set each pixel's depth to the largest float value
        for (int i = 0; i < zDepthBuffer.length; i++) {
            zDepthBuffer[i] = Float.MAX_VALUE;
        }
    }

    /**
     * Resets all pixels to black.
     */
    private void clearFrame() {
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
    private void setPixel(int x, int y, int colour) {
        // Ensure the provided coordinates are within the bounds of the display
        if (x >= 0 && x < width && y >= 0 && y < height) {
            // Converts a 2D coordinate to a 1D array index
            int pixelIndex = (y * width) + x;
            pixels[pixelIndex] = colour;
        }
    }

    /**
     * Transforms a given 3D object into camera space.
     * This is done my performing the same operations 
     * @param object
     */
    private void transformToCameraSpace(Object3D object) {
        // WORK IN PROGRESS
    }

    /**
     * Projects a given 3D vertex into a 2D coordinate in relative screen space.
     * @param vertex The 3D vertex to project.
     * @return A Point2D object representing the projected vertex in relative screen space.
     */
    private Point2D projectVertex(Point3D vertex) {
        // WORK IN PROGRESS
        return null;
    }

    /**
     * Converts a 2D coordinate from relative screen space to an actual pixel coordinate on the screen.
     * @param relativeCoord The 2D relative screen space coordinate.
     * @return A Point2D object representing the actual pixel coordinate.
     */
    private Point2D convertToPixelCoordinate(Point2D relativeCoord) {
        // WORK IN PROGRESS
        return null;
    }
}
