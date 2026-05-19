package renderer;

public class Camera {
    // Camera coordinates
    private Vector coordinate;
    // Field of View
    private int fov;
    // The direction the camera is facing
    private Vector forward;
    // Direction of "right" relative to the camera
    private Vector right;

    public Camera() {
        // Default properties of the camera
        this(new Vector(0,0,0), 90, new Vector(1,0,0), new Vector(0,-1,0));
    }

    public Camera(Vector coordinate, int fov, Vector forward, Vector right) {
        this.coordinate = coordinate;
        this.fov = fov;
        this.forward = forward;
        this.right = right;
    }

    /**
     * Allows the client to know the coordinates of the camera.
     * @return The coordinates of the camera as a Vector.
     */
    public Vector getCoordinate() {
        return this.coordinate;
    }

    /**
     * Allows the client to update the camera's coordinate.
     * @param newCoord The new coordinate of the camera as a Vector.
     */
    public void setCoordinate(Vector newCoord) {
        this.coordinate = newCoord;
    }

    /**
     * Allows the client to know the camera's Field of View.
     * @return The FOV as an integer.
     */
    public int getFOV() {
        return this.fov;
    }

    /**
     * Allows the client to change the camera's Field of View.
     * @param fov The new FOV for the camera.
     */
    public void setFOV(int newFov) {
        this.fov = newFov;
    }

    /**
     * Allows the client to know which direction the camera is facing.
     * @return The forward direction as a Vector.
     */
    public Vector getForwardVector() {
        return this.forward;
    }

    /**
     * Allows the client to update the camera's forward vector.
     * @param newForward The new forward Vector.
     */
    public void setForwardVector(Vector newForward) {
        this.forward = newForward;
    }

    /**
     * Allows the client to know the right direction of the camera.
     * @return The right direction as a Vector.
     */
    public Vector getRightVector() {
        return this.right;
    }

    /**
     * Allows the client to update the camera's right direction.
     * @param newRight The new right direction as a Vector.
     */
    public void setRightVector(Vector newRight) {
        this.right = newRight;
    }
}
