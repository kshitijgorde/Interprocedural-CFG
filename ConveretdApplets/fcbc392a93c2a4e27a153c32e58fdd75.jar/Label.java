// 
// Decompiled by Procyon v0.5.30
// 

public class Label
{
    private int x;
    private int y;
    private StringBuffer text;
    
    public Label(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.text = new StringBuffer();
    }
    
    public void appendChar(final char c) {
        this.text.append(c);
    }
    
    public char[] getChars() {
        final int length = this.text.length();
        if (length == 0) {
            return null;
        }
        final char[] array = new char[length];
        this.text.getChars(0, length, array, 0);
        return array;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public String toString() {
        return this.text.toString();
    }
}
