import java.awt.event.ActionEvent;
import mindbright.terminal.TerminalMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class l implements ActionListener
{
    public final TerminalMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        try {
            this.cv.d4.ci("te", TerminalMenuHandlerFull.dg[this.cv.dz.getSelectedIndex()]);
            this.cv.d4.ci("fn", TerminalMenuHandlerFull.df[this.cv.dy.getSelectedIndex()]);
            this.cv.d4.ci("fs", this.cv.do.getText());
            this.cv.d4.ci("fg", this.cv.cw(this.cv.dx, this.cv.dn));
            this.cv.d4.ci("bg", this.cv.cw(this.cv.dw, this.cv.dm));
            this.cv.d4.ci("cc", this.cv.cw(this.cv.dv, this.cv.dl));
            String text = "";
            if (this.cv.du.getState()) {
                text = this.cv.di.getText();
            }
            this.cv.d4.ci("gm", String.valueOf(this.cv.dj.getText()) + "x" + this.cv.dk.getText() + text);
            this.cv.d_.setVisible(false);
        }
        catch (Exception ex) {
            this.cv.dh.setText(ex.getMessage());
        }
    }
    
    public l(final TerminalMenuHandlerFull cv) {
        this.cv = cv;
    }
}
