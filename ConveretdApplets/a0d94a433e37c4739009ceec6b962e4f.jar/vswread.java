import java.io.IOException;
import java.net.MalformedURLException;
import java.io.DataInputStream;
import java.net.URL;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class vswread implements Runnable
{
    Applet a;
    String file_location;
    boolean goodRead;
    String[] fileInfo;
    boolean refresh;
    boolean error;
    
    public vswread(final Applet a, final String file_location) {
        this.goodRead = false;
        this.refresh = false;
        this.error = false;
        this.a = a;
        this.file_location = file_location;
    }
    
    public void getInfo() {
        final Vector vector = new Vector<String>();
        try {
            while (true) {
                DataInputStream dataInputStream = null;
                Label_0099: {
                    try {
                        final URL url = new URL(this.a.getDocumentBase(), this.file_location);
                        url.openConnection().setDefaultUseCaches(false);
                        dataInputStream = new DataInputStream(url.openStream());
                        break Label_0099;
                    }
                    catch (MalformedURLException ex) {
                        System.out.println("Could Not Locate File: " + this.file_location);
                        this.goodRead = false;
                        this.error = true;
                        return;
                    }
                    final String line;
                    vector.addElement(line);
                }
                final String line;
                if ((line = dataInputStream.readLine()) != null) {
                    continue;
                }
                break;
            }
        }
        catch (IOException ex2) {
            System.out.println("Error Loading: " + this.file_location);
            this.goodRead = false;
            this.error = true;
            return;
        }
        vector.copyInto(this.fileInfo = new String[vector.size()]);
        this.goodRead = true;
    }
    
    public void run() {
        while (true) {
            if (this.refresh) {
                this.goodRead = false;
                this.getInfo();
                this.refresh = false;
            }
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
    }
}
