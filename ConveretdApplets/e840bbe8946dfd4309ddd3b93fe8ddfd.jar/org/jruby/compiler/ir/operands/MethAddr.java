// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.interpreter.InterpreterContext;

public class MethAddr extends Reference
{
    public static final MethAddr GVAR_ALIAS;
    public static final MethAddr DEFINE_ALIAS;
    public static final MethAddr RETRIEVE_CONSTANT;
    public static final MethAddr FOR_EACH;
    public static final MethAddr SUPER;
    public static final MethAddr ZSUPER;
    public static final MethAddr MATCH;
    public static final MethAddr MATCH2;
    public static final MethAddr MATCH3;
    public static final MethAddr TO_ARY;
    public static final MethAddr GET_FILE_NAME;
    public static final MethAddr UNDEF_METHOD;
    public static final MethAddr SET_WITHIN_DEFINED;
    
    public MethAddr(final String name) {
        super(name);
    }
    
    public Object retrieve(final InterpreterContext interp) {
        return this.getName();
    }
    
    public boolean equals(final Object o) {
        return o instanceof MethAddr && ((MethAddr)o).getName().equals(this.getName());
    }
    
    static {
        GVAR_ALIAS = new MethAddr("aliasGlobalVariable");
        DEFINE_ALIAS = new MethAddr("defineAlias");
        RETRIEVE_CONSTANT = new MethAddr("retrieveConstant");
        FOR_EACH = new MethAddr("each");
        SUPER = new MethAddr("super");
        ZSUPER = new MethAddr("super");
        MATCH = new MethAddr("op_match2");
        MATCH2 = new MethAddr("op_match");
        MATCH3 = new MethAddr("match3");
        TO_ARY = new MethAddr("aryToAry");
        GET_FILE_NAME = new MethAddr("getFileName");
        UNDEF_METHOD = new MethAddr("undefMethod");
        SET_WITHIN_DEFINED = new MethAddr("setWithinDefined");
    }
}
