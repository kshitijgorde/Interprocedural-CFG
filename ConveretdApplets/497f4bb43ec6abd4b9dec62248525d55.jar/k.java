import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Canvas;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

public class k extends Container
{
    private int au;
    private int av;
    private int aq;
    private int as;
    private int ar;
    private int at;
    private int C;
    private int D;
    private boolean bb;
    Canvas bN;
    Canvas bO;
    j bP;
    a bQ;
    
    public k(final int au, final int av, final int aq, final int as, final int ar, final int at) {
        this.au = au;
        this.av = av;
        this.aq = aq;
        this.as = as;
        this.ar = ar;
        this.at = at;
        this.setLayout(null);
        (this.bN = new Canvas()).setBounds(aq - 2, 0, au - aq - ar + 4, as - 2);
        this.add(this.bN);
        (this.bO = new Canvas()).setBounds(au - ar + 2, 0, ar - 2, av - at + 2);
        this.add(this.bO);
        (this.bP = new j()).setBounds(0, av - at + 2, au, at - 2);
        this.bP.setCursor(Cursor.getPredefinedCursor(12));
        this.add(this.bP);
        (this.bQ = new a()).setBounds(0, 0, aq - 2, av - at + 2);
        this.bQ.setCursor(Cursor.getPredefinedCursor(12));
        this.add(this.bQ);
        this.setBounds(0, 0, au, av);
    }
    
    public void a(final int c, final int d, final Color color, final Color color2) {
        this.C = c;
        this.D = d;
        this.bN.setBackground(color2);
        this.bO.setBackground(color2);
        this.bP.setForeground(color);
        this.bP.setBackground(color2);
        this.bP.setFont(new Font("Dialog", 1, 12));
        this.bQ.setForeground(color);
        this.bQ.setBackground(color2);
        this.bQ.setFont(new Font("Dialog", 1, 12));
        this.bb = true;
    }
    
    public void a(final ChessBoard chessBoard) {
        this.bN.addMouseListener(chessBoard);
        this.bO.addMouseListener(chessBoard);
        this.bP.addMouseListener(chessBoard);
        this.bQ.addMouseListener(chessBoard);
    }
    
    public void g(final boolean bb) {
        if (this.bb == bb) {
            return;
        }
        this.bb = bb;
        this.bP.repaint();
        this.bQ.repaint();
    }
    
    public int ab() {
        return this.aq;
    }
    
    public int ac() {
        return this.as;
    }
    
    public int ad() {
        return this.C;
    }
    
    public int ae() {
        return this.D;
    }
    
    public boolean af() {
        return this.bb;
    }
    
    public boolean e(final Component component) {
        return component == this.bP || component == this.bQ;
    }
    
    public boolean a(final Rectangle rectangle) {
        final Rectangle bounds = this.bN.getBounds();
        if (bounds.contains(rectangle.x, rectangle.y) && bounds.contains(rectangle.x + rectangle.width - 1, rectangle.y + rectangle.height - 1)) {
            return true;
        }
        final Rectangle bounds2 = this.bO.getBounds();
        if (bounds2.contains(rectangle.x, rectangle.y) && bounds2.contains(rectangle.x + rectangle.width - 1, rectangle.y + rectangle.height - 1)) {
            return true;
        }
        final Rectangle bounds3 = this.bP.getBounds();
        if (bounds3.contains(rectangle.x, rectangle.y) && bounds3.contains(rectangle.x + rectangle.width - 1, rectangle.y + rectangle.height - 1)) {
            return true;
        }
        final Rectangle bounds4 = this.bQ.getBounds();
        return bounds4.contains(rectangle.x, rectangle.y) && bounds4.contains(rectangle.x + rectangle.width - 1, rectangle.y + rectangle.height - 1);
    }
}
