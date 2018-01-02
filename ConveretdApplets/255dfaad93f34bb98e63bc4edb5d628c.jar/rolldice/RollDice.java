// 
// Decompiled by Procyon v0.5.30
// 

package rolldice;

import java.awt.Component;
import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JApplet;

public class RollDice extends JApplet
{
    public RollDice() {
        this.setContentPane(new RollDicePanel());
    }
    
    public static void main(final String[] args) {
        final JFrame window = new JFrame("Dice Demo");
        window.setDefaultCloseOperation(3);
        window.setContentPane(new RollDicePanel());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
