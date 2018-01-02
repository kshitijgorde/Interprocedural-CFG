// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.awt.Container;
import java.awt.Button;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Checkbox;
import java.awt.ScrollPane;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import COM.NextBus.Applets.d;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class an implements ActionListener
{
    private /* synthetic */ RouteSelectorDialog a;
    
    an(final RouteSelectorDialog a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (this.a._agencyManager.b().size() > 10) {
            this.a._mapInfo.j.a("Selecting all routes. This could take a few seconds...");
        }
        RouteSelectorDialog.c(this.a);
    }
}
