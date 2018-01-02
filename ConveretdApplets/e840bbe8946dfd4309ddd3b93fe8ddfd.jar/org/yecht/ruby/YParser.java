// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.RubyClass;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyNumeric;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyHash;
import org.yecht.BadAnchorHandler;
import org.yecht.ErrorHandler;
import org.yecht.ParserInput;
import org.jruby.RubyObject;
import org.jruby.RubyModule;
import org.yecht.NodeHandler;
import org.jruby.util.ByteList;
import org.yecht.IoStrRead;
import org.yecht.Pointer;
import org.jruby.RubyString;
import org.jruby.runtime.builtin.IRubyObject;
import org.yecht.Parser;
import org.jruby.Ruby;
import org.jruby.runtime.ObjectAllocator;

public class YParser
{
    public static final ObjectAllocator Allocator;
    
    public static boolean assignIO(final Ruby runtime, final Parser parser, final IRubyObject[] pport) {
        boolean taint = true;
        IRubyObject port = pport[0];
        final IRubyObject tmp;
        if (!(tmp = port.checkStringType()).isNil()) {
            taint = port.isTaint();
            port = tmp;
            final ByteList bl = ((RubyString)port).getByteList();
            parser.str(Pointer.create(bl.bytes, bl.begin), bl.realSize, null);
        }
        else {
            if (!port.respondsTo("read")) {
                throw runtime.newTypeError("instance of IO needed");
            }
            if (port.respondsTo("binmode")) {
                port.callMethod(runtime.getCurrentContext(), "binmode");
            }
            parser.str(Pointer.empty(), 0, new RubyIoStrRead(port));
        }
        pport[0] = port;
        return taint;
    }
    
    public static void setModel(final IRubyObject p, IRubyObject input, final IRubyObject model) {
        final Ruby runtime = p.getRuntime();
        final Parser parser = (Parser)p.dataGetStructChecked();
        parser.handler(new RubyLoadHandler(runtime, (YAMLExtra)runtime.getModule("YAML").dataGetStruct()));
        if (model == runtime.newSymbol("Generic")) {
            p.callMethod(runtime.getCurrentContext(), "set_resolver", ((RubyModule)runtime.getModule("YAML").getConstant("Yecht")).getConstant("GenericResolver"));
        }
        parser.implicitTyping(true);
        parser.taguriExpansion(true);
        if (input.isNil()) {
            input = ((RubyObject)p).fastGetInstanceVariable("@input");
        }
        if (input == runtime.newSymbol("bytecode")) {
            parser.setInputType(ParserInput.Bytecode_UTF8);
        }
        else {
            parser.setInputType(ParserInput.YAML_UTF8);
        }
        parser.errorHandler(new RubyErrHandler(runtime));
        parser.badAnchorHandler(new RubyBadAnchorHandler(runtime));
    }
    
    @JRubyMethod(optional = 1)
    public static IRubyObject initialize(final IRubyObject self, final IRubyObject[] args) {
        IRubyObject options = null;
        if (args.length == 0) {
            options = RubyHash.newHash(self.getRuntime());
        }
        else {
            options = args[0].convertToHash();
        }
        ((RubyObject)self).fastSetInstanceVariable("@options", options);
        ((RubyObject)self).fastSetInstanceVariable("@input", self.getRuntime().getNil());
        ((RubyObject)self).fastSetInstanceVariable("@resolver", self.getRuntime().getNil());
        return self;
    }
    
    @JRubyMethod(name = { "bufsize=" })
    public static IRubyObject bufsize_set(final IRubyObject self, final IRubyObject size) {
        if (size.respondsTo("to_i")) {
            final int n = RubyNumeric.fix2int(size.callMethod(self.getRuntime().getCurrentContext(), "to_i"));
            final Parser p = (Parser)self.dataGetStructChecked();
            p.bufsize = n;
        }
        return self;
    }
    
    @JRubyMethod
    public static IRubyObject bufsize(final IRubyObject self) {
        final Parser p = (Parser)self.dataGetStructChecked();
        return self.getRuntime().newFixnum(p.bufsize);
    }
    
    @JRubyMethod(required = 1, optional = 1)
    public static IRubyObject load(final IRubyObject self, final IRubyObject[] args) {
        final Ruby runtime = self.getRuntime();
        final ThreadContext ctx = runtime.getCurrentContext();
        final IRubyObject port = args[0];
        IRubyObject proc = null;
        if (args.length > 1) {
            proc = args[1];
        }
        else {
            proc = runtime.getNil();
        }
        final IRubyObject input = ((RubyHash)self.callMethod(ctx, "options")).op_aref(ctx, runtime.newSymbol("input"));
        final IRubyObject model = ((RubyHash)self.callMethod(ctx, "options")).op_aref(ctx, runtime.newSymbol("Model"));
        final Parser parser = (Parser)self.dataGetStructChecked();
        setModel(self, input, model);
        final Extra bonus = (Extra)parser.bonus;
        bonus.taint = assignIO(runtime, parser, new IRubyObject[] { port });
        parser.setRootOnError(runtime.getNil());
        bonus.data = RubyHash.newHash(runtime);
        bonus.resolver = self.callMethod(ctx, "resolver");
        if (proc.isNil()) {
            bonus.proc = null;
        }
        else {
            bonus.proc = proc;
        }
        IRubyObject result = (IRubyObject)parser.parse();
        if (result == null) {
            result = runtime.getFalse();
        }
        return result;
    }
    
    @JRubyMethod(frame = true)
    public static IRubyObject load_documents(final IRubyObject self, final IRubyObject port, final Block proc) {
        final Ruby runtime = self.getRuntime();
        final ThreadContext ctx = runtime.getCurrentContext();
        final IRubyObject input = ((RubyHash)self.callMethod(ctx, "options")).op_aref(ctx, runtime.newSymbol("input"));
        final IRubyObject model = ((RubyHash)self.callMethod(ctx, "options")).op_aref(ctx, runtime.newSymbol("Model"));
        final Parser parser = (Parser)self.dataGetStructChecked();
        setModel(self, input, model);
        final Extra bonus = (Extra)parser.bonus;
        bonus.taint = assignIO(runtime, parser, new IRubyObject[] { port });
        parser.setRootOnError(runtime.getNil());
        bonus.resolver = self.callMethod(ctx, "resolver");
        bonus.proc = null;
        while (true) {
            bonus.data = RubyHash.newHash(runtime);
            IRubyObject v = (IRubyObject)parser.parse();
            if (parser.eof) {
                break;
            }
            if (v == null) {
                v = runtime.getFalse();
            }
            proc.yield(ctx, v);
        }
        return runtime.getNil();
    }
    
    @JRubyMethod
    public static IRubyObject set_resolver(final IRubyObject self, final IRubyObject resolver) {
        ((RubyObject)self).fastSetInstanceVariable("@resolver", resolver);
        return self;
    }
    
    static {
        Allocator = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                final Parser parser = Parser.newParser();
                parser.bonus = new Extra();
                final IRubyObject pobj = runtime.newData(klass, parser);
                parser.setRootOnError(runtime.getNil());
                return pobj;
            }
        };
    }
    
    public static class Extra
    {
        public IRubyObject data;
        public IRubyObject proc;
        public IRubyObject resolver;
        public boolean taint;
    }
}
