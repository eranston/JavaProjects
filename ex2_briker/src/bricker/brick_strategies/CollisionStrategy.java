package bricker.brick_strategies;

import danogl.GameObject;

/**
 * This interface responsible for what happens when the ball hit the brick
 */
public interface CollisionStrategy {
    /**
     *This function responsible for to objects to collide
     * @param thisObj: one object for collision
     * @param otherObj: second object for collision
     */
    void onCollision(GameObject thisObj, GameObject otherObj);
}
