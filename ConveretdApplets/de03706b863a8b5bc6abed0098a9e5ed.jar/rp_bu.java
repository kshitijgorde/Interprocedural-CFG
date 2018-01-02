import java.awt.Font;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JButton;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_bu extends JButton
{
    public rp_bu(final String s, final String s2) {
        final String[] array;
        (array = new String[2])[0] = s;
        array[1] = s2;
        this.a(array);
    }
    
    private void a(final String[] array) {
        this.setLayout(new BoxLayout(this, 1));
        for (int i = 0; i < array.length; ++i) {
            final JLabel label;
            (label = new JLabel(array[i])).setAlignmentX(0.5f);
            this.add(label);
        }
    }
    
    public final void setFont(final Font font) {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            this.getComponent(i).setFont(font);
        }
    }
}
