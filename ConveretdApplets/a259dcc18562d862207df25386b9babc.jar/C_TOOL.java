import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class C_TOOL
{
    public static Font fontNormal;
    public static Font fontBold;
    public static Font fontSmall;
    public static Font fontMonospaced;
    public static int fontNormalSize;
    public static int fontSmallSize;
    public static final Color gray;
    public static Color borderColor;
    public static Color BackgroundColor;
    public static Color ForegroundColor;
    public static int windowTopNoMenu;
    public static int windowBottom;
    public static int windowLeft;
    public static int windowRight;
    public static int fontHeight;
    public static int fontWidth;
    public static int boxHeight;
    
    public static dataAXIS FindAxisStep(final double n, final double n2, final double n3) {
        final dataAXIS dataAXIS = new dataAXIS();
        final double n4 = (n2 - n) / n3;
        final double pow = Math.pow(10.0, Math.floor(log10(n4)));
        double ceil = Math.ceil(n4 / pow);
        if (ceil < 1.0) {
            ceil = 1.0;
        }
        else if (ceil > 2.1 && ceil < 5.1) {
            ceil = 5.0;
        }
        else if (ceil > 5.1 && ceil < 10.1) {
            ceil = 10.0;
        }
        final double step = ceil * pow;
        dataAXIS.step = step;
        double start = step * Math.floor(n / step);
        if (start < n) {
            start += step;
        }
        dataAXIS.start = start;
        dataAXIS.count = (int)Math.floor((n2 - dataAXIS.start) / dataAXIS.step);
        return dataAXIS;
    }
    
    public static String AxisValueToString(final double n, final double n2) {
        String s = "";
        final int n3 = (int)Math.round(log10(n2)) - 1;
        final double pow = Math.pow(10.0, n3);
        final double n4 = Math.round(n / pow) * pow;
        if (Math.abs(n4) < 1000000.0) {
            if (n2 >= 10) {
                s = new DecimalFormat("#######").format(n4);
            }
            else {
                String concat = "0.";
                for (int i = 0; i > n3; --i) {
                    concat = String.valueOf(String.valueOf(concat)).concat("#");
                }
                for (s = new DecimalFormat(concat).format(n4); s.indexOf(".") > -1 && s.endsWith("0"); s = s.substring(0, s.length() - 1)) {}
            }
        }
        else if (Math.abs(n4) >= 1000000.0) {
            final double log10 = log10(n4);
            final int n5 = (int)log10;
            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(new DecimalFormat("#.######").format(Math.pow(10.0, log10 - n5))))).append("E").append(new DecimalFormat("###").format(n5))));
        }
        return s;
    }
    
    public static String Str$(final double n, final int n2) {
        final double abs = Math.abs(n);
        if (abs < 1.0E-25) {
            return "0";
        }
        if (abs >= 1000000.0 || abs < 1.0E-7) {
            final double log10 = log10(abs);
            final int n3 = (int)Math.floor(log10);
            final double n4 = sign(n) * Math.pow(10.0, log10 - n3);
            String concat = "0.";
            for (int i = 0; i < n2; ++i) {
                concat = String.valueOf(String.valueOf(concat)).concat("#");
            }
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(new DecimalFormat(concat).format(n4)))).append("E").append(new DecimalFormat("###").format(n3))));
        }
        final int n5 = (int)Math.floor(log10(abs)) - (n2 - 1);
        if (n5 >= 0) {
            return new DecimalFormat("#######").format(n);
        }
        String concat2 = "#######0.";
        for (int j = 0; j > n5; --j) {
            concat2 = String.valueOf(String.valueOf(concat2)).concat("#");
        }
        String s;
        for (s = new DecimalFormat(concat2).format(n); s.indexOf(".") > -1 && s.endsWith("0"); s = s.substring(0, s.length() - 1)) {}
        return s;
    }
    
    public static long val(final String s) {
        try {
            return Long.valueOf(s);
        }
        catch (NumberFormatException ex) {
            return 0L;
        }
    }
    
    public static double log10(final double n) {
        return Math.log(n) / Math.log(10.0);
    }
    
    public static double sign(final double n) {
        if (n > 0.0) {
            return 1.0;
        }
        if (n < 0.0) {
            return -1.0;
        }
        return 0.0;
    }
    
    public static String equationFormat(String s) {
        for (int i = 0; i != -1; ++i) {
            int n = 2;
            i = s.indexOf("^2", i);
            if (i == -1) {
                i = s.indexOf("^3", i);
                n = 3;
            }
            if (i != -1) {
                int char1;
                if (i + 2 < s.length()) {
                    char1 = s.charAt(i + 2);
                }
                else {
                    char1 = 32;
                }
                if (char1 < 48 || char1 > 57) {
                    if (n == 2) {
                        s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s.substring(0, i)))).append("²").append(s.substring(i + 2))));
                    }
                    else {
                        s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s.substring(0, i)))).append("³").append(s.substring(i + 2))));
                    }
                }
            }
        }
        return s;
    }
    
    public static int getDefaultFontSize() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int min = Math.min(screenSize.width, screenSize.height);
        if (min < 510) {
            return 10;
        }
        if (min < 810) {
            return 12;
        }
        if (min < 2010) {
            return 14;
        }
        if (min < 4010) {
            return 16;
        }
        return 18;
    }
    
    public static void setFonts(int fontNormalSize) {
        C_TOOL.fontNormalSize = fontNormalSize;
        if (fontNormalSize > 12) {
            C_TOOL.fontSmallSize = fontNormalSize - 2;
        }
        else {
            fontNormalSize = 10;
        }
        C_TOOL.fontNormal = new Font("SansSerif", 0, C_TOOL.fontNormalSize);
        C_TOOL.fontBold = new Font("SansSerif", 1, C_TOOL.fontNormalSize);
        C_TOOL.fontSmall = new Font("SansSerif", 0, C_TOOL.fontSmallSize);
    }
    
    static {
        gray = new Color(205, 205, 205);
        C_TOOL.borderColor = C_TOOL.gray;
        C_TOOL.BackgroundColor = Color.white;
        C_TOOL.ForegroundColor = Color.black;
    }
}
