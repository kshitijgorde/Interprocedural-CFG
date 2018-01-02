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

class VersionDialog extends Dialog implements ActionListener
{
    public VersionDialog(final Frame frame, final String s) {
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
        graphics.drawString("The Next Version will be Complete by the End of July", 10, 80);
        graphics.drawString("Please Email me with Questions or Comments at matthewp93@comcast.net", 10, 115);
    }
}
