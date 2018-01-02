// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import COM.NextBus.HttpMapClient.ResponseComponent;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
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
import COM.NextBus.HttpMapClient.ConnectionException;
import COM.NextBus.Predictor2.a;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class M implements ActionListener
{
    private /* synthetic */ BusSelectorDialog a;
    
    M(final BusSelectorDialog a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.a.c();
        final aj h;
        if (this.a.h() && (h = this.a._agencyManager.h(this.a.d())) != null) {
            this.a._searchRouteChoice.c(h.e());
            String a = null;
            final String k;
            if ((k = h.k()) != null) {
                a = COM.NextBus.Predictor2.a.a(k).a();
            }
            try {
                this.a._searchJobOrRunChoice.c(this.a._mapInfo.e.a(this.a._mapInfo.B(), a, this.a._mapInfo));
            }
            catch (ConnectionException ex) {}
        }
    }
}
