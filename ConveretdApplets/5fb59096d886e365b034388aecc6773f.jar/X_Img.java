import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class X_Img
{
    int x;
    int y;
    int priority;
    int direction;
    int speed;
    int width;
    int height;
    boolean selected;
    Image Img;
    String Link;
    String TargetFrame;
    int Time;
    Applet a;
    
    public X_Img(final Image img, final String link, final String targetFrame) {
        this.x = 10000;
        this.y = 0;
        this.priority = 0;
        this.direction = 0;
        this.speed = 0;
        this.width = 0;
        this.height = 0;
        this.selected = false;
        this.Img = img;
        this.Link = link;
        this.TargetFrame = targetFrame;
        this.width = this.Img.getWidth(this.a);
        this.height = this.Img.getHeight(this.a);
    }
}
