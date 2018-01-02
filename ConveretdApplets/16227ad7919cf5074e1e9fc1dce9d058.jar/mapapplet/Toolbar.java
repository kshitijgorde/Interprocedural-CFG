// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet;

import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.awt.Graphics;
import mapapplet.imageload.ImageMessage;
import java.util.Observable;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.event.MouseListener;
import java.util.Observer;
import java.awt.Panel;

public class Toolbar extends Panel implements Observer, MouseListener
{
    public Vector buttons;
    Object parent;
    
    public Toolbar(final Object par) {
        this.setLayout(null);
        this.parent = par;
        this.buttons = new Vector();
        this.setSize(25, 25);
    }
    
    public void addButton(final String img_checked, final String img_unchecked, final String ID, final String owner, final boolean saveState, final String group) {
        final PanelButton button = (PanelButton)this.add(new PanelButton(ID, owner, saveState, group));
        button.setName(ID);
        button.addMouseListener(this);
        this.buttons.addElement(button);
        Main.imgLoader.loadImage(img_checked, ID + "_ch");
        Main.imgLoader.loadImage(img_unchecked, ID + "_unch");
        this.repaint();
    }
    
    private PanelButton getButton(final String ID) {
        PanelButton button = null;
        if (this.buttons.size() > 0) {
            for (int i = 0; i < this.buttons.size(); ++i) {
                final PanelButton b = this.buttons.elementAt(i);
                if (b.buttonID.compareTo(ID) == 0) {
                    button = b;
                }
            }
        }
        return button;
    }
    
    public void update(final Observable o, final Object arg) {
        if (arg.getClass().getName().compareTo("mapapplet.imageload.ImageMessage") == 0) {
            final String buttonID = this.recognizeButton(((ImageMessage)arg).ID);
            if (buttonID.length() > 0 && this.getButton(buttonID) != null) {
                final PanelButton but = this.getButton(buttonID);
                if (((ImageMessage)arg).ID.endsWith("_ch")) {
                    but.setCheckedImage(((ImageMessage)arg).image);
                }
                if (((ImageMessage)arg).ID.endsWith("_unch")) {
                    but.setUncheckedImage(((ImageMessage)arg).image);
                }
            }
        }
        this.repaint();
    }
    
    public void paint(final Graphics g) {
        int endx = 3;
        if (this.buttons.size() > 0) {
            for (int i = 0; i < this.buttons.size(); ++i) {
                this.buttons.elementAt(i).setBounds(endx, 0, 25, 25);
                endx += 27;
                if (i != this.buttons.size() - 1 && !this.buttons.elementAt(i).group.equals(this.buttons.elementAt(i + 1).group)) {
                    endx += 5;
                }
                this.buttons.elementAt(i).repaint();
            }
            if (this.getSize().width != endx) {
                this.setSize(endx, 25);
            }
        }
    }
    
    private String recognizeButton(final String str) {
        if (str.endsWith("_ch")) {
            return str.substring(0, str.lastIndexOf("_ch"));
        }
        if (str.endsWith("_unch")) {
            return str.substring(0, str.lastIndexOf("_unch"));
        }
        return "";
    }
    
    private void notifyButtonOwner(final PanelButton button) {
        if (button.saveState) {
            if (this.buttons.size() > 0) {
                for (int i = 0; i < this.buttons.size(); ++i) {
                    final PanelButton but = this.buttons.elementAt(i);
                    if (but.group.compareTo(button.group) == 0 && but.buttonID != button.buttonID && button.checked) {
                        but.checked = false;
                        but.repaint();
                        this.notifyButtonOwner(but.owner);
                    }
                }
                final Hashtable buttonsHash = new Hashtable();
                for (int j = 0; j < this.buttons.size(); ++j) {
                    final PanelButton but2 = this.buttons.elementAt(j);
                    if (but2.owner.compareTo(button.owner) == 0) {
                        buttonsHash.put(but2.buttonID, but2);
                    }
                }
                ((Main)this.parent).modules.get(button.owner).changedButton(buttonsHash);
            }
        }
        else if (((Main)this.parent).modules.get(button.owner) != null) {
            ((Main)this.parent).modules.get(button.owner).pressedButton(button);
        }
    }
    
    private void notifyButtonOwner(final String owner) {
        if (this.buttons.size() > 0) {
            final Hashtable buttonsHash = new Hashtable();
            for (int i = 0; i < this.buttons.size(); ++i) {
                final PanelButton but = this.buttons.elementAt(i);
                if (but.owner.compareTo(owner) == 0) {
                    buttonsHash.put(but.buttonID, but);
                }
            }
            if (((Main)this.parent).modules.get(owner) != null) {
                ((Main)this.parent).modules.get(owner).changedButton(buttonsHash);
            }
        }
    }
    
    public void pressButton(final String buttonID) {
        PanelButton button = null;
        if (this.buttons.size() > 0) {
            for (int i = 0; i < this.buttons.size(); ++i) {
                final PanelButton but = this.buttons.elementAt(i);
                if (but.buttonID.compareTo(buttonID) == 0) {
                    button = but;
                }
            }
        }
        if (button != null) {
            button.checked = true;
            button.repaint();
            this.notifyButtonOwner(button);
            this.repaint();
        }
    }
    
    public void mouseExited(final MouseEvent e) {
        ((Main)this.parent).statusBar.setText("");
    }
    
    public void mouseEntered(final MouseEvent e) {
        ((Main)this.parent).statusBar.setText(e.getComponent().getName() + " button");
    }
    
    public void mousePressed(final MouseEvent e) {
        final PanelButton button = (PanelButton)e.getComponent();
        button.pressed = true;
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent e) {
        final PanelButton button = (PanelButton)e.getComponent();
        if (button.pressed && button.saveState) {
            button.checked = !button.checked;
        }
        this.notifyButtonOwner(button);
        button.pressed = false;
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
