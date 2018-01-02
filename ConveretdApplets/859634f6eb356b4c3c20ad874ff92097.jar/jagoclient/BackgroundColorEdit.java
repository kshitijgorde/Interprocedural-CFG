// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient;

import java.awt.SystemColor;
import java.awt.Component;
import jagoclient.gui.DoActionListener;
import jagoclient.gui.ButtonAction;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Frame;
import jagoclient.dialogs.ColorEdit;

class BackgroundColorEdit extends ColorEdit
{
    public BackgroundColorEdit(final Frame frame, final String s, final Color color) {
        super(frame, s, color, false);
        this.show();
    }
    
    public void addbutton(final Panel panel) {
        panel.add(new ButtonAction(this, Global.resourceString("System")));
    }
    
    public void tell(final Color gray) {
        rene.gui.Global.setParameter("color.background", Global.gray = gray);
        rene.gui.Global.setParameter("color.control", gray);
        rene.gui.Global.makeColors();
    }
    
    public void doAction(final String s) {
        if (s.equals(Global.resourceString("System"))) {
            rene.gui.Global.removeParameter("color.control");
            rene.gui.Global.removeParameter("color.background");
            Global.gray = SystemColor.window;
            rene.gui.Global.makeColors();
            super.doAction(Global.resourceString("OK"));
            return;
        }
        super.doAction(s);
    }
}
