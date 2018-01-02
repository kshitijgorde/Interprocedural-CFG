import java.awt.Rectangle;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public interface WirisFormulaInterface
{
    void doAction(final String p0);
    
    void calculateAll(final boolean p0);
    
    Component getComponent();
    
    boolean loading();
    
    int getFormulaWidth();
    
    int getFormulaHeight();
    
    void componentResized(final ComponentEvent p0);
    
    void newSession();
    
    Rectangle getBoxEditorRectangle(final Point p0);
    
    Rectangle getBoxEditorRectangle();
    
    void setTimeout(final int p0);
    
    boolean getIsUndoEmpty();
    
    boolean getIsRedoEmpty();
    
    boolean getHasSelection();
    
    void setAcceleratorByMenu(final boolean p0);
}
