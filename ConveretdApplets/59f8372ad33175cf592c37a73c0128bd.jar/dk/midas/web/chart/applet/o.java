// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.util.Enumeration;
import java.util.Vector;

public class o
{
    public static final int char = 0;
    public static final int n = 11;
    public static final int do = 21;
    public static final int t = 31;
    public static final int long = 41;
    public static final int z = 51;
    public static final int if = 61;
    public static final int case = 71;
    public static final int int = 81;
    public static final int B = 91;
    public static final int h = 52;
    public static final int q = 82;
    public static final int c = 92;
    public static final int o = 93;
    public static final int g = 42;
    public static final int e = 43;
    public static final int new = 44;
    public static final int byte = 45;
    public static final int s = 46;
    public static final int m = 47;
    public static final int p = -330;
    public static final int b = -340;
    public static final int i = -1;
    public static final int k = 1;
    public static final int r = 2;
    public static final int goto = 3;
    public static final int try = 4;
    public static final int else = 5;
    public static final int w = 6;
    public static final int for = 7;
    public static final int l = 8;
    public static final int u = 9;
    public static final int C = 10;
    public static final int f = 11;
    public static final int void = 12;
    public static final int v = 13;
    public static final int null = 14;
    public static final int d = 15;
    public static final int E = 16;
    public static final int A = 17;
    public static final int x = 18;
    public static final int j = 19;
    public static final int a = -330;
    public static final int y = -340;
    private static Vector D;
    
    private static synchronized Vector a() {
        if (dk.midas.web.chart.applet.o.D == null) {
            (dk.midas.web.chart.applet.o.D = new Vector()).addElement(new Study(0, 0, "", ""));
            dk.midas.web.chart.applet.o.D.addElement(new Study(11, 1, "MOM", "dk.midas.web.chart.applet.Momentum"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(21, 2, "RSI", "dk.midas.web.chart.applet.RSI"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(31, 3, "ROC", "dk.midas.web.chart.applet.ROC"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(41, 4, "MVG", "dk.midas.web.chart.applet.MovAvg"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(51, 5, "MACD", "dk.midas.web.chart.applet.MACD"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(61, 6, "OSC", "dk.midas.web.chart.applet.OSC"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(71, 7, "PKS", "dk.midas.web.chart.applet.PKS"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(81, 8, "MAE", "dk.midas.web.chart.applet.MAE"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(91, 9, "BOL", "dk.midas.web.chart.applet.BOLL"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(52, 10, "PMACD", "dk.midas.web.chart.applet.MACD"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(82, 11, "PMAE", "dk.midas.web.chart.applet.MAE"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(92, 12, "PSAR", "dk.midas.web.chart.applet.PSAR"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(93, 13, "RNG", "dk.midas.web.chart.applet.RNG"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(42, 14, "DMI", "dk.midas.web.chart.applet.DMI"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(43, 15, "PF", "dk.midas.web.chart.applet.PFAnalyse"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(44, 16, "WPR", "dk.midas.web.chart.applet.WPR"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(45, 17, "TR", "dk.midas.web.chart.applet.TrueRange"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(46, 18, "CCI", "dk.midas.web.chart.applet.CCI"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(47, 19, "ADX", "dk.midas.web.chart.applet.DMI"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(-330, -330, "Volume", "dk.midas.web.chart.applet.Volume"));
            dk.midas.web.chart.applet.o.D.addElement(new Study(-340, -340, "Open Interest", "dk.midas.web.chart.applet.OpenInterest"));
        }
        return dk.midas.web.chart.applet.o.D;
    }
    
    public static Study if(final int n) {
        final Enumeration<Study> elements = a().elements();
        while (elements.hasMoreElements()) {
            final Study study = elements.nextElement();
            if (n == study.new()) {
                return study;
            }
        }
        return null;
    }
    
    public static Study do(final int n) {
        final Enumeration<Study> elements = a().elements();
        while (elements.hasMoreElements()) {
            final Study study = elements.nextElement();
            if (n == study.a()) {
                return study;
            }
        }
        return null;
    }
    
    public static Study a(final String s) {
        final Enumeration<Study> elements = a().elements();
        while (elements.hasMoreElements()) {
            final Study study = elements.nextElement();
            if (s.equals(study.int())) {
                return study;
            }
        }
        return null;
    }
    
    public static Study if(final String s) {
        final Enumeration<Study> elements = a().elements();
        while (elements.hasMoreElements()) {
            final Study study = elements.nextElement();
            if (s.startsWith(study.int())) {
                return study;
            }
        }
        return null;
    }
    
    public static ag a(final int n) {
        final Study if1 = if(n);
        return (if1 == null) ? null : if1.try();
    }
    
    public static void a(final int n, final ag ag) {
        final Study if1 = if(n);
        if (if1 != null) {
            if1.a(ag);
        }
    }
}
