// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.PUTFIELD;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import java.util.StringTokenizer;
import java.io.UnsupportedEncodingException;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import org.apache.xml.serializer.Encodings;
import org.apache.xml.utils.XMLChar;
import java.util.Properties;
import org.apache.xalan.xsltc.compiler.util.Util;

final class Output extends TopLevelElement
{
    private String _version;
    private String _method;
    private String _encoding;
    private boolean _omitHeader;
    private String _standalone;
    private String _doctypePublic;
    private String _doctypeSystem;
    private String _cdata;
    private boolean _indent;
    private String _mediaType;
    private String _cdataToMerge;
    private boolean _disabled;
    private static final String STRING_SIG = "Ljava/lang/String;";
    private static final String XML_VERSION = "1.0";
    private static final String HTML_VERSION = "4.0";
    
    Output() {
        this._omitHeader = false;
        this._indent = false;
        this._disabled = false;
    }
    
    public void display(final int indent) {
        this.indent(indent);
        Util.println("Output " + this._method);
    }
    
    public void disable() {
        this._disabled = true;
    }
    
    public boolean enabled() {
        return !this._disabled;
    }
    
    public String getCdata() {
        return this._cdata;
    }
    
    public String getOutputMethod() {
        return this._method;
    }
    
    public void mergeCdata(final String cdata) {
        this._cdataToMerge = cdata;
    }
    
    public void parseContents(final Parser parser) {
        final Properties outputProperties = new Properties();
        parser.setOutput(this);
        if (this._disabled) {
            return;
        }
        String attrib = null;
        this._version = this.getAttribute("version");
        if (this._version == null || this._version.equals("")) {
            this._version = null;
        }
        else {
            outputProperties.setProperty("version", this._version);
        }
        this._method = this.getAttribute("method");
        if (this._method.equals("")) {
            this._method = null;
        }
        if (this._method != null) {
            this._method = this._method.toLowerCase();
            if (this._method.equals("xml") || this._method.equals("html") || this._method.equals("text") || (XMLChar.isValidQName(this._method) && this._method.indexOf(":") > 0)) {
                outputProperties.setProperty("method", this._method);
            }
            else {
                this.reportError(this, parser, "INVALID_METHOD_IN_OUTPUT", this._method);
            }
        }
        this._encoding = this.getAttribute("encoding");
        if (this._encoding.equals("")) {
            this._encoding = null;
        }
        else {
            try {
                final String canonicalEncoding = Encodings.convertMime2JavaEncoding(this._encoding);
                final OutputStreamWriter writer = new OutputStreamWriter(System.out, canonicalEncoding);
            }
            catch (UnsupportedEncodingException e) {
                final ErrorMsg msg = new ErrorMsg("UNSUPPORTED_ENCODING", this._encoding, this);
                parser.reportError(4, msg);
            }
            outputProperties.setProperty("encoding", this._encoding);
        }
        attrib = this.getAttribute("omit-xml-declaration");
        if (attrib != null && !attrib.equals("")) {
            if (attrib.equals("yes")) {
                this._omitHeader = true;
            }
            outputProperties.setProperty("omit-xml-declaration", attrib);
        }
        this._standalone = this.getAttribute("standalone");
        if (this._standalone.equals("")) {
            this._standalone = null;
        }
        else {
            outputProperties.setProperty("standalone", this._standalone);
        }
        this._doctypeSystem = this.getAttribute("doctype-system");
        if (this._doctypeSystem.equals("")) {
            this._doctypeSystem = null;
        }
        else {
            outputProperties.setProperty("doctype-system", this._doctypeSystem);
        }
        this._doctypePublic = this.getAttribute("doctype-public");
        if (this._doctypePublic.equals("")) {
            this._doctypePublic = null;
        }
        else {
            outputProperties.setProperty("doctype-public", this._doctypePublic);
        }
        this._cdata = this.getAttribute("cdata-section-elements");
        if (this._cdata != null && this._cdata.equals("")) {
            this._cdata = null;
        }
        else {
            final StringBuffer expandedNames = new StringBuffer();
            final StringTokenizer tokens = new StringTokenizer(this._cdata);
            while (tokens.hasMoreTokens()) {
                final String qname = tokens.nextToken();
                if (!XMLChar.isValidQName(qname)) {
                    final ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", qname, this);
                    parser.reportError(3, err);
                }
                expandedNames.append(parser.getQName(qname).toString()).append(' ');
            }
            this._cdata = expandedNames.toString();
            if (this._cdataToMerge != null) {
                this._cdata += this._cdataToMerge;
            }
            outputProperties.setProperty("cdata-section-elements", this._cdata);
        }
        attrib = this.getAttribute("indent");
        if (attrib != null && !attrib.equals("")) {
            if (attrib.equals("yes")) {
                this._indent = true;
            }
            outputProperties.setProperty("indent", attrib);
        }
        else if (this._method != null && this._method.equals("html")) {
            this._indent = true;
        }
        this._mediaType = this.getAttribute("media-type");
        if (this._mediaType.equals("")) {
            this._mediaType = null;
        }
        else {
            outputProperties.setProperty("media-type", this._mediaType);
        }
        if (this._method != null) {
            if (this._method.equals("html")) {
                if (this._version == null) {
                    this._version = "4.0";
                }
                if (this._mediaType == null) {
                    this._mediaType = "text/html";
                }
            }
            else if (this._method.equals("text") && this._mediaType == null) {
                this._mediaType = "text/plain";
            }
        }
        parser.getCurrentStylesheet().setOutputProperties(outputProperties);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        if (this._disabled) {
            return;
        }
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        int field = 0;
        il.append(classGen.loadTranslet());
        if (this._version != null && !this._version.equals("1.0")) {
            field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_version", "Ljava/lang/String;");
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, this._version));
            il.append(new PUTFIELD(field));
        }
        if (this._method != null) {
            field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_method", "Ljava/lang/String;");
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, this._method));
            il.append(new PUTFIELD(field));
        }
        if (this._encoding != null) {
            field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_encoding", "Ljava/lang/String;");
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, this._encoding));
            il.append(new PUTFIELD(field));
        }
        if (this._omitHeader) {
            field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_omitHeader", "Z");
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, this._omitHeader));
            il.append(new PUTFIELD(field));
        }
        if (this._standalone != null) {
            field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_standalone", "Ljava/lang/String;");
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, this._standalone));
            il.append(new PUTFIELD(field));
        }
        field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_doctypeSystem", "Ljava/lang/String;");
        il.append(InstructionConstants.DUP);
        il.append(new PUSH(cpg, this._doctypeSystem));
        il.append(new PUTFIELD(field));
        field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_doctypePublic", "Ljava/lang/String;");
        il.append(InstructionConstants.DUP);
        il.append(new PUSH(cpg, this._doctypePublic));
        il.append(new PUTFIELD(field));
        if (this._mediaType != null) {
            field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_mediaType", "Ljava/lang/String;");
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, this._mediaType));
            il.append(new PUTFIELD(field));
        }
        if (this._indent) {
            field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_indent", "Z");
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, this._indent));
            il.append(new PUTFIELD(field));
        }
        if (this._cdata != null) {
            final int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "addCdataElement", "(Ljava/lang/String;)V");
            final StringTokenizer tokens = new StringTokenizer(this._cdata);
            while (tokens.hasMoreTokens()) {
                il.append(InstructionConstants.DUP);
                il.append(new PUSH(cpg, tokens.nextToken()));
                il.append(new INVOKEVIRTUAL(index));
            }
        }
        il.append(InstructionConstants.POP);
    }
}
