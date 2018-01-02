import java.awt.event.ActionEvent;
import mindbright.terminal.TerminalMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class k implements ActionListener
{
    public final TerminalMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        try {
            this.cv.d4.ci("sb", TerminalMenuHandlerFull.c6[this.cv.dd.getSelectedIndex()]);
            this.cv.d4.ci("rg", TerminalMenuHandlerFull.c5[this.cv.dc.getSelectedIndex()]);
            this.cv.d4.ci("sl", this.cv.c9.getText());
            this.cv.d4.ci("sd", this.cv.c8.getText());
            if (this.cv.da.getState()) {
                this.cv.d4.ci("bs", "DEL");
            }
            else {
                this.cv.d4.ci("bs", "BS");
            }
            if (this.cv.db.getState()) {
                this.cv.d4.ci("de", "BS");
            }
            else {
                this.cv.d4.ci("de", "DEL");
            }
            this.cv.de.setVisible(false);
        }
        catch (Exception ex) {
            this.cv.c7.setText(ex.getMessage());
        }
    }
    
    public k(final TerminalMenuHandlerFull cv) {
        this.cv = cv;
    }
}
