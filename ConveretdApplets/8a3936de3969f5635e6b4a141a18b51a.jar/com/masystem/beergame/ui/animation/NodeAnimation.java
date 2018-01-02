// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.animation;

import javax.swing.SwingUtilities;
import com.masystem.beergame.ui.scene.Node;

public abstract class NodeAnimation
{
    private Animation anim;
    private int startDelay;
    private boolean cacheAutoEnabled;
    private int duration;
    private final Node target;
    private int filtering;
    private int prevFiltering;
    private boolean wasCacheEnabled;
    private boolean wasOpaque;
    private boolean waiting;
    private boolean canceled;
    private Listener listener;
    
    public NodeAnimation(final Node target) {
        this.target = target;
        this.filtering = 0;
        this.duration = 300;
        this.cacheAutoEnabled = true;
    }
    
    public final Node getTargetNode() {
        return this.target;
    }
    
    public final void setFiltering(final int filtering) {
        this.filtering = filtering;
    }
    
    public final void setDuration(final int duration) {
        this.duration = duration;
    }
    
    public final void setStartDelay(final int startDelay) {
        this.startDelay = startDelay;
    }
    
    public final void setCacheAutoEnabled(final boolean cacheAutoEnabled) {
        this.cacheAutoEnabled = cacheAutoEnabled;
    }
    
    public final void start() {
        if (this.target != null) {
            if (this.cacheAutoEnabled) {
                this.wasCacheEnabled = this.target.isCacheEnabled();
                this.target.setCacheEnabled(true);
            }
            this.wasOpaque = this.target.isOpaque();
            this.target.setOpaque(false);
            this.prevFiltering = this.target.getFiltering();
            if (this.filtering != 0) {
                this.target.setFiltering(this.filtering);
            }
            this.stepAnimation(0.0f);
            this.anim = new Animation(this.duration) {
                @Override
                public final void onStart() {
                    NodeAnimation.access$002(NodeAnimation.this, false);
                    NodeAnimation.this.startAnimation();
                    if (NodeAnimation.this.listener != null) {
                        NodeAnimation.this.listener.onAnimationStart();
                    }
                }
                
                @Override
                public final void onStep(final float n) {
                    NodeAnimation.this.stepAnimation(n);
                }
                
                @Override
                public final void onEnd() {
                    NodeAnimation.access$002(NodeAnimation.this, false);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public final void run() {
                            NodeAnimation.this.restoreCache();
                            NodeAnimation.this.getTargetNode().setFiltering(NodeAnimation.this.prevFiltering);
                            if (!NodeAnimation.this.canceled) {
                                NodeAnimation.this.endAnimation();
                                if (NodeAnimation.this.listener != null) {
                                    NodeAnimation.this.listener.onAnimationEnd();
                                }
                            }
                            NodeAnimation.access$302(NodeAnimation.this, false);
                        }
                    });
                }
            };
        }
        else {
            this.anim = new Animation(this.duration) {
                @Override
                public final void onStart() {
                    NodeAnimation.access$002(NodeAnimation.this, false);
                    NodeAnimation.this.startAnimation();
                    if (NodeAnimation.this.listener != null) {
                        NodeAnimation.this.listener.onAnimationStart();
                    }
                }
                
                @Override
                public final void onStep(final float n) {
                }
                
                @Override
                public final void onEnd() {
                    NodeAnimation.access$002(NodeAnimation.this, false);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public final void run() {
                            if (!NodeAnimation.this.canceled) {
                                NodeAnimation.this.endAnimation();
                                if (NodeAnimation.this.listener != null) {
                                    NodeAnimation.this.listener.onAnimationEnd();
                                }
                            }
                            NodeAnimation.access$302(NodeAnimation.this, false);
                        }
                    });
                }
            };
        }
        if (this.startDelay > 0) {
            this.waiting = true;
            this.anim.setStartDelay(this.startDelay);
        }
        this.anim.start();
    }
    
    public final boolean isRunning() {
        return this.anim != null && this.anim.isRunning();
    }
    
    public final boolean isWaiting() {
        return this.waiting;
    }
    
    public final void cancel() {
        if (this.isRunning()) {
            this.canceled = true;
            this.anim.stop();
        }
    }
    
    protected final void restoreCache() {
        final Node target = this.target;
        if (this.cacheAutoEnabled && !this.wasCacheEnabled) {
            target.destroyCache();
        }
        target.setOpaque(this.wasOpaque);
    }
    
    protected void startAnimation() {
    }
    
    protected void endAnimation() {
    }
    
    protected abstract void stepAnimation(final float p0);
    
    public final void setListener(final Listener listener) {
        this.listener = listener;
    }
    
    static /* synthetic */ boolean access$002(final NodeAnimation nodeAnimation, final boolean b) {
        return nodeAnimation.waiting = false;
    }
    
    static /* synthetic */ boolean access$302(final NodeAnimation nodeAnimation, final boolean b) {
        return nodeAnimation.canceled = false;
    }
    
    public static class Adapter implements Listener
    {
        @Override
        public void onAnimationStart() {
        }
        
        @Override
        public void onAnimationEnd() {
        }
    }
    
    public interface Listener
    {
        void onAnimationStart();
        
        void onAnimationEnd();
    }
}
