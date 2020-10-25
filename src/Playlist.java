package src;

import java.io.File;
import java.io.PrintWriter;
public class Playlist {
    private Song first;
    private Song last;

    public Playlist() {
        this.first = null;
        this.last = null;
    }

    //creates Playlist object from a myList object
    public Playlist(myList list) {
        this.first = null;
        this.last = null;
        myNode position = list.getFirst();
        while (position != null) {
            this.add(new Song(position.getName()));
            position = position.getNext();
        }
    }

    public Song getFirst() {
        return this.first;
    }

    public void addFirst(Song s) {
        s.setNext(first); 
        first = s;
        last = s;
    }

    //adds Song to end of list
    public void add(Song s) {
        if (first == null) {
            this.addFirst(s);
        } 
        else {
            last.setNext(s);
            last = s;
        }
    }

    public Song removeFirst() {
        Song temp = this.first;
        first = temp.getNext();
        return temp;
    }

    //iterates through Playlist and creates output file based on content of Playlist object
    public void listenToPlaylist() {
        try {
            //custom stack object to store previously listened to song
            songHistoryList test = new songHistoryList();
            Song position = first;
            PrintWriter pw = new PrintWriter(new File("FQ3 Playlist.txt"));
            while (position != null) {
                if (test.lastListened() == null) {
                    pw.println("Current Song: " + position.getSongName());
                }
                else {
                    pw.println("Current Song: " + position.getSongName() + " Previous Song: " + test.lastListened().getSongName());
                }
                //need to clone the object, if we do addSong(position) position.next is set to null (first = null in an empty HistoryList)
                // and the while loop breaks. Same result if we do Song temp = position;
                Song temp = new Song(position.getSongName());
                test.addSong(temp);
                position = position.getNext();
            }
            pw.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
}

class Song {
    private String songName;
    private Song next;

    public Song(String name) {
        this.songName = name;
        this.next = null;
    }

    public void setNext(Song s) {
        this.next = s;
    }
    public Song getNext() {
        return this.next;
    }
    
    public String getSongName() {
        return this.songName;
    }
}