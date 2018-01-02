// 
// Decompiled by Procyon v0.5.30
// 

package org.wordsmith.anagram;

import javax.swing.SwingUtilities;
import java.util.TimerTask;

public class AnimationTask
{
    private final AnagramCanvas myCanvas;
    private final int myAnimationFrames;
    private final int myPauseFrames;
    private final int myTotalFrames;
    private final boolean myShouldReverse;
    private final boolean myShouldRepeat;
    private volatile int myFrameIndex;
    private volatile boolean myIsForward;
    private TimerTask myTask;
    private final Runnable myPaintFrameCommand;
    
    public AnimationTask(final AnagramCanvas myCanvas, final int myAnimationFrames, final int myPauseFrames, final Configuration configuration) {
        this.myPaintFrameCommand = new Runnable() {
            public void run() {
                AnimationTask.this.myCanvas.paintAnimationFrame(AnimationTask.this.myIsForward ? AnimationTask.this.myFrameIndex : (AnimationTask.this.myFrameIndex - AnimationTask.this.myPauseFrames));
            }
        };
        this.myAnimationFrames = myAnimationFrames;
        this.myPauseFrames = myPauseFrames;
        this.myTotalFrames = this.myAnimationFrames + this.myPauseFrames;
        this.myCanvas = myCanvas;
        this.myShouldReverse = configuration.shouldReverse();
        this.myShouldRepeat = configuration.isEndless();
        this.myFrameIndex = 0;
        this.myIsForward = true;
    }
    
    public void setManagerTask(final TimerTask myTask) {
        this.myTask = myTask;
    }
    
    public boolean advanceAnimation() {
        this.myFrameIndex += (this.myIsForward ? 1 : -1);
        if (this.isDisplayableFrame()) {
            SwingUtilities.invokeLater(this.myPaintFrameCommand);
        }
        this.checkReverse();
        return this.checkRepeat();
    }
    
    private boolean isDisplayableFrame() {
        return this.myIsForward ? (this.myFrameIndex <= this.myAnimationFrames) : (this.myTotalFrames - this.myFrameIndex <= this.myAnimationFrames);
    }
    
    private void checkReverse() {
        if (this.myFrameIndex == this.myTotalFrames) {
            if (this.myShouldReverse) {
                this.myIsForward = false;
            }
            else {
                this.cancel();
            }
        }
    }
    
    private boolean checkRepeat() {
        if (this.myFrameIndex <= 0) {
            if (this.myShouldRepeat) {
                return this.myIsForward = true;
            }
            this.cancel();
        }
        return false;
    }
    
    public void cancel() {
        this.myTask.cancel();
    }
}
