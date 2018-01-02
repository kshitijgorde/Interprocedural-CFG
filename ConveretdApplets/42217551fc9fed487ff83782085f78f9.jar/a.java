import netscape.javascript.JSObject;

// 
// Decompiled by Procyon v0.5.30
// 

class a extends Thread
{
    JSObject if;
    String a;
    
    public a(final JSObject if1, final String a) {
        this.if = if1;
        this.a = a;
    }
    
    public void run() {
        if (this.a != null && this.if != null) {
            try {
                this.if.eval(this.a);
            }
            catch (Exception ex) {}
        }
    }
}
