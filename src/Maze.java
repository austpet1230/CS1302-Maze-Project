/**
 * Created by jsmi1018 on 11/4/2017
 * 1302/07 Jack Smith
 * PACKAGE_NAME
 */
public class Maze {

    private char location;
    private char[][] useableMaze;
    private char wall = '#';
    private char path = ' ';
    private char direction = 's';

    public Maze(int[][] array){

        location = '@';

        useableMaze = new char[array.length][array[0].length];

        for(int i = 0; i < array.length; i++){
            for (int j = 0; j < array[0].length; j++){
                if (array[i][j] == 1){
                    this.useableMaze[i][j] = path;
                }
                else{
                    this.useableMaze[i][j] = wall;
                }
            }
        }
        this.useableMaze[0][2] = location;
    }

    public void takeStep(){
        char lastStep = '/';

        for (int x = 0; x < useableMaze.length; x++){
            for(int y = 0; y < useableMaze[0].length; y++){

                /*This is the first check where the maze checks the direction of you facing south
                When there is no place to move that is new, it will backstep without changing the
                direction. When a new place is found, to then changes the direction */
                if (useableMaze[x][y] == location && direction == 's') {
                    if (useableMaze[x][y - 1] == path) {
                        useableMaze[x][y - 1] = location;
                        useableMaze[x][y] = lastStep;
                        direction = 'w';
                        break;
                    } else if (useableMaze[x + 1][y] == path) {
                        useableMaze[x + 1][y] = location;
                        useableMaze[x][y] = lastStep;
                        direction = 's';
                        break;
                    } else if (useableMaze[x][y + 1] == path) {
                        useableMaze[x][y + 1] = location;
                        useableMaze[x][y] = lastStep;
                        direction = 'e';
                        break;
                    } else {
                        if (useableMaze[x][y - 1] == lastStep) {
                            useableMaze[x][y - 1] = location;
                            useableMaze[x][y] = lastStep; //checks west
                            direction = 'w';
                            break;
                        }
                        else if (useableMaze[x + 1][y] == lastStep) {
                            useableMaze[x + 1][y] = location;
                            useableMaze[x][y] = lastStep; // checks south
                            direction = 's';
                            break;
                        }
                        else if (useableMaze[x][y + 1] == lastStep) {
                            useableMaze[x][y + 1] = location;
                            useableMaze[x][y] = lastStep; // checks east
                            direction = 'e';
                            break;
                        }
                        if (useableMaze[x - 1][y] == lastStep) {
                            useableMaze[x - 1][y] = location;
                            useableMaze[x][y] = lastStep; //checks north
                            direction = 'n';
                            break;
                        }
                    }
                }

                /*If the direction is changed to west, if will follow the same logic as when the direction
                was south, except changed to follow west(left) being the forward direction.
                 */
                if (useableMaze[x][y] == location && direction == 'w') {
                    if (useableMaze[x - 1][y] == path) {
                        useableMaze[x - 1][y] = location;
                        useableMaze[x][y] = lastStep;
                        direction = 'n';
                        break;
                    }
                    else if (useableMaze[x][y - 1] == path) {
                        useableMaze[x][y - 1] = location;
                        useableMaze[x][y] = lastStep;
                        direction = 'w';
                        break;
                    }
                    else if (useableMaze[x + 1][y] == path) {
                        useableMaze[x + 1][y] = location;
                        useableMaze[x][y] = lastStep;
                        direction = 's';
                        break;
                    }
                    else {
                        if (useableMaze[x - 1][y] == lastStep) {
                            useableMaze[x - 1][y] = location;
                            useableMaze[x][y] = lastStep; //checks north
                            direction = 'n';
                            break;
                        }
                        else if (useableMaze[x][y - 1] == lastStep) {
                            useableMaze[x][y - 1] = location;
                            useableMaze[x][y] = lastStep; //checks west
                            direction = 'w';
                            break;
                        }
                        else if (useableMaze[x + 1][y] == lastStep) {
                            useableMaze[x + 1][y] = location;
                            useableMaze[x][y] = lastStep; // checks south
                            direction = 's';
                            break;
                        }
                        else if (useableMaze[x][y + 1] == lastStep) {
                            useableMaze[x][y + 1] = location;
                            useableMaze[x][y] = lastStep; // checks east
                            direction = 'e';
                            break;
                        }
                    }
                }

                /*Again, the same idea is used, except changed to follow the east direction.
                there is nothing changed from the following logic.
                 */

                if (useableMaze[x][y] == location && direction == 'e') {
                    if (useableMaze[x + 1][y] == path) {
                        useableMaze[x + 1][y] = location;
                        useableMaze[x][y] = lastStep;
                        direction = 's';
                        break;
                    }
                    else if (useableMaze[x][y + 1] == path) {
                        useableMaze[x][y + 1] = location;
                        useableMaze[x][y] = lastStep;
                        direction = 'e';
                        break;
                    }
                    else if (useableMaze[x - 1][y] == path) {
                        useableMaze[x - 1][y] = location;
                        useableMaze[x][y] = lastStep;
                        direction = 'n';
                        break;
                    }
                    else {
                        if (useableMaze[x + 1][y] == lastStep) {
                            useableMaze[x + 1][y] = location;
                            useableMaze[x][y] = lastStep; // checks south
                            direction = 's';
                            break;
                        }
                        else if(useableMaze[x][y + 1] == lastStep) {
                            useableMaze[x][y + 1] = location;
                            useableMaze[x][y] = lastStep; // checks east
                            direction = 'e';
                            break;
                        }
                        else if (useableMaze[x - 1][y] == lastStep) {
                            useableMaze[x - 1][y] = location;
                            useableMaze[x][y] = lastStep; //checks north
                            direction = 'n';
                            break;
                        }
                        else if (useableMaze[x][y - 1] == lastStep) {
                            useableMaze[x][y - 1] = location;
                            useableMaze[x][y] = lastStep; //checks west
                            direction = 'w';
                            break;
                        }
                    }
                }

                /*If the direction is north, it follows the same logic as the other directions,
                yet, the forward direction is north.
                 */


                if (useableMaze[x][y] == location && direction == 'n') {
                    if (useableMaze[x][y + 1] == path) {
                        useableMaze[x][y + 1] = location;
                        useableMaze[x][y] = lastStep;
                        direction = 'e';
                        break;
                    }
                    else if (useableMaze[x - 1][y] == path) {
                        useableMaze[x - 1][y] = location;
                        useableMaze[x][y] = lastStep;
                        direction = 'n';
                        break;
                    }
                    else if (useableMaze[x][y - 1] == path) {
                        useableMaze[x][y - 1] = location;
                        useableMaze[x][y] = lastStep;
                        direction = 'w';
                        break;
                    }
                    else {
                        if (useableMaze[x][y + 1] == lastStep) {
                            useableMaze[x][y + 1] = location;
                            useableMaze[x][y] = lastStep; // checks east
                            direction = 'e';
                            break;
                        }
                        else if (useableMaze[x - 1][y] == lastStep) {
                            useableMaze[x - 1][y] = location;
                            useableMaze[x][y] = lastStep; //checks north
                            direction = 'n';
                            break;
                        }
                        else if (useableMaze[x][y - 1] == lastStep) {
                            useableMaze[x][y - 1] = location;
                            useableMaze[x][y] = lastStep; //checks west
                            direction = 'w';
                            break;
                        }
                        else if (useableMaze[x + 1][y] == lastStep) {
                            useableMaze[x + 1][y] = location;
                            useableMaze[x][y] = lastStep; // checks south
                            direction = 's';
                            break;
                        }
                    }
                }
            }
        }
        //after each step, the display method is called to run the display for each step
        System.out.print(this.display());
    }


    public String display(){
        String tempString = "";

        for (int i = 0; i < useableMaze.length; i++){
            tempString += "\n";
            for (int j = 0; j < useableMaze[0].length; j++){
                tempString += useableMaze[i][j];
            }
        }
        return tempString;
    }
}

