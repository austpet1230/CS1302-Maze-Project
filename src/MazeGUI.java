import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

/**********************************************
 * CS 1302 Final Project - The Maze
 * Created by Jack Smith and Austin Pettit
 * CS1302/07 - T. Kilinc
 * Fall 2017
 *********************************************/
public class MazeGUI extends Application{
    protected Scene mainMenu, gameScene;
    public int gameSceneW, gameSceneH;
    private static final String DING_URL = "src/resources/moveSound.mp3";
    private static final String INTRO_URL = "src/resources/intro.mp3";

    @Override
    public void start(Stage primaryStage){
        // MAIN MENU
        BorderPane borderPane = new BorderPane();

        // TITLE BAR
        HBox titleBox = new HBox(5);
        titleBox.setAlignment(Pos.CENTER);
        Text txtTitle = new Text("Welcome to the Maze!");
        txtTitle.setFont(Font.font(25));
        titleBox.getChildren().addAll(txtTitle);

        // BUTTON PANEL
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        Button btStartMaze = new Button("Start Maze");
        btStartMaze.setStyle("-fx-base: #43ef6b");
        btStartMaze.setPrefSize(150,150);
        Button btQuit = new Button("Quit");
        btQuit.setStyle("-fx-base: #ef5f43");
        btQuit.setPrefSize(150,150);
        buttonBox.getChildren().addAll(btStartMaze, btQuit);

        AudioClip dingSound = new AudioClip(new File(DING_URL).toURI().toString());
        AudioClip introSound = new AudioClip(new File(INTRO_URL).toURI().toString());

        btStartMaze.setOnAction(e->{
            introSound.play();
            // ACTIVATES GAME WINDOW WITH MAZE
            BorderPane gameLayout = new BorderPane();
            MazePane mazePane = new MazePane();
            gameLayout.setCenter(mazePane);

            HBox gameButtons = new HBox(5);
            gameButtons.setAlignment(Pos.CENTER);
            gameButtons.setPadding(new Insets(5));
            while(introSound.isPlaying()){
                gameButtons.setDisable(true);
            }
            gameButtons.setDisable(false);

            // TAKE STEP BUTTON
            Button btTakeStep = new Button("Take Step");
            btTakeStep.setPrefSize(200, 30);
            btTakeStep.setStyle("-fx-base: #5eceff");

            // TAKE 5 STEP BUTTON
            Button bt5TakeStep = new Button("Take 10 Steps");
            bt5TakeStep.setPrefSize(200, 30);
            bt5TakeStep.setStyle("-fx-base: #5eceff");

            // AUTO SOLVE MAZE BUTTON
            Button btSolveMaze = new Button("Solve Maze");
            btSolveMaze.setPrefSize(200, 30);
            btSolveMaze.setStyle("-fx-base: #51ff7f");

            // GIVE UP BUTTON
            Button btGiveUp = new Button("Give Up");
            btGiveUp.setPrefSize(200, 30);
            btGiveUp.setStyle("-fx-base: #ff7351");

            Text txtCoordLabel = new Text("Current Coords: ");
            Text txtXLabel = new Text("0");
            Text txtSpace = new Text(" ");
            Text txtYLabel = new Text("0");

            Text txtGoalLabel = new Text("Goal Coords: ");
            Text txtGXLabel = new Text("0");
            Text txtGSpace = new Text(" ");
            Text txtGYLabel = new Text("0");

            btTakeStep.setOnAction(step->{
                mazePane.takeStep();
                txtXLabel.setText(Integer.toString(mazePane.getCurrentXCoord()));
                txtYLabel.setText(Integer.toString(mazePane.getCurrentYCoord()));
                txtGXLabel.setText(Integer.toString(mazePane.getGoalXCoord()));
                txtGYLabel.setText(Integer.toString(mazePane.getGoalYCoord()));
                dingSound.play();
            });

            bt5TakeStep.setOnAction(s->{
                for(int i = 0; i < 10; i++){
                    txtXLabel.setText(Integer.toString(mazePane.getCurrentXCoord()));
                    txtYLabel.setText(Integer.toString(mazePane.getCurrentYCoord()));
                    txtGXLabel.setText(Integer.toString(mazePane.getGoalXCoord()));
                    txtGYLabel.setText(Integer.toString(mazePane.getGoalYCoord()));
                    mazePane.takeStep();
                }
                dingSound.play();
            });

            gameButtons.getChildren().addAll(btTakeStep, bt5TakeStep, btSolveMaze, btGiveUp, txtCoordLabel, txtXLabel, txtSpace, txtYLabel, txtGoalLabel, txtGXLabel, txtGSpace, txtGYLabel);

            gameLayout.setBottom(gameButtons);

            gameScene = new Scene(gameLayout);
            primaryStage.setScene(gameScene);
            primaryStage.show();
        });
        btQuit.setOnAction(e->{
            System.out.println("User terminated GUI program");
            System.exit(-1);
        });

        borderPane.setTop(titleBox);
        borderPane.setCenter(buttonBox);
        mainMenu = new Scene(borderPane, 400,300);
        primaryStage.setScene(mainMenu);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Made by Austin and Jack");
        primaryStage.show();
    }
}

class MazePane extends GridPane {
    private Maze maze;
    private Image wallImg = new Image("resources/stonetexture.JPG");
    private Image pathImg = new Image("resources/pathtexture.jpg");
    private Image currentPathImg = new Image("resources/currentPathTexture.jpg");
    private Image goalImage = new Image("resources/goaltexture.jpg");
    private int[][] mazePath = {
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0},
            {0,0,1,1,1,1,1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,1,0},
            {0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0},
            {0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

    public MazePane(){
        // create new instance of maze with the maze, mazePath
        maze = new Maze(mazePath);
        updateImages();
    }

    public void updateImages(){
        for(int i = 0; i < mazePath.length; i++){
            for(int j = 0; j < mazePath[i].length; j++){
                switch(mazePath[i][j]){
                    case 0:
                        // wall
                        add(new ImageView(wallImg), j, i);
                        break;
                    case 1:
                        // path
                        add(new ImageView(pathImg), j, i);
                        break;
                    case 2:
                        // travelled
                        add(new ImageView(pathImg), j, i);
                        break;
                    case 3:
                        // current position
                        add(new ImageView(currentPathImg), j, i);
                        break;
                    case 4:
                        // goal
                        add(new ImageView(goalImage), j , i);
                        break;
                }
            }
        }
    }

    public void takeStep(){
        maze.takeStep();
        updateImages();
    }

    public int getCurrentXCoord(){
        return maze.currentX;
    }
    public int getCurrentYCoord(){
        return maze.currentY;
    }

    public int getGoalXCoord(){
        return maze.goalX;
    }
    public int getGoalYCoord(){
        return maze.goalY;
    }
}
