import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.JTree;
import java.awt.Insets;
import java.awt.Image;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import javax.swing.AbstractButton;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.border.AbstractBorder;
import java.awt.Color;
import java.awt.AWTKeyStroke;
import java.util.Set;
import javax.swing.JTextArea;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_ap
{
    String a;
    String b;
    String c;
    String d;
    String e;
    String f;
    String g;
    private String h;
    private String i;
    
    public rp_ap(final String a, final String b, final String c, final String d, final String e, final String f, final String g, final String h, final String i) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
    }
    
    final String a(final boolean b) {
        return "&name=" + rp_C.b(this.a) + "&address=" + rp_C.b(this.b) + "&city=" + rp_C.b(this.c) + "&state=" + rp_C.b(this.d) + "&zip=" + rp_C.b(this.e) + "&phone=" + rp_C.b(this.f) + "&emailaddress=" + rp_C.b(this.g) + (b ? ("&oldpassword=" + rp_C.b(this.h)) : "") + "&password=" + rp_C.b(this.i);
    }
    
    private rp_ap() {
    }
    
    public static void a(final JTextArea textArea) {
        textArea.setFocusTraversalKeys(0, null);
        textArea.setFocusTraversalKeys(1, null);
    }
    
    public static AbstractBorder a(final String s, final Color color) {
        return BorderFactory.createTitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(22, 1, 1, 1, color), BorderFactory.createEmptyBorder(0, 8, 8, 8)), s, 2, 2);
    }
    
    public static AbstractButton a(final rp_aJ rp_aJ, final String s, final String s2, String a, final String s3, final String actionCommand, final ActionListener actionListener, final boolean b) {
        Object o = null;
        final Image a2;
        if (a != null && (a2 = rp_aJ.a(a)) != null) {
            final ImageIcon imageIcon = new ImageIcon(a2);
            o = (b ? new JToggleButton(imageIcon) : new JButton(imageIcon));
        }
        if (o == null && s != null) {
            a = rp_aJ.a().a(0, s);
            if (s2 == null) {
                o = (b ? new JToggleButton(a) : new JButton(a));
            }
            else {
                o = new rp_bu(a, rp_aJ.a().a(0, s2));
            }
        }
        ((AbstractButton)o).setActionCommand(actionCommand);
        ((JComponent)o).setToolTipText(rp_aJ.a(rp_aJ.a().a(0, s3)));
        if (actionListener != null) {
            ((AbstractButton)o).addActionListener(actionListener);
            ((Component)o).setCursor(Cursor.getPredefinedCursor(12));
        }
        if (!b) {
            ((AbstractButton)o).setBorderPainted(false);
        }
        ((AbstractButton)o).setFocusPainted(false);
        ((JComponent)o).setFont(rp_aJ.a);
        ((AbstractButton)o).setContentAreaFilled(false);
        final Insets margin;
        final Insets insets = margin = ((AbstractButton)o).getMargin();
        insets.left /= 3;
        final Insets insets2 = margin;
        insets2.right /= 3;
        ((AbstractButton)o).setMargin(margin);
        return (AbstractButton)o;
    }
    
    public static rp_aH a(final rp_aJ rp_aJ, rp_f rp_f, String actionCommand, final String s, final ActionListener actionListener) {
        rp_f = new rp_f(rp_f.a());
        final rp_aH rp_aH;
        (rp_aH = new rp_aH(new JTree(rp_f), null, true, rp_aJ)).setActionCommand(actionCommand);
        if (actionListener != null) {
            rp_aH.addActionListener(actionListener);
        }
        final rp_aH rp_aH2 = rp_aH;
        final rp_fb a = rp_aJ.a();
        actionCommand = s;
        rp_aH2.setToolTipText(rp_aJ.a(a.a(0, actionCommand)));
        rp_aH.b = true;
        rp_aH.b();
        return rp_aH;
    }
}
