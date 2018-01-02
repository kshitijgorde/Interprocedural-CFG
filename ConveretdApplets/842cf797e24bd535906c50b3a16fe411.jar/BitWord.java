import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class BitWord extends JPanel implements ActionListener
{
    private ChangeListener listener;
    private ImageIcon onImage;
    private ImageIcon onImagePressed;
    private ImageIcon offImage;
    private ImageIcon offImagePressed;
    private BitButton[] word;
    private int size;
    private boolean isSelectable;
    
    public BitWord(final boolean _isSelectable, final int _size, final ImageIcon img1, final ImageIcon img2, final ImageIcon img3, final ImageIcon img4) {
        this.isSelectable = _isSelectable;
        this.size = _size;
        this.onImage = img1;
        this.onImagePressed = img2;
        this.offImage = img3;
        this.offImagePressed = img4;
        if (this.onImage == null || this.onImagePressed == null || this.offImage == null || this.offImagePressed == null) {
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
    }
    
    public int getValue() {
        int val = 0;
        int place = 1;
        for (int i = 0; i < this.size; ++i) {
            val += this.word[i].value() * place;
            place *= 2;
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
        if (bit < this.size && bit >= 0) {
            this.word[bit].setState(state);
        }
    }
    
    public void setValue(int value) {
        if (value >= 0 && value < Math.pow(2.0, this.size)) {
            int place = (int)Math.pow(2.0, this.size - 1);
            for (int i = this.size - 1; i >= 0; --i) {
                if (value / place >= 1) {
                    this.word[i].setState(true);
                    value -= place;
                }
                else {
                    this.word[i].setState(false);
                }
                place /= 2;
            }
        }
    }
    
    public void addChangeListener(final ChangeListener l) {
        this.listener = l;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.isSelectable) {
            ((BitButton)actionEvent.getSource()).flipState();
            if (this.listener != null) {
                this.listener.stateChanged(new ChangeEvent(this));
            }
        }
    }
}
