import java.awt.EventQueue;
import java.awt.AWTEvent;
import java.awt.Toolkit;

// 
// Decompiled by Procyon v0.5.30
// 

public class FormulaEditorParse extends Thread
{
    static FormulaEditorParse Z;
    static Cua dispatchEvent;
    
    static final synchronized void I() {
    }
    
    static final void I(final FormulaEditorCalc formulaEditorCalc, final String s, final boolean b, final boolean b2, final boolean b3, final String s2) {
        final Object[] array = { s, formulaEditorCalc, new Boolean(b), new Boolean(b2), new Boolean(b3), s2 };
        formulaEditorCalc.pendingSetContent = true;
        final SetContentEvent setContentEvent = new SetContentEvent(formulaEditorCalc.getComponent(), array);
        EventQueue systemEventQueue = null;
        try {
            systemEventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
            systemEventQueue.postEvent(setContentEvent);
        }
        catch (Throwable t) {
            if (systemEventQueue == null) {
                formulaEditorCalc.getComponent().dispatchEvent(setContentEvent);
            }
        }
    }
    
    public final void run() {
    }
    
    static {
        FormulaEditorParse.Z = null;
        FormulaEditorParse.dispatchEvent = new Cua();
    }
}
