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
    protected int[] m_nStates;
    protected int m_nState;
    
    public void setState(final int nState) {
        final int frame = this.m_nStates[nState];
        if (frame == -1) {
            this.show(false);
        }
        else {
            this.setFrame(frame);
            if (!this.visible()) {
                this.show(true);
            }
        }
        this.m_nState = nState;
    }
    
    public ButtonSprite() {
        this.m_nStates = new int[3];
        this.m_nState = 0;
        super.m_bVisible = false;
        super.m_bVisibleNext = false;
    }
    
    public ButtonSprite(final Image image, final int n, final int n2, final int n3, final int n4) {
        super(image, 1, n, null);
        this.m_nStates = new int[3];
        this.m_nState = 0;
        this.configStates(n2, n3, n4);
        super.m_bVisible = false;
        super.m_bVisibleNext = false;
    }
    
    public ButtonSprite(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        super(image, n, n2, null);
        this.m_nStates = new int[3];
        this.m_nState = 0;
        this.configStates(n3, n4, n5);
        super.m_bVisible = false;
        super.m_bVisibleNext = false;
    }
    
    public void enable(final boolean state) {
        this.setState(state ? 1 : 0);
    }
    
    public void configStates(final int n, final int n2, final int n3) {
        this.m_nStates[2] = n;
        this.m_nStates[1] = n2;
        this.m_nStates[0] = n3;
    }
    
    public boolean init(final Image[] array, final int[] array2) {
        this.setImage(array[0]);
        this.setPosition(array2[0], array2[1]);
        super.m_cols = array2[2];
        super.m_rows = array2[3];
        super.m_nNumImages = super.m_rows * super.m_cols;
        if (super.m_image != null) {
            super.m_nWidth = super.m_image.getWidth(null) / super.m_cols;
            super.m_nHeight = super.m_image.getHeight(null) / super.m_rows;
        }
        this.configStates(array2[4], array2[5], array2[6]);
        if (array2.length >= 8) {
            this.setState(array2[7]);
        }
        return true;
    }
    
    public boolean enabled() {
        return this.m_nState != 0;
    }
    
    public boolean onMouseMove(final int n, final int n2) {
        if (!this.enabled()) {
            return false;
        }
        if (this.hittest(n, n2)) {
            this.setState(2);
            return true;
        }
        this.setState(1);
        return false;
    }
}
