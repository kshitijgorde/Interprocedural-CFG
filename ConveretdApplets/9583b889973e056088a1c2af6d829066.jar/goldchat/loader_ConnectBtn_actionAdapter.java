// 
// Decompiled by Procyon v0.5.30
// 

package goldchat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class loader_ConnectBtn_actionAdapter implements ActionListener
{
    loader adaptee;
    
    loader_ConnectBtn_actionAdapter(final loader adaptee) {
        this.adaptee = adaptee;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.adaptee.ConnectBtn_actionPerformed(actionEvent);
    }
}
