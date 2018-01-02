// 
// Decompiled by Procyon v0.5.30
// 

package platform;

import logging.LogHolder;
import logging.LogType;
import java.lang.reflect.Method;
import java.util.Hashtable;

public final class VMPerfDataFile
{
    private Hashtable m_tblEntries;
    private Object m_buff;
    private Object m_perf;
    private boolean m_bUsable;
    private int m_nextEntry;
    private int m_numEntries;
    private static final Integer PERFDATA_MAGIC_POSITION;
    private static final Integer PERFDATA_BYTEORDER_POSITION;
    private static final Integer PERFDATA_ACCESSIBLE_POSITION;
    private static final Integer PERFDATA_ENTRYOFFSET_POSITION;
    private static final Integer PERFDATA_NUMENTRIES_POSITION;
    private static final int PERFDATA_MAGIC = -889274176;
    private static final int PERFDATA_SYNC_TIMEOUT = 5000;
    private static Class m_javaNioByteBufferClass;
    private static Class m_javaNioByteOrderClass;
    private static Class m_sunMiscPerfClass;
    private static Method m_byteBufferPositionMethod;
    private static Method m_byteBufferGetMethod;
    private static Method m_byteBufferGetIntMethod;
    private int m_vmId;
    static /* synthetic */ Class class$java$lang$String;
    
    public VMPerfDataFile(final int vmId) {
        this.m_bUsable = false;
        this.m_vmId = vmId;
        try {
            VMPerfDataFile.m_javaNioByteBufferClass = Class.forName("java.nio.ByteBuffer");
            VMPerfDataFile.m_javaNioByteOrderClass = Class.forName("java.nio.ByteOrder");
            VMPerfDataFile.m_sunMiscPerfClass = Class.forName("sun.misc.Perf");
            VMPerfDataFile.m_byteBufferPositionMethod = VMPerfDataFile.m_javaNioByteBufferClass.getMethod("position", Integer.TYPE);
            VMPerfDataFile.m_byteBufferGetMethod = VMPerfDataFile.m_javaNioByteBufferClass.getMethod("get", (Class[])null);
            VMPerfDataFile.m_byteBufferGetIntMethod = VMPerfDataFile.m_javaNioByteBufferClass.getMethod("getInt", (Class[])null);
            this.m_perf = Class.forName("java.security.AccessController").getMethod("doPrivileged", Class.forName("java.security.PrivilegedAction")).invoke(null, Class.forName("sun.misc.Perf$GetPerfAction").newInstance());
            this.m_buff = VMPerfDataFile.m_sunMiscPerfClass.getMethod("attach", Integer.TYPE, (VMPerfDataFile.class$java$lang$String == null) ? (VMPerfDataFile.class$java$lang$String = class$("java.lang.String")) : VMPerfDataFile.class$java$lang$String).invoke(this.m_perf, new Integer(vmId), "r");
            if (this.m_buff == null) {
                return;
            }
            if (this.getMagic() != -889274176) {
                return;
            }
            VMPerfDataFile.m_javaNioByteBufferClass.getMethod("order", VMPerfDataFile.m_javaNioByteOrderClass).invoke(this.m_buff, this.getByteOrder());
            this.m_bUsable = this.buildEntries();
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.MISC, "Java VM < 1.4 found, can't use multiple-instances feature.");
        }
    }
    
    private synchronized boolean buildEntries() throws Exception {
        if (this.m_buff == null) {
            return false;
        }
        final long n = System.currentTimeMillis() + 5000L;
        while (!this.isAccessible()) {
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex) {}
            if (System.currentTimeMillis() > n) {
                return false;
            }
        }
        VMPerfDataFile.m_byteBufferPositionMethod.invoke(this.m_buff, VMPerfDataFile.PERFDATA_ENTRYOFFSET_POSITION);
        this.m_nextEntry = (int)VMPerfDataFile.m_byteBufferGetIntMethod.invoke(this.m_buff, (Object[])null);
        VMPerfDataFile.m_byteBufferPositionMethod.invoke(this.m_buff, VMPerfDataFile.PERFDATA_NUMENTRIES_POSITION);
        this.m_numEntries = (int)VMPerfDataFile.m_byteBufferGetIntMethod.invoke(this.m_buff, (Object[])null);
        this.m_tblEntries = new Hashtable();
        while (this.buildNextEntry()) {}
        return true;
    }
    
    private synchronized boolean buildNextEntry() throws Exception {
        if (this.m_buff == null) {
            return false;
        }
        if (this.m_nextEntry % 4 != 0) {
            return false;
        }
        if (this.m_nextEntry < 0 || this.m_nextEntry >= (int)VMPerfDataFile.m_javaNioByteBufferClass.getMethod("limit", (Class[])null).invoke(this.m_buff, (Object[])null)) {
            return false;
        }
        VMPerfDataFile.m_byteBufferPositionMethod.invoke(this.m_buff, new Integer(this.m_nextEntry));
        final int intValue = (int)VMPerfDataFile.m_byteBufferGetIntMethod.invoke(this.m_buff, (Object[])null);
        if (this.m_nextEntry + intValue > (int)VMPerfDataFile.m_javaNioByteBufferClass.getMethod("limit", (Class[])null).invoke(this.m_buff, (Object[])null) || intValue == 0) {
            return false;
        }
        final int intValue2 = (int)VMPerfDataFile.m_byteBufferGetIntMethod.invoke(this.m_buff, (Object[])null);
        final int intValue3 = (int)VMPerfDataFile.m_byteBufferGetIntMethod.invoke(this.m_buff, (Object[])null);
        final byte byteValue = (byte)VMPerfDataFile.m_byteBufferGetMethod.invoke(this.m_buff, (Object[])null);
        VMPerfDataFile.m_byteBufferGetMethod.invoke(this.m_buff, (Object[])null);
        final byte byteValue2 = (byte)VMPerfDataFile.m_byteBufferGetMethod.invoke(this.m_buff, (Object[])null);
        VMPerfDataFile.m_byteBufferGetMethod.invoke(this.m_buff, (Object[])null);
        final int intValue4 = (int)VMPerfDataFile.m_byteBufferGetIntMethod.invoke(this.m_buff, (Object[])null);
        int n;
        byte[] array;
        int n2;
        byte byteValue3;
        for (n = intValue4 - intValue2, array = new byte[n], n2 = 0; (byteValue3 = (byte)VMPerfDataFile.m_byteBufferGetMethod.invoke(this.m_buff, (Object[])null)) != 0 && n > n2; array[n2++] = byteValue3) {}
        final String s = new String(array, 0, n2);
        VMPerfDataFile.m_byteBufferPositionMethod.invoke(this.m_buff, new Integer(this.m_nextEntry + intValue4));
        if (intValue3 > 0 && byteValue == 66 && byteValue2 == 5) {
            byte[] array2;
            int n3;
            byte byteValue4;
            for (array2 = new byte[intValue3], n3 = 0; (byteValue4 = (byte)VMPerfDataFile.m_byteBufferGetMethod.invoke(this.m_buff, (Object[])null)) != 0 && intValue3 > n3; array2[n3++] = byteValue4) {}
            this.m_tblEntries.put(s, new String(array2, 0, n3));
        }
        this.m_nextEntry += intValue;
        return true;
    }
    
    private boolean isAccessible() throws Exception {
        if (this.m_buff == null) {
            return false;
        }
        VMPerfDataFile.m_byteBufferPositionMethod.invoke(this.m_buff, VMPerfDataFile.PERFDATA_ACCESSIBLE_POSITION);
        return (byte)VMPerfDataFile.m_byteBufferGetMethod.invoke(this.m_buff, (Object[])null) != 0;
    }
    
    private int getMagic() throws Exception {
        if (this.m_buff == null) {
            return 0;
        }
        final Object invoke = VMPerfDataFile.m_javaNioByteBufferClass.getMethod("order", (Class[])null).invoke(this.m_buff, (Object[])null);
        VMPerfDataFile.m_javaNioByteBufferClass.getMethod("order", VMPerfDataFile.m_javaNioByteOrderClass).invoke(this.m_buff, VMPerfDataFile.m_javaNioByteOrderClass.getField("BIG_ENDIAN").get(null));
        VMPerfDataFile.m_byteBufferPositionMethod.invoke(this.m_buff, VMPerfDataFile.PERFDATA_MAGIC_POSITION);
        final int intValue = (int)VMPerfDataFile.m_byteBufferGetIntMethod.invoke(this.m_buff, (Object[])null);
        VMPerfDataFile.m_javaNioByteBufferClass.getMethod("order", VMPerfDataFile.m_javaNioByteOrderClass).invoke(this.m_buff, invoke);
        return intValue;
    }
    
    private Object getByteOrder() throws Exception {
        if (this.m_buff == null) {
            return null;
        }
        VMPerfDataFile.m_byteBufferPositionMethod.invoke(this.m_buff, VMPerfDataFile.PERFDATA_BYTEORDER_POSITION);
        if ((byte)VMPerfDataFile.m_byteBufferGetMethod.invoke(this.m_buff, (Object[])null) == 0) {
            return VMPerfDataFile.m_javaNioByteOrderClass.getField("BIG_ENDIAN").get(null);
        }
        return VMPerfDataFile.m_javaNioByteOrderClass.getField("LITTLE_ENDIAN").get(null);
    }
    
    public String getMainClass() {
        if (!this.m_bUsable) {
            return null;
        }
        final String s = this.m_tblEntries.get("sun.rt.javaCommand");
        if (s == null) {
            return null;
        }
        final int index = s.indexOf(32);
        if (index > 0) {
            return s.substring(0, index);
        }
        return s;
    }
    
    public int getId() {
        return this.m_vmId;
    }
    
    public String toString() {
        return this.getMainClass();
    }
    
    public boolean isUsable() {
        return this.m_bUsable;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        PERFDATA_MAGIC_POSITION = new Integer(0);
        PERFDATA_BYTEORDER_POSITION = new Integer(4);
        PERFDATA_ACCESSIBLE_POSITION = new Integer(7);
        PERFDATA_ENTRYOFFSET_POSITION = new Integer(24);
        PERFDATA_NUMENTRIES_POSITION = new Integer(28);
    }
}
