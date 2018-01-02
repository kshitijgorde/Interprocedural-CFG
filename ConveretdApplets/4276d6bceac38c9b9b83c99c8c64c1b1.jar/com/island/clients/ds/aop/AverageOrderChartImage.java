// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop;

import java.awt.Image;

public class AverageOrderChartImage extends AverageOrderChartImageProducer
{
    Image image;
    
    public AverageOrderChartImage(final String symbol) {
        super(symbol);
    }
    
    public void setImage(final Image image) {
        this.image = image;
    }
    
    protected Image getImageTemplate(final int width, final int height) {
        return this.image;
    }
}
