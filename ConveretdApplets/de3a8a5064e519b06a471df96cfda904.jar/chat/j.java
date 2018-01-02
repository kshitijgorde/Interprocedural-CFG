// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;
import java.awt.Choice;

public final class j extends Choice
{
    public static Color a;
    public static Color b;
    public static Color c;
    public static Color d;
    public static Color e;
    public static boolean a;
    
    public final void a(final Color color) {
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
        if (color.equals(j.a)) {
            this.select(6);
            return;
        }
        if (color.equals(j.b)) {
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
        if (color.equals(j.c)) {
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
    
    public final Color a() {
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
                return j.a;
            }
            case 7: {
                return j.b;
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
                return j.c;
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
    
    public j() {
        this.addItem(ak.a(480));
        this.addItem(ak.a(481));
        this.addItem(ak.a(482));
        this.addItem(ak.a(483));
        this.addItem(ak.a(484));
        this.addItem(ak.a(485));
        this.addItem(ak.a(486));
        this.addItem(ak.a(487));
        this.addItem(ak.a(488));
        this.addItem(ak.a(489));
        this.addItem(ak.a(490));
        this.addItem(ak.a(491));
        this.addItem(ak.a(492));
        this.addItem(ak.a(493));
        this.addItem(ak.a(494));
    }
    
    static {
        j.a = new Color(16777164);
        j.b = new Color(14540253);
        j.c = new Color(15658734);
        j.d = new Color(15658734);
        j.e = Color.white;
        j.a = false;
    }
}
