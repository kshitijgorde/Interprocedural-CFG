import I.I;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

class JI implements MouseListener
{
    private final ztmPlayer I;
    
    JI(final ztmPlayer i) {
        this.I = i;
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        ztmPlayer.I(this.I, mouseEvent);
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        ztmPlayer.I(this.I, mouseEvent);
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.I.setCursor(Cursor.getPredefinedCursor(0));
        synchronized (ztmPlayer.I(this.I)) {
            if (this.I.c) {
                this.I.c = false;
            }
        }
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        synchronized (ztmPlayer.I(this.I)) {
            if (!this.I.b && null == this.I.R) {
                this.I.c = true;
            }
        }
        if (2 == this.I.B && ztmPlayer.Z(this.I)) {
            this.I.start_playback();
        }
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        if (!this.I.b && this.I.N - mouseEvent.getX() <= 30 && this.I.O - mouseEvent.getY() <= 30) {
            this.I.show_fscreen();
        }
        else {
            if (null != ztmPlayer.C(this.I) && !ztmPlayer.B(this.I) && 400L < System.currentTimeMillis() - ztmPlayer.D(this.I)) {
                this.I.getAppletContext().showDocument(ztmPlayer.C(this.I), (null == ztmPlayer.F(this.I)) ? I.I.I(441) : ztmPlayer.F(this.I));
            }
            ztmPlayer.I(this.I, System.currentTimeMillis());
            ztmPlayer.I(this.I, false);
        }
    }
}
