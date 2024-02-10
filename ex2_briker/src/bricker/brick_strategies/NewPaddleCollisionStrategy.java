package bricker.brick_strategies;

import bricker.gameobjects.UserPaddle;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.util.Random;

/**
 * This class implements CollisionStrategy and responsible for the strategy for the current brick
 */
public class NewPaddleCollisionStrategy extends BasicCollisionStrategy implements CollisionStrategy{
    private static final int PADDLE_HEIGHT = 20;
    private static final int PADDLE_WIDTH = 100;
    private Renderable paddleImage;
    private UserInputListener inputListener;
    private Vector2 windowDimensions;
    //TODO:ASK IF I CAN PUT STATIC
    private static int countPaddle;




    public NewPaddleCollisionStrategy(GameObjectCollection gameObjects, Renderable paddleImage, UserInputListener inputListener,
                                      Vector2 windowDimensions){
        super(gameObjects);
        this.gameObjects = gameObjects;
        this.paddleImage = paddleImage;
        this.inputListener = inputListener;
        this.windowDimensions = windowDimensions;
        this.countPaddle = 1;
    }


    /**
     *This function responsible for to brick and a ball to collide
     * @param thisObj: one object for collision
     * @param otherObj: second object for collision
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj){
        super.onCollision(thisObj, otherObj);
        //create new paddle at the center
        if (countPaddle < 2){
            GameObject userPaddle = new UserPaddle(
                    Vector2.ZERO,
                    new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT),
                    paddleImage,
                    inputListener,
                    windowDimensions );
            userPaddle.setTag("additionalPaddle");
            this.countPaddle += 1;
            userPaddle.setCenter(new Vector2(windowDimensions.x()/2, (int)windowDimensions.y()/2));
            gameObjects.addGameObject(userPaddle);
        }
    }
}
