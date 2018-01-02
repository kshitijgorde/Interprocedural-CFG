// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.component;

import com.grooveshark.sharklet.Sharklet;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import com.grooveshark.ui.foldertree.FileSelectionState;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

public class TristateCheckbox extends JLabel implements MouseListener
{
    private static final long serialVersionUID = 2688417489872523810L;
    private static final ImageIcon SELECTED_ICON;
    private static final ImageIcon NOT_SELECTED_ICON;
    private static final ImageIcon PARTIAL_ICON;
    private static final ImageIcon HOVER_SELECTED_ICON;
    private static final ImageIcon HOVER_NOT_SELECTED_ICON;
    private static final ImageIcon HOVER_PARTIAL_ICON;
    private static final ImageIcon PRESSED_SELECTED_ICON;
    private static final ImageIcon PRESSED_NOT_SELECTED_ICON;
    private static final ImageIcon PRESSED_PARTIAL_ICON;
    private FileSelectionState state;
    
    public TristateCheckbox() {
        this.setIcon(TristateCheckbox.NOT_SELECTED_ICON);
    }
    
    public void addActionListener(final ActionListener listener) {
        this.addMouseListener(new MouseToActionListener(listener, ""));
    }
    
    public FileSelectionState getState() {
        return this.state;
    }
    
    public void setState(final FileSelectionState state) {
        this.state = state;
        this.setIcon(this.getDefaultIcon());
    }
    
    private ImageIcon getDefaultIcon() {
        ImageIcon icon = null;
        switch (this.state) {
            case SELECTED: {
                icon = TristateCheckbox.SELECTED_ICON;
                break;
            }
            case PARTIALLY_SELECTED: {
                icon = TristateCheckbox.PARTIAL_ICON;
                break;
            }
            case NOT_SELECTED: {
                icon = TristateCheckbox.NOT_SELECTED_ICON;
                break;
            }
        }
        return icon;
    }
    
    private ImageIcon getHoverIcon() {
        ImageIcon icon = null;
        switch (this.state) {
            case SELECTED: {
                icon = TristateCheckbox.HOVER_SELECTED_ICON;
                break;
            }
            case PARTIALLY_SELECTED: {
                icon = TristateCheckbox.HOVER_PARTIAL_ICON;
                break;
            }
            case NOT_SELECTED: {
                icon = TristateCheckbox.HOVER_NOT_SELECTED_ICON;
                break;
            }
        }
        return icon;
    }
    
    private ImageIcon getPressedIcon() {
        ImageIcon icon = null;
        switch (this.state) {
            case SELECTED: {
                icon = TristateCheckbox.PRESSED_SELECTED_ICON;
                break;
            }
            case PARTIALLY_SELECTED: {
                icon = TristateCheckbox.PRESSED_PARTIAL_ICON;
                break;
            }
            case NOT_SELECTED: {
                icon = TristateCheckbox.PRESSED_NOT_SELECTED_ICON;
                break;
            }
        }
        return icon;
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
        System.out.println(e);
        this.setIcon(this.getHoverIcon());
    }
    
    public void mouseExited(final MouseEvent e) {
        System.out.println(e);
        this.setIcon(this.getDefaultIcon());
    }
    
    public void mousePressed(final MouseEvent e) {
        System.out.println("pppp");
        this.setIcon(this.getPressedIcon());
    }
    
    public void mouseReleased(final MouseEvent e) {
        this.setIcon(this.getDefaultIcon());
    }
    
    static {
        SELECTED_ICON = Sharklet.getImage("checkbox_active_up.png");
        NOT_SELECTED_ICON = Sharklet.getImage("checkbox_normal_up.png");
        PARTIAL_ICON = Sharklet.getImage("checkbox_neutral_up.png");
        HOVER_SELECTED_ICON = Sharklet.getImage("checkbox_active_over.png");
        HOVER_NOT_SELECTED_ICON = Sharklet.getImage("checkbox_normal_over.png");
        HOVER_PARTIAL_ICON = Sharklet.getImage("checkbox_neutral_over.png");
        PRESSED_SELECTED_ICON = Sharklet.getImage("checkbox_active_down.png");
        PRESSED_NOT_SELECTED_ICON = Sharklet.getImage("checkbox_normal_down.png");
        PRESSED_PARTIAL_ICON = Sharklet.getImage("checkbox_neutral_down.png");
    }
}
