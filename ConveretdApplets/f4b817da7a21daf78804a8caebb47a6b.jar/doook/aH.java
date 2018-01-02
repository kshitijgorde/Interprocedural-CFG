// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.Choice;

public class aH extends Choice
{
    public static Color a;
    public static Color b;
    public static Color c;
    
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
        else if (color.equals(aH.a)) {
            this.select(6);
        }
        else if (color.equals(aH.b)) {
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
        else if (color.equals(aH.c)) {
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
    
    public Color a() {
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
                return aH.a;
            }
            case 7: {
                return aH.b;
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
                return aH.c;
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
    
    public aH() {
        this.addItem(aG.a("Black"));
        this.addItem(aG.a("Blue"));
        this.addItem(aG.a("Cyan"));
        this.addItem(aG.a("Dark Gray"));
        this.addItem(aG.a("Gray"));
        this.addItem(aG.a("Green"));
        this.addItem(aG.a("Lemon Yellow"));
        this.addItem(aG.a("Light Gray"));
        this.addItem(aG.a("Magenta"));
        this.addItem(aG.a("Orange"));
        this.addItem(aG.a("Pink"));
        this.addItem(aG.a("Platinum"));
        this.addItem(aG.a("Red"));
        this.addItem(aG.a("White"));
        this.addItem(aG.a("Yellow"));
    }
    
    static {
        aH.a = new Color(16777164);
        aH.b = new Color(14540253);
        aH.c = new Color(15658734);
    }
}
