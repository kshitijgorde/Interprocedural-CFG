// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.pages.game;

import com.masystem.beergame.ui.scene.Node;
import com.masystem.beergame.ui.scene.Scene;
import com.masystem.beergame.resource.ResourceManager;
import com.masystem.beergame.ui.animation.NodeAnimation;
import com.masystem.beergame.ui.beergamecomponents.BitmapTextNode;

public final class Points extends BitmapTextNode
{
    private NodeAnimation anim;
    
    public Points() {
        this.setVisible(false);
    }
    
    public Points(final int n) {
        this.setStyle(0);
        this.setVisible(false);
    }
    
    public final void setStyle(int n) {
        this.setCharsImage(ResourceManager.getOptimizedImage(((n = n) == 1) ? "bitmap_text_red.png" : ((n == 2) ? "bitmap_text_green.png" : "bitmap_text_grey.png")), "0123456789+-$", 32);
    }
    
    public final NodeAnimation getAnimation() {
        return this.anim;
    }
    
    public final NodeAnimation createAnimation(final int startDelay) {
        (this.anim = new NodeAnimation(this) {
            private /* synthetic */ float val$y = this.getY();
            private /* synthetic */ float val$movement = Scene.getCurrentScene().getHeight() * 0.1f;
            
            @Override
            protected final void startAnimation() {
                Points.this.setAlpha(1.0f);
                Points.this.setVisible(true);
            }
            
            @Override
            protected final void stepAnimation(final float n) {
                if (n > 0.5f) {
                    Points.this.setAlpha(1.0f - 2.0f * (n - 0.5f));
                }
                Points.this.setPosition(-1.0f, this.val$y - n * this.val$movement);
            }
            
            @Override
            protected final void endAnimation() {
                Points.this.setVisible(false);
            }
        }).setDuration(2000);
        this.anim.setStartDelay(startDelay);
        return this.anim;
    }
}
