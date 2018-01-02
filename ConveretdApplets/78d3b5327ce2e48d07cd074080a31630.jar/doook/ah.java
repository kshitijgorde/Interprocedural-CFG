// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.Choice;

public class ah extends Choice
{
    public static Color c;
    public static Color i;
    public static Color a;
    
    public void a(final Color color) {
        if (color.equals(Color.black)) {
            this.select(0);
        }
        else if (color.equals(Color.blue)) {
            this.select(1);
        }
        else if (color.equals(Color.cyan)) {
            this.select(2);
        }
        else if (color.equals(Color.darkGray)) {
            this.select(3);
        }
        else if (color.equals(Color.gray)) {
            this.select(4);
        }
        else if (color.equals(Color.green)) {
            this.select(5);
        }
        else if (color.equals(ah.c)) {
            this.select(6);
        }
        else if (color.equals(ah.i)) {
            this.select(7);
        }
        else if (color.equals(Color.magenta)) {
            this.select(8);
        }
        else if (color.equals(Color.orange)) {
            this.select(9);
        }
        else if (color.equals(Color.pink)) {
            this.select(10);
        }
        else if (color.equals(ah.a)) {
            this.select(11);
        }
        else if (color.equals(Color.red)) {
            this.select(12);
        }
        else if (color.equals(Color.white)) {
            this.select(13);
        }
        else if (color.equals(Color.yellow)) {
            this.select(14);
        }
    }
    
    public Color b() {
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
                return ah.c;
            }
            case 7: {
                return ah.i;
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
                return ah.a;
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
            default: {
                return null;
            }
        }
    }
    
    public ah() {
        this.addItem(ar.b("Black"));
        this.addItem(ar.b("Blue"));
        this.addItem(ar.b("Cyan"));
        this.addItem(ar.b("Dark Gray"));
        this.addItem(ar.b("Gray"));
        this.addItem(ar.b("Green"));
        this.addItem(ar.b("Lemon Yellow"));
        this.addItem(ar.b("Light Gray"));
        this.addItem(ar.b("Magenta"));
        this.addItem(ar.b("Orange"));
        this.addItem(ar.b("Pink"));
        this.addItem(ar.b("Platinum"));
        this.addItem(ar.b("Red"));
        this.addItem(ar.b("White"));
        this.addItem(ar.b("Yellow"));
    }
    
    static {
        ah.c = new Color(16777164);
        ah.i = new Color(14540253);
        ah.a = new Color(15658734);
    }
}
