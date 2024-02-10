package bricker.brick_strategies;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.util.Counter;

/**
 * This class implements CollisionStrategy of 2 objects
 */
public class BasicCollisionStrategy implements CollisionStrategy {
    protected Counter brickCounter;
    protected GameObjectCollection gameObjects;
    /**
     * Default constructor
     */
    public BasicCollisionStrategy(GameObjectCollection gameObjects , Counter brickCounter){
        this.brickCounter = brickCounter;
        this.gameObjects = gameObjects;
    }
    /**
     *This function responsible for to objects to collide
     * @param thisObj: one object for collision
     * @param otherObj: second object for collision
     */
    public void onCollision(GameObject thisObj, GameObject otherObj){

        if(this.gameObjects.removeGameObject(thisObj)){
            brickCounter.decrement();
        }

    }
}
