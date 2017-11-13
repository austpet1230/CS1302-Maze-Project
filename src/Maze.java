/**********************************************
 * CS 1302 Final Project - The Maze
 * Created by Jack Smith and Austin Pettit
 * CS1302/07 - T. Kilinc
 * Fall 2017
 *********************************************/
public class Maze {

    private int[][] mazePath;
    private int wall = 0, path = 1, travelled = 2, currentPos = 3, goal = 4;
    private int goalX, goalY, currentX, currentY;
    private char direction = 's';
    protected boolean mazeSolved = false, allowedToMove = false;

    public Maze(int[][] array){
        this.mazePath = array;

        mazePath[0][2] = currentPos;
        direction = 's';

        // identify goal location - assume goal location is always in last row
        int lastRow = mazePath.length - 1;
        for(int j = 0; j < mazePath[lastRow].length; j++){
            if(mazePath[lastRow][j] == path){
                goalX = lastRow;
                goalY = j;
                mazePath[goalX][goalY] = goal;
            }
        }
    }

    public void takeStep() {
        allowedToMove = true;
        for (int x = 0; x < mazePath.length; x++) {
            for (int y = 0; y < mazePath[0].length; y++) {
                getCurrentLocation();
                if(currentX + 1 == goalX && currentY + 1 == goalY){
                    mazeSolved = true;
                    System.exit(3);
                }

                /*This is the first check where the maze checks the direction of you facing south
                When there is no place to move that is new, it will backstep without changing the
                direction. When a new place is found, to then changes the direction */
                if (mazePath[x][y] == currentPos && direction == 's' && !mazeSolved) {
                    if (mazePath[x][y - 1] == path && allowedToMove) {
                        mazePath[x][y - 1] = currentPos;
                        mazePath[x][y] = travelled;
                        direction = 'w';
                        allowedToMove = false;
                        break;
                    } else if (mazePath[x + 1][y] == path && allowedToMove) {
                        mazePath[x + 1][y] = currentPos;
                        mazePath[x][y] = travelled;
                        direction = 's';
                        allowedToMove = false;
                        break;
                    } else if (mazePath[x][y + 1] == path && allowedToMove) {
                        mazePath[x][y + 1] = currentPos;
                        mazePath[x][y] = travelled;
                        direction = 'e';
                        allowedToMove = false;
                        break;
                    } else {
                        if (mazePath[x][y - 1] == travelled && allowedToMove) {
                            mazePath[x][y - 1] = currentPos;
                            mazePath[x][y] = travelled; //checks west
                            direction = 'w';
                            allowedToMove = false;
                            break;
                        } else if (mazePath[x + 1][y] == travelled && allowedToMove) {
                            mazePath[x + 1][y] = currentPos;
                            mazePath[x][y] = travelled; // checks south
                            direction = 's';
                            allowedToMove = false;
                            break;
                        } else if (mazePath[x][y + 1] == travelled && allowedToMove) {
                            mazePath[x][y + 1] = currentPos;
                            mazePath[x][y] = travelled; // checks east
                            direction = 'e';
                            allowedToMove = false;
                            break;
                        }
                        if (mazePath[x - 1][y] == travelled && allowedToMove) {
                            mazePath[x - 1][y] = currentPos;
                            mazePath[x][y] = travelled; //checks north
                            direction = 'n';
                            allowedToMove = false;
                            break;
                        }
                    }
                }

                /*If the direction is changed to west, if will follow the same logic as when the direction
                was south, except changed to follow west(left) being the forward direction.
                 */
                if (mazePath[x][y] == currentPos && direction == 'w' && !mazeSolved) {
                    if (mazePath[x - 1][y] == path && allowedToMove) {
                        mazePath[x - 1][y] = currentPos;
                        mazePath[x][y] = travelled;
                        direction = 'n';
                        allowedToMove = false;
                        break;
                    } else if (mazePath[x][y - 1] == path && allowedToMove) {
                        mazePath[x][y - 1] = currentPos;
                        mazePath[x][y] = travelled;
                        direction = 'w';
                        allowedToMove = false;
                        break;
                    } else if (mazePath[x + 1][y] == path && allowedToMove) {
                        mazePath[x + 1][y] = currentPos;
                        mazePath[x][y] = travelled;
                        direction = 's';
                        allowedToMove = false;
                        break;
                    } else {
                        if (mazePath[x - 1][y] == travelled && allowedToMove) {
                            mazePath[x - 1][y] = currentPos;
                            mazePath[x][y] = travelled; //checks north
                            direction = 'n';
                            allowedToMove = false;
                            break;
                        } else if (mazePath[x][y - 1] == travelled && allowedToMove) {
                            mazePath[x][y - 1] = currentPos;
                            mazePath[x][y] = travelled; //checks west
                            direction = 'w';
                            allowedToMove = false;
                            break;
                        } else if (mazePath[x + 1][y] == travelled && allowedToMove) {
                            mazePath[x + 1][y] = currentPos;
                            mazePath[x][y] = travelled; // checks south
                            direction = 's';
                            allowedToMove = false;
                            break;
                        } else if (mazePath[x][y + 1] == travelled && allowedToMove) {
                            mazePath[x][y + 1] = currentPos;
                            mazePath[x][y] = travelled; // checks east
                            direction = 'e';
                            allowedToMove = false;
                            break;
                        }
                    }
                }

                /*Again, the same idea is used, except changed to follow the east direction.
                there is nothing changed from the following logic.
                 */
                if (mazePath[x][y] == currentPos && direction == 'e' && !mazeSolved) {
                    if (mazePath[x + 1][y] == path && allowedToMove) {
                        mazePath[x + 1][y] = currentPos;
                        mazePath[x][y] = travelled;
                        direction = 's';
                        allowedToMove = false;
                        break;
                    } else if (mazePath[x][y + 1] == path && allowedToMove) {
                        mazePath[x][y + 1] = currentPos;
                        mazePath[x][y] = travelled;
                        direction = 'e';
                        allowedToMove = false;
                        break;
                    } else if (mazePath[x - 1][y] == path && allowedToMove) {
                        mazePath[x - 1][y] = currentPos;
                        mazePath[x][y] = travelled;
                        direction = 'n';
                        allowedToMove = false;
                        break;
                    } else {
                        if (mazePath[x + 1][y] == travelled && allowedToMove) {
                            mazePath[x + 1][y] = currentPos;
                            mazePath[x][y] = travelled; // checks south
                            direction = 's';
                            allowedToMove = false;
                            break;
                        } else if (mazePath[x][y + 1] == travelled && allowedToMove) {
                            mazePath[x][y + 1] = currentPos;
                            mazePath[x][y] = travelled; // checks east
                            direction = 'e';
                            allowedToMove = false;
                            break;
                        } else if (mazePath[x - 1][y] == travelled && allowedToMove) {
                            mazePath[x - 1][y] = currentPos;
                            mazePath[x][y] = travelled; //checks north
                            direction = 'n';
                            allowedToMove = false;
                            break;
                        } else if (mazePath[x][y - 1] == travelled && allowedToMove) {
                            mazePath[x][y - 1] = currentPos;
                            mazePath[x][y] = travelled; //checks west
                            direction = 'w';
                            allowedToMove = false;
                            break;
                        }
                    }
                }

                /*If the direction is north, it follows the same logic as the other directions,
                yet, the forward direction is north.
                 */
                if (mazePath[x][y] == currentPos && direction == 'n' && !mazeSolved) {
                    if (mazePath[x][y + 1] == path && allowedToMove) {
                        mazePath[x][y + 1] = currentPos;
                        mazePath[x][y] = travelled;
                        direction = 'e';
                        allowedToMove = false;
                        break;
                    } else if (mazePath[x - 1][y] == path && allowedToMove) {
                        mazePath[x - 1][y] = currentPos;
                        mazePath[x][y] = travelled;
                        direction = 'n';
                        allowedToMove = false;
                        break;
                    } else if (mazePath[x][y - 1] == path && allowedToMove) {
                        mazePath[x][y - 1] = currentPos;
                        mazePath[x][y] = travelled;
                        direction = 'w';
                        allowedToMove = false;
                        break;
                    } else {
                        if (mazePath[x][y + 1] == travelled && allowedToMove) {
                            mazePath[x][y + 1] = currentPos;
                            mazePath[x][y] = travelled; // checks east
                            direction = 'e';
                            allowedToMove = false;
                            break;
                        } else if (mazePath[x - 1][y] == travelled && allowedToMove) {
                            mazePath[x - 1][y] = currentPos;
                            mazePath[x][y] = travelled; //checks north
                            direction = 'n';
                            allowedToMove = false;
                            break;
                        } else if (mazePath[x][y - 1] == travelled && allowedToMove) {
                            mazePath[x][y - 1] = currentPos;
                            mazePath[x][y] = travelled; //checks west
                            direction = 'w';
                            allowedToMove = false;
                            break;
                        } else if (mazePath[x + 1][y] == travelled && allowedToMove) {
                            mazePath[x + 1][y] = currentPos;
                            mazePath[x][y] = travelled; // checks south
                            direction = 's';
                            allowedToMove = false;
                            break;
                        }
                    }
                }
            }
        }
        //after each step, the display method is called to run the display for each step
        this.display();
    }

    public void getCurrentLocation(){
        for(int i = 0; i < mazePath.length; i++){
            for(int j = 0; j < mazePath[i].length; j++){
                if(mazePath[i][j] == 3){
                    currentX = i;
                    currentY = j;
                }
            }
        }
    }

    public void display(){
        for(int i = 0; i < mazePath.length; i++){
            // select row
            for(int j = 0; j < mazePath[i].length; j++) {
                // select column
                switch(mazePath[i][j]){
                    case 0:
                        System.out.print("#");
                        break;
                    case 1:
                        System.out.print(" ");
                        break;
                    case 2:
                        System.out.print("~");
                        break;
                    case 3:
                        System.out.print("@");
                        break;
                    case 4:
                        System.out.print("G");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }
}

