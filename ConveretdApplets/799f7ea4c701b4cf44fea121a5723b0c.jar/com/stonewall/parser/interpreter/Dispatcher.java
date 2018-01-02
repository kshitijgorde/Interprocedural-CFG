// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter;

import java.lang.reflect.InvocationTargetException;
import org.w3c.dom.ranges.RangeException;
import java.util.HashMap;
import com.stonewall.parser.interpreter.builtin.Builtin;
import org.xmodel.log.Log;
import com.stonewall.parser.Function;
import java.util.Map;

public class Dispatcher
{
    private Dispatcher child;
    private Map<String, Function> functions;
    static Log log;
    
    static {
        Dispatcher.log = Log.getLog(Builtin.class);
    }
    
    public Dispatcher() {
        this.child = null;
        this.functions = new HashMap<String, Function>();
    }
    
    public void register(final String name, final Function f) {
        this.functions.put(name, f);
    }
    
    public void attach(final Dispatcher child) {
        this.child = child;
    }
    
    public String dispatch(final Interpreter intr, final String[] args) {
        return this.dispatch(new Function.Context(this, intr), args);
    }
    
    protected String dispatch(final Function.Context ctx, final String[] args) {
        String result = "[?]";
        try {
            final String[] call = args[0].split("\\.");
            result = this.resolve(call[0]).execute(ctx, args);
        }
        catch (RangeException re) {
            result = "function [" + args[0] + "(), missing required arguments]";
            ctx.raiseEvent(Event.Severity.error, result);
        }
        catch (StringIndexOutOfBoundsException ie) {
            result = "function [" + args[0] + "(), invalid subscript/substring]";
            ctx.raiseEvent(Event.Severity.error, result);
        }
        catch (UnsupportedOperationException uoe) {
            result = "function [" + args[0] + "(), not-supported]";
            ctx.raiseEvent(Event.Severity.error, result);
        }
        catch (InvocationTargetException ite) {
            final Throwable te = ite.getTargetException();
            result = "function [" + args[0] + "(), failed, " + te.getMessage() + "]";
            final String msg = this.dispatchString(args, result);
            ctx.raiseEvent(Event.Severity.error, msg, te);
        }
        catch (IllegalAccessException iae) {
            result = "function [" + args[0] + "(), permission-denied]";
            ctx.raiseEvent(Event.Severity.error, result);
        }
        catch (Exception e) {
            result = "function [" + args[0] + "(), failed," + e.getMessage() + "]";
            final String msg2 = this.dispatchString(args, result);
            ctx.raiseEvent(Event.Severity.error, msg2, e);
        }
        Dispatcher.log.debug(this.dispatchString(args, result));
        return result;
    }
    
    public Function resolve(final String fn) {
        Function result = null;
        try {
            result = this.child.resolve(fn);
        }
        catch (Exception e) {
            result = this.functions.get(fn);
        }
        if (result == null) {
            throw new UnsupportedOperationException(fn);
        }
        return result;
    }
    
    protected void raised(final Exception e, final String result) {
        Dispatcher.log.error(result, e);
    }
    
    String dispatchString(final String[] args, final String result) {
        final StringBuilder sb = new StringBuilder();
        sb.append("function called: ");
        sb.append(args[0]);
        sb.append("(");
        for (int i = 1; i < args.length; ++i) {
            sb.append(args[i]);
            sb.append(", ");
        }
        sb.append(") result \"");
        sb.append(result);
        sb.append('\"');
        return sb.toString();
    }
}
