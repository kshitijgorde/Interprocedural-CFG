// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir;

import org.jruby.compiler.ir.operands.MetaObject;
import org.jruby.parser.StaticScope;
import org.jruby.compiler.ir.operands.Operand;

public class IRMetaClass extends IRClass
{
    static IRMetaClass CLASS_METACLASS;
    
    public IRMetaClass(final IRScope s, final Operand receiver, final StaticScope staticScope) {
        super(s, null, MetaObject.create(IRMetaClass.CLASS_METACLASS), "<FIXME>", staticScope);
    }
    
    public String getScopeName() {
        return "MetaClass";
    }
}
