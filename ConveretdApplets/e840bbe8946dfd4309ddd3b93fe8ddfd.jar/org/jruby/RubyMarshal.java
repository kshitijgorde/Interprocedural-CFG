// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.marshal.MarshalStream;
import org.jruby.util.IOInputStream;
import java.io.InputStream;
import java.io.EOFException;
import org.jruby.runtime.marshal.UnmarshalStream;
import java.io.ByteArrayInputStream;
import org.jruby.util.IOOutputStream;
import org.jruby.runtime.ThreadContext;
import org.jruby.anno.JRubyMethod;
import java.io.IOException;
import org.jruby.util.ByteList;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "Marshal" })
public class RubyMarshal
{
    public static RubyModule createMarshalModule(final Ruby runtime) {
        final RubyModule module = runtime.defineModule("Marshal");
        runtime.setMarshal(module);
        module.defineAnnotatedMethods(RubyMarshal.class);
        module.defineConstant("MAJOR_VERSION", runtime.newFixnum(4));
        module.defineConstant("MINOR_VERSION", runtime.newFixnum(8));
        return module;
    }
    
    @JRubyMethod(required = 1, optional = 2, module = true)
    public static IRubyObject dump(final IRubyObject recv, final IRubyObject[] args, final Block unusedBlock) {
        final Ruby runtime = recv.getRuntime();
        final IRubyObject objectToDump = args[0];
        IRubyObject io = null;
        int depthLimit = -1;
        if (args.length >= 2) {
            if (args[1].respondsTo("write")) {
                io = args[1];
            }
            else {
                if (!(args[1] instanceof RubyFixnum)) {
                    throw runtime.newTypeError("Instance of IO needed");
                }
                depthLimit = (int)((RubyFixnum)args[1]).getLongValue();
            }
            if (args.length == 3) {
                depthLimit = (int)args[2].convertToInteger().getLongValue();
            }
        }
        try {
            if (io != null) {
                dumpToStream(runtime, objectToDump, outputStream(runtime.getCurrentContext(), io), depthLimit);
                return io;
            }
            final ByteArrayOutputStream stringOutput = new ByteArrayOutputStream();
            final boolean[] taintUntrust = dumpToStream(runtime, objectToDump, stringOutput, depthLimit);
            final RubyString result = RubyString.newString(runtime, new ByteList(stringOutput.toByteArray()));
            if (taintUntrust[0]) {
                result.setTaint(true);
            }
            if (taintUntrust[1]) {
                result.setUntrusted(true);
            }
            return result;
        }
        catch (IOException ioe) {
            throw runtime.newIOErrorFromException(ioe);
        }
    }
    
    private static OutputStream outputStream(final ThreadContext context, final IRubyObject out) {
        setBinmodeIfPossible(context, out);
        return new IOOutputStream(out);
    }
    
    private static void setBinmodeIfPossible(final ThreadContext context, final IRubyObject io) {
        if (io.respondsTo("binmode")) {
            io.callMethod(context, "binmode");
        }
    }
    
    @JRubyMethod(name = { "load", "restore" }, required = 1, optional = 1, module = true)
    public static IRubyObject load(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block unusedBlock) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject in = args[0];
        final IRubyObject proc = (args.length == 2) ? args[1] : null;
        try {
            final IRubyObject v = in.checkStringType();
            boolean tainted;
            boolean untrusted;
            InputStream rawInput;
            if (!v.isNil()) {
                tainted = in.isTaint();
                untrusted = in.isUntrusted();
                final ByteList bytes = ((RubyString)v).getByteList();
                rawInput = new ByteArrayInputStream(bytes.getUnsafeBytes(), bytes.begin(), bytes.length());
            }
            else {
                if (!in.respondsTo("getc") || !in.respondsTo("read")) {
                    throw runtime.newTypeError("instance of IO needed");
                }
                tainted = true;
                untrusted = true;
                rawInput = inputStream(context, in);
            }
            return new UnmarshalStream(runtime, rawInput, proc, tainted, untrusted).unmarshalObject();
        }
        catch (EOFException e) {
            if (in.respondsTo("to_str")) {
                throw runtime.newArgumentError("marshal data too short");
            }
            throw runtime.newEOFError();
        }
        catch (IOException ioe) {
            throw runtime.newIOErrorFromException(ioe);
        }
    }
    
    private static InputStream inputStream(final ThreadContext context, final IRubyObject in) {
        setBinmodeIfPossible(context, in);
        return new IOInputStream(in);
    }
    
    private static boolean[] dumpToStream(final Ruby runtime, final IRubyObject object, final OutputStream rawOutput, final int depthLimit) throws IOException {
        final MarshalStream output = new MarshalStream(runtime, rawOutput, depthLimit);
        output.dumpObject(object);
        return new boolean[] { output.isTainted(), output.isUntrusted() };
    }
}
