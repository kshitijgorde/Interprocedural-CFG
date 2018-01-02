// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap.Toolbar;

import java.awt.Button;
import COM.NextBus.Applets.EmptyBorderPanel;
import java.awt.event.ItemListener;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import COM.NextBus.Applets.d;
import COM.NextBus.Applets.LabeledTextField;
import java.awt.List;
import COM.NextBus.AdminMap.r;
import COM.NextBus.AdminMap.DialogCommon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class a implements ActionListener
{
    private /* synthetic */ ConfigurationsDialog a;
    
    a(final ConfigurationsDialog a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String selectedItem;
        if ((selectedItem = this.a._list.getSelectedItem()) != null) {
            this.a._configs.c(selectedItem);
        }
        this.a._nameField.a("");
        this.a._configs.f();
    }
}
