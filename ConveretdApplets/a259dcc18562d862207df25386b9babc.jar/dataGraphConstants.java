import java.awt.image.ImageObserver;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Image;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class dataGraphConstants
{
    public Graphics Scr;
    public Graphics Buf;
    public Image buffered_image;
    public double xmin;
    public double xmax;
    public double xmin0;
    public double xmax0;
    public double xmin00;
    public double xmax00;
    public double ymin;
    public double ymax;
    public double ymin0;
    public double ymax0;
    public double ymin00;
    public double ymax00;
    public int windowWidth;
    public int windowLeft;
    public int windowRight;
    public int windowHeight;
    public int windowTop;
    public int graphLeft;
    public int graphTop;
    public int graphWidth;
    public int graphHeight;
    public double scaleX;
    public double scaleY;
    public short pointType;
    public Button StopButton;
    public static final short POINT_NONE = 0;
    public static final short POINT_CIRCLE = 1;
    
    dataGraphConstants() {
        this.pointType = 0;
        this.StopButton = new Button("Stop");
    }
    
    public int screenX(final double n) {
        return this.graphLeft + (int)Math.rint((n - this.xmin) * this.scaleX);
    }
    
    public int screenY(final double n) {
        return this.graphTop + (this.graphHeight - (int)Math.rint((n - this.ymin) * this.scaleY));
    }
    
    public void plotXY(final int n, final int n2, final boolean b, final dataORBIT dataORBIT) {
        double n3 = dataORBIT.X[n];
        double n4 = dataORBIT.Y[n];
        int n5 = this.screenX(n3);
        int n6 = this.screenY(n4);
        for (int i = n; i < n2; ++i) {
            final double n7 = n3;
            final double n8 = n4;
            n3 = dataORBIT.X[i + 1];
            n4 = dataORBIT.Y[i + 1];
            short pointType = this.pointType;
            boolean b2 = true;
            if (n3 < this.xmin || n3 > this.xmax || n4 < this.ymin || n4 > this.ymax) {
                if ((n7 < this.xmin && n3 < this.xmin) || (n7 > this.xmax && n3 > this.xmax) || (n8 < this.ymin && n4 < this.ymin) || (n8 > this.ymax && n4 > this.ymax)) {
                    b2 = false;
                }
                pointType = 0;
            }
            final int n9 = n5;
            final int n10 = n6;
            n5 = this.screenX(n3);
            n6 = this.screenY(n4);
            if (b2) {
                this.Buf.drawLine(n9, n10, n5, n6);
                if (pointType == 1) {
                    this.Buf.drawOval(n5 - 2, n6 - 2, 4, 4);
                }
                if (b) {
                    this.Scr.drawLine(n9, n10, n5, n6);
                    if (pointType == 1) {
                        this.Scr.drawOval(n5 - 2, n6 - 2, 4, 4);
                    }
                }
            }
        }
    }
    
    public void GetGraphics(final Frame frame) {
        this.buffered_image = frame.createImage(this.windowWidth, this.windowHeight);
        this.Buf = this.buffered_image.getGraphics();
    }
    
    public void myRepaint() {
        this.Scr.setClip(0, 0, this.windowWidth, this.windowHeight + 50);
        this.Scr.drawImage(this.buffered_image, 0, 0, null);
    }
}
