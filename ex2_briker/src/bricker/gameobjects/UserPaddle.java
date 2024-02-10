package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;

public class UserPaddle extends GameObject {
    private static final float MOVEMENT_SPEED = 400;
    private UserInputListener inputListener;
    private Vector2 windowDementions;
    /**
     * Construct a new GameObject instance.
     *  @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     * @param inputListener
     */
    public UserPaddle(Vector2 topLeftCorner, Vector2 dimensions,
                      Renderable renderable, UserInputListener inputListener,Vector2 windowDementions) {
        super(topLeftCorner, dimensions, renderable);
        this.inputListener = inputListener;
        this.windowDementions = windowDementions;
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Vector2 movementDir = Vector2.ZERO;
        float farRightXCordinates = getCenter().x() + getDimensions().x()/2 ;
        float farLeftXCordinates = getCenter().x() - getDimensions().x()/2 ;

        if(inputListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            movementDir = movementDir.add(Vector2.LEFT);
            if(farLeftXCordinates <= 0){
                movementDir = Vector2.ZERO;
            }
        }
        if(inputListener.isKeyPressed(KeyEvent.VK_RIGHT)) {

            movementDir = movementDir.add(Vector2.RIGHT);
            if(farRightXCordinates >= this.windowDementions.x()){
                movementDir = Vector2.ZERO;
            }
        }
        setVelocity(movementDir.mult(MOVEMENT_SPEED));
    }
}
