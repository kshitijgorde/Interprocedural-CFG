import java.awt.Cursor;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class q extends MouseAdapter
{
    private URL h;
    final Ticker x;
    private static String l = "\u84dd\u84e0\u84ee\u84e3\u84ec\u84e9";
    
    public q(final Ticker x, final URL h) {
        this.x = x;
        this.h = h;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        ((Component)mouseEvent.getSource()).setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        ((Component)mouseEvent.getSource()).setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.x.getAppletContext().showDocument(this.h, q.l);
    }
    
    private static String m(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u8482');
        }
        return new String(array);
    }
    
    static {
        q.l = m(q.l);
    }
}
