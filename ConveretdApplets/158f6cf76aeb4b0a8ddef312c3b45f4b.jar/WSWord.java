import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class WSWord
{
    public String list;
    public String grid;
    public Point endpt1;
    public Point endpt2;
    public boolean found;
    
    public WSWord(final String s) {
        this.found = false;
        this.list = s.toUpperCase();
        String string = new String();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (Character.isLetter(char1)) {
                string += char1;
            }
        }
        this.grid = string.toUpperCase();
    }
}
