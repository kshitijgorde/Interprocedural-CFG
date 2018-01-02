import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class cambiaimmagine extends animateoggetti
{
    Image[] ImageList;
    int nCurrent;
    MediaTracker Tracker;
    
    public cambiaimmagine(final Image[] il, final MediaTracker mt) {
        this.ImageList = il;
        this.nCurrent = 0;
        this.Tracker = mt;
    }
    
    public void clockTick() {
        ++this.nCurrent;
        if (this.nCurrent >= this.ImageList.length) {
            this.nCurrent = 0;
        }
    }
    
    public void paint(final Graphics g, final Applet Parent) {
        if (this.Tracker.isErrorID(1)) {
            return;
        }
        if (this.Tracker.checkID(1)) {
            g.drawImage(this.ImageList[this.nCurrent], 0, 0, Parent);
        }
    }
}
