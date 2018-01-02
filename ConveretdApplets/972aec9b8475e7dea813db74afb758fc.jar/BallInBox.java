import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Timer;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class BallInBox extends JPanel
{
    private Ball m_ball;
    private int m_interval;
    private Timer m_timer;
    
    public BallInBox() {
        this.m_ball = new Ball(0, 0, 2, 3);
        this.m_interval = 35;
        this.setPreferredSize(new Dimension(200, 80));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.m_timer = new Timer(this.m_interval, new TimerAction());
    }
    
    public void setAnimation(final boolean turnOnOff) {
        if (turnOnOff) {
            this.m_timer.start();
        }
        else {
            this.m_timer.stop();
        }
    }
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.m_ball.draw(g);
    }
    
    class TimerAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            BallInBox.this.m_ball.setBounds(BallInBox.this.getWidth(), BallInBox.this.getHeight());
            BallInBox.this.m_ball.move();
            BallInBox.this.repaint();
        }
    }
}
