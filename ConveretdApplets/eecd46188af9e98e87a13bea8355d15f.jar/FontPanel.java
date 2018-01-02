import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import ru.zhuk.gui.b;
import java.awt.Image;
import java.awt.Panel;
import java.awt.GridLayout;
import java.net.URL;
import java.awt.MediaTracker;
import java.awt.Component;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import ru.zhuk.gui.f;
import java.awt.Font;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class FontPanel extends Applet implements ActionListener, ItemListener
{
    private Choice a;
    private Choice c;
    private Font e;
    private f g;
    private f f;
    private String[] b;
    private f[] h;
    private f d;
    
    public FontPanel() {
        this.b = new String[] { "left", "center", "right" };
        this.h = new f[this.b.length];
    }
    
    public void init() {
        try {
            String parameter = this.getParameter("icondir");
            if (parameter == null) {
                parameter = "icons/";
            }
            this.setLayout(new FlowLayout(0, 4, 0));
            final String s = "Serif,SansSerif,Monospaced,Dialog";
            final String s2 = "12,16,24,32,48";
            final String parameter2 = this.getParameter("bgcolor");
            this.setBackground(new Color((parameter2 != null) ? Integer.decode(parameter2) : 12632256));
            String parameter3 = this.getParameter("font_faces");
            if (parameter3 == null) {
                parameter3 = s;
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter3, ",");
            this.a = new Choice();
            while (stringTokenizer.hasMoreTokens()) {
                this.a.addItem(stringTokenizer.nextToken().trim());
            }
            this.a.addItemListener(this);
            this.add(this.a);
            String parameter4 = this.getParameter("font_sizes");
            if (parameter4 == null) {
                parameter4 = s2;
            }
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter4, ",");
            this.c = new Choice();
            while (stringTokenizer2.hasMoreTokens()) {
                this.c.addItem(stringTokenizer2.nextToken().trim());
            }
            this.c.addItemListener(this);
            this.add(this.c);
            final MediaTracker mediaTracker = new MediaTracker(this);
            final Image image = this.getImage(new URL(this.getCodeBase(), String.valueOf(String.valueOf(parameter)).concat("bold.gif")));
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
            (this.g = new f(null, image, ru.zhuk.gui.f.g)).a(this);
            final Image image2 = this.getImage(new URL(this.getCodeBase(), String.valueOf(String.valueOf(parameter)).concat("italic.gif")));
            mediaTracker.addImage(image2, 1);
            mediaTracker.waitForID(1);
            (this.f = new f(null, image2, ru.zhuk.gui.f.g)).a(this);
            final Panel panel = new Panel(new GridLayout(1, 2));
            panel.add(this.g);
            panel.add(this.f);
            this.add(panel);
            final String parameter5 = this.getParameter("text_alignment");
            if (parameter5 != null) {
                final Panel panel2 = new Panel(new GridLayout(1, this.b.length));
                for (int i = 0; i < this.b.length; ++i) {
                    final Image image3 = this.getImage(new URL(this.getCodeBase(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(parameter))).append(this.b[i]).append(".gif")))));
                    mediaTracker.addImage(image3, i);
                    mediaTracker.waitForID(i);
                    final f d = new f(null, image3, ru.zhuk.gui.f.h);
                    d.a("".concat(String.valueOf(String.valueOf(i))));
                    d.a(this);
                    d.b(this.b[i].equalsIgnoreCase(parameter5));
                    if (d.c()) {
                        this.d = d;
                    }
                    panel2.add(d);
                    this.h[i] = d;
                }
                this.add(panel2);
            }
            final String parameter6 = this.getParameter("default");
            if (parameter6 != null) {
                this.a(Font.decode(parameter6));
            }
            this.a();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void a(final Font e) {
        this.e = e;
        this.a.select(e.getName());
        this.c.select(Integer.toString(e.getSize()));
        this.g.b(e.isBold());
        this.f.b(e.isItalic());
    }
    
    private void a() {
        this.e = new Font(this.a.getSelectedItem(), ((false | this.g.c()) ? 1 : 0) | (this.f.c() ? 2 : 0), Integer.parseInt(this.c.getSelectedItem()));
        try {
            int n;
            DrawCanvas d;
            for (n = 600; (d = DrawCanvas.d()) == null && n > 400; --n) {
                Thread.sleep(100L);
            }
            b a;
            while ((a = d.a) == null && n > 0) {
                Thread.sleep(100L);
                --n;
            }
            if (n == 0) {
                return;
            }
            a.setFont(this.e);
            if (this.d != null) {
                a.b(Integer.parseInt(this.d.b()));
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        for (int i = 0; i < this.h.length; ++i) {
            if (actionEvent.getSource() == this.h[i]) {
                if (this.d != null) {
                    this.d.b(false);
                }
                this.d = (f)actionEvent.getSource();
                break;
            }
        }
        this.a();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.a();
    }
}
