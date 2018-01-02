import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public interface MVCDisplayer
{
    double getValue(final int p0);
    
    Rectangle drawPoint(final Graphics p0, final int p1, final Point p2, final double p3);
    
    void drawXStep(final Graphics p0, final int p1, final Point p2);
    
    double getMinValue();
    
    double getMaxValue();
    
    int getNbValue();
    
    void onMouseEnterValue(final MonoValueChart p0, final Graphics p1, final int p2, final Point p3);
    
    void onMouseExitValue(final MonoValueChart p0, final Graphics p1, final int p2, final Point p3);
    
    double getYValueStep();
}
