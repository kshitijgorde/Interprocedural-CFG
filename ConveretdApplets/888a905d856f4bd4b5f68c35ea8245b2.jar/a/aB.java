// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.awt.Choice;

public final class aB extends Choice
{
    public static Color q;
    public static Color w;
    public static Color e;
    private static Color r;
    
    public final void q(final Color color) {
        if (color.equals(Color.black)) {
            this.select(0);
            return;
        }
        if (color.equals(Color.blue)) {
            this.select(1);
            return;
        }
        if (color.equals(Color.cyan)) {
            this.select(2);
            return;
        }
        if (color.equals(Color.darkGray)) {
            this.select(3);
            return;
        }
        if (color.equals(Color.gray)) {
            this.select(4);
            return;
        }
        if (color.equals(Color.green)) {
            this.select(5);
            return;
        }
        if (color.equals(aB.q)) {
            this.select(6);
            return;
        }
        if (color.equals(aB.w)) {
            this.select(7);
            return;
        }
        if (color.equals(Color.magenta)) {
            this.select(8);
            return;
        }
        if (color.equals(Color.orange)) {
            this.select(9);
            return;
        }
        if (color.equals(Color.pink)) {
            this.select(10);
            return;
        }
        if (color.equals(aB.e)) {
            this.select(11);
            return;
        }
        if (color.equals(Color.red)) {
            this.select(12);
            return;
        }
        if (color.equals(Color.white)) {
            this.select(13);
            return;
        }
        if (color.equals(Color.yellow)) {
            this.select(14);
        }
    }
    
    public final Color q() {
        switch (this.getSelectedIndex()) {
            case 0: {
                return Color.black;
            }
            case 1: {
                return Color.blue;
            }
            case 2: {
                return Color.cyan;
            }
            case 3: {
                return Color.darkGray;
            }
            case 4: {
                return Color.gray;
            }
            case 5: {
                return Color.green;
            }
            case 6: {
                return aB.q;
            }
            case 7: {
                return aB.w;
            }
            case 8: {
                return Color.magenta;
            }
            case 9: {
                return Color.orange;
            }
            case 10: {
                return Color.pink;
            }
            case 11: {
                return aB.e;
            }
            case 12: {
                return Color.red;
            }
            case 13: {
                return Color.white;
            }
            case 14: {
                return Color.yellow;
            }
            case 15: {
                return aB.r;
            }
            default: {
                return null;
            }
        }
    }
    
    public aB() {
        this.addItem(be.w("Black"));
        this.addItem(be.w("Blue"));
        this.addItem(be.w("Cyan"));
        this.addItem(be.w("Dark Gray"));
        this.addItem(be.w("Gray"));
        this.addItem(be.w("Green"));
        this.addItem(be.w("Lemon Yellow"));
        this.addItem(be.w("Light Gray"));
        this.addItem(be.w("Magenta"));
        this.addItem(be.w("Orange"));
        this.addItem(be.w("Pink"));
        this.addItem(be.w("Platinum"));
        this.addItem(be.w("Red"));
        this.addItem(be.w("White"));
        this.addItem(be.w("Yellow"));
        this.addItem(be.w("Light Green"));
        this.setForeground(Color.black);
        this.setBackground(Color.white);
    }
    
    static {
        aB.q = new Color(16777164);
        aB.w = new Color(14540253);
        aB.e = new Color(15658734);
        aB.r = new Color(9041144);
    }
}
