import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class n
{
    private final JavaWatch new;
    aa for;
    x int;
    Date byte;
    int do;
    int a;
    String if;
    String try;
    
    n(final JavaWatch new1, final aa for1, final String s, final Date byte1, final int do1, final int a) {
        final String[] split = s.split(" ");
        this.new = new1;
        this.if = split[0];
        this.try = split[2];
        String substring = split[1];
        if (substring.indexOf(63) > 0) {
            substring = substring.substring(0, substring.indexOf(63));
        }
        this.for = for1;
        this.int = this.new.do(substring);
        this.byte = byte1;
        this.do = do1;
        this.a = a;
    }
    
    public aa for() {
        return this.for;
    }
    
    public x if() {
        return this.int;
    }
    
    public String a() {
        switch (this.do) {
            case 200: {
                return "OK";
            }
            case 404: {
                return "Not Found";
            }
            case 304: {
                return "Unchanged";
            }
            default: {
                return "" + this.do;
            }
        }
    }
    
    public JavaWatch do() {
        return this.new;
    }
}
