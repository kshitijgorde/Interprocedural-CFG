// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.icon;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import jmaster.util.log.B;
import javax.swing.ImageIcon;
import jmaster.util.log.A;

public class FrameIcon extends ResizingIcon
{
    protected A S;
    private ImageIcon T;
    private ImageIcon R;
    private ImageIcon N;
    private ImageIcon P;
    private ImageIcon U;
    private ImageIcon O;
    private ImageIcon Q;
    private ImageIcon M;
    private ImageIcon V;
    
    public FrameIcon() {
        this.S = jmaster.util.log.B.getInstance().getLog(this.getClass());
    }
    
    public ImageIcon getIconB() {
        return this.M;
    }
    
    public void setIconB(final ImageIcon m) {
        this.M = m;
    }
    
    public ImageIcon getIconC() {
        return this.V;
    }
    
    public void setIconC(final ImageIcon v) {
        this.V = v;
    }
    
    public ImageIcon getIconL() {
        return this.U;
    }
    
    public void setIconL(final ImageIcon u) {
        this.U = u;
    }
    
    public ImageIcon getIconLB() {
        return this.P;
    }
    
    public void setIconLB(final ImageIcon p) {
        this.P = p;
    }
    
    public ImageIcon getIconLT() {
        return this.T;
    }
    
    public void setIconLT(final ImageIcon t) {
        this.T = t;
    }
    
    public ImageIcon getIconR() {
        return this.Q;
    }
    
    public void setIconR(final ImageIcon q) {
        this.Q = q;
    }
    
    public ImageIcon getIconRB() {
        return this.N;
    }
    
    public void setIconRB(final ImageIcon n) {
        this.N = n;
    }
    
    public ImageIcon getIconRT() {
        return this.R;
    }
    
    public void setIconRT(final ImageIcon r) {
        this.R = r;
    }
    
    public ImageIcon getIconT() {
        return this.O;
    }
    
    public void setIconT(final ImageIcon o) {
        this.O = o;
    }
    
    public int getImageIconHeight() {
        return this.A;
    }
    
    public int getImageIconWidth() {
        return this.B;
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        final ImageObserver imageObserver = null;
        graphics.drawImage(this.T.getImage(), n, n2, imageObserver);
        graphics.drawImage(this.R.getImage(), n + this.getIconWidth() - this.R.getIconWidth(), n2, imageObserver);
        graphics.drawImage(this.N.getImage(), n + this.getIconWidth() - this.R.getIconWidth(), n2 + this.getIconHeight() - this.N.getIconHeight(), imageObserver);
        graphics.drawImage(this.P.getImage(), n, n2 + this.getIconHeight() - this.N.getIconHeight(), imageObserver);
        graphics.drawImage(this.U.getImage(), n, n2 + this.T.getIconHeight(), this.U.getIconWidth(), this.getIconHeight() - this.T.getIconHeight() - this.P.getIconHeight(), imageObserver);
        graphics.drawImage(this.Q.getImage(), n + this.getIconWidth() - this.Q.getIconWidth(), n2 + this.R.getIconHeight(), this.Q.getIconWidth(), this.getIconHeight() - this.R.getIconHeight() - this.N.getIconHeight(), imageObserver);
        graphics.drawImage(this.O.getImage(), n + this.T.getIconWidth(), n2, this.getIconWidth() - this.T.getIconWidth() - this.R.getIconWidth(), this.O.getIconHeight(), imageObserver);
        graphics.drawImage(this.M.getImage(), n + this.P.getIconWidth(), n2 + this.getIconHeight() - this.M.getIconHeight(), this.getIconWidth() - this.P.getIconWidth() - this.N.getIconWidth(), this.M.getIconHeight(), imageObserver);
        graphics.drawImage(this.V.getImage(), n + this.T.getIconWidth(), n2 + this.T.getIconHeight(), this.getIconWidth() - this.T.getIconWidth() - this.N.getIconWidth(), this.getIconHeight() - this.T.getIconHeight() - this.N.getIconHeight(), imageObserver);
    }
}
