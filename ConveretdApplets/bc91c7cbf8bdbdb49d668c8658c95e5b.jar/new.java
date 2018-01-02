// 
// Decompiled by Procyon v0.5.30
// 

public class new
{
    public String bb;
    public float db;
    public String size;
    public int cb;
    private static String Ea = "";
    private static String Fa = "\u3684";
    
    public new() {
        this.bb = new String(new.Ea);
        this.size = new String(new.Fa);
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u36e5');
        }
        return new String(array);
    }
    
    static {
        new.Ea = _(new.Ea);
        new.Fa = _(new.Fa);
    }
}
