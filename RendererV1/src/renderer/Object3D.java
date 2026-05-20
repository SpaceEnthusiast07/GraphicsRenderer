package renderer;

public class Object3D {
    Point3D[] vertices;
    int[][] edges;

    public Object3D() {
        // Doesn't do anything, just allows client to create a new 3D object and specify the vertices and edges later.
    }

    public Object3D(Point3D[] vertices, int[][] edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    @Override
    public Object3D clone() {
        return new Object3D(this.vertices.clone(), this.edges.clone());
    }
}
