import java.awt.AWTEvent;

// 
// Decompiled by Procyon v0.5.30
// 

public class SetContentEvent extends AWTEvent implements Runnable
{
    private Object[] action;
    
    public SetContentEvent(final Object o, final Object[] action) {
        super(o, 3000);
        this.action = action;
    }
    
    public final void run() {
        final String s = (String)this.action[0];
        final FormulaEditorCalc formulaEditorCalc = (FormulaEditorCalc)this.action[1];
        try {
            final boolean booleanValue = (boolean)this.action[2];
            final boolean booleanValue2 = (boolean)this.action[3];
            final boolean booleanValue3 = (boolean)this.action[4];
            String s2;
            if (this.action.length >= 6) {
                s2 = (String)this.action[5];
            }
            else {
                s2 = null;
            }
            formulaEditorCalc.setInitialString(s, booleanValue, booleanValue2, booleanValue3, s2, false);
        }
        finally {
            formulaEditorCalc.pendingSetContent = false;
        }
    }
}
