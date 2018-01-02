import java.awt.Cursor;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class null extends MouseAdapter
{
    private URL X;
    final Ticker ja;
    private static String V = "\u419d\u41a0\u41ae\u41a3\u41ac\u41a9";
    
    public null(final Ticker ja, final URL x) {
        this.ja = ja;
        this.X = x;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        ((Component)mouseEvent.getSource()).setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        ((Component)mouseEvent.getSource()).setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.ja.getAppletContext().showDocument(this.X, null.V);
    }
    
    private static String e(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x141C2);
        }
        return new String(array);
    }
    
    static {
        null.V = e(null.V);
    }
}
