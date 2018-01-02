// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.tools;

import java.lang.reflect.Method;

public class FieldWrapper
{
    private String textDisplay;
    private String fieldName;
    private Object object;
    private String iconPath;
    private int width;
    
    public FieldWrapper(final String textDisplay, final String fieldName, final Object object) {
        this.width = -1;
        this.textDisplay = textDisplay;
        this.fieldName = fieldName;
        this.object = object;
    }
    
    public FieldWrapper(final String textDisplay, final String fieldName, final Object object, final String iconPath) {
        this.width = -1;
        this.textDisplay = textDisplay;
        this.fieldName = fieldName;
        this.object = object;
        this.iconPath = iconPath;
    }
    
    public FieldWrapper(final String textDisplay, final String fieldName, final Object object, final int width, final String iconPath) {
        this.width = -1;
        this.textDisplay = textDisplay;
        this.fieldName = fieldName;
        this.object = object;
        this.width = width;
        this.iconPath = iconPath;
    }
    
    public String getTextDisplay() {
        return this.textDisplay;
    }
    
    public void setTextDisplay(final String textDisplay) {
        this.textDisplay = textDisplay;
    }
    
    public String getFieldName() {
        return this.fieldName;
    }
    
    public Object getObject() {
        return this.object;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public String getIconPath() {
        return this.iconPath;
    }
    
    public boolean hasField() {
        return this.fieldName != null;
    }
    
    public Object getValue() throws Exception {
        return this.getGetMethod().invoke(this.object, new Object[0]);
    }
    
    public void setValue(final Object value) throws Exception {
        this.getSetMethod().invoke(this.object, value);
    }
    
    public Class getFieldType() throws Exception {
        return this.getGetMethod().getReturnType();
    }
    
    private Method getGetMethod() throws Exception {
        final String methodName = "get" + this.fieldName.substring(0, 1).toUpperCase() + this.fieldName.substring(1, this.fieldName.length());
        return this.object.getClass().getMethod(methodName, (Class<?>[])new Class[0]);
    }
    
    private Method getSetMethod() throws Exception {
        final String methodName = "set" + this.fieldName.substring(0, 1).toUpperCase() + this.fieldName.substring(1, this.fieldName.length());
        return this.object.getClass().getMethod(methodName, this.getFieldType());
    }
}
