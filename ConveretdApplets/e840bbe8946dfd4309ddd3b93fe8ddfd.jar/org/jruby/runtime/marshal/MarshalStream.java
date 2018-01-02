// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.marshal;

import org.jruby.IncludedModuleWrapper;
import org.jruby.util.ByteList;
import org.jcodings.specific.UTF8Encoding;
import org.jcodings.specific.USASCIIEncoding;
import org.jcodings.Encoding;
import java.util.Iterator;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.RubySymbol;
import org.jruby.RubyStruct;
import org.jruby.RubyHash;
import org.jruby.RubyFloat;
import org.jruby.RubyBignum;
import org.jruby.RubyArray;
import org.jruby.RubyModule;
import org.jcodings.specific.ASCIIEncoding;
import org.jruby.runtime.encoding.EncodingCapable;
import org.jruby.RubyRegexp;
import org.jruby.RubyString;
import org.jruby.RubyClass;
import org.jruby.runtime.builtin.Variable;
import java.util.List;
import org.jruby.RubyFixnum;
import org.jruby.RubyBoolean;
import org.jruby.runtime.builtin.IRubyObject;
import java.io.IOException;
import java.io.OutputStream;
import org.jruby.Ruby;
import java.io.FilterOutputStream;

public class MarshalStream extends FilterOutputStream
{
    private final Ruby runtime;
    private final MarshalCache cache;
    private final int depthLimit;
    private boolean tainted;
    private boolean untrusted;
    private int depth;
    private static final char TYPE_IVAR = 'I';
    private static final char TYPE_USRMARSHAL = 'U';
    private static final char TYPE_USERDEF = 'u';
    private static final char TYPE_UCLASS = 'C';
    public static final String SYMBOL_ENCODING_SPECIAL = "E";
    private static final String SYMBOL_ENCODING = "encoding";
    
    public MarshalStream(final Ruby runtime, final OutputStream out, final int depthLimit) throws IOException {
        super(out);
        this.tainted = false;
        this.untrusted = false;
        this.depth = 0;
        this.runtime = runtime;
        this.depthLimit = ((depthLimit >= 0) ? depthLimit : Integer.MAX_VALUE);
        this.cache = new MarshalCache();
        out.write(4);
        out.write(8);
    }
    
    public void dumpObject(final IRubyObject value) throws IOException {
        ++this.depth;
        if (this.depth > this.depthLimit) {
            throw this.runtime.newArgumentError("exceed depth limit");
        }
        this.tainted |= value.isTaint();
        this.untrusted |= value.isUntrusted();
        this.writeAndRegister(value);
        --this.depth;
        if (this.depth == 0) {
            this.out.flush();
        }
    }
    
    public void registerLinkTarget(final IRubyObject newObject) {
        if (shouldBeRegistered(newObject)) {
            this.cache.register(newObject);
        }
    }
    
    public void registerSymbol(final String sym) {
        this.cache.registerSymbol(sym);
    }
    
    static boolean shouldBeRegistered(final IRubyObject value) {
        return !value.isNil() && !(value instanceof RubyBoolean) && (!(value instanceof RubyFixnum) || !isMarshalFixnum((RubyFixnum)value));
    }
    
    private static boolean isMarshalFixnum(final RubyFixnum fixnum) {
        return fixnum.getLongValue() <= 1073741823L && fixnum.getLongValue() >= -1073741824L;
    }
    
    private void writeAndRegisterSymbol(final String sym) throws IOException {
        if (this.cache.isSymbolRegistered(sym)) {
            this.cache.writeSymbolLink(this, sym);
        }
        else {
            this.registerSymbol(sym);
            this.dumpSymbol(sym);
        }
    }
    
    private void writeAndRegister(final IRubyObject value) throws IOException {
        if (this.cache.isRegistered(value)) {
            this.cache.writeLink(this, value);
        }
        else {
            value.getMetaClass().smartDump(this, value);
        }
    }
    
    private List<Variable<Object>> getVariables(final IRubyObject value) throws IOException {
        List<Variable<Object>> variables = null;
        if (value instanceof CoreObjectType) {
            final int nativeTypeIndex = ((CoreObjectType)value).getNativeTypeIndex();
            if (nativeTypeIndex != 14 && nativeTypeIndex != 38) {
                if (this.shouldMarshalEncoding(value) || (!value.isImmediate() && value.hasVariables() && nativeTypeIndex != 13 && nativeTypeIndex != 12)) {
                    variables = value.getVariableList();
                    this.write(73);
                }
                RubyClass type = value.getMetaClass();
                switch (nativeTypeIndex) {
                    case 3:
                    case 4:
                    case 9:
                    case 10: {
                        type = this.dumpExtended(type);
                        break;
                    }
                }
                if (nativeTypeIndex != value.getMetaClass().index && nativeTypeIndex != 15) {
                    this.writeUserClass(value, type);
                }
            }
        }
        return variables;
    }
    
    private boolean shouldMarshalEncoding(final IRubyObject value) {
        return this.runtime.is1_9() && (value instanceof RubyString || value instanceof RubyRegexp) && ((EncodingCapable)value).getEncoding() != ASCIIEncoding.INSTANCE;
    }
    
    public void writeDirectly(final IRubyObject value) throws IOException {
        final List<Variable<Object>> variables = this.getVariables(value);
        this.writeObjectData(value);
        if (variables != null) {
            if (this.runtime.is1_9()) {
                this.dumpVariablesWithEncoding(variables, value);
            }
            else {
                this.dumpVariables(variables);
            }
        }
    }
    
    public static String getPathFromClass(final RubyModule clazz) {
        final String path = clazz.getName();
        if (path.charAt(0) == '#') {
            final String classOrModule = clazz.isClass() ? "class" : "module";
            throw clazz.getRuntime().newTypeError("can't dump anonymous " + classOrModule + " " + path);
        }
        final RubyModule real = clazz.isModule() ? clazz : ((RubyClass)clazz).getRealClass();
        if (clazz.getRuntime().getClassFromPath(path) != real) {
            throw clazz.getRuntime().newTypeError(path + " can't be referred");
        }
        return path;
    }
    
    private void writeObjectData(IRubyObject value) throws IOException {
        if (!(value instanceof CoreObjectType)) {
            this.dumpDefaultObjectHeader(value.getMetaClass());
            value.getMetaClass().getRealClass().marshal(value, this);
            return;
        }
        if (value instanceof DataType) {
            throw value.getRuntime().newTypeError("no marshal_dump is defined for class " + value.getMetaClass().getName());
        }
        final int nativeTypeIndex = ((CoreObjectType)value).getNativeTypeIndex();
        switch (nativeTypeIndex) {
            case 3: {
                this.write(91);
                RubyArray.marshalTo((RubyArray)value, this);
            }
            case 7: {
                this.write(70);
            }
            case 1: {
                final RubyFixnum fixnum = (RubyFixnum)value;
                if (isMarshalFixnum(fixnum)) {
                    this.write(105);
                    this.writeInt((int)fixnum.getLongValue());
                    return;
                }
                value = RubyBignum.newBignum(value.getRuntime(), fixnum.getLongValue());
            }
            case 2: {
                this.write(108);
                RubyBignum.marshalTo((RubyBignum)value, this);
            }
            case 13: {
                if (((RubyClass)value).isSingleton()) {
                    throw this.runtime.newTypeError("singleton class can't be dumped");
                }
                this.write(99);
                RubyClass.marshalTo((RubyClass)value, this);
            }
            case 11: {
                this.write(102);
                RubyFloat.marshalTo((RubyFloat)value, this);
            }
            case 10: {
                final RubyHash hash = (RubyHash)value;
                if (hash.getIfNone().isNil()) {
                    this.write(123);
                }
                else {
                    if (hash.hasDefaultProc()) {
                        throw hash.getRuntime().newTypeError("can't dump hash with default proc");
                    }
                    this.write(125);
                }
                RubyHash.marshalTo(hash, this);
            }
            case 12: {
                this.write(109);
                RubyModule.marshalTo((RubyModule)value, this);
            }
            case 5: {
                this.write(48);
            }
            case 14:
            case 38: {
                this.dumpDefaultObjectHeader(value.getMetaClass());
                value.getMetaClass().getRealClass().marshal(value, this);
            }
            case 9: {
                this.write(47);
                RubyRegexp.marshalTo((RubyRegexp)value, this);
            }
            case 4: {
                this.registerLinkTarget(value);
                this.write(34);
                this.writeString(value.convertToString().getByteList());
            }
            case 15: {
                RubyStruct.marshalTo((RubyStruct)value, this);
            }
            case 8: {
                this.writeAndRegisterSymbol(((RubySymbol)value).asJavaString());
            }
            case 6: {
                this.write(84);
            }
            default: {
                throw this.runtime.newTypeError("can't dump " + value.getMetaClass().getName());
            }
        }
    }
    
    public void userNewMarshal(final IRubyObject value, final DynamicMethod method) throws IOException {
        this.userNewCommon(value, method);
    }
    
    public void userNewMarshal(final IRubyObject value) throws IOException {
        this.userNewCommon(value, null);
    }
    
    private void userNewCommon(final IRubyObject value, final DynamicMethod method) throws IOException {
        this.registerLinkTarget(value);
        this.write(85);
        final RubyClass metaclass = value.getMetaClass().getRealClass();
        this.writeAndRegisterSymbol(metaclass.getName());
        IRubyObject marshaled;
        if (method != null) {
            marshaled = method.call(this.runtime.getCurrentContext(), value, value.getMetaClass(), "marshal_dump");
        }
        else {
            marshaled = value.callMethod(this.runtime.getCurrentContext(), "marshal_dump");
        }
        this.dumpObject(marshaled);
    }
    
    public void userMarshal(final IRubyObject value, final DynamicMethod method) throws IOException {
        this.userCommon(value, method);
    }
    
    public void userMarshal(final IRubyObject value) throws IOException {
        this.userCommon(value, null);
    }
    
    private void userCommon(final IRubyObject value, final DynamicMethod method) throws IOException {
        this.registerLinkTarget(value);
        final RubyFixnum depthLimitFixnum = this.runtime.newFixnum(this.depthLimit);
        IRubyObject dumpResult;
        if (method != null) {
            dumpResult = method.call(this.runtime.getCurrentContext(), value, value.getMetaClass(), "_dump", depthLimitFixnum);
        }
        else {
            dumpResult = value.callMethod(this.runtime.getCurrentContext(), "_dump", depthLimitFixnum);
        }
        if (!(dumpResult instanceof RubyString)) {
            throw this.runtime.newTypeError(dumpResult, this.runtime.getString());
        }
        final RubyString marshaled = (RubyString)dumpResult;
        final boolean hasVars;
        if (hasVars = marshaled.hasVariables()) {
            this.write(73);
        }
        this.write(117);
        final RubyClass metaclass = value.getMetaClass().getRealClass();
        this.writeAndRegisterSymbol(metaclass.getName());
        this.writeString(marshaled.getByteList());
        if (hasVars) {
            this.dumpVariables(marshaled.getVariableList());
        }
    }
    
    public void writeUserClass(final IRubyObject obj, final RubyClass type) throws IOException {
        this.write(67);
        if (type.getName().charAt(0) == '#') {
            throw obj.getRuntime().newTypeError("can't dump anonymous class " + type.getName());
        }
        this.writeAndRegisterSymbol(type.getName());
    }
    
    public void dumpVariablesWithEncoding(final List<Variable<Object>> vars, final IRubyObject obj) throws IOException {
        if (this.shouldMarshalEncoding(obj)) {
            this.writeInt(vars.size() + 1);
            this.writeEncoding(((EncodingCapable)obj).getEncoding());
        }
        else {
            this.writeInt(vars.size());
        }
        this.dumpVariablesShared(vars);
    }
    
    public void dumpVariables(final List<Variable<Object>> vars) throws IOException {
        this.writeInt(vars.size());
        this.dumpVariablesShared(vars);
    }
    
    private void dumpVariablesShared(final List<Variable<Object>> vars) throws IOException {
        for (final Variable<Object> var : vars) {
            if (var.getValue() instanceof IRubyObject) {
                this.writeAndRegisterSymbol(var.getName());
                this.dumpObject(var.getValue());
            }
        }
    }
    
    public void writeEncoding(final Encoding encoding) throws IOException {
        if (encoding == null || encoding == USASCIIEncoding.INSTANCE) {
            this.writeAndRegisterSymbol("E");
            this.writeObjectData(this.runtime.getFalse());
        }
        else if (encoding == UTF8Encoding.INSTANCE) {
            this.writeAndRegisterSymbol("E");
            this.writeObjectData(this.runtime.getTrue());
        }
        else {
            this.writeAndRegisterSymbol("encoding");
            final byte[] name = encoding.getName();
            this.write(34);
            this.writeString(new ByteList(name, false));
        }
    }
    
    private boolean hasSingletonMethods(final RubyClass type) {
        for (final DynamicMethod method : type.getMethods().values()) {
            if (method.getImplementationClass() == type) {
                return true;
            }
        }
        return false;
    }
    
    private RubyClass dumpExtended(RubyClass type) throws IOException {
        if (type.isSingleton()) {
            if (this.hasSingletonMethods(type) || type.hasVariables()) {
                throw type.getRuntime().newTypeError("singleton can't be dumped");
            }
            type = type.getSuperClass();
        }
        while (type.isIncluded()) {
            this.write(101);
            this.writeAndRegisterSymbol(((IncludedModuleWrapper)type).getNonIncludedClass().getName());
            type = type.getSuperClass();
        }
        return type;
    }
    
    public void dumpDefaultObjectHeader(final RubyClass type) throws IOException {
        this.dumpDefaultObjectHeader('o', type);
    }
    
    public void dumpDefaultObjectHeader(final char tp, final RubyClass type) throws IOException {
        this.dumpExtended(type);
        this.write(tp);
        this.writeAndRegisterSymbol(getPathFromClass(type.getRealClass()));
    }
    
    public void writeString(final String value) throws IOException {
        this.writeInt(value.length());
        this.out.write(RubyString.stringToBytes(value));
    }
    
    public void writeString(final ByteList value) throws IOException {
        final int len = value.length();
        this.writeInt(len);
        this.out.write(value.getUnsafeBytes(), value.begin(), len);
    }
    
    public void dumpSymbol(final String value) throws IOException {
        this.write(58);
        this.writeString(value);
    }
    
    public void writeInt(int value) throws IOException {
        if (value == 0) {
            this.out.write(0);
        }
        else if (0 < value && value < 123) {
            this.out.write(value + 5);
        }
        else if (-124 < value && value < 0) {
            this.out.write(value - 5 & 0xFF);
        }
        else {
            byte[] buf;
            int i;
            for (buf = new byte[4], i = 0; i < buf.length; ++i) {
                buf[i] = (byte)(value & 0xFF);
                value >>= 8;
                if (value == 0) {
                    break;
                }
                if (value == -1) {
                    break;
                }
            }
            final int len = i + 1;
            this.out.write((value < 0) ? (-len) : len);
            this.out.write(buf, 0, i + 1);
        }
    }
    
    public void writeByte(final int value) throws IOException {
        this.out.write(value);
    }
    
    public boolean isTainted() {
        return this.tainted;
    }
    
    public boolean isUntrusted() {
        return this.untrusted;
    }
}
