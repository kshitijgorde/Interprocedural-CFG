import javax.swing.BorderFactory;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellRenderer;
import ca.odell.glazedlists.swing.TableComparatorChooser;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;
import javax.swing.event.ListSelectionListener;
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
import com.daysofwonder.applet.k;
import com.daysofwonder.applet.ar;
import javax.swing.JScrollPane;
import ca.odell.glazedlists.swing.EventSelectionModel;
import ca.odell.glazedlists.swing.EventTableModel;
import ca.odell.glazedlists.EventList;
import com.daysofwonder.util.K;
import java.util.Map;
import java.util.Vector;
import com.daysofwonder.applet.m;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class au implements DocumentListener
{
    final /* synthetic */ y a;
    
    public au(final y a) {
        this.a = a;
    }
    
    private void a() {
        this.a.repaint(this.a.U.a.x, this.a.U.a.y, this.a.U.a.width, this.a.U.a.height);
    }
    
    public void changedUpdate(final DocumentEvent documentEvent) {
        this.a();
    }
    
    public void insertUpdate(final DocumentEvent documentEvent) {
        this.a();
    }
    
    public void removeUpdate(final DocumentEvent documentEvent) {
        this.a();
    }
}