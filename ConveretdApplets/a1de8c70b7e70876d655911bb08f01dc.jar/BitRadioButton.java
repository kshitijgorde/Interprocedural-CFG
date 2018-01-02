import java.awt.Graphics;
import javax.swing.Icon;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

// 
// Decompiled by Procyon v0.5.30
// 

public class BitRadioButton extends JRadioButton
{
    private ImageIcon _imgOff;
    private ImageIcon _imgOffPressed;
    private ImageIcon _imgOn;
    private ImageIcon _imgOnPressed;
    private RadioEvent _gui;
    
    public BitRadioButton(final RadioEvent gui, final boolean boolRollOver, final ImageIcon imgOff, final ImageIcon imgOffPressed, final ImageIcon imgOn, final ImageIcon imgOnPressed) {
        this._gui = gui;
        this._imgOff = imgOff;
        this._imgOffPressed = imgOffPressed;
        this._imgOn = imgOn;
        this._imgOnPressed = imgOnPressed;
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
    }
    
    protected void paintComponent(final Graphics g) {
        if (this.isSelected()) {
            if (this.getPressedIcon() == null || !this.getPressedIcon().equals(this._imgOnPressed)) {
                this.setPressedIcon(this._imgOnPressed);
                this._gui.update();
            }
        }
        else if (this.getPressedIcon() == null || !this.getPressedIcon().equals(this._imgOffPressed)) {
            this.setPressedIcon(this._imgOffPressed);
            this._gui.update();
        }
        super.paintComponent(g);
    }
    
    public int getValue() {
        return this.isSelected() ? 1 : 0;
    }
    
    public void setValue(final int value) {
        if (value == 0) {
            this.setSelected(false);
        }
        else {
            this.setSelected(true);
        }
    }
}
