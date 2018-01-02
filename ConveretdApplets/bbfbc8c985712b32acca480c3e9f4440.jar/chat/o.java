// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;
import java.awt.Choice;

public final class o extends Choice
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
        if (color.equals(o.a)) {
            this.select(6);
            return;
        }
        if (color.equals(o.b)) {
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
        if (color.equals(o.c)) {
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
                return o.a;
            }
            case 7: {
                return o.b;
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
                return o.c;
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
    
    public o() {
        this.addItem(aS.a(480));
        this.addItem(aS.a(481));
        this.addItem(aS.a(482));
        this.addItem(aS.a(483));
        this.addItem(aS.a(484));
        this.addItem(aS.a(485));
        this.addItem(aS.a(486));
        this.addItem(aS.a(487));
        this.addItem(aS.a(488));
        this.addItem(aS.a(489));
        this.addItem(aS.a(490));
        this.addItem(aS.a(491));
        this.addItem(aS.a(492));
        this.addItem(aS.a(493));
        this.addItem(aS.a(494));
    }
    
    static {
        o.a = new Color(16777164);
        o.b = new Color(14540253);
        o.c = new Color(15658734);
        o.d = new Color(15658734);
        o.e = Color.white;
        o.a = false;
    }
}
