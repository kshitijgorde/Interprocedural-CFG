// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.swing;

import java.io.Serializable;

public class SizeRequirements implements Serializable
{
    public int minimum;
    public int preferred;
    public int maximum;
    public float alignment;
    
    public SizeRequirements() {
        this.minimum = 0;
        this.preferred = 0;
        this.maximum = 0;
        this.alignment = 0.5f;
    }
    
    public SizeRequirements(final int min, final int pref, final int max, final float a) {
        this.minimum = min;
        this.preferred = pref;
        this.maximum = max;
        this.alignment = ((a > 1.0f) ? 1.0f : ((a < 0.0f) ? 0.0f : a));
    }
    
    public static void calculateAlignedPositions(final int allocated, final SizeRequirements total, final SizeRequirements[] children, final int[] offsets, final int[] spans) {
        final int totalAscent = (int)(allocated * total.alignment);
        final int totalDescent = allocated - totalAscent;
        for (int i = 0; i < children.length; ++i) {
            final SizeRequirements req = children[i];
            final int maxAscent = (int)(req.maximum * req.alignment);
            final int maxDescent = req.maximum - maxAscent;
            final int ascent = Math.min(totalAscent, maxAscent);
            final int descent = Math.min(totalDescent, maxDescent);
            offsets[i] = totalAscent - ascent;
            spans[i] = (int)Math.min(ascent + descent, 2147483647L);
        }
    }
    
    public static void calculateTiledPositions(final int allocated, final SizeRequirements total, final SizeRequirements[] children, final int[] offsets, final int[] spans) {
        if (allocated > total.preferred) {
            expandedTile(allocated, total, children, offsets, spans);
        }
        else {
            compressedTile(allocated, total, children, offsets, spans);
        }
    }
    
    private static void compressedTile(final int allocated, final SizeRequirements total, final SizeRequirements[] request, final int[] offsets, final int[] spans) {
        final int totalPlay = Math.min(total.preferred - allocated, total.preferred - total.minimum);
        final float factor = (total.preferred - total.minimum == 0) ? 0.0f : (totalPlay / (total.preferred - total.minimum));
        int totalOffset = 0;
        for (int i = 0; i < spans.length; ++i) {
            offsets[i] = totalOffset;
            final SizeRequirements req = request[i];
            final int play = (int)(factor * (req.preferred - req.minimum));
            spans[i] = req.preferred - play;
            totalOffset = (int)Math.min(totalOffset + spans[i], 2147483647L);
        }
    }
    
    private static void expandedTile(final int allocated, final SizeRequirements total, final SizeRequirements[] request, final int[] offsets, final int[] spans) {
        final int totalPlay = Math.min(allocated - total.preferred, total.maximum - total.preferred);
        final float factor = (total.maximum - total.preferred == 0) ? 0.0f : (totalPlay / (total.maximum - total.preferred));
        int totalOffset = 0;
        for (int i = 0; i < spans.length; ++i) {
            offsets[i] = totalOffset;
            final SizeRequirements req = request[i];
            final int play = (int)(factor * (req.maximum - req.preferred));
            spans[i] = (int)Math.min(req.preferred + play, 2147483647L);
            totalOffset = (int)Math.min(totalOffset + spans[i], 2147483647L);
        }
    }
    
    public static SizeRequirements getAlignedSizeRequirements(final SizeRequirements[] children) {
        final SizeRequirements totalAscent = new SizeRequirements();
        final SizeRequirements totalDescent = new SizeRequirements();
        for (int i = 0; i < children.length; ++i) {
            final SizeRequirements req = children[i];
            int ascent = (int)(req.alignment * req.minimum);
            int descent = req.minimum - ascent;
            totalAscent.minimum = Math.max(ascent, totalAscent.minimum);
            totalDescent.minimum = Math.max(descent, totalDescent.minimum);
            ascent = (int)(req.alignment * req.preferred);
            descent = req.preferred - ascent;
            totalAscent.preferred = Math.max(ascent, totalAscent.preferred);
            totalDescent.preferred = Math.max(descent, totalDescent.preferred);
            ascent = (int)(req.alignment * req.maximum);
            descent = req.maximum - ascent;
            totalAscent.maximum = Math.max(ascent, totalAscent.maximum);
            totalDescent.maximum = Math.max(descent, totalDescent.maximum);
        }
        final int min = (int)Math.min(totalAscent.minimum + totalDescent.minimum, 2147483647L);
        final int pref = (int)Math.min(totalAscent.preferred + totalDescent.preferred, 2147483647L);
        final int max = (int)Math.min(totalAscent.maximum + totalDescent.maximum, 2147483647L);
        float alignment = 0.0f;
        if (min > 0) {
            alignment = totalAscent.minimum / min;
            alignment = ((alignment > 1.0f) ? 1.0f : ((alignment < 0.0f) ? 0.0f : alignment));
        }
        return new SizeRequirements(min, pref, max, alignment);
    }
    
    public static SizeRequirements getTiledSizeRequirements(final SizeRequirements[] children) {
        final SizeRequirements total = new SizeRequirements();
        for (int i = 0; i < children.length; ++i) {
            final SizeRequirements req = children[i];
            total.minimum = (int)Math.min(total.minimum + req.minimum, 2147483647L);
            total.preferred = (int)Math.min(total.preferred + req.preferred, 2147483647L);
            total.maximum = (int)Math.min(total.maximum + req.maximum, 2147483647L);
        }
        return total;
    }
    
    public String toString() {
        return "[" + this.minimum + "," + this.preferred + "," + this.maximum + "]@" + this.alignment;
    }
}
