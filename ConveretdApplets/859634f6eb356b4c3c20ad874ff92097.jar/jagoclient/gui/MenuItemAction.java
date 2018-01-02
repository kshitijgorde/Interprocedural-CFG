// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import jagoclient.Global;
import java.awt.event.ActionListener;
import java.awt.MenuItem;

public class MenuItemAction extends MenuItem
{
    public MenuItemAction(final DoActionListener doActionListener, final String s, final String s2) {
        super(s);
        this.addActionListener(new ActionTranslator(doActionListener, s2));
        this.setFont(Global.SansSerif);
    }
    
    public MenuItemAction(final DoActionListener doActionListener, final String s) {
        this(doActionListener, s, s);
    }
}
