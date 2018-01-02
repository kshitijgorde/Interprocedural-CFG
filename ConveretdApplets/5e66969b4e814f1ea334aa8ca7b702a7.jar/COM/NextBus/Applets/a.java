// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Applets;

import java.awt.event.ActionEvent;
import java.util.Vector;
import java.awt.event.ActionListener;

public final class a implements ActionListener
{
    private Vector a;
    
    public a() {
        this.a = new Vector();
    }
    
    public final void a(final ActionListener actionListener) {
        this.a.addElement(actionListener);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        for (int i = 0; i < this.a.size(); ++i) {
            ((ActionListener)this.a.get(i)).actionPerformed(actionEvent);
        }
    }
}
