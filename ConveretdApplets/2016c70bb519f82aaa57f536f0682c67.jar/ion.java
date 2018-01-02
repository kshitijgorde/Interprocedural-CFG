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
    
    ion(final String s, final String s1) {
        this();
        this.compare(s, s1);
    }
    
    private String FVget(String s) {
        final byte byte0 = 20;
        s = s.toLowerCase();
        final char[] ac = new char[byte0];
        final int l = s.length();
        int i1 = 0;
        int j = 0;
        byte byte2 = 0;
        if (s.startsWith("www.")) {
            byte2 = 4;
            for (j = 4; j < l; i1 += s.charAt(j++)) {}
            j = 4;
        }
        else {
            for (j = 0; j < l; i1 += s.charAt(j++)) {}
            j = 0;
        }
        this.FVrandom(i1);
        ac[0] = '6';
        ac[1] = 's';
        int k = 2;
        int j2 = 0;
        while (k < byte0) {
            int m = Math.abs(this.FVrand() + j2);
            m += s.charAt(j);
            m %= 10;
            if (m < 10) {
                ac[k] = (char)(m + 48);
            }
            else if (m < 34) {
                ac[k] = (char)(m - 10 + 97);
            }
            else {
                ac[k] = (char)(m - 34 + 65);
            }
            ++k;
            if (++j >= l) {
                j = byte2;
                j2 = Math.abs(this.FVrand());
            }
        }
        return new String(ac);
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
    
    private void FVrandom(final int i) {
        this.FVseed = i;
        this.FVseedpos = 1;
    }
    
    public boolean compare(final String s, final String s1) {
        System.err.println("Get.Host : " + s);
        if (s.equals("http://membres.lycos.fr/droles2cochonnes/") || s.equals("")) {
            return this.fullversion = true;
        }
        if (s1 == null) {
            return false;
        }
        if (s1.equals("")) {
            return false;
        }
        System.err.println("ID KEY: " + s1);
        final StringTokenizer stringtokenizer = new StringTokenizer(s1, "+");
        while (stringtokenizer.hasMoreTokens()) {
            if (this.FVget(s).equals(stringtokenizer.nextToken())) {
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
