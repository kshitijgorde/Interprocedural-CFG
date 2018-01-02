import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class GraphPanel extends Panel implements Runnable
{
    Graph graph;
    int nnodes;
    Node[] nodes;
    int nedges;
    Edge[] edges;
    Thread relaxer;
    boolean stress;
    boolean random;
    Node pick;
    boolean pickfixed;
    Image offscreen;
    Dimension offscreensize;
    Graphics offgraphics;
    final Color fixedColor;
    final Color selectColor;
    final Color edgeColor;
    final Color nodeColor;
    final Color stressColor;
    final Color arcColor1;
    final Color arcColor2;
    final Color arcColor3;
    
    GraphPanel(final Graph graph) {
        this.nodes = new Node[100];
        this.edges = new Edge[200];
        this.fixedColor = Color.red;
        this.selectColor = Color.pink;
        this.edgeColor = Color.black;
        this.nodeColor = new Color(250, 220, 100);
        this.stressColor = Color.gray;
        this.arcColor1 = Color.black;
        this.arcColor2 = Color.pink;
        this.arcColor3 = Color.red;
        this.graph = graph;
    }
    
    int findNode(final String lbl) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_2        /* i */
        //     2: goto            26
        //     5: aload_0         /* this */
        //     6: getfield        GraphPanel.nodes:[LNode;
        //     9: iload_2        
        //    10: aaload         
        //    11: getfield        Node.lbl:Ljava/lang/String;
        //    14: aload_1         /* lbl */
        //    15: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    18: ifeq            23
        //    21: iload_2        
        //    22: ireturn        
        //    23: iinc            2, 1
        //    26: iload_2        
        //    27: aload_0         /* this */
        //    28: getfield        GraphPanel.nnodes:I
        //    31: if_icmplt       5
        //    34: aload_0         /* this */
        //    35: aload_1         /* lbl */
        //    36: invokevirtual   GraphPanel.addNode:(Ljava/lang/String;)I
        //    39: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ------------------
        //  0      40      0     this  LGraphPanel;
        //  0      40      1     lbl   Ljava/lang/String;
        //  2      3       2     i     I
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
    
    int addNode(final String lbl) {
        final Node n = new Node();
        n.x = 10.0 + 380.0 * Math.random();
        n.y = 10.0 + 380.0 * Math.random();
        n.lbl = lbl;
        this.nodes[this.nnodes] = n;
        return this.nnodes++;
    }
    
    void addEdge(final String from, final String to, final int len) {
        final Edge e = new Edge();
        e.from = this.findNode(from);
        e.to = this.findNode(to);
        e.len = len;
        this.edges[this.nedges++] = e;
    }
    
    public void run() {
        while (true) {
            this.relax();
            if (this.random && Math.random() < 0.03) {
                final Node n = this.nodes[(int)(Math.random() * this.nnodes)];
                if (!n.fixed) {
                    final Node node = n;
                    node.x += 100.0 * Math.random() - 50.0;
                    final Node node2 = n;
                    node2.y += 100.0 * Math.random() - 50.0;
                }
                this.graph.play(this.graph.getCodeBase(), "audio/drip.au");
            }
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    synchronized void relax() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_1        /* i */
        //     2: goto            194
        //     5: aload_0         /* this */
        //     6: getfield        GraphPanel.edges:[LEdge;
        //     9: iload_1        
        //    10: aaload         
        //    11: astore_2        /* e */
        //    12: aload_0         /* this */
        //    13: getfield        GraphPanel.nodes:[LNode;
        //    16: aload_2         /* e */
        //    17: getfield        Edge.to:I
        //    20: aaload         
        //    21: getfield        Node.x:D
        //    24: aload_0         /* this */
        //    25: getfield        GraphPanel.nodes:[LNode;
        //    28: aload_2         /* e */
        //    29: getfield        Edge.from:I
        //    32: aaload         
        //    33: getfield        Node.x:D
        //    36: dsub           
        //    37: dstore_3        /* vx */
        //    38: aload_0         /* this */
        //    39: getfield        GraphPanel.nodes:[LNode;
        //    42: aload_2         /* e */
        //    43: getfield        Edge.to:I
        //    46: aaload         
        //    47: getfield        Node.y:D
        //    50: aload_0         /* this */
        //    51: getfield        GraphPanel.nodes:[LNode;
        //    54: aload_2         /* e */
        //    55: getfield        Edge.from:I
        //    58: aaload         
        //    59: getfield        Node.y:D
        //    62: dsub           
        //    63: dstore          vy
        //    65: dload_3         /* vx */
        //    66: dload_3         /* vx */
        //    67: dmul           
        //    68: dload           vy
        //    70: dload           vy
        //    72: dmul           
        //    73: dadd           
        //    74: invokestatic    java/lang/Math.sqrt:(D)D
        //    77: dstore          len
        //    79: aload_0         /* this */
        //    80: getfield        GraphPanel.edges:[LEdge;
        //    83: iload_1        
        //    84: aaload         
        //    85: getfield        Edge.len:D
        //    88: dload           len
        //    90: dsub           
        //    91: dload           len
        //    93: ldc2_w          3.0
        //    96: dmul           
        //    97: ddiv           
        //    98: dstore          f
        //   100: dload           f
        //   102: dload_3         /* vx */
        //   103: dmul           
        //   104: dstore          dx
        //   106: dload           f
        //   108: dload           vy
        //   110: dmul           
        //   111: dstore          dy
        //   113: aload_0         /* this */
        //   114: getfield        GraphPanel.nodes:[LNode;
        //   117: aload_2         /* e */
        //   118: getfield        Edge.to:I
        //   121: aaload         
        //   122: dup            
        //   123: getfield        Node.dx:D
        //   126: dload           dx
        //   128: dadd           
        //   129: putfield        Node.dx:D
        //   132: aload_0         /* this */
        //   133: getfield        GraphPanel.nodes:[LNode;
        //   136: aload_2         /* e */
        //   137: getfield        Edge.to:I
        //   140: aaload         
        //   141: dup            
        //   142: getfield        Node.dy:D
        //   145: dload           dy
        //   147: dadd           
        //   148: putfield        Node.dy:D
        //   151: aload_0         /* this */
        //   152: getfield        GraphPanel.nodes:[LNode;
        //   155: aload_2         /* e */
        //   156: getfield        Edge.from:I
        //   159: aaload         
        //   160: dup            
        //   161: getfield        Node.dx:D
        //   164: dload           dx
        //   166: dneg           
        //   167: dadd           
        //   168: putfield        Node.dx:D
        //   171: aload_0         /* this */
        //   172: getfield        GraphPanel.nodes:[LNode;
        //   175: aload_2         /* e */
        //   176: getfield        Edge.from:I
        //   179: aaload         
        //   180: dup            
        //   181: getfield        Node.dy:D
        //   184: dload           dy
        //   186: dneg           
        //   187: dadd           
        //   188: putfield        Node.dy:D
        //   191: iinc            1, 1
        //   194: iload_1        
        //   195: aload_0         /* this */
        //   196: getfield        GraphPanel.nedges:I
        //   199: if_icmplt       5
        //   202: iconst_0       
        //   203: istore_2        /* i */
        //   204: goto            407
        //   207: aload_0         /* this */
        //   208: getfield        GraphPanel.nodes:[LNode;
        //   211: iload_2        
        //   212: aaload         
        //   213: astore_3        /* n1 */
        //   214: dconst_0       
        //   215: dstore          dx
        //   217: dconst_0       
        //   218: dstore          dy
        //   220: iconst_0       
        //   221: istore          j
        //   223: goto            336
        //   226: iload_2        
        //   227: iload           8
        //   229: if_icmpeq       333
        //   232: aload_0         /* this */
        //   233: getfield        GraphPanel.nodes:[LNode;
        //   236: iload           8
        //   238: aaload         
        //   239: astore          n2
        //   241: aload_3        
        //   242: getfield        Node.x:D
        //   245: aload           n2
        //   247: getfield        Node.x:D
        //   250: dsub           
        //   251: dstore          vx
        //   253: aload_3        
        //   254: getfield        Node.y:D
        //   257: aload           n2
        //   259: getfield        Node.y:D
        //   262: dsub           
        //   263: dstore          vy
        //   265: dload           vx
        //   267: dload           vx
        //   269: dmul           
        //   270: dload           vy
        //   272: dload           vy
        //   274: dmul           
        //   275: dadd           
        //   276: dstore          len
        //   278: dload           len
        //   280: dconst_0       
        //   281: dcmpl          
        //   282: ifne            304
        //   285: dload           4
        //   287: invokestatic    java/lang/Math.random:()D
        //   290: dadd           
        //   291: dstore          dx
        //   293: dload           6
        //   295: invokestatic    java/lang/Math.random:()D
        //   298: dadd           
        //   299: dstore          dy
        //   301: goto            333
        //   304: dload           len
        //   306: ldc2_w          10000.0
        //   309: dcmpg          
        //   310: ifge            333
        //   313: dload           4
        //   315: dload           10
        //   317: dload           14
        //   319: ddiv           
        //   320: dadd           
        //   321: dstore          dx
        //   323: dload           6
        //   325: dload           12
        //   327: dload           14
        //   329: ddiv           
        //   330: dadd           
        //   331: dstore          6
        //   333: iinc            8, 1
        //   336: iload           8
        //   338: aload_0         /* this */
        //   339: getfield        GraphPanel.nnodes:I
        //   342: if_icmplt       226
        //   345: dload           4
        //   347: dload           4
        //   349: dmul           
        //   350: dload           6
        //   352: dload           6
        //   354: dmul           
        //   355: dadd           
        //   356: dstore          dlen
        //   358: dload           dlen
        //   360: dconst_0       
        //   361: dcmpl          
        //   362: ifle            404
        //   365: dload           dlen
        //   367: invokestatic    java/lang/Math.sqrt:(D)D
        //   370: ldc2_w          2.0
        //   373: ddiv           
        //   374: dstore          dlen
        //   376: aload_3        
        //   377: dup            
        //   378: getfield        Node.dx:D
        //   381: dload           4
        //   383: dload           dlen
        //   385: ddiv           
        //   386: dadd           
        //   387: putfield        Node.dx:D
        //   390: aload_3        
        //   391: dup            
        //   392: getfield        Node.dy:D
        //   395: dload           6
        //   397: dload           dlen
        //   399: ddiv           
        //   400: dadd           
        //   401: putfield        Node.dy:D
        //   404: iinc            2, 1
        //   407: iload_2        
        //   408: aload_0         /* this */
        //   409: getfield        GraphPanel.nnodes:I
        //   412: if_icmplt       207
        //   415: aload_0         /* this */
        //   416: invokevirtual   java/awt/Component.size:()Ljava/awt/Dimension;
        //   419: astore_3        /* d */
        //   420: iconst_0       
        //   421: istore          i
        //   423: goto            612
        //   426: aload_0         /* this */
        //   427: getfield        GraphPanel.nodes:[LNode;
        //   430: iload           4
        //   432: aaload         
        //   433: astore          n
        //   435: aload           n
        //   437: getfield        Node.fixed:Z
        //   440: ifne            583
        //   443: aload           n
        //   445: dup            
        //   446: getfield        Node.x:D
        //   449: ldc2_w          -5.0
        //   452: ldc2_w          5.0
        //   455: aload           n
        //   457: getfield        Node.dx:D
        //   460: invokestatic    java/lang/Math.min:(DD)D
        //   463: invokestatic    java/lang/Math.max:(DD)D
        //   466: dadd           
        //   467: putfield        Node.x:D
        //   470: aload           n
        //   472: dup            
        //   473: getfield        Node.y:D
        //   476: ldc2_w          -5.0
        //   479: ldc2_w          5.0
        //   482: aload           n
        //   484: getfield        Node.dy:D
        //   487: invokestatic    java/lang/Math.min:(DD)D
        //   490: invokestatic    java/lang/Math.max:(DD)D
        //   493: dadd           
        //   494: putfield        Node.y:D
        //   497: aload           n
        //   499: getfield        Node.x:D
        //   502: dconst_0       
        //   503: dcmpg          
        //   504: ifge            516
        //   507: aload           n
        //   509: dconst_0       
        //   510: putfield        Node.x:D
        //   513: goto            540
        //   516: aload           n
        //   518: getfield        Node.x:D
        //   521: aload_3        
        //   522: getfield        java/awt/Dimension.width:I
        //   525: i2d            
        //   526: dcmpl          
        //   527: ifle            540
        //   530: aload           5
        //   532: aload_3        
        //   533: getfield        java/awt/Dimension.width:I
        //   536: i2d            
        //   537: putfield        Node.x:D
        //   540: aload           5
        //   542: getfield        Node.y:D
        //   545: dconst_0       
        //   546: dcmpg          
        //   547: ifge            559
        //   550: aload           5
        //   552: dconst_0       
        //   553: putfield        Node.y:D
        //   556: goto            583
        //   559: aload           5
        //   561: getfield        Node.y:D
        //   564: aload_3        
        //   565: getfield        java/awt/Dimension.height:I
        //   568: i2d            
        //   569: dcmpl          
        //   570: ifle            583
        //   573: aload           5
        //   575: aload_3        
        //   576: getfield        java/awt/Dimension.height:I
        //   579: i2d            
        //   580: putfield        Node.y:D
        //   583: aload           5
        //   585: dup            
        //   586: getfield        Node.dx:D
        //   589: ldc2_w          2.0
        //   592: ddiv           
        //   593: putfield        Node.dx:D
        //   596: aload           5
        //   598: dup            
        //   599: getfield        Node.dy:D
        //   602: ldc2_w          2.0
        //   605: ddiv           
        //   606: putfield        Node.dy:D
        //   609: iinc            4, 1
        //   612: iload           4
        //   614: aload_0         /* this */
        //   615: getfield        GraphPanel.nnodes:I
        //   618: if_icmplt       426
        //   621: aload_0         /* this */
        //   622: invokevirtual   java/awt/Component.repaint:()V
        //   625: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  --------------------
        //  0      626     0     this  LGraphPanel;
        //  2      3       1     i     I
        //  12     182     2     e     LEdge;
        //  38     156     3     vx    D
        //  65     129     5     vy    D
        //  79     115     7     len   D
        //  100    94      9     f     D
        //  106    88      11    dx    D
        //  113    81      13    dy    D
        //  204    3       2     i     I
        //  214    12      3     n1    LNode;
        //  217    9       4     dx    D
        //  220    6       6     dy    D
        //  223    3       8     j     I
        //  293    11      4     dx    D
        //  301    3       6     dy    D
        //  241    63      9     n2    LNode;
        //  253    51      10    vx    D
        //  265    39      12    vy    D
        //  278    26      14    len   D
        //  323    10      4     dx    D
        //  358    46      9     dlen  D
        //  420    6       3     d     Ljava/awt/Dimension;
        //  423    3       4     i     I
        //  435    81      5     n     LNode;
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
    
    public void paintNode(final Graphics g, final Node n, final FontMetrics fm) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2         /* n */
        //     1: getfield        Node.x:D
        //     4: d2i            
        //     5: istore          x
        //     7: aload_2         /* n */
        //     8: getfield        Node.y:D
        //    11: d2i            
        //    12: istore          y
        //    14: aload_1         /* g */
        //    15: aload_2         /* n */
        //    16: aload_0         /* this */
        //    17: getfield        GraphPanel.pick:LNode;
        //    20: if_acmpne       30
        //    23: aload_0         /* this */
        //    24: getfield        GraphPanel.selectColor:Ljava/awt/Color;
        //    27: goto            48
        //    30: aload_2         /* n */
        //    31: getfield        Node.fixed:Z
        //    34: ifeq            44
        //    37: aload_0         /* this */
        //    38: getfield        GraphPanel.fixedColor:Ljava/awt/Color;
        //    41: goto            48
        //    44: aload_0         /* this */
        //    45: getfield        GraphPanel.nodeColor:Ljava/awt/Color;
        //    48: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //    51: aload_3         /* fm */
        //    52: aload_2         /* n */
        //    53: getfield        Node.lbl:Ljava/lang/String;
        //    56: invokevirtual   java/awt/FontMetrics.stringWidth:(Ljava/lang/String;)I
        //    59: bipush          10
        //    61: iadd           
        //    62: istore          w
        //    64: aload_3         /* fm */
        //    65: invokevirtual   java/awt/FontMetrics.getHeight:()I
        //    68: iconst_4       
        //    69: iadd           
        //    70: istore          h
        //    72: aload_1         /* g */
        //    73: iload           4
        //    75: iload           w
        //    77: iconst_2       
        //    78: idiv           
        //    79: isub           
        //    80: iload           5
        //    82: iload           h
        //    84: iconst_2       
        //    85: idiv           
        //    86: isub           
        //    87: iload           w
        //    89: iload           h
        //    91: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //    94: aload_1         /* g */
        //    95: getstatic       java/awt/Color.black:Ljava/awt/Color;
        //    98: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   101: aload_1         /* g */
        //   102: iload           4
        //   104: iload           w
        //   106: iconst_2       
        //   107: idiv           
        //   108: isub           
        //   109: iload           5
        //   111: iload           h
        //   113: iconst_2       
        //   114: idiv           
        //   115: isub           
        //   116: iload           w
        //   118: iconst_1       
        //   119: isub           
        //   120: iload           h
        //   122: iconst_1       
        //   123: isub           
        //   124: invokevirtual   java/awt/Graphics.drawRect:(IIII)V
        //   127: aload_1         /* g */
        //   128: aload_2         /* n */
        //   129: getfield        Node.lbl:Ljava/lang/String;
        //   132: iload           4
        //   134: iload           w
        //   136: bipush          10
        //   138: isub           
        //   139: iconst_2       
        //   140: idiv           
        //   141: isub           
        //   142: iload           5
        //   144: iload           h
        //   146: iconst_4       
        //   147: isub           
        //   148: iconst_2       
        //   149: idiv           
        //   150: isub           
        //   151: aload_3         /* fm */
        //   152: invokevirtual   java/awt/FontMetrics.getAscent:()I
        //   155: iadd           
        //   156: invokevirtual   java/awt/Graphics.drawString:(Ljava/lang/String;II)V
        //   159: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ----------------------
        //  0      160     0     this  LGraphPanel;
        //  0      160     1     g     Ljava/awt/Graphics;
        //  0      160     2     n     LNode;
        //  0      160     3     fm    Ljava/awt/FontMetrics;
        //  7      23      4     x     I
        //  14     16      5     y     I
        //  64     96      6     w     I
        //  72     88      7     h     I
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
    
    public synchronized void update(final Graphics g) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: invokevirtual   java/awt/Component.size:()Ljava/awt/Dimension;
        //     4: astore_2        /* d */
        //     5: aload_0         /* this */
        //     6: getfield        GraphPanel.offscreen:Ljava/awt/Image;
        //     9: ifnull          40
        //    12: aload_2         /* d */
        //    13: getfield        java/awt/Dimension.width:I
        //    16: aload_0         /* this */
        //    17: getfield        GraphPanel.offscreensize:Ljava/awt/Dimension;
        //    20: getfield        java/awt/Dimension.width:I
        //    23: if_icmpne       40
        //    26: aload_2         /* d */
        //    27: getfield        java/awt/Dimension.height:I
        //    30: aload_0         /* this */
        //    31: getfield        GraphPanel.offscreensize:Ljava/awt/Dimension;
        //    34: getfield        java/awt/Dimension.height:I
        //    37: if_icmpeq       83
        //    40: aload_0         /* this */
        //    41: aload_0         /* this */
        //    42: aload_2        
        //    43: getfield        java/awt/Dimension.width:I
        //    46: aload_2        
        //    47: getfield        java/awt/Dimension.height:I
        //    50: invokevirtual   java/awt/Component.createImage:(II)Ljava/awt/Image;
        //    53: putfield        GraphPanel.offscreen:Ljava/awt/Image;
        //    56: aload_0         /* this */
        //    57: aload_2        
        //    58: putfield        GraphPanel.offscreensize:Ljava/awt/Dimension;
        //    61: aload_0         /* this */
        //    62: aload_0         /* this */
        //    63: getfield        GraphPanel.offscreen:Ljava/awt/Image;
        //    66: invokevirtual   java/awt/Image.getGraphics:()Ljava/awt/Graphics;
        //    69: putfield        GraphPanel.offgraphics:Ljava/awt/Graphics;
        //    72: aload_0         /* this */
        //    73: getfield        GraphPanel.offgraphics:Ljava/awt/Graphics;
        //    76: aload_0         /* this */
        //    77: invokevirtual   java/awt/Component.getFont:()Ljava/awt/Font;
        //    80: invokevirtual   java/awt/Graphics.setFont:(Ljava/awt/Font;)V
        //    83: aload_0         /* this */
        //    84: getfield        GraphPanel.offgraphics:Ljava/awt/Graphics;
        //    87: aload_0         /* this */
        //    88: invokevirtual   java/awt/Component.getBackground:()Ljava/awt/Color;
        //    91: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //    94: aload_0         /* this */
        //    95: getfield        GraphPanel.offgraphics:Ljava/awt/Graphics;
        //    98: iconst_0       
        //    99: iconst_0       
        //   100: aload_2        
        //   101: getfield        java/awt/Dimension.width:I
        //   104: aload_2        
        //   105: getfield        java/awt/Dimension.height:I
        //   108: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   111: iconst_0       
        //   112: istore_3        /* i */
        //   113: goto            349
        //   116: aload_0         /* this */
        //   117: getfield        GraphPanel.edges:[LEdge;
        //   120: iload_3        
        //   121: aaload         
        //   122: astore          e
        //   124: aload_0         /* this */
        //   125: getfield        GraphPanel.nodes:[LNode;
        //   128: aload           e
        //   130: getfield        Edge.from:I
        //   133: aaload         
        //   134: getfield        Node.x:D
        //   137: d2i            
        //   138: istore          x1
        //   140: aload_0         /* this */
        //   141: getfield        GraphPanel.nodes:[LNode;
        //   144: aload           e
        //   146: getfield        Edge.from:I
        //   149: aaload         
        //   150: getfield        Node.y:D
        //   153: d2i            
        //   154: istore          y1
        //   156: aload_0         /* this */
        //   157: getfield        GraphPanel.nodes:[LNode;
        //   160: aload           e
        //   162: getfield        Edge.to:I
        //   165: aaload         
        //   166: getfield        Node.x:D
        //   169: d2i            
        //   170: istore          x2
        //   172: aload_0         /* this */
        //   173: getfield        GraphPanel.nodes:[LNode;
        //   176: aload           e
        //   178: getfield        Edge.to:I
        //   181: aaload         
        //   182: getfield        Node.y:D
        //   185: d2i            
        //   186: istore          y2
        //   188: iload           x1
        //   190: iload           x2
        //   192: isub           
        //   193: iload           x1
        //   195: iload           x2
        //   197: isub           
        //   198: imul           
        //   199: iload           y1
        //   201: iload           y2
        //   203: isub           
        //   204: iload           y1
        //   206: iload           y2
        //   208: isub           
        //   209: imul           
        //   210: iadd           
        //   211: i2d            
        //   212: invokestatic    java/lang/Math.sqrt:(D)D
        //   215: aload           e
        //   217: getfield        Edge.len:D
        //   220: dsub           
        //   221: invokestatic    java/lang/Math.abs:(D)D
        //   224: d2i            
        //   225: istore          len
        //   227: aload_0         /* this */
        //   228: getfield        GraphPanel.offgraphics:Ljava/awt/Graphics;
        //   231: iload           len
        //   233: bipush          10
        //   235: if_icmpge       245
        //   238: aload_0         /* this */
        //   239: getfield        GraphPanel.arcColor1:Ljava/awt/Color;
        //   242: goto            263
        //   245: iload           len
        //   247: bipush          20
        //   249: if_icmpge       259
        //   252: aload_0         /* this */
        //   253: getfield        GraphPanel.arcColor2:Ljava/awt/Color;
        //   256: goto            263
        //   259: aload_0         /* this */
        //   260: getfield        GraphPanel.arcColor3:Ljava/awt/Color;
        //   263: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   266: aload_0         /* this */
        //   267: getfield        GraphPanel.offgraphics:Ljava/awt/Graphics;
        //   270: iload           5
        //   272: iload           6
        //   274: iload           7
        //   276: iload           8
        //   278: invokevirtual   java/awt/Graphics.drawLine:(IIII)V
        //   281: aload_0         /* this */
        //   282: getfield        GraphPanel.stress:Z
        //   285: ifeq            346
        //   288: iload           9
        //   290: invokestatic    java/lang/String.valueOf:(I)Ljava/lang/String;
        //   293: astore          lbl
        //   295: aload_0         /* this */
        //   296: getfield        GraphPanel.offgraphics:Ljava/awt/Graphics;
        //   299: aload_0         /* this */
        //   300: getfield        GraphPanel.stressColor:Ljava/awt/Color;
        //   303: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   306: aload_0         /* this */
        //   307: getfield        GraphPanel.offgraphics:Ljava/awt/Graphics;
        //   310: aload           lbl
        //   312: iload           5
        //   314: iload           7
        //   316: iload           5
        //   318: isub           
        //   319: iconst_2       
        //   320: idiv           
        //   321: iadd           
        //   322: iload           6
        //   324: iload           8
        //   326: iload           6
        //   328: isub           
        //   329: iconst_2       
        //   330: idiv           
        //   331: iadd           
        //   332: invokevirtual   java/awt/Graphics.drawString:(Ljava/lang/String;II)V
        //   335: aload_0         /* this */
        //   336: getfield        GraphPanel.offgraphics:Ljava/awt/Graphics;
        //   339: aload_0         /* this */
        //   340: getfield        GraphPanel.edgeColor:Ljava/awt/Color;
        //   343: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   346: iinc            3, 1
        //   349: iload_3        
        //   350: aload_0         /* this */
        //   351: getfield        GraphPanel.nedges:I
        //   354: if_icmplt       116
        //   357: aload_0         /* this */
        //   358: getfield        GraphPanel.offgraphics:Ljava/awt/Graphics;
        //   361: invokevirtual   java/awt/Graphics.getFontMetrics:()Ljava/awt/FontMetrics;
        //   364: astore          fm
        //   366: iconst_0       
        //   367: istore          i
        //   369: goto            392
        //   372: aload_0         /* this */
        //   373: aload_0         /* this */
        //   374: getfield        GraphPanel.offgraphics:Ljava/awt/Graphics;
        //   377: aload_0         /* this */
        //   378: getfield        GraphPanel.nodes:[LNode;
        //   381: iload           5
        //   383: aaload         
        //   384: aload           4
        //   386: invokevirtual   GraphPanel.paintNode:(Ljava/awt/Graphics;LNode;Ljava/awt/FontMetrics;)V
        //   389: iinc            5, 1
        //   392: iload           5
        //   394: aload_0         /* this */
        //   395: getfield        GraphPanel.nnodes:I
        //   398: if_icmplt       372
        //   401: aload_1         /* g */
        //   402: aload_0         /* this */
        //   403: getfield        GraphPanel.offscreen:Ljava/awt/Image;
        //   406: iconst_0       
        //   407: iconst_0       
        //   408: aconst_null    
        //   409: invokevirtual   java/awt/Graphics.drawImage:(Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
        //   412: pop            
        //   413: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ----------------------
        //  0      414     0     this  LGraphPanel;
        //  0      414     1     g     Ljava/awt/Graphics;
        //  5      35      2     d     Ljava/awt/Dimension;
        //  113    3       3     i     I
        //  124    121     4     e     LEdge;
        //  140    105     5     x1    I
        //  156    89      6     y1    I
        //  172    73      7     x2    I
        //  188    57      8     y2    I
        //  227    18      9     len   I
        //  295    51      10    lbl   Ljava/lang/String;
        //  366    6       4     fm    Ljava/awt/FontMetrics;
        //  369    3       5     i     I
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
    
    public synchronized boolean mouseDown(final Event evt, final int x, final int y) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc2_w          1.7976931348623157E308
        //     3: dstore          bestdist
        //     5: iconst_0       
        //     6: istore          i
        //     8: goto            78
        //    11: aload_0         /* this */
        //    12: getfield        GraphPanel.nodes:[LNode;
        //    15: iload           6
        //    17: aaload         
        //    18: astore          n
        //    20: aload           n
        //    22: getfield        Node.x:D
        //    25: iload_2         /* x */
        //    26: i2d            
        //    27: dsub           
        //    28: aload           n
        //    30: getfield        Node.x:D
        //    33: iload_2         /* x */
        //    34: i2d            
        //    35: dsub           
        //    36: dmul           
        //    37: aload           n
        //    39: getfield        Node.y:D
        //    42: iload_3         /* y */
        //    43: i2d            
        //    44: dsub           
        //    45: aload           n
        //    47: getfield        Node.y:D
        //    50: iload_3         /* y */
        //    51: i2d            
        //    52: dsub           
        //    53: dmul           
        //    54: dadd           
        //    55: dstore          dist
        //    57: dload           dist
        //    59: dload           4
        //    61: dcmpg          
        //    62: ifge            75
        //    65: aload_0         /* this */
        //    66: aload           n
        //    68: putfield        GraphPanel.pick:LNode;
        //    71: dload           dist
        //    73: dstore          4
        //    75: iinc            6, 1
        //    78: iload           6
        //    80: aload_0         /* this */
        //    81: getfield        GraphPanel.nnodes:I
        //    84: if_icmplt       11
        //    87: aload_0         /* this */
        //    88: aload_0         /* this */
        //    89: getfield        GraphPanel.pick:LNode;
        //    92: getfield        Node.fixed:Z
        //    95: putfield        GraphPanel.pickfixed:Z
        //    98: aload_0         /* this */
        //    99: getfield        GraphPanel.pick:LNode;
        //   102: iconst_1       
        //   103: putfield        Node.fixed:Z
        //   106: aload_0         /* this */
        //   107: getfield        GraphPanel.pick:LNode;
        //   110: iload_2         /* x */
        //   111: i2d            
        //   112: putfield        Node.x:D
        //   115: aload_0         /* this */
        //   116: getfield        GraphPanel.pick:LNode;
        //   119: iload_3         /* y */
        //   120: i2d            
        //   121: putfield        Node.y:D
        //   124: aload_0         /* this */
        //   125: invokevirtual   java/awt/Component.repaint:()V
        //   128: iconst_1       
        //   129: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name      Signature
        //  -----  ------  ----  --------  ----------------
        //  0      130     0     this      LGraphPanel;
        //  0      130     1     evt       Ljava/awt/Event;
        //  0      130     2     x         I
        //  0      130     3     y         I
        //  5      6       4     bestdist  D
        //  8      3       6     i         I
        //  20     55      7     n         LNode;
        //  57     18      8     dist      D
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
    
    public synchronized boolean mouseDrag(final Event evt, final int x, final int y) {
        this.pick.x = x;
        this.pick.y = y;
        this.repaint();
        return true;
    }
    
    public synchronized boolean mouseUp(final Event evt, final int x, final int y) {
        this.pick.x = x;
        this.pick.y = y;
        this.pick.fixed = this.pickfixed;
        this.pick = null;
        this.repaint();
        return true;
    }
    
    public void start() {
        (this.relaxer = new Thread(this)).start();
    }
    
    public void stop() {
        this.relaxer.stop();
    }
}
