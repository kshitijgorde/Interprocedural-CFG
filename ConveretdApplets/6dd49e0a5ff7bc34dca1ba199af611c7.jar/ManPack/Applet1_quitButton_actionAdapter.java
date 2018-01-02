// 
// Decompiled by Procyon v0.5.30
// 

package ManPack;

import jclass.bwt.JCActionEvent;
import jclass.bwt.JCActionListener;

class Applet1_quitButton_actionAdapter implements JCActionListener
{
    Applet1 adaptee;
    
    Applet1_quitButton_actionAdapter(final Applet1 adaptee) {
        this.adaptee = adaptee;
    }
    
    public void actionPerformed(final JCActionEvent e) {
        this.adaptee.quitButton_actionPerformed(e);
    }
}
