// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.IASjTools;

import java.util.Iterator;
import java.util.ArrayList;

public class IASImage
{
    private int numComponents;
    private int numQualityLayers;
    private int numDiscardLevels;
    private boolean isYcc;
    private boolean isReversible;
    private String colorSpace;
    private String progressionOrder;
    private IASCoords tileRange;
    private IASCoords sSize;
    private ArrayList components;
    private IASComponent dummyComponent;
    
    public IASImage() {
        this.dummyComponent = new IASComponent();
        (this.sSize = new IASCoords()).setX(0);
        this.sSize.setY(0);
        (this.tileRange = new IASCoords()).setX(0);
        this.tileRange.setY(0);
        this.components = new ArrayList(0);
        this.numQualityLayers = 0;
        this.numDiscardLevels = 0;
        this.isYcc = false;
        this.colorSpace = "unknown";
    }
    
    public void reset() {
        (this.sSize = new IASCoords()).setX(0);
        this.sSize.setY(0);
        (this.tileRange = new IASCoords()).setX(0);
        this.tileRange.setY(0);
        this.components = new ArrayList(0);
        this.numQualityLayers = 0;
        this.numDiscardLevels = 0;
        this.isYcc = false;
        this.colorSpace = "unknown";
    }
    
    public boolean isReversible() {
        return this.isReversible;
    }
    
    public boolean isYcc() {
        return this.isYcc;
    }
    
    public int getWidth() {
        return this.sSize.getX();
    }
    
    public int getHeight() {
        return this.sSize.getY();
    }
    
    public int getNumTilesInXDimension() {
        return this.tileRange.getX();
    }
    
    public int getNumTilesInYDimension() {
        return this.tileRange.getY();
    }
    
    public String getProgressionOrder() {
        return this.progressionOrder;
    }
    
    public String getColorSpace() {
        return this.colorSpace;
    }
    
    public int getNumDiscardLevels() {
        return this.numDiscardLevels;
    }
    
    public int getNumComponents() {
        return this.numComponents;
    }
    
    public int getNumQualityLayers() {
        return this.numQualityLayers;
    }
    
    public ArrayList getComponents() {
        return this.components;
    }
    
    public IASComponent[] getIASComponents() {
        final IASComponent[] array = new IASComponent[this.components.size()];
        if (this.components != null && this.components.size() > 0) {
            int n = 0;
            final Iterator<IASComponent> iterator = (Iterator<IASComponent>)this.components.iterator();
            while (iterator.hasNext()) {
                array[n] = iterator.next();
                ++n;
            }
        }
        return array;
    }
    
    public void setColorSpace(final String colorSpace) {
        this.colorSpace = colorSpace;
    }
    
    public void setComponents(final ArrayList components) {
        this.components = components;
    }
    
    public void addComponents(final IASComponent iasComponent) {
        this.components.add(iasComponent);
    }
    
    public void setDummyComponent(final IASComponent dummyComponent) {
        this.dummyComponent = dummyComponent;
    }
    
    public void setReversible(final boolean isReversible) {
        this.isReversible = isReversible;
    }
    
    public void setYcc(final boolean isYcc) {
        this.isYcc = isYcc;
    }
    
    public void setNumComponents(final int numComponents) {
        this.numComponents = numComponents;
    }
    
    public void setNumDiscardLevels(final int numDiscardLevels) {
        this.numDiscardLevels = numDiscardLevels;
    }
    
    public void setNumQualityLayers(final int numQualityLayers) {
        this.numQualityLayers = numQualityLayers;
    }
    
    public void setProgressionOrder(final String progressionOrder) {
        this.progressionOrder = progressionOrder;
    }
    
    public void setSSize(final IASCoords sSize) {
        this.sSize = sSize;
    }
    
    public void setSsize(final int x, final int y) {
        this.sSize.setX(x);
        this.sSize.setY(y);
    }
    
    public void setTileRange(final IASCoords tileRange) {
        this.tileRange = tileRange;
    }
    
    public IASCoords getTileRange() {
        return this.tileRange;
    }
}
