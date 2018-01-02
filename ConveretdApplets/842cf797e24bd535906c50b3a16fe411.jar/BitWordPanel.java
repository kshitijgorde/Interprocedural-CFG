import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class BitWordPanel extends JPanel
{
    private ChangeListener listener;
    static final int get_word1 = 1;
    static final int get_word2 = 2;
    static final int get_sum = 3;
    static final int get_overflow = 4;
    private int size;
    private String operator;
    private BitWord word1;
    private BitWord word2;
    private BitWord sum;
    private JLabel operatorLabel;
    private ImageIcon onImage2;
    private ImageIcon onImagePressed2;
    private ImageIcon offImage2;
    private ImageIcon offImagePressed2;
    
    public BitWordPanel(final String op) {
        this.size = 0;
        this.operatorLabel = new JLabel("");
        this.onImage2 = Toolbox.createImageIcon("images/onImage.gif");
        this.onImagePressed2 = Toolbox.createImageIcon("images/onImagePressed.gif");
        this.offImage2 = Toolbox.createImageIcon("images/offImage.gif");
        this.offImagePressed2 = Toolbox.createImageIcon("images/offImagePressed.gif");
        this.operator = op;
    }
    
    public void addComponents(final int _size) {
        this.size = _size;
        this.removeAll();
        this.setLayout(new GridBagLayout());
        (this.word1 = new BitWord(true, this.size, this.onImage2, this.onImagePressed2, this.offImage2, this.offImagePressed2)).setBackground(this.getBackground());
        (this.word2 = new BitWord(true, this.size, this.onImage2, this.onImagePressed2, this.offImage2, this.offImagePressed2)).setBackground(this.getBackground());
        (this.sum = new BitWord(false, this.size, this.onImage2, this.onImagePressed2, this.offImage2, this.offImagePressed2)).setBackground(this.getBackground());
        final GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        this.add(this.word1, c);
        c.gridy = 1;
        this.add(this.word2, c);
        c.gridy = 3;
        this.add(this.sum, c);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = 14;
        c.insets = new Insets(0, 0, 3, 0);
        if (this.operator != null) {
            this.operatorLabel.setText(this.operator);
        }
        else {
            this.operatorLabel.setText("");
        }
        this.operatorLabel.setFont(new Font("Dialog", 1, 16));
        this.operatorLabel.setPreferredSize(new Dimension(50, 15));
        this.operatorLabel.setHorizontalTextPosition(4);
        this.add(this.operatorLabel, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = 2;
        this.add(new JSeparator(), c);
        if (this.listener != null) {
            this.addChangeListenerToWords(this.listener);
        }
        this.revalidate();
    }
    
    public void addChangeListenerToWords(final ChangeListener l) {
        this.listener = l;
        this.word1.addChangeListener(l);
        this.word2.addChangeListener(l);
        this.sum.addChangeListener(l);
    }
    
    public int getSumValue() {
        return this.word1.getValue() + this.word2.getValue();
    }
    
    public void setOperatorName(final String op) {
        this.operator = op;
        this.operatorLabel.setText(this.operator);
    }
    
    public void setBackground(final Color c) {
        super.setBackground(c);
        if (this.word1 != null) {
            this.word1.setBackground(c);
        }
        if (this.word2 != null) {
            this.word2.setBackground(c);
        }
        if (this.sum != null) {
            this.sum.setBackground(c);
        }
    }
    
    public int getWordValue(final int comp) {
        switch (comp) {
            case 1: {
                return this.word1.getValue();
            }
            case 2: {
                return this.word2.getValue();
            }
            case 3: {
                return this.sum.getValue();
            }
            default: {
                return 0;
            }
        }
    }
    
    public void setWordBit(final int comp, final int index, final boolean state) {
        switch (comp) {
            case 1: {
                this.word1.setBit(index, state);
                break;
            }
            case 2: {
                this.word2.setBit(index, state);
                break;
            }
            case 3: {
                this.sum.setBit(index, state);
                break;
            }
        }
    }
    
    public void setWordValue(final int comp, final int value) {
        switch (comp) {
            case 1: {
                this.word1.setValue(value);
                break;
            }
            case 2: {
                this.word2.setValue(value);
                break;
            }
            case 3: {
                this.sum.setValue(value);
                break;
            }
        }
    }
    
    public void setActionable(final int comp, final boolean st) {
        switch (comp) {
            case 1: {
                this.word1.setActionable(st);
                break;
            }
            case 2: {
                this.word2.setActionable(st);
                break;
            }
            case 3: {
                this.sum.setActionable(st);
                break;
            }
        }
    }
    
    public void clearWords() {
        this.word1.setValue(0);
        this.word2.setValue(0);
        this.sum.setValue(0);
    }
    
    public int getWordSize() {
        return this.size;
    }
    
    public int getMaxWordValue() {
        return (2 << this.size - 1) - 1;
    }
    
    public void addChangeListener(final ChangeListener _listener) {
        this.listener = _listener;
    }
}
