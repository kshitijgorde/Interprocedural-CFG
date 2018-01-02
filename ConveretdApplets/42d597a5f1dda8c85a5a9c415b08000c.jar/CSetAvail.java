import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.InputStreamReader;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class CSetAvail
{
    String[] ms;
    int year;
    boolean bfile;
    boolean bevnt;
    boolean bschedule;
    URL base;
    int nmois;
    String[] evnt;
    
    void ReadFile() {
        int nmois = 0;
        this.bevnt = false;
        this.bfile = false;
        int n = -1;
        int n2 = -1;
        if (this.bschedule) {
            try {
                final StreamTokenizer streamTokenizer = new StreamTokenizer(new InputStreamReader(new URL(this.base, String.valueOf(this.year) + ".txt").openStream()));
                streamTokenizer.commentChar(59);
                streamTokenizer.quoteChar(34);
                streamTokenizer.ordinaryChar(48);
                streamTokenizer.ordinaryChar(49);
                streamTokenizer.eolIsSignificant(true);
                this.ms = new String[12];
                this.nmois = 0;
                this.ms[0] = new String("");
                while (streamTokenizer.ttype != -1 && nmois < 12) {
                    streamTokenizer.nextToken();
                    switch (streamTokenizer.ttype) {
                        case 10: {
                            if (++nmois < 12) {
                                this.ms[nmois] = new String("");
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                        case 48: {
                            this.ms[nmois] = String.valueOf(this.ms[nmois]) + '0';
                            continue;
                        }
                        case 49: {
                            this.ms[nmois] = String.valueOf(this.ms[nmois]) + '1';
                            continue;
                        }
                    }
                }
                this.bfile = true;
                if (nmois < 12 && this.ms[nmois].length() > 0) {
                    ++nmois;
                }
                this.nmois = nmois;
            }
            catch (Exception ex) {}
        }
        this.evnt = new String[372];
        try {
            final StreamTokenizer streamTokenizer2 = new StreamTokenizer(new InputStreamReader(new URL(this.base, String.valueOf(this.year) + ".evt").openStream()));
            streamTokenizer2.commentChar(59);
            streamTokenizer2.quoteChar(34);
            streamTokenizer2.eolIsSignificant(true);
            while (streamTokenizer2.ttype != -1) {
                streamTokenizer2.nextToken();
                switch (streamTokenizer2.ttype) {
                    case -2: {
                        if (n == -1) {
                            n = (int)streamTokenizer2.nval;
                            continue;
                        }
                        n2 = (int)streamTokenizer2.nval;
                        continue;
                    }
                    case -3:
                    case 34: {
                        if (n2 > 0 && n > 0) {
                            final String s = new String(streamTokenizer2.sval);
                            if (--n < 12 && --n2 < 31) {
                                this.evnt[n * 31 + n2] = s;
                            }
                            this.bevnt = true;
                            continue;
                        }
                        continue;
                    }
                    case 10: {
                        n = -1;
                        n2 = -1;
                    }
                    default: {
                        continue;
                    }
                }
            }
        }
        catch (Exception ex2) {}
    }
    
    String returnString(final int[] array, final int n, final int year, final int n2) {
        String s = "";
        int n3 = 0;
        if (year != this.year) {
            this.year = year;
            this.ReadFile();
        }
        for (int i = 0; i < n; ++i) {
            if (this.bfile && n2 < this.nmois && n3 < this.ms[n2].length() && array[i] > 0) {
                s = String.valueOf(s) + this.ms[n2].charAt(n3++);
            }
            else {
                s = String.valueOf(s) + "0";
            }
        }
        return s;
    }
    
    boolean isEvntThisDay(final int n, final int n2) {
        return this.bevnt && n2 > 0 && this.evnt[n * 31 + n2 - 1] != null;
    }
    
    String evntToDay(final int n, final int n2) {
        if (this.bevnt && this.evnt[n * 31 + n2 - 1] != null) {
            return this.evnt[n * 31 + n2 - 1];
        }
        return null;
    }
    
    CSetAvail() {
        this.year = -1;
        this.bfile = false;
        this.bevnt = false;
        this.bschedule = false;
    }
}
