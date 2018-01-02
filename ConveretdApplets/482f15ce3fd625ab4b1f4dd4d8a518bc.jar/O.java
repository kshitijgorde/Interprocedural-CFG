// 
// Decompiled by Procyon v0.5.30
// 

public final class O
{
    public static String a;
    public String b;
    public String c;
    public String d;
    private au a;
    
    public final boolean a() {
        return O.a.equalsIgnoreCase(this.a.a.getCodeBase().getHost());
    }
    
    public O(final au a) {
        this.b = O.a;
        this.c = "/nescafe/nescafe.server.asp";
        this.d = "/nescafe/server/NESCafe.romlist.txt";
        this.a = a;
        this.b = a.a.getCodeBase().getHost();
    }
    
    static {
        O.a = "www.nescafeweb.com";
    }
}
