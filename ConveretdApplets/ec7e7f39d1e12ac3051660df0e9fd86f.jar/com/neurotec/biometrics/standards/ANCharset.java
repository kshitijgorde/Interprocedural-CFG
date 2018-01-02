// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class ANCharset
{
    private ANCharsetData data;
    
    ANCharset() {
        this.data = new ANCharsetData();
    }
    
    ANCharset(final ANCharsetData data) {
        this.data = data;
    }
    
    public ANCharset(final int index, final String name, final String version) {
        this.data = new ANCharsetData(index, name, version);
    }
    
    public int getIndex() {
        return this.data.index;
    }
    
    public void setIndex(final int value) {
        this.data.index = value;
    }
    
    public String getName() {
        return this.data.name;
    }
    
    public void setName(final String value) {
        this.data.name = value;
    }
    
    public String getVersion() {
        return this.data.version;
    }
    
    public void setVersion(final String value) {
        this.data.version = value;
    }
    
    ANCharsetData getData() {
        return this.data;
    }
    
    protected static class ANCharsetData extends Structure
    {
        private int index;
        private String name;
        private String version;
        
        public ANCharsetData() {
        }
        
        public ANCharsetData(final int index, final String name, final String version) {
            this.index = index;
            this.name = name;
            this.version = version;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected ANCharsetData newInstance() {
            return new ANCharsetData();
        }
        
        public static class ByReference extends ANCharsetData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends ANCharsetData implements Structure.ByValue
        {
        }
    }
}
