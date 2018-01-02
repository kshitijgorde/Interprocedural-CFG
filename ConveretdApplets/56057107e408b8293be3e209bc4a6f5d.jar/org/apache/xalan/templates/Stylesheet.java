// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import java.io.ObjectOutputStream;
import org.apache.xml.utils.SystemIDResolver;
import javax.xml.transform.SourceLocator;
import org.w3c.dom.Node;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.QName;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;
import org.apache.xml.utils.StringVector;
import org.w3c.dom.Document;
import java.io.Serializable;

public class Stylesheet extends ElemTemplateElement implements Serializable, Document
{
    public static final String STYLESHEET_EXT = ".lxc";
    private String m_XmlnsXsl;
    private StringVector m_ExtensionElementURIs;
    private StringVector m_ExcludeResultPrefixs;
    private String m_Id;
    private String m_Version;
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
        this.m_href = null;
        if (parent != null) {
            this.m_stylesheetParent = parent;
            this.m_stylesheetRoot = parent.getStylesheetRoot();
        }
    }
    
    public boolean containsExcludeResultPrefix(String prefix) {
        if (this.m_ExcludeResultPrefixs == null) {
            return false;
        }
        if (prefix.length() == 0) {
            prefix = "#default";
        }
        return this.m_ExcludeResultPrefixs.contains(prefix);
    }
    
    public boolean containsExtensionElementURI(final String uri) {
        return this.m_ExtensionElementURIs != null && this.m_ExtensionElementURIs.contains(uri);
    }
    
    public ElemAttributeSet getAttributeSet(final int i) throws ArrayIndexOutOfBoundsException {
        if (this.m_attributeSets == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_attributeSets.elementAt(i);
    }
    
    public int getAttributeSetCount() {
        return (this.m_attributeSets != null) ? this.m_attributeSets.size() : 0;
    }
    
    public DecimalFormatProperties getDecimalFormat(final int i) throws ArrayIndexOutOfBoundsException {
        if (this.m_DecimalFormatDeclarations == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (DecimalFormatProperties)this.m_DecimalFormatDeclarations.elementAt(i);
    }
    
    public DecimalFormatProperties getDecimalFormat(final QName name) {
        if (this.m_DecimalFormatDeclarations == null) {
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
    
    public int getDecimalFormatCount() {
        return (this.m_DecimalFormatDeclarations != null) ? this.m_DecimalFormatDeclarations.size() : 0;
    }
    
    public String getExcludeResultPrefix(final int i) throws ArrayIndexOutOfBoundsException {
        if (this.m_ExcludeResultPrefixs == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_ExcludeResultPrefixs.elementAt(i);
    }
    
    public int getExcludeResultPrefixCount() {
        return (this.m_ExcludeResultPrefixs != null) ? this.m_ExcludeResultPrefixs.size() : 0;
    }
    
    public String getExtensionElementPrefix(final int i) throws ArrayIndexOutOfBoundsException {
        if (this.m_ExtensionElementURIs == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_ExtensionElementURIs.elementAt(i);
    }
    
    public int getExtensionElementPrefixCount() {
        return (this.m_ExtensionElementURIs != null) ? this.m_ExtensionElementURIs.size() : 0;
    }
    
    public String getHref() {
        return this.m_href;
    }
    
    public String getId() {
        return this.m_Id;
    }
    
    public StylesheetComposed getImport(final int i) throws ArrayIndexOutOfBoundsException {
        if (this.m_imports == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_imports.elementAt(i);
    }
    
    public int getImportCount() {
        return (this.m_imports != null) ? this.m_imports.size() : 0;
    }
    
    public Stylesheet getInclude(final int i) throws ArrayIndexOutOfBoundsException {
        if (this.m_includes == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_includes.elementAt(i);
    }
    
    public int getIncludeCount() {
        return (this.m_includes != null) ? this.m_includes.size() : 0;
    }
    
    public KeyDeclaration getKey(final int i) throws ArrayIndexOutOfBoundsException {
        if (this.m_keyDeclarations == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_keyDeclarations.elementAt(i);
    }
    
    public int getKeyCount() {
        return (this.m_keyDeclarations != null) ? this.m_keyDeclarations.size() : 0;
    }
    
    public NamespaceAlias getNamespaceAlias(final int i) throws ArrayIndexOutOfBoundsException {
        if (this.m_prefix_aliases == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_prefix_aliases.elementAt(i);
    }
    
    public int getNamespaceAliasCount() {
        return (this.m_prefix_aliases != null) ? this.m_prefix_aliases.size() : 0;
    }
    
    public String getNodeName() {
        return "stylesheet";
    }
    
    public short getNodeType() {
        return 9;
    }
    
    public Object getNonXslTopLevel(final QName name) {
        return (this.m_NonXslTopLevel != null) ? this.m_NonXslTopLevel.get(name) : null;
    }
    
    public OutputProperties getOutput(final int i) throws ArrayIndexOutOfBoundsException {
        if (this.m_output == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_output.elementAt(i);
    }
    
    public int getOutputCount() {
        return (this.m_output != null) ? this.m_output.size() : 0;
    }
    
    public ElemParam getParam(final QName qname) {
        if (this.m_topLevelVariables != null) {
            for (int n = this.getVariableOrParamCount(), i = 0; i < n; ++i) {
                final ElemVariable var = this.getVariableOrParam(i);
                if (var.getXSLToken() == 41 && var.getName().equals(qname)) {
                    return (ElemParam)var;
                }
            }
        }
        return null;
    }
    
    public WhiteSpaceInfo getPreserveSpace(final int i) throws ArrayIndexOutOfBoundsException {
        if (this.m_whitespacePreservingElements == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_whitespacePreservingElements.elementAt(i);
    }
    
    public int getPreserveSpaceCount() {
        return (this.m_whitespacePreservingElements != null) ? this.m_whitespacePreservingElements.size() : 0;
    }
    
    public WhiteSpaceInfo getStripSpace(final int i) throws ArrayIndexOutOfBoundsException {
        if (this.m_whitespaceStrippingElements == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_whitespaceStrippingElements.elementAt(i);
    }
    
    public int getStripSpaceCount() {
        return (this.m_whitespaceStrippingElements != null) ? this.m_whitespaceStrippingElements.size() : 0;
    }
    
    public Stylesheet getStylesheet() {
        return this;
    }
    
    public StylesheetComposed getStylesheetComposed() {
        Stylesheet sheet;
        for (sheet = this; !sheet.isAggregatedType(); sheet = sheet.getStylesheetParent()) {}
        return (StylesheetComposed)sheet;
    }
    
    public Stylesheet getStylesheetParent() {
        return this.m_stylesheetParent;
    }
    
    public StylesheetRoot getStylesheetRoot() {
        return this.m_stylesheetRoot;
    }
    
    public ElemTemplate getTemplate(final int i) throws TransformerException {
        if (this.m_templates == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_templates.elementAt(i);
    }
    
    public int getTemplateCount() {
        return (this.m_templates != null) ? this.m_templates.size() : 0;
    }
    
    public ElemVariable getVariable(final QName qname) {
        if (this.m_topLevelVariables != null) {
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
        if (this.m_topLevelVariables == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_topLevelVariables.elementAt(i);
    }
    
    public ElemVariable getVariableOrParam(final QName qname) {
        if (this.m_topLevelVariables != null) {
            for (int n = this.getVariableOrParamCount(), i = 0; i < n; ++i) {
                final ElemVariable var = this.getVariableOrParam(i);
                if (var.getName().equals(qname)) {
                    return var;
                }
            }
        }
        return null;
    }
    
    public int getVariableOrParamCount() {
        return (this.m_topLevelVariables != null) ? this.m_topLevelVariables.size() : 0;
    }
    
    public String getVersion() {
        return this.m_Version;
    }
    
    public int getXSLToken() {
        return 25;
    }
    
    public String getXmlnsXsl() {
        return this.m_XmlnsXsl;
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
    
    public void replaceTemplate(final ElemTemplate v, final int i) throws TransformerException {
        if (this.m_templates == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.replaceChild(v, this.m_templates.elementAt(i));
        this.m_templates.setElementAt(v, i);
        v.setStylesheet(this);
    }
    
    public void setAttributeSet(final ElemAttributeSet attrSet) {
        if (this.m_attributeSets == null) {
            this.m_attributeSets = new Vector();
        }
        this.m_attributeSets.addElement(attrSet);
    }
    
    public void setDecimalFormat(final DecimalFormatProperties edf) {
        if (this.m_DecimalFormatDeclarations == null) {
            this.m_DecimalFormatDeclarations = new Stack();
        }
        this.m_DecimalFormatDeclarations.push(edf);
    }
    
    public void setExcludeResultPrefixes(final StringVector v) {
        this.m_ExcludeResultPrefixs = v;
    }
    
    public void setExtensionElementPrefixes(final StringVector v) {
        this.m_ExtensionElementURIs = v;
    }
    
    public void setHref(final String baseIdent) {
        this.m_href = baseIdent;
    }
    
    public void setId(final String v) {
        this.m_Id = v;
    }
    
    public void setImport(final StylesheetComposed v) {
        if (this.m_imports == null) {
            this.m_imports = new Vector();
        }
        this.m_imports.addElement(v);
    }
    
    public void setInclude(final Stylesheet v) {
        if (this.m_includes == null) {
            this.m_includes = new Vector();
        }
        this.m_includes.addElement(v);
    }
    
    public void setKey(final KeyDeclaration v) {
        if (this.m_keyDeclarations == null) {
            this.m_keyDeclarations = new Vector();
        }
        this.m_keyDeclarations.addElement(v);
    }
    
    public void setLocaterInfo(final SourceLocator locator) {
        if (locator != null) {
            this.m_publicId = locator.getPublicId();
            this.m_systemId = locator.getSystemId();
            if (this.m_systemId != null) {
                try {
                    this.m_href = SystemIDResolver.getAbsoluteURI(this.m_systemId, null);
                }
                catch (TransformerException ex) {}
            }
            super.setLocaterInfo(locator);
        }
    }
    
    public void setNamespaceAlias(final NamespaceAlias na) {
        if (this.m_prefix_aliases == null) {
            this.m_prefix_aliases = new Vector();
        }
        this.m_prefix_aliases.addElement(na);
    }
    
    public void setNonXslTopLevel(final QName name, final Object obj) {
        if (this.m_NonXslTopLevel == null) {
            this.m_NonXslTopLevel = new Hashtable();
        }
        this.m_NonXslTopLevel.put(name, obj);
    }
    
    public void setOutput(final OutputProperties v) {
        if (this.m_output == null) {
            this.m_output = new Vector();
        }
        this.m_output.addElement(v);
    }
    
    public void setParam(final ElemParam v) {
        this.setVariable(v);
    }
    
    public void setPreserveSpaces(final WhiteSpaceInfo wsi) {
        if (this.m_whitespacePreservingElements == null) {
            this.m_whitespacePreservingElements = new Vector();
        }
        this.m_whitespacePreservingElements.addElement(wsi);
    }
    
    public void setStripSpaces(final WhiteSpaceInfo wsi) {
        if (this.m_whitespaceStrippingElements == null) {
            this.m_whitespaceStrippingElements = new Vector();
        }
        this.m_whitespaceStrippingElements.addElement(wsi);
    }
    
    public void setStylesheetParent(final Stylesheet v) {
        this.m_stylesheetParent = v;
    }
    
    public void setStylesheetRoot(final StylesheetRoot v) {
        this.m_stylesheetRoot = v;
    }
    
    public void setTemplate(final ElemTemplate v) {
        if (this.m_templates == null) {
            this.m_templates = new Vector();
        }
        this.m_templates.addElement(v);
        v.setStylesheet(this);
    }
    
    public void setVariable(final ElemVariable v) {
        if (this.m_topLevelVariables == null) {
            this.m_topLevelVariables = new Vector();
        }
        this.m_topLevelVariables.addElement(v);
    }
    
    public void setVersion(final String v) {
        this.m_Version = v;
    }
    
    public void setXmlnsXsl(final String v) {
        this.m_XmlnsXsl = v;
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }
}
