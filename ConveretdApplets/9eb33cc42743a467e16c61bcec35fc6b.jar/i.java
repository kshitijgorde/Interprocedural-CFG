import java.awt.event.ActionEvent;
import mindbright.terminal.TerminalMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class i implements ActionListener
{
    public final TerminalMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String text = this.cv.c3.getText();
        if (text != null && text.length() > 0) {
            this.cv.cq();
        }
    }
    
    public i(final TerminalMenuHandlerFull cv) {
        this.cv = cv;
    }
}
