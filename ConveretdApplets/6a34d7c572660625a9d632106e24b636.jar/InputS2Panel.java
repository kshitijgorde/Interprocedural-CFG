import java.awt.Event;
import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class InputS2Panel extends Panel
{
    PlotS2Panel panel;
    TextField Lin1;
    TextField Lin2;
    TextField d1in;
    TextField d2in;
    TextField min;
    TextField ofin;
    Refract ref;
    public static float m;
    public static float d1;
    public static float d2;
    public static float L1;
    public static float L2;
    public static float of;
    public static int jstep;
    Button Calculate;
    Button Stop;
    public boolean stop;
    Label label1;
    Label label2;
    
    public InputS2Panel(final PlotS2Panel panel) {
        this.Block$();
        this.panel = panel;
        this.setLayout(new GridLayout(8, 2));
        this.add(this.label1 = new Label("Basin 1 Data:"));
        this.add(new Label(" "));
        this.add(new Label("Length (m)?"));
        this.add(this.Lin1 = new TextField("100.0"));
        this.add(new Label("Depth?"));
        this.add(this.d1in = new TextField("7.0"));
        this.add(new Label("Modal Number?"));
        this.add(this.min = new TextField("1"));
        this.add(this.label2 = new Label("Basin 2 Data:"));
        this.add(new Label(" "));
        this.add(new Label("Length (m)?"));
        this.add(this.Lin2 = new TextField("150.0"));
        this.add(new Label("Depth?"));
        this.add(this.d2in = new TextField("5.5"));
        this.add(this.Calculate = new Button("Calculate"));
        this.add(this.Stop = new Button("Stop"));
    }
    
    public Insets insets() {
        return new Insets(10, 10, 10, 10);
    }
    
    public boolean handleEvent(final Event event) {
        this.ref = new Refract();
        try {
            if (event.id == 1001 && event.target instanceof Button) {
                if (event.target == this.Calculate) {
                    InputS2Panel.m = Float.valueOf(this.min.getText());
                    InputS2Panel.L1 = Float.valueOf(this.Lin1.getText());
                    InputS2Panel.L2 = Float.valueOf(this.Lin2.getText());
                    InputS2Panel.d1 = Float.valueOf(this.d1in.getText());
                    InputS2Panel.d2 = Float.valueOf(this.d2in.getText());
                    InputS2Panel.of = 0.0f;
                    this.stop = false;
                    this.panel.set(this.stop);
                    this.panel.do_wave(InputS2Panel.L1, InputS2Panel.L2, InputS2Panel.d1, InputS2Panel.d2, InputS2Panel.m, InputS2Panel.of);
                }
                else if (event.target == this.Stop) {
                    this.stop = true;
                    this.panel.set(this.stop);
                }
            }
        }
        catch (NumberFormatException ex) {
            System.out.println(ex);
        }
        return false;
    }
    
    private void Block$() {
    }
}
