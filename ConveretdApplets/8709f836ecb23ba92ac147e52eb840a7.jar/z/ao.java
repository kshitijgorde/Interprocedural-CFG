// 
// Decompiled by Procyon v0.5.30
// 

package z;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import java.awt.Font;
import java.util.Iterator;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.TableCellRenderer;

final class ao implements TableCellRenderer
{
    private static boolean a;
    private static JPanel b;
    private static x c;
    private static JLabel d;
    private static /* synthetic */ boolean e;
    
    public static void a(final bg bg, final String s) {
        if (ao.a) {
            return;
        }
        ao.b.setLayout(new BoxLayout(ao.b, 0));
        final Iterator<bg> iterator = (Iterator<bg>)bg.a().iterator();
        while (iterator.hasNext()) {
            final bg bg2;
            if ((bg2 = iterator.next()).b().equals("horizGap")) {
                ao.b.add(Box.createRigidArea(new Dimension(bg2.b("size"), 0)));
            }
            else if (bg2.b().equals("label")) {
                final String c;
                (ao.d = al.b(bg2.a("style") ? (c = bg2.c("style")) : s, bg2.c("caption"))).setPreferredSize(bg2.e("size"));
                ao.d.setMinimumSize(bg2.e("size"));
                ao.b.add(ao.d);
            }
            else {
                if (!bg2.b().equals("progressBar")) {
                    throw new RuntimeException("Unexpected element under listView/progressBar: " + bg2.b());
                }
                ao.c = al.c(bg2);
                ao.b.add(ao.c);
            }
        }
        ao.a = true;
    }
    
    public static void a(final Font font) {
        if (!ao.e && font == null) {
            throw new AssertionError();
        }
        ao.d.setFont(font);
    }
    
    public final Component getTableCellRendererComponent(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        if (o == null) {
            return l.c;
        }
        if (!ao.e && !(o instanceof Integer)) {
            throw new AssertionError();
        }
        if (!ao.e && !ao.a) {
            throw new AssertionError();
        }
        final int intValue;
        if ((intValue = (int)o) == 0) {
            return l.c;
        }
        ao.c.a(intValue);
        ao.c.a = (intValue == 0);
        ao.d.setText(String.format("%d%%", intValue));
        return ao.b;
    }
    
    static {
        ao.e = !l.class.desiredAssertionStatus();
        ao.a = false;
        ao.b = new JPanel();
        ao.d = new JLabel();
    }
}
