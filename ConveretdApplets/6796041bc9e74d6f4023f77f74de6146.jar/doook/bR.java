// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.Choice;

public class bR extends Choice
{
    public static Color c;
    public static Color h;
    public static Color a;
    
    public void b(final Color color) {
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
        else if (color.equals(bR.c)) {
            this.select(6);
        }
        else if (color.equals(bR.h)) {
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
        else if (color.equals(bR.a)) {
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
                return bR.c;
            }
            case 7: {
                return bR.h;
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
                return bR.a;
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
    
    public bR() {
        this.addItem(ao.e("Black"));
        this.addItem(ao.e("Blue"));
        this.addItem(ao.e("Cyan"));
        this.addItem(ao.e("Dark Gray"));
        this.addItem(ao.e("Gray"));
        this.addItem(ao.e("Green"));
        this.addItem(ao.e("Lemon Yellow"));
        this.addItem(ao.e("Light Gray"));
        this.addItem(ao.e("Magenta"));
        this.addItem(ao.e("Orange"));
        this.addItem(ao.e("Pink"));
        this.addItem(ao.e("Platinum"));
        this.addItem(ao.e("Red"));
        this.addItem(ao.e("White"));
        this.addItem(ao.e("Yellow"));
    }
    
    static {
        bR.c = new Color(16777164);
        bR.h = new Color(14540253);
        bR.a = new Color(15658734);
    }
}
