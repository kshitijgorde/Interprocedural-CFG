// 
// Decompiled by Procyon v0.5.30
// 

package octoshape.applets.init;

import netscape.javascript.JSObject;

public class AppletDebug
{
    private boolean consoleOut;
    private boolean debugdivOut;
    private String callbackId;
    private JSObject jso;
    
    public AppletDebug(final String callbackId, final String s, final JSObject jso) {
        this.consoleOut = false;
        this.debugdivOut = false;
        this.callbackId = callbackId;
        this.jso = jso;
        if (s != null && s.startsWith("debug")) {
            if (s.indexOf("console") > 0) {
                this.consoleOut = true;
            }
            if (s.indexOf("div") > 0) {
                this.debugdivOut = true;
            }
        }
    }
    
    public void printInfo() {
        this.print("debug console: " + this.consoleOut + " debug div: " + this.debugdivOut);
    }
    
    public synchronized void print(final String s) {
        if (this.consoleOut) {
            System.out.println(s);
        }
        if (this.debugdivOut && this.jso != null) {
            this.jso.call("octopv_debug", (Object[])new String[] { s, this.callbackId });
        }
    }
    
    public void progress(final String s) {
        this.jso.call("octopv_appletProgress", (Object[])new String[] { s });
    }
}
