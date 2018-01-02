// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import java.awt.event.KeyEvent;
import jagoclient.Global;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.Button;

public class ButtonAction extends Button implements KeyListener
{
    DoActionListener C;
    String Name;
    ActionTranslator AT;
    
    public ButtonAction(final DoActionListener c, final String s, final String name) {
        super(s);
        this.C = c;
        this.Name = name;
        this.addActionListener(this.AT = new ActionTranslator(c, name));
        this.addKeyListener(this);
        this.setFont(Global.SansSerif);
    }
    
    public ButtonAction(final DoActionListener doActionListener, final String s) {
        this(doActionListener, s, s);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.C.doAction(this.Name);
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
}
