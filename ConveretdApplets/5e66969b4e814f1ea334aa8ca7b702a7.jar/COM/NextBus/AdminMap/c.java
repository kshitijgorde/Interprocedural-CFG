// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.event.WindowEvent;
import java.awt.Frame;
import COM.NextBus.Applets.d;
import java.awt.Component;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class c implements ActionListener
{
    private /* synthetic */ DialogCommon a;
    
    c(final DialogCommon a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        DialogCommon.a(this.a);
    }
}
