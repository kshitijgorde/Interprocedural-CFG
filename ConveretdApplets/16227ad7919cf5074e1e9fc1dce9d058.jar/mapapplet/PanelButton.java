// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Button;

public class PanelButton extends Button
{
    private Image img_checked;
    private Image img_unchecked;
    public boolean checked;
    public boolean pressed;
    protected String buttonID;
    protected boolean saveState;
    protected String owner;
    protected String group;
    
    public PanelButton(final String ID, final String own, final boolean st, final String gr) {
        this.checked = false;
        this.pressed = false;
        this.buttonID = ID;
        this.saveState = st;
        this.owner = own;
        this.group = gr;
    }
    
    public void changeState() {
        if (this.saveState) {
            this.checked = !this.checked;
        }
        this.repaint();
    }
    
    protected void setCheckedImage(final Image img) {
        this.img_checked = img;
        this.repaint();
    }
    
    protected void setUncheckedImage(final Image img) {
        this.img_unchecked = img;
        this.repaint();
    }
    
    public void paint(final Graphics g) {
        try {
            if (this.checked || this.pressed) {
                if (this.img_checked != null) {
                    g.drawImage(this.img_checked, 0, 0, this);
                }
            }
            else if (this.img_unchecked != null) {
                g.drawImage(this.img_unchecked, 0, 0, this);
            }
        }
        catch (NullPointerException e) {
            System.err.println("Buttons aren't loaded yet");
        }
    }
    
    public void update(final Graphics g) {
        super.update(g);
    }
    
    public String getID() {
        return this.buttonID;
    }
}
