// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap.Toolbar;

import java.awt.event.ActionListener;
import java.awt.Button;
import COM.NextBus.Applets.EmptyBorderPanel;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.BorderLayout;
import COM.NextBus.AdminMap.a;
import java.awt.Component;
import java.awt.Frame;
import COM.NextBus.Applets.d;
import COM.NextBus.Applets.LabeledTextField;
import java.awt.List;
import COM.NextBus.AdminMap.r;
import COM.NextBus.AdminMap.DialogCommon;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

final class g implements ItemListener
{
    private /* synthetic */ ConfigurationsDialog a;
    
    g(final ConfigurationsDialog a) {
        this.a = a;
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        final String selectedItem;
        this.a._nameField.a(((selectedItem = this.a._list.getSelectedItem()) == null) ? "" : selectedItem);
    }
}
