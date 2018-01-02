// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import com.hw.client.util.a;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

final class dk extends ComponentAdapter
{
    private dk(final cz cz, final byte b) {
    }
    
    public final void componentShown(final ComponentEvent componentEvent) {
        if (a.a()) {
            a.b("ModalDesktopPane$MyComponentListener.componentShown");
        }
        ((aM)componentEvent.getComponent()).e().setVisible(true);
    }
    
    public final void componentHidden(final ComponentEvent componentEvent) {
        if (a.a()) {
            a.b("ModalDesktopPane$MyComponentListener.componentHidden");
        }
        ((aM)componentEvent.getComponent()).e().setVisible(false);
    }
    
    dk(final cz cz) {
        this(cz, (byte)0);
    }
}
