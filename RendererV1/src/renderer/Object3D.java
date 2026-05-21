package renderer;

public class Object3D {
    Point3D[] vertices;
    Edge[] edges;
    Point3D centre;

    public Object3D() {
        // Doesn't do anything, just allows client to create a new 3D object and specify the vertices and edges later.
    }

    public Object3D(Point3D[] vertices, Edge[] edges, Point3D centre) {
        this.vertices = vertices;
        this.edges = edges;
        this.centre = centre;
    }

    @Override
    public Object3D clone() {
        return new Object3D(this.vertices.clone(), this.edges.clone(), this.centre.clone());
    }

    /**
     * Gives the client access to this Object's centre as a 3D point.
     * @return A Point3D representing the object's centre.
     */
    public Point3D getObjectCentre() {
        return this.centre;
    }
}
