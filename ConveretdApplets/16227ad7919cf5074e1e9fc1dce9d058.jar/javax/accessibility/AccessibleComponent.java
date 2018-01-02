// 
// Decompiled by Procyon v0.5.30
// 

package javax.accessibility;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.FocusListener;

public interface AccessibleComponent
{
    void addFocusListener(final FocusListener p0);
    
    boolean contains(final Point p0);
    
    Accessible getAccessibleAt(final Point p0);
    
    Color getBackground();
    
    Rectangle getBounds();
    
    Cursor getCursor();
    
    Font getFont();
    
    FontMetrics getFontMetrics(final Font p0);
    
    Color getForeground();
    
    Point getLocation();
    
    Point getLocationOnScreen();
    
    Dimension getSize();
    
    boolean isEnabled();
    
    boolean isFocusTraversable();
    
    boolean isShowing();
    
    boolean isVisible();
    
    void removeFocusListener(final FocusListener p0);
    
    void requestFocus();
    
    void setBackground(final Color p0);
    
    void setBounds(final Rectangle p0);
    
    void setCursor(final Cursor p0);
    
    void setEnabled(final boolean p0);
    
    void setFont(final Font p0);
    
    void setForeground(final Color p0);
    
    void setLocation(final Point p0);
    
    void setSize(final Dimension p0);
    
    void setVisible(final boolean p0);
}
