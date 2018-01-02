import java.net.MalformedURLException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.net.URL;
import java.util.zip.GZIPOutputStream;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class CGISaveManager extends SaveManager implements Runnable
{
    DesignCanvas myCanvas;
    Color myBackground;
    GZIPOutputStream os;
    URL uBase;
    String uCgi;
    URL url;
    URLConnection uc;
    String urlBasisStr;
    String codeStr;
    Thread myThread;
    
    public boolean setURL(final URL uBase, final String uCgi) {
        this.uBase = uBase;
        this.uCgi = uCgi;
        return true;
    }
    
    public URL getCGIURL() {
        try {
            if (this.uCgi == null) {
                this.url = this.uBase;
            }
            else {
                this.url = new URL(this.uBase, this.uCgi);
            }
        }
        catch (Exception ex) {
            System.out.println("Exception at line 32: " + ex);
            return null;
        }
        try {
            this.urlBasisStr = this.url.toString();
            this.codeStr = "1" + System.currentTimeMillis() + (int)(Math.random() * 2000.0);
            this.url = new URL(this.urlBasisStr + "?put=" + this.codeStr);
        }
        catch (Exception ex2) {
            System.out.println("Exception at line 43: " + ex2);
            return null;
        }
        return this.url;
    }
    
    public void performSave(final DesignCanvas myCanvas, final Color myBackground) {
        this.getDesigns(myCanvas);
        if (super.designs == null || super.designs.length == 0) {
            this.setChanged();
            this.notifyObservers("No designs drawn; not saving.");
            return;
        }
        this.myCanvas = myCanvas;
        this.myBackground = myBackground;
        (this.myThread = new Thread(this)).start();
        this.setChanged();
        this.notifyObservers("Starting save..");
    }
    
    public String formatMessage(final Object o) {
        if (o instanceof Integer) {
            return "CGI Web Save: " + o.toString() + " bytes sent.";
        }
        return "CGI Web Save: " + o.toString();
    }
    
    public void run() {
        try {
            Thread.sleep(200L);
            final URL cgiurl = this.getCGIURL();
            this.setChanged();
            this.notifyObservers("Connecting to " + cgiurl.toString() + "..");
            (this.uc = cgiurl.openConnection()).setDoOutput(true);
            this.uc.setDoInput(true);
            this.uc.setUseCaches(false);
            final OutputStream outputStream = this.uc.getOutputStream();
            Thread.sleep(200L);
            this.setChanged();
            this.notifyObservers("Sending..");
            this.uc.getURL().getFile();
            final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
            this.myCanvas.writeData(super.designs, gzipOutputStream, this.myBackground, 1);
            gzipOutputStream.finish();
            this.setChanged();
            this.notifyObservers("Data sent, getting response..");
        }
        catch (Exception ex) {
            this.setChanged();
            this.notifyObservers("Upload failed: " + ex);
            return;
        }
        try {
            Thread.sleep(200L);
        }
        catch (InterruptedException ex3) {}
        try {
            final Object content = this.uc.getContent();
            if (content instanceof InputStream) {
                ((InputStream)content).read(new byte[4096]);
                ((InputStream)content).close();
            }
            this.setChanged();
            this.notifyObservers("Done.");
        }
        catch (Exception ex4) {}
        try {
            Thread.sleep(200L);
        }
        catch (InterruptedException ex5) {}
        this.setChanged();
        this.notifyObservers("Spawning browser window.");
        try {
            final URL url = new URL(this.urlBasisStr + "?page=" + this.codeStr);
            try {
                Thread.sleep(200L);
            }
            catch (InterruptedException ex6) {}
            this.setChanged();
            this.notifyObservers(url);
        }
        catch (MalformedURLException ex2) {
            this.setChanged();
            this.notifyObservers("Problem with URL: " + ex2);
        }
    }
}
