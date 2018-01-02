import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class CommandBuffer
{
    private Vector m_vCommand;
    
    CommandBuffer() {
        this.m_vCommand = new Vector();
    }
    
    public synchronized void putCommand(final WhCommand whCommand) {
        this.m_vCommand.addElement(whCommand);
    }
    
    public synchronized WhCommand getCommand() {
        if (this.m_vCommand.size() > 0) {
            final WhCommand whCommand = this.m_vCommand.elementAt(0);
            this.m_vCommand.removeElementAt(0);
            return whCommand;
        }
        return null;
    }
}
