// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.property.loader;

import java.awt.Font;
import jmaster.util.property.D;
import javax.swing.JLabel;
import jmaster.util.property.A;

public class FontPropertyLoader implements A
{
    public static final String FAMILY = "family";
    public static final String BOLD = "bold";
    public static final String ITALIC = "italic";
    public static final String SIZE = "size";
    protected JLabel A;
    static /* synthetic */ Class class$java$awt$Font;
    
    public FontPropertyLoader() {
        this.A = new JLabel();
    }
    
    public Object loadProperty(final String s, final D d, final String s2, final int n) {
        Object o = null;
        String s3 = null;
        if (d.containsKey(d.F(s, "family"))) {
            s3 = d.Q(d.F(s, "family"));
        }
        int n2 = 0;
        if (d.containsKey(d.F(s, "size"))) {
            n2 = d.D(d.F(s, "size"));
        }
        boolean b = false;
        int n3 = 0;
        if (d.containsKey(d.F(s, "bold")) && d.C(d.F(s, "bold"))) {
            n3 |= 0x1;
            b = true;
        }
        if (d.containsKey(d.F(s, "italic")) && d.C(d.F(s, "italic"))) {
            n3 |= 0x2;
            b = true;
        }
        if (s3 != null || n2 != 0 || b) {
            final Font font = this.A.getFont();
            if (s3 == null) {
                s3 = font.getFamily();
            }
            if (n2 == 0) {
                n2 = font.getSize();
            }
            o = new Font(s3, n3, n2);
        }
        return o;
    }
    
    public Class getPropertyClass() {
        return (FontPropertyLoader.class$java$awt$Font == null) ? (FontPropertyLoader.class$java$awt$Font = class$("java.awt.Font")) : FontPropertyLoader.class$java$awt$Font;
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
