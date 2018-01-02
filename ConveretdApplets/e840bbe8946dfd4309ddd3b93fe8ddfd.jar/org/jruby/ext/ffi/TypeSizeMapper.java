// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.RubyHash;
import org.jruby.RubyModule;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

final class TypeSizeMapper
{
    private static final int[] sizes;
    
    private static final int getTypeSize(final IRubyObject type) {
        final int index = NativeType.valueOf(type).ordinal();
        return (index >= 0 && index < TypeSizeMapper.sizes.length) ? TypeSizeMapper.sizes[index] : -1;
    }
    
    private static final int callTypeSize(final ThreadContext context, final RubyModule ffi, final IRubyObject sizeArg) {
        return getTypeSize(ffi.callMethod(context, "type_size", sizeArg));
    }
    
    public static final int getTypeSize(final ThreadContext context, final IRubyObject sizeArg) {
        final RubyModule ffi = context.getRuntime().fastGetModule("FFI");
        final IRubyObject typeDefs = ffi.fastFetchConstant("TypeDefs");
        final IRubyObject type = ((RubyHash)typeDefs).fastARef(sizeArg);
        final int size = (type != null && !type.isNil()) ? getTypeSize(type) : 0;
        return (size > 0) ? size : callTypeSize(context, ffi, sizeArg);
    }
    
    static {
        final NativeType[] types = NativeType.values();
        final int[] sz = new int[types.length];
        for (int i = 0; i < sz.length; ++i) {
            switch (types[i]) {
                case CHAR:
                case UCHAR: {
                    sz[types[i].ordinal()] = 1;
                    break;
                }
                case SHORT:
                case USHORT: {
                    sz[types[i].ordinal()] = 2;
                    break;
                }
                case INT:
                case UINT:
                case FLOAT: {
                    sz[types[i].ordinal()] = 4;
                    break;
                }
                case LONG_LONG:
                case ULONG_LONG:
                case DOUBLE: {
                    sz[types[i].ordinal()] = 8;
                    break;
                }
                case LONG:
                case ULONG: {
                    sz[types[i].ordinal()] = Platform.getPlatform().longSize() >> 3;
                    break;
                }
                case POINTER: {
                    sz[types[i].ordinal()] = Platform.getPlatform().addressSize() >> 3;
                    break;
                }
                default: {
                    sz[types[i].ordinal()] = -1;
                    break;
                }
            }
        }
        sizes = sz;
    }
}
