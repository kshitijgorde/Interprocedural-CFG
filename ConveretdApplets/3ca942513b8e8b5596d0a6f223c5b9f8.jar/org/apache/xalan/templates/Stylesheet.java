// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import java.util.Enumeration;
import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xml.utils.SystemIDResolver;
import javax.xml.transform.SourceLocator;
import org.apache.xml.utils.QName;
import java.io.ObjectOutputStream;
import java.io.IOException;
import javax.xml.transform.TransformerException;
import java.io.ObjectInputStream;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;
import org.apache.xml.utils.StringVector;
import java.io.Serializable;

public class Stylesheet extends ElemTemplateElement implements Serializable
{
    static final long serialVersionUID = 2085337282743043776L;
    public static final String STYLESHEET_EXT = ".lxc";
    private String m_XmlnsXsl;
    private StringVector m_ExtensionElementURIs;
    private StringVector m_ExcludeResultPrefixs;
    private String m_Id;
    private String m_Version;
    private boolean m_isCompatibleMode;
    private Vector m_imports;
    private Vector m_includes;
    Stack m_DecimalFormatDeclarations;
    private Vector m_whitespaceStrippingElements;
    private Vector m_whitespacePreservingElements;
    private Vector m_output;
    private Vector m_keyDeclarations;
    private Vector m_attributeSets;
    private Vector m_topLevelVariables;
    private Vector m_templates;
    private Vector m_prefix_aliases;
    private Hashtable m_NonXslTopLevel;
    private String m_href;
    private String m_publicId;
    private String m_systemId;
    private StylesheetRoot m_stylesheetRoot;
    private Stylesheet m_stylesheetParent;
    
    public Stylesheet(final Stylesheet parent) {
        this.m_isCompatibleMode = false;
        this.m_href = null;
        if (null != parent) {
            this.m_stylesheetParent = parent;
            this.m_stylesheetRoot = parent.getStylesheetRoot();
        }
    }
    
    public Stylesheet getStylesheet() {
        return this;
    }
    
    public boolean isAggregatedType() {
        return false;
    }
    
    public boolean isRoot() {
        return false;
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, TransformerException {
        try {
            stream.defaultReadObject();
        }
        catch (ClassNotFoundException cnfe) {
            throw new TransformerException(cnfe);
        }
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }
    
    public void setXmlnsXsl(final String v) {
        this.m_XmlnsXsl = v;
    }
    
    public String getXmlnsXsl() {
        return this.m_XmlnsXsl;
    }
    
    public void setExtensionElementPrefixes(final StringVector v) {
        this.m_ExtensionElementURIs = v;
    }
    
    public String getExtensionElementPrefix(final int i) throws ArrayIndexOutOfBoundsException {
        if (null == this.m_ExtensionElementURIs) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_ExtensionElementURIs.elementAt(i);
    }
    
    public int getExtensionElementPrefixCount() {
        return (null != this.m_ExtensionElementURIs) ? this.m_ExtensionElementURIs.size() : 0;
    }
    
    public boolean containsExtensionElementURI(final String uri) {
        return null != this.m_ExtensionElementURIs && this.m_ExtensionElementURIs.contains(uri);
    }
    
    public void setExcludeResultPrefixes(final StringVector v) {
        this.m_ExcludeResultPrefixs = v;
    }
    
    public String getExcludeResultPrefix(final int i) throws ArrayIndexOutOfBoundsException {
        if (null == this.m_ExcludeResultPrefixs) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_ExcludeResultPrefixs.elementAt(i);
    }
    
    public int getExcludeResultPrefixCount() {
        return (null != this.m_ExcludeResultPrefixs) ? this.m_ExcludeResultPrefixs.size() : 0;
    }
    
    public boolean containsExcludeResultPrefix(final String prefix, final String uri) {
        if (null == this.m_ExcludeResultPrefixs || uri == null) {
            return false;
        }
        for (int i = 0; i < this.m_ExcludeResultPrefixs.size(); ++i) {
            if (uri.equals(this.getNamespaceForPrefix(this.m_ExcludeResultPrefixs.elementAt(i)))) {
                return true;
            }
        }
        return false;
    }
    
    public void setId(final String v) {
        this.m_Id = v;
    }
    
    public String getId() {
        return this.m_Id;
    }
    
    public void setVersion(final String v) {
        this.m_Version = v;
        this.m_isCompatibleMode = (Double.valueOf(v) > 1.0);
    }
    
    public boolean getCompatibleMode() {
        return this.m_isCompatibleMode;
    }
    
    public String getVersion() {
        return this.m_Version;
    }
    
    public void setImport(final StylesheetComposed v) {
        if (null == this.m_imports) {
            this.m_imports = new Vector();
        }
        this.m_imports.addElement(v);
    }
    
    public StylesheetComposed getImport(final int i) throws ArrayIndexOutOfBoundsException {
        if (null == this.m_imports) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_imports.elementAt(i);
    }
    
    public int getImportCount() {
        return (null != this.m_imports) ? this.m_imports.size() : 0;
    }
    
    public void setInclude(final Stylesheet v) {
        if (null == this.m_includes) {
            this.m_includes = new Vector();
        }
        this.m_includes.addElement(v);
    }
    
    public Stylesheet getInclude(final int i) throws ArrayIndexOutOfBoundsException {
        if (null == this.m_includes) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_includes.elementAt(i);
    }
    
    public int getIncludeCount() {
        return (null != this.m_includes) ? this.m_includes.size() : 0;
    }
    
    public void setDecimalFormat(final DecimalFormatProperties edf) {
        if (null == this.m_DecimalFormatDeclarations) {
            this.m_DecimalFormatDeclarations = new Stack();
        }
        this.m_DecimalFormatDeclarations.push(edf);
    }
    
    public DecimalFormatProperties getDecimalFormat(final QName name) {
        if (null == this.m_DecimalFormatDeclarations) {
            return null;
        }
        final int n = this.getDecimalFormatCount();
        for (int i = n - 1; i >= 0; ++i) {
            final DecimalFormatProperties dfp = this.getDecimalFormat(i);
            if (dfp.getName().equals(name)) {
                return dfp;
            }
        }
        return null;
    }
    
    public DecimalFormatProperties getDecimalFormat(final int i) throws ArrayIndexOutOfBoundsException {
        if (null == this.m_DecimalFormatDeclarations) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (DecimalFormatProperties)this.m_DecimalFormatDeclarations.elementAt(i);
    }
    
    public int getDecimalFormatCount() {
        return (null != this.m_DecimalFormatDeclarations) ? this.m_DecimalFormatDeclarations.size() : 0;
    }
    
    public void setStripSpaces(final WhiteSpaceInfo wsi) {
        if (null == this.m_whitespaceStrippingElements) {
            this.m_whitespaceStrippingElements = new Vector();
        }
        this.m_whitespaceStrippingElements.addElement(wsi);
    }
    
    public WhiteSpaceInfo getStripSpace(final int i) throws ArrayIndexOutOfBoundsException {
        if (null == this.m_whitespaceStrippingElements) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_whitespaceStrippingElements.elementAt(i);
    }
    
    public int getStripSpaceCount() {
        return (null != this.m_whitespaceStrippingElements) ? this.m_whitespaceStrippingElements.size() : 0;
    }
    
    public void setPreserveSpaces(final WhiteSpaceInfo wsi) {
        if (null == this.m_whitespacePreservingElements) {
            this.m_whitespacePreservingElements = new Vector();
        }
        this.m_whitespacePreservingElements.addElement(wsi);
    }
    
    public WhiteSpaceInfo getPreserveSpace(final int i) throws ArrayIndexOutOfBoundsException {
        if (null == this.m_whitespacePreservingElements) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_whitespacePreservingElements.elementAt(i);
    }
    
    public int getPreserveSpaceCount() {
        return (null != this.m_whitespacePreservingElements) ? this.m_whitespacePreservingElements.size() : 0;
    }
    
    public void setOutput(final OutputProperties v) {
        if (null == this.m_output) {
            this.m_output = new Vector();
        }
        this.m_output.addElement(v);
    }
    
    public OutputProperties getOutput(final int i) throws ArrayIndexOutOfBoundsException {
        if (null == this.m_output) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_output.elementAt(i);
    }
    
    public int getOutputCount() {
        return (null != this.m_output) ? this.m_output.size() : 0;
    }
    
    public void setKey(final KeyDeclaration v) {
        if (null == this.m_keyDeclarations) {
            this.m_keyDeclarations = new Vector();
        }
        this.m_keyDeclarations.addElement(v);
    }
    
    public KeyDeclaration getKey(final int i) throws ArrayIndexOutOfBoundsException {
        if (null == this.m_keyDeclarations) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_keyDeclarations.elementAt(i);
    }
    
    public int getKeyCount() {
        return (null != this.m_keyDeclarations) ? this.m_keyDeclarations.size() : 0;
    }
    
    public void setAttributeSet(final ElemAttributeSet attrSet) {
        if (null == this.m_attributeSets) {
            this.m_attributeSets = new Vector();
        }
        this.m_attributeSets.addElement(attrSet);
    }
    
    public ElemAttributeSet getAttributeSet(final int i) throws ArrayIndexOutOfBoundsException {
        if (null == this.m_attributeSets) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_attributeSets.elementAt(i);
    }
    
    public int getAttributeSetCount() {
        return (null != this.m_attributeSets) ? this.m_attributeSets.size() : 0;
    }
    
    public void setVariable(final ElemVariable v) {
        if (null == this.m_topLevelVariables) {
            this.m_topLevelVariables = new Vector();
        }
        this.m_topLevelVariables.addElement(v);
    }
    
    public ElemVariable getVariableOrParam(final QName qname) {
        if (null != this.m_topLevelVariables) {
            for (int n = this.getVariableOrParamCount(), i = 0; i < n; ++i) {
                final ElemVariable var = this.getVariableOrParam(i);
                if (var.getName().equals(qname)) {
                    return var;
                }
            }
        }
        return null;
    }
    
    public ElemVariable getVariable(final QName qname) {
        if (null != this.m_topLevelVariables) {
            for (int n = this.getVariableOrParamCount(), i = 0; i < n; ++i) {
                final ElemVariable var = this.getVariableOrParam(i);
                if (var.getXSLToken() == 73 && var.getName().equals(qname)) {
                    return var;
                }
            }
        }
        return null;
    }
    
    public ElemVariable getVariableOrParam(final int i) throws ArrayIndexOutOfBoundsException {
        if (null == this.m_topLevelVariables) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_topLevelVariables.elementAt(i);
    }
    
    public int getVariableOrParamCount() {
        return (null != this.m_topLevelVariables) ? this.m_topLevelVariables.size() : 0;
    }
    
    public void setParam(final ElemParam v) {
        this.setVariable(v);
    }
    
    public ElemParam getParam(final QName qname) {
        if (null != this.m_topLevelVariables) {
            for (int n = this.getVariableOrParamCount(), i = 0; i < n; ++i) {
                final ElemVariable var = this.getVariableOrParam(i);
                if (var.getXSLToken() == 41 && var.getName().equals(qname)) {
                    return (ElemParam)var;
                }
            }
        }
        return null;
    }
    
    public void setTemplate(final ElemTemplate v) {
        if (null == this.m_templates) {
            this.m_templates = new Vector();
        }
        this.m_templates.addElement(v);
        v.setStylesheet(this);
    }
    
    public ElemTemplate getTemplate(final int i) throws TransformerException {
        if (null == this.m_templates) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_templates.elementAt(i);
    }
    
    public int getTemplateCount() {
        return (null != this.m_templates) ? this.m_templates.size() : 0;
    }
    
    public void setNamespaceAlias(final NamespaceAlias na) {
        if (this.m_prefix_aliases == null) {
            this.m_prefix_aliases = new Vector();
        }
        this.m_prefix_aliases.addElement(na);
    }
    
    public NamespaceAlias getNamespaceAlias(final int i) throws ArrayIndexOutOfBoundsException {
        if (null == this.m_prefix_aliases) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_prefix_aliases.elementAt(i);
    }
    
    public int getNamespaceAliasCount() {
        return (null != this.m_prefix_aliases) ? this.m_prefix_aliases.size() : 0;
    }
    
    public void setNonXslTopLevel(final QName name, final Object obj) {
        if (null == this.m_NonXslTopLevel) {
            this.m_NonXslTopLevel = new Hashtable();
        }
        this.m_NonXslTopLevel.put(name, obj);
    }
    
    public Object getNonXslTopLevel(final QName name) {
        return (null != this.m_NonXslTopLevel) ? this.m_NonXslTopLevel.get(name) : null;
    }
    
    public String getHref() {
        return this.m_href;
    }
    
    public void setHref(final String baseIdent) {
        this.m_href = baseIdent;
    }
    
    public void setLocaterInfo(final SourceLocator locator) {
        if (null != locator) {
            this.m_publicId = locator.getPublicId();
            this.m_systemId = locator.getSystemId();
            if (null != this.m_systemId) {
                try {
                    this.m_href = SystemIDResolver.getAbsoluteURI(this.m_systemId, null);
                }
                catch (TransformerException ex) {}
            }
            super.setLocaterInfo(locator);
        }
    }
    
    public StylesheetRoot getStylesheetRoot() {
        return this.m_stylesheetRoot;
    }
    
    public void setStylesheetRoot(final StylesheetRoot v) {
        this.m_stylesheetRoot = v;
    }
    
    public Stylesheet getStylesheetParent() {
        return this.m_stylesheetParent;
    }
    
    public void setStylesheetParent(final Stylesheet v) {
        this.m_stylesheetParent = v;
    }
    
    public StylesheetComposed getStylesheetComposed() {
        Stylesheet sheet;
        for (sheet = this; !sheet.isAggregatedType(); sheet = sheet.getStylesheetParent()) {}
        return (StylesheetComposed)sheet;
    }
    
    public short getNodeType() {
        return 9;
    }
    
    public int getXSLToken() {
        return 25;
    }
    
    public String getNodeName() {
        return "stylesheet";
    }
    
    public void replaceTemplate(final ElemTemplate v, final int i) throws TransformerException {
        if (null == this.m_templates) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.replaceChild(v, this.m_templates.elementAt(i));
        this.m_templates.setElementAt(v, i);
        v.setStylesheet(this);
    }
    
    protected void callChildVisitors(final XSLTVisitor visitor, final boolean callAttrs) {
        for (int s = this.getImportCount(), j = 0; j < s; ++j) {
            this.getImport(j).callVisitors(visitor);
        }
        for (int s = this.getIncludeCount(), i = 0; i < s; ++i) {
            this.getInclude(i).callVisitors(visitor);
        }
        for (int s = this.getOutputCount(), k = 0; k < s; ++k) {
            visitor.visitTopLevelInstruction(this.getOutput(k));
        }
        for (int s = this.getAttributeSetCount(), l = 0; l < s; ++l) {
            final ElemAttributeSet attrSet = this.getAttributeSet(l);
            if (visitor.visitTopLevelInstruction(attrSet)) {
                attrSet.callChildVisitors(visitor);
            }
        }
        for (int s = this.getDecimalFormatCount(), m = 0; m < s; ++m) {
            visitor.visitTopLevelInstruction(this.getDecimalFormat(m));
        }
        for (int s = this.getKeyCount(), j2 = 0; j2 < s; ++j2) {
            visitor.visitTopLevelInstruction(this.getKey(j2));
        }
        for (int s = this.getNamespaceAliasCount(), j3 = 0; j3 < s; ++j3) {
            visitor.visitTopLevelInstruction(this.getNamespaceAlias(j3));
        }
        for (int s = this.getTemplateCount(), j4 = 0; j4 < s; ++j4) {
            try {
                final ElemTemplate template = this.getTemplate(j4);
                if (visitor.visitTopLevelInstruction(template)) {
                    template.callChildVisitors(visitor);
                }
            }
            catch (TransformerException te) {
                throw new WrappedRuntimeException(te);
            }
        }
        for (int s = this.getVariableOrParamCount(), j5 = 0; j5 < s; ++j5) {
            final ElemVariable var = this.getVariableOrParam(j5);
            if (visitor.visitTopLevelVariableOrParamDecl(var)) {
                var.callChildVisitors(visitor);
            }
        }
        for (int s = this.getStripSpaceCount(), j6 = 0; j6 < s; ++j6) {
            visitor.visitTopLevelInstruction(this.getStripSpace(j6));
        }
        for (int s = this.getPreserveSpaceCount(), j7 = 0; j7 < s; ++j7) {
            visitor.visitTopLevelInstruction(this.getPreserveSpace(j7));
        }
        if (null != this.m_NonXslTopLevel) {
            final Enumeration elements = this.m_NonXslTopLevel.elements();
            while (elements.hasMoreElements()) {
                final ElemTemplateElement elem = elements.nextElement();
                if (visitor.visitTopLevelInstruction(elem)) {
                    elem.callChildVisitors(visitor);
                }
            }
        }
    }
    
    protected boolean accept(final XSLTVisitor visitor) {
        return visitor.visitStylesheet(this);
    }
}
