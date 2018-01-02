// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.component;

import com.grooveshark.sharklet.Sharklet;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

public class BackButton extends JLabel implements MouseListener
{
    private static final long serialVersionUID = 1053748968471187302L;
    private static final ImageIcon BACK_BUTTON;
    private static final ImageIcon HOVER_BACK_BUTTON;
    private static final ImageIcon PRESSED_BACK_BUTTON;
    
    public BackButton() {
        this.addMouseListener(this);
        this.setIcon(BackButton.BACK_BUTTON);
    }
    
    public void mouseClicked(final MouseEvent arg0) {
    }
    
    public void mouseEntered(final MouseEvent arg0) {
        this.setIcon(BackButton.HOVER_BACK_BUTTON);
    }
    
    public void mouseExited(final MouseEvent arg0) {
        this.setIcon(BackButton.BACK_BUTTON);
    }
    
    public void mousePressed(final MouseEvent arg0) {
        this.setIcon(BackButton.PRESSED_BACK_BUTTON);
    }
    
    public void mouseReleased(final MouseEvent arg0) {
        this.setIcon(BackButton.BACK_BUTTON);
    }
    
    public void addActionListener(final ActionListener listener) {
        this.addMouseListener(new MouseToActionListener(listener, "Back"));
    }
    
    static {
        BACK_BUTTON = Sharklet.getImage("browseBack_up.png");
        HOVER_BACK_BUTTON = Sharklet.getImage("browseBack_over.png");
        PRESSED_BACK_BUTTON = Sharklet.getImage("browseBack_down.png");
    }
}
