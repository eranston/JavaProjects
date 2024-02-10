package bricker.gameobjects;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
public class Heart extends GameObject{

    public Heart(Heart heartToDuplicate){
        super(new Vector2(heartToDuplicate.getCenter()),
                new Vector2(heartToDuplicate.getDimensions()),
                heartToDuplicate.renderer().getRenderable());
        this.setCenter(heartToDuplicate.getCenter());
        this.setVelocity(heartToDuplicate.getVelocity());
    }
    public Heart(Vector2 center, Vector2 dimensions, Renderable renderable, Vector2 velocity) {
        super(center, dimensions, renderable);
        this.setCenter(center);
        this.setVelocity(velocity);

    }
    @Override
    public boolean shouldCollideWith(GameObject other) {
        if(other.getTag().equals("UserPaddle")){
            return true;
        }
        return false;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
    }
}
