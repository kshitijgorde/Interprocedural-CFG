// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.animation.timing;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import org.jdesktop.animation.timing.interpolation.LinearInterpolator;
import org.jdesktop.animation.timing.interpolation.Interpolator;
import java.util.ArrayList;

public final class Animator
{
    private TimingSource timer;
    private TimingSource swingTimer;
    private ArrayList<TimingTarget> targets;
    private long startTime;
    private long currentStartTime;
    private boolean intRepeatCount;
    private boolean timeToStop;
    private boolean hasBegun;
    private long pauseBeginTime;
    private boolean running;
    private double repeatCount;
    private int startDelay;
    private RepeatBehavior repeatBehavior;
    private EndBehavior endBehavior;
    private int duration;
    private int resolution;
    private float acceleration;
    private float deceleration;
    private float startFraction;
    private Direction direction;
    private Interpolator interpolator;
    
    public Animator(final int duration, TimingTarget timingTarget) {
        this.targets = new ArrayList<TimingTarget>();
        this.intRepeatCount = true;
        this.timeToStop = false;
        this.hasBegun = false;
        this.pauseBeginTime = 0L;
        this.running = false;
        this.repeatCount = 1.0;
        this.repeatBehavior = RepeatBehavior.REVERSE;
        this.endBehavior = EndBehavior.HOLD;
        this.resolution = 20;
        this.acceleration = 0.0f;
        this.deceleration = 0.0f;
        this.startFraction = 0.0f;
        this.direction = Direction.FORWARD;
        this.interpolator = LinearInterpolator.getInstance();
        this.duration = duration;
        timingTarget = timingTarget;
        if (timingTarget != null) {
            synchronized (this.targets) {
                if (!this.targets.contains(timingTarget)) {
                    this.targets.add(timingTarget);
                }
            }
        }
        Toolkit.getDefaultToolkit();
        this.swingTimer = new SwingTimingSource();
        this.timer = this.swingTimer;
    }
    
    private void throwExceptionIfRunning() {
        if (this.running) {
            throw new IllegalStateException("Cannot perform this operation while Animator is running");
        }
    }
    
    public final void setResolution(final int n) {
        this.throwExceptionIfRunning();
        this.resolution = 16;
        this.timer.setResolution(16);
    }
    
    public final void setStartDelay(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("startDelay (" + n + ") cannot be < 0");
        }
        this.throwExceptionIfRunning();
        this.startDelay = n;
        this.timer.setStartDelay(n);
    }
    
    public final void start() {
        this.throwExceptionIfRunning();
        this.hasBegun = false;
        this.running = true;
        this.startTime = System.nanoTime() / 1000000L + this.startDelay;
        if (this.duration != -1) {
            final Direction direction = this.direction;
            final Direction forward = Direction.FORWARD;
            if (this.direction == Direction.BACKWARD) {
                this.startTime -= (long)(this.duration * ((this.direction == Direction.FORWARD) ? 0.0f : 1.0f));
            }
        }
        this.currentStartTime = this.startTime;
        this.timer.start();
    }
    
    public final boolean isRunning() {
        return this.running;
    }
    
    public final void stop() {
        this.timer.stop();
        synchronized (this.targets) {
            for (int i = 0; i < this.targets.size(); ++i) {
                this.targets.get(i).end();
            }
        }
        this.timeToStop = false;
        this.running = false;
        this.pauseBeginTime = 0L;
    }
    
    public final void pause() {
        if (this.running) {
            this.pauseBeginTime = System.nanoTime();
            this.running = false;
        }
    }
    
    public final void resume() {
        if (this.pauseBeginTime > 0L) {
            final long n = (System.nanoTime() - this.pauseBeginTime) / 1000000L;
            this.startTime += n;
            this.currentStartTime += n;
            this.pauseBeginTime = 0L;
            this.running = true;
        }
    }
    
    public final float getTimingFraction() {
        final long n = System.nanoTime() / 1000000L;
        final long n2 = n - this.currentStartTime;
        final double n3 = (n - this.startTime) / this.duration;
        if (!this.hasBegun) {
            synchronized (this.targets) {
                for (int i = 0; i < this.targets.size(); ++i) {
                    this.targets.get(i).begin();
                }
            }
            this.hasBegun = true;
        }
        float n4 = 0.0f;
        if (this.duration != -1 && this.repeatCount != -1.0 && n3 >= this.repeatCount) {
            Label_0241: {
                switch (this.endBehavior) {
                    case HOLD: {
                        if (!this.intRepeatCount) {
                            n4 = Math.min(1.0f, n2 / this.duration);
                            break Label_0241;
                        }
                        if (this.direction != Direction.BACKWARD) {
                            n4 = 1.0f;
                            break Label_0241;
                        }
                        break;
                    }
                    case RESET: {
                        n4 = 0.0f;
                        break Label_0241;
                    }
                }
                n4 = 0.0f;
            }
            this.timeToStop = true;
        }
        else {
            if (this.duration != -1 && n2 > this.duration) {
                final long n5;
                n4 = (n5 = n2 % this.duration) / this.duration;
                final long n6 = n;
                this.currentStartTime = n6 - n6;
                if (this.repeatBehavior == RepeatBehavior.REVERSE) {
                    final boolean b;
                    if (b = ((int)(n2 / this.duration) % 2 > 0)) {
                        this.direction = ((this.direction == Direction.FORWARD) ? Direction.BACKWARD : Direction.FORWARD);
                    }
                    if (this.direction == Direction.BACKWARD) {
                        n4 = 1.0f - n4;
                    }
                }
                synchronized (this.targets) {
                    for (int j = 0; j < this.targets.size(); ++j) {
                        this.targets.get(j).repeat();
                    }
                    return this.interpolator.interpolate(n4);
                }
            }
            n4 = 0.0f;
            if (this.duration != -1) {
                float n7 = n2 / this.duration;
                if (this.direction == Direction.BACKWARD) {
                    n7 = 1.0f - n7;
                }
                n4 = Math.max(Math.min(n7, 1.0f), 0.0f);
            }
        }
        return this.interpolator.interpolate(n4);
    }
    
    static /* synthetic */ void access$200(Animator animator, float n) {
        final Animator animator2 = animator;
        n = n;
        animator = animator2;
        synchronized (animator2.targets) {
            for (int i = 0; i < animator.targets.size(); ++i) {
                animator.targets.get(i).timingEvent(n);
            }
        }
        final Animator animator3;
        if (animator3.timeToStop) {
            animator3.stop();
        }
    }
    
    final class TimerTarget implements ActionListener
    {
        private TimerTarget(final byte b) {
        }
        
        @Override
        public final void actionPerformed(final ActionEvent actionEvent) {
            Animator.access$200(Animator.this, Animator.this.getTimingFraction());
        }
    }
    
    final class SwingTimingSource extends TimingSource
    {
        private Timer timer;
        
        public SwingTimingSource(final Animator animator) {
            (this.timer = new Timer(animator.resolution, new TimerTarget())).setInitialDelay(0);
        }
        
        @Override
        public final void start() {
            this.timer.start();
        }
        
        @Override
        public final void stop() {
            this.timer.stop();
        }
        
        @Override
        public final void setResolution(final int delay) {
            this.timer.setDelay(delay);
        }
        
        @Override
        public final void setStartDelay(final int initialDelay) {
            this.timer.setInitialDelay(initialDelay);
        }
    }
    
    public enum RepeatBehavior
    {
        LOOP, 
        REVERSE;
    }
    
    public enum Direction
    {
        FORWARD, 
        BACKWARD;
    }
    
    public enum EndBehavior
    {
        HOLD, 
        RESET;
    }
}
