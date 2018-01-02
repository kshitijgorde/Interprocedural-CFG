import java.awt.event.WindowEvent;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.Image;
import java.awt.Button;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class OptionsDialog extends Dialog implements ActionListener, WindowListener
{
    Button Close;
    Image Icon;
    Checkbox Grid;
    Checkbox Mouse;
    Checkbox green;
    Checkbox red;
    Checkbox blue;
    Checkbox black;
    Checkbox yellow;
    Checkbox green1;
    Checkbox red1;
    Checkbox blue1;
    Checkbox black1;
    Checkbox yellow1;
    CheckboxGroup CLine;
    CheckboxGroup UCLine;
    CheckboxGroup ALine;
    
    OptionsDialog(final Frame frame, final String s) {
        super(frame, s, false);
        final Panel panel = new Panel();
        this.CLine = new CheckboxGroup();
        this.UCLine = new CheckboxGroup();
        this.setSize(300, 425);
        this.Close = new Button("Close");
        panel.setLayout(new FlowLayout(0));
        this.Grid = new Checkbox("Show Grid", true);
        this.Mouse = new Checkbox("Show Mouse Cordinates", true);
        this.green = new Checkbox("Green", this.CLine, false);
        this.red = new Checkbox("Red", this.CLine, true);
        this.blue = new Checkbox("Blue", this.CLine, false);
        this.black = new Checkbox("Black", this.CLine, false);
        this.yellow = new Checkbox("Yellow", this.CLine, false);
        this.green1 = new Checkbox("Green", this.UCLine, false);
        this.red1 = new Checkbox("Red", this.UCLine, true);
        this.blue1 = new Checkbox("Blue", this.UCLine, false);
        this.black1 = new Checkbox("Black", this.UCLine, false);
        this.yellow1 = new Checkbox("Yellow", this.UCLine, false);
        panel.add(new Label(""));
        panel.add(new Label("                            "));
        panel.add(new Label("                            "));
        panel.add(this.Grid);
        panel.add(this.Mouse);
        panel.add(new Label("                                                                         "));
        panel.add(new Label("Connected Line Color                            "));
        panel.add(this.green);
        panel.add(this.red);
        panel.add(this.blue);
        panel.add(this.black);
        panel.add(this.yellow);
        panel.add(new Label("                                                                         "));
        panel.add(new Label("Disconnected Line Color                            "));
        panel.add(this.green1);
        panel.add(this.red1);
        panel.add(this.blue1);
        panel.add(this.black1);
        panel.add(this.yellow1);
        this.setLayout(new BorderLayout());
        this.add(panel);
        this.add(this.Close, "South");
        this.Close.addActionListener(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.dispose();
        this.setVisible(false);
    }
    
    public Insets getInsets() {
        return new Insets(30, 30, 30, 30);
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
}
