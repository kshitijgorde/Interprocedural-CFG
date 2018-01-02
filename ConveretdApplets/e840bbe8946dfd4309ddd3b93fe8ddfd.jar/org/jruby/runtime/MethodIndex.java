// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.runtime.callsite.SuperCallSite;
import org.jruby.runtime.callsite.VariableCachingCallSite;
import org.jruby.runtime.callsite.FunctionalCachingCallSite;
import org.jruby.runtime.callsite.ShiftLeftCallSite;
import org.jruby.runtime.callsite.ShiftRightCallSite;
import org.jruby.runtime.callsite.XorCallSite;
import org.jruby.runtime.callsite.BitOrCallSite;
import org.jruby.runtime.callsite.BitAndCallSite;
import org.jruby.runtime.callsite.CmpCallSite;
import org.jruby.runtime.callsite.EqCallSite;
import org.jruby.runtime.callsite.GeCallSite;
import org.jruby.runtime.callsite.GtCallSite;
import org.jruby.runtime.callsite.LeCallSite;
import org.jruby.runtime.callsite.LtCallSite;
import org.jruby.runtime.callsite.MulCallSite;
import org.jruby.runtime.callsite.MinusCallSite;
import org.jruby.runtime.callsite.PlusCallSite;
import org.jruby.runtime.callsite.NormalCachingCallSite;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.callsite.RespondToCallSite;

public class MethodIndex
{
    public static final int NO_METHOD = 0;
    public static final int OP_EQUAL = 1;
    public static final int EQL = 2;
    public static final int HASH = 3;
    public static final int OP_CMP = 4;
    public static final int MAX_METHODS = 5;
    public static final String[] METHOD_NAMES;
    
    public static CallSite getCallSite(final String name) {
        if (name.equals("respond_to?")) {
            return new RespondToCallSite();
        }
        if (RubyInstanceConfig.FASTOPS_COMPILE_ENABLED && !RubyInstanceConfig.FULL_TRACE_ENABLED) {
            return getFastOpsCallSite(name);
        }
        return new NormalCachingCallSite(name);
    }
    
    public static boolean hasFastOps(final String name) {
        return name.equals("+") || name.equals("-") || name.equals("*") || name.equals("<") || name.equals("<=") || name.equals(">") || name.equals(">=") || name.equals("==") || name.equals("<=>") || name.equals("&") || name.equals("|") || name.equals("^") || name.equals(">>") || name.equals("<<");
    }
    
    public static CallSite getFastOpsCallSite(final String name) {
        if (name.equals("+")) {
            return new PlusCallSite();
        }
        if (name.equals("-")) {
            return new MinusCallSite();
        }
        if (name.equals("*")) {
            return new MulCallSite();
        }
        if (name.equals("<")) {
            return new LtCallSite();
        }
        if (name.equals("<=")) {
            return new LeCallSite();
        }
        if (name.equals(">")) {
            return new GtCallSite();
        }
        if (name.equals(">=")) {
            return new GeCallSite();
        }
        if (name.equals("==")) {
            return new EqCallSite();
        }
        if (name.equals("<=>")) {
            return new CmpCallSite();
        }
        if (name.equals("&")) {
            return new BitAndCallSite();
        }
        if (name.equals("|")) {
            return new BitOrCallSite();
        }
        if (name.equals("^")) {
            return new XorCallSite();
        }
        if (name.equals(">>")) {
            return new ShiftRightCallSite();
        }
        if (name.equals("<<")) {
            return new ShiftLeftCallSite();
        }
        return new NormalCachingCallSite(name);
    }
    
    public static CallSite getFunctionalCallSite(final String name) {
        return new FunctionalCachingCallSite(name);
    }
    
    public static CallSite getVariableCallSite(final String name) {
        return new VariableCachingCallSite(name);
    }
    
    public static CallSite getSuperCallSite() {
        return new SuperCallSite();
    }
    
    static {
        METHOD_NAMES = new String[] { "", "==", "eql?", "hash", "<=>" };
    }
}
