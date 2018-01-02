import java.io.InputStream;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class fileLoader extends Thread
{
    static final String BAD_URL = "\r\nError: Malformed file URL";
    static final String FILE_ERROR = "\r\rError: Cannot load file.";
    static final String FILE_LOADED = "\r\nFile loaded.";
    static final String FILE_LOADING = "\r\nLoading file...";
    static final int BUF_SIZE = 2048;
    String helpFileName;
    boolean loaded;
    Applet app;
    URL url;
    String fileName;
    Scroller s;
    String string;
    
    fileLoader(final Applet app, final String fileName, final Scroller s) {
        this.app = app;
        this.url = null;
        this.fileName = fileName;
        this.s = s;
        this.string = "";
    }
    
    public void run() {
        this.loaded = false;
        if (this.s != null) {
            this.s.appendText("\r\nLoading file...");
        }
        try {
            this.url = new URL(this.app.getCodeBase(), this.fileName);
        }
        catch (MalformedURLException ex) {
            this.s.appendText("\r\nError: Malformed file URL");
            this.url = null;
        }
        try {
            final URLConnection openConnection = this.url.openConnection();
            openConnection.connect();
            final InputStream inputStream = openConnection.getInputStream();
            int i;
            do {
                final byte[] array = new byte[2048];
                i = inputStream.read(array, 0, 2048);
                this.string += new String(array, 0);
            } while (i != -1);
        }
        catch (Exception ex2) {
            this.string = "";
            this.s.appendText("\r\rError: Cannot load file.");
            this.stop();
            return;
        }
        this.loaded = true;
        if (this.s != null) {
            this.s.appendText("\r\nFile loaded.");
        }
        this.stop();
    }
    
    String retrieveFileContent() {
        if (this.loaded) {
            return this.string;
        }
        return null;
    }
}
