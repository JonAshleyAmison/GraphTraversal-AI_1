
import java.util.StringTokenizer;// imported to utilize the string tokenizer class
// Program:          Amison1.java
// Course:           COSC470
// Description:      Takes in a text file containing: Number of Nodes, Starting Node, Ending Node, Distance Weight,
//                   Treasure Weight, a 2D Adjacency Matrix (delimeted by commas, reads From-To), and Treasure Data.
//                   Using this, the program will find the path that yields the most treasure while traveling the 
//                   shortest path, based on the weights, and computing a fitness from the starting node to the ending node.
// Author:           Reagan JonAshley Amison
// Revised:          2/6/17
// Notes:            Does not contain the bonus. I had some other homework I had to do and it took all of my time.
//                   I apologize for that.
// ************************************************************************************************************ 
// ************************************************************************************************************
// Class:        Amison1
// Description:  refer to the program description
// Data Values:  none
// Constant:     none 
// Globals:      KeyboardInputClass input
//               TextFileClass textFile
//               LList greatestPath
//               LList currentPath
//               int startNode
//               int endNode
//               double tresWeight
//               double arcWeight
//               double[][] matrix
//               double[] treasureYeild
//               double greatestScore
//               double bestTreasure
//               double bestPathLength
public class Amison1 {
    public static KeyboardInputClass input = new KeyboardInputClass();// access to KeyBoardInputClass()
    public static TextFileClass textFile = new TextFileClass();// access to textFile()
    public static LList greatestPath = new LList();// list to use the greatest path
    public static int startNode;// start node
    public static int endNode;// end node
    public static double tresWeight;// treasure weight in computing fitness
    public static double arcWeight;// arc weight in computing fitness
    public static double[][] matrix;// adjacency matrix of the graph
    public static double[] treasureYeild;// how much treasure each node has
    public static double greatestScore;// current greatestScore
    public static double bestTreasure;// greatest treasure yeild
    public static double bestPathLength;// best path length
//**************************************************************************************************************
// Method:       main
// Description:  Handles all the logic and calling of the program  
// Parameters:   none
// Returns:      none
// Calls:        getAndParse()
//               KeyboardInputClass.getCharacter()
//               KeyboardInputClass.getString()
//               depthFirstRecurse()
//               LList.add()
//               LList()
//               printerBest()
//               LList.clear()
//               String.equals()
// Globals:      input
    public static void main(String[] args) {
        while (true) {
            getAndParse(); // call to get and parse   
            LList currentPath = new LList();// initialize a new current path
            currentPath.add(startNode);// add the start node to the current path
            depthFirstRecurse(startNode, currentPath);// call to depth first algorithm 
            printerBest();// print the best path
            String quitMe = input.getString("", "Exit? (Y/N Default=N)");// asks the user if they want to exit the program
            if (quitMe.equals("Y") || quitMe.equals("y")) {// if user wants to exit
                break;// terminates the program
            }// end if user wants to exit
            greatestPath.clear();// clear greatest path for next run            
        }// end overall while
    }// end main
    //**************************************************************************************************************
// Method:       depthFirstR
// Description:  depth first search algotithm with recursion. If the current state is an end node, score it
// Parameters:   int currentState
//               LList currentPath
// Returns:      none
// Calls:        scorer()
//               LList.contains()
//               copier()
//               LList.add()
//               depthFirstRecurse
// Globals:      endNode
//               matrix
    public static boolean depthFirstRecurse(int currentState, LList currentPath) {
        if (currentState == endNode) {// base case for recursion, if we find a path to the end node
            scorer(currentPath);// call to the scorer method to score the current path
            return true;// return that we found a path
        } else { // if the current state is not the end node                                      
            for (int i = 0; i < matrix.length; i++) {// for to generate children, but add the backwards               
                if (matrix[currentState][i] != 0 && !currentPath.contains(i)) {//if there is an arc between and it not in closed
                    LList currentPathCopy = copier(currentPath);
                    int newCurrentState = i;// i is the new current state
                    currentPathCopy.add(newCurrentState);//add the new current state to the list
                    depthFirstRecurse(newCurrentState, currentPathCopy);// recursion call pass the new current state, and current path copy
                }// end for
            }// end else
        }// end else current state isnt an end node
        return false;// return false
    }// end depthFirstR
    //**************************************************************************************************************
// Method:      copier
// Description: makes a deep copy of a passed Linked List
// Parameters:  LList currentPath
// Returns:     LList copied- the copied list
// Calls:       LList.getLength()
//              LList.getEntry() 
// Globals:     none
    public static LList copier(LList currentList) {
        LList copied = new LList();// make a new list to be copied to
        for (int i = 1; i <= currentList.getLength(); i++) {// loader for
            copied.add(currentList.getEntry(i));// get the entry and add that entry to the copied list
        }// loader for
        return copied;// return the copied list
    }// end copied
    //**************************************************************************************************************
// Method:      scorer
// Description: scores the current path and determines if it's best or equal, puts the path, treasure, and distance
//              into a double array to be added to the greatestPath list
// Parameters:  LList currentPath
// Returns:     none
// Calls:       LList.getEntry()
//              LList.getLength()
//              LList.clear()
//              LList.add()
// Globals:     treasureYeild
//              matrix
//              tresWeight
//              arcWeight
//              greatestPath
//              greatestScore
    public static void scorer(LList currentPath) {
        double[] allAttributes = new double[currentPath.getLength() + 2];// make a new [] to hold all the attributes of the path
        double totalDistance = 0;// initialize totalDistance
        int current = (int) currentPath.getEntry(1);// get the first node in the path
        double totalYeild = treasureYeild[current];// get the first amount of treasure
        allAttributes[0] = current;// add the first node to the path
        for (int i = 2; i <= currentPath.getLength(); i++) {// distance and treasure computing for
            int next = (int) currentPath.getEntry(i);// get the next node
            totalDistance += matrix[current][next];// add the new arc length the current distance
            totalYeild += treasureYeild[next];// add the new treasure to the current treasure
            current = next;// make the current next the new current node
            allAttributes[i - 1] = next;// add the next node to the allAttributes array
        }//end distance and treasure computing for
        allAttributes[allAttributes.length - 2] = totalDistance;// add the distance to the next to last index in allAttributes
        allAttributes[allAttributes.length - 1] = totalYeild;// add the treasure to the last index in allAttributes
        double score = totalYeild * tresWeight - totalDistance * arcWeight;//teasure*teasureWeight-distance*distanceWeight              
        if (score > greatestScore) {// if new best score
            greatestPath.clear();// clear the greatest path
            greatestPath.add(allAttributes);// add the new greatest Path
            greatestScore = score;// assign the new greatest score to greatestScore
        } else if (score == greatestScore) {// if same score
            greatestPath.add(allAttributes);// used to seperate the different paths, list indexes will never be negative            
        }// end of same score if
    }//end scorer
    //**************************************************************************************************************
// Method:      printerBest
// Description: prints the best path(s) in the greatestPath List
// Parameters:  none
// Returns:     none
// Calls:       LList.getLength()
//              LList.getEntry() 
// Globals:     greatestScore
//              greatestPath
    public static void printerBest() {
        System.out.println("***************************************************************");//aesthetic
        System.out.println("Greatest Score: " + greatestScore);// show the greatest score
        System.out.println("Number of Optimal Paths: " + greatestPath.getLength());// show the number of optimal paths
        if (greatestPath.getLength() == 0) {// if there are no paths
            System.out.println("No Paths Were Found.");// tell the user there are no paths
            return;// exit printer best
        }// end if there are no paths
        for (int i = 1; i <= greatestPath.getLength(); i++) {// printing list for
            double[] casted = (double[]) greatestPath.getEntry(i);// cast the entries in the list to a double[]
            if (greatestPath.getLength() == 1) {// if there is only one path
                System.out.print("Path: ");// singular path
            } else if (greatestPath.getLength() > 1) {// if there is more than one path
                System.out.print("Paths:");// plural path
            }// end number of paths if
            for (int j = 0; j < casted.length - 2; j++) {// actual path printing for
                if (j == casted.length - 3) {// if this is the last node in the path
                    System.out.print((int) casted[j] + " , Distance: ");// dont print a -, next is distance
                } else {// if this isnt the last node in the path
                    System.out.print((int) casted[j] + "-");// print a dash between nodes
                }// end node in path if
            }// end actual printing for
            System.out.print(casted[casted.length - 2] + " , Treasure: " + casted[casted.length - 1]);//show distance and treasure
            System.out.println("");// line down for next path
        }// end printing list for
        System.out.println("***************************************************************");//aesthetic
    }// end printerBest
//**************************************************************************************************************
// Method:       getAndParse
// Description:  gets the text file from the user and breaks it up into the different data types and data
//               structures needed for this program
// Parameters:   none
// Returns:      none
// Calls:        textFileClass.getFileName()
//               textFileClass.getFileContents()
//               parseInt()
//               parseDouble()
//               StringTokenizer()
//               StringTokenizer.nextToken()
//               StringTokenizer.hasMoreElements()
// Globals:      TextFile
//               matrix
//               startNode
//               endNode
//               tresWeight
//               arcWeight
//               treasureYeild

    public static void getAndParse() {
        boolean breaker = false;// used to validate the file
        while (breaker == false) {// while the file is not valid
            textFile.getFileName("Enter Valid File Name: ");// get the file
            textFile.getFileContents();// gets the contents of the file and saves them under the object text
            if (textFile.text[0] != null) {// if the first line is not empty
                breaker = true;// file is valid, end while
            }// end if first line
        }// end valid while
        int numNodes = Integer.parseInt(textFile.text[0]);// get the number of nodes from first line
        matrix = new double[numNodes][numNodes];// make the new matrix
        startNode = Integer.parseInt(textFile.text[1]);// get the start node from the second line
        endNode = Integer.parseInt(textFile.text[2]);// get the end node from the third line
        arcWeight = Double.parseDouble(textFile.text[3]);// get the treasureWeight from the fourth line
        tresWeight = Double.parseDouble(textFile.text[4]);// get the arc weight from the fifth line
        for (int i = 0; i < numNodes; i++) {// matrix row loading for
            StringTokenizer parser = new StringTokenizer(textFile.text[i + 5]);// access to string tokenizer for the current line
            double token = Double.parseDouble(parser.nextToken(", "));// get's the next token, delimeted by commas
            for (int j = 0; j < numNodes; j++) {// matrix column loading for
                matrix[i][j] = token;// assign the current token to the current index in the matrix
                if (parser.hasMoreElements()) {// if there are more elements, get the next token for this row
                    token = Double.parseDouble(parser.nextToken(", "));// make the next token the current token
                }// end more elements if
            }//end matrix column loading for
        }// end matrix row loading for
        treasureYeild = new double[numNodes];// make the treasureYeild array
        for (int i = 0; i < numNodes; i++) {// treasureYeild loading for
            double yeild = Double.parseDouble(textFile.text[i + numNodes + 5]);// make yeild the current line of the text file
            treasureYeild[i] = yeild;// assign the current index in treasureYeild to the current yeild
        }// end treasureYeild loading for
    }// end get and parse
}// end Amison1
