package renderer;

public class Camera {
    // Camera coordinates
    private Point3D coordinate;
    // Field of View
    private double fov;
    // The direction the camera is facing
    private Vector forward;
    // Direction of "right" relative to the camera
    private Vector right;
    // Stores the camera's rotation in the y axis, for side-to-side rotation
    private double yRotation;
    // Stores the camera's rotation in the x axis, for looking up and down
    private double xRotation;

    public Camera() {
        // Default properties of the camera
        this(new Point3D(0,0,0), 70.0, new Vector(0,0,1), new Vector(1,0,0));
    }

    public Camera(Point3D coordinate, double fov, Vector forward, Vector right) {
        this.coordinate = coordinate;
        this.fov = fov;
        this.forward = forward;
        this.right = right;
        // Initialise the x and y rotation
        yRotation = 0.0;
        xRotation = 0.0;
    }

    /**
     * Allows the client to know the coordinates of the camera.
     * @return The coordinates of the camera as a Point3D.
     */
    public Point3D getCoordinate() {
        return this.coordinate;
    }

    /**
     * Allows the client to update the camera's coordinate.
     * @param newCoord The new coordinate of the camera as a Point3D.
     */
    public void setCoordinate(Point3D newCoord) {
        this.coordinate = newCoord;
    }

    /**
     * Allows the client to know the camera's Field of View.
     * @return The FOV as a double.
     */
    public double getFOV() {
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

    /**
     * Gives the client access to this camera's rotation in the y-axis.
     * @return A float representing the y-axis rotation.
     */
    public double getYRotation() {
        return this.yRotation;
    }

    /**
     * Allows the client to update this camera's rotation in the y-axis.
     * @param newYRotation The new y-axis rotation for this camera.
     */
    public void setYRotation(float newYRotation) {
        this.yRotation = newYRotation;
    }

    /**
     * Gives the client access to this camera's rotation in the x-axis.
     * @return A float representing the x-axis rotation.
     */
    public double getXRotation() {
        return this.xRotation;
    }

    /**
     * Allows the client to update this camera's rotation in the x-axis.
     * @param newXRotation The new x-axis rotation for this camera.
     */
    public void setXRotation(float newXRotation) {
        this.xRotation = newXRotation;
    }

    /**
     * Calculates the distance between the camera and near plane.
     * @return A float representing the distance to the near plane.
     */
    public double getNearPlaneDistance() {
        return 1.0 / Math.tan(fov);
    }
}
