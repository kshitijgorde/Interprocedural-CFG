// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xalan.res.XSLMessages;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.Transformer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.apache.xpath.XPathContext;
import java.util.Properties;
import java.text.DecimalFormatSymbols;
import org.apache.xml.utils.QName;
import org.apache.xalan.processor.XSLTSchema;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;
import org.apache.xml.utils.PrefixResolver;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.ErrorListener;
import org.apache.xpath.XPath;
import java.util.Vector;
import java.util.Hashtable;
import javax.xml.transform.Templates;
import java.io.Serializable;

public class StylesheetRoot extends StylesheetComposed implements Serializable, Templates
{
    private Hashtable m_availElems;
    private StylesheetComposed[] m_globalImportList;
    private OutputProperties m_outputProperties;
    private boolean m_outputMethodSet;
    private Hashtable m_attrSets;
    private Hashtable m_decimalFormatSymbols;
    private Vector m_keyDecls;
    private Hashtable m_namespaceAliasComposed;
    private TemplateList m_templateList;
    private Vector m_variables;
    private TemplateList m_whiteSpaceInfoList;
    private ElemTemplate m_defaultTextRule;
    private ElemTemplate m_defaultRule;
    private ElemTemplate m_defaultRootRule;
    XPath m_selectDefault;
    
    public StylesheetRoot(final ErrorListener errorListener) throws TransformerConfigurationException {
        super(null);
        this.m_outputMethodSet = false;
        this.setStylesheetRoot(this);
        try {
            this.m_selectDefault = new XPath("node()", this, this, 0, errorListener);
            this.initDefaultRule(errorListener);
        }
        catch (TransformerException se) {
            throw new TransformerConfigurationException("Can't init default templates!", se);
        }
    }
    
    public StylesheetRoot(final XSLTSchema schema, final ErrorListener listener) throws TransformerConfigurationException {
        this(listener);
        this.m_availElems = schema.getElemsAvailable();
    }
    
    private void QuickSort2(final Vector v, final int lo0, final int hi0) {
        int lo = lo0;
        int hi = hi0;
        if (hi0 > lo0) {
            final ElemTemplateElement midNode = v.elementAt((lo0 + hi0) / 2);
            while (lo <= hi) {
                while (lo < hi0) {
                    if (v.elementAt(lo).compareTo(midNode) >= 0) {
                        break;
                    }
                    ++lo;
                }
                while (hi > lo0 && v.elementAt(hi).compareTo(midNode) > 0) {
                    --hi;
                }
                if (lo <= hi) {
                    final ElemTemplateElement node = v.elementAt(lo);
                    v.setElementAt(v.elementAt(hi), lo);
                    v.setElementAt(node, hi);
                    ++lo;
                    --hi;
                }
            }
            if (lo0 < hi) {
                this.QuickSort2(v, lo0, hi);
            }
            if (lo < hi0) {
                this.QuickSort2(v, lo, hi0);
            }
        }
    }
    
    protected void addImports(final Stylesheet stylesheet, final boolean addToList, final Vector importList) {
        int n = stylesheet.getImportCount();
        if (n > 0) {
            for (int i = 0; i < n; ++i) {
                final Stylesheet imported = stylesheet.getImport(i);
                this.addImports(imported, true, importList);
            }
        }
        n = stylesheet.getIncludeCount();
        if (n > 0) {
            for (int i = 0; i < n; ++i) {
                final Stylesheet included = stylesheet.getInclude(i);
                this.addImports(included, false, importList);
            }
        }
        if (addToList) {
            importList.addElement(stylesheet);
        }
    }
    
    public boolean canStripWhiteSpace() {
        return this.m_whiteSpaceInfoList != null;
    }
    
    static void composeTemplates(final ElemTemplateElement templ) {
        templ.compose();
        for (ElemTemplateElement child = templ.getFirstChildElem(); child != null; child = child.getNextSiblingElem()) {
            composeTemplates(child);
        }
    }
    
    public Vector getAttributeSetComposed(final QName name) throws ArrayIndexOutOfBoundsException {
        return this.m_attrSets.get(name);
    }
    
    public Hashtable getAvailableElements() {
        return this.m_availElems;
    }
    
    public DecimalFormatSymbols getDecimalFormatComposed(final QName name) {
        return this.m_decimalFormatSymbols.get(name);
    }
    
    public Properties getDefaultOutputProps() {
        return this.m_outputProperties.getProperties();
    }
    
    public final ElemTemplate getDefaultRootRule() {
        return this.m_defaultRootRule;
    }
    
    public final ElemTemplate getDefaultRule() {
        return this.m_defaultRule;
    }
    
    public final ElemTemplate getDefaultTextRule() {
        return this.m_defaultTextRule;
    }
    
    public StylesheetComposed getGlobalImport(final int i) {
        return this.m_globalImportList[i];
    }
    
    public int getGlobalImportCount() {
        return (this.m_globalImportList != null) ? this.m_globalImportList.length : 1;
    }
    
    public int getImportNumber(final StylesheetComposed sheet) {
        if (this == sheet) {
            return 0;
        }
        for (int n = this.getGlobalImportCount(), i = 0; i < n; ++i) {
            if (sheet == this.getGlobalImport(i)) {
                return i;
            }
        }
        return -1;
    }
    
    public Vector getKeysComposed() {
        return this.m_keyDecls;
    }
    
    public NamespaceAlias getNamespaceAliasComposed(final String uri) {
        return (this.m_namespaceAliasComposed == null) ? null : this.m_namespaceAliasComposed.get(uri);
    }
    
    public OutputProperties getOutputComposed() {
        return this.m_outputProperties;
    }
    
    public Properties getOutputProperties() {
        return (Properties)this.getDefaultOutputProps().clone();
    }
    
    public ElemTemplate getTemplateComposed(final QName qname) {
        return this.m_templateList.getTemplate(qname);
    }
    
    public ElemTemplate getTemplateComposed(final XPathContext xctxt, final Node targetNode, final QName mode, final int maxImportLevel, final boolean quietConflictWarnings) throws TransformerException {
        return this.m_templateList.getTemplate(xctxt, targetNode, mode, maxImportLevel, quietConflictWarnings);
    }
    
    public final TemplateList getTemplateListComposed() {
        return this.m_templateList;
    }
    
    public ElemVariable getVariableOrParamComposed(final QName qname) {
        if (this.m_variables != null) {
            for (int n = this.m_variables.size(), i = 0; i < n; ++i) {
                final ElemVariable var = this.m_variables.elementAt(i);
                if (var.getName().equals(qname)) {
                    return var;
                }
            }
        }
        return null;
    }
    
    public Vector getVariablesAndParamsComposed() {
        return this.m_variables;
    }
    
    public WhiteSpaceInfo getWhiteSpaceInfo(final XPathContext support, final Element targetElement) throws TransformerException {
        if (this.m_whiteSpaceInfoList != null) {
            return (WhiteSpaceInfo)this.m_whiteSpaceInfoList.getTemplate(support, targetElement, null, -1, false);
        }
        return null;
    }
    
    private void initDefaultRule(final ErrorListener errorListener) throws TransformerException {
        (this.m_defaultRule = new ElemTemplate()).setStylesheet(this);
        XPath defMatch = new XPath("*", this, this, 1, errorListener);
        this.m_defaultRule.setMatch(defMatch);
        ElemApplyTemplates childrenElement = new ElemApplyTemplates();
        childrenElement.setIsDefaultTemplate(true);
        this.m_defaultRule.appendChild(childrenElement);
        (this.m_defaultTextRule = new ElemTemplate()).setStylesheet(this);
        defMatch = new XPath("text() | @*", this, this, 1, errorListener);
        this.m_defaultTextRule.setMatch(defMatch);
        final ElemValueOf elemValueOf = new ElemValueOf();
        this.m_defaultTextRule.appendChild(elemValueOf);
        final XPath selectPattern = new XPath(".", this, this, 0, errorListener);
        elemValueOf.setSelect(selectPattern);
        (this.m_defaultRootRule = new ElemTemplate()).setStylesheet(this);
        defMatch = new XPath("/", this, this, 1, errorListener);
        this.m_defaultRootRule.setMatch(defMatch);
        childrenElement = new ElemApplyTemplates();
        childrenElement.setIsDefaultTemplate(true);
        this.m_defaultRootRule.appendChild(childrenElement);
    }
    
    public boolean isOutputMethodSet() {
        return this.m_outputMethodSet;
    }
    
    public boolean isRoot() {
        return true;
    }
    
    public Transformer newTransformer() {
        return new TransformerImpl(this);
    }
    
    public void recompose() throws TransformerException {
        if (this.m_globalImportList == null) {
            final Vector importList = new Vector();
            this.addImports(this, true, importList);
            this.m_globalImportList = new StylesheetComposed[importList.size()];
            int i = importList.size() - 1;
            int j = 0;
            while (i >= 0) {
                this.m_globalImportList[j++] = importList.elementAt(i);
                --i;
            }
        }
        final Vector recomposableElements = new Vector();
        for (int n = this.getGlobalImportCount(), k = 0; k < n; ++k) {
            final StylesheetComposed imported = this.getGlobalImport(k);
            imported.recompose(recomposableElements);
        }
        this.QuickSort2(recomposableElements, 0, recomposableElements.size() - 1);
        this.m_outputProperties = new OutputProperties("xml");
        this.m_attrSets = new Hashtable();
        this.m_decimalFormatSymbols = new Hashtable();
        this.m_keyDecls = new Vector();
        this.m_namespaceAliasComposed = new Hashtable();
        this.m_templateList = new TemplateList();
        this.m_variables = new Vector();
        for (int l = recomposableElements.size() - 1; l >= 0; --l) {
            recomposableElements.elementAt(l).recompose(this);
        }
        this.m_templateList.compose();
        this.m_outputProperties.compose();
        for (int n = this.getGlobalImportCount(), m = 0; m < n; ++m) {
            final StylesheetComposed imported2 = this.getGlobalImport(m);
            for (int includedCount = imported2.getIncludeCountComposed(), j2 = -1; j2 < includedCount; ++j2) {
                final Stylesheet included = imported2.getIncludeComposed(j2);
                composeTemplates(included);
            }
        }
    }
    
    void recomposeAttributeSets(final ElemAttributeSet attrSet) {
        Vector attrSetList = this.m_attrSets.get(attrSet.getName());
        if (attrSetList == null) {
            attrSetList = new Vector();
            this.m_attrSets.put(attrSet.getName(), attrSetList);
        }
        attrSetList.addElement(attrSet);
    }
    
    void recomposeDecimalFormats(final DecimalFormatProperties dfp) {
        final DecimalFormatSymbols oldDfs = this.m_decimalFormatSymbols.get(dfp.getName());
        if (oldDfs == null) {
            this.m_decimalFormatSymbols.put(dfp.getName(), dfp.getDecimalFormatSymbols());
        }
        else if (!dfp.getDecimalFormatSymbols().equals(oldDfs)) {
            String themsg;
            if (dfp.getName().equals(new QName(""))) {
                themsg = XSLMessages.createWarning(19, new Object[0]);
            }
            else {
                themsg = XSLMessages.createWarning(20, new Object[] { dfp.getName() });
            }
            throw new RuntimeException(themsg);
        }
    }
    
    void recomposeKeys(final KeyDeclaration keyDecl) {
        this.m_keyDecls.addElement(keyDecl);
    }
    
    void recomposeNamespaceAliases(final NamespaceAlias nsAlias) {
        this.m_namespaceAliasComposed.put(nsAlias.getStylesheetNamespace(), nsAlias);
    }
    
    void recomposeOutput(final OutputProperties oprops) throws TransformerException {
        this.m_outputProperties.copyFrom(oprops);
    }
    
    void recomposeTemplates(final ElemTemplate template) {
        this.m_templateList.setTemplate(template);
    }
    
    void recomposeVariables(final ElemVariable elemVar) {
        if (this.getVariableOrParamComposed(elemVar.getName()) == null) {
            this.m_variables.addElement(elemVar);
        }
    }
    
    void recomposeWhiteSpaceInfo(final WhiteSpaceInfo wsi) {
        if (this.m_whiteSpaceInfoList == null) {
            this.m_whiteSpaceInfoList = new TemplateList();
        }
        this.m_whiteSpaceInfoList.setTemplate(wsi);
    }
    
    public final void setTemplateListComposed(final TemplateList templateList) {
        this.m_templateList = templateList;
    }
    
    public boolean shouldCheckWhitespace() {
        return this.m_whiteSpaceInfoList != null;
    }
    
    public boolean shouldStripWhiteSpace(final XPathContext support, Element targetElement) throws TransformerException {
        if (this.m_whiteSpaceInfoList != null) {
            while (targetElement != null) {
                final WhiteSpaceInfo info = (WhiteSpaceInfo)this.m_whiteSpaceInfoList.getTemplate(support, targetElement, null, -1, false);
                if (info != null) {
                    return info.getShouldStripSpace();
                }
                final Node parent = targetElement.getParentNode();
                if (parent != null && parent.getNodeType() == 1) {
                    targetElement = (Element)parent;
                }
                else {
                    targetElement = null;
                }
            }
        }
        return false;
    }
}
