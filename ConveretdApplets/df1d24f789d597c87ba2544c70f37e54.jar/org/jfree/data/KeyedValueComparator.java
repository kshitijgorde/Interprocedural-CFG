// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import org.jfree.util.SortOrder;
import java.util.Comparator;

public class KeyedValueComparator implements Comparator
{
    private KeyedValueComparatorType type;
    private SortOrder order;
    
    public KeyedValueComparator(final KeyedValueComparatorType type, final SortOrder order) {
        this.type = type;
        this.order = order;
    }
    
    public int compare(final Object o1, final Object o2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2         /* o2 */
        //     1: ifnonnull       6
        //     4: iconst_m1      
        //     5: ireturn        
        //     6: aload_1         /* o1 */
        //     7: ifnonnull       12
        //    10: iconst_1       
        //    11: ireturn        
        //    12: aload_1         /* o1 */
        //    13: checkcast       Lorg/jfree/data/KeyedValue;
        //    16: astore          kv1
        //    18: aload_2         /* o2 */
        //    19: checkcast       Lorg/jfree/data/KeyedValue;
        //    22: astore          kv2
        //    24: aload_0         /* this */
        //    25: getfield        org/jfree/data/KeyedValueComparator.type:Lorg/jfree/data/KeyedValueComparatorType;
        //    28: getstatic       org/jfree/data/KeyedValueComparatorType.BY_KEY:Lorg/jfree/data/KeyedValueComparatorType;
        //    31: if_acmpne       116
        //    34: aload_0         /* this */
        //    35: getfield        org/jfree/data/KeyedValueComparator.order:Lorg/jfree/util/SortOrder;
        //    38: getstatic       org/jfree/util/SortOrder.ASCENDING:Lorg/jfree/util/SortOrder;
        //    41: invokevirtual   org/jfree/util/SortOrder.equals:(Ljava/lang/Object;)Z
        //    44: ifeq            70
        //    47: aload           kv1
        //    49: invokeinterface org/jfree/data/KeyedValue.getKey:()Ljava/lang/Comparable;
        //    54: aload           kv2
        //    56: invokeinterface org/jfree/data/KeyedValue.getKey:()Ljava/lang/Comparable;
        //    61: invokeinterface java/lang/Comparable.compareTo:(Ljava/lang/Object;)I
        //    66: istore_3        /* result */
        //    67: goto            283
        //    70: aload_0         /* this */
        //    71: getfield        org/jfree/data/KeyedValueComparator.order:Lorg/jfree/util/SortOrder;
        //    74: getstatic       org/jfree/util/SortOrder.DESCENDING:Lorg/jfree/util/SortOrder;
        //    77: invokevirtual   org/jfree/util/SortOrder.equals:(Ljava/lang/Object;)Z
        //    80: ifeq            106
        //    83: aload           kv2
        //    85: invokeinterface org/jfree/data/KeyedValue.getKey:()Ljava/lang/Comparable;
        //    90: aload           kv1
        //    92: invokeinterface org/jfree/data/KeyedValue.getKey:()Ljava/lang/Comparable;
        //    97: invokeinterface java/lang/Comparable.compareTo:(Ljava/lang/Object;)I
        //   102: istore_3        /* result */
        //   103: goto            283
        //   106: new             Ljava/lang/IllegalArgumentException;
        //   109: dup            
        //   110: ldc             "KeyedValueComparator.compare(...) : unrecognised sort order."
        //   112: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   115: athrow         
        //   116: aload_0         /* this */
        //   117: getfield        org/jfree/data/KeyedValueComparator.type:Lorg/jfree/data/KeyedValueComparatorType;
        //   120: getstatic       org/jfree/data/KeyedValueComparatorType.BY_VALUE:Lorg/jfree/data/KeyedValueComparatorType;
        //   123: if_acmpne       273
        //   126: aload           kv1
        //   128: invokeinterface org/jfree/data/KeyedValue.getValue:()Ljava/lang/Number;
        //   133: astore          n1
        //   135: aload           kv2
        //   137: invokeinterface org/jfree/data/KeyedValue.getValue:()Ljava/lang/Number;
        //   142: astore          n2
        //   144: aload           n2
        //   146: ifnonnull       151
        //   149: iconst_m1      
        //   150: ireturn        
        //   151: aload           n1
        //   153: ifnonnull       158
        //   156: iconst_1       
        //   157: ireturn        
        //   158: aload           n1
        //   160: invokevirtual   java/lang/Number.doubleValue:()D
        //   163: dstore          d1
        //   165: aload           n2
        //   167: invokevirtual   java/lang/Number.doubleValue:()D
        //   170: dstore          d2
        //   172: aload_0         /* this */
        //   173: getfield        org/jfree/data/KeyedValueComparator.order:Lorg/jfree/util/SortOrder;
        //   176: getstatic       org/jfree/util/SortOrder.ASCENDING:Lorg/jfree/util/SortOrder;
        //   179: invokevirtual   org/jfree/util/SortOrder.equals:(Ljava/lang/Object;)Z
        //   182: ifeq            216
        //   185: dload           d1
        //   187: dload           d2
        //   189: dcmpl          
        //   190: ifle            198
        //   193: iconst_1       
        //   194: istore_3        /* result */
        //   195: goto            270
        //   198: dload           d1
        //   200: dload           d2
        //   202: dcmpg          
        //   203: ifge            211
        //   206: iconst_m1      
        //   207: istore_3        /* result */
        //   208: goto            270
        //   211: iconst_0       
        //   212: istore_3        /* result */
        //   213: goto            270
        //   216: aload_0         /* this */
        //   217: getfield        org/jfree/data/KeyedValueComparator.order:Lorg/jfree/util/SortOrder;
        //   220: getstatic       org/jfree/util/SortOrder.DESCENDING:Lorg/jfree/util/SortOrder;
        //   223: invokevirtual   org/jfree/util/SortOrder.equals:(Ljava/lang/Object;)Z
        //   226: ifeq            260
        //   229: dload           d1
        //   231: dload           d2
        //   233: dcmpl          
        //   234: ifle            242
        //   237: iconst_m1      
        //   238: istore_3        /* result */
        //   239: goto            270
        //   242: dload           d1
        //   244: dload           d2
        //   246: dcmpg          
        //   247: ifge            255
        //   250: iconst_1       
        //   251: istore_3        /* result */
        //   252: goto            270
        //   255: iconst_0       
        //   256: istore_3        /* result */
        //   257: goto            270
        //   260: new             Ljava/lang/IllegalArgumentException;
        //   263: dup            
        //   264: ldc             "KeyedValueComparator.compare(...) : unrecognised sort order."
        //   266: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   269: athrow         
        //   270: goto            283
        //   273: new             Ljava/lang/IllegalArgumentException;
        //   276: dup            
        //   277: ldc             "KeyedValueComparator.compare(...) : unrecognised type."
        //   279: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   282: athrow         
        //   283: iload_3        
        //   284: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  -------------------------------------
        //  67     3       3     result  I
        //  103    3       3     result  I
        //  195    3       3     result  I
        //  208    3       3     result  I
        //  213    3       3     result  I
        //  239    3       3     result  I
        //  252    3       3     result  I
        //  257    3       3     result  I
        //  135    135     6     n1      Ljava/lang/Number;
        //  144    126     7     n2      Ljava/lang/Number;
        //  165    105     8     d1      D
        //  172    98      10    d2      D
        //  0      285     0     this    Lorg/jfree/data/KeyedValueComparator;
        //  0      285     1     o1      Ljava/lang/Object;
        //  0      285     2     o2      Ljava/lang/Object;
        //  18     267     4     kv1     Lorg/jfree/data/KeyedValue;
        //  24     261     5     kv2     Lorg/jfree/data/KeyedValue;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
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
