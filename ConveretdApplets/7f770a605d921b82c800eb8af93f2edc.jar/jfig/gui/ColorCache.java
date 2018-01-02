// 
// Decompiled by Procyon v0.5.30
// 

package jfig.gui;

import java.util.Enumeration;
import java.awt.Color;
import java.util.Vector;
import java.util.Hashtable;

public class ColorCache
{
    private static ColorCache _colorCache;
    public static int BLACK;
    public static int BLUE;
    public static int GREEN;
    public static int CYAN;
    public static int RED;
    public static int MAGENTA;
    public static int YELLOW;
    public static int WHITE;
    public static int BLUE4;
    public static int BLUE3;
    public static int BLUE2;
    public static int LTBLUE;
    public static int GREEN4;
    public static int GREEN3;
    public static int GREEN2;
    public static int CYAN4;
    public static int CYAN3;
    public static int CYAN2;
    public static int RED4;
    public static int RED3;
    public static int RED2;
    public static int MAGENTA4;
    public static int MAGENTA3;
    public static int MAGENTA2;
    public static int BROWN4;
    public static int BROWN3;
    public static int BROWN2;
    public static int PINK4;
    public static int PINK3;
    public static int PINK2;
    public static int PINK;
    public static int GOLD;
    private Hashtable nameHT;
    private Hashtable intHT;
    private Hashtable indexHT;
    private Hashtable colorNumberHT;
    private Vector figIndexVector;
    private long timestamp;
    private boolean debug;
    
    public static synchronized ColorCache getColorCache() {
        if (ColorCache._colorCache == null) {
            ColorCache._colorCache = new ColorCache();
        }
        return ColorCache._colorCache;
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }
    
    public void setDebug(final boolean debug) {
        this.debug = debug;
    }
    
    public void put(final String s, final int n, final Color color) {
        if (this.debug) {
            System.out.println("-#- ColorCache.put: '" + s + "' " + n + " " + color);
        }
        final Integer n2 = new Integer(n);
        if (this.intHT.get(n2) == null) {
            if (n >= this.figIndexVector.size()) {
                this.figIndexVector.addElement(n2);
            }
        }
        this.nameHT.put(s, color);
        this.intHT.put(n2, color);
        this.indexHT.put(n2, s);
        this.colorNumberHT.put(color, n2);
        this.timestamp = System.currentTimeMillis();
        if (this.debug) {
            System.out.println("-#- fiv.size= " + this.figIndexVector.size() + " intHT.size=" + this.intHT.size());
        }
    }
    
    public Color get(final String s) {
        if (s == null) {
            return null;
        }
        return this.nameHT.get(s);
    }
    
    public Color get(int n) {
        if (n < 0) {
            n = 0;
        }
        return this.intHT.get(new Integer(n));
    }
    
    public int getIndex(final Color color) {
        final Integer n = this.colorNumberHT.get(color);
        if (n != null) {
            return n;
        }
        return 0;
    }
    
    public int getFigColorIndex(final int n) {
        final Integer n2 = this.figIndexVector.elementAt(n);
        if (n2 != null) {
            return n2;
        }
        return 0;
    }
    
    public Color getColorAt(final int n) {
        return this.get(this.getFigColorIndex(n));
    }
    
    public int numberOfColors() {
        return this.figIndexVector.size();
    }
    
    public Vector getColorNames() {
        final Vector<Object> vector = new Vector<Object>();
        for (int i = 0; i < this.indexHT.size(); ++i) {
            vector.addElement(this.indexHT.get(new Integer(i)));
        }
        return vector;
    }
    
    public Vector getColorIndices() {
        final Vector<Object> vector = new Vector<Object>();
        final Enumeration<Object> keys = this.intHT.keys();
        while (keys.hasMoreElements()) {
            vector.addElement(keys.nextElement());
        }
        return vector;
    }
    
    public int getColorNumber(final Color color) {
        final Integer n = this.colorNumberHT.get(color);
        if (n != null) {
            return n;
        }
        return -1;
    }
    
    public Color get(final String s, final int n) {
        final Color color = this.nameHT.get(s);
        if (color == null) {
            return null;
        }
        return this.xfig_area_fill(color, n);
    }
    
    public Color get(int n, final int n2) {
        if (n < 0) {
            n = 0;
        }
        final Color color = this.intHT.get(new Integer(n));
        if (color == null) {
            return null;
        }
        return this.xfig_area_fill(color, n2);
    }
    
    public Color xfig_area_fill(final Color color, final int n) {
        final Integer n2 = this.colorNumberHT.get(color);
        if (n2 == null) {
            System.err.println("-E- Internal error in ColorCache: no such color");
            return null;
        }
        final int intValue = n2;
        if (intValue == 0) {
            if (n == -1) {
                return null;
            }
            if (n >= 21) {
                return Color.orange;
            }
            final int n3 = 255 * (20 - n) / 20;
            return new Color(n3, n3, n3);
        }
        else if (intValue == 7) {
            if (n == -1) {
                return null;
            }
            if (n >= 21) {
                return Color.orange;
            }
            final int n4 = 255 * n / 20;
            return new Color(n4, n4, n4);
        }
        else {
            if (n == -1) {
                return null;
            }
            if (n >= 41) {
                return Color.orange;
            }
            if (n >= 21) {
                final int red = color.getRed();
                final int green = color.getGreen();
                final int blue = color.getBlue();
                final int n5 = 255 - red;
                final int n6 = 255 - green;
                final int n7 = 255 - blue;
                final int n8 = 40 - n;
                return new Color(255 - n5 * n8 / 20, 255 - n6 * n8 / 20, 255 - n7 * n8 / 20);
            }
            return new Color(color.getRed() * n / 20, color.getGreen() * n / 20, color.getBlue() * n / 20);
        }
    }
    
    public Color getFIG21Gray(final int n) {
        if (n == 0) {
            return null;
        }
        return this.xfig_area_fill(this.nameHT.get("black"), n - 1);
    }
    
    public void putUserColor(final int n, final int n2) {
        if (this.debug) {
            System.out.println("-#- putUserColor: " + n + " " + n2 + " X " + this.intHT.size() + " " + this.figIndexVector.size());
        }
        this.put("user" + n, n, new Color(n2));
    }
    
    public boolean hasUserColors() {
        return this.intHT.size() > 32;
    }
    
    public int registerUserColor(final Color color) {
        final int colorNumber = this.getColorNumber(color);
        if (colorNumber >= 0) {
            return colorNumber;
        }
        final int size = this.figIndexVector.size();
        this.put("user" + size, size, color);
        if (this.debug) {
            System.out.println("-#- registered " + "user" + size + " at " + size + " " + this.intHT.size());
        }
        return size;
    }
    
    public void clearUserColors() {
        final Enumeration<Integer> keys = this.intHT.keys();
        while (keys.hasMoreElements()) {
            final int intValue = keys.nextElement();
            if (intValue >= 32) {
                this.removeUserColor(intValue);
            }
        }
    }
    
    public void removeUserColor(final int n) {
        if (n <= 31) {
            return;
        }
        final Integer n2 = new Integer(n);
        final Color color = this.intHT.get(n2);
        final String string = "user" + n;
        this.intHT.remove(n2);
        this.nameHT.remove(string);
        this.indexHT.remove(string);
        this.colorNumberHT.remove(color);
        this.figIndexVector.remove(n2);
    }
    
    private ColorCache() {
        this.debug = false;
        this.nameHT = new Hashtable();
        this.intHT = new Hashtable();
        this.indexHT = new Hashtable();
        this.colorNumberHT = new Hashtable();
        this.figIndexVector = new Vector();
        this.put("black", 0, Color.black);
        this.put("blue", 1, Color.blue);
        this.put("green", 2, Color.green);
        this.put("cyan", 3, Color.cyan);
        this.put("red", 4, Color.red);
        this.put("magenta", 5, Color.magenta);
        this.put("yellow", 6, Color.yellow);
        this.put("white", 7, Color.white);
        this.put("blue4", 8, new Color(0, 0, 144));
        this.put("blue3", 9, new Color(0, 0, 176));
        this.put("blue2", 10, new Color(0, 0, 208));
        this.put("ltblue", 11, new Color(135, 206, 255));
        this.put("green4", 12, new Color(0, 144, 0));
        this.put("green3", 13, new Color(0, 176, 0));
        this.put("green2", 14, new Color(0, 208, 0));
        this.put("cyan4", 15, new Color(0, 144, 144));
        this.put("cyan3", 16, new Color(0, 176, 176));
        this.put("cyan2", 17, new Color(0, 208, 208));
        this.put("red4", 18, new Color(144, 0, 0));
        this.put("red3", 19, new Color(176, 0, 0));
        this.put("red2", 20, new Color(208, 0, 0));
        this.put("magenta4", 21, new Color(144, 0, 144));
        this.put("magenta3", 22, new Color(176, 0, 176));
        this.put("magenta2", 23, new Color(208, 0, 208));
        this.put("brown4", 24, new Color(128, 48, 0));
        this.put("brown3", 25, new Color(160, 64, 0));
        this.put("brown2", 26, new Color(192, 96, 0));
        this.put("pink4", 27, new Color(255, 128, 128));
        this.put("pink3", 28, new Color(255, 160, 160));
        this.put("pink2", 29, new Color(255, 192, 192));
        this.put("pink", 30, new Color(255, 224, 224));
        this.put("gold", 31, new Color(255, 215, 0));
    }
    
    static {
        ColorCache.BLACK = 0;
        ColorCache.BLUE = 1;
        ColorCache.GREEN = 2;
        ColorCache.CYAN = 3;
        ColorCache.RED = 4;
        ColorCache.MAGENTA = 5;
        ColorCache.YELLOW = 6;
        ColorCache.WHITE = 7;
        ColorCache.BLUE4 = 8;
        ColorCache.BLUE3 = 9;
        ColorCache.BLUE2 = 10;
        ColorCache.LTBLUE = 11;
        ColorCache.GREEN4 = 12;
        ColorCache.GREEN3 = 13;
        ColorCache.GREEN2 = 14;
        ColorCache.CYAN4 = 15;
        ColorCache.CYAN3 = 16;
        ColorCache.CYAN2 = 17;
        ColorCache.RED4 = 18;
        ColorCache.RED3 = 19;
        ColorCache.RED2 = 20;
        ColorCache.MAGENTA4 = 21;
        ColorCache.MAGENTA3 = 22;
        ColorCache.MAGENTA2 = 23;
        ColorCache.BROWN4 = 24;
        ColorCache.BROWN3 = 25;
        ColorCache.BROWN2 = 26;
        ColorCache.PINK4 = 27;
        ColorCache.PINK3 = 28;
        ColorCache.PINK2 = 29;
        ColorCache.PINK = 30;
        ColorCache.GOLD = 31;
    }
}
