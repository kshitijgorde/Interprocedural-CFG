// 
// Decompiled by Procyon v0.5.30
// 

public class SongSaver
{
    int[] noteList;
    int[] rhythmList;
    int currentSlot;
    static int currentPlayingNote;
    static boolean paused;
    static boolean stopped;
    
    SongSaver() {
        this.noteList = new int[20];
        this.rhythmList = new int[20];
        this.currentSlot = 0;
        SongSaver.paused = false;
        SongSaver.currentPlayingNote = 0;
    }
    
    public void play() {
        SongSaver.paused = false;
        new PlayThread(this).start();
    }
    
    public void pause() {
        SongSaver.paused = true;
    }
    
    public void stop() {
        SongSaver.stopped = true;
        SongSaver.paused = true;
    }
    
    public void addNote(final int n) {
        if (this.noteList[19] == 0) {
            this.noteList[this.currentSlot] = n + 1;
            this.rhythmList[this.currentSlot] = Xylophone.rhythmChoice.getSelectedIndex();
            ++this.currentSlot;
        }
    }
    
    public void erase() {
        this.noteList = new int[20];
        this.currentSlot = 0;
    }
    
    public void eraseLast() {
        --this.currentSlot;
        int i;
        for (i = -1; i < 19; ++i) {
            if (this.noteList[i + 1] == 0) {
                this.noteList[i] = 0;
                i = 20;
            }
        }
        if (i == 19) {
            this.noteList[19] = 0;
        }
    }
}
