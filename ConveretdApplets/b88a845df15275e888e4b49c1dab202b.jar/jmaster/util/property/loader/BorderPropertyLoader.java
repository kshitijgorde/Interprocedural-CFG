// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.property.loader;

import javax.swing.border.CompoundBorder;
import javax.swing.border.Border;
import java.awt.Color;
import javax.swing.BorderFactory;
import jmaster.util.property.D;
import javax.swing.JLabel;
import jmaster.util.property.A;

public class BorderPropertyLoader implements A
{
    public static final String STYLE = "style";
    public static final String STYLE_LINE = "lineBorder";
    public static final String STYLE_EMPTY = "emptyBorder";
    public static final String STYLE_ETCHED = "etchedBorder";
    public static final String STYLE_COMPOUND = "compoundBorder";
    public static final String STYLE_ETCHED_TYPE = "etchedBorderType";
    public static final String STYLE_ETCHED_TYPE_RAISED = "raised";
    public static final String STYLE_ETCHED_TYPE_LOWERED = "lowered";
    public static final String COLOR = "color";
    public static final String COLOR_HIGHLIGHT = "colorHighlight";
    public static final String COLOR_SHADOW = "colorShadow";
    public static final String THICKNESS = "thickness";
    public static final String COMPOUND_BORDER_INNER = "innerBorder";
    public static final String COMPOUND_BORDER_OUTER = "outerBorder";
    protected JLabel C;
    static /* synthetic */ Class class$javax$swing$border$Border;
    
    public BorderPropertyLoader() {
        this.C = new JLabel();
    }
    
    public Object loadProperty(final String s, final D d, final String s2, final int n) {
        Object o = null;
        if (d.B(s, "style")) {
            final String q = d.Q(d.F(s, "style"));
            if ("emptyBorder".equals(q)) {
                if (d.B(s, "thickness")) {
                    final int[] i = d.I(d.F(s, "thickness"));
                    o = ((i != null && i.length > 0) ? BorderFactory.createEmptyBorder(i[0], (i.length > 1) ? i[1] : i[0], (i.length > 2) ? i[2] : i[0], (i.length > 3) ? i[3] : i[0]) : BorderFactory.createEmptyBorder());
                }
            }
            else if ("lineBorder".equals(q)) {
                Color black = Color.black;
                if (d.B(s, "color")) {
                    black = new Color(d.D(d.F(s, "color")));
                }
                if (d.B(s, "thickness")) {
                    o = BorderFactory.createLineBorder(black, d.D(d.F(s, "thickness")));
                }
                else {
                    o = BorderFactory.createLineBorder(black);
                }
            }
            else if ("etchedBorder".equals(q)) {
                Color color = null;
                if (d.B(s, "colorHighlight")) {
                    color = new Color(d.D(d.F(s, "colorHighlight")));
                }
                Color color2 = null;
                if (d.B(s, "colorShadow")) {
                    color2 = new Color(d.D(d.F(s, "colorShadow")));
                }
                Integer n2 = null;
                if (d.B(s, "etchedBorderType")) {
                    final String q2 = d.Q(d.F(s, "etchedBorderType"));
                    if ("raised".equals(q2)) {
                        n2 = new Integer(0);
                    }
                    else if ("lowered".equals(q2)) {
                        n2 = new Integer(1);
                    }
                }
                if (n2 != null && color != null && color2 != null) {
                    o = BorderFactory.createEtchedBorder(n2, color, color2);
                }
                else if (n2 != null) {
                    o = BorderFactory.createEtchedBorder(n2);
                }
                else if (color != null && color2 != null) {
                    o = BorderFactory.createEtchedBorder(color, color2);
                }
            }
            else if ("compoundBorder".equals(q)) {
                o = new CompoundBorder((Border)this.loadProperty(d.Q(d.F(s, "outerBorder")), d, null, 0), (Border)this.loadProperty(d.Q(d.F(s, "innerBorder")), d, null, 0));
            }
        }
        return o;
    }
    
    public Class getPropertyClass() {
        return (BorderPropertyLoader.class$javax$swing$border$Border == null) ? (BorderPropertyLoader.class$javax$swing$border$Border = class$("javax.swing.border.Border")) : BorderPropertyLoader.class$javax$swing$border$Border;
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
