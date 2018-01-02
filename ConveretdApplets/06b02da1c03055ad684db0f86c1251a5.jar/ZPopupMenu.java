import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.net.URL;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ZPopupMenu extends Applet implements Runnable
{
    int ni;
    int count;
    int ucount;
    Color border_color;
    Color fill_color;
    Color fill_color_mouse_over;
    Color text_color;
    Color text_color_mouse_over;
    Image offI;
    Graphics offG;
    Font font;
    String text;
    String[] mtext;
    String bc;
    String fc;
    String fcm;
    String tc;
    String tcm;
    String[] u;
    PopupMenu popup;
    MenuItem[] item;
    URL[] url;
    FontMetrics fm;
    boolean entry;
    StringTokenizer st;
    Thread thread;
    
    public void init() {
        this.getInfo();
        this.offI = this.createImage(this.size().width, this.size().height);
        this.offG = this.offI.getGraphics();
        this.font = new Font("TimesRoman", 1, Integer.parseInt(this.getParameter("font_size")));
        this.offG.setFont(this.font);
        this.fm = this.offG.getFontMetrics(this.font);
        this.entry = false;
        this.add(this.popup);
    }
    
    public void paint(final Graphics graphics) {
        this.offG.setColor(this.border_color);
        this.offG.fill3DRect(0, 0, this.size().width, this.size().height, true);
        if (!this.entry) {
            this.offG.setColor(this.fill_color);
        }
        else {
            this.offG.setColor(this.fill_color_mouse_over);
        }
        this.offG.fillRect(2, 2, this.size().width - 4, this.size().height - 4);
        if (!this.entry) {
            this.offG.setColor(this.text_color);
        }
        else {
            this.offG.setColor(this.text_color_mouse_over);
        }
        this.offG.drawString(this.text, (this.size().width - this.fm.stringWidth(this.text)) / 2, this.size().height / 2 + (this.fm.getAscent() - this.fm.getDescent()) / 2);
        graphics.drawImage(this.offI, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.setCursor(new Cursor(1));
        this.entry = true;
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.setCursor(new Cursor(0));
        this.entry = false;
        this.repaint();
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.popup.show(this, 0, this.size().height);
        return true;
    }
    
    public boolean action(final Event event, final Object o) {
        for (int i = 0; i < this.ni; ++i) {
            if (o == this.item[i].getLabel()) {
                this.getAppletContext().showDocument(this.url[i], "_self");
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        return true;
    }
    
    public void getInfo() {
        this.text = this.getParameter("text");
        this.bc = this.getParameter("border_color");
        this.st = new StringTokenizer(this.bc, ",");
        this.border_color = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()));
        this.fc = this.getParameter("fill_color");
        this.st = new StringTokenizer(this.fc, ",");
        this.fill_color = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()));
        this.fcm = this.getParameter("fill_color_mouse_over");
        this.st = new StringTokenizer(this.fcm, ",");
        this.fill_color_mouse_over = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()));
        this.tc = this.getParameter("text_color");
        this.st = new StringTokenizer(this.tc, ",");
        this.text_color = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()));
        this.tcm = this.getParameter("text_color_mouse_over");
        this.st = new StringTokenizer(this.tcm, ",");
        this.text_color_mouse_over = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()));
        this.ni = Integer.parseInt(this.getParameter("number_of_items"));
        this.popup = new PopupMenu();
        this.item = new MenuItem[this.ni];
        this.url = new URL[this.ni];
        this.u = new String[this.ni];
        for (int i = 0; i < this.ni; ++i) {
            this.count = i + 1;
            this.item[i] = new MenuItem(this.getParameter("MenuItem" + this.count));
            this.popup.add(this.item[i]);
        }
        for (int j = 0; j < this.ni; ++j) {
            this.ucount = j + 1;
            this.u[j] = this.getParameter("link" + this.ucount);
            try {
                this.url[j] = new URL(this.u[j]);
            }
            catch (MalformedURLException ex) {}
        }
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
        }
    }
    
    public void run() {
    Label_0003:
        while (true) {
            break Label_0003;
            while (true) {
                try {
                    while (true) {
                        Thread.sleep(200L);
                        this.showStatus("***Zmei Popup Menu***Demo Version***...    http://www.zmei-soft.com");
                    }
                }
                catch (InterruptedException ex) {
                    continue;
                }
                continue Label_0003;
            }
            break;
        }
    }
}
