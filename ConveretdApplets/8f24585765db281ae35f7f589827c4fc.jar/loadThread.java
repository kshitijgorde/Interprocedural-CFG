import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;

// 
// Decompiled by Procyon v0.5.30
// 

class loadThread extends Thread
{
    MediaTracker tracker;
    piece owner;
    Image pic;
    int index;
    String image_name;
    
    public loadThread(final String s, final String image_name, final piece owner, final int index) {
        super(s);
        this.owner = owner;
        this.image_name = image_name;
        this.index = index;
    }
    
    public void run() {
        this.pic = this.owner.obj.getImage(this.owner.obj.getDocumentBase(), this.image_name);
        (this.tracker = new MediaTracker(this.owner.obj)).addImage(this.pic, this.index);
        try {
            this.tracker.waitForID(this.index);
        }
        catch (InterruptedException ex) {}
        this.owner.setImage(this.pic);
    }
}
