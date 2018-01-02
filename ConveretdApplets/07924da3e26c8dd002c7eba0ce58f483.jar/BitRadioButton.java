import java.awt.Graphics;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Icon;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

// 
// Decompiled by Procyon v0.5.30
// 

public class BitRadioButton extends JRadioButton implements BitSource
{
    private ImageIcon _imgOff;
    private ImageIcon _imgOffPressed;
    private ImageIcon _imgOn;
    private ImageIcon _imgOnPressed;
    private UpdateEvent _downStream;
    private boolean _boolTermLeft;
    
    public BitRadioButton() {
        this(false);
    }
    
    public BitRadioButton(final boolean boolRollOver) {
        final ImageLoader iLoader = new ImageLoader();
        final ImageIcon imgOff = iLoader.getImageIcon("images/offImage.gif");
        final ImageIcon imgOffPressed = iLoader.getImageIcon("images/offImagePressed.gif");
        final ImageIcon imgOn = iLoader.getImageIcon("images/onImage.gif");
        final ImageIcon imgOnPressed = iLoader.getImageIcon("images/onImagePressed.gif");
        this.pConstruct(boolRollOver, imgOff, imgOffPressed, imgOn, imgOnPressed);
    }
    
    public BitRadioButton(final boolean boolRollOver, final ImageIcon imgOff, final ImageIcon imgOffPressed, final ImageIcon imgOn, final ImageIcon imgOnPressed) {
        this.pConstruct(boolRollOver, imgOff, imgOffPressed, imgOn, imgOnPressed);
    }
    
    protected void pConstruct(final boolean boolRollOver, final ImageIcon imgOff, final ImageIcon imgOffPressed, final ImageIcon imgOn, final ImageIcon imgOnPressed) {
        this._imgOff = imgOff;
        this._imgOffPressed = imgOffPressed;
        this._imgOn = imgOn;
        this._imgOnPressed = imgOnPressed;
        this._boolTermLeft = false;
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setPreferredSize(new Dimension(32, 32));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setHorizontalAlignment(0);
        this.setHorizontalTextPosition(0);
        this.setVisible(true);
        this.setVerticalAlignment(0);
        this.setText("");
        this.setIconTextGap(0);
        this.setVerticalTextPosition(0);
        this.setSelectedIcon(this._imgOn);
        this.setDisabledSelectedIcon(this._imgOn);
        this.setIcon(this._imgOff);
        this.setDisabledIcon(this._imgOff);
        if (this._imgOff != null) {
            this.setSize(this._imgOff.getIconWidth() + 2, this._imgOff.getIconHeight() + 2);
        }
        if (boolRollOver) {
            this.setRolloverIcon(this._imgOffPressed);
            this.setRolloverSelectedIcon(this._imgOnPressed);
        }
        this.setPressedIcon(this._imgOffPressed);
        this.addPropertyChangeListener("pressedIcon", new PropertyChangeListener() {
            public void propertyChange(final PropertyChangeEvent e) {
                if (BitRadioButton.this._downStream != null) {
                    BitRadioButton.this._downStream.update();
                }
            }
        });
    }
    
    public void setDownStream(final UpdateEvent downStream) {
        this._downStream = downStream;
    }
    
    public void setSelected(final boolean selected) {
        super.setSelected(selected);
        if (this._downStream != null) {
            this._downStream.update();
        }
    }
    
    public void setTermLeft(final boolean boolTermLeft) {
        this._boolTermLeft = boolTermLeft;
    }
    
    public int getValue() {
        return this.isSelected() ? 1 : 0;
    }
    
    public void setValue(final int value) {
        this.setSelected(value != 0);
    }
    
    public Point getTerminal() {
        final Point point = new Point();
        if (this._boolTermLeft) {
            point.x = this.getX() - 1;
            point.y = this.getY() + this.getHeight() / 2;
        }
        else {
            point.x = this.getX() + this.getWidth() + 1;
            point.y = this.getY() + this.getHeight() / 2;
        }
        return point;
    }
    
    public void setTerminal(final Point point) {
        if (this._boolTermLeft) {
            this.setLocation(point.x + 1, point.y - this.getHeight() / 2);
        }
        else {
            this.setLocation(point.x - this.getWidth() - 1, point.y - this.getHeight() / 2);
        }
    }
    
    protected void paintComponent(final Graphics g) {
        if (this.isSelected()) {
            if (this.getPressedIcon() == null || !this.getPressedIcon().equals(this._imgOnPressed)) {
                this.setPressedIcon(this._imgOnPressed);
            }
        }
        else if (this.getPressedIcon() == null || !this.getPressedIcon().equals(this._imgOffPressed)) {
            this.setPressedIcon(this._imgOffPressed);
        }
        super.paintComponent(g);
    }
}
