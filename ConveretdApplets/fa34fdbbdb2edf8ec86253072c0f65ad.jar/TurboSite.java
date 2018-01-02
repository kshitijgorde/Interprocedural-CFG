import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.Image;
import java.awt.Graphics;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TurboSite extends Applet implements Runnable
{
    Thread m_TurboSite;
    URL theURL;
    int Counter;
    String[] aURL;
    private Graphics m_Graphics;
    private Image[] m_Images;
    private int m_nCurrImage;
    private int m_nImgWidth;
    private int m_nImgHeight;
    private boolean m_fAllLoaded;
    private final int NUM_IMAGES = 7;
    private String m_UserName;
    private String m_Copyright;
    private final String PARAM_UserName = "UserName";
    private final String PARAM_Copyright = "Copyright";
    
    public void start() {
        boolean testing = false;
        testing = this.m_Copyright.equals("(c)Copyright 1998 Intellisoft, Inc.");
        if ((testing = true) && this.m_TurboSite == null) {
            (this.m_TurboSite = new Thread(this)).start();
        }
    }
    
    public String[][] getParameterInfo() {
        final String[][] info = { { "UserName", "String", "Licensed User Name" } };
        return info;
    }
    
    public void stop() {
        if (this.m_TurboSite != null) {
            this.m_TurboSite.stop();
            this.m_TurboSite = null;
        }
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        try {
            this.theURL = new URL("http://www.intellisoft-inc.com/TurboSite.html");
        }
        catch (MalformedURLException e) {
            System.out.println("Bad URL: " + this.theURL);
        }
        this.getAppletContext().showDocument(this.theURL, "_blank");
        return true;
    }
    
    private void displayImage(final Graphics g) {
        if (!this.m_fAllLoaded) {
            return;
        }
        g.drawImage(this.m_Images[this.m_nCurrImage], (this.size().width - this.m_nImgWidth) / 2, (this.size().height - this.m_nImgHeight) / 2, null);
    }
    
    public String getAppletInfo() {
        return "Name: TurboSite\r\n" + "Author: Intellisoft, Inc.\r\n" + "(c) Copyright 1998 ";
    }
    
    public boolean mouseUp(final Event evt, final int x, final int y) {
        return true;
    }
    
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_1        /* conn */
        //     2: aconst_null    
        //     3: astore_2        /* data */
        //     4: new             Ljava/lang/StringBuffer;
        //     7: dup            
        //     8: invokespecial   java/lang/StringBuffer.<init>:()V
        //    11: astore          buf
        //    13: aload_0         /* this */
        //    14: iconst_0       
        //    15: putfield        TurboSite.m_nCurrImage:I
        //    18: aload_0         /* this */
        //    19: getfield        TurboSite.m_fAllLoaded:Z
        //    22: ifne            227
        //    25: aload_0         /* this */
        //    26: invokevirtual   java/awt/Component.repaint:()V
        //    29: aload_0         /* this */
        //    30: aload_0         /* this */
        //    31: invokevirtual   java/awt/Component.getGraphics:()Ljava/awt/Graphics;
        //    34: putfield        TurboSite.m_Graphics:Ljava/awt/Graphics;
        //    37: aload_0         /* this */
        //    38: bipush          7
        //    40: anewarray       Ljava/awt/Image;
        //    43: putfield        TurboSite.m_Images:[Ljava/awt/Image;
        //    46: new             Ljava/awt/MediaTracker;
        //    49: dup            
        //    50: aload_0         /* this */
        //    51: invokespecial   java/awt/MediaTracker.<init>:(Ljava/awt/Component;)V
        //    54: astore          tracker
        //    56: iconst_1       
        //    57: istore          i
        //    59: new             Ljava/lang/StringBuffer;
        //    62: dup            
        //    63: invokespecial   java/lang/StringBuffer.<init>:()V
        //    66: ldc             "img"
        //    68: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    71: iload           i
        //    73: bipush          10
        //    75: if_icmpge       83
        //    78: ldc             "0"
        //    80: goto            85
        //    83: ldc             ""
        //    85: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    88: iload           i
        //    90: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //    93: ldc             ".gif"
        //    95: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    98: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   101: astore          strImage
        //   103: aload_0         /* this */
        //   104: getfield        TurboSite.m_Images:[Ljava/awt/Image;
        //   107: iload           i
        //   109: iconst_1       
        //   110: isub           
        //   111: aload_0         /* this */
        //   112: aload_0         /* this */
        //   113: invokevirtual   java/applet/Applet.getDocumentBase:()Ljava/net/URL;
        //   116: aload           strImage
        //   118: invokevirtual   java/applet/Applet.getImage:(Ljava/net/URL;Ljava/lang/String;)Ljava/awt/Image;
        //   121: aastore        
        //   122: aload           tracker
        //   124: aload_0         /* this */
        //   125: getfield        TurboSite.m_Images:[Ljava/awt/Image;
        //   128: iload           i
        //   130: iconst_1       
        //   131: isub           
        //   132: aaload         
        //   133: iconst_0       
        //   134: invokevirtual   java/awt/MediaTracker.addImage:(Ljava/awt/Image;I)V
        //   137: iinc            i, 1
        //   140: iload           i
        //   142: bipush          7
        //   144: if_icmple       59
        //   147: aload           tracker
        //   149: invokevirtual   java/awt/MediaTracker.waitForAll:()V
        //   152: aload_0         /* this */
        //   153: aload           tracker
        //   155: invokevirtual   java/awt/MediaTracker.isErrorAny:()Z
        //   158: ifeq            165
        //   161: iconst_0       
        //   162: goto            166
        //   165: iconst_1       
        //   166: putfield        TurboSite.m_fAllLoaded:Z
        //   169: goto            174
        //   172: astore          e
        //   174: aload_0         /* this */
        //   175: getfield        TurboSite.m_fAllLoaded:Z
        //   178: ifne            199
        //   181: aload_0         /* this */
        //   182: invokevirtual   TurboSite.stop:()V
        //   185: aload_0         /* this */
        //   186: getfield        TurboSite.m_Graphics:Ljava/awt/Graphics;
        //   189: ldc             "Error loading images!"
        //   191: bipush          10
        //   193: bipush          40
        //   195: invokevirtual   java/awt/Graphics.drawString:(Ljava/lang/String;II)V
        //   198: return         
        //   199: aload_0         /* this */
        //   200: aload_0         /* this */
        //   201: getfield        TurboSite.m_Images:[Ljava/awt/Image;
        //   204: iconst_0       
        //   205: aaload         
        //   206: aload_0         /* this */
        //   207: invokevirtual   java/awt/Image.getWidth:(Ljava/awt/image/ImageObserver;)I
        //   210: putfield        TurboSite.m_nImgWidth:I
        //   213: aload_0         /* this */
        //   214: aload_0         /* this */
        //   215: getfield        TurboSite.m_Images:[Ljava/awt/Image;
        //   218: iconst_0       
        //   219: aaload         
        //   220: aload_0         /* this */
        //   221: invokevirtual   java/awt/Image.getHeight:(Ljava/awt/image/ImageObserver;)I
        //   224: putfield        TurboSite.m_nImgHeight:I
        //   227: aload_0         /* this */
        //   228: invokevirtual   java/awt/Component.repaint:()V
        //   231: aload_0         /* this */
        //   232: aload_0         /* this */
        //   233: getfield        TurboSite.m_Graphics:Ljava/awt/Graphics;
        //   236: invokespecial   TurboSite.displayImage:(Ljava/awt/Graphics;)V
        //   239: aload_0         /* this */
        //   240: dup            
        //   241: getfield        TurboSite.m_nCurrImage:I
        //   244: iconst_1       
        //   245: iadd           
        //   246: putfield        TurboSite.m_nCurrImage:I
        //   249: aload_0         /* this */
        //   250: getfield        TurboSite.m_nCurrImage:I
        //   253: bipush          7
        //   255: if_icmpne       263
        //   258: aload_0         /* this */
        //   259: iconst_5       
        //   260: putfield        TurboSite.m_nCurrImage:I
        //   263: aload_0         /* this */
        //   264: aload_0         /* this */
        //   265: getfield        TurboSite.Counter:I
        //   268: iconst_1       
        //   269: iadd           
        //   270: putfield        TurboSite.Counter:I
        //   273: aload_0         /* this */
        //   274: getfield        TurboSite.Counter:I
        //   277: bipush          10
        //   279: if_icmple       287
        //   282: aload_0         /* this */
        //   283: iconst_0       
        //   284: putfield        TurboSite.Counter:I
        //   287: aload_0         /* this */
        //   288: getfield        TurboSite.aURL:[Ljava/lang/String;
        //   291: aload_0         /* this */
        //   292: getfield        TurboSite.Counter:I
        //   295: aaload         
        //   296: ifnull          437
        //   299: aload_0         /* this */
        //   300: new             Ljava/net/URL;
        //   303: dup            
        //   304: aload_0         /* this */
        //   305: getfield        TurboSite.aURL:[Ljava/lang/String;
        //   308: aload_0         /* this */
        //   309: getfield        TurboSite.Counter:I
        //   312: aaload         
        //   313: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   316: putfield        TurboSite.theURL:Ljava/net/URL;
        //   319: goto            352
        //   322: astore          e
        //   324: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   327: new             Ljava/lang/StringBuffer;
        //   330: dup            
        //   331: invokespecial   java/lang/StringBuffer.<init>:()V
        //   334: ldc             "Bad URL: "
        //   336: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   339: aload_0         /* this */
        //   340: getfield        TurboSite.theURL:Ljava/net/URL;
        //   343: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/Object;)Ljava/lang/StringBuffer;
        //   346: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   349: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   352: aload_0         /* this */
        //   353: getfield        TurboSite.theURL:Ljava/net/URL;
        //   356: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //   359: astore_1        /* conn */
        //   360: aload_1         /* conn */
        //   361: invokevirtual   java/net/URLConnection.connect:()V
        //   364: new             Ljava/io/DataInputStream;
        //   367: dup            
        //   368: new             Ljava/io/BufferedInputStream;
        //   371: dup            
        //   372: aload_1         /* conn */
        //   373: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
        //   376: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //   379: invokespecial   java/io/DataInputStream.<init>:(Ljava/io/InputStream;)V
        //   382: astore_2        /* data */
        //   383: goto            393
        //   386: aload           buf
        //   388: aload_3        
        //   389: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   392: pop            
        //   393: aload_2         /* data */
        //   394: invokevirtual   java/io/DataInputStream.readLine:()Ljava/lang/String;
        //   397: dup            
        //   398: astore_3        /* line */
        //   399: ifnonnull       386
        //   402: goto            437
        //   405: astore          e
        //   407: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   410: new             Ljava/lang/StringBuffer;
        //   413: dup            
        //   414: invokespecial   java/lang/StringBuffer.<init>:()V
        //   417: ldc_w           "IO Error: "
        //   420: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   423: aload           e
        //   425: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //   428: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   431: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   434: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   437: ldc2_w          1000
        //   440: invokestatic    java/lang/Thread.sleep:(J)V
        //   443: goto            452
        //   446: astore          8
        //   448: aload_0         /* this */
        //   449: invokevirtual   TurboSite.stop:()V
        //   452: goto            231
        //    LocalVariableTable:
        //  Start  Length  Slot  Name      Signature
        //  -----  ------  ----  --------  --------------------------------
        //  0      455     0     this      LTurboSite;
        //  2      453     1     conn      Ljava/net/URLConnection;
        //  4      451     2     data      Ljava/io/DataInputStream;
        //  399    56      3     line      Ljava/lang/String;
        //  13     442     4     buf       Ljava/lang/StringBuffer;
        //  56     171     5     tracker   Ljava/awt/MediaTracker;
        //  103    124     6     strImage  Ljava/lang/String;
        //  59     88      7     i         I
        //  0      174     8     e         Ljava/lang/InterruptedException;
        //  0      452     8     e         Ljava/lang/InterruptedException;
        //  0      352     8     e         Ljava/net/MalformedURLException;
        //  0      437     8     e         Ljava/io/IOException;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  147    169    172    174    Ljava/lang/InterruptedException;
        //  299    319    322    352    Ljava/net/MalformedURLException;
        //  352    402    405    437    Ljava/io/IOException;
        //  231    443    446    452    Ljava/lang/InterruptedException;
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
    
    public void destroy() {
    }
    
    public TurboSite() {
        this.aURL = new String[11];
        this.m_UserName = "unlicensed";
        this.m_Copyright = "unlicensed";
    }
    
    public void init() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_1        /* conn */
        //     2: aconst_null    
        //     3: astore_2        /* data */
        //     4: new             Ljava/lang/StringBuffer;
        //     7: dup            
        //     8: invokespecial   java/lang/StringBuffer.<init>:()V
        //    11: astore          buf
        //    13: iconst_0       
        //    14: istore          i
        //    16: aload_0         /* this */
        //    17: iconst_0       
        //    18: putfield        TurboSite.Counter:I
        //    21: aload_0         /* this */
        //    22: ldc_w           "Copyright"
        //    25: invokevirtual   java/applet/Applet.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //    28: astore          param
        //    30: aload           param
        //    32: ifnull          41
        //    35: aload_0         /* this */
        //    36: aload           param
        //    38: putfield        TurboSite.m_Copyright:Ljava/lang/String;
        //    41: aload_0         /* this */
        //    42: ldc             "UserName"
        //    44: invokevirtual   java/applet/Applet.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //    47: astore          param
        //    49: aload           param
        //    51: ifnull          60
        //    54: aload_0         /* this */
        //    55: aload           param
        //    57: putfield        TurboSite.m_UserName:Ljava/lang/String;
        //    60: aload_0         /* this */
        //    61: new             Ljava/net/URL;
        //    64: dup            
        //    65: aload_0         /* this */
        //    66: getfield        TurboSite.m_UserName:Ljava/lang/String;
        //    69: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    72: putfield        TurboSite.theURL:Ljava/net/URL;
        //    75: goto            108
        //    78: astore          e
        //    80: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //    83: new             Ljava/lang/StringBuffer;
        //    86: dup            
        //    87: invokespecial   java/lang/StringBuffer.<init>:()V
        //    90: ldc             "Bad URL: "
        //    92: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    95: aload_0         /* this */
        //    96: getfield        TurboSite.theURL:Ljava/net/URL;
        //    99: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/Object;)Ljava/lang/StringBuffer;
        //   102: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   105: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   108: aload_0         /* this */
        //   109: getfield        TurboSite.theURL:Ljava/net/URL;
        //   112: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //   115: astore_1        /* conn */
        //   116: aload_1         /* conn */
        //   117: invokevirtual   java/net/URLConnection.connect:()V
        //   120: new             Ljava/io/DataInputStream;
        //   123: dup            
        //   124: new             Ljava/io/BufferedInputStream;
        //   127: dup            
        //   128: aload_1         /* conn */
        //   129: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
        //   132: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //   135: invokespecial   java/io/DataInputStream.<init>:(Ljava/io/InputStream;)V
        //   138: astore_2        /* data */
        //   139: goto            179
        //   142: aload_0         /* this */
        //   143: getfield        TurboSite.aURL:[Ljava/lang/String;
        //   146: iload           i
        //   148: aload_3        
        //   149: aastore        
        //   150: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   153: aload_0         /* this */
        //   154: getfield        TurboSite.aURL:[Ljava/lang/String;
        //   157: iload           i
        //   159: aaload         
        //   160: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   163: iload           i
        //   165: iconst_1       
        //   166: iadd           
        //   167: istore          i
        //   169: iload           i
        //   171: bipush          10
        //   173: if_icmple       179
        //   176: goto            188
        //   179: aload_2         /* data */
        //   180: invokevirtual   java/io/DataInputStream.readLine:()Ljava/lang/String;
        //   183: dup            
        //   184: astore_3        /* line */
        //   185: ifnonnull       142
        //   188: goto            223
        //   191: astore          e
        //   193: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   196: new             Ljava/lang/StringBuffer;
        //   199: dup            
        //   200: invokespecial   java/lang/StringBuffer.<init>:()V
        //   203: ldc_w           "IO Error: "
        //   206: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   209: aload           e
        //   211: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //   214: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   217: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   220: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   223: aload_0         /* this */
        //   224: bipush          110
        //   226: bipush          32
        //   228: invokevirtual   java/applet/Applet.resize:(II)V
        //   231: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name   Signature
        //  -----  ------  ----  -----  --------------------------------
        //  0      232     0     this   LTurboSite;
        //  2      230     1     conn   Ljava/net/URLConnection;
        //  4      228     2     data   Ljava/io/DataInputStream;
        //  185    47      3     line   Ljava/lang/String;
        //  13     219     4     buf    Ljava/lang/StringBuffer;
        //  16     216     5     i      I
        //  30     202     6     param  Ljava/lang/String;
        //  0      108     8     e      Ljava/net/MalformedURLException;
        //  0      223     8     e      Ljava/io/IOException;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  60     75     78     108    Ljava/net/MalformedURLException;
        //  108    188    191    223    Ljava/io/IOException;
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
    
    public void paint(final Graphics g) {
        if (this.m_fAllLoaded) {
            final Rectangle r = g.getClipRect();
            g.clearRect(r.x, r.y, r.width, r.height);
            this.displayImage(g);
        }
        else {
            g.drawString("Starting TurboSite...", 10, 20);
        }
    }
}
