// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.apache.xerces.dom3.as.DOMASException;
import org.apache.xerces.dom3.as.ASContentModel;
import org.apache.xerces.dom3.as.ASEntityDeclaration;
import org.apache.xerces.dom3.as.ASNotationDeclaration;
import org.apache.xerces.dom3.as.ASAttributeDeclaration;
import org.apache.xerces.dom3.as.ASElementDeclaration;
import org.apache.xerces.dom3.as.ASObjectList;
import org.apache.xerces.dom3.as.ASNamedObjectMap;
import org.apache.xerces.dom3.as.ASObject;
import org.w3c.dom.DOMException;
import org.apache.xerces.impl.xs.SchemaGrammar;
import java.util.Vector;
import org.apache.xerces.dom3.as.ASModel;

public class ASModelImpl implements ASModel
{
    boolean fNamespaceAware;
    protected Vector fASModels;
    protected SchemaGrammar fGrammar;
    
    public ASModelImpl() {
        this.fNamespaceAware = true;
        this.fGrammar = null;
        this.fASModels = new Vector();
    }
    
    public ASModelImpl(final boolean isNamespaceAware) {
        this.fNamespaceAware = true;
        this.fGrammar = null;
        this.fASModels = new Vector();
        this.fNamespaceAware = isNamespaceAware;
    }
    
    public short getAsNodeType() {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public ASModel getOwnerASModel() {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public void setOwnerASModel(final ASModel ownerASModel) {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public String getNodeName() {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public void setNodeName(final String nodeName) {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public String getPrefix() {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public void setPrefix(final String prefix) {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public String getLocalName() {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public void setLocalName(final String localName) {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public String getNamespaceURI() {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public void setNamespaceURI(final String namespaceURI) {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public ASObject cloneASObject(final boolean deep) {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public boolean getIsNamespaceAware() {
        return this.fNamespaceAware;
    }
    
    public short getUsageLocation() {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public String getAsLocation() {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public void setAsLocation(final String asLocation) {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public String getAsHint() {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public void setAsHint(final String asHint) {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public boolean getContainer() {
        return this.fGrammar != null;
    }
    
    public ASNamedObjectMap getElementDeclarations() {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public ASNamedObjectMap getAttributeDeclarations() {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public ASNamedObjectMap getNotationDeclarations() {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public ASNamedObjectMap getEntityDeclarations() {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public ASNamedObjectMap getContentModelDeclarations() {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public void addASModel(final ASModel abstractSchema) {
        this.fASModels.addElement(abstractSchema);
    }
    
    public ASObjectList getASModels() {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public void removeAS(final ASModel as) {
        this.fASModels.removeElement(as);
    }
    
    public boolean validate() {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public void importASObject(final ASObject asobject) {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public void insertASObject(final ASObject asobject) {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public ASElementDeclaration createASElementDeclaration(final String namespaceURI, final String name) throws DOMException {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public ASAttributeDeclaration createASAttributeDeclaration(final String namespaceURI, final String name) throws DOMException {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public ASNotationDeclaration createASNotationDeclaration(final String namespaceURI, final String name, final String systemId, final String publicId) throws DOMException {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public ASEntityDeclaration createASEntityDeclaration(final String name) throws DOMException {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public ASContentModel createASContentModel(final int minOccurs, final int maxOccurs, final short operator) throws DOMASException {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    public SchemaGrammar getGrammar() {
        return this.fGrammar;
    }
    
    public void setGrammar(final SchemaGrammar grammar) {
        this.fGrammar = grammar;
    }
    
    public Vector getInternalASModels() {
        return this.fASModels;
    }
}
