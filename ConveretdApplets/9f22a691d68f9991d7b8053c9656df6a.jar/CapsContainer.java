import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

class CapsContainer
{
    protected Hashtable infoMap;
    protected Vector orderedList;
    
    public CapsContainer() {
        this.infoMap = new Hashtable(64, 0.25f);
        this.orderedList = new Vector(32, 8);
    }
    
    public void add(final CapabilityInfo capabilityInfo) {
        this.infoMap.put(new Integer(capabilityInfo.getCode()), capabilityInfo);
    }
    
    public void add(final int n, final String s, final String s2, final String s3) {
        this.infoMap.put(new Integer(n), new CapabilityInfo(n, s, s2, s3));
    }
    
    public boolean isKnown(final int n) {
        return this.infoMap.containsKey(new Integer(n));
    }
    
    public CapabilityInfo getInfo(final int n) {
        return this.infoMap.get(new Integer(n));
    }
    
    public String getDescription(final int n) {
        final CapabilityInfo capabilityInfo = this.infoMap.get(new Integer(n));
        if (capabilityInfo == null) {
            return null;
        }
        return capabilityInfo.getDescription();
    }
    
    public boolean enable(final CapabilityInfo capabilityInfo) {
        final Integer n = new Integer(capabilityInfo.getCode());
        final CapabilityInfo capabilityInfo2 = this.infoMap.get(n);
        if (capabilityInfo2 == null) {
            return false;
        }
        final boolean enableIfEquals = capabilityInfo2.enableIfEquals(capabilityInfo);
        if (enableIfEquals) {
            this.orderedList.addElement(n);
        }
        return enableIfEquals;
    }
    
    public boolean isEnabled(final int n) {
        final CapabilityInfo capabilityInfo = this.infoMap.get(new Integer(n));
        return capabilityInfo != null && capabilityInfo.isEnabled();
    }
    
    public int numEnabled() {
        return this.orderedList.size();
    }
    
    public int getByOrder(final int n) {
        int intValue;
        try {
            intValue = this.orderedList.elementAt(n);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            intValue = 0;
        }
        return intValue;
    }
}
