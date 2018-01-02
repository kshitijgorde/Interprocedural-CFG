// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir;

import org.jruby.compiler.ir.operands.LocalVariable;
import java.util.ArrayList;
import java.util.Iterator;
import org.jruby.compiler.ir.compiler_pass.CompilerPass;
import java.util.Collections;
import org.jruby.compiler.ir.instructions.Instr;
import org.jruby.compiler.ir.instructions.ReceiveSelfInstruction;
import org.jruby.parser.LocalStaticScope;
import org.jruby.compiler.ir.operands.MetaObject;
import java.util.HashMap;
import org.jruby.parser.StaticScope;
import org.jruby.compiler.ir.operands.Operand;
import java.util.List;
import java.util.Map;

public class IRModule extends IRScopeImpl
{
    private static final String ROOT_METHOD_PREFIX = "<ROOT>";
    private static Map<String, IRClass> coreClasses;
    private IRMethod rootMethod;
    private CodeVersion version;
    private List<IRModule> modules;
    private List<IRClass> classes;
    private List<IRMethod> methods;
    private Map<String, Operand> constants;
    
    private static IRClass addCoreClass(final String name, final IRScope parent, final String[] coreMethods, final StaticScope staticScope) {
        final IRClass c = new IRClass(parent, null, null, name, staticScope);
        IRModule.coreClasses.put(c.getName(), c);
        if (coreMethods != null) {
            for (final String m : coreMethods) {
                final IRMethod meth = new IRMethod(c, null, m, true, null);
                meth.setCodeModificationFlag(false);
                c.addMethod(meth);
            }
        }
        return c;
    }
    
    public static void bootStrap() {
        IRModule.coreClasses = new HashMap<String, IRClass>();
        final IRClass obj = addCoreClass("Object", null, null, null);
        addCoreClass("Class", addCoreClass("Module", obj, null, null), null, null);
        addCoreClass("Fixnum", obj, new String[] { "+", "-", "/", "*" }, null);
        addCoreClass("Float", obj, new String[] { "+", "-", "/", "*" }, null);
        addCoreClass("Array", obj, new String[] { "[]", "each", "inject" }, null);
        addCoreClass("Range", obj, new String[] { "each" }, null);
        addCoreClass("Hash", obj, new String[] { "each" }, null);
        addCoreClass("String", obj, null, null);
        addCoreClass("Proc", obj, null, null);
    }
    
    public static IRClass getCoreClass(final String n) {
        return IRModule.coreClasses.get(n);
    }
    
    public static boolean isAClassRootMethod(final IRMethod m) {
        return m.getName().startsWith("<ROOT>");
    }
    
    private void addRootMethod() {
        final String n = "<ROOT>" + this.getName();
        (this.rootMethod = new IRMethod(this, MetaObject.create(this), n, false, new LocalStaticScope(null))).addInstr(new ReceiveSelfInstruction(this.rootMethod.getSelf()));
    }
    
    public List<IRModule> getModules() {
        return this.modules;
    }
    
    public List<IRClass> getClasses() {
        return this.classes;
    }
    
    public List<IRMethod> getMethods() {
        return this.methods;
    }
    
    public Map getConstants() {
        return Collections.unmodifiableMap((Map<?, ?>)this.constants);
    }
    
    public Operand getConstantValue(final String constRef) {
        return null;
    }
    
    public void setConstantValue(final String constRef, final Operand val) {
        if (val.isConstant()) {
            this.constants.put(constRef, val);
        }
    }
    
    public void addModule(final IRModule m) {
        this.modules.add(m);
    }
    
    public void addClass(final IRClass c) {
        this.classes.add(c);
    }
    
    public void addMethod(final IRMethod method) {
        assert !isAClassRootMethod(method);
        this.methods.add(method);
    }
    
    public void runCompilerPassOnNestedScopes(final CompilerPass p) {
        for (final IRScope m : this.modules) {
            m.runCompilerPass(p);
        }
        for (final IRScope c : this.classes) {
            c.runCompilerPass(p);
        }
        this.getRootMethod().runCompilerPass(p);
        for (final IRScope meth : this.methods) {
            meth.runCompilerPass(p);
        }
    }
    
    public IRModule getNearestModule() {
        return this;
    }
    
    public IRModule(final IRScope lexicalParent, final Operand container, final String name, final StaticScope scope) {
        super(lexicalParent, container, name, scope);
        this.modules = new ArrayList<IRModule>();
        this.classes = new ArrayList<IRClass>();
        this.methods = new ArrayList<IRMethod>();
        this.constants = new HashMap<String, Operand>();
        this.addRootMethod();
        this.updateVersion();
    }
    
    public void updateVersion() {
        this.version = CodeVersion.getClassVersionToken();
    }
    
    public String getScopeName() {
        return "Module";
    }
    
    public CodeVersion getVersion() {
        return this.version;
    }
    
    public IRMethod getRootMethod() {
        return this.rootMethod;
    }
    
    public IRMethod getInstanceMethod(final String name) {
        for (final IRMethod m : this.methods) {
            if (m.isInstanceMethod && m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }
    
    public IRMethod getClassMethod(final String name) {
        for (final IRMethod m : this.methods) {
            if (!m.isInstanceMethod && this.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }
    
    public boolean isCoreClass(final String className) {
        return this == getCoreClass(className);
    }
    
    public LocalVariable getLocalVariable(final String name) {
        throw new UnsupportedOperationException("This should be happening in the root method of this module/class instead");
    }
    
    static {
        bootStrap();
    }
}
