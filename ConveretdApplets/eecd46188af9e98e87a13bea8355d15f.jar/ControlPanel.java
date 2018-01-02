import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import ru.zhuk.gui.b;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.awt.Component;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Cursor;
import java.util.Hashtable;
import java.awt.Image;
import ru.zhuk.gui.f;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ControlPanel extends Applet implements ActionListener, MouseListener
{
    private f n;
    private int i;
    private Image f;
    private String g;
    private String a;
    private f h;
    private Hashtable d;
    final int l = 0;
    final int o = 1;
    final int b = 2;
    final int k = 3;
    final int c = 4;
    static final String[] j;
    private static final Cursor m;
    private static final Cursor e;
    
    public ControlPanel() {
        this.d = new Hashtable();
    }
    
    public void init() {
        final String parameter = this.getParameter("rows");
        final int n = (parameter != null) ? Integer.parseInt(parameter) : 1;
        final String parameter2 = this.getParameter("columns");
        final int n2 = (parameter2 != null) ? Integer.parseInt(parameter2) : 1;
        this.setLayout(new GridLayout(n, n2, 1, 1));
        final String parameter3 = this.getParameter("bgcolor");
        this.setBackground(new Color((parameter3 != null) ? Integer.decode(parameter3) : 12632256));
        String s = "";
        for (int i = 0; i < 5; ++i) {
            final String parameter4;
            if ((parameter4 = this.getParameter(ControlPanel.j[i])) != null) {
                s = parameter4;
                this.i = i;
                break;
            }
        }
        final boolean b = this.i == 0 || this.i == 1 || this.i == 4;
        final String parameter5 = this.getParameter("default");
        String s2 = this.getParameter("icondir");
        this.getSize();
        final String parameter6 = this.getParameter("hints");
        final StringTokenizer stringTokenizer = (parameter6 != null) ? new StringTokenizer(parameter6, ",") : null;
        final String parameter7 = this.getParameter("tile");
        this.g = ((parameter7 != null) ? parameter7 : "");
        final String parameter8 = this.getParameter("colorable");
        this.a = ((parameter8 != null) ? parameter8 : "");
        final StringTokenizer stringTokenizer2 = new StringTokenizer(s, ",");
        for (int n3 = 0; n3 < n * n2 && stringTokenizer2.hasMoreTokens(); ++n3) {
            final String trim = stringTokenizer2.nextToken().trim();
            try {
                final int n4 = (this.i != 3) ? Integer.decode(trim) : -1;
                Image image = null;
                if (this.i != 1 || s2 != null) {
                    if (s2 == null) {
                        s2 = ((this.i != 3) ? "icons" : "");
                    }
                    if (s2.charAt(s2.length() - 1) != '/') {
                        s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf('/')));
                    }
                    image = this.getImage(new URL(this.getCodeBase(), s2), String.valueOf(String.valueOf(trim)).concat(String.valueOf(String.valueOf((this.i != 3) ? ".gif" : ""))));
                }
                Color background = (this.i == 1) ? new Color(n4) : Color.lightGray;
                final boolean equals = "false".equals(this.getParameter("outline"));
                if (equals) {
                    background = this.getBackground();
                }
                final f f = new f(background, image, b ? ru.zhuk.gui.f.h : ru.zhuk.gui.f.a);
                if (equals) {
                    f.a(true);
                }
                f.a(trim);
                f.a(this);
                f.setCursor(ControlPanel.m);
                this.add(f);
                if (stringTokenizer != null && stringTokenizer.hasMoreTokens()) {
                    this.d.put(trim, stringTokenizer.nextToken());
                    f.addMouseListener(this);
                }
                if (b) {
                    if (parameter5 != null) {
                        if (trim.equalsIgnoreCase(parameter5)) {
                            f.b(true);
                        }
                    }
                    else if (n3 == 0) {
                        f.b(true);
                    }
                }
            }
            catch (MalformedURLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        final f n = (f)actionEvent.getSource();
        final int n2 = (this.i != 3) ? Integer.decode(actionCommand) : -1;
        final Image d = n.d();
        if (n.a() == ru.zhuk.gui.f.h) {
            if (this.n != null && this.n != n) {
                this.n.b(false);
            }
            this.n = n;
        }
        DrawCanvas d2;
        b a;
        try {
            int n3;
            for (n3 = 300; (d2 = DrawCanvas.d()) == null && n3 > 0; --n3) {
                Thread.sleep(100L);
            }
            if (n3 <= 0) {
                if (this.n != null) {
                    this.n.b(false);
                    this.n = null;
                }
                return;
            }
            a = d2.a;
        }
        catch (InterruptedException ex) {
            if (this.n != null) {
                this.n.b(false);
                this.n = null;
            }
            this.n = null;
            return;
        }
        Label_0523: {
            switch (this.i) {
                case 1: {
                    a.a(new Color(n2));
                    break;
                }
                case 0: {
                    a.d(n2);
                    break;
                }
                case 4: {
                    a.a(n2 - 200);
                    break;
                }
                case 3: {
                    if (d != null) {
                        final int n4 = (this.g.indexOf(actionCommand) >= 0) ? 1 : 0;
                        final boolean b = this.a.indexOf(actionCommand) >= 0;
                        int int1 = Integer.MAX_VALUE;
                        int int2 = Integer.MAX_VALUE;
                        final String parameter = this.getParameter("insertat");
                        if (parameter != null) {
                            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
                            try {
                                int1 = Integer.parseInt(stringTokenizer.nextToken().trim());
                                int2 = Integer.parseInt(stringTokenizer.nextToken().trim());
                            }
                            catch (Exception ex2) {}
                        }
                        a.a(d, int1, int2, n4, b);
                        break;
                    }
                    break;
                }
                case 2: {
                    switch (n2) {
                        case 101: {
                            a.c();
                            break Label_0523;
                        }
                        case 102: {
                            a.h();
                            break Label_0523;
                        }
                        case 103: {
                            a.b();
                            break Label_0523;
                        }
                        case 104: {
                            final String parameter2 = this.getParameter("method");
                            final String parameter3 = this.getParameter("progresstext");
                            final String parameter4 = this.getParameter("progresscolor");
                            if (parameter4 != null) {
                                try {
                                    a.b(Color.decode(parameter4));
                                }
                                catch (NumberFormatException ex3) {}
                            }
                            int n5 = 0;
                            if (parameter2 != null && (parameter2.equalsIgnoreCase("BASE64") || parameter2.equalsIgnoreCase("POST"))) {
                                n5 = 1;
                            }
                            d2.a(this.getParameter("save"), n5, n, parameter3);
                            break Label_0523;
                        }
                    }
                    break;
                }
            }
        }
        a.requestFocus();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.h = (f)mouseEvent.getSource();
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.h = null;
        this.f = null;
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.f == null) {
            this.f = this.createImage(this.getSize().width, this.getSize().height);
        }
        final Graphics graphics2 = this.f.getGraphics();
        graphics2.setClip(0, 0, this.getSize().width, this.getSize().height);
        super.paint(graphics2);
        if (this.h != null) {
            final String s = this.d.get(this.h.b());
            if (s != null) {
                final int n = graphics2.getFontMetrics().stringWidth(s) + 2;
                final int n2 = graphics2.getFont().getSize() + 3;
                int n3 = this.h.getLocation().x + this.h.getSize().width;
                if (n3 + n >= this.getSize().width) {
                    n3 -= this.h.getSize().width + n + 1;
                }
                if (n3 + n >= this.getSize().width) {
                    n3 = this.getSize().width - n - 1;
                }
                if (n3 < 0) {
                    n3 = 0;
                }
                final int n4 = this.h.getLocation().y + this.h.getSize().height - n2 - 3;
                graphics2.setColor(new Color(255, 255, 192));
                graphics2.fillRect(n3, n4, n, n2);
                graphics2.setColor(Color.black);
                graphics2.drawRect(n3, n4, n, n2);
                graphics2.drawString(s, n3 + 1, n4 + n2 - 3);
            }
        }
        graphics.drawImage(this.f, 0, 0, null);
    }
    
    static {
        j = new String[] { "tools", "colors", "system", "clips", "pens" };
        m = new Cursor(12);
        e = new Cursor(3);
    }
}
