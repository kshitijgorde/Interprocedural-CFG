// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util.$XG;

import java.io.IOException;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.Enumeration;
import java.io.Reader;
import java.util.Hashtable;

public class $DJ
{
    StringBuffer $EJ;
    String $FJ;
    StringBuffer $GJ;
    StringBuffer $HJ;
    StringBuffer $IJ;
    StringBuffer $JJ;
    Hashtable $UF;
    int $KJ;
    int $LJ;
    Reader in;
    String $MJ;
    private final int $NJ = 1;
    private final int $OJ = 2;
    private final int $PJ = 3;
    private final int $QJ = 4;
    private int $RJ;
    
    public void $BK(final String s) {
        this.$MJ = String.valueOf(';') + s + ';';
    }
    
    public String $CK() {
        return this.$IJ.toString();
    }
    
    public String $DK() {
        return this.$JJ.toString();
    }
    
    public String $EK(final String s) {
        return this.$EK(s, null);
    }
    
    public int $EK(final String s, final int n) throws NumberFormatException {
        return Integer.parseInt(this.$EK(s, String.valueOf(n)));
    }
    
    public String $EK(final String s, final String s2) {
        final String s3 = this.$UF.get(s.toUpperCase());
        return (s3 == null) ? s2 : s3;
    }
    
    public void $GK(final String s, final String s2) {
        this.$UF.put(s.toUpperCase(), s2);
    }
    
    public Enumeration $HK() {
        return ((Hashtable)this.$UF.clone()).keys();
    }
    
    private boolean $XJ() {
        switch (this.$RJ) {
            case 2: {
                this.$FJ = this.$EJ.toString().toUpperCase();
                final String s = (this.$FJ.indexOf(47) == 0) ? this.$FJ.substring(1) : this.$FJ;
                if (s.length() != 0 && this.$MJ.indexOf(String.valueOf(';') + s + ';') == -1) {
                    this.$RJ = 3;
                    this.$GJ.setLength(0);
                    return true;
                }
                break;
            }
            case 3: {
                final String upperCase = this.$GJ.toString().toUpperCase();
                if (upperCase.length() != 0) {
                    this.$UF.put(upperCase, "");
                }
                this.$HJ.setLength(0);
                return true;
            }
            case 4: {
                final String upperCase2 = this.$GJ.toString().toUpperCase();
                final String string = this.$HJ.toString();
                if (upperCase2.length() != 0) {
                    this.$UF.put(upperCase2, string);
                }
                this.$RJ = 3;
                this.$GJ.setLength(0);
                this.$HJ.setLength(0);
                return true;
            }
        }
        this.$RJ = 1;
        this.$EJ.setLength(0);
        this.$FJ = null;
        return false;
    }
    
    public $AI $YJ() {
        return new $AI(this.$FJ, (Dictionary)this.$UF.clone());
    }
    
    public $DJ(final InputStream inputStream) {
        this.$EJ = new StringBuffer(256);
        this.$FJ = null;
        this.$GJ = new StringBuffer(256);
        this.$HJ = new StringBuffer(256);
        this.$IJ = new StringBuffer(256);
        this.$JJ = new StringBuffer(256);
        this.$UF = new Hashtable();
        this.$KJ = 0;
        this.$LJ = 32;
        this.in = null;
        this.$MJ = ";!--;";
        this.in = new BufferedReader(new InputStreamReader(inputStream));
    }
    
    public $DJ(final String s) {
        this.$EJ = new StringBuffer(256);
        this.$FJ = null;
        this.$GJ = new StringBuffer(256);
        this.$HJ = new StringBuffer(256);
        this.$IJ = new StringBuffer(256);
        this.$JJ = new StringBuffer(256);
        this.$UF = new Hashtable();
        this.$KJ = 0;
        this.$LJ = 32;
        this.in = null;
        this.$MJ = ";!--;";
        this.in = new StringReader(s);
    }
    
    public String getNextTag() throws IOException {
        int i = 32;
        final char c = (char)i;
        int $xj = 0;
        int n = 0;
        int n2 = 0;
        String $fj = null;
        this.$EJ.setLength(0);
        this.$FJ = null;
        this.$IJ.setLength(0);
        if (this.$KJ == 0) {
            this.$JJ.setLength(0);
        }
        this.$UF.clear();
        this.$RJ = 1;
        ++this.$KJ;
        Label_0343: {
            try {
                while (i > -1) {
                    char c2;
                    do {
                        i = this.nextChar();
                        c2 = (char)i;
                        if (i < 0) {
                            this.$RJ = -1;
                        }
                        if (c2 == '>' && this.$XJ()) {
                            $fj = this.$FJ;
                            break Label_0343;
                        }
                    } while (c2 == '>');
                    switch (this.$RJ) {
                        case 1: {
                            if (c2 == '<') {
                                this.$RJ = 2;
                                continue;
                            }
                            this.$IJ.append(c2);
                            continue;
                        }
                        case 2: {
                            if (c2 == ' ') {
                                this.$XJ();
                                continue;
                            }
                            this.$EJ.append(c2);
                            continue;
                        }
                        case 3: {
                            if (c2 == ' ') {
                                $xj = (this.$XJ() ? 1 : 0);
                                continue;
                            }
                            if (c2 == '=') {
                                this.$RJ = 4;
                                $xj = 0;
                                continue;
                            }
                            if ($xj != 0) {
                                this.$GJ.setLength(0);
                                $xj = 0;
                            }
                            this.$GJ.append(c2);
                            continue;
                        }
                        case 4: {
                            if (c2 == ' ' && n == 0 && n2 == 0) {
                                this.$XJ();
                                continue;
                            }
                            if (c2 == '\"') {
                                n ^= 0x1;
                                continue;
                            }
                            if (c2 == '\'') {
                                n2 ^= 0x1;
                                continue;
                            }
                            this.$HJ.append(c2);
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                }
            }
            finally {}
        }
        --this.$KJ;
        if (this.$KJ == 0 && $fj != null) {
            final int lastIndex = this.$JJ.toString().lastIndexOf(60);
            if (lastIndex > -1) {
                this.$JJ.setLength(lastIndex);
            }
        }
        return $fj;
    }
    
    public String getNextTag(String s) throws IOException {
        String nextTag = null;
        s = String.valueOf(';') + s + ';';
        s = s.toUpperCase();
        this.$JJ.setLength(0);
        ++this.$KJ;
        try {
            do {
                nextTag = this.getNextTag();
                if (nextTag != null) {
                    continue;
                }
                break;
            } while (s.indexOf((String.valueOf(';') + nextTag + ';').toUpperCase()) == -1);
        }
        finally {}
        --this.$KJ;
        if (this.$KJ == 0 && nextTag != null) {
            final int lastIndex = this.$JJ.toString().lastIndexOf(60);
            if (lastIndex > -1) {
                this.$JJ.setLength(lastIndex);
            }
        }
        return nextTag;
    }
    
    private int nextChar() throws IOException {
        boolean b;
        int read;
        do {
            read = this.in.read();
            if (read == -1) {
                return this.$LJ = -1;
            }
            if (this.$KJ != 0) {
                this.$JJ.append((char)read);
            }
            if (read < 32) {
                read = 32;
            }
            b = (read == this.$LJ && this.$LJ == 32);
            this.$LJ = read;
        } while (b);
        return read;
    }
    
    public String toString() {
        return String.valueOf(this.getClass().getName()) + "[tagname=" + (Object)this.$EJ + " properties=" + this.$UF + "]";
    }
}
