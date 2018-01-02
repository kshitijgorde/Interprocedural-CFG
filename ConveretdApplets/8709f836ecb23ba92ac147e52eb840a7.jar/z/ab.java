// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.EventObject;
import java.util.ArrayList;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumnModel;
import java.net.URL;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import javax.swing.Icon;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import com.photochannel.easyUploader.b;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ab extends JPanel implements I
{
    private aL a;
    private boolean b;
    private aj c;
    private JLabel d;
    private b e;
    private static /* synthetic */ boolean f;
    
    public ab(final b e, final Rectangle bounds, final aj c, final bg bg) {
        this.b = false;
        if (!ab.f && bg == null) {
            throw new AssertionError();
        }
        if (!ab.f && c == null) {
            throw new AssertionError();
        }
        this.e = e;
        (this.c = c).a(this);
        final Iterator<bg> iterator = (Iterator<bg>)bg.a().iterator();
        while (iterator.hasNext()) {
            final bg bg2;
            final String c2;
            if ((bg2 = iterator.next()).b().equals("column") && !(c2 = bg2.c("id")).equals("remove") && !c2.equals("zoom") && c2.equals("progress")) {
                ao.a(bg2, bg.c("style"));
            }
        }
        (this = this).setLayout(new BorderLayout());
        this.setOpaque(true);
        this.setBounds(bounds);
        if (bg.a("clickHereImage")) {
            final ab ab = this;
            final ab ab2 = this;
            final URL j = bg.j("clickHereImage");
            final int width = bounds.width;
            final int height = bounds.height;
            final int n = width;
            final URL url = j;
            final ab ab3 = ab2;
            final JLabel d;
            (d = new JLabel()).setIcon(V.a(url));
            d.setCursor(Cursor.getPredefinedCursor(12));
            final int iconWidth = d.getIcon().getIconWidth();
            final int iconHeight = d.getIcon().getIconHeight();
            d.setBounds(new Rectangle(n / 2 - iconWidth / 2, height / 2 - iconHeight / 2, iconWidth, iconHeight));
            d.addMouseListener(new aQ(ab3));
            ab.d = d;
            this.add(this.d);
            this.setComponentZOrder(this.d, 0);
        }
        (this.a = new aL(c, a(this.getWidth(), bg))).a(bg.d("backcolor"));
        this.a.b(bg.d("altRowBackColor"));
        this.a.setRowHeight(bg.b("rowHeight"));
        this.a.setShowVerticalLines(bg.k("showColumnDividingLines"));
        this.a.setFont(al.a(bg.c("style")));
        final JScrollPane scrollPane;
        (scrollPane = new JScrollPane(this.a)).setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(scrollPane, "Center");
        if (this.d != null) {
            this.setComponentZOrder(scrollPane, 1);
        }
    }
    
    public final void a() {
        if (!this.b) {
            this.c.a(this.a.a());
            this.b = true;
        }
    }
    
    public final void b() {
        if (this.d != null) {
            this.d.setVisible(false);
        }
    }
    
    private static TableColumnModel a(final int n, final bg bg) {
        if (!ab.f && n <= 0) {
            throw new AssertionError();
        }
        final DefaultTableColumnModel defaultTableColumnModel = new DefaultTableColumnModel();
        int n2 = 0;
        for (final bg bg2 : bg.a()) {
            final TableColumn tableColumn;
            (tableColumn = new TableColumn()).setIdentifier(bg2.c("title"));
            tableColumn.setModelIndex(n2++);
            tableColumn.setHeaderRenderer(null);
            tableColumn.setCellEditor(null);
            final String c;
            if ((c = bg2.c("width")).endsWith("%")) {
                tableColumn.setPreferredWidth((int)(Float.parseFloat(c.substring(0, c.length() - 1)) / 100.0f * n));
            }
            else {
                tableColumn.setPreferredWidth(Integer.parseInt(c));
            }
            final String c2;
            if (!(c2 = bg2.c("id")).equals("zoom") && !c2.equals("filename")) {
                if (c2.equals("filesize")) {
                    tableColumn.setCellRenderer(l.b);
                }
                else if (c2.equals("progress")) {
                    tableColumn.setCellRenderer(l.a);
                }
                else if (!c2.equals("remove")) {
                    throw new RuntimeException("Unexpected column ID: " + c2);
                }
            }
            defaultTableColumnModel.addColumn(tableColumn);
        }
        return defaultTableColumnModel;
    }
    
    public final ArrayList c() {
        if (!ab.f && this.a == null) {
            throw new AssertionError();
        }
        final ArrayList<v> list = new ArrayList<v>();
        int left = this.a.getInsets().left;
        for (int i = 0; i < this.a.getColumnCount(); ++i) {
            final TableColumn column = this.a.getColumnModel().getColumn(i);
            final v v;
            (v = new v()).a = (String)column.getIdentifier();
            v.b = left;
            left += column.getWidth();
            list.add(v);
        }
        return list;
    }
    
    public final void d() {
        this.a.b();
    }
    
    public final void a(final EventObject eventObject) {
        this.a.a(((J)eventObject).a);
    }
    
    static {
        ab.f = !ab.class.desiredAssertionStatus();
    }
}
