// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import java.util.HashMap;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import org.jruby.ast.ArgsNode;
import org.jruby.ast.ArrayNode;
import org.jruby.ast.CallNode;
import org.jruby.ast.types.IArityNode;
import org.jruby.ast.AttrAssignNode;
import org.jruby.ast.Node;
import org.jruby.anno.JRubyMethod;
import java.util.Map;
import java.io.Serializable;

public final class Arity implements Serializable
{
    private static final long serialVersionUID = 1L;
    private static final Map<Integer, Arity> arities;
    private final int value;
    public static final Arity NO_ARGUMENTS;
    public static final Arity ONE_ARGUMENT;
    public static final Arity TWO_ARGUMENTS;
    public static final Arity THREE_ARGUMENTS;
    public static final Arity OPTIONAL;
    public static final Arity ONE_REQUIRED;
    public static final Arity TWO_REQUIRED;
    public static final Arity THREE_REQUIRED;
    
    private Arity(final int value) {
        this.value = value;
    }
    
    public static Arity createArity(final int value) {
        switch (value) {
            case -4: {
                return Arity.THREE_REQUIRED;
            }
            case -3: {
                return Arity.TWO_REQUIRED;
            }
            case -2: {
                return Arity.ONE_REQUIRED;
            }
            case -1: {
                return Arity.OPTIONAL;
            }
            case 0: {
                return Arity.NO_ARGUMENTS;
            }
            case 1: {
                return Arity.ONE_ARGUMENT;
            }
            case 2: {
                return Arity.TWO_ARGUMENTS;
            }
            case 3: {
                return Arity.THREE_ARGUMENTS;
            }
            default: {
                return newArity(value);
            }
        }
    }
    
    public static Arity fromAnnotation(final JRubyMethod anno) {
        if (anno.optional() > 0 || anno.rest()) {
            return createArity(-(anno.required() + 1));
        }
        return createArity(anno.required());
    }
    
    public static Arity fromAnnotation(final JRubyMethod anno, final int actualRequired) {
        if (anno.optional() > 0 || anno.rest()) {
            return createArity(-(actualRequired + 1));
        }
        return createArity(actualRequired);
    }
    
    public static Arity fromAnnotation(final JRubyMethod anno, final Class[] parameterTypes, final boolean isStatic) {
        int required = 0;
        if (anno.optional() == 0 && !anno.rest() && anno.required() == 0) {
            int i = parameterTypes.length;
            if (isStatic) {
                --i;
            }
            if (parameterTypes.length > 0) {
                if (parameterTypes[0] == ThreadContext.class) {
                    --i;
                }
                if (parameterTypes[parameterTypes.length - 1] == Block.class) {
                    --i;
                }
            }
            required = i;
        }
        else {
            required = anno.required();
        }
        if (anno.optional() > 0 || anno.rest()) {
            return createArity(-(required + 1));
        }
        return createArity(required);
    }
    
    private static Arity newArity(final int value) {
        Arity result;
        synchronized (Arity.arities) {
            result = Arity.arities.get(value);
            if (result == null) {
                result = new Arity(value);
                Arity.arities.put(value, result);
            }
        }
        return result;
    }
    
    public static Arity fixed(final int arity) {
        assert arity >= 0;
        return createArity(arity);
    }
    
    public static Arity optional() {
        return Arity.OPTIONAL;
    }
    
    public static Arity required(final int minimum) {
        assert minimum >= 0;
        return createArity(-(1 + minimum));
    }
    
    public static Arity noArguments() {
        return Arity.NO_ARGUMENTS;
    }
    
    public static Arity singleArgument() {
        return Arity.ONE_ARGUMENT;
    }
    
    public static Arity twoArguments() {
        return Arity.TWO_ARGUMENTS;
    }
    
    public static Arity procArityOf(Node node) {
        if (node instanceof AttrAssignNode && node != null) {
            node = ((AttrAssignNode)node).getArgsNode();
        }
        if (node == null) {
            return optional();
        }
        if (node instanceof IArityNode) {
            return ((IArityNode)node).getArity();
        }
        if (node instanceof CallNode) {
            return singleArgument();
        }
        if (node instanceof ArrayNode) {
            return singleArgument();
        }
        if (node instanceof ArgsNode) {
            return ((ArgsNode)node).getArity();
        }
        throw new Error("unexpected type " + node.getClass() + " at " + node.getPosition());
    }
    
    public int getValue() {
        return this.value;
    }
    
    public void checkArity(final Ruby runtime, final IRubyObject[] args) {
        if (this.isFixed()) {
            if (args.length != this.required()) {
                throw runtime.newArgumentError("wrong number of arguments (" + args.length + " for " + this.required() + ")");
            }
        }
        else if (args.length < this.required()) {
            throw runtime.newArgumentError("wrong number of arguments (" + args.length + " for " + this.required() + ")");
        }
    }
    
    public void checkArity(final Ruby runtime, final int length) {
        if (this.isFixed()) {
            if (length != this.required()) {
                throw runtime.newArgumentError("wrong number of arguments (" + length + " for " + this.required() + ")");
            }
        }
        else if (length < this.required()) {
            throw runtime.newArgumentError("wrong number of arguments (" + length + " for " + this.required() + ")");
        }
    }
    
    public boolean isFixed() {
        return this.value >= 0;
    }
    
    public int required() {
        if (this.value < 0) {
            return -(1 + this.value);
        }
        return this.value;
    }
    
    public boolean equals(final Object other) {
        return this == other;
    }
    
    public int hashCode() {
        return this.value;
    }
    
    public String toString() {
        if (this.isFixed()) {
            return "Fixed" + this.required();
        }
        return "Opt";
    }
    
    public static int checkArgumentCount(final Ruby runtime, final IRubyObject[] args, final int min, final int max) {
        return checkArgumentCount(runtime, args.length, min, max);
    }
    
    public static void raiseArgumentError(final Ruby runtime, final IRubyObject[] args, final int min, final int max) {
        raiseArgumentError(runtime, args.length, min, max);
    }
    
    public static int checkArgumentCount(final Ruby runtime, final int length, final int min, final int max) {
        int expected = 0;
        if (length < min) {
            expected = min;
        }
        else {
            if (max <= -1 || length <= max) {
                return length;
            }
            expected = max;
        }
        throw runtime.newArgumentError(length, expected);
    }
    
    public static void raiseArgumentError(final Ruby runtime, final int length, final int min, final int max) {
        if (length < min) {
            throw runtime.newArgumentError(length, min);
        }
        if (max > -1 && length > max) {
            throw runtime.newArgumentError(length, max);
        }
    }
    
    public static IRubyObject[] scanArgs(final Ruby runtime, final IRubyObject[] args, final int required, final int optional) {
        final int total = required + optional;
        final int real = checkArgumentCount(runtime, args, required, total);
        final IRubyObject[] narr = new IRubyObject[total];
        System.arraycopy(args, 0, narr, 0, real);
        for (int i = real; i < total; ++i) {
            narr[i] = runtime.getNil();
        }
        return narr;
    }
    
    static {
        arities = new HashMap<Integer, Arity>();
        NO_ARGUMENTS = newArity(0);
        ONE_ARGUMENT = newArity(1);
        TWO_ARGUMENTS = newArity(2);
        THREE_ARGUMENTS = newArity(3);
        OPTIONAL = newArity(-1);
        ONE_REQUIRED = newArity(-2);
        TWO_REQUIRED = newArity(-3);
        THREE_REQUIRED = newArity(-4);
    }
}
