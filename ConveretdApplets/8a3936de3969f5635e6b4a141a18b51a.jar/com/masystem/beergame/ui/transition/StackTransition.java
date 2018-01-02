// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.transition;

import com.masystem.beergame.ui.animation.NodeAnimation;
import com.masystem.beergame.ui.animation.StackAnimation;
import com.masystem.beergame.ui.scene.PaintProperties;
import com.masystem.beergame.ui.scene.Node;

public final class StackTransition extends Transition
{
    private float srcEndScaling;
    private float destStartScaling;
    private int destStartDelay;
    
    public StackTransition(final Node node, final Node node2) {
        super(node, node2);
        this.destStartDelay = 225;
        this.setEndScaling(1.5f);
    }
    
    public final void setEndScaling(final float srcEndScaling) {
        this.srcEndScaling = srcEndScaling;
        this.destStartScaling = 1.0f / srcEndScaling;
    }
    
    @Override
    protected final void startTransition() {
        final Node sourceNode = this.getSourceNode();
        final Node destinationNode = this.getDestinationNode();
        if (sourceNode != null) {
            this.addChild(sourceNode);
        }
        if (destinationNode != null) {
            destinationNode.setVisible(false);
            this.addChild(destinationNode);
        }
        if (sourceNode != null) {
            final PaintProperties paintProperties = sourceNode.getPaintProperties();
            sourceNode.setPaintProperties(new PaintProperties(paintProperties));
            final StackAnimation stackAnimation;
            (stackAnimation = new StackAnimation(sourceNode, 1.0f, this.srcEndScaling, 1.0f, 0.0f)).setDuration((this.getDuration() - this.destStartDelay) * 60 / 100);
            stackAnimation.start();
            stackAnimation.setListener(new NodeAnimation.Listener() {
                @Override
                public final void onAnimationStart() {
                    StackTransition.this.started();
                }
                
                @Override
                public final void onAnimationEnd() {
                    if (destinationNode == null) {
                        StackTransition.this.ended();
                    }
                    sourceNode.setPaintProperties(paintProperties);
                    sourceNode.setScaling(1.0f);
                }
            });
        }
        if (destinationNode != null) {
            final StackAnimation stackAnimation2;
            (stackAnimation2 = new StackAnimation(destinationNode, this.destStartScaling, 1.0f, 0.0f, 1.0f)).setDuration((this.getDuration() - this.destStartDelay) * 40 / 100);
            stackAnimation2.setListener(new NodeAnimation.Listener() {
                @Override
                public final void onAnimationStart() {
                    if (sourceNode == null) {
                        StackTransition.this.started();
                    }
                    destinationNode.setVisible(true);
                }
                
                @Override
                public final void onAnimationEnd() {
                    StackTransition.this.ended();
                }
            });
            stackAnimation2.setStartDelay(this.destStartDelay);
            stackAnimation2.start();
        }
    }
}
