// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import rene.gui.Global;
import java.awt.Panel;

public class MyPanel extends Panel
{
    public MyPanel() {
        if (Global.ControlBackground != null) {
            this.setBackground(Global.ControlBackground);
        }
    }
}
