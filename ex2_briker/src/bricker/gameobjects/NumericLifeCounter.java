package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.rendering.Renderable;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Vector2;
import org.w3c.dom.Text;

import java.awt.*;

public class NumericLifeCounter implements LifeCounter{
    private static final int GREEN_THRESHOLD = 3;
    private static final int YELLOW_THRESHOLD = 2;
    private static final int RED_THRESHOLD = 1;
    private static final int LIFE_THRESHOLD_LIMIT = 4;

    private int lifeThreshold = LIFE_THRESHOLD_LIMIT;
    private int numberOfLife;
    private TextRenderable renderable;
    private GameObjectCollection gameObjects;

    public NumericLifeCounter(Vector2 topLeftCorner, Vector2 dimensions, int numberOfLife , TextRenderable renderable,
                              GameObjectCollection gameObjects){
        this.numberOfLife = 0;
        this.renderable = renderable;
        for(int i = 0 ; i < numberOfLife ; i++){
            addLife();
        }
        gameObjects.addGameObject(new GameObject(topLeftCorner,dimensions,this.renderable), Layer.UI);
    }
    @Override
    public void removeLife() {
        if(numberOfLife > 0) {
            numberOfLife--;
            updateLifeCounter(numberOfLife);
        }
    }

    @Override
    public void addLife() {
        if(numberOfLife < lifeThreshold) {
            numberOfLife++;
            updateLifeCounter(numberOfLife);
        }
    }

    @Override
    public int getLife() {
        return numberOfLife;
    }

    private void updateLifeCounter(int updatedLifeCounter){
        renderable.setString(String.valueOf(updatedLifeCounter));
        if (updatedLifeCounter >= GREEN_THRESHOLD) {
            renderable.setColor(Color.GREEN);
        }
        if (updatedLifeCounter == YELLOW_THRESHOLD) {
            renderable.setColor(Color.YELLOW);
        }
        if (updatedLifeCounter == RED_THRESHOLD) {
            renderable.setColor(Color.RED);
        }
    }
}
