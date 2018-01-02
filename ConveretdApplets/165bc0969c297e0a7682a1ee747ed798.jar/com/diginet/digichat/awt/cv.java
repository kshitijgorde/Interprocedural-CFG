// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import com.esial.util.c;
import java.awt.Color;
import java.awt.Choice;

public class cv extends Choice
{
    public static Color a;
    public static Color b;
    public static Color c;
    public static Color clrLightMag;
    public static Color clrLightGrn;
    
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
        else if (color.equals(cv.a)) {
            this.select(6);
        }
        else if (color.equals(cv.b)) {
            this.select(7);
        }
        else if (color.equals(cv.clrLightMag)) {
            this.select(8);
        }
        else if (color.equals(cv.clrLightGrn)) {
            this.select(9);
        }
        else if (color.equals(Color.magenta)) {
            this.select(10);
        }
        else if (color.equals(Color.orange)) {
            this.select(11);
        }
        else if (color.equals(Color.pink)) {
            this.select(12);
        }
        else if (color.equals(cv.c)) {
            this.select(13);
        }
        else if (color.equals(Color.red)) {
            this.select(14);
        }
        else if (color.equals(Color.white)) {
            this.select(15);
        }
        else if (color.equals(Color.yellow)) {
            this.select(16);
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
                return cv.a;
            }
            case 7: {
                return cv.b;
            }
            case 8: {
                return cv.clrLightMag;
            }
            case 9: {
                return cv.clrLightGrn;
            }
            case 10: {
                return Color.magenta;
            }
            case 11: {
                return Color.orange;
            }
            case 12: {
                return Color.pink;
            }
            case 13: {
                return cv.c;
            }
            case 14: {
                return Color.red;
            }
            case 15: {
                return Color.white;
            }
            case 16: {
                return Color.yellow;
            }
            default: {
                return null;
            }
        }
    }
    
    public cv() {
        this.addItem(com.esial.util.c.a("Black"));
        this.addItem(com.esial.util.c.a("Blue"));
        this.addItem(com.esial.util.c.a("Cyan"));
        this.addItem(com.esial.util.c.a("Dark Gray"));
        this.addItem(com.esial.util.c.a("Gray"));
        this.addItem(com.esial.util.c.a("Green"));
        this.addItem(com.esial.util.c.a("Lemon Yellow"));
        this.addItem(com.esial.util.c.a("Light Gray"));
        this.addItem(com.esial.util.c.a("Light Magenta"));
        this.addItem(com.esial.util.c.a("Light Green"));
        this.addItem(com.esial.util.c.a("Magenta"));
        this.addItem(com.esial.util.c.a("Orange"));
        this.addItem(com.esial.util.c.a("Pink"));
        this.addItem(com.esial.util.c.a("Platinum"));
        this.addItem(com.esial.util.c.a("Red"));
        this.addItem(com.esial.util.c.a("White"));
        this.addItem(com.esial.util.c.a("Yellow"));
    }
    
    static {
        cv.a = new Color(16777164);
        cv.b = new Color(14540253);
        cv.c = new Color(15658734);
        cv.clrLightMag = Color.magenta.brighter();
        cv.clrLightGrn = new Color(11599792);
    }
}
