package renderer;

public class Renderer {
    // The raw pixel data
    private int[] pixels;
    // This second buffer for the pixel data is used to eliminate the flickering experienced
    // with Swing rendering the pixels array even before my renderer has finsished rendering
    private int[] hiddenPixelBuffer;
    // Keeps track of the depth of each pixel
    private double[] zDepthBuffer;
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

        // Initialise the private pixel buffer that is only used by this renderer
        hiddenPixelBuffer = new int[pixels.length];

        // Initialise each pixels depth to the largest int value
        initialiseZDepthBuffer();
    }

    /**
     * Displays the provided scene of 3D objects onto the 2D screen.
     * @param width The width of the rendered output.
     * @param height The height of the rendered output.
     */
    public void render() {
        // Start with a blank screen
        clearFrame();

        // Grab the collection of objects to render
        objects = scene.getObjects();

        // Array to hold each projected vertex of the object
        Point2D[] projectedVertices;

        // Iterate through each object and render it
        for (Object3D object : objects) {
            // Duplicate the object so the original remains in world space and unaffected
            object = object.clone();

            // Initialise a new array of projected vertices
            projectedVertices = new Point2D[object.vertices.length];

            // Transform the object from world space to camera space
            transformToCameraSpace(object);

            int i = 0;
            // Project each vertex of the object onto the screen
            for (Point3D vertex : object.vertices) {
                // Project the current vertex to relative screen space
                relativeScreenSpaceCoord = projectVertex(vertex);

                // Convert relative screen space to actual pixel coordinates
                pixelCoord = convertToPixelCoordinate(relativeScreenSpaceCoord);

                // Add this new projected vertex to the array of projected vertices
                projectedVertices[i] = pixelCoord;

                /*
                // Only paint this pixel if it is in bounds and the vertex that maps to it is
                // closer than the current vertex at that pixel
                int pixelIndex = (int)((pixelCoord.y*width)+pixelCoord.x);
                if (pixelInBounds(pixelIndex) && vertex.z < zDepthBuffer[pixelIndex]) {
                    // Set that pixel's colour to white (0xFFFFFF)
                    setPixel((int)pixelCoord.x, (int)pixelCoord.y, 0xFFFFFF);

                    // Update the z depth buffer with the new pixel depth information
                    zDepthBuffer[pixelIndex] = vertex.z;
                }
                */

                // Increment the projected vertex array pointer
                i++;
            }

            // Iterate through each edge of the object, drawing it to the screen
            for (Edge edge : object.edges) {
                drawLine((int)projectedVertices[edge.vertex1].x, (int)projectedVertices[edge.vertex1].y, (int)projectedVertices[edge.vertex2].x, (int)projectedVertices[edge.vertex2].y, 0xFFFFFF);
            }
        }

        // Once my renderer has finished drawing all the objects to its hidden display,
        // simple copy over all the data from the renderer's hidden pixel data to the public pixels array
        System.arraycopy(hiddenPixelBuffer, 0, pixels, 0, hiddenPixelBuffer.length);
    }

    /**
     * Determines if the provided <b>pixelIndex</b> is a valid index for the <b>hiddenPixelBuffer</b> array.
     * @param pixelIndex The pixel index to analyse.
     * @return True if the provided <b>pixelIndex</b> is valid, otherwise false.
     */
    private boolean pixelInBounds(int pixelIndex) {
        return pixelIndex >= 0 && pixelIndex < hiddenPixelBuffer.length;
    }

    /**
     * Initialises the depth for each pixel to the float max value.
     */
    private void initialiseZDepthBuffer() {
        // Create a new array to hold each pixels depth
        zDepthBuffer = new double[hiddenPixelBuffer.length];

        // Set each pixel's depth to the largest float value
        for (int i = 0; i < zDepthBuffer.length; i++) {
            zDepthBuffer[i] = Double.MAX_VALUE;
        }
    }

    /**
     * Resets each pixel's colour to black and its depth to the maximum float value.
     */
    private void clearFrame() {
        for (int i = 0; i < hiddenPixelBuffer.length; i++) {
            hiddenPixelBuffer[i] = 0x000000;
            zDepthBuffer[i] = Double.MAX_VALUE;
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
            hiddenPixelBuffer[pixelIndex] = colour;
        }
    }

    /**
     * Uses Bresenham's Line Algorithm to draw a straight line between two pixel coordinates.
     * @param x1 The starting x component.
     * @param y1 The starting y component.
     * @param x2 The ending x component.
     * @param y2 The ending y component.
     * @param colour The colour of the line, represented as hexadecimal.
     */
    private void drawLine(int x1, int y1, int x2, int y2, int colour) {
        // 1. Calculate the absolute horizontal and vertical distances
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        // 2. Determine the direction of the step (-1 or 1)
        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;

        // 3. Initialise the decision/error tracker
        int err = dx - dy;

        while (true) {
            // Paint the current pixel using your custom setPixel method
            setPixel(x1, y1, colour);

            // If we have reached the destination pixel, break the loop
            if (x1 == x2 && y1 == y2) {
                break;
            }

            // 4. Evaluate the error and adjust coordinates
            int e2 = 2 * err;

            // Should we move horizontally?
            if (e2 > -dy) {
                err -= dy;
                x1 += sx; // Step East/West
            }

            // Should we move vertically?
            if (e2 < dx) {
                err += dx;
                y1 += sy; // Step North/South
            }
        }
    }

    /**
     * Transforms a given 3D object into camera space.<br>
     * This is done my performing the same operations on the object, that transform
     * the camera to the origin and looking down the positive z-axis.
     * @param object The 3D object to transform to camera space.
     */
    private void transformToCameraSpace(Object3D object) {
        // Iterate through each vertex, converting it to camera space
        for (Point3D vertex : object.vertices) {
            // Translate by camera coordinates so the camera sits at the origin
            translateVertex(vertex, camera.getCoordinate());
            // Rotate the vertex in the x-axis by the camera's angle in the x-axis
            rotateVertexAboutXAxis(vertex, camera.getXRotation());
            // Rotate the vertex in the y-axis by the camera's angle in the y-axis
            rotateVertexAboutYAxis(vertex, camera.getYRotation());
        }
    }

    /**
     * Projects a given 3D vertex into a 2D coordinate in relative screen space.
     * @param vertex The 3D vertex to project.
     * @return A Point2D object representing the projected vertex in relative screen space.
     */
    private Point2D projectVertex(Point3D vertex) {
        // Formula:
        // 1/tan(FOV/2) = near plane distance
        // X_projected = (x*aspectRatio)/(z*tan(FOV/2))
        // Y_projected = y/(z*tan(FOV/2))
        double aspectRatio = (double)height / width;
        double projectedX = (vertex.x * aspectRatio * camera.getNearPlaneDistance()) / vertex.z;
        double projectedY = (vertex.y * camera.getNearPlaneDistance()) / vertex.z;
        return new Point2D(projectedX, projectedY);
    }

    /**
     * Converts a 2D coordinate from relative screen space to an actual pixel coordinate on the screen.
     * @param relativeCoord The 2D relative screen space coordinate.
     * @return A Point2D object representing the actual pixel coordinate.
     */
    private Point2D convertToPixelCoordinate(Point2D relativeCoord) {
        // Formula:
        // X_p = (((x+1)*width)/2)
        // Y_p = -((y-1)*height)/2
        double xPixel = (((relativeCoord.x+1.0)*width)/2.0);
        double yPixel = ((1-relativeCoord.y)*height)/2.0;
        return new Point2D(xPixel, yPixel);
    }

    /**
     * Translates a given vertex by a vector. This is achieved by subtracting the <b>translatingVector</b> from the <b>vertexToTranslate</b>.
     * @param vertexToTranslate The vertex of a 3D object to translate.
     * @param translatingVector The vector used to translate the object's vertex.
     */
    private void translateVertex(Point3D vertexToTranslate, Point3D translatingVector) {
        // Perform element-wise subtraction on these two vectors
        vertexToTranslate.x = vertexToTranslate.x - translatingVector.x;
        vertexToTranslate.y = vertexToTranslate.y - translatingVector.y;
        vertexToTranslate.z = vertexToTranslate.z - translatingVector.z;
    }

    /**
     * Rotates a given vertex about the global x-axis.
     * @param vertex The vertex to rotate.
     * @param theta The angle in radians to rotate <b>vertex</b> by.
     */
    private void rotateVertexAboutXAxis(Point3D vertex, double theta) {
        // Calculate the new y and z components of the vertex
        double newY = (vertex.y * Math.cos(theta)) + (vertex.z * Math.sin(theta));
        double newZ = (-1 * vertex.y * Math.sin(theta)) + (vertex.z * Math.cos(theta));
        // Assign the new y and z back to the original vertex
        vertex.y = newY;
        vertex.z = newZ;
    }

    /**
     * Rotates a given vertex about the global y-axis.
     * @param vertex The vertex to rotate.
     * @param theta The angle in radians to rotate <b>vertex</b> by.
     */
    private void rotateVertexAboutYAxis(Point3D vertex, double theta) {
        // Calculate the new x and z components of the vertex
        double newX = (vertex.x * Math.cos(theta)) - (vertex.z * Math.sin(theta));
        double newZ = (vertex.x * Math.sin(theta)) + (vertex.z * Math.cos(theta));
        // Assign the new x and z back to the original vertex
        vertex.x = newX;
        vertex.z = newZ;
    }

    /**
     * Rotates a given vertex about the global z-axis.
     * @param vertex The vertex to rotate.
     * @param theta The angle in radians to rotate <b>vertex</b> by.
     */
    private void rotateVertexAboutZAxis(Point3D vertex, double theta) {
        // Calculate the new x and y components of the vertex
        double newX = (vertex.x * Math.cos(theta)) + (vertex.y * Math.sin(theta));
        double newY = (-1 * vertex.x * Math.sin(theta)) + (vertex.y * Math.cos(theta));
        // Assign the new x and y back to the original vertex
        vertex.x = newX;
        vertex.y = newY;
    }
}
