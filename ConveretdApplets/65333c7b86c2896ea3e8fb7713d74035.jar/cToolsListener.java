import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public interface cToolsListener
{
    void onErase();
    
    void onUndo();
    
    void onColor(final int p0, final int p1);
    
    void onFont(final Font p0);
    
    void onAction(final int p0);
    
    void onSize(final int p0);
    
    void onArrow(final int p0);
}
