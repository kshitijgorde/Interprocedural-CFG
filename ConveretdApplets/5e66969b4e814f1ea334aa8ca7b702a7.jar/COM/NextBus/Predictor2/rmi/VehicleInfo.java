// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Predictor2.rmi;

import java.io.Serializable;

public class VehicleInfo implements Serializable
{
    private static final long serialVersionUID = -3378140855575409326L;
    private final String vehicleTag;
    private final int seatedCapacity;
    private final int totalCapacity;
    private final String vehicleType;
    
    public final int a() {
        return this.seatedCapacity;
    }
    
    public int hashCode() {
        return (((31 + this.seatedCapacity) * 31 + this.totalCapacity) * 31 + ((this.vehicleTag == null) ? 0 : this.vehicleTag.hashCode())) * 31 + ((this.vehicleType == null) ? 0 : this.vehicleType.hashCode());
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        final VehicleInfo vehicleInfo = (VehicleInfo)o;
        if (this.seatedCapacity != vehicleInfo.seatedCapacity) {
            return false;
        }
        if (this.totalCapacity != vehicleInfo.totalCapacity) {
            return false;
        }
        if (this.vehicleTag == null) {
            if (vehicleInfo.vehicleTag != null) {
                return false;
            }
        }
        else if (!this.vehicleTag.equals(vehicleInfo.vehicleTag)) {
            return false;
        }
        if (this.vehicleType == null) {
            if (vehicleInfo.vehicleType != null) {
                return false;
            }
        }
        else if (!this.vehicleType.equals(vehicleInfo.vehicleType)) {
            return false;
        }
        return true;
    }
    
    public String toString() {
        return "VehicleInfo [seatedCapacity=" + this.seatedCapacity + ", totalCapacity=" + this.totalCapacity + ", vehicleTag=" + this.vehicleTag + ", vehicleType=" + this.vehicleType + "]";
    }
}
