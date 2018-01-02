// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import java.awt.geom.Point2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import abc.notation.MusicElement;

public interface JScoreElement
{
    double getWidth();
    
    MusicElement getMusicElement();
    
    Rectangle2D getBoundingBox();
    
    JScoreElement getScoreElementAt(final Point p0);
    
    Point2D getBase();
}
