// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.player;

import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.InputStream;
import java.io.IOException;
import javazoom.jl.decoder.JavaLayerException;

public class jlp
{
    private String fFilename;
    private boolean remote;
    
    public static void main(final String[] array) {
        int n = 0;
        try {
            final jlp instance = createInstance(array);
            if (instance != null) {
                instance.play();
            }
        }
        catch (Exception ex) {
            System.err.println(ex);
            ex.printStackTrace(System.err);
            n = 1;
        }
        System.exit(n);
    }
    
    public static jlp createInstance(final String[] array) {
        jlp jlp = new jlp();
        if (!jlp.parseArgs(array)) {
            jlp = null;
        }
        return jlp;
    }
    
    private jlp() {
        this.fFilename = null;
        this.remote = false;
    }
    
    public jlp(final String s) {
        this.fFilename = null;
        this.remote = false;
        this.init(s);
    }
    
    protected void init(final String fFilename) {
        this.fFilename = fFilename;
    }
    
    protected boolean parseArgs(final String[] array) {
        boolean b = false;
        if (array.length == 1) {
            this.init(array[0]);
            b = true;
            this.remote = false;
        }
        else if (array.length == 2) {
            if (!array[0].equals("-url")) {
                this.showUsage();
            }
            else {
                this.init(array[1]);
                b = true;
                this.remote = true;
            }
        }
        else {
            this.showUsage();
        }
        return b;
    }
    
    public void showUsage() {
        System.out.println("Usage: jlp [-url] <filename>");
        System.out.println("");
        System.out.println(" e.g. : java javazoom.jl.player.jlp localfile.mp3");
        System.out.println("        java javazoom.jl.player.jlp -url http://www.server.com/remotefile.mp3");
        System.out.println("        java javazoom.jl.player.jlp -url http://www.shoutcastserver.com:8000");
    }
    
    public void play() throws JavaLayerException {
        try {
            System.out.println("playing " + this.fFilename + "...");
            InputStream inputStream;
            if (this.remote) {
                inputStream = this.getURLInputStream();
            }
            else {
                inputStream = this.getInputStream();
            }
            new Player(inputStream, this.getAudioDevice()).play();
        }
        catch (IOException ex) {
            throw new JavaLayerException("Problem playing file " + this.fFilename, ex);
        }
        catch (Exception ex2) {
            throw new JavaLayerException("Problem playing file " + this.fFilename, ex2);
        }
    }
    
    protected InputStream getURLInputStream() throws Exception {
        return new BufferedInputStream(new URL(this.fFilename).openStream());
    }
    
    protected InputStream getInputStream() throws IOException {
        return new BufferedInputStream(new FileInputStream(this.fFilename));
    }
    
    protected AudioDevice getAudioDevice() throws JavaLayerException {
        return FactoryRegistry.systemRegistry().createAudioDevice();
    }
}
