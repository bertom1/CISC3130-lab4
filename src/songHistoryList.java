package src;

public class songHistoryList {
    private Song first;

    public songHistoryList() {
        this.first = null;
    }

    //adds song to top of history list
    public void addSong(Song s) {
        s.setNext(first);
        this.first = s;
    }

    //returns song at top of history list
    public Song lastListened() {
        return this.first;
    }
}
