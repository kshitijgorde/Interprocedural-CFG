import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Applet1 extends Applet implements Bank
{
    Blackjack blackjack;
    Button button2;
    
    public void init() {
        this.setLayout(null);
        this.setBackground(new Color(0, 128, 0));
        this.setSize(157, 110);
        this.button2.setLabel("Play Blackjack");
        this.add(this.button2);
        this.button2.setBackground(Color.lightGray);
        this.button2.setBounds(24, 36, 108, 40);
        this.button2.addActionListener(new SymAction());
    }
    
    public void start() {
        CardPanel.setCardsImage(this.getImage(this.getCodeBase(), "faces.gif"), this.getImage(this.getCodeBase(), "back.gif"), this.getImage(this.getCodeBase(), "joker.gif"));
        this.blackjack = new Blackjack("Blackjack");
    }
    
    void button2_ActionPerformed(final ActionEvent actionEvent) {
        this.blackjack.startGame(this);
    }
    
    public void addToBank(final int n) {
    }
    
    public Applet1() {
        this.button2 = new Button();
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == Applet1.this.button2) {
                Applet1.this.button2_ActionPerformed(actionEvent);
            }
        }
    }
}
