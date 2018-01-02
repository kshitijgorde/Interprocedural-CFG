import netscape.javascript.JSObject;

// 
// Decompiled by Procyon v0.5.30
// 

public class a implements b
{
    private String b;
    private JSObject a;
    
    public a(final String b, final JSObject a) {
        this.b = b;
        this.a = a;
    }
    
    public void a(final f f) {
        final Object[] array = { f.b, f.a, this.b, f };
        try {
            if (f.a.equalsIgnoreCase("LPS_ADMIN")) {
                if (!((f.size() > 0) ? new String(f.c[0]) : "").equals("")) {
                    this.a.eval(new String(f.c[0]));
                }
            }
            else {
                this.a.call("live_receive_java", array);
            }
        }
        catch (Exception ex) {
            h.a("", ex);
        }
    }
}
