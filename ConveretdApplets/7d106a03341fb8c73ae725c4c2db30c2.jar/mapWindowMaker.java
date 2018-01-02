import java.awt.Image;
import java.awt.TextField;

// 
// Decompiled by Procyon v0.5.30
// 

public class mapWindowMaker extends Thread
{
    imageLoader ilMaps;
    MapWindow window;
    int index;
    Thread makerThread;
    TextField lat1;
    TextField long1;
    TextField lat2;
    TextField long2;
    
    mapWindowMaker(final imageLoader ilMaps, final int index, final TextField lat1, final TextField long1, final TextField lat2, final TextField long2) {
        this.ilMaps = ilMaps;
        this.index = index;
        this.lat1 = lat1;
        this.long1 = long1;
        this.lat2 = lat2;
        this.long2 = long2;
    }
    
    public void run() {
        Image[] retrieveImages = new Image[3];
        final Image[] array = new Image[2];
        while (this.makerThread != null) {
            if (this.ilMaps != null) {
                retrieveImages = this.ilMaps.retrieveImages();
            }
            if (retrieveImages != null) {
                this.ilMaps.stop();
                array[0] = retrieveImages[this.index];
                array[1] = retrieveImages[0];
                this.window = new MapWindow(array, this.lat1, this.long1, this.lat2, this.long2);
                if (this.window != null) {
                    this.window.pack();
                    return;
                }
                break;
            }
        }
    }
    
    MapWindow retrieveMapWindow() {
        return this.window;
    }
    
    public void start() {
        if (this.makerThread == null) {
            (this.makerThread = new Thread(this)).start();
        }
    }
    
    public void makerStop() {
        if (this.makerThread != null) {
            this.makerThread.stop();
            this.makerThread = null;
        }
    }
}
