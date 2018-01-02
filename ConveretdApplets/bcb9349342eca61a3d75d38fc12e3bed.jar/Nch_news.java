import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Nch_news extends Applet implements Runnable
{
    private Font fuente_tit;
    private Font fuente_txt;
    private Color bck_tit;
    private Color bck_txt;
    private Color txt_tit;
    private Color txt_txt;
    private News_item[] itemsArray;
    private int current_new;
    private String[] paramTxts;
    private String[] paramTits1;
    private String[] paramTits2;
    private ParameterCollector pc;
    private Image img;
    private Graphics gra;
    private Thread mi_applet;
    
    public void init() {
        this.pc = new ParameterCollector("tit1_", this);
        this.paramTits1 = this.pc.get("tit1_");
        this.paramTits2 = this.pc.get("tit2_");
        this.paramTxts = this.pc.get("txt_");
        this.fuente_tit = new Font(this.getParameter("font_tit_face"), this.pc.getFontStyle(this.getParameter("font_tit_style")), Integer.parseInt(this.getParameter("font_tit_size")));
        this.fuente_txt = new Font(this.getParameter("font_txt_face"), this.pc.getFontStyle(this.getParameter("font_txt_style")), Integer.parseInt(this.getParameter("font_txt_size")));
        this.bck_tit = this.pc.hexConvert(this.getParameter("bck_tit"));
        this.bck_txt = this.pc.hexConvert(this.getParameter("bck_txt"));
        this.txt_tit = this.pc.hexConvert(this.getParameter("txt_tit"));
        this.txt_txt = this.pc.hexConvert(this.getParameter("txt_txt"));
        final int int1 = Integer.parseInt(this.getParameter("still_delay"));
        final int int2 = Integer.parseInt(this.getParameter("fade_delay"));
        final int int3 = Integer.parseInt(this.getParameter("vibration"));
        this.setSize(this.size().width, this.size().height);
        this.img = this.createImage(this.size().width, this.size().height);
        this.gra = this.img.getGraphics();
        this.itemsArray = new News_item[this.paramTits1.length];
        for (int i = 0; i < this.paramTits1.length; ++i) {
            Color color;
            try {
                color = this.pc.hexConvert(this.getParameter("coltit_" + (i + 1)));
            }
            catch (Exception ex) {
                color = this.txt_tit;
            }
            this.itemsArray[i] = new News_item(this.size().width, this.size().height, this.paramTits1[i], this.paramTits2[i], this.paramTxts[i], color, this.bck_tit, this.fuente_tit, this.txt_txt, this.bck_txt, this.fuente_txt, int3, int2, int1, this.gra);
        }
        this.current_new = 0;
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
        this.itemsArray[this.current_new].set_mouse_in(true);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.itemsArray[this.current_new].set_mouse_in(false);
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.itemsArray[this.current_new].force_next();
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.itemsArray[this.current_new].dibujar(this.gra)) {
            this.current_new = (this.current_new + 1) % this.paramTits1.length;
        }
        graphics.drawImage(this.img, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
