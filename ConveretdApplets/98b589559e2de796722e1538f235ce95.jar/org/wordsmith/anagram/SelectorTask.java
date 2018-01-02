// 
// Decompiled by Procyon v0.5.30
// 

package org.wordsmith.anagram;

import java.util.TimerTask;

public class SelectorTask extends TimerTask
{
    private final ShowBannerTask myShowBannerTask;
    private final AnimationTask myAnimationTask;
    private boolean myShowAnimation;
    private int myBannerFrameIndex;
    private final int myFramesForBanner;
    
    public SelectorTask(final AnimationTask myAnimationTask, final ShowBannerTask myShowBannerTask, final int myFramesForBanner) {
        this.myBannerFrameIndex = 0;
        this.myAnimationTask = myAnimationTask;
        this.myShowBannerTask = myShowBannerTask;
        this.myFramesForBanner = myFramesForBanner;
        this.myShowAnimation = true;
    }
    
    public void run() {
        if (this.myShowAnimation) {
            if (this.myAnimationTask.advanceAnimation()) {
                this.myBannerFrameIndex = 0;
                this.myShowAnimation = (this.myFramesForBanner == 0);
            }
        }
        else {
            this.myShowBannerTask.run();
            if (++this.myBannerFrameIndex >= this.myFramesForBanner) {
                this.myBannerFrameIndex = 0;
                this.myShowAnimation = true;
            }
        }
    }
}
