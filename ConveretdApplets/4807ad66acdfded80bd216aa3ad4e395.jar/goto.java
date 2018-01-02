import java.awt.Cursor;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class goto extends MouseAdapter
{
    private URL ka;
    final Ticker xa;
    private static String ja = "\u6689\u66b4\u66ba\u66b7\u66b8\u66bd";
    
    public goto(final Ticker xa, final URL ka) {
        this.xa = xa;
        this.ka = ka;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        ((Component)mouseEvent.getSource()).setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        ((Component)mouseEvent.getSource()).setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.xa.getAppletContext().showDocument(this.ka, goto.ja);
    }
    
    private static String l(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF66D6);
        }
        return new String(array);
    }
    
    static {
        goto.ja = l(goto.ja);
    }
}
