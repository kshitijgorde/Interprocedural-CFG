// 
// Decompiled by Procyon v0.5.30
// 

package speedometer.A;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.Icon;
import java.awt.Insets;
import javax.swing.AbstractButton;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Dimension;

public class B
{
    public static final String b = "preferredSize";
    public static final String c = "minimumSize";
    public static final String O = "maximumSize";
    public static final String e = "background";
    public static final String _ = "foreground";
    public static final String d = "font.family";
    public static final String Z = "font.bold";
    public static final String E = "font.italic";
    public static final String f = "font.size";
    public static final String L = "border.style";
    public static final String D = "lineBorder";
    public static final String R = "emptyBorder";
    public static final String Y = "etchedBorder";
    public static final String U = "border.etchedBorderType";
    public static final String W = "raised";
    public static final String a = "lowered";
    public static final String A = "border.color";
    public static final String J = "border.colorHighlight";
    public static final String K = "border.colorShadow";
    public static final String T = "border.thickness";
    public static final String G = "text";
    public static final String i = "align";
    public static final String j = "left";
    public static final String M = "center";
    public static final String I = "right";
    public static final String g = "icon";
    public static final String Q = "text";
    public static final String N = "margin";
    public static final String F = "icon";
    public static final String B = "icon_pressed";
    public static final String P = "icon_selected";
    public static final String C = "icon_rollover";
    public static final String h = "icon_rolloverSelected";
    public static final String S = "icon_disabled";
    public static final String H = "items";
    public static final String V = "selectedItem";
    static B X;
    
    static {
        speedometer.A.B.X = null;
    }
    
    public static synchronized B A() {
        if (speedometer.A.B.X == null) {
            speedometer.A.B.X = new B();
        }
        return speedometer.A.B.X;
    }
    
    public boolean A(final Object o, final F f, final String s) {
        return f.containsKey(String.valueOf(s) + o);
    }
    
    public Dimension A(final String s, final F f) {
        final int[] g = f.G(s);
        return new Dimension(g[0], g[1]);
    }
    
    public Dimension A(final String s, final F f, final Dimension dimension) {
        try {
            return this.A(s, f);
        }
        catch (Exception ex) {
            return dimension;
        }
    }
    
    public Dimension A(final String s, final F f, final String s2) {
        return this.A(String.valueOf(s2) + s, f);
    }
    
    public void A(final JComponent component, final F f, final String s) {
        if (this.A((Object)"preferredSize", f, s)) {
            component.setPreferredSize(this.A("preferredSize", f, s));
        }
        if (this.A((Object)"minimumSize", f, s)) {
            component.setMinimumSize(this.A("minimumSize", f, s));
        }
        if (this.A((Object)"maximumSize", f, s)) {
            component.setMaximumSize(this.A("maximumSize", f, s));
        }
        if (this.A((Object)"background", f, s)) {
            component.setBackground(new Color(f.B(String.valueOf(s) + "background")));
        }
        if (this.A((Object)"foreground", f, s)) {
            component.setForeground(new Color(f.B(String.valueOf(s) + "foreground")));
        }
        String s2 = null;
        if (this.A((Object)"font.family", f, s)) {
            s2 = f.N(String.valueOf(s) + "font.family");
        }
        int n = 0;
        if (this.A((Object)"font.size", f, s)) {
            n = f.B(String.valueOf(s) + "font.size");
        }
        boolean b = false;
        int n2 = 0;
        if (this.A((Object)"font.bold", f, s) && f.I(String.valueOf(s) + "font.bold")) {
            n2 |= 0x1;
            b = true;
        }
        if (this.A((Object)"font.italic", f, s) && f.I(String.valueOf(s) + "font.italic")) {
            n2 |= 0x2;
            b = true;
        }
        if (s2 != null || n != 0 || b) {
            final Font font = component.getFont();
            if (s2 == null) {
                s2 = font.getFamily();
            }
            if (n == 0) {
                n = font.getSize();
            }
            component.setFont(new Font(s2, n2, n));
        }
        Border border = null;
        if (this.A((Object)"border.style", f, s)) {
            final String n3 = f.N(String.valueOf(s) + "border.style");
            if ("emptyBorder".equals(n3)) {
                if (this.A((Object)"border.thickness", f, s)) {
                    final int[] g = f.G(String.valueOf(s) + "border.thickness");
                    border = ((g != null && g.length > 0) ? BorderFactory.createEmptyBorder(g[0], (g.length > 1) ? g[1] : g[0], (g.length > 2) ? g[2] : g[0], (g.length > 3) ? g[3] : g[0]) : BorderFactory.createEmptyBorder());
                }
            }
            else if ("lineBorder".equals(n3)) {
                Color black = Color.black;
                if (this.A((Object)"border.color", f, s)) {
                    black = new Color(f.B(String.valueOf(s) + "border.color"));
                }
                if (this.A((Object)"border.thickness", f, s)) {
                    border = BorderFactory.createLineBorder(black, f.B(String.valueOf(s) + "border.thickness"));
                }
                else {
                    border = BorderFactory.createLineBorder(black);
                }
            }
            else if ("etchedBorder".equals(n3)) {
                Color color = null;
                if (this.A((Object)"border.colorHighlight", f, s)) {
                    color = new Color(f.B(String.valueOf(s) + "border.colorHighlight"));
                }
                Color color2 = null;
                if (this.A((Object)"border.colorShadow", f, s)) {
                    color2 = new Color(f.B(String.valueOf(s) + "border.colorShadow"));
                }
                Integer n4 = null;
                if (this.A((Object)"border.etchedBorderType", f, s)) {
                    final String n5 = f.N(String.valueOf(s) + "border.etchedBorderType");
                    if ("raised".equals(n5)) {
                        n4 = new Integer(0);
                    }
                    else if ("lowered".equals(n5)) {
                        n4 = new Integer(1);
                    }
                }
                if (n4 != null && color != null && color2 != null) {
                    border = BorderFactory.createEtchedBorder(n4, color, color2);
                }
                else if (n4 != null) {
                    border = BorderFactory.createEtchedBorder(n4);
                }
                else if (color != null && color2 != null) {
                    border = BorderFactory.createEtchedBorder(color, color2);
                }
            }
        }
        if (border != null) {
            component.setBorder(border);
        }
    }
    
    public void A(final AbstractButton abstractButton, final F f, final String s) {
        this.A((JComponent)abstractButton, f, s);
        if (this.A((Object)"text", f, s)) {
            abstractButton.setText(f.N(String.valueOf(s) + "text"));
        }
        if (this.A((Object)"margin", f, s)) {
            final int[] g = f.G(String.valueOf(s) + "margin");
            Insets margin = new Insets(g[0], g[0], g[0], g[0]);
            if (g.length == 4) {
                margin = new Insets(g[0], g[1], g[2], g[3]);
            }
            abstractButton.setMargin(margin);
        }
        if (this.A((Object)"icon", f, s)) {
            abstractButton.setIcon(speedometer.A.D.A(f.N(String.valueOf(s) + "icon")));
        }
        if (this.A((Object)"icon_pressed", f, s)) {
            abstractButton.setPressedIcon(speedometer.A.D.A(f.N(String.valueOf(s) + "icon_pressed")));
        }
        if (this.A((Object)"icon_selected", f, s)) {
            abstractButton.setSelectedIcon(speedometer.A.D.A(f.N(String.valueOf(s) + "icon_selected")));
        }
        if (this.A((Object)"icon_rollover", f, s)) {
            abstractButton.setRolloverIcon(speedometer.A.D.A(f.N(String.valueOf(s) + "icon_rollover")));
            abstractButton.setRolloverEnabled(true);
        }
        if (this.A((Object)"icon_rolloverSelected", f, s)) {
            abstractButton.setRolloverSelectedIcon(speedometer.A.D.A(f.N(String.valueOf(s) + "icon_rolloverSelected")));
        }
        if (this.A((Object)"icon_disabled", f, s)) {
            abstractButton.setDisabledIcon(speedometer.A.D.A(f.N(String.valueOf(s) + "icon_disabled")));
        }
    }
    
    public JLabel A(final F f, final String s) {
        final JLabel label = new JLabel();
        this.A(label, f, s);
        if (this.A((Object)"text", f, s)) {
            label.setText(f.N(String.valueOf(s) + "text"));
        }
        if (this.A((Object)"align", f, s)) {
            final String n = f.N(String.valueOf(s) + "align");
            Integer n2 = null;
            if ("left".equals(n)) {
                n2 = new Integer(2);
            }
            else if ("center".equals(n)) {
                n2 = new Integer(0);
            }
            else if ("right".equals(n)) {
                n2 = new Integer(4);
            }
            if (n2 != null) {
                label.setHorizontalAlignment(n2);
            }
        }
        if (this.A((Object)"icon", f, s)) {
            label.setIcon(speedometer.A.D.A(f.N(String.valueOf(s) + "icon")));
        }
        return label;
    }
    
    public JToggleButton B(final F f, final String s) {
        final JToggleButton toggleButton = new JToggleButton();
        this.A((JComponent)toggleButton, f, s);
        this.A(toggleButton, f, s);
        return toggleButton;
    }
    
    public JButton D(final F f, final String s) {
        final JButton button = new JButton();
        this.A((JComponent)button, f, s);
        this.A(button, f, s);
        return button;
    }
    
    public JComboBox C(final F f, final String s) {
        Object[] f2 = null;
        if (this.A((Object)"items", f, s)) {
            f2 = f.F(String.valueOf(s) + "items");
        }
        int b = -1;
        if (this.A((Object)"selectedItem", f, s)) {
            b = f.B(String.valueOf(s) + "selectedItem");
        }
        final JComboBox comboBox = (f2 == null) ? new JComboBox() : new JComboBox(f2);
        if (b != -1) {
            comboBox.setSelectedIndex(b);
        }
        this.A(comboBox, f, s);
        return comboBox;
    }
}
