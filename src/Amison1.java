//test twice

import java.util.StringTokenizer;// imported to utilize the string tokenizer class
// Program:          Amison1.java
// Course:           COSC470
// Description:      Takes in a text file containing: Number of Nodes, Starting Node, Ending Node, Distance Weight,
//                   Treasure Weight, a 2D Adjacency Matrix (delimeted by commas, reads From-To), and Treasure Data.
//                   Using this, the program will find the path that yields the most treasure while traveling the 
//                   shortest path, based on the weights, and computing a fitness from the starting node to the ending node.
// Author:           Reagan JonAshley Amison
// Revised:          1/24/17
// Notes:            INSERT NOTES HERE
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
//REMOVE THESE COMMENTS! (used to remember my testing file names)
// orig.txt is the original params Dr.D demoed

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
//               getCharacter()
//               brute()
//               branchCut()
//               getString()
// Globals:      input
    public static void main(String[] args) {
        while (true) {
            getAndParse(); // call to get and parse            
            System.out.println("Start is: " + startNode);
            LList currentPath=new LList();
            LList closed=new LList();
            System.out.println(depthFirstRecurse(startNode,currentPath,closed) + " FINISHED");// call to depth first algorithm           
            String quitMe = input.getString("", "Exit? (Y/N)");// asks the user if they want to exit the program
            if (quitMe.equals("Y") || quitMe.equals("y")) {// if user wants to exit
                break;// terminates the program
            }// end if user wants to exit
        }// end overall while
    }// end main
    //**************************************************************************************************************
// Method:       depthFirstR
// Description:  depth first search algotithm WITH RECURSION
// Parameters:   none
// Returns:      none
// Calls:        
// Globals:     
    //public static LList visited = new LList();
    //public static LList children = new LList();

    public static boolean depthFirstRecurse(int current_state, LList currentPath, LList closed) {
        closed.add(current_state);
        currentPath.add(current_state);
        for (int i = 1; i <=currentPath.getLength(); i++) {
            System.out.print(currentPath.getEntry(i)+", ");            
        }
        System.out.println("");
        for (int i = 1; i <=closed.getLength(); i++) {
            System.out.print(closed.getEntry(i)+", ");            
        }
        System.out.println("");
        System.out.println("Current State is: "+current_state);
        LList currentPathCopy = copier(currentPath);
        LList closedCopy = copier(closed);
        if (current_state == endNode) {// base case for recursion, if we find a path to the end node
            System.out.println("FOUND IT: " + current_state);// print that we found the end node
            return true;// return that we found a path
        } else {// if the current state is not the end node            
            input.getString("", "Acknowledge");// acknowledge what the current state is
            for (int i = 0; i <matrix.length; i++) {// for to generate children, but add the backwards
                if (matrix[current_state][i] != 0 && !closedCopy.contains(i)) {//if there is an arc between and it not in closed
                    current_state = i;
                    depthFirstRecurse(current_state, currentPathCopy, closedCopy);
//                    children.add(i);// add child to the children backwards
                }// end if
            }// end for
        }// end else
        System.out.println("bottom");// show we made it to the end and returned false
        return false;// return false
    }// end depthFirstR
    //**************************************************************************************************************
// Method:      scorer
// Description:  scores the current path and determines if it's best or equal
// Parameters:   LList currentPath
// Returns:      none
// Calls:        
// Globals: 

    public static LList copier(LList currentList) {
        LList copied = new LList();
        for (int i = 1; i <= currentList.getLength(); i++) {
            copied.add(currentList.getEntry(i));
        }
        return copied;
    }
    //**************************************************************************************************************
// Method:      scorer
// Description:  scores the current path and determines if it's best or equal
// Parameters:   LList currentPath
// Returns:      none
// Calls:        
// Globals: 

    public static void scorer(LList currentPath) {
        currentPath.add(startNode);
        int currentNode = startNode;
        double totalYeild = 0;
        double totalDistance = 0;
        totalYeild += treasureYeild[0];// CHANGE THE INDEXES
        totalDistance += matrix[0][0];// CHANGE THE INDEXES
        //double score = (totalYeild * tresWeight) - (totalDistance * arcWeight);//teasure*teasureWeight-distance*distanceWeight      
        // need a for loop to add the visited nodes to the array, add 1 to each of them so that the algorithm
        // printer will work properly(if using an array)
        double score = (totalYeild * tresWeight) - (totalDistance * arcWeight);//teasure*teasureWeight-distance*distanceWeight
        if (score > greatestScore) {// if new best score
            greatestPath.clear();// clear the greatest path
            for (int k = 1; k <= currentPath.getLength(); k++) {// loop the length of the current path
                greatestPath.add(currentPath.getEntry(k));// add the current path to the other                                                                                  // current paths already in the list
            }// end of if new best score for
        } else if (score == greatestScore) {// if same score
            greatestPath.add(-1);// used to seperate the different paths, list indexes will never be negative
            for (int k = 1; k <= currentPath.getLength(); k++) {// loop the length of the current path
                greatestPath.add(greatestPath.getLength() + k, currentPath.getEntry(k));// add the current path to the other                                                                                  // current paths already in the list
            }// end of if same score for
        }// end of same score if
    }

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
        tresWeight = Double.parseDouble(textFile.text[3]);// get the treasureWeight from the fourth line
        arcWeight = Double.parseDouble(textFile.text[4]);// get the arc weight from the fifth line
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
        }// end treasureYeild oading for
        //***********************DELETE**********************
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix.length; j++) {
//                System.out.print(matrix[i][j] + ", ");
//            }
//            System.out.println("");
//        }
//        System.out.println("HERE");
//        for (int i = 0; i < treasureYeild.length; i++) {
//            System.out.print(treasureYeild[i] + ", ");
//        }
        //***********************DELETE**********************
    }// end get and parse
}// end Amison1
