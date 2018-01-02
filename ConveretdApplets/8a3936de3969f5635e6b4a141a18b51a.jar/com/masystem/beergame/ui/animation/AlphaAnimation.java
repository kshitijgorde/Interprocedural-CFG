// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.animation;

import com.masystem.beergame.ui.scene.Node;

public class AlphaAnimation extends NodeAnimation
{
    private final float start;
    private final float end;
    
    public AlphaAnimation(final Node node, final float start, final float end) {
        super(node);
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected final void stepAnimation(final float n) {
        this.getTargetNode().setAlpha(this.start + (this.end - this.start) * n);
    }
}
