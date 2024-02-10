package bricker.brick_strategies;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;

/**
 * This class implements CollisionStrategy of 2 objects
 */
public class BasicCollisionStrategy implements CollisionStrategy {

    protected GameObjectCollection gameObjects;
    /**
     * Default constructor
     */
    public BasicCollisionStrategy(GameObjectCollection gameObjects){
        this.gameObjects = gameObjects;
    }
    /**
     *This function responsible for to objects to collide
     * @param thisObj: one object for collision
     * @param otherObj: second object for collision
     */
    public void onCollision(GameObject thisObj, GameObject otherObj){
        this.gameObjects.removeGameObject(thisObj);
    }
}
