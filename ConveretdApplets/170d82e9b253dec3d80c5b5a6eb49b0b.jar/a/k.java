// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.awt.Choice;

public final class k extends Choice
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
        if (color.equals(k.q)) {
            this.select(6);
            return;
        }
        if (color.equals(k.w)) {
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
        if (color.equals(k.e)) {
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
                return k.q;
            }
            case 7: {
                return k.w;
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
                return k.e;
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
                return k.r;
            }
            default: {
                return null;
            }
        }
    }
    
    public k() {
        this.addItem(eb.q("Black"));
        this.addItem(eb.q("Blue"));
        this.addItem(eb.q("Cyan"));
        this.addItem(eb.q("Dark Gray"));
        this.addItem(eb.q("Gray"));
        this.addItem(eb.q("Green"));
        this.addItem(eb.q("Lemon Yellow"));
        this.addItem(eb.q("Light Gray"));
        this.addItem(eb.q("Magenta"));
        this.addItem(eb.q("Orange"));
        this.addItem(eb.q("Pink"));
        this.addItem(eb.q("Platinum"));
        this.addItem(eb.q("Red"));
        this.addItem(eb.q("White"));
        this.addItem(eb.q("Yellow"));
        this.addItem(eb.q("Light Green"));
        this.setForeground(Color.black);
        this.setBackground(Color.white);
    }
    
    static {
        k.q = new Color(16777164);
        k.w = new Color(14540253);
        k.e = new Color(15658734);
        k.r = new Color(9041144);
    }
}
