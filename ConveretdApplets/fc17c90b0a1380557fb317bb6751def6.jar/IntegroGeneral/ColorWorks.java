// 
// Decompiled by Procyon v0.5.30
// 

package IntegroGeneral;

import java.awt.Color;

public class ColorWorks
{
    public static Color getColorObject(final String name) {
        Color theColorObject = Color.white;
        if (name.equalsIgnoreCase("black")) {
            theColorObject = Color.black;
        }
        else if (name.equalsIgnoreCase("blue")) {
            theColorObject = Color.blue;
        }
        else if (name.equalsIgnoreCase("cyan")) {
            theColorObject = Color.cyan;
        }
        else if (name.equalsIgnoreCase("darkGray")) {
            theColorObject = Color.darkGray;
        }
        else if (name.equalsIgnoreCase("gray")) {
            theColorObject = Color.gray;
        }
        else if (name.equalsIgnoreCase("green")) {
            theColorObject = Color.green;
        }
        else if (name.equalsIgnoreCase("lightGray")) {
            theColorObject = Color.lightGray;
        }
        else if (name.equalsIgnoreCase("magenta")) {
            theColorObject = Color.magenta;
        }
        else if (name.equalsIgnoreCase("orange")) {
            theColorObject = Color.orange;
        }
        else if (name.equalsIgnoreCase("pink")) {
            theColorObject = Color.pink;
        }
        else if (name.equalsIgnoreCase("red")) {
            theColorObject = Color.red;
        }
        else if (name.equalsIgnoreCase("white")) {
            theColorObject = Color.white;
        }
        else if (name.equalsIgnoreCase("yellow")) {
            theColorObject = Color.yellow;
        }
        return theColorObject;
    }
}
