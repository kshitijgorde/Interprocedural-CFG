// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.awt.Choice;

public final class ai extends Choice
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
        if (color.equals(ai.q)) {
            this.select(6);
            return;
        }
        if (color.equals(ai.w)) {
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
        if (color.equals(ai.e)) {
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
                return ai.q;
            }
            case 7: {
                return ai.w;
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
                return ai.e;
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
                return ai.r;
            }
            default: {
                return null;
            }
        }
    }
    
    public ai() {
        this.addItem(al.q("Black"));
        this.addItem(al.q("Blue"));
        this.addItem(al.q("Cyan"));
        this.addItem(al.q("Dark Gray"));
        this.addItem(al.q("Gray"));
        this.addItem(al.q("Green"));
        this.addItem(al.q("Lemon Yellow"));
        this.addItem(al.q("Light Gray"));
        this.addItem(al.q("Magenta"));
        this.addItem(al.q("Orange"));
        this.addItem(al.q("Pink"));
        this.addItem(al.q("Platinum"));
        this.addItem(al.q("Red"));
        this.addItem(al.q("White"));
        this.addItem(al.q("Yellow"));
        this.addItem(al.q("Light Green"));
        this.setForeground(Color.black);
        this.setBackground(Color.white);
    }
    
    static {
        ai.q = new Color(16777164);
        ai.w = new Color(14540253);
        ai.e = new Color(15658734);
        ai.r = new Color(9041144);
    }
}
