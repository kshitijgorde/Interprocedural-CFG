// 
// Decompiled by Procyon v0.5.30
// 

package equis.metastock;

import java.util.Date;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.util.StringTokenizer;
import java.net.URL;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.applet.Applet;

public class MS4Java extends Applet implements Runnable
{
    public static f a;
    public static h b;
    public static h c;
    public static g[] d;
    public static b e;
    public static c f;
    public static d g;
    public static Panel h;
    public static l i;
    public static k j;
    public static Applet k;
    private static Thread l;
    private static Frame m;
    public static int n;
    public static Color o;
    public static Color p;
    public static Color q;
    public static Color r;
    public static Color s;
    public static Color t;
    public static Color u;
    public static Color v;
    public static Color w;
    public static Color x;
    public static Color y;
    public static Color z;
    public static Color aa;
    public static Color ab;
    private static boolean ac;
    public static boolean ad;
    public static boolean ae;
    public static int af;
    private static URL ag;
    private static URL ah;
    private static URL ai;
    private static URL aj;
    private static URL ak;
    private static URL al;
    private static URL am;
    private static String an;
    private static String ao;
    private static int ap;
    private static String aq;
    public static boolean ar;
    public static String as;
    public static char at;
    public static String au;
    public static String av;
    public static int aw;
    public static String ax;
    public static String ay;
    private static boolean az;
    public static boolean a0;
    private static boolean a1;
    private static boolean a2;
    private static boolean a3;
    private static boolean a4;
    public static boolean a5;
    private static String a6;
    private static String a7;
    private static String a8;
    private static boolean a9;
    public static boolean ba;
    public static boolean bb;
    public static int bc;
    public static int bd;
    public static int be;
    public static String[] bf;
    private MS4Java bg;
    
    private static final String a(final int n) {
        final StringBuffer sb = new StringBuffer(10);
        final int n2 = n % 100;
        final int n3 = n / 100 % 100;
        sb.append(n / 10000);
        sb.append('.');
        sb.append(n3);
        sb.append('.');
        sb.append(n2);
        return sb.toString();
    }
    
    private static final int c(final String s) {
        int n = -1;
        if (s != null && s.length() > 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ".");
            n = 0;
            try {
                while (stringTokenizer.hasMoreTokens()) {
                    n = n * 100 + Integer.parseInt(stringTokenizer.nextToken().toString());
                }
            }
            catch (NumberFormatException ex) {
                n = -1;
                a("Invalid version string '" + s + "'");
            }
        }
        return n;
    }
    
    public String getAppletInfo() {
        return "Copyright (C) 1997-2000 Equis International, Inc.\nAll Rights Reserved.";
    }
    
    public static void a() {
        final StringBuffer sb = new StringBuffer("Copyright (C) 1997-2000 Equis International, Inc.\nAll Rights Reserved.");
        sb.append("\nVersion ");
        sb.append(a(20506));
        if (MS4Java.bb) {
            sb.append(" \n(" + MS4Java.k.getCodeBase() + ") - WIP");
        }
        MS4Java.e.a(sb.toString());
    }
    
    public static URL b() {
        return MS4Java.ag;
    }
    
    public static URL c() {
        return MS4Java.ah;
    }
    
    public static String d() {
        return MS4Java.ao;
    }
    
    public static int e() {
        return MS4Java.ap;
    }
    
    public static String f() {
        return MS4Java.aq;
    }
    
    public static URL g() {
        return MS4Java.ak;
    }
    
    public static URL h() {
        if (MS4Java.bc >= 20000) {
            return MS4Java.al;
        }
        return null;
    }
    
    public static URL i() {
        return MS4Java.aj;
    }
    
    public static URL j() {
        return MS4Java.am;
    }
    
    public static String k() {
        return String.valueOf(MS4Java.k.getCodeBase().toString()) + "equis/metastock/" + "ads.txt";
    }
    
    public static String l() {
        return "Copyright (C) 1997-2000 Equis International, Inc.\nAll Rights Reserved.";
    }
    
    public static boolean m() {
        return MS4Java.ac;
    }
    
    public static String n() {
        return MS4Java.an;
    }
    
    public static boolean o() {
        return MS4Java.az;
    }
    
    public static boolean p() {
        return MS4Java.a1;
    }
    
    public static boolean q() {
        return MS4Java.a2;
    }
    
    public static boolean r() {
        return MS4Java.a3;
    }
    
    public static boolean s() {
        return MS4Java.a4;
    }
    
    public static int t() {
        return MS4Java.bd;
    }
    
    private char d(String upperCase) {
        int n = 0;
        upperCase = upperCase.toUpperCase();
        for (int i = 0; i < upperCase.length(); ++i) {
            final int n2 = n * 16;
            char c;
            if (upperCase.charAt(i) >= '0' && upperCase.charAt(i) <= '9') {
                c = (char)(upperCase.charAt(i) - '0');
            }
            else if (upperCase.charAt(i) >= 'A' && upperCase.charAt(i) <= 'F') {
                c = (char)('\n' + (upperCase.charAt(i) - 'A'));
            }
            else {
                c = '\0';
            }
            n = n2 + c;
        }
        return (char)n;
    }
    
    private String e(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        URL url = null;
        final String string = String.valueOf(lowerCase) + ".lang";
        for (int i = 0; i < 69; ++i) {
            MS4Java.bf[i] = "Error: Term not initialized";
        }
        InputStream openStream;
        try {
            url = new URL(String.valueOf(this.getCodeBase().toString()) + "equis/metastock/" + string);
            a("Language URL: '" + url.toString() + "'");
            openStream = url.openStream();
            if (lowerCase.toUpperCase().equals("JAPANESE")) {
                MS4Java.ao = "\u30e1\u30bf\u30b9\u30c8\u30c3\u30af\u30fb\u30aa\u30f3\u30e9\u30a4\u30f3";
                a("Changed applet name to Japanese.");
            }
        }
        catch (Exception ex) {
            if (MS4Java.ad) {
                ex.printStackTrace();
            }
            return "Language Error: opening '" + url.toString() + "'.";
        }
        final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(openStream, 4000));
        int n = 0;
        try {
            while (true) {
                String s = dataInputStream.readLine();
                ++n;
                a("Line #" + n + ": " + s);
                if (s == null) {
                    break;
                }
                final String s2 = "\\u";
                if (s.indexOf(s2) >= 0) {
                    String substring = s;
                    String string2 = "";
                    while (substring.indexOf(s2) >= 0) {
                        final int index = substring.indexOf(s2);
                        final String string3 = String.valueOf(string2) + substring.substring(0, index);
                        final String substring2 = substring.substring(index + s2.length());
                        if (substring2.length() < 4) {
                            return "Language Error: less than 4 chars after \\u on line #" + substring2;
                        }
                        string2 = String.valueOf(string3) + this.d(substring2.substring(0, 4));
                        substring = substring2.substring(4);
                    }
                    s = String.valueOf(string2) + "\"";
                }
                if (s.indexOf(44) < 1) {
                    return "Language Error: Missing comma on line #" + n;
                }
                if (Integer.valueOf(s.substring(0, s.indexOf(44))) != n) {
                    return "Language Error: Invalid line number on line " + n;
                }
                if (n - 1 >= 69) {
                    break;
                }
                MS4Java.bf[n - 1] = s.substring(s.indexOf(34) + 1, s.lastIndexOf(34));
            }
            if (n - 1 != 69) {
                a("Language Warning: Invalid number of terms in file (missing " + (69 - (n - 3)) + " terms)");
            }
        }
        catch (Exception ex2) {
            if (MS4Java.ad) {
                ex2.printStackTrace();
            }
            return "Language Error: reading '" + string + "' file.";
        }
        try {
            openStream.close();
        }
        catch (Exception ex3) {
            if (MS4Java.ad) {
                ex3.printStackTrace();
            }
            return "Language Error: closing '" + string + "' file.";
        }
        return "";
    }
    
    public static Frame a(final Component component) {
        if (MS4Java.m != null) {
            return MS4Java.m;
        }
        Component parent = component;
        if (parent instanceof Frame) {
            return (Frame)parent;
        }
        while ((parent = parent.getParent()) != null) {
            if (parent instanceof Frame) {
                return (Frame)parent;
            }
        }
        return null;
    }
    
    public Color a(final String s, final Color color) {
        Color color2 = color;
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            final String trim = parameter.toUpperCase().trim();
            if (trim.equals("BLACK")) {
                color2 = Color.black;
            }
            else if (trim.equals("BLUE")) {
                color2 = Color.blue;
            }
            else if (trim.equals("CYAN")) {
                color2 = Color.cyan;
            }
            else if (trim.equals("DARKGRAY")) {
                color2 = Color.darkGray;
            }
            else if (trim.equals("GRAY")) {
                color2 = Color.gray;
            }
            else if (trim.equals("GREEN")) {
                color2 = Color.green;
            }
            else if (trim.equals("LIGHTGREEN")) {
                color2 = new Color(128, 255, 128);
            }
            else if (trim.equals("LIGHTGRAY")) {
                color2 = Color.lightGray;
            }
            else if (trim.equals("MAGENTA")) {
                color2 = Color.magenta;
            }
            else if (trim.equals("ORANGE")) {
                color2 = Color.orange;
            }
            else if (trim.equals("PINK")) {
                color2 = Color.pink;
            }
            else if (trim.equals("RED")) {
                color2 = Color.red;
            }
            else if (trim.equals("WHITE")) {
                color2 = Color.white;
            }
            else if (trim.equals("YELLOW")) {
                color2 = Color.yellow;
            }
            else if (MS4Java.bc >= 20000) {
                final StringTokenizer stringTokenizer = new StringTokenizer(trim, ",");
                String nextToken = "0";
                String nextToken2 = "0";
                String nextToken3 = "0";
                if (stringTokenizer.hasMoreTokens()) {
                    nextToken = stringTokenizer.nextToken();
                }
                if (stringTokenizer.hasMoreTokens()) {
                    nextToken2 = stringTokenizer.nextToken();
                }
                if (stringTokenizer.hasMoreTokens()) {
                    nextToken3 = stringTokenizer.nextToken();
                }
                color2 = new Color(Integer.parseInt(nextToken), Integer.parseInt(nextToken2), Integer.parseInt(nextToken3));
            }
        }
        return color2;
    }
    
    public void init() {
        this.bg = new MS4Java();
        MS4Java.k = this;
        System.out.println(MS4Java.ao);
        System.out.println("Copyright (C) 1997-2000 Equis International, Inc.\nAll Rights Reserved.");
        System.out.println("Version " + a(20506));
        System.out.println("27 FEB 2002 15:30 MST");
        final String s = "";
        final String parameter = this.getParameter("AdOverrideURL");
        final String parameter2 = this.getParameter("Ad");
        final String parameter3 = this.getParameter("AppletName");
        final String parameter4 = this.getParameter("AppletNamePosition");
        final String parameter5 = this.getParameter("AppletNameFontSize");
        final String parameter6 = this.getParameter("AutoPriceStyleChanges");
        final String parameter7 = this.getParameter("DateFormat");
        final String parameter8 = this.getParameter("Debug");
        final String parameter9 = this.getParameter("ForceToUpper");
        final String parameter10 = this.getParameter("HelpURL");
        final String parameter11 = this.getParameter("NewsURL");
        final String parameter12 = this.getParameter("DataURL");
        final String parameter13 = this.getParameter("IntradayDataURL");
        final String parameter14 = this.getParameter("AdsURL");
        final String parameter15 = this.getParameter("SearchURL");
        final String parameter16 = this.getParameter("HumanLanguage");
        final String parameter17 = this.getParameter("RelativeStrengthSymbol");
        final String parameter18 = this.getParameter("Remove");
        final String parameter19 = this.getParameter("SearchDialogHeight");
        final String parameter20 = this.getParameter("SetDefaultIndicator");
        final String parameter21 = this.getParameter("SetDefaultIndicatorValue1");
        final String parameter22 = this.getParameter("SetDefaultIndicatorValue2");
        final String parameter23 = this.getParameter("SetDefaultPeriodicity");
        final String parameter24 = this.getParameter("ShowData");
        final String parameter25 = this.getParameter("ShowDataErrorMessages");
        final String parameter26 = this.getParameter("ShowHelpButton");
        final String parameter27 = this.getParameter("ShowIntradayVolume");
        final String parameter28 = this.getParameter("ShowNewsButton");
        final String parameter29 = this.getParameter("ShowPeriodicityControl");
        final String parameter30 = this.getParameter("ShowPriceStyleControl");
        final String parameter31 = this.getParameter("ShowSymbolControl");
        final String parameter32 = this.getParameter("ShowDocumentHelpFrame");
        final String parameter33 = this.getParameter("ShowDocumentNewsFrame");
        final String parameter34 = this.getParameter("Symbol");
        final String parameter35 = this.getParameter("WorkInProgress");
        final String parameter36 = this.getParameter("MS4JVersion");
        final String parameter37 = this.getParameter("YAxisWidth");
        final String parameter38 = this.getParameter("HeadingWidth");
        final String parameter39 = this.getParameter("SetDefaultPriceStyle");
        final String parameter40 = this.getParameter("AllowComposites");
        final String parameter41 = this.getParameter("DecimalPlaces");
        final String parameter42 = this.getParameter("DataDisplaySpacing");
        if (parameter8 != null) {
            MS4Java.ad = parameter8.toUpperCase().trim().equals("TRUE");
        }
        if (parameter35 != null) {
            MS4Java.bb = (parameter35.toUpperCase().trim().equals("TRUE") && MS4Java.ad);
        }
        if (parameter36 != null) {
            MS4Java.bc = c(parameter36.trim());
            if (MS4Java.bc == -1) {
                MS4Java.bc = 0;
            }
            if (MS4Java.bc == 0) {
                MS4Java.bc = 10900;
            }
            else if (MS4Java.bc == 2) {
                MS4Java.bc = 20000;
            }
            else if (MS4Java.bc == 205) {
                MS4Java.bc = 20000;
            }
        }
        else {
            MS4Java.bc = 10900;
        }
        a("Feature level at version " + a(MS4Java.bc));
        if (parameter2 != null) {
            MS4Java.ac = !parameter2.toUpperCase().trim().equals("FALSE");
        }
        else {
            MS4Java.ac = true;
        }
        MS4Java.bd = 50;
        if (parameter37 != null) {
            try {
                final int intValue = Integer.valueOf(parameter37.trim());
                if (intValue >= 1 && intValue <= 100) {
                    MS4Java.bd = intValue;
                }
            }
            catch (Exception ex) {
                a("Invalid YAxisWidth ('" + parameter37 + "')");
                System.exit(1);
            }
        }
        if (parameter3 != null && !parameter3.equals(MS4Java.ao)) {
            MS4Java.ao = parameter3;
        }
        if (parameter5 != null) {
            try {
                MS4Java.ap = Integer.parseInt(parameter5.trim());
            }
            catch (Exception ex2) {
                a("Invalid AppletNameFontSize '" + parameter5 + "'");
                MS4Java.ap = 18;
            }
        }
        if (parameter4 != null) {
            MS4Java.aq = parameter4;
        }
        if (!MS4Java.aq.toUpperCase().equals("WEST")) {
            MS4Java.aq = "north";
        }
        if (parameter6 != null) {
            MS4Java.ar = !parameter6.toUpperCase().trim().equals("FALSE");
        }
        String trim;
        if (parameter16 != null) {
            trim = parameter16.toUpperCase().trim();
        }
        else {
            trim = "English";
        }
        final String string = String.valueOf(s) + this.e(trim);
        if (parameter9 != null) {
            MS4Java.ae = !parameter9.toUpperCase().trim().equals("FALSE");
        }
        MS4Java.an = MS4Java.k.getCodeBase().getHost();
        if (MS4Java.an.length() == 0) {
            MS4Java.an = "www.moneynet.com";
        }
        try {
            if (parameter12 != null) {
                MS4Java.ak = new URL(parameter12.trim());
            }
            else {
                MS4Java.ak = new URL("http", MS4Java.an, "/data/EQUIS/rawphistory/phistory.asp");
            }
        }
        catch (Exception ex3) {
            String s2;
            if (parameter12 != null) {
                s2 = parameter12.trim();
            }
            else {
                s2 = "http://, " + MS4Java.an + ", " + "/data/EQUIS/rawphistory/phistory.aspx";
            }
            a("Exception creating EODDataURL (" + s2 + ")");
            System.exit(1);
        }
        try {
            if (parameter13 != null) {
                final String trim2 = parameter13.trim();
                if (trim2.equals("")) {
                    MS4Java.al = null;
                }
                else {
                    MS4Java.al = new URL(trim2);
                }
            }
            else {
                MS4Java.al = new URL("http", MS4Java.an, "/data/EQUIS/rawintraday/intraday.aspx");
            }
        }
        catch (Exception ex4) {
            a("Exception creating pIntradayDataURL");
            System.exit(1);
        }
        try {
            if (parameter15 != null) {
                final String trim3 = parameter15.trim();
                if (trim3.equals("")) {
                    MS4Java.ai = null;
                }
                else {
                    MS4Java.ai = new URL(trim3);
                }
            }
            else {
                MS4Java.ai = new URL("http", MS4Java.an, "/data/EQUIS/rawlookup/lookup.aspx");
            }
        }
        catch (Exception ex5) {
            a("Exception creating pIntradayDataURL");
            System.exit(1);
        }
        try {
            if (parameter14 != null) {
                MS4Java.am = new URL(parameter14.trim());
            }
            else {
                MS4Java.am = new URL(String.valueOf(this.getCodeBase().toString()) + "equis/metastock/" + "ads");
            }
        }
        catch (Exception ex6) {
            a("Exception creating AdsURL");
            System.exit(1);
        }
        try {
            if (parameter10 != null) {
                MS4Java.ag = new URL(parameter10.trim());
            }
            else {
                MS4Java.ag = new URL(this.getDocumentBase(), "manual/manual.html");
            }
        }
        catch (Exception ex7) {
            a("Exception creating Help URL");
            System.exit(1);
        }
        try {
            if (parameter11 != null) {
                MS4Java.ah = new URL(parameter11.trim());
            }
            else {
                MS4Java.ah = new URL("http", MS4Java.an, "/content/EQUIS/News/NewsHeadlines.aspx?SYMBOL=");
            }
        }
        catch (Exception ex8) {
            a("Exception creating News URL");
            System.exit(1);
        }
        try {
            if (parameter != null) {
                MS4Java.aj = new URL(parameter.trim());
            }
        }
        catch (Exception ex9) {
            a("Exception creating AdOverrideURL");
            System.exit(1);
        }
        if (parameter38 != null) {
            try {
                MS4Java.af = Integer.valueOf(parameter38.trim());
            }
            catch (Exception ex10) {
                a("invalid heading height");
            }
        }
        if (parameter19 != null && MS4Java.bc >= 20000) {
            try {
                MS4Java.aw = Integer.parseInt(parameter19.trim());
            }
            catch (Exception ex11) {
                a("Exception parsing SearchDialogHeight parameter");
                System.exit(1);
            }
        }
        if (parameter20 != null) {
            MS4Java.a6 = parameter20;
        }
        else {
            MS4Java.a6 = MS4Java.bf[11];
        }
        if (parameter21 != null) {
            MS4Java.a7 = parameter21;
        }
        else {
            MS4Java.a7 = "15";
        }
        if (parameter22 != null) {
            MS4Java.a8 = parameter22;
        }
        else {
            MS4Java.a8 = "";
        }
        if (parameter24 != null) {
            MS4Java.a5 = parameter24.toUpperCase().trim().equals("TRUE");
        }
        if (parameter25 != null) {
            MS4Java.ba = parameter25.toUpperCase().trim().equals("TRUE");
        }
        if (parameter26 != null) {
            MS4Java.az = !parameter26.toUpperCase().trim().equals("FALSE");
        }
        if (parameter27 != null) {
            MS4Java.a0 = !parameter27.toUpperCase().trim().equals("FALSE");
        }
        if (parameter28 != null) {
            MS4Java.a1 = !parameter28.toUpperCase().trim().equals("FALSE");
        }
        if (parameter29 != null) {
            MS4Java.a2 = !parameter29.toUpperCase().trim().equals("FALSE");
        }
        if (parameter30 != null) {
            MS4Java.a3 = !parameter30.toUpperCase().trim().equals("FALSE");
        }
        if (parameter31 != null) {
            MS4Java.a4 = !parameter31.toUpperCase().trim().equals("FALSE");
        }
        if (parameter32 != null) {
            MS4Java.ax = parameter32;
        }
        if (parameter33 != null) {
            MS4Java.ay = parameter33;
        }
        MS4Java.o = this.a("ColorChartBackground", MS4Java.o);
        MS4Java.p = this.a("ColorChartText", MS4Java.p);
        MS4Java.q = this.a("ColorChartFrame", MS4Java.q);
        MS4Java.r = this.a("ColorGrid", MS4Java.r);
        MS4Java.s = this.a("ColorSearchDialog", MS4Java.s);
        MS4Java.t = this.a("ColorScaleBackground", MS4Java.t);
        MS4Java.u = this.a("ColorScaleText", MS4Java.u);
        MS4Java.v = this.a("ColorNameBackground", MS4Java.v);
        MS4Java.w = this.a("ColorPrices", MS4Java.w);
        MS4Java.x = this.a("ColorVolume", MS4Java.x);
        MS4Java.y = this.a("ColorIndicator1", MS4Java.y);
        MS4Java.z = this.a("ColorIndicator2", MS4Java.z);
        MS4Java.aa = this.a("ColorTrendlines", MS4Java.aa);
        MS4Java.ab = this.a("ColorAdPanel", MS4Java.ab);
        MS4Java.at = '/';
        MS4Java.as = "MMDDYY";
        if (parameter7 != null) {
            final String upperCase = MS4Java.k.getParameter("DateFormat").toUpperCase();
            boolean b = false;
            if (upperCase.length() == 8 && upperCase.charAt(2) == upperCase.charAt(5)) {
                MS4Java.at = upperCase.charAt(2);
                final int index = upperCase.indexOf("DD");
                final int index2 = upperCase.indexOf("MM");
                final int index3 = upperCase.indexOf("YY");
                if (index >= 0 && index2 >= 0 && index3 >= 0) {
                    if (index < index2 && index2 < index3) {
                        b = true;
                        MS4Java.as = "DDMMYY";
                    }
                    else if (index3 < index2 && index2 < index) {
                        b = true;
                        MS4Java.as = "YYMMDD";
                    }
                    else if (index2 < index && index < index3) {
                        b = true;
                        MS4Java.as = "MMDDYY";
                    }
                }
            }
            if (!b) {
                a("Error: Invalid DateFormat '" + upperCase + "'");
                System.exit(1);
            }
        }
        if (parameter17 != null) {
            MS4Java.au = MS4Java.k.getParameter("RelativeStrengthSymbol").toUpperCase();
        }
        if (parameter18 != null) {
            MS4Java.av = MS4Java.k.getParameter("Remove").toUpperCase();
        }
        if (parameter40 != null) {
            MS4Java.a9 = !parameter40.toUpperCase().trim().equals("FALSE");
        }
        if (parameter41 != null) {
            try {
                MS4Java.be = Integer.valueOf(parameter41.trim());
                if (MS4Java.be < 0 || MS4Java.be > 4) {
                    MS4Java.be = 3;
                }
            }
            catch (Exception ex12) {}
        }
        if (MS4Java.ad) {
            a("Parameters:");
            if (MS4Java.aj != null) {
                a("  AdOverrideURL          = " + MS4Java.aj.toString());
            }
            a("  AdsURL                 = " + MS4Java.am.toString());
            if (parameter3 != null) {
                a("  Applet Name            = " + MS4Java.ao);
            }
            a("  Applet Name Font Size  = " + MS4Java.ap);
            a("  Applet Name Position   = " + MS4Java.aq);
            a("  AutoPriceStyleChanges  = " + MS4Java.ar);
            a("  Copyright notice       = Copyright (C) 1997-2000 Equis International, Inc.\nAll Rights Reserved.");
            a("  DateFormat             = " + MS4Java.as.substring(0, 2) + MS4Java.at + MS4Java.as.substring(2, 4) + MS4Java.at + MS4Java.as.substring(4, 6));
            a("  EODDataURL             = " + MS4Java.ak.toString());
            a("  ForceUpperParam        = " + parameter9);
            a("  HelpURL                = " + MS4Java.ag.toString());
            a("  HumanLanguage          = " + trim);
            if (MS4Java.al != null) {
                a("  IntradayDataURL        = " + MS4Java.al.toString());
            }
            if (MS4Java.bc >= 20000) {
                a("  MS4JVersion            = " + a(MS4Java.bc));
            }
            a("  NewsURL                = " + MS4Java.ah.toString());
            a("  RelativeStrengthSymbol = " + MS4Java.au);
            a("  Removed Indicators     = " + MS4Java.av);
            if (MS4Java.bc >= 20000) {
                a("  SearchDialogHeight     = " + MS4Java.aw);
            }
            a("  SearchURL              = " + MS4Java.ai.toString());
            a("  SetDefaultIndicator    = " + MS4Java.a6 + " (value1=" + MS4Java.a7 + ", value2=" + MS4Java.a7 + ")");
            a("  ShowAdPanel            = " + MS4Java.ac);
            a("  ShowData               = " + MS4Java.a5);
            a("  ShowDataErrorMessages  = " + MS4Java.ba);
            a("  ShowDebugMessages      = " + MS4Java.ad);
            a("  ShowDocumentHelpFrame  = " + MS4Java.ax);
            a("  ShowDocumentNewsFrame  = " + MS4Java.ay);
            a("  ShowHelpButton         = " + MS4Java.az);
            a("  ShowIntradayVolume     = " + MS4Java.a0);
            a("  ShowNewsButton         = " + MS4Java.a1);
            a("  ShowPeriodicityControl = " + MS4Java.a2);
            a("  ShowPriceStyleControl  = " + MS4Java.a3);
            a("  ShowSymbolControl      = " + MS4Java.a4);
            if (MS4Java.bb) {
                a("  ShowWorkInProgress     = " + MS4Java.bb);
            }
            a("  YAxisWidth             = " + MS4Java.bd);
        }
        final boolean b2 = false;
        if (b2) {
            a("Debug #00");
        }
        this.setBackground(MS4Java.t);
        MS4Java.e = new b();
        MS4Java.f = new c(MS4Java.e, this.bg);
        (MS4Java.h = new Panel()).setLayout(new BorderLayout());
        MS4Java.g = new d(MS4Java.h, this.bg);
        if (parameter42 != null) {
            MS4Java.e.a(Integer.parseInt(parameter42));
        }
        if (b2) {
            a("Debug #10");
        }
        MS4Java.a = new f(this.bg);
        MS4Java.b = new h(550);
        MS4Java.c = new h(550);
        for (int i = 0; i < 5; ++i) {
            MS4Java.d[i] = new g(550);
        }
        if (b2) {
            a("Debug #20");
        }
        this.setLayout(new BorderLayout());
        this.add("North", MS4Java.f);
        this.add("Center", MS4Java.e);
        this.add("South", MS4Java.h);
        MS4Java.h.add("North", MS4Java.g);
        int n = 1;
        if (parameter23 != null) {
            final String upperCase2 = parameter23.toUpperCase();
            for (int j = 25; j <= 28; ++j) {
                if (upperCase2.equals(MS4Java.bf[j].toUpperCase())) {
                    n = j - 25;
                }
            }
        }
        if (n == 0 && MS4Java.bc < 20000) {
            n = 1;
        }
        this.SetPeriodicity(n);
        if (parameter39 != null) {
            final String trim4 = parameter39.trim();
            if (trim4.equals(MS4Java.bf[0])) {
                n = 0;
            }
            else if (trim4.equals(MS4Java.bf[1])) {
                n = 1;
            }
            else if (trim4.equals(MS4Java.bf[2])) {
                n = 2;
            }
            else if (trim4.equals(MS4Java.bf[3])) {
                n = 3;
            }
            this.SetPriceStyle(n);
        }
        if (b2) {
            a("Debug #30");
        }
        if (MS4Java.aw > 0) {
            MS4Java.j = new k((MS4Java)MS4Java.k, this.bounds().width, MS4Java.aw, MS4Java.ad, MS4Java.s, MS4Java.ai.toString());
            MS4Java.h.add("Center", MS4Java.j);
        }
        if (b2) {
            a("Debug #40");
        }
        if (m()) {
            MS4Java.i = new l();
            MS4Java.h.add("South", MS4Java.i);
        }
        if (b2) {
            a("Debug #50");
        }
        MS4Java.f.a.setText(parameter34);
        if (string.length() > 0) {
            a("Critical Error: " + string);
            System.exit(1);
        }
        if (b2) {
            a("Debug #60");
        }
    }
    
    public void LoadDataInternal() {
        String getSymbol = this.GetSymbol();
        a("Loading " + getSymbol);
        if (getSymbol.toLowerCase().equals("showworkinprogress")) {
            MS4Java.bb = true;
            getSymbol = ".DJI";
            MS4Java.f.a.setText(getSymbol);
        }
        MS4Java.f.a.setText(getSymbol);
        MS4Java.f.a.selectAll();
        MS4Java.f.a.requestFocus();
        MS4Java.f.a.selectAll();
        MS4Java.e.a();
        MS4Java.n = -1;
        a("Tokenizing " + getSymbol);
        try {
            final j j = new j(getSymbol);
            final int a = j.a();
            final String s = "+-*/";
            if (MS4Java.bc >= 20000 && a > 1) {
                String s2 = null;
                while (j.c()) {
                    if (++MS4Java.n >= 5) {
                        --MS4Java.n;
                        break;
                    }
                    String s3 = j.b();
                    a("Symbol = " + s3);
                    if (s.indexOf(s3) >= 0) {
                        if (!MS4Java.a9) {
                            --MS4Java.n;
                            continue;
                        }
                        s2 = s3;
                        while (j.c()) {
                            s3 = j.b();
                            if (s.indexOf(s3) == -1) {
                                break;
                            }
                            s2 = s3;
                        }
                        if (!j.c() && s3.equals(s2)) {
                            --MS4Java.n;
                            break;
                        }
                    }
                Label_0312:
                    while (true) {
                        try {
                            if (!a(s3, MS4Java.n)) {
                                b(s3, MS4Java.n);
                            }
                        }
                        catch (Exception ex) {
                            if (s2 != null) {
                                s2 = null;
                            }
                            while (j.c()) {
                                s3 = j.b();
                                if (s.indexOf(s3) == -1) {
                                    continue Label_0312;
                                }
                            }
                            --MS4Java.n;
                        }
                        break;
                    }
                    if (s2 == null) {
                        continue;
                    }
                    a(s2.charAt(0), MS4Java.n);
                    --MS4Java.n;
                    s2 = null;
                }
                if (MS4Java.n > 0) {
                    for (int i = 0; i <= MS4Java.n; ++i) {
                        MS4Java.d[i].g();
                    }
                }
                else if (MS4Java.n < 0) {
                    MS4Java.n = 0;
                }
                MS4Java.f.b(getSymbol);
            }
            else if (a == 1 || MS4Java.bc < 20000) {
                MS4Java.n = 0;
                final String b = j.b();
                if (s.indexOf(b) == -1) {
                    a("Getting data for " + b);
                    MS4Java.d[MS4Java.n].a(b, MS4Java.g.b());
                    a("Data loaded for " + b);
                }
                else {
                    MS4Java.e.a(String.valueOf(MS4Java.bf[46]) + ": \"" + b + "\"");
                }
            }
            MS4Java.g.a();
        }
        catch (Exception ex2) {
            a("Exception while tokenizing");
        }
    }
    
    public void run() {
        this.LoadData(MS4Java.f.a.getText());
        MS4Java.f.a.requestFocus();
        MS4Java.f.a.selectAll();
    }
    
    public void start() {
        if (MS4Java.l == null) {
            (MS4Java.l = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (MS4Java.l != null) {
            MS4Java.l.stop();
            MS4Java.l = null;
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                System.exit(0);
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public void repaint() {
        MS4Java.e.repaint();
    }
    
    public void paint(final Graphics graphics) {
        MS4Java.e.repaint();
    }
    
    private static void f(String string) {
        string = "MS4Java --> " + string;
        System.out.println(string);
        MS4Java.k.showStatus(string);
    }
    
    public static void a(final String s) {
        if (MS4Java.ad) {
            f(s);
        }
    }
    
    public static void b(final String s) {
        f(s);
    }
    
    public static int u() {
        final String a = MS4Java.f.a();
        int n = -1;
        if (a.equals(MS4Java.bf[0])) {
            n = 0;
        }
        else if (a.equals(MS4Java.bf[1])) {
            n = 1;
        }
        else if (a.equals(MS4Java.bf[2])) {
            n = 2;
        }
        else if (a.equals(MS4Java.bf[3])) {
            n = 3;
        }
        else {
            a("Warning: Invalid price style in GetPriceStyle()");
        }
        return n;
    }
    
    public void SetPriceStyle(final int n) {
        switch (n) {
            case 0: {
                MS4Java.f.a(MS4Java.bf[0]);
                a("Public Method Call: Setting price style to PRICESTYLE_BARS");
                break;
            }
            case 1: {
                MS4Java.f.a(MS4Java.bf[1]);
                a("Public Method Call: Setting price style to PRICESTYLE_BARSWOPEN");
                break;
            }
            case 2: {
                MS4Java.f.a(MS4Java.bf[2]);
                a("Public Method Call: Setting price style to PRICESTYLE_CANDLESTICKS");
                break;
            }
            case 3: {
                MS4Java.f.a(MS4Java.bf[3]);
                a("Public Method Call: Setting price style to PRICESTYLE_LINE");
                break;
            }
            default: {
                a("Warning: Invalid price style passed to SetPriceStyle()");
                break;
            }
        }
        MS4Java.e.repaint();
    }
    
    public void SetPeriodicity(final int n) {
        switch (n) {
            case 0: {
                MS4Java.g.a(MS4Java.bf[25]);
                a("Public Method Call: Setting periodicity to PERIODICITY_INTRADAY");
                break;
            }
            case 1: {
                MS4Java.g.a(MS4Java.bf[26]);
                a("Public Method Call: Setting periodicity to PERIODICITY_DAILY");
                break;
            }
            case 2: {
                MS4Java.g.a(MS4Java.bf[27]);
                a("Public Method Call: Setting periodicity to PERIODICITY_WEEKLY");
                break;
            }
            case 3: {
                MS4Java.g.a(MS4Java.bf[28]);
                a("Public Method Call: Setting periodicity to PERIODICITY_MONTHLY");
                break;
            }
            default: {
                a("Warning: Invalid periodicity passed to SetPeriodicity()");
                break;
            }
        }
        this.LoadData(null);
    }
    
    public void SetDateRange(final Date date, final Date date2) {
        MS4Java.d[0].a(date);
        MS4Java.d[0].b(date2);
        a("Public Method Call: Date range set (LoadData() to test new range)");
    }
    
    public void LoadData(final String text) {
        int int1 = 15;
        int int2 = 0;
        a("Public Method Call: LoadData(" + text + ")");
        if (text != null) {
            text.trim();
            MS4Java.f.a.setText(text);
        }
        try {
            this.LoadDataInternal();
            if (!MS4Java.a6.equals("")) {
                a("Setting indicator " + MS4Java.a6);
                MS4Java.f.a(MS4Java.a6, MS4Java.a7, MS4Java.a8);
            }
            if (!MS4Java.a7.equals("")) {
                int1 = Integer.parseInt(MS4Java.a7);
            }
            if (!MS4Java.a8.equals("")) {
                int2 = Integer.parseInt(MS4Java.a8);
            }
            a("Calling CalcIndicator");
            MS4Java.a.a(MS4Java.f.e.getSelectedItem(), MS4Java.d[0], MS4Java.b, MS4Java.c, "", int1, int2);
        }
        catch (Exception ex) {
            a("Exception loading data for symbol " + text);
        }
        MS4Java.e.repaint();
    }
    
    public String GetSymbol() {
        a("Entering GetSymbol()");
        String s = MS4Java.f.a.getText();
        if (MS4Java.ae) {
            s = s.toUpperCase();
        }
        final String trim = s.trim();
        a("Public Method Call: Getting symbol '" + trim + "'");
        return trim;
    }
    
    private static void a(final char c, final int n) {
        switch (c) {
            case '+': {
                for (int i = MS4Java.d[n].k.a; i <= MS4Java.d[n].k.b; ++i) {
                    final double[] c2 = MS4Java.d[n - 1].h.c;
                    final int n2 = i;
                    c2[n2] += MS4Java.d[n].h.c[i];
                    final double[] c3 = MS4Java.d[n - 1].i.c;
                    final int n3 = i;
                    c3[n3] += MS4Java.d[n].i.c[i];
                    final double[] c4 = MS4Java.d[n - 1].j.c;
                    final int n4 = i;
                    c4[n4] += MS4Java.d[n].j.c[i];
                    final double[] c5 = MS4Java.d[n - 1].k.c;
                    final int n5 = i;
                    c5[n5] += MS4Java.d[n].k.c[i];
                    final double[] c6 = MS4Java.d[n - 1].l.c;
                    final int n6 = i;
                    c6[n6] += MS4Java.d[n].k.c[i];
                }
            }
            case '-': {
                for (int j = MS4Java.d[n].k.a; j <= MS4Java.d[n].k.b; ++j) {
                    final double[] c7 = MS4Java.d[n - 1].h.c;
                    final int n7 = j;
                    c7[n7] -= MS4Java.d[n].h.c[j];
                    final double[] c8 = MS4Java.d[n - 1].i.c;
                    final int n8 = j;
                    c8[n8] -= MS4Java.d[n].i.c[j];
                    final double[] c9 = MS4Java.d[n - 1].j.c;
                    final int n9 = j;
                    c9[n9] -= MS4Java.d[n].j.c[j];
                    final double[] c10 = MS4Java.d[n - 1].k.c;
                    final int n10 = j;
                    c10[n10] -= MS4Java.d[n].k.c[j];
                    final double[] c11 = MS4Java.d[n - 1].l.c;
                    final int n11 = j;
                    c11[n11] -= MS4Java.d[n].k.c[j];
                }
            }
            case '*': {
                for (int k = MS4Java.d[n].k.a; k <= MS4Java.d[n].k.b; ++k) {
                    final double[] c12 = MS4Java.d[n - 1].h.c;
                    final int n12 = k;
                    c12[n12] *= MS4Java.d[n].h.c[k];
                    final double[] c13 = MS4Java.d[n - 1].i.c;
                    final int n13 = k;
                    c13[n13] *= MS4Java.d[n].i.c[k];
                    final double[] c14 = MS4Java.d[n - 1].j.c;
                    final int n14 = k;
                    c14[n14] *= MS4Java.d[n].j.c[k];
                    final double[] c15 = MS4Java.d[n - 1].k.c;
                    final int n15 = k;
                    c15[n15] *= MS4Java.d[n].k.c[k];
                    final double[] c16 = MS4Java.d[n - 1].l.c;
                    final int n16 = k;
                    c16[n16] *= MS4Java.d[n].k.c[k];
                }
            }
            case '/': {
                final double n17 = MS4Java.d[n - 1].k.c[0] / MS4Java.d[n].k.c[0];
                final double n18 = 0.0;
                final double n19 = 1.0;
                for (int l = MS4Java.d[n].k.a; l <= MS4Java.d[n].k.b; ++l) {
                    MS4Java.d[n - 1].h.c[l] = (MS4Java.d[n - 1].h.c[l] / MS4Java.d[n].h.c[l] - n18) * n19;
                    MS4Java.d[n - 1].i.c[l] = (MS4Java.d[n - 1].i.c[l] / MS4Java.d[n].i.c[l] - n18) * n19;
                    MS4Java.d[n - 1].j.c[l] = (MS4Java.d[n - 1].j.c[l] / MS4Java.d[n].j.c[l] - n18) * n19;
                    MS4Java.d[n - 1].k.c[l] = (MS4Java.d[n - 1].k.c[l] / MS4Java.d[n].k.c[l] - n18) * n19;
                    MS4Java.d[n - 1].l.c[l] = MS4Java.d[n - 1].l.c[l];
                }
            }
            default: {}
        }
    }
    
    private static boolean a(final String b, final int n) {
        boolean b2 = false;
        if (MS4Java.a9) {
            try {
                final double doubleValue = Double.valueOf(b);
                for (int i = MS4Java.d[n - 1].k.a; i <= MS4Java.d[n - 1].k.b; ++i) {
                    MS4Java.d[n].e[i] = MS4Java.d[n - 1].e[i];
                    MS4Java.d[n].d[i] = MS4Java.d[n - 1].d[i];
                    MS4Java.d[n].c[i] = MS4Java.d[n - 1].c[i];
                    MS4Java.d[n].g[i] = MS4Java.d[n - 1].g[i];
                    MS4Java.d[n].h.c[i] = doubleValue;
                    MS4Java.d[n].i.c[i] = doubleValue;
                    MS4Java.d[n].j.c[i] = doubleValue;
                    MS4Java.d[n].k.c[i] = doubleValue;
                    MS4Java.d[n].l.c[i] = 0.0;
                }
                MS4Java.d[n].m = MS4Java.d[n - 1].m;
                MS4Java.d[n].n = MS4Java.d[n - 1].n;
                final h h = MS4Java.d[n].h;
                final h j = MS4Java.d[n].i;
                final h k = MS4Java.d[n].j;
                final h l = MS4Java.d[n].k;
                final h m = MS4Java.d[n].l;
                final boolean a = false;
                m.a = (a ? 1 : 0);
                l.a = (a ? 1 : 0);
                k.a = (a ? 1 : 0);
                j.a = (a ? 1 : 0);
                h.a = (a ? 1 : 0);
                final h h2 = MS4Java.d[n].h;
                final h i2 = MS4Java.d[n].i;
                final h j2 = MS4Java.d[n].j;
                final h k2 = MS4Java.d[n].k;
                final h l2 = MS4Java.d[n].l;
                final int b3 = MS4Java.d[n - 1].k.b;
                l2.b = b3;
                k2.b = b3;
                j2.b = b3;
                i2.b = b3;
                h2.b = b3;
                MS4Java.d[n].b = b;
                b2 = true;
            }
            catch (NumberFormatException ex) {}
        }
        return b2;
    }
    
    private static final void b(final String s, final int n) throws Exception {
        final Color[] array = { Color.blue, new Color(0, 128, 0), new Color(196, 0, 0), Color.magenta, Color.darkGray };
        final int n2 = 5;
        final Color w = MS4Java.w;
        a("Fetch symbol = " + s);
        a("Loading data for " + s);
        MS4Java.d[n].a(s, MS4Java.g.b());
        a("Data loaded for " + s);
        Color black;
        if (n < n2) {
            black = array[n];
        }
        else {
            black = Color.black;
        }
        final h h = MS4Java.d[n].h;
        final h i = MS4Java.d[n].i;
        final h j = MS4Java.d[n].j;
        final h k = MS4Java.d[n].k;
        final Color color = black;
        k.h = color;
        j.h = color;
        i.h = color;
        h.h = color;
    }
    
    static {
        MS4Java.d = new g[550];
        MS4Java.l = null;
        MS4Java.m = null;
        MS4Java.n = -1;
        MS4Java.o = Color.white;
        MS4Java.p = Color.black;
        MS4Java.q = Color.black;
        MS4Java.r = new Color(230, 230, 230);
        MS4Java.s = Color.lightGray;
        MS4Java.t = Color.white;
        MS4Java.u = Color.black;
        MS4Java.v = Color.white;
        MS4Java.w = Color.blue;
        MS4Java.x = Color.blue;
        MS4Java.y = Color.magenta;
        MS4Java.z = Color.red;
        MS4Java.aa = Color.black;
        MS4Java.ab = Color.white;
        MS4Java.ac = true;
        MS4Java.ae = true;
        MS4Java.af = 500;
        MS4Java.ag = null;
        MS4Java.ah = null;
        MS4Java.ai = null;
        MS4Java.aj = null;
        MS4Java.ak = null;
        MS4Java.al = null;
        MS4Java.am = null;
        MS4Java.an = "";
        MS4Java.ao = "MetaStock®";
        MS4Java.ap = 18;
        MS4Java.aq = "north";
        MS4Java.ar = true;
        MS4Java.as = "MMDDYY";
        MS4Java.at = '/';
        MS4Java.au = ".SPX";
        MS4Java.av = "NONE";
        MS4Java.ax = "_self";
        MS4Java.ay = "_self";
        MS4Java.az = true;
        MS4Java.a1 = true;
        MS4Java.a2 = true;
        MS4Java.a3 = true;
        MS4Java.a4 = true;
        MS4Java.a9 = true;
        MS4Java.bd = 50;
        MS4Java.be = 3;
        MS4Java.bf = new String[69];
    }
}
