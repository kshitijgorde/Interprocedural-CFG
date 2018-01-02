import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import java.awt.Insets;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class BitWord extends JPanel implements ActionListener
{
    private ActionListener listener;
    private ImageIcon onImage;
    private ImageIcon onImagePressed;
    private ImageIcon offImage;
    private ImageIcon offImagePressed;
    private BitButton[] word;
    private JTextField wordlabel;
    private int size;
    private int sign;
    private boolean isSelectable;
    
    public BitWord(final boolean _isSelectable, final int _size, final ImageIcon img1, final ImageIcon img2, final ImageIcon img3, final ImageIcon img4) {
        this.isSelectable = _isSelectable;
        this.size = _size;
        this.sign = this.size - 1;
        this.onImage = img1;
        this.onImagePressed = img2;
        this.offImage = img3;
        this.offImagePressed = img4;
        if (this.onImage == null || this.offImage == null) {
            System.err.println("One of the Images did not load correctly!");
        }
        else {
            if (this.onImagePressed == null || this.offImagePressed == null) {
                this.onImagePressed = this.onImage;
                this.offImagePressed = this.offImage;
            }
            this.createButtons();
        }
    }
    
    protected void createButtons() {
        this.word = new BitButton[this.size];
        this.setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();
        for (int i = this.size - 1; i >= 0; --i) {
            (this.word[i] = new BitButton(this.isSelectable, this.onImage, this.offImage, this.onImagePressed, this.offImagePressed)).addActionListener(this);
            this.add(this.word[i], c);
        }
        c.insets = new Insets(0, 4, 0, 4);
        (this.wordlabel = this.createProperTextField()).setHorizontalAlignment(11);
        this.wordlabel.setEditable(false);
        this.wordlabel.setOpaque(false);
        this.wordlabel.setBorder(null);
        this.add(this.wordlabel, c);
    }
    
    private JTextField createProperTextField() {
        int cols = 0;
        int num;
        for (num = 0, num = (int)Math.pow(2.0, this.size - 1); num > 0; num /= 10) {
            ++cols;
        }
        ++cols;
        return new JTextField("0", cols);
    }
    
    public int getValue() {
        int val = 0;
        int place = 1;
        if (this.word[this.sign].value() == 0) {
            for (int i = 0; i < this.size; ++i) {
                val += this.word[i].value() * place;
                place *= 2;
            }
        }
        else {
            for (int i = 0; i < this.size; ++i) {
                if (this.word[i].value() != 1) {
                    val += place;
                }
                place *= 2;
            }
            val = ++val * -1;
        }
        return val;
    }
    
    public void setActionable(final boolean sel) {
        this.isSelectable = sel;
        for (int i = 0; i < this.size; ++i) {
            this.word[i].setActionable(sel);
        }
    }
    
    public void setBit(final int bit, final boolean state) {
        if (bit < this.size) {
            this.word[bit].setState(state);
        }
    }
    
    public void setValue(int value) {
        if (value >= 0 && value < Math.pow(2.0, this.size)) {
            int place = (int)Math.pow(2.0, this.size - 2);
            for (int i = this.size - 2; i >= 0; --i) {
                if (value / place >= 1) {
                    this.word[i].setState(true);
                    value -= place;
                }
                else {
                    this.word[i].setState(false);
                }
                place /= 2;
            }
            this.word[this.sign].setState(false);
        }
        else if (value < 0 && value >= -1.0 * Math.pow(2.0, this.size)) {
            int place = (int)Math.pow(2.0, this.size - 1);
            value *= -1;
            --value;
            for (int i = this.size - 1; i >= 0; --i) {
                if (value / place >= 1) {
                    this.word[i].setState(false);
                    value -= place;
                }
                else {
                    this.word[i].setState(true);
                }
                place /= 2;
            }
        }
    }
    
    public void setLabel() {
        this.wordlabel.setText(Integer.toString(this.getValue()));
    }
    
    public void setLabel(final String text) {
        this.wordlabel.setText(text);
    }
    
    public void addActionListener(final ActionListener l) {
        this.listener = l;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.isSelectable) {
            ((BitButton)actionEvent.getSource()).flipState();
            if (this.listener != null) {
                this.listener.actionPerformed(new ActionEvent(this, 0, null));
            }
        }
    }
}
