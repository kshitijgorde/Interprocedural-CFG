import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Label;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Choice;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class GooControls extends Panel implements ActionListener
{
    static Button button;
    public static Choice choice;
    static QGoo applet;
    static LayoutManager dcLayout;
    
    static {
        GooControls.dcLayout = new FlowLayout(1, 3, 3);
    }
    
    public GooControls(final QGoo applet) {
        GooControls.applet = applet;
        this.setLayout(GooControls.dcLayout);
        final Label label;
        this.add(label = new Label("Tool"));
        label.setBackground(QGoo.bg);
        this.add(GooControls.choice = new Choice());
        GooControls.choice.addItem("Goo (Big)");
        GooControls.choice.addItem("Goo (Small)");
        GooControls.choice.addItem("UnGoo (Big)");
        GooControls.choice.addItem("UnGoo (Small)");
        GooControls.choice.addItem("Soften");
        GooControls.choice.addItem("Lighten");
        GooControls.choice.addItem("Darken");
        GooControls.choice.select(0);
        this.add(GooControls.button = new Button("Reset"));
        GooControls.button.addActionListener(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == GooControls.button) {
            QGoo.canvas.reset();
        }
    }
    
    public int getTool(final int[] array) {
        return GooControls.choice.getSelectedIndex();
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(QGoo.bg);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
    }
}
