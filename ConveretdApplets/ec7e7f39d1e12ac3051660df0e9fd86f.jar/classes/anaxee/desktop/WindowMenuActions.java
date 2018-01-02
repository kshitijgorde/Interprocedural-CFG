// 
// Decompiled by Procyon v0.5.30
// 

package classes.anaxee.desktop;

import java.lang.management.ManagementFactory;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

class WindowMenuActions extends WindowAdapter
{
    boolean is_mode_debug;
    
    public WindowMenuActions(final boolean is_mode_debug) {
        this.is_mode_debug = is_mode_debug;
    }
    
    @Override
    public void windowClosing(final WindowEvent windowEvent) {
        final String name = ManagementFactory.getRuntimeMXBean().getName();
        final String string = "taskkill /f /pid " + name.substring(0, name.indexOf(64));
        if (this.is_mode_debug) {
            System.out.println(string);
        }
        try {
            Runtime.getRuntime().exec(string);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
