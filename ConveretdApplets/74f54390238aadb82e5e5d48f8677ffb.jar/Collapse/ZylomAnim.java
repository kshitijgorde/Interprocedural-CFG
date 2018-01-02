// 
// Decompiled by Procyon v0.5.30
// 

package Collapse;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.applet.Applet;

public class ZylomAnim
{
    static final int STATE_COBRAND_OFF = 4;
    static final int STATE_COBRAND_ON = 2;
    static final int STATE_COBRAND_WAIT = 3;
    static final int STATE_DONE = 5;
    static final int STATE_INIT = 1;
    static final int STATE_LOADING = 0;
    Applet mApplet;
    Image mCbimage;
    int mCbimageHeight;
    int mCbimageWidth;
    Image mCburlimage;
    String mCobrandImageName;
    int mCobrandOffset;
    String mCobrandUrlImageName;
    int mDelay;
    int mHeight;
    ImageObserver mObserver;
    int mState;
    int mWidth;
    
    public ZylomAnim(final Applet a, final String cbimage, final String cburlimage) {
        this.mState = 0;
        this.mCbimage = null;
        this.mCburlimage = null;
        this.mCbimageHeight = 100;
        this.mCbimageWidth = 235;
        final MediaTracker tracker = new MediaTracker(a);
        try {
            this.mApplet = a;
            this.mState = 0;
            tracker.addImage(this.mCbimage = a.getImage(a.getCodeBase(), cbimage), 100);
            tracker.waitForID(100);
            tracker.addImage(this.mCburlimage = a.getImage(a.getCodeBase(), cburlimage), 101);
            tracker.waitForID(101);
            this.mHeight = a.size().height;
            this.mWidth = a.size().width;
            this.mState = 1;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void Draw(final Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, this.mWidth, this.mHeight);
        final int aCenterX = this.mWidth / 2;
        final int aCenterY = this.mHeight / 2;
        if (this.mState == 2 || this.mState == 3 || this.mState == 4) {
            if (this.mCburlimage != null) {
                g.drawImage(this.mCburlimage, aCenterX - this.mCburlimage.getWidth(null) / 2, this.mHeight - (20 + this.mCburlimage.getHeight(null)), null);
            }
            g.drawImage(this.mCbimage, aCenterX - this.mCbimageWidth / 2, this.mCobrandOffset - this.mCbimageHeight / 2, null);
        }
    }
    
    public boolean IsDone() {
        return this.mState == 5;
    }
    
    public boolean IsLoaded() {
        return this.mState != 0;
    }
    
    public void Update() {
        switch (this.mState) {
            case 1: {
                if (this.mCbimage != null) {
                    this.mCobrandOffset = this.mHeight + this.mCbimageHeight / 2;
                    this.mState = 2;
                    break;
                }
                this.mState = 5;
                break;
            }
            case 2: {
                this.mCobrandOffset -= 8;
                if (this.mCobrandOffset < this.mHeight / 2) {
                    this.mCobrandOffset = this.mHeight / 2;
                    this.mDelay = 100;
                    this.mState = 3;
                    break;
                }
                break;
            }
            case 3: {
                if (this.mDelay > 0) {
                    --this.mDelay;
                    break;
                }
                this.mState = 4;
                break;
            }
            case 4: {
                this.mCobrandOffset -= 8;
                if (this.mCobrandOffset < -this.mCbimageHeight / 2) {
                    this.mState = 5;
                    break;
                }
                break;
            }
        }
    }
}
