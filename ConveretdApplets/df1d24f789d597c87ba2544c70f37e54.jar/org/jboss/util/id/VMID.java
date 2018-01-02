// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.id;

import java.security.AccessController;
import java.net.InetAddress;
import java.security.PrivilegedAction;
import org.jboss.util.Primitives;
import org.jboss.util.HashCode;
import org.jboss.util.platform.PID;

public class VMID implements ID
{
    protected final byte[] address;
    protected final PID pid;
    protected final UID uid;
    protected final int hashCode;
    private static VMID instance;
    public static final byte[] UNKNOWN_HOST;
    
    protected VMID(final byte[] address, final PID pid, final UID uid) {
        this.address = address;
        this.pid = pid;
        this.uid = uid;
        int code = pid.hashCode();
        code ^= uid.hashCode();
        code ^= HashCode.generate(address);
        this.hashCode = code;
    }
    
    protected VMID(final VMID vmid) {
        this.address = vmid.address;
        this.pid = vmid.pid;
        this.uid = vmid.uid;
        this.hashCode = vmid.hashCode;
    }
    
    public final byte[] getAddress() {
        return this.address;
    }
    
    public final PID getProcessID() {
        return this.pid;
    }
    
    public final UID getUID() {
        return this.uid;
    }
    
    public String toString() {
        final StringBuffer buff = new StringBuffer();
        for (int i = 0; i < this.address.length; ++i) {
            final int n = this.address[i] & 0xFF;
            buff.append(Integer.toString(n, 36));
        }
        buff.append("-").append(this.pid.toString(36));
        buff.append("-").append(this.uid);
        return buff.toString();
    }
    
    public final int hashCode() {
        return this.hashCode;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == this.getClass()) {
            final VMID vmid = (VMID)obj;
            return Primitives.equals(vmid.address, this.address) && vmid.pid.equals((Object)this.pid) && vmid.uid.equals(this.uid);
        }
        return false;
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
    
    public static String asString() {
        return getInstance().toString();
    }
    
    public static synchronized VMID getInstance() {
        if (VMID.instance == null) {
            VMID.instance = create();
        }
        return VMID.instance;
    }
    
    private static byte[] getHostAddress() {
        return AccessController.doPrivileged((PrivilegedAction<byte[]>)new PrivilegedAction() {
            public Object run() {
                try {
                    return InetAddress.getLocalHost().getAddress();
                }
                catch (Exception e) {
                    return VMID.UNKNOWN_HOST;
                }
            }
        });
    }
    
    private static VMID create() {
        final byte[] address = getHostAddress();
        return new VMID(address, PID.getInstance(), new UID());
    }
    
    static {
        VMID.instance = null;
        UNKNOWN_HOST = new byte[] { 0, 0, 0, 0 };
    }
}
