import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public class b
{
    private String a;
    private String if;
    private int byte;
    private int try;
    private boolean else;
    private boolean case;
    private int for;
    public String long;
    private String goto;
    private String char;
    private boolean int;
    private int do;
    public int new;
    
    public b() {
        this.a = "N";
        this.if = "";
        this.byte = 1;
        this.try = 1;
        this.else = true;
        this.case = true;
        this.for = 0;
        this.long = "";
        this.goto = "HH:mm";
        this.char = "2";
        this.int = true;
        this.do = 0;
    }
    
    public int char() {
        return this.try;
    }
    
    public int case() {
        return this.byte;
    }
    
    public String for() {
        return this.a;
    }
    
    public String else() {
        if (this.a.equals("Y")) {
            return "B|A";
        }
        return this.a;
    }
    
    public String do() {
        return this.if;
    }
    
    public boolean byte() {
        return this.int;
    }
    
    public void a(final boolean int1) {
        this.int = int1;
    }
    
    public int a() {
        return this.for;
    }
    
    public int if() {
        return this.do;
    }
    
    public boolean new() {
        return this.else;
    }
    
    public boolean int() {
        return this.case;
    }
    
    public void a(final String s, final int n, final int n2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        if (stringTokenizer.hasMoreTokens()) {
            this.a = stringTokenizer.nextToken();
        }
        if (stringTokenizer.hasMoreTokens()) {
            this.if = stringTokenizer.nextToken();
        }
        if (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            if (!trim.equals("")) {
                this.byte = Math.min(Math.max(Integer.parseInt(trim), n2), n);
            }
            else {
                this.byte = n2;
            }
        }
        else {
            this.byte = n2;
        }
        if (stringTokenizer.hasMoreTokens()) {
            this.try = Integer.parseInt(stringTokenizer.nextToken());
        }
        if (stringTokenizer.hasMoreTokens()) {
            this.else = Boolean.valueOf(stringTokenizer.nextToken());
        }
        if (stringTokenizer.hasMoreTokens()) {
            this.case = Boolean.valueOf(stringTokenizer.nextToken());
        }
        if (stringTokenizer.hasMoreTokens()) {
            this.do = Integer.parseInt(stringTokenizer.nextToken());
        }
        if (stringTokenizer.hasMoreTokens()) {
            this.for = Integer.parseInt(stringTokenizer.nextToken());
        }
    }
    
    public void a(final int byte1) {
        this.byte = byte1;
    }
    
    public String try() {
        return String.valueOf(this.a) + ";" + this.if + ";" + String.valueOf(this.byte) + ";" + String.valueOf(this.try) + ";" + String.valueOf(this.else) + ";" + String.valueOf(this.case) + ";" + String.valueOf(this.for) + ";" + this.long + ";" + this.goto + ";" + this.char;
    }
    
    public String toString() {
        return String.valueOf(this.a) + ";" + this.if + ";" + String.valueOf(this.byte) + ";" + String.valueOf(this.try) + ";" + String.valueOf(this.else) + ";" + String.valueOf(this.case) + ";" + String.valueOf(this.for) + ";" + this.long + ";" + this.goto + ";" + this.char;
    }
}
