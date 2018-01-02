// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.apache.xalan.xsltc.runtime.BasisLibrary;
import org.apache.xml.dtm.Axis;
import org.apache.xml.dtm.ref.DTMAxisIteratorBase;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.apache.xalan.xsltc.StripFilter;
import org.apache.xalan.xsltc.TransletException;
import org.apache.xml.serializer.SerializationHandler;
import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xml.utils.SuballocatedIntVector;
import org.apache.xml.dtm.ref.DTMDefaultBase;
import org.apache.xalan.xsltc.runtime.Hashtable;
import org.apache.xml.dtm.DTMManager;
import org.apache.xalan.xsltc.DOM;

public final class MultiDOM implements DOM
{
    private static final int NO_TYPE = -2;
    private static final int INITIAL_SIZE = 4;
    private DOM[] _adapters;
    private DOMAdapter _main;
    private DTMManager _dtmManager;
    private int _free;
    private int _size;
    private Hashtable _documents;
    
    public MultiDOM(final DOM main) {
        this._documents = new Hashtable();
        this._size = 4;
        this._free = 1;
        this._adapters = new DOM[4];
        final DOMAdapter adapter = (DOMAdapter)main;
        this._adapters[0] = adapter;
        this._main = adapter;
        final DOM dom = adapter.getDOMImpl();
        if (dom instanceof DTMDefaultBase) {
            this._dtmManager = ((DTMDefaultBase)dom).getManager();
        }
        this.addDOMAdapter(adapter, false);
    }
    
    public int nextMask() {
        return this._free;
    }
    
    public void setupMapping(final String[] names, final String[] uris, final int[] types, final String[] namespaces) {
    }
    
    public int addDOMAdapter(final DOMAdapter adapter) {
        return this.addDOMAdapter(adapter, true);
    }
    
    private int addDOMAdapter(final DOMAdapter adapter, final boolean indexByURI) {
        final DOM dom = adapter.getDOMImpl();
        int domNo = 1;
        int dtmSize = 1;
        SuballocatedIntVector dtmIds = null;
        if (dom instanceof DTMDefaultBase) {
            final DTMDefaultBase dtmdb = (DTMDefaultBase)dom;
            dtmIds = dtmdb.getDTMIDs();
            dtmSize = dtmIds.size();
            domNo = dtmIds.elementAt(dtmSize - 1) >>> 16;
        }
        else if (dom instanceof SimpleResultTreeImpl) {
            final SimpleResultTreeImpl simpleRTF = (SimpleResultTreeImpl)dom;
            domNo = simpleRTF.getDocument() >>> 16;
        }
        if (domNo >= this._size) {
            final int oldSize = this._size;
            do {
                this._size *= 2;
            } while (this._size <= domNo);
            final DOMAdapter[] newArray = new DOMAdapter[this._size];
            System.arraycopy(this._adapters, 0, newArray, 0, oldSize);
            this._adapters = newArray;
        }
        this._free = domNo + 1;
        if (dtmSize == 1) {
            this._adapters[domNo] = adapter;
        }
        else if (dtmIds != null) {
            int domPos = 0;
            for (int i = dtmSize - 1; i >= 0; --i) {
                domPos = dtmIds.elementAt(i) >>> 16;
                this._adapters[domPos] = adapter;
            }
            domNo = domPos;
        }
        if (indexByURI) {
            final String uri = adapter.getDocumentURI(0);
            this._documents.put(uri, new Integer(domNo));
        }
        if (dom instanceof AdaptiveResultTreeImpl) {
            final AdaptiveResultTreeImpl adaptiveRTF = (AdaptiveResultTreeImpl)dom;
            final DOM nestedDom = adaptiveRTF.getNestedDOM();
            if (nestedDom != null) {
                final DOMAdapter newAdapter = new DOMAdapter(nestedDom, adapter.getNamesArray(), adapter.getUrisArray(), adapter.getTypesArray(), adapter.getNamespaceArray());
                this.addDOMAdapter(newAdapter);
            }
        }
        return domNo;
    }
    
    public int getDocumentMask(final String uri) {
        final Integer domIdx = (Integer)this._documents.get(uri);
        if (domIdx == null) {
            return -1;
        }
        return domIdx;
    }
    
    public DOM getDOMAdapter(final String uri) {
        final Integer domIdx = (Integer)this._documents.get(uri);
        if (domIdx == null) {
            return null;
        }
        return this._adapters[domIdx];
    }
    
    public int getDocument() {
        return this._main.getDocument();
    }
    
    public DTMManager getDTMManager() {
        return this._dtmManager;
    }
    
    public DTMAxisIterator getIterator() {
        return this._main.getIterator();
    }
    
    public String getStringValue() {
        return this._main.getStringValue();
    }
    
    public DTMAxisIterator getChildren(final int node) {
        return this._adapters[this.getDTMId(node)].getChildren(node);
    }
    
    public DTMAxisIterator getTypedChildren(final int type) {
        return new AxisIterator(3, type);
    }
    
    public DTMAxisIterator getAxisIterator(final int axis) {
        return new AxisIterator(axis, -2);
    }
    
    public DTMAxisIterator getTypedAxisIterator(final int axis, final int type) {
        return new AxisIterator(axis, type);
    }
    
    public DTMAxisIterator getNthDescendant(final int node, final int n, final boolean includeself) {
        return this._adapters[this.getDTMId(node)].getNthDescendant(node, n, includeself);
    }
    
    public DTMAxisIterator getNodeValueIterator(final DTMAxisIterator iterator, final int type, final String value, final boolean op) {
        return new NodeValueIterator(iterator, type, value, op);
    }
    
    public DTMAxisIterator getNamespaceAxisIterator(final int axis, final int ns) {
        final DTMAxisIterator iterator = this._main.getNamespaceAxisIterator(axis, ns);
        return iterator;
    }
    
    public DTMAxisIterator orderNodes(final DTMAxisIterator source, final int node) {
        return this._adapters[this.getDTMId(node)].orderNodes(source, node);
    }
    
    public int getExpandedTypeID(final int node) {
        if (node != -1) {
            return this._adapters[node >>> 16].getExpandedTypeID(node);
        }
        return -1;
    }
    
    public int getNamespaceType(final int node) {
        return this._adapters[this.getDTMId(node)].getNamespaceType(node);
    }
    
    public int getNSType(final int node) {
        return this._adapters[this.getDTMId(node)].getNSType(node);
    }
    
    public int getParent(final int node) {
        if (node == -1) {
            return -1;
        }
        return this._adapters[node >>> 16].getParent(node);
    }
    
    public int getAttributeNode(final int type, final int el) {
        if (el == -1) {
            return -1;
        }
        return this._adapters[el >>> 16].getAttributeNode(type, el);
    }
    
    public String getNodeName(final int node) {
        if (node == -1) {
            return "";
        }
        return this._adapters[node >>> 16].getNodeName(node);
    }
    
    public String getNodeNameX(final int node) {
        if (node == -1) {
            return "";
        }
        return this._adapters[node >>> 16].getNodeNameX(node);
    }
    
    public String getNamespaceName(final int node) {
        if (node == -1) {
            return "";
        }
        return this._adapters[node >>> 16].getNamespaceName(node);
    }
    
    public String getStringValueX(final int node) {
        if (node == -1) {
            return "";
        }
        return this._adapters[node >>> 16].getStringValueX(node);
    }
    
    public void copy(final int node, final SerializationHandler handler) throws TransletException {
        if (node != -1) {
            this._adapters[node >>> 16].copy(node, handler);
        }
    }
    
    public void copy(final DTMAxisIterator nodes, final SerializationHandler handler) throws TransletException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: goto            19
        //     3: aload_0         /* this */
        //     4: getfield        org/apache/xalan/xsltc/dom/MultiDOM._adapters:[Lorg/apache/xalan/xsltc/DOM;
        //     7: iload_3        
        //     8: bipush          16
        //    10: iushr          
        //    11: aaload         
        //    12: iload_3        
        //    13: aload_2         /* handler */
        //    14: invokeinterface org/apache/xalan/xsltc/DOM.copy:(ILorg/apache/xml/serializer/SerializationHandler;)V
        //    19: aload_1         /* nodes */
        //    20: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
        //    25: dup            
        //    26: istore_3        /* node */
        //    27: iconst_m1      
        //    28: if_icmpne       3
        //    31: return         
        //    Exceptions:
        //  throws org.apache.xalan.xsltc.TransletException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ------------------------------------------------
        //  0      32      0     this     Lorg/apache/xalan/xsltc/dom/MultiDOM;
        //  0      32      1     nodes    Lorg/apache/xml/dtm/DTMAxisIterator;
        //  0      32      2     handler  Lorg/apache/xml/serializer/SerializationHandler;
        //  27     4       3     node     I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public String shallowCopy(final int node, final SerializationHandler handler) throws TransletException {
        if (node == -1) {
            return "";
        }
        return this._adapters[node >>> 16].shallowCopy(node, handler);
    }
    
    public boolean lessThan(final int node1, final int node2) {
        if (node1 == -1) {
            return true;
        }
        if (node2 == -1) {
            return false;
        }
        final int dom1 = this.getDTMId(node1);
        final int dom2 = this.getDTMId(node2);
        return (dom1 == dom2) ? this._adapters[dom1].lessThan(node1, node2) : (dom1 < dom2);
    }
    
    public void characters(final int textNode, final SerializationHandler handler) throws TransletException {
        if (textNode != -1) {
            this._adapters[textNode >>> 16].characters(textNode, handler);
        }
    }
    
    public void setFilter(final StripFilter filter) {
        for (int dom = 0; dom < this._free; ++dom) {
            if (this._adapters[dom] != null) {
                this._adapters[dom].setFilter(filter);
            }
        }
    }
    
    public Node makeNode(final int index) {
        if (index == -1) {
            return null;
        }
        return this._adapters[this.getDTMId(index)].makeNode(index);
    }
    
    public Node makeNode(final DTMAxisIterator iter) {
        return this._main.makeNode(iter);
    }
    
    public NodeList makeNodeList(final int index) {
        if (index == -1) {
            return null;
        }
        return this._adapters[this.getDTMId(index)].makeNodeList(index);
    }
    
    public NodeList makeNodeList(final DTMAxisIterator iter) {
        return this._main.makeNodeList(iter);
    }
    
    public String getLanguage(final int node) {
        return this._adapters[this.getDTMId(node)].getLanguage(node);
    }
    
    public int getSize() {
        int size = 0;
        for (int i = 0; i < this._size; ++i) {
            size += this._adapters[i].getSize();
        }
        return size;
    }
    
    public String getDocumentURI(int node) {
        if (node == -1) {
            node = 0;
        }
        return this._adapters[node >>> 16].getDocumentURI(0);
    }
    
    public boolean isElement(final int node) {
        return node != -1 && this._adapters[node >>> 16].isElement(node);
    }
    
    public boolean isAttribute(final int node) {
        return node != -1 && this._adapters[node >>> 16].isAttribute(node);
    }
    
    public int getDTMId(final int nodeHandle) {
        if (nodeHandle == -1) {
            return 0;
        }
        int id;
        for (id = nodeHandle >>> 16; id >= 2 && this._adapters[id] == this._adapters[id - 1]; --id) {}
        return id;
    }
    
    public int getNodeIdent(final int nodeHandle) {
        return this._adapters[nodeHandle >>> 16].getNodeIdent(nodeHandle);
    }
    
    public int getNodeHandle(final int nodeId) {
        return this._main.getNodeHandle(nodeId);
    }
    
    public DOM getResultTreeFrag(final int initSize, final int rtfType) {
        return this._main.getResultTreeFrag(initSize, rtfType);
    }
    
    public DOM getResultTreeFrag(final int initSize, final int rtfType, final boolean addToManager) {
        return this._main.getResultTreeFrag(initSize, rtfType, addToManager);
    }
    
    public DOM getMain() {
        return this._main;
    }
    
    public SerializationHandler getOutputDomBuilder() {
        return this._main.getOutputDomBuilder();
    }
    
    public String lookupNamespace(final int node, final String prefix) throws TransletException {
        return this._main.lookupNamespace(node, prefix);
    }
    
    public String getUnparsedEntityURI(final String entity) {
        return this._main.getUnparsedEntityURI(entity);
    }
    
    public Hashtable getElementsWithIDs() {
        return this._main.getElementsWithIDs();
    }
    
    private final class AxisIterator extends DTMAxisIteratorBase
    {
        private final int _axis;
        private final int _type;
        private DTMAxisIterator _source;
        private int _dtmId;
        
        public AxisIterator(final int axis, final int type) {
            this._dtmId = -1;
            this._axis = axis;
            this._type = type;
        }
        
        public int next() {
            if (this._source == null) {
                return -1;
            }
            return this._source.next();
        }
        
        public void setRestartable(final boolean flag) {
            if (this._source != null) {
                this._source.setRestartable(flag);
            }
        }
        
        public DTMAxisIterator setStartNode(final int node) {
            if (node == -1) {
                return this;
            }
            final int dom = node >>> 16;
            if (this._source == null || this._dtmId != dom) {
                if (this._type == -2) {
                    this._source = MultiDOM.this._adapters[dom].getAxisIterator(this._axis);
                }
                else if (this._axis == 3) {
                    this._source = MultiDOM.this._adapters[dom].getTypedChildren(this._type);
                }
                else {
                    this._source = MultiDOM.this._adapters[dom].getTypedAxisIterator(this._axis, this._type);
                }
            }
            this._dtmId = dom;
            this._source.setStartNode(node);
            return this;
        }
        
        public DTMAxisIterator reset() {
            if (this._source != null) {
                this._source.reset();
            }
            return this;
        }
        
        public int getLast() {
            if (this._source != null) {
                return this._source.getLast();
            }
            return -1;
        }
        
        public int getPosition() {
            if (this._source != null) {
                return this._source.getPosition();
            }
            return -1;
        }
        
        public boolean isReverse() {
            return Axis.isReverse(this._axis);
        }
        
        public void setMark() {
            if (this._source != null) {
                this._source.setMark();
            }
        }
        
        public void gotoMark() {
            if (this._source != null) {
                this._source.gotoMark();
            }
        }
        
        public DTMAxisIterator cloneIterator() {
            final AxisIterator clone = new AxisIterator(this._axis, this._type);
            if (this._source != null) {
                clone._source = this._source.cloneIterator();
            }
            clone._dtmId = this._dtmId;
            return clone;
        }
    }
    
    private final class NodeValueIterator extends DTMAxisIteratorBase
    {
        private DTMAxisIterator _source;
        private String _value;
        private boolean _op;
        private final boolean _isReverse;
        private int _returnType;
        
        public NodeValueIterator(final DTMAxisIterator source, final int returnType, final String value, final boolean op) {
            this._returnType = 1;
            this._source = source;
            this._returnType = returnType;
            this._value = value;
            this._op = op;
            this._isReverse = source.isReverse();
        }
        
        public boolean isReverse() {
            return this._isReverse;
        }
        
        public DTMAxisIterator cloneIterator() {
            try {
                final NodeValueIterator clone = (NodeValueIterator)super.clone();
                clone._source = this._source.cloneIterator();
                clone.setRestartable(false);
                return clone.reset();
            }
            catch (CloneNotSupportedException e) {
                BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
                return null;
            }
        }
        
        public void setRestartable(final boolean isRestartable) {
            super._isRestartable = isRestartable;
            this._source.setRestartable(isRestartable);
        }
        
        public DTMAxisIterator reset() {
            this._source.reset();
            return this.resetPosition();
        }
        
        public int next() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: goto            53
            //     3: aload_0         /* this */
            //     4: getfield        org/apache/xalan/xsltc/dom/MultiDOM$NodeValueIterator.this$0:Lorg/apache/xalan/xsltc/dom/MultiDOM;
            //     7: iload_1        
            //     8: invokevirtual   org/apache/xalan/xsltc/dom/MultiDOM.getStringValueX:(I)Ljava/lang/String;
            //    11: astore_2        /* val */
            //    12: aload_0         /* this */
            //    13: getfield        org/apache/xalan/xsltc/dom/MultiDOM$NodeValueIterator._value:Ljava/lang/String;
            //    16: aload_2         /* val */
            //    17: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
            //    20: aload_0         /* this */
            //    21: getfield        org/apache/xalan/xsltc/dom/MultiDOM$NodeValueIterator._op:Z
            //    24: if_icmpne       53
            //    27: aload_0         /* this */
            //    28: getfield        org/apache/xalan/xsltc/dom/MultiDOM$NodeValueIterator._returnType:I
            //    31: ifne            40
            //    34: aload_0         /* this */
            //    35: iload_1        
            //    36: invokevirtual   org/apache/xml/dtm/ref/DTMAxisIteratorBase.returnNode:(I)I
            //    39: ireturn        
            //    40: aload_0         /* this */
            //    41: aload_0         /* this */
            //    42: getfield        org/apache/xalan/xsltc/dom/MultiDOM$NodeValueIterator.this$0:Lorg/apache/xalan/xsltc/dom/MultiDOM;
            //    45: iload_1        
            //    46: invokevirtual   org/apache/xalan/xsltc/dom/MultiDOM.getParent:(I)I
            //    49: invokevirtual   org/apache/xml/dtm/ref/DTMAxisIteratorBase.returnNode:(I)I
            //    52: ireturn        
            //    53: aload_0         /* this */
            //    54: getfield        org/apache/xalan/xsltc/dom/MultiDOM$NodeValueIterator._source:Lorg/apache/xml/dtm/DTMAxisIterator;
            //    57: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
            //    62: dup            
            //    63: istore_1        /* node */
            //    64: iconst_m1      
            //    65: if_icmpne       3
            //    68: iconst_m1      
            //    69: ireturn        
            //    LocalVariableTable:
            //  Start  Length  Slot  Name  Signature
            //  -----  ------  ----  ----  -------------------------------------------------------
            //  0      70      0     this  Lorg/apache/xalan/xsltc/dom/MultiDOM$NodeValueIterator;
            //  64     6       1     node  I
            //  12     41      2     val   Ljava/lang/String;
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public DTMAxisIterator setStartNode(final int node) {
            if (super._isRestartable) {
                this._source.setStartNode(super._startNode = node);
                return this.resetPosition();
            }
            return this;
        }
        
        public void setMark() {
            this._source.setMark();
        }
        
        public void gotoMark() {
            this._source.gotoMark();
        }
    }
}
