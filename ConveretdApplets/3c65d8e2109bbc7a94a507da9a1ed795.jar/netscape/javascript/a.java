// 
// Decompiled by Procyon v0.5.30
// 

package netscape.javascript;

public class a extends RuntimeException
{
    public static final int new = -1;
    public static final int case = 0;
    public static final int b = 1;
    public static final int goto = 2;
    public static final int for = 3;
    public static final int void = 4;
    public static final int int = 5;
    public static final int byte = 6;
    protected String long;
    protected String if;
    protected int do;
    protected String a;
    protected int try;
    private int else;
    private Object char;
    
    public a() {
        this((String)null);
    }
    
    public a(final String s) {
        this(s, null, -1, null, -1);
    }
    
    public a(final String long1, final String if1, final int do1, final String a, final int try1) {
        super(long1);
        this.long = null;
        this.if = null;
        this.do = -1;
        this.a = null;
        this.try = -1;
        this.else = -1;
        this.char = null;
        this.long = long1;
        this.if = if1;
        this.do = do1;
        this.a = a;
        this.try = try1;
        this.else = -1;
    }
    
    public a(final int else1, final Object char1) {
        this();
        this.else = else1;
        this.char = char1;
    }
    
    public int if() {
        return this.else;
    }
    
    public Object a() {
        return this.char;
    }
}
