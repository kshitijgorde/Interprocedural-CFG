// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.Frame;
import java.awt.Cursor;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.net.URLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Choice;
import java.awt.BorderLayout;
import java.awt.Button;
import java.applet.Applet;

public class Principale extends Applet implements Runnable
{
    private Button long;
    private Button int;
    private BorderLayout m;
    private BorderLayout j;
    private BorderLayout i;
    private BorderLayout h;
    private Choice new;
    Dimension e;
    boolean try;
    Image c;
    Image do;
    int f;
    private boolean o;
    private Label p;
    private Label else;
    private Label char;
    private Label case;
    private Label byte;
    private Label b;
    int d;
    private Panel void;
    private Panel null;
    private Panel goto;
    private Panel l;
    private Panel if;
    private Thread g;
    j for;
    URL k;
    String a;
    String n;
    
    public Principale() {
        this.o = false;
        this.try = false;
        this.m = new BorderLayout();
        this.l = new Panel();
        this.int = new Button();
        this.b = new Label();
        this.void = new Panel();
        this.p = new Label();
        this.null = new Panel();
        this.goto = new Panel();
        this.j = new BorderLayout();
        this.if = new Panel();
        this.i = new BorderLayout();
        this.h = new BorderLayout();
        this.else = new Label();
        this.char = new Label();
        this.case = new Label();
        this.long = new Button();
        this.new = new Choice();
        this.byte = new Label();
    }
    
    void if() {
        this.new.removeAll();
        this.new.addItem("New");
        final String string = this.a.substring(0, this.a.indexOf(63)) + "?type_aff=6";
        URL url;
        try {
            url = new URL(string);
        }
        catch (MalformedURLException ex) {
            System.out.println("Error: " + ex.toString());
            return;
        }
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            openConnection.setDoInput(true);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.equals("")) {
                    this.new.add(line);
                }
            }
            bufferedReader.close();
        }
        catch (IOException ex2) {
            System.out.println("Error: " + ex2.toString());
        }
    }
    
    void if(final ActionEvent actionEvent) {
        if (this.new.getSelectedIndex() == 0) {
            this.for.byte();
            this.for.repaint();
        }
        else {
            this.for.for(this.a.substring(0, this.a.indexOf(63)) + "?type_aff=7&numgraphique=" + this.new.getSelectedItem());
        }
    }
    
    void do(final ActionEvent actionEvent) {
        if (this.for.a() > 0) {
            try {
                this.int.setEnabled(false);
                final Thread thread = new Thread() {
                    public void run() {
                        Principale.this.for.a(true);
                        synchronized (this) {
                            try {
                                Thread.sleep(100L);
                            }
                            catch (InterruptedException ex) {}
                        }
                        try {
                            Thread.sleep(100L);
                        }
                        catch (InterruptedException ex2) {}
                    }
                };
                final Thread thread2 = new Thread() {
                    private final /* synthetic */ Thread val$inibation = thread;
                    
                    public void run() {
                        synchronized (this.val$inibation) {
                            try {
                                this.val$inibation.wait();
                            }
                            catch (InterruptedException ex) {}
                        }
                        int intValue = 0;
                        if (Principale.this.new.getSelectedIndex() != 0) {
                            intValue = Integer.valueOf(Principale.this.new.getSelectedItem());
                        }
                        Principale.this.for.if(intValue);
                    }
                };
                final Thread thread3 = new Thread() {
                    private final /* synthetic */ Thread val$EnvoieDonnees = thread2;
                    
                    public void run() {
                        synchronized (this.val$EnvoieDonnees) {
                            try {
                                this.val$EnvoieDonnees.wait();
                            }
                            catch (InterruptedException ex) {}
                        }
                        if (Principale.this.new.getSelectedIndex() == 0) {
                            try {
                                Principale.this.getAppletContext().showDocument(new URL(Principale.this.n), "_self");
                            }
                            catch (Exception ex2) {}
                        }
                        else {
                            Principale.this.for.a(false);
                            Principale.this.int.setEnabled(true);
                            Principale.this.for.repaint();
                            Principale.this.repaint();
                        }
                    }
                };
                thread.start();
                thread2.start();
                thread3.start();
            }
            catch (Exception ex) {}
        }
    }
    
    void a(final ActionEvent actionEvent) {
        this.g = null;
        System.exit(0);
    }
    
    void a(final ItemEvent itemEvent) {
    }
    
    public void destroy() {
    }
    
    public String getAppletInfo() {
        return "Information applet";
    }
    
    public String a(final String s, final String s2) {
        return this.o ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "param0", "String", "" }, { "param1", "String", "" }, { "param2", "String", "" }, { "param3", "String", "" } };
    }
    
    public void init() {
        try {
            this.a = this.a("param0", "");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this.n = this.a("param1", "");
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        try {
            this.a("param2", "");
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        try {
            this.a("param3", "");
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
        try {
            this.a();
        }
        catch (Exception ex5) {
            ex5.printStackTrace();
        }
    }
    
    private void a() throws Exception {
        this.setBackground(Color.white);
        this.setLayout(this.m);
        this.e = this.getSize();
        this.k = this.getCodeBase();
        this.do();
        this.a.indexOf("modcontdro=");
        final String s = "1";
        this.for = new j(this, false, this.a, this.n, s);
        final u u = new u(this, this.for, this.do, this.c);
        final int n = 83;
        final int n2 = 30;
        if (s.equals("2")) {
            final e e = new e(this, this.for);
            e.setSize(n, this.e.height);
            this.add(e, "East");
        }
        else if (s.equals("3")) {
            final d d = new d(this, this.for);
            d.setSize(n, this.e.height);
            this.add(d, "East");
        }
        else {
            final int n3 = 3;
            final h h = new h(this, this.for);
            h.setSize(n3, this.e.height);
            this.add(h, "East");
        }
        u.setSize(this.e.width, n2);
        this.for.setSize(this.e.width, this.e.height - 50);
        this.l.setBackground(Color.white);
        this.l.setLayout(this.j);
        this.int.setLabel("  Save  ");
        this.int.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                Principale.this.do(actionEvent);
            }
        });
        this.b.setFont(new Font("SansSerif", 0, 10));
        this.b.setForeground(Color.blue);
        this.b.setText(" Unregistred version");
        this.b.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                Principale.this.if(mouseEvent);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                Principale.this.a(mouseEvent);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Principale.this.do(mouseEvent);
            }
        });
        this.p.setFont(new Font("SansSerif", 0, 9));
        this.p.setText("                          ");
        this.null.setBackground(Color.white);
        this.void.setBackground(Color.white);
        this.goto.setBackground(Color.white);
        this.goto.setLayout(this.h);
        this.if.setLayout(this.i);
        this.else.setFont(new Font("Dialog", 0, 6));
        this.else.setText("     ");
        this.char.setText("  ");
        this.case.setText("   ");
        this.long.setLabel("Edit");
        this.long.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                Principale.this.if(actionEvent);
            }
        });
        this.byte.setText("Edit graphic n°=");
        this.new.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                Principale.this.a(itemEvent);
            }
        });
        this.add(u, "North");
        this.add(this.for, "Center");
        this.add(this.l, "South");
        this.l.add(this.null, "Center");
        this.null.add(this.byte, null);
        this.l.add(this.goto, "East");
        this.goto.add(this.int, "Center");
        this.goto.add(this.b, "South");
        this.goto.add(this.else, "North");
        this.goto.add(this.char, "West");
        this.goto.add(this.case, "East");
        this.l.add(this.void, "West");
        this.void.add(this.p, null);
        this.l.add(this.if, "North");
        this.null.add(this.new, null);
        this.null.add(this.long, null);
        this.if();
    }
    
    void if(final MouseEvent mouseEvent) {
        try {
            this.getAppletContext().showDocument(new URL(this.for.int()), "_blank");
        }
        catch (Exception ex) {}
    }
    
    void a(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(12));
    }
    
    void do(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public static void a(final String[] array) {
        final Principale principale = new Principale();
        principale.o = true;
        final Frame frame = new Frame() {
            protected void processWindowEvent(final WindowEvent windowEvent) {
                super.processWindowEvent(windowEvent);
                if (windowEvent.getID() == 201) {
                    System.exit(0);
                }
            }
            
            public synchronized void setTitle(final String title) {
                super.setTitle(title);
                this.enableEvents(64L);
            }
        };
        frame.setTitle("Cadre dessin");
        frame.add(principale, "Center");
        principale.init();
        principale.start();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - frame.getSize().width) / 2, (screenSize.height - frame.getSize().height) / 2);
        frame.setVisible(true);
    }
    
    void a(final int n, final int n2) {
        this.p.setText("x=" + n + "  y=" + n2 + "");
    }
    
    public void do() {
        this.do = this.getImage(this.k, "dessin/images/selection.gif");
        this.c = this.getImage(this.k, "dessin/images/delete.gif");
    }
    
    public void run() {
        while (this.g == Thread.currentThread()) {
            this.repaint();
            try {
                Thread.currentThread();
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        if (this.g == null) {
            (this.g = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.g = null;
    }
}
