import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public class ion
{
    private int FVseed;
    private int FVseedpos;
    public boolean fullversion;
    
    ion() {
        this.fullversion = false;
        this.FVseed = 1;
        this.FVseedpos = 1;
    }
    
    ion(final String s, final String s2) {
        this();
        this.compare(s, s2);
    }
    
    private String FVget(String lowerCase) {
        final int n = 20;
        lowerCase = lowerCase.toLowerCase();
        final char[] array = new char[n];
        final int length = lowerCase.length();
        char c = '\0';
        int n2 = 0;
        int n3;
        if (lowerCase.startsWith("www.")) {
            n2 = 4;
            for (int i = 4; i < length; c += lowerCase.charAt(i++)) {}
            n3 = 4;
        }
        else {
            for (int j = 0; j < length; c += lowerCase.charAt(j++)) {}
            n3 = 0;
        }
        this.FVrandom(c);
        array[0] = '6';
        array[1] = 's';
        int k = 2;
        int abs = 0;
        while (k < n) {
            final int n4 = (Math.abs(this.FVrand() + abs) + lowerCase.charAt(n3)) % 10;
            if (n4 < 10) {
                array[k] = (char)(n4 + 48);
            }
            else if (n4 < 34) {
                array[k] = (char)(n4 - 10 + 97);
            }
            else {
                array[k] = (char)(n4 - 34 + 65);
            }
            ++k;
            if (++n3 >= length) {
                n3 = n2;
                abs = Math.abs(this.FVrand());
            }
        }
        return new String(array);
    }
    
    private int FVrand() {
        if (this.FVseed == 0) {
            this.FVseed = this.FVseedpos;
        }
        this.FVseed *= this.FVseedpos;
        this.FVseed += this.FVseedpos % 2;
        this.FVseed *= this.FVseed % 100;
        this.FVseed %= 20000;
        if (++this.FVseedpos > 20) {
            this.FVseedpos = 1;
        }
        return this.FVseed;
    }
    
    private void FVrandom(final int fVseed) {
        this.FVseed = fVseed;
        this.FVseedpos = 1;
    }
    
    public boolean compare(final String s, final String s2) {
        if (s.equals("")) {
            return this.fullversion = true;
        }
        if (s2 == null) {
            return false;
        }
        if (s2.equals("")) {
            return false;
        }
        System.err.println("ID KEY: " + s2);
        final StringTokenizer stringTokenizer = new StringTokenizer(s2, "+");
        while (stringTokenizer.hasMoreTokens()) {
            if (this.FVget(s).equals(stringTokenizer.nextToken())) {
                return this.fullversion = true;
            }
        }
        System.err.println("ID KEY: invalid");
        return false;
    }
    
    public boolean isFull() {
        return this.fullversion;
    }
}
