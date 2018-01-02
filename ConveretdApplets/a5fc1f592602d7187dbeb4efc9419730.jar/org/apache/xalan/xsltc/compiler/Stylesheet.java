// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import java.util.Iterator;
import org.apache.bcel.generic.TargetLostException;
import org.apache.bcel.util.InstructionFinder;
import org.apache.bcel.generic.GETFIELD;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.LocalVariableGen;
import org.apache.bcel.generic.INVOKEINTERFACE;
import java.util.Collection;
import org.apache.bcel.generic.ISTORE;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.PUTFIELD;
import org.apache.bcel.generic.GETSTATIC;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.NEWARRAY;
import org.apache.bcel.generic.PUTSTATIC;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.ANEWARRAY;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.FieldGen;
import org.apache.xalan.xsltc.compiler.util.Util;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import java.util.StringTokenizer;
import org.apache.xml.utils.SystemIDResolver;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Hashtable;
import java.util.Vector;

public final class Stylesheet extends SyntaxTreeNode
{
    private String _version;
    private QName _name;
    private String _systemId;
    private Stylesheet _parentStylesheet;
    private Vector _globals;
    private Boolean _hasLocalParams;
    private String _className;
    private final Vector _templates;
    private Vector _allValidTemplates;
    private int _nextModeSerial;
    private final Hashtable _modes;
    private Mode _defaultMode;
    private final Hashtable _extensions;
    public Stylesheet _importedFrom;
    public Stylesheet _includedFrom;
    private Vector _includedStylesheets;
    private int _importPrecedence;
    private Hashtable _keys;
    private SourceLoader _loader;
    private boolean _numberFormattingUsed;
    private boolean _simplified;
    private boolean _multiDocument;
    private boolean _callsNodeset;
    private boolean _hasIdCall;
    private boolean _templateInlining;
    private Output _lastOutputElement;
    private Properties _outputProperties;
    private int _outputMethod;
    public static final int UNKNOWN_OUTPUT = 0;
    public static final int XML_OUTPUT = 1;
    public static final int HTML_OUTPUT = 2;
    public static final int TEXT_OUTPUT = 3;
    
    public Stylesheet() {
        this._globals = new Vector();
        this._hasLocalParams = null;
        this._templates = new Vector();
        this._allValidTemplates = null;
        this._nextModeSerial = 1;
        this._modes = new Hashtable();
        this._extensions = new Hashtable();
        this._importedFrom = null;
        this._includedFrom = null;
        this._includedStylesheets = null;
        this._importPrecedence = 1;
        this._keys = new Hashtable();
        this._loader = null;
        this._numberFormattingUsed = false;
        this._simplified = false;
        this._multiDocument = false;
        this._callsNodeset = false;
        this._hasIdCall = false;
        this._templateInlining = true;
        this._lastOutputElement = null;
        this._outputProperties = null;
        this._outputMethod = 0;
    }
    
    public int getOutputMethod() {
        return this._outputMethod;
    }
    
    private void checkOutputMethod() {
        if (this._lastOutputElement != null) {
            final String method = this._lastOutputElement.getOutputMethod();
            if (method != null) {
                if (method.equals("xml")) {
                    this._outputMethod = 1;
                }
                else if (method.equals("html")) {
                    this._outputMethod = 2;
                }
                else if (method.equals("text")) {
                    this._outputMethod = 3;
                }
            }
        }
    }
    
    public boolean getTemplateInlining() {
        return this._templateInlining;
    }
    
    public void setTemplateInlining(final boolean flag) {
        this._templateInlining = flag;
    }
    
    public boolean isSimplified() {
        return this._simplified;
    }
    
    public void setSimplified() {
        this._simplified = true;
    }
    
    public void setHasIdCall(final boolean flag) {
        this._hasIdCall = flag;
    }
    
    public void setOutputProperty(final String key, final String value) {
        if (this._outputProperties == null) {
            this._outputProperties = new Properties();
        }
        this._outputProperties.setProperty(key, value);
    }
    
    public void setOutputProperties(final Properties props) {
        this._outputProperties = props;
    }
    
    public Properties getOutputProperties() {
        return this._outputProperties;
    }
    
    public Output getLastOutputElement() {
        return this._lastOutputElement;
    }
    
    public void setMultiDocument(final boolean flag) {
        this._multiDocument = flag;
    }
    
    public boolean isMultiDocument() {
        return this._multiDocument;
    }
    
    public void setCallsNodeset(final boolean flag) {
        if (flag) {
            this.setMultiDocument(flag);
        }
        this._callsNodeset = flag;
    }
    
    public boolean callsNodeset() {
        return this._callsNodeset;
    }
    
    public void numberFormattingUsed() {
        this._numberFormattingUsed = true;
        final Stylesheet parent = this.getParentStylesheet();
        if (null != parent) {
            parent.numberFormattingUsed();
        }
    }
    
    public void setImportPrecedence(final int precedence) {
        this._importPrecedence = precedence;
        final Enumeration elements = this.elements();
        while (elements.hasMoreElements()) {
            final SyntaxTreeNode child = elements.nextElement();
            if (child instanceof Include) {
                final Stylesheet included = ((Include)child).getIncludedStylesheet();
                if (included == null || included._includedFrom != this) {
                    continue;
                }
                included.setImportPrecedence(precedence);
            }
        }
        if (this._importedFrom != null) {
            if (this._importedFrom.getImportPrecedence() < precedence) {
                final Parser parser = this.getParser();
                final int nextPrecedence = parser.getNextImportPrecedence();
                this._importedFrom.setImportPrecedence(nextPrecedence);
            }
        }
        else if (this._includedFrom != null && this._includedFrom.getImportPrecedence() != precedence) {
            this._includedFrom.setImportPrecedence(precedence);
        }
    }
    
    public int getImportPrecedence() {
        return this._importPrecedence;
    }
    
    public boolean checkForLoop(final String systemId) {
        return (this._systemId != null && this._systemId.equals(systemId)) || (this._parentStylesheet != null && this._parentStylesheet.checkForLoop(systemId));
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        this._name = this.makeStylesheetName("__stylesheet_");
    }
    
    public void setParentStylesheet(final Stylesheet parent) {
        this._parentStylesheet = parent;
    }
    
    public Stylesheet getParentStylesheet() {
        return this._parentStylesheet;
    }
    
    public void setImportingStylesheet(final Stylesheet parent) {
        (this._importedFrom = parent).addIncludedStylesheet(this);
    }
    
    public void setIncludingStylesheet(final Stylesheet parent) {
        (this._includedFrom = parent).addIncludedStylesheet(this);
    }
    
    public void addIncludedStylesheet(final Stylesheet child) {
        if (this._includedStylesheets == null) {
            this._includedStylesheets = new Vector();
        }
        this._includedStylesheets.addElement(child);
    }
    
    public void setSystemId(final String systemId) {
        if (systemId != null) {
            this._systemId = SystemIDResolver.getAbsoluteURI(systemId);
        }
    }
    
    public String getSystemId() {
        return this._systemId;
    }
    
    public void setSourceLoader(final SourceLoader loader) {
        this._loader = loader;
    }
    
    public SourceLoader getSourceLoader() {
        return this._loader;
    }
    
    private QName makeStylesheetName(final String prefix) {
        return this.getParser().getQName(prefix + this.getXSLTC().nextStylesheetSerial());
    }
    
    public boolean hasGlobals() {
        return this._globals.size() > 0;
    }
    
    public boolean hasLocalParams() {
        if (this._hasLocalParams == null) {
            final Vector templates = this.getAllValidTemplates();
            for (int n = templates.size(), i = 0; i < n; ++i) {
                final Template template = templates.elementAt(i);
                if (template.hasParams()) {
                    this._hasLocalParams = new Boolean(true);
                    return true;
                }
            }
            this._hasLocalParams = new Boolean(false);
            return false;
        }
        return this._hasLocalParams;
    }
    
    protected void addPrefixMapping(final String prefix, final String uri) {
        if (prefix.equals("") && uri.equals("http://www.w3.org/1999/xhtml")) {
            return;
        }
        super.addPrefixMapping(prefix, uri);
    }
    
    private void extensionURI(final String prefixes, final SymbolTable stable) {
        if (prefixes != null) {
            final StringTokenizer tokens = new StringTokenizer(prefixes);
            while (tokens.hasMoreTokens()) {
                final String prefix = tokens.nextToken();
                final String uri = this.lookupNamespace(prefix);
                if (uri != null) {
                    this._extensions.put(uri, prefix);
                }
            }
        }
    }
    
    public boolean isExtension(final String uri) {
        return this._extensions.get(uri) != null;
    }
    
    public void excludeExtensionPrefixes(final Parser parser) {
        final SymbolTable stable = parser.getSymbolTable();
        final String excludePrefixes = this.getAttribute("exclude-result-prefixes");
        final String extensionPrefixes = this.getAttribute("extension-element-prefixes");
        stable.excludeURI("http://www.w3.org/1999/XSL/Transform");
        stable.excludeNamespaces(excludePrefixes);
        stable.excludeNamespaces(extensionPrefixes);
        this.extensionURI(extensionPrefixes, stable);
    }
    
    public void parseContents(final Parser parser) {
        final SymbolTable stable = parser.getSymbolTable();
        this.addPrefixMapping("xml", "http://www.w3.org/XML/1998/namespace");
        final Stylesheet sheet = stable.addStylesheet(this._name, this);
        if (sheet != null) {
            final ErrorMsg err = new ErrorMsg("MULTIPLE_STYLESHEET_ERR", this);
            parser.reportError(3, err);
        }
        if (this._simplified) {
            stable.excludeURI("http://www.w3.org/1999/XSL/Transform");
            final Template template = new Template();
            template.parseSimplified(this, parser);
        }
        else {
            this.parseOwnChildren(parser);
        }
    }
    
    public final void parseOwnChildren(final Parser parser) {
        final Vector contents = this.getContents();
        final int count = contents.size();
        for (int i = 0; i < count; ++i) {
            final SyntaxTreeNode child = contents.elementAt(i);
            if (child instanceof VariableBase || child instanceof NamespaceAlias) {
                parser.getSymbolTable().setCurrentNode(child);
                child.parseContents(parser);
            }
        }
        for (int j = 0; j < count; ++j) {
            final SyntaxTreeNode child2 = contents.elementAt(j);
            if (!(child2 instanceof VariableBase) && !(child2 instanceof NamespaceAlias)) {
                parser.getSymbolTable().setCurrentNode(child2);
                child2.parseContents(parser);
            }
            if (!this._templateInlining && child2 instanceof Template) {
                final Template template = (Template)child2;
                final String name = "template$dot$" + template.getPosition();
                template.setName(parser.getQName(name));
            }
        }
    }
    
    public void processModes() {
        if (this._defaultMode == null) {
            this._defaultMode = new Mode(null, this, "");
        }
        this._defaultMode.processPatterns(this._keys);
        final Enumeration modes = this._modes.elements();
        while (modes.hasMoreElements()) {
            final Mode mode = modes.nextElement();
            mode.processPatterns(this._keys);
        }
    }
    
    private void compileModes(final ClassGenerator classGen) {
        this._defaultMode.compileApplyTemplates(classGen);
        final Enumeration modes = this._modes.elements();
        while (modes.hasMoreElements()) {
            final Mode mode = modes.nextElement();
            mode.compileApplyTemplates(classGen);
        }
    }
    
    public Mode getMode(final QName modeName) {
        if (modeName == null) {
            if (this._defaultMode == null) {
                this._defaultMode = new Mode(null, this, "");
            }
            return this._defaultMode;
        }
        Mode mode = this._modes.get(modeName);
        if (mode == null) {
            final String suffix = Integer.toString(this._nextModeSerial++);
            this._modes.put(modeName, mode = new Mode(modeName, this, suffix));
        }
        return mode;
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        for (int count = this._globals.size(), i = 0; i < count; ++i) {
            final VariableBase var = this._globals.elementAt(i);
            var.typeCheck(stable);
        }
        return this.typeCheckContents(stable);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        this.translate();
    }
    
    private void addDOMField(final ClassGenerator classGen) {
        final FieldGen fgen = new FieldGen(1, Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), "_dom", classGen.getConstantPool());
        classGen.addField(fgen.getField());
    }
    
    private void addStaticField(final ClassGenerator classGen, final String type, final String name) {
        final FieldGen fgen = new FieldGen(12, Util.getJCRefType(type), name, classGen.getConstantPool());
        classGen.addField(fgen.getField());
    }
    
    public void translate() {
        this._className = this.getXSLTC().getClassName();
        final ClassGenerator classGen = new ClassGenerator(this._className, "org.apache.xalan.xsltc.runtime.AbstractTranslet", "", 33, null, this);
        this.addDOMField(classGen);
        this.compileTransform(classGen);
        final Enumeration elements = this.elements();
        while (elements.hasMoreElements()) {
            final Object element = elements.nextElement();
            if (element instanceof Template) {
                final Template template = (Template)element;
                this.getMode(template.getModeName()).addTemplate(template);
            }
            else if (element instanceof AttributeSet) {
                ((AttributeSet)element).translate(classGen, null);
            }
            else {
                if (!(element instanceof Output)) {
                    continue;
                }
                final Output output = (Output)element;
                if (!output.enabled()) {
                    continue;
                }
                this._lastOutputElement = output;
            }
        }
        this.checkOutputMethod();
        this.processModes();
        this.compileModes(classGen);
        this.compileStaticInitializer(classGen);
        this.compileConstructor(classGen, this._lastOutputElement);
        if (!this.getParser().errorsFound()) {
            this.getXSLTC().dumpClass(classGen.getJavaClass());
        }
    }
    
    private void compileStaticInitializer(final ClassGenerator classGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = new InstructionList();
        final MethodGenerator staticConst = new MethodGenerator(9, org.apache.bcel.generic.Type.VOID, null, null, "<clinit>", this._className, il, cpg);
        this.addStaticField(classGen, "[Ljava/lang/String;", "_sNamesArray");
        this.addStaticField(classGen, "[Ljava/lang/String;", "_sUrisArray");
        this.addStaticField(classGen, "[I", "_sTypesArray");
        this.addStaticField(classGen, "[Ljava/lang/String;", "_sNamespaceArray");
        for (int charDataFieldCount = this.getXSLTC().getCharacterDataCount(), i = 0; i < charDataFieldCount; ++i) {
            this.addStaticField(classGen, "[C", "_scharData" + i);
        }
        final Vector namesIndex = this.getXSLTC().getNamesIndex();
        final int size = namesIndex.size();
        final String[] namesArray = new String[size];
        final String[] urisArray = new String[size];
        final int[] typesArray = new int[size];
        for (int j = 0; j < size; ++j) {
            final String encodedName = namesIndex.elementAt(j);
            int index;
            if ((index = encodedName.lastIndexOf(58)) > -1) {
                urisArray[j] = encodedName.substring(0, index);
            }
            ++index;
            if (encodedName.charAt(index) == '@') {
                typesArray[j] = 2;
                ++index;
            }
            else if (encodedName.charAt(index) == '?') {
                typesArray[j] = 13;
                ++index;
            }
            else {
                typesArray[j] = 1;
            }
            if (index == 0) {
                namesArray[j] = encodedName;
            }
            else {
                namesArray[j] = encodedName.substring(index);
            }
        }
        il.append(new PUSH(cpg, size));
        il.append(new ANEWARRAY(cpg.addClass("java.lang.String")));
        for (int k = 0; k < size; ++k) {
            final String name = namesArray[k];
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, k));
            il.append(new PUSH(cpg, name));
            il.append(InstructionConstants.AASTORE);
        }
        il.append(new PUTSTATIC(cpg.addFieldref(this._className, "_sNamesArray", "[Ljava/lang/String;")));
        il.append(new PUSH(cpg, size));
        il.append(new ANEWARRAY(cpg.addClass("java.lang.String")));
        for (int l = 0; l < size; ++l) {
            final String uri = urisArray[l];
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, l));
            il.append(new PUSH(cpg, uri));
            il.append(InstructionConstants.AASTORE);
        }
        il.append(new PUTSTATIC(cpg.addFieldref(this._className, "_sUrisArray", "[Ljava/lang/String;")));
        il.append(new PUSH(cpg, size));
        il.append(new NEWARRAY(org.apache.bcel.generic.Type.INT));
        for (int m = 0; m < size; ++m) {
            final int nodeType = typesArray[m];
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, m));
            il.append(new PUSH(cpg, nodeType));
            il.append(InstructionConstants.IASTORE);
        }
        il.append(new PUTSTATIC(cpg.addFieldref(this._className, "_sTypesArray", "[I")));
        final Vector namespaces = this.getXSLTC().getNamespaceIndex();
        il.append(new PUSH(cpg, namespaces.size()));
        il.append(new ANEWARRAY(cpg.addClass("java.lang.String")));
        for (int i2 = 0; i2 < namespaces.size(); ++i2) {
            final String ns = namespaces.elementAt(i2);
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, i2));
            il.append(new PUSH(cpg, ns));
            il.append(InstructionConstants.AASTORE);
        }
        il.append(new PUTSTATIC(cpg.addFieldref(this._className, "_sNamespaceArray", "[Ljava/lang/String;")));
        final int charDataCount = this.getXSLTC().getCharacterDataCount();
        final int toCharArray = cpg.addMethodref("java.lang.String", "toCharArray", "()[C");
        for (int i3 = 0; i3 < charDataCount; ++i3) {
            il.append(new PUSH(cpg, this.getXSLTC().getCharacterData(i3)));
            il.append(new INVOKEVIRTUAL(toCharArray));
            il.append(new PUTSTATIC(cpg.addFieldref(this._className, "_scharData" + i3, "[C")));
        }
        il.append(InstructionConstants.RETURN);
        staticConst.stripAttributes(true);
        staticConst.setMaxLocals();
        staticConst.setMaxStack();
        classGen.addMethod(staticConst.getMethod());
    }
    
    private void compileConstructor(final ClassGenerator classGen, final Output output) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = new InstructionList();
        final MethodGenerator constructor = new MethodGenerator(1, org.apache.bcel.generic.Type.VOID, null, null, "<init>", this._className, il, cpg);
        il.append(classGen.loadTranslet());
        il.append(new INVOKESPECIAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "<init>", "()V")));
        il.append(classGen.loadTranslet());
        il.append(new GETSTATIC(cpg.addFieldref(this._className, "_sNamesArray", "[Ljava/lang/String;")));
        il.append(new PUTFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "namesArray", "[Ljava/lang/String;")));
        il.append(classGen.loadTranslet());
        il.append(new GETSTATIC(cpg.addFieldref(this._className, "_sUrisArray", "[Ljava/lang/String;")));
        il.append(new PUTFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "urisArray", "[Ljava/lang/String;")));
        il.append(classGen.loadTranslet());
        il.append(new GETSTATIC(cpg.addFieldref(this._className, "_sTypesArray", "[I")));
        il.append(new PUTFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "typesArray", "[I")));
        il.append(classGen.loadTranslet());
        il.append(new GETSTATIC(cpg.addFieldref(this._className, "_sNamespaceArray", "[Ljava/lang/String;")));
        il.append(new PUTFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "namespaceArray", "[Ljava/lang/String;")));
        il.append(classGen.loadTranslet());
        il.append(new PUSH(cpg, 101));
        il.append(new PUTFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "transletVersion", "I")));
        if (this._hasIdCall) {
            il.append(classGen.loadTranslet());
            il.append(new PUSH(cpg, Boolean.TRUE));
            il.append(new PUTFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_hasIdCall", "Z")));
        }
        if (output != null) {
            output.translate(classGen, constructor);
        }
        if (this._numberFormattingUsed) {
            DecimalFormatting.translateDefaultDFS(classGen, constructor);
        }
        il.append(InstructionConstants.RETURN);
        constructor.stripAttributes(true);
        constructor.setMaxLocals();
        constructor.setMaxStack();
        classGen.addMethod(constructor.getMethod());
    }
    
    private String compileTopLevel(final ClassGenerator classGen, final Enumeration elements) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final org.apache.bcel.generic.Type[] argTypes = { Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), Util.getJCRefType("Lorg/apache/xml/serializer/SerializationHandler;") };
        final String[] argNames = { "document", "iterator", "handler" };
        final InstructionList il = new InstructionList();
        final MethodGenerator toplevel = new MethodGenerator(1, org.apache.bcel.generic.Type.VOID, argTypes, argNames, "topLevel", this._className, il, classGen.getConstantPool());
        toplevel.addException("org.apache.xalan.xsltc.TransletException");
        final LocalVariableGen current = toplevel.addLocalVariable("current", org.apache.bcel.generic.Type.INT, il.getEnd(), null);
        final int setFilter = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "setFilter", "(Lorg/apache/xalan/xsltc/StripFilter;)V");
        il.append(new PUSH(cpg, 0));
        il.append(new ISTORE(current.getIndex()));
        this._globals = this.resolveReferences(this._globals);
        for (int count = this._globals.size(), i = 0; i < count; ++i) {
            final VariableBase var = this._globals.elementAt(i);
            var.translate(classGen, toplevel);
        }
        final Vector whitespaceRules = new Vector();
        while (elements.hasMoreElements()) {
            final Object element = elements.nextElement();
            if (element instanceof DecimalFormatting) {
                ((DecimalFormatting)element).translate(classGen, toplevel);
            }
            else {
                if (!(element instanceof Whitespace)) {
                    continue;
                }
                whitespaceRules.addAll(((Whitespace)element).getRules());
            }
        }
        if (whitespaceRules.size() > 0) {
            Whitespace.translateRules(whitespaceRules, classGen);
        }
        if (classGen.containsMethod("stripSpace", "(Lorg/apache/xalan/xsltc/DOM;II)Z") != null) {
            il.append(toplevel.loadDOM());
            il.append(classGen.loadTranslet());
            il.append(new INVOKEINTERFACE(setFilter, 2));
        }
        il.append(InstructionConstants.RETURN);
        toplevel.stripAttributes(true);
        toplevel.setMaxLocals();
        toplevel.setMaxStack();
        toplevel.removeNOPs();
        classGen.addMethod(toplevel.getMethod());
        return "(Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/serializer/SerializationHandler;)V";
    }
    
    private Vector resolveReferences(final Vector input) {
        for (int i = 0; i < input.size(); ++i) {
            final VariableBase var = input.elementAt(i);
            final Vector dep = var.getDependencies();
            for (int depSize = (dep != null) ? dep.size() : 0, j = 0; j < depSize; ++j) {
                final VariableBase depVar = dep.elementAt(j);
                if (!input.contains(depVar)) {
                    input.addElement(depVar);
                }
            }
        }
        final Vector result = new Vector();
        while (input.size() > 0) {
            boolean changed = false;
            int k = 0;
            while (k < input.size()) {
                final VariableBase var2 = input.elementAt(k);
                final Vector dep2 = var2.getDependencies();
                if (dep2 == null || result.containsAll(dep2)) {
                    result.addElement(var2);
                    input.remove(k);
                    changed = true;
                }
                else {
                    ++k;
                }
            }
            if (!changed) {
                final ErrorMsg err = new ErrorMsg("CIRCULAR_VARIABLE_ERR", input.toString(), this);
                this.getParser().reportError(3, err);
                return result;
            }
        }
        return result;
    }
    
    private String compileBuildKeys(final ClassGenerator classGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final org.apache.bcel.generic.Type[] argTypes = { Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), Util.getJCRefType("Lorg/apache/xml/serializer/SerializationHandler;"), org.apache.bcel.generic.Type.INT };
        final String[] argNames = { "document", "iterator", "handler", "current" };
        final InstructionList il = new InstructionList();
        final MethodGenerator buildKeys = new MethodGenerator(1, org.apache.bcel.generic.Type.VOID, argTypes, argNames, "buildKeys", this._className, il, classGen.getConstantPool());
        buildKeys.addException("org.apache.xalan.xsltc.TransletException");
        final Enumeration elements = this.elements();
        while (elements.hasMoreElements()) {
            final Object element = elements.nextElement();
            if (element instanceof Key) {
                final Key key = (Key)element;
                key.translate(classGen, buildKeys);
                this._keys.put(key.getName(), key);
            }
        }
        il.append(InstructionConstants.RETURN);
        buildKeys.stripAttributes(true);
        buildKeys.setMaxLocals();
        buildKeys.setMaxStack();
        buildKeys.removeNOPs();
        classGen.addMethod(buildKeys.getMethod());
        return "(Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/serializer/SerializationHandler;I)V";
    }
    
    private void compileTransform(final ClassGenerator classGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final org.apache.bcel.generic.Type[] argTypes = { Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), Util.getJCRefType("Lorg/apache/xml/serializer/SerializationHandler;") };
        final String[] argNames = { "document", "iterator", "handler" };
        final InstructionList il = new InstructionList();
        final MethodGenerator transf = new MethodGenerator(1, org.apache.bcel.generic.Type.VOID, argTypes, argNames, "transform", this._className, il, classGen.getConstantPool());
        transf.addException("org.apache.xalan.xsltc.TransletException");
        final LocalVariableGen current = transf.addLocalVariable("current", org.apache.bcel.generic.Type.INT, il.getEnd(), null);
        final String applyTemplatesSig = classGen.getApplyTemplatesSig();
        final int applyTemplates = cpg.addMethodref(this.getClassName(), "applyTemplates", applyTemplatesSig);
        final int domField = cpg.addFieldref(this.getClassName(), "_dom", "Lorg/apache/xalan/xsltc/DOM;");
        il.append(classGen.loadTranslet());
        if (this.isMultiDocument()) {
            il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.MultiDOM")));
            il.append(InstructionConstants.DUP);
        }
        il.append(classGen.loadTranslet());
        il.append(transf.loadDOM());
        il.append(new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "makeDOMAdapter", "(Lorg/apache/xalan/xsltc/DOM;)Lorg/apache/xalan/xsltc/dom/DOMAdapter;")));
        if (this.isMultiDocument()) {
            final int init = cpg.addMethodref("org.apache.xalan.xsltc.dom.MultiDOM", "<init>", "(Lorg/apache/xalan/xsltc/DOM;)V");
            il.append(new INVOKESPECIAL(init));
        }
        il.append(new PUTFIELD(domField));
        il.append(new PUSH(cpg, 0));
        il.append(new ISTORE(current.getIndex()));
        il.append(classGen.loadTranslet());
        il.append(transf.loadHandler());
        final int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "transferOutputSettings", "(Lorg/apache/xml/serializer/SerializationHandler;)V");
        il.append(new INVOKEVIRTUAL(index));
        final String keySig = this.compileBuildKeys(classGen);
        final int keyIdx = cpg.addMethodref(this.getClassName(), "buildKeys", keySig);
        il.append(classGen.loadTranslet());
        il.append(classGen.loadTranslet());
        il.append(new GETFIELD(domField));
        il.append(transf.loadIterator());
        il.append(transf.loadHandler());
        il.append(new PUSH(cpg, 0));
        il.append(new INVOKEVIRTUAL(keyIdx));
        final Enumeration toplevel = this.elements();
        if (this._globals.size() > 0 || toplevel.hasMoreElements()) {
            final String topLevelSig = this.compileTopLevel(classGen, toplevel);
            final int topLevelIdx = cpg.addMethodref(this.getClassName(), "topLevel", topLevelSig);
            il.append(classGen.loadTranslet());
            il.append(classGen.loadTranslet());
            il.append(new GETFIELD(domField));
            il.append(transf.loadIterator());
            il.append(transf.loadHandler());
            il.append(new INVOKEVIRTUAL(topLevelIdx));
        }
        il.append(transf.loadHandler());
        il.append(transf.startDocument());
        il.append(classGen.loadTranslet());
        il.append(classGen.loadTranslet());
        il.append(new GETFIELD(domField));
        il.append(transf.loadIterator());
        il.append(transf.loadHandler());
        il.append(new INVOKEVIRTUAL(applyTemplates));
        il.append(transf.loadHandler());
        il.append(transf.endDocument());
        il.append(InstructionConstants.RETURN);
        transf.stripAttributes(true);
        transf.setMaxLocals();
        transf.setMaxStack();
        transf.removeNOPs();
        classGen.addMethod(transf.getMethod());
    }
    
    private void peepHoleOptimization(final MethodGenerator methodGen) {
        final String pattern = "`ALOAD'`POP'`Instruction'";
        final InstructionList il = methodGen.getInstructionList();
        final InstructionFinder find = new InstructionFinder(il);
        final Iterator iter = find.search("`ALOAD'`POP'`Instruction'");
        while (iter.hasNext()) {
            final InstructionHandle[] match = iter.next();
            try {
                il.delete(match[0], match[1]);
            }
            catch (TargetLostException ex) {}
        }
    }
    
    public int addParam(final Param param) {
        this._globals.addElement(param);
        return this._globals.size() - 1;
    }
    
    public int addVariable(final Variable global) {
        this._globals.addElement(global);
        return this._globals.size() - 1;
    }
    
    public void display(final int indent) {
        this.indent(indent);
        Util.println("Stylesheet");
        this.displayContents(indent + 4);
    }
    
    public String getNamespace(final String prefix) {
        return this.lookupNamespace(prefix);
    }
    
    public String getClassName() {
        return this._className;
    }
    
    public Vector getTemplates() {
        return this._templates;
    }
    
    public Vector getAllValidTemplates() {
        if (this._includedStylesheets == null) {
            return this._templates;
        }
        if (this._allValidTemplates == null) {
            final Vector templates = new Vector();
            for (int size = this._includedStylesheets.size(), i = 0; i < size; ++i) {
                final Stylesheet included = this._includedStylesheets.elementAt(i);
                templates.addAll(included.getAllValidTemplates());
            }
            templates.addAll(this._templates);
            if (this._parentStylesheet != null) {
                return templates;
            }
            this._allValidTemplates = templates;
        }
        return this._allValidTemplates;
    }
    
    protected void addTemplate(final Template template) {
        this._templates.addElement(template);
    }
}
