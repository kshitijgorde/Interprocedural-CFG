import java.util.EventListener;

// 
// Decompiled by Procyon v0.5.30
// 

public interface TextBufferListener extends EventListener
{
    ColoredText[] colorText(final String p0);
    
    void curLineNumChanged(final int p0);
    
    void focusGained();
    
    void focusLost();
    
    void lineAdded(final int p0);
    
    void lineDeleted(final int p0);
    
    void lineModified(final int p0);
}
