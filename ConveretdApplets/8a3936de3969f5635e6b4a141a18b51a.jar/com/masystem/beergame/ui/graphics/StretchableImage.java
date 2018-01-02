// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.graphics;

import com.masystem.beergame.ui.scene.Node;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class StretchableImage
{
    private BufferedImage source;
    private BufferedImage cache;
    private int widthA;
    private int widthB;
    private int widthC;
    private int heightA;
    private int heightB;
    private int heightC;
    
    public StretchableImage(final BufferedImage source) {
        if (source != this.source && (this.source = source) != null) {
            final int width = source.getWidth();
            final int height = source.getHeight();
            final int n = width / 2;
            final int n2 = height / 2;
            final int n3 = width - width / 2 - 1;
            final int heightC = height - height / 2 - 1;
            final int widthC = n3;
            final int heightA = n2;
            final int widthA = n;
            if (this.source != null) {
                this.widthA = widthA;
                this.heightA = heightA;
                this.widthB = this.source.getWidth() - widthA - widthC;
                this.heightB = this.source.getHeight() - heightA - heightC;
                this.widthC = widthC;
                this.heightC = heightC;
            }
        }
    }
    
    public final BufferedImage getSourceImage() {
        return this.source;
    }
    
    public void draw$2f958723(final Graphics graphics, final int n, final int n2) {
        if (this.cache == null || this.cache.getWidth() != n || this.cache.getHeight() != n2) {
            this.cache = GraphicsTools.createOptimizedImage(n, n2, this.source.getTransparency());
            final Graphics2D graphics2 = this.cache.createGraphics();
            final int n3 = n - this.widthC;
            final int n4 = n2 - this.heightC;
            final int n5 = this.widthA + this.widthB + this.widthC;
            final int n6 = this.heightA + this.heightB + this.heightC;
            final int n7 = this.widthA + this.widthB;
            final int n8 = this.heightA + this.heightB;
            graphics2.drawImage(this.source, 0, 0, this.widthA, this.heightA, 0, 0, this.widthA, this.heightA, null);
            graphics2.drawImage(this.source, this.widthA, 0, n3, this.heightA, this.widthA, 0, n7, this.heightA, null);
            graphics2.drawImage(this.source, n3, 0, n, this.heightA, n7, 0, n5, this.heightA, null);
            graphics2.drawImage(this.source, 0, this.heightA, this.widthA, n4, 0, this.heightA, this.widthA, n8, null);
            graphics2.drawImage(this.source, this.widthA, this.heightA, n3, n4, this.widthA, this.heightA, n7, n8, null);
            graphics2.drawImage(this.source, n3, this.heightA, n, n4, n7, this.heightA, n5, n8, null);
            graphics2.drawImage(this.source, 0, n4, this.widthA, n2, 0, n8, this.widthA, n6, null);
            graphics2.drawImage(this.source, this.widthA, n4, n3, n2, this.widthA, n8, n7, n6, null);
            graphics2.drawImage(this.source, n3, n4, n, n2, n7, n8, n5, n6, null);
        }
        graphics.drawImage(this.cache, 0, 0, null);
    }
    
    private StretchableImage() {
    }
    
    public static void horizontal(final Node node, final int n, final Node node2, final int n2) {
        horizontal(node, n, node2, n2, null, 0.0f);
    }
    
    public static void horizontal(final Node node, final int n, final Node node2, final int n2, final Node node3, float n3) {
        if ((n & 0xF) != 0x0 && (n2 & 0xF) != 0x0) {
            final float horizontalAnchorPos = getHorizontalAnchorPos(node, n);
            final float horizontalAnchorPos2 = getHorizontalAnchorPos(node2, n2);
            if (node3 != null) {
                n3 *= node3.getWidth();
            }
            node.setPosition(horizontalAnchorPos2 + node.getX() - horizontalAnchorPos + n3, -1.0f);
            return;
        }
        throw new IllegalArgumentException("Both anchors must be horizontal.");
    }
    
    public static void vertical(final Node node, final int n, final Node node2, final int n2) {
        vertical(node, n, node2, 17, null, 0.0f);
    }
    
    public static void vertical(final Node node, final int n, final Node node2, final int n2, final float n3) {
        vertical(node, 17, node2, 17, null, 1.0f);
    }
    
    public static void vertical(final Node node, final int n, final Node node2, final int n2, final Node node3, float n3) {
        if ((n & 0xF0) != 0x0 && (n2 & 0xF0) != 0x0) {
            final float verticalAnchorPos = getVerticalAnchorPos(node, n);
            final float verticalAnchorPos2 = getVerticalAnchorPos(node2, n2);
            if (node3 != null) {
                n3 *= node3.getHeight();
            }
            node.setPosition(-1.0f, verticalAnchorPos2 + node.getY() - verticalAnchorPos + n3);
            return;
        }
        throw new IllegalArgumentException("Both anchors must be vertical.");
    }
    
    private static float getHorizontalAnchorPos(final Node node, final int n) {
        switch (n) {
            case 1: {
                return node.getLeft();
            }
            case 2: {
                return node.getRight();
            }
            case 17: {
                return (node.getLeft() + node.getRight()) / 2.0f;
            }
            default: {
                return node.getX();
            }
        }
    }
    
    private static float getVerticalAnchorPos(final Node node, final int n) {
        switch (n) {
            case 16: {
                return node.getTop();
            }
            case 32: {
                return node.getBottom();
            }
            case 17: {
                return (node.getTop() + node.getBottom()) / 2.0f;
            }
            default: {
                return node.getY();
            }
        }
    }
}
