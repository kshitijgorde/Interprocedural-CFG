// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.io.PrintStream;
import java.util.Hashtable;
import java.awt.Color;

class DebugGraphicsInfo
{
    Color flashColor;
    int flashTime;
    int flashCount;
    Hashtable componentToDebug;
    JFrame debugFrame;
    PrintStream stream;
    
    DebugGraphicsInfo() {
        this.flashColor = Color.red;
        this.flashTime = 100;
        this.flashCount = 2;
        this.debugFrame = null;
        this.stream = System.out;
    }
    
    int getDebugOptions(final JComponent component) {
        if (this.componentToDebug == null) {
            return 0;
        }
        final Integer n = this.componentToDebug.get(component);
        return (n == null) ? 0 : n;
    }
    
    void log(final String s) {
        this.stream.println(s);
    }
    
    void setDebugOptions(final JComponent component, final int n) {
        if (this.componentToDebug == null) {
            this.componentToDebug = new Hashtable();
        }
        if (n > 0) {
            this.componentToDebug.put(component, new Integer(n));
        }
        else {
            this.componentToDebug.remove(component);
        }
    }
}
