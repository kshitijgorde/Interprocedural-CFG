// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.IASjTools;

import java.util.logging.Logger;

public class IASComponent
{
    private static Logger logger;
    int precision;
    IASCoords sDims;
    IASCoords sTiles;
    
    public IASComponent() {
        this.sDims = new IASCoords();
        this.sTiles = new IASCoords();
        this.precision = 0;
        this.sDims.setX(0);
        this.sDims.setY(0);
    }
    
    public int getPrecision() {
        return this.precision;
    }
    
    public int getWidth() {
        return this.sDims.getX();
    }
    
    public int getHeight() {
        return this.sDims.getY();
    }
    
    public int getTileWidth() {
        return this.sTiles.getX();
    }
    
    public int getTileHeight() {
        return this.sTiles.getY();
    }
    
    public static void setLogger(final Logger logger) {
        IASComponent.logger = logger;
    }
    
    public void setPrecision(final int precision) {
        this.precision = precision;
    }
    
    public void setSDims(final IASCoords sDims) {
        this.sDims = sDims;
    }
    
    public void setSTiles(final IASCoords sTiles) {
        this.sTiles = sTiles;
    }
    
    public static Logger getLogger() {
        return IASComponent.logger;
    }
    
    public IASCoords getSDims() {
        return this.sDims;
    }
    
    public IASCoords getSTiles() {
        return this.sTiles;
    }
    
    static {
        IASComponent.logger = Logger.getLogger("com.itt.IASjTools.IASComponent");
    }
}
