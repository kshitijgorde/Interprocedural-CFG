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
    
    ion(final String host_str, final String id_code) {
        this();
        this.compare(host_str, id_code);
    }
    
    private String FVget(String host_str) {
        final int MAXCODELEN = 20;
        host_str = host_str.toLowerCase();
        final char[] finalstr = new char[MAXCODELEN];
        final int len = host_str.length();
        int rand_first = 0;
        int st = 0;
        int st_first = 0;
        if (host_str.startsWith("www.")) {
            st_first = 4;
            for (st = 4; st < len; rand_first += host_str.charAt(st++)) {}
            st = 4;
        }
        else {
            for (st = 0; st < len; rand_first += host_str.charAt(st++)) {}
            st = 0;
        }
        this.FVrandom(rand_first);
        finalstr[0] = '6';
        finalstr[1] = 's';
        int count = 2;
        int rand_add = 0;
        while (count < MAXCODELEN) {
            int num = Math.abs(this.FVrand() + rand_add);
            num += host_str.charAt(st);
            num %= 10;
            if (num < 10) {
                finalstr[count] = (char)(num + 48);
            }
            else if (num < 34) {
                finalstr[count] = (char)(num - 10 + 97);
            }
            else {
                finalstr[count] = (char)(num - 34 + 65);
            }
            ++count;
            if (++st >= len) {
                st = st_first;
                rand_add = Math.abs(this.FVrand());
            }
        }
        return new String(finalstr);
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
    
    private void FVrandom(final int seed) {
        this.FVseed = seed;
        this.FVseedpos = 1;
    }
    
    public boolean compare(final String host_str, final String id_code) {
        if (host_str.equals("")) {
            return this.fullversion = true;
        }
        if (id_code == null) {
            return false;
        }
        if (id_code.equals("")) {
            return false;
        }
        System.err.println("ID KEY: " + id_code);
        final StringTokenizer st = new StringTokenizer(id_code, "+");
        while (st.hasMoreTokens()) {
            if (this.FVget(host_str).equals(st.nextToken())) {
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
