// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jaffl.Type;
import com.kenai.jaffl.provider.NativeType;
import com.kenai.jffi.LastError;
import com.kenai.jaffl.LibraryOption;
import java.util.Map;
import com.kenai.jaffl.provider.MemoryManager;
import com.kenai.jaffl.FFIProvider;

public class Provider extends FFIProvider
{
    private final MemoryManager memoryManager;
    
    public Provider() {
        this.memoryManager = new com.kenai.jaffl.provider.jffi.MemoryManager();
    }
    
    public MemoryManager getMemoryManager() {
        return this.memoryManager;
    }
    
    public <T> T loadLibrary(final String libraryName, final Class<T> interfaceClass, final Map<LibraryOption, ?> libraryOptions) {
        return this.loadLibrary(new Library(libraryName), interfaceClass, libraryOptions);
    }
    
    public <T> T loadLibrary(final Class<T> interfaceClass, final Map<LibraryOption, ?> libraryOptions, final String... libraryNames) {
        return this.loadLibrary(new Library(libraryNames), interfaceClass, libraryOptions);
    }
    
    private <T> T loadLibrary(final Library library, final Class<T> interfaceClass, final Map<LibraryOption, ?> libraryOptions) {
        try {
            if (AsmLibraryLoader.getInstance().isInterfaceSupported(interfaceClass, libraryOptions)) {
                return AsmLibraryLoader.getInstance().loadLibrary(library, interfaceClass, libraryOptions);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        return ProxyLibraryLoader.getInstance().loadLibrary(library, interfaceClass, libraryOptions);
    }
    
    public int getLastError() {
        return LastError.getInstance().get();
    }
    
    public void setLastError(final int error) {
        LastError.getInstance().set(error);
    }
    
    public Type getType(final NativeType type) {
        switch (type) {
            case VOID: {
                return new TypeDelegate(com.kenai.jffi.Type.VOID);
            }
            case SCHAR: {
                return new TypeDelegate(com.kenai.jffi.Type.SCHAR);
            }
            case UCHAR: {
                return new TypeDelegate(com.kenai.jffi.Type.UCHAR);
            }
            case SSHORT: {
                return new TypeDelegate(com.kenai.jffi.Type.SSHORT);
            }
            case USHORT: {
                return new TypeDelegate(com.kenai.jffi.Type.USHORT);
            }
            case SINT: {
                return new TypeDelegate(com.kenai.jffi.Type.SINT);
            }
            case UINT: {
                return new TypeDelegate(com.kenai.jffi.Type.UINT);
            }
            case SLONG: {
                return new TypeDelegate(com.kenai.jffi.Type.SLONG);
            }
            case ULONG: {
                return new TypeDelegate(com.kenai.jffi.Type.ULONG);
            }
            case SLONGLONG: {
                return new TypeDelegate(com.kenai.jffi.Type.SINT64);
            }
            case ULONGLONG: {
                return new TypeDelegate(com.kenai.jffi.Type.UINT64);
            }
            case FLOAT: {
                return new TypeDelegate(com.kenai.jffi.Type.FLOAT);
            }
            case DOUBLE: {
                return new TypeDelegate(com.kenai.jffi.Type.DOUBLE);
            }
            case ADDRESS: {
                return new TypeDelegate(com.kenai.jffi.Type.POINTER);
            }
            default: {
                return new BadType(type);
            }
        }
    }
    
    private static final class TypeDelegate implements Type
    {
        private final com.kenai.jffi.Type type;
        
        public TypeDelegate(final com.kenai.jffi.Type type) {
            this.type = type;
        }
        
        public int alignment() {
            return this.type.alignment();
        }
        
        public int size() {
            return this.type.size();
        }
    }
    
    private static final class BadType implements Type
    {
        private final NativeType type;
        
        public BadType(final NativeType type) {
            this.type = type;
        }
        
        public int alignment() {
            throw new RuntimeException("invalid type: " + this.type);
        }
        
        public int size() {
            throw new RuntimeException("invalid type: " + this.type);
        }
    }
}
