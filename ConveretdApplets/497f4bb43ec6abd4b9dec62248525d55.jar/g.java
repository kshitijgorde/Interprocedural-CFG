import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.Label;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class g
{
    private final Object[][] ai;
    private Object[] aj;
    
    g() {
        this.ai = new Object[][] { { null, new Color(15981762), new Color(12944962), new Color(10144128) }, { null, new Color(12447215), new Color(96693), new Color(10860239) }, { null, new Color(15981762), new Color(12616290), new Color(13421704) } };
    }
    
    private void a(final int n, final String s) {
        if (s == null) {
            return;
        }
        try {
            this.aj[n] = new Color(Integer.parseInt(s, 16));
        }
        catch (Exception ex) {}
    }
    
    public void a(final String s, final String s2, final String s3) {
        this.aj = this.ai[0];
        this.a(1, s);
        this.a(2, s2);
        this.a(3, s3);
    }
    
    public void a(final ChessBoard chessBoard, int n, final int n2, final int n3, final int n4) {
        for (int i = 0; i < 3; ++i) {
            final Label label = new Label();
            label.setBackground((Color)this.ai[i][2]);
            label.setBounds(n, n2, n3, n4);
            label.addMouseListener(chessBoard);
            label.setCursor(Cursor.getPredefinedCursor(12));
            chessBoard.add(label);
            this.ai[i][0] = label;
            n += n3 + n3;
        }
    }
    
    public Color Q() {
        return (Color)this.aj[1];
    }
    
    public Color R() {
        return (Color)this.aj[2];
    }
    
    public Color S() {
        return (Color)this.aj[3];
    }
    
    public boolean a(final Component component) {
        for (int i = 0; i < 3; ++i) {
            if (this.ai[i][0] == component) {
                return true;
            }
        }
        return false;
    }
    
    public void b(final Component component) {
        int n;
        for (n = 0; n < 3 && this.ai[n][0] != component; ++n) {}
        this.aj = this.ai[n];
    }
}
