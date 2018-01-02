// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jnr.x86asm.CPU;
import com.kenai.jnr.x86asm.Assembler;
import com.kenai.jffi.PageManager;
import com.kenai.jffi.Internals;
import com.kenai.jffi.Function;
import com.kenai.jffi.CallingConvention;
import com.kenai.jaffl.Platform;

abstract class StubCompiler
{
    static final long errnoFunctionAddress;
    static final boolean hasPageManager;
    static final boolean hasAssembler;
    
    public static final StubCompiler newCompiler() {
        if (StubCompiler.errnoFunctionAddress != 0L && StubCompiler.hasPageManager && StubCompiler.hasAssembler) {
            switch (Platform.getPlatform().getCPU()) {
                case I386: {
                    if (Platform.getPlatform().getOS() != Platform.OS.WINDOWS) {
                        return new X86_32StubCompiler();
                    }
                    break;
                }
                case X86_64: {
                    if (Platform.getPlatform().getOS() != Platform.OS.WINDOWS) {
                        return new X86_64StubCompiler();
                    }
                    break;
                }
            }
        }
        return new DummyStubCompiler();
    }
    
    abstract boolean canCompile(final Class p0, final Class[] p1, final CallingConvention p2);
    
    abstract void compile(final Function p0, final String p1, final Class p2, final Class[] p3, final CallingConvention p4, final boolean p5);
    
    abstract void attach(final Class p0);
    
    private static final long getErrnoSaveFunction() {
        try {
            return Internals.getErrnoSaveFunction();
        }
        catch (Throwable t) {
            return 0L;
        }
    }
    
    private static final boolean hasPageManager() {
        try {
            final long page = PageManager.getInstance().allocatePages(1, 3);
            PageManager.getInstance().freePages(page, 1);
            return true;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    private static final boolean hasAssembler() {
        try {
            switch (Platform.getPlatform().getCPU()) {
                case I386: {
                    new Assembler(CPU.X86_32);
                    return true;
                }
                case X86_64: {
                    new Assembler(CPU.X86_64);
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    static {
        errnoFunctionAddress = getErrnoSaveFunction();
        hasPageManager = hasPageManager();
        hasAssembler = hasAssembler();
    }
    
    static final class DummyStubCompiler extends StubCompiler
    {
        boolean canCompile(final Class returnType, final Class[] parameterTypes, final CallingConvention convention) {
            return false;
        }
        
        void compile(final Function function, final String name, final Class returnType, final Class[] parameterTypes, final CallingConvention convention, final boolean saveErrno) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        void attach(final Class clazz) {
        }
    }
}
