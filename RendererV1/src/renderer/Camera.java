package renderer;

public class Camera {
    // Camera coordinates
    private Point3D coordinate;
    // Field of View
    private double fov;
    // The direction the camera is facing
    private Vector lookingDirection;
    // The forward direction of the camera, independent of whether you are looking up or down
    private Vector forward;
    // Direction of "right" relative to the camera
    private Vector right;
    // Stores the camera's rotation in the y axis, for side-to-side rotation
    private double yRotation;
    // Stores the camera's rotation in the x axis, for looking up and down
    private double xRotation;
    // Used to track which direction the camera is moving in
    private boolean movingForward;
    private boolean movingBackward;
    private boolean movingRight;
    private boolean movingLeft;
    private boolean movingUp;
    private boolean movingDown;
    // The movement speed of the camera (units per second)
    private double cameraSpeed;

    public Camera() {
        // Default properties of the camera
        this(new Point3D(0,0,0), 70.0, new Vector(0,0,1), new Vector(1,0,0));
    }

    /**
     * Sets up a new camera.
     * @param coordinate The location the camera starts at as a Point3D object.
     * @param fov The Field of View of the camera.
     * @param forward The forward direction relative to the camera. Note: The looking direction is set to this initially.
     * @param right The right direction relative to the camera.
     */
    public Camera(Point3D coordinate, double fov, Vector forward, Vector right) {
        this.coordinate = coordinate;
        this.fov = fov;
        // Initially the camera is looking in the exact same direction as forward
        this.forward = forward;
        this.lookingDirection = forward;
        this.right = right;
        // Initialise the x and y rotation
        yRotation = 0.0;
        xRotation = 0.0;
        // Initialise the camera speed
        cameraSpeed = 2;
    }

    /**
     * Moves the camera forward.
     * @param deltaTime The time since the last frame, used to have frame independent camera movement.
     */
    public void moveForward(double deltaTime) {
        if (this.movingBackward) {
            this.movingForward = false;
            return;
        }
        // Y component will always remain the same
        this.coordinate.x += deltaTime * this.cameraSpeed * forward.getX();
        this.coordinate.z += deltaTime * this.cameraSpeed * forward.getZ();
    }

    /**
     * Moves the camera backward.
     * @param deltaTime The time since the last frame, used to have frame independent camera movement.
     */
    public void moveBackward(double deltaTime) {
        if (this.movingForward) {
            this.movingBackward = false;
            return;
        }
        // Y component will always remain the same
        this.coordinate.x -= deltaTime * this.cameraSpeed * forward.getX();
        this.coordinate.z -= deltaTime * this.cameraSpeed * forward.getZ();
    }

    /**
     * Moves the camera right.
     * @param deltaTime The time since the last frame, used to have frame independent camera movement.
     */
    public void moveRight(double deltaTime) {
        if (this.movingLeft) {
            this.movingRight = false;
            return;
        }
        // Y component will always remain the same
        this.coordinate.x += deltaTime * this.cameraSpeed * right.getX();
        this.coordinate.z += deltaTime * this.cameraSpeed * right.getZ();
    }

    /**
     * Moves the camera left.
     * @param deltaTime The time since the last frame, used to have frame independent camera movement.
     */
    public void moveLeft(double deltaTime) {
        if (this.movingRight) {
            this.movingLeft = false;
            return;
        }
        // Y component will always remain the same
        this.coordinate.x -= deltaTime * this.cameraSpeed * right.getX();
        this.coordinate.z -= deltaTime * this.cameraSpeed * right.getZ();
    }

    /**
     * Moves the camera up.
     * @param deltaTime The time since the last frame, used to have frame independent camera movement.
     */
    public void moveUp(double deltaTime) {
        if (this.movingDown) {
            this.movingUp = false;
            return;
        }
        // "Up" is always in the positive y direction
        this.coordinate.y += deltaTime * this.cameraSpeed;
    }

    /**
     * Moves the camera down.
     * @param deltaTime The time since the last frame, used to have frame independent camera movement.
     */
    public void moveDown(double deltaTime) {
        if (this.movingUp) {
            this.movingDown = false;
            return;
        }
        // "Down" is always in the negative y direction
        this.coordinate.y -= deltaTime * this.cameraSpeed;
    }

    /**
     * Allows the client to determine if this camera is moving forward.
     * @return True if the camera is moving forward.
     */
    public boolean isMovingForward() {
        return this.movingForward;
    }

    /**
     * Allows the client to change whether the camera is moving forward.
     * @param newMovingForwardState The new moving forward state.
     */
    public void setIsMovingForward(boolean newMovingForwardState) {
        this.movingForward = newMovingForwardState;
    }

    /**
     * Allows the client to determine if this camera is moving backward.
     * @return True if the camera is moving backward.
     */
    public boolean isMovingBackward() {
        return this.movingBackward;
    }

    /**
     * Allows the client to change whether the camera is moving backward.
     * @param newMovingBackwardState The new moving backward state.
     */
    public void setIsMovingBackward(boolean newMovingBackwardState) {
        this.movingBackward = newMovingBackwardState;
    }

    /**
     * Allows the client to determine if this camera is moving right.
     * @return True if the camera is moving right.
     */
    public boolean isMovingRight() {
        return this.movingRight;
    }

    /**
     * Allows the client to change whether the camera is moving right.
     * @param newMovingRightState The new moving right state.
     */
    public void setIsMovingRight(boolean newMovingRightState) {
        this.movingRight = newMovingRightState;
    }

    /**
     * Allows the client to determine if this camera is moving left.
     * @return True if the camera is moving left.
     */
    public boolean isMovingLeft() {
        return this.movingLeft;
    }

    /**
     * Allows the client to change whether the camera is moving left.
     * @param newMovingLeftState The new moving left state.
     */
    public void setIsMovingLeft(boolean newMovingLeftState) {
        this.movingLeft = newMovingLeftState;
    }

    /**
     * Allows the client to determine if the camera is moving up.
     * @return True if the camera is moving up.
     */
    public boolean isMovingUp() {
        return this.movingUp;
    }

    /**
     * Allows the client to change whether the camera is moving up.
     * @param newMovingUpState The new moving up state.
     */
    public void setIsMovingUp(boolean newMovingUpState) {
        this.movingUp = newMovingUpState;
    }

    /**
     * Allows the client to determine if the camera is moving down.
     * @return True if the camera is moving down.
     */
    public boolean isMovingDown() {
        return this.movingDown;
    }

    /**
     * Allows the client to change whether the camera is moving down.
     * @param newMovingDownState The new moving down state.
     */
    public void setIsMovingDown(boolean newMovingDownState) {
        this.movingDown = newMovingDownState;
    }

    /**
     * Gives the client access to this camera's speed.
     * @return The camera's speed as a double.
     */
    public double getCameraSpeed() {
        return this.cameraSpeed;
    }

    /**
     * Allows the client to change this camera's speed.
     * @param newCameraSpeed The new speed for the camera.
     */
    public void setCameraSpeed(double newCameraSpeed) {
        this.cameraSpeed = newCameraSpeed;
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
     * Gives the client access to the direction this camera is facing.
     * @return A Vector representing the direction the camera is facing.
     */
    public Vector getLookingDirection() {
        return this.lookingDirection;
    }

    /**
     * Allows the client to update the direction that the camera is looking in.
     * @param newLookingDirection The new direction the camera is looking in.
     */
    public void setLookingDirection(Vector newLookingDirection) {
        this.lookingDirection = newLookingDirection;
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
        // Convert 70 degrees down to its half angle in radians
        double fovRadians = Math.toRadians(this.fov / 2.0);
        return 1.0 / Math.tan(fovRadians);
    }
}
