import java.awt.Container;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Button;
import java.awt.Component;
import java.awt.TextArea;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class MsgDialog extends Dialog implements ActionListener, WindowListener
{
    public MsgDialog(final Frame frame, final String s, final String s2) {
        super(frame, s, true);
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.white);
        this.setFont(new Font("Helvetica", 0, 14));
        this.add("Center", new TextArea(s2, 3, 30, 1));
        final Button button = new Button("OK");
        button.addActionListener(this);
        this.add("South", button);
        this.addWindowListener(this);
        this.setSize(300, 150);
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation(new Point(Math.round((screenSize.width - 300) / 2), Math.round((screenSize.height - 150) / 2)));
        this.setVisible(true);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button && ((Button)actionEvent.getSource()).getLabel() == "OK") {
            this.setVisible(false);
            this.dispose();
        }
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
        this.dispose();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public static Frame getFrame(final Component component) {
        Container container;
        for (container = component.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        return (Frame)container;
    }
}
