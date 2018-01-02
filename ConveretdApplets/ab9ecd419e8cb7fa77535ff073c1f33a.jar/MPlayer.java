import java.io.IOException;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import java.io.InputStream;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

public class MPlayer implements Runnable
{
    private File MP3File;
    private FileInputStream str;
    private Player musicFile;
    private Thread playerThread;
    
    public MPlayer(final File mp3File) throws MPlayerException {
        this.MP3File = mp3File;
        try {
            this.str = new FileInputStream(this.MP3File);
            this.musicFile = new Player(this.str);
        }
        catch (JavaLayerException ex) {
            throw new MPlayerException("Decoding-Fehler...", ex);
        }
        catch (FileNotFoundException ex2) {
            throw new MPlayerException("Datei konnte nicht gefunden werden...", ex2);
        }
        catch (IOException ex3) {
            throw new MPlayerException("Ein - Ausgbaefehler", ex3);
        }
    }
    
    public void play() {
        (this.playerThread = new Thread(this)).setPriority(1);
        this.playerThread.start();
    }
    
    public void stop() {
        this.musicFile.close();
        try {
            this.str.close();
        }
        catch (IOException ex) {
            System.out.println("Ein-Ausgabefehler beim Schliessen der MPEG-Datei");
        }
    }
    
    public void run() {
        try {
            System.out.println("spielen");
            this.musicFile.play();
        }
        catch (JavaLayerException ex) {
            System.out.println("Fehler beim Decoding...: " + ex.toString());
        }
    }
}
