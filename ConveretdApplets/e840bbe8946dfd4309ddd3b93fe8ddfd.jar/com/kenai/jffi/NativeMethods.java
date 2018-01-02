// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.WeakHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class NativeMethods
{
    private static final Map<Class, NativeMethods> registeredMethods;
    private final ResourceHolder memory;
    
    private NativeMethods(final ResourceHolder memory) {
        this.memory = memory;
    }
    
    public static final synchronized void register(final Class clazz, final List<NativeMethod> methods) {
        int stringSize = 0;
        for (final NativeMethod m : methods) {
            stringSize += m.name.getBytes().length + 1;
            stringSize += m.signature.getBytes().length + 1;
        }
        final int ptrSize = Platform.getPlatform().addressSize() / 8;
        final MemoryIO mm = MemoryIO.getInstance();
        final int structSize = methods.size() * 3 * ptrSize;
        final long memory = mm.allocateMemory(structSize + stringSize, true);
        if (memory == 0L) {
            throw new OutOfMemoryError("could not allocate native memory");
        }
        final NativeMethods nm = new NativeMethods(new ResourceHolder(mm, memory));
        int off = 0;
        int stringOff = structSize;
        for (final NativeMethod i : methods) {
            final byte[] name = i.name.getBytes();
            final long nameAddress = memory + stringOff;
            stringOff += name.length + 1;
            mm.putZeroTerminatedByteArray(nameAddress, name, 0, name.length);
            final byte[] sig = i.signature.getBytes();
            final long sigAddress = memory + stringOff;
            stringOff += sig.length + 1;
            mm.putZeroTerminatedByteArray(sigAddress, sig, 0, sig.length);
            mm.putAddress(memory + off, nameAddress);
            off += ptrSize;
            mm.putAddress(memory + off, sigAddress);
            off += ptrSize;
            mm.putAddress(memory + off, i.function);
            off += ptrSize;
        }
        if (Foreign.getInstance().registerNatives(clazz, memory, methods.size()) != 0) {
            throw new RuntimeException("failed to register native methods");
        }
        NativeMethods.registeredMethods.put(clazz, nm);
    }
    
    public static final synchronized void unregister(final Class clazz) {
        if (!NativeMethods.registeredMethods.containsKey(clazz)) {
            throw new IllegalArgumentException("methods were not registered on class via NativeMethods.register");
        }
        if (Foreign.getInstance().unregisterNatives(clazz) != 0) {
            throw new RuntimeException("failed to unregister native methods");
        }
        NativeMethods.registeredMethods.remove(clazz);
    }
    
    static {
        registeredMethods = new WeakHashMap<Class, NativeMethods>();
    }
    
    private static final class ResourceHolder
    {
        private final MemoryIO mm;
        private final long memory;
        
        public ResourceHolder(final MemoryIO mm, final long memory) {
            this.mm = mm;
            this.memory = memory;
        }
        
        protected void finalize() throws Throwable {
            try {
                this.mm.freeMemory(this.memory);
            }
            catch (Throwable t) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Exception when freeing native method struct array: %s", t.getLocalizedMessage());
            }
            finally {
                super.finalize();
            }
        }
    }
}
