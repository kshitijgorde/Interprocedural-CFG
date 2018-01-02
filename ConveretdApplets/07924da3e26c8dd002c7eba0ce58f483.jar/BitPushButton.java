import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class BitPushButton extends BitRadioButton implements MouseListener
{
    private boolean _boolPressed;
    private boolean _boolDefault;
    
    public BitPushButton() {
        this(false, false);
    }
    
    public BitPushButton(final boolean boolRollOver, final boolean boolDefault) {
        this._boolDefault = true;
        final ImageLoader iLoader = new ImageLoader();
        final ImageIcon imgOff = iLoader.getImageIcon("images/pushOff.gif");
        final ImageIcon imgOffPressed = iLoader.getImageIcon("images/pushOffPressed.gif");
        final ImageIcon imgOn = iLoader.getImageIcon("images/pushOn.gif");
        final ImageIcon imgOnPressed = iLoader.getImageIcon("images/pushOnPressed.gif");
        this.pConstruct(boolRollOver, imgOff, imgOffPressed, imgOn, imgOnPressed);
        this._boolPressed = false;
        this.setSelected(this._boolDefault = boolDefault);
        this.addMouseListener(this);
    }
    
    public BitPushButton(final boolean boolRollOver, final boolean boolDefault, final ImageIcon imgOff, final ImageIcon imgOffPressed, final ImageIcon imgOn, final ImageIcon imgOnPressed) {
        this._boolDefault = true;
        this.pConstruct(boolRollOver, imgOff, imgOffPressed, imgOn, imgOnPressed);
        this._boolPressed = false;
        this._boolDefault = boolDefault;
        this.addMouseListener(this);
    }
    
    public void mouseClicked(final MouseEvent e) {
        this.setSelected(this._boolDefault);
    }
    
    public void mouseEntered(final MouseEvent e) {
        if (this._boolPressed) {
            this.setSelected(!this._boolDefault);
        }
    }
    
    public void mouseExited(final MouseEvent e) {
        this.setSelected(this._boolDefault);
    }
    
    public void mousePressed(final MouseEvent e) {
        this._boolPressed = true;
        this.setSelected(!this._boolDefault);
    }
    
    public void mouseReleased(final MouseEvent e) {
        this._boolPressed = false;
        this.setSelected(this._boolDefault);
    }
}
