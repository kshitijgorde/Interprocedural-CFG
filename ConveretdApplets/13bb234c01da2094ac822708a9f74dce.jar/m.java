import javax.swing.BorderFactory;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellRenderer;
import ca.odell.glazedlists.swing.TableComparatorChooser;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.matchers.MatcherEditor;
import java.util.Comparator;
import java.util.Collections;
import java.awt.Container;
import java.awt.event.TextEvent;
import java.awt.CheckboxMenuItem;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import com.daysofwonder.applet.g;
import java.awt.event.ActionEvent;
import com.daysofwonder.a.n;
import com.daysofwonder.applet.D;
import java.awt.Frame;
import com.daysofwonder.applet.aO;
import java.util.Enumeration;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import com.daysofwonder.applet.aL;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.util.StringTokenizer;
import com.daysofwonder.a.a;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentListener;
import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import com.daysofwonder.applet.filters.TableFilter;
import javax.swing.JMenuItem;
import javax.swing.Action;
import java.util.Iterator;
import ca.odell.glazedlists.GlazedLists;
import com.daysofwonder.req.x;
import com.daysofwonder.req.H;
import com.daysofwonder.util.t;
import java.util.List;
import java.awt.Component;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import ca.odell.glazedlists.BasicEventList;
import java.util.HashMap;
import ca.odell.glazedlists.FilterList;
import com.daysofwonder.a.d;
import java.util.LinkedHashMap;
import com.daysofwonder.applet.ar;
import javax.swing.JScrollPane;
import ca.odell.glazedlists.swing.EventTableModel;
import ca.odell.glazedlists.EventList;
import com.daysofwonder.util.K;
import java.util.Map;
import com.daysofwonder.applet.au;
import java.util.Vector;
import com.daysofwonder.applet.ag;
import com.daysofwonder.applet.R;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JPopupMenu;
import java.awt.Rectangle;
import java.awt.Color;
import com.daysofwonder.util.UIProperties;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import com.daysofwonder.applet.aG;
import java.awt.Font;
import java.awt.Dimension;
import java.util.Hashtable;
import java.awt.event.TextListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import com.daysofwonder.applet.p;
import com.daysofwonder.applet.e;
import com.daysofwonder.applet.an;
import javax.swing.JPanel;
import ca.odell.glazedlists.swing.EventSelectionModel;
import com.daysofwonder.applet.k;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class m implements ListSelectionListener
{
    final /* synthetic */ y a;
    
    public m(final y a) {
        this.a = a;
    }
    
    public void valueChanged(final ListSelectionEvent listSelectionEvent) {
        k k = null;
        final EventSelectionModel i = this.a.cr;
        if (i.a().size() > 0) {
            final Object value = i.a().get(0);
            if (value instanceof k) {
                k = (k)value;
            }
        }
        if (k != null && k != this.a.cs) {
            this.a.bk = 2;
            this.a.i.aj();
            this.a.cx.setVisible(false);
            this.a.cB.setVisible(false);
            this.a.aw.setEnabled(false);
            this.a.aw.setVisible(false);
            this.a.aJ.setEnabled(false);
            this.a.aJ.setVisible(false);
            this.a.aP.setVisible(false);
            this.a.aP.setEnabled(false);
            this.a.aR.setVisible(false);
            this.a.aR.setEnabled(false);
            this.a.aT.setVisible(false);
            this.a.aT.setEnabled(false);
            this.a.aV.setVisible(false);
            this.a.aV.setEnabled(false);
            this.a.aY.setVisible(false);
            this.a.aY.setEnabled(false);
            this.a.bb.setVisible(false);
            this.a.bb.setEnabled(false);
            this.a.aL.setVisible(false);
            this.a.aL.setEnabled(false);
            this.a.bh.setVisible(false);
            this.a.bh.setEnabled(false);
            this.a.bj.setVisible(false);
            this.a.bj.setEnabled(false);
            this.a.bf.setVisible(false);
            this.a.bf.setEnabled(false);
            this.a.bd.setVisible(false);
            this.a.bd.setEnabled(false);
            this.a.G = false;
            this.a.cs = k;
            if (!k.equals(this.a.cc.get(0))) {
                this.a.i.l(k.b());
                this.a.bE = k.a();
            }
            else {
                this.a.i.l(0);
                this.a.bE = this.a.i.ay();
            }
        }
        this.a.cs = k;
        this.a.repaint();
    }
}
