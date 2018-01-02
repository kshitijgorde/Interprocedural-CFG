import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class sourceFrame extends Frame
{
    boolean fComponentsAdjusted;
    TextArea textArea1;
    JMProps props;
    
    public sourceFrame(final JMProps props) {
        this.fComponentsAdjusted = false;
        this.props = props;
        this.setBackground(this.props.background);
        this.setForeground(this.props.foreground);
        this.setFont(this.props.font);
        this.props.addWindow(this);
        this.setLayout(new BorderLayout(0, 0));
        this.setVisible(false);
        this.setSize(430, 270);
        (this.textArea1 = new TextArea("", 0, 0, 1)).setEditable(false);
        this.textArea1.setBounds(0, 0, 430, 270);
        this.add("Center", this.textArea1);
        this.setTitle("Message Headers");
        this.addWindowListener(new SymWindow());
    }
    
    public sourceFrame(final JMessage message, final JMProps jmProps) {
        this(jmProps);
        final String headerValue = message.getHeaderValue("subject");
        if (headerValue != null && !headerValue.equals("")) {
            this.setTitle(String.valueOf(headerValue) + " (headers)");
        }
        this.textArea1.setText(message.getRawHeaders());
    }
    
    public synchronized void show() {
        this.move(50, 50);
        super.show();
    }
    
    public void addNotify() {
        final Dimension size = this.getSize();
        super.addNotify();
        if (this.fComponentsAdjusted) {
            return;
        }
        this.setSize(this.insets().left + this.insets().right + size.width, this.insets().top + this.insets().bottom + size.height);
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Point location = components[i].getLocation();
            location.translate(this.insets().left, this.insets().top);
            components[i].setLocation(location);
        }
        this.fComponentsAdjusted = true;
    }
    
    void Frame1_WindowClosing(final WindowEvent windowEvent) {
        this.hide();
        this.props.removeWindow(this);
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == sourceFrame.this) {
                sourceFrame.this.Frame1_WindowClosing(windowEvent);
            }
        }
    }
}
