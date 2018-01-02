import java.awt.Event;
import java.awt.Component;
import java.awt.Font;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Label;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PatternSimulator extends Applet
{
    SimCanvas canvas;
    public Label time_label;
    public Label number_label;
    Button pause_button;
    
    public void init() {
        (this.canvas = new SimCanvas()).init(this);
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        panel.setFont(new Font("TimesRoman", 1, 12));
        panel.add(new Label("Simulation Time:", 2));
        panel.add(this.time_label = new Label("1000", 0));
        panel.add(new Label("Airplanes in system:", 2));
        panel.add(this.number_label = new Label("0", 0));
        panel.add(new Label("     "));
        panel.add(this.pause_button = new Button("Start Sim"));
        this.pause_button.disable();
        this.add("North", panel);
        this.add("Center", this.canvas);
    }
    
    public void start() {
        if (this.canvas.sim_thread == null) {
            (this.canvas.sim_thread = new Thread(this.canvas)).start();
        }
    }
    
    public void stop() {
        this.canvas.sim_thread = null;
    }
    
    public boolean action(final Event event, final Object o) {
        if ("Start Sim".equals(o)) {
            this.pause_button.setLabel("Pause");
            this.canvas.startSim();
            return true;
        }
        if ("Pause".equals(o)) {
            this.canvas.paused = true;
            this.pause_button.setLabel("Resume");
            return true;
        }
        if ("Resume".equals(o)) {
            this.canvas.paused = false;
            this.pause_button.setLabel("Pause");
        }
        return false;
    }
}
