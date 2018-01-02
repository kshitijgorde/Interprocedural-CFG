import java.awt.event.ItemEvent;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ba implements ItemListener
{
    public final SSHMenuHandlerFull cv;
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        this.cv.iw();
    }
    
    public ba(final SSHMenuHandlerFull cv) {
        this.cv = cv;
    }
}
