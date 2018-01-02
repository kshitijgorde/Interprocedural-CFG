// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class BorderFactory
{
    static final Border sharedRaisedBevel;
    static final Border sharedLoweredBevel;
    static final Border sharedEtchedBorder;
    static final Border emptyBorder;
    
    static {
        sharedRaisedBevel = new BevelBorder(0);
        sharedLoweredBevel = new BevelBorder(1);
        sharedEtchedBorder = new EtchedBorder();
        emptyBorder = new EmptyBorder(0, 0, 0, 0);
    }
    
    public static Border createBevelBorder(final int n) {
        return createSharedBevel(n);
    }
    
    public static Border createBevelBorder(final int n, final Color color, final Color color2) {
        return new BevelBorder(n, color, color2);
    }
    
    public static Border createBevelBorder(final int n, final Color color, final Color color2, final Color color3, final Color color4) {
        return new BevelBorder(n, color, color2, color3, color4);
    }
    
    public static CompoundBorder createCompoundBorder() {
        return new CompoundBorder();
    }
    
    public static CompoundBorder createCompoundBorder(final Border border, final Border border2) {
        return new CompoundBorder(border, border2);
    }
    
    public static Border createEmptyBorder() {
        return BorderFactory.emptyBorder;
    }
    
    public static Border createEmptyBorder(final int n, final int n2, final int n3, final int n4) {
        return new EmptyBorder(n, n2, n3, n4);
    }
    
    public static Border createEtchedBorder() {
        return BorderFactory.sharedEtchedBorder;
    }
    
    public static Border createEtchedBorder(final Color color, final Color color2) {
        return new EtchedBorder(color, color2);
    }
    
    public static Border createLineBorder(final Color color) {
        return new LineBorder(color, 1);
    }
    
    public static Border createLineBorder(final Color color, final int n) {
        return new LineBorder(color, n);
    }
    
    public static Border createLoweredBevelBorder() {
        return createSharedBevel(1);
    }
    
    public static MatteBorder createMatteBorder(final int n, final int n2, final int n3, final int n4, final Color color) {
        return new MatteBorder(n, n2, n3, n4, color);
    }
    
    public static MatteBorder createMatteBorder(final int n, final int n2, final int n3, final int n4, final Icon icon) {
        return new MatteBorder(n, n2, n3, n4, icon);
    }
    
    public static Border createRaisedBevelBorder() {
        return createSharedBevel(0);
    }
    
    static Border createSharedBevel(final int n) {
        if (n == 0) {
            return BorderFactory.sharedRaisedBevel;
        }
        if (n == 1) {
            return BorderFactory.sharedLoweredBevel;
        }
        return null;
    }
    
    public static TitledBorder createTitledBorder(final String s) {
        return new TitledBorder(s);
    }
    
    public static TitledBorder createTitledBorder(final Border border) {
        return new TitledBorder(border);
    }
    
    public static TitledBorder createTitledBorder(final Border border, final String s) {
        return new TitledBorder(border, s);
    }
    
    public static TitledBorder createTitledBorder(final Border border, final String s, final int n, final int n2) {
        return new TitledBorder(border, s, n, n2);
    }
    
    public static TitledBorder createTitledBorder(final Border border, final String s, final int n, final int n2, final Font font) {
        return new TitledBorder(border, s, n, n2, font);
    }
    
    public static TitledBorder createTitledBorder(final Border border, final String s, final int n, final int n2, final Font font, final Color color) {
        return new TitledBorder(border, s, n, n2, font, color);
    }
}
