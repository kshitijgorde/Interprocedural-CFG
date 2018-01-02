// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.images.NImage;
import com.neurotec.biometrics.NFRecord;
import com.neurotec.images.NMonochromeImage;
import com.neurotec.images.NGrayscaleImage;
import com.neurotec.util.NObjectCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.ShortByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import com.neurotec.lang.NCore;
import com.sun.jna.Pointer;
import com.neurotec.jna.ptr.BooleanByReference;
import java.nio.Buffer;
import com.neurotec.jna.ptr.NativeSize;
import com.neurotec.jna.ptr.NativeSizeByReference;
import com.sun.jna.WString;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.biometrics.NTemplate;
import java.nio.ByteBuffer;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NVersion;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class ANTemplate extends NObject implements Cloneable
{
    private RecordCollection records;
    static final ANTemplateLibrary LIBRARY;
    public static final int FLAG_USE_NIST_MINUTIA_NEIGHBORS = 65536;
    public static final int FLAG_LEAVE_INVALID_RECORDS_UNVALIDATED = 131072;
    public static final NNativeType NATIVE_TYPE;
    public static final NVersion VERSION_20;
    public static final NVersion VERSION_21;
    public static final NVersion VERSION_30;
    public static final NVersion VERSION_40;
    public static final NVersion VERSION_CURRENT;
    
    private ANTemplate(final HNObject handle, final boolean claimHandle) {
        super(handle, ANTemplate.NATIVE_TYPE, claimHandle);
        this.records = new RecordCollection(this);
    }
    
    public ANTemplate() {
        this(create(0), true);
    }
    
    public ANTemplate(final int flags) {
        this(create(flags), true);
    }
    
    public ANTemplate(final String tot, final String dai, final String ori, final String tcn, final int flags) {
        this(create(tot, dai, ori, tcn, flags), true);
    }
    
    public ANTemplate(final String tot, final String dai, final String ori, final String tcn) {
        this(create(tot, dai, ori, tcn, 0), true);
    }
    
    public ANTemplate(final String fileName, final ANValidationLevel validationLevel, final int flags) {
        this(create(fileName, validationLevel, flags), true);
    }
    
    public ANTemplate(final String fileName, final ANValidationLevel validationLevel) {
        this(create(fileName, validationLevel, 0), true);
    }
    
    public ANTemplate(final ByteBuffer buffer, final ANValidationLevel validationLevel) {
        this(create(buffer, validationLevel, 0), true);
    }
    
    public ANTemplate(final ByteBuffer buffer, final ANValidationLevel validationLevel, final int flags) {
        this(create(buffer, validationLevel, flags), true);
    }
    
    public ANTemplate(final String tot, final String dai, final String ori, final String tcn, final boolean type9RecordFmt, final NTemplate nTemplate, final int flags) {
        this(create(tot, dai, ori, tcn, type9RecordFmt, nTemplate, flags), true);
    }
    
    public ANTemplate(final String tot, final String dai, final String ori, final String tcn, final boolean type9RecordFmt, final NTemplate nTemplate) {
        this(create(tot, dai, ori, tcn, type9RecordFmt, nTemplate, 0), true);
    }
    
    private static HNObject create(final int flags) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(ANTemplate.LIBRARY.ANTemplateCreateWithMinimalValidation(flags, rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final String tot, final String dai, final String ori, final String tcn, final int flags) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(ANTemplate.LIBRARY.ANTemplateCreate(new WString(tot), new WString(dai), new WString(ori), new WString(tcn), flags, rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final String fileName, final ANValidationLevel validationLevel, final int flags) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(ANTemplate.LIBRARY.ANTemplateCreateFromFile(new WString(fileName), validationLevel.getValue(), flags, rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final ByteBuffer buffer, final ANValidationLevel validationLevel, final int flags) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(ANTemplate.LIBRARY.ANTemplateCreateFromMemoryEx(buffer, new NativeSize((long)buffer.remaining()), validationLevel.getValue(), flags, rSize, rhValue));
        buffer.position(buffer.position() + rSize.getValue().intValue());
        return rhValue.getValue();
    }
    
    private static HNObject create(final String tot, final String dai, final String ori, final String tcn, final boolean type9RecordFmt, final NTemplate nTemplate, final int flags) {
        if (nTemplate == null) {
            throw new NullPointerException("nTemplate");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(ANTemplate.LIBRARY.ANTemplateCreateFromNTemplate(new WString(tot), new WString(dai), new WString(ori), new WString(tcn), type9RecordFmt, nTemplate.getHandle(), flags, rhValue));
        return rhValue.getValue();
    }
    
    public static NVersion[] getVersions() {
        final int size = NResult.check(ANTemplate.LIBRARY.ANTemplateGetVersionsEx(null, 0));
        final short[] values = new short[size];
        final NVersion[] versions = new NVersion[size];
        NResult.check(ANTemplate.LIBRARY.ANTemplateGetVersionsEx(values, size));
        for (int i = 0; i < size; ++i) {
            versions[i] = new NVersion(values[i]);
        }
        return versions;
    }
    
    public static boolean isVersionSupported(final NVersion version) {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(ANTemplate.LIBRARY.ANTemplateIsVersionSupported(version.getValue(), rValue));
        return rValue.getValue();
    }
    
    public static String getVersionName(final NVersion version) {
        final int pLen = ANTemplate.LIBRARY.ANTemplateGetVersionNameEx(version.getValue(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANTemplate.LIBRARY.ANTemplateGetVersionNameEx(version.getValue(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public static ANTemplate fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static ANTemplate fromHandle(final HNObject handle, final boolean ownsHandle) {
        final ANTemplate value = new ANTemplate(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    private void refresh() {
        this.records.onChanged();
    }
    
    protected void dispose(final boolean disposing) {
        if (disposing) {
            this.records.disposeItems();
        }
        this.records = null;
        super.dispose(disposing);
    }
    
    public void save(final String fileName) {
        this.save(fileName, 0);
    }
    
    public void save(final String fileName, final int flags) {
        NResult.check(ANTemplate.LIBRARY.ANTemplateSaveToFile(this.getHandle(), new WString(fileName), flags));
    }
    
    public ByteBuffer save() {
        return this.save(0);
    }
    
    public ByteBuffer save(final int flags) {
        ByteBuffer value = null;
        final PointerByReference rValue = new PointerByReference();
        Pointer pValue = null;
        final NativeSizeByReference rBufferLength = new NativeSizeByReference();
        NResult.check(ANTemplate.LIBRARY.ANTemplateSaveToMemory(this.getHandle(), flags, rValue, rBufferLength));
        try {
            pValue = rValue.getValue();
            value = NCore.getByteBuffer(rValue.getValue(), rBufferLength.getValue().longValue());
        }
        finally {
            if (value == null) {
                NCore.free(pValue);
            }
        }
        return value;
    }
    
    public NTemplate toNTemplate(final int flags) {
        NTemplate value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(ANTemplate.LIBRARY.ANTemplateToNTemplate(this.getHandle(), flags, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NTemplate.fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public NTemplate toNTemplate() {
        return this.toNTemplate(0);
    }
    
    public ANValidationLevel getValidationLevel() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANTemplate.LIBRARY.ANTemplateGetValidationLevel(this.getHandle(), rValue));
        return ANValidationLevel.get(rValue.getValue());
    }
    
    public NVersion getVersion() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(ANTemplate.LIBRARY.ANTemplateGetVersion(this.getHandle(), rValue));
        return new NVersion(rValue.getValue());
    }
    
    public void setVersion(final NVersion value) {
        NResult.check(ANTemplate.LIBRARY.ANTemplateSetVersion(this.getHandle(), value.getValue()));
    }
    
    public RecordCollection getRecords() {
        this.check();
        return this.records;
    }
    
    public Object clone() throws CloneNotSupportedException {
        ANTemplate value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        try {
            NResult.check(ANTemplate.LIBRARY.ANTemplateClone(this.getHandle(), rhValue));
            hValue = rhValue.getValue();
            value = fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    static {
        LIBRARY = (ANTemplateLibrary)Native.loadLibrary("NBiometricStandards", ANTemplateLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANTemplate.LIBRARY.ANTemplateTypeOf());
        VERSION_20 = new NVersion((short)512);
        VERSION_21 = new NVersion((short)513);
        VERSION_30 = new NVersion((short)768);
        VERSION_40 = new NVersion((short)1024);
        VERSION_CURRENT = ANTemplate.VERSION_40;
    }
    
    public final class RecordCollection extends NObjectCollection<ANRecord>
    {
        RecordCollection(final NObject owner) {
            super(owner, false);
        }
        
        private void refreshCnt() {
            this.get(0).getFields().get(2).refresh();
        }
        
        void onChanged() {
            this.refreshItems();
        }
        
        protected void onRefreshItem(final ANRecord item) {
            item.refresh();
            super.onRefreshItem(item);
        }
        
        protected void onInsertItem(final int index, final ANRecord item) {
            super.onInsertItem(index, item);
            this.refreshCnt();
        }
        
        protected void onRemoveItemRange(final int index, final int count) {
            this.refreshCnt();
            if (index == 0) {
                super.onRemoveItemRange(index + 1, count - 1);
            }
            else {
                super.onRemoveItemRange(index, count);
            }
        }
        
        public ANRecord add(final ANRecordType recordType, final int idc, final int flags) {
            if (recordType == null) {
                throw new NullPointerException("recordType");
            }
            ANRecord value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateAddRecord(this.getOwner().getHandle(), recordType.getHandle(), idc, flags, rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANTemplate.LIBRARY.ANTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public ANRecord add(final ANRecordType recordType, final int idc) {
            return this.add(recordType, idc, 0);
        }
        
        public ANType2Record addType2(final int idc, final int flags) {
            ANType2Record value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateAddType2Record(this.getOwner().getHandle(), idc, flags, rhValue));
            try {
                value = ((NObjectCollection<ANType2Record>)this).addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANTemplate.LIBRARY.ANTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public ANType2Record addType2(final int idc) {
            return this.addType2(idc, 0);
        }
        
        public ANType3Record addType3(final int idc, final boolean isr, final int isrValue, final ANImageCompressionAlgorithm ca, final NGrayscaleImage image, final int flags) {
            if (image == null) {
                throw new NullPointerException("image");
            }
            ANType3Record value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateAddType3RecordFromNImage(this.getOwner().getHandle(), idc, isr, isrValue, ca.getValue(), image.getHandle(), flags, rhValue));
            try {
                value = ((NObjectCollection<ANType3Record>)this).addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANTemplate.LIBRARY.ANTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public ANType3Record addType3(final int idc, final boolean isr, final int isrValue, final ANImageCompressionAlgorithm ca, final NGrayscaleImage image) {
            return this.addType3(idc, isr, isrValue, ca, image, 0);
        }
        
        public ANType4Record addType4(final int idc, final boolean isr, final int isrValue, final ANImageCompressionAlgorithm ca, final NGrayscaleImage image, final int flags) {
            if (image == null) {
                throw new NullPointerException("image");
            }
            ANType4Record value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateAddType4RecordFromNImage(this.getOwner().getHandle(), idc, isr, isrValue, ca.getValue(), image.getHandle(), flags, rhValue));
            try {
                value = ((NObjectCollection<ANType4Record>)this).addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANTemplate.LIBRARY.ANTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public ANType4Record addType4(final int idc, final boolean isr, final int isrValue, final ANImageCompressionAlgorithm ca, final NGrayscaleImage image) {
            return this.addType4(idc, isr, isrValue, ca, image, 0);
        }
        
        public ANType5Record addType5(final int idc, final boolean isr, final int isrValue, final ANBinaryImageCompressionAlgorithm ca, final NMonochromeImage image, final int flags) {
            if (image == null) {
                throw new NullPointerException("image");
            }
            ANType5Record value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateAddType5RecordFromNImage(this.getOwner().getHandle(), idc, isr, isrValue, ca.getValue(), image.getHandle(), flags, rhValue));
            try {
                value = ((NObjectCollection<ANType5Record>)this).addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANTemplate.LIBRARY.ANTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public ANType5Record addType5(final int idc, final boolean isr, final int isrValue, final ANBinaryImageCompressionAlgorithm ca, final NMonochromeImage image) {
            return this.addType5(idc, isr, isrValue, ca, image, 0);
        }
        
        public ANType6Record addType6(final int idc, final boolean isr, final int isrValue, final ANBinaryImageCompressionAlgorithm ca, final NMonochromeImage image, final int flags) {
            if (image == null) {
                throw new NullPointerException("image");
            }
            ANType6Record value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateAddType6RecordFromNImage(this.getOwner().getHandle(), idc, isr, isrValue, ca.getValue(), image.getHandle(), flags, rhValue));
            try {
                value = ((NObjectCollection<ANType6Record>)this).addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANTemplate.LIBRARY.ANTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public ANType6Record addType6(final int idc, final boolean isr, final int isrValue, final ANBinaryImageCompressionAlgorithm ca, final NMonochromeImage image) {
            return this.addType6(idc, isr, isrValue, ca, image, 0);
        }
        
        public ANType8Record addType8(final int idc, final int isr, final int ir, final ANPenVector[] penVectors, final int flags) {
            ANType8Record value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            final ANPenVector.ANPenVectorData[] penVectorsData = new ANPenVector.ANPenVectorData[penVectors.length];
            for (int i = 0; i < penVectors.length; ++i) {
                penVectorsData[i] = penVectors[i].getData();
            }
            NResult.check(ANTemplate.LIBRARY.ANTemplateAddType8RecordFromVectors(this.getOwner().getHandle(), idc, isr, ir, penVectorsData, (penVectors == null) ? 0 : penVectors.length, flags, rhValue));
            try {
                value = ((NObjectCollection<ANType8Record>)this).addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANTemplate.LIBRARY.ANTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public ANType8Record addType8(final int idc, final int isr, final int ir, final ANPenVector[] penVectors) {
            return this.addType8(idc, isr, ir, penVectors, 0);
        }
        
        public ANType8Record addType8(final int idc, final ANSignatureRepresentationType srt, final boolean isr, final int isrValue, final NMonochromeImage image, final int flags) {
            if (image == null) {
                throw new NullPointerException("image");
            }
            ANType8Record value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateAddType8RecordFromNImage(this.getOwner().getHandle(), idc, srt.getValue(), isr, isrValue, image.getHandle(), flags, rhValue));
            try {
                value = ((NObjectCollection<ANType8Record>)this).addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANTemplate.LIBRARY.ANTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public ANType8Record addType8(final int idc, final ANSignatureRepresentationType srt, final boolean isr, final int isrValue, final NMonochromeImage image) {
            return this.addType8(idc, srt, isr, isrValue, image, 0);
        }
        
        public ANType9Record addType9(final int idc, final BdifFPImpressionType imp, final boolean fmt, final boolean hasMinutiae, final boolean rdg, final boolean hasMinutiaeRidgeCountsIndicator, final int flags) {
            ANType9Record value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateAddType9Record(this.getOwner().getHandle(), idc, imp.getValue(), fmt, hasMinutiae, rdg, hasMinutiaeRidgeCountsIndicator, flags, rhValue));
            try {
                value = ((NObjectCollection<ANType9Record>)this).addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANTemplate.LIBRARY.ANTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public ANType9Record addType9(final int idc, final BdifFPImpressionType imp, final boolean fmt, final boolean hasMinutiae, final boolean rdg, final boolean hasMinutiaeRidgeCountsIndicator) {
            return this.addType9(idc, imp, fmt, hasMinutiae, rdg, hasMinutiaeRidgeCountsIndicator, 0);
        }
        
        public ANType9Record addType9(final int idc, final BdifFPImpressionType imp, final boolean rdg, final int flags) {
            return this.addType9(idc, imp, true, true, rdg, true, flags);
        }
        
        public ANType9Record addType9(final int idc, final BdifFPImpressionType imp, final boolean rdg) {
            return this.addType9(idc, imp, rdg, 0);
        }
        
        public ANType9Record addType9(final int idc, final boolean fmt, final NFRecord nfRecord, final int flags) {
            ANType9Record value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateAddType9RecordFromNFRecord(this.getOwner().getHandle(), idc, fmt, nfRecord.getHandle(), flags, rhValue));
            try {
                value = ((NObjectCollection<ANType9Record>)this).addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANTemplate.LIBRARY.ANTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public ANType9Record addType9(final int idc, final boolean fmt, final NFRecord nfRecord) {
            return this.addType9(idc, fmt, nfRecord, 0);
        }
        
        public ANType10Record addType10(final int idc, final ANImageType imt, final String src, final BdifScaleUnits slc, final ANImageCompressionAlgorithm cga, final String smt, final NImage image, final int flags) {
            if (image == null) {
                throw new NullPointerException("image");
            }
            ANType10Record value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateAddType10RecordFromNImage(this.getOwner().getHandle(), idc, imt.getValue(), (src == null) ? null : new WString(src), slc.getValue(), cga.getValue(), (smt == null) ? null : new WString(smt), image.getHandle(), flags, rhValue));
            try {
                value = ((NObjectCollection<ANType10Record>)this).addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANTemplate.LIBRARY.ANTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public ANType10Record addType10(final int idc, final ANImageType imt, final String src, final BdifScaleUnits slc, final ANImageCompressionAlgorithm cga, final String smt, final NImage image) {
            return this.addType10(idc, imt, src, slc, cga, smt, image, 0);
        }
        
        public ANType13Record addType13(final int idc, final BdifFPImpressionType imp, final String src, final BdifScaleUnits slc, final ANImageCompressionAlgorithm cga, final NImage image, final int flags) {
            if (image == null) {
                throw new NullPointerException("image");
            }
            ANType13Record value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateAddType13RecordFromNImage(this.getOwner().getHandle(), idc, imp.getValue(), new WString(src), slc.getValue(), cga.getValue(), image.getHandle(), flags, rhValue));
            try {
                value = ((NObjectCollection<ANType13Record>)this).addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANTemplate.LIBRARY.ANTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public ANType13Record addType13(final int idc, final BdifFPImpressionType imp, final String src, final BdifScaleUnits slc, final ANImageCompressionAlgorithm cga, final NImage image) {
            return this.addType13(idc, imp, src, slc, cga, image, 0);
        }
        
        public ANType14Record addType14(final int idc, final String src, final BdifScaleUnits slc, final ANImageCompressionAlgorithm cga, final NImage image, final int flags) {
            if (image == null) {
                throw new NullPointerException("image");
            }
            ANType14Record value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateAddType14RecordFromNImage(this.getOwner().getHandle(), idc, new WString(src), slc.getValue(), cga.getValue(), image.getHandle(), flags, rhValue));
            try {
                value = ((NObjectCollection<ANType14Record>)this).addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANTemplate.LIBRARY.ANTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public ANType14Record addType14(final int idc, final String src, final BdifScaleUnits slc, final ANImageCompressionAlgorithm cga, final NImage image) {
            return this.addType14(idc, src, slc, cga, image, 0);
        }
        
        public ANType15Record addType15(final int idc, final String src, final BdifScaleUnits slc, final ANImageCompressionAlgorithm cga, final NImage image, final int flags) {
            if (image == null) {
                throw new NullPointerException("image");
            }
            ANType15Record value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateAddType15RecordFromNImage(this.getOwner().getHandle(), idc, new WString(src), slc.getValue(), cga.getValue(), image.getHandle(), flags, rhValue));
            try {
                value = ((NObjectCollection<ANType15Record>)this).addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANTemplate.LIBRARY.ANTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public ANType15Record addType15(final int idc, final String src, final BdifScaleUnits slc, final ANImageCompressionAlgorithm cga, final NImage image) {
            return this.addType15(idc, src, slc, cga, image, 0);
        }
        
        public ANType16Record addType16(final int idc, final String udi, final String src, final BdifScaleUnits slc, final ANImageCompressionAlgorithm cga, final NImage image, final int flags) {
            if (image == null) {
                throw new NullPointerException("image");
            }
            ANType16Record value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateAddType16RecordFromNImage(this.getOwner().getHandle(), idc, new WString(udi), new WString(src), slc.getValue(), cga.getValue(), image.getHandle(), flags, rhValue));
            try {
                value = ((NObjectCollection<ANType16Record>)this).addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANTemplate.LIBRARY.ANTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public ANType16Record addType16(final int idc, final String udi, final String src, final BdifScaleUnits slc, final ANImageCompressionAlgorithm cga, final NImage image) {
            return this.addType16(idc, udi, src, slc, cga, image, 0);
        }
        
        public ANType17Record addType17(final int idc, final String src, final BdifScaleUnits slc, final ANImageCompressionAlgorithm cga, final NImage image, final int flags) {
            if (image == null) {
                throw new NullPointerException("image");
            }
            ANType17Record value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateAddType17RecordFromNImage(this.getOwner().getHandle(), idc, new WString(src), slc.getValue(), cga.getValue(), image.getHandle(), flags, rhValue));
            try {
                value = ((NObjectCollection<ANType17Record>)this).addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANTemplate.LIBRARY.ANTemplateRemoveRecord(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public ANType17Record addType17(final int idc, final String src, final BdifScaleUnits slc, final ANImageCompressionAlgorithm cga, final NImage image) {
            return this.addType17(idc, src, slc, cga, image, 0);
        }
        
        protected void clearNative() {
            NResult.check(ANTemplate.LIBRARY.ANTemplateClearRecords(this.getOwner().getHandle()));
        }
        
        protected ANRecord fromHandleNative(final HNObject handle) {
            return ANRecord.fromHandle(handle);
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateGetRecord(this.getOwner().getHandle(), index, rhValue));
            return rhValue.getValue();
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateGetRecordCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(ANTemplate.LIBRARY.ANTemplateSetRecordCapacity(this.getOwner().getHandle(), value));
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANTemplate.LIBRARY.ANTemplateGetRecordCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANTemplate.LIBRARY.ANTemplateRemoveRecord(this.getOwner().getHandle(), index));
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
    }
    
    interface ANTemplateLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANTemplateTypeOf();
        
        int ANTemplateGetVersionsEx(final short[] p0, final int p1);
        
        int ANTemplateIsVersionSupported(final short p0, final BooleanByReference p1);
        
        int ANTemplateGetVersionNameEx(final short p0, final Pointer p1, final int p2);
        
        int ANTemplateCreateWithMinimalValidation(final int p0, final HNObjectByReference p1);
        
        int ANTemplateCreate(final WString p0, final WString p1, final WString p2, final WString p3, final int p4, final HNObjectByReference p5);
        
        int ANTemplateCreateFromFile(final WString p0, final int p1, final int p2, final HNObjectByReference p3);
        
        int ANTemplateCreateFromMemoryEx(final Buffer p0, final NativeSize p1, final int p2, final int p3, final NativeSizeByReference p4, final HNObjectByReference p5);
        
        int ANTemplateCreateFromNTemplate(final WString p0, final WString p1, final WString p2, final WString p3, final boolean p4, final HNObject p5, final int p6, final HNObjectByReference p7);
        
        int ANTemplateClone(final HNObject p0, final HNObjectByReference p1);
        
        int ANTemplateSaveToFile(final HNObject p0, final WString p1, final int p2);
        
        int ANTemplateSaveToMemory(final HNObject p0, final int p1, final PointerByReference p2, final NativeSizeByReference p3);
        
        int ANTemplateToNTemplate(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int ANTemplateGetValidationLevel(final HNObject p0, final IntByReference p1);
        
        int ANTemplateGetVersion(final HNObject p0, final ShortByReference p1);
        
        int ANTemplateSetVersion(final HNObject p0, final short p1);
        
        int ANTemplateGetRecordCount(final HNObject p0, final IntByReference p1);
        
        int ANTemplateGetRecord(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int ANTemplateGetRecordCapacity(final HNObject p0, final IntByReference p1);
        
        int ANTemplateSetRecordCapacity(final HNObject p0, final int p1);
        
        int ANTemplateAddRecord(final HNObject p0, final HNObject p1, final int p2, final int p3, final HNObjectByReference p4);
        
        int ANTemplateAddType2Record(final HNObject p0, final int p1, final int p2, final HNObjectByReference p3);
        
        int ANTemplateAddType3RecordFromNImage(final HNObject p0, final int p1, final boolean p2, final int p3, final int p4, final HNObject p5, final int p6, final HNObjectByReference p7);
        
        int ANTemplateAddType4RecordFromNImage(final HNObject p0, final int p1, final boolean p2, final int p3, final int p4, final HNObject p5, final int p6, final HNObjectByReference p7);
        
        int ANTemplateAddType5RecordFromNImage(final HNObject p0, final int p1, final boolean p2, final int p3, final int p4, final HNObject p5, final int p6, final HNObjectByReference p7);
        
        int ANTemplateAddType6RecordFromNImage(final HNObject p0, final int p1, final boolean p2, final int p3, final int p4, final HNObject p5, final int p6, final HNObjectByReference p7);
        
        int ANTemplateAddType7Record(final HNObject p0, final int p1, final boolean p2, final int p3, final HNObject p4, final NativeSize p5, final int p6, final HNObjectByReference p7);
        
        int ANTemplateAddType8RecordFromVectors(final HNObject p0, final int p1, final int p2, final int p3, final ANPenVector.ANPenVectorData[] p4, final int p5, final int p6, final HNObjectByReference p7);
        
        int ANTemplateAddType8RecordFromNImage(final HNObject p0, final int p1, final int p2, final boolean p3, final int p4, final HNObject p5, final int p6, final HNObjectByReference p7);
        
        int ANTemplateAddType9Record(final HNObject p0, final int p1, final int p2, final boolean p3, final boolean p4, final boolean p5, final boolean p6, final int p7, final HNObjectByReference p8);
        
        int ANTemplateAddType9RecordFromNFRecord(final HNObject p0, final int p1, final boolean p2, final HNObject p3, final int p4, final HNObjectByReference p5);
        
        int ANTemplateAddType10RecordFromNImage(final HNObject p0, final int p1, final int p2, final WString p3, final int p4, final int p5, final WString p6, final HNObject p7, final int p8, final HNObjectByReference p9);
        
        int ANTemplateAddType13RecordFromNImage(final HNObject p0, final int p1, final int p2, final WString p3, final int p4, final int p5, final HNObject p6, final int p7, final HNObjectByReference p8);
        
        int ANTemplateAddType14RecordFromNImage(final HNObject p0, final int p1, final WString p2, final int p3, final int p4, final HNObject p5, final int p6, final HNObjectByReference p7);
        
        int ANTemplateAddType15RecordFromNImage(final HNObject p0, final int p1, final WString p2, final int p3, final int p4, final HNObject p5, final int p6, final HNObjectByReference p7);
        
        int ANTemplateAddType16RecordFromNImage(final HNObject p0, final int p1, final WString p2, final WString p3, final int p4, final int p5, final HNObject p6, final int p7, final HNObjectByReference p8);
        
        int ANTemplateAddType17RecordFromNImage(final HNObject p0, final int p1, final WString p2, final int p3, final int p4, final HNObject p5, final int p6, final HNObjectByReference p7);
        
        int ANTemplateAddType99Record(final HNObject p0, final int p1, final WString p2, final short p3, final int p4, final short p5, final short p6, final HNObject p7, final NativeSize p8, final int p9, final HNObjectByReference p10);
        
        int ANTemplateRemoveRecord(final HNObject p0, final int p1);
        
        int ANTemplateClearRecords(final HNObject p0);
    }
}
