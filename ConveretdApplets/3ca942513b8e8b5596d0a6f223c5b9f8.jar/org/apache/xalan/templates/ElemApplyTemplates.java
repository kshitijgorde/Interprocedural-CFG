// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.QName;

public class ElemApplyTemplates extends ElemCallTemplate
{
    static final long serialVersionUID = 2903125371542621004L;
    private QName m_mode;
    private boolean m_isDefaultTemplate;
    
    public ElemApplyTemplates() {
        this.m_mode = null;
        this.m_isDefaultTemplate = false;
    }
    
    public void setMode(final QName mode) {
        this.m_mode = mode;
    }
    
    public QName getMode() {
        return this.m_mode;
    }
    
    public void setIsDefaultTemplate(final boolean b) {
        this.m_isDefaultTemplate = b;
    }
    
    public int getXSLToken() {
        return 50;
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
    }
    
    public String getNodeName() {
        return "apply-templates";
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        transformer.pushCurrentTemplateRuleIsNull(false);
        boolean pushMode = false;
        try {
            final QName mode = transformer.getMode();
            if (!this.m_isDefaultTemplate && ((null == mode && null != this.m_mode) || (null != mode && !mode.equals(this.m_mode)))) {
                pushMode = true;
                transformer.pushMode(this.m_mode);
            }
            if (transformer.getDebug()) {
                transformer.getTraceManager().fireTraceEvent(this);
            }
            this.transformSelectedNodes(transformer);
        }
        finally {
            if (transformer.getDebug()) {
                transformer.getTraceManager().fireTraceEndEvent(this);
            }
            if (pushMode) {
                transformer.popMode();
            }
            transformer.popCurrentTemplateRuleIsNull();
        }
    }
    
    public void transformSelectedNodes(final TransformerImpl transformer) throws TransformerException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1         /* transformer */
        //     1: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getXPathContext:()Lorg/apache/xpath/XPathContext;
        //     4: astore_2        /* xctxt */
        //     5: aload_2         /* xctxt */
        //     6: invokevirtual   org/apache/xpath/XPathContext.getCurrentNode:()I
        //     9: istore_3        /* sourceNode */
        //    10: aload_0         /* this */
        //    11: getfield        org/apache/xalan/templates/ElemForEach.m_selectExpression:Lorg/apache/xpath/Expression;
        //    14: aload_2         /* xctxt */
        //    15: iload_3         /* sourceNode */
        //    16: invokevirtual   org/apache/xpath/Expression.asIterator:(Lorg/apache/xpath/XPathContext;I)Lorg/apache/xml/dtm/DTMIterator;
        //    19: astore          sourceNodes
        //    21: aload_2         /* xctxt */
        //    22: invokevirtual   org/apache/xpath/XPathContext.getVarStack:()Lorg/apache/xpath/VariableStack;
        //    25: astore          vars
        //    27: aload_0         /* this */
        //    28: invokevirtual   org/apache/xalan/templates/ElemCallTemplate.getParamElemCount:()I
        //    31: istore          nParams
        //    33: aload           vars
        //    35: invokevirtual   org/apache/xpath/VariableStack.getStackFrame:()I
        //    38: istore          thisframe
        //    40: aload_1         /* transformer */
        //    41: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getStackGuard:()Lorg/apache/xalan/transformer/StackGuard;
        //    44: astore          guard
        //    46: aload           guard
        //    48: invokevirtual   org/apache/xalan/transformer/StackGuard.getRecursionLimit:()I
        //    51: iconst_m1      
        //    52: if_icmple       59
        //    55: iconst_1       
        //    56: goto            60
        //    59: iconst_0       
        //    60: istore          check
        //    62: iconst_0       
        //    63: istore          pushContextNodeListFlag
        //    65: aload_2         /* xctxt */
        //    66: iconst_m1      
        //    67: invokevirtual   org/apache/xpath/XPathContext.pushCurrentNode:(I)V
        //    70: aload_2         /* xctxt */
        //    71: iconst_m1      
        //    72: invokevirtual   org/apache/xpath/XPathContext.pushCurrentExpressionNode:(I)V
        //    75: aload_2         /* xctxt */
        //    76: invokevirtual   org/apache/xpath/XPathContext.pushSAXLocatorNull:()V
        //    79: aload_1         /* transformer */
        //    80: aconst_null    
        //    81: invokevirtual   org/apache/xalan/transformer/TransformerImpl.pushElemTemplateElement:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //    84: aload_0         /* this */
        //    85: getfield        org/apache/xalan/templates/ElemForEach.m_sortElems:Ljava/util/Vector;
        //    88: ifnonnull       95
        //    91: aconst_null    
        //    92: goto            101
        //    95: aload_1         /* transformer */
        //    96: aload_0         /* this */
        //    97: iload_3         /* sourceNode */
        //    98: invokevirtual   org/apache/xalan/transformer/TransformerImpl.processSortKeys:(Lorg/apache/xalan/templates/ElemForEach;I)Ljava/util/Vector;
        //   101: astore          keys
        //   103: aconst_null    
        //   104: aload           keys
        //   106: if_acmpeq       120
        //   109: aload_0         /* this */
        //   110: aload_2         /* xctxt */
        //   111: aload           keys
        //   113: aload           sourceNodes
        //   115: invokevirtual   org/apache/xalan/templates/ElemForEach.sortNodes:(Lorg/apache/xpath/XPathContext;Ljava/util/Vector;Lorg/apache/xml/dtm/DTMIterator;)Lorg/apache/xml/dtm/DTMIterator;
        //   118: astore          sourceNodes
        //   120: aload_1         /* transformer */
        //   121: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getDebug:()Z
        //   124: ifeq            158
        //   127: aload_1         /* transformer */
        //   128: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   131: iload_3         /* sourceNode */
        //   132: aload_0         /* this */
        //   133: ldc             "select"
        //   135: new             Lorg/apache/xpath/XPath;
        //   138: dup            
        //   139: aload_0         /* this */
        //   140: getfield        org/apache/xalan/templates/ElemForEach.m_selectExpression:Lorg/apache/xpath/Expression;
        //   143: invokespecial   org/apache/xpath/XPath.<init>:(Lorg/apache/xpath/Expression;)V
        //   146: new             Lorg/apache/xpath/objects/XNodeSet;
        //   149: dup            
        //   150: aload           sourceNodes
        //   152: invokespecial   org/apache/xpath/objects/XNodeSet.<init>:(Lorg/apache/xml/dtm/DTMIterator;)V
        //   155: invokevirtual   org/apache/xalan/trace/TraceManager.fireSelectedEvent:(ILorg/apache/xalan/templates/ElemTemplateElement;Ljava/lang/String;Lorg/apache/xpath/XPath;Lorg/apache/xpath/objects/XObject;)V
        //   158: aload_1         /* transformer */
        //   159: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getSerializationHandler:()Lorg/apache/xml/serializer/SerializationHandler;
        //   162: astore          rth
        //   164: aload_1         /* transformer */
        //   165: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getStylesheet:()Lorg/apache/xalan/templates/StylesheetRoot;
        //   168: astore          sroot
        //   170: aload           sroot
        //   172: invokevirtual   org/apache/xalan/templates/StylesheetRoot.getTemplateListComposed:()Lorg/apache/xalan/templates/TemplateList;
        //   175: astore          tl
        //   177: aload_1         /* transformer */
        //   178: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getQuietConflictWarnings:()Z
        //   181: istore          quiet
        //   183: aload_2         /* xctxt */
        //   184: iload_3         /* sourceNode */
        //   185: invokevirtual   org/apache/xpath/XPathContext.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //   188: astore          dtm
        //   190: iconst_m1      
        //   191: istore          argsFrame
        //   193: iload           nParams
        //   195: ifle            298
        //   198: aload           vars
        //   200: iload           nParams
        //   202: invokevirtual   org/apache/xpath/VariableStack.link:(I)I
        //   205: istore          argsFrame
        //   207: aload           vars
        //   209: iload           thisframe
        //   211: invokevirtual   org/apache/xpath/VariableStack.setStackFrame:(I)V
        //   214: iconst_0       
        //   215: istore          i
        //   217: goto            284
        //   220: aload_0         /* this */
        //   221: getfield        org/apache/xalan/templates/ElemCallTemplate.m_paramElems:[Lorg/apache/xalan/templates/ElemWithParam;
        //   224: iload           i
        //   226: aaload         
        //   227: astore          ewp
        //   229: aload_1         /* transformer */
        //   230: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getDebug:()Z
        //   233: ifeq            245
        //   236: aload_1         /* transformer */
        //   237: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   240: aload           ewp
        //   242: invokevirtual   org/apache/xalan/trace/TraceManager.fireTraceEvent:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   245: aload           ewp
        //   247: aload_1         /* transformer */
        //   248: iload_3         /* sourceNode */
        //   249: invokevirtual   org/apache/xalan/templates/ElemWithParam.getValue:(Lorg/apache/xalan/transformer/TransformerImpl;I)Lorg/apache/xpath/objects/XObject;
        //   252: astore          obj
        //   254: aload_1         /* transformer */
        //   255: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getDebug:()Z
        //   258: ifeq            270
        //   261: aload_1         /* transformer */
        //   262: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   265: aload           ewp
        //   267: invokevirtual   org/apache/xalan/trace/TraceManager.fireTraceEndEvent:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   270: aload           vars
        //   272: iload           i
        //   274: aload           obj
        //   276: iload           argsFrame
        //   278: invokevirtual   org/apache/xpath/VariableStack.setLocalVariable:(ILorg/apache/xpath/objects/XObject;I)V
        //   281: iinc            i, 1
        //   284: iload           i
        //   286: iload           nParams
        //   288: if_icmplt       220
        //   291: aload           vars
        //   293: iload           argsFrame
        //   295: invokevirtual   org/apache/xpath/VariableStack.setStackFrame:(I)V
        //   298: aload_2         /* xctxt */
        //   299: aload           sourceNodes
        //   301: invokevirtual   org/apache/xpath/XPathContext.pushContextNodeList:(Lorg/apache/xml/dtm/DTMIterator;)V
        //   304: iconst_1       
        //   305: istore          pushContextNodeListFlag
        //   307: aload_2         /* xctxt */
        //   308: invokevirtual   org/apache/xpath/XPathContext.getCurrentNodeStack:()Lorg/apache/xml/utils/IntStack;
        //   311: astore          currentNodes
        //   313: aload_2         /* xctxt */
        //   314: invokevirtual   org/apache/xpath/XPathContext.getCurrentExpressionNodeStack:()Lorg/apache/xml/utils/IntStack;
        //   317: astore          currentExpressionNodes
        //   319: goto            837
        //   322: aload           currentNodes
        //   324: iload           20
        //   326: invokevirtual   org/apache/xml/utils/IntStack.setTop:(I)V
        //   329: aload           currentExpressionNodes
        //   331: iload           20
        //   333: invokevirtual   org/apache/xml/utils/IntStack.setTop:(I)V
        //   336: aload_2         /* xctxt */
        //   337: iload           20
        //   339: invokevirtual   org/apache/xpath/XPathContext.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //   342: aload           dtm
        //   344: if_acmpeq       355
        //   347: aload_2         /* xctxt */
        //   348: iload           20
        //   350: invokevirtual   org/apache/xpath/XPathContext.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //   353: astore          dtm
        //   355: aload           dtm
        //   357: iload           20
        //   359: invokeinterface org/apache/xml/dtm/DTM.getExpandedTypeID:(I)I
        //   364: istore          exNodeType
        //   366: aload           dtm
        //   368: iload           20
        //   370: invokeinterface org/apache/xml/dtm/DTM.getNodeType:(I)S
        //   375: istore          nodeType
        //   377: aload_1         /* transformer */
        //   378: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getMode:()Lorg/apache/xml/utils/QName;
        //   381: astore          mode
        //   383: aload           tl
        //   385: aload_2         /* xctxt */
        //   386: iload           20
        //   388: iload           exNodeType
        //   390: aload           mode
        //   392: iconst_m1      
        //   393: iload           quiet
        //   395: aload           dtm
        //   397: invokevirtual   org/apache/xalan/templates/TemplateList.getTemplateFast:(Lorg/apache/xpath/XPathContext;IILorg/apache/xml/utils/QName;IZLorg/apache/xml/dtm/DTM;)Lorg/apache/xalan/templates/ElemTemplate;
        //   400: astore          template
        //   402: aconst_null    
        //   403: aload           template
        //   405: if_acmpne       530
        //   408: iload           nodeType
        //   410: tableswitch {
        //                2: 468
        //                3: 478
        //                4: 478
        //                5: 478
        //                6: 527
        //                7: 527
        //                8: 527
        //                9: 527
        //               10: 517
        //               11: 527
        //               12: 468
        //          default: 527
        //        }
        //   468: aload           sroot
        //   470: invokevirtual   org/apache/xalan/templates/StylesheetRoot.getDefaultRule:()Lorg/apache/xalan/templates/ElemTemplate;
        //   473: astore          template
        //   475: goto            536
        //   478: aload_1         /* transformer */
        //   479: aload           sroot
        //   481: invokevirtual   org/apache/xalan/templates/StylesheetRoot.getDefaultTextRule:()Lorg/apache/xalan/templates/ElemTemplate;
        //   484: iload           20
        //   486: invokevirtual   org/apache/xalan/transformer/TransformerImpl.pushPairCurrentMatched:(Lorg/apache/xalan/templates/ElemTemplateElement;I)V
        //   489: aload_1         /* transformer */
        //   490: aload           sroot
        //   492: invokevirtual   org/apache/xalan/templates/StylesheetRoot.getDefaultTextRule:()Lorg/apache/xalan/templates/ElemTemplate;
        //   495: invokevirtual   org/apache/xalan/transformer/TransformerImpl.setCurrentElement:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   498: aload           dtm
        //   500: iload           20
        //   502: aload           rth
        //   504: iconst_0       
        //   505: invokeinterface org/apache/xml/dtm/DTM.dispatchCharactersEvents:(ILorg/xml/sax/ContentHandler;Z)V
        //   510: aload_1         /* transformer */
        //   511: invokevirtual   org/apache/xalan/transformer/TransformerImpl.popCurrentMatched:()V
        //   514: goto            837
        //   517: aload           sroot
        //   519: invokevirtual   org/apache/xalan/templates/StylesheetRoot.getDefaultRootRule:()Lorg/apache/xalan/templates/ElemTemplate;
        //   522: astore          template
        //   524: goto            536
        //   527: goto            837
        //   530: aload_1         /* transformer */
        //   531: aload           template
        //   533: invokevirtual   org/apache/xalan/transformer/TransformerImpl.setCurrentElement:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   536: aload_1         /* transformer */
        //   537: aload           template
        //   539: iload           20
        //   541: invokevirtual   org/apache/xalan/transformer/TransformerImpl.pushPairCurrentMatched:(Lorg/apache/xalan/templates/ElemTemplateElement;I)V
        //   544: iload           check
        //   546: ifeq            554
        //   549: aload           guard
        //   551: invokevirtual   org/apache/xalan/transformer/StackGuard.checkForInfinateLoop:()V
        //   554: aload           template
        //   556: getfield        org/apache/xalan/templates/ElemTemplate.m_frameSize:I
        //   559: ifle            717
        //   562: aload_2         /* xctxt */
        //   563: invokevirtual   org/apache/xpath/XPathContext.pushRTFContext:()V
        //   566: aload           vars
        //   568: invokevirtual   org/apache/xpath/VariableStack.getStackFrame:()I
        //   571: istore          currentFrameBottom
        //   573: aload           vars
        //   575: aload           template
        //   577: getfield        org/apache/xalan/templates/ElemTemplate.m_frameSize:I
        //   580: invokevirtual   org/apache/xpath/VariableStack.link:(I)I
        //   583: pop            
        //   584: aload           template
        //   586: getfield        org/apache/xalan/templates/ElemTemplate.m_inArgsSize:I
        //   589: ifle            720
        //   592: iconst_0       
        //   593: istore          paramIndex
        //   595: aload           template
        //   597: invokevirtual   org/apache/xalan/templates/ElemTemplateElement.getFirstChildElem:()Lorg/apache/xalan/templates/ElemTemplateElement;
        //   600: astore          elem
        //   602: goto            708
        //   605: bipush          41
        //   607: aload           elem
        //   609: invokevirtual   org/apache/xalan/templates/ElemTemplateElement.getXSLToken:()I
        //   612: if_icmpne       714
        //   615: aload           elem
        //   617: checkcast       Lorg/apache/xalan/templates/ElemParam;
        //   620: astore          ep
        //   622: iconst_0       
        //   623: istore          i
        //   625: goto            676
        //   628: aload_0         /* this */
        //   629: getfield        org/apache/xalan/templates/ElemCallTemplate.m_paramElems:[Lorg/apache/xalan/templates/ElemWithParam;
        //   632: iload           i
        //   634: aaload         
        //   635: astore          ewp
        //   637: aload           ewp
        //   639: getfield        org/apache/xalan/templates/ElemWithParam.m_qnameID:I
        //   642: aload           ep
        //   644: getfield        org/apache/xalan/templates/ElemParam.m_qnameID:I
        //   647: if_icmpne       673
        //   650: aload           vars
        //   652: iload           i
        //   654: iload           argsFrame
        //   656: invokevirtual   org/apache/xpath/VariableStack.getLocalVariable:(II)Lorg/apache/xpath/objects/XObject;
        //   659: astore          obj
        //   661: aload           vars
        //   663: iload           paramIndex
        //   665: aload           obj
        //   667: invokevirtual   org/apache/xpath/VariableStack.setLocalVariable:(ILorg/apache/xpath/objects/XObject;)V
        //   670: goto            683
        //   673: iinc            i, 1
        //   676: iload           i
        //   678: iload           nParams
        //   680: if_icmplt       628
        //   683: iload           i
        //   685: iload           nParams
        //   687: if_icmpne       698
        //   690: aload           vars
        //   692: iload           paramIndex
        //   694: aconst_null    
        //   695: invokevirtual   org/apache/xpath/VariableStack.setLocalVariable:(ILorg/apache/xpath/objects/XObject;)V
        //   698: iinc            paramIndex, 1
        //   701: aload           elem
        //   703: invokevirtual   org/apache/xalan/templates/ElemTemplateElement.getNextSiblingElem:()Lorg/apache/xalan/templates/ElemTemplateElement;
        //   706: astore          elem
        //   708: aconst_null    
        //   709: aload           elem
        //   711: if_acmpne       605
        //   714: goto            720
        //   717: iconst_0       
        //   718: istore          currentFrameBottom
        //   720: aload_1         /* transformer */
        //   721: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getDebug:()Z
        //   724: ifeq            736
        //   727: aload_1         /* transformer */
        //   728: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   731: aload           template
        //   733: invokevirtual   org/apache/xalan/trace/TraceManager.fireTraceEvent:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   736: aload           template
        //   738: getfield        org/apache/xalan/templates/ElemTemplateElement.m_firstChild:Lorg/apache/xalan/templates/ElemTemplateElement;
        //   741: astore          t
        //   743: goto            793
        //   746: aload_2         /* xctxt */
        //   747: aload           t
        //   749: invokevirtual   org/apache/xpath/XPathContext.setSAXLocator:(Ljavax/xml/transform/SourceLocator;)V
        //   752: aload_1         /* transformer */
        //   753: aload           t
        //   755: invokevirtual   org/apache/xalan/transformer/TransformerImpl.pushElemTemplateElement:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   758: aload           t
        //   760: aload_1         /* transformer */
        //   761: invokevirtual   org/apache/xalan/templates/ElemTemplateElement.execute:(Lorg/apache/xalan/transformer/TransformerImpl;)V
        //   764: jsr             778
        //   767: goto            786
        //   770: astore          32
        //   772: jsr             778
        //   775: aload           32
        //   777: athrow         
        //   778: astore          33
        //   780: aload_1         /* transformer */
        //   781: invokevirtual   org/apache/xalan/transformer/TransformerImpl.popElemTemplateElement:()V
        //   784: ret             33
        //   786: aload           t
        //   788: getfield        org/apache/xalan/templates/ElemTemplateElement.m_nextSibling:Lorg/apache/xalan/templates/ElemTemplateElement;
        //   791: astore          t
        //   793: aload           t
        //   795: ifnonnull       746
        //   798: aload_1         /* transformer */
        //   799: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getDebug:()Z
        //   802: ifeq            814
        //   805: aload_1         /* transformer */
        //   806: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   809: aload           template
        //   811: invokevirtual   org/apache/xalan/trace/TraceManager.fireTraceEndEvent:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   814: aload           template
        //   816: getfield        org/apache/xalan/templates/ElemTemplate.m_frameSize:I
        //   819: ifle            833
        //   822: aload           vars
        //   824: iload           currentFrameBottom
        //   826: invokevirtual   org/apache/xpath/VariableStack.unlink:(I)V
        //   829: aload_2         /* xctxt */
        //   830: invokevirtual   org/apache/xpath/XPathContext.popRTFContext:()V
        //   833: aload_1         /* transformer */
        //   834: invokevirtual   org/apache/xalan/transformer/TransformerImpl.popCurrentMatched:()V
        //   837: iconst_m1      
        //   838: aload           sourceNodes
        //   840: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //   845: dup            
        //   846: istore          child
        //   848: if_icmpne       322
        //   851: jsr             891
        //   854: goto            977
        //   857: astore          se
        //   859: aload_1         /* transformer */
        //   860: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getErrorListener:()Ljavax/xml/transform/ErrorListener;
        //   863: new             Ljavax/xml/transform/TransformerException;
        //   866: dup            
        //   867: aload           se
        //   869: invokespecial   javax/xml/transform/TransformerException.<init>:(Ljava/lang/Throwable;)V
        //   872: invokeinterface javax/xml/transform/ErrorListener.fatalError:(Ljavax/xml/transform/TransformerException;)V
        //   877: jsr             891
        //   880: goto            977
        //   883: astore          34
        //   885: jsr             891
        //   888: aload           34
        //   890: athrow         
        //   891: astore          35
        //   893: aload_1         /* transformer */
        //   894: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getDebug:()Z
        //   897: ifeq            931
        //   900: aload_1         /* transformer */
        //   901: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   904: iload_3         /* sourceNode */
        //   905: aload_0         /* this */
        //   906: ldc             "select"
        //   908: new             Lorg/apache/xpath/XPath;
        //   911: dup            
        //   912: aload_0         /* this */
        //   913: getfield        org/apache/xalan/templates/ElemForEach.m_selectExpression:Lorg/apache/xpath/Expression;
        //   916: invokespecial   org/apache/xpath/XPath.<init>:(Lorg/apache/xpath/Expression;)V
        //   919: new             Lorg/apache/xpath/objects/XNodeSet;
        //   922: dup            
        //   923: aload           sourceNodes
        //   925: invokespecial   org/apache/xpath/objects/XNodeSet.<init>:(Lorg/apache/xml/dtm/DTMIterator;)V
        //   928: invokevirtual   org/apache/xalan/trace/TraceManager.fireSelectedEndEvent:(ILorg/apache/xalan/templates/ElemTemplateElement;Ljava/lang/String;Lorg/apache/xpath/XPath;Lorg/apache/xpath/objects/XObject;)V
        //   931: iload           nParams
        //   933: ifle            943
        //   936: aload           vars
        //   938: iload           thisframe
        //   940: invokevirtual   org/apache/xpath/VariableStack.unlink:(I)V
        //   943: aload_2         /* xctxt */
        //   944: invokevirtual   org/apache/xpath/XPathContext.popSAXLocator:()V
        //   947: iload           pushContextNodeListFlag
        //   949: ifeq            956
        //   952: aload_2         /* xctxt */
        //   953: invokevirtual   org/apache/xpath/XPathContext.popContextNodeList:()V
        //   956: aload_1         /* transformer */
        //   957: invokevirtual   org/apache/xalan/transformer/TransformerImpl.popElemTemplateElement:()V
        //   960: aload_2         /* xctxt */
        //   961: invokevirtual   org/apache/xpath/XPathContext.popCurrentExpressionNode:()V
        //   964: aload_2         /* xctxt */
        //   965: invokevirtual   org/apache/xpath/XPathContext.popCurrentNode:()V
        //   968: aload           sourceNodes
        //   970: invokeinterface org/apache/xml/dtm/DTMIterator.detach:()V
        //   975: ret             35
        //   977: return         
        //    Exceptions:
        //  throws javax.xml.transform.TransformerException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name                     Signature
        //  -----  ------  ----  -----------------------  ------------------------------------------------
        //  0      978     0     this                     Lorg/apache/xalan/templates/ElemApplyTemplates;
        //  0      978     1     transformer              Lorg/apache/xalan/transformer/TransformerImpl;
        //  5      972     2     xctxt                    Lorg/apache/xpath/XPathContext;
        //  10     967     3     sourceNode               I
        //  21     956     4     sourceNodes              Lorg/apache/xml/dtm/DTMIterator;
        //  27     950     5     vars                     Lorg/apache/xpath/VariableStack;
        //  33     944     6     nParams                  I
        //  40     937     7     thisframe                I
        //  46     931     8     guard                    Lorg/apache/xalan/transformer/StackGuard;
        //  62     915     9     check                    Z
        //  65     912     10    pushContextNodeListFlag  Z
        //  103    748     11    keys                     Ljava/util/Vector;
        //  164    687     12    rth                      Lorg/apache/xml/serializer/SerializationHandler;
        //  170    681     13    sroot                    Lorg/apache/xalan/templates/StylesheetRoot;
        //  177    674     14    tl                       Lorg/apache/xalan/templates/TemplateList;
        //  183    668     15    quiet                    Z
        //  190    661     16    dtm                      Lorg/apache/xml/dtm/DTM;
        //  193    658     17    argsFrame                I
        //  217    81      18    i                        I
        //  229    52      19    ewp                      Lorg/apache/xalan/templates/ElemWithParam;
        //  254    27      20    obj                      Lorg/apache/xpath/objects/XObject;
        //  313    538     18    currentNodes             Lorg/apache/xml/utils/IntStack;
        //  319    532     19    currentExpressionNodes   Lorg/apache/xml/utils/IntStack;
        //  848    3       20    child                    I
        //  366    471     21    exNodeType               I
        //  377    460     22    nodeType                 I
        //  383    454     23    mode                     Lorg/apache/xml/utils/QName;
        //  402    435     24    template                 Lorg/apache/xalan/templates/ElemTemplate;
        //  573    264     25    currentFrameBottom       I
        //  595    119     26    paramIndex               I
        //  602    112     27    elem                     Lorg/apache/xalan/templates/ElemTemplateElement;
        //  622    76      28    ep                       Lorg/apache/xalan/templates/ElemParam;
        //  625    73      29    i                        I
        //  637    36      30    ewp                      Lorg/apache/xalan/templates/ElemWithParam;
        //  661    12      31    obj                      Lorg/apache/xpath/objects/XObject;
        //  743    94      26    t                        Lorg/apache/xalan/templates/ElemTemplateElement;
        //  859    118     11    se                       Lorg/xml/sax/SAXException;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  752    770    770    778    Any
        //  65     851    857    883    Lorg/xml/sax/SAXException;
        //  65     883    883    891    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
