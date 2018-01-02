// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

import java.util.Enumeration;
import java.applet.Applet;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class Stv_Button extends JPanel implements MouseListener
{
    private static int goto;
    private static int case;
    public static int a;
    public static int if;
    private int ID;
    private int type;
    private Image picture;
    protected int status;
    protected int functionCode;
    private String tooltip;
    protected boolean toggle_on;
    protected m group;
    
    static {
        Stv_Button.goto = 20;
        Stv_Button.case = 20;
        Stv_Button.a = Stv_Button.goto + 0;
        Stv_Button.if = Stv_Button.case + 0;
    }
    
    public Stv_Button(final int id, final int type, final int functionCode, final String tooltip, final Image picture, final Color color) {
        this.ID = id;
        this.type = type;
        this.setSize(Stv_Button.if, Stv_Button.a);
        this.functionCode = functionCode;
        this.setToolTipText(this.tooltip = tooltip);
        this.picture = picture;
        this.setBackground(null);
        this.status = 2;
        this.addMouseListener(this);
    }
    
    public Stv_Button(final int id, final int type, final int functionCode, final String s, final Image picture, final Color color, final boolean toggle_on) {
        this.ID = id;
        this.type = type;
        this.setSize(Stv_Button.if, Stv_Button.a);
        this.functionCode = functionCode;
        this.setToolTipText(s);
        this.tooltip = s;
        this.picture = picture;
        this.setBackground(null);
        this.toggle_on = toggle_on;
        if (toggle_on) {
            this.status = 0;
        }
        else {
            this.status = 2;
        }
        this.addMouseListener(this);
    }
    
    public void paint(final Graphics graphics) {
        if (this.type != 0 && this.status == 2 && this.toggle_on) {
            this.status = 0;
        }
        if (this.picture != null) {
            graphics.drawImage(this.picture, 0, 0, 0 + Stv_Button.case, 0 + Stv_Button.goto, this.status * Stv_Button.case, this.functionCode * Stv_Button.goto, (this.status + 1) * Stv_Button.case, (this.functionCode + 1) * Stv_Button.goto, Stv_Toolbar.a, null);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        ((Applet)this.getParent().getParent()).showStatus(this.tooltip);
        this.status = 1;
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        ((Applet)this.getParent().getParent()).showStatus("");
        switch (this.type) {
            case 0: {
                this.status = 2;
                this.repaint();
                break;
            }
            case 1: {
                if (this.toggle_on) {
                    this.status = 0;
                }
                else {
                    this.status = 2;
                }
                this.repaint();
                break;
            }
            case 2: {
                final Enumeration<Stv_Button> elements = this.group.do.elements();
                while (elements.hasMoreElements()) {
                    final Stv_Button stv_Button = elements.nextElement();
                    if (stv_Button == this.group.if) {
                        stv_Button.status = 0;
                    }
                    else {
                        stv_Button.status = 2;
                    }
                    stv_Button.repaint();
                }
                break;
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.status = 0;
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.type == 1) {
            this.toggle_on = !this.toggle_on;
        }
        if (this.type == 2) {
            final Stv_Button if1 = this.group.if;
            this.group.if = this;
            final Enumeration<Stv_Button> elements = this.group.do.elements();
            while (elements.hasMoreElements()) {
                final Stv_Button stv_Button = elements.nextElement();
                if (stv_Button == this.group.if) {
                    stv_Button.status = 0;
                }
                else {
                    stv_Button.status = 2;
                }
                stv_Button.repaint();
            }
        }
        if (this.contains(mouseEvent.getPoint())) {
            this.status = 1;
        }
        else {
            switch (this.type) {
                case 0: {
                    this.status = 2;
                    break;
                }
                case 1: {
                    if (this.toggle_on) {
                        this.status = 0;
                        break;
                    }
                    this.status = 2;
                    break;
                }
            }
        }
        this.repaint();
        ((Prove3d)this.getParent().getParent()).if(this.functionCode);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public int a() {
        return this.ID;
    }
    
    public static void a(final int case1, final int goto1) {
        Stv_Button.case = case1;
        Stv_Button.goto = goto1;
        Stv_Button.a = Stv_Button.goto + 0;
        Stv_Button.if = Stv_Button.case + 0;
    }
}
