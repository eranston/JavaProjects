package bricker.brick_strategies;

import bricker.gameobjects.Heart;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.util.Counter;
import danogl.util.Vector2;

public class AddLifeStartegy extends BasicCollisionStrategy implements CollisionStrategy{

    private GameObject heart;
    /**
     * Default constructor
     *
     * @param gameObjects
     * @param brickCounter
     */
    public AddLifeStartegy(GameObjectCollection gameObjects, Counter brickCounter,Heart heart) {
        super(gameObjects, brickCounter);
        this.heart = new Heart(heart);
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        super.onCollision(thisObj, otherObj);
        heart.setCenter(thisObj.getCenter());
        gameObjects.addGameObject(heart, Layer.DEFAULT);
    }
}
