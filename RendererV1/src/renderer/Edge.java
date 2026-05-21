package renderer;

public class Edge {
    int vertex1;
    int vertex2;

    public Edge(int vertex1, int vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    @Override
    public Edge clone() {
        return new Edge(this.vertex1, this.vertex2);
    }
}
