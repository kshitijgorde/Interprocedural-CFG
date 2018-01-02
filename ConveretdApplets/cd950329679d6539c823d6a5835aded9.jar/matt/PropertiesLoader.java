// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;

class PropertiesLoader extends Thread
{
    long lastModified;
    File f;
    
    PropertiesLoader() {
        this.lastModified = 0L;
        this.f = null;
    }
    
    public void load() {
        final String curDir = System.getProperty("user.dir");
        this.f = new File(curDir + System.getProperty("file.separator") + "matt.properties");
        this.lastModified = this.f.lastModified();
        try {
            MattProperties.instance().load(new FileInputStream(this.f));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void run() {
        while (true) {
            final long lastModified = this.f.lastModified();
            if (lastModified > this.lastModified) {
                try {
                    Logger.log("Reloading properties");
                    this.load();
                    Logger.log(MattProperties.instance());
                }
                catch (Exception e) {
                    Logger.log("Could not load matt.properties file");
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000L);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
