import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Nch_menu extends Applet implements Runnable
{
    private Font fuente;
    private Color txt_normal;
    private Color bck_normal;
    private Color txt_on;
    private Color bck_on;
    private Menu_item[] itemsArray;
    private String[] paramStrings;
    private ParameterCollector pc;
    private Image img;
    private Graphics gra;
    private Thread mi_applet;
    
    public void init() {
        this.setSize(this.size().width, 1);
        this.pc = new ParameterCollector("string_", this);
        this.paramStrings = this.pc.get("string_");
        this.fuente = new Font(this.getParameter("font_face"), this.pc.getFontStyle(this.getParameter("font_style")), Integer.parseInt(this.getParameter("font_size")));
        final int int1 = Integer.parseInt(this.getParameter("cell_height"));
        final int n = this.size().width / this.paramStrings.length;
        final int int2 = Integer.parseInt(this.getParameter("fade_delay"));
        Integer.parseInt(this.getParameter("orientation"));
        final String parameter = this.getParameter("align");
        final int int3 = Integer.parseInt(this.getParameter("orientation"));
        final int int4 = Integer.parseInt(this.getParameter("line_around"));
        this.txt_normal = this.pc.hexConvert(this.getParameter("text_normal"));
        this.bck_normal = this.pc.hexConvert(this.getParameter("bck_normal"));
        this.setSize(this.size().width, int1 * this.paramStrings.length);
        this.img = this.createImage(this.size().width, this.size().height);
        (this.gra = this.img.getGraphics()).setFont(this.fuente);
        this.itemsArray = new Menu_item[this.paramStrings.length];
        for (int i = 0; i < this.paramStrings.length; ++i) {
            try {
                this.bck_on = this.pc.hexConvert(this.getParameter("bck_" + (i + 1)));
            }
            catch (Exception ex) {
                this.bck_on = this.pc.hexConvert(this.getParameter("bck_on"));
            }
            try {
                this.txt_on = this.pc.hexConvert(this.getParameter("text_" + (i + 1)));
            }
            catch (Exception ex2) {
                this.txt_on = this.pc.hexConvert(this.getParameter("text_on"));
            }
            String parameter2;
            try {
                parameter2 = this.getParameter("url_" + (i + 1));
            }
            catch (Exception ex3) {
                parameter2 = "";
            }
            if (int3 == 0) {
                this.itemsArray[i] = new Menu_item(0, i * int1, this.size().width, int1, this.paramStrings[i], parameter2, this.bck_normal, this.txt_normal, this.bck_on, this.txt_on, int2, parameter, int4, this.gra);
            }
            else if (i == this.paramStrings.length - 1) {
                this.itemsArray[i] = new Menu_item(i * n, 0, this.size().width - i * n, int1, this.paramStrings[i], parameter2, this.bck_normal, this.txt_normal, this.bck_on, this.txt_on, int2, parameter, int4, this.gra);
            }
            else {
                this.itemsArray[i] = new Menu_item(i * n, 0, n, int1, this.paramStrings[i], parameter2, this.bck_normal, this.txt_normal, this.bck_on, this.txt_on, int2, parameter, int4, this.gra);
            }
        }
    }
    
    public void start() {
        if (this.mi_applet == null) {
            (this.mi_applet = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.mi_applet != null) {
            this.mi_applet.stop();
            this.mi_applet = null;
        }
    }
    
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(40L);
            }
            catch (Exception ex) {}
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.paramStrings.length; ++i) {
            this.itemsArray[i].mouse_position(n, n2, false);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.paramStrings.length; ++i) {
            this.itemsArray[i].mouse_position(n, n2, true);
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.paramStrings.length; ++i) {
            final String mouse_click = this.itemsArray[i].mouse_click(n, n2);
            if (mouse_click != "") {
                try {
                    this.getAppletContext().showDocument(new URL(mouse_click), "_self");
                    System.out.print(this.getDocumentBase().toString());
                }
                catch (Exception ex) {}
            }
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < this.paramStrings.length; ++i) {
            this.itemsArray[i].dibujar(this.gra);
        }
        graphics.drawImage(this.img, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
