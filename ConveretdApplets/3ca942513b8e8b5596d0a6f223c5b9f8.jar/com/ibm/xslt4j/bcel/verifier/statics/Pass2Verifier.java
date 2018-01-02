// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier.statics;

import com.ibm.xslt4j.bcel.classfile.LineNumber;
import com.ibm.xslt4j.bcel.classfile.Unknown;
import com.ibm.xslt4j.bcel.classfile.LocalVariable;
import com.ibm.xslt4j.bcel.classfile.CodeException;
import com.ibm.xslt4j.bcel.verifier.exc.LocalVariableInfoInconsistentException;
import com.ibm.xslt4j.bcel.classfile.LocalVariableTable;
import com.ibm.xslt4j.bcel.classfile.LineNumberTable;
import com.ibm.xslt4j.bcel.classfile.InnerClass;
import com.ibm.xslt4j.bcel.classfile.ExceptionTable;
import com.ibm.xslt4j.bcel.classfile.Code;
import com.ibm.xslt4j.bcel.generic.ObjectType;
import com.ibm.xslt4j.bcel.generic.ArrayType;
import com.ibm.xslt4j.bcel.classfile.ConstantValue;
import com.ibm.xslt4j.bcel.classfile.Field;
import com.ibm.xslt4j.bcel.classfile.ConstantUtf8;
import com.ibm.xslt4j.bcel.classfile.ConstantNameAndType;
import com.ibm.xslt4j.bcel.classfile.ConstantDouble;
import com.ibm.xslt4j.bcel.classfile.ConstantLong;
import com.ibm.xslt4j.bcel.classfile.ConstantFloat;
import com.ibm.xslt4j.bcel.classfile.ConstantInteger;
import com.ibm.xslt4j.bcel.classfile.ConstantString;
import com.ibm.xslt4j.bcel.classfile.ConstantInterfaceMethodref;
import com.ibm.xslt4j.bcel.classfile.ConstantMethodref;
import com.ibm.xslt4j.bcel.classfile.ConstantFieldref;
import com.ibm.xslt4j.bcel.classfile.ConstantClass;
import com.ibm.xslt4j.bcel.classfile.Attribute;
import com.ibm.xslt4j.bcel.classfile.Synthetic;
import com.ibm.xslt4j.bcel.classfile.InnerClasses;
import com.ibm.xslt4j.bcel.classfile.Deprecated;
import com.ibm.xslt4j.bcel.classfile.SourceFile;
import com.ibm.xslt4j.bcel.classfile.Constant;
import com.ibm.xslt4j.bcel.classfile.ConstantPool;
import com.ibm.xslt4j.bcel.classfile.EmptyVisitor;
import com.ibm.xslt4j.bcel.classfile.Node;
import com.ibm.xslt4j.bcel.classfile.Visitor;
import com.ibm.xslt4j.bcel.classfile.DescendingVisitor;
import com.ibm.xslt4j.bcel.classfile.Method;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import java.util.HashMap;
import com.ibm.xslt4j.bcel.classfile.JavaClass;
import com.ibm.xslt4j.bcel.verifier.VerifierFactory;
import com.ibm.xslt4j.bcel.generic.Type;
import java.util.HashSet;
import com.ibm.xslt4j.bcel.verifier.exc.ClassConstraintException;
import com.ibm.xslt4j.bcel.Repository;
import com.ibm.xslt4j.bcel.verifier.exc.AssertionViolatedException;
import com.ibm.xslt4j.bcel.verifier.VerificationResult;
import com.ibm.xslt4j.bcel.verifier.Verifier;
import com.ibm.xslt4j.bcel.Constants;
import com.ibm.xslt4j.bcel.verifier.PassVerifier;

public final class Pass2Verifier extends PassVerifier implements Constants
{
    private LocalVariablesInfo[] localVariablesInfos;
    private Verifier myOwner;
    
    public Pass2Verifier(final Verifier owner) {
        this.myOwner = owner;
    }
    
    public LocalVariablesInfo getLocalVariablesInfo(final int method_nr) {
        if (this.verify() != VerificationResult.VR_OK) {
            return null;
        }
        if (method_nr < 0 || method_nr >= this.localVariablesInfos.length) {
            throw new AssertionViolatedException("Method number out of range.");
        }
        return this.localVariablesInfos[method_nr];
    }
    
    public VerificationResult do_verify() {
        final VerificationResult vr1 = this.myOwner.doPass1();
        if (vr1.equals(VerificationResult.VR_OK)) {
            this.localVariablesInfos = new LocalVariablesInfo[Repository.lookupClass(this.myOwner.getClassName()).getMethods().length];
            VerificationResult vr2 = VerificationResult.VR_OK;
            try {
                this.constant_pool_entries_satisfy_static_constraints();
                this.field_and_method_refs_are_valid();
                this.every_class_has_an_accessible_superclass();
                this.final_methods_are_not_overridden();
            }
            catch (ClassConstraintException cce) {
                vr2 = new VerificationResult(2, cce.getMessage());
            }
            return vr2;
        }
        return VerificationResult.VR_NOTYET;
    }
    
    private void every_class_has_an_accessible_superclass() {
        final HashSet hs = new HashSet();
        JavaClass jc = Repository.lookupClass(this.myOwner.getClassName());
        int supidx = -1;
        while (supidx != 0) {
            supidx = jc.getSuperclassNameIndex();
            if (supidx == 0) {
                if (jc != Repository.lookupClass(Type.OBJECT.getClassName())) {
                    throw new ClassConstraintException("Superclass of '" + jc.getClassName() + "' missing but not " + Type.OBJECT.getClassName() + " itself!");
                }
                continue;
            }
            else {
                final String supername = jc.getSuperclassName();
                if (!hs.add(supername)) {
                    throw new ClassConstraintException("Circular superclass hierarchy detected.");
                }
                final Verifier v = VerifierFactory.getVerifier(supername);
                final VerificationResult vr = v.doPass1();
                if (vr != VerificationResult.VR_OK) {
                    throw new ClassConstraintException("Could not load in ancestor class '" + supername + "'.");
                }
                jc = Repository.lookupClass(supername);
                if (jc.isFinal()) {
                    throw new ClassConstraintException("Ancestor class '" + supername + "' has the FINAL access modifier and must therefore not be subclassed.");
                }
                continue;
            }
        }
    }
    
    private void final_methods_are_not_overridden() {
        final HashMap hashmap = new HashMap();
        JavaClass jc = Repository.lookupClass(this.myOwner.getClassName());
        int supidx = -1;
        while (supidx != 0) {
            supidx = jc.getSuperclassNameIndex();
            final ConstantPoolGen cpg = new ConstantPoolGen(jc.getConstantPool());
            final Method[] methods = jc.getMethods();
            for (int i = 0; i < methods.length; ++i) {
                final String name_and_sig = String.valueOf(methods[i].getName()) + methods[i].getSignature();
                if (hashmap.containsKey(name_and_sig)) {
                    if (methods[i].isFinal()) {
                        throw new ClassConstraintException("Method '" + name_and_sig + "' in class '" + hashmap.get(name_and_sig) + "' overrides the final (not-overridable) definition in class '" + jc.getClassName() + "'.");
                    }
                    if (!methods[i].isStatic()) {
                        hashmap.put(name_and_sig, jc.getClassName());
                    }
                }
                else if (!methods[i].isStatic()) {
                    hashmap.put(name_and_sig, jc.getClassName());
                }
            }
            jc = Repository.lookupClass(jc.getSuperclassName());
        }
    }
    
    private void constant_pool_entries_satisfy_static_constraints() {
        final JavaClass jc = Repository.lookupClass(this.myOwner.getClassName());
        new CPESSC_Visitor(jc);
    }
    
    private void field_and_method_refs_are_valid() {
        final JavaClass jc = Repository.lookupClass(this.myOwner.getClassName());
        final DescendingVisitor v = new DescendingVisitor(jc, new FAMRAV_Visitor(jc));
        v.visit();
    }
    
    private static final boolean validClassName(final String name) {
        return true;
    }
    
    private static boolean validMethodName(final String name, final boolean allowStaticInit) {
        if (validJavaLangMethodName(name)) {
            return true;
        }
        if (allowStaticInit) {
            return name.equals("<init>") || name.equals("<clinit>");
        }
        return name.equals("<init>");
    }
    
    private static boolean validClassMethodName(final String name) {
        return validMethodName(name, false);
    }
    
    private static boolean validJavaLangMethodName(final String name) {
        if (!Character.isJavaIdentifierStart(name.charAt(0))) {
            return false;
        }
        for (int i = 1; i < name.length(); ++i) {
            if (!Character.isJavaIdentifierPart(name.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean validInterfaceMethodName(final String name) {
        return !name.startsWith("<") && validJavaLangMethodName(name);
    }
    
    private static boolean validJavaIdentifier(final String name) {
        if (!Character.isJavaIdentifierStart(name.charAt(0))) {
            return false;
        }
        for (int i = 1; i < name.length(); ++i) {
            if (!Character.isJavaIdentifierPart(name.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean validFieldName(final String name) {
        return validJavaIdentifier(name);
    }
    
    private static String tostring(final Node n) {
        return new StringRepresentation(n).toString();
    }
    
    private class CPESSC_Visitor extends EmptyVisitor implements Visitor
    {
        private Class CONST_Class;
        private Class CONST_Fieldref;
        private Class CONST_Methodref;
        private Class CONST_InterfaceMethodref;
        private Class CONST_String;
        private Class CONST_Integer;
        private Class CONST_Float;
        private Class CONST_Long;
        private Class CONST_Double;
        private Class CONST_NameAndType;
        private Class CONST_Utf8;
        private final JavaClass jc;
        private final ConstantPool cp;
        private final int cplen;
        private DescendingVisitor carrier;
        private HashSet field_names;
        private HashSet field_names_and_desc;
        private HashSet method_names_and_desc;
        static /* synthetic */ Class class$0;
        static /* synthetic */ Class class$1;
        static /* synthetic */ Class class$2;
        static /* synthetic */ Class class$3;
        static /* synthetic */ Class class$4;
        static /* synthetic */ Class class$5;
        static /* synthetic */ Class class$6;
        static /* synthetic */ Class class$7;
        static /* synthetic */ Class class$8;
        static /* synthetic */ Class class$9;
        static /* synthetic */ Class class$10;
        
        CPESSC_Visitor(final JavaClass _jc) {
            this.field_names = new HashSet();
            this.field_names_and_desc = new HashSet();
            this.method_names_and_desc = new HashSet();
            this.jc = _jc;
            this.cp = _jc.getConstantPool();
            this.cplen = this.cp.getLength();
            Class class$0;
            if ((class$0 = CPESSC_Visitor.class$0) == null) {
                try {
                    class$0 = (CPESSC_Visitor.class$0 = Class.forName("com.ibm.xslt4j.bcel.classfile.ConstantClass"));
                }
                catch (ClassNotFoundException ex) {
                    throw new NoClassDefFoundError(ex.getMessage());
                }
            }
            this.CONST_Class = class$0;
            Class class$2;
            if ((class$2 = CPESSC_Visitor.class$1) == null) {
                try {
                    class$2 = (CPESSC_Visitor.class$1 = Class.forName("com.ibm.xslt4j.bcel.classfile.ConstantFieldref"));
                }
                catch (ClassNotFoundException ex2) {
                    throw new NoClassDefFoundError(ex2.getMessage());
                }
            }
            this.CONST_Fieldref = class$2;
            Class class$3;
            if ((class$3 = CPESSC_Visitor.class$2) == null) {
                try {
                    class$3 = (CPESSC_Visitor.class$2 = Class.forName("com.ibm.xslt4j.bcel.classfile.ConstantMethodref"));
                }
                catch (ClassNotFoundException ex3) {
                    throw new NoClassDefFoundError(ex3.getMessage());
                }
            }
            this.CONST_Methodref = class$3;
            Class class$4;
            if ((class$4 = CPESSC_Visitor.class$3) == null) {
                try {
                    class$4 = (CPESSC_Visitor.class$3 = Class.forName("com.ibm.xslt4j.bcel.classfile.ConstantInterfaceMethodref"));
                }
                catch (ClassNotFoundException ex4) {
                    throw new NoClassDefFoundError(ex4.getMessage());
                }
            }
            this.CONST_InterfaceMethodref = class$4;
            Class class$5;
            if ((class$5 = CPESSC_Visitor.class$4) == null) {
                try {
                    class$5 = (CPESSC_Visitor.class$4 = Class.forName("com.ibm.xslt4j.bcel.classfile.ConstantString"));
                }
                catch (ClassNotFoundException ex5) {
                    throw new NoClassDefFoundError(ex5.getMessage());
                }
            }
            this.CONST_String = class$5;
            Class class$6;
            if ((class$6 = CPESSC_Visitor.class$5) == null) {
                try {
                    class$6 = (CPESSC_Visitor.class$5 = Class.forName("com.ibm.xslt4j.bcel.classfile.ConstantInteger"));
                }
                catch (ClassNotFoundException ex6) {
                    throw new NoClassDefFoundError(ex6.getMessage());
                }
            }
            this.CONST_Integer = class$6;
            Class class$7;
            if ((class$7 = CPESSC_Visitor.class$6) == null) {
                try {
                    class$7 = (CPESSC_Visitor.class$6 = Class.forName("com.ibm.xslt4j.bcel.classfile.ConstantFloat"));
                }
                catch (ClassNotFoundException ex7) {
                    throw new NoClassDefFoundError(ex7.getMessage());
                }
            }
            this.CONST_Float = class$7;
            Class class$8;
            if ((class$8 = CPESSC_Visitor.class$7) == null) {
                try {
                    class$8 = (CPESSC_Visitor.class$7 = Class.forName("com.ibm.xslt4j.bcel.classfile.ConstantLong"));
                }
                catch (ClassNotFoundException ex8) {
                    throw new NoClassDefFoundError(ex8.getMessage());
                }
            }
            this.CONST_Long = class$8;
            Class class$9;
            if ((class$9 = CPESSC_Visitor.class$8) == null) {
                try {
                    class$9 = (CPESSC_Visitor.class$8 = Class.forName("com.ibm.xslt4j.bcel.classfile.ConstantDouble"));
                }
                catch (ClassNotFoundException ex9) {
                    throw new NoClassDefFoundError(ex9.getMessage());
                }
            }
            this.CONST_Double = class$9;
            Class class$10;
            if ((class$10 = CPESSC_Visitor.class$9) == null) {
                try {
                    class$10 = (CPESSC_Visitor.class$9 = Class.forName("com.ibm.xslt4j.bcel.classfile.ConstantNameAndType"));
                }
                catch (ClassNotFoundException ex10) {
                    throw new NoClassDefFoundError(ex10.getMessage());
                }
            }
            this.CONST_NameAndType = class$10;
            Class class$11;
            if ((class$11 = CPESSC_Visitor.class$10) == null) {
                try {
                    class$11 = (CPESSC_Visitor.class$10 = Class.forName("com.ibm.xslt4j.bcel.classfile.ConstantUtf8"));
                }
                catch (ClassNotFoundException ex11) {
                    throw new NoClassDefFoundError(ex11.getMessage());
                }
            }
            this.CONST_Utf8 = class$11;
            (this.carrier = new DescendingVisitor(_jc, this)).visit();
        }
        
        private void checkIndex(final Node referrer, final int index, final Class shouldbe) {
            if (index < 0 || index >= this.cplen) {
                throw new ClassConstraintException("Invalid index '" + index + "' used by '" + tostring(referrer) + "'.");
            }
            final Constant c = this.cp.getConstant(index);
            if (!shouldbe.isInstance(c)) {
                final String isnot = shouldbe.toString().substring(shouldbe.toString().lastIndexOf(".") + 1);
                throw new ClassCastException("Illegal constant '" + tostring(c) + "' at index '" + index + "'. '" + tostring(referrer) + "' expects a '" + shouldbe + "'.");
            }
        }
        
        public void visitJavaClass(final JavaClass obj) {
            final Attribute[] atts = obj.getAttributes();
            boolean foundSourceFile = false;
            boolean foundInnerClasses = false;
            final boolean hasInnerClass = new InnerClassDetector(this.jc).innerClassReferenced();
            for (int i = 0; i < atts.length; ++i) {
                if (!(atts[i] instanceof SourceFile) && !(atts[i] instanceof Deprecated) && !(atts[i] instanceof InnerClasses) && !(atts[i] instanceof Synthetic)) {
                    Pass2Verifier.this.addMessage("Attribute '" + tostring(atts[i]) + "' as an attribute of the ClassFile structure '" + tostring(obj) + "' is unknown and will therefore be ignored.");
                }
                if (atts[i] instanceof SourceFile) {
                    if (foundSourceFile) {
                        throw new ClassConstraintException("A ClassFile structure (like '" + tostring(obj) + "') may have no more than one SourceFile attribute.");
                    }
                    foundSourceFile = true;
                }
                if (atts[i] instanceof InnerClasses) {
                    if (!foundInnerClasses) {
                        foundInnerClasses = true;
                    }
                    else if (hasInnerClass) {
                        throw new ClassConstraintException("A Classfile structure (like '" + tostring(obj) + "') must have exactly one InnerClasses attribute if at least one Inner Class is referenced (which is the case). More than one InnerClasses attribute was found.");
                    }
                    if (!hasInnerClass) {
                        Pass2Verifier.this.addMessage("No referenced Inner Class found, but InnerClasses attribute '" + tostring(atts[i]) + "' found. Strongly suggest removal of that attribute.");
                    }
                }
            }
            if (hasInnerClass && !foundInnerClasses) {
                Pass2Verifier.this.addMessage("A Classfile structure (like '" + tostring(obj) + "') must have exactly one InnerClasses attribute if at least one Inner Class is referenced (which is the case). No InnerClasses attribute was found.");
            }
        }
        
        public void visitConstantClass(final ConstantClass obj) {
            if (obj.getTag() != 7) {
                throw new ClassConstraintException("Wrong constant tag in '" + tostring(obj) + "'.");
            }
            this.checkIndex(obj, obj.getNameIndex(), this.CONST_Utf8);
        }
        
        public void visitConstantFieldref(final ConstantFieldref obj) {
            if (obj.getTag() != 9) {
                throw new ClassConstraintException("Wrong constant tag in '" + tostring(obj) + "'.");
            }
            this.checkIndex(obj, obj.getClassIndex(), this.CONST_Class);
            this.checkIndex(obj, obj.getNameAndTypeIndex(), this.CONST_NameAndType);
        }
        
        public void visitConstantMethodref(final ConstantMethodref obj) {
            if (obj.getTag() != 10) {
                throw new ClassConstraintException("Wrong constant tag in '" + tostring(obj) + "'.");
            }
            this.checkIndex(obj, obj.getClassIndex(), this.CONST_Class);
            this.checkIndex(obj, obj.getNameAndTypeIndex(), this.CONST_NameAndType);
        }
        
        public void visitConstantInterfaceMethodref(final ConstantInterfaceMethodref obj) {
            if (obj.getTag() != 11) {
                throw new ClassConstraintException("Wrong constant tag in '" + tostring(obj) + "'.");
            }
            this.checkIndex(obj, obj.getClassIndex(), this.CONST_Class);
            this.checkIndex(obj, obj.getNameAndTypeIndex(), this.CONST_NameAndType);
        }
        
        public void visitConstantString(final ConstantString obj) {
            if (obj.getTag() != 8) {
                throw new ClassConstraintException("Wrong constant tag in '" + tostring(obj) + "'.");
            }
            this.checkIndex(obj, obj.getStringIndex(), this.CONST_Utf8);
        }
        
        public void visitConstantInteger(final ConstantInteger obj) {
            if (obj.getTag() != 3) {
                throw new ClassConstraintException("Wrong constant tag in '" + tostring(obj) + "'.");
            }
        }
        
        public void visitConstantFloat(final ConstantFloat obj) {
            if (obj.getTag() != 4) {
                throw new ClassConstraintException("Wrong constant tag in '" + tostring(obj) + "'.");
            }
        }
        
        public void visitConstantLong(final ConstantLong obj) {
            if (obj.getTag() != 5) {
                throw new ClassConstraintException("Wrong constant tag in '" + tostring(obj) + "'.");
            }
        }
        
        public void visitConstantDouble(final ConstantDouble obj) {
            if (obj.getTag() != 6) {
                throw new ClassConstraintException("Wrong constant tag in '" + tostring(obj) + "'.");
            }
        }
        
        public void visitConstantNameAndType(final ConstantNameAndType obj) {
            if (obj.getTag() != 12) {
                throw new ClassConstraintException("Wrong constant tag in '" + tostring(obj) + "'.");
            }
            this.checkIndex(obj, obj.getNameIndex(), this.CONST_Utf8);
            this.checkIndex(obj, obj.getSignatureIndex(), this.CONST_Utf8);
        }
        
        public void visitConstantUtf8(final ConstantUtf8 obj) {
            if (obj.getTag() != 1) {
                throw new ClassConstraintException("Wrong constant tag in '" + tostring(obj) + "'.");
            }
        }
        
        public void visitField(final Field obj) {
            if (this.jc.isClass()) {
                int maxone = 0;
                if (obj.isPrivate()) {
                    ++maxone;
                }
                if (obj.isProtected()) {
                    ++maxone;
                }
                if (obj.isPublic()) {
                    ++maxone;
                }
                if (maxone > 1) {
                    throw new ClassConstraintException("Field '" + tostring(obj) + "' must only have at most one of its ACC_PRIVATE, ACC_PROTECTED, ACC_PUBLIC modifiers set.");
                }
                if (obj.isFinal() && obj.isVolatile()) {
                    throw new ClassConstraintException("Field '" + tostring(obj) + "' must only have at most one of its ACC_FINAL, ACC_VOLATILE modifiers set.");
                }
            }
            else {
                if (!obj.isPublic()) {
                    throw new ClassConstraintException("Interface field '" + tostring(obj) + "' must have the ACC_PUBLIC modifier set but hasn't!");
                }
                if (!obj.isStatic()) {
                    throw new ClassConstraintException("Interface field '" + tostring(obj) + "' must have the ACC_STATIC modifier set but hasn't!");
                }
                if (!obj.isFinal()) {
                    throw new ClassConstraintException("Interface field '" + tostring(obj) + "' must have the ACC_FINAL modifier set but hasn't!");
                }
            }
            if ((obj.getAccessFlags() & 0xFFFFFF20) > 0) {
                Pass2Verifier.this.addMessage("Field '" + tostring(obj) + "' has access flag(s) other than ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL, ACC_VOLATILE, ACC_TRANSIENT set (ignored).");
            }
            this.checkIndex(obj, obj.getNameIndex(), this.CONST_Utf8);
            final String name = obj.getName();
            if (!validFieldName(name)) {
                throw new ClassConstraintException("Field '" + tostring(obj) + "' has illegal name '" + obj.getName() + "'.");
            }
            this.checkIndex(obj, obj.getSignatureIndex(), this.CONST_Utf8);
            final String sig = ((ConstantUtf8)this.cp.getConstant(obj.getSignatureIndex())).getBytes();
            try {
                final Type t = Type.getType(sig);
            }
            catch (ClassFormatError cfe) {
                throw new ClassConstraintException("Illegal descriptor (==signature) '" + sig + "' used by '" + tostring(obj) + "'.");
            }
            final String nameanddesc = String.valueOf(name) + sig;
            if (this.field_names_and_desc.contains(nameanddesc)) {
                throw new ClassConstraintException("No two fields (like '" + tostring(obj) + "') are allowed have same names and descriptors!");
            }
            if (this.field_names.contains(name)) {
                Pass2Verifier.this.addMessage("More than one field of name '" + name + "' detected (but with different type descriptors). This is very unusual.");
            }
            this.field_names_and_desc.add(nameanddesc);
            this.field_names.add(name);
            final Attribute[] atts = obj.getAttributes();
            for (int i = 0; i < atts.length; ++i) {
                if (!(atts[i] instanceof ConstantValue) && !(atts[i] instanceof Synthetic) && !(atts[i] instanceof Deprecated)) {
                    Pass2Verifier.this.addMessage("Attribute '" + tostring(atts[i]) + "' as an attribute of Field '" + tostring(obj) + "' is unknown and will therefore be ignored.");
                }
                if (!(atts[i] instanceof ConstantValue)) {
                    Pass2Verifier.this.addMessage("Attribute '" + tostring(atts[i]) + "' as an attribute of Field '" + tostring(obj) + "' is not a ConstantValue and is therefore only of use for debuggers and such.");
                }
            }
        }
        
        public void visitMethod(final Method obj) {
            this.checkIndex(obj, obj.getNameIndex(), this.CONST_Utf8);
            final String name = obj.getName();
            if (!validMethodName(name, true)) {
                throw new ClassConstraintException("Method '" + tostring(obj) + "' has illegal name '" + name + "'.");
            }
            this.checkIndex(obj, obj.getSignatureIndex(), this.CONST_Utf8);
            final String sig = ((ConstantUtf8)this.cp.getConstant(obj.getSignatureIndex())).getBytes();
            Type t;
            Type[] ts;
            try {
                t = Type.getReturnType(sig);
                ts = Type.getArgumentTypes(sig);
            }
            catch (ClassFormatError cfe) {
                throw new ClassConstraintException("Illegal descriptor (==signature) '" + sig + "' used by Method '" + tostring(obj) + "'.");
            }
            Type act = t;
            if (act instanceof ArrayType) {
                act = ((ArrayType)act).getBasicType();
            }
            if (act instanceof ObjectType) {
                final Verifier v = VerifierFactory.getVerifier(((ObjectType)act).getClassName());
                final VerificationResult vr = v.doPass1();
                if (vr != VerificationResult.VR_OK) {
                    throw new ClassConstraintException("Method '" + tostring(obj) + "' has a return type that does not pass verification pass 1: '" + vr + "'.");
                }
            }
            for (int i = 0; i < ts.length; ++i) {
                act = ts[i];
                if (act instanceof ArrayType) {
                    act = ((ArrayType)act).getBasicType();
                }
                if (act instanceof ObjectType) {
                    final Verifier v2 = VerifierFactory.getVerifier(((ObjectType)act).getClassName());
                    final VerificationResult vr2 = v2.doPass1();
                    if (vr2 != VerificationResult.VR_OK) {
                        throw new ClassConstraintException("Method '" + tostring(obj) + "' has an argument type that does not pass verification pass 1: '" + vr2 + "'.");
                    }
                }
            }
            if (name.equals("<clinit>") && ts.length != 0) {
                throw new ClassConstraintException("Method '" + tostring(obj) + "' has illegal name '" + name + "'. It's name resembles the class or interface initialization method which it isn't because of its arguments (==descriptor).");
            }
            if (this.jc.isClass()) {
                int maxone = 0;
                if (obj.isPrivate()) {
                    ++maxone;
                }
                if (obj.isProtected()) {
                    ++maxone;
                }
                if (obj.isPublic()) {
                    ++maxone;
                }
                if (maxone > 1) {
                    throw new ClassConstraintException("Method '" + tostring(obj) + "' must only have at most one of its ACC_PRIVATE, ACC_PROTECTED, ACC_PUBLIC modifiers set.");
                }
                if (obj.isAbstract()) {
                    if (obj.isFinal()) {
                        throw new ClassConstraintException("Abstract method '" + tostring(obj) + "' must not have the ACC_FINAL modifier set.");
                    }
                    if (obj.isNative()) {
                        throw new ClassConstraintException("Abstract method '" + tostring(obj) + "' must not have the ACC_NATIVE modifier set.");
                    }
                    if (obj.isPrivate()) {
                        throw new ClassConstraintException("Abstract method '" + tostring(obj) + "' must not have the ACC_PRIVATE modifier set.");
                    }
                    if (obj.isStatic()) {
                        throw new ClassConstraintException("Abstract method '" + tostring(obj) + "' must not have the ACC_STATIC modifier set.");
                    }
                    if (obj.isStrictfp()) {
                        throw new ClassConstraintException("Abstract method '" + tostring(obj) + "' must not have the ACC_STRICT modifier set.");
                    }
                    if (obj.isSynchronized()) {
                        throw new ClassConstraintException("Abstract method '" + tostring(obj) + "' must not have the ACC_SYNCHRONIZED modifier set.");
                    }
                }
            }
            else if (!name.equals("<clinit>")) {
                if (!obj.isPublic()) {
                    throw new ClassConstraintException("Interface method '" + tostring(obj) + "' must have the ACC_PUBLIC modifier set but hasn't!");
                }
                if (!obj.isAbstract()) {
                    throw new ClassConstraintException("Interface method '" + tostring(obj) + "' must have the ACC_STATIC modifier set but hasn't!");
                }
                if (obj.isPrivate() || obj.isProtected() || obj.isStatic() || obj.isFinal() || obj.isSynchronized() || obj.isNative() || obj.isStrictfp()) {
                    throw new ClassConstraintException("Interface method '" + tostring(obj) + "' must not have any of the ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL, ACC_SYNCHRONIZED, ACC_NATIVE, ACC_ABSTRACT, ACC_STRICT modifiers set.");
                }
            }
            if (name.equals("<init>") && (obj.isStatic() || obj.isFinal() || obj.isSynchronized() || obj.isNative() || obj.isAbstract())) {
                throw new ClassConstraintException("Instance initialization method '" + tostring(obj) + "' must not have any of the ACC_STATIC, ACC_FINAL, ACC_SYNCHRONIZED, ACC_NATIVE, ACC_ABSTRACT modifiers set.");
            }
            if (name.equals("<clinit>")) {
                if ((obj.getAccessFlags() & 0xFFFFF7FF) > 0) {
                    Pass2Verifier.this.addMessage("Class or interface initialization method '" + tostring(obj) + "' has superfluous access modifier(s) set: everything but ACC_STRICT is ignored.");
                }
                if (obj.isAbstract()) {
                    throw new ClassConstraintException("Class or interface initialization method '" + tostring(obj) + "' must not be abstract. This contradicts the Java Language Specification, Second Edition (which omits this constraint) but is common practice of existing verifiers.");
                }
            }
            if ((obj.getAccessFlags() & 0xFFFFF2C0) > 0) {
                Pass2Verifier.this.addMessage("Method '" + tostring(obj) + "' has access flag(s) other than ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL, ACC_SYNCHRONIZED, ACC_NATIVE, ACC_ABSTRACT, ACC_STRICT set (ignored).");
            }
            final String nameanddesc = String.valueOf(name) + sig;
            if (this.method_names_and_desc.contains(nameanddesc)) {
                throw new ClassConstraintException("No two methods (like '" + tostring(obj) + "') are allowed have same names and desciptors!");
            }
            this.method_names_and_desc.add(nameanddesc);
            final Attribute[] atts = obj.getAttributes();
            int num_code_atts = 0;
            for (int j = 0; j < atts.length; ++j) {
                if (!(atts[j] instanceof Code) && !(atts[j] instanceof ExceptionTable) && !(atts[j] instanceof Synthetic) && !(atts[j] instanceof Deprecated)) {
                    Pass2Verifier.this.addMessage("Attribute '" + tostring(atts[j]) + "' as an attribute of Method '" + tostring(obj) + "' is unknown and will therefore be ignored.");
                }
                if (!(atts[j] instanceof Code) && !(atts[j] instanceof ExceptionTable)) {
                    Pass2Verifier.this.addMessage("Attribute '" + tostring(atts[j]) + "' as an attribute of Method '" + tostring(obj) + "' is neither Code nor Exceptions and is therefore only of use for debuggers and such.");
                }
                if (atts[j] instanceof Code && (obj.isNative() || obj.isAbstract())) {
                    throw new ClassConstraintException("Native or abstract methods like '" + tostring(obj) + "' must not have a Code attribute like '" + tostring(atts[j]) + "'.");
                }
                if (atts[j] instanceof Code) {
                    ++num_code_atts;
                }
            }
            if (!obj.isNative() && !obj.isAbstract() && num_code_atts != 1) {
                throw new ClassConstraintException("Non-native, non-abstract methods like '" + tostring(obj) + "' must have exactly one Code attribute (found: " + num_code_atts + ").");
            }
        }
        
        public void visitSourceFile(final SourceFile obj) {
            this.checkIndex(obj, obj.getNameIndex(), this.CONST_Utf8);
            final String name = ((ConstantUtf8)this.cp.getConstant(obj.getNameIndex())).getBytes();
            if (!name.equals("SourceFile")) {
                throw new ClassConstraintException("The SourceFile attribute '" + tostring(obj) + "' is not correctly named 'SourceFile' but '" + name + "'.");
            }
            this.checkIndex(obj, obj.getSourceFileIndex(), this.CONST_Utf8);
            final String sourcefilename = ((ConstantUtf8)this.cp.getConstant(obj.getSourceFileIndex())).getBytes();
            final String sourcefilenamelc = sourcefilename.toLowerCase();
            if (sourcefilename.indexOf(47) != -1 || sourcefilename.indexOf(92) != -1 || sourcefilename.indexOf(58) != -1 || sourcefilenamelc.lastIndexOf(".java") == -1) {
                Pass2Verifier.this.addMessage("SourceFile attribute '" + tostring(obj) + "' has a funny name: remember not to confuse certain parsers working on javap's output. Also, this name ('" + sourcefilename + "') is considered an unqualified (simple) file name only.");
            }
        }
        
        public void visitDeprecated(final Deprecated obj) {
            this.checkIndex(obj, obj.getNameIndex(), this.CONST_Utf8);
            final String name = ((ConstantUtf8)this.cp.getConstant(obj.getNameIndex())).getBytes();
            if (!name.equals("Deprecated")) {
                throw new ClassConstraintException("The Deprecated attribute '" + tostring(obj) + "' is not correctly named 'Deprecated' but '" + name + "'.");
            }
        }
        
        public void visitSynthetic(final Synthetic obj) {
            this.checkIndex(obj, obj.getNameIndex(), this.CONST_Utf8);
            final String name = ((ConstantUtf8)this.cp.getConstant(obj.getNameIndex())).getBytes();
            if (!name.equals("Synthetic")) {
                throw new ClassConstraintException("The Synthetic attribute '" + tostring(obj) + "' is not correctly named 'Synthetic' but '" + name + "'.");
            }
        }
        
        public void visitInnerClasses(final InnerClasses obj) {
            this.checkIndex(obj, obj.getNameIndex(), this.CONST_Utf8);
            final String name = ((ConstantUtf8)this.cp.getConstant(obj.getNameIndex())).getBytes();
            if (!name.equals("InnerClasses")) {
                throw new ClassConstraintException("The InnerClasses attribute '" + tostring(obj) + "' is not correctly named 'InnerClasses' but '" + name + "'.");
            }
            final InnerClass[] ics = obj.getInnerClasses();
            for (int i = 0; i < ics.length; ++i) {
                this.checkIndex(obj, ics[i].getInnerClassIndex(), this.CONST_Class);
                final int outer_idx = ics[i].getOuterClassIndex();
                if (outer_idx != 0) {
                    this.checkIndex(obj, outer_idx, this.CONST_Class);
                }
                final int innername_idx = ics[i].getInnerNameIndex();
                if (innername_idx != 0) {
                    this.checkIndex(obj, innername_idx, this.CONST_Utf8);
                }
                int acc = ics[i].getInnerAccessFlags();
                acc &= 0xFFFFF9E0;
                if (acc != 0) {
                    Pass2Verifier.this.addMessage("Unknown access flag for inner class '" + tostring(ics[i]) + "' set (InnerClasses attribute '" + tostring(obj) + "').");
                }
            }
        }
        
        public void visitConstantValue(final ConstantValue obj) {
            this.checkIndex(obj, obj.getNameIndex(), this.CONST_Utf8);
            final String name = ((ConstantUtf8)this.cp.getConstant(obj.getNameIndex())).getBytes();
            if (!name.equals("ConstantValue")) {
                throw new ClassConstraintException("The ConstantValue attribute '" + tostring(obj) + "' is not correctly named 'ConstantValue' but '" + name + "'.");
            }
            final Object pred = this.carrier.predecessor();
            if (!(pred instanceof Field)) {
                return;
            }
            final Field f = (Field)pred;
            final Type field_type = Type.getType(((ConstantUtf8)this.cp.getConstant(f.getSignatureIndex())).getBytes());
            final int index = obj.getConstantValueIndex();
            if (index < 0 || index >= this.cplen) {
                throw new ClassConstraintException("Invalid index '" + index + "' used by '" + tostring(obj) + "'.");
            }
            final Constant c = this.cp.getConstant(index);
            if (this.CONST_Long.isInstance(c) && field_type.equals(Type.LONG)) {
                return;
            }
            if (this.CONST_Float.isInstance(c) && field_type.equals(Type.FLOAT)) {
                return;
            }
            if (this.CONST_Double.isInstance(c) && field_type.equals(Type.DOUBLE)) {
                return;
            }
            if (this.CONST_Integer.isInstance(c) && (field_type.equals(Type.INT) || field_type.equals(Type.SHORT) || field_type.equals(Type.CHAR) || field_type.equals(Type.BYTE) || field_type.equals(Type.BOOLEAN))) {
                return;
            }
            if (this.CONST_String.isInstance(c) && field_type.equals(Type.STRING)) {
                return;
            }
            throw new ClassConstraintException("Illegal type of ConstantValue '" + obj + "' embedding Constant '" + c + "'. It is referenced by field '" + tostring(f) + "' expecting a different type: '" + field_type + "'.");
        }
        
        public void visitCode(final Code obj) {
            this.checkIndex(obj, obj.getNameIndex(), this.CONST_Utf8);
            final String name = ((ConstantUtf8)this.cp.getConstant(obj.getNameIndex())).getBytes();
            if (!name.equals("Code")) {
                throw new ClassConstraintException("The Code attribute '" + tostring(obj) + "' is not correctly named 'Code' but '" + name + "'.");
            }
            Method m = null;
            if (!(this.carrier.predecessor() instanceof Method)) {
                Pass2Verifier.this.addMessage("Code attribute '" + tostring(obj) + "' is not declared in a method_info structure but in '" + this.carrier.predecessor() + "'. Ignored.");
                return;
            }
            m = (Method)this.carrier.predecessor();
            if (obj.getCode().length == 0) {
                throw new ClassConstraintException("Code array of Code attribute '" + tostring(obj) + "' (method '" + m + "') must not be empty.");
            }
            final CodeException[] exc_table = obj.getExceptionTable();
            for (int i = 0; i < exc_table.length; ++i) {
                final int exc_index = exc_table[i].getCatchType();
                if (exc_index != 0) {
                    this.checkIndex(obj, exc_index, this.CONST_Class);
                    final ConstantClass cc = (ConstantClass)this.cp.getConstant(exc_index);
                    this.checkIndex(cc, cc.getNameIndex(), this.CONST_Utf8);
                    final String cname = ((ConstantUtf8)this.cp.getConstant(cc.getNameIndex())).getBytes().replace('/', '.');
                    Verifier v = VerifierFactory.getVerifier(cname);
                    VerificationResult vr = v.doPass1();
                    if (vr != VerificationResult.VR_OK) {
                        throw new ClassConstraintException("Code attribute '" + tostring(obj) + "' (method '" + m + "') has an exception_table entry '" + tostring(exc_table[i]) + "' that references '" + cname + "' as an Exception but it does not pass verification pass 1: " + vr);
                    }
                    JavaClass e = Repository.lookupClass(cname);
                    final JavaClass t = Repository.lookupClass(Type.THROWABLE.getClassName());
                    for (JavaClass o = Repository.lookupClass(Type.OBJECT.getClassName()); e != o && e != t; e = Repository.lookupClass(e.getSuperclassName())) {
                        v = VerifierFactory.getVerifier(e.getSuperclassName());
                        vr = v.doPass1();
                        if (vr != VerificationResult.VR_OK) {
                            throw new ClassConstraintException("Code attribute '" + tostring(obj) + "' (method '" + m + "') has an exception_table entry '" + tostring(exc_table[i]) + "' that references '" + cname + "' as an Exception but '" + e.getSuperclassName() + "' in the ancestor hierachy does not pass verification pass 1: " + vr);
                        }
                    }
                    if (e != t) {
                        throw new ClassConstraintException("Code attribute '" + tostring(obj) + "' (method '" + m + "') has an exception_table entry '" + tostring(exc_table[i]) + "' that references '" + cname + "' as an Exception but it is not a subclass of '" + t.getClassName() + "'.");
                    }
                }
            }
            int method_number = -1;
            final Method[] ms = Repository.lookupClass(Pass2Verifier.this.myOwner.getClassName()).getMethods();
            for (int mn = 0; mn < ms.length; ++mn) {
                if (m == ms[mn]) {
                    method_number = mn;
                    break;
                }
            }
            if (method_number < 0) {
                throw new AssertionViolatedException("Could not find a known BCEL Method object in the corresponding BCEL JavaClass object.");
            }
            Pass2Verifier.this.localVariablesInfos[method_number] = new LocalVariablesInfo(obj.getMaxLocals());
            int num_of_lvt_attribs = 0;
            final Attribute[] atts = obj.getAttributes();
            for (int a = 0; a < atts.length; ++a) {
                if (!(atts[a] instanceof LineNumberTable) && !(atts[a] instanceof LocalVariableTable)) {
                    Pass2Verifier.this.addMessage("Attribute '" + tostring(atts[a]) + "' as an attribute of Code attribute '" + tostring(obj) + "' (method '" + m + "') is unknown and will therefore be ignored.");
                }
                else {
                    Pass2Verifier.this.addMessage("Attribute '" + tostring(atts[a]) + "' as an attribute of Code attribute '" + tostring(obj) + "' (method '" + m + "') will effectively be ignored and is only useful for debuggers and such.");
                }
                if (atts[a] instanceof LocalVariableTable) {
                    final LocalVariableTable lvt = (LocalVariableTable)atts[a];
                    this.checkIndex(lvt, lvt.getNameIndex(), this.CONST_Utf8);
                    final String lvtname = ((ConstantUtf8)this.cp.getConstant(lvt.getNameIndex())).getBytes();
                    if (!lvtname.equals("LocalVariableTable")) {
                        throw new ClassConstraintException("The LocalVariableTable attribute '" + tostring(lvt) + "' is not correctly named 'LocalVariableTable' but '" + lvtname + "'.");
                    }
                    final int max_locals = obj.getMaxLocals();
                    final LocalVariable[] localvariables = lvt.getLocalVariableTable();
                    for (int j = 0; j < localvariables.length; ++j) {
                        this.checkIndex(lvt, localvariables[j].getNameIndex(), this.CONST_Utf8);
                        final String localname = ((ConstantUtf8)this.cp.getConstant(localvariables[j].getNameIndex())).getBytes();
                        if (!validJavaIdentifier(localname)) {
                            throw new ClassConstraintException("LocalVariableTable '" + tostring(lvt) + "' references a local variable by the name '" + localname + "' which is not a legal Java simple name.");
                        }
                        this.checkIndex(lvt, localvariables[j].getSignatureIndex(), this.CONST_Utf8);
                        final String localsig = ((ConstantUtf8)this.cp.getConstant(localvariables[j].getSignatureIndex())).getBytes();
                        Type t2;
                        try {
                            t2 = Type.getType(localsig);
                        }
                        catch (ClassFormatError cfe) {
                            throw new ClassConstraintException("Illegal descriptor (==signature) '" + localsig + "' used by LocalVariable '" + tostring(localvariables[j]) + "' referenced by '" + tostring(lvt) + "'.");
                        }
                        final int localindex = localvariables[j].getIndex();
                        if (((t2 == Type.LONG || t2 == Type.DOUBLE) ? (localindex + 1) : localindex) >= obj.getMaxLocals()) {
                            throw new ClassConstraintException("LocalVariableTable attribute '" + tostring(lvt) + "' references a LocalVariable '" + tostring(localvariables[j]) + "' with an index that exceeds the surrounding Code attribute's max_locals value of '" + obj.getMaxLocals() + "'.");
                        }
                        try {
                            Pass2Verifier.this.localVariablesInfos[method_number].add(localindex, localname, localvariables[j].getStartPC(), localvariables[j].getLength(), t2);
                        }
                        catch (LocalVariableInfoInconsistentException lviie) {
                            throw new ClassConstraintException("Conflicting information in LocalVariableTable '" + tostring(lvt) + "' found in Code attribute '" + tostring(obj) + "' (method '" + tostring(m) + "'). " + lviie.getMessage());
                        }
                    }
                    if (++num_of_lvt_attribs > obj.getMaxLocals()) {
                        throw new ClassConstraintException("Number of LocalVariableTable attributes of Code attribute '" + tostring(obj) + "' (method '" + tostring(m) + "') exceeds number of local variable slots '" + obj.getMaxLocals() + "' ('There may be no more than one LocalVariableTable attribute per local variable in the Code attribute.').");
                    }
                }
            }
        }
        
        public void visitExceptionTable(final ExceptionTable obj) {
            this.checkIndex(obj, obj.getNameIndex(), this.CONST_Utf8);
            final String name = ((ConstantUtf8)this.cp.getConstant(obj.getNameIndex())).getBytes();
            if (!name.equals("Exceptions")) {
                throw new ClassConstraintException("The Exceptions attribute '" + tostring(obj) + "' is not correctly named 'Exceptions' but '" + name + "'.");
            }
            final int[] exc_indices = obj.getExceptionIndexTable();
            for (int i = 0; i < exc_indices.length; ++i) {
                this.checkIndex(obj, exc_indices[i], this.CONST_Class);
                final ConstantClass cc = (ConstantClass)this.cp.getConstant(exc_indices[i]);
                this.checkIndex(cc, cc.getNameIndex(), this.CONST_Utf8);
                final String cname = ((ConstantUtf8)this.cp.getConstant(cc.getNameIndex())).getBytes().replace('/', '.');
                Verifier v = VerifierFactory.getVerifier(cname);
                VerificationResult vr = v.doPass1();
                if (vr != VerificationResult.VR_OK) {
                    throw new ClassConstraintException("Exceptions attribute '" + tostring(obj) + "' references '" + cname + "' as an Exception but it does not pass verification pass 1: " + vr);
                }
                JavaClass e = Repository.lookupClass(cname);
                final JavaClass t = Repository.lookupClass(Type.THROWABLE.getClassName());
                for (JavaClass o = Repository.lookupClass(Type.OBJECT.getClassName()); e != o && e != t; e = Repository.lookupClass(e.getSuperclassName())) {
                    v = VerifierFactory.getVerifier(e.getSuperclassName());
                    vr = v.doPass1();
                    if (vr != VerificationResult.VR_OK) {
                        throw new ClassConstraintException("Exceptions attribute '" + tostring(obj) + "' references '" + cname + "' as an Exception but '" + e.getSuperclassName() + "' in the ancestor hierachy does not pass verification pass 1: " + vr);
                    }
                }
                if (e != t) {
                    throw new ClassConstraintException("Exceptions attribute '" + tostring(obj) + "' references '" + cname + "' as an Exception but it is not a subclass of '" + t.getClassName() + "'.");
                }
            }
        }
        
        public void visitLineNumberTable(final LineNumberTable obj) {
            this.checkIndex(obj, obj.getNameIndex(), this.CONST_Utf8);
            final String name = ((ConstantUtf8)this.cp.getConstant(obj.getNameIndex())).getBytes();
            if (!name.equals("LineNumberTable")) {
                throw new ClassConstraintException("The LineNumberTable attribute '" + tostring(obj) + "' is not correctly named 'LineNumberTable' but '" + name + "'.");
            }
        }
        
        public void visitLocalVariableTable(final LocalVariableTable obj) {
        }
        
        public void visitUnknown(final Unknown obj) {
            this.checkIndex(obj, obj.getNameIndex(), this.CONST_Utf8);
            Pass2Verifier.this.addMessage("Unknown attribute '" + tostring(obj) + "'. This attribute is not known in any context!");
        }
        
        public void visitLocalVariable(final LocalVariable obj) {
        }
        
        public void visitCodeException(final CodeException obj) {
        }
        
        public void visitConstantPool(final ConstantPool obj) {
        }
        
        public void visitInnerClass(final InnerClass obj) {
        }
        
        public void visitLineNumber(final LineNumber obj) {
        }
    }
    
    private class FAMRAV_Visitor extends EmptyVisitor implements Visitor
    {
        private final JavaClass jc;
        private final ConstantPool cp;
        
        FAMRAV_Visitor(final JavaClass _jc) {
            this.jc = _jc;
            this.cp = _jc.getConstantPool();
        }
        
        public void visitConstantFieldref(final ConstantFieldref obj) {
            if (obj.getTag() != 9) {
                throw new ClassConstraintException("ConstantFieldref '" + tostring(obj) + "' has wrong tag!");
            }
            final int name_and_type_index = obj.getNameAndTypeIndex();
            final ConstantNameAndType cnat = (ConstantNameAndType)this.cp.getConstant(name_and_type_index);
            final String name = ((ConstantUtf8)this.cp.getConstant(cnat.getNameIndex())).getBytes();
            if (!validFieldName(name)) {
                throw new ClassConstraintException("Invalid field name '" + name + "' referenced by '" + tostring(obj) + "'.");
            }
            final int class_index = obj.getClassIndex();
            final ConstantClass cc = (ConstantClass)this.cp.getConstant(class_index);
            final String className = ((ConstantUtf8)this.cp.getConstant(cc.getNameIndex())).getBytes();
            if (!validClassName(className)) {
                throw new ClassConstraintException("Illegal class name '" + className + "' used by '" + tostring(obj) + "'.");
            }
            final String sig = ((ConstantUtf8)this.cp.getConstant(cnat.getSignatureIndex())).getBytes();
            try {
                final Type t = Type.getType(sig);
            }
            catch (ClassFormatError cfe) {
                throw new ClassConstraintException("Illegal descriptor (==signature) '" + sig + "' used by '" + tostring(obj) + "'.");
            }
        }
        
        public void visitConstantMethodref(final ConstantMethodref obj) {
            if (obj.getTag() != 10) {
                throw new ClassConstraintException("ConstantMethodref '" + tostring(obj) + "' has wrong tag!");
            }
            final int name_and_type_index = obj.getNameAndTypeIndex();
            final ConstantNameAndType cnat = (ConstantNameAndType)this.cp.getConstant(name_and_type_index);
            final String name = ((ConstantUtf8)this.cp.getConstant(cnat.getNameIndex())).getBytes();
            if (!validClassMethodName(name)) {
                throw new ClassConstraintException("Invalid (non-interface) method name '" + name + "' referenced by '" + tostring(obj) + "'.");
            }
            final int class_index = obj.getClassIndex();
            final ConstantClass cc = (ConstantClass)this.cp.getConstant(class_index);
            final String className = ((ConstantUtf8)this.cp.getConstant(cc.getNameIndex())).getBytes();
            if (!validClassName(className)) {
                throw new ClassConstraintException("Illegal class name '" + className + "' used by '" + tostring(obj) + "'.");
            }
            final String sig = ((ConstantUtf8)this.cp.getConstant(cnat.getSignatureIndex())).getBytes();
            try {
                final Type t = Type.getReturnType(sig);
                final Type[] ts = Type.getArgumentTypes(sig);
                if (name.equals("<init>") && t != Type.VOID) {
                    throw new ClassConstraintException("Instance initialization method must have VOID return type.");
                }
            }
            catch (ClassFormatError cfe) {
                throw new ClassConstraintException("Illegal descriptor (==signature) '" + sig + "' used by '" + tostring(obj) + "'.");
            }
        }
        
        public void visitConstantInterfaceMethodref(final ConstantInterfaceMethodref obj) {
            if (obj.getTag() != 11) {
                throw new ClassConstraintException("ConstantInterfaceMethodref '" + tostring(obj) + "' has wrong tag!");
            }
            final int name_and_type_index = obj.getNameAndTypeIndex();
            final ConstantNameAndType cnat = (ConstantNameAndType)this.cp.getConstant(name_and_type_index);
            final String name = ((ConstantUtf8)this.cp.getConstant(cnat.getNameIndex())).getBytes();
            if (!validInterfaceMethodName(name)) {
                throw new ClassConstraintException("Invalid (interface) method name '" + name + "' referenced by '" + tostring(obj) + "'.");
            }
            final int class_index = obj.getClassIndex();
            final ConstantClass cc = (ConstantClass)this.cp.getConstant(class_index);
            final String className = ((ConstantUtf8)this.cp.getConstant(cc.getNameIndex())).getBytes();
            if (!validClassName(className)) {
                throw new ClassConstraintException("Illegal class name '" + className + "' used by '" + tostring(obj) + "'.");
            }
            final String sig = ((ConstantUtf8)this.cp.getConstant(cnat.getSignatureIndex())).getBytes();
            try {
                final Type t = Type.getReturnType(sig);
                final Type[] ts = Type.getArgumentTypes(sig);
                if (name.equals("<clinit>") && t != Type.VOID) {
                    Pass2Verifier.this.addMessage("Class or interface initialization method '<clinit>' usually has VOID return type instead of '" + t + "'. Note this is really not a requirement of The Java Virtual Machine Specification, Second Edition.");
                }
            }
            catch (ClassFormatError cfe) {
                throw new ClassConstraintException("Illegal descriptor (==signature) '" + sig + "' used by '" + tostring(obj) + "'.");
            }
        }
    }
    
    private class InnerClassDetector extends EmptyVisitor
    {
        private boolean hasInnerClass;
        private JavaClass jc;
        private ConstantPool cp;
        
        InnerClassDetector() {
            this.hasInnerClass = false;
        }
        
        public InnerClassDetector(final JavaClass _jc) {
            this.hasInnerClass = false;
            this.jc = _jc;
            this.cp = this.jc.getConstantPool();
            new DescendingVisitor(this.jc, this).visit();
        }
        
        public boolean innerClassReferenced() {
            return this.hasInnerClass;
        }
        
        public void visitConstantClass(final ConstantClass obj) {
            final Constant c = this.cp.getConstant(obj.getNameIndex());
            if (c instanceof ConstantUtf8) {
                final String classname = ((ConstantUtf8)c).getBytes();
                if (classname.startsWith(String.valueOf(this.jc.getClassName().replace('.', '/')) + "$")) {
                    this.hasInnerClass = true;
                }
            }
        }
    }
}
