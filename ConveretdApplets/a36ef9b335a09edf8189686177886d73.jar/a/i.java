// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.awt.Choice;

public final class i extends Choice
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
        if (color.equals(i.q)) {
            this.select(6);
            return;
        }
        if (color.equals(i.w)) {
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
        if (color.equals(i.e)) {
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
                return i.q;
            }
            case 7: {
                return i.w;
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
                return i.e;
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
                return i.r;
            }
            default: {
                return null;
            }
        }
    }
    
    public i() {
        this.addItem(cv.q("Black"));
        this.addItem(cv.q("Blue"));
        this.addItem(cv.q("Cyan"));
        this.addItem(cv.q("Dark Gray"));
        this.addItem(cv.q("Gray"));
        this.addItem(cv.q("Green"));
        this.addItem(cv.q("Lemon Yellow"));
        this.addItem(cv.q("Light Gray"));
        this.addItem(cv.q("Magenta"));
        this.addItem(cv.q("Orange"));
        this.addItem(cv.q("Pink"));
        this.addItem(cv.q("Platinum"));
        this.addItem(cv.q("Red"));
        this.addItem(cv.q("White"));
        this.addItem(cv.q("Yellow"));
        this.addItem(cv.q("Light Green"));
        this.setForeground(Color.black);
        this.setBackground(Color.white);
    }
    
    static {
        i.q = new Color(16777164);
        i.w = new Color(14540253);
        i.e = new Color(15658734);
        i.r = new Color(9041144);
    }
}
