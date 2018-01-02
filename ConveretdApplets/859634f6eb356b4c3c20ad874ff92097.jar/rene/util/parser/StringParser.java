// 
// Decompiled by Procyon v0.5.30
// 

package rene.util.parser;

import java.util.Vector;

public class StringParser
{
    char[] C;
    int N;
    int L;
    boolean Error;
    
    public StringParser(final String s) {
        this.C = s.toCharArray();
        this.N = 0;
        this.L = this.C.length;
        this.Error = (this.N >= this.L);
    }
    
    public boolean blank() {
        return this.C[this.N] == ' ' || this.C[this.N] == '\t' || this.C[this.N] == '\n' || this.C[this.N] == '\r';
    }
    
    public boolean blank(final char c) {
        return this.C[this.N] == ' ' || this.C[this.N] == '\t' || this.C[this.N] == '\n' || this.C[this.N] == '\r' || this.C[this.N] == c;
    }
    
    public String upto(final char c) {
        if (this.Error) {
            return "";
        }
        int n;
        for (n = this.N; n < this.L && this.C[n] != c; ++n) {}
        if (n >= this.L) {
            this.Error = true;
        }
        final String s = new String(this.C, this.N, n - this.N);
        this.N = n;
        return s;
    }
    
    public boolean advance() {
        if (this.N < this.L) {
            ++this.N;
        }
        if (this.N >= this.L) {
            this.Error = true;
        }
        return !this.Error;
    }
    
    public String parseword() {
        if (this.Error) {
            return "";
        }
        while (this.blank()) {
            if (!this.advance()) {
                return "";
            }
        }
        final int n = this.N;
        while (!this.Error && !this.blank()) {
            this.advance();
        }
        return new String(this.C, n, this.N - n);
    }
    
    public String parsedigits(final char c) {
        if (this.Error) {
            return "";
        }
        while (this.blank()) {
            if (!this.advance()) {
                return "";
            }
        }
        final int n = this.N;
        while (!this.Error && !this.blank() && this.N <= this.L && this.C[this.N] >= '0' && this.C[this.N] <= '9' && this.C[this.N] != c) {
            this.advance();
        }
        return new String(this.C, n, this.N - n);
    }
    
    public String parsedigits() {
        if (this.Error) {
            return "";
        }
        while (this.blank()) {
            if (!this.advance()) {
                return "";
            }
        }
        final int n = this.N;
        while (!this.Error && !this.blank() && this.N <= this.L && this.C[this.N] >= '0' && this.C[this.N] <= '9') {
            this.advance();
        }
        return new String(this.C, n, this.N - n);
    }
    
    public String parseword(final char c) {
        if (this.Error) {
            return "";
        }
        while (this.blank()) {
            if (!this.advance()) {
                return "";
            }
        }
        final int n = this.N;
        while (!this.Error && !this.blank(c)) {
            this.advance();
        }
        return new String(this.C, n, this.N - n);
    }
    
    public boolean isint() {
        return !this.Error && this.C[this.N] >= '0' && this.C[this.N] <= '9';
    }
    
    public int parseint() {
        int n = 1;
        try {
            this.skipblanks();
            if (this.Error) {
                return 0;
            }
            if (this.C[this.N] == '-') {
                n = -1;
                ++this.N;
                if (this.N > this.L) {
                    this.Error = true;
                    return 0;
                }
            }
            return n * Integer.parseInt(this.parsedigits(), 10);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    public int parseint(final char c) {
        int n = 1;
        try {
            this.skipblanks();
            if (this.Error) {
                return 0;
            }
            if (this.C[this.N] == '-') {
                n = -1;
                ++this.N;
                if (this.N > this.L) {
                    this.Error = true;
                    return 0;
                }
            }
            return n * Integer.parseInt(this.parsedigits(c), 10);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    public void skipblanks() {
        if (this.Error) {
            return;
        }
        while (this.blank()) {
            if (!this.advance()) {
                return;
            }
        }
    }
    
    public boolean skip(final String s) {
        if (this.Error) {
            return false;
        }
        final int length = s.length();
        if (this.N + length > this.L) {
            return false;
        }
        if (!new String(this.C, this.N, length).equals(s)) {
            return false;
        }
        this.N += length;
        if (this.N >= this.L) {
            this.Error = true;
        }
        return true;
    }
    
    public char next() {
        if (this.Error) {
            return ' ';
        }
        ++this.N;
        if (this.N >= this.L) {
            this.Error = true;
        }
        return this.C[this.N - 1];
    }
    
    public String wrapline(final int n) {
        int i = this.N;
        int n2 = this.N;
        String s = "";
        while (i < this.L) {
            if (this.C[i] == '\n') {
                if (i > this.N) {
                    s = new String(this.C, this.N, i - this.N);
                }
                this.N = i + 1;
                break;
            }
            if (this.C[i] == ' ' || this.C[i] == '\t' || this.C[i] == '\n') {
                n2 = i;
            }
            if (++i - this.N >= n && n2 > this.N) {
                s = new String(this.C, this.N, n2 - this.N);
                this.N = n2 + 1;
                break;
            }
            if (i >= this.L) {
                if (i > this.N) {
                    s = new String(this.C, this.N, i - this.N);
                }
                this.N = i;
                break;
            }
        }
        if (this.N >= this.L) {
            this.Error = true;
        }
        return s;
    }
    
    public Vector wraplines(final int n) {
        final Vector<String> vector = new Vector<String>(10, 10);
        while (!this.Error) {
            vector.addElement(this.wrapline(n));
        }
        return vector;
    }
    
    public String wraplineword(final int n) {
        int i = this.N;
        final int n2 = this.N;
        String string = "";
        while (i < this.L) {
            if (this.C[i] == '\n') {
                string = new String(this.C, this.N, i - this.N);
                this.N = i + 1;
                break;
            }
            if (++i >= this.L) {
                if (i > this.N) {
                    string = new String(this.C, this.N, i - this.N);
                }
                this.N = i;
                break;
            }
            if (i - this.N < n || n2 <= this.N) {
                continue;
            }
            string = new String(this.C, this.N, n2 - this.N);
            this.N = n2 + 1;
            if (this.N < this.L && this.C[this.N] != '\n') {
                string = String.valueOf(string) + "\\";
                break;
            }
            break;
        }
        if (this.N >= this.L) {
            this.Error = true;
        }
        return string;
    }
    
    public Vector wrapwords(final int n) {
        final Vector<String> vector = new Vector<String>(10, 10);
        while (!this.Error) {
            vector.addElement(this.wraplineword(n));
        }
        return vector;
    }
    
    public void replace(final char c, final char c2) {
        for (int i = 0; i < this.L; ++i) {
            if (this.C[i] == c) {
                this.C[i] = c2;
            }
        }
    }
    
    public boolean error() {
        return this.Error;
    }
}
