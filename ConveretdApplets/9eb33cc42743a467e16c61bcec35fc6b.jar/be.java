import java.awt.event.MouseEvent;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.event.MouseMotionAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public final class be extends MouseMotionAdapter
{
    public final SSHMenuHandlerFull cv;
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        final SSHMenuHandlerFull cv = this.cv;
        ++cv.it;
        if (this.cv.iu < 256) {
            if (this.cv.it % 2 == 0) {
                this.cv.is[this.cv.iu++] = (byte)((this.cv.iu % 2 == 0) ? mouseEvent.getX() : mouseEvent.getY());
                this.cv.ix.n0(this.cv.iu);
            }
        }
        else {
            this.cv.iv.setEnabled(true);
        }
    }
    
    public be(final SSHMenuHandlerFull cv) {
        this.cv = cv;
    }
}
