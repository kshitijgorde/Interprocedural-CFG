// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast.executable;

import org.jruby.runtime.MethodIndex;
import org.jcodings.EncodingDB;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.RubyModule;
import java.math.BigInteger;
import org.jruby.RubyRegexp;
import org.jruby.RubyFloat;
import org.jruby.RubyFixnum;
import org.jcodings.Encoding;
import org.jruby.util.ByteList;
import org.jruby.RubyString;
import org.jruby.RubySymbol;
import org.jruby.runtime.CompiledBlockCallback;
import org.jruby.Ruby;
import org.jruby.runtime.BlockBody;
import org.jruby.runtime.CallSite;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public abstract class AbstractScript implements Script
{
    public RuntimeCache runtimeCache;
    public static final int NUMBERED_SCOPE_COUNT = 10;
    public static final int NUMBERED_CALLSITE_COUNT = 10;
    public static final int NUMBERED_BLOCKBODY_COUNT = 10;
    public static final int NUMBERED_BLOCKCALLBACK_COUNT = 10;
    public static final int NUMBERED_SYMBOL_COUNT = 10;
    public static final int NUMBERED_STRING_COUNT = 10;
    public static final int NUMBERED_ENCODING_COUNT = 10;
    public static final int NUMBERED_FIXNUM_COUNT = 10;
    public static final int NUMBERED_FLOAT_COUNT = 10;
    public static final int NUMBERED_REGEXP_COUNT = 10;
    public static final int NUMBERED_BIGINTEGER_COUNT = 10;
    public static final int NUMBERED_VARIABLEREADER_COUNT = 10;
    public static final int NUMBERED_VARIABLEWRITER_COUNT = 10;
    public static final int NUMBERED_CONSTANT_COUNT = 10;
    public static final int NUMBERED_CONSTANTFROM_COUNT = 10;
    public static final int NUMBERED_METHOD_COUNT = 10;
    protected String filename;
    
    public IRubyObject __file__(final ThreadContext context, final IRubyObject self, final Block block) {
        return this.__file__(context, self, IRubyObject.NULL_ARRAY, block);
    }
    
    public IRubyObject __file__(final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block) {
        return this.__file__(context, self, new IRubyObject[] { arg }, block);
    }
    
    public IRubyObject __file__(final ThreadContext context, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return this.__file__(context, self, new IRubyObject[] { arg1, arg2 }, block);
    }
    
    public IRubyObject __file__(final ThreadContext context, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        return this.__file__(context, self, new IRubyObject[] { arg1, arg2, arg3 }, block);
    }
    
    public IRubyObject load(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
        return null;
    }
    
    public IRubyObject run(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
        return this.__file__(context, self, args, block);
    }
    
    public final StaticScope getScope(final ThreadContext context, final String varNamesDescriptor, final int i) {
        return this.runtimeCache.getScope(context, varNamesDescriptor, i);
    }
    
    public final StaticScope getScope0(final ThreadContext context, final String varNamesDescriptor) {
        return this.runtimeCache.getScope(context, varNamesDescriptor, 0);
    }
    
    public final StaticScope getScope1(final ThreadContext context, final String varNamesDescriptor) {
        return this.runtimeCache.getScope(context, varNamesDescriptor, 1);
    }
    
    public final StaticScope getScope2(final ThreadContext context, final String varNamesDescriptor) {
        return this.runtimeCache.getScope(context, varNamesDescriptor, 2);
    }
    
    public final StaticScope getScope3(final ThreadContext context, final String varNamesDescriptor) {
        return this.runtimeCache.getScope(context, varNamesDescriptor, 3);
    }
    
    public final StaticScope getScope4(final ThreadContext context, final String varNamesDescriptor) {
        return this.runtimeCache.getScope(context, varNamesDescriptor, 4);
    }
    
    public final StaticScope getScope5(final ThreadContext context, final String varNamesDescriptor) {
        return this.runtimeCache.getScope(context, varNamesDescriptor, 5);
    }
    
    public final StaticScope getScope6(final ThreadContext context, final String varNamesDescriptor) {
        return this.runtimeCache.getScope(context, varNamesDescriptor, 6);
    }
    
    public final StaticScope getScope7(final ThreadContext context, final String varNamesDescriptor) {
        return this.runtimeCache.getScope(context, varNamesDescriptor, 7);
    }
    
    public final StaticScope getScope8(final ThreadContext context, final String varNamesDescriptor) {
        return this.runtimeCache.getScope(context, varNamesDescriptor, 8);
    }
    
    public final StaticScope getScope9(final ThreadContext context, final String varNamesDescriptor) {
        return this.runtimeCache.getScope(context, varNamesDescriptor, 9);
    }
    
    public final CallSite getCallSite(final int i) {
        return this.runtimeCache.callSites[i];
    }
    
    public final CallSite getCallSite0() {
        return this.runtimeCache.callSites[0];
    }
    
    public final CallSite getCallSite1() {
        return this.runtimeCache.callSites[1];
    }
    
    public final CallSite getCallSite2() {
        return this.runtimeCache.callSites[2];
    }
    
    public final CallSite getCallSite3() {
        return this.runtimeCache.callSites[3];
    }
    
    public final CallSite getCallSite4() {
        return this.runtimeCache.callSites[4];
    }
    
    public final CallSite getCallSite5() {
        return this.runtimeCache.callSites[5];
    }
    
    public final CallSite getCallSite6() {
        return this.runtimeCache.callSites[6];
    }
    
    public final CallSite getCallSite7() {
        return this.runtimeCache.callSites[7];
    }
    
    public final CallSite getCallSite8() {
        return this.runtimeCache.callSites[8];
    }
    
    public final CallSite getCallSite9() {
        return this.runtimeCache.callSites[9];
    }
    
    public final BlockBody getBlockBody(final ThreadContext context, final int i, final String descriptor) {
        return this.runtimeCache.getBlockBody(this, context, i, descriptor);
    }
    
    public final BlockBody getBlockBody0(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody(this, context, 0, descriptor);
    }
    
    public final BlockBody getBlockBody1(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody(this, context, 1, descriptor);
    }
    
    public final BlockBody getBlockBody2(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody(this, context, 2, descriptor);
    }
    
    public final BlockBody getBlockBody3(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody(this, context, 3, descriptor);
    }
    
    public final BlockBody getBlockBody4(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody(this, context, 4, descriptor);
    }
    
    public final BlockBody getBlockBody5(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody(this, context, 5, descriptor);
    }
    
    public final BlockBody getBlockBody6(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody(this, context, 6, descriptor);
    }
    
    public final BlockBody getBlockBody7(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody(this, context, 7, descriptor);
    }
    
    public final BlockBody getBlockBody8(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody(this, context, 8, descriptor);
    }
    
    public final BlockBody getBlockBody9(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody(this, context, 9, descriptor);
    }
    
    public final BlockBody getBlockBody19(final ThreadContext context, final int i, final String descriptor) {
        return this.runtimeCache.getBlockBody19(this, context, i, descriptor);
    }
    
    public final BlockBody getBlockBody190(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody19(this, context, 0, descriptor);
    }
    
    public final BlockBody getBlockBody191(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody19(this, context, 1, descriptor);
    }
    
    public final BlockBody getBlockBody192(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody19(this, context, 2, descriptor);
    }
    
    public final BlockBody getBlockBody193(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody19(this, context, 3, descriptor);
    }
    
    public final BlockBody getBlockBody194(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody19(this, context, 4, descriptor);
    }
    
    public final BlockBody getBlockBody195(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody19(this, context, 5, descriptor);
    }
    
    public final BlockBody getBlockBody196(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody19(this, context, 6, descriptor);
    }
    
    public final BlockBody getBlockBody197(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody19(this, context, 7, descriptor);
    }
    
    public final BlockBody getBlockBody198(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody19(this, context, 8, descriptor);
    }
    
    public final BlockBody getBlockBody199(final ThreadContext context, final String descriptor) {
        return this.runtimeCache.getBlockBody19(this, context, 9, descriptor);
    }
    
    public final CompiledBlockCallback getBlockCallback(final Ruby runtime, final int i, final String method) {
        return this.runtimeCache.getBlockCallback(this, runtime, i, method);
    }
    
    public final CompiledBlockCallback getBlockCallback0(final Ruby runtime, final String method) {
        return this.runtimeCache.getBlockCallback(this, runtime, 0, method);
    }
    
    public final CompiledBlockCallback getBlockCallback1(final Ruby runtime, final String method) {
        return this.runtimeCache.getBlockCallback(this, runtime, 1, method);
    }
    
    public final CompiledBlockCallback getBlockCallback2(final Ruby runtime, final String method) {
        return this.runtimeCache.getBlockCallback(this, runtime, 2, method);
    }
    
    public final CompiledBlockCallback getBlockCallback3(final Ruby runtime, final String method) {
        return this.runtimeCache.getBlockCallback(this, runtime, 3, method);
    }
    
    public final CompiledBlockCallback getBlockCallback4(final Ruby runtime, final String method) {
        return this.runtimeCache.getBlockCallback(this, runtime, 4, method);
    }
    
    public final CompiledBlockCallback getBlockCallback5(final Ruby runtime, final String method) {
        return this.runtimeCache.getBlockCallback(this, runtime, 5, method);
    }
    
    public final CompiledBlockCallback getBlockCallback6(final Ruby runtime, final String method) {
        return this.runtimeCache.getBlockCallback(this, runtime, 6, method);
    }
    
    public final CompiledBlockCallback getBlockCallback7(final Ruby runtime, final String method) {
        return this.runtimeCache.getBlockCallback(this, runtime, 7, method);
    }
    
    public final CompiledBlockCallback getBlockCallback8(final Ruby runtime, final String method) {
        return this.runtimeCache.getBlockCallback(this, runtime, 8, method);
    }
    
    public final CompiledBlockCallback getBlockCallback9(final Ruby runtime, final String method) {
        return this.runtimeCache.getBlockCallback(this, runtime, 9, method);
    }
    
    public final RubySymbol getSymbol(final Ruby runtime, final int i, final String name) {
        return this.runtimeCache.getSymbol(runtime, i, name);
    }
    
    public final RubySymbol getSymbol0(final Ruby runtime, final String name) {
        return this.runtimeCache.getSymbol(runtime, 0, name);
    }
    
    public final RubySymbol getSymbol1(final Ruby runtime, final String name) {
        return this.runtimeCache.getSymbol(runtime, 1, name);
    }
    
    public final RubySymbol getSymbol2(final Ruby runtime, final String name) {
        return this.runtimeCache.getSymbol(runtime, 2, name);
    }
    
    public final RubySymbol getSymbol3(final Ruby runtime, final String name) {
        return this.runtimeCache.getSymbol(runtime, 3, name);
    }
    
    public final RubySymbol getSymbol4(final Ruby runtime, final String name) {
        return this.runtimeCache.getSymbol(runtime, 4, name);
    }
    
    public final RubySymbol getSymbol5(final Ruby runtime, final String name) {
        return this.runtimeCache.getSymbol(runtime, 5, name);
    }
    
    public final RubySymbol getSymbol6(final Ruby runtime, final String name) {
        return this.runtimeCache.getSymbol(runtime, 6, name);
    }
    
    public final RubySymbol getSymbol7(final Ruby runtime, final String name) {
        return this.runtimeCache.getSymbol(runtime, 7, name);
    }
    
    public final RubySymbol getSymbol8(final Ruby runtime, final String name) {
        return this.runtimeCache.getSymbol(runtime, 8, name);
    }
    
    public final RubySymbol getSymbol9(final Ruby runtime, final String name) {
        return this.runtimeCache.getSymbol(runtime, 9, name);
    }
    
    public final RubyString getString(final Ruby runtime, final int i, final int codeRange) {
        return this.runtimeCache.getString(runtime, i, codeRange);
    }
    
    public final RubyString getString0(final Ruby runtime, final int codeRange) {
        return this.runtimeCache.getString(runtime, 0, codeRange);
    }
    
    public final RubyString getString1(final Ruby runtime, final int codeRange) {
        return this.runtimeCache.getString(runtime, 1, codeRange);
    }
    
    public final RubyString getString2(final Ruby runtime, final int codeRange) {
        return this.runtimeCache.getString(runtime, 2, codeRange);
    }
    
    public final RubyString getString3(final Ruby runtime, final int codeRange) {
        return this.runtimeCache.getString(runtime, 3, codeRange);
    }
    
    public final RubyString getString4(final Ruby runtime, final int codeRange) {
        return this.runtimeCache.getString(runtime, 4, codeRange);
    }
    
    public final RubyString getString5(final Ruby runtime, final int codeRange) {
        return this.runtimeCache.getString(runtime, 5, codeRange);
    }
    
    public final RubyString getString6(final Ruby runtime, final int codeRange) {
        return this.runtimeCache.getString(runtime, 6, codeRange);
    }
    
    public final RubyString getString7(final Ruby runtime, final int codeRange) {
        return this.runtimeCache.getString(runtime, 7, codeRange);
    }
    
    public final RubyString getString8(final Ruby runtime, final int codeRange) {
        return this.runtimeCache.getString(runtime, 8, codeRange);
    }
    
    public final RubyString getString9(final Ruby runtime, final int codeRange) {
        return this.runtimeCache.getString(runtime, 9, codeRange);
    }
    
    public final ByteList getByteList(final int i) {
        return this.runtimeCache.getByteList(i);
    }
    
    public final ByteList getByteList0() {
        return this.runtimeCache.getByteList(0);
    }
    
    public final ByteList getByteList1() {
        return this.runtimeCache.getByteList(1);
    }
    
    public final ByteList getByteList2() {
        return this.runtimeCache.getByteList(2);
    }
    
    public final ByteList getByteList3() {
        return this.runtimeCache.getByteList(3);
    }
    
    public final ByteList getByteList4() {
        return this.runtimeCache.getByteList(4);
    }
    
    public final ByteList getByteList5() {
        return this.runtimeCache.getByteList(5);
    }
    
    public final ByteList getByteList6() {
        return this.runtimeCache.getByteList(6);
    }
    
    public final ByteList getByteList7() {
        return this.runtimeCache.getByteList(7);
    }
    
    public final ByteList getByteList8() {
        return this.runtimeCache.getByteList(8);
    }
    
    public final ByteList getByteList9() {
        return this.runtimeCache.getByteList(9);
    }
    
    public final Encoding getEncoding(final int i) {
        return this.runtimeCache.getEncoding(i);
    }
    
    public final Encoding getEncoding0() {
        return this.runtimeCache.getEncoding(0);
    }
    
    public final Encoding getEncoding1() {
        return this.runtimeCache.getEncoding(1);
    }
    
    public final Encoding getEncoding2() {
        return this.runtimeCache.getEncoding(2);
    }
    
    public final Encoding getEncoding3() {
        return this.runtimeCache.getEncoding(3);
    }
    
    public final Encoding getEncoding4() {
        return this.runtimeCache.getEncoding(4);
    }
    
    public final Encoding getEncoding5() {
        return this.runtimeCache.getEncoding(5);
    }
    
    public final Encoding getEncoding6() {
        return this.runtimeCache.getEncoding(6);
    }
    
    public final Encoding getEncoding7() {
        return this.runtimeCache.getEncoding(7);
    }
    
    public final Encoding getEncoding8() {
        return this.runtimeCache.getEncoding(8);
    }
    
    public final Encoding getEncoding9() {
        return this.runtimeCache.getEncoding(9);
    }
    
    public final RubyFixnum getFixnum(final Ruby runtime, final int i, final int value) {
        return this.runtimeCache.getFixnum(runtime, i, value);
    }
    
    public final RubyFixnum getFixnum(final Ruby runtime, final int i, final long value) {
        return this.runtimeCache.getFixnum(runtime, i, value);
    }
    
    public final RubyFixnum getFixnum0(final Ruby runtime, final int value) {
        return this.runtimeCache.getFixnum(runtime, 0, value);
    }
    
    public final RubyFixnum getFixnum1(final Ruby runtime, final int value) {
        return this.runtimeCache.getFixnum(runtime, 1, value);
    }
    
    public final RubyFixnum getFixnum2(final Ruby runtime, final int value) {
        return this.runtimeCache.getFixnum(runtime, 2, value);
    }
    
    public final RubyFixnum getFixnum3(final Ruby runtime, final int value) {
        return this.runtimeCache.getFixnum(runtime, 3, value);
    }
    
    public final RubyFixnum getFixnum4(final Ruby runtime, final int value) {
        return this.runtimeCache.getFixnum(runtime, 4, value);
    }
    
    public final RubyFixnum getFixnum5(final Ruby runtime, final int value) {
        return this.runtimeCache.getFixnum(runtime, 5, value);
    }
    
    public final RubyFixnum getFixnum6(final Ruby runtime, final int value) {
        return this.runtimeCache.getFixnum(runtime, 6, value);
    }
    
    public final RubyFixnum getFixnum7(final Ruby runtime, final int value) {
        return this.runtimeCache.getFixnum(runtime, 7, value);
    }
    
    public final RubyFixnum getFixnum8(final Ruby runtime, final int value) {
        return this.runtimeCache.getFixnum(runtime, 8, value);
    }
    
    public final RubyFixnum getFixnum9(final Ruby runtime, final int value) {
        return this.runtimeCache.getFixnum(runtime, 9, value);
    }
    
    public final RubyFloat getFloat(final Ruby runtime, final int i, final double value) {
        return this.runtimeCache.getFloat(runtime, i, value);
    }
    
    public final RubyFloat getFloat0(final Ruby runtime, final double value) {
        return this.runtimeCache.getFloat(runtime, 0, value);
    }
    
    public final RubyFloat getFloat1(final Ruby runtime, final double value) {
        return this.runtimeCache.getFloat(runtime, 1, value);
    }
    
    public final RubyFloat getFloat2(final Ruby runtime, final double value) {
        return this.runtimeCache.getFloat(runtime, 2, value);
    }
    
    public final RubyFloat getFloat3(final Ruby runtime, final double value) {
        return this.runtimeCache.getFloat(runtime, 3, value);
    }
    
    public final RubyFloat getFloat4(final Ruby runtime, final double value) {
        return this.runtimeCache.getFloat(runtime, 4, value);
    }
    
    public final RubyFloat getFloat5(final Ruby runtime, final double value) {
        return this.runtimeCache.getFloat(runtime, 5, value);
    }
    
    public final RubyFloat getFloat6(final Ruby runtime, final double value) {
        return this.runtimeCache.getFloat(runtime, 6, value);
    }
    
    public final RubyFloat getFloat7(final Ruby runtime, final double value) {
        return this.runtimeCache.getFloat(runtime, 7, value);
    }
    
    public final RubyFloat getFloat8(final Ruby runtime, final double value) {
        return this.runtimeCache.getFloat(runtime, 8, value);
    }
    
    public final RubyFloat getFloat9(final Ruby runtime, final double value) {
        return this.runtimeCache.getFloat(runtime, 9, value);
    }
    
    public final RubyRegexp getRegexp(final Ruby runtime, final int i, final ByteList pattern, final int options) {
        return this.runtimeCache.getRegexp(runtime, i, pattern, options);
    }
    
    public final RubyRegexp getRegexp0(final Ruby runtime, final ByteList pattern, final int options) {
        return this.runtimeCache.getRegexp(runtime, 0, pattern, options);
    }
    
    public final RubyRegexp getRegexp1(final Ruby runtime, final ByteList pattern, final int options) {
        return this.runtimeCache.getRegexp(runtime, 1, pattern, options);
    }
    
    public final RubyRegexp getRegexp2(final Ruby runtime, final ByteList pattern, final int options) {
        return this.runtimeCache.getRegexp(runtime, 2, pattern, options);
    }
    
    public final RubyRegexp getRegexp3(final Ruby runtime, final ByteList pattern, final int options) {
        return this.runtimeCache.getRegexp(runtime, 3, pattern, options);
    }
    
    public final RubyRegexp getRegexp4(final Ruby runtime, final ByteList pattern, final int options) {
        return this.runtimeCache.getRegexp(runtime, 4, pattern, options);
    }
    
    public final RubyRegexp getRegexp5(final Ruby runtime, final ByteList pattern, final int options) {
        return this.runtimeCache.getRegexp(runtime, 5, pattern, options);
    }
    
    public final RubyRegexp getRegexp6(final Ruby runtime, final ByteList pattern, final int options) {
        return this.runtimeCache.getRegexp(runtime, 6, pattern, options);
    }
    
    public final RubyRegexp getRegexp7(final Ruby runtime, final ByteList pattern, final int options) {
        return this.runtimeCache.getRegexp(runtime, 7, pattern, options);
    }
    
    public final RubyRegexp getRegexp8(final Ruby runtime, final ByteList pattern, final int options) {
        return this.runtimeCache.getRegexp(runtime, 8, pattern, options);
    }
    
    public final RubyRegexp getRegexp9(final Ruby runtime, final ByteList pattern, final int options) {
        return this.runtimeCache.getRegexp(runtime, 9, pattern, options);
    }
    
    public final BigInteger getBigInteger(final Ruby runtime, final int i, final String name) {
        return this.runtimeCache.getBigInteger(runtime, i, name);
    }
    
    public final BigInteger getBigInteger0(final Ruby runtime, final String name) {
        return this.runtimeCache.getBigInteger(runtime, 0, name);
    }
    
    public final BigInteger getBigInteger1(final Ruby runtime, final String name) {
        return this.runtimeCache.getBigInteger(runtime, 1, name);
    }
    
    public final BigInteger getBigInteger2(final Ruby runtime, final String name) {
        return this.runtimeCache.getBigInteger(runtime, 2, name);
    }
    
    public final BigInteger getBigInteger3(final Ruby runtime, final String name) {
        return this.runtimeCache.getBigInteger(runtime, 3, name);
    }
    
    public final BigInteger getBigInteger4(final Ruby runtime, final String name) {
        return this.runtimeCache.getBigInteger(runtime, 4, name);
    }
    
    public final BigInteger getBigInteger5(final Ruby runtime, final String name) {
        return this.runtimeCache.getBigInteger(runtime, 5, name);
    }
    
    public final BigInteger getBigInteger6(final Ruby runtime, final String name) {
        return this.runtimeCache.getBigInteger(runtime, 6, name);
    }
    
    public final BigInteger getBigInteger7(final Ruby runtime, final String name) {
        return this.runtimeCache.getBigInteger(runtime, 7, name);
    }
    
    public final BigInteger getBigInteger8(final Ruby runtime, final String name) {
        return this.runtimeCache.getBigInteger(runtime, 8, name);
    }
    
    public final BigInteger getBigInteger9(final Ruby runtime, final String name) {
        return this.runtimeCache.getBigInteger(runtime, 9, name);
    }
    
    public final IRubyObject getVariable(final Ruby runtime, final int i, final String name, final IRubyObject object) {
        return this.runtimeCache.getVariable(runtime, i, name, object);
    }
    
    public final IRubyObject getVariable0(final Ruby runtime, final String name, final IRubyObject object) {
        return this.runtimeCache.getVariable(runtime, 0, name, object);
    }
    
    public final IRubyObject getVariable1(final Ruby runtime, final String name, final IRubyObject object) {
        return this.runtimeCache.getVariable(runtime, 1, name, object);
    }
    
    public final IRubyObject getVariable2(final Ruby runtime, final String name, final IRubyObject object) {
        return this.runtimeCache.getVariable(runtime, 2, name, object);
    }
    
    public final IRubyObject getVariable3(final Ruby runtime, final String name, final IRubyObject object) {
        return this.runtimeCache.getVariable(runtime, 3, name, object);
    }
    
    public final IRubyObject getVariable4(final Ruby runtime, final String name, final IRubyObject object) {
        return this.runtimeCache.getVariable(runtime, 4, name, object);
    }
    
    public final IRubyObject getVariable5(final Ruby runtime, final String name, final IRubyObject object) {
        return this.runtimeCache.getVariable(runtime, 5, name, object);
    }
    
    public final IRubyObject getVariable6(final Ruby runtime, final String name, final IRubyObject object) {
        return this.runtimeCache.getVariable(runtime, 6, name, object);
    }
    
    public final IRubyObject getVariable7(final Ruby runtime, final String name, final IRubyObject object) {
        return this.runtimeCache.getVariable(runtime, 7, name, object);
    }
    
    public final IRubyObject getVariable8(final Ruby runtime, final String name, final IRubyObject object) {
        return this.runtimeCache.getVariable(runtime, 8, name, object);
    }
    
    public final IRubyObject getVariable9(final Ruby runtime, final String name, final IRubyObject object) {
        return this.runtimeCache.getVariable(runtime, 9, name, object);
    }
    
    public final IRubyObject setVariable(final Ruby runtime, final int i, final String name, final IRubyObject object, final IRubyObject value) {
        return this.runtimeCache.setVariable(runtime, i, name, object, value);
    }
    
    public final IRubyObject setVariable0(final Ruby runtime, final String name, final IRubyObject object, final IRubyObject value) {
        return this.runtimeCache.setVariable(runtime, 0, name, object, value);
    }
    
    public final IRubyObject setVariable1(final Ruby runtime, final String name, final IRubyObject object, final IRubyObject value) {
        return this.runtimeCache.setVariable(runtime, 1, name, object, value);
    }
    
    public final IRubyObject setVariable2(final Ruby runtime, final String name, final IRubyObject object, final IRubyObject value) {
        return this.runtimeCache.setVariable(runtime, 2, name, object, value);
    }
    
    public final IRubyObject setVariable3(final Ruby runtime, final String name, final IRubyObject object, final IRubyObject value) {
        return this.runtimeCache.setVariable(runtime, 3, name, object, value);
    }
    
    public final IRubyObject setVariable4(final Ruby runtime, final String name, final IRubyObject object, final IRubyObject value) {
        return this.runtimeCache.setVariable(runtime, 4, name, object, value);
    }
    
    public final IRubyObject setVariable5(final Ruby runtime, final String name, final IRubyObject object, final IRubyObject value) {
        return this.runtimeCache.setVariable(runtime, 5, name, object, value);
    }
    
    public final IRubyObject setVariable6(final Ruby runtime, final String name, final IRubyObject object, final IRubyObject value) {
        return this.runtimeCache.setVariable(runtime, 6, name, object, value);
    }
    
    public final IRubyObject setVariable7(final Ruby runtime, final String name, final IRubyObject object, final IRubyObject value) {
        return this.runtimeCache.setVariable(runtime, 7, name, object, value);
    }
    
    public final IRubyObject setVariable8(final Ruby runtime, final String name, final IRubyObject object, final IRubyObject value) {
        return this.runtimeCache.setVariable(runtime, 8, name, object, value);
    }
    
    public final IRubyObject setVariable9(final Ruby runtime, final String name, final IRubyObject object, final IRubyObject value) {
        return this.runtimeCache.setVariable(runtime, 9, name, object, value);
    }
    
    public final IRubyObject getConstant(final ThreadContext context, final String name, final int i) {
        return this.runtimeCache.getConstant(context, name, i);
    }
    
    public final IRubyObject getConstant0(final ThreadContext context, final String name) {
        return this.runtimeCache.getConstant(context, name, 0);
    }
    
    public final IRubyObject getConstant1(final ThreadContext context, final String name) {
        return this.runtimeCache.getConstant(context, name, 1);
    }
    
    public final IRubyObject getConstant2(final ThreadContext context, final String name) {
        return this.runtimeCache.getConstant(context, name, 2);
    }
    
    public final IRubyObject getConstant3(final ThreadContext context, final String name) {
        return this.runtimeCache.getConstant(context, name, 3);
    }
    
    public final IRubyObject getConstant4(final ThreadContext context, final String name) {
        return this.runtimeCache.getConstant(context, name, 4);
    }
    
    public final IRubyObject getConstant5(final ThreadContext context, final String name) {
        return this.runtimeCache.getConstant(context, name, 5);
    }
    
    public final IRubyObject getConstant6(final ThreadContext context, final String name) {
        return this.runtimeCache.getConstant(context, name, 6);
    }
    
    public final IRubyObject getConstant7(final ThreadContext context, final String name) {
        return this.runtimeCache.getConstant(context, name, 7);
    }
    
    public final IRubyObject getConstant8(final ThreadContext context, final String name) {
        return this.runtimeCache.getConstant(context, name, 8);
    }
    
    public final IRubyObject getConstant9(final ThreadContext context, final String name) {
        return this.runtimeCache.getConstant(context, name, 9);
    }
    
    public final IRubyObject getConstantFrom(final RubyModule target, final ThreadContext context, final String name, final int i) {
        return this.runtimeCache.getConstantFrom(target, context, name, i);
    }
    
    public final IRubyObject getConstantFrom0(final RubyModule target, final ThreadContext context, final String name) {
        return this.runtimeCache.getConstantFrom(target, context, name, 0);
    }
    
    public final IRubyObject getConstantFrom1(final RubyModule target, final ThreadContext context, final String name) {
        return this.runtimeCache.getConstantFrom(target, context, name, 1);
    }
    
    public final IRubyObject getConstantFrom2(final RubyModule target, final ThreadContext context, final String name) {
        return this.runtimeCache.getConstantFrom(target, context, name, 2);
    }
    
    public final IRubyObject getConstantFrom3(final RubyModule target, final ThreadContext context, final String name) {
        return this.runtimeCache.getConstantFrom(target, context, name, 3);
    }
    
    public final IRubyObject getConstantFrom4(final RubyModule target, final ThreadContext context, final String name) {
        return this.runtimeCache.getConstantFrom(target, context, name, 4);
    }
    
    public final IRubyObject getConstantFrom5(final RubyModule target, final ThreadContext context, final String name) {
        return this.runtimeCache.getConstantFrom(target, context, name, 5);
    }
    
    public final IRubyObject getConstantFrom6(final RubyModule target, final ThreadContext context, final String name) {
        return this.runtimeCache.getConstantFrom(target, context, name, 6);
    }
    
    public final IRubyObject getConstantFrom7(final RubyModule target, final ThreadContext context, final String name) {
        return this.runtimeCache.getConstantFrom(target, context, name, 7);
    }
    
    public final IRubyObject getConstantFrom8(final RubyModule target, final ThreadContext context, final String name) {
        return this.runtimeCache.getConstantFrom(target, context, name, 8);
    }
    
    public final IRubyObject getConstantFrom9(final RubyModule target, final ThreadContext context, final String name) {
        return this.runtimeCache.getConstantFrom(target, context, name, 9);
    }
    
    protected DynamicMethod getMethod(final ThreadContext context, final IRubyObject self, final int i, final String methodName) {
        return this.runtimeCache.getMethod(context, self, i, methodName);
    }
    
    protected DynamicMethod getMethod0(final ThreadContext context, final IRubyObject self, final String methodName) {
        return this.runtimeCache.getMethod(context, self, 0, methodName);
    }
    
    protected DynamicMethod getMethod1(final ThreadContext context, final IRubyObject self, final String methodName) {
        return this.runtimeCache.getMethod(context, self, 1, methodName);
    }
    
    protected DynamicMethod getMethod2(final ThreadContext context, final IRubyObject self, final String methodName) {
        return this.runtimeCache.getMethod(context, self, 2, methodName);
    }
    
    protected DynamicMethod getMethod3(final ThreadContext context, final IRubyObject self, final String methodName) {
        return this.runtimeCache.getMethod(context, self, 3, methodName);
    }
    
    protected DynamicMethod getMethod4(final ThreadContext context, final IRubyObject self, final String methodName) {
        return this.runtimeCache.getMethod(context, self, 4, methodName);
    }
    
    protected DynamicMethod getMethod5(final ThreadContext context, final IRubyObject self, final String methodName) {
        return this.runtimeCache.getMethod(context, self, 5, methodName);
    }
    
    protected DynamicMethod getMethod6(final ThreadContext context, final IRubyObject self, final String methodName) {
        return this.runtimeCache.getMethod(context, self, 6, methodName);
    }
    
    protected DynamicMethod getMethod7(final ThreadContext context, final IRubyObject self, final String methodName) {
        return this.runtimeCache.getMethod(context, self, 7, methodName);
    }
    
    protected DynamicMethod getMethod8(final ThreadContext context, final IRubyObject self, final String methodName) {
        return this.runtimeCache.getMethod(context, self, 8, methodName);
    }
    
    protected DynamicMethod getMethod9(final ThreadContext context, final IRubyObject self, final String methodName) {
        return this.runtimeCache.getMethod(context, self, 9, methodName);
    }
    
    public void setByteList(final int index, final String str, final Encoding encoding) {
        final char[] chars = str.toCharArray();
        final byte[] bytes = new byte[chars.length];
        for (int i = 0; i < chars.length; ++i) {
            bytes[i] = (byte)chars[i];
        }
        this.runtimeCache.byteLists[index] = new ByteList(bytes, encoding, false);
    }
    
    public void setEncoding(final int index, final String encStr) {
        this.runtimeCache.encodings[index] = EncodingDB.getEncodings().get(encStr.getBytes()).getEncoding();
    }
    
    public static CallSite[] setCallSite(final CallSite[] callSites, final int index, final String name) {
        callSites[index] = MethodIndex.getCallSite(name);
        return callSites;
    }
    
    public static CallSite[] setFunctionalCallSite(final CallSite[] callSites, final int index, final String name) {
        callSites[index] = MethodIndex.getFunctionalCallSite(name);
        return callSites;
    }
    
    public static CallSite[] setVariableCallSite(final CallSite[] callSites, final int index, final String name) {
        callSites[index] = MethodIndex.getVariableCallSite(name);
        return callSites;
    }
    
    public static CallSite[] setSuperCallSite(final CallSite[] callSites, final int index) {
        callSites[index] = MethodIndex.getSuperCallSite();
        return callSites;
    }
    
    public final void setFilename(final String filename) {
        this.filename = filename;
    }
    
    public final void initFromDescriptor(final String descriptor) {
        this.runtimeCache.initFromDescriptor(descriptor);
    }
}
