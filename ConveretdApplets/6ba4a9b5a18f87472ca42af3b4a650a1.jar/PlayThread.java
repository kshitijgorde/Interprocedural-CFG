// 
// Decompiled by Procyon v0.5.30
// 

public class PlayThread extends Thread
{
    int[] noteList;
    int[] rhythmList;
    int currentSlot;
    int currentPlayingNote;
    
    PlayThread(final SongSaver songSaver) {
        this.noteList = songSaver.noteList;
        this.rhythmList = songSaver.rhythmList;
        this.currentPlayingNote = SongSaver.currentPlayingNote;
    }
    
    public void run() {
        for (int currentPlayingNote = this.currentPlayingNote; currentPlayingNote < 20 && this.noteList[currentPlayingNote] != 0; ++currentPlayingNote) {
            Xylophone.acArray[this.noteList[currentPlayingNote] - 1].play();
            final int n = this.rhythmList[currentPlayingNote];
            int n2;
            if (n == 0) {
                n2 = 100 * (Xylophone.tempoChoice.getSelectedIndex() * 2 + 6) - 80;
            }
            else if (n == 1) {
                n2 = 50 * (Xylophone.tempoChoice.getSelectedIndex() * 2 + 6) - 80;
            }
            else {
                n2 = 25 * (Xylophone.tempoChoice.getSelectedIndex() * 2 + 6) - 80;
            }
            try {
                Thread.sleep(n2);
            }
            catch (Exception ex) {}
            if (SongSaver.paused) {
                this.currentPlayingNote = currentPlayingNote;
                currentPlayingNote = 20;
            }
        }
        if (SongSaver.stopped) {
            SongSaver.currentPlayingNote = 0;
            SongSaver.stopped = false;
        }
        else {
            SongSaver.currentPlayingNote = this.currentPlayingNote + 1;
        }
        Xylophone.playButton.setLabel("Play");
        if (!SongSaver.paused) {
            SongSaver.currentPlayingNote = 0;
        }
    }
}
