import java.awt.FontMetrics;
import java.io.IOException;
import javax.swing.SwingUtilities;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.Icon;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class LabelNode extends JPanel
{
    static ImageIcon _imgAdd;
    static ImageIcon _imgSubtract;
    static ImageIcon _imgMultiply;
    static ImageIcon _imgDivide;
    private JLabel _label;
    private String _text;
    
    public LabelNode() {
        this.pInit(new String(""));
    }
    
    public LabelNode(final String node) {
        this.pInit(node);
    }
    
    protected void pInit(final String node) {
        this._text = node;
        if (LabelNode._imgAdd == null) {
            final ImageLoader loader = new ImageLoader();
            LabelNode._imgAdd = loader.getImageIcon("images/add.gif");
            LabelNode._imgSubtract = loader.getImageIcon("images/subtract.gif");
            LabelNode._imgMultiply = loader.getImageIcon("images/multiply.gif");
            LabelNode._imgDivide = loader.getImageIcon("images/divide.gif");
        }
        (this._label = new JLabel(node)).setFont(new Font("Dialog", 1, 14));
        this._label.setForeground(Color.white);
        this._label.setBackground(new Color(123, 123, 123));
        this._label.setOpaque(true);
        this._label.setHorizontalAlignment(0);
        this._label.setHorizontalTextPosition(0);
        this._label.setVerticalAlignment(0);
        this._label.setVerticalTextPosition(0);
        if (node.equals("+") || node.equals("-") || node.equals("/") || node.equals("*")) {
            this._label.setText("");
            this.setSize(24, 24);
            this.setPreferredSize(new Dimension(24, 24));
            switch (node.charAt(0)) {
                case '+': {
                    this._label.setIcon(LabelNode._imgAdd);
                    break;
                }
                case '-': {
                    this._label.setIcon(LabelNode._imgSubtract);
                    break;
                }
                case '*': {
                    this._label.setIcon(LabelNode._imgMultiply);
                    break;
                }
                case '/': {
                    this._label.setIcon(LabelNode._imgDivide);
                    break;
                }
                default: {
                    this._label.setText("Err!");
                    break;
                }
            }
        }
        else {
            this.setSize();
        }
        this._label.setBounds(0, 0, this.getWidth(), this.getHeight());
        this._label.setBorder(new LineBorder(Color.orange, 2));
        this.setLayout(null);
        this.add(this._label);
    }
    
    public void setSize() {
        int height = 0;
        int width = 0;
        int lineWidth = 0;
        int lines = 0;
        final FontMetrics fm = this.getFontMetrics(this._label.getFont());
        final BufferedReader br = new BufferedReader(new StringReader(this._label.getText()));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                lineWidth = SwingUtilities.computeStringWidth(fm, line);
                width = ((lineWidth > width) ? lineWidth : width);
                ++lines;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        lines = ((lines < 1) ? 1 : lines);
        height = fm.getHeight() * lines;
        width = ((width < 12) ? 24 : (width + 12));
        height = ((height < 12) ? 24 : (height + 12));
        this.setPreferredSize(new Dimension(width, height));
        this.setSize(width, height);
    }
    
    public String toString() {
        return this._text;
    }
    
    static {
        LabelNode._imgAdd = null;
        LabelNode._imgSubtract = null;
        LabelNode._imgMultiply = null;
        LabelNode._imgDivide = null;
    }
}
