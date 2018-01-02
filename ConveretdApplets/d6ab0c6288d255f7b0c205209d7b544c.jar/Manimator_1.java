import java.awt.Graphics;
import java.awt.Event;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Manimator_1 extends Applet implements Runnable
{
    String dir;
    boolean isStandalone;
    Thread kicker;
    int nSpeed;
    int nPriority;
    animateoggetti[] animateoggetti;
    loadimmagini liImageHolder;
    String[] imgs;
    String autore;
    private URL m_HRef;
    private String m_Frame;
    private String m_s_Mouse;
    private String m_e_Mouse;
    private final String PARAM_href = "href";
    private final String PARAM_target = "target";
    private final String PARAM_mouse_s = "mouse_enter";
    private final String PARAM_mouse_e = "mouse_exit";
    
    public Manimator_1() {
        this.isStandalone = false;
        this.kicker = null;
        this.m_Frame = "_self";
        this.m_s_Mouse = "";
        this.m_e_Mouse = "";
    }
    
    public String getAppletInfo() {
        return "Manimator  by Massimo Giari 01/08/2000";
    }
    
    public String getParameter(final String key, final String def) {
        return this.isStandalone ? System.getProperty(key, def) : ((this.getParameter(key) != null) ? this.getParameter(key) : def);
    }
    
    public String[][] getParameterInfo() {
        final String[][] info = { { "speed", "int", "ritardo numero frane" }, { "img", "String", "directory che contiene le immagini" }, { "priority", "int", "thread priorit\u00e0 valori da 1 - 10" }, { "mouse_enter", "String", "Testo visualizzato in status quando il mouse passa sull'applet" }, { "mouse_exit", "String", "Testo visualizzato in status quando il mouse esce dall'applet" }, { "href", "URL", "URL da inserire" }, { "target", "String", "Target frame destinazione" } };
        return info;
    }
    
    public void init() {
        try {
            this.autore = this.getParameter("autore", "");
        }
        catch (Exception ex) {}
        if (!this.autore.equalsIgnoreCase("Massimo Giari")) {
            System.exit(0);
        }
        String at = this.getParameter("speed");
        this.nSpeed = ((at != null) ? Integer.valueOf(at) : 60);
        at = this.getParameter("priority");
        this.nPriority = ((at != null) ? Integer.valueOf(at) : 1);
        at = this.getParameter("img");
        final String dir = (at != null) ? at : "";
        this.liImageHolder = new loadimmagini(this.getDocumentBase(), dir, this);
        (this.animateoggetti = new animateoggetti[1])[0] = new cambiaimmagine(this.liImageHolder.imgs, this.liImageHolder.ImageTracker);
        String param = this.getParameter("mouse_enter");
        if (param != null) {
            this.m_s_Mouse = param;
        }
        param = this.getParameter("mouse_exit");
        if (param != null) {
            this.m_e_Mouse = param;
        }
        param = this.getParameter("href");
        if (param != null) {
            try {
                this.m_HRef = new URL(this.getDocumentBase(), param);
            }
            catch (MalformedURLException ex2) {
                this.getAppletContext().showStatus("Bad URL: " + param);
                return;
            }
        }
        param = this.getParameter("target");
        if (param != null) {
            this.m_Frame = param;
        }
    }
    
    public boolean mouseEnter(final Event evt, final int i, final int j) {
        if (this.m_s_Mouse == null) {
            this.showStatus(String.valueOf(this.m_s_Mouse));
        }
        this.getAppletContext().showStatus(this.m_s_Mouse);
        return true;
    }
    
    public boolean mouseExit(final Event evt, final int i, final int j) {
        if (this.m_e_Mouse == null) {
            this.showStatus(String.valueOf(this.m_e_Mouse));
        }
        this.getAppletContext().showStatus(this.m_e_Mouse);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int i, final int j) {
        final boolean flag = super.mouseUp(event, i, j);
        this.showStatus(String.valueOf(String.valueOf(this.m_HRef)));
        this.getAppletContext().showDocument(this.m_HRef, this.m_Frame);
        return true;
    }
    
    public void paint(final Graphics g) {
        this.update(g);
        g.drawString("Caricando Immagini", 5, 20);
    }
    
    public void run() {
        int max = 0;
        Thread.currentThread().setPriority(this.nPriority);
        while (true) {
            Label_0072: {
                try {
                    this.liImageHolder.ImageTracker.waitForAll();
                    break Label_0072;
                }
                catch (InterruptedException ex) {
                    return;
                }
                for (max = 0; max < this.animateoggetti.length; ++max) {
                    this.animateoggetti[max].clockTick();
                }
                this.repaint();
                try {
                    Thread.sleep(this.nSpeed);
                }
                catch (InterruptedException ex2) {}
            }
            if (this.size().width <= 0 || this.size().height <= 0 || this.kicker == null) {
                return;
            }
            continue;
        }
    }
    
    public void start() {
        if (this.kicker == null) {
            (this.kicker = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.kicker = null;
    }
    
    public void update(final Graphics g) {
        if (this.liImageHolder.ImageTracker.isErrorAny()) {
            g.drawString("Errore:Caricamento", 5, 40);
            g.drawString("Immagini", 5, 50);
            return;
        }
        for (int max = 0; max < this.animateoggetti.length; ++max) {
            this.animateoggetti[max].paint(g, this);
        }
    }
}
