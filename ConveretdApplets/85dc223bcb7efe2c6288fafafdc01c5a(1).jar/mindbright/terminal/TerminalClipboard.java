// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.terminal;

public interface TerminalClipboard
{
    void setSelection(final String p0);
    
    String getSelection();
    
    void selectionAvailable(final boolean p0);
}
