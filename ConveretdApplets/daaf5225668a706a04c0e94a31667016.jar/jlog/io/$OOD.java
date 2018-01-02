// 
// Decompiled by Procyon v0.5.30
// 

package jlog.io;

import java.io.IOException;
import java.io.Reader;
import java.io.FilterReader;

public abstract class $OOD extends FilterReader
{
    String $ROD;
    String $SOD;
    Reader in;
    char[] $PPD;
    int $OQD;
    boolean $UQD;
    boolean $YQD;
    
    public abstract String $POD(final String p0);
    
    public void $PQD(final String $rod, final String $sod) {
        this.$ROD = $rod;
        this.$SOD = $sod;
    }
    
    boolean $SQD(final String s) throws IOException {
        final int length = s.length();
        int i = this.$OQD - length;
        if (i < 0 && this.$UQD) {
            return false;
        }
        while (i < 0) {
            ++i;
            final int read = this.in.read();
            if (read == -1) {
                this.$UQD = true;
                return false;
            }
            this.$WQD(read);
        }
        for (int j = 0; j < length; ++j) {
            if (s.charAt(j) != this.$PPD[j]) {
                return false;
            }
        }
        return true;
    }
    
    int $TQD() throws IOException {
        if (this.$OQD != 0) {
            final char c = this.$PPD[0];
            --this.$OQD;
            System.arraycopy(this.$PPD, 1, this.$PPD, 0, this.$OQD);
            return c;
        }
        if (this.$YQD) {
            return this.in.read();
        }
        this.$YQD = true;
        return -1;
    }
    
    void $WQD(final int n) {
        if (this.$PPD.length == this.$OQD) {
            final char[] $ppd = new char[this.$OQD * 2];
            System.arraycopy(this.$PPD, 0, $ppd, 0, this.$OQD);
            this.$PPD = $ppd;
        }
        this.$PPD[this.$OQD] = (char)n;
        ++this.$OQD;
    }
    
    public $OOD(final Reader in) {
        super(in);
        this.$ROD = "{";
        this.$SOD = "}";
        this.$PPD = new char[8];
        this.$OQD = 0;
        this.$UQD = false;
        this.$YQD = false;
        this.in = in;
    }
    
    public int read() throws IOException {
        if (this.$ROD == null || this.$ROD.length() == 0 || !this.$SQD(this.$ROD)) {
            return this.$TQD();
        }
        this.$OQD -= this.$ROD.length();
        System.arraycopy(this.$PPD, this.$ROD.length(), this.$PPD, 0, this.$OQD);
        final StringBuffer sb = new StringBuffer();
        while (!this.$SQD(this.$SOD)) {
            final int $tqd = this.$TQD();
            if ($tqd == -1) {
                return -1;
            }
            sb.append((char)$tqd);
        }
        final String $pod = this.$POD(sb.toString());
        this.$OQD = $pod.length();
        if (this.$PPD.length < $pod.length()) {
            this.$PPD = new char[this.$OQD];
        }
        $pod.getChars(0, $pod.length(), this.$PPD, 0);
        return this.$TQD();
    }
    
    public int read(final char[] array, final int n, final int n2) throws IOException {
        int read;
        int n3;
        for (read = 0, n3 = 0; n3 < n2 && read != -1; ++n3) {
            read = this.read();
            if (read == -1) {
                break;
            }
            array[n + n3] = (char)read;
        }
        if (n3 == 0 && read == -1) {
            return -1;
        }
        return n3;
    }
}
