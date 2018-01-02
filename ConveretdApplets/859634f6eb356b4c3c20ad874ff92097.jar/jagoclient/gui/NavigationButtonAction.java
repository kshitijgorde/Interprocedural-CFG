// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.event.MouseEvent;
import jagoclient.Global;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.event.MouseListener;
import java.awt.Panel;

public class NavigationButtonAction extends Panel implements MouseListener
{
    DoActionListener C;
    String Name;
    String S;
    FontMetrics FM;
    int W;
    int H;
    boolean Pressed;
    boolean Focus;
    boolean Enabled;
    Image I;
    Graphics IG;
    
    public NavigationButtonAction(final DoActionListener c, final String s, final String name) {
        this.Pressed = false;
        this.Focus = false;
        this.Enabled = true;
        this.C = c;
        this.Name = name;
        this.S = s;
        this.addMouseListener(this);
        this.setFont(Global.SansSerif);
        this.FM = this.getFontMetrics(Global.SansSerif);
        final int stringWidth = this.FM.stringWidth("<<<");
        final int stringWidth2 = this.FM.stringWidth(this.S);
        if (stringWidth2 > stringWidth) {
            this.W = stringWidth2 * 5 / 4 + 2;
        }
        else {
            this.W = stringWidth * 5 / 4 + 2;
        }
        this.H = this.FM.getHeight() * 5 / 4 + 2;
        this.repaint();
    }
    
    public NavigationButtonAction(final DoActionListener doActionListener, final String s) {
        this(doActionListener, s, s);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.Pressed = true;
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.Pressed = false;
        this.repaint();
        if (mouseEvent.getX() >= 0 && mouseEvent.getX() < this.W && mouseEvent.getY() >= 0 && mouseEvent.getY() < this.H) {
            this.C.doAction(this.Name);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        if (this.I == null) {
            this.I = this.createImage(this.W, this.H);
            (this.IG = this.I.getGraphics()).setFont(this.getFont());
        }
        final int stringWidth = this.FM.stringWidth(this.S);
        this.IG.setColor(Global.gray);
        this.IG.fill3DRect(0, 0, this.W, this.H, !this.Pressed);
        if (this.Enabled) {
            this.IG.setColor(Color.black);
        }
        else {
            this.IG.setColor(Color.gray);
        }
        this.IG.drawString(this.S, (this.W - stringWidth) / 2, this.H - ((this.H - this.FM.getHeight()) / 2 + this.FM.getDescent()));
        graphics.drawImage(this.I, 0, 0, this.W, this.H, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.W, this.H);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.W, this.H);
    }
    
    public void setEnabled(final boolean b) {
        if (this.Enabled == b) {
            return;
        }
        this.Enabled = b;
        this.repaint();
        super.setEnabled(b);
    }
}
