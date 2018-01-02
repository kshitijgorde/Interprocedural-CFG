// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.marshal;

import org.jruby.RubyObject;
import org.jcodings.EncodingDB;
import org.jcodings.specific.USASCIIEncoding;
import org.jcodings.Encoding;
import org.jcodings.specific.UTF8Encoding;
import org.jruby.runtime.encoding.EncodingCapable;
import org.jruby.util.ByteList;
import org.jruby.RubyStruct;
import org.jruby.RubyBignum;
import org.jruby.exceptions.RaiseException;
import org.jruby.RubyHash;
import org.jruby.RubyArray;
import org.jruby.RubySymbol;
import org.jruby.RubyRegexp;
import org.jruby.RubyFloat;
import org.jruby.RubyFixnum;
import org.jruby.RubyString;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import java.io.EOFException;
import java.io.IOException;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import java.io.InputStream;

public class UnmarshalStream extends InputStream
{
    protected final Ruby runtime;
    private final UnmarshalCache cache;
    private final IRubyObject proc;
    private final InputStream inputStream;
    private final boolean taint;
    private final boolean untrust;
    
    public UnmarshalStream(final Ruby runtime, final InputStream in, final IRubyObject proc, final boolean taint) throws IOException {
        this(runtime, in, proc, taint, false);
    }
    
    public UnmarshalStream(final Ruby runtime, final InputStream in, final IRubyObject proc, final boolean taint, final boolean untrust) throws IOException {
        this.runtime = runtime;
        this.cache = new UnmarshalCache(runtime);
        this.proc = proc;
        this.inputStream = in;
        this.taint = taint;
        this.untrust = untrust;
        final int major = in.read();
        final int minor = in.read();
        if (major == -1 || minor == -1) {
            throw new EOFException("Unexpected end of stream");
        }
        if (major != 4 || minor > 8) {
            throw runtime.newTypeError(String.format("incompatible marshal file format (can't be read)\n\tformat version %d.%d required; %d.%d given", 4, 8, major, minor));
        }
    }
    
    public IRubyObject unmarshalObject() throws IOException {
        return this.unmarshalObject(new MarshalState(false));
    }
    
    public IRubyObject unmarshalObject(final boolean callProc) throws IOException {
        return this.unmarshalObject(new MarshalState(false), callProc);
    }
    
    public IRubyObject unmarshalObject(final MarshalState state) throws IOException {
        return this.unmarshalObject(state, true);
    }
    
    public IRubyObject unmarshalObject(final MarshalState state, final boolean callProc) throws IOException {
        final int type = this.readUnsignedByte();
        IRubyObject result = null;
        if (this.cache.isLinkType(type)) {
            result = this.cache.readLink(this, type);
            if (callProc && this.runtime.is1_9()) {
                return this.doCallProcForLink(result, type);
            }
        }
        else {
            result = this.unmarshalObjectDirectly(type, state, callProc);
        }
        result.setTaint(this.taint);
        result.setUntrusted(this.untrust);
        return result;
    }
    
    public void registerLinkTarget(final IRubyObject newObject) {
        if (MarshalStream.shouldBeRegistered(newObject)) {
            this.cache.register(newObject);
        }
    }
    
    public static RubyModule getModuleFromPath(final Ruby runtime, final String path) {
        final RubyModule value = runtime.getClassFromPath(path);
        if (!value.isModule()) {
            throw runtime.newArgumentError(path + " does not refer module");
        }
        return value;
    }
    
    public static RubyClass getClassFromPath(final Ruby runtime, final String path) {
        final RubyModule value = runtime.getClassFromPath(path);
        if (!value.isClass()) {
            throw runtime.newArgumentError(path + " does not refer class");
        }
        return (RubyClass)value;
    }
    
    private IRubyObject doCallProcForLink(final IRubyObject result, final int type) {
        if (this.proc != null && type != 59) {
            return RuntimeHelpers.invoke(this.getRuntime().getCurrentContext(), this.proc, "call", result);
        }
        return result;
    }
    
    private IRubyObject doCallProcForObj(final IRubyObject result) {
        if (this.proc != null) {
            return RuntimeHelpers.invoke(this.getRuntime().getCurrentContext(), this.proc, "call", result);
        }
        return result;
    }
    
    private IRubyObject unmarshalObjectDirectly(final int type, final MarshalState state, final boolean callProc) throws IOException {
        IRubyObject rubyObj = null;
        switch (type) {
            case 73: {
                final MarshalState childState = new MarshalState(true);
                rubyObj = this.unmarshalObject(childState);
                if (childState.isIvarWaiting()) {
                    this.defaultVariablesUnmarshal(rubyObj);
                }
                return rubyObj;
            }
            case 48: {
                rubyObj = this.runtime.getNil();
                break;
            }
            case 84: {
                rubyObj = this.runtime.getTrue();
                break;
            }
            case 70: {
                rubyObj = this.runtime.getFalse();
                break;
            }
            case 34: {
                rubyObj = RubyString.unmarshalFrom(this);
                break;
            }
            case 105: {
                rubyObj = RubyFixnum.unmarshalFrom(this);
                break;
            }
            case 102: {
                rubyObj = RubyFloat.unmarshalFrom(this);
                break;
            }
            case 47: {
                rubyObj = RubyRegexp.unmarshalFrom(this);
                break;
            }
            case 58: {
                rubyObj = RubySymbol.unmarshalFrom(this);
                break;
            }
            case 91: {
                rubyObj = RubyArray.unmarshalFrom(this);
                break;
            }
            case 123: {
                rubyObj = RubyHash.unmarshalFrom(this, false);
                break;
            }
            case 125: {
                rubyObj = RubyHash.unmarshalFrom(this, true);
                break;
            }
            case 99: {
                rubyObj = RubyClass.unmarshalFrom(this);
                break;
            }
            case 109: {
                rubyObj = RubyModule.unmarshalFrom(this);
                break;
            }
            case 101: {
                final RubySymbol moduleName = (RubySymbol)this.unmarshalObject();
                RubyModule tp = null;
                try {
                    tp = this.runtime.getClassFromPath(moduleName.asJavaString());
                }
                catch (RaiseException e) {
                    if (this.runtime.fastGetModule("NameError").isInstance(e.getException())) {
                        throw this.runtime.newArgumentError("undefined class/module " + moduleName.asJavaString());
                    }
                    throw e;
                }
                rubyObj = this.unmarshalObject();
                tp.extend_object(rubyObj);
                tp.callMethod(this.runtime.getCurrentContext(), "extended", rubyObj);
                break;
            }
            case 108: {
                rubyObj = RubyBignum.unmarshalFrom(this);
                break;
            }
            case 83: {
                rubyObj = RubyStruct.unmarshalFrom(this);
                break;
            }
            case 111: {
                rubyObj = this.defaultObjectUnmarshal();
                break;
            }
            case 117: {
                rubyObj = this.userUnmarshal(state);
                break;
            }
            case 85: {
                rubyObj = this.userNewUnmarshal();
                break;
            }
            case 67: {
                rubyObj = this.uclassUnmarshall();
                break;
            }
            default: {
                throw this.getRuntime().newArgumentError("dump format error(" + (char)type + ")");
            }
        }
        if (this.runtime.is1_9()) {
            if (callProc) {
                return this.doCallProcForObj(rubyObj);
            }
        }
        else if (type != 58) {
            this.doCallProcForObj(rubyObj);
        }
        return rubyObj;
    }
    
    public Ruby getRuntime() {
        return this.runtime;
    }
    
    public int readUnsignedByte() throws IOException {
        final int result = this.read();
        if (result == -1) {
            throw new EOFException("Unexpected end of stream");
        }
        return result;
    }
    
    public byte readSignedByte() throws IOException {
        final int b = this.readUnsignedByte();
        if (b > 127) {
            return (byte)(b - 256);
        }
        return (byte)b;
    }
    
    public ByteList unmarshalString() throws IOException {
        final int length = this.unmarshalInt();
        final byte[] buffer = new byte[length];
        int read;
        for (int readLength = 0; readLength < length; readLength += read) {
            read = this.inputStream.read(buffer, readLength, length - readLength);
            if (read == -1) {
                throw this.getRuntime().newArgumentError("marshal data too short");
            }
        }
        return new ByteList(buffer, false);
    }
    
    public int unmarshalInt() throws IOException {
        int c = this.readSignedByte();
        if (c == 0) {
            return 0;
        }
        if (5 < c && c < 128) {
            return c - 5;
        }
        if (-129 < c && c < -5) {
            return c + 5;
        }
        long result;
        if (c > 0) {
            result = 0L;
            for (int i = 0; i < c; ++i) {
                result |= this.readUnsignedByte() << 8 * i;
            }
        }
        else {
            c = -c;
            result = -1L;
            for (int i = 0; i < c; ++i) {
                result &= ~(255L << 8 * i);
                result |= this.readUnsignedByte() << 8 * i;
            }
        }
        return (int)result;
    }
    
    private IRubyObject defaultObjectUnmarshal() throws IOException {
        final RubySymbol className = (RubySymbol)this.unmarshalObject(false);
        RubyClass type = null;
        try {
            type = getClassFromPath(this.runtime, className.toString());
        }
        catch (RaiseException e) {
            if (this.runtime.fastGetModule("NameError").isInstance(e.getException())) {
                throw this.runtime.newArgumentError("undefined class/module " + className.asJavaString());
            }
            throw e;
        }
        assert type != null : "type shouldn't be null.";
        final IRubyObject result = (IRubyObject)type.unmarshal(this);
        return result;
    }
    
    public void defaultVariablesUnmarshal(final IRubyObject object) throws IOException {
        final int count = this.unmarshalInt();
        final RubyClass cls = object.getMetaClass().getRealClass();
        int i = count;
        while (--i >= 0) {
            final IRubyObject key = this.unmarshalObject(false);
            if (i == 0 && this.runtime.is1_9() && (object instanceof RubyString || object instanceof RubyRegexp) && count >= 1) {
                final EncodingCapable strObj = (EncodingCapable)object;
                if (key.asJavaString().equals("E")) {
                    if (this.unmarshalObject().isTrue()) {
                        strObj.setEncoding(UTF8Encoding.INSTANCE);
                        continue;
                    }
                    strObj.setEncoding(USASCIIEncoding.INSTANCE);
                    continue;
                }
                else if (key.asJavaString().equals("encoding")) {
                    this.read();
                    final ByteList encodingName = this.unmarshalString();
                    final EncodingDB.Entry entry = this.runtime.getEncodingService().findEncodingOrAliasEntry(encodingName);
                    if (entry == null) {
                        throw this.runtime.newArgumentError("invalid encoding in marshaling stream: " + (Object)encodingName);
                    }
                    final Encoding encoding = entry.getEncoding();
                    strObj.setEncoding(encoding);
                    continue;
                }
            }
            final String name = key.asJavaString();
            final IRubyObject value = this.unmarshalObject();
            cls.getVariableAccessorForWrite(name).set(object, value);
        }
    }
    
    private IRubyObject uclassUnmarshall() throws IOException {
        final RubySymbol className = (RubySymbol)this.unmarshalObject(false);
        final RubyClass type = (RubyClass)this.runtime.getClassFromPath(className.asJavaString());
        final RubyObject result = (RubyObject)this.unmarshalObject();
        result.setMetaClass(type);
        return result;
    }
    
    private IRubyObject userUnmarshal(final MarshalState state) throws IOException {
        final String className = this.unmarshalObject(false).asJavaString();
        final ByteList marshaled = this.unmarshalString();
        final RubyClass classInstance = this.findClass(className);
        final RubyString data = RubyString.newString(this.getRuntime(), marshaled);
        if (state.isIvarWaiting()) {
            this.defaultVariablesUnmarshal(data);
            state.setIvarWaiting(false);
        }
        final IRubyObject unmarshaled = classInstance.smartLoadOldUser(data);
        this.registerLinkTarget(unmarshaled);
        return unmarshaled;
    }
    
    private IRubyObject userNewUnmarshal() throws IOException {
        final String className = this.unmarshalObject(false).asJavaString();
        final RubyClass classInstance = this.findClass(className);
        final IRubyObject result = classInstance.allocate();
        this.registerLinkTarget(result);
        final IRubyObject marshaled = this.unmarshalObject();
        return classInstance.smartLoadNewUser(result, marshaled);
    }
    
    private RubyClass findClass(final String className) {
        RubyModule classInstance;
        try {
            classInstance = this.runtime.getClassFromPath(className);
        }
        catch (RaiseException e) {
            if (this.runtime.getModule("NameError").isInstance(e.getException())) {
                throw this.runtime.newArgumentError("undefined class/module " + className);
            }
            throw e;
        }
        if (!(classInstance instanceof RubyClass)) {
            throw this.runtime.newArgumentError(className + " does not refer class");
        }
        return (RubyClass)classInstance;
    }
    
    public int read() throws IOException {
        return this.inputStream.read();
    }
    
    private class MarshalState
    {
        private boolean ivarWaiting;
        
        MarshalState(final boolean ivarWaiting) {
            this.ivarWaiting = ivarWaiting;
        }
        
        boolean isIvarWaiting() {
            return this.ivarWaiting;
        }
        
        void setIvarWaiting(final boolean ivarWaiting) {
            this.ivarWaiting = ivarWaiting;
        }
    }
}
