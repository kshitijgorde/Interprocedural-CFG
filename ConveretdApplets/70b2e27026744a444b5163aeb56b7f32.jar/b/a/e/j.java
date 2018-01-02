// 
// Decompiled by Procyon v0.5.30
// 

package b.a.e;

import java.awt.Font;
import java.awt.Insets;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.UIResource;
import javax.swing.UIManager;
import b.a.d.b;
import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.JButton;

public class j extends JButton
{
    protected int a;
    
    public j() {
        this.a = 23;
        this.a();
    }
    
    public j(final String s, final Icon icon) {
        super(s, icon);
        this.a = 23;
        this.a();
    }
    
    public Dimension getMinimumSize() {
        final Dimension dimension = new Dimension(super.getMinimumSize());
        if (this.getIcon() == null) {
            dimension.height = Math.min(dimension.height, this.a);
        }
        return dimension;
    }
    
    public Dimension getPreferredSize() {
        final Dimension dimension = new Dimension(super.getPreferredSize());
        if (this.getIcon() == null) {
            dimension.height = Math.max(dimension.height, this.a);
        }
        else {
            dimension.height = Math.max(Math.max(dimension.height, this.getIcon().getIconHeight() + 8), this.a);
        }
        return dimension;
    }
    
    public void updateUI() {
        super.updateUI();
        this.a();
    }
    
    protected void a() {
        if (b.a()) {
            this.putClientProperty("JButton.buttonType", "icon");
            this.a = 30;
        }
        else {
            this.a = 23;
        }
        final Font font = UIManager.getFont("Button.font");
        if (this.getFont() instanceof UIResource && font != null) {
            this.setFont(font.deriveFont(this.getFont().getStyle()));
        }
        if (this.getMargin() instanceof UIResource) {
            if (b.b()) {
                this.setMargin(new InsetsUIResource(2, 2, 2, 2));
            }
            else if (b.c()) {
                this.setMargin(new InsetsUIResource(0, 2, 0, 2));
            }
        }
    }
}
