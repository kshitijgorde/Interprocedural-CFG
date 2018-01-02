// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.terminal;

public abstract class TerminalInterpreter
{
    protected Terminal term;
    public static final int IGNORE = -1;
    
    public abstract String terminalType();
    
    public abstract int interpretChar(final char p0);
    
    public void vtReset() {
    }
    
    public void keyHandler(final int virtualKey, final int gMode) {
    }
    
    public void mouseHandler(final int x, final int y, final boolean press, final int modifiers) {
    }
    
    public final void setTerminal(final Terminal term) {
        this.term = term;
    }
}
