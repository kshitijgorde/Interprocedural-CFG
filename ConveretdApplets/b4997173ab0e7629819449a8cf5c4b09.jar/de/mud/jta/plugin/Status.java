// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import java.awt.Menu;
import de.mud.jta.event.OnlineStatusListener;
import java.awt.Component;
import de.mud.jta.event.SocketListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import de.mud.jta.PluginListener;
import java.awt.Color;
import java.awt.Font;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import de.mud.jta.PluginBus;
import java.util.Hashtable;
import java.awt.Panel;
import java.awt.Label;
import de.mud.jta.VisualPlugin;
import de.mud.jta.Plugin;

public class Status extends Plugin implements VisualPlugin, Runnable
{
    private static final int debug = 1;
    private Label status;
    private Label host;
    private Panel sPanel;
    private String address;
    private String port;
    private String infoURL;
    private int interval;
    private Thread infoThread;
    private Hashtable ports;
    
    public Status(final PluginBus bus, final String id) {
        super(bus, id);
        this.ports = new Hashtable();
        bus.registerPluginListener(new ConfigurationListener() {
            public void setConfiguration(final PluginConfig config) {
                Status.this.infoURL = config.getProperty("Status", id, "info");
                if (Status.this.infoURL != null) {
                    Status.this.host.setAlignment(1);
                }
                String tmp;
                if ((tmp = config.getProperty("Status", id, "font")) != null) {
                    final String font = tmp;
                    int style = 0;
                    int fsize = 12;
                    if ((tmp = config.getProperty("Status", id, "fontSize")) != null) {
                        fsize = Integer.parseInt(tmp);
                    }
                    final String fontStyle = config.getProperty("Status", id, "fontStyle");
                    if (fontStyle == null || fontStyle.equals("plain")) {
                        style = 0;
                    }
                    else if (fontStyle.equals("bold")) {
                        style = 1;
                    }
                    else if (fontStyle.equals("italic")) {
                        style = 2;
                    }
                    else if (fontStyle.equals("bold+italic")) {
                        style = 3;
                    }
                    Status.this.host.setFont(new Font(font, style, fsize));
                }
                if ((tmp = config.getProperty("Status", id, "foreground")) != null) {
                    Status.this.host.setForeground(Color.decode(tmp));
                }
                if ((tmp = config.getProperty("Status", id, "background")) != null) {
                    Status.this.host.setBackground(Color.decode(tmp));
                }
                if (config.getProperty("Status", id, "interval") != null) {
                    try {
                        Status.this.interval = Integer.parseInt(config.getProperty("Status", id, "interval"));
                        Status.this.infoThread = new Thread(Status.this);
                        Status.this.infoThread.start();
                    }
                    catch (NumberFormatException e) {
                        Status.this.error("interval is not a number");
                    }
                }
            }
        });
        this.ports.put("22", "ssh");
        this.ports.put("23", "telnet");
        this.ports.put("25", "smtp");
        this.sPanel = new Panel(new BorderLayout());
        this.host = new Label("Not connected.", 0);
        bus.registerPluginListener(new SocketListener() {
            public void connect(final String addr, final int p) {
                Status.this.address = addr;
                if (Status.this.address == null || Status.this.address.length() == 0) {
                    Status.this.address = "<unknwon host>";
                }
                if (Status.this.ports.get("" + p) != null) {
                    Status.this.port = Status.this.ports.get("" + p);
                }
                else {
                    Status.this.port = "" + p;
                }
                if (Status.this.infoURL == null) {
                    Status.this.host.setText("Trying " + Status.this.address + " " + Status.this.port + " ...");
                }
            }
            
            public void disconnect() {
                if (Status.this.infoURL == null) {
                    Status.this.host.setText("Not connected.");
                }
            }
        });
        this.sPanel.add("Center", this.host);
        this.status = new Label("offline", 1);
        bus.registerPluginListener(new OnlineStatusListener() {
            public void online() {
                Status.this.status.setText("online");
                Status.this.status.setBackground(Color.green);
                if (Status.this.infoURL == null) {
                    Status.this.host.setText("Connected to " + Status.this.address + " " + Status.this.port);
                }
            }
            
            public void offline() {
                Status.this.status.setText("offline");
                Status.this.status.setBackground(Color.red);
                if (Status.this.infoURL == null) {
                    Status.this.host.setText("Not connected.");
                }
            }
        });
        this.sPanel.add("East", this.status);
    }
    
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_1        /* url */
        //     2: new             Ljava/net/URL;
        //     5: dup            
        //     6: aload_0         /* this */
        //     7: getfield        de/mud/jta/plugin/Status.infoURL:Ljava/lang/String;
        //    10: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    13: astore_1        /* url */
        //    14: goto            47
        //    17: astore_2        /* e */
        //    18: aload_0         /* this */
        //    19: new             Ljava/lang/StringBuffer;
        //    22: dup            
        //    23: invokespecial   java/lang/StringBuffer.<init>:()V
        //    26: ldc             "infoURL is not valid: "
        //    28: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    31: aload_2         /* e */
        //    32: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/Object;)Ljava/lang/StringBuffer;
        //    35: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //    38: invokevirtual   de/mud/jta/Plugin.error:(Ljava/lang/String;)V
        //    41: aload_0         /* this */
        //    42: aconst_null    
        //    43: putfield        de/mud/jta/plugin/Status.infoURL:Ljava/lang/String;
        //    46: return         
        //    47: goto            236
        //    50: new             Ljava/io/BufferedReader;
        //    53: dup            
        //    54: new             Ljava/io/InputStreamReader;
        //    57: dup            
        //    58: aload_1         /* url */
        //    59: invokevirtual   java/net/URL.openStream:()Ljava/io/InputStream;
        //    62: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    65: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //    68: astore_2        /* content */
        //    69: goto            148
        //    72: aload_3        
        //    73: ldc             "#"
        //    75: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    78: ifeq            127
        //    81: aload_3        
        //    82: iconst_1       
        //    83: bipush          7
        //    85: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //    88: astore          color
        //    90: aload_3        
        //    91: bipush          8
        //    93: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //    96: astore_3        /* line */
        //    97: aload_0         /* this */
        //    98: getfield        de/mud/jta/plugin/Status.host:Ljava/awt/Label;
        //   101: new             Ljava/lang/StringBuffer;
        //   104: dup            
        //   105: invokespecial   java/lang/StringBuffer.<init>:()V
        //   108: ldc             "#"
        //   110: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   113: aload           color
        //   115: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   118: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   121: invokestatic    java/awt/Color.decode:(Ljava/lang/String;)Ljava/awt/Color;
        //   124: invokevirtual   java/awt/Component.setForeground:(Ljava/awt/Color;)V
        //   127: aload_0         /* this */
        //   128: getfield        de/mud/jta/plugin/Status.host:Ljava/awt/Label;
        //   131: aload_3         /* line */
        //   132: invokevirtual   java/awt/Label.setText:(Ljava/lang/String;)V
        //   135: aload_0         /* this */
        //   136: pop            
        //   137: bipush          10
        //   139: aload_0         /* this */
        //   140: getfield        de/mud/jta/plugin/Status.interval:I
        //   143: imul           
        //   144: i2l            
        //   145: invokestatic    java/lang/Thread.sleep:(J)V
        //   148: aload_2         /* content */
        //   149: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   152: dup            
        //   153: astore_3        /* line */
        //   154: ifnonnull       72
        //   157: goto            167
        //   160: astore_3        /* e */
        //   161: aload_0         /* this */
        //   162: ldc             "error while loading info ..."
        //   164: invokevirtual   de/mud/jta/Plugin.error:(Ljava/lang/String;)V
        //   167: aload_0         /* this */
        //   168: pop            
        //   169: bipush          100
        //   171: aload_0         /* this */
        //   172: getfield        de/mud/jta/plugin/Status.interval:I
        //   175: imul           
        //   176: i2l            
        //   177: invokestatic    java/lang/Thread.sleep:(J)V
        //   180: goto            236
        //   183: astore_2        /* e */
        //   184: aload_0         /* this */
        //   185: new             Ljava/lang/StringBuffer;
        //   188: dup            
        //   189: invokespecial   java/lang/StringBuffer.<init>:()V
        //   192: ldc             "error retrieving info content: "
        //   194: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   197: aload_2         /* e */
        //   198: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/Object;)Ljava/lang/StringBuffer;
        //   201: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   204: invokevirtual   de/mud/jta/Plugin.error:(Ljava/lang/String;)V
        //   207: aload_2         /* e */
        //   208: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   211: aload_0         /* this */
        //   212: getfield        de/mud/jta/plugin/Status.host:Ljava/awt/Label;
        //   215: getstatic       java/awt/Color.red:Ljava/awt/Color;
        //   218: invokevirtual   java/awt/Component.setForeground:(Ljava/awt/Color;)V
        //   221: aload_0         /* this */
        //   222: getfield        de/mud/jta/plugin/Status.host:Ljava/awt/Label;
        //   225: ldc             "error retrieving info content"
        //   227: invokevirtual   java/awt/Label.setText:(Ljava/lang/String;)V
        //   230: aload_0         /* this */
        //   231: aconst_null    
        //   232: putfield        de/mud/jta/plugin/Status.infoURL:Ljava/lang/String;
        //   235: return         
        //   236: aload_1         /* url */
        //   237: ifnull          247
        //   240: aload_0         /* this */
        //   241: getfield        de/mud/jta/plugin/Status.infoThread:Ljava/lang/Thread;
        //   244: ifnonnull       50
        //   247: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  --------------------------
        //  0      248     0     this     Lde/mud/jta/plugin/Status;
        //  2      245     1     url      Ljava/net/URL;
        //  17     30      2     e        Ljava/lang/Exception;
        //  69     111     2     content  Ljava/io/BufferedReader;
        //  97     60      3     line     Ljava/lang/String;
        //  90     37      4     color    Ljava/lang/String;
        //  160    7       3     e        Ljava/io/IOException;
        //  183    53      2     e        Ljava/lang/Exception;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      14     17     47     Ljava/lang/Exception;
        //  69     157    160    167    Ljava/io/IOException;
        //  50     180    183    236    Ljava/lang/Exception;
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
    
    public Component getPluginVisual() {
        return this.sPanel;
    }
    
    public Menu getPluginMenu() {
        return null;
    }
}
