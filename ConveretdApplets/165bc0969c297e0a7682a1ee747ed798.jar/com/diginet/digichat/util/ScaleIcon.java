// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util;

import java.awt.image.ImageObserver;
import java.awt.Image;

public class ScaleIcon
{
    public static Image scale(final Image image) {
        final int height;
        final double n;
        return (image != null && (n = 24.0 / (height = image.getHeight(null))) < 1.0) ? image.getScaledInstance((int)(image.getWidth(null) * n), (int)(height * n), 4) : image;
    }
}
