import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public interface BitSource
{
    boolean isSelected();
    
    void setSelected(final boolean p0);
    
    int getValue();
    
    void setValue(final int p0);
    
    Point getTerminal();
    
    void setTerminal(final Point p0);
    
    void setDownStream(final UpdateEvent p0);
}
