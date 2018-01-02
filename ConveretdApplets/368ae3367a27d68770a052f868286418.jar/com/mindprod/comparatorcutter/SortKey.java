// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.comparatorcutter;

class SortKey
{
    private final FieldType fieldType;
    private final String fieldName;
    private final boolean isFieldDescending;
    
    public SortKey(final FieldType fieldType, final boolean isFieldDescending, final String fieldName) {
        this.fieldType = fieldType;
        this.isFieldDescending = isFieldDescending;
        this.fieldName = fieldName;
    }
    
    public String getFieldName() {
        return this.fieldName;
    }
    
    public FieldType getFieldType() {
        return this.fieldType;
    }
    
    public boolean isFieldDescending() {
        return this.isFieldDescending;
    }
}
