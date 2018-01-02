// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

public class Field
{
    int C;
    boolean Mark;
    TreeNode T;
    int Letter;
    String LabelLetter;
    boolean HaveLabel;
    int Territory;
    int Marker;
    static final int NONE = 0;
    static final int CROSS = 1;
    static final int SQUARE = 2;
    static final int TRIANGLE = 3;
    static final int CIRCLE = 4;
    int Number;
    
    public Field() {
        this.C = 0;
        this.T = null;
        this.Letter = 0;
        this.HaveLabel = false;
        this.Number = 0;
    }
    
    int color() {
        return this.C;
    }
    
    void color(final int c) {
        this.C = c;
        this.Number = 0;
    }
    
    static String string(final int n, final int n2) {
        return new String(new char[] { (char)(97 + n), (char)(97 + n2) });
    }
    
    static String coordinate(int n, final int n2, final int n3) {
        final char[] array = { '\0' };
        if (n >= 8) {
            ++n;
        }
        array[0] = (char)(65 + n);
        return String.valueOf(new String(array)) + (n3 - n2);
    }
    
    static int i(final String s) {
        if (s.length() < 2) {
            return -1;
        }
        return s.charAt(0) - 'a';
    }
    
    static int j(final String s) {
        if (s.length() < 2) {
            return -1;
        }
        return s.charAt(1) - 'a';
    }
    
    void mark(final boolean mark) {
        this.Mark = mark;
    }
    
    void tree(final TreeNode t) {
        this.T = t;
    }
    
    void marker(final int marker) {
        this.Marker = marker;
    }
    
    void letter(final int letter) {
        this.Letter = letter;
    }
    
    void territory(final int territory) {
        this.Territory = territory;
    }
    
    void setlabel(final String labelLetter) {
        this.HaveLabel = true;
        this.LabelLetter = labelLetter;
    }
    
    void clearlabel() {
        this.HaveLabel = false;
    }
    
    void number(final int number) {
        this.Number = number;
    }
    
    boolean mark() {
        return this.Mark;
    }
    
    int marker() {
        return this.Marker;
    }
    
    TreeNode tree() {
        return this.T;
    }
    
    int letter() {
        return this.Letter;
    }
    
    int territory() {
        return this.Territory;
    }
    
    boolean havelabel() {
        return this.HaveLabel;
    }
    
    String label() {
        return this.LabelLetter;
    }
    
    int number() {
        return this.Number;
    }
}
