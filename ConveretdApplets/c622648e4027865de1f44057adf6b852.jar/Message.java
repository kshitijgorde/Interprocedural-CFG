import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class Message extends Frame implements ActionListener
{
    Button startknop;
    
    public Message() {
        this.startknop = new Button("OK");
        this.setLocation(500, 50);
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setLayout(null);
        this.startknop.requestFocus();
        this.setSize(300, 200);
        this.getSize();
        this.startknop.setBounds(100, 100, 100, 50);
        this.add(this.startknop);
        this.startknop.addActionListener(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.startknop) {
            this.dispose();
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Verdana", 1, 15));
        graphics.drawString("Changes will take effect", 40, 50);
        graphics.drawString("When you start a new Game", 40, 70);
    }
}
