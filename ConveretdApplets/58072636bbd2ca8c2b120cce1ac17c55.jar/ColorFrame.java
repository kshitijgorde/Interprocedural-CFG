import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ColorFrame extends JPanel
{
    private JFrame window;
    private JColorChooser colorChooser;
    private JButton ok;
    private JButton cancel;
    private GraphArea gr;
    private int index;
    
    public ColorFrame(final GraphArea gr, final int index) {
        this.gr = gr;
        this.index = index;
        this.initialize();
    }
    
    private void initialize() {
        this.window = new JFrame();
        (this.colorChooser = new JColorChooser()).setColor(this.gr.getCurves()[this.index].getColor());
        this.ok = new JButton("Ok");
        this.cancel = new JButton("Cancel");
        this.ok.addActionListener(new OkClicked());
        this.cancel.addActionListener(new CancelClicked());
        this.setLayout(new FlowLayout());
        this.add(this.colorChooser);
        this.add(this.ok);
        this.add(this.cancel);
        this.window.getContentPane().add(this);
        this.window.setSize(480, 480);
        this.window.setTitle("Choose Color for Curve " + (this.index + 1));
        this.window.setResizable(false);
        this.window.setVisible(true);
    }
    
    private class OkClicked implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            ColorFrame.this.gr.getCurves()[ColorFrame.this.index].setColor(ColorFrame.this.colorChooser.getColor());
            ColorFrame.this.window.dispose();
            ColorFrame.this.gr.repaint();
        }
    }
    
    private class CancelClicked implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            ColorFrame.this.window.dispose();
        }
    }
}
