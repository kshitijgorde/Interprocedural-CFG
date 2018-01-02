// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util.$XG;

import java.awt.Color;

public class $GI extends Color implements $YG
{
    static Color $HI(String s) {
        s = s.trim();
        if (s.startsWith("#")) {
            return new Color(Integer.parseInt(s.substring(1), 16));
        }
        s = s.toLowerCase();
        for (int i = 0; i < $YG.$ZH.length; ++i) {
            if (s.equals($YG.$ZH[i])) {
                return $YG.$YH[i];
            }
        }
        return new Color(Integer.parseInt(s));
    }
    
    boolean $II() {
        for (int i = 0; i < $YG.$YH.length; ++i) {
            if (this.equals($YG.$YH[i])) {
                return true;
            }
        }
        return false;
    }
    
    void $JI(final StringBuffer sb, final int n) {
        if (n < 16) {
            sb.append('0');
        }
        sb.append(Integer.toString(n, 16));
    }
    
    public $GI(final float n, final float n2, final float n3) {
        super(n, n2, n3);
    }
    
    public $GI(final int n) {
        super(n);
    }
    
    public $GI(final int n, final int n2, final int n3) {
        super(n, n2, n3);
    }
    
    public $GI(final Color color) {
        super(color.getRed(), color.getGreen(), color.getBlue());
    }
    
    public $GI(final String s) {
        this($HI(s));
    }
    
    public String toString() {
        for (int i = 0; i < $YG.$YH.length; ++i) {
            if (this.equals($YG.$YH[i])) {
                return $YG.$ZH[i];
            }
        }
        final StringBuffer sb = new StringBuffer("#");
        final int red = this.getRed();
        if (red != 0) {
            this.$JI(sb, red);
        }
        final int green = this.getGreen();
        if (green != 0) {
            this.$JI(sb, green);
        }
        this.$JI(sb, this.getBlue());
        return sb.toString();
    }
}
