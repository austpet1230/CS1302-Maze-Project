/**********************************************
 * CS 1302 Final Project - The Maze
 * Created by Jack Smith and Austin Pettit
 * CS1302/07 - T. Kilinc
 * Fall 2017
 *********************************************/
public class Maze {

    private int location;
    private int[][] mazePath;
    private int wall = 0, path = 1, travelled = 2, currentPos = 3;
    private char direction = 's';
    protected boolean mazeSolved = false;

    public Maze(int[][] array){
        this.mazePath = array;
        location = currentPos;

        mazePath[0][2] = currentPos;
        direction = 's';
    }

    public void takeStep(){
        for (int x = 0; x < mazePath.length; x++){
            for(int y = 0; y < mazePath[0].length; y++){

                /*This is the first check where the maze checks the direction of you facing south
                When there is no place to move that is new, it will backstep without changing the
                direction. When a new place is found, to then changes the direction */
                if (mazePath[x][y] == location && direction == 's') {
                    if (mazePath[x][y - 1] == path) {
                        mazePath[x][y - 1] = location;
                        mazePath[x][y] = travelled;
                        direction = 'w';
                        break;
                    } else if (mazePath[x + 1][y] == path) {
                        mazePath[x + 1][y] = location;
                        mazePath[x][y] = travelled;
                        direction = 's';
                        break;
                    } else if (mazePath[x][y + 1] == path) {
                        mazePath[x][y + 1] = location;
                        mazePath[x][y] = travelled;
                        direction = 'e';
                        break;
                    } else {
                        if (mazePath[x][y - 1] == travelled) {
                            mazePath[x][y - 1] = location;
                            mazePath[x][y] = travelled; //checks west
                            direction = 'w';
                            break;
                        }
                        else if (mazePath[x + 1][y] == travelled) {
                            mazePath[x + 1][y] = location;
                            mazePath[x][y] = travelled; // checks south
                            direction = 's';
                            break;
                        }
                        else if (mazePath[x][y + 1] == travelled) {
                            mazePath[x][y + 1] = location;
                            mazePath[x][y] = travelled; // checks east
                            direction = 'e';
                            break;
                        }
                        if (mazePath[x - 1][y] == travelled) {
                            mazePath[x - 1][y] = location;
                            mazePath[x][y] = travelled; //checks north
                            direction = 'n';
                            break;
                        }
                    }
                }

                /*If the direction is changed to west, if will follow the same logic as when the direction
                was south, except changed to follow west(left) being the forward direction.
                 */
                if (mazePath[x][y] == location && direction == 'w') {
                    if (mazePath[x - 1][y] == path) {
                        mazePath[x - 1][y] = location;
                        mazePath[x][y] = travelled;
                        direction = 'n';
                        break;
                    }
                    else if (mazePath[x][y - 1] == path) {
                        mazePath[x][y - 1] = location;
                        mazePath[x][y] = travelled;
                        direction = 'w';
                        break;
                    }
                    else if (mazePath[x + 1][y] == path) {
                        mazePath[x + 1][y] = location;
                        mazePath[x][y] = travelled;
                        direction = 's';
                        break;
                    }
                    else {
                        if (mazePath[x - 1][y] == travelled) {
                            mazePath[x - 1][y] = location;
                            mazePath[x][y] = travelled; //checks north
                            direction = 'n';
                            break;
                        }
                        else if (mazePath[x][y - 1] == travelled) {
                            mazePath[x][y - 1] = location;
                            mazePath[x][y] = travelled; //checks west
                            direction = 'w';
                            break;
                        }
                        else if (mazePath[x + 1][y] == travelled) {
                            mazePath[x + 1][y] = location;
                            mazePath[x][y] = travelled; // checks south
                            direction = 's';
                            break;
                        }
                        else if (mazePath[x][y + 1] == travelled) {
                            mazePath[x][y + 1] = location;
                            mazePath[x][y] = travelled; // checks east
                            direction = 'e';
                            break;
                        }
                    }
                }

                /*Again, the same idea is used, except changed to follow the east direction.
                there is nothing changed from the following logic.
                 */

                if (mazePath[x][y] == location && direction == 'e') {
                    if (mazePath[x + 1][y] == path) {
                        mazePath[x + 1][y] = location;
                        mazePath[x][y] = travelled;
                        direction = 's';
                        break;
                    }
                    else if (mazePath[x][y + 1] == path) {
                        mazePath[x][y + 1] = location;
                        mazePath[x][y] = travelled;
                        direction = 'e';
                        break;
                    }
                    else if (mazePath[x - 1][y] == path) {
                        mazePath[x - 1][y] = location;
                        mazePath[x][y] = travelled;
                        direction = 'n';
                        break;
                    }
                    else {
                        if (mazePath[x + 1][y] == travelled) {
                            mazePath[x + 1][y] = location;
                            mazePath[x][y] = travelled; // checks south
                            direction = 's';
                            break;
                        }
                        else if(mazePath[x][y + 1] == travelled) {
                            mazePath[x][y + 1] = location;
                            mazePath[x][y] = travelled; // checks east
                            direction = 'e';
                            break;
                        }
                        else if (mazePath[x - 1][y] == travelled) {
                            mazePath[x - 1][y] = location;
                            mazePath[x][y] = travelled; //checks north
                            direction = 'n';
                            break;
                        }
                        else if (mazePath[x][y - 1] == travelled) {
                            mazePath[x][y - 1] = location;
                            mazePath[x][y] = travelled; //checks west
                            direction = 'w';
                            break;
                        }
                    }
                }

                /*If the direction is north, it follows the same logic as the other directions,
                yet, the forward direction is north.
                 */


                if (mazePath[x][y] == location && direction == 'n') {
                    if (mazePath[x][y + 1] == path) {
                        mazePath[x][y + 1] = location;
                        mazePath[x][y] = travelled;
                        direction = 'e';
                        break;
                    }
                    else if (mazePath[x - 1][y] == path) {
                        mazePath[x - 1][y] = location;
                        mazePath[x][y] = travelled;
                        direction = 'n';
                        break;
                    }
                    else if (mazePath[x][y - 1] == path) {
                        mazePath[x][y - 1] = location;
                        mazePath[x][y] = travelled;
                        direction = 'w';
                        break;
                    }
                    else {
                        if (mazePath[x][y + 1] == travelled) {
                            mazePath[x][y + 1] = location;
                            mazePath[x][y] = travelled; // checks east
                            direction = 'e';
                            break;
                        }
                        else if (mazePath[x - 1][y] == travelled) {
                            mazePath[x - 1][y] = location;
                            mazePath[x][y] = travelled; //checks north
                            direction = 'n';
                            break;
                        }
                        else if (mazePath[x][y - 1] == travelled) {
                            mazePath[x][y - 1] = location;
                            mazePath[x][y] = travelled; //checks west
                            direction = 'w';
                            break;
                        }
                        else if (mazePath[x + 1][y] == travelled) {
                            mazePath[x + 1][y] = location;
                            mazePath[x][y] = travelled; // checks south
                            direction = 's';
                            break;
                        }
                    }
                }
            }
        }
        //after each step, the display method is called to run the display for each step
        this.display();
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
    }
}

