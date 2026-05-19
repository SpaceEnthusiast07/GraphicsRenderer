package renderer;

public class Vector {
    private int x;
    private int y;
    private int z;

    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Allows the client to obtain the x coordinate of this Vector.
     * @return The x coordinate as an integer.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Allows the client to change the x coordinate of this Vector.
     * @param newX The new x coordinate value.
     */
    public void setX(int newX) {
        this.x = newX;
    }

    /**
     * Allows the client to obtain the y coordinate of this Vector.
     * @return The y coordinate as an integer.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Allows the client to change the y coordinate of this Vector.
     * @param newY The new y coordinate value.
     */
    public void setY(int newY) {
        this.y = newY;
    }

    /**
     * Allows the client to obtain the z coordinate of this Vector.
     * @return The z coordinate as an integer.
     */
    public int getZ() {
        return this.z;
    }

    /**
     * Allows the client to change the z coordinate of this Vector.
     * @param newZ The new z coordinate value.
     */
    public void setZ(int newZ) {
        this.z = newZ;
    }
}
