// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class ANFSegment
{
    private ANFSegmentData data;
    
    ANFSegment() {
        this.data = new ANFSegmentData();
    }
    
    ANFSegment(final ANFSegmentData data) {
        this.data = data;
    }
    
    public ANFSegment(final BdifFPPosition position, final int left, final int right, final int top, final int bottom) {
        this.data = new ANFSegmentData(position.getValue(), left, right, top, bottom);
    }
    
    public BdifFPPosition getPosition() {
        return BdifFPPosition.get(this.data.position);
    }
    
    public void setPosition(final BdifFPPosition position) {
        this.data.position = position.getValue();
    }
    
    public int getLeft() {
        return this.data.left;
    }
    
    public void setLeft(final int left) {
        this.data.left = left;
    }
    
    public int getRight() {
        return this.data.right;
    }
    
    public void setRight(final int right) {
        this.data.right = right;
    }
    
    public int getTop() {
        return this.data.top;
    }
    
    public void setTop(final int top) {
        this.data.top = top;
    }
    
    public int getBottom() {
        return this.data.bottom;
    }
    
    public void setBottom(final int bottom) {
        this.data.bottom = bottom;
    }
    
    ANFSegmentData getData() {
        return this.data;
    }
    
    protected static class ANFSegmentData extends Structure
    {
        public int position;
        public int left;
        public int right;
        public int top;
        public int bottom;
        
        public ANFSegmentData() {
        }
        
        public ANFSegmentData(final int position, final int left, final int right, final int top, final int bottom) {
            this.position = position;
            this.left = left;
            this.right = right;
            this.top = top;
            this.bottom = bottom;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected ANFSegmentData newInstance() {
            return new ANFSegmentData();
        }
        
        public static class ByReference extends ANFSegmentData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends ANFSegmentData implements Structure.ByValue
        {
        }
    }
}
