// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap.Toolbar;

import java.util.List;
import COM.NextBus.Applets.Spacer;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.event.ItemListener;
import COM.NextBus.AdminMap.a;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import COM.NextBus.AdminMap.O;
import COM.NextBus.AdminMap.VerticalFlowPanel;
import COM.NextBus.Applets.d;
import COM.NextBus.Applets.LabeledChoice;
import java.awt.Panel;
import java.awt.Component;
import COM.NextBus.AdminMap.r;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class h implements ActionListener
{
    private ConfigurationsDialog a;
    private /* synthetic */ AgencyToolbarPanel b;
    
    h(final AgencyToolbarPanel b) {
        this.b = b;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (this.a == null) {
            this.a = new ConfigurationsDialog(r.a(this.b), this.b, this.b._toolbarModel, this.b._lnf);
        }
        this.a.setVisible(true);
        this.a.pack();
    }
}
