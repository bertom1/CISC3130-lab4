package src;

import java.io.File;
//import java.io.PrintWriter;

//separated Main and file reading/writing for better readability. 
public class Main {

	public static void main(String[] args) {
        File folderPath = new File("./Spotify CSV FQ3");
		File[] CSVFiles = folderPath.listFiles();
		myQueue runningList = new myQueue(CSVFiles[0]);
		for (int i = 1; i < CSVFiles.length; i++){
			myQueue fileQueue = new myQueue(CSVFiles[i]);
			runningList = runningList.merge(runningList, fileQueue);
		}
		//runningList.displayList();
		Playlist FQPlaylist = new Playlist(runningList);

		FQPlaylist.listenToPlaylist();
	}

}

//Artist class, nodes for list
class myNode {
	//stores Artist name and node for next artist in list
    private String name;
	private myNode next;

    public myNode(String name) {
        this.name = name;
		this.next = null;
    }

	//Assigns new value for next since the variable is private
    public void setNext(myNode n) {
        this.next = n;
	}
	
	//getter for next Artist in list since the variable is private
    public myNode getNext() {
        return this.next;
	}

    public String getName() {
        return this.name;
    }

 }

//linked list class for Artist list
class myList {
    private myNode first;
	private int size;
	
    public myList(){
      first = null;
    }

	//adds node as list head
    public void addFirst(myNode node) {
		node.setNext(first);
		first = node;
        size += 1;
	}

	public myNode getFirst() {
		return this.first;
	}
	
	/*Adds artists into sorted order, follows bubblesort algorithm.
	Ignores case when comparing, but keeps case when adding into list
	**/
	public void sortedAdd(myNode node) {
		//node variable to mark current node/position in list
		myNode position = first;
		//Stores artist name, sets to lowercase 
		String name = node.getName();
		//checks for empty list, doesn't update position to skip while loop
		if (this.isEmpty()) {
		    this.addFirst(node);
		}
		//loops through the entire list till the end, or if it is forced out of the loop
		while (position != null) {
			String posName = position.getName();
            if (name.compareTo(posName) == 0) {
                position = null;
            }
            //checks for insertion at head. If true, new node becomes the head of the list.
			else if (name.compareToIgnoreCase(posName) < 0 && position == first) {
				node.setNext(first);
				first = node;
				size += 1;
				//force out of loop once insertion is done
				position = null;
			} 
			//checks for insertion at tail. If true, new node becomes last element of list
			else if (name.compareToIgnoreCase(posName) > 0 && position.getNext() == null) {
				position.setNext(node);
				size += 1;
				position = null;
			}
			//checks for insertion within list. If true, new node is inserted between current node and next node. 
			else if (name.compareToIgnoreCase(posName) > 0 && name.compareToIgnoreCase(position.getNext().getName()) < 0) {
				myNode temp = position.getNext();
				node.setNext(temp);
				position.setNext(node);
				size += 1;
				position = null;
			}
			//continues to next node if no cases are met.
			else {
				position = position.getNext();
			}
		}
	}

	//removes head of list. Second element becomes new head
    public myNode removeFirst() {
        myNode temp = this.first;
		first = temp.getNext();
        size -= 1;
        return temp;
    }

	//prints all elements of list, does not alter order
    public void displayList() {
        myNode currentPos = this.first;
        while (currentPos != null) {
            System.out.println(currentPos.getName());
            currentPos = currentPos.getNext();
        }
    }
	
	//tracks returns private size variable. Used to avoid looping through list if size is needed
    public int size() {
        return size;
	}
	
	//checks if list is empty
    public boolean isEmpty(){
     return (first == null);
    }
 }