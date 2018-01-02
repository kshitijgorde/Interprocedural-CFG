import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class af implements ActionListener
{
    public final ag cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (!this.cv.gb) {
            if (this.cv.gp != null) {
                this.cv.gp.stop();
            }
            if (this.cv.gq != null) {
                this.cv.gq.kj();
            }
        }
        this.cv.gs.setVisible(false);
    }
    
    public af(final ag cv) {
        this.cv = cv;
    }
}
