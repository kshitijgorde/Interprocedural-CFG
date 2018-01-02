import java.awt.event.MouseEvent;
import java.awt.Label;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class l extends MouseAdapter
{
    private Cursor a;
    private Cursor b;
    private Label c;
    private bf d;
    private String e;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.d.a(a("a86m\u0014\u0015") + this.e + "\n");
    }
    
    l(final Label c, final bf d, final String e) {
        this.a = new Cursor(12);
        this.b = new Cursor(0);
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.c.setCursor(this.a);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.c.setCursor(this.b);
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '5';
                    break;
                }
                case 1: {
                    c2 = 'w';
                    break;
                }
                case 2: {
                    c2 = 'f';
                    break;
                }
                case 3: {
                    c2 = '$';
                    break;
                }
                default: {
                    c2 = 'W';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
