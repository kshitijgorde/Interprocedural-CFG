import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class BitWordPanel extends JPanel implements ActionListener
{
    private ChangeListener listener;
    static final int get_word1 = 1;
    static final int get_word2 = 2;
    static final int get_sum = 3;
    static final int get_overflow = 4;
    private int size;
    private int sign;
    private boolean showLabels;
    private String operator;
    private BitWord word1;
    private BitWord word2;
    private BitWord sum;
    private BitButton overflow;
    private JLabel ovrlabl;
    private JLabel operatorLabel;
    private ImageIcon onImage;
    private ImageIcon offImage;
    private ImageIcon onImage2;
    private ImageIcon onImagePressed2;
    private ImageIcon offImage2;
    private ImageIcon offImagePressed2;
    static /* synthetic */ Class class$TwosComplement;
    
    public BitWordPanel(final String op) {
        this.size = 0;
        this.sign = 0;
        this.showLabels = true;
        this.onImage = createImageIcon("images1/red.gif");
        this.offImage = createImageIcon("images1/offImage.gif");
        this.onImage2 = createImageIcon("images2/onImage.gif");
        this.onImagePressed2 = createImageIcon("images2/onImagePressed.gif");
        this.offImage2 = createImageIcon("images2/offImage.gif");
        this.offImagePressed2 = createImageIcon("images2/offImagePressed.gif");
        this.operator = op;
    }
    
    public void addComponents(final int _size) {
        this.size = _size;
        this.sign = this.size - 1;
        this.removeAll();
        this.setLayout(new GridBagLayout());
        (this.word1 = new BitWord(true, this.size, this.onImage2, this.onImagePressed2, this.offImage2, this.offImagePressed2)).setBackground(this.getBackground());
        this.word1.addActionListener(this);
        (this.word2 = new BitWord(true, this.size, this.onImage2, this.onImagePressed2, this.offImage2, this.offImagePressed2)).setBackground(this.getBackground());
        this.word2.addActionListener(this);
        (this.sum = new BitWord(false, this.size, this.onImage2, this.onImagePressed2, this.offImage2, this.offImagePressed2)).setBackground(this.getBackground());
        this.sum.addActionListener(this);
        final GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        this.add(this.word1, c);
        c.gridy = 1;
        this.add(this.word2, c);
        c.gridy = 3;
        this.add(this.sum, c);
        if (this.operator != null) {
            c.gridx = 0;
            c.gridy = 1;
            c.anchor = 14;
            c.insets = new Insets(0, 0, 7, 0);
            (this.operatorLabel = new JLabel(this.operator)).setFont(new Font("Dialog", 1, 16));
            this.add(this.operatorLabel, c);
        }
        c.gridy = 3;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = 10;
        this.add(this.overflow = new BitButton(false, this.onImage, this.offImage, null, null), c);
        this.overflow.addActionListener(this);
        c.anchor = 18;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 4;
        (this.ovrlabl = new JLabel("Overflow")).setHorizontalAlignment(0);
        this.add(this.ovrlabl, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = 2;
        this.add(new JSeparator(), c);
        this.revalidate();
    }
    
    public void showLabels() {
        this.word1.setLabel();
        this.word2.setLabel();
        this.sum.setLabel();
    }
    
    public void clearLabels() {
        this.word1.setLabel("");
        this.word2.setLabel("");
        this.sum.setLabel("");
    }
    
    public int getSumValue() {
        return this.word1.getValue() + this.word2.getValue();
    }
    
    public void setOperator(final String op) {
        this.operator = op;
        this.operatorLabel.setText(this.operator);
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
            case 4: {
                this.overflow.setState(state);
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
            case 4: {
                this.overflow.setActionable(st);
                break;
            }
        }
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
        if (this.overflow != null) {
            this.overflow.setBackground(c);
        }
    }
    
    public void clearWords() {
        for (int i = 0; i < this.size; ++i) {
            this.word1.setBit(i, false);
            this.word2.setBit(i, false);
            this.sum.setBit(i, false);
        }
        this.overflow.setState(false);
        this.showLabels();
    }
    
    public int getWordSize() {
        return this.size;
    }
    
    public void isShowingLabels(final boolean _showLabels) {
        this.showLabels = _showLabels;
    }
    
    protected static ImageIcon createImageIcon(final String path) {
        final URL imgURL = ((BitWordPanel.class$TwosComplement == null) ? (BitWordPanel.class$TwosComplement = class$("TwosComplement")) : BitWordPanel.class$TwosComplement).getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        }
        System.err.println("Couldn't find file: " + path);
        return null;
    }
    
    public void addChangeListener(final ChangeListener _listener) {
        this.listener = _listener;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.overflow.getSelectable()) {
            this.overflow.flipState();
        }
        if (this.listener != null) {
            this.listener.stateChanged(new ChangeEvent(this));
        }
        if (this.showLabels) {
            this.showLabels();
        }
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
