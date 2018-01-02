// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Applets;

import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Vector;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Choice;
import java.util.List;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

final class c implements ItemListener
{
    private /* synthetic */ LabeledChoice a;
    
    c(final LabeledChoice a) {
        this.a = a;
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 1) {
            LabeledChoice.a(this.a);
        }
    }
}
