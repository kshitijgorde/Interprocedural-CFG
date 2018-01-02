// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.event.ChangeListener;

public interface Caret
{
    void addChangeListener(final ChangeListener p0);
    
    void deinstall(final JTextComponent p0);
    
    int getBlinkRate();
    
    int getDot();
    
    Point getMagicCaretPosition();
    
    int getMark();
    
    void install(final JTextComponent p0);
    
    boolean isSelectionVisible();
    
    boolean isVisible();
    
    void moveDot(final int p0);
    
    void paint(final Graphics p0);
    
    void removeChangeListener(final ChangeListener p0);
    
    void setBlinkRate(final int p0);
    
    void setDot(final int p0);
    
    void setMagicCaretPosition(final Point p0);
    
    void setSelectionVisible(final boolean p0);
    
    void setVisible(final boolean p0);
}
