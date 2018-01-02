// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.Observable;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JOptionPane;
import java.util.EventObject;
import java.awt.event.KeyEvent;
import javax.swing.ListCellRenderer;
import javax.swing.ComboBoxModel;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import java.util.Iterator;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.Container;
import java.util.Collections;
import java.util.Collection;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ComponentListener;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.HashSet;
import java.util.ArrayList;
import java.awt.Frame;
import com.photochannel.easyUploader.AppletParams;
import com.photochannel.easyUploader.d;
import javax.swing.ButtonGroup;
import java.io.FileFilter;
import javax.swing.ImageIcon;
import java.util.Set;
import java.util.List;
import java.util.Hashtable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.util.Observer;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class aT extends JDialog implements ActionListener, KeyListener, Observer, I
{
    private aq b;
    private JComboBox c;
    private f d;
    private JPanel e;
    private JPanel f;
    private JPanel g;
    private JTable h;
    private JPanel i;
    private JLabel j;
    private JPanel k;
    private JPanel l;
    private JScrollPane m;
    private JScrollPane n;
    private Hashtable o;
    private List p;
    private Set q;
    private Hashtable r;
    private ImageIcon s;
    private ImageIcon t;
    private final FileFilter u;
    private final w v;
    private boolean w;
    private boolean x;
    private ButtonGroup y;
    private int z;
    private int A;
    private boolean B;
    private boolean C;
    private String D;
    private bg E;
    private d F;
    AppletParams a;
    private aY G;
    private static /* synthetic */ boolean H;
    
    public aT(final bg e, final d f, final FileFilter u, final w v, final boolean w, final boolean x, final AppletParams a) {
        super((Frame)null, true);
        this.b = null;
        this.o = new Hashtable();
        this.p = new ArrayList();
        this.q = new HashSet();
        this.r = new Hashtable();
        this.z = -1;
        this.A = -1;
        this.B = false;
        this.C = true;
        this.G = new aY(this, this);
        this.setAlwaysOnTop(true);
        if (!aT.H && u == null) {
            throw new AssertionError();
        }
        if (!aT.H && v == null) {
            throw new AssertionError();
        }
        if (!aT.H && f == null) {
            throw new AssertionError();
        }
        this.F = f;
        this.w = w;
        this.x = x;
        this.setLayout(new BorderLayout());
        this.setSize(e.e("size"));
        this.setMinimumSize(e.e("size"));
        this.setTitle(z.G.c(e.c("title")));
        this.setBackground(e.d("backcolor"));
        this.add(this.f = this.f(e.f("sideNavigationBar")), "West");
        this.add(this.e = this.e(e.f("topBar")), "North");
        this.add(this.a(e), "Center");
        this.add(this.i = this.c(e.f("bottomBar")), "South");
        this.add(this.l = this.a(e), "Center");
        (this.g = new JPanel()).setBackground(e.d("scrollPaneBackColor"));
        final as layout = new as(e.f("row").b("columns"), e.b("horizGap"), e.b("vertGap"), e.f("row").b("height"));
        this.g.setLayout(layout);
        final JScrollPane m;
        (m = new JScrollPane(this.g)).setHorizontalScrollBarPolicy(31);
        m.getVerticalScrollBar().setUnitIncrement(10);
        m.getVerticalScrollBar().setBlockIncrement(100);
        m.setBorder(BorderFactory.createLoweredBevelBorder());
        m.getViewport().addComponentListener(new aM(this, m, layout));
        this.m = m;
        e.f("detailsTable");
        (this.h = new JTable()).setOpaque(true);
        this.h.setBackground(Color.magenta);
        this.n = new JScrollPane(this.h);
        this.a(aq.a);
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        this.setLocation(defaultToolkit.getScreenSize().width / 2 - this.getWidth() / 2, defaultToolkit.getScreenSize().height / 2 - this.getHeight() / 2);
        this.u = u;
        this.s = V.a(e.i("folderIcon"));
        if (e.a("pdfIcon")) {
            this.t = V.a(e.i("pdfIcon"));
        }
        (this.v = v).addObserver(this);
        this.E = e;
        this.a = a;
        this.addKeyListener(this);
        this.setFocusable(true);
        if (f()) {
            this.addComponentListener(new aP(this, this));
        }
    }
    
    private static boolean f() {
        return System.getProperty("os.name").startsWith("Mac");
    }
    
    public final boolean a() {
        return this.C;
    }
    
    public final boolean b() {
        return this.w;
    }
    
    public final Collection c() {
        synchronized (this.p) {
            Collections.sort((List<Comparable>)this.p);
            return this.p;
        }
    }
    
    public void dispose() {
        this.o.clear();
        super.dispose();
    }
    
    private JPanel a(final bg bg) {
        final JPanel panel;
        (panel = new JPanel()).setLayout(new BorderLayout());
        panel.setOpaque(false);
        panel.add(this.b(bg.f("scrollPaneControlBar")), "North");
        return panel;
    }
    
    private void a(final aq b) {
        if (b.equals(this.b)) {
            return;
        }
        switch (aR.a[b.ordinal()]) {
            case 1: {
                a(this.n, this.l);
                this.l.add(this.m, "Center");
                break;
            }
            case 2: {
                a(this.m, this.l);
                this.l.add(this.n, "Center");
                break;
            }
        }
        this.b = b;
        this.l.validate();
        this.l.repaint();
    }
    
    private static void a(final Component component, final Container container) {
        Component[] components;
        for (int length = (components = container.getComponents()).length, i = 0; i < length; ++i) {
            if (components[i] == component) {
                container.remove(component);
                return;
            }
        }
    }
    
    private JPanel b(final bg bg) {
        final JPanel panel;
        (panel = new JPanel()).setLayout(new BoxLayout(panel, 0));
        panel.setPreferredSize(new Dimension(1, bg.b("height")));
        panel.setOpaque(true);
        panel.setBackground(bg.d("backcolor"));
        final int b = bg.b("xpad");
        final int b2 = bg.b("ypad");
        panel.setBorder(BorderFactory.createEmptyBorder(b2, b, b2, b));
        final Iterator<bg> iterator = bg.a().iterator();
        while (iterator.hasNext()) {
            final bg bg2;
            if ((bg2 = iterator.next()).b().equals("button")) {
                final K b3;
                (b3 = al.b(bg2)).a(this);
                panel.add(b3);
            }
            else if (bg2.b().equals("icon")) {
                panel.add(al.d(bg2));
            }
            else if (bg2.b().equals("filler")) {
                panel.add(Box.createHorizontalGlue());
            }
            else if (bg2.b().equals("gap")) {
                panel.add(Box.createHorizontalStrut(bg2.b("size")));
            }
            else {
                if (!bg2.b().equals("buttonGroup")) {
                    throw new RuntimeException("Unexpected UI node: " + bg2.b());
                }
                this.a(bg2, panel);
            }
        }
        return panel;
    }
    
    private ButtonGroup a(final bg bg, final JPanel panel) {
        final ButtonGroup buttonGroup = new ButtonGroup();
        final Iterator<bg> iterator = bg.a().iterator();
        while (iterator.hasNext()) {
            final bg bg2;
            if (!(bg2 = iterator.next()).b().equals("radioButton")) {
                throw new RuntimeException("Unexpected UI node: " + bg2.b());
            }
            final JRadioButton radioButton;
            (radioButton = new JRadioButton()).setOpaque(false);
            String text;
            if ((text = bg2.c("caption")).charAt(0) == '$') {
                text = z.G.c(text.substring(1));
            }
            radioButton.setText(text);
            al.a(bg2.c("style"), radioButton);
            final String c = bg2.c("action");
            radioButton.setActionCommand(c);
            if (c.equals("ICON_VIEW")) {
                radioButton.setSelected(true);
            }
            buttonGroup.add(radioButton);
            panel.add(radioButton);
            radioButton.addActionListener(this);
            radioButton.addKeyListener(this);
        }
        return buttonGroup;
    }
    
    private JPanel c(bg bg) {
        final H h = new H();
        if (bg.a("bordercolor")) {
            h.setBorder(BorderFactory.createLineBorder(bg.d("bordercolor"), 1));
        }
        h.a(bg.k("isVertical"));
        h.a(bg.d("backcolor0"));
        h.b(bg.d("backcolor1"));
        h.setOpaque(false);
        h.setLayout(new BoxLayout(h, 1));
        final Iterator<bg> iterator = bg.a().iterator();
        while (iterator.hasNext()) {
            if ((bg = iterator.next()).b().equals("panel")) {
                final Component d;
                if ((d = this.d(bg)) == null) {
                    continue;
                }
                h.add(d);
            }
            else {
                if (!bg.b().equals("gap")) {
                    throw new RuntimeException("Unexpected UI node: " + bg.b());
                }
                h.add(Box.createVerticalStrut(bg.b("size")));
            }
        }
        return h;
    }
    
    private Component d(final bg bg) {
        final JPanel k = new JPanel();
        if (bg.a("backcolor")) {
            k.setOpaque(true);
            k.setBackground(bg.d("backcolor"));
        }
        else {
            k.setOpaque(false);
        }
        final String c;
        if ((c = bg.c("layout")).equals("box-X")) {
            k.setLayout(new BoxLayout(k, 0));
        }
        else if (c.equals("box-Y")) {
            k.setLayout(new BoxLayout(k, 1));
        }
        else {
            if (!c.equals("border")) {
                throw new RuntimeException("Unsupported layout: " + c);
            }
            k.setLayout(new BorderLayout());
        }
        final Iterator<bg> iterator = (Iterator<bg>)bg.a().iterator();
        while (iterator.hasNext()) {
            final bg bg2;
            if ((bg2 = iterator.next()).b().equals("label")) {
                if (this.x) {
                    continue;
                }
                final JLabel a = al.a(bg2);
                if (k.getLayout() instanceof BorderLayout) {
                    final String c2;
                    if (!(c2 = bg2.c("pos")).equals("center")) {
                        throw new RuntimeException("Unsupported layout position: " + c2);
                    }
                    k.add(a, "Center");
                }
                else {
                    k.add(a);
                }
            }
            else if (bg2.b().equals("button")) {
                if (this.x) {
                    continue;
                }
                final K b;
                (b = al.b(bg2)).a(this);
                if (k.getLayout() instanceof BorderLayout) {
                    final String c3;
                    if (!(c3 = bg2.c("pos")).equals("east")) {
                        throw new RuntimeException("Unsupported layout position: " + c3);
                    }
                    k.add(b, "East");
                }
                else {
                    k.add(b);
                }
            }
            else if (bg2.b().equals("gap")) {
                final JPanel panel = k;
                final String s = c;
                final int b2 = bg2.b("size");
                final String s2 = s;
                Component component;
                if (s.equals("box-X")) {
                    component = Box.createHorizontalStrut(b2);
                }
                else {
                    if (!s2.equals("box-Y")) {
                        throw new RuntimeException("Unexpected layout: " + s2);
                    }
                    component = Box.createVerticalStrut(b2);
                }
                panel.add(component);
            }
            else if (bg2.b().equals("radioButton")) {
                final JRadioButton radioButton;
                (radioButton = new JRadioButton()).setOpaque(false);
                String text;
                if ((text = bg2.c("caption")).charAt(0) == '$') {
                    text = z.G.c(text.substring(1));
                }
                radioButton.setText(text);
                al.a(bg2.c("style"), radioButton);
                final String c4 = bg2.c("action");
                radioButton.setActionCommand(c4);
                if (c4.equals("REDUCE_PHOTOS_ON") && this.w) {
                    radioButton.setSelected(true);
                }
                else if (c4.equals("REDUCE_PHOTOS_OFF") && !this.w) {
                    radioButton.setSelected(true);
                }
                if (this.y == null) {
                    this.y = new ButtonGroup();
                }
                this.y.add(radioButton);
                k.add(radioButton);
                radioButton.addActionListener(this);
                radioButton.addKeyListener(this);
            }
            else if (bg2.b().equals("filler")) {
                final JPanel panel2 = k;
                final String s3;
                Component component2;
                if ((s3 = c).equals("box-X")) {
                    component2 = Box.createHorizontalGlue();
                }
                else {
                    if (!s3.equals("box-Y")) {
                        throw new RuntimeException("Unexpected layout: " + s3);
                    }
                    component2 = Box.createVerticalGlue();
                }
                panel2.add(component2);
            }
            else if (bg2.b().equals("comboBox")) {
                if (this.x) {
                    continue;
                }
                final JComboBox e;
                (e = al.e(bg2)).addActionListener(this);
                e.addKeyListener(this);
                k.add(e);
                e.setSelectedIndex(this.w ? 0 : 1);
            }
            else if (bg2.b().equals("gbutton")) {
                final aC f;
                (f = al.f(bg2)).a(this);
                k.add(f);
            }
            else {
                if (!bg2.b().equals("panel")) {
                    throw new RuntimeException("Unexpected UI node: " + bg2.b());
                }
                k.add(this.d(bg2));
            }
        }
        if (!bg.a("id") || !bg.c("id").equals("helpPanel")) {
            return k;
        }
        k.setOpaque(true);
        k.setBackground(bg.d("backcolor"));
        final int b3 = bg.b("xpad");
        final int b4 = bg.b("ypad");
        k.setBorder(BorderFactory.createEmptyBorder(b4, b3, b4, b3));
        final int b5 = bg.b("expandedHeight");
        k.setPreferredSize(new Dimension(1, b5));
        k.setMinimumSize(new Dimension(1, b5));
        if (bg.a("isAlwaysOpen") && bg.k("isAlwaysOpen")) {
            return k;
        }
        this.k = k;
        return null;
    }
    
    private JPanel e(final bg bg) {
        final JPanel panel = new JPanel();
        if (bg.a("bordercolor")) {
            panel.setBorder(BorderFactory.createLineBorder(bg.d("bordercolor"), 1));
            panel.setOpaque(true);
        }
        panel.setBackground(bg.d("backcolor"));
        this.setBackground(bg.d("backcolor"));
        final JPanel panel2 = new JPanel();
        panel.add(panel2);
        panel2.setLayout(new BoxLayout(panel2, 0));
        panel2.setOpaque(false);
        final int b = bg.b("xPadding");
        final int b2 = bg.b("yPadding");
        panel2.setBorder(BorderFactory.createEmptyBorder(b2, b, b2, b));
        final Iterator iterator = bg.a().iterator();
        while (iterator.hasNext()) {
            final bg bg2;
            if ((bg2 = iterator.next()).b().equals("label")) {
                final JLabel a = al.a(bg2);
                if (bg2.c("caption").equals("#FILE_COUNT")) {
                    a.setPreferredSize(bg2.e("size"));
                    a.setHorizontalAlignment(11);
                    a.setText("0");
                    this.j = a;
                }
                panel2.add(a);
            }
            else if (bg2.b().equals("comboBox")) {
                if (!aT.H && this.d != null) {
                    throw new AssertionError();
                }
                this.d = new f();
                final f d = this.d;
                final bg f = bg.f("comboBox");
                final f model = d;
                final JComboBox c = new JComboBox<Object>();
                c.setPreferredSize(f.e("size"));
                c.setModel(model);
                c.setRenderer(new be(f));
                c.setMaximumRowCount(f.b("maxRows"));
                (this.c = c).addActionListener(this);
                this.c.addKeyListener(this);
                panel2.add(this.c);
            }
            else if (bg2.b().equals("button")) {
                final K b3;
                (b3 = al.b(bg2)).a(this);
                panel2.add(b3);
            }
            else {
                if (!bg2.b().equals("gap")) {
                    throw new RuntimeException("Unexpected config node type: " + bg.b() + "/" + bg2.b());
                }
                panel2.add(Box.createHorizontalStrut(bg2.b("size")));
            }
        }
        return panel;
    }
    
    private JPanel f(final bg bg) {
        final JPanel panel;
        (panel = new JPanel()).setPreferredSize(new Dimension(bg.b("width"), 1));
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBackground(bg.d("backcolor"));
        final Dimension maximumSize = new Dimension(bg.b("width"), bg.b("buttonHeight"));
        final String c = bg.c("buttonStyle");
        final Iterator<bg> iterator = bg.a().iterator();
        while (iterator.hasNext()) {
            final bg bg2;
            if ((bg2 = iterator.next()).b().equals("label")) {
                final JLabel a = al.a(bg2);
                final Dimension dimension = new Dimension(bg.b("width"), bg2.b("height"));
                a.setPreferredSize(dimension);
                a.setMinimumSize(dimension);
                a.setAlignmentX(0.5f);
                panel.add(a);
            }
            else {
                if (!bg2.b().equals("button")) {
                    throw new RuntimeException("Unexpected UI node: " + bg2.b());
                }
                if (bg2.a("macOnly") && !f()) {
                    continue;
                }
                final bd a2;
                (a2 = al.a(bg2, c)).setMinimumSize(maximumSize);
                a2.setPreferredSize(maximumSize);
                a2.setMaximumSize(maximumSize);
                a2.a(bg2.c("action"));
                a2.setAlignmentX(0.5f);
                a2.a(this);
                panel.add(a2);
            }
        }
        panel.add(Box.createVerticalGlue());
        return panel;
    }
    
    public final void d() {
        this.a(au.d());
    }
    
    public final void a(String a) {
        if (a == null) {
            return;
        }
        if ((a = this.d.a(a)).equals(this.D)) {
            return;
        }
        this.D = a;
        this.o.clear();
        this.g.removeAll();
        this.g.validate();
        this.g.repaint();
        this.d.b(a);
        final aT at = this;
        a = a;
        this = at;
        at.z = -1;
        this.A = -1;
        if (this.G.c) {
            this.G.a();
            synchronized (this.G) {
                try {
                    this.G.wait();
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        aY.a(this.G, false);
        this.G.a = a;
        this.G.b = this.d.c();
        this.G.c = true;
        new Thread(this.G).start();
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 16) {
            this.B = true;
            return;
        }
        if (keyEvent.getKeyCode() == 27) {
            this.i();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 16) {
            this.B = false;
            if (this.A >= 0) {
                final boolean b = !this.a(this.A).d();
                final int z = this.z;
                final int a = this.A;
                final boolean b2 = b;
                int n = a;
                int n2 = z;
                if (n < n2) {
                    final int n3 = n;
                    n = n2;
                    n2 = n3;
                }
                for (int i = n2; i <= n; ++i) {
                    this.a(i).a(b2);
                }
                this.h();
            }
        }
    }
    
    public final void a(final EventObject eventObject) {
        if (eventObject instanceof T) {
            final T t;
            if ((t = (T)eventObject).a.equals("MYPICS")) {
                (this = this).a(au.d());
            }
            else if (t.a.equals("MYDOCS")) {
                this.a(au.c());
            }
            else if (t.a.equals("COMPUTER")) {
                this.a(this.d.c());
            }
            else if (t.a.equals("DESKTOP")) {
                this.a(au.e());
            }
            else if (t.a.equals("MAC_VOLUMES")) {
                this.a(au.g());
            }
            else if (t.a.equals("UP")) {
                if (this.d.a() == null) {
                    return;
                }
                this.a(this.d.a().getParent());
            }
            else if (t.a.equals("SELECT_ALL")) {
                this = this;
                for (int i = 0; i < this.g.getComponentCount(); ++i) {
                    final Component component;
                    final z.d d;
                    if ((component = this.g.getComponent(i)) instanceof z.d && !(d = (z.d)component).g()) {
                        d.a(true);
                    }
                }
                if (this.e() != null) {
                    File[] listFiles;
                    for (int length = (listFiles = this.e().listFiles(this.u)).length, j = 0; j < length; ++j) {
                        final File file;
                        if (!(file = listFiles[j]).isDirectory() && !this.q.contains(file.getPath()) && !this.p.contains(file.getPath()) && (int)(file.length() / 1024L) <= this.a.b()) {
                            this.p.add(file.getPath());
                        }
                    }
                }
                this.g();
            }
            else if (t.a.equals("UNSELECT_ALL")) {
                this = this;
                for (int k = 0; k < this.g.getComponentCount(); ++k) {
                    final Component component2;
                    if ((component2 = this.g.getComponent(k)) instanceof z.d) {
                        ((z.d)component2).a(false);
                    }
                }
                synchronized (this.p) {
                    this.p.clear();
                }
                this.g();
            }
            else if (t.a.equals("CANCEL")) {
                this.i();
            }
            else if (t.a.equals("UPLOAD_PHOTOS")) {
                if (this.p.size() == 0) {
                    JOptionPane.showMessageDialog(this, z.G.c("SELECTION_REQUIRED_DIALOG_BLURB"), z.G.c("SELECTION_REQUIRED_DIALOG_TITLE"), 1);
                }
                else {
                    (this = this).C = false;
                    this.v.deleteObserver(this);
                    this.setVisible(false);
                }
            }
            else if (t.a.equals("GET_MORE_INFO")) {
                final int height = this.k.getPreferredSize().height;
                if (this.k.getParent() == null) {
                    this.i.add(this.k);
                    this.setSize(new Dimension(this.getWidth(), this.getHeight() + height));
                }
                else {
                    this.i.remove(this.k);
                    this.setSize(new Dimension(this.getWidth(), this.getHeight() - height));
                }
                this.i.invalidate();
                this.invalidate();
            }
            else {
                if (!t.a.equals("LAUNCH_HELP_BROWSER")) {
                    throw new RuntimeException("Unexpected action: " + t.a);
                }
                this.F.c();
            }
        }
        else {
            if (eventObject instanceof ac) {
                final z.d a = ((ac)eventObject).a();
                if (this.q.contains(a.b()) && a.d()) {
                    a.h();
                }
                synchronized (this.p) {
                    if (a.d()) {
                        this.p.add(a.b());
                        this.v.a(a.b(), a.d());
                    }
                    else {
                        this.p.remove(a.b());
                    }
                }
                final aT at;
                at.g();
                return;
            }
            if (eventObject instanceof c) {
                this.a((c)eventObject);
                return;
            }
            if (!(eventObject instanceof m)) {
                throw new RuntimeException("Unexpected event: " + eventObject);
            }
            final aT at2 = this;
            final m m = (m)eventObject;
            this = at2;
            final z.d a2;
            if (!(a2 = m.a()).a()) {
                this.a(new c(m.a()));
                return;
            }
            this.a(a2.b());
        }
    }
    
    private void g() {
        final int size;
        synchronized (this.p) {
            size = this.p.size();
        }
        final aT at;
        at.j.setText(Integer.toString(size));
    }
    
    private void a(final c c) {
        final z.d a = c.a();
        if (this.z < 0) {
            this.z = c.a().c();
            a.f();
            a.b(true);
            return;
        }
        if (this.B) {
            if (this.A < 0) {
                this.a(this.z, a.c(), true);
            }
            else if (this.A > this.z && a.c() > this.z) {
                if (a.c() > this.A) {
                    this.a(this.A + 1, a.c(), true);
                }
                else {
                    this.a(a.c() + 1, this.A, false);
                }
            }
            else if (this.A < this.z && a.c() < this.z) {
                if (a.c() < this.A) {
                    this.a(this.A - 1, a.c(), true);
                }
                else {
                    this.a(a.c() - 1, this.A, false);
                }
            }
            else {
                this.h();
                this.a(a.c(), this.z, true);
            }
            this.A = a.c();
            return;
        }
        this.a(this.z).b(false);
        this.z = a.c();
        a.f();
        a.b(true);
    }
    
    private void a(int n, int n2, final boolean b) {
        if (n2 < n) {
            final int n3 = n2;
            n2 = n;
            n = n3;
        }
        for (int i = n; i <= n2; ++i) {
            this.a(i).b(b);
        }
    }
    
    private void h() {
        if (this.A < this.z) {
            for (int i = this.A; i <= this.z; ++i) {
                this.a(i).b(false);
            }
        }
        else {
            for (int j = this.z; j <= this.A; ++j) {
                this.a(j).b(false);
            }
        }
        this.A = -1;
    }
    
    private z.d a(final int n) {
        if (!aT.H && n < 0) {
            throw new AssertionError();
        }
        if (!aT.H && n >= this.g.getComponentCount()) {
            throw new AssertionError();
        }
        return (z.d)this.g.getComponent(n);
    }
    
    private void i() {
        this.C = true;
        this.v.deleteObserver(this);
        this.setVisible(false);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.c) {
            String s;
            if ((s = ((at)this.c.getSelectedItem()).d).length() == 0) {
                s = this.d.c();
            }
            this.a(s);
            return;
        }
        if (!(actionEvent.getSource() instanceof JComboBox)) {
            if (actionEvent.getSource() instanceof AbstractButton) {
                final String actionCommand;
                if ((actionCommand = actionEvent.getActionCommand()).equals("ICON_VIEW")) {
                    this.a(aq.a);
                    return;
                }
                if (actionCommand.equals("LIST_VIEW")) {
                    this.a(aq.b);
                    return;
                }
                if (actionCommand.equals("REDUCE_PHOTOS_ON")) {
                    this.w = true;
                    return;
                }
                if (actionCommand.equals("REDUCE_PHOTOS_OFF")) {
                    this.w = false;
                }
            }
            return;
        }
        final JComboBox comboBox;
        if ((comboBox = (JComboBox)actionEvent.getSource()).getName().equals("REDUCE_PHOTOS_COMBO")) {
            final B b;
            if ((b = (B)comboBox.getSelectedItem()).a().equals("REDUCE_PHOTOS_ON")) {
                this.w = true;
            }
            else {
                if (!b.a().equals("REDUCE_PHOTOS_OFF")) {
                    throw new RuntimeException("Unhandled combo box event: " + b.a());
                }
                this.w = false;
            }
            return;
        }
        throw new RuntimeException("Unknown combo box event source: " + comboBox.getName());
    }
    
    public void update(Observable observable, Object o2) {
        if (o instanceof j) {
            final z.d d;
            if ((d = this.o.get(((j)o).a)) != null) {
                d.repaint();
            }
            return;
        }
        if (o instanceof bc) {
            observable = (Observable)o;
            o2 = 0;
            String toolTipText;
            if (((bc)observable).b instanceof ah || ((bc)observable).b instanceof M) {
                toolTipText = z.G.c("PREVIEW_UNAVAILABLE_TOOLTIP");
            }
            else if (((bc)observable).b instanceof am) {
                toolTipText = z.G.c("UNSUPPORTED_FORMAT_TOOLTIP");
                o2 = 1;
            }
            else {
                toolTipText = z.G.c("LOAD_PHOTO_IO_ERROR_TITLE");
                o2 = 1;
            }
            final z.d d2;
            if ((d2 = this.o.get(((bc)observable).a)) != null) {
                d2.setToolTipText(toolTipText);
                d2.repaint();
            }
            else {
                synchronized (this.r) {
                    this.r.put(((bc)observable).a, toolTipText);
                }
            }
            if (o2 != 0) {
                final aT at;
                synchronized (at.q) {
                    at.q.add(((bc)observable).a);
                }
                if (d2 != null) {
                    d2.h();
                }
            }
        }
    }
    
    public final File e() {
        if (this.D == null) {
            return null;
        }
        return new File(this.D);
    }
    
    static {
        aT.H = !aT.class.desiredAssertionStatus();
    }
}
