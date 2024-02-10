package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * This class is responsible for the life in the game.
 * The class can add a number of life, or sub the number of life.
 */
public class GraphicLifeCounter implements LifeCounter{
    private static final int LIFE_THRESHOLD_LIMIT = 4;
    private static final int SPACE_BETWEEN_HEARTS = 5;
    private int lifeCounter;
    private GameObject[] hearts;
    private int lifeThreshold = LIFE_THRESHOLD_LIMIT;

    private Renderable renderable;
    private Vector2 topLeftCorner;

    private Vector2 dimensions;
    private GameObjectCollection gameObject;


    /**
     * constructor
     * @param topLeftCorner: the left corner of the sceen
     * @param dimensions: Width and height in window coordinates.
     * @param renderable: The renderable representing the object.
     *                  Can be null, in which case the GameObject will not be rendered.
     * @param numberOfLifes: the number of life that the player have
     */
    public GraphicLifeCounter(Vector2 topLeftCorner, Vector2 dimensions,
                              Renderable renderable, int numberOfLifes ,GameObjectCollection gameObject ){

        this.lifeCounter = 0;
        this.gameObject = gameObject;
        this.hearts = new GameObject[lifeThreshold];
        this.topLeftCorner = topLeftCorner;
        this.renderable = renderable;
        this.dimensions = dimensions;
        for(int i = 0 ; i < numberOfLifes ; i++){
            addLife();
        }
    }

    /**
     * This function remove one life for the user
     */
    @Override
    public void removeLife(){
        if(lifeCounter > 0){
            gameObject.removeGameObject(hearts[lifeCounter-1] , Layer.UI);
            this.lifeCounter--;
        }

    }


    /**
     * This function add one life for the user
     */
    @Override
    public void addLife(){
        if(lifeCounter < lifeThreshold){
            Vector2 heartPosition = new Vector2(topLeftCorner.x() +
                    (lifeCounter+1)*(dimensions.x()+SPACE_BETWEEN_HEARTS), topLeftCorner.y() );
            this.hearts[lifeCounter] = new GameObject(heartPosition , dimensions , renderable);
            this.gameObject.addGameObject(this.hearts[lifeCounter] , Layer.UI);
            this.lifeCounter++;
        }
    }

    /**
     * This function return the number of current life
     * @return: number of current life
     */
    @Override
    public int getLife(){
        return this.lifeCounter;
    }



}
