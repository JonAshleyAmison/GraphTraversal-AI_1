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
            char bruteOrNo = input.getCharacter(true, 'B', "D,B", 1, "Depth First(D) or Branch & Cut(B)? (Enter B/C, Default=D) ");// allows the user to pick between the 2 different algorithms
            if (bruteOrNo == 'D') {// if user wants brute force
                int current = startNode;
                depthFirst(current);// call to depth first algorithm
            } else {// if user wants branch and cut
                int current = startNode;
                branchCut();// call to branch and cut
            }// end B and B&C if,else
            String quitMe = input.getString("", "Exit? (Y/N)");// asks the user if they want to exit the program
            if (quitMe.equals("Y") || quitMe.equals("y")) {// if user wants to exit
                break;// terminates the program
            }// end if user wants to exit
        }// end overall while
    }// end main
//**************************************************************************************************************
// Method:       depthFirst
// Description:  depth first search algotithm
// Parameters:   none
// Returns:      none
// Calls:        
// Globals:      treasureYeild[]
//               matrix[][]
//               tresWeight
//               arcWeight
//               allPaths
//               greatestPath
//               greatestScore
//               

    public static void depthFirst(int current) {       
        LList currentPath = new LList();
        currentPath.add(startNode);
        int currentNode=startNode;
        
        
        double totalYeild = 0;
        double totalDistance = 0;
        totalYeild += treasureYeild[0];// CHANGE THE INDEXES
        totalDistance += matrix[0][0];// CHANGE THE INDEXES
        double score = (totalYeild * tresWeight) - (totalDistance * arcWeight);//teasure*teasureWeight-distance*distanceWeight      
        // need a for loop to add the visited nodes to the array, add 1 to each of them so that the algorithm
        // printer will work properly(if using an array)
        
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
// Method:       findNext
// Description:  finds the next neighbor in the passed row after the previous column
// Parameters:   none
// Returns:      none
// Calls:        
// Globals: 
    public static int findNext(int row, int previous){
        int next=0;// initial to find next index
        if(previous+1==matrix[0].length){// if previous node is the last
            return 0;// return 0
        }// end if the previous node is the last
        for (int i = previous+1; i < matrix[0].length; i++) {// for loop to find the next neighbor
            if(matrix[row][i]!=0){// if next neighbor
                next=i;// assign the next index to next
                break;// break the for loop
            }// end if next neighbor
        }// end find next for loop
        return next;// return the new index
    }// end findNext
//**************************************************************************************************************
// Method:       branchCut
// Description:  branch and cut algorithm
// Parameters:   none
// Returns:      none
// Calls:        
// Globals: 

    public static void branchCut() {

    }
    //**************************************************************************************************************
// Method:       printer
// Description:  printing the total lists and such
// Parameters:   none
// Returns:      none
// Calls:        
// Globals: 

    public static void printerBest() {
        System.out.println("Score: " + greatestScore);
        //ADD FOR MORE THAN ONE PATH, NEED FOR LOOP
        double[] caster = (double[]) greatestPath.getEntry(1);
        System.out.println("Path: ");
        for (int i = 0; i < caster.length - 3; i++) {
            if (caster[i] != 0) {
                if (i != caster.length - 4) {
                    System.out.print(caster[i] + "-");
                } else {
                    System.out.print(caster[i]);
                }
            }
            System.out.println("Total Treasure: " + caster[caster.length - 2]);
            System.out.println("Total Distance: " + caster[caster.length - 3]);
        }
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
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + ", ");
            }
            System.out.println("");
        }
        System.out.println("HERE");
        for (int i = 0; i < treasureYeild.length; i++) {
            System.out.print(treasureYeild[i] + ", ");
        }
        //***********************DELETE**********************
    }// end get and parse
}// end Amison1
