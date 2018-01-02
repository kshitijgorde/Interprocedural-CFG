// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.awt.Choice;

public final class ah extends Choice
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
        if (color.equals(ah.q)) {
            this.select(6);
            return;
        }
        if (color.equals(ah.w)) {
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
        if (color.equals(ah.e)) {
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
                return ah.q;
            }
            case 7: {
                return ah.w;
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
                return ah.e;
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
                return ah.r;
            }
            default: {
                return null;
            }
        }
    }
    
    public ah() {
        this.addItem(ak.q("Black"));
        this.addItem(ak.q("Blue"));
        this.addItem(ak.q("Cyan"));
        this.addItem(ak.q("Dark Gray"));
        this.addItem(ak.q("Gray"));
        this.addItem(ak.q("Green"));
        this.addItem(ak.q("Lemon Yellow"));
        this.addItem(ak.q("Light Gray"));
        this.addItem(ak.q("Magenta"));
        this.addItem(ak.q("Orange"));
        this.addItem(ak.q("Pink"));
        this.addItem(ak.q("Platinum"));
        this.addItem(ak.q("Red"));
        this.addItem(ak.q("White"));
        this.addItem(ak.q("Yellow"));
        this.addItem(ak.q("Light Green"));
        this.setForeground(Color.black);
        this.setBackground(Color.white);
    }
    
    static {
        ah.q = new Color(16777164);
        ah.w = new Color(14540253);
        ah.e = new Color(15658734);
        ah.r = new Color(9041144);
    }
}
