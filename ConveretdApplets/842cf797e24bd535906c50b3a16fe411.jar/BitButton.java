import javax.swing.Icon;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;

// 
// Decompiled by Procyon v0.5.30
// 

public class BitButton extends JButton
{
    static final boolean offState = false;
    static final boolean onState = true;
    private ImageIcon offImage;
    private ImageIcon offImagePressed;
    private ImageIcon onImage;
    private ImageIcon onImagePressed;
    private boolean state;
    private boolean isSelectable;
    
    public BitButton(final boolean _sel, final ImageIcon _onImage, final ImageIcon _offImage, final ImageIcon _onImagePressed, final ImageIcon _offImagePressed) {
        this.state = false;
        this.isSelectable = true;
        this.onImage = _onImage;
        this.onImagePressed = _onImagePressed;
        this.offImage = _offImage;
        this.offImagePressed = _offImagePressed;
        this.isSelectable = _sel;
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setPreferredSize(new Dimension(32, 32));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setIcon(this.offImage);
        if (this.isSelectable) {
            this.setPressedIcon(this.offImagePressed);
        }
        else {
            this.setPressedIcon(this.offImage);
        }
    }
    
    public void setActionable(final boolean st) {
        this.isSelectable = st;
        if (this.isSelectable) {
            if (this.state) {
                this.setPressedIcon(this.onImagePressed);
            }
            else {
                this.setPressedIcon(this.offImagePressed);
            }
        }
        else if (this.state) {
            this.setPressedIcon(this.onImage);
        }
        else {
            this.setPressedIcon(this.offImage);
        }
    }
    
    public void flipState() {
        if (!this.state) {
            this.setIcon(this.onImage);
            if (this.isSelectable) {
                this.setPressedIcon(this.onImagePressed);
            }
            else {
                this.setPressedIcon(this.onImage);
            }
            this.state = true;
        }
        else {
            this.setIcon(this.offImage);
            if (this.isSelectable) {
                this.setPressedIcon(this.offImagePressed);
            }
            else {
                this.setPressedIcon(this.offImage);
            }
            this.state = false;
        }
    }
    
    public boolean getSelectable() {
        return this.isSelectable;
    }
    
    public void setState(final boolean st) {
        if (this.state != st) {
            this.flipState();
        }
    }
    
    public int value() {
        if (this.state) {
            return 1;
        }
        return 0;
    }
}
