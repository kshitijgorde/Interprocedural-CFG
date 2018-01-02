// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.QName;

public class ElemApplyTemplates extends ElemCallTemplate
{
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
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireTraceEvent(this);
            }
            this.transformSelectedNodes(transformer);
        }
        finally {
            if (TransformerImpl.S_DEBUG) {
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
        //   120: getstatic       org/apache/xalan/transformer/TransformerImpl.S_DEBUG:Z
        //   123: ifeq            157
        //   126: aload_1         /* transformer */
        //   127: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   130: iload_3         /* sourceNode */
        //   131: aload_0         /* this */
        //   132: ldc             "select"
        //   134: new             Lorg/apache/xpath/XPath;
        //   137: dup            
        //   138: aload_0         /* this */
        //   139: getfield        org/apache/xalan/templates/ElemForEach.m_selectExpression:Lorg/apache/xpath/Expression;
        //   142: invokespecial   org/apache/xpath/XPath.<init>:(Lorg/apache/xpath/Expression;)V
        //   145: new             Lorg/apache/xpath/objects/XNodeSet;
        //   148: dup            
        //   149: aload           sourceNodes
        //   151: invokespecial   org/apache/xpath/objects/XNodeSet.<init>:(Lorg/apache/xml/dtm/DTMIterator;)V
        //   154: invokevirtual   org/apache/xalan/trace/TraceManager.fireSelectedEvent:(ILorg/apache/xalan/templates/ElemTemplateElement;Ljava/lang/String;Lorg/apache/xpath/XPath;Lorg/apache/xpath/objects/XObject;)V
        //   157: aload_1         /* transformer */
        //   158: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getSerializationHandler:()Lorg/apache/xml/serializer/SerializationHandler;
        //   161: astore          rth
        //   163: aload_1         /* transformer */
        //   164: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getStylesheet:()Lorg/apache/xalan/templates/StylesheetRoot;
        //   167: astore          sroot
        //   169: aload           sroot
        //   171: invokevirtual   org/apache/xalan/templates/StylesheetRoot.getTemplateListComposed:()Lorg/apache/xalan/templates/TemplateList;
        //   174: astore          tl
        //   176: aload_1         /* transformer */
        //   177: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getQuietConflictWarnings:()Z
        //   180: istore          quiet
        //   182: aload_2         /* xctxt */
        //   183: iload_3         /* sourceNode */
        //   184: invokevirtual   org/apache/xpath/XPathContext.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //   187: astore          dtm
        //   189: iconst_m1      
        //   190: istore          argsFrame
        //   192: iload           nParams
        //   194: ifle            295
        //   197: aload           vars
        //   199: iload           nParams
        //   201: invokevirtual   org/apache/xpath/VariableStack.link:(I)I
        //   204: istore          argsFrame
        //   206: aload           vars
        //   208: iload           thisframe
        //   210: invokevirtual   org/apache/xpath/VariableStack.setStackFrame:(I)V
        //   213: iconst_0       
        //   214: istore          i
        //   216: goto            281
        //   219: aload_0         /* this */
        //   220: getfield        org/apache/xalan/templates/ElemCallTemplate.m_paramElems:[Lorg/apache/xalan/templates/ElemWithParam;
        //   223: iload           i
        //   225: aaload         
        //   226: astore          ewp
        //   228: getstatic       org/apache/xalan/transformer/TransformerImpl.S_DEBUG:Z
        //   231: ifeq            243
        //   234: aload_1         /* transformer */
        //   235: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   238: aload           ewp
        //   240: invokevirtual   org/apache/xalan/trace/TraceManager.fireTraceEvent:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   243: aload           ewp
        //   245: aload_1         /* transformer */
        //   246: iload_3         /* sourceNode */
        //   247: invokevirtual   org/apache/xalan/templates/ElemWithParam.getValue:(Lorg/apache/xalan/transformer/TransformerImpl;I)Lorg/apache/xpath/objects/XObject;
        //   250: astore          obj
        //   252: getstatic       org/apache/xalan/transformer/TransformerImpl.S_DEBUG:Z
        //   255: ifeq            267
        //   258: aload_1         /* transformer */
        //   259: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   262: aload           ewp
        //   264: invokevirtual   org/apache/xalan/trace/TraceManager.fireTraceEndEvent:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   267: aload           vars
        //   269: iload           i
        //   271: aload           obj
        //   273: iload           argsFrame
        //   275: invokevirtual   org/apache/xpath/VariableStack.setLocalVariable:(ILorg/apache/xpath/objects/XObject;I)V
        //   278: iinc            i, 1
        //   281: iload           i
        //   283: iload           nParams
        //   285: if_icmplt       219
        //   288: aload           vars
        //   290: iload           argsFrame
        //   292: invokevirtual   org/apache/xpath/VariableStack.setStackFrame:(I)V
        //   295: aload_2         /* xctxt */
        //   296: aload           sourceNodes
        //   298: invokevirtual   org/apache/xpath/XPathContext.pushContextNodeList:(Lorg/apache/xml/dtm/DTMIterator;)V
        //   301: iconst_1       
        //   302: istore          pushContextNodeListFlag
        //   304: aload_2         /* xctxt */
        //   305: invokevirtual   org/apache/xpath/XPathContext.getCurrentNodeStack:()Lorg/apache/xml/utils/IntStack;
        //   308: astore          currentNodes
        //   310: aload_2         /* xctxt */
        //   311: invokevirtual   org/apache/xpath/XPathContext.getCurrentExpressionNodeStack:()Lorg/apache/xml/utils/IntStack;
        //   314: astore          currentExpressionNodes
        //   316: goto            831
        //   319: aload           currentNodes
        //   321: iload           20
        //   323: invokevirtual   org/apache/xml/utils/IntStack.setTop:(I)V
        //   326: aload           currentExpressionNodes
        //   328: iload           20
        //   330: invokevirtual   org/apache/xml/utils/IntStack.setTop:(I)V
        //   333: aload_2         /* xctxt */
        //   334: iload           20
        //   336: invokevirtual   org/apache/xpath/XPathContext.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //   339: aload           dtm
        //   341: if_acmpeq       352
        //   344: aload_2         /* xctxt */
        //   345: iload           20
        //   347: invokevirtual   org/apache/xpath/XPathContext.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //   350: astore          dtm
        //   352: aload           dtm
        //   354: iload           20
        //   356: invokeinterface org/apache/xml/dtm/DTM.getExpandedTypeID:(I)I
        //   361: istore          exNodeType
        //   363: aload           dtm
        //   365: iload           20
        //   367: invokeinterface org/apache/xml/dtm/DTM.getNodeType:(I)S
        //   372: istore          nodeType
        //   374: aload_1         /* transformer */
        //   375: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getMode:()Lorg/apache/xml/utils/QName;
        //   378: astore          mode
        //   380: aload           tl
        //   382: aload_2         /* xctxt */
        //   383: iload           20
        //   385: iload           exNodeType
        //   387: aload           mode
        //   389: iconst_m1      
        //   390: iload           quiet
        //   392: aload           dtm
        //   394: invokevirtual   org/apache/xalan/templates/TemplateList.getTemplateFast:(Lorg/apache/xpath/XPathContext;IILorg/apache/xml/utils/QName;IZLorg/apache/xml/dtm/DTM;)Lorg/apache/xalan/templates/ElemTemplate;
        //   397: astore          template
        //   399: aconst_null    
        //   400: aload           template
        //   402: if_acmpne       526
        //   405: iload           nodeType
        //   407: tableswitch {
        //                2: 464
        //                3: 474
        //                4: 474
        //                5: 474
        //                6: 523
        //                7: 523
        //                8: 523
        //                9: 523
        //               10: 513
        //               11: 523
        //               12: 464
        //          default: 523
        //        }
        //   464: aload           sroot
        //   466: invokevirtual   org/apache/xalan/templates/StylesheetRoot.getDefaultRule:()Lorg/apache/xalan/templates/ElemTemplate;
        //   469: astore          template
        //   471: goto            532
        //   474: aload_1         /* transformer */
        //   475: aload           sroot
        //   477: invokevirtual   org/apache/xalan/templates/StylesheetRoot.getDefaultTextRule:()Lorg/apache/xalan/templates/ElemTemplate;
        //   480: iload           20
        //   482: invokevirtual   org/apache/xalan/transformer/TransformerImpl.pushPairCurrentMatched:(Lorg/apache/xalan/templates/ElemTemplateElement;I)V
        //   485: aload_1         /* transformer */
        //   486: aload           sroot
        //   488: invokevirtual   org/apache/xalan/templates/StylesheetRoot.getDefaultTextRule:()Lorg/apache/xalan/templates/ElemTemplate;
        //   491: invokevirtual   org/apache/xalan/transformer/TransformerImpl.setCurrentElement:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   494: aload           dtm
        //   496: iload           20
        //   498: aload           rth
        //   500: iconst_0       
        //   501: invokeinterface org/apache/xml/dtm/DTM.dispatchCharactersEvents:(ILorg/xml/sax/ContentHandler;Z)V
        //   506: aload_1         /* transformer */
        //   507: invokevirtual   org/apache/xalan/transformer/TransformerImpl.popCurrentMatched:()V
        //   510: goto            831
        //   513: aload           sroot
        //   515: invokevirtual   org/apache/xalan/templates/StylesheetRoot.getDefaultRootRule:()Lorg/apache/xalan/templates/ElemTemplate;
        //   518: astore          template
        //   520: goto            532
        //   523: goto            831
        //   526: aload_1         /* transformer */
        //   527: aload           template
        //   529: invokevirtual   org/apache/xalan/transformer/TransformerImpl.setCurrentElement:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   532: aload_1         /* transformer */
        //   533: aload           template
        //   535: iload           20
        //   537: invokevirtual   org/apache/xalan/transformer/TransformerImpl.pushPairCurrentMatched:(Lorg/apache/xalan/templates/ElemTemplateElement;I)V
        //   540: iload           check
        //   542: ifeq            550
        //   545: aload           guard
        //   547: invokevirtual   org/apache/xalan/transformer/StackGuard.checkForInfinateLoop:()V
        //   550: aload           template
        //   552: getfield        org/apache/xalan/templates/ElemTemplate.m_frameSize:I
        //   555: ifle            713
        //   558: aload_2         /* xctxt */
        //   559: invokevirtual   org/apache/xpath/XPathContext.pushRTFContext:()V
        //   562: aload           vars
        //   564: invokevirtual   org/apache/xpath/VariableStack.getStackFrame:()I
        //   567: istore          currentFrameBottom
        //   569: aload           vars
        //   571: aload           template
        //   573: getfield        org/apache/xalan/templates/ElemTemplate.m_frameSize:I
        //   576: invokevirtual   org/apache/xpath/VariableStack.link:(I)I
        //   579: pop            
        //   580: aload           template
        //   582: getfield        org/apache/xalan/templates/ElemTemplate.m_inArgsSize:I
        //   585: ifle            716
        //   588: iconst_0       
        //   589: istore          paramIndex
        //   591: aload           template
        //   593: invokevirtual   org/apache/xalan/templates/ElemTemplateElement.getFirstChildElem:()Lorg/apache/xalan/templates/ElemTemplateElement;
        //   596: astore          elem
        //   598: goto            704
        //   601: bipush          41
        //   603: aload           elem
        //   605: invokevirtual   org/apache/xalan/templates/ElemTemplateElement.getXSLToken:()I
        //   608: if_icmpne       710
        //   611: aload           elem
        //   613: checkcast       Lorg/apache/xalan/templates/ElemParam;
        //   616: astore          ep
        //   618: iconst_0       
        //   619: istore          i
        //   621: goto            672
        //   624: aload_0         /* this */
        //   625: getfield        org/apache/xalan/templates/ElemCallTemplate.m_paramElems:[Lorg/apache/xalan/templates/ElemWithParam;
        //   628: iload           i
        //   630: aaload         
        //   631: astore          ewp
        //   633: aload           ewp
        //   635: getfield        org/apache/xalan/templates/ElemWithParam.m_qnameID:I
        //   638: aload           ep
        //   640: getfield        org/apache/xalan/templates/ElemParam.m_qnameID:I
        //   643: if_icmpne       669
        //   646: aload           vars
        //   648: iload           i
        //   650: iload           argsFrame
        //   652: invokevirtual   org/apache/xpath/VariableStack.getLocalVariable:(II)Lorg/apache/xpath/objects/XObject;
        //   655: astore          obj
        //   657: aload           vars
        //   659: iload           paramIndex
        //   661: aload           obj
        //   663: invokevirtual   org/apache/xpath/VariableStack.setLocalVariable:(ILorg/apache/xpath/objects/XObject;)V
        //   666: goto            679
        //   669: iinc            i, 1
        //   672: iload           i
        //   674: iload           nParams
        //   676: if_icmplt       624
        //   679: iload           i
        //   681: iload           nParams
        //   683: if_icmpne       694
        //   686: aload           vars
        //   688: iload           paramIndex
        //   690: aconst_null    
        //   691: invokevirtual   org/apache/xpath/VariableStack.setLocalVariable:(ILorg/apache/xpath/objects/XObject;)V
        //   694: iinc            paramIndex, 1
        //   697: aload           elem
        //   699: invokevirtual   org/apache/xalan/templates/ElemTemplateElement.getNextSiblingElem:()Lorg/apache/xalan/templates/ElemTemplateElement;
        //   702: astore          elem
        //   704: aconst_null    
        //   705: aload           elem
        //   707: if_acmpne       601
        //   710: goto            716
        //   713: iconst_0       
        //   714: istore          currentFrameBottom
        //   716: getstatic       org/apache/xalan/transformer/TransformerImpl.S_DEBUG:Z
        //   719: ifeq            731
        //   722: aload_1         /* transformer */
        //   723: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   726: aload           template
        //   728: invokevirtual   org/apache/xalan/trace/TraceManager.fireTraceEvent:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   731: aload           template
        //   733: getfield        org/apache/xalan/templates/ElemTemplateElement.m_firstChild:Lorg/apache/xalan/templates/ElemTemplateElement;
        //   736: astore          t
        //   738: goto            788
        //   741: aload_2         /* xctxt */
        //   742: aload           t
        //   744: invokevirtual   org/apache/xpath/XPathContext.setSAXLocator:(Ljavax/xml/transform/SourceLocator;)V
        //   747: aload_1         /* transformer */
        //   748: aload           t
        //   750: invokevirtual   org/apache/xalan/transformer/TransformerImpl.pushElemTemplateElement:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   753: aload           t
        //   755: aload_1         /* transformer */
        //   756: invokevirtual   org/apache/xalan/templates/ElemTemplateElement.execute:(Lorg/apache/xalan/transformer/TransformerImpl;)V
        //   759: jsr             773
        //   762: goto            781
        //   765: astore          32
        //   767: jsr             773
        //   770: aload           32
        //   772: athrow         
        //   773: astore          33
        //   775: aload_1         /* transformer */
        //   776: invokevirtual   org/apache/xalan/transformer/TransformerImpl.popElemTemplateElement:()V
        //   779: ret             33
        //   781: aload           t
        //   783: getfield        org/apache/xalan/templates/ElemTemplateElement.m_nextSibling:Lorg/apache/xalan/templates/ElemTemplateElement;
        //   786: astore          t
        //   788: aload           t
        //   790: ifnonnull       741
        //   793: getstatic       org/apache/xalan/transformer/TransformerImpl.S_DEBUG:Z
        //   796: ifeq            808
        //   799: aload_1         /* transformer */
        //   800: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   803: aload           template
        //   805: invokevirtual   org/apache/xalan/trace/TraceManager.fireTraceEndEvent:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   808: aload           template
        //   810: getfield        org/apache/xalan/templates/ElemTemplate.m_frameSize:I
        //   813: ifle            827
        //   816: aload           vars
        //   818: iload           currentFrameBottom
        //   820: invokevirtual   org/apache/xpath/VariableStack.unlink:(I)V
        //   823: aload_2         /* xctxt */
        //   824: invokevirtual   org/apache/xpath/XPathContext.popRTFContext:()V
        //   827: aload_1         /* transformer */
        //   828: invokevirtual   org/apache/xalan/transformer/TransformerImpl.popCurrentMatched:()V
        //   831: iconst_m1      
        //   832: aload           sourceNodes
        //   834: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //   839: dup            
        //   840: istore          child
        //   842: if_icmpne       319
        //   845: jsr             885
        //   848: goto            970
        //   851: astore          se
        //   853: aload_1         /* transformer */
        //   854: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getErrorListener:()Ljavax/xml/transform/ErrorListener;
        //   857: new             Ljavax/xml/transform/TransformerException;
        //   860: dup            
        //   861: aload           se
        //   863: invokespecial   javax/xml/transform/TransformerException.<init>:(Ljava/lang/Throwable;)V
        //   866: invokeinterface javax/xml/transform/ErrorListener.fatalError:(Ljavax/xml/transform/TransformerException;)V
        //   871: jsr             885
        //   874: goto            970
        //   877: astore          34
        //   879: jsr             885
        //   882: aload           34
        //   884: athrow         
        //   885: astore          35
        //   887: getstatic       org/apache/xalan/transformer/TransformerImpl.S_DEBUG:Z
        //   890: ifeq            924
        //   893: aload_1         /* transformer */
        //   894: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   897: iload_3         /* sourceNode */
        //   898: aload_0         /* this */
        //   899: ldc             "select"
        //   901: new             Lorg/apache/xpath/XPath;
        //   904: dup            
        //   905: aload_0         /* this */
        //   906: getfield        org/apache/xalan/templates/ElemForEach.m_selectExpression:Lorg/apache/xpath/Expression;
        //   909: invokespecial   org/apache/xpath/XPath.<init>:(Lorg/apache/xpath/Expression;)V
        //   912: new             Lorg/apache/xpath/objects/XNodeSet;
        //   915: dup            
        //   916: aload           sourceNodes
        //   918: invokespecial   org/apache/xpath/objects/XNodeSet.<init>:(Lorg/apache/xml/dtm/DTMIterator;)V
        //   921: invokevirtual   org/apache/xalan/trace/TraceManager.fireSelectedEndEvent:(ILorg/apache/xalan/templates/ElemTemplateElement;Ljava/lang/String;Lorg/apache/xpath/XPath;Lorg/apache/xpath/objects/XObject;)V
        //   924: iload           nParams
        //   926: ifle            936
        //   929: aload           vars
        //   931: iload           thisframe
        //   933: invokevirtual   org/apache/xpath/VariableStack.unlink:(I)V
        //   936: aload_2         /* xctxt */
        //   937: invokevirtual   org/apache/xpath/XPathContext.popSAXLocator:()V
        //   940: iload           pushContextNodeListFlag
        //   942: ifeq            949
        //   945: aload_2         /* xctxt */
        //   946: invokevirtual   org/apache/xpath/XPathContext.popContextNodeList:()V
        //   949: aload_1         /* transformer */
        //   950: invokevirtual   org/apache/xalan/transformer/TransformerImpl.popElemTemplateElement:()V
        //   953: aload_2         /* xctxt */
        //   954: invokevirtual   org/apache/xpath/XPathContext.popCurrentExpressionNode:()V
        //   957: aload_2         /* xctxt */
        //   958: invokevirtual   org/apache/xpath/XPathContext.popCurrentNode:()V
        //   961: aload           sourceNodes
        //   963: invokeinterface org/apache/xml/dtm/DTMIterator.detach:()V
        //   968: ret             35
        //   970: return         
        //    Exceptions:
        //  throws javax.xml.transform.TransformerException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name                     Signature
        //  -----  ------  ----  -----------------------  ------------------------------------------------
        //  0      971     0     this                     Lorg/apache/xalan/templates/ElemApplyTemplates;
        //  0      971     1     transformer              Lorg/apache/xalan/transformer/TransformerImpl;
        //  5      965     2     xctxt                    Lorg/apache/xpath/XPathContext;
        //  10     960     3     sourceNode               I
        //  21     949     4     sourceNodes              Lorg/apache/xml/dtm/DTMIterator;
        //  27     943     5     vars                     Lorg/apache/xpath/VariableStack;
        //  33     937     6     nParams                  I
        //  40     930     7     thisframe                I
        //  46     924     8     guard                    Lorg/apache/xalan/transformer/StackGuard;
        //  62     908     9     check                    Z
        //  65     905     10    pushContextNodeListFlag  Z
        //  103    742     11    keys                     Ljava/util/Vector;
        //  163    682     12    rth                      Lorg/apache/xml/serializer/SerializationHandler;
        //  169    676     13    sroot                    Lorg/apache/xalan/templates/StylesheetRoot;
        //  176    669     14    tl                       Lorg/apache/xalan/templates/TemplateList;
        //  182    663     15    quiet                    Z
        //  189    656     16    dtm                      Lorg/apache/xml/dtm/DTM;
        //  192    653     17    argsFrame                I
        //  216    79      18    i                        I
        //  228    50      19    ewp                      Lorg/apache/xalan/templates/ElemWithParam;
        //  252    26      20    obj                      Lorg/apache/xpath/objects/XObject;
        //  310    535     18    currentNodes             Lorg/apache/xml/utils/IntStack;
        //  316    529     19    currentExpressionNodes   Lorg/apache/xml/utils/IntStack;
        //  842    3       20    child                    I
        //  363    468     21    exNodeType               I
        //  374    457     22    nodeType                 I
        //  380    451     23    mode                     Lorg/apache/xml/utils/QName;
        //  399    432     24    template                 Lorg/apache/xalan/templates/ElemTemplate;
        //  569    262     25    currentFrameBottom       I
        //  591    119     26    paramIndex               I
        //  598    112     27    elem                     Lorg/apache/xalan/templates/ElemTemplateElement;
        //  618    76      28    ep                       Lorg/apache/xalan/templates/ElemParam;
        //  621    73      29    i                        I
        //  633    36      30    ewp                      Lorg/apache/xalan/templates/ElemWithParam;
        //  657    12      31    obj                      Lorg/apache/xpath/objects/XObject;
        //  738    93      26    t                        Lorg/apache/xalan/templates/ElemTemplateElement;
        //  853    117     11    se                       Lorg/xml/sax/SAXException;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  747    765    765    773    Any
        //  65     845    851    877    Lorg/xml/sax/SAXException;
        //  65     877    877    885    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
