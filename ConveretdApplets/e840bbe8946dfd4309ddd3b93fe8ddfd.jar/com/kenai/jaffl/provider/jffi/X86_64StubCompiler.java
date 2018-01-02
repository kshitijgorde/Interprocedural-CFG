// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jnr.x86asm.Assembler;
import com.kenai.jnr.x86asm.Asm;
import com.kenai.jffi.Function;
import com.kenai.jffi.CallingConvention;

final class X86_64StubCompiler extends AbstractX86StubCompiler
{
    final boolean canCompile(final Class returnType, final Class[] parameterTypes, final CallingConvention convention) {
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
        return iCount <= 6 && fCount <= 8;
    }
    
    final void compile(final Function function, final String name, final Class returnType, final Class[] parameterTypes, final CallingConvention convention, final boolean saveErrno) {
        int fCount = 0;
        int iCount = 0;
        for (final Class t : parameterTypes) {
            if (t == Byte.TYPE || t == Short.TYPE || t == Integer.TYPE || t == Long.TYPE) {
                ++iCount;
            }
            else {
                if (t != Float.TYPE && t != Double.TYPE) {
                    throw new IllegalArgumentException("invalid parameter type");
                }
                ++fCount;
            }
        }
        final Assembler a = new Assembler(Asm.X86_64);
        if (iCount > 0) {
            a.mov(Asm.rdi, Asm.rdx);
        }
        if (iCount > 1) {
            a.mov(Asm.rsi, Asm.rcx);
        }
        if (iCount > 2) {
            a.mov(Asm.rdx, Asm.r8);
        }
        if (iCount > 3) {
            a.mov(Asm.rcx, Asm.r9);
        }
        if (iCount > 4) {
            a.mov(Asm.r8, Asm.qword_ptr(Asm.rsp, 8L));
        }
        if (iCount > 5) {
            a.mov(Asm.r9, Asm.qword_ptr(Asm.rsp, 16L));
        }
        if (iCount > 6) {
            throw new IllegalArgumentException("integer argument count > 6");
        }
        if (fCount > 8) {
            throw new IllegalArgumentException("float argument count > 8");
        }
        if (saveErrno) {
            final int space = (returnType == Float.TYPE || returnType == Double.TYPE) ? 24 : 8;
            a.sub(Asm.rsp, Asm.imm((long)space));
            a.mov(Asm.rax, Asm.imm(function.getFunctionAddress()));
            a.call(Asm.rax);
            if (returnType == Float.TYPE) {
                a.movss(Asm.dword_ptr(Asm.rsp, 0L), Asm.xmm0);
            }
            else if (returnType == Double.TYPE) {
                a.movsd(Asm.qword_ptr(Asm.rsp, 0L), Asm.xmm0);
            }
            else {
                a.mov(Asm.qword_ptr(Asm.rsp, 0L), Asm.rax);
            }
            a.mov(Asm.rax, Asm.imm(X86_64StubCompiler.errnoFunctionAddress));
            a.call(Asm.rax);
            if (returnType == Float.TYPE) {
                a.movss(Asm.xmm0, Asm.dword_ptr(Asm.rsp, 0L));
            }
            else if (returnType == Double.TYPE) {
                a.movsd(Asm.xmm0, Asm.qword_ptr(Asm.rsp, 0L));
            }
            else {
                a.mov(Asm.rax, Asm.dword_ptr(Asm.rsp, 0L));
            }
            a.add(Asm.rsp, Asm.imm((long)space));
            a.ret();
        }
        else {
            a.mov(Asm.rax, Asm.imm(function.getFunctionAddress()));
            a.jmp(Asm.rax);
        }
        this.stubs.add(new Stub(this, name, CodegenUtils.sig(returnType, parameterTypes), a));
    }
}
