import java.util.Vector;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public interface UBBComponent extends UBBComponentListener
{
    void setContainer(final UBBComponent p0);
    
    UBBComponent getContainer();
    
    void stop();
    
    Rectangle getUpdateArea();
    
    int getState();
    
    boolean setState(final String p0, final int p1);
    
    boolean isHidden();
    
    void addUBBComponent(final UBBComponent p0, final int p1, final int p2);
    
    void drawBackground(final Graphics p0, final Rectangle p1);
    
    String[][] create();
    
    void paint(final Graphics p0);
    
    boolean animationUpdate();
    
    void destroy();
    
    Point getAbsPosition();
    
    void childEvent(final UBBComponent p0, final int p1);
    
    void start();
    
    void initTipText(final Vector p0, final int p1, final int p2);
    
    void addUBBComponentListener(final UBBComponentListener p0);
    
    String getName();
    
    String[][] getSubtagAttributes(final int p0, final int p1, final boolean p2);
    
    Rectangle getBounds();
    
    void componentUpdate(final UBBComponent p0, final boolean p1);
    
    void init(final UBB p0, final String p1, final int p2, final int p3, final String[][] p4, final String p5, final UBBTag[] p6, final UBBImageFactory p7, final UBBTextTools p8, final UBBAnimationTimer p9, final UBBErrorHandler p10, final boolean p11);
    
    boolean setHidden(final String p0, final boolean p1);
    
    int mouseEvent(final int p0, final Point p1);
    
    void updateUBBListeners(final boolean p0);
    
    boolean clockEvent(final boolean p0);
}
