// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.animation;

import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.Animator;

public abstract class Animation
{
    private final Animator animator;
    
    public Animation(final int n) {
        (this.animator = new Animator(n, new AnimationTimingTarget())).setResolution(16);
    }
    
    public final void setStartDelay(final int startDelay) {
        this.animator.setStartDelay(startDelay);
    }
    
    public final boolean isRunning() {
        return this.animator.isRunning();
    }
    
    public final void start() {
        this.animator.start();
    }
    
    public final void stop() {
        this.animator.stop();
    }
    
    public void onStart() {
    }
    
    public void onEnd() {
    }
    
    public static void onRepeat() {
    }
    
    public abstract void onStep(final float p0);
    
    protected final void timingEvent(final float n) {
        this.onStep(n);
    }
    
    static {
        final Animator.RepeatBehavior loop = Animator.RepeatBehavior.LOOP;
        final Animator.RepeatBehavior reverse = Animator.RepeatBehavior.REVERSE;
    }
    
    public class AnimationTimingTarget implements TimingTarget
    {
        protected AnimationTimingTarget() {
        }
        
        @Override
        public final void begin() {
            Animation.this.animator.pause();
            Animation.this.onStart();
            Animation.this.animator.resume();
        }
        
        @Override
        public final void end() {
            Animation.this.onEnd();
        }
        
        @Override
        public final void repeat() {
            Animation.this.animator.pause();
            final Animation this$0 = Animation.this;
            Animation.onRepeat();
            Animation.this.animator.resume();
        }
        
        @Override
        public final void timingEvent(final float n) {
            Animation.this.timingEvent(n);
        }
        
        public AnimationTimingTarget() {
        }
        
        public static float evaluate(float n, final float[] array) {
            final int n2 = array.length - 3;
            float n3 = n * n2;
            if (n >= 1.0f) {
                n3 = n2 - 1;
                n = 1.0f;
            }
            else {
                n = n3 - (int)n3;
            }
            final float n4 = (((-n + 3.0f) * n - 3.0f) * n + 1.0f) / 6.0f;
            final float n5 = ((3.0f * n - 6.0f) * n * n + 4.0f) / 6.0f;
            final float n6 = (((-3.0f * n + 3.0f) * n + 3.0f) * n + 1.0f) / 6.0f;
            n = n * n * n / 6.0f;
            final int n7 = (int)n3;
            return array[n7] * n4 + array[n7 + 1] * n5 + array[n7 + 2] * n6 + array[n7 + 3] * n;
        }
    }
}
