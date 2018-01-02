// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.event.WindowEvent;
import java.awt.Container;
import java.awt.Button;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import COM.NextBus.Applets.d;
import java.awt.Component;
import java.awt.Frame;
import java.util.Iterator;
import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class ap implements ActionListener
{
    private /* synthetic */ RouteSelectorDialog a;
    
    ap(final RouteSelectorDialog a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final Iterator g = this.a._agencyManager.g();
        while (g.hasNext()) {
            g.next().a(false);
        }
        final Iterator f = this.a._agencyManager.f();
        while (f.hasNext()) {
            f.next().setState(false);
        }
        this.a._mapInfo.b();
    }
}
