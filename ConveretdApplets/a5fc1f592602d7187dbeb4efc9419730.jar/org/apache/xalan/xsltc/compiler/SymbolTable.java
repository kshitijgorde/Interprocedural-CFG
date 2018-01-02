// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.xalan.xsltc.compiler.util.MethodType;
import java.util.Hashtable;

final class SymbolTable
{
    private final Hashtable _stylesheets;
    private final Hashtable _primops;
    private Hashtable _variables;
    private Hashtable _templates;
    private Hashtable _attributeSets;
    private Hashtable _aliases;
    private Hashtable _excludedURI;
    private Hashtable _decimalFormats;
    private int _nsCounter;
    private SyntaxTreeNode _current;
    
    SymbolTable() {
        this._stylesheets = new Hashtable();
        this._primops = new Hashtable();
        this._variables = null;
        this._templates = null;
        this._attributeSets = null;
        this._aliases = null;
        this._excludedURI = null;
        this._decimalFormats = null;
        this._nsCounter = 0;
        this._current = null;
    }
    
    public DecimalFormatting getDecimalFormatting(final QName name) {
        if (this._decimalFormats == null) {
            return null;
        }
        return this._decimalFormats.get(name);
    }
    
    public void addDecimalFormatting(final QName name, final DecimalFormatting symbols) {
        if (this._decimalFormats == null) {
            this._decimalFormats = new Hashtable();
        }
        this._decimalFormats.put(name, symbols);
    }
    
    public Stylesheet addStylesheet(final QName name, final Stylesheet node) {
        return this._stylesheets.put(name, node);
    }
    
    public Stylesheet lookupStylesheet(final QName name) {
        return this._stylesheets.get(name);
    }
    
    public Template addTemplate(final Template template) {
        final QName name = template.getName();
        if (this._templates == null) {
            this._templates = new Hashtable();
        }
        return this._templates.put(name, template);
    }
    
    public Template lookupTemplate(final QName name) {
        if (this._templates == null) {
            return null;
        }
        return this._templates.get(name);
    }
    
    public Variable addVariable(final Variable variable) {
        if (this._variables == null) {
            this._variables = new Hashtable();
        }
        final String name = variable.getName().getStringRep();
        return this._variables.put(name, variable);
    }
    
    public Param addParam(final Param parameter) {
        if (this._variables == null) {
            this._variables = new Hashtable();
        }
        final String name = parameter.getName().getStringRep();
        return this._variables.put(name, parameter);
    }
    
    public Variable lookupVariable(final QName qname) {
        if (this._variables == null) {
            return null;
        }
        final String name = qname.getStringRep();
        final Object obj = this._variables.get(name);
        return (obj instanceof Variable) ? ((Variable)obj) : null;
    }
    
    public Param lookupParam(final QName qname) {
        if (this._variables == null) {
            return null;
        }
        final String name = qname.getStringRep();
        final Object obj = this._variables.get(name);
        return (obj instanceof Param) ? ((Param)obj) : null;
    }
    
    public SyntaxTreeNode lookupName(final QName qname) {
        if (this._variables == null) {
            return null;
        }
        final String name = qname.getStringRep();
        return this._variables.get(name);
    }
    
    public AttributeSet addAttributeSet(final AttributeSet atts) {
        if (this._attributeSets == null) {
            this._attributeSets = new Hashtable();
        }
        return this._attributeSets.put(atts.getName(), atts);
    }
    
    public AttributeSet lookupAttributeSet(final QName name) {
        if (this._attributeSets == null) {
            return null;
        }
        return this._attributeSets.get(name);
    }
    
    public void addPrimop(final String name, final MethodType mtype) {
        Vector methods = this._primops.get(name);
        if (methods == null) {
            this._primops.put(name, methods = new Vector());
        }
        methods.addElement(mtype);
    }
    
    public Vector lookupPrimop(final String name) {
        return this._primops.get(name);
    }
    
    public String generateNamespacePrefix() {
        return new String("ns" + this._nsCounter++);
    }
    
    public void setCurrentNode(final SyntaxTreeNode node) {
        this._current = node;
    }
    
    public String lookupNamespace(final String prefix) {
        if (this._current == null) {
            return "";
        }
        return this._current.lookupNamespace(prefix);
    }
    
    public void addPrefixAlias(final String prefix, final String alias) {
        if (this._aliases == null) {
            this._aliases = new Hashtable();
        }
        this._aliases.put(prefix, alias);
    }
    
    public String lookupPrefixAlias(final String prefix) {
        if (this._aliases == null) {
            return null;
        }
        return this._aliases.get(prefix);
    }
    
    public void excludeURI(final String uri) {
        if (uri == null) {
            return;
        }
        if (this._excludedURI == null) {
            this._excludedURI = new Hashtable();
        }
        Integer refcnt = this._excludedURI.get(uri);
        if (refcnt == null) {
            refcnt = new Integer(1);
        }
        else {
            refcnt = new Integer(refcnt + 1);
        }
        this._excludedURI.put(uri, refcnt);
    }
    
    public void excludeNamespaces(final String prefixes) {
        if (prefixes != null) {
            final StringTokenizer tokens = new StringTokenizer(prefixes);
            while (tokens.hasMoreTokens()) {
                final String prefix = tokens.nextToken();
                String uri;
                if (prefix.equals("#default")) {
                    uri = this.lookupNamespace("");
                }
                else {
                    uri = this.lookupNamespace(prefix);
                }
                if (uri != null) {
                    this.excludeURI(uri);
                }
            }
        }
    }
    
    public boolean isExcludedNamespace(final String uri) {
        if (uri != null && this._excludedURI != null) {
            final Integer refcnt = this._excludedURI.get(uri);
            return refcnt != null && refcnt > 0;
        }
        return false;
    }
    
    public void unExcludeNamespaces(final String prefixes) {
        if (this._excludedURI == null) {
            return;
        }
        if (prefixes != null) {
            final StringTokenizer tokens = new StringTokenizer(prefixes);
            while (tokens.hasMoreTokens()) {
                final String prefix = tokens.nextToken();
                String uri;
                if (prefix.equals("#default")) {
                    uri = this.lookupNamespace("");
                }
                else {
                    uri = this.lookupNamespace(prefix);
                }
                final Integer refcnt = this._excludedURI.get(uri);
                if (refcnt != null) {
                    this._excludedURI.put(uri, new Integer(refcnt - 1));
                }
            }
        }
    }
}
