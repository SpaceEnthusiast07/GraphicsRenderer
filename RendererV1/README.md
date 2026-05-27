# RendererV1

## About
This is my first <b>software 3D renderer</b> using Java Swing. It renders the wireframe of a rotating cube and provides functionality to move the camera using WASD (forward, left, backward, and right), SPACE (up) and LCTRL (down).

One notable convention this project uses is the ordering of the 3 axes. In mathematics, you use the left hand rule where your thumb points along the positive z-axis, your index finger points along the positive y-axis, and your middle finger along the positive x-axis. However, in my renderer, you still use the left hand rule, but your thumb points along the positive y-axis, your index finger points along the positive z-axis, and your middle finger along the positive x-axis. This is done so that the x and y axes map nicely to the x and y axes of 2D screen space.


## Future Improvements
<ul>
    <li>Cube vertex data is hard coded into the program, rendering (no pun intended 😂) it hard to modify the shape's data. Therefore, write an "obj" file parser to allow the program to render all sorts of 3D objects.</li>
    <li>The renderer only renders a wireframe of the cube, so add functionality to render triangles for solid faces. This will involve creating new triangles when certain (but not all) verticies pass behind the camera, which utilises line and plane intersections.</li>
    <li>Camera movement is limited to linear movement. So, improve the renderer by allowing mouse movement to rotate the camera about its y and x axes.</li>
</ul>