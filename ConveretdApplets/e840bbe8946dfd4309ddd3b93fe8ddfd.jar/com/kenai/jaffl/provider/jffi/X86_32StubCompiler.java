// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jnr.x86asm.Assembler;
import com.kenai.jnr.x86asm.Asm;
import com.kenai.jffi.Function;
import com.kenai.jffi.CallingConvention;

final class X86_32StubCompiler extends AbstractX86StubCompiler
{
    boolean canCompile(final Class returnType, final Class[] parameterTypes, final CallingConvention convention) {
        if (returnType != Byte.TYPE && returnType != Short.TYPE && returnType != Integer.TYPE && returnType != Long.TYPE && returnType != Float.TYPE && returnType != Double.TYPE && returnType != Void.TYPE) {
            return false;
        }
        if (convention != CallingConvention.DEFAULT) {
            return false;
        }
        int fCount = 0;
        int iCount = 0;
        for (final Class t : parameterTypes) {
            if (t == Byte.TYPE || t == Short.TYPE || t == Integer.TYPE || t == Long.TYPE) {
                ++iCount;
            }
            else {
                if (t != Float.TYPE && t != Double.TYPE) {
                    return false;
                }
                ++fCount;
            }
        }
        return true;
    }
    
    void compile(final Function function, final String name, final Class returnType, final Class[] parameterTypes, final CallingConvention convention, final boolean saveErrno) {
        int psize = 0;
        for (final Class t : parameterTypes) {
            if (t == Byte.TYPE || t == Short.TYPE || t == Integer.TYPE || t == Float.TYPE) {
                psize += 4;
            }
            else {
                if (t != Long.TYPE && t != Double.TYPE) {
                    throw new IllegalArgumentException("invalid parameter type" + t);
                }
                psize += 8;
            }
        }
        int rsize = 0;
        if (Double.TYPE == returnType || Float.TYPE == returnType) {
            rsize = 16;
        }
        else if (Long.TYPE == returnType) {
            rsize = 8;
        }
        else if (Byte.TYPE == returnType || Short.TYPE == returnType || Integer.TYPE == returnType) {
            rsize = 4;
        }
        else {
            if (Void.TYPE != returnType) {
                throw new IllegalArgumentException("invalid return type " + returnType);
            }
            rsize = 0;
        }
        final int stackadj = AbstractX86StubCompiler.align(Math.max(psize, rsize) + 4, 16) - 4;
        final Assembler a = new Assembler(Asm.X86_32);
        a.sub(Asm.esp, Asm.imm((long)stackadj));
        for (int i = 0; i < psize; i += 4) {
            a.mov(Asm.eax, Asm.dword_ptr(Asm.esp, (long)(stackadj + 4 + 8 + i)));
            a.mov(Asm.dword_ptr(Asm.esp, (long)i), Asm.eax);
        }
        a.mov(Asm.eax, Asm.imm(function.getFunctionAddress()));
        a.call(Asm.eax);
        if (saveErrno) {
            final int save = 0;
            if (Float.TYPE == returnType) {
                a.fstp(Asm.dword_ptr(Asm.esp, (long)save));
            }
            else if (Double.TYPE == returnType) {
                a.fstp(Asm.qword_ptr(Asm.esp, (long)save));
            }
            else if (Long.TYPE == returnType) {
                a.mov(Asm.dword_ptr(Asm.esp, (long)save), Asm.eax);
                a.mov(Asm.dword_ptr(Asm.esp, (long)(save + 4)), Asm.edx);
            }
            else if (Void.TYPE != returnType) {
                a.mov(Asm.dword_ptr(Asm.esp, (long)save), Asm.eax);
            }
            a.mov(Asm.eax, Asm.imm(X86_32StubCompiler.errnoFunctionAddress));
            a.call(Asm.eax);
            if (Float.TYPE == returnType) {
                a.fld(Asm.dword_ptr(Asm.esp, (long)save));
            }
            else if (Double.TYPE == returnType) {
                a.fld(Asm.qword_ptr(Asm.esp, (long)save));
            }
            else if (Long.TYPE == returnType) {
                a.mov(Asm.eax, Asm.dword_ptr(Asm.esp, (long)save));
                a.mov(Asm.edx, Asm.dword_ptr(Asm.esp, (long)(save + 4)));
            }
            else if (Void.TYPE != returnType) {
                a.mov(Asm.eax, Asm.dword_ptr(Asm.esp, (long)save));
            }
        }
        a.add(Asm.esp, Asm.imm((long)stackadj));
        a.ret();
        this.stubs.add(new Stub(this, name, CodegenUtils.sig(returnType, parameterTypes), a));
    }
}
