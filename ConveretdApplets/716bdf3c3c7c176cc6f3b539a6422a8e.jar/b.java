import java.awt.Event;
import java.net.URLConnection;
import java.io.IOException;
import java.util.Vector;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.List;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

final class b extends Frame
{
    private List e;
    private List f;
    private Button g;
    private Button h;
    private Button i;
    private Button j;
    private Button k;
    private Button l;
    private Button m;
    private Checkbox n;
    private Checkbox o;
    private Checkbox p;
    private Checkbox q;
    private Choice r;
    private Choice s;
    private Choice t;
    private final Choice u;
    private Label v;
    private Label w;
    private TextField x;
    private TextArea y;
    private a[] z;
    private a[] A;
    private a B;
    private personalclock2 C;
    GridBagLayout a;
    Panel b;
    a c;
    int d;
    
    b(final personalclock2 c, final a a) {
        this.g = new Button("Move Up");
        this.h = new Button("Move Down");
        this.i = new Button("Remove All");
        this.j = new Button("Add selected location(s)");
        this.k = new Button("Remove");
        this.l = new Button("Use these settings");
        this.m = new Button("Cancel - go back to old");
        this.n = new Checkbox("Show weekday?");
        this.o = new Checkbox("Show time zone abbreviation (EST, PDT, GMT...)?");
        this.p = new Checkbox("Show asterisk (*) for Daylight Saving Time?");
        this.q = new Checkbox("Show seconds for each city?");
        this.r = new Choice();
        this.s = new Choice();
        this.t = new Choice();
        this.u = new Choice();
        this.v = new Label("Select one city above");
        this.w = new Label("-");
        this.x = new TextField(30);
        this.y = new TextArea(4, 30);
        this.z = null;
        this.A = new a[50];
        this.B = null;
        this.C = null;
        this.c = new a();
        this.d = 0;
        this.C = c;
        this.setFont(a.a(12, false));
        this.r.add("Sort by country name");
        this.r.add("Sort by location name");
        this.s.add("Automatic");
        this.s.add("No columns - display as list");
        this.s.add("2 columns");
        this.s.add("3 columns");
        this.s.add("4 columns");
        this.s.add("5 columns");
        this.s.add("6 columns");
        this.s.add("7 columns");
        this.s.add("8 columns");
        this.t.add("Do not display country and state");
        this.t.add("Display only city and country name");
        this.t.add("Display city, country and state");
        this.u.add("Digital clock only");
        this.u.add("Analog clock only (if enough space)");
        this.u.add("Digital and Analog (if enough space)");
        this.e = new List(20, true);
        this.f = new List(20, true);
        final Panel panel;
        (panel = new Panel()).setLayout(new FlowLayout());
        panel.add(this.i);
        panel.add(this.k);
        panel.add(this.g);
        panel.add(this.h);
        final Panel panel2;
        (panel2 = new Panel()).setLayout(new BorderLayout());
        panel2.add("West", a("Available locations", 12));
        panel2.add("East", this.r);
        final Panel panel3;
        (panel3 = new Panel()).setLayout(new FlowLayout());
        panel3.add(this.l);
        panel3.add(this.m);
        this.b = new Panel();
        this.a = new GridBagLayout();
        this.b.setLayout(this.a);
        final Panel a2 = this.a();
        this.a(this.v, 0, 0, false);
        this.a(new Label("Display another name for this location:"), 0, 1);
        this.a(this.w, 0, 2);
        this.a(this.x, 0, 3);
        this.a(new Label("Notes for this location"), 0, 4);
        this.a(this.y, 0, 5);
        final Panel a3 = this.a();
        this.a(a("Columns:", 12), 0, 0);
        this.a(this.s, 1, 0, true);
        this.a(a("Country/state:", 12), 0, 1);
        this.a(this.t, 1, 1, true);
        this.a(a("Clock type:", 12), 0, 2);
        this.a(this.u, 1, 2, true);
        this.a();
        this.a(panel2, 0, 0, true);
        this.a(a("Selected locations (max 25)", 12), 1, 0);
        this.a(this.e, 0, 1, 1, 1.0);
        this.a(this.f, 1, 1, 1, 1.0);
        this.a(this.j, 0, 2, true);
        this.a(panel, 1, 2, true);
        this.a(a("Other options", 14), 0, 3);
        this.a(this.n, 0, 4);
        this.a(this.o, 0, 5);
        this.a(this.p, 0, 6);
        this.a(this.q, 0, 7);
        this.a(a3, 0, 8);
        if (a.d("loggedin") == 1) {
            this.a(a("Configure location", 14), 1, 3);
            this.a(a2, 1, 4, 0, 0.0, 1, 5);
        }
        this.a(panel3, 0, 9, 2, 0.0, 2, 1);
        this.add(this.b);
        new Panel().setLayout(new BorderLayout());
        this.setTitle("Personal World Clock Applet Setup");
        this.setBackground(new Color(240, 240, 240));
        this.pack();
        this.show();
    }
    
    private static Label a(final String s, final int n) {
        final Label label;
        (label = new Label(s)).setFont(a.a(n, true));
        return label;
    }
    
    private final Panel a() {
        this.b = new Panel();
        this.a = new GridBagLayout();
        this.b.setLayout(this.a);
        return this.b;
    }
    
    private final void a(final Component component, final int gridx, final int gridy, final int fill, final double n, final int gridwidth, final int gridheight) {
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).fill = fill;
        gridBagConstraints.weightx = n;
        if (fill == 1) {
            gridBagConstraints.weighty = n;
        }
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.insets = new Insets(0, 3, 0, 3);
        gridBagConstraints.anchor = 18;
        this.a.setConstraints(component, gridBagConstraints);
        this.b.add(component);
    }
    
    private final void a(final Component component, final int n, final int n2, final int n3, final double n4) {
        this.a(component, n, n2, n3, n4, 1, 1);
    }
    
    private final void a(final Component component, final int n, final int n2, final boolean b) {
        this.a(component, n, n2, b ? 2 : 0, 0.0);
    }
    
    private final void a(final Component component, final int n, final int n2) {
        this.a(component, n, n2, 0, 0.0);
    }
    
    public final Dimension getMinimumSize() {
        return new Dimension(400, 350);
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(750, 600);
    }
    
    final void a(final a a, final a[] array) {
        this.c = a.j();
        this.n.setState(!this.c.b("wct", 4));
        this.o.setState(this.c.b("wct", 8));
        this.p.setState(!this.c.b("wct", 16));
        this.q.setState(this.c.b("wct", 32));
        final int n;
        if ((n = (this.c.d("wct") & 0x3) - 1) >= 0) {
            this.u.select(n);
        }
        this.s.select(this.c.d("wco"));
        int d;
        if ((d = this.c.d("wcc")) == 1) {
            d = 2;
        }
        if (d == 3) {
            d = 1;
        }
        this.t.select(d);
        if (this.z == null) {
            this.e.add("Please wait - loading cities");
            URL url;
            try {
                url = new URL("http://www.timeanddate.com/worldclock/utc2.php?list=1");
            }
            catch (MalformedURLException ex) {
                return;
            }
            try {
                final URLConnection openConnection;
                (openConnection = url.openConnection()).setUseCaches(false);
                final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
                final Vector vector = new Vector<a>();
                String line;
                while ((line = dataInputStream.readLine()) != null) {
                    vector.addElement(new a(line));
                }
                final int size = vector.size();
                this.z = new a[size];
                for (int i = 0; i < size; ++i) {
                    this.z[i] = vector.elementAt(i);
                }
                dataInputStream.close();
            }
            catch (IOException ex2) {}
        }
        this.a(0);
        this.A = a.a(array);
        this.b();
        this.c();
    }
    
    private void a(final a[] array, final int n, final int n2) {
        int i = n;
        int n3 = n2;
        final String c = array[(n + n2) / 2].c();
        do {
            while (array[i].c().compareTo(c) < 0) {
                ++i;
            }
            while (array[n3].c().compareTo(c) > 0) {
                --n3;
            }
            if (i <= n3) {
                final a a = array[i];
                array[i] = array[n3];
                array[n3] = a;
                ++i;
                --n3;
            }
        } while (i <= n3);
        if (n < n3) {
            this.a(array, n, n3);
        }
        if (i < n2) {
            this.a(array, i, n2);
        }
    }
    
    private void a(final int d) {
        this.d = d;
        this.e.clear();
        final int length = this.z.length;
        for (int i = 0; i < length; ++i) {
            this.z[i].a(d);
        }
        this.a(this.z, 0, length - 1);
        this.e.show(false);
        for (int j = 0; j < length; ++j) {
            this.e.addItem(this.z[j].c());
        }
        this.e.show(true);
        this.b();
    }
    
    private void b() {
        this.f.clear();
        for (int i = 0; i < this.A.length; ++i) {
            if (this.A[i] != null) {
                this.A[i].a(this.d);
                this.f.add(this.A[i].b(), i);
            }
        }
    }
    
    private void c() {
        this.j.setEnabled(this.e.getSelectedIndexes().length > 0);
        final boolean enabled = this.f.getSelectedIndexes().length > 0;
        this.k.setEnabled(enabled);
        this.h.setEnabled(enabled);
        this.g.setEnabled(enabled);
        this.i.setEnabled(this.f.countItems() > 0);
    }
    
    private final int a(final a a) {
        final int a2 = a.a;
        for (int i = 0; i < this.A.length; ++i) {
            if (this.A[i] != null && this.A[i].a == a2) {
                return i;
            }
        }
        return -1;
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.hide();
        }
        else if (event.id == 1001) {
            this.d();
            if (this.r.equals(event.target)) {
                this.a(this.r.getSelectedIndex());
            }
            if (this.m.equals(event.target)) {
                this.hide();
            }
            if (this.l.equals(event.target)) {
                this.c.a("wco", this.s.getSelectedIndex());
                int selectedIndex;
                if ((selectedIndex = this.t.getSelectedIndex()) == 1) {
                    selectedIndex = 3;
                }
                if (selectedIndex == 2) {
                    selectedIndex = 1;
                }
                this.c.a("wcc", selectedIndex);
                this.c.a("wct", (this.n.getState() ? 0 : 4) + (this.o.getState() ? 8 : 0) + (this.p.getState() ? 0 : 16) + (this.q.getState() ? 32 : 0) + this.u.getSelectedIndex() + 1);
                this.C.a(this.c, this.A);
                this.hide();
            }
            if (this.i.equals(event.target)) {
                this.f.clear();
                for (int i = 0; i < this.A.length; ++i) {
                    this.A[i] = null;
                }
            }
            final int[] selectedIndexes;
            final int length;
            if (this.j.equals(event.target) && (length = (selectedIndexes = this.e.getSelectedIndexes()).length) > 0) {
                for (int j = 0; j < length; ++j) {
                    final a a = this.z[selectedIndexes[j]];
                    final int countItems;
                    if ((countItems = this.f.countItems()) < 25) {
                        this.e.deselect(selectedIndexes[j]);
                        if (this.a(a) < 0) {
                            this.f.addItem(a.c());
                            this.A[countItems] = (a)a.clone();
                        }
                    }
                }
            }
            final int[] selectedIndexes2;
            final int length2;
            if ((length2 = (selectedIndexes2 = this.f.getSelectedIndexes()).length) > 0) {
                if (this.k.equals(event.target)) {
                    for (int k = 0; k < length2; ++k) {
                        int l = selectedIndexes2[k] - k;
                        final int countItems2 = this.f.countItems();
                        this.f.delItem(l);
                        this.A[countItems2] = null;
                        while (l < countItems2) {
                            this.A[l] = this.A[l + 1];
                            ++l;
                        }
                    }
                }
                if (this.g.equals(event.target)) {
                    for (int n = 0; n < length2; ++n) {
                        final int n2;
                        if ((n2 = selectedIndexes2[n]) > n) {
                            this.a(n2, n2 - 1);
                        }
                    }
                }
                if (this.h.equals(event.target)) {
                    for (int n3 = length2 - 1; n3 >= 0; --n3) {
                        final int n4;
                        if ((n4 = selectedIndexes2[n3]) < this.f.countItems() - length2 + n3) {
                            this.a(n4, n4 + 1);
                        }
                    }
                }
            }
        }
        else if (event.id == 1005) {
            this.d();
            if (this.x.equals(event.target) || this.y.equals(event.target)) {
                this.B = null;
            }
        }
        if (this.f.equals(event.target)) {
            final int[] selectedIndexes3;
            final int length3 = (selectedIndexes3 = this.f.getSelectedIndexes()).length;
            if (event.id == 701 || event.id == 702) {
                this.d();
                if (length3 == 1) {
                    this.B = this.A[selectedIndexes3[0]];
                    if (this.B != null) {
                        this.x.enable();
                        this.x.setText(this.B.g());
                        this.y.enable();
                        this.y.setText(this.B.i());
                        this.w.setText("Default name is " + this.B.h());
                        this.b.validate();
                    }
                }
                else {
                    this.B = null;
                    this.x.disable();
                    this.x.setText("N/A");
                    this.y.disable();
                    this.y.setText("N/A");
                    this.w.setText("-");
                }
            }
        }
        this.c();
        return false;
    }
    
    private final void d() {
        if (this.B != null) {
            this.B.b(this.x.getText());
            this.B.c(this.y.getText());
            final int a;
            if ((a = this.a(this.B)) >= 0) {
                final boolean selected = this.f.isSelected(a);
                this.f.replaceItem(this.A[a].b(), a);
                if (selected) {
                    this.f.select(a);
                }
            }
        }
    }
    
    private final void a(final int n, final int n2) {
        final boolean selected = this.f.isSelected(n2);
        final String item = this.f.getItem(n2);
        this.f.replaceItem(this.f.getItem(n), n2);
        this.f.replaceItem(item, n);
        this.f.select(n2);
        if (selected) {
            this.f.select(n);
        }
        else {
            this.f.deselect(n);
        }
        final a a = this.A[n];
        this.A[n] = this.A[n2];
        this.A[n2] = a;
    }
}
