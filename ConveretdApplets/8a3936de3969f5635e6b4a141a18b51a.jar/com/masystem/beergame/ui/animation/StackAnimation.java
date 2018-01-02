// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.animation;

import com.masystem.beergame.ui.scene.Node;

public final class StackAnimation extends NodeAnimation
{
    private float startScaling;
    private float endScaling;
    private float startAlpha;
    private float endAlpha;
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    
    public StackAnimation(final Node node, final float n, final float n2, final float n3, final float n4) {
        this(node, n, n2, n3, n4, node.getX(), node.getY(), node.getX(), node.getY());
    }
    
    private StackAnimation(final Node node, float n, float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        super(node);
        final float n9 = n;
        n2 = n2;
        n = n9;
        this.startScaling = n;
        this.endScaling = n2;
        n2 = n4;
        n = n3;
        this.startAlpha = n;
        this.endAlpha = n2;
        this.setTranslation(n5, n6, n7, n8);
    }
    
    public final void setTranslation(final float startX, final float startY, final float endX, final float endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }
    
    @Override
    protected final void startAnimation() {
    }
    
    @Override
    protected final void stepAnimation(final float n) {
        final Node targetNode;
        (targetNode = this.getTargetNode()).setScaling(this.startScaling + (this.endScaling - this.startScaling) * n);
        targetNode.setAlpha(this.startAlpha + (this.endAlpha - this.startAlpha) * n);
        targetNode.setPosition(this.startX + (this.endX - this.startX) * n, this.startY + (this.endY - this.startY) * n);
    }
    
    @Override
    protected final void endAnimation() {
    }
}
