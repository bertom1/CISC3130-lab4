# CISC3130-lab4
Java program written to read all Spotify CSV Files from Financial Quarter 3 (Months of July, August, and September).
Source code for the program can be found within the folder "src." Main.java contains the code needed to run the program, the remaining files are source code for the objects used.
A sample output file is included which is titled "FQ3 Playlist.txt" 
while all CSV files used are stored in the folder "Spotify CSV FQ3"  
## Implementation
For this program, the files stored in the CSV folder are stored in a File array so they can be accessed by index. Each file in the array is passed through a myQueue object,
which extends the linked list class included in Main.java. This is done so we can add the necessary constructor which reads the file and a merge function which is only used
by the myQueue object. The constructor reads the file using a Scanner object and stores each comma seperated value in an array. A custom regex is used to split the file line
([Source](https://stackoverflow.com/a/18147076)). Since we are reading multiple files, the first file input is stored in a myQueue object called runningList and create a 
loop which starts at the second file in the folder. Each file input is stored into a seperate myQueue object, which we merge using the merge method and store the merged list
in runningList. Once we read all files, we convert runningList to a Playlist object using a constructor that takes a myQueue object. We then run the Playlist method
listToPlaylist() which writes the output file using a PrintWriter object. This method writes the song title at the current position in the Playlist, as well as the previously
listened to song, if there is any. We get the previously listened song by using a custom stack called songHistoryList and adding the current position to the stack before we 
increment to the next position in the playlist.
### Dependencies
* Written using Java 14
* Terminal to run commands for setup, may differ by OS used.
#### Set up
To run this program, you need to have a github account setup with an SSH key. Access to a terminal and git commands
are needed, unless your IDE of choice has git support. If you choose to run the program with this IDE,
the setup process will differ.  
1. Clone to your machine using `git clone git@github.com:bertom1/CISC3130-lab4.git` 
2. Open the project directory with the command `cd CISC3130-lab4`. After you run this command enter `cd src`  
3. To compile the program, use the command `javac Main.java`
4. To run the program, use the command `java Main`  
