import java.awt.Container;
import java.awt.Point;
import java.awt.Dimension;
import symantec.itools.multimedia.ImageViewer;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.net.URL;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Label;
import symantec.itools.awt.util.dialog.ModalDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class AttentionDialog extends ModalDialog
{
    boolean fComponentsAdjusted;
    Label label1;
    Button okButton;
    
    public AttentionDialog(final Frame frame, final String s, final String text, final URL url) {
        super(frame, s);
        this.fComponentsAdjusted = false;
        ((Container)this).setLayout(new FlowLayout(0, 5, 5));
        this.addNotify();
        ((Component)this).setSize(270, 73);
        (this.label1 = new Label("")).setBounds(5, 5, 14, 21);
        ((Container)this).add(this.label1);
        (this.okButton = new Button("OK")).setBounds(24, 5, 26, 21);
        ((Container)this).add(this.okButton);
        this.label1.setText(text);
        if (url != null) {
            ((Container)this).add((Component)new ImageViewer(url));
        }
    }
    
    public AttentionDialog(final Frame frame) {
        this(frame, "Attention", "Event", null);
    }
    
    public AttentionDialog(final Frame frame, final boolean b) {
        this(frame);
    }
    
    public AttentionDialog(final Frame frame, final String s, final boolean b) {
        this(frame, "Attention", s, null);
    }
    
    public void addNotify() {
        final Dimension size = ((Component)this).getSize();
        super.addNotify();
        if (this.fComponentsAdjusted) {
            return;
        }
        ((Component)this).setSize(((Container)this).insets().left + ((Container)this).insets().right + size.width, ((Container)this).insets().top + ((Container)this).insets().bottom + size.height);
        final Component[] components = ((Container)this).getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Point location = components[i].getLocation();
            location.translate(((Container)this).insets().left, ((Container)this).insets().top);
            components[i].setLocation(location);
        }
        this.fComponentsAdjusted = true;
    }
}
