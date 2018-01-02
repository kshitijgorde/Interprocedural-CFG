// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.player;

import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import java.applet.Applet;

public class PlayerApplet extends Applet implements Runnable
{
    public static final String AUDIO_PARAMETER = "audioURL";
    private Player player;
    private Thread playerThread;
    private String fileName;
    
    public PlayerApplet() {
        this.player = null;
        this.playerThread = null;
        this.fileName = null;
    }
    
    protected AudioDevice getAudioDevice() throws JavaLayerException {
        return FactoryRegistry.systemRegistry().createAudioDevice();
    }
    
    protected InputStream getAudioStream() {
        InputStream openStream = null;
        try {
            final URL audioURL = this.getAudioURL();
            if (audioURL != null) {
                openStream = audioURL.openStream();
            }
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
        return openStream;
    }
    
    protected String getAudioFileName() {
        String s = this.fileName;
        if (s == null) {
            s = this.getParameter("audioURL");
        }
        return s;
    }
    
    protected URL getAudioURL() {
        final String audioFileName = this.getAudioFileName();
        URL url = null;
        if (audioFileName != null) {
            try {
                url = new URL(this.getDocumentBase(), audioFileName);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        }
        return url;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    protected void stopPlayer() throws JavaLayerException {
        if (this.player != null) {
            this.player.close();
            this.player = null;
            this.playerThread = null;
        }
    }
    
    protected void play(final InputStream inputStream, final AudioDevice audioDevice) throws JavaLayerException {
        this.stopPlayer();
        if (inputStream != null && audioDevice != null) {
            this.player = new Player(inputStream, audioDevice);
            (this.playerThread = this.createPlayerThread()).start();
        }
    }
    
    protected Thread createPlayerThread() {
        return new Thread(this, "Audio player thread");
    }
    
    public void init() {
    }
    
    public void start() {
        final String audioFileName = this.getAudioFileName();
        try {
            this.play(this.getAudioStream(), this.getAudioDevice());
        }
        catch (JavaLayerException ex) {
            synchronized (System.err) {
                System.err.println("Unable to play " + audioFileName);
                ex.printStackTrace(System.err);
            }
        }
    }
    
    public void stop() {
        try {
            this.stopPlayer();
        }
        catch (JavaLayerException ex) {
            System.err.println(ex);
        }
    }
    
    public void destroy() {
    }
    
    public void run() {
        if (this.player != null) {
            try {
                this.player.play();
            }
            catch (JavaLayerException ex) {
                System.err.println("Problem playing audio: " + ex);
            }
        }
    }
}
