// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xml.dtm.ref.DTMNodeList;
import org.w3c.dom.NodeList;
import javax.xml.transform.TransformerException;
import org.apache.xml.dtm.ref.DTMNodeIterator;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xml.utils.FastStringBuffer;
import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;
import org.apache.xml.utils.XMLString;
import org.apache.xpath.NodeSetDTM;
import org.apache.xml.dtm.DTMManager;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xpath.axes.NodeSequence;

public class XNodeSet extends NodeSequence
{
    static final long serialVersionUID = 1916026368035639667L;
    static final LessThanComparator S_LT;
    static final LessThanOrEqualComparator S_LTE;
    static final GreaterThanComparator S_GT;
    static final GreaterThanOrEqualComparator S_GTE;
    static final EqualComparator S_EQ;
    static final NotEqualComparator S_NEQ;
    
    protected XNodeSet() {
    }
    
    public XNodeSet(final DTMIterator val) {
        if (val instanceof XNodeSet) {
            this.setIter(((XNodeSet)val).m_iter);
            super.m_dtmMgr = ((XNodeSet)val).m_dtmMgr;
            super.m_last = ((XNodeSet)val).m_last;
            if (!((XNodeSet)val).hasCache()) {
                ((XNodeSet)val).setShouldCacheNodes(true);
            }
            super.m_obj = ((XNodeSet)val).m_obj;
        }
        else {
            this.setIter(val);
        }
    }
    
    public XNodeSet(final XNodeSet val) {
        this.setIter(val.m_iter);
        super.m_dtmMgr = val.m_dtmMgr;
        super.m_last = val.m_last;
        if (!val.hasCache()) {
            val.setShouldCacheNodes(true);
        }
        super.m_obj = val.m_obj;
    }
    
    public XNodeSet(final DTMManager dtmMgr) {
        this(-1, dtmMgr);
    }
    
    public XNodeSet(final int n, final DTMManager dtmMgr) {
        super(new NodeSetDTM(dtmMgr));
        super.m_dtmMgr = dtmMgr;
        if (-1 != n) {
            ((NodeSetDTM)super.m_obj).addNode(n);
            super.m_last = 1;
        }
        else {
            super.m_last = 0;
        }
    }
    
    public int getType() {
        return 4;
    }
    
    public String getTypeString() {
        return "#NODESET";
    }
    
    public double getNumberFromNode(final int n) {
        final XMLString xstr = super.m_dtmMgr.getDTM(n).getStringValue(n);
        return xstr.toDouble();
    }
    
    public double num() {
        final int node = this.item(0);
        return (node != -1) ? this.getNumberFromNode(node) : Double.NaN;
    }
    
    public double numWithSideEffects() {
        final int node = this.nextNode();
        return (node != -1) ? this.getNumberFromNode(node) : Double.NaN;
    }
    
    public boolean bool() {
        return this.item(0) != -1;
    }
    
    public boolean boolWithSideEffects() {
        return this.nextNode() != -1;
    }
    
    public XMLString getStringFromNode(final int n) {
        if (-1 != n) {
            return super.m_dtmMgr.getDTM(n).getStringValue(n);
        }
        return XString.EMPTYSTRING;
    }
    
    public void dispatchCharactersEvents(final ContentHandler ch) throws SAXException {
        final int node = this.item(0);
        if (node != -1) {
            super.m_dtmMgr.getDTM(node).dispatchCharactersEvents(node, ch, false);
        }
    }
    
    public XMLString xstr() {
        final int node = this.item(0);
        return (node != -1) ? this.getStringFromNode(node) : XString.EMPTYSTRING;
    }
    
    public void appendToFsb(final FastStringBuffer fsb) {
        final XString xstring = (XString)this.xstr();
        xstring.appendToFsb(fsb);
    }
    
    public String str() {
        final int node = this.item(0);
        return (node != -1) ? this.getStringFromNode(node).toString() : "";
    }
    
    public Object object() {
        if (null == super.m_obj) {
            return this;
        }
        return super.m_obj;
    }
    
    public NodeIterator nodeset() throws TransformerException {
        return new DTMNodeIterator(this.iter());
    }
    
    public NodeList nodelist() throws TransformerException {
        final DTMNodeList nodelist = new DTMNodeList(this);
        final XNodeSet clone = (XNodeSet)nodelist.getDTMIterator();
        this.SetVector(clone.getVector());
        return nodelist;
    }
    
    public DTMIterator iterRaw() {
        return this;
    }
    
    public void release(final DTMIterator iter) {
    }
    
    public DTMIterator iter() {
        try {
            if (this.hasCache()) {
                return this.cloneWithReset();
            }
            return this;
        }
        catch (CloneNotSupportedException cnse) {
            throw new RuntimeException(cnse.getMessage());
        }
    }
    
    public XObject getFresh() {
        try {
            if (this.hasCache()) {
                return (XObject)this.cloneWithReset();
            }
            return this;
        }
        catch (CloneNotSupportedException cnse) {
            throw new RuntimeException(cnse.getMessage());
        }
    }
    
    public NodeSetDTM mutableNodeset() {
        NodeSetDTM mnl;
        if (super.m_obj instanceof NodeSetDTM) {
            mnl = (NodeSetDTM)super.m_obj;
        }
        else {
            mnl = new NodeSetDTM(this.iter());
            super.m_obj = mnl;
            this.setCurrentPos(0);
        }
        return mnl;
    }
    
    public boolean compare(final XObject obj2, final Comparator comparator) throws TransformerException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_3        /* result */
        //     2: aload_1         /* obj2 */
        //     3: invokevirtual   org/apache/xpath/objects/XObject.getType:()I
        //     6: istore          type
        //     8: iconst_4       
        //     9: iload           type
        //    11: if_icmpne       193
        //    14: aload_0         /* this */
        //    15: invokevirtual   org/apache/xpath/objects/XNodeSet.iterRaw:()Lorg/apache/xml/dtm/DTMIterator;
        //    18: astore          list1
        //    20: aload_1         /* obj2 */
        //    21: checkcast       Lorg/apache/xpath/objects/XNodeSet;
        //    24: invokevirtual   org/apache/xpath/objects/XNodeSet.iterRaw:()Lorg/apache/xml/dtm/DTMIterator;
        //    27: astore          list2
        //    29: aconst_null    
        //    30: astore          node2Strings
        //    32: goto            162
        //    35: aload_0         /* this */
        //    36: iload           7
        //    38: invokevirtual   org/apache/xpath/objects/XNodeSet.getStringFromNode:(I)Lorg/apache/xml/utils/XMLString;
        //    41: astore          s1
        //    43: aconst_null    
        //    44: aload           node2Strings
        //    46: if_acmpne       115
        //    49: goto            98
        //    52: aload_0         /* this */
        //    53: iload           10
        //    55: invokevirtual   org/apache/xpath/objects/XNodeSet.getStringFromNode:(I)Lorg/apache/xml/utils/XMLString;
        //    58: astore          s2
        //    60: aload_2         /* comparator */
        //    61: aload           s1
        //    63: aload           s2
        //    65: invokevirtual   org/apache/xpath/objects/Comparator.compareStrings:(Lorg/apache/xml/utils/XMLString;Lorg/apache/xml/utils/XMLString;)Z
        //    68: ifeq            76
        //    71: iconst_1       
        //    72: istore_3        /* result */
        //    73: goto            112
        //    76: aconst_null    
        //    77: aload           node2Strings
        //    79: if_acmpne       91
        //    82: new             Ljava/util/Vector;
        //    85: dup            
        //    86: invokespecial   java/util/Vector.<init>:()V
        //    89: astore          node2Strings
        //    91: aload           node2Strings
        //    93: aload           s2
        //    95: invokevirtual   java/util/Vector.addElement:(Ljava/lang/Object;)V
        //    98: iconst_m1      
        //    99: aload           list2
        //   101: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //   106: dup            
        //   107: istore          node2
        //   109: if_icmpne       52
        //   112: goto            162
        //   115: aload           node2Strings
        //   117: invokevirtual   java/util/Vector.size:()I
        //   120: istore          n
        //   122: iconst_0       
        //   123: istore          i
        //   125: goto            155
        //   128: aload_2         /* comparator */
        //   129: aload           s1
        //   131: aload           node2Strings
        //   133: iload           i
        //   135: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //   138: checkcast       Lorg/apache/xml/utils/XMLString;
        //   141: invokevirtual   org/apache/xpath/objects/Comparator.compareStrings:(Lorg/apache/xml/utils/XMLString;Lorg/apache/xml/utils/XMLString;)Z
        //   144: ifeq            152
        //   147: iconst_1       
        //   148: istore_3        /* result */
        //   149: goto            162
        //   152: iinc            i, 1
        //   155: iload           i
        //   157: iload           n
        //   159: if_icmplt       128
        //   162: iconst_m1      
        //   163: aload           list1
        //   165: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //   170: dup            
        //   171: istore          node1
        //   173: if_icmpne       35
        //   176: aload           list1
        //   178: invokeinterface org/apache/xml/dtm/DTMIterator.reset:()V
        //   183: aload           list2
        //   185: invokeinterface org/apache/xml/dtm/DTMIterator.reset:()V
        //   190: goto            451
        //   193: iconst_1       
        //   194: iload           type
        //   196: if_icmpne       231
        //   199: aload_0         /* this */
        //   200: invokevirtual   org/apache/xpath/objects/XNodeSet.bool:()Z
        //   203: ifeq            210
        //   206: dconst_1       
        //   207: goto            211
        //   210: dconst_0       
        //   211: dstore          num1
        //   213: aload_1         /* obj2 */
        //   214: invokevirtual   org/apache/xpath/objects/XObject.num:()D
        //   217: dstore          num2
        //   219: aload_2         /* comparator */
        //   220: dload           num1
        //   222: dload           num2
        //   224: invokevirtual   org/apache/xpath/objects/Comparator.compareNumbers:(DD)Z
        //   227: istore_3        /* result */
        //   228: goto            451
        //   231: iconst_2       
        //   232: iload           type
        //   234: if_icmpne       300
        //   237: aload_0         /* this */
        //   238: invokevirtual   org/apache/xpath/objects/XNodeSet.iterRaw:()Lorg/apache/xml/dtm/DTMIterator;
        //   241: astore          list1
        //   243: aload_1         /* obj2 */
        //   244: invokevirtual   org/apache/xpath/objects/XObject.num:()D
        //   247: dstore          num2
        //   249: goto            276
        //   252: aload_0         /* this */
        //   253: iload           8
        //   255: invokevirtual   org/apache/xpath/objects/XNodeSet.getNumberFromNode:(I)D
        //   258: dstore          num1
        //   260: aload_2         /* comparator */
        //   261: dload           num1
        //   263: dload           num2
        //   265: invokevirtual   org/apache/xpath/objects/Comparator.compareNumbers:(DD)Z
        //   268: ifeq            276
        //   271: iconst_1       
        //   272: istore_3        /* result */
        //   273: goto            290
        //   276: iconst_m1      
        //   277: aload           list1
        //   279: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //   284: dup            
        //   285: istore          node
        //   287: if_icmpne       252
        //   290: aload           list1
        //   292: invokeinterface org/apache/xml/dtm/DTMIterator.reset:()V
        //   297: goto            451
        //   300: iconst_5       
        //   301: iload           type
        //   303: if_icmpne       369
        //   306: aload_1         /* obj2 */
        //   307: invokevirtual   org/apache/xpath/objects/XObject.xstr:()Lorg/apache/xml/utils/XMLString;
        //   310: astore          s2
        //   312: aload_0         /* this */
        //   313: invokevirtual   org/apache/xpath/objects/XNodeSet.iterRaw:()Lorg/apache/xml/dtm/DTMIterator;
        //   316: astore          list1
        //   318: goto            345
        //   321: aload_0         /* this */
        //   322: iload           7
        //   324: invokevirtual   org/apache/xpath/objects/XNodeSet.getStringFromNode:(I)Lorg/apache/xml/utils/XMLString;
        //   327: astore          s1
        //   329: aload_2         /* comparator */
        //   330: aload           s1
        //   332: aload           s2
        //   334: invokevirtual   org/apache/xpath/objects/Comparator.compareStrings:(Lorg/apache/xml/utils/XMLString;Lorg/apache/xml/utils/XMLString;)Z
        //   337: ifeq            345
        //   340: iconst_1       
        //   341: istore_3        /* result */
        //   342: goto            359
        //   345: iconst_m1      
        //   346: aload           list1
        //   348: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //   353: dup            
        //   354: istore          node
        //   356: if_icmpne       321
        //   359: aload           list1
        //   361: invokeinterface org/apache/xml/dtm/DTMIterator.reset:()V
        //   366: goto            451
        //   369: iconst_3       
        //   370: iload           type
        //   372: if_icmpne       438
        //   375: aload_1         /* obj2 */
        //   376: invokevirtual   org/apache/xpath/objects/XObject.xstr:()Lorg/apache/xml/utils/XMLString;
        //   379: astore          s2
        //   381: aload_0         /* this */
        //   382: invokevirtual   org/apache/xpath/objects/XNodeSet.iterRaw:()Lorg/apache/xml/dtm/DTMIterator;
        //   385: astore          list1
        //   387: goto            414
        //   390: aload_0         /* this */
        //   391: iload           7
        //   393: invokevirtual   org/apache/xpath/objects/XNodeSet.getStringFromNode:(I)Lorg/apache/xml/utils/XMLString;
        //   396: astore          s1
        //   398: aload_2         /* comparator */
        //   399: aload           s1
        //   401: aload           s2
        //   403: invokevirtual   org/apache/xpath/objects/Comparator.compareStrings:(Lorg/apache/xml/utils/XMLString;Lorg/apache/xml/utils/XMLString;)Z
        //   406: ifeq            414
        //   409: iconst_1       
        //   410: istore_3        /* result */
        //   411: goto            428
        //   414: iconst_m1      
        //   415: aload           list1
        //   417: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //   422: dup            
        //   423: istore          node
        //   425: if_icmpne       390
        //   428: aload           list1
        //   430: invokeinterface org/apache/xml/dtm/DTMIterator.reset:()V
        //   435: goto            451
        //   438: aload_2         /* comparator */
        //   439: aload_0         /* this */
        //   440: invokevirtual   org/apache/xpath/objects/XNodeSet.num:()D
        //   443: aload_1         /* obj2 */
        //   444: invokevirtual   org/apache/xpath/objects/XObject.num:()D
        //   447: invokevirtual   org/apache/xpath/objects/Comparator.compareNumbers:(DD)Z
        //   450: istore_3        /* result */
        //   451: iload_3         /* result */
        //   452: ireturn        
        //    Exceptions:
        //  throws javax.xml.transform.TransformerException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name          Signature
        //  -----  ------  ----  ------------  -------------------------------------
        //  0      453     0     this          Lorg/apache/xpath/objects/XNodeSet;
        //  0      453     1     obj2          Lorg/apache/xpath/objects/XObject;
        //  0      453     2     comparator    Lorg/apache/xpath/objects/Comparator;
        //  2      451     3     result        Z
        //  8      445     4     type          I
        //  20     170     5     list1         Lorg/apache/xml/dtm/DTMIterator;
        //  29     161     6     list2         Lorg/apache/xml/dtm/DTMIterator;
        //  173    17      7     node1         I
        //  32     158     8     node2Strings  Ljava/util/Vector;
        //  43     119     9     s1            Lorg/apache/xml/utils/XMLString;
        //  109    3       10    node2         I
        //  60     38      11    s2            Lorg/apache/xml/utils/XMLString;
        //  122    40      10    n             I
        //  125    37      11    i             I
        //  213    15      5     num1          D
        //  219    9       7     num2          D
        //  243    54      5     list1         Lorg/apache/xml/dtm/DTMIterator;
        //  249    48      6     num2          D
        //  287    10      8     node          I
        //  260    16      9     num1          D
        //  312    54      5     s2            Lorg/apache/xml/utils/XMLString;
        //  318    48      6     list1         Lorg/apache/xml/dtm/DTMIterator;
        //  356    10      7     node          I
        //  329    16      8     s1            Lorg/apache/xml/utils/XMLString;
        //  381    54      5     s2            Lorg/apache/xml/utils/XMLString;
        //  387    48      6     list1         Lorg/apache/xml/dtm/DTMIterator;
        //  425    10      7     node          I
        //  398    16      8     s1            Lorg/apache/xml/utils/XMLString;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean lessThan(final XObject obj2) throws TransformerException {
        return this.compare(obj2, XNodeSet.S_LT);
    }
    
    public boolean lessThanOrEqual(final XObject obj2) throws TransformerException {
        return this.compare(obj2, XNodeSet.S_LTE);
    }
    
    public boolean greaterThan(final XObject obj2) throws TransformerException {
        return this.compare(obj2, XNodeSet.S_GT);
    }
    
    public boolean greaterThanOrEqual(final XObject obj2) throws TransformerException {
        return this.compare(obj2, XNodeSet.S_GTE);
    }
    
    public boolean equals(final XObject obj2) {
        try {
            return this.compare(obj2, XNodeSet.S_EQ);
        }
        catch (TransformerException te) {
            throw new WrappedRuntimeException(te);
        }
    }
    
    public boolean notEquals(final XObject obj2) throws TransformerException {
        return this.compare(obj2, XNodeSet.S_NEQ);
    }
    
    static {
        S_LT = new LessThanComparator();
        S_LTE = new LessThanOrEqualComparator();
        S_GT = new GreaterThanComparator();
        S_GTE = new GreaterThanOrEqualComparator();
        S_EQ = new EqualComparator();
        S_NEQ = new NotEqualComparator();
    }
}
