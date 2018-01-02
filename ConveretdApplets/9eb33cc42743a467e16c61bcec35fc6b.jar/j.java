import java.awt.event.ActionEvent;
import mindbright.terminal.TerminalMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class j implements ActionListener
{
    public final TerminalMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.cv.c4.setVisible(false);
        if (this.cv.cw > 0) {
            this.cv.d4.ab(this.cv.cy, this.cv.cx, this.cv.cy, this.cv.cx + this.cv.cw - 1);
        }
        this.cv.cy = 0;
        this.cv.cx = 0;
        this.cv.cw = 0;
    }
    
    public j(final TerminalMenuHandlerFull cv) {
        this.cv = cv;
    }
}
