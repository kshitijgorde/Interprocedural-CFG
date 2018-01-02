// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.licensing;

import com.sun.jna.Structure;

public final class NLicenseInfo
{
    private NLicenseInfoData data;
    
    NLicenseInfo(final NLicenseInfoData data) {
        this.data = data;
    }
    
    public NLicenseInfo() {
        this(new NLicenseInfoData());
    }
    
    public boolean isObtained() {
        return this.data.isObtained;
    }
    
    public int getDistributorId() {
        return this.data.distributorId;
    }
    
    public int getSerialNumber() {
        return this.data.serialNumber;
    }
    
    NLicenseInfoData getData() {
        return this.data;
    }
    
    protected static class NLicenseInfoData extends Structure
    {
        public boolean isObtained;
        public int distributorId;
        public int serialNumber;
        
        public NLicenseInfoData() {
        }
        
        NLicenseInfoData(final boolean isObtained, final int distributorId, final int serialNumber) {
            this.isObtained = isObtained;
            this.distributorId = distributorId;
            this.serialNumber = serialNumber;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected NLicenseInfoData newInstance() {
            return new NLicenseInfoData();
        }
        
        static class ByReference extends NLicenseInfoData implements Structure.ByReference
        {
        }
        
        static class ByValue extends NLicenseInfoData implements Structure.ByValue
        {
        }
    }
}
