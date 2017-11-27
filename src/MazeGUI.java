import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    private Scene mainMenu, gameScene, winScene;
    private static final String DING_URL = "src/resources/moveSound.mp3";
    private static final String INTRO_URL = "src/resources/intro.mp3";
    private static final String WIN_URL = "src/resources/winMusic.mp3";

    @Override
    public void start(Stage primaryStage){
        // MAIN MENU
        BorderPane borderPane = new BorderPane();

        // TITLE BAR
        VBox titleBox = new VBox(5);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(10));
        Text txtTitle = new Text("Welcome to the Maze!");
        txtTitle.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 30));
        Text txtMadeBy = new Text("Made by Austin & Jack for CS 1302 - Fall 2017");
        txtMadeBy.setFont(Font.font("Trebuchet MS", 22));
        titleBox.getChildren().addAll(txtTitle, txtMadeBy);

        // BUTTON PANEL
        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button btStartMaze = new Button("Start Maze");
        btStartMaze.setStyle("-fx-base: #43ef6b");
        btStartMaze.setPrefSize(400,75);

        Button btStartMazeWithKeys = new Button("Start Maze with Arrow Keys");
        btStartMazeWithKeys.setStyle("-fx-base: #43ef6b");
        btStartMazeWithKeys.setPrefSize(400, 40);

        Button btQuit = new Button("Quit");
        btQuit.setStyle("-fx-base: #ef5f43");
        btQuit.setPrefSize(400,40);
        buttonBox.getChildren().addAll(btStartMaze, btStartMazeWithKeys, btQuit);

        // AUDIO CLIP VARS
        AudioClip dingSound = new AudioClip(new File(DING_URL).toURI().toString());
        AudioClip introSound = new AudioClip(new File(INTRO_URL).toURI().toString());
        AudioClip winSound = new AudioClip(new File(WIN_URL).toURI().toString());

        btStartMaze.setOnAction(e->{
            introSound.play();

            // ACTIVATES GAME WINDOW WITH MAZE
            BorderPane gameLayout = new BorderPane();
            MazePane mazePane = new MazePane();
            gameLayout.setCenter(mazePane);

            // HOLDS ALL LOWER PANEL GAME COMPONENTS
            VBox lowerPanel = new VBox(5);
            lowerPanel.setAlignment(Pos.CENTER);
            lowerPanel.setPadding(new Insets(5));

            // HOLDS ALL GAME BUTTONS
            HBox gameButtons = new HBox(5);
            gameButtons.setAlignment(Pos.CENTER);
            gameButtons.setPadding(new Insets(5));
            while(introSound.isPlaying()){
                gameButtons.setDisable(true);
            }
            gameButtons.setDisable(false);

            // HOLDS ALL GAME TEXT ELEMENTS:
            HBox gameText = new HBox(5);
            gameText.setAlignment(Pos.CENTER);
            gameText.setPadding(new Insets(5));

            // GAME COMPONENTS
            // TAKE STEP BUTTON
            Button btTakeStep = new Button("Take Step");
            btTakeStep.setPrefSize(300, 30);
            btTakeStep.setStyle("-fx-base: #5eceff");

            // AUTO SOLVE MAZE BUTTON
            Button btSolveMaze = new Button("Solve Maze");
            btSolveMaze.setPrefSize(300, 30);
            btSolveMaze.setStyle("-fx-base: #51ff7f");

            // GIVE UP BUTTON
            Button btGiveUp = new Button("Give Up");
            btGiveUp.setPrefSize(300, 30);
            btGiveUp.setStyle("-fx-base: #ff7351");

            Text txtCoordLabel = new Text("Current Coords: ");
            txtCoordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            Text txtCLabel = new Text("(0, 0)");

            Text txtGoalLabel = new Text("Goal Coords: ");
            txtGoalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            Text txtGLabel = new Text("(0, 0)");

            Text txtMoveLabel = new Text("Moves: ");
            txtMoveLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            Text txtMoveNum = new Text("0");

            btTakeStep.setOnAction(step1->{
                if(!mazePane.getMazeSolved()){
                    mazePane.takeStep();
                    txtCLabel.setText("(" + mazePane.getCurrentXCoord() + ", " + mazePane.getCurrentYCoord() + ")");
                    txtGLabel.setText("(" + mazePane.getGoalXCoord() + ", " + mazePane.getGoalYCoord() + ")");
                    txtMoveNum.setText(Integer.toString(mazePane.getMoveCount()));
                    dingSound.play();
                } else {
                    // maze solved
                    StackPane winPane = new StackPane();

                    VBox winComps = new VBox();
                    winComps.setAlignment(Pos.CENTER);

                    Text winText = new Text("You completed the maze! \nYou solved it in " + mazePane.getMoveCount() + " steps.");
                    winText.setFont(Font.font("Arial", FontWeight.BOLD, 40));

                    Text credits = new Text("\n\nMaze Logic Design ........ Jack Smith \n" +
                            "GUI Design & Implementation ........ Austin Pettit");
                    credits.setFont(Font.font(22));

                    winComps.getChildren().addAll(winText, credits);

                    winPane.getChildren().addAll(winComps);

                    winSound.play();

                    winScene = new Scene(winPane, 750, 500);
                    primaryStage.setScene(winScene);
                    primaryStage.show();
                }
            });

//            bt5TakeStep.setOnAction(step5->{
//                if(!mazePane.getMazeSolved()) {
//                    for (int i = 0; i < 10; i++) {
//                        txtCLabel.setText("(" + mazePane.getCurrentXCoord() + ", " + mazePane.getCurrentYCoord() + ")");
//                        txtGLabel.setText("(" + mazePane.getGoalXCoord() + ", " + mazePane.getGoalYCoord() + ")");
//                        txtMoveNum.setText(Integer.toString(mazePane.getMoveCount()));
//                        mazePane.takeStep();
//                    }
//                    dingSound.play();
//                }
//            });

            btSolveMaze.setOnAction(solve->{
                while(!mazePane.getMazeSolved()){
                    mazePane.takeStep();
                }
                if(mazePane.getMazeSolved()){
                    // maze solved
                    StackPane winPane = new StackPane();

                    VBox winComps = new VBox();
                    winComps.setAlignment(Pos.CENTER);

                    Text winText = new Text("You completed the maze! \nYou solved it in " + mazePane.getMoveCount() + " steps.");
                    winText.setFont(Font.font("Arial", FontWeight.BOLD, 40));

                    Text credits = new Text("\n\nMaze Logic Design ........ Jack Smith \n" +
                            "GUI Design & Implementation ........ Austin Pettit");
                    credits.setFont(Font.font(22));

                    winComps.getChildren().addAll(winText, credits);

                    winPane.getChildren().addAll(winComps);

                    winSound.play();

                    winScene = new Scene(winPane, 750, 500);
                    primaryStage.setScene(winScene);
                    primaryStage.show();
                }
            });

            btGiveUp.setOnAction(giveup->{
                // maze solved
                StackPane winPane = new StackPane();

                Text winText = new Text("You failed! Better luck next time!");
                winText.setFont(Font.font("Arial", FontWeight.BOLD, 40));

                winPane.getChildren().addAll(winText);

                winScene = new Scene(winPane);
                primaryStage.setScene(winScene);
                primaryStage.show();
            });

            gameButtons.getChildren().addAll(btTakeStep, btSolveMaze, btGiveUp);
            gameText.getChildren().addAll(txtCoordLabel, txtCLabel, txtGoalLabel, txtGLabel, txtMoveLabel, txtMoveNum);

            lowerPanel.getChildren().addAll(gameButtons, gameText);

            gameLayout.setBottom(lowerPanel);

            gameScene = new Scene(gameLayout);
            primaryStage.setScene(gameScene);
            primaryStage.show();
        });
        btStartMazeWithKeys.setOnAction(e->{
            //introSound.play();

            // ACTIVATES GAME WINDOW WITH MAZE
            BorderPane gameLayout = new BorderPane();
            MazePane mazePane = new MazePane();
            gameLayout.setCenter(mazePane);

            // HOLDS ALL LOWER PANEL GAME COMPONENTS
            VBox lowerPanel = new VBox(5);
            lowerPanel.setAlignment(Pos.CENTER);
            lowerPanel.setPadding(new Insets(5));

            // HOLDS ALL GAME BUTTONS
            HBox gameButtons = new HBox(5);
            gameButtons.setAlignment(Pos.CENTER);
            gameButtons.setPadding(new Insets(5));
            while(introSound.isPlaying()){
                gameButtons.setDisable(true);
            }
            gameButtons.setDisable(false);

            // HOLDS ALL GAME TEXT ELEMENTS:
            HBox gameText = new HBox(5);
            gameText.setAlignment(Pos.CENTER);
            gameText.setPadding(new Insets(5));

            // GAME COMPONENTS
            // GIVE UP BUTTON
            Button btGiveUp = new Button("Give Up");
            btGiveUp.setPrefSize(300, 30);
            btGiveUp.setStyle("-fx-base: #ff7351");

            Text txtCoordLabel = new Text("Current Coords: ");
            txtCoordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            Text txtCLabel = new Text("(0, 0)");

            Text txtGoalLabel = new Text("Goal Coords: ");
            txtGoalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            Text txtGLabel = new Text("(0, 0)");

            Text txtMoveLabel = new Text("Moves: ");
            txtMoveLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            Text txtMoveNum = new Text("0");

            gameButtons.setOnKeyPressed(step1->{
                if(!mazePane.getMazeSolved()){
                    switch(step1.getCode()){
                        case UP:
                            mazePane.stepUp();
                            System.out.println("UP");
                            break;
                        case DOWN:
                            mazePane.stepDown();
                            System.out.println("DOWN");
                            break;
                        case LEFT:
                            mazePane.stepLeft();
                            System.out.println("LEFT");
                            break;
                        case RIGHT:
                            mazePane.stepRight();
                            System.out.println("RIGHT");
                            break;
                    }
                    txtCLabel.setText("(" + mazePane.getCurrentXCoord() + ", " + mazePane.getCurrentYCoord() + ")");
                    txtGLabel.setText("(" + mazePane.getGoalXCoord() + ", " + mazePane.getGoalYCoord() + ")");
                    txtMoveNum.setText(Integer.toString(mazePane.getMoveCount()));
                    dingSound.play();
                } else {
                    // maze solved
                    StackPane winPane = new StackPane();

                    VBox winComps = new VBox();
                    winComps.setAlignment(Pos.CENTER);

                    Text winText = new Text("You completed the maze! \nYou solved it in " + mazePane.getMoveCount() + " steps.");
                    winText.setFont(Font.font("Arial", FontWeight.BOLD, 40));

                    Text credits = new Text("\n\nMaze Logic Design ........ Jack Smith \n" +
                            "GUI Design & Implementation ........ Austin Pettit");
                    credits.setFont(Font.font(22));

                    winComps.getChildren().addAll(winText, credits);

                    winPane.getChildren().addAll(winComps);

                    winSound.play();

                    winScene = new Scene(winPane, 750, 500);
                    primaryStage.setScene(winScene);
                    primaryStage.show();
                }
            });

            btGiveUp.setOnAction(giveup->{
                // maze solved
                StackPane winPane = new StackPane();

                Text winText = new Text("You failed! Better luck next time!");
                winText.setFont(Font.font("Arial", FontWeight.BOLD, 40));

                winPane.getChildren().addAll(winText);

                winScene = new Scene(winPane);
                primaryStage.setScene(winScene);
                primaryStage.show();
            });

            gameButtons.getChildren().addAll(btGiveUp);
            gameText.getChildren().addAll(txtCoordLabel, txtCLabel, txtGoalLabel, txtGLabel, txtMoveLabel, txtMoveNum);

            lowerPanel.getChildren().addAll(gameButtons, gameText);

            gameLayout.setBottom(lowerPanel);

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
        mainMenu = new Scene(borderPane, 500,300);
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
    private int moveCount = 0;
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
            {0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,1,0,1,0,0,0},
            {0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1}};

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
        moveCount++;
        maze.takeStep();
        updateImages();
    }

    public boolean getMazeSolved() { return maze.mazeSolved; }

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

    public void stepUp(){
        moveCount++;
        maze.stepUp();
        updateImages();
    }
    public void stepDown(){
        moveCount++;
        maze.stepDown();
        updateImages();
    }
    public void stepLeft(){
        moveCount++;
        maze.stepLeft();
        updateImages();
    }
    public void stepRight(){
        moveCount++;
        maze.stepRight();
        updateImages();
    }

    public int getMoveCount() { return moveCount; }
}
