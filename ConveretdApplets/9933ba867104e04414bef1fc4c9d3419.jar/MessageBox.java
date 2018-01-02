import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.30
// 

public class MessageBox extends JFrame
{
    private final String oktext = "OK";
    private JLabel text;
    private JButton okbtn;
    
    public MessageBox(final String s) {
        super("Word Search Message");
        this.getContentPane().setLayout(new GridLayout(2, 1));
        this.text = new JLabel(s, 0);
        this.okbtn = new JButton("OK");
        this.getContentPane().add(this.text);
        this.getContentPane().add(this.okbtn);
        this.okbtn.addMouseListener(new MouseHandler());
        this.pack();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        final Rectangle bounds = this.getBounds();
        this.setBounds((screenSize.width - bounds.width) / 2, (screenSize.height - bounds.height) / 2, bounds.width, bounds.height);
        this.show();
    }
    
    private class MouseHandler extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
            MessageBox.this.hide();
        }
    }
}
