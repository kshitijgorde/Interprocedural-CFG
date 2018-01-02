import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Difus extends Applet implements Runnable
{
    static final Color CFONDO;
    private int x;
    private int y;
    boolean btrace;
    int tiempo_pausa;
    Thread tgrafi;
    Panel marco;
    Digraf grafi;
    ProceDifu calcular;
    Pancontrol pantrol;
    Panbotones panbot;
    Dif_info finfo;
    private Panel ptitu;
    private Label ltitu;
    
    public Dimension getPreferredSize() {
        return new Dimension(this.x, this.y);
    }
    
    public void init() {
        this.setLayout(null);
        this.setSize(this.getPreferredSize());
        this.setBackground(Difus.CFONDO);
        try {
            final String parameter = this.getParameter("an");
            final String parameter2 = this.getParameter("al");
            if (parameter != null & parameter2 != null) {
                this.x = Integer.valueOf(parameter);
                this.y = Integer.valueOf(parameter2);
            }
        }
        catch (Exception ex) {}
        (this.marco = new Tapiz(this.x, this.y, Difus.CFONDO)).setBounds(0, 0, this.x, this.y);
        this.add(this.marco);
        this.ptitu.setBounds(20, 10, this.x - 40, this.y / 10);
        this.ptitu.setBackground(Difus.CFONDO);
        this.ltitu.setForeground(new Color(10, 10, 240));
        this.ltitu.setFont(new Font("Serif", 1, 16));
        this.ptitu.add("Center", this.ltitu);
        this.marco.add(this.ptitu);
        this.grafi = new Digraf(this);
        this.marco.add(this.grafi);
        this.grafi.setBounds(16, this.y / 10 + 10, this.x - 32, 7 * this.y / 10);
        this.grafi.iniciar();
        (this.calcular = new ProceDifu(50)).setorigen(this.grafi.getBounds().width, this.grafi.getBounds().height);
        this.calcular.setrandom();
        (this.pantrol = new Pancontrol(this, Difus.CFONDO, this.x - 40, 20)).setBounds(20, 8 * this.y / 10 + 20, this.x - 40, 20);
        this.marco.add(this.pantrol);
        (this.panbot = new Panbotones(this, Difus.CFONDO)).setBounds(20, 7 * this.y / 8 + 20, this.x - 40, this.y / 16);
        this.marco.add(this.panbot);
        this.finfo = new Dif_info();
    }
    
    public void start() {
        this.grafi.iniciar();
        this.calcular.setorigen(this.grafi.getBounds().width, this.grafi.getBounds().height);
        if (this.tgrafi == null) {
            (this.tgrafi = new Thread(this)).start();
            return;
        }
        this.repaint();
    }
    
    public void stop() {
        if (this.tgrafi != null) {
            this.tgrafi.stop();
            this.tgrafi = null;
        }
    }
    
    public void continuar() {
        if (this.tgrafi == null) {
            (this.tgrafi = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (this.tgrafi != null) {
            this.calcular.getnewpos();
            try {
                for (int i = 0; i < 5; ++i) {
                    this.grafi.dibusalto(i, this.btrace);
                    Thread.sleep(this.tiempo_pausa);
                }
            }
            catch (InterruptedException ex) {
                this.tgrafi.stop();
            }
        }
    }
    
    public void cambionumpart(final int n) {
        this.stop();
        (this.calcular = new ProceDifu(n)).setorigen(this.grafi.getBounds().width, this.grafi.getBounds().height);
        this.start();
    }
    
    public static void main(final String[] array) {
        final Difus difus = new Difus();
        difus.init();
        difus.start();
    }
    
    public Difus() {
        this.x = 400;
        this.y = 400;
        this.btrace = true;
        this.tiempo_pausa = 200;
        this.ptitu = new Panel();
        this.ltitu = new Label("Particles Diffusion");
    }
    
    static {
        CFONDO = new Color(200, 100, 120);
    }
}
