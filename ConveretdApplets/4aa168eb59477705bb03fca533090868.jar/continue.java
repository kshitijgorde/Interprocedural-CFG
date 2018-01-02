import java.awt.Cursor;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class continue extends MouseAdapter
{
    private URL _;
    final Ticker fb;
    private static String a = "\u45b9\u4584\u458a\u4587\u4588\u458d";
    
    public continue(final Ticker fb, final URL _) {
        this.fb = fb;
        this._ = _;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        ((Component)mouseEvent.getSource()).setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        ((Component)mouseEvent.getSource()).setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.fb.getAppletContext().showDocument(this._, continue.a);
    }
    
    private static String d(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF45E6);
        }
        return new String(array);
    }
    
    static {
        continue.a = d(continue.a);
    }
}
