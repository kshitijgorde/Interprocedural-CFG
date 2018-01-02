import netscape.javascript.JSObject;

// 
// Decompiled by Procyon v0.5.30
// 

class n extends Thread
{
    JSObject a;
    String if;
    
    public n(final JSObject a, final String if1) {
        this.a = a;
        this.if = if1;
    }
    
    public void run() {
        if (this.if != null && this.a != null) {
            try {
                this.a.eval(this.if);
            }
            catch (Exception ex) {}
        }
    }
}
