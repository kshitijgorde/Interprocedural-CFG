import java.awt.Event;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Graph extends Applet
{
    GraphPanel panel;
    
    public void init() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: new             Ljava/awt/BorderLayout;
        //     4: dup            
        //     5: invokespecial   java/awt/BorderLayout.<init>:()V
        //     8: invokevirtual   java/awt/Container.setLayout:(Ljava/awt/LayoutManager;)V
        //    11: aload_0         /* this */
        //    12: new             LGraphPanel;
        //    15: dup            
        //    16: aload_0         /* this */
        //    17: invokespecial   GraphPanel.<init>:(LGraph;)V
        //    20: putfield        Graph.panel:LGraphPanel;
        //    23: aload_0         /* this */
        //    24: ldc             "Center"
        //    26: aload_0         /* this */
        //    27: getfield        Graph.panel:LGraphPanel;
        //    30: invokevirtual   java/awt/Container.add:(Ljava/lang/String;Ljava/awt/Component;)Ljava/awt/Component;
        //    33: pop            
        //    34: new             Ljava/awt/Panel;
        //    37: dup            
        //    38: invokespecial   java/awt/Panel.<init>:()V
        //    41: astore_1        /* p */
        //    42: aload_0         /* this */
        //    43: ldc             "South"
        //    45: aload_1         /* p */
        //    46: invokevirtual   java/awt/Container.add:(Ljava/lang/String;Ljava/awt/Component;)Ljava/awt/Component;
        //    49: pop            
        //    50: aload_1         /* p */
        //    51: new             Ljava/awt/Button;
        //    54: dup            
        //    55: ldc             "Scramble"
        //    57: invokespecial   java/awt/Button.<init>:(Ljava/lang/String;)V
        //    60: invokevirtual   java/awt/Container.add:(Ljava/awt/Component;)Ljava/awt/Component;
        //    63: pop            
        //    64: aload_1         /* p */
        //    65: new             Ljava/awt/Button;
        //    68: dup            
        //    69: ldc             "Shake"
        //    71: invokespecial   java/awt/Button.<init>:(Ljava/lang/String;)V
        //    74: invokevirtual   java/awt/Container.add:(Ljava/awt/Component;)Ljava/awt/Component;
        //    77: pop            
        //    78: aload_1         /* p */
        //    79: new             Ljava/awt/Checkbox;
        //    82: dup            
        //    83: ldc             "Stress"
        //    85: invokespecial   java/awt/Checkbox.<init>:(Ljava/lang/String;)V
        //    88: invokevirtual   java/awt/Container.add:(Ljava/awt/Component;)Ljava/awt/Component;
        //    91: pop            
        //    92: aload_1         /* p */
        //    93: new             Ljava/awt/Checkbox;
        //    96: dup            
        //    97: ldc             "Random"
        //    99: invokespecial   java/awt/Checkbox.<init>:(Ljava/lang/String;)V
        //   102: invokevirtual   java/awt/Container.add:(Ljava/awt/Component;)Ljava/awt/Component;
        //   105: pop            
        //   106: aload_0         /* this */
        //   107: ldc             "edges"
        //   109: invokevirtual   java/applet/Applet.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   112: astore_2        /* edges */
        //   113: new             Ljava/util/StringTokenizer;
        //   116: dup            
        //   117: aload_2         /* edges */
        //   118: ldc             ","
        //   120: invokespecial   java/util/StringTokenizer.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //   123: astore_3        /* t */
        //   124: goto            218
        //   127: aload_3         /* t */
        //   128: invokevirtual   java/util/StringTokenizer.nextToken:()Ljava/lang/String;
        //   131: astore          str
        //   133: aload           str
        //   135: bipush          45
        //   137: invokevirtual   java/lang/String.indexOf:(I)I
        //   140: istore          i
        //   142: iload           i
        //   144: ifle            218
        //   147: bipush          50
        //   149: istore          len
        //   151: aload           str
        //   153: bipush          47
        //   155: invokevirtual   java/lang/String.indexOf:(I)I
        //   158: istore          j
        //   160: iload           j
        //   162: ifle            192
        //   165: aload           str
        //   167: iload           j
        //   169: iconst_1       
        //   170: iadd           
        //   171: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   174: invokestatic    java/lang/Integer.valueOf:(Ljava/lang/String;)Ljava/lang/Integer;
        //   177: invokevirtual   java/lang/Integer.intValue:()I
        //   180: istore          len
        //   182: aload           str
        //   184: iconst_0       
        //   185: iload           j
        //   187: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   190: astore          str
        //   192: aload_0         /* this */
        //   193: getfield        Graph.panel:LGraphPanel;
        //   196: aload           4
        //   198: iconst_0       
        //   199: iload           5
        //   201: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   204: aload           4
        //   206: iload           5
        //   208: iconst_1       
        //   209: iadd           
        //   210: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   213: iload           6
        //   215: invokevirtual   GraphPanel.addEdge:(Ljava/lang/String;Ljava/lang/String;I)V
        //   218: aload_3        
        //   219: invokevirtual   java/util/StringTokenizer.hasMoreTokens:()Z
        //   222: ifne            127
        //   225: aload_0         /* this */
        //   226: invokevirtual   java/awt/Component.size:()Ljava/awt/Dimension;
        //   229: astore          d
        //   231: aload_0         /* this */
        //   232: ldc             "center"
        //   234: invokevirtual   java/applet/Applet.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   237: astore          center
        //   239: aload           center
        //   241: ifnull          295
        //   244: aload_0         /* this */
        //   245: getfield        Graph.panel:LGraphPanel;
        //   248: getfield        GraphPanel.nodes:[LNode;
        //   251: aload_0         /* this */
        //   252: getfield        Graph.panel:LGraphPanel;
        //   255: aload           center
        //   257: invokevirtual   GraphPanel.findNode:(Ljava/lang/String;)I
        //   260: aaload         
        //   261: astore          n
        //   263: aload           n
        //   265: aload           d
        //   267: getfield        java/awt/Dimension.width:I
        //   270: iconst_2       
        //   271: idiv           
        //   272: i2d            
        //   273: putfield        Node.x:D
        //   276: aload           n
        //   278: aload           d
        //   280: getfield        java/awt/Dimension.height:I
        //   283: iconst_2       
        //   284: idiv           
        //   285: i2d            
        //   286: putfield        Node.y:D
        //   289: aload           n
        //   291: iconst_1       
        //   292: putfield        Node.fixed:Z
        //   295: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  ---------------------------
        //  0      296     0     this    LGraph;
        //  42     85      1     p       Ljava/awt/Panel;
        //  113    14      2     edges   Ljava/lang/String;
        //  124    3       3     t       Ljava/util/StringTokenizer;
        //  151    41      6     len     I
        //  133    59      4     str     Ljava/lang/String;
        //  142    50      5     i       I
        //  160    32      7     j       I
        //  231    64      4     d       Ljava/awt/Dimension;
        //  239    56      5     center  Ljava/lang/String;
        //  263    32      6     n       LNode;
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
    
    public void start() {
        this.panel.start();
    }
    
    public void stop() {
        this.panel.stop();
    }
    
    public boolean action(final Event evt, final Object arg) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2         /* arg */
        //     1: instanceof      Ljava/lang/Boolean;
        //     4: ifeq            58
        //     7: aload_1         /* evt */
        //     8: getfield        java/awt/Event.target:Ljava/lang/Object;
        //    11: checkcast       Ljava/awt/Checkbox;
        //    14: invokevirtual   java/awt/Checkbox.getLabel:()Ljava/lang/String;
        //    17: ldc             "Stress"
        //    19: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    22: ifeq            42
        //    25: aload_0         /* this */
        //    26: getfield        Graph.panel:LGraphPanel;
        //    29: aload_2         /* arg */
        //    30: checkcast       Ljava/lang/Boolean;
        //    33: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    36: putfield        GraphPanel.stress:Z
        //    39: goto            56
        //    42: aload_0         /* this */
        //    43: getfield        Graph.panel:LGraphPanel;
        //    46: aload_2         /* arg */
        //    47: checkcast       Ljava/lang/Boolean;
        //    50: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    53: putfield        GraphPanel.random:Z
        //    56: iconst_1       
        //    57: ireturn        
        //    58: ldc             "Scramble"
        //    60: aload_2         /* arg */
        //    61: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    64: ifeq            167
        //    67: aload_0         /* this */
        //    68: aload_0         /* this */
        //    69: invokevirtual   java/applet/Applet.getCodeBase:()Ljava/net/URL;
        //    72: ldc             "audio/computer.au"
        //    74: invokevirtual   java/applet/Applet.play:(Ljava/net/URL;Ljava/lang/String;)V
        //    77: aload_0         /* this */
        //    78: invokevirtual   java/awt/Component.size:()Ljava/awt/Dimension;
        //    81: astore_3        /* d */
        //    82: iconst_0       
        //    83: istore          i
        //    85: goto            153
        //    88: aload_0         /* this */
        //    89: getfield        Graph.panel:LGraphPanel;
        //    92: getfield        GraphPanel.nodes:[LNode;
        //    95: iload           4
        //    97: aaload         
        //    98: astore          n
        //   100: aload           n
        //   102: getfield        Node.fixed:Z
        //   105: ifne            150
        //   108: aload           n
        //   110: ldc2_w          10.0
        //   113: aload_3        
        //   114: getfield        java/awt/Dimension.width:I
        //   117: bipush          20
        //   119: isub           
        //   120: i2d            
        //   121: invokestatic    java/lang/Math.random:()D
        //   124: dmul           
        //   125: dadd           
        //   126: putfield        Node.x:D
        //   129: aload           n
        //   131: ldc2_w          10.0
        //   134: aload_3        
        //   135: getfield        java/awt/Dimension.height:I
        //   138: bipush          20
        //   140: isub           
        //   141: i2d            
        //   142: invokestatic    java/lang/Math.random:()D
        //   145: dmul           
        //   146: dadd           
        //   147: putfield        Node.y:D
        //   150: iinc            4, 1
        //   153: iload           4
        //   155: aload_0         /* this */
        //   156: getfield        Graph.panel:LGraphPanel;
        //   159: getfield        GraphPanel.nnodes:I
        //   162: if_icmplt       88
        //   165: iconst_1       
        //   166: ireturn        
        //   167: ldc             "Shake"
        //   169: aload_2         /* arg */
        //   170: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   173: ifeq            273
        //   176: aload_0         /* this */
        //   177: aload_0         /* this */
        //   178: invokevirtual   java/applet/Applet.getCodeBase:()Ljava/net/URL;
        //   181: ldc             "audio/gong.au"
        //   183: invokevirtual   java/applet/Applet.play:(Ljava/net/URL;Ljava/lang/String;)V
        //   186: aload_0         /* this */
        //   187: invokevirtual   java/awt/Component.size:()Ljava/awt/Dimension;
        //   190: pop            
        //   191: iconst_0       
        //   192: istore_3        /* i */
        //   193: goto            260
        //   196: aload_0         /* this */
        //   197: getfield        Graph.panel:LGraphPanel;
        //   200: getfield        GraphPanel.nodes:[LNode;
        //   203: iload_3        
        //   204: aaload         
        //   205: astore          n
        //   207: aload           n
        //   209: getfield        Node.fixed:Z
        //   212: ifne            257
        //   215: aload           n
        //   217: dup            
        //   218: getfield        Node.x:D
        //   221: ldc2_w          80.0
        //   224: invokestatic    java/lang/Math.random:()D
        //   227: dmul           
        //   228: ldc2_w          40.0
        //   231: dsub           
        //   232: dadd           
        //   233: putfield        Node.x:D
        //   236: aload           n
        //   238: dup            
        //   239: getfield        Node.y:D
        //   242: ldc2_w          80.0
        //   245: invokestatic    java/lang/Math.random:()D
        //   248: dmul           
        //   249: ldc2_w          40.0
        //   252: dsub           
        //   253: dadd           
        //   254: putfield        Node.y:D
        //   257: iinc            3, 1
        //   260: iload_3        
        //   261: aload_0         /* this */
        //   262: getfield        Graph.panel:LGraphPanel;
        //   265: getfield        GraphPanel.nnodes:I
        //   268: if_icmplt       196
        //   271: iconst_1       
        //   272: ireturn        
        //   273: iconst_0       
        //   274: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  --------------------
        //  0      275     0     this  LGraph;
        //  0      275     1     evt   Ljava/awt/Event;
        //  0      275     2     arg   Ljava/lang/Object;
        //  82     6       3     d     Ljava/awt/Dimension;
        //  85     3       4     i     I
        //  100    50      5     n     LNode;
        //  193    3       3     i     I
        //  207    50      4     n     LNode;
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
