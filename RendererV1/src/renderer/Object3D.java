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
        //return new Object3D(this.vertices.clone(), this.edges.clone(), this.centre.clone());
        Object3D clondeObject = new Object3D();

        // Clone the centre point
        clondeObject.centre = new Point3D(this.centre.x, this.centre.y, this.centre.z);

        // Clone each vertex
        clondeObject.vertices = new Point3D[this.vertices.length];
        Point3D vertex;
        for (int i = 0; i < vertices.length; i++) {
            vertex = this.vertices[i];
            clondeObject.vertices[i] = new Point3D(vertex.x, vertex.y, vertex.z); 
        }

        // Copy over the reference to the edges as these only require a shallow copy
        clondeObject.edges = this.edges;

        return clondeObject;
    }

    /**
     * Gives the client access to this Object's centre as a 3D point.
     * @return A Point3D representing the object's centre.
     */
    public Point3D getObjectCentre() {
        return this.centre;
    }
}
