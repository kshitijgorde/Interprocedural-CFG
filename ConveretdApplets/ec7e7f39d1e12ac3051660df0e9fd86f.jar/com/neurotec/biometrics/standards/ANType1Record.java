// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.lang.NObject;
import com.neurotec.biometrics.standards.util.ANRecordSimpleCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.LongByReference;
import java.util.Date;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.lang.NCore;
import com.sun.jna.Pointer;
import com.neurotec.jna.ptr.BooleanByReference;
import com.neurotec.lang.NResult;
import com.neurotec.lang.NVersion;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class ANType1Record extends ANAsciiRecord
{
    private CharsetCollection charsets;
    static final ANType1RecordLibrary LIBRARY;
    public static final int FIELD_VER = 2;
    public static final int FIELD_CNT = 3;
    public static final int FIELD_TOT = 4;
    public static final int FIELD_DAT = 5;
    public static final int FIELD_PRY = 6;
    public static final int FIELD_DAI = 7;
    public static final int FIELD_ORI = 8;
    public static final int FIELD_TCN = 9;
    public static final int FIELD_TCR = 10;
    public static final int FIELD_NSR = 11;
    public static final int FIELD_NTR = 12;
    public static final int FIELD_DOM = 13;
    public static final int FIELD_GMT = 14;
    public static final int FIELD_DCS = 15;
    public static final int CHARSET_ASCII = 0;
    public static final int CHARSET_LATIN = 1;
    public static final int CHARSET_UNICODE = 2;
    public static final int CHARSET_UTF_8 = 3;
    public static final int CHARSET_USER_DEFINED_FROM = 128;
    public static final int CHARSET_USER_DEFINED_TO = 999;
    public static final int MAX_RESOLUTION = 99990;
    public static final int MAX_RESOLUTION_V4 = 999990;
    public static final int MIN_SCANNING_RESOLUTION = 19690;
    public static final int MIN_NATIVE_SCANNING_RESOLUTION = 19490;
    public static final int MIN_LOW_TRANSMITTING_RESOLUTION = 9740;
    public static final int MAX_LOW_TRANSMITTING_RESOLUTION = 10340;
    public static final int MIN_HIGH_TRANSMITTING_RESOLUTION = 19490;
    public static final int MAX_HIGH_TRANSMITTING_RESOLUTION = 20670;
    public static final int MIN_TRANSACTION_TYPE_LENGTH_V4 = 3;
    public static final int MAX_TRANSACTION_TYPE_LENGTH_V4 = 4;
    public static final int MAX_PRIORITY = 4;
    public static final int MAX_PRIORITY_V3 = 9;
    public static final NNativeType NATIVE_TYPE;
    
    ANType1Record(final HNObject handle) {
        super(handle, ANType1Record.NATIVE_TYPE);
        this.charsets = new CharsetCollection(this);
    }
    
    public static int[] getStandardCharsetIndexes(final NVersion version) {
        final int[] value = new int[NResult.check(ANType1Record.LIBRARY.ANType1RecordGetStandardCharsetIndexes(version.getValue(), null, 0))];
        NResult.check(ANType1Record.LIBRARY.ANType1RecordGetStandardCharsetIndexes(version.getValue(), value, value.length));
        return value;
    }
    
    public static boolean isCharsetKnown(final NVersion version, final int charsetIndex) {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(ANType1Record.LIBRARY.ANType1RecordIsCharsetKnown(version.getValue(), charsetIndex, rValue));
        return rValue.getValue();
    }
    
    public static boolean isCharsetStandard(final NVersion version, final int charsetIndex) {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(ANType1Record.LIBRARY.ANType1RecordIsCharsetStandard(version.getValue(), charsetIndex, rValue));
        return rValue.getValue();
    }
    
    public static boolean isCharsetUserDefined(final NVersion version, final int charsetIndex) {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(ANType1Record.LIBRARY.ANType1RecordIsCharsetUserDefined(version.getValue(), charsetIndex, rValue));
        return rValue.getValue();
    }
    
    public static String getStandardCharsetName(final NVersion version, final int charsetIndex) {
        final int pLen = ANType1Record.LIBRARY.ANType1RecordGetStandardCharsetNameEx(version.getValue(), charsetIndex, null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANType1Record.LIBRARY.ANType1RecordGetStandardCharsetNameEx(version.getValue(), charsetIndex, pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public static String getStandardCharsetDescription(final NVersion version, final int charsetIndex) {
        final int pLen = ANType1Record.LIBRARY.ANType1RecordGetStandardCharsetDescriptionEx(version.getValue(), charsetIndex, null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANType1Record.LIBRARY.ANType1RecordGetStandardCharsetDescriptionEx(version.getValue(), charsetIndex, pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public static int getStandardCharsetIndexByName(final NVersion version, final String name) {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType1Record.LIBRARY.ANType1RecordGetStandardCharsetIndexByName(version.getValue(), new WString(name), rValue));
        return rValue.getValue();
    }
    
    public void setDomain(final String name, final String version) {
        NResult.check(ANType1Record.LIBRARY.ANType1RecordSetDomain(this.getHandle(), new WString(name), new WString(version)));
        this.refresh();
    }
    
    public String getTransactionType() {
        final int pLen = ANType1Record.LIBRARY.ANType1RecordGetTransactionTypeEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANType1Record.LIBRARY.ANType1RecordGetTransactionTypeEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public void setTransactionType(final String value) {
        NResult.check(ANType1Record.LIBRARY.ANType1RecordSetTransactionType(this.getHandle(), new WString(value)));
    }
    
    public Date getDate() {
        final LongByReference rValue = new LongByReference();
        NResult.check(ANType1Record.LIBRARY.ANType1RecordGetDate(this.getHandle(), rValue));
        return NCore.toDate(rValue.getValue());
    }
    
    public void setDate(final Date value) {
        if (value == null) {
            throw new NullPointerException();
        }
        NResult.check(ANType1Record.LIBRARY.ANType1RecordSetDate(this.getHandle(), NCore.toRawDate(value)));
    }
    
    public int getPriority() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType1Record.LIBRARY.ANType1RecordGetPriority(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public void setPriority(final int value) {
        NResult.check(ANType1Record.LIBRARY.ANType1RecordSetPriority(this.getHandle(), value));
    }
    
    public String getDestinationAgency() {
        final int pLen = ANType1Record.LIBRARY.ANType1RecordGetDestinationAgencyEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANType1Record.LIBRARY.ANType1RecordGetDestinationAgencyEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public void setDestinationAgency(final String value) {
        NResult.check(ANType1Record.LIBRARY.ANType1RecordSetDestinationAgency(this.getHandle(), new WString(value)));
    }
    
    public String getOriginatingAgency() {
        final int pLen = ANType1Record.LIBRARY.ANType1RecordGetOriginatingAgencyEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANType1Record.LIBRARY.ANType1RecordGetOriginatingAgencyEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public void setOriginatingAgency(final String value) {
        NResult.check(ANType1Record.LIBRARY.ANType1RecordSetOriginatingAgency(this.getHandle(), new WString(value)));
    }
    
    public String getTransactionControl() {
        final int pLen = ANType1Record.LIBRARY.ANType1RecordGetTransactionControlEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANType1Record.LIBRARY.ANType1RecordGetTransactionControlEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public void setTransactionControl(final String value) {
        NResult.check(ANType1Record.LIBRARY.ANType1RecordSetTransactionControl(this.getHandle(), new WString(value)));
    }
    
    public String getTransactionControlReference() {
        final int pLen = ANType1Record.LIBRARY.ANType1RecordGetTransactionControlReferenceEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANType1Record.LIBRARY.ANType1RecordGetTransactionControlReferenceEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public void setTransactionControlReference(final String value) {
        NResult.check(ANType1Record.LIBRARY.ANType1RecordSetTransactionControlReference(this.getHandle(), new WString(value)));
    }
    
    public int getNativeScanningResolution() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType1Record.LIBRARY.ANType1RecordGetNativeScanningResolution(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public int getNominalTransmittingResolution() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType1Record.LIBRARY.ANType1RecordGetNominalTransmittingResolution(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public String getDomainName() {
        final int pLen = ANType1Record.LIBRARY.ANType1RecordGetDomainNameEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANType1Record.LIBRARY.ANType1RecordGetDomainNameEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public String getDomainVersion() {
        final int pLen = ANType1Record.LIBRARY.ANType1RecordGetDomainVersionEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANType1Record.LIBRARY.ANType1RecordGetDomainVersionEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public Date getGmt() {
        final LongByReference rValue = new LongByReference();
        NResult.check(ANType1Record.LIBRARY.ANType1RecordGetGmt(this.getHandle(), rValue));
        return (rValue.getValue() == -1L) ? null : NCore.toUtcDate(rValue.getValue());
    }
    
    public void setGmt(final Date value) {
        NResult.check(ANType1Record.LIBRARY.ANType1RecordSetGmt(this.getHandle(), (value == null) ? -1L : NCore.toRawUtcDate(value)));
        this.refresh();
    }
    
    public CharsetCollection getCharsets() {
        this.check();
        return this.charsets;
    }
    
    static {
        LIBRARY = (ANType1RecordLibrary)Native.loadLibrary("NBiometricStandards", ANType1RecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANType1Record.LIBRARY.ANType1RecordTypeOf());
    }
    
    public final class CharsetCollection extends ANRecordSimpleCollection<ANCharset>
    {
        CharsetCollection(final ANType1Record owner) {
            super(owner, 15, false, false, false);
        }
        
        protected void addNative(final ANCharset value) {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType1Record.LIBRARY.ANType1RecordAddCharset(this.getOwner().getHandle(), value.getIndex(), new WString(value.getName()), new WString(value.getVersion()), rValue));
        }
        
        protected int addWithIndexNative(final ANCharset value) {
            throw new UnsupportedOperationException();
        }
        
        protected ANCharset[] getAllNative() {
            throw new UnsupportedOperationException();
        }
        
        protected ANCharset getNative(final int index) {
            String name = null;
            String version = null;
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType1Record.LIBRARY.ANType1RecordGetCharsetIndex(this.getOwner().getHandle(), index, rValue));
            final int charsetIndex = rValue.getValue();
            final int nLen = ANType1Record.LIBRARY.ANType1RecordGetCharsetNameEx(this.getOwner().getHandle(), index, null, 0);
            if (NResult.isFailed(nLen)) {
                return null;
            }
            Pointer pValue = NCore.allocString(nLen);
            try {
                if (NResult.isFailed(ANType1Record.LIBRARY.ANType1RecordGetCharsetNameEx(this.getOwner().getHandle(), index, pValue, nLen + 1))) {
                    return null;
                }
                name = pValue.getString(0L, true);
            }
            finally {
                NCore.free(pValue);
            }
            final int vLen = ANType1Record.LIBRARY.ANType1RecordGetCharsetVersionEx(this.getOwner().getHandle(), index, null, 0);
            if (NResult.isFailed(vLen)) {
                return null;
            }
            pValue = NCore.allocString(vLen);
            try {
                if (NResult.isFailed(ANType1Record.LIBRARY.ANType1RecordGetCharsetVersionEx(this.getOwner().getHandle(), index, pValue, vLen + 1))) {
                    return null;
                }
                version = pValue.getString(0L, true);
            }
            finally {
                NCore.free(pValue);
            }
            return new ANCharset(charsetIndex, name, version);
        }
        
        protected void addNative(final int index, final ANCharset value) {
            throw new UnsupportedOperationException();
        }
        
        protected void setNative(final int index, final ANCharset value) {
            throw new UnsupportedOperationException();
        }
        
        protected void clearNative() {
            NResult.check(ANType1Record.LIBRARY.ANType1RecordClearCharsets(this.getOwner().getHandle()));
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType1Record.LIBRARY.ANType1RecordGetCharsetCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANType1Record.LIBRARY.ANType1RecordRemoveCharset(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final ANCharset value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
        
        public boolean add(final int charsetIndex, final String name, final String version) {
            return this.add(new ANCharset(charsetIndex, name, version));
        }
        
        public boolean contains(final int charsetIndex) {
            final BooleanByReference rValue = new BooleanByReference();
            NResult.check(ANType1Record.LIBRARY.ANType1RecordContainsCharset(this.getOwner().getHandle(), charsetIndex, rValue));
            return rValue.getValue();
        }
        
        public int getIndex(final int index) {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType1Record.LIBRARY.ANType1RecordGetCharsetIndex(this.getOwner().getHandle(), index, rValue));
            return rValue.getValue();
        }
        
        public String getName(final int index) {
            final int pLen = ANType1Record.LIBRARY.ANType1RecordGetCharsetNameEx(this.getOwner().getHandle(), index, null, 0);
            if (NResult.isFailed(pLen)) {
                return null;
            }
            final Pointer pValue = NCore.allocString(pLen);
            try {
                if (NResult.isFailed(ANType1Record.LIBRARY.ANType1RecordGetCharsetNameEx(this.getOwner().getHandle(), index, pValue, pLen + 1))) {
                    return null;
                }
                return pValue.getString(0L, true);
            }
            finally {
                NCore.free(pValue);
            }
        }
        
        public String getVersion(final int index) {
            final int pLen = ANType1Record.LIBRARY.ANType1RecordGetCharsetVersionEx(this.getOwner().getHandle(), index, null, 0);
            if (NResult.isFailed(pLen)) {
                return null;
            }
            final Pointer pValue = NCore.allocString(pLen);
            try {
                if (NResult.isFailed(ANType1Record.LIBRARY.ANType1RecordGetCharsetVersionEx(this.getOwner().getHandle(), index, pValue, pLen + 1))) {
                    return null;
                }
                return pValue.getString(0L, true);
            }
            finally {
                NCore.free(pValue);
            }
        }
    }
    
    interface ANType1RecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANType1RecordTypeOf();
        
        int ANType1RecordGetStandardCharsetIndexes(final short p0, final int[] p1, final int p2);
        
        int ANType1RecordIsCharsetKnown(final short p0, final int p1, final BooleanByReference p2);
        
        int ANType1RecordIsCharsetStandard(final short p0, final int p1, final BooleanByReference p2);
        
        int ANType1RecordIsCharsetUserDefined(final short p0, final int p1, final BooleanByReference p2);
        
        int ANType1RecordGetStandardCharsetNameEx(final short p0, final int p1, final Pointer p2, final int p3);
        
        int ANType1RecordGetStandardCharsetDescriptionEx(final short p0, final int p1, final Pointer p2, final int p3);
        
        int ANType1RecordGetStandardCharsetIndexByName(final short p0, final WString p1, final IntByReference p2);
        
        int ANType1RecordGetCharsetCount(final HNObject p0, final IntByReference p1);
        
        int ANType1RecordGetCharsetIndex(final HNObject p0, final int p1, final IntByReference p2);
        
        int ANType1RecordGetCharsetNameEx(final HNObject p0, final int p1, final Pointer p2, final int p3);
        
        int ANType1RecordGetCharsetVersionEx(final HNObject p0, final int p1, final Pointer p2, final int p3);
        
        int ANType1RecordAddCharset(final HNObject p0, final int p1, final WString p2, final WString p3, final IntByReference p4);
        
        int ANType1RecordRemoveCharset(final HNObject p0, final int p1);
        
        int ANType1RecordClearCharsets(final HNObject p0);
        
        int ANType1RecordContainsCharset(final HNObject p0, final int p1, final BooleanByReference p2);
        
        int ANType1RecordGetTransactionTypeEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANType1RecordSetTransactionType(final HNObject p0, final WString p1);
        
        int ANType1RecordGetDate(final HNObject p0, final LongByReference p1);
        
        int ANType1RecordSetDate(final HNObject p0, final long p1);
        
        int ANType1RecordGetPriority(final HNObject p0, final IntByReference p1);
        
        int ANType1RecordSetPriority(final HNObject p0, final int p1);
        
        int ANType1RecordGetDestinationAgencyEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANType1RecordSetDestinationAgency(final HNObject p0, final WString p1);
        
        int ANType1RecordGetOriginatingAgencyEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANType1RecordSetOriginatingAgency(final HNObject p0, final WString p1);
        
        int ANType1RecordGetTransactionControlEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANType1RecordSetTransactionControl(final HNObject p0, final WString p1);
        
        int ANType1RecordGetTransactionControlReferenceEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANType1RecordSetTransactionControlReference(final HNObject p0, final WString p1);
        
        int ANType1RecordGetNativeScanningResolution(final HNObject p0, final IntByReference p1);
        
        int ANType1RecordGetNominalTransmittingResolution(final HNObject p0, final IntByReference p1);
        
        int ANType1RecordGetDomainNameEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANType1RecordGetDomainVersionEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANType1RecordSetDomain(final HNObject p0, final WString p1, final WString p2);
        
        int ANType1RecordGetGmt(final HNObject p0, final LongByReference p1);
        
        int ANType1RecordSetGmt(final HNObject p0, final long p1);
    }
}
