// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import org.jruby.ext.ffi.io.FileDescriptorIO;
import org.jruby.RubyModule;
import org.jruby.Ruby;

public abstract class Factory
{
    public static final Factory getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    public void init(final Ruby runtime, final RubyModule ffi) {
        synchronized (ffi) {
            if (ffi.fastGetClass("Type") == null) {
                Type.createTypeClass(runtime, ffi);
            }
            DataConverter.createDataConverterModule(runtime, ffi);
            if (ffi.fastGetClass("AbstractMemory") == null) {
                AbstractMemory.createAbstractMemoryClass(runtime, ffi);
            }
            if (ffi.fastGetClass("Buffer") == null) {
                Buffer.createBufferClass(runtime, ffi);
            }
            if (ffi.fastGetClass("Pointer") == null) {
                Pointer.createPointerClass(runtime, ffi);
            }
            if (ffi.fastGetClass("AutoPointer") == null) {
                AutoPointer.createAutoPointerClass(runtime, ffi);
            }
            if (ffi.fastGetClass("MemoryPointer") == null) {
                MemoryPointer.createMemoryPointerClass(runtime, ffi);
            }
            if (ffi.fastGetClass("Struct") == null) {
                Struct.createStructClass(runtime, ffi);
            }
            if (ffi.fastGetClass("StructLayout") == null) {
                StructLayout.createStructLayoutClass(runtime, ffi);
            }
            if (ffi.fastGetClass("StructByValue") == null) {
                StructByValue.createStructByValueClass(runtime, ffi);
            }
            if (ffi.fastGetClass("AbstractInvoker") == null) {
                AbstractInvoker.createAbstractInvokerClass(runtime, ffi);
            }
            if (ffi.fastGetClass("CallbackInfo") == null) {
                CallbackInfo.createCallbackInfoClass(runtime, ffi);
            }
            if (ffi.fastGetClass("Enum") == null) {
                Enum.createEnumClass(runtime, ffi);
            }
            if (ffi.fastGetClass("Type").fastGetClass("Mapped") == null) {
                MappedType.createConverterTypeClass(runtime, ffi);
            }
            if (ffi.fastGetClass("FileDescriptorIO") == null) {
                FileDescriptorIO.createFileDescriptorIOClass(runtime, ffi);
            }
            Platform.createPlatformModule(runtime, ffi);
            IOModule.createIOModule(runtime, ffi);
            StructByReference.createStructByReferenceClass(runtime, ffi);
        }
    }
    
    public abstract AllocatedDirectMemoryIO allocateDirectMemory(final Ruby p0, final int p1, final boolean p2);
    
    public abstract AllocatedDirectMemoryIO allocateDirectMemory(final Ruby p0, final int p1, final int p2, final boolean p3);
    
    public abstract DirectMemoryIO wrapDirectMemory(final Ruby p0, final long p1);
    
    public abstract CallbackManager getCallbackManager();
    
    public abstract AbstractInvoker newFunction(final Ruby p0, final Pointer p1, final CallbackInfo p2);
    
    public abstract int sizeOf(final NativeType p0);
    
    public abstract int alignmentOf(final NativeType p0);
    
    private static final class SingletonHolder
    {
        private static final Factory INSTANCE;
        
        private static final Factory getInstance() {
            final String providerName = System.getProperty("ffi.factory");
            Factory factory = null;
            final List<String> providerNames = new ArrayList<String>();
            final List<Throwable> errors = new ArrayList<Throwable>();
            if (providerName != null) {
                providerNames.add(providerName);
            }
            final String prefix = Factory.class.getPackage().getName();
            providerNames.add(prefix + ".jffi.Factory");
            for (final String className : providerNames) {
                try {
                    factory = (Factory)Class.forName(className, true, Ruby.getClassLoader()).newInstance();
                }
                catch (Throwable ex) {
                    errors.add(ex);
                    continue;
                }
                break;
            }
            if (factory == null) {
                final StringBuilder sb = new StringBuilder();
                for (final Throwable t : errors) {
                    sb.append(t.getLocalizedMessage()).append('\n');
                }
                factory = new NoImplFactory(sb.toString());
            }
            return factory;
        }
        
        static {
            INSTANCE = getInstance();
        }
    }
}
