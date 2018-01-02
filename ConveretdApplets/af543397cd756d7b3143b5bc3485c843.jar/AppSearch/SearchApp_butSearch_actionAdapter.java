// 
// Decompiled by Procyon v0.5.30
// 

package AppSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SearchApp_butSearch_actionAdapter implements ActionListener
{
    SearchApp adaptee;
    
    SearchApp_butSearch_actionAdapter(final SearchApp adaptee) {
        this.adaptee = adaptee;
    }
    
    public void actionPerformed(final ActionEvent e) {
        this.adaptee.butSearch_actionPerformed(e);
    }
}
