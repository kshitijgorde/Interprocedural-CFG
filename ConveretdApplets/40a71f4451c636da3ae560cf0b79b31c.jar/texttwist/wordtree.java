// 
// Decompiled by Procyon v0.5.30
// 

package texttwist;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

class wordtree
{
    String \u00de;
    Vector \u00df;
    boolean \u00e0;
    
    public boolean testWord(final String s, final int n) {
        if (s.length() == n) {
            return this.\u00e0;
        }
        final int index = this.\u00de.indexOf(s.charAt(n));
        return index != -1 && ((wordtree)this.\u00df.elementAt(index)).testWord(s, n + 1);
    }
    
    public Vector getWordList(final String s) {
        final Vector vector = new Vector();
        this.getWordList("", s, vector);
        return vector;
    }
    
    private void getWordList(final String s, final String s2, final Vector vector) {
        final int length = s2.length();
        if (this.\u00e0) {
            vector.addElement(s);
        }
        for (int i = 0; i < length; ++i) {
            final char char1 = s2.charAt(i);
            if (s2.indexOf(char1) == i) {
                final wordtree \u00fe = this.\u00de(char1);
                if (\u00fe != null) {
                    \u00fe.getWordList(s + char1, s2.substring(0, i) + s2.substring(i + 1), vector);
                }
            }
        }
    }
    
    public wordtree() {
        this.\u00e0 = false;
        this.\u00df = new Vector();
        this.\u00de = "";
    }
    
    private wordtree \u00de(final char c) {
        final int index = this.\u00de.indexOf(c);
        if (index == -1) {
            return null;
        }
        return (wordtree)this.\u00df.elementAt(index);
    }
    
    private void get6LetterWords(final String s, final Vector vector) {
        if (this.\u00e0 && s.length() == 6) {
            vector.addElement(s);
        }
        for (int length = this.\u00de.length(), i = 0; i < length; ++i) {
            ((wordtree)this.\u00df.elementAt(i)).get6LetterWords(s + this.\u00de.charAt(i), vector);
        }
    }
    
    public Vector get6LetterWords() {
        final Vector vector = new Vector();
        this.get6LetterWords("", vector);
        return vector;
    }
    
    public int readNode(final InputStream inputStream) throws IOException {
        int n = 0;
        int n2 = 0;
        char c;
        do {
            final int read = inputStream.read();
            if (read == -1) {
                throw new IOException("end of file found");
            }
            c = (char)read;
            if (c == '1' || c == '0') {
                this.\u00e0 = (c == '1');
            }
            else {
                if (c == '2' || c == '3') {
                    final String \u00fe = this.\u00de;
                    this.\u00e0 = (c == '3');
                    this.\u00de = "";
                    this.\u00df(\u00fe);
                    return \u00fe.length() + 1;
                }
                if (c < 'a' || c > 'z') {
                    throw new IOException("illegal character " + c);
                }
                this.\u00de += c;
                ++n2;
            }
        } while (c != '1' && c != '0');
        if (n2 != this.\u00de.length()) {
            throw new IOException("error counting nodes");
        }
        for (int i = 0; i < n2; ++i) {
            final wordtree wordtree = new wordtree();
            n += wordtree.readNode(inputStream);
            this.\u00df.addElement(wordtree);
        }
        return n + 1;
    }
    
    void \u00df(final String s) {
        if (s.length() == 0) {
            this.\u00e0 = true;
            return;
        }
        final char char1 = s.charAt(0);
        int n = this.\u00de.indexOf(char1);
        if (n == -1) {
            n = this.\u00de.length();
            this.\u00de += char1;
            this.\u00df.addElement(new wordtree());
        }
        ((wordtree)this.\u00df.elementAt(n)).\u00df(s.substring(1));
    }
    
    void \u00df(final char c, wordtree wordtree) {
        this.\u00de += c;
        wordtree = new wordtree();
        this.\u00df.addElement(wordtree);
    }
    
    public boolean testWord(final String s) {
        return this.testWord(s, 0);
    }
}
