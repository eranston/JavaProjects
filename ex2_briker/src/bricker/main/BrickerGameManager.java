package bricker.main;

import bricker.brick_strategies.*;
//import bricker.brick_strategies.Factory;
import bricker.gameobjects.*;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.*;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.awt.*;
import java.sql.SQLOutput;
import java.util.Random;

public class BrickerGameManager extends GameManager {

    private static final int DEFAULT_ROWS_OF_BRICK = 7;
    private static final int DEFUALT_LIFE = 3;
    private static final int HIGHT_OF_BRICKS = 15;
    private static final int SPACE_BETWEEN_BRICKS = 5;
    private static final int SPACE_BETWEEN_HEARTS = 5;
    private static final int SPACE_BETWEEN_ROWS_BRICKS = 5;
    private static final int DEFAULT_BRICKS_IN_ROW = 8;
    private static final int BORDER_WIDTH = 10;
    private static final int PADDLE_HEIGHT = 20;
    private static final int PADDLE_WIDTH = 100;
    private static final int BALL_RADIUS = 35;
    private static final float BALL_SPEED = 350;
    private static final float ANOTHER_PADDLE = 6;
    private static final Renderable BORDER_RENDERABLE =
            new RectangleRenderable(new Color(80, 140, 250));
    private static final Vector2 LIFE_DIMENSIONS = new Vector2(20,20);
    private static final Vector2 HEART_VELOCITY = new Vector2(0,100);

    private LifeCounter[] lifeCounter;
    private Ball ball;
    private Vector2 windowDimensions;
    private WindowController windowController;
    private UserInputListener inputListener;
    private int brickRows;
    private int brickInEachRow;
    private Counter countCurrentBricks;
//    private Factory factory;
    private ImageReader imageReader;
    private SoundReader soundReader;


    // for the "factory"
    private static final String BASIC = "basic";
    private static final String BALL = "ball";
    private static final String PADDLE = "paddle";
    private static final String CAMERA = "camera";
    private static final String MULTIPLE = "multiple";
    private static final String STRIKE = "strike";
    private static final int NUMBERS_FOR_BASIC_STRATEGY = 4;
    private static final int NUMBERS_FOR_ANOTHER_BALL = 5;
    private static final int NUMBERS_FOR_ANOTHER_PADDLE = 6;
    private static final int NUMBERS_FOR_CHANGE_CAMERA = 7;
    private static final int NUMBERS_FOR_RETURN_STRIKE = 8;
    private static final int NUMBERS_FOR_MULTIPLE_BEHAVIOR = 9;



    public BrickerGameManager(String windowTitle, Vector2 windowDimensions,
                              int brickRows , int brickInEachRow ) {
        super(windowTitle, windowDimensions);
        this.brickRows = brickRows;
        this.brickInEachRow = brickInEachRow;
        this.lifeCounter = new LifeCounter[2];
        this.countCurrentBricks = new Counter();
    }

    @Override
    public void initializeGame(ImageReader imageReader,
                               SoundReader soundReader,
                               UserInputListener inputListener,
                               WindowController windowController) {
        this.windowController = windowController;
        //initialization
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        windowDimensions = windowController.getWindowDimensions();
        //initialize image and sound reader
        this.imageReader = imageReader;
        this.soundReader = soundReader;
        //initialize input listener
        this.inputListener = inputListener;
        //create background
        Renderable backgroundImage = imageReader.readImage(
                "ex2_briker/assets/DARK_BG2_small.jpeg", false);
        gameObjects().addGameObject(new GameObject(Vector2.ZERO,new Vector2(windowDimensions.x(),windowDimensions.y()),backgroundImage), Layer.BACKGROUND);

        //create ball
        createBall(imageReader, soundReader, windowController);

        //create main paddle
        Renderable paddleImage = imageReader.readImage(
                "ex2_briker/assets/paddle.png", false);

        createUserPaddle(paddleImage, inputListener, windowDimensions,
                    windowDimensions.x()/2, (int)windowDimensions.y()-30);

        //create life counter
        Renderable heartImage = imageReader.readImage(
                "ex2_briker/assets/heart.png", true);
        TextRenderable textRender = new TextRenderable("0");
        this.lifeCounter[0] = createGraphicLifeCounter(heartImage,DEFUALT_LIFE);
        this.lifeCounter[1] = createNumericLifeCounter(textRender,DEFUALT_LIFE);

        //create brick
        Renderable brickImage =
                imageReader.readImage("ex2_briker/assets/brick.png", false);
        createBrickMatrix(brickImage, countCurrentBricks);

        //create borders
        createBorders(windowDimensions);
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        checkForGameEnd();
    }
    private NumericLifeCounter createNumericLifeCounter(TextRenderable renderable, int numberOfLifes){
        Vector2 startingPosition =new Vector2(BORDER_WIDTH + SPACE_BETWEEN_HEARTS, (int)windowDimensions.y()-30);
        Vector2 dimensions = new Vector2(10 , 10);

        NumericLifeCounter lifecounter = new NumericLifeCounter(startingPosition,
                new Vector2(20,20),numberOfLifes,renderable,gameObjects());

        return lifecounter;

    }
    private GraphicLifeCounter createGraphicLifeCounter(Renderable renderable, int numberOfLifes){
        Vector2 startingPosition = Vector2.ZERO;
        Vector2 dimensions = new Vector2(10 , 10);

        GraphicLifeCounter lifecounter = new GraphicLifeCounter(new Vector2(
                BORDER_WIDTH + SPACE_BETWEEN_HEARTS, (int)windowDimensions.y()-30),
                new Vector2(20,20),renderable,numberOfLifes,gameObjects());

        return lifecounter;

    }

    /**
     * This function create all the bricks in the game
     * @param brickImage: the image of the brick
     */
    private void createBrickMatrix(Renderable brickImage, Counter countCurrentBricks){
        float x = windowDimensions.x(); //get x dimension
        float y = windowDimensions.y(); //get y dimension
        x = x - (2 * BORDER_WIDTH) - ((2 + brickInEachRow-1)* SPACE_BETWEEN_BRICKS);
        //TODO:check if the size is correct
        float widthOfBricks = x / brickInEachRow;
        float startingOfBricks = BORDER_WIDTH + SPACE_BETWEEN_BRICKS;

        for(int row = 0 ; row < brickRows ; row++ ){
            for(int rowIndex = 0 ; rowIndex < brickInEachRow ; rowIndex++){
                float xStartIndex = startingOfBricks + rowIndex*(widthOfBricks+SPACE_BETWEEN_BRICKS);
                //this vector is the location of the brick
                Vector2 startIndex = new Vector2( xStartIndex, SPACE_BETWEEN_ROWS_BRICKS+BORDER_WIDTH + row * (SPACE_BETWEEN_ROWS_BRICKS+HIGHT_OF_BRICKS));
                createBrick(startIndex ,new Vector2(widthOfBricks ,HIGHT_OF_BRICKS ),brickImage, countCurrentBricks);
                this.countCurrentBricks.increment(); //increase the number of bricks
            }
        }
    }

    /**
     * this function create one brick
     * @param brickImage: the brick image
     * @param startingPosition: starting position to create the brick
     * @param size: the size of one brick
     */
    private void createBrick(Vector2 startingPosition ,Vector2 size, Renderable brickImage,
                             Counter countCurrentBricks){
        CollisionStrategy strategy = randomNumbers();
        Brick brick = new Brick(startingPosition ,
                size,
                brickImage,
                strategy);
        gameObjects().addGameObject(brick); //add a brick to the game object
    }


    /**
     * This function check if the game ended
     */
    private void checkForGameEnd() {
        double ballHeight = ball.getCenter().y();
        String prompt = "";
        //we win
        if(this.countCurrentBricks.value() == 0){
            prompt = "You win!";
        }
        //we lose
        if(ballHeight > windowDimensions.y()) {
            for(LifeCounter counter :lifeCounter){
                counter.removeLife();
            }

            if(lifeCounter[0].getLife() == 0){
                prompt = "You Lose!";
            }
            else {
                ball.setCenter(windowDimensions.mult(0.5f));
                ball.setVelocity(randomDirection());
            }
        }
        if(!prompt.isEmpty()) {
            prompt += " Play again?";
            if(windowController.openYesNoDialog(prompt))
                windowController.resetGame();
            else
                windowController.closeWindow();
        }
    }
    

    private void createBall(ImageReader imageReader, SoundReader soundReader, WindowController windowController) {
        Renderable ballImage =
                imageReader.readImage("ex2_briker/assets/ball.png", true);
        Sound collisionSound = soundReader.readSound("ex2_briker/assets/blop.wav");
        ball = new Ball(
                Vector2.ZERO, new Vector2(BALL_RADIUS, BALL_RADIUS), ballImage, collisionSound);

        Vector2 windowDimensions = windowController.getWindowDimensions();
        ball.setCenter(windowDimensions.mult(0.5f));
        gameObjects().addGameObject(ball);
        ball.setVelocity(randomDirection());
    }

    /**
     * This function create a user paddle
     * @param paddleImage: the image of the paddle
     * @param inputListener: the input
     * @param windowDimensions: the window dimension
     * @param x: parameter for set center
     * @param y: parameter fot set center
     */
    private void createUserPaddle(Renderable paddleImage, UserInputListener inputListener,
                                  Vector2 windowDimensions, float x, float y) {
        GameObject userPaddle = new UserPaddle(
                Vector2.ZERO,
                new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT),
                paddleImage,
                inputListener,
                windowDimensions);
        userPaddle.setCenter(new Vector2(x,y));
        userPaddle.setTag("UserPaddle");
        gameObjects().addGameObject(userPaddle);

    }

    /**
     * This function intialize the screen borders
     * @param windowDimensions: the size of the window
     */
    private void createBorders(Vector2 windowDimensions) {
        gameObjects().addGameObject(
                new GameObject(
                        new Vector2(0,0),
                        new Vector2(windowDimensions.x() , BORDER_WIDTH),
                        null)
                );
        gameObjects().addGameObject(
                new GameObject(
                        Vector2.ZERO,
                        new Vector2(BORDER_WIDTH, windowDimensions.y()),
                        null)
        );
        gameObjects().addGameObject(
                new GameObject(
                        new Vector2(windowDimensions.x()-BORDER_WIDTH, 0),
                        new Vector2(BORDER_WIDTH, windowDimensions.y()),
                        null)
        );
    }



    //create relevant instance for strategy
    /**
     * This function helps us to create a strategy that we need
     * @param name: the name of the strategy
     * @return: new strategy
     */
    private CollisionStrategy choseStrategy(String name ){
        CollisionStrategy strategy;
        switch (name){
            case (BASIC):
                strategy = new BasicCollisionStrategy(gameObjects() , this.countCurrentBricks);
                break;
            case (BALL):
                Renderable puckImage =
                        imageReader.readImage("ex2_briker/assets/mockBall.png", true);
                Sound collisionSound = soundReader.readSound("ex2_briker/assets/blop.wav");

                Vector2 dimenstionsPuck = new Vector2(26,26);
                strategy = new AddBallCollosionStartegy(gameObjects(),collisionSound ,puckImage,
                                                       dimenstionsPuck, this.countCurrentBricks);
                break;
            case (PADDLE):
                strategy = new NewPaddleCollisionStrategy(gameObjects(), this.imageReader.readImage(
                        "ex2_briker/assets/paddle.png", false),this.inputListener ,
                        this.windowDimensions, this.countCurrentBricks);
                break;
//            case (CAMERA):
//            case (MULTIPLE):
            case (STRIKE):
                Renderable heart_image = imageReader.readImage(
                        "ex2_briker/assets/heart.png", true);
                Heart heart = new Heart(Vector2.ZERO,LIFE_DIMENSIONS,
                        heart_image,HEART_VELOCITY );
                strategy = new AddLifeStartegy(gameObjects(),this.countCurrentBricks,heart);
                break;

            default:
                return null;
        }
        return strategy;
    }

    private CollisionStrategy randomNumbers(){
        Random random = new Random();
        int randomNumber = random.nextInt(10);
        if (randomNumber <= NUMBERS_FOR_BASIC_STRATEGY){//if this is basic strategy
            return choseStrategy(BASIC);
        }
        if (randomNumber == NUMBERS_FOR_ANOTHER_BALL){//if we need another ball
            return choseStrategy(BALL);
        }
        if (randomNumber == NUMBERS_FOR_ANOTHER_PADDLE){//if we need another paddle
            return choseStrategy(PADDLE);
        }
        if(randomNumber == NUMBERS_FOR_RETURN_STRIKE){//if we need to change camera
            return choseStrategy(STRIKE);
        }
        else{
            return choseStrategy(BASIC);
        }

//        else if (randomNumber == NUMBERS_FOR_ANOTHER_BALL){//if we need another paddle
//            return choseStrategy(BALL);
//        }
//
//        else if (randomNumber == NUMBERS_FOR_ANOTHER_PADDLE){//if we need another paddle
//            return choseStrategy(PADDLE);
//        }
//
//        else if (randomNumber == NUMBERS_FOR_CHANGE_CAMERA){//if we need to change camera
//            return choseStrategy(CAMERA);
//        }
//
//        else if (randomNumber == NUMBERS_FOR_RETURN_STRIKE){//if we need to return a strike
//            return choseStrategy(STRIKE);
//        }
//
//        else if (randomNumber == NUMBERS_FOR_MULTIPLE_BEHAVIOR){//if we need to do multiple behavior
//            return choseStrategy(MULTIPLE);
//        }
//        return null;
    }

    private Vector2 randomDirection(){
        float ballVelX = BALL_SPEED;
        float ballVelY = BALL_SPEED;
        Random rand = new Random();
        if(rand.nextBoolean())
            ballVelX *= -1;
        if(rand.nextBoolean())
            ballVelY *= -1;
        return new Vector2(ballVelX, ballVelY);
    }
    public static void main(String[] args) {
        BrickerGameManager game;
        if (args.length == 0 ){ //if there is no arguments
            game = new BrickerGameManager(
                    "Bouncing Ball",
                    new Vector2(700, 500) ,
                    DEFAULT_ROWS_OF_BRICK ,
                    DEFAULT_BRICKS_IN_ROW);
            game.run();
        }
        else {
            game = new BrickerGameManager(
                    "Bouncing Ball",
                    new Vector2(700, 500) ,
                    Integer.valueOf(args[1]) ,
                    Integer.valueOf(args[0]));
            game.run();
        }

    }

}
