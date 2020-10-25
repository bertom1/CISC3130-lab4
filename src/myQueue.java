package src;

import java.io.File;
import java.util.Scanner;
public class myQueue extends myList {
    //takes file as parameter and reads file to create list
    public myQueue(File file) {
        super();
        try {
			Scanner scan = new Scanner(file);
			//used to skip first two lines of file since it explains the file formatting
			scan.nextLine();
			scan.nextLine();
			while (scan.hasNextLine()) {
				//assigns the complete line from the csv file to a string
				String s = scan.nextLine();
				//array that contains the comma seperated values
				//regex is made to handle quoted inputs in lines to prevent improper formatting
				String[] fileLine = s.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				//assigns second element to variable. With proper formatting, the song name should fall under the second element
				String input = fileLine[1];
				//adds input to list in sorted order
                this.sortedAdd(new myNode(input));
			}
            scan.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
        }
    }
    //creates empty list
    public myQueue() {
        super();
    }
    /*merges linked lists which are passed as parameters. Since myQueue cannot add to the tail,
    * we create a temporary node and set it to the smallest value in either list. Then for each additional node,
    * we set it to the next node and update the temporary node to its next node.
    **/
    public myQueue merge(myQueue queue1, myQueue queue2) {
        myNode queue1Pos = queue1.getFirst();
        myNode queue2Pos = queue2.getFirst();
        myQueue mergedList = new myQueue();
        myNode mergedListPos;
        //checks for smallest node value, assigns smaller node as head
        //if both nodes are the same, both positions are incremented to avoid duplicates. 
        if (queue1Pos.getName().compareToIgnoreCase(queue2Pos.getName()) == 0) {
            mergedListPos = queue1Pos;
            queue1Pos = queue1Pos.getNext();
            queue2Pos = queue2Pos.getNext();
        }
        else if (queue1Pos.getName().compareToIgnoreCase(queue2Pos.getName()) > 0) {
            mergedListPos = queue2Pos;
            queue2Pos = queue2Pos.getNext();
        }
        else {
            mergedListPos = queue1Pos;
            queue1Pos = queue1Pos.getNext();
        }
        mergedList.addFirst(mergedListPos);
		//loops till we reach the end of one list
        while (queue1Pos != null && queue2Pos != null) {
            if (queue1Pos.getName().compareToIgnoreCase(queue2Pos.getName()) == 0) {
                mergedListPos.setNext(queue1Pos);;
                queue1Pos = queue1Pos.getNext();
                queue2Pos = queue2Pos.getNext();
                mergedListPos = mergedListPos.getNext();
            }
            else if (queue1Pos.getName().compareToIgnoreCase(queue2Pos.getName()) > 0) {
                mergedListPos.setNext(queue2Pos);
                queue2Pos = queue2Pos.getNext();
                mergedListPos = mergedListPos.getNext();
            }
            else {
                mergedListPos.setNext(queue1Pos);
                queue1Pos = queue1Pos.getNext();
                mergedListPos = mergedListPos.getNext();
            }
        }
        //sets next node as the current node for queue that has not reached the end
        if (queue1Pos == null) {
            mergedListPos.setNext(queue2Pos);
        }
        else if (queue2Pos == null) {
            mergedListPos.setNext(queue1Pos);
        }
        return mergedList;
    }
}

