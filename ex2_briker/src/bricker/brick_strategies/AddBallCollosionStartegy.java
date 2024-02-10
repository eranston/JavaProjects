package bricker.brick_strategies;

import bricker.gameobjects.Ball;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;


import java.util.Random;

public class AddBallCollosionStartegy extends BasicCollisionStrategy implements CollisionStrategy {

    private Sound collisionSound;
    private static final int BALL_SPEED = 400;
    private Vector2 dimenstions;
    private Renderable renderable;




    public AddBallCollosionStartegy(GameObjectCollection gameObjects,Sound collisionSound,
                                    Renderable renderable , Vector2 dimenstions, Counter counter){
        super(gameObjects,counter);

        this.dimenstions = dimenstions;
        this.collisionSound = collisionSound;
        this.renderable = renderable;

    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        super.onCollision(thisObj, otherObj);

        Ball ball1 = new Ball(Vector2.ZERO,dimenstions,renderable,
                collisionSound);
        Ball ball2 = new Ball(Vector2.ZERO,dimenstions,renderable,
                collisionSound);
        ball1.setCenter(thisObj.getCenter());
        ball2.setCenter(thisObj.getCenter());
        ball1.setVelocity(randomDirection());
        ball2.setVelocity(randomDirection());
        gameObjects.addGameObject(ball1);
        gameObjects.addGameObject(ball2);

    }
    private Vector2 randomDirection(){
        float ballVelX = BALL_SPEED;
        float ballVelY = BALL_SPEED;
        Random rand = new Random();
        double angle = rand.nextDouble()*Math.PI;

        float velocityX = (float)Math.cos(angle)*ballVelX;
        float velocityY = (float)Math.sin(angle)*ballVelY;
        return new Vector2(velocityX, velocityY);
    }
}
