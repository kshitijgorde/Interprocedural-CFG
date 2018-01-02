import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ActionButtons extends Panel implements ActionListener
{
    Hand hand;
    Button StandButton;
    Button SplitButton;
    Button DoubleButton;
    Button HitButton;
    
    public ActionButtons() {
        this.StandButton = new Button();
        this.SplitButton = new Button();
        this.DoubleButton = new Button();
        this.HitButton = new Button();
        this.setLayout(null);
        this.setBackground(new Color(0, 128, 0));
        this.setFont(new Font("Dialog", 0, 8));
        this.setSize(105, 51);
        this.StandButton.setLabel("Stand");
        this.add(this.StandButton);
        this.StandButton.setBackground(Color.lightGray);
        this.StandButton.setFont(new Font("SansSerif", 0, 12));
        this.StandButton.setBounds(54, 0, 52, 24);
        this.SplitButton.setLabel("Split");
        this.add(this.SplitButton);
        this.SplitButton.setBackground(Color.lightGray);
        this.SplitButton.setFont(new Font("SansSerif", 0, 12));
        this.SplitButton.setBounds(0, 26, 52, 24);
        this.DoubleButton.setLabel("Double");
        this.add(this.DoubleButton);
        this.DoubleButton.setBackground(Color.lightGray);
        this.DoubleButton.setFont(new Font("SansSerif", 0, 12));
        this.DoubleButton.setBounds(54, 26, 52, 24);
        this.HitButton.setLabel("Hit");
        this.add(this.HitButton);
        this.HitButton.setBackground(Color.lightGray);
        this.HitButton.setFont(new Font("SansSerif", 0, 12));
        this.HitButton.setBounds(0, 0, 52, 24);
        this.StandButton.addActionListener(this);
        this.SplitButton.addActionListener(this);
        this.DoubleButton.addActionListener(this);
        this.HitButton.addActionListener(this);
    }
    
    public ActionButtons(final Hand hand) {
        this();
        this.hand = hand;
    }
    
    public void setButtons(final boolean enabled, final boolean enabled2, final boolean enabled3, final boolean enabled4) {
        this.HitButton.setEnabled(enabled);
        this.StandButton.setEnabled(enabled2);
        this.SplitButton.setEnabled(enabled3);
        this.DoubleButton.setEnabled(enabled4);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.HitButton.setEnabled(false);
        this.StandButton.setEnabled(false);
        this.SplitButton.setEnabled(false);
        this.DoubleButton.setEnabled(false);
        final Object source = actionEvent.getSource();
        if (source == this.HitButton) {
            this.hand.hit();
        }
        if (source == this.StandButton) {
            this.hand.stand();
        }
        if (source == this.SplitButton) {
            this.hand.split();
        }
        if (source == this.DoubleButton) {
            this.hand.doubleDown();
        }
    }
}
