import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Point;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.TextArea;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class AboutFrame extends Frame
{
    boolean fComponentsAdjusted;
    TextArea aboutTextArea;
    
    public AboutFrame() {
        this.fComponentsAdjusted = false;
        this.aboutTextArea = new TextArea();
        this.setLayout(null);
        this.setBackground(new Color(0, 128, 0));
        this.setSize(410, 255);
        this.setVisible(false);
        this.add(this.aboutTextArea);
        this.aboutTextArea.setBounds(12, 12, 384, 228);
        this.setTitle("About");
        this.setResizable(false);
        this.aboutTextArea.append("Java Video Poker by Robert Kosirog\n");
        this.aboutTextArea.append("Comments or Suggestions:  rskosirog@yahoo.com\n");
        this.aboutTextArea.append("\n");
        this.aboutTextArea.append("This program may be freely distributed and published\n");
        this.aboutTextArea.append("on Internet web pages, provided you notify the author\n");
        this.aboutTextArea.append("by e-mail at rskosirog@yahoo.com.  See the license.txt file.\n");
        this.aboutTextArea.append("\n");
        this.aboutTextArea.append("You may not may modify or disassemble this program,\n");
        this.aboutTextArea.append("or use any of the classes for any purpose other than\n");
        this.aboutTextArea.append("running the Video Poker program, without the\n");
        this.aboutTextArea.append("permission of the author.\n");
        this.addWindowListener(new SymWindow());
    }
    
    public AboutFrame(final String title) {
        this();
        this.setTitle(title);
    }
    
    public void setVisible(final boolean visible) {
        if (visible) {
            this.setLocation(50, 50);
        }
        super.setVisible(visible);
    }
    
    public static void main(final String[] array) {
        new AboutFrame().setVisible(true);
    }
    
    public void addNotify() {
        final Dimension size = this.getSize();
        super.addNotify();
        if (this.fComponentsAdjusted) {
            return;
        }
        final Insets insets = this.getInsets();
        this.setSize(insets.left + insets.right + size.width, insets.top + insets.bottom + size.height);
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Point location = components[i].getLocation();
            location.translate(insets.left, insets.top);
            components[i].setLocation(location);
        }
        this.fComponentsAdjusted = true;
    }
    
    void AboutFrame_WindowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
        this.dispose();
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == AboutFrame.this) {
                AboutFrame.this.AboutFrame_WindowClosing(windowEvent);
            }
        }
    }
}
