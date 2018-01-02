// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import COM.NextBus.HttpMapClient.ResponseComponent;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import COM.NextBus.HttpMapClient.ConnectionException;
import COM.NextBus.Applets.EmptyBorderPanel;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.event.ItemListener;
import java.util.Collection;
import COM.NextBus.Applets.d;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Button;
import java.awt.Label;
import COM.NextBus.Applets.LabeledChoice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class ah implements ActionListener
{
    private /* synthetic */ BusSelectorDialog a;
    
    ah(final BusSelectorDialog a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        BusSelectorDialog.k(this.a);
    }
}
