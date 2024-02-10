package bricker.gameobjects;

import bricker.brick_strategies.BasicCollisionStrategy;
import bricker.brick_strategies.CollisionStrategy;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

/**
 *
 */
public class Brick extends GameObject {

    private CollisionStrategy startegy;
    private Counter countCurrentBricks;


    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param windowDimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     */
    public Brick(Vector2 topLeftCorner, Vector2 windowDimensions, Renderable renderable , CollisionStrategy startegy) {
        super(topLeftCorner, windowDimensions, renderable);
        this.startegy = startegy;
    }


    @Override
    /**
     * This function check if there is a collosion
     * @other: game object to check for collide
     * @collision: the collision object
     */
    public void onCollisionEnter(GameObject other, Collision collision) {
        startegy.onCollision(this , other);

    }
}


