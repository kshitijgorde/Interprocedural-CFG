import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.Color;
import java.awt.Label;

// 
// Decompiled by Procyon v0.5.30
// 

public class h
{
    final int ak = 5;
    final int al = 11;
    private Label[] am;
    private int[] an;
    private int ao;
    private int ap;
    
    public h(final ChessBoard chessBoard, int n, final int n2, final int ap, final Color background) {
        this.am = new Label[11];
        this.an = new int[5];
        this.ap = ap;
        for (int i = 0; i < 11; ++i) {
            final Label label = new Label("", 1);
            label.setBackground(background);
            if (i % 2 == 0) {
                label.setFont(new Font("Dialog", 1, 12));
                if (i > 0) {
                    label.setBounds(n, n2, 12, 16);
                    n += 16;
                    label.addMouseListener(chessBoard);
                    label.setCursor(Cursor.getPredefinedCursor(12));
                }
                else {
                    label.setBounds(n, n2, 67, 16);
                    n += 71;
                    label.setForeground(Color.white);
                    label.setText("Variation at");
                }
            }
            else {
                label.setForeground(Color.black);
                label.setFont(new Font("Dialog", 1, 14));
                label.setBounds(n, n2, 8, 16);
                label.setText(">");
                n += 9;
            }
            label.setVisible(false);
            chessBoard.add(label, 0);
            this.am[i] = label;
        }
        this.ao = 0;
    }
    
    public void b(final Color background) {
        for (int i = 0; i < 11; ++i) {
            this.am[i].setBackground(background);
        }
    }
    
    public boolean c(final Component component) {
        if (this.ao == 0) {
            return false;
        }
        for (int i = 2 * this.ao; i > 0; i -= 2) {
            if (this.am[i] == component) {
                return true;
            }
        }
        return false;
    }
    
    public int d(final Component component) {
        for (int i = 2 * this.ao; i > 0; i -= 2) {
            if (this.am[i] == component) {
                return this.an[(i - 1) / 2];
            }
        }
        return -1;
    }
    
    public int T() {
        if (this.ao == 0) {
            return -1;
        }
        return this.an[0];
    }
    
    private void U() {
        for (int i = (this.ao > 0) ? (2 * this.ao + 1) : 0; i < 11; ++i) {
            this.am[i].setVisible(false);
        }
    }
    
    public void V() {
        this.ao = 0;
        this.U();
    }
    
    public void a(final e e, final int n) {
        final int g = e.g(n);
        while (this.ao > 0 && this.an[this.ao - 1] >= n) {
            --this.ao;
        }
        if (this.ao == 5) {
            return;
        }
        if (this.an[this.ao] == n && g == 0) {
            this.U();
            return;
        }
        if (g > 0) {
            final StringBuffer sb = new StringBuffer("");
            int n2 = 2 * this.ao + 1;
            if (this.ao == 0) {
                this.am[0].setVisible(true);
            }
            final Rectangle bounds = this.am[n2].getBounds();
            final Label label = this.am[++n2];
            final FontMetrics fontMetrics = label.getFontMetrics(label.getFont());
            e.b(sb, n);
            sb.append(' ');
            sb.append(e.c(n));
            if (bounds.x + fontMetrics.stringWidth(sb.toString()) > this.ap - 9) {
                return;
            }
            this.an[this.ao++] = n;
            this.am[n2 - 1].setVisible(true);
            label.setForeground(Color.white);
            label.setText(sb.toString());
            label.setBounds(bounds.x + 9, bounds.y, fontMetrics.stringWidth(sb.toString()) + 2, 16);
            label.setVisible(true);
            if (n2 < 9) {
                final Rectangle bounds2 = label.getBounds();
                this.am[n2 + 1].setBounds(bounds2.x + bounds2.width + 4, bounds2.y, 8, 16);
            }
            this.U();
        }
    }
}
