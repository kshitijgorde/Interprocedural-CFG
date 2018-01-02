import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Button;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class WControlDialog extends Dialog implements ActionListener
{
    public WControlDialog(final Frame frame, final String s) {
        super(frame, s);
        this.setSize(500, 225);
        this.setLayout(new BorderLayout());
        final Button button = new Button("OK");
        this.add(button, "South");
        button.addActionListener(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.dispose();
    }
    
    public Insets getInsets() {
        return new Insets(30, 30, 30, 30);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setFont(new Font("Arial", 1, 12));
        graphics.setColor(Color.blue);
        graphics.drawString("Thank You For Using the Angle Finder", 10, 40);
        graphics.drawString("Press S to turn on the Selection Lock, And then Select a Line to See Properties", 10, 80);
        graphics.drawString("Hold the Up/Down or Left/Right to turn on the X-Lock or the Y-Lock", 10, 115);
    }
}
