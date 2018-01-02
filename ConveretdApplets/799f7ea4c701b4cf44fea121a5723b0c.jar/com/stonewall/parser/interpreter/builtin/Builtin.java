// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import com.stonewall.parser.interpreter.Interpreter;
import com.stonewall.parser.Function;
import org.xmodel.log.Log;
import com.stonewall.parser.interpreter.Dispatcher;

public class Builtin extends Dispatcher
{
    static Log log;
    
    static {
        Builtin.log = Log.getLog(Builtin.class);
    }
    
    public Builtin() {
        this.register("not", new Negate());
        this.register("length", new Length());
        this.register("value", new Value());
        this.register("upper", new Upper());
        this.register("lower", new Lower());
        this.register("trim", new Trim());
        this.register("substring", new Substring());
        this.register("split", new Split());
        this.register("subscript", new Subscript());
        this.register("contained", new Contained());
        this.register("concat", new Concat());
        this.register("xpath", new EvXPath());
        this.register("log", new com.stonewall.parser.interpreter.builtin.Log());
        this.register("hash", new Hash());
        this.register("seq", new Sequence());
        this.register("ip", new Ip());
        this.register("decode", new Decode());
        this.register("dict", new Dict());
    }
    
    @Override
    public String dispatch(final Interpreter interpreter, final String[] args) {
        final BuiltinContext ctx = new BuiltinContext(this, interpreter);
        return super.dispatch(ctx, args);
    }
}
