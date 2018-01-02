// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.scene;

import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.AlphaComposite;

public final class PaintProperties
{
    private AlphaComposite composite;
    private Object filteringInterpolation;
    private boolean enabled;
    private int filtering;
    
    public PaintProperties() {
        this.enabled = true;
    }
    
    public PaintProperties(final PaintProperties paintProperties) {
        this.composite = paintProperties.composite;
        this.filteringInterpolation = paintProperties.filteringInterpolation;
        this.enabled = paintProperties.enabled;
        this.filtering = paintProperties.filtering;
    }
    
    public final float getAlpha() {
        if (this.composite != null) {
            return this.composite.getAlpha();
        }
        return 1.0f;
    }
    
    public final void setAlpha(final float n) {
        this.composite = this.getComposite().derive(n);
    }
    
    public final int getFiltering() {
        return this.filtering;
    }
    
    public final void setFiltering(final int filtering) {
        switch (this.filtering = filtering) {
            case 1: {
                this.filteringInterpolation = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
            }
            case 2: {
                this.filteringInterpolation = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
            }
            case 3: {
                this.filteringInterpolation = RenderingHints.VALUE_INTERPOLATION_BICUBIC;
            }
            default: {
                this.filteringInterpolation = null;
            }
        }
    }
    
    public final Graphics2D createGraphics(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics.create();
        if (this.enabled) {
            final Graphics2D graphics2D2 = graphics2D;
            final Composite composite;
            if (this.composite != null && (this.getAlpha() < 1.0f || ((this.composite != null) ? this.composite.getRule() : 3) != 3) && (composite = graphics2D2.getComposite()) instanceof AlphaComposite) {
                final float alpha;
                if ((alpha = ((AlphaComposite)composite).getAlpha()) != 1.0f) {
                    graphics2D2.setComposite(this.getComposite().derive(this.getAlpha() * alpha));
                }
                else {
                    graphics2D2.setComposite(this.getComposite());
                }
            }
            final Graphics2D graphics2D3 = graphics2D;
            if (this.filtering != 0) {
                graphics2D3.setRenderingHint(RenderingHints.KEY_INTERPOLATION, this.filteringInterpolation);
            }
        }
        return graphics2D;
    }
    
    private AlphaComposite getComposite() {
        if (this.composite == null) {
            return this.composite = AlphaComposite.SrcOver;
        }
        return this.composite;
    }
}
