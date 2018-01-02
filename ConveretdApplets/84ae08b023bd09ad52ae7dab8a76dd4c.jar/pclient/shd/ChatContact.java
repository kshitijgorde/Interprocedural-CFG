// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

public class ChatContact
{
    private Config mainConf;
    
    public ChatContact(final Config mainConf) {
        this.mainConf = null;
        this.mainConf = mainConf;
    }
    
    public void print(final String s) {
        if (this.doit()) {
            System.out.println(s);
        }
    }
    
    public void print(final String s, final Exception ex) {
        if (this.doit()) {
            System.out.println(s);
            ex.printStackTrace();
        }
    }
    
    private boolean doit() {
        final String value = this.mainConf.get("Burger");
        return value != null && value.equals("TRUE");
    }
}
