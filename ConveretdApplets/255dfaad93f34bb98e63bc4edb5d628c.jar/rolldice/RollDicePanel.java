// 
// Decompiled by Procyon v0.5.30
// 

package rolldice;

import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;

public class RollDicePanel extends JPanel
{
    private Die _leftDie;
    private Die _rightDie;
    
    RollDicePanel() {
        this._leftDie = new Die();
        this._rightDie = new Die();
        final JButton rollButton = new JButton("New Roll");
        rollButton.setFont(new Font("Sansserif", 0, 24));
        rollButton.addActionListener(new RollListener());
        this.setLayout(new BorderLayout(5, 5));
        this.add(rollButton, "North");
        this.add(this._leftDie, "West");
        this.add(this._rightDie, "East");
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
    
    private class RollListener implements ActionListener
    {
        @Override
        public void actionPerformed(final ActionEvent e) {
            RollDicePanel.this._leftDie.roll();
            RollDicePanel.this._rightDie.roll();
        }
    }
}
