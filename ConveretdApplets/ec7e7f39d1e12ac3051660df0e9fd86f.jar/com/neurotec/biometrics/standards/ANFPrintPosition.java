// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class ANFPrintPosition
{
    private ANFPrintPositionData data;
    
    ANFPrintPosition() {
        this.data = new ANFPrintPositionData();
    }
    
    ANFPrintPosition(final ANFPrintPositionData data) {
        this.data = data;
    }
    
    public ANFPrintPosition(final ANFMajorCase fingerView, final ANFMajorCase segment, final int left, final int right, final int top, final int bottom) {
        this.data = new ANFPrintPositionData(fingerView.getValue(), segment.getValue(), left, right, top, bottom);
    }
    
    public ANFMajorCase getFingerView() {
        return ANFMajorCase.get(this.data.fingerView);
    }
    
    public void setFingerView(final ANFMajorCase fingerView) {
        this.data.fingerView = fingerView.getValue();
    }
    
    public ANFMajorCase getSegment() {
        return ANFMajorCase.get(this.data.segment);
    }
    
    public void setSegment(final ANFMajorCase segment) {
        this.data.segment = segment.getValue();
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
    
    ANFPrintPositionData getData() {
        return this.data;
    }
    
    protected static class ANFPrintPositionData extends Structure
    {
        public int fingerView;
        public int segment;
        public int left;
        public int right;
        public int top;
        public int bottom;
        
        public ANFPrintPositionData() {
        }
        
        public ANFPrintPositionData(final int fingerView, final int segment, final int left, final int right, final int top, final int bottom) {
            this.fingerView = fingerView;
            this.segment = segment;
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
        
        protected ANFPrintPositionData newInstance() {
            return new ANFPrintPositionData();
        }
        
        public static class ByReference extends ANFPrintPositionData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends ANFPrintPositionData implements Structure.ByValue
        {
        }
    }
}
