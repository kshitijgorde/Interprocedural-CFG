// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.domapi;

import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.Event;
import org.w3c.dom.DOMException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.xpath.XPathException;
import org.apache.xpath.res.XPATHMessages;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.Node;
import org.apache.xpath.XPath;
import org.apache.xpath.objects.XObject;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.xpath.XPathResult;

class XPathResultImpl implements XPathResult, EventListener
{
    private final XObject m_resultObj;
    private final XPath m_xpath;
    private final short m_resultType;
    private boolean m_isInvalidIteratorState;
    private final Node m_contextNode;
    private NodeIterator m_iterator;
    private NodeList m_list;
    
    XPathResultImpl(final short type, final XObject result, final Node contextNode, final XPath xpath) {
        this.m_isInvalidIteratorState = false;
        this.m_iterator = null;
        this.m_list = null;
        if (!isValidType(type)) {
            final String fmsg = XPATHMessages.createXPATHMessage("ER_INVALID_XPATH_TYPE", new Object[] { new Integer(type) });
            throw new XPathException((short)52, fmsg);
        }
        if (null == result) {
            final String fmsg = XPATHMessages.createXPATHMessage("ER_EMPTY_XPATH_RESULT", null);
            throw new XPathException((short)51, fmsg);
        }
        this.m_resultObj = result;
        this.m_contextNode = contextNode;
        this.m_xpath = xpath;
        if (type == 0) {
            this.m_resultType = this.getTypeFromXObject(result);
        }
        else {
            this.m_resultType = type;
        }
        if (this.m_resultType == 5 || this.m_resultType == 4) {
            this.addEventListener();
        }
        if (this.m_resultType != 5 && this.m_resultType != 4 && this.m_resultType != 8) {
            if (this.m_resultType != 9) {
                if (this.m_resultType != 6) {
                    if (this.m_resultType != 7) {
                        return;
                    }
                }
                try {
                    this.m_list = this.m_resultObj.nodelist();
                }
                catch (TransformerException te) {
                    final String fmsg2 = XPATHMessages.createXPATHMessage("ER_INCOMPATIBLE_TYPES", new Object[] { this.m_xpath.getPatternString(), this.getTypeString(this.getTypeFromXObject(this.m_resultObj)), this.getTypeString(this.m_resultType) });
                    throw new XPathException((short)52, fmsg2);
                }
                return;
            }
        }
        try {
            this.m_iterator = this.m_resultObj.nodeset();
        }
        catch (TransformerException te) {
            final String fmsg2 = XPATHMessages.createXPATHMessage("ER_INCOMPATIBLE_TYPES", new Object[] { this.m_xpath.getPatternString(), this.getTypeString(this.getTypeFromXObject(this.m_resultObj)), this.getTypeString(this.m_resultType) });
            throw new XPathException((short)52, fmsg2);
        }
    }
    
    public short getResultType() {
        return this.m_resultType;
    }
    
    public double getNumberValue() throws XPathException {
        if (this.getResultType() != 1) {
            final String fmsg = XPATHMessages.createXPATHMessage("ER_CANT_CONVERT_XPATHRESULTTYPE_TO_NUMBER", new Object[] { this.m_xpath.getPatternString(), this.getTypeString(this.m_resultType) });
            throw new XPathException((short)52, fmsg);
        }
        try {
            return this.m_resultObj.num();
        }
        catch (Exception e) {
            throw new XPathException((short)52, e.getMessage());
        }
    }
    
    public String getStringValue() throws XPathException {
        if (this.getResultType() != 2) {
            final String fmsg = XPATHMessages.createXPATHMessage("ER_CANT_CONVERT_TO_STRING", new Object[] { this.m_xpath.getPatternString(), this.m_resultObj.getTypeString() });
            throw new XPathException((short)52, fmsg);
        }
        try {
            return this.m_resultObj.str();
        }
        catch (Exception e) {
            throw new XPathException((short)52, e.getMessage());
        }
    }
    
    public boolean getBooleanValue() throws XPathException {
        if (this.getResultType() != 3) {
            final String fmsg = XPATHMessages.createXPATHMessage("ER_CANT_CONVERT_TO_BOOLEAN", new Object[] { this.m_xpath.getPatternString(), this.getTypeString(this.m_resultType) });
            throw new XPathException((short)52, fmsg);
        }
        try {
            return this.m_resultObj.bool();
        }
        catch (TransformerException e) {
            throw new XPathException((short)52, e.getMessage());
        }
    }
    
    public Node getSingleNodeValue() throws XPathException {
        if (this.m_resultType != 8 && this.m_resultType != 9) {
            final String fmsg = XPATHMessages.createXPATHMessage("ER_CANT_CONVERT_TO_SINGLENODE", new Object[] { this.m_xpath.getPatternString(), this.getTypeString(this.m_resultType) });
            throw new XPathException((short)52, fmsg);
        }
        NodeIterator result = null;
        try {
            result = this.m_resultObj.nodeset();
        }
        catch (TransformerException te) {
            throw new XPathException((short)52, te.getMessage());
        }
        if (null == result) {
            return null;
        }
        final Node node = result.nextNode();
        if (this.isNamespaceNode(node)) {
            return new XPathNamespaceImpl(node);
        }
        return node;
    }
    
    public boolean getInvalidIteratorState() {
        return this.m_isInvalidIteratorState;
    }
    
    public int getSnapshotLength() throws XPathException {
        if (this.m_resultType != 6 && this.m_resultType != 7) {
            final String fmsg = XPATHMessages.createXPATHMessage("ER_CANT_GET_SNAPSHOT_LENGTH", new Object[] { this.m_xpath.getPatternString(), this.getTypeString(this.m_resultType) });
            throw new XPathException((short)52, fmsg);
        }
        return this.m_list.getLength();
    }
    
    public Node iterateNext() throws XPathException, DOMException {
        if (this.m_resultType != 4 && this.m_resultType != 5) {
            final String fmsg = XPATHMessages.createXPATHMessage("ER_NON_ITERATOR_TYPE", new Object[] { this.m_xpath.getPatternString(), this.getTypeString(this.m_resultType) });
            throw new XPathException((short)52, fmsg);
        }
        if (this.getInvalidIteratorState()) {
            final String fmsg = XPATHMessages.createXPATHMessage("ER_DOC_MUTATED", null);
            throw new DOMException((short)11, fmsg);
        }
        final Node node = this.m_iterator.nextNode();
        if (null == node) {
            this.removeEventListener();
        }
        if (this.isNamespaceNode(node)) {
            return new XPathNamespaceImpl(node);
        }
        return node;
    }
    
    public Node snapshotItem(final int index) throws XPathException {
        if (this.m_resultType != 6 && this.m_resultType != 7) {
            final String fmsg = XPATHMessages.createXPATHMessage("ER_NON_SNAPSHOT_TYPE", new Object[] { this.m_xpath.getPatternString(), this.getTypeString(this.m_resultType) });
            throw new XPathException((short)52, fmsg);
        }
        final Node node = this.m_list.item(index);
        if (this.isNamespaceNode(node)) {
            return new XPathNamespaceImpl(node);
        }
        return node;
    }
    
    static boolean isValidType(final short type) {
        switch (type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public void handleEvent(final Event event) {
        if (event.getType().equals("DOMSubtreeModified")) {
            this.m_isInvalidIteratorState = true;
            this.removeEventListener();
        }
    }
    
    private String getTypeString(final int type) {
        switch (type) {
            case 0: {
                return "ANY_TYPE";
            }
            case 8: {
                return "ANY_UNORDERED_NODE_TYPE";
            }
            case 3: {
                return "BOOLEAN";
            }
            case 9: {
                return "FIRST_ORDERED_NODE_TYPE";
            }
            case 1: {
                return "NUMBER_TYPE";
            }
            case 5: {
                return "ORDERED_NODE_ITERATOR_TYPE";
            }
            case 7: {
                return "ORDERED_NODE_SNAPSHOT_TYPE";
            }
            case 2: {
                return "STRING_TYPE";
            }
            case 4: {
                return "UNORDERED_NODE_ITERATOR_TYPE";
            }
            case 6: {
                return "UNORDERED_NODE_SNAPSHOT_TYPE";
            }
            default: {
                return "#UNKNOWN";
            }
        }
    }
    
    private short getTypeFromXObject(final XObject object) {
        switch (object.getType()) {
            case 1: {
                return 3;
            }
            case 4: {
                return 4;
            }
            case 2: {
                return 1;
            }
            case 3: {
                return 2;
            }
            case 5: {
                return 4;
            }
            case -1: {
                return 0;
            }
            default: {
                return 0;
            }
        }
    }
    
    private boolean isNamespaceNode(final Node node) {
        return null != node && node.getNodeType() == 2 && (node.getNodeName().startsWith("xmlns:") || node.getNodeName().equals("xmlns"));
    }
    
    private void addEventListener() {
        if (this.m_contextNode instanceof EventTarget) {
            ((EventTarget)this.m_contextNode).addEventListener("DOMSubtreeModified", this, true);
        }
    }
    
    private void removeEventListener() {
        if (this.m_contextNode instanceof EventTarget) {
            ((EventTarget)this.m_contextNode).removeEventListener("DOMSubtreeModified", this, true);
        }
    }
}
