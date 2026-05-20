package renderer;

public class Scene {
    private Object3D[] objects;

    public Scene(Object3D[] objects) {
        this.objects = objects;
    }

    /**
     * Gives the client access to the collection of objects within the Scene to render.
     * @return An array of Object3D objects.
     */
    public Object3D[] getObjects() {
        return this.objects;
    }

    /**
     * Allows the client to update the array of objects within the current Scene.
     * @param newObjects The array of new Object3D objects.
     */
    public void setObjects(Object3D[] newObjects) {
        this.objects = newObjects;
    }
}
