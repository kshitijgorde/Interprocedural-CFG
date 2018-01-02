// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import java.net.URL;
import java.io.IOException;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import de.mud.jta.PluginListener;
import java.applet.Applet;
import de.mud.jta.event.AppletListener;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import de.mud.jta.PluginBus;
import java.applet.AppletContext;
import java.io.PipedOutputStream;
import java.io.PipedInputStream;
import java.awt.Menu;
import java.awt.Panel;
import java.awt.List;
import java.util.Vector;
import de.mud.jta.VisualPlugin;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class URLFilter extends Plugin implements FilterPlugin, VisualPlugin, Runnable
{
    private static final int debug = 0;
    protected Vector protocols;
    protected List urlList;
    protected Panel urlPanel;
    protected Menu urlMenu;
    protected PipedInputStream pin;
    protected PipedOutputStream pout;
    protected AppletContext context;
    private Vector urlCache;
    protected FilterPlugin source;
    
    public URLFilter(final PluginBus bus, final String id) {
        super(bus, id);
        this.protocols = new Vector();
        this.urlList = new List(4, false);
        this.urlCache = new Vector();
        this.urlPanel = new Panel(new BorderLayout());
        this.urlList.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                URLFilter.this.showURL(evt.getActionCommand());
            }
        });
        this.urlPanel.add("Center", this.urlList);
        final Panel p = new Panel(new GridLayout(3, 1));
        Button b = new Button("Clear List");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                URLFilter.this.urlCache.removeAllElements();
                URLFilter.this.urlList.removeAll();
            }
        });
        p.add(b);
        b = new Button("Remove URL");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                final String item = URLFilter.this.urlList.getSelectedItem();
                if (item != null) {
                    URLFilter.this.urlCache.removeElement(item);
                    URLFilter.this.urlList.remove(item);
                }
            }
        });
        p.add(b);
        b = new Button("Show URL");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                final String item = URLFilter.this.urlList.getSelectedItem();
                if (item != null) {
                    URLFilter.this.showURL(item);
                }
            }
        });
        p.add(b);
        this.urlPanel.add("East", p);
        bus.registerPluginListener(new AppletListener() {
            public void setApplet(final Applet applet) {
                URLFilter.this.context = applet.getAppletContext();
            }
        });
        bus.registerPluginListener(new ConfigurationListener() {
            public void setConfiguration(final PluginConfig config) {
                final String s;
                if ((s = config.getProperty("URLFilter", id, "protocols")) != null) {
                    int old = -1;
                    for (int idx = s.indexOf(44); idx >= 0; idx = s.indexOf(44, old + 1)) {
                        System.out.println("URLFilter: adding protocol '" + s.substring(old + 1, idx) + "'");
                        URLFilter.this.protocols.addElement(s.substring(old + 1, idx));
                        old = idx;
                    }
                    System.out.println("URLFilter: adding protocol '" + s.substring(old + 1) + "'");
                    URLFilter.this.protocols.addElement(s.substring(old + 1));
                }
                else {
                    URLFilter.this.protocols.addElement("http");
                    URLFilter.this.protocols.addElement("ftp");
                    URLFilter.this.protocols.addElement("gopher");
                    URLFilter.this.protocols.addElement("file");
                }
            }
        });
        this.pin = new PipedInputStream();
        this.pout = new PipedOutputStream();
        try {
            this.pout.connect(this.pin);
        }
        catch (IOException e) {
            System.err.println("URLFilter: error installing recognizer: " + e);
        }
        final Thread recognizer = new Thread(this);
        recognizer.start();
    }
    
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/io/StreamTokenizer;
        //     3: dup            
        //     4: new             Ljava/io/BufferedReader;
        //     7: dup            
        //     8: new             Ljava/io/InputStreamReader;
        //    11: dup            
        //    12: aload_0         /* this */
        //    13: getfield        de/mud/jta/plugin/URLFilter.pin:Ljava/io/PipedInputStream;
        //    16: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    19: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //    22: invokespecial   java/io/StreamTokenizer.<init>:(Ljava/io/Reader;)V
        //    25: astore_1        /* st */
        //    26: aload_1         /* st */
        //    27: iconst_1       
        //    28: invokevirtual   java/io/StreamTokenizer.eolIsSignificant:(Z)V
        //    31: aload_1         /* st */
        //    32: iconst_0       
        //    33: invokevirtual   java/io/StreamTokenizer.slashSlashComments:(Z)V
        //    36: aload_1         /* st */
        //    37: iconst_0       
        //    38: invokevirtual   java/io/StreamTokenizer.slashStarComments:(Z)V
        //    41: aload_1         /* st */
        //    42: iconst_0       
        //    43: bipush          31
        //    45: invokevirtual   java/io/StreamTokenizer.whitespaceChars:(II)V
        //    48: aload_1         /* st */
        //    49: bipush          34
        //    51: invokevirtual   java/io/StreamTokenizer.ordinaryChar:(I)V
        //    54: aload_1         /* st */
        //    55: bipush          60
        //    57: invokevirtual   java/io/StreamTokenizer.ordinaryChar:(I)V
        //    60: aload_1         /* st */
        //    61: bipush          62
        //    63: invokevirtual   java/io/StreamTokenizer.ordinaryChar:(I)V
        //    66: aload_1         /* st */
        //    67: bipush          47
        //    69: invokevirtual   java/io/StreamTokenizer.ordinaryChar:(I)V
        //    72: aload_1         /* st */
        //    73: bipush          58
        //    75: invokevirtual   java/io/StreamTokenizer.ordinaryChar:(I)V
        //    78: goto            322
        //    81: iload_2        
        //    82: bipush          -3
        //    84: if_icmpne       322
        //    87: aload_1         /* st */
        //    88: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
        //    91: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
        //    94: astore_3        /* word */
        //    95: aload_0         /* this */
        //    96: getfield        de/mud/jta/plugin/URLFilter.protocols:Ljava/util/Vector;
        //    99: aload_3         /* word */
        //   100: invokevirtual   java/util/Vector.contains:(Ljava/lang/Object;)Z
        //   103: ifeq            322
        //   106: aload_1         /* st */
        //   107: invokevirtual   java/io/StreamTokenizer.nextToken:()I
        //   110: bipush          58
        //   112: if_icmpne       322
        //   115: aload_1         /* st */
        //   116: invokevirtual   java/io/StreamTokenizer.nextToken:()I
        //   119: bipush          47
        //   121: if_icmpne       322
        //   124: new             Ljava/lang/StringBuffer;
        //   127: dup            
        //   128: invokespecial   java/lang/StringBuffer.<init>:()V
        //   131: aload_3         /* word */
        //   132: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   135: ldc             ":/"
        //   137: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   140: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   143: astore          url
        //   145: goto            203
        //   148: iload_2        
        //   149: bipush          -3
        //   151: if_icmpne       181
        //   154: new             Ljava/lang/StringBuffer;
        //   157: dup            
        //   158: invokespecial   java/lang/StringBuffer.<init>:()V
        //   161: aload           url
        //   163: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   166: aload_1         /* st */
        //   167: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
        //   170: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   173: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   176: astore          url
        //   178: goto            203
        //   181: new             Ljava/lang/StringBuffer;
        //   184: dup            
        //   185: invokespecial   java/lang/StringBuffer.<init>:()V
        //   188: aload           url
        //   190: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   193: iload_2        
        //   194: i2c            
        //   195: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   198: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   201: astore          url
        //   203: aload_1         /* st */
        //   204: invokevirtual   java/io/StreamTokenizer.nextToken:()I
        //   207: dup            
        //   208: istore_2        /* token */
        //   209: bipush          -3
        //   211: if_icmpeq       148
        //   214: iload_2         /* token */
        //   215: bipush          47
        //   217: if_icmpeq       148
        //   220: aload           url
        //   222: ldc             "."
        //   224: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   227: ifeq            245
        //   230: aload           url
        //   232: iconst_0       
        //   233: aload           url
        //   235: invokevirtual   java/lang/String.length:()I
        //   238: iconst_1       
        //   239: isub           
        //   240: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   243: astore          url
        //   245: aload_0         /* this */
        //   246: getfield        de/mud/jta/plugin/URLFilter.urlCache:Ljava/util/Vector;
        //   249: aload           url
        //   251: invokevirtual   java/util/Vector.contains:(Ljava/lang/Object;)Z
        //   254: ifne            322
        //   257: aload_0         /* this */
        //   258: getfield        de/mud/jta/plugin/URLFilter.urlCache:Ljava/util/Vector;
        //   261: aload           url
        //   263: invokevirtual   java/util/Vector.addElement:(Ljava/lang/Object;)V
        //   266: aload_0         /* this */
        //   267: getfield        de/mud/jta/plugin/URLFilter.urlList:Ljava/awt/List;
        //   270: aload           url
        //   272: invokevirtual   java/awt/List.add:(Ljava/lang/String;)V
        //   275: aload_0         /* this */
        //   276: getfield        de/mud/jta/plugin/URLFilter.urlList:Ljava/awt/List;
        //   279: aload_0         /* this */
        //   280: getfield        de/mud/jta/plugin/URLFilter.urlList:Ljava/awt/List;
        //   283: invokevirtual   java/awt/List.getItemCount:()I
        //   286: iconst_1       
        //   287: isub           
        //   288: invokevirtual   java/awt/List.makeVisible:(I)V
        //   291: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   294: new             Ljava/lang/StringBuffer;
        //   297: dup            
        //   298: invokespecial   java/lang/StringBuffer.<init>:()V
        //   301: ldc             "URLFilter: found \""
        //   303: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   306: aload           url
        //   308: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   311: ldc             "\""
        //   313: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   316: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   319: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   322: aload_1         /* st */
        //   323: invokevirtual   java/io/StreamTokenizer.nextToken:()I
        //   326: dup            
        //   327: istore_2        /* token */
        //   328: iconst_m1      
        //   329: if_icmpne       81
        //   332: goto            361
        //   335: astore_1        /* e */
        //   336: getstatic       java/lang/System.err:Ljava/io/PrintStream;
        //   339: new             Ljava/lang/StringBuffer;
        //   342: dup            
        //   343: invokespecial   java/lang/StringBuffer.<init>:()V
        //   346: ldc             "URLFilter: recognition aborted: "
        //   348: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   351: aload_1         /* e */
        //   352: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/Object;)Ljava/lang/StringBuffer;
        //   355: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   358: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   361: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name   Signature
        //  -----  ------  ----  -----  -----------------------------
        //  0      362     0     this   Lde/mud/jta/plugin/URLFilter;
        //  26     306     1     st     Ljava/io/StreamTokenizer;
        //  209    123     2     token  I
        //  95     227     3     word   Ljava/lang/String;
        //  145    177     4     url    Ljava/lang/String;
        //  335    26      1     e      Ljava/io/IOException;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      332    335    361    Ljava/io/IOException;
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
    
    protected void showURL(final String url) {
        if (this.context == null) {
            System.err.println("URLFilter: no url-viewer available\n");
            return;
        }
        try {
            this.context.showDocument(new URL(url), "URLFilter");
        }
        catch (Exception e) {
            System.err.println("URLFilter: cannot load url: " + e);
        }
    }
    
    public void setFilterSource(final FilterPlugin plugin) {
        this.source = plugin;
    }
    
    public int read(final byte[] b) throws IOException {
        final int n = this.source.read(b);
        if (n > 0) {
            this.pout.write(b, 0, n);
        }
        return n;
    }
    
    public void write(final byte[] b) throws IOException {
        this.source.write(b);
    }
    
    public Component getPluginVisual() {
        return this.urlPanel;
    }
    
    public Menu getPluginMenu() {
        return this.urlMenu;
    }
}
