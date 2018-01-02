import java.io.StreamTokenizer;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class rd2
{
    public int iB;
    public int iT;
    public String sM;
    public String sU;
    public String sTarg;
    public Font wFont;
    public int iScr;
    
    public boolean get(final rd2 rd2, final StreamTokenizer streamTokenizer) {
        int n = 0;
        int n2 = 0;
        String s = "Arial";
        int n3 = 16;
        int n4 = 0;
        try {
            while (true) {
                streamTokenizer.nextToken();
                if (streamTokenizer.ttype == 10) {
                    if (n > 2) {
                        rd2.wFont = new Font(s, n4, n3);
                    }
                    return true;
                }
                if (streamTokenizer.ttype == -1) {
                    return false;
                }
                if (streamTokenizer.ttype == -2) {
                    if (n2 == 0) {
                        rd2.iB = (int)streamTokenizer.nval;
                    }
                    else if (n2 == 1) {
                        rd2.iT = (int)streamTokenizer.nval;
                    }
                    else if (n2 == 2) {
                        n3 = (int)streamTokenizer.nval;
                    }
                    else if (n2 == 3) {
                        n4 = (int)streamTokenizer.nval;
                    }
                    else if (n2 == 4) {
                        rd2.iScr = (int)streamTokenizer.nval;
                    }
                    ++n2;
                }
                else {
                    if (n == 0) {
                        rd2.sM = new String(streamTokenizer.sval);
                    }
                    else if (n == 1) {
                        rd2.sU = new String(streamTokenizer.sval);
                    }
                    else if (n == 2) {
                        s = new String(streamTokenizer.sval);
                    }
                    else if (n == 3) {
                        this.sTarg = new String(streamTokenizer.sval);
                    }
                    ++n;
                }
            }
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public rd2() {
        this.iB = -1;
        this.iT = -1;
        this.sM = "";
        this.sU = "";
        this.sTarg = "_self";
    }
}
