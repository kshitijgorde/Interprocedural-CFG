// 
// Decompiled by Procyon v0.5.30
// 

package mika.graphics;

import java.awt.Panel;
import mika.system.S_Debug;
import java.awt.Component;
import java.awt.Image;

public final class G_Animation
{
    private Image[] m_image;
    private int m_imageCnt;
    private final int ARRAY_GROW_SIZE = 10;
    private float m_startTime;
    private int m_currentFrame;
    private int m_fps;
    private boolean m_bLoop;
    
    public G_Animation() {
        this.m_image = null;
        this.m_imageCnt = 0;
        this.m_startTime = 0.0f;
        this.m_currentFrame = 0;
        this.m_fps = 0;
        this.m_bLoop = false;
    }
    
    public G_Animation(final Component component, final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.m_image = null;
        this.m_imageCnt = 0;
        this.m_startTime = 0.0f;
        this.m_currentFrame = 0;
        this.m_fps = 0;
        this.m_bLoop = false;
        this.construct(component, image, n, n2, n3, n4, n5, n6);
    }
    
    public G_Animation(final Component component, final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.m_image = null;
        this.m_imageCnt = 0;
        this.m_startTime = 0.0f;
        this.m_currentFrame = 0;
        this.m_fps = 0;
        this.m_bLoop = false;
        this.construct(component, image, n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public G_Animation(final Component component, final Image image, final int n, final int n2, final int n3, final int n4, final Image image2, final int n5, final int n6, final int n7, final int n8) {
        this.m_image = null;
        this.m_imageCnt = 0;
        this.m_startTime = 0.0f;
        this.m_currentFrame = 0;
        this.m_fps = 0;
        this.m_bLoop = false;
        this.construct(component, image, n, n2, n3, n4, image2, n5, n6, n7, n8);
    }
    
    public G_Animation(final Component component, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.m_image = null;
        this.m_imageCnt = 0;
        this.m_startTime = 0.0f;
        this.m_currentFrame = 0;
        this.m_fps = 0;
        this.m_bLoop = false;
        this.construct(component, n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public G_Animation(final G_Animation g_Animation) {
        this.m_image = null;
        this.m_imageCnt = 0;
        this.m_startTime = 0.0f;
        this.m_currentFrame = 0;
        this.m_fps = 0;
        this.m_bLoop = false;
        this.construct(g_Animation);
    }
    
    public void construct(final Component component, final Image image, final int n, final int n2, final int n3, final int n4, final int imageCnt, final int n5) {
        try {
            this.m_imageCnt = imageCnt;
            this.m_image = new Image[imageCnt];
            for (int n6 = n, i = 0; i < imageCnt; ++i, n6 += n3 + n5) {
                this.m_image[i] = G_ImageTools.extractImage(component, image, n6, n2, n3, n4);
            }
        }
        catch (Exception ex) {
            S_Debug.print(ex);
        }
    }
    
    public void construct(final Component component, final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int imageCnt, final int n7) {
        try {
            this.m_imageCnt = imageCnt;
            this.m_image = new Image[imageCnt];
            for (int n8 = 0, i = 0; i < imageCnt; ++i, n8 += n3 + n7) {
                this.m_image[i] = G_ImageTools.extractImage(component, image, n + n8, n2, n3, n4, n5 + n8, n6);
            }
        }
        catch (Exception ex) {
            S_Debug.print(ex);
        }
    }
    
    public void construct(final Component component, final Image image, final int n, final int n2, final int n3, final int n4, final Image image2, final int n5, final int n6, final int imageCnt, final int n7) {
        try {
            this.m_imageCnt = imageCnt;
            this.m_image = new Image[imageCnt];
            for (int n8 = 0, i = 0; i < imageCnt; ++i, n8 += n3 + n7) {
                this.m_image[i] = G_ImageTools.extractImage(component, image, n + n8, n2, n3, n4, image2, n5 + n8, n6);
            }
        }
        catch (Exception ex) {
            S_Debug.print(ex);
        }
    }
    
    public void construct(final Component component, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int imageCnt, final int n7) {
        try {
            this.m_imageCnt = imageCnt;
            this.m_image = new Image[imageCnt];
            for (int n8 = 0, i = 0; i < imageCnt; ++i, n8 += n3 + n7) {
                this.m_image[i] = G_ImageTools.extractImage(component, n + n8, n2, n3, n4, n5 + n8, n6);
            }
        }
        catch (Exception ex) {
            S_Debug.print(ex);
        }
    }
    
    public void construct(final G_Animation g_Animation) {
        this.m_image = g_Animation.m_image;
        this.m_imageCnt = g_Animation.m_imageCnt;
        this.m_fps = g_Animation.m_fps;
        this.m_bLoop = g_Animation.m_bLoop;
        this.m_startTime = 0.0f;
        this.m_currentFrame = 0;
    }
    
    public void addFrame(final Image image) {
        try {
            if (this.m_image == null || this.m_image.length <= this.m_imageCnt) {
                final Image[] image2 = new Image[this.m_imageCnt + 10];
                if (this.m_image != null) {
                    System.arraycopy(this.m_image, 0, image2, 0, this.m_image.length);
                }
                this.m_image = image2;
            }
            this.m_image[this.m_imageCnt] = image;
            ++this.m_imageCnt;
        }
        catch (Exception ex) {
            S_Debug.print(ex);
        }
    }
    
    public void appendFrames(final Component component, final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            this.m_imageCnt += n5;
            if (this.m_image == null || this.m_image.length <= this.m_imageCnt) {
                final Image[] image2 = new Image[this.m_imageCnt];
                if (this.m_image != null) {
                    System.arraycopy(this.m_image, 0, image2, 0, this.m_image.length);
                }
                this.m_image = image2;
            }
            for (int n7 = 0, i = this.m_imageCnt - n5; i < this.m_imageCnt; ++i, n7 += n3 + n6) {
                this.m_image[i] = G_ImageTools.extractImage(component, image, n + n7, n2, n3, n4);
            }
        }
        catch (Exception ex) {
            S_Debug.print(ex);
        }
    }
    
    public void appendFrames(final Image image, final int n, final int n2, final int n3, final int n4, final Image image2, final int n5, final int n6, final int n7, final int n8) {
        try {
            this.m_imageCnt += n7;
            if (this.m_image == null || this.m_image.length <= this.m_imageCnt) {
                final Image[] image3 = new Image[this.m_imageCnt];
                if (this.m_image != null) {
                    System.arraycopy(this.m_image, 0, image3, 0, this.m_image.length);
                }
                this.m_image = image3;
            }
            final Panel panel = new Panel();
            for (int n9 = 0, i = this.m_imageCnt - n7; i < this.m_imageCnt; ++i, n9 += n3 + n8) {
                this.m_image[i] = G_ImageTools.extractImage(panel, image, n + n9, n2, n3, n4, image2, n5 + n9, n6);
            }
        }
        catch (Exception ex) {
            S_Debug.print(ex);
        }
    }
    
    public int getImageCount() {
        return this.m_imageCnt;
    }
    
    public Image getImage(final int n) {
        return this.m_image[n];
    }
    
    public void setFps(final int fps) {
        this.m_fps = fps;
    }
    
    public int getFps() {
        return this.m_fps;
    }
    
    public Image getCurrentImage() {
        return this.m_image[this.m_currentFrame];
    }
    
    public int getCurrentFrame() {
        return this.m_currentFrame;
    }
    
    public void setLoop(final boolean bLoop) {
        this.m_bLoop = bLoop;
    }
    
    public boolean isLooping() {
        return this.m_bLoop;
    }
    
    public void setStartTime(final float startTime) {
        this.m_startTime = startTime;
    }
    
    public void animate(final float n) {
        final int n2 = (int)((n - this.m_startTime) * this.m_fps);
        if (!this.m_bLoop && n2 >= this.m_imageCnt) {
            this.m_currentFrame = this.m_imageCnt - 1;
        }
        else {
            this.m_currentFrame = n2 % this.m_imageCnt;
        }
    }
}
