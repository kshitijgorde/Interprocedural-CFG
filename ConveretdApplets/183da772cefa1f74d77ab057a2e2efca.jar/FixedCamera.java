import java.awt.Graphics;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class FixedCamera
{
    private static final int X = 0;
    private static final int Y = 1;
    private static final int Z = 2;
    private int xposition;
    private int yposition;
    private int zposition;
    private int screendistance;
    private int screenwidth;
    private int screenheight;
    private Vector memberVector;
    private boolean isValid;
    
    public FixedCamera(final int xPosition, final int yPosition, final int zPosition, final int screenwidth, final int screenheight, final int screendistance) {
        this.memberVector = new Vector();
        this.setXPosition(xPosition);
        this.setYPosition(yPosition);
        this.setZPosition(zPosition);
        this.setScreenwidth(screenwidth);
        this.setScreenheight(screenheight);
        this.setScreendistance(screendistance);
    }
    
    public int[][] viewcoords(final int[][] array) {
        final int[][] array2 = new int[array.length][2];
        for (int i = 0; i < array.length; ++i) {
            final int n = array[i][0] - this.xposition;
            final int n2 = array[i][1] - this.yposition;
            final int n3 = array[i][2] - this.zposition;
            array2[i][0] = this.screenwidth / 2 + this.screendistance * n / n3;
            array2[i][1] = this.screenheight / 2 + this.screendistance * n2 / n3;
        }
        return array2;
    }
    
    public void addWireframe(final Wireframe wireframe) {
        this.memberVector.addElement(wireframe);
    }
    
    public void removeWireframe(final Wireframe wireframe) {
        this.memberVector.removeElement(wireframe);
    }
    
    private void invalidate() {
        this.isValid = false;
    }
    
    private void validate() {
        if (!this.isValid) {
            for (int i = 0; i < this.memberVector.size(); ++i) {
                ((Wireframe)this.memberVector.elementAt(i)).viewcoords(this);
            }
            this.isValid = true;
            return;
        }
        for (int j = 0; j < this.memberVector.size(); ++j) {
            final Wireframe wireframe;
            if (!(wireframe = this.memberVector.elementAt(j)).isValid()) {
                wireframe.viewcoords(this);
            }
        }
    }
    
    public void draw(final Graphics graphics) {
        this.validate();
        for (int i = 0; i < this.memberVector.size(); ++i) {
            ((Wireframe)this.memberVector.elementAt(i)).draw(graphics);
        }
    }
    
    public int getXPosition() {
        return this.xposition;
    }
    
    public void setXPosition(final int xposition) {
        this.xposition = xposition;
        this.invalidate();
    }
    
    public int getYPosition() {
        return this.yposition;
    }
    
    public void setYPosition(final int yposition) {
        this.yposition = yposition;
        this.invalidate();
    }
    
    public int getZPosition() {
        return this.zposition;
    }
    
    public void setZPosition(final int zposition) {
        this.zposition = zposition;
        this.invalidate();
    }
    
    public void move(final int n, final int n2, final int n3) {
        this.xposition += n;
        this.yposition += n2;
        this.zposition += n3;
        this.invalidate();
    }
    
    public int getScreenwidth() {
        return this.screenwidth;
    }
    
    public void setScreenwidth(final int screenwidth) {
        this.screenwidth = screenwidth;
        this.invalidate();
    }
    
    public int getScreenheight() {
        return this.screenheight;
    }
    
    public void setScreenheight(final int screenheight) {
        this.screenheight = screenheight;
        this.invalidate();
    }
    
    public int getScreendistance() {
        return this.screendistance;
    }
    
    public void setScreendistance(final int screendistance) {
        this.screendistance = screendistance;
        this.invalidate();
    }
}
