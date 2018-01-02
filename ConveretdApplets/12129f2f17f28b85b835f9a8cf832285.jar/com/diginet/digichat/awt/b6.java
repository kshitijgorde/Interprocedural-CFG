// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import com.esial.util.d;
import java.awt.Color;
import java.awt.Choice;

public class b6 extends Choice
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
        else if (color.equals(b6.a)) {
            this.select(6);
        }
        else if (color.equals(b6.b)) {
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
        else if (color.equals(b6.c)) {
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
                return b6.a;
            }
            case 7: {
                return b6.b;
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
                return b6.c;
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
    
    public b6() {
        this.addItem(d.a("Black"));
        this.addItem(d.a("Blue"));
        this.addItem(d.a("Cyan"));
        this.addItem(d.a("Dark Gray"));
        this.addItem(d.a("Gray"));
        this.addItem(d.a("Green"));
        this.addItem(d.a("Lemon Yellow"));
        this.addItem(d.a("Light Gray"));
        this.addItem(d.a("Magenta"));
        this.addItem(d.a("Orange"));
        this.addItem(d.a("Pink"));
        this.addItem(d.a("Platinum"));
        this.addItem(d.a("Red"));
        this.addItem(d.a("White"));
        this.addItem(d.a("Yellow"));
    }
    
    static {
        b6.a = new Color(16777164);
        b6.b = new Color(14540253);
        b6.c = new Color(15658734);
    }
}
