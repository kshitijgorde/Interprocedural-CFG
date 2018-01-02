// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.lang.NResult;
import com.neurotec.lang.NObject;
import com.neurotec.biometrics.standards.util.ANRecordSimpleCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import java.util.Date;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class ANType13Record extends ANFPImageAsciiBinaryRecord
{
    static final ANType13RecordLibrary LIBRARY;
    public static final int FIELD_LCD = 5;
    public static final int FIELD_SPD = 14;
    public static final int FIELD_LQM = 24;
    public static final int MAX_SEARCH_POSITION_DESCRIPTOR_COUNT = 9;
    public static final int MAX_QUALITY_METRIC_COUNT = 4;
    private SearchPositionDescriptorCollection searchPositionDescriptors;
    public static final NNativeType NATIVE_TYPE;
    
    ANType13Record(final HNObject handle) {
        super(handle, ANType13Record.NATIVE_TYPE);
        this.searchPositionDescriptors = new SearchPositionDescriptorCollection(this);
    }
    
    public Date getLatentCaptureDate() {
        return this.getDate();
    }
    
    public void setLatentCaptureDate(final Date value) {
        this.setDate(value);
    }
    
    public SearchPositionDescriptorCollection getSearchPositionDescriptors() {
        this.check();
        return this.searchPositionDescriptors;
    }
    
    static {
        LIBRARY = (ANType13RecordLibrary)Native.loadLibrary("NBiometricStandards", ANType13RecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANType13Record.LIBRARY.ANType13RecordTypeOf());
    }
    
    public final class SearchPositionDescriptorCollection extends ANRecordSimpleCollection<ANFPositionDescriptor>
    {
        protected SearchPositionDescriptorCollection(final ANType13Record owner) {
            super(owner, 14, false, false, false);
        }
        
        protected void addNative(final ANFPositionDescriptor value) {
            NResult.check(ANType13Record.LIBRARY.ANType13RecordAddSearchPositionDescriptor(this.getOwner().getHandle(), value.getData()));
        }
        
        protected int addWithIndexNative(final ANFPositionDescriptor value) {
            throw new UnsupportedOperationException();
        }
        
        protected ANFPositionDescriptor[] getAllNative() {
            final int size = this.sizeNative();
            final ANFPositionDescriptor.ANFPositionDescriptorData[] structures = (ANFPositionDescriptor.ANFPositionDescriptorData[])new ANFPositionDescriptor.ANFPositionDescriptorData().toArray(size);
            final ANFPositionDescriptor[] values = new ANFPositionDescriptor[size];
            ANType13Record.LIBRARY.ANType13RecordGetSearchPositionDescriptorsEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new ANFPositionDescriptor(structures[i]);
            }
            return values;
        }
        
        protected ANFPositionDescriptor getNative(final int index) {
            final ANFPositionDescriptor value = new ANFPositionDescriptor();
            NResult.check(ANType13Record.LIBRARY.ANType13RecordGetSearchPositionDescriptor(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected void addNative(final int index, final ANFPositionDescriptor value) {
            NResult.check(ANType13Record.LIBRARY.ANType13RecordInsertSearchPositionDescriptor(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void setNative(final int index, final ANFPositionDescriptor value) {
            NResult.check(ANType13Record.LIBRARY.ANType13RecordSetSearchPositionDescriptor(this.getOwner().getHandle(), index, value.getData()));
        }
        
        protected void clearNative() {
            NResult.check(ANType13Record.LIBRARY.ANType13RecordClearSearchPositionDescriptors(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType13Record.LIBRARY.ANType13RecordGetSearchPositionDescriptorCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType13Record.LIBRARY.ANType13RecordRemoveSearchPositionDescriptor(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final ANFPositionDescriptor value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    interface ANType13RecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANType13RecordTypeOf();
        
        int ANType13RecordGetSearchPositionDescriptorCount(final HNObject p0, final IntByReference p1);
        
        int ANType13RecordGetSearchPositionDescriptor(final HNObject p0, final int p1, final ANFPositionDescriptor.ANFPositionDescriptorData p2);
        
        int ANType13RecordGetSearchPositionDescriptorsEx(final HNObject p0, final ANFPositionDescriptor.ANFPositionDescriptorData[] p1, final int p2);
        
        int ANType13RecordSetSearchPositionDescriptor(final HNObject p0, final int p1, final ANFPositionDescriptor.ANFPositionDescriptorData p2);
        
        int ANType13RecordAddSearchPositionDescriptor(final HNObject p0, final ANFPositionDescriptor.ANFPositionDescriptorData p1);
        
        int ANType13RecordInsertSearchPositionDescriptor(final HNObject p0, final int p1, final ANFPositionDescriptor.ANFPositionDescriptorData p2);
        
        int ANType13RecordRemoveSearchPositionDescriptor(final HNObject p0, final int p1);
        
        int ANType13RecordClearSearchPositionDescriptors(final HNObject p0);
    }
}
