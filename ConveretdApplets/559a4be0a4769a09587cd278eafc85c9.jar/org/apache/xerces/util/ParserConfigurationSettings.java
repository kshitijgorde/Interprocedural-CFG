// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

import org.apache.xerces.xni.parser.XMLConfigurationException;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.xerces.xni.parser.XMLComponentManager;

public class ParserConfigurationSettings implements XMLComponentManager
{
    protected Vector fRecognizedProperties;
    protected Hashtable fProperties;
    protected Vector fRecognizedFeatures;
    protected Hashtable fFeatures;
    protected XMLComponentManager fParentSettings;
    static int counter;
    
    public ParserConfigurationSettings() {
        this(null);
    }
    
    public ParserConfigurationSettings(final XMLComponentManager parent) {
        this.fRecognizedFeatures = new Vector();
        this.fRecognizedProperties = new Vector();
        this.fFeatures = new Hashtable();
        this.fProperties = new Hashtable();
        this.fParentSettings = parent;
    }
    
    public void addRecognizedFeatures(final String[] featureIds) {
        for (int featureIdsCount = (featureIds != null) ? featureIds.length : 0, i = 0; i < featureIdsCount; ++i) {
            final String featureId = featureIds[i];
            if (!this.fRecognizedFeatures.contains(featureId)) {
                this.fRecognizedFeatures.addElement(featureId);
            }
        }
    }
    
    public void setFeature(final String featureId, final boolean state) throws XMLConfigurationException {
        this.checkFeature(featureId);
        this.fFeatures.put(featureId, state ? Boolean.TRUE : Boolean.FALSE);
    }
    
    public void addRecognizedProperties(final String[] propertyIds) {
        for (int propertyIdsCount = (propertyIds != null) ? propertyIds.length : 0, i = 0; i < propertyIdsCount; ++i) {
            final String propertyId = propertyIds[i];
            if (!this.fRecognizedProperties.contains(propertyId)) {
                this.fRecognizedProperties.addElement(propertyId);
            }
        }
    }
    
    public void setProperty(final String propertyId, final Object value) throws XMLConfigurationException {
        this.checkProperty(propertyId);
        this.fProperties.put(propertyId, value);
    }
    
    public boolean getFeature(final String featureId) throws XMLConfigurationException {
        final Boolean state = this.fFeatures.get(featureId);
        if (state == null) {
            this.checkFeature(featureId);
            return false;
        }
        return state;
    }
    
    public Object getProperty(final String propertyId) throws XMLConfigurationException {
        final Object propertyValue = this.fProperties.get(propertyId);
        if (propertyValue == null) {
            this.checkProperty(propertyId);
        }
        return propertyValue;
    }
    
    protected void checkFeature(final String featureId) throws XMLConfigurationException {
        if (!this.fRecognizedFeatures.contains(featureId)) {
            if (this.fParentSettings == null) {
                final short type = 0;
                throw new XMLConfigurationException(type, featureId);
            }
            this.fParentSettings.getFeature(featureId);
        }
    }
    
    protected void checkProperty(final String propertyId) throws XMLConfigurationException {
        if (!this.fRecognizedProperties.contains(propertyId)) {
            if (this.fParentSettings == null) {
                final short type = 0;
                throw new XMLConfigurationException(type, propertyId);
            }
            this.fParentSettings.getProperty(propertyId);
        }
    }
    
    static {
        ParserConfigurationSettings.counter = 1;
    }
}
