
public class HolderForStuff_DeleteBeforeTurnIn {
//    public static boolean depthFirstRecurse(int current_state,LList currentPath, LList closed) {
//         LList currentPathCopy = new LList();
//         LList closedCopy = new LList();
//        if (current_state == endNode) {// base case for recursion, if we find a path to the end node
//            System.out.println("FOUND IT: " + current_state);// print that we found the end node
//            return true;// return that we found a path
//        } else {// if the current state is not the end node
//            closed.add(current_state);// add the current state to closed
//            input.getString("", "Acknowledge");// acknowledge what the current state is
//            for (int i = matrix.length - 1; i >= 0; i--) {// for to generate children, but add the backwards
//                if (matrix[current_state][i] != 0 && !closed.contains(i)) {//if there is an arc between and it not in closed
//                    children.add(i);// add child to the children backwards
//                }// end if
//            }// end for
//            while (!children.isEmpty()) {// while children is not empty              
//                int child = (int) children.getEntry(children.getLength());// child is last child in children list
//                children.remove(children.getLength()); // remove the last child
//                System.out.println("Child is: "+child);// show what the child is
//                if (!closed.contains(child)) {// if child has not been examined
//                    if (depthFirstRecurse(child) == true) {// if the child is the end node upon recursion
//                        return true;    // return true 
//                        //WILL RECURSE BACK TO HERE!
//                    }// end recursive if
//                }// end closed contains child if
//            }// end while children has contents
//        }// end else
//        System.out.println("bottom");// show we made it to the end and returned false
//        return false;// return false
    //**************************************************************************************************************
// Method:       depthFirstNoR
// Description:  depth first search algotithm, NO RECURSION
// Parameters:   none
// Returns:      none
// Calls:        
// Globals:     

//    public static void depthFirstNoR() {
//        System.out.println("");
//        LList open = new LList();
//        LList closed = new LList();
//        open.add(startNode);
//        while (open.isEmpty() == false) {
//            input.getString("", "Acknowledge");
//            int x = (int) open.getEntry(1);
//            System.out.println("Xis: " + x);
//            open.remove(1);
//            if (x == endNode) {
//                System.out.println("FOUND IT: " + x);
//            } else {
//                closed.add(x);
//                for (int i = matrix.length - 1; i >= 0; i--) {
//                    if ((matrix[x][i] != 0)) {
//                        System.out.println("Child of X is: " + i);
//                        if ((!open.contains(i)) && (!closed.contains(i))) {
//                            System.out.println("adding: " + i);
//                            open.add(1, (int) i);
//                        }
//                    }
//                }
//                if (closed.getLength() != 0) {
//                    System.out.println("Closed: ");
//                    for (int i = 1; i <= closed.getLength(); i++) {
//                        System.out.print(closed.getEntry(i) + ", ");
//                    }
//                }
//                System.out.println("");
//                if (open.getLength() != 0) {
//                    System.out.println("Open: ");
//                    for (int i = 1; i <= open.getLength(); i++) {
//                        System.out.print((int) open.getEntry(i) + ", ");
//                    }
//                    System.out.println("");
//                }
//            }
//        }
//        System.out.println("No States Left");
//    } 
//}
}
