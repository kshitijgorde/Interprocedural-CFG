// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.xml.sax.SAXException;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.templates.TemplateList;
import org.apache.xalan.templates.StylesheetRoot;
import org.apache.xml.utils.QName;
import org.apache.xalan.templates.ElemAttributeSet;
import org.apache.xalan.templates.ElemUse;
import org.apache.xml.utils.synthetic.reflection.Method;
import org.apache.xml.utils.synthetic.reflection.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.apache.xalan.templates.ElemTemplate;
import org.apache.xml.utils.synthetic.SynthesisException;
import org.apache.xml.utils.synthetic.JavaUtils;
import java.io.IOException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.StringTokenizer;
import java.io.File;
import java.util.Enumeration;
import org.apache.xalan.templates.XMLNSDecl;
import org.apache.xalan.templates.ElemLiteralResult;
import org.apache.xalan.templates.ElemAttribute;
import org.apache.xalan.templates.ElemTemplateElement;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.PrefixResolver;
import org.w3c.dom.Node;
import org.apache.xpath.XPathContext;
import java.util.Vector;
import org.apache.xalan.templates.AVT;
import javax.xml.transform.TransformerConfigurationException;
import java.util.Stack;
import org.apache.xml.utils.synthetic.Class;

public class CompilingStylesheetHandler extends StylesheetHandler
{
    final int MAY_THROW_SAX_EXCEPTION = 1;
    private static final Class sClassCompiledTemplate;
    private static final Class sClassObjectArray;
    Stack attrSetStack;
    int uniqueVarSuffix;
    static int templateCounter;
    static /* synthetic */ java.lang.Class class$org$apache$xalan$processor$CompiledTemplate;
    static /* synthetic */ java.lang.Class array$Ljava$lang$Object;
    static /* synthetic */ java.lang.Class class$org$apache$xalan$templates$ElemTemplate;
    static /* synthetic */ java.lang.Class class$org$apache$xalan$transformer$TransformerImpl;
    static /* synthetic */ java.lang.Class class$org$w3c$dom$Node;
    static /* synthetic */ java.lang.Class class$org$apache$xml$utils$QName;
    static /* synthetic */ java.lang.Class class$javax$xml$transform$TransformerException;
    
    static {
        sClassCompiledTemplate = Class.forClass((CompilingStylesheetHandler.class$org$apache$xalan$processor$CompiledTemplate != null) ? CompilingStylesheetHandler.class$org$apache$xalan$processor$CompiledTemplate : (CompilingStylesheetHandler.class$org$apache$xalan$processor$CompiledTemplate = class$("org.apache.xalan.processor.CompiledTemplate")));
        sClassObjectArray = Class.forClass((CompilingStylesheetHandler.array$Ljava$lang$Object != null) ? CompilingStylesheetHandler.array$Ljava$lang$Object : (CompilingStylesheetHandler.array$Ljava$lang$Object = class$("[Ljava.lang.Object;")));
        CompilingStylesheetHandler.templateCounter = 0;
    }
    
    public CompilingStylesheetHandler(final TransformerFactoryImpl processor) throws TransformerConfigurationException {
        super(processor);
        this.attrSetStack = new Stack();
        this.uniqueVarSuffix = 0;
    }
    
    static /* synthetic */ java.lang.Class class$(final String class$) {
        try {
            return java.lang.Class.forName(class$);
        }
        catch (ClassNotFoundException forName) {
            throw new NoClassDefFoundError(forName.getMessage());
        }
    }
    
    String compileAVTvalue(final AVT avt, final StringBuffer body, final Vector interpretVector) {
        if (avt.isContextInsensitive()) {
            try {
                return this.makeQuotedString(avt.evaluate(null, null, null));
            }
            catch (TransformerException e) {
                final String s = ">UNEXPECTED ERROR evaluating context-insensitive AVT<";
                System.err.println(s);
                e.printStackTrace();
                return "\"" + s + '\"';
            }
        }
        final int offset = interpretVector.size();
        interpretVector.addElement(avt);
        return "( ((org.apache.xalan.templates.AVT)m_interpretArray[" + offset + "]).evaluate(transformer.getXPathContext(),sourceNode,this,new StringBuffer()) )";
    }
    
    void compileChildTemplates(final ElemTemplateElement source, final StringBuffer body, final Vector interpretVector) {
        int maythrow = 0;
        ++this.uniqueVarSuffix;
        if (source.getFirstChildElem() != null) {
            final String savedLocatorName = "savedLocator" + this.uniqueVarSuffix;
            final String varstackName = "varstack" + this.uniqueVarSuffix;
            body.append("\n// Unwound Transformer.executeChildTemplates: //\n\n// We need to push an element frame in the variables stack,\n// so all the variables can be popped at once when we're done.\norg.apache.xpath.VariableStack " + varstackName + " = transformer.getXPathContext().getVarStack();\n" + varstackName + ".pushElemFrame();\n" + "javax.xml.transform.SourceLocator " + savedLocatorName + " = xctxt.getSAXLocator();\n");
            body.append("try {\n\n");
            for (ElemTemplateElement kid = source.getFirstChildElem(); kid != null; kid = kid.getNextSiblingElem()) {
                maythrow |= this.compileElemTemplateElement(kid, body, interpretVector);
            }
            body.append("\n\n}\n");
            if ((maythrow & 0x1) != 0x0) {
                body.append("catch(org.xml.sax.SAXException se) {\n  throw new javax.xml.transform.TransformerException(se);\n}\n");
            }
            body.append("finally {\n  xctxt.setSAXLocator(" + savedLocatorName + ");\n" + "  // Pop all the variables in this element frame.\n" + "  " + varstackName + ".popElemFrame();\n" + "}\n");
        }
    }
    
    void compileElemAttribute(final ElemAttribute ea, final StringBuffer body, final Vector interpretVector) {
        ++this.uniqueVarSuffix;
        final String attrName = "attrName" + this.uniqueVarSuffix;
        final String val = "val" + this.uniqueVarSuffix;
        final String attrNameSpace = "attrNameSpace" + this.uniqueVarSuffix;
        final String prefix = "prefix" + this.uniqueVarSuffix;
        final String ns = "ns" + this.uniqueVarSuffix;
        final String attributeHandled = "attributeHandled" + this.uniqueVarSuffix;
        final String nsprefix = "nsprefix" + this.uniqueVarSuffix;
        final String ex = "ex" + this.uniqueVarSuffix;
        final String localName = "localName" + this.uniqueVarSuffix;
        final String origAttrName = this.compileAVTvalue(ea.getName(), body, interpretVector);
        body.append("if(null == rhandler.getPendingElementName())\n{\ntransformer.getMsgMgr().warn(org.apache.xalan.res.XSLTErrorResources.WG_ILLEGAL_ATTRIBUTE_NAME, new Object[]{" + origAttrName + "}); \n" + "// warn(templateChild, sourceNode, \"Trying to add attribute after element child has been added, ignoring...\");\n" + "}\n");
        if (origAttrName == null) {
            return;
        }
        body.append("boolean " + attributeHandled + "=false;\n");
        body.append("String " + attrName + "=" + origAttrName + ";\n");
        final String strval = this.compileTransformToString(ea, body, interpretVector);
        body.append("String " + val + "=" + strval + ";\n");
        body.append("if(null == rhandler.getPendingElementName())\n{\ntransformer.getMsgMgr().warn(org.apache.xalan.res.XSLTErrorResources.WG_ILLEGAL_ATTRIBUTE_NAME, new Object[]{" + origAttrName + "}); \n" + "// warn(templateChild, sourceNode, \"Trying to add attribute after element child has been added, ignoring...\");\n" + "}\n" + "if(null==" + attrName + ")\n return;\n\n");
        body.append("String " + attrNameSpace + "=null; // by default\n");
        if (ea.getNamespace() != null) {
            final String avtValueExpression = this.compileAVTvalue(ea.getNamespace(), body, interpretVector);
            body.append(String.valueOf(attrNameSpace) + "=" + avtValueExpression + ";\n" + "if(null!=" + attrNameSpace + " && " + attrNameSpace + ".length()>0)\n" + "{\n" + "  String " + prefix + "=rhandler.getPrefix(" + attrNameSpace + ");\n" + "  if(null==" + prefix + ")\n" + "  {\n" + "    " + prefix + "=rhandler.getNewUniqueNSPrefix();\n" + "    rhandler.startPrefixMapping(" + prefix + "," + attrNameSpace + ");\n" + "  }\n" + "  " + attrName + "=(" + prefix + "+':'+org.apache.xml.utils.QName.getLocalPart(" + attrName + "));\n" + "}\n");
        }
        body.append("if(org.apache.xml.utils.QName.isXMLNSDecl(" + origAttrName + "))\n" + "{ // Just declare namespace prefix \n" + "  String " + prefix + "=org.apache.xml.utils.QName.getPrefixFromXMLNSDecl(" + origAttrName + ");\n" + "  String " + ns + "=rhandler.getURI(" + prefix + ");\n" + "  if(null==" + ns + ")\n" + "    rhandler.startPrefixMapping(" + prefix + "," + val + ");\n");
        body.append("  " + attributeHandled + "=true;\n");
        body.append("}\nelse\n{\n  String " + nsprefix + "=org.apache.xml.utils.QName.getPrefixPart(" + origAttrName + ");\n" + "  if(null==" + nsprefix + ") " + nsprefix + "=\"\";\n" + attrNameSpace + "=nsSupport.getURI(" + nsprefix + ");\n" + "if(!" + attributeHandled + ")\n{\n" + "String " + localName + "=org.apache.xml.utils.QName.getLocalPart(" + attrName + ");\n" + "rhandler.addAttribute(" + attrNameSpace + "," + localName + "," + attrName + ",\"CDATA\"," + val + ");\n" + "} //end attributeHandled\n" + "} //end else\n");
    }
    
    int compileElemLiteralResult(final ElemLiteralResult ele, final StringBuffer body, final Vector interpretVector) {
        final int maythrow = 0;
        ++this.uniqueVarSuffix;
        body.append("rhandler.startElement(\"" + ele.getNamespace() + "\",\"" + ele.getLocalName() + "\",\"" + ele.getRawName() + "\");\n");
        this.compileUseAttrSet(ele, body, interpretVector);
        final Vector prefixTable = ele.getPrefixes();
        final int n = prefixTable.size();
        final boolean newNSlevel = n > 0;
        if (newNSlevel) {
            body.append("nsSupport.pushContext();\n");
        }
        for (int i = 0; i < n; ++i) {
            final XMLNSDecl decl = prefixTable.elementAt(i);
            if (!decl.getIsExcluded()) {
                body.append("rhandler.startPrefixMapping(\"" + decl.getPrefix() + "\",\"" + decl.getURI() + "\");\n");
            }
            body.append("nsSupport.declarePrefix(\"" + decl.getPrefix() + "\",\"" + decl.getURI() + "\");\n");
        }
        final Enumeration avts = ele.enumerateLiteralResultAttributes();
        if (avts != null) {
            while (avts.hasMoreElements()) {
                final AVT avt = avts.nextElement();
                String avtValueExpression = null;
                final boolean literal = avt.isContextInsensitive();
                if (literal) {
                    try {
                        avtValueExpression = this.makeQuotedString(avt.evaluate(null, null, null));
                    }
                    catch (TransformerException ex) {}
                }
                else {
                    final int offset = interpretVector.size();
                    interpretVector.addElement(avt);
                    body.append("avtStringedValue=((org.apache.xalan.templates.AVT)(m_interpretArray[" + offset + "])" + ").evaluate(xctxt,sourceNode,this,new StringBuffer());\n" + "if(null!=avtStringedValue)\n{\n");
                    avtValueExpression = "avtStringedValue";
                }
                body.append("rhandler.addAttribute(\"" + avt.getURI() + "\",\"" + avt.getName() + "\",\"" + avt.getRawName() + "\",\"CDATA\"," + avtValueExpression + ");\n");
                if (!literal) {
                    body.append("} // endif\n");
                }
            }
        }
        this.compileChildTemplates(ele, body, interpretVector);
        body.append("rhandler.endElement(\"" + ele.getNamespace() + "\",\"" + ele.getLocalName() + "\",\"" + ele.getRawName() + "\");\n");
        if (newNSlevel) {
            body.append("nsSupport.popContext();\n");
        }
        return maythrow | 0x1;
    }
    
    int compileElemTemplateElement(final ElemTemplateElement kid, final StringBuffer body, final Vector interpretVector) {
        int maythrow = 0;
        ++this.uniqueVarSuffix;
        switch (kid.getXSLToken()) {
            case 77: {
                maythrow = this.compileElemLiteralResult((ElemLiteralResult)kid, body, interpretVector);
                break;
            }
            default: {
                final int offset = interpretVector.size();
                interpretVector.addElement(kid);
                body.append("((org.apache.xalan.templates.ElemTemplateElement)m_interpretArray[" + offset + "]).execute(transformer,sourceNode,mode);\n");
                break;
            }
        }
        return maythrow;
    }
    
    java.lang.Class compileSyntheticClass(final Class tClass, final String classLocation) {
        java.lang.Class resolved = null;
        final int fnstart = classLocation.lastIndexOf(File.separator);
        final StringBuffer subdir = new StringBuffer((fnstart >= 0) ? classLocation.substring(0, fnstart) : ".");
        final StringTokenizer parts = new StringTokenizer(tClass.getPackageName(), ".");
        while (parts.hasMoreTokens()) {
            subdir.append(File.separator).append(parts.nextToken());
        }
        if (fnstart < 0) {
            subdir.append(File.separator);
        }
        File jfile = new File(subdir.toString());
        jfile.mkdirs();
        subdir.append(tClass.getShortName()).append(".java");
        final String filename = subdir.toString();
        jfile = new File(filename);
        try {
            final PrintWriter out = new PrintWriter(new FileWriter(filename));
            tClass.toSource(out, 0);
            out.close();
        }
        catch (IOException ex) {
            System.err.println("ERR: File open failed for " + filename);
            ex.printStackTrace();
            return null;
        }
        final String classpath = System.getProperty("java.class.path");
        final String javac_options = System.getProperty("org.apache.xalan.processor.CompilingStylesheetHandler.options", "");
        final boolean debug = javac_options.indexOf("-g") >= 0;
        JavaUtils.setDebug(debug);
        final boolean compileOK = JavaUtils.JDKcompile(filename, classpath);
        if (compileOK) {
            if (debug) {
                System.err.println("\tCompilation successful. Debug specified, .java file retained.");
            }
            else if (jfile.exists()) {
                jfile.delete();
            }
            try {
                resolved = java.lang.Class.forName(tClass.getName());
                tClass.setRealClass(resolved);
            }
            catch (ClassNotFoundException e) {
                System.err.println("ERR: synthesized Template class load failed for " + tClass.getName());
                e.printStackTrace();
            }
            catch (SynthesisException e2) {
                System.err.println("ERR: synthesized Template class realization failed for " + tClass.getName());
                e2.printStackTrace();
            }
        }
        else {
            if (debug) {
                System.err.println("\tTemplate compilation failed; retaining .java file");
            }
            System.err.println("ERR: Java compilation failed for " + filename);
            System.exit(1);
        }
        return resolved;
    }
    
    ElemTemplate compileTemplate(final ElemTemplate source) {
        ElemTemplate instance = source;
        final String className = this.generateUniqueClassName("org.apache.xalan.processor.ACompiledTemplate");
        try {
            final Class tClass = Class.declareClass(className);
            tClass.setModifiers(1);
            tClass.setSuperClass(CompilingStylesheetHandler.sClassCompiledTemplate);
            final Constructor ctor = tClass.declareConstructor();
            ctor.setModifiers(1);
            ctor.addParameter(Class.forClass((CompilingStylesheetHandler.class$org$apache$xalan$templates$ElemTemplate != null) ? CompilingStylesheetHandler.class$org$apache$xalan$templates$ElemTemplate : (CompilingStylesheetHandler.class$org$apache$xalan$templates$ElemTemplate = class$("org.apache.xalan.templates.ElemTemplate"))), "original");
            ctor.addParameter(CompilingStylesheetHandler.sClassObjectArray, "interpretArray");
            ctor.getBody().append("super(original,\n\t" + source.getLineNumber() + ',' + source.getColumnNumber() + ",\n" + '\t' + this.makeQuotedString(source.getPublicId()) + ",\n" + '\t' + this.makeQuotedString(source.getSystemId()) + ",\n" + "\tinterpretArray);\n");
            final Vector interpretVector = new Vector();
            final Method exec = tClass.declareMethod("execute");
            exec.setModifiers(1);
            exec.addParameter(Class.forClass((CompilingStylesheetHandler.class$org$apache$xalan$transformer$TransformerImpl != null) ? CompilingStylesheetHandler.class$org$apache$xalan$transformer$TransformerImpl : (CompilingStylesheetHandler.class$org$apache$xalan$transformer$TransformerImpl = class$("org.apache.xalan.transformer.TransformerImpl"))), "transformer");
            exec.addParameter(Class.forClass((CompilingStylesheetHandler.class$org$w3c$dom$Node != null) ? CompilingStylesheetHandler.class$org$w3c$dom$Node : (CompilingStylesheetHandler.class$org$w3c$dom$Node = class$("org.w3c.dom.Node"))), "sourceNode");
            exec.addParameter(Class.forClass((CompilingStylesheetHandler.class$org$apache$xml$utils$QName != null) ? CompilingStylesheetHandler.class$org$apache$xml$utils$QName : (CompilingStylesheetHandler.class$org$apache$xml$utils$QName = class$("org.apache.xml.utils.QName"))), "mode");
            exec.addExceptionType(Class.forClass((CompilingStylesheetHandler.class$javax$xml$transform$TransformerException != null) ? CompilingStylesheetHandler.class$javax$xml$transform$TransformerException : (CompilingStylesheetHandler.class$javax$xml$transform$TransformerException = class$("javax.xml.transform.TransformerException"))));
            final ElemTemplateElement firstChild = source.getFirstChildElem();
            if (firstChild == null) {
                exec.getBody().append("//empty template");
            }
            else {
                final StringBuffer body = exec.getBody().append("if(transformer.S_DEBUG)\n  transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);\norg.apache.xalan.transformer.ResultTreeHandler rhandler = transformer.getResultTreeHandler();\norg.xml.sax.ContentHandler saxChandler = rhandler.getContentHandler();\nif(null == sourceNode) {\n  transformer.getMsgMgr().error(this, sourceNode,\n    org.apache.xalan.res.XSLTErrorResources.ER_NULL_SOURCENODE_HANDLEAPPLYTEMPLATES);\n  return; }\norg.apache.xpath.XPathContext xctxt = transformer.getXPathContext();\n// Check for infinite loops if requested\nboolean check = (transformer.getRecursionLimit() > -1);\nif (check)\n  transformer.getStackGuard().push(this, sourceNode);\nString avtStringedValue; // ***** Optimize away?\n\norg.xml.sax.helpers.NamespaceSupport nsSupport=new org.xml.sax.helpers.NamespaceSupport();\norg.xml.sax.helpers.NamespaceSupport savedNsSupport=(org.xml.sax.helpers.NamespaceSupport)m_nsThreadContexts.get(Thread.currentThread());\nm_nsThreadContexts.put(Thread.currentThread(),nsSupport);\n");
                this.compileChildTemplates(source, body, interpretVector);
                body.append("if(null!=savedNsSupport) m_nsThreadContexts.put(Thread.currentThread(),savedNsSupport);\nelse m_nsThreadContexts.remove(Thread.currentThread());\n\n// Decrement infinite-loop check\nif (check)\n  transformer.getStackGuard().pop();\n");
            }
            final java.lang.Class realclass = this.compileSyntheticClass(tClass, ".");
            final Object[] eteParms = new Object[interpretVector.size()];
            interpretVector.copyInto(eteParms);
            final Constructor c = tClass.getConstructor(ctor.getParameterTypes());
            final Object[] parms = { source, eteParms };
            instance = (ElemTemplate)c.newInstance(parms);
        }
        catch (SynthesisException e) {
            System.out.println("CompilingStylesheetHandler class synthesis error");
            e.printStackTrace();
        }
        catch (IllegalAccessException e2) {
            System.out.println("CompilingStylesheetHandler class comilation error");
            e2.printStackTrace();
        }
        catch (NoSuchMethodException e3) {
            System.out.println("CompilingStylesheetHandler constructor resolution error");
            e3.printStackTrace();
        }
        catch (InstantiationException e4) {
            System.out.println("CompilingStylesheetHandler constructor invocation error");
            e4.printStackTrace();
        }
        catch (InvocationTargetException e5) {
            System.out.println("CompilingStylesheetHandler constructor invocation error");
            e5.printStackTrace();
        }
        return instance;
    }
    
    String compileTransformToString(final ElemTemplateElement ea, final StringBuffer body, final Vector interpretVector) {
        ++this.uniqueVarSuffix;
        final String savedResultTreeHandler = "savedResultTreeHandler" + this.uniqueVarSuffix;
        final String shandler = "shandler" + this.uniqueVarSuffix;
        final String sw = "sw" + this.uniqueVarSuffix;
        final String sfactory = "sfactory" + this.uniqueVarSuffix;
        final String format = "format" + this.uniqueVarSuffix;
        final String serializer = "serializer" + this.uniqueVarSuffix;
        final String ioe = "ioe" + this.uniqueVarSuffix;
        body.append("\n// Begin transformToString (probably of attribute contents)\n// by redirecting output into a StringWriter.\norg.apache.xalan.transformer.ResultTreeHandler " + savedResultTreeHandler + "=rhandler;\n" + "org.xml.sax.ContentHandler " + shandler + ";\n" + "java.io.StringWriter " + sw + ";\n" + "try\n{\n" + "org.apache.xml.org.apache.xalan.serialize.SerializerFactory " + sfactory + "=org.apache.xml.org.apache.xalan.serialize.SerializerFactory.getSerializerFactory(\"text\");\n" + sw + "=new java.io.StringWriter();\n" + "org.apache.xalan.templates.OutputProperties " + format + "=new org.apache.xalan.templates.OutputProperties();\n" + format + ".setPreserveSpace(true);\n" + "org.apache.xml.org.apache.xalan.serialize.Serializer " + serializer + "=" + sfactory + ".makeSerializer(" + sw + "," + format + ");\n" + shandler + "=" + serializer + ".asContentHandler();\n" + "}\ncatch (java.io.IOException " + ioe + ")\n{\n" + "throw new javax.xml.transform.TransformerException(" + ioe + ");\n}\n" + "rhandler=new org.apache.xalan.transformer.ResultTreeHandler(transformer," + shandler + ");\n\n" + "rhandler.startDocument();\n" + "\n//unwind executeChildTemplates\n");
        this.compileChildTemplates(ea, body, interpretVector);
        body.append("\nrhandler.flushPending();\nrhandler.endDocument();\nrhandler=" + savedResultTreeHandler + ";\n" + "//End transformToString unwind; result in " + sw + "\n\n");
        return "(" + sw + ".toString())";
    }
    
    void compileUseAttrSet(final ElemTemplateElement ete, final StringBuffer body, final Vector interpretVector) {
        ++this.uniqueVarSuffix;
        body.append("if(transformer.S_DEBUG)\n  transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);\n");
        final QName[] attributeSetsNames = ((ElemUse)ete).getUseAttributeSets();
        if (attributeSetsNames != null) {
            final StylesheetRoot stylesheet = ete.getStylesheetRoot();
            for (final QName qname : attributeSetsNames) {
                final Vector attrSets = stylesheet.getAttributeSetComposed(qname);
                for (int nSets = attrSets.size(), k = 0; k < nSets; ++k) {
                    final ElemAttributeSet attrSet = attrSets.elementAt(k);
                    if (this.attrSetStack.contains(attrSet)) {
                        final String errmsg = "TEMPLATE COMPILATION ERROR: ATTRIBUTE SET RECURSION SUPPRESSED in " + attrSet.getName().getLocalPart();
                        System.err.println(errmsg);
                        body.append("// ***** " + errmsg + " *****/\n");
                        return;
                    }
                    this.attrSetStack.push(attrSet);
                    this.compileUseAttrSet(attrSet, body, interpretVector);
                    for (ElemAttribute attr = (ElemAttribute)attrSet.getFirstChild(); attr != null; attr = (ElemAttribute)attr.getNextSibling()) {
                        this.compileElemTemplateElement(attr, body, interpretVector);
                    }
                    this.attrSetStack.pop();
                }
            }
        }
    }
    
    public void endDocument() throws SAXException {
        super.endDocument();
        if (this.isStylesheetParsingComplete()) {
            final Stylesheet current = this.getStylesheet();
            final Vector compiledTemplates = new Vector();
            final StylesheetRoot root = this.getStylesheetRoot();
            final TemplateList newTl = new TemplateList();
            final TemplateList.TemplateWalker tw = root.getTemplateListComposed().getWalker();
            ElemTemplate et;
            while ((et = tw.next()) != null) {
                final ElemTemplate ct = this.compileTemplate(et);
                newTl.setTemplate((ct != null) ? ct : et);
            }
            newTl.compose();
            root.setTemplateListComposed(newTl);
            CompiledStylesheetBundle.createBundle(root, compiledTemplates);
        }
    }
    
    String generateUniqueClassName(final String basename) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: lstore_2        /* intAddr */
        //     2: invokestatic    java/net/InetAddress.getLocalHost:()Ljava/net/InetAddress;
        //     5: invokevirtual   java/net/InetAddress.getAddress:()[B
        //     8: astore          ipAddr
        //    10: iconst_0       
        //    11: istore          i
        //    13: goto            31
        //    16: lload_2         /* intAddr */
        //    17: bipush          8
        //    19: lshl           
        //    20: aload           ipAddr
        //    22: iload           i
        //    24: baload         
        //    25: i2l            
        //    26: ladd           
        //    27: lstore_2        /* intAddr */
        //    28: iinc            i, 1
        //    31: iload           i
        //    33: aload           ipAddr
        //    35: arraylength    
        //    36: if_icmplt       16
        //    39: goto            49
        //    42: astore          e
        //    44: aload           e
        //    46: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //    49: aload_0         /* this */
        //    50: astore          6
        //    52: aload           6
        //    54: monitorenter   
        //    55: getstatic       org/apache/xalan/processor/CompilingStylesheetHandler.templateCounter:I
        //    58: iconst_1       
        //    59: iadd           
        //    60: dup            
        //    61: putstatic       org/apache/xalan/processor/CompilingStylesheetHandler.templateCounter:I
        //    64: i2l            
        //    65: lstore          4
        //    67: aload           6
        //    69: monitorexit    
        //    70: goto            77
        //    73: aload           6
        //    75: monitorexit    
        //    76: athrow         
        //    77: new             Ljava/lang/StringBuffer;
        //    80: dup            
        //    81: aload_1         /* basename */
        //    82: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    85: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //    88: ldc             "On"
        //    90: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    93: lload_2         /* intAddr */
        //    94: invokevirtual   java/lang/StringBuffer.append:(J)Ljava/lang/StringBuffer;
        //    97: ldc             "at"
        //    99: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   102: new             Ljava/util/Date;
        //   105: dup            
        //   106: invokespecial   java/util/Date.<init>:()V
        //   109: invokevirtual   java/util/Date.getTime:()J
        //   112: invokevirtual   java/lang/StringBuffer.append:(J)Ljava/lang/StringBuffer;
        //   115: bipush          110
        //   117: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   120: lload           templateNumber
        //   122: invokevirtual   java/lang/StringBuffer.append:(J)Ljava/lang/StringBuffer;
        //   125: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   128: astore          className
        //   130: aload           className
        //   132: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name            Signature
        //  -----  ------  ----  --------------  -------------------------------------------------------
        //  0      133     0     this            Lorg/apache/xalan/processor/CompilingStylesheetHandler;
        //  0      133     1     basename        Ljava/lang/String;
        //  2      131     2     intAddr         J
        //  10     32      4     ipAddr          [B
        //  13     29      5     i               I
        //  44     5       4     e               Ljava/net/UnknownHostException;
        //  67     6       4     templateNumber  J
        //  73     4       4     e               Ljava/net/UnknownHostException;
        //  77     56      4     templateNumber  J
        //  130    3       6     className       Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  2      39     42     49     Ljava/net/UnknownHostException;
        //  55     67     73     77     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    String makeQuotedString(final String in) {
        if (in == null) {
            return "null";
        }
        final StringBuffer out = new StringBuffer("\"");
        int startpos = 0;
        for (int quotepos = in.indexOf(34, startpos); quotepos != -1; quotepos = in.indexOf(34, startpos)) {
            out.append(in.substring(startpos, quotepos)).append('\\').append('\"');
            startpos = quotepos + 1;
        }
        out.append(in.substring(startpos)).append('\"');
        return out.toString();
    }
}
