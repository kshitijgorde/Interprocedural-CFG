// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Image;

public class ButtonSprite extends AnimateSprite
{
    public static final int DISABLED = 0;
    public static final int NORMAL = 1;
    public static final int ROLLOVER = 2;
    protected int m_nState;
    protected int[] m_nStates;
    
    public ButtonSprite(final Image img, final int nStatesCol, final int nStatesRow, final int nOverState, final int nNormalState, final int nDisabledState) {
        super(img, nStatesCol, nStatesRow, null);
        this.m_nStates = new int[3];
        this.m_nState = 0;
        this.configStates(nOverState, nNormalState, nDisabledState);
        super.m_bVisible = false;
        super.m_bVisibleNext = false;
    }
    
    public ButtonSprite(final Image img, final int nStates, final int nOverState, final int nNormalState, final int nDisabledState) {
        super(img, 1, nStates, null);
        this.m_nStates = new int[3];
        this.m_nState = 0;
        this.configStates(nOverState, nNormalState, nDisabledState);
        super.m_bVisible = false;
        super.m_bVisibleNext = false;
    }
    
    public ButtonSprite() {
        this.m_nStates = new int[3];
        this.m_nState = 0;
        super.m_bVisible = false;
        super.m_bVisibleNext = false;
    }
    
    public void configStates(final int nOverState, final int nNormalState, final int nDisabledState) {
        this.m_nStates[2] = nOverState;
        this.m_nStates[1] = nNormalState;
        this.m_nStates[0] = nDisabledState;
    }
    
    public void enable(final boolean b) {
        this.setState(b ? 1 : 0);
    }
    
    public boolean enabled() {
        return this.m_nState != 0;
    }
    
    public boolean init(final Image[] imgs, final int[] nParams) {
        this.setImage(imgs[0]);
        this.setPosition(nParams[0], nParams[1]);
        super.m_cols = nParams[2];
        super.m_rows = nParams[3];
        super.m_nNumImages = super.m_rows * super.m_cols;
        if (super.m_image != null) {
            super.m_nWidth = super.m_image.getWidth(null) / super.m_cols;
            super.m_nHeight = super.m_image.getHeight(null) / super.m_rows;
        }
        this.configStates(nParams[4], nParams[5], nParams[6]);
        if (nParams.length >= 8) {
            this.setState(nParams[7]);
        }
        return true;
    }
    
    public boolean onMouseMove(final int mousex, final int mousey) {
        if (!this.enabled()) {
            return false;
        }
        if (this.hittest(mousex, mousey)) {
            this.setState(2);
            return true;
        }
        this.setState(1);
        return false;
    }
    
    public void setState(final int nState) {
        final int nFrame = this.m_nStates[nState];
        if (nFrame == -1) {
            this.show(false);
        }
        else {
            this.setFrame(nFrame);
            if (!this.visible()) {
                this.show(true);
            }
        }
        this.m_nState = nState;
    }
}
