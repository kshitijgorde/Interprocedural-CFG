// 
// Decompiled by Procyon v0.5.30
// 

package mousedemo;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.MouseListener;
import javax.swing.JApplet;

public class MouseTest extends JApplet implements MouseListener
{
    JTextArea _loggingTA;
    MouseGraphicsComponent _display;
    
    public MouseTest() {
        this._loggingTA = new JTextArea(10, 40);
        this._display = new MouseGraphicsComponent();
        final JButton clearBtn = new JButton("Clear");
        final JLabel commentsLbl = new JLabel("Experiment with combinations of mouse buttons and modifier keys.");
        this._display.addMouseListener(this);
        clearBtn.addActionListener(new ClearAction());
        final JPanel topPanel = new JPanel(new FlowLayout(0));
        topPanel.add(clearBtn);
        topPanel.add(commentsLbl);
        final JPanel content = new JPanel();
        content.setLayout(new BorderLayout(5, 5));
        content.add(topPanel, "North");
        content.add(this._display, "West");
        content.add(new JScrollPane(this._loggingTA), "Center");
        this.setContentPane(content);
    }
    
    public static void main(final String[] args) {
        final JFrame window = new JFrame("Mouse Demo");
        window.setDefaultCloseOperation(3);
        window.setContentPane(new MouseTest());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    public void mouseClicked(final MouseEvent e) {
        this.log(e);
    }
    
    public void mouseEntered(final MouseEvent e) {
        this.log(e);
    }
    
    public void mouseExited(final MouseEvent e) {
        this.log(e);
    }
    
    public void mousePressed(final MouseEvent e) {
        this.log(e);
    }
    
    public void mouseReleased(final MouseEvent e) {
        this.log(e);
    }
    
    private void log(final MouseEvent e) {
        this._loggingTA.append(e.paramString() + '\n');
    }
    
    class ClearAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            MouseTest.this._loggingTA.setText("");
            MouseTest.this._display.clear();
        }
    }
}
