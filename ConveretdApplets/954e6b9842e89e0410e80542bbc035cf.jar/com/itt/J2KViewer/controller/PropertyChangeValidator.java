// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.controller;

import java.awt.Rectangle;
import java.beans.PropertyVetoException;
import java.beans.PropertyChangeEvent;
import com.itt.J2KViewer.util.ViewerConst;
import java.beans.VetoableChangeListener;

public class PropertyChangeValidator implements VetoableChangeListener, ViewerConst
{
    private PropertyManager propertyManager;
    
    public PropertyChangeValidator(final PropertyManager propertyManager) {
        this.propertyManager = null;
        this.propertyManager = propertyManager;
    }
    
    public void vetoableChange(final PropertyChangeEvent propertyChangeEvent) throws PropertyVetoException {
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (propertyName.equals("maxZoomOutLevel")) {
            final int intValue = (int)propertyChangeEvent.getNewValue();
            if (intValue != Integer.MIN_VALUE && intValue != -3 && intValue < -2) {
                throw new PropertyVetoException("Invalid Max zoom Out level", propertyChangeEvent);
            }
        }
        if (propertyName.equals("MaxDiscardedZoomLevels") && (int)propertyChangeEvent.getNewValue() < -1) {
            throw new PropertyVetoException("Invalid Max Discarded zoom levels", propertyChangeEvent);
        }
        if (propertyName.equals("DiscardedZoomLevels")) {
            final int maxDiscardedZoomLevels = this.propertyManager.getMaxDiscardedZoomLevels();
            final int intValue2 = (int)propertyChangeEvent.getNewValue();
            if (intValue2 < -2 && intValue2 != Integer.MIN_VALUE) {
                throw new PropertyVetoException("Cannot zoom beyond 400%", propertyChangeEvent);
            }
            if (maxDiscardedZoomLevels != -1 && intValue2 > maxDiscardedZoomLevels) {
                throw new PropertyVetoException("Discarded zoom levels cannot be greater than Max Discarded zoom levels ", propertyChangeEvent);
            }
        }
        if (propertyName.equals("TotalQualityLayers") && (int)propertyChangeEvent.getNewValue() < -1) {
            throw new PropertyVetoException("Total Quality Layers cannot be negative", propertyChangeEvent);
        }
        if (propertyName.equals("QualityLayers")) {
            final int intValue3 = (int)propertyChangeEvent.getNewValue();
            if (intValue3 < -1) {
                throw new PropertyVetoException("Quality Layers cannot be negative", propertyChangeEvent);
            }
            if (this.propertyManager.getTotalQualityLayers() != -1 && intValue3 > this.propertyManager.getTotalQualityLayers()) {
                throw new PropertyVetoException("Quality layers cannot be greater than Total Quality Layers", propertyChangeEvent);
            }
            if (this.propertyManager.getTotalQualityLayers() != -1 && intValue3 <= 0) {
                throw new PropertyVetoException("Quality layers cannot be less than 1", propertyChangeEvent);
            }
        }
        if (propertyName.equals("TotalDimensions") || propertyName.equals("CurrentDimensions")) {
            final Rectangle rectangle = (Rectangle)propertyChangeEvent.getNewValue();
            if (rectangle != null) {
                if (rectangle.width <= 0) {
                    throw new PropertyVetoException(propertyName + " has invalid width", propertyChangeEvent);
                }
                if (rectangle.height <= 0) {
                    throw new PropertyVetoException(propertyName + " has invalid height", propertyChangeEvent);
                }
            }
        }
        if (propertyName.equals("ShowScrollbars")) {
            final int intValue4 = (int)propertyChangeEvent.getNewValue();
            if (intValue4 < 1 || intValue4 > 3) {
                throw new PropertyVetoException("Invalid value for ShowScrollbars", propertyChangeEvent);
            }
        }
        if (propertyName.equals("BytesTransferred") && (long)propertyChangeEvent.getNewValue() < 0L) {
            throw new PropertyVetoException("Bytes transferred cannot be negative", propertyChangeEvent);
        }
        if (propertyName.equals("WaveletSharpeningGain")) {
            final double doubleValue = (double)propertyChangeEvent.getNewValue();
            if (doubleValue < 1.0 || doubleValue > 25.0) {
                throw new PropertyVetoException("Wavelet Sharpening Gain must be between 1 and 25", propertyChangeEvent);
            }
        }
    }
}
