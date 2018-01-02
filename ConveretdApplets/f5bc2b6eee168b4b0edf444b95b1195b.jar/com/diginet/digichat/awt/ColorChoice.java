// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import com.esial.util.LanguageSupport;
import java.awt.Color;
import java.awt.Choice;

public class ColorChoice extends Choice
{
    public static Color lemonYellow;
    public static Color lightGray;
    public static Color platinum;
    public static Color beige;
    public static Color lime;
    
    public final void select(final Color color) {
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
        else if (color.equals(ColorChoice.lemonYellow)) {
            this.select(6);
        }
        else if (color.equals(ColorChoice.lightGray)) {
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
        else if (color.equals(ColorChoice.platinum)) {
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
        else if (color.equals(ColorChoice.beige)) {
            this.select(15);
        }
        else if (color.equals(ColorChoice.lime)) {
            this.select(16);
        }
    }
    
    public final Color getSelectedColor() {
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
                return ColorChoice.lemonYellow;
            }
            case 7: {
                return ColorChoice.lightGray;
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
                return ColorChoice.platinum;
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
                return ColorChoice.beige;
            }
            case 16: {
                return ColorChoice.lime;
            }
            default: {
                return null;
            }
        }
    }
    
    public ColorChoice() {
        this.addItem(LanguageSupport.translate("Black"));
        this.addItem(LanguageSupport.translate("Blue"));
        this.addItem(LanguageSupport.translate("Cyan"));
        this.addItem(LanguageSupport.translate("Dark Gray"));
        this.addItem(LanguageSupport.translate("Gray"));
        this.addItem(LanguageSupport.translate("Green"));
        this.addItem(LanguageSupport.translate("Lemon Yellow"));
        this.addItem(LanguageSupport.translate("Light Gray"));
        this.addItem(LanguageSupport.translate("Magenta"));
        this.addItem(LanguageSupport.translate("Orange"));
        this.addItem(LanguageSupport.translate("Pink"));
        this.addItem(LanguageSupport.translate("Platinum"));
        this.addItem(LanguageSupport.translate("Red"));
        this.addItem(LanguageSupport.translate("White"));
        this.addItem(LanguageSupport.translate("Yellow"));
        this.addItem(LanguageSupport.translate("Gator Peach"));
        this.addItem(LanguageSupport.translate("Light Lime"));
    }
    
    static {
        ColorChoice.lemonYellow = new Color(16777164);
        ColorChoice.lightGray = new Color(14540253);
        ColorChoice.platinum = new Color(15658734);
        ColorChoice.beige = new Color(16773088);
        ColorChoice.lime = new Color(15002318);
    }
}
