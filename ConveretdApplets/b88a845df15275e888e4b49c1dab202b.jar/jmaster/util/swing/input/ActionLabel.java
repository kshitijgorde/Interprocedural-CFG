// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.input;

import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.GrayFilter;
import java.awt.Component;
import java.awt.image.BufferedImage;
import jmaster.util.log.B;
import javax.swing.Icon;
import jmaster.util.swing.GUIHelper;
import jmaster.util.log.A;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

public class ActionLabel extends JLabel implements MouseListener, MouseMotionListener
{
    private static final long A = 2650686548714864553L;
    protected A E;
    protected GUIHelper L;
    protected jmaster.util.C.A K;
    private Icon J;
    private Icon D;
    private Icon B;
    private Icon G;
    private Icon I;
    private Icon M;
    private Icon H;
    private Icon C;
    private Icon F;
    static /* synthetic */ Class class$java$awt$event$ActionListener;
    
    public ActionLabel() {
        this.E = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.L = GUIHelper.getInstance();
        this.K = new jmaster.util.C.A((ActionLabel.class$java$awt$event$ActionListener == null) ? (ActionLabel.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")) : ActionLabel.class$java$awt$event$ActionListener);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public Icon getDisabledIcon() {
        if (this.J == null) {
            Icon f = this.F;
            if (f != null) {
                final BufferedImage bufferedImage = new BufferedImage(f.getIconWidth(), f.getIconHeight(), 2);
                f.paintIcon(this, bufferedImage.getGraphics(), 0, 0);
                f = new ImageIcon(GrayFilter.createDisabledImage(bufferedImage));
            }
            this.J = this.A(this.C, f);
        }
        return this.J;
    }
    
    public void setDisabledIcon(final Icon j) {
        this.J = j;
    }
    
    public Icon getHoverIcon() {
        if (this.B == null) {
            this.B = this.A(this.M, this.F);
        }
        return this.B;
    }
    
    public void setHoverIcon(final Icon b) {
        this.B = b;
    }
    
    public Icon getPressedIcon() {
        if (this.G == null) {
            this.G = this.A(this.H, this.F);
        }
        return this.G;
    }
    
    public void setPressedIcon(final Icon g) {
        this.G = g;
    }
    
    public Icon getNormalIcon() {
        if (this.D == null) {
            this.D = this.A(this.I, this.F);
        }
        return this.D;
    }
    
    public void setNormalIcon(final Icon d) {
        this.setIcon(this.D = d);
    }
    
    public Icon getDisabledTemplateIcon() {
        return this.C;
    }
    
    public void setDisabledTemplateIcon(final Icon c) {
        this.C = c;
    }
    
    public Icon getHoverTemplateIcon() {
        return this.M;
    }
    
    public void setHoverTemplateIcon(final Icon m) {
        this.M = m;
    }
    
    public Icon getInnerIcon() {
        return this.F;
    }
    
    public void setInnerIcon(final Icon f) {
        this.F = f;
    }
    
    public Icon getNormalTemplateIcon() {
        return this.I;
    }
    
    public void setNormalTemplateIcon(final Icon i) {
        this.I = i;
    }
    
    public Icon getPressedTemplateIcon() {
        return this.H;
    }
    
    public void setPressedTemplateIcon(final Icon h) {
        this.H = h;
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.K.C(actionListener);
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.K.A(actionListener);
    }
    
    private void A(final ActionEvent actionEvent) {
        for (int i = 0; i < this.K.C(); ++i) {
            ((ActionListener)this.K.A(i)).actionPerformed(actionEvent);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.isEnabled()) {
            this.A(this.getHoverIcon(), this.L.getHandCursor());
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.isEnabled()) {
            this.A(this.getNormalIcon(), this.L.getDefaultCursor());
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.isEnabled()) {
            this.A(this.getPressedIcon());
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.isEnabled()) {
            this.A(this.getNormalIcon());
            this.A(new ActionEvent(this, 0, null));
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    private void A(final Icon icon) {
        if ((icon != null && !icon.equals(this.getIcon())) || icon == null) {
            this.setIcon(icon);
        }
    }
    
    private void A(final Cursor cursor) {
        if ((cursor != null && !cursor.equals(this.getCursor())) || cursor == null) {
            this.setCursor(cursor);
        }
    }
    
    private void A(final Icon icon, final Cursor cursor) {
        this.A(icon);
        this.A(cursor);
    }
    
    private Icon A(final Icon icon, final Icon icon2) {
        Icon icon3 = null;
        if (icon != null && icon2 != null) {
            final int max = Math.max(icon.getIconWidth(), icon2.getIconWidth());
            final int max2 = Math.max(icon.getIconHeight(), icon2.getIconHeight());
            final BufferedImage bufferedImage = new BufferedImage(max, max2, 2);
            final Graphics graphics = bufferedImage.getGraphics();
            icon.paintIcon(this, graphics, (max - icon.getIconWidth()) / 2, (max2 - icon.getIconHeight()) / 2);
            icon2.paintIcon(this, graphics, (max - icon2.getIconWidth()) / 2, (max2 - icon2.getIconHeight()) / 2);
            icon3 = new ImageIcon(bufferedImage);
        }
        return icon3;
    }
    
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        this.setIcon(enabled ? this.getNormalIcon() : this.getDisabledIcon());
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
