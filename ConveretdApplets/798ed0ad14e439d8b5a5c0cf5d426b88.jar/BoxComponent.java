import java.awt.Component;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Cursor;

// 
// Decompiled by Procyon v0.5.30
// 

public interface BoxComponent extends FontMetricsProvider
{
    void repaint();
    
    void setCursor(final Cursor p0);
    
    void setCaret(final BoxPosition p0);
    
    BoxPosition getCaret();
    
    Font getDefaultFont(final int p0, final AbstractBox p1);
    
    boolean isDisplayStyle();
    
    FontMetrics getFontMetrics(final Font p0);
    
    FontUtils getFontUtils();
    
    void setColor(final Graphics p0, final Color p1);
    
    Point getOrigin();
    
    BoxComponent[] getPointer();
    
    AbstractBox parse(final String p0);
    
    String script(final AbstractBox p0);
    
    BoxScripting getBoxScripting();
    
    Color getColor(final int p0, final AbstractBox p1);
    
    Component getComponent();
    
    boolean isDesign();
    
    int getDPI();
    
    ResourceProvider getResourceProvider();
    
    boolean getMathRTL();
}
