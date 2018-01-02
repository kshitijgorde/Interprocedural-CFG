// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.JScrollPane;
import javax.swing.JComponent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import javax.swing.JTextArea;
import javax.swing.JFrame;

public class AmInfoBox extends JFrame
{
    private JTextArea textBox;
    
    public AmInfoBox(final String title) {
        this.buildUI();
        this.setSize(400, 320);
        this.setTitle(title);
        WindowUtil.center(this);
    }
    
    public void displayText(final String text) {
        this.textBox.setText(text);
        this.setVisible(true);
    }
    
    private void buildUI() {
        final JComponent displayBox = this.getDisplayBox();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(displayBox, "Center");
    }
    
    private JComponent getDisplayBox() {
        final JTextArea textBox = new JTextArea(3, 20);
        final JScrollPane scrollPane = new JScrollPane(textBox, 20, 31);
        textBox.setEditable(false);
        textBox.setLineWrap(true);
        textBox.setWrapStyleWord(true);
        this.textBox = textBox;
        return scrollPane;
    }
}
