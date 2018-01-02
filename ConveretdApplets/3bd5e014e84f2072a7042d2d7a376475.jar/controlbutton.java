import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Button;

// 
// Decompiled by Procyon v0.5.30
// 

class controlbutton extends Button implements ActionListener
{
    board game;
    
    public controlbutton(final board game, final String s) {
        super(s);
        this.setForeground(Color.white);
        this.setBackground(new Color(128, 0, 32));
        this.game = game;
        this.addActionListener(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.game.process(this);
    }
}
