import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class SelectedLine extends Dialog implements ActionListener
{
    Button Close;
    Image Icon;
    Line l;
    
    SelectedLine(final Frame frame, final String s, final Line l, final int n) {
        super(frame, s, false);
        this.setSize(300, 200);
        this.Close = new Button("Close");
        this.setLayout(new BorderLayout());
        this.add(this.Close, "South");
        this.Close.addActionListener(this);
        this.l = l;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.dispose();
        this.setVisible(false);
    }
    
    public Insets getInsets() {
        return new Insets(30, 30, 30, 30);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Dialog", 1, 14));
        graphics.drawString("Line Properties", 25, 50);
        graphics.setFont(new Font("Dialog", 1, 12));
        graphics.setColor(Color.black);
        graphics.drawString("Starting Coordinates:", 25, 75);
        graphics.drawString("Ending Coordinates:", 25, 95);
        graphics.drawString("Slope:", 25, 115);
        graphics.drawString("Length:", 25, 135);
        graphics.setColor(Color.blue);
        graphics.drawString(String.valueOf(this.l.getSlope() * -1.0), 72, 115);
        graphics.drawString(String.valueOf(this.l.getLength() / 10.0), 75, 135);
        graphics.drawString(this.l.getX2() + ", " + -1 * (this.l.getY2() - 400), 150, 95);
        graphics.drawString(this.l.getX1() + ", " + -1 * (this.l.getY1() - 400), 160, 75);
    }
}
