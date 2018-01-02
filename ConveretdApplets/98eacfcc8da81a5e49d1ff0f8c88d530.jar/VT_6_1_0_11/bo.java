// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

final class bo implements dr
{
    private static dG a;
    
    public final cU a(final aJ aj) {
        String string;
        String s;
        String string2;
        if (aj.c().equalsIgnoreCase("SOCKS5")) {
            string = "Enter username and password for SOCKS server on host";
            s = aj.a();
            string2 = "Authentication Method: username/password";
        }
        else {
            string = "Enter username and password for realm `" + aj.d() + "'";
            s = "on host " + aj.a() + ":" + aj.b();
            string2 = "Authentication Scheme: " + aj.c();
        }
        synchronized (this.getClass()) {
            if (bo.a == null) {
                bo.a = new dG();
            }
        }
        final aJ aj2;
        return bo.a.a(string, s, string2, aj2.c());
    }
    
    static {
        bo.a = null;
    }
}
