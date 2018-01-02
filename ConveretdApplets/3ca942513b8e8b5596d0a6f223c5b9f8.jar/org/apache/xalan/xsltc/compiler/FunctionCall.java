// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.INVOKESPECIAL;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.NEW;
import com.ibm.xslt4j.bcel.generic.LocalVariableGen;
import com.ibm.xslt4j.bcel.generic.CompoundInstruction;
import com.ibm.xslt4j.bcel.generic.PUSH;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.INVOKESTATIC;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.BranchInstruction;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import com.ibm.xslt4j.bcel.generic.IFEQ;
import org.apache.xalan.xsltc.compiler.util.IntType;
import org.apache.xalan.xsltc.compiler.util.BooleanType;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import java.util.Enumeration;
import java.lang.reflect.Modifier;
import org.apache.xalan.xsltc.compiler.util.ReferenceType;
import org.apache.xalan.xsltc.compiler.util.ObjectType;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.Hashtable;
import org.apache.xalan.xsltc.compiler.util.MultiHashtable;
import org.apache.xalan.xsltc.compiler.util.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Vector;

class FunctionCall extends Expression
{
    private QName _fname;
    private final Vector _arguments;
    private static final Vector EMPTY_ARG_LIST;
    protected static final String EXT_XSLTC = "http://xml.apache.org/xalan/xsltc";
    protected static final String JAVA_EXT_XSLTC = "http://xml.apache.org/xalan/xsltc/java";
    protected static final String EXT_XALAN = "http://xml.apache.org/xalan";
    protected static final String JAVA_EXT_XALAN = "http://xml.apache.org/xalan/java";
    protected static final String JAVA_EXT_XALAN_OLD = "http://xml.apache.org/xslt/java";
    protected static final String EXSLT_COMMON = "http://exslt.org/common";
    protected static final String EXSLT_MATH = "http://exslt.org/math";
    protected static final String EXSLT_SETS = "http://exslt.org/sets";
    protected static final String EXSLT_DATETIME = "http://exslt.org/dates-and-times";
    protected static final String EXSLT_STRINGS = "http://exslt.org/strings";
    protected static final int NAMESPACE_FORMAT_JAVA = 0;
    protected static final int NAMESPACE_FORMAT_CLASS = 1;
    protected static final int NAMESPACE_FORMAT_PACKAGE = 2;
    protected static final int NAMESPACE_FORMAT_CLASS_OR_PACKAGE = 3;
    private int _namespace_format;
    Expression _thisArgument;
    private String _className;
    private Class _clazz;
    private Method _chosenMethod;
    private Constructor _chosenConstructor;
    private MethodType _chosenMethodType;
    private boolean unresolvedExternal;
    private boolean _isExtConstructor;
    private boolean _isStatic;
    private static final MultiHashtable _internal2Java;
    private static final Hashtable _java2Internal;
    private static final Hashtable _extensionNamespaceTable;
    private static final Hashtable _extensionFunctionTable;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$String;
    
    public FunctionCall(final QName fname, final Vector arguments) {
        this._namespace_format = 0;
        this._thisArgument = null;
        this._isExtConstructor = false;
        this._isStatic = false;
        this._fname = fname;
        this._arguments = arguments;
        super._type = null;
    }
    
    public FunctionCall(final QName fname) {
        this(fname, FunctionCall.EMPTY_ARG_LIST);
    }
    
    public String getName() {
        return this._fname.toString();
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        if (this._arguments != null) {
            for (int n = this._arguments.size(), i = 0; i < n; ++i) {
                final Expression exp = this._arguments.elementAt(i);
                exp.setParser(parser);
                exp.setParent(this);
            }
        }
    }
    
    public String getClassNameFromUri(final String uri) {
        final String className = FunctionCall._extensionNamespaceTable.get(uri);
        if (className != null) {
            return className;
        }
        if (uri.startsWith("http://xml.apache.org/xalan/xsltc/java")) {
            final int length = "http://xml.apache.org/xalan/xsltc/java".length() + 1;
            return (uri.length() > length) ? uri.substring(length) : "";
        }
        if (uri.startsWith("http://xml.apache.org/xalan/java")) {
            final int length = "http://xml.apache.org/xalan/java".length() + 1;
            return (uri.length() > length) ? uri.substring(length) : "";
        }
        if (uri.startsWith("http://xml.apache.org/xslt/java")) {
            final int length = "http://xml.apache.org/xslt/java".length() + 1;
            return (uri.length() > length) ? uri.substring(length) : "";
        }
        final int index = uri.lastIndexOf(47);
        return (index > 0) ? uri.substring(index + 1) : uri;
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (super._type != null) {
            return super._type;
        }
        final String namespace = this._fname.getNamespace();
        String local = this._fname.getLocalPart();
        if (this.isExtension()) {
            this._fname = new QName(null, null, local);
            return this.typeCheckStandard(stable);
        }
        if (this.isStandard()) {
            return this.typeCheckStandard(stable);
        }
        try {
            this._className = this.getClassNameFromUri(namespace);
            final int pos = local.lastIndexOf(46);
            if (pos > 0) {
                this._isStatic = true;
                if (this._className != null && this._className.length() > 0) {
                    this._namespace_format = 2;
                    this._className = this._className + "." + local.substring(0, pos);
                }
                else {
                    this._namespace_format = 0;
                    this._className = local.substring(0, pos);
                }
                this._fname = new QName(namespace, null, local.substring(pos + 1));
            }
            else {
                if (this._className != null && this._className.length() > 0) {
                    try {
                        this._clazz = ObjectFactory.findProviderClass(this._className, ObjectFactory.findClassLoader(), true);
                        this._namespace_format = 1;
                    }
                    catch (ClassNotFoundException e2) {
                        this._namespace_format = 2;
                    }
                }
                else {
                    this._namespace_format = 0;
                }
                if (local.indexOf(45) > 0) {
                    local = replaceDash(local);
                }
                final String extFunction = FunctionCall._extensionFunctionTable.get(namespace + ":" + local);
                if (extFunction != null) {
                    this._fname = new QName(null, null, extFunction);
                    return this.typeCheckStandard(stable);
                }
                this._fname = new QName(namespace, null, local);
            }
            return this.typeCheckExternal(stable);
        }
        catch (TypeCheckError e) {
            ErrorMsg errorMsg = e.getErrorMsg();
            if (errorMsg == null) {
                final String name = this._fname.getLocalPart();
                errorMsg = new ErrorMsg("METHOD_NOT_FOUND_ERR", name);
            }
            this.getParser().reportError(3, errorMsg);
            return super._type = Type.Void;
        }
    }
    
    public Type typeCheckStandard(final SymbolTable stable) throws TypeCheckError {
        this._fname.clearNamespace();
        final int n = this._arguments.size();
        final Vector argsType = this.typeCheckArgs(stable);
        final MethodType args = new MethodType(Type.Void, argsType);
        final MethodType ptype = this.lookupPrimop(stable, this._fname.getLocalPart(), args);
        if (ptype != null) {
            for (int i = 0; i < n; ++i) {
                final Type argType = ptype.argsType().elementAt(i);
                final Expression exp = this._arguments.elementAt(i);
                if (!argType.identicalTo(exp.getType())) {
                    try {
                        this._arguments.setElementAt(new CastExpr(exp, argType), i);
                    }
                    catch (TypeCheckError e) {
                        throw new TypeCheckError(this);
                    }
                }
            }
            this._chosenMethodType = ptype;
            return super._type = ptype.resultType();
        }
        throw new TypeCheckError(this);
    }
    
    public Type typeCheckConstructor(final SymbolTable stable) throws TypeCheckError {
        final Vector constructors = this.findConstructors();
        if (constructors == null) {
            throw new TypeCheckError("CONSTRUCTOR_NOT_FOUND", this._className);
        }
        final int nConstructors = constructors.size();
        final int nArgs = this._arguments.size();
        final Vector argsType = this.typeCheckArgs(stable);
        int bestConstrDistance = Integer.MAX_VALUE;
        super._type = null;
        for (int i = 0; i < nConstructors; ++i) {
            final Constructor constructor = constructors.elementAt(i);
            final Class[] paramTypes = constructor.getParameterTypes();
            Class extType = null;
            int currConstrDistance = 0;
            int j;
            for (j = 0; j < nArgs; ++j) {
                extType = paramTypes[j];
                final Type intType = argsType.elementAt(j);
                final Object match = FunctionCall._internal2Java.maps(intType, extType);
                if (match != null) {
                    currConstrDistance += ((JavaType)match).distance;
                }
                else {
                    if (!(intType instanceof ObjectType)) {
                        currConstrDistance = Integer.MAX_VALUE;
                        break;
                    }
                    final ObjectType objectType = (ObjectType)intType;
                    if (objectType.getJavaClass() != extType) {
                        if (!extType.isAssignableFrom(objectType.getJavaClass())) {
                            currConstrDistance = Integer.MAX_VALUE;
                            break;
                        }
                        ++currConstrDistance;
                    }
                }
            }
            if (j == nArgs && currConstrDistance < bestConstrDistance) {
                this._chosenConstructor = constructor;
                this._isExtConstructor = true;
                bestConstrDistance = currConstrDistance;
                super._type = ((this._clazz != null) ? Type.newObjectType(this._clazz) : Type.newObjectType(this._className));
            }
        }
        if (super._type != null) {
            return super._type;
        }
        throw new TypeCheckError("ARGUMENT_CONVERSION_ERR", this.getMethodSignature(argsType));
    }
    
    public Type typeCheckExternal(final SymbolTable stable) throws TypeCheckError {
        int nArgs = this._arguments.size();
        final String name = this._fname.getLocalPart();
        if (this._fname.getLocalPart().equals("new")) {
            return this.typeCheckConstructor(stable);
        }
        boolean hasThisArgument = false;
        if (nArgs == 0) {
            this._isStatic = true;
        }
        if (!this._isStatic) {
            if (this._namespace_format == 0 || this._namespace_format == 2) {
                hasThisArgument = true;
            }
            final Expression firstArg = this._arguments.elementAt(0);
            final Type firstArgType = firstArg.typeCheck(stable);
            if (this._namespace_format == 1 && firstArgType instanceof ObjectType && this._clazz != null && this._clazz.isAssignableFrom(((ObjectType)firstArgType).getJavaClass())) {
                hasThisArgument = true;
            }
            if (hasThisArgument) {
                this._thisArgument = this._arguments.elementAt(0);
                this._arguments.remove(0);
                --nArgs;
                if (!(firstArgType instanceof ObjectType)) {
                    throw new TypeCheckError("NO_JAVA_FUNCT_THIS_REF", name);
                }
                this._className = ((ObjectType)firstArgType).getJavaClassName();
            }
        }
        else if (this._className.length() == 0) {
            final Parser parser = this.getParser();
            if (parser != null) {
                this.reportWarning(this, parser, "FUNCTION_RESOLVE_ERR", this._fname.toString());
            }
            this.unresolvedExternal = true;
            return super._type = Type.Int;
        }
        final Vector methods = this.findMethods();
        if (methods == null) {
            throw new TypeCheckError("METHOD_NOT_FOUND_ERR", this._className + "." + name);
        }
        Class extType = null;
        final int nMethods = methods.size();
        final Vector argsType = this.typeCheckArgs(stable);
        int bestMethodDistance = Integer.MAX_VALUE;
        super._type = null;
        for (int i = 0; i < nMethods; ++i) {
            final Method method = methods.elementAt(i);
            final Class[] paramTypes = method.getParameterTypes();
            int currMethodDistance = 0;
            int j;
            for (j = 0; j < nArgs; ++j) {
                extType = paramTypes[j];
                final Type intType = argsType.elementAt(j);
                final Object match = FunctionCall._internal2Java.maps(intType, extType);
                if (match != null) {
                    currMethodDistance += ((JavaType)match).distance;
                }
                else if (intType instanceof ReferenceType) {
                    ++currMethodDistance;
                }
                else {
                    if (!(intType instanceof ObjectType)) {
                        currMethodDistance = Integer.MAX_VALUE;
                        break;
                    }
                    final ObjectType object = (ObjectType)intType;
                    if (extType.getName().equals(object.getJavaClassName())) {
                        currMethodDistance += 0;
                    }
                    else {
                        if (!extType.isAssignableFrom(object.getJavaClass())) {
                            currMethodDistance = Integer.MAX_VALUE;
                            break;
                        }
                        ++currMethodDistance;
                    }
                }
            }
            if (j == nArgs) {
                extType = method.getReturnType();
                super._type = FunctionCall._java2Internal.get(extType);
                if (super._type == null) {
                    super._type = Type.newObjectType(extType);
                }
                if (super._type != null && currMethodDistance < bestMethodDistance) {
                    this._chosenMethod = method;
                    bestMethodDistance = currMethodDistance;
                }
            }
        }
        if (this._chosenMethod != null && this._thisArgument == null && !Modifier.isStatic(this._chosenMethod.getModifiers())) {
            throw new TypeCheckError("NO_JAVA_FUNCT_THIS_REF", this.getMethodSignature(argsType));
        }
        if (super._type != null) {
            if (super._type == Type.NodeSet) {
                this.getXSLTC().setMultiDocument(true);
            }
            return super._type;
        }
        throw new TypeCheckError("ARGUMENT_CONVERSION_ERR", this.getMethodSignature(argsType));
    }
    
    public Vector typeCheckArgs(final SymbolTable stable) throws TypeCheckError {
        final Vector result = new Vector();
        final Enumeration e = this._arguments.elements();
        while (e.hasMoreElements()) {
            final Expression exp = e.nextElement();
            result.addElement(exp.typeCheck(stable));
        }
        return result;
    }
    
    protected final Expression argument(final int i) {
        return this._arguments.elementAt(i);
    }
    
    protected final Expression argument() {
        return this.argument(0);
    }
    
    protected final int argumentCount() {
        return this._arguments.size();
    }
    
    protected final void setArgument(final int i, final Expression exp) {
        this._arguments.setElementAt(exp, i);
    }
    
    public void translateDesynthesized(final ClassGenerator classGen, final MethodGenerator methodGen) {
        Type type = Type.Boolean;
        if (this._chosenMethodType != null) {
            type = this._chosenMethodType.resultType();
        }
        final InstructionList il = methodGen.getInstructionList();
        this.translate(classGen, methodGen);
        if (type instanceof BooleanType || type instanceof IntType) {
            super._falseList.add(il.append(new IFEQ(null)));
        }
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final int n = this.argumentCount();
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final boolean isSecureProcessing = classGen.getParser().getXSLTC().isSecureProcessing();
        if (this.isStandard() || this.isExtension()) {
            for (int i = 0; i < n; ++i) {
                final Expression exp = this.argument(i);
                exp.translate(classGen, methodGen);
                exp.startIterator(classGen, methodGen);
            }
            final String name = this._fname.toString().replace('-', '_') + "F";
            String args = "";
            if (name.equals("sumF")) {
                args = "Lorg/apache/xalan/xsltc/DOM;";
                il.append(methodGen.loadDOM());
            }
            else if (name.equals("normalize_spaceF") && this._chosenMethodType.toSignature(args).equals("()Ljava/lang/String;")) {
                args = "ILorg/apache/xalan/xsltc/DOM;";
                il.append(methodGen.loadContextNode());
                il.append(methodGen.loadDOM());
            }
            final int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", name, this._chosenMethodType.toSignature(args));
            il.append(new INVOKESTATIC(index));
        }
        else if (this.unresolvedExternal) {
            final int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "unresolved_externalF", "(Ljava/lang/String;)V");
            il.append(new PUSH(cpg, this._fname.toString()));
            il.append(new INVOKESTATIC(index));
        }
        else if (this._isExtConstructor) {
            if (isSecureProcessing) {
                this.translateUnallowedExtension(cpg, il);
            }
            final String clazz = this._chosenConstructor.getDeclaringClass().getName();
            final Class[] paramTypes = this._chosenConstructor.getParameterTypes();
            final LocalVariableGen[] paramTemp = new LocalVariableGen[n];
            for (int j = 0; j < n; ++j) {
                final Expression exp2 = this.argument(j);
                final Type expType = exp2.getType();
                exp2.translate(classGen, methodGen);
                exp2.startIterator(classGen, methodGen);
                expType.translateTo(classGen, methodGen, paramTypes[j]);
                paramTemp[j] = methodGen.addLocalVariable("function_call_tmp" + j, expType.toJCType(), il.getEnd(), null);
                il.append(expType.STORE(paramTemp[j].getIndex()));
            }
            il.append(new NEW(cpg.addClass(this._className)));
            il.append(InstructionConstants.DUP);
            for (int k = 0; k < n; ++k) {
                final Expression arg = this.argument(k);
                il.append(arg.getType().LOAD(paramTemp[k].getIndex()));
            }
            final StringBuffer buffer = new StringBuffer();
            buffer.append('(');
            for (int l = 0; l < paramTypes.length; ++l) {
                buffer.append(getSignature(paramTypes[l]));
            }
            buffer.append(')');
            buffer.append("V");
            final int index = cpg.addMethodref(clazz, "<init>", buffer.toString());
            il.append(new INVOKESPECIAL(index));
            Type.Object.translateFrom(classGen, methodGen, this._chosenConstructor.getDeclaringClass());
        }
        else {
            if (isSecureProcessing) {
                this.translateUnallowedExtension(cpg, il);
            }
            final String clazz = this._chosenMethod.getDeclaringClass().getName();
            final Class[] paramTypes = this._chosenMethod.getParameterTypes();
            if (this._thisArgument != null) {
                this._thisArgument.translate(classGen, methodGen);
            }
            for (int m = 0; m < n; ++m) {
                final Expression exp3 = this.argument(m);
                exp3.translate(classGen, methodGen);
                exp3.startIterator(classGen, methodGen);
                exp3.getType().translateTo(classGen, methodGen, paramTypes[m]);
            }
            final StringBuffer buffer2 = new StringBuffer();
            buffer2.append('(');
            for (int k = 0; k < paramTypes.length; ++k) {
                buffer2.append(getSignature(paramTypes[k]));
            }
            buffer2.append(')');
            buffer2.append(getSignature(this._chosenMethod.getReturnType()));
            if (this._thisArgument != null && this._clazz.isInterface()) {
                final int index = cpg.addInterfaceMethodref(clazz, this._fname.getLocalPart(), buffer2.toString());
                il.append(new INVOKEINTERFACE(index, n + 1));
            }
            else {
                final int index = cpg.addMethodref(clazz, this._fname.getLocalPart(), buffer2.toString());
                il.append((this._thisArgument != null) ? new INVOKEVIRTUAL(index) : new INVOKESTATIC(index));
            }
            super._type.translateFrom(classGen, methodGen, this._chosenMethod.getReturnType());
        }
    }
    
    public String toString() {
        return "funcall(" + this._fname + ", " + this._arguments + ')';
    }
    
    public boolean isStandard() {
        final String namespace = this._fname.getNamespace();
        return namespace == null || namespace.equals("");
    }
    
    public boolean isExtension() {
        final String namespace = this._fname.getNamespace();
        return namespace != null && namespace.equals("http://xml.apache.org/xalan/xsltc");
    }
    
    private Vector findMethods() {
        Vector result = null;
        final String namespace = this._fname.getNamespace();
        if (this._className != null && this._className.length() > 0) {
            final int nArgs = this._arguments.size();
            try {
                if (this._clazz == null) {
                    this._clazz = ObjectFactory.findProviderClass(this._className, ObjectFactory.findClassLoader(), true);
                    if (this._clazz == null) {
                        final ErrorMsg msg = new ErrorMsg("CLASS_NOT_FOUND_ERR", this._className);
                        this.getParser().reportError(3, msg);
                    }
                }
                final String methodName = this._fname.getLocalPart();
                final Method[] methods = this._clazz.getMethods();
                for (int i = 0; i < methods.length; ++i) {
                    final int mods = methods[i].getModifiers();
                    if (Modifier.isPublic(mods) && methods[i].getName().equals(methodName) && methods[i].getParameterTypes().length == nArgs) {
                        if (result == null) {
                            result = new Vector();
                        }
                        result.addElement(methods[i]);
                    }
                }
            }
            catch (ClassNotFoundException e) {
                final ErrorMsg msg2 = new ErrorMsg("CLASS_NOT_FOUND_ERR", this._className);
                this.getParser().reportError(3, msg2);
            }
        }
        return result;
    }
    
    private Vector findConstructors() {
        Vector result = null;
        final String namespace = this._fname.getNamespace();
        final int nArgs = this._arguments.size();
        try {
            if (this._clazz == null) {
                this._clazz = ObjectFactory.findProviderClass(this._className, ObjectFactory.findClassLoader(), true);
                if (this._clazz == null) {
                    final ErrorMsg msg = new ErrorMsg("CLASS_NOT_FOUND_ERR", this._className);
                    this.getParser().reportError(3, msg);
                }
            }
            final Constructor[] constructors = this._clazz.getConstructors();
            for (int i = 0; i < constructors.length; ++i) {
                final int mods = constructors[i].getModifiers();
                if (Modifier.isPublic(mods) && constructors[i].getParameterTypes().length == nArgs) {
                    if (result == null) {
                        result = new Vector();
                    }
                    result.addElement(constructors[i]);
                }
            }
        }
        catch (ClassNotFoundException e) {
            final ErrorMsg msg2 = new ErrorMsg("CLASS_NOT_FOUND_ERR", this._className);
            this.getParser().reportError(3, msg2);
        }
        return result;
    }
    
    static final String getSignature(final Class clazz) {
        if (clazz.isArray()) {
            final StringBuffer sb = new StringBuffer();
            Class cl;
            for (cl = clazz; cl.isArray(); cl = cl.getComponentType()) {
                sb.append("[");
            }
            sb.append(getSignature(cl));
            return sb.toString();
        }
        if (!clazz.isPrimitive()) {
            return "L" + clazz.getName().replace('.', '/') + ';';
        }
        if (clazz == Integer.TYPE) {
            return "I";
        }
        if (clazz == Byte.TYPE) {
            return "B";
        }
        if (clazz == Long.TYPE) {
            return "J";
        }
        if (clazz == Float.TYPE) {
            return "F";
        }
        if (clazz == Double.TYPE) {
            return "D";
        }
        if (clazz == Short.TYPE) {
            return "S";
        }
        if (clazz == Character.TYPE) {
            return "C";
        }
        if (clazz == Boolean.TYPE) {
            return "Z";
        }
        if (clazz == Void.TYPE) {
            return "V";
        }
        final String name = clazz.toString();
        final ErrorMsg err = new ErrorMsg("UNKNOWN_SIG_TYPE_ERR", name);
        throw new Error(err.toString());
    }
    
    static final String getSignature(final Method meth) {
        final StringBuffer sb = new StringBuffer();
        sb.append('(');
        final Class[] params = meth.getParameterTypes();
        for (int j = 0; j < params.length; ++j) {
            sb.append(getSignature(params[j]));
        }
        return sb.append(')').append(getSignature(meth.getReturnType())).toString();
    }
    
    static final String getSignature(final Constructor cons) {
        final StringBuffer sb = new StringBuffer();
        sb.append('(');
        final Class[] params = cons.getParameterTypes();
        for (int j = 0; j < params.length; ++j) {
            sb.append(getSignature(params[j]));
        }
        return sb.append(")V").toString();
    }
    
    private String getMethodSignature(final Vector argsType) {
        final StringBuffer buf = new StringBuffer(this._className);
        buf.append('.').append(this._fname.getLocalPart()).append('(');
        for (int nArgs = argsType.size(), i = 0; i < nArgs; ++i) {
            final Type intType = argsType.elementAt(i);
            buf.append(intType.toString());
            if (i < nArgs - 1) {
                buf.append(", ");
            }
        }
        buf.append(')');
        return buf.toString();
    }
    
    protected static String replaceDash(final String name) {
        final char dash = '-';
        final StringBuffer buff = new StringBuffer("");
        for (int i = 0; i < name.length(); ++i) {
            if (i > 0 && name.charAt(i - 1) == dash) {
                buff.append(Character.toUpperCase(name.charAt(i)));
            }
            else if (name.charAt(i) != dash) {
                buff.append(name.charAt(i));
            }
        }
        return buff.toString();
    }
    
    private void translateUnallowedExtension(final ConstantPoolGen cpg, final InstructionList il) {
        final int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "unallowed_extension_functionF", "(Ljava/lang/String;)V");
        il.append(new PUSH(cpg, this._fname.toString()));
        il.append(new INVOKESTATIC(index));
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        EMPTY_ARG_LIST = new Vector(0);
        _internal2Java = new MultiHashtable();
        _java2Internal = new Hashtable();
        _extensionNamespaceTable = new Hashtable();
        _extensionFunctionTable = new Hashtable();
        try {
            final Class nodeClass = Class.forName("org.w3c.dom.Node");
            final Class nodeListClass = Class.forName("org.w3c.dom.NodeList");
            FunctionCall._internal2Java.put(Type.Boolean, new JavaType(Boolean.TYPE, 0));
            FunctionCall._internal2Java.put(Type.Boolean, new JavaType((FunctionCall.class$java$lang$Boolean == null) ? (FunctionCall.class$java$lang$Boolean = class$("java.lang.Boolean")) : FunctionCall.class$java$lang$Boolean, 1));
            FunctionCall._internal2Java.put(Type.Boolean, new JavaType((FunctionCall.class$java$lang$Object == null) ? (FunctionCall.class$java$lang$Object = class$("java.lang.Object")) : FunctionCall.class$java$lang$Object, 2));
            FunctionCall._internal2Java.put(Type.Real, new JavaType(Double.TYPE, 0));
            FunctionCall._internal2Java.put(Type.Real, new JavaType((FunctionCall.class$java$lang$Double == null) ? (FunctionCall.class$java$lang$Double = class$("java.lang.Double")) : FunctionCall.class$java$lang$Double, 1));
            FunctionCall._internal2Java.put(Type.Real, new JavaType(Float.TYPE, 2));
            FunctionCall._internal2Java.put(Type.Real, new JavaType(Long.TYPE, 3));
            FunctionCall._internal2Java.put(Type.Real, new JavaType(Integer.TYPE, 4));
            FunctionCall._internal2Java.put(Type.Real, new JavaType(Short.TYPE, 5));
            FunctionCall._internal2Java.put(Type.Real, new JavaType(Byte.TYPE, 6));
            FunctionCall._internal2Java.put(Type.Real, new JavaType(Character.TYPE, 7));
            FunctionCall._internal2Java.put(Type.Real, new JavaType((FunctionCall.class$java$lang$Object == null) ? (FunctionCall.class$java$lang$Object = class$("java.lang.Object")) : FunctionCall.class$java$lang$Object, 8));
            FunctionCall._internal2Java.put(Type.Int, new JavaType(Double.TYPE, 0));
            FunctionCall._internal2Java.put(Type.Int, new JavaType((FunctionCall.class$java$lang$Double == null) ? (FunctionCall.class$java$lang$Double = class$("java.lang.Double")) : FunctionCall.class$java$lang$Double, 1));
            FunctionCall._internal2Java.put(Type.Int, new JavaType(Float.TYPE, 2));
            FunctionCall._internal2Java.put(Type.Int, new JavaType(Long.TYPE, 3));
            FunctionCall._internal2Java.put(Type.Int, new JavaType(Integer.TYPE, 4));
            FunctionCall._internal2Java.put(Type.Int, new JavaType(Short.TYPE, 5));
            FunctionCall._internal2Java.put(Type.Int, new JavaType(Byte.TYPE, 6));
            FunctionCall._internal2Java.put(Type.Int, new JavaType(Character.TYPE, 7));
            FunctionCall._internal2Java.put(Type.Int, new JavaType((FunctionCall.class$java$lang$Object == null) ? (FunctionCall.class$java$lang$Object = class$("java.lang.Object")) : FunctionCall.class$java$lang$Object, 8));
            FunctionCall._internal2Java.put(Type.String, new JavaType((FunctionCall.class$java$lang$String == null) ? (FunctionCall.class$java$lang$String = class$("java.lang.String")) : FunctionCall.class$java$lang$String, 0));
            FunctionCall._internal2Java.put(Type.String, new JavaType((FunctionCall.class$java$lang$Object == null) ? (FunctionCall.class$java$lang$Object = class$("java.lang.Object")) : FunctionCall.class$java$lang$Object, 1));
            FunctionCall._internal2Java.put(Type.NodeSet, new JavaType(nodeListClass, 0));
            FunctionCall._internal2Java.put(Type.NodeSet, new JavaType(nodeClass, 1));
            FunctionCall._internal2Java.put(Type.NodeSet, new JavaType((FunctionCall.class$java$lang$Object == null) ? (FunctionCall.class$java$lang$Object = class$("java.lang.Object")) : FunctionCall.class$java$lang$Object, 2));
            FunctionCall._internal2Java.put(Type.NodeSet, new JavaType((FunctionCall.class$java$lang$String == null) ? (FunctionCall.class$java$lang$String = class$("java.lang.String")) : FunctionCall.class$java$lang$String, 3));
            FunctionCall._internal2Java.put(Type.Node, new JavaType(nodeListClass, 0));
            FunctionCall._internal2Java.put(Type.Node, new JavaType(nodeClass, 1));
            FunctionCall._internal2Java.put(Type.Node, new JavaType((FunctionCall.class$java$lang$Object == null) ? (FunctionCall.class$java$lang$Object = class$("java.lang.Object")) : FunctionCall.class$java$lang$Object, 2));
            FunctionCall._internal2Java.put(Type.Node, new JavaType((FunctionCall.class$java$lang$String == null) ? (FunctionCall.class$java$lang$String = class$("java.lang.String")) : FunctionCall.class$java$lang$String, 3));
            FunctionCall._internal2Java.put(Type.ResultTree, new JavaType(nodeListClass, 0));
            FunctionCall._internal2Java.put(Type.ResultTree, new JavaType(nodeClass, 1));
            FunctionCall._internal2Java.put(Type.ResultTree, new JavaType((FunctionCall.class$java$lang$Object == null) ? (FunctionCall.class$java$lang$Object = class$("java.lang.Object")) : FunctionCall.class$java$lang$Object, 2));
            FunctionCall._internal2Java.put(Type.ResultTree, new JavaType((FunctionCall.class$java$lang$String == null) ? (FunctionCall.class$java$lang$String = class$("java.lang.String")) : FunctionCall.class$java$lang$String, 3));
            FunctionCall._internal2Java.put(Type.Reference, new JavaType((FunctionCall.class$java$lang$Object == null) ? (FunctionCall.class$java$lang$Object = class$("java.lang.Object")) : FunctionCall.class$java$lang$Object, 0));
            FunctionCall._java2Internal.put(Boolean.TYPE, Type.Boolean);
            FunctionCall._java2Internal.put(Void.TYPE, Type.Void);
            FunctionCall._java2Internal.put(Character.TYPE, Type.Real);
            FunctionCall._java2Internal.put(Byte.TYPE, Type.Real);
            FunctionCall._java2Internal.put(Short.TYPE, Type.Real);
            FunctionCall._java2Internal.put(Integer.TYPE, Type.Real);
            FunctionCall._java2Internal.put(Long.TYPE, Type.Real);
            FunctionCall._java2Internal.put(Float.TYPE, Type.Real);
            FunctionCall._java2Internal.put(Double.TYPE, Type.Real);
            FunctionCall._java2Internal.put((FunctionCall.class$java$lang$String == null) ? (FunctionCall.class$java$lang$String = class$("java.lang.String")) : FunctionCall.class$java$lang$String, Type.String);
            FunctionCall._java2Internal.put((FunctionCall.class$java$lang$Object == null) ? (FunctionCall.class$java$lang$Object = class$("java.lang.Object")) : FunctionCall.class$java$lang$Object, Type.Reference);
            FunctionCall._java2Internal.put(nodeListClass, Type.NodeSet);
            FunctionCall._java2Internal.put(nodeClass, Type.NodeSet);
            FunctionCall._extensionNamespaceTable.put("http://xml.apache.org/xalan", "org.apache.xalan.lib.Extensions");
            FunctionCall._extensionNamespaceTable.put("http://exslt.org/common", "org.apache.xalan.lib.ExsltCommon");
            FunctionCall._extensionNamespaceTable.put("http://exslt.org/math", "org.apache.xalan.lib.ExsltMath");
            FunctionCall._extensionNamespaceTable.put("http://exslt.org/sets", "org.apache.xalan.lib.ExsltSets");
            FunctionCall._extensionNamespaceTable.put("http://exslt.org/dates-and-times", "org.apache.xalan.lib.ExsltDatetime");
            FunctionCall._extensionNamespaceTable.put("http://exslt.org/strings", "org.apache.xalan.lib.ExsltStrings");
            FunctionCall._extensionFunctionTable.put("http://exslt.org/common:nodeSet", "nodeset");
            FunctionCall._extensionFunctionTable.put("http://exslt.org/common:objectType", "objectType");
            FunctionCall._extensionFunctionTable.put("http://xml.apache.org/xalan:nodeset", "nodeset");
        }
        catch (ClassNotFoundException e) {
            System.err.println(e);
        }
    }
    
    static class JavaType
    {
        public Class type;
        public int distance;
        
        public JavaType(final Class type, final int distance) {
            this.type = type;
            this.distance = distance;
        }
        
        public boolean equals(final Object query) {
            return query.equals(this.type);
        }
    }
}
