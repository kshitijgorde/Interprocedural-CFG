// 
// Decompiled by Procyon v0.5.30
// 

package S1S;

import java.util.EventObject;
import java.util.EventListener;
import java.awt.AWTEventMulticaster;

public class SpinPanelListenerEventMulticaster extends AWTEventMulticaster implements SpinPanelListener
{
    protected SpinPanelListenerEventMulticaster(final SpinPanelListener a, final SpinPanelListener b) {
        super(a, b);
    }
    
    public static SpinPanelListener add(final SpinPanelListener a, final SpinPanelListener b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return new SpinPanelListenerEventMulticaster(a, b);
    }
    
    public static SpinPanelListener remove(final SpinPanelListener a, final SpinPanelListener b) {
        return (SpinPanelListener)AWTEventMulticaster.removeInternal(a, b);
    }
    
    public void textField1Text_textValueChanged(final EventObject newEvent) {
        ((SpinPanelListener)super.a).textField1Text_textValueChanged(newEvent);
        ((SpinPanelListener)super.b).textField1Text_textValueChanged(newEvent);
    }
}
