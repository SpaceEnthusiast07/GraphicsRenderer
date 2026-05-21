package renderer;

public class Point3D {
    double x;
    double y;
    double z;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public Point3D clone() {
        return new Point3D(this.x, this.y, this.z);
    }
}
