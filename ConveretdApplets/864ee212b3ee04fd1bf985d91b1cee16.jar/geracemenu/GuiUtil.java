// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Image;
import java.awt.Component;
import java.awt.Color;
import java.util.Hashtable;

public final class GuiUtil
{
    protected static Hashtable colors;
    public static VImage NULL_ICON;
    
    public static Color findColor(String substring, final Color color) {
        if (substring.trim().charAt(0) == '#') {
            substring = substring.substring(1);
            return new Color(Integer.parseInt(substring.substring(0, 2), 16), Integer.parseInt(substring.substring(2, 4), 16), Integer.parseInt(substring.substring(4, 6), 16));
        }
        final Color color2 = GuiUtil.colors.get(substring);
        if (color2 != null) {
            return color2;
        }
        return color;
    }
    
    public static final Image filterImage(final Component component, final TransparencyFilter transparencyFilter) {
        return component.createImage(new FilteredImageSource(transparencyFilter.getSource(), transparencyFilter));
    }
    
    static {
        GuiUtil.NULL_ICON = new VImage(new byte[] { 71, 73, 70, 56, 57, 97, 16, 0, 16, 0, -128, 0, 0, -1, -1, -1, 0, 0, 0, 33, -7, 4, 1, 0, 0, 0, 0, 44, 0, 0, 0, 0, 16, 0, 16, 0, 0, 2, 14, -124, -113, -87, -53, -19, 15, -93, -100, -76, -38, -117, -77, 62, 5, 0, 59 });
        (GuiUtil.colors = new Hashtable()).put("black", Color.black);
        GuiUtil.colors.put("blue", Color.blue);
        GuiUtil.colors.put("cyan", Color.cyan);
        GuiUtil.colors.put("darkgray", Color.darkGray);
        GuiUtil.colors.put("gray", Color.gray);
        GuiUtil.colors.put("green", Color.green);
        GuiUtil.colors.put("lightgray", Color.lightGray);
        GuiUtil.colors.put("magenta", Color.magenta);
        GuiUtil.colors.put("orange", Color.orange);
        GuiUtil.colors.put("pink", Color.pink);
        GuiUtil.colors.put("red", Color.red);
        GuiUtil.colors.put("white", Color.white);
        GuiUtil.colors.put("yellow", Color.yellow);
    }
}
