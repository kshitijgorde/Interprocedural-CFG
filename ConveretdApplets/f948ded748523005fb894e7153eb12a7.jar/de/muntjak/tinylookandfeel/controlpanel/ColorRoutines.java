// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.controlpanel;

import java.awt.Color;

public class ColorRoutines
{
    private static final int RGB = 1;
    private static final int RBG = 2;
    private static final int GBR = 3;
    private static final int GRB = 4;
    private static final int BRG = 5;
    private static final int BGR = 6;
    private boolean preserveGrey;
    private int chue;
    private int csat;
    private int cbri;
    private int fr;
    private int fg;
    private int fb;
    int hi;
    int lo;
    int md;
    boolean hiIsR;
    boolean hiIsG;
    boolean hiIsB;
    boolean mdIsR;
    boolean mdIsG;
    boolean mdIsB;
    boolean loIsR;
    boolean loIsG;
    boolean loIsB;
    
    public ColorRoutines(final Color color) {
        this.setHSB(color.getRed(), color.getGreen(), color.getBlue());
    }
    
    public ColorRoutines(final int chue, final int csat, final int cbri, final boolean preserveGrey) {
        this.chue = chue;
        this.csat = csat;
        this.cbri = cbri;
        this.preserveGrey = preserveGrey;
        final Color hsbColor = Color.getHSBColor((float)(this.chue / 360.0), 1.0f, 1.0f);
        this.fr = hsbColor.getRed();
        this.fg = hsbColor.getGreen();
        this.fb = hsbColor.getBlue();
        if (this.fr >= this.fg && this.fg >= this.fb) {
            this.hi = this.fr;
            this.md = this.fg;
            this.lo = this.fb;
            this.hiIsR = true;
            this.mdIsG = true;
            this.loIsB = true;
        }
        else if (this.fr >= this.fb && this.fb >= this.fg) {
            this.hi = this.fr;
            this.md = this.fb;
            this.lo = this.fg;
            this.hiIsR = true;
            this.mdIsB = true;
            this.loIsG = true;
        }
        else if (this.fg >= this.fr && this.fr >= this.fb) {
            this.hi = this.fg;
            this.md = this.fr;
            this.lo = this.fb;
            this.hiIsG = true;
            this.mdIsR = true;
            this.loIsB = true;
        }
        else if (this.fg >= this.fb && this.fb >= this.fr) {
            this.hi = this.fg;
            this.md = this.fb;
            this.lo = this.fr;
            this.hiIsG = true;
            this.mdIsB = true;
            this.loIsR = true;
        }
        else if (this.fb >= this.fg && this.fg >= this.fr) {
            this.hi = this.fb;
            this.md = this.fg;
            this.lo = this.fr;
            this.hiIsB = true;
            this.mdIsG = true;
            this.loIsR = true;
        }
        else if (this.fb >= this.fr && this.fr >= this.fg) {
            this.hi = this.fb;
            this.md = this.fr;
            this.lo = this.fg;
            this.hiIsB = true;
            this.mdIsR = true;
            this.loIsG = true;
        }
    }
    
    private void setHSB(final int n, final int n2, final int n3) {
        this.chue = getHue(n, n2, n3);
        this.csat = getSaturation(n, n2, n3);
        this.cbri = getBrightness(n, n2, n3);
    }
    
    public static Color getAverage(final Color color, final Color color2) {
        return new Color((int)Math.round((color.getRed() + color2.getRed()) / 2.0), (int)Math.round((color.getGreen() + color2.getGreen()) / 2.0), (int)Math.round((color.getBlue() + color2.getBlue()) / 2.0));
    }
    
    public static Color getGradient(final Color color, final Color color2, final int n, final int n2) {
        if (n2 == 0) {
            return color;
        }
        if (n2 == n) {
            return color2;
        }
        final double n3 = n2 * 1.1 / n;
        final double n4 = 1.0 - n3;
        return new Color((int)Math.round(color.getRed() * n4 + color2.getRed() * n3), (int)Math.round(color.getGreen() * n4 + color2.getGreen() * n3), (int)Math.round(color.getBlue() * n4 + color2.getBlue() * n3));
    }
    
    public static Color getMaxSaturation(final Color color, final int n) {
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        if (red == green && red == blue) {
            return color;
        }
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 1;
        if (red >= green && red >= blue) {
            n4 = red;
            if (green == blue) {
                n2 = green;
                n3 = blue;
                n5 = 1;
            }
            else if (green > blue) {
                n2 = green;
                n3 = blue;
                n5 = 1;
            }
            else {
                n3 = green;
                n2 = blue;
                n5 = 2;
            }
        }
        else if (green >= red && green >= blue) {
            n4 = green;
            if (red == blue) {
                n2 = red;
                n3 = blue;
                n5 = 4;
            }
            else if (red > blue) {
                n2 = red;
                n3 = blue;
                n5 = 4;
            }
            else {
                n3 = red;
                n2 = blue;
                n5 = 3;
            }
        }
        else if (blue >= red && blue >= green) {
            n4 = blue;
            if (red == green) {
                n2 = red;
                n3 = green;
                n5 = 5;
            }
            else if (red > green) {
                n2 = red;
                n3 = green;
                n5 = 5;
            }
            else {
                n3 = red;
                n2 = green;
                n5 = 6;
            }
        }
        if (n3 == 0) {
            return color;
        }
        final int min = Math.min(255, n4 + n3);
        final int max = Math.max(0, n4 + n3 - 255);
        int n6 = n2;
        int n7 = 0;
        int n8 = 360;
        Color color2 = null;
        switch (n5) {
            case 1: {
                int hue2;
                int hue = hue2 = getHue(min, n6, max);
                while (hue != n && n6 < 256) {
                    hue = getHue(min, ++n6, max);
                    if (n6 == 256) {
                        break;
                    }
                    if (hue == n) {
                        return new Color(min, n6, max);
                    }
                    if ((hue2 < n && hue > n) || (hue2 > n && hue < n)) {
                        return new Color(min, n6, max);
                    }
                    if (Math.abs(hue - n) < n8) {
                        n8 = Math.abs(hue - n);
                        n7 = n6;
                    }
                    hue2 = hue;
                }
                if (hue != n) {
                    int hue4;
                    int hue3 = hue4 = getHue(min, n6, max);
                    n6 = n2;
                    while (hue3 != n && n6 >= 0) {
                        hue3 = getHue(min, --n6, max);
                        if (n6 == -1) {
                            break;
                        }
                        if (hue3 == n) {
                            return new Color(min, n6, max);
                        }
                        if ((hue4 < n && hue3 > n) || (hue4 > n && hue3 < n)) {
                            return new Color(min, n6, max);
                        }
                        if (Math.abs(hue3 - n) < n8) {
                            n8 = Math.abs(hue3 - n);
                            n7 = n6;
                        }
                        hue4 = hue3;
                    }
                }
                if (n6 == 256 | n6 == -1) {
                    n6 = n7;
                }
                color2 = new Color(min, n6, max);
                break;
            }
            case 2: {
                int hue6;
                int hue5 = hue6 = getHue(min, max, n6);
                while (hue5 != n && n6 < 256) {
                    hue5 = getHue(min, max, ++n6);
                    if (n6 == 256) {
                        break;
                    }
                    if (hue5 == n) {
                        return new Color(min, max, n6);
                    }
                    if ((hue6 < n && hue5 > n) || (hue6 > n && hue5 < n)) {
                        return new Color(min, max, n6);
                    }
                    if (Math.abs(hue5 - n) < n8) {
                        n8 = Math.abs(hue5 - n);
                        n7 = n6;
                    }
                    hue6 = hue5;
                }
                if (hue5 != n) {
                    int hue8;
                    int hue7 = hue8 = getHue(min, n6, max);
                    n6 = n2;
                    while (hue7 != n && n6 >= 0) {
                        hue7 = getHue(min, max, --n6);
                        if (n6 == -1) {
                            break;
                        }
                        if (hue7 == n) {
                            return new Color(min, max, n6);
                        }
                        if ((hue8 < n && hue7 > n) || (hue8 > n && hue7 < n)) {
                            return new Color(min, max, n6);
                        }
                        if (Math.abs(hue7 - n) < n8) {
                            n8 = Math.abs(hue7 - n);
                            n7 = n6;
                        }
                        hue8 = hue7;
                    }
                }
                if (n6 == 256 | n6 == -1) {
                    n6 = n7;
                }
                color2 = new Color(min, max, n6);
                break;
            }
            case 3: {
                int hue10;
                int hue9 = hue10 = getHue(max, min, n6);
                while (hue9 != n && n6 < 256) {
                    hue9 = getHue(max, min, ++n6);
                    if (n6 == 256) {
                        break;
                    }
                    if (hue9 == n) {
                        return new Color(max, min, n6);
                    }
                    if ((hue10 < n && hue9 > n) || (hue10 > n && hue9 < n)) {
                        return new Color(max, min, n6);
                    }
                    if (Math.abs(hue9 - n) < n8) {
                        n8 = Math.abs(hue9 - n);
                        n7 = n6;
                    }
                    hue10 = hue9;
                }
                if (hue9 != n) {
                    int hue12;
                    int hue11 = hue12 = getHue(min, n6, max);
                    n6 = n2;
                    while (hue11 != n && n6 >= 0) {
                        hue11 = getHue(max, min, --n6);
                        if (n6 == -1) {
                            break;
                        }
                        if (hue11 == n) {
                            return new Color(max, min, n6);
                        }
                        if ((hue12 < n && hue11 > n) || (hue12 > n && hue11 < n)) {
                            return new Color(max, min, n6);
                        }
                        if (Math.abs(hue11 - n) < n8) {
                            n8 = Math.abs(hue11 - n);
                            n7 = n6;
                        }
                        hue12 = hue11;
                    }
                }
                if (n6 == 256 | n6 == -1) {
                    n6 = n7;
                }
                color2 = new Color(max, min, n6);
                break;
            }
            case 4: {
                int hue14;
                int hue13 = hue14 = getHue(n6, min, max);
                while (hue13 != n && n6 < 256) {
                    hue13 = getHue(++n6, min, max);
                    if (n6 == 256) {
                        break;
                    }
                    if (hue13 == n) {
                        return new Color(n6, min, max);
                    }
                    if ((hue14 < n && hue13 > n) || (hue14 > n && hue13 < n)) {
                        return new Color(n6, min, max);
                    }
                    if (Math.abs(hue13 - n) < n8) {
                        n8 = Math.abs(hue13 - n);
                        n7 = n6;
                    }
                    hue14 = hue13;
                }
                if (hue13 != n) {
                    int hue16;
                    int hue15 = hue16 = getHue(min, n6, max);
                    n6 = n2;
                    while (hue15 != n && n6 >= 0) {
                        hue15 = getHue(--n6, min, max);
                        if (n6 == -1) {
                            break;
                        }
                        if (hue15 == n) {
                            return new Color(n6, min, max);
                        }
                        if ((hue16 < n && hue15 > n) || (hue16 > n && hue15 < n)) {
                            return new Color(n6, min, max);
                        }
                        if (Math.abs(hue15 - n) < n8) {
                            n8 = Math.abs(hue15 - n);
                            n7 = n6;
                        }
                        hue16 = hue15;
                    }
                }
                if (n6 == 256 | n6 == -1) {
                    n6 = n7;
                }
                color2 = new Color(n6, min, max);
                break;
            }
            case 5: {
                int hue18;
                int hue17 = hue18 = getHue(n6, max, min);
                while (hue17 != n && n6 < 256) {
                    hue17 = getHue(++n6, max, min);
                    if (n6 == 256) {
                        break;
                    }
                    if (hue17 == n) {
                        return new Color(n6, max, min);
                    }
                    if ((hue18 < n && hue17 > n) || (hue18 > n && hue17 < n)) {
                        return new Color(n6, max, min);
                    }
                    if (Math.abs(hue17 - n) < n8) {
                        n8 = Math.abs(hue17 - n);
                        n7 = n6;
                    }
                    hue18 = hue17;
                }
                if (hue17 != n) {
                    int hue20;
                    int hue19 = hue20 = getHue(min, n6, max);
                    n6 = n2;
                    while (hue19 != n && n6 >= 0) {
                        hue19 = getHue(--n6, max, min);
                        if (n6 == -1) {
                            break;
                        }
                        if (hue19 == n) {
                            return new Color(n6, max, min);
                        }
                        if ((hue20 < n && hue19 > n) || (hue20 > n && hue19 < n)) {
                            return new Color(n6, max, min);
                        }
                        if (Math.abs(hue19 - n) < n8) {
                            n8 = Math.abs(hue19 - n);
                            n7 = n6;
                        }
                        hue20 = hue19;
                    }
                }
                if (n6 == 256 | n6 == -1) {
                    n6 = n7;
                }
                color2 = new Color(n6, max, min);
                break;
            }
            case 6: {
                int hue22;
                int hue21 = hue22 = getHue(max, n6, min);
                while (hue21 != n && n6 < 256) {
                    hue21 = getHue(max, ++n6, min);
                    if (n6 == 256) {
                        break;
                    }
                    if (hue21 == n) {
                        return new Color(max, n6, min);
                    }
                    if ((hue22 < n && hue21 > n) || (hue22 > n && hue21 < n)) {
                        return new Color(max, n6, min);
                    }
                    if (Math.abs(hue21 - n) < n8) {
                        n8 = Math.abs(hue21 - n);
                        n7 = n6;
                    }
                    hue22 = hue21;
                }
                if (hue21 != n) {
                    int hue24;
                    int hue23 = hue24 = getHue(min, n6, max);
                    n6 = n2;
                    while (hue23 != n && n6 >= 0) {
                        hue23 = getHue(max, --n6, min);
                        if (n6 == -1) {
                            break;
                        }
                        if (hue23 == n) {
                            return new Color(max, n6, min);
                        }
                        if ((hue24 < n && hue23 > n) || (hue24 > n && hue23 < n)) {
                            return new Color(max, n6, min);
                        }
                        if (Math.abs(hue23 - n) < n8) {
                            n8 = Math.abs(hue23 - n);
                            n7 = n6;
                        }
                        hue24 = hue23;
                    }
                }
                if (n6 == 256 | n6 == -1) {
                    n6 = n7;
                }
                color2 = new Color(max, n6, min);
                break;
            }
        }
        return color2;
    }
    
    public static float getGreyValue(final Color color) {
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        int n = 0;
        int n2 = 0;
        if (red >= green && red >= blue) {
            if (red == 0) {
                return 0.0f;
            }
            n2 = red;
            if (green >= blue) {
                n = blue;
            }
            else {
                n = green;
            }
        }
        else if (green >= red && green >= blue) {
            n2 = green;
            if (red >= blue) {
                n = blue;
            }
            else {
                n = red;
            }
        }
        else if (blue >= red && blue >= green) {
            n2 = blue;
            if (red >= green) {
                n = green;
            }
            else {
                n = red;
            }
        }
        return (float)((n2 + n) / 2.0);
    }
    
    public static int getBrightness(final Color color) {
        return getBrightness(color.getRed(), color.getGreen(), color.getBlue());
    }
    
    public static int getBrightness(final int n, final int n2, final int n3) {
        if (n >= n2 && n >= n3) {
            return (int)Math.round(100 * n / 255.0);
        }
        if (n2 >= n && n2 >= n3) {
            return (int)Math.round(100 * n2 / 255.0);
        }
        if (n3 >= n && n3 >= n2) {
            return (int)Math.round(100 * n3 / 255.0);
        }
        return -1;
    }
    
    public static int getSaturation(final Color color) {
        return getSaturation(color.getRed(), color.getGreen(), color.getBlue());
    }
    
    public static int getSaturation(final int n, final int n2, final int n3) {
        int n4 = 0;
        int n5 = 0;
        if (n >= n2 && n >= n3) {
            if (n == 0) {
                return 0;
            }
            n5 = n;
            if (n2 >= n3) {
                n4 = n3;
            }
            else {
                n4 = n2;
            }
        }
        else if (n2 >= n && n2 >= n3) {
            n5 = n2;
            if (n >= n3) {
                n4 = n3;
            }
            else {
                n4 = n;
            }
        }
        else if (n3 >= n && n3 >= n2) {
            n5 = n3;
            if (n >= n2) {
                n4 = n2;
            }
            else {
                n4 = n;
            }
        }
        return 100 - (int)Math.round(100.0 * n4 / n5);
    }
    
    public static int getHue(final Color color) {
        return getHue(color.getRed(), color.getGreen(), color.getBlue());
    }
    
    public static int calculateHue(final Color color) {
        return (int)Math.round(360.0 * Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null)[0]);
    }
    
    public static int getHue(final int n, final int n2, final int n3) {
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 1;
        if (n >= n2 && n >= n3) {
            n6 = n;
            if (n2 == n3) {
                return 0;
            }
            if (n2 > n3) {
                n4 = n2;
                n5 = n3;
                n7 = 1;
            }
            else {
                n5 = n2;
                n4 = n3;
                n7 = 2;
            }
        }
        else if (n2 >= n && n2 >= n3) {
            n6 = n2;
            if (n == n3) {
                return 120;
            }
            if (n > n3) {
                n4 = n;
                n5 = n3;
                n7 = 4;
            }
            else {
                n5 = n;
                n4 = n3;
                n7 = 3;
            }
        }
        else if (n3 >= n && n3 >= n2) {
            n6 = n3;
            if (n == n2) {
                return 240;
            }
            if (n > n2) {
                n4 = n;
                n5 = n2;
                n7 = 5;
            }
            else {
                n5 = n;
                n4 = n2;
                n7 = 6;
            }
        }
        final double n8 = n4 * 255.0 / n6;
        final double n9 = n5 * 255.0 / n6;
        final int n10 = (int)Math.round(60.0 * ((n8 - n9) * 255.0 / (255.0 - n9)) / 255.0);
        switch (n7) {
            case 1: {
                return n10;
            }
            case 2: {
                return 360 - n10;
            }
            case 3: {
                return 120 + n10;
            }
            case 4: {
                return 120 - n10;
            }
            case 5: {
                return 240 + n10;
            }
            case 6: {
                return 240 - n10;
            }
            default: {
                return -1;
            }
        }
    }
    
    public static Color getHSB(int n, final int n2, final int n3) {
        double n4 = 0.0;
        double n5 = 0.0;
        double n6 = 0.0;
        int n7 = 1;
        if (n == 360) {
            n = 0;
        }
        final int n8 = n / 60;
        final int n9 = n % 60;
        switch (n8) {
            case 0: {
                n4 = 255.0;
                n5 = 255 * n9 / 60.0;
                n7 = 1;
                break;
            }
            case 1: {
                n5 = 255.0;
                n4 = 255.0 - 255 * n9 / 60.0;
                n7 = 3;
                break;
            }
            case 2: {
                n5 = 255.0;
                n6 = 255 * n9 / 60.0;
                n7 = 3;
                break;
            }
            case 3: {
                n6 = 255.0;
                n5 = 255.0 - 255 * n9 / 60.0;
                n7 = 5;
                break;
            }
            case 4: {
                n6 = 255.0;
                n4 = 255 * n9 / 60.0;
                n7 = 5;
                break;
            }
            case 5: {
                n4 = 255.0;
                n6 = 255.0 - 255 * n9 / 60.0;
                n7 = 1;
                break;
            }
        }
        double n10 = n4 * n3 / 100.0;
        double n11 = n5 * n3 / 100.0;
        double n12 = n6 * n3 / 100.0;
        final int n13 = 100 - n2;
        switch (n7) {
            case 1: {
                n11 += (n10 - n11) * n13 / 100.0;
                n12 += (n10 - n12) * n13 / 100.0;
                break;
            }
            case 3: {
                n10 += (n11 - n10) * n13 / 100.0;
                n12 += (n11 - n12) * n13 / 100.0;
                break;
            }
            case 5: {
                n10 += (n12 - n10) * n13 / 100.0;
                n11 += (n12 - n11) * n13 / 100.0;
                break;
            }
        }
        return new Color((int)Math.round(n10), (int)Math.round(n11), (int)Math.round(n12));
    }
    
    public int colorize(final int n, final int n2, final int n3, final int n4) {
        if (this.cbri == 100) {
            return colorToInt(255, 255, 255, n4);
        }
        if (this.cbri == -100) {
            return colorToInt(0, 0, 0, n4);
        }
        int n5 = n;
        if (n2 >= n && n2 >= n3) {
            n5 = n2;
        }
        else if (n3 >= n && n3 >= n2) {
            n5 = n3;
        }
        int n6;
        if (n2 <= (n6 = n) && n2 <= n3) {
            n6 = n2;
        }
        else if (n3 <= n && n3 <= n2) {
            n6 = n3;
        }
        int n7 = (n5 + n6) / 2;
        if (this.cbri < 0) {
            n7 += n7 * this.cbri / 100;
        }
        else if (this.cbri > 0) {
            n7 += (255 - n7) * this.cbri / 100;
        }
        if (this.preserveGrey && n == n2 && n == n3) {
            return colorToInt(n7, n7, n7, n4);
        }
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        int n11;
        if (n7 >= 127) {
            n11 = 255 - n7;
        }
        else {
            n11 = n7;
        }
        if (this.hiIsR) {
            n8 = n7 + n11 * this.csat / 100;
        }
        else if (this.hiIsG) {
            n9 = n7 + n11 * this.csat / 100;
        }
        else if (this.hiIsB) {
            n10 = n7 + n11 * this.csat / 100;
        }
        if (this.mdIsR) {
            int n12;
            if (n7 >= 127) {
                n12 = this.fr + (255 - this.fr) * (n7 - 127) / 128 - n7;
            }
            else {
                n12 = this.fr * n7 / 127 - n7;
            }
            n8 = n7 + n12 * this.csat / 100;
        }
        else if (this.mdIsG) {
            int n13;
            if (n7 >= 127) {
                n13 = this.fg + (255 - this.fg) * (n7 - 127) / 128 - n7;
            }
            else {
                n13 = this.fg * n7 / 127 - n7;
            }
            n9 = n7 + n13 * this.csat / 100;
        }
        else if (this.mdIsB) {
            int n14;
            if (n7 >= 127) {
                n14 = this.fb + (255 - this.fb) * (n7 - 127) / 128 - n7;
            }
            else {
                n14 = this.fb * n7 / 127 - n7;
            }
            n10 = n7 + n14 * this.csat / 100;
        }
        int n15 = n7 - (255 - n7);
        if (n15 < 0) {
            n15 = 0;
        }
        final int n16 = n7 - n15;
        if (this.loIsR) {
            n8 = n7 - n16 * this.csat / 100;
        }
        else if (this.loIsG) {
            n9 = n7 - n16 * this.csat / 100;
        }
        else if (this.loIsB) {
            n10 = n7 - n16 * this.csat / 100;
        }
        return colorToInt(n8, n9, n10, n4);
    }
    
    public static Color getInverseColor(final Color color) {
        return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
    }
    
    public static Color getRandomColor() {
        return new Color((int)(Math.random() * 255.0), (int)(Math.random() * 255.0), (int)(Math.random() * 255.0));
    }
    
    public static Color getAlphaColor(final Color color, final int n) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
    }
    
    protected static int colorToInt(final Color color, final int n) {
        return color.getBlue() + color.getGreen() * 256 + color.getRed() * 65536 + n * 16777216;
    }
    
    protected static int colorToInt(final int n, final int n2, final int n3, final int n4) {
        return n3 + n2 * 256 + n * 65536 + n4 * 16777216;
    }
    
    public static Color lighten(final Color color, int n) {
        if (n < 0) {
            return color;
        }
        if (n > 100) {
            n = 100;
        }
        return new Color(color.getRed() + (int)Math.round((255 - color.getRed()) * n / 100.0), color.getGreen() + (int)Math.round((255 - color.getGreen()) * n / 100.0), color.getBlue() + (int)Math.round((255 - color.getBlue()) * n / 100.0), color.getAlpha());
    }
    
    public static Color darken(final Color color, final int n) {
        if (n < 0 || n > 100) {
            return color;
        }
        return new Color((int)Math.round(color.getRed() * (100 - n) / 100.0), (int)Math.round(color.getGreen() * (100 - n) / 100.0), (int)Math.round(color.getBlue() * (100 - n) / 100.0), color.getAlpha());
    }
    
    public static Color lighten(final int n, final int n2) {
        if (n2 < 0 || n2 > 100) {
            return new Color(n, n, n);
        }
        final int n3 = (255 - n) * n2 / 100 + n;
        return new Color(n3, n3, n3);
    }
    
    public static Color darken(final int n, final int n2) {
        if (n2 < 0 || n2 > 100) {
            return new Color(n, n, n);
        }
        final int n3 = n * (100 - n2) / 100;
        return new Color(n3, n3, n3);
    }
}
