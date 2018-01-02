import java.awt.Event;
import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Color;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class SimCanvas extends Canvas implements Runnable
{
    Applet app;
    public Thread sim_thread;
    Image background;
    Image hangar;
    Image double_buffer;
    Graphics double_buffer_graphics;
    int mouse_down_at_x;
    int mouse_down_at_y;
    public Vector airplanes;
    int simulation_units;
    static final double new_plane_frequency = 0.1;
    public boolean paused;
    
    public void init(final Applet app) {
        this.resize(450, 450);
        this.setBackground(new Color(10, 70, 30));
        this.app = app;
        this.background = app.getImage(app.getDocumentBase(), "backgrnd.gif");
        this.hangar = app.getImage(app.getDocumentBase(), "hangar.gif");
        CommercialAirplane.MakeImages(app.getImage(app.getDocumentBase(), "777.gif"), app);
        PropellerAirplane.MakeImages(app.getImage(app.getDocumentBase(), "propeller.gif"), app);
        this.double_buffer = app.createImage(450, 450);
        (this.double_buffer_graphics = this.double_buffer.getGraphics()).setColor(new Color(10, 70, 30));
        this.double_buffer_graphics.fillRect(0, 0, 450, 450);
        this.double_buffer_graphics.setColor(Color.white);
        this.double_buffer_graphics.drawString("Loading Pattern Simulator...", 10, 425);
        this.airplanes = new Vector(20);
    }
    
    public void startSim() {
        this.airplanes.removeAllElements();
        this.airplanes.addElement(new CommercialAirplane());
        this.repaint();
        this.simulation_units = 1000;
        this.paused = false;
    }
    
    public void paint(final Graphics graphics) {
        this.double_buffer_graphics.drawImage(this.background, 0, 0, this.app);
        final Enumeration<Airplane> elements = this.airplanes.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().Draw(this.double_buffer_graphics, this.app);
        }
        this.double_buffer_graphics.drawImage(this.hangar, 170, 292, this.app);
        graphics.drawImage(this.double_buffer, 0, 0, this.app);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        while ((this.app.checkImage(this.background, this.app) & 0x20) == 0x0) {
            this.repaint();
            try {
                Thread.sleep(250L);
            }
            catch (InterruptedException ex) {}
        }
        ((PatternSimulator)this.getParent()).pause_button.enable();
        while (true) {
            if (this.paused) {
                try {
                    Thread.sleep(1500L);
                }
                catch (InterruptedException ex2) {}
            }
            else {
                if (--this.simulation_units <= 0) {
                    ((PatternSimulator)this.getParent()).pause_button.setLabel("Start Sim");
                    this.paused = true;
                }
                ((PatternSimulator)this.getParent()).time_label.setText(Integer.toString(this.simulation_units));
                ((PatternSimulator)this.getParent()).number_label.setText(Integer.toString(this.airplanes.size()));
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex3) {}
                this.DoUpdate();
                try {
                    this.paint(this.getGraphics());
                }
                catch (Exception ex4) {
                    this.sim_thread = null;
                }
            }
        }
    }
    
    protected void DoUpdate() {
        boolean b = true;
        int n = 1;
        final Enumeration<Airplane> elements = this.airplanes.elements();
        while (elements.hasMoreElements()) {
            final Airplane airplane = elements.nextElement();
            if (airplane.InTheWayOfNewAirplane()) {
                b = false;
            }
            if (airplane.CurrentlyLanding()) {
                n = 0;
            }
        }
        final Enumeration<Airplane> elements2 = this.airplanes.elements();
        while (elements2.hasMoreElements()) {
            final Airplane airplane2 = elements2.nextElement();
            if (n != 0 && airplane2.DoLandIfPossible()) {
                n = 0;
            }
            if (this.mouse_down_at_x >= 0) {
                airplane2.Release(this.mouse_down_at_x, this.mouse_down_at_y);
            }
            airplane2.Fly();
            if (airplane2.RemoveMe()) {
                this.airplanes.removeElement(airplane2);
            }
        }
        if (b) {
            final double random = Math.random();
            if (random < 0.05) {
                this.airplanes.addElement(new PropellerAirplane());
                return;
            }
            if (random < 0.1) {
                this.airplanes.addElement(new CommercialAirplane());
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int mouse_down_at_x, final int mouse_down_at_y) {
        this.mouse_down_at_x = mouse_down_at_x;
        this.mouse_down_at_y = mouse_down_at_y;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.mouse_down_at_x = -1;
        this.mouse_down_at_y = -1;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int mouse_down_at_x, final int mouse_down_at_y) {
        this.mouse_down_at_x = mouse_down_at_x;
        this.mouse_down_at_y = mouse_down_at_y;
        return true;
    }
    
    SimCanvas() {
        this.paused = true;
    }
}
