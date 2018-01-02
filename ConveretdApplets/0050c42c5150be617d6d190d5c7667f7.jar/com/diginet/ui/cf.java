// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.ui;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Component;

public class cf
{
    public static void a(final Component component, final Graphics graphics, final Image[] array) {
        final int width = component.size().width;
        final int height = component.size().height;
        for (int i = 0; i < width; i += array[0].getWidth(component)) {
            graphics.drawImage(array[0], i, 0, component);
        }
        for (int j = 0; j < height; j += array[1].getHeight(component)) {
            graphics.drawImage(array[1], 0, j, component);
        }
        final int n = height - array[2].getHeight(component);
        for (int k = 0; k < width; k += array[2].getWidth(component)) {
            graphics.drawImage(array[2], k, n, component);
        }
        final int n2 = width - array[3].getWidth(component);
        for (int l = 0; l < height; l += array[3].getHeight(component)) {
            graphics.drawImage(array[3], n2, l, component);
        }
        final int n3 = height - array[5].getHeight(component);
        final int n4 = width - array[6].getWidth(component);
        graphics.drawImage(array[4], 0, 0, component);
        graphics.drawImage(array[5], 0, n3, component);
        graphics.drawImage(array[6], n4, n3, component);
        graphics.drawImage(array[7], n4, 0, component);
    }
}
