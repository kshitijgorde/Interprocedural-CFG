// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import com.esial.util.LanguageSupport;
import java.awt.Color;
import javax.swing.JComboBox;

public class JColorChoice extends JComboBox
{
    public static Color lemonYellow;
    public static Color lightGray;
    public static Color platinum;
    public static Color beige;
    public static Color lime;
    
    public final void setSelectedIndex(final Color color) {
        if (color.equals(Color.black)) {
            this.setSelectedIndex(0);
        }
        else if (color.equals(Color.blue)) {
            this.setSelectedIndex(1);
        }
        else if (color.equals(Color.cyan)) {
            this.setSelectedIndex(2);
        }
        else if (color.equals(Color.darkGray)) {
            this.setSelectedIndex(3);
        }
        else if (color.equals(Color.gray)) {
            this.setSelectedIndex(4);
        }
        else if (color.equals(Color.green)) {
            this.setSelectedIndex(5);
        }
        else if (color.equals(JColorChoice.lemonYellow)) {
            this.setSelectedIndex(6);
        }
        else if (color.equals(JColorChoice.lightGray)) {
            this.setSelectedIndex(7);
        }
        else if (color.equals(Color.magenta)) {
            this.setSelectedIndex(8);
        }
        else if (color.equals(Color.orange)) {
            this.setSelectedIndex(9);
        }
        else if (color.equals(Color.pink)) {
            this.setSelectedIndex(10);
        }
        else if (color.equals(JColorChoice.platinum)) {
            this.setSelectedIndex(11);
        }
        else if (color.equals(Color.red)) {
            this.setSelectedIndex(12);
        }
        else if (color.equals(Color.white)) {
            this.setSelectedIndex(13);
        }
        else if (color.equals(Color.yellow)) {
            this.setSelectedIndex(14);
        }
        else if (color.equals(JColorChoice.beige)) {
            this.setSelectedIndex(15);
        }
        else if (color.equals(JColorChoice.lime)) {
            this.setSelectedIndex(16);
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
                return JColorChoice.lemonYellow;
            }
            case 7: {
                return JColorChoice.lightGray;
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
                return JColorChoice.platinum;
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
                return JColorChoice.beige;
            }
            case 16: {
                return JColorChoice.lime;
            }
            default: {
                return null;
            }
        }
    }
    
    public JColorChoice() {
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
        JColorChoice.lemonYellow = new Color(16777164);
        JColorChoice.lightGray = new Color(14540253);
        JColorChoice.platinum = new Color(15658734);
        JColorChoice.beige = new Color(16773088);
        JColorChoice.lime = new Color(15002318);
    }
}
