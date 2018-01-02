// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import java.awt.event.KeyEvent;
import java.awt.Container;
import java.awt.event.ActionEvent;
import jagoclient.Global;
import java.awt.CardLayout;
import java.awt.Panel;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.Button;

class CardPanelButton extends Button implements ActionListener, KeyListener
{
    String Name;
    Panel P;
    CardLayout CL;
    
    public CardPanelButton(final String s, final CardLayout cl, final String name, final Panel p4) {
        super(s);
        this.Name = name;
        this.P = p4;
        this.CL = cl;
        this.addActionListener(this);
        this.addKeyListener(this);
        this.setFont(Global.SansSerif);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.CL.show(this.P, this.Name);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.CL.show(this.P, this.Name);
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
}
