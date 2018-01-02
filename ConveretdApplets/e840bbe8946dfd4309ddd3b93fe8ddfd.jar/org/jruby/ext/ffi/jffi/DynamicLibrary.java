// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.ext.ffi.InvalidMemoryIO;
import org.jruby.ext.ffi.Pointer;
import org.jruby.RubyString;
import org.jruby.ext.ffi.DirectMemoryIO;
import org.jruby.ext.ffi.NullMemoryIO;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyNumeric;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import com.kenai.jffi.Library;
import org.jruby.anno.JRubyConstant;
import org.jruby.anno.JRubyClass;
import org.jruby.RubyObject;

@JRubyClass(name = { "FFI::DynamicLibrary" }, parent = "Object")
public class DynamicLibrary extends RubyObject
{
    @JRubyConstant
    public static final int RTLD_LAZY = 1;
    @JRubyConstant
    public static final int RTLD_NOW = 2;
    @JRubyConstant
    public static final int RTLD_LOCAL = 4;
    @JRubyConstant
    public static final int RTLD_GLOBAL = 8;
    private final Library library;
    private final String name;
    
    public static RubyClass createDynamicLibraryClass(final Ruby runtime, final RubyModule module) {
        final RubyClass result = module.defineClassUnder("DynamicLibrary", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        final RubyClass symClass = result.defineClassUnder("Symbol", module.fastGetClass("Pointer"), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        symClass.defineAnnotatedMethods(Symbol.class);
        result.defineAnnotatedMethods(DynamicLibrary.class);
        result.defineAnnotatedConstants(DynamicLibrary.class);
        return result;
    }
    
    private static final int getNativeLibraryFlags(final IRubyObject rbFlags) {
        int f = 0;
        final int flags = RubyNumeric.fix2int(rbFlags);
        f |= (((flags & 0x1) != 0x0) ? 1 : 0);
        f |= (((flags & 0x2) != 0x0) ? 2 : 0);
        f |= (((flags & 0x4) != 0x0) ? 4 : 0);
        f |= (((flags & 0x8) != 0x0) ? 8 : 0);
        return f;
    }
    
    public DynamicLibrary(final Ruby runtime, final RubyClass klass, final String name, final Library library) {
        super(runtime, klass);
        this.name = name;
        this.library = library;
    }
    
    final Library getNativeLibrary() {
        return this.library;
    }
    
    @JRubyMethod(name = { "open" }, meta = true)
    public static final IRubyObject open(final ThreadContext context, final IRubyObject recv, final IRubyObject libraryName, final IRubyObject libraryFlags) {
        final String libName = libraryName.isNil() ? null : libraryName.toString();
        try {
            final Library library = Library.getCachedInstance(libName, getNativeLibraryFlags(libraryFlags));
            if (library == null) {
                throw new UnsatisfiedLinkError(Library.getLastError());
            }
            return new DynamicLibrary(context.getRuntime(), (RubyClass)recv, libName, library);
        }
        catch (UnsatisfiedLinkError ex) {
            throw context.getRuntime().newLoadError(String.format("Could not open library '%s' : %s", (libName != null) ? libName : "current process", ex.getMessage()));
        }
    }
    
    @JRubyMethod(name = { "find_variable", "find_symbol" })
    public IRubyObject findVariable(final ThreadContext context, final IRubyObject symbolName) {
        final String sym = symbolName.toString();
        final long address = this.library.getSymbolAddress(sym);
        if (address == 0L) {
            return context.getRuntime().getNil();
        }
        return new Symbol(context.getRuntime(), this, sym, (DirectMemoryIO)((address != 0L) ? new DataSymbolMemoryIO(context.getRuntime(), this, address) : new NullMemoryIO(context.getRuntime())));
    }
    
    @JRubyMethod(name = { "find_function" })
    public IRubyObject findFunction(final ThreadContext context, final IRubyObject symbolName) {
        final String sym = symbolName.toString();
        final long address = this.library.getSymbolAddress(sym);
        if (address == 0L) {
            return context.getRuntime().getNil();
        }
        return new Symbol(context.getRuntime(), this, sym, new TextSymbolMemoryIO(context.getRuntime(), this, address));
    }
    
    @JRubyMethod(name = { "name" })
    public IRubyObject name(final ThreadContext context) {
        return (this.name != null) ? RubyString.newString(context.getRuntime(), this.name) : context.getRuntime().getNil();
    }
    
    public static final class Symbol extends Pointer
    {
        private final DynamicLibrary library;
        private final String name;
        
        public Symbol(final Ruby runtime, final DynamicLibrary library, final String name, final DirectMemoryIO io) {
            super(runtime, runtime.fastGetModule("FFI").fastGetClass("DynamicLibrary").fastGetClass("Symbol"), io, Long.MAX_VALUE);
            this.library = library;
            this.name = name;
        }
        
        @JRubyMethod(name = { "library" })
        public IRubyObject library(final ThreadContext context) {
            return this.library;
        }
        
        @JRubyMethod(name = { "inspect" })
        public IRubyObject inspect(final ThreadContext context) {
            return RubyString.newString(context.getRuntime(), String.format("#<%s library=%s symbol=%s address=%#x>", this.getMetaClass().getName(), this.library.name, this.name, this.getAddress()));
        }
        
        @JRubyMethod(name = { "to_s" }, optional = 1)
        public IRubyObject to_s(final ThreadContext context, final IRubyObject[] args) {
            return RubyString.newString(context.getRuntime(), this.name);
        }
        
        public final String toString() {
            return this.name;
        }
        
        final String getName() {
            return this.name;
        }
    }
    
    private static final class DataSymbolMemoryIO extends NativeMemoryIO
    {
        private final DynamicLibrary library;
        
        public DataSymbolMemoryIO(final Ruby runtime, final DynamicLibrary library, final long address) {
            super(runtime, address);
            this.library = library;
        }
    }
    
    private static final class TextSymbolMemoryIO extends InvalidMemoryIO implements DirectMemoryIO
    {
        private final DynamicLibrary library;
        private final long address;
        
        public TextSymbolMemoryIO(final Ruby runtime, final DynamicLibrary library, final long address) {
            super(runtime, "Library text region is inaccessible");
            this.library = library;
            this.address = address;
        }
        
        public long getAddress() {
            return this.address;
        }
        
        public boolean isDirect() {
            return true;
        }
        
        public boolean isNull() {
            return false;
        }
    }
}
