import java.net.URL;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_cC extends MouseAdapter
{
    private /* synthetic */ String a;
    
    rp_cC(final String a) {
        this.a = a;
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        mouseEvent.getComponent().setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        mouseEvent.getComponent().setCursor(Cursor.getDefaultCursor());
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        final rp_fK a = rp_au.a.a();
        try {
            a.a(new URL(this.a), null);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
