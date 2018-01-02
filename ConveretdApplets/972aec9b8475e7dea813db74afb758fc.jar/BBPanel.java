import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

class BBPanel extends JPanel
{
    BallInBox m_bb;
    
    BBPanel() {
        this.m_bb = new BallInBox();
        final JButton startButton = new JButton("Start");
        final JButton stopButton = new JButton("Stop");
        startButton.addActionListener(new StartAction());
        stopButton.addActionListener(new StopAction());
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        this.setLayout(new BorderLayout());
        this.add(buttonPanel, "North");
        this.add(this.m_bb, "Center");
    }
    
    class StartAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            BBPanel.this.m_bb.setAnimation(true);
        }
    }
    
    class StopAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            BBPanel.this.m_bb.setAnimation(false);
        }
    }
}
