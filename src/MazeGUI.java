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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**********************************************
 * CS 1302 Final Project - The Maze
 * Created by Jack Smith and Austin Pettit
 * CS1302/07 - T. Kilinc
 * Fall 2017
 *********************************************/
public class MazeGUI extends Application{
    protected Scene mainMenu, gameScene;
    public int gameSceneW, gameSceneH;

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

        btStartMaze.setOnAction(e->{
            // ACTIVATES GAME WINDOW WITH MAZE
            BorderPane gameLayout = new BorderPane();
            MazePane mazePane = new MazePane();
            gameLayout.setCenter(mazePane);

            HBox gameButtons = new HBox(5);
            gameButtons.setAlignment(Pos.CENTER);
            gameButtons.setPadding(new Insets(5));

            Button btTakeStep = new Button("Take Step");
            btTakeStep.setPrefSize(200, 30);
            btTakeStep.setStyle("-fx-base: #5eceff");

            btTakeStep.setOnAction(step->mazePane.takeStep());

            Button btSolveMaze = new Button("Solve Maze");
            btSolveMaze.setPrefSize(200, 30);
            btSolveMaze.setStyle("-fx-base: #51ff7f");

            Button btGiveUp = new Button("Give Up");
            btGiveUp.setPrefSize(200, 30);
            btGiveUp.setStyle("-fx-base: #ff7351");
            gameButtons.getChildren().addAll(btTakeStep, btSolveMaze, btGiveUp);

            gameLayout.setBottom(gameButtons);

            gameScene = new Scene(gameLayout);
            primaryStage.setScene(gameScene);
            primaryStage.show();
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
    private Image wallImg = new Image("img/stonetexture.JPG");
    private Image pathImg = new Image("img/pathtexture.jpg");
    private Image currentPathImg = new Image("img/currentPathTexture.jpg");
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
                }
            }
        }
    }

    public void takeStep(){
        maze.takeStep();
        updateImages();
    }
}
