// 
// Decompiled by Procyon v0.5.30
// 

package uk.co.cjswebdesign.multitab;

import javax.swing.event.HyperlinkEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.AppletContext;
import java.net.URL;
import javax.swing.JPanel;
import java.util.logging.Logger;
import javax.swing.event.HyperlinkListener;
import javax.swing.JApplet;

public final class MultiTabJApplet extends JApplet implements HyperlinkListener
{
    static Logger logger;
    private boolean isStandalone;
    static boolean debug;
    private String version;
    private MultiAppletTabPanel tabpanel;
    private JPanel mainPanel;
    private URL doc;
    private String root;
    private String contentURL;
    private AppletContext context;
    private String[] htmlSections;
    private MultitabEditorPane[] pages;
    private static final int LINK_TARGET = 0;
    private static final int DEBUG = 1;
    private static final int TAB_POLICY = 2;
    private static final int TAB_ALIGN = 3;
    private static final int TAB_BACKGROUND_COLOR = 4;
    private static final int TAB_FOREGROUND_COLOR = 5;
    private static final int APPLET_BACKGROUND_COLOR = 6;
    private static final int TITLE = 7;
    private static final int TITLE_JUSTIFICATION = 8;
    private static final int TITLE_POSITION = 9;
    private static final int TITLE_COLOR = 10;
    private String[] parameters;
    private static final String[] PARAMETER_NAMES;
    private static String[] PARAMETER_DEFAULTS;
    
    public MultiTabJApplet() {
        this.isStandalone = false;
        this.version = " 2.0 beta ";
        this.htmlSections = null;
        this.pages = null;
        this.parameters = new String[11];
        MultiTabJApplet.PARAMETER_NAMES[0] = "link_target";
        MultiTabJApplet.PARAMETER_NAMES[1] = "debug";
        MultiTabJApplet.PARAMETER_NAMES[2] = "tabpolicy";
        MultiTabJApplet.PARAMETER_NAMES[3] = "tabalign";
        MultiTabJApplet.PARAMETER_NAMES[4] = "tab_background_color";
        MultiTabJApplet.PARAMETER_NAMES[5] = "tab_foreground_color";
        MultiTabJApplet.PARAMETER_NAMES[6] = "background_color";
        MultiTabJApplet.PARAMETER_NAMES[7] = "title";
        MultiTabJApplet.PARAMETER_NAMES[8] = "title_justification";
        MultiTabJApplet.PARAMETER_NAMES[9] = "title_position";
        MultiTabJApplet.PARAMETER_NAMES[10] = "title_color";
    }
    
    public void init() {
        try {
            final JApplet app = new MultiTabJApplet();
            this.context = this.getAppletContext();
            this.getContentPane().setLayout(new BorderLayout());
            this.logStatus("Initialising parameters...");
            this.initParameters();
            this.setDegugging();
            this.doc = this.getDocumentBase();
            this.root = this.doc.toString();
            this.contentURL = this.root.substring(0, this.root.lastIndexOf("/") + 1) + "pages.html";
            this.logStatus("Reading file...");
            this.htmlSections = this.readFile(this.contentURL);
            this.pages = new MultitabEditorPane[this.htmlSections.length];
            for (int i = 0; i < this.pages.length; ++i) {
                (this.pages[i] = new MultitabEditorPane(this.htmlSections[i])).addHyperlinkListener(this);
            }
            this.logStatus("Creating Tab Panel...");
            (this.tabpanel = new MultiAppletTabPanel(this.pages)).setAppletTabPlacement(this.parameters[3]);
            this.tabpanel.setTabPolicy(this.parameters[2]);
            this.tabpanel.setTabForegroundColor(this.parameters[5]);
            this.tabpanel.setTabBackgroundColor(this.parameters[4]);
            (this.mainPanel = new JPanel(new BorderLayout())).setBackground(Color.decode(this.parameters[6]));
            this.setBackground(Color.decode(this.parameters[6]));
            this.mainPanel.add(this.tabpanel, "Center");
            this.getContentPane().add(this.mainPanel, "Center");
            this.setTitleBorder();
        }
        catch (MultitabException multix) {
            if (MultiTabJApplet.debug) {
                MultiTabJApplet.logger.severe(multix.getMessage());
            }
        }
        catch (Exception e) {
            if (MultiTabJApplet.debug) {
                MultiTabJApplet.logger.severe(e.getMessage());
            }
        }
        finally {
            System.out.println(this.getAppletInfo());
            if (MultiTabJApplet.debug) {
                this.printParameterInfo();
                final StringBuffer buffer = new StringBuffer();
                buffer.append(System.getProperty("line.separator", "\n"));
                System.out.print(buffer.toString());
                System.out.println();
                System.out.print(this);
            }
        }
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    public void destroy() {
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < this.parameters.length; ++i) {
            buffer.append(MultiTabJApplet.PARAMETER_NAMES[i]);
            buffer.append("=");
            buffer.append(this.parameters[i]);
            buffer.append(System.getProperty("line.separator", "\n"));
        }
        return buffer.toString();
    }
    
    public String getAppletInfo() {
        return "Multitab JApplet " + this.version;
    }
    
    public String[][] getParameterInfo() {
        final String[][] params = { { "title", "String", "Text displayed in the surrounding title border" }, { "tabalign", "String", "Controls placement of tabs valid values are top,left,bottom,right" }, { "tabpolicy", "String", "Sets whether tabs should wrap to next line or scroll, values wrap or scroll" }, { "tab_background_color", "Number", "Sets tab background color, values should be octal or hexidecimal number" }, { "tab_foreground_color", "Number", "Sets tab foreground color, values should be octal or hexidecimal number" }, { "background_color", "Number", "Sets applets background color, values should be octal or hexidecimal number" }, { "link_target", "String", "Target where followed link is to be open in" }, { "debug", "boolean", "If extra information should be output, values true or false" }, { "title_justification", "interger", "How the title should be justified, values 0-3. 0=default,1=left,2=center,3=right" }, { "title_position", "Integer", "How the title should be positioned, values 0-6. 0=default, 1=above top, 2=top, 3=below top, 4=above bottom, 5=bottom, 6=below bottom." }, { "title_color", "Number", "The color the title should be, values should be octal or hexidecimal number" } };
        return params;
    }
    
    private void printParameterInfo() {
        System.out.println();
        System.out.println("Parameter Information");
        final String[][] paramaters = this.getParameterInfo();
        for (int i = 0; i < paramaters.length; ++i) {
            System.out.println();
            for (int j = 0; j < 3; ++j) {
                System.out.print(paramaters[i][j] + " ");
            }
        }
        System.out.println();
    }
    
    public String getParameter(final String key, final String def) {
        return this.isStandalone ? System.getProperty(key, def) : ((this.getParameter(key) != null) ? this.getParameter(key) : def);
    }
    
    private void initParameters() {
        for (int i = 0; i < MultiTabJApplet.PARAMETER_NAMES.length; ++i) {
            this.parameters[i] = ((this.getParameter(MultiTabJApplet.PARAMETER_NAMES[i]) != null) ? this.getParameter(MultiTabJApplet.PARAMETER_NAMES[i]) : MultiTabJApplet.PARAMETER_DEFAULTS[i]);
            this.parameters[i] = this.parameters[i].replaceAll("\"", "\\\"").replaceAll("'", "\\'");
            this.logStatus("Setting parameter " + MultiTabJApplet.PARAMETER_NAMES[i] + " to " + this.parameters[i]);
        }
    }
    
    private void setTitleBorder() {
        if (this.parameters[7] != null) {
            if (!this.parameters[7].trim().equals("")) {
                int titlePosition = 0;
                int titleJustification = 0;
                Color titleColor = Color.BLACK;
                final TitledBorder border = new TitledBorder(this.parameters[7]);
                try {
                    titlePosition = Integer.parseInt(this.parameters[9]);
                    if (titlePosition > 6 || titlePosition < 0) {
                        titlePosition = 0;
                    }
                }
                catch (NumberFormatException x) {
                    titlePosition = 0;
                }
                finally {
                    border.setTitlePosition(titlePosition);
                }
                try {
                    titleJustification = Integer.parseInt(this.parameters[8]);
                    if (titleJustification > 3 || titleJustification < 0) {
                        titleJustification = 0;
                    }
                }
                catch (NumberFormatException x) {
                    titleJustification = 0;
                }
                finally {
                    border.setTitleJustification(titleJustification);
                }
                try {
                    titleColor = Color.decode(this.parameters[10]);
                }
                catch (NumberFormatException x) {
                    titleColor = Color.BLACK;
                }
                finally {
                    border.setTitleColor(titleColor);
                }
                this.mainPanel.setBorder(border);
            }
        }
        else {
            this.mainPanel.setBorder(BorderFactory.createEmptyBorder());
        }
    }
    
    private void setDegugging() {
        if (this.parameters[1].equalsIgnoreCase("true")) {
            MultiTabJApplet.debug = true;
        }
        else {
            MultiTabJApplet.debug = false;
        }
    }
    
    private String[] readFile(final String url) throws MultitabException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_2        /* pages */
        //     2: aconst_null    
        //     3: astore_3        /* retVal */
        //     4: aconst_null    
        //     5: astore          contentsOfFile
        //     7: aconst_null    
        //     8: astore          pagesURL
        //    10: aconst_null    
        //    11: astore          conn
        //    13: aconst_null    
        //    14: astore          sb
        //    16: iconst_0       
        //    17: istore          len
        //    19: aconst_null    
        //    20: astore          in
        //    22: aconst_null    
        //    23: astore          buffer
        //    25: aconst_null    
        //    26: astore          reader
        //    28: new             Ljava/net/URL;
        //    31: dup            
        //    32: aload_1         /* url */
        //    33: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    36: astore          pagesURL
        //    38: aload           pagesURL
        //    40: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    43: astore          conn
        //    45: new             Ljava/lang/StringBuffer;
        //    48: dup            
        //    49: invokespecial   java/lang/StringBuffer.<init>:()V
        //    52: astore          sb
        //    54: aload           conn
        //    56: invokevirtual   java/net/URLConnection.getContentLength:()I
        //    59: istore          len
        //    61: aload           conn
        //    63: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
        //    66: astore          in
        //    68: new             Ljava/io/BufferedInputStream;
        //    71: dup            
        //    72: aload           in
        //    74: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    77: astore          buffer
        //    79: new             Ljava/io/InputStreamReader;
        //    82: dup            
        //    83: aload           buffer
        //    85: ldc             "ISO-8859-1"
        //    87: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;Ljava/lang/String;)V
        //    90: astore          reader
        //    92: goto            104
        //    95: aload           sb
        //    97: iload           12
        //    99: i2c            
        //   100: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   103: pop            
        //   104: aload           reader
        //   106: invokevirtual   java/io/InputStreamReader.read:()I
        //   109: dup            
        //   110: istore          c
        //   112: iconst_m1      
        //   113: if_icmpne       95
        //   116: aload           sb
        //   118: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   121: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   124: astore          contentsOfFile
        //   126: aload_0         /* this */
        //   127: aload           contentsOfFile
        //   129: invokespecial   uk/co/cjswebdesign/multitab/MultiTabJApplet.replaceDocType:(Ljava/lang/String;)Ljava/lang/String;
        //   132: astore          contentsOfFile
        //   134: aload_0         /* this */
        //   135: aload           contentsOfFile
        //   137: invokespecial   uk/co/cjswebdesign/multitab/MultiTabJApplet.replaceHTMLTag:(Ljava/lang/String;)Ljava/lang/String;
        //   140: astore          contentsOfFile
        //   142: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   145: ldc             "*********** Contents of file ***************"
        //   147: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   150: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   153: ldc             ""
        //   155: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   158: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   161: aload           contentsOfFile
        //   163: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   166: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   169: ldc             ""
        //   171: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   174: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   177: ldc             "*********** Contents of file ***************"
        //   179: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   182: aload           contentsOfFile
        //   184: ldc             "<html>"
        //   186: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //   189: astore_2        /* pages */
        //   190: aload_2         /* pages */
        //   191: arraylength    
        //   192: anewarray       Ljava/lang/String;
        //   195: astore_3        /* retVal */
        //   196: iconst_0       
        //   197: istore          x
        //   199: goto            296
        //   202: aload_3         /* retVal */
        //   203: iload           x
        //   205: new             Ljava/lang/StringBuffer;
        //   208: dup            
        //   209: invokespecial   java/lang/StringBuffer.<init>:()V
        //   212: ldc             "<html>"
        //   214: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   217: aload_2         /* pages */
        //   218: iload           x
        //   220: aaload         
        //   221: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   224: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   227: aastore        
        //   228: new             Ljava/lang/StringBuffer;
        //   231: dup            
        //   232: aload_3         /* retVal */
        //   233: iload           x
        //   235: aaload         
        //   236: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   239: astore          HTMLbuffer
        //   241: aload           HTMLbuffer
        //   243: aload           HTMLbuffer
        //   245: ldc             "<head>"
        //   247: invokevirtual   java/lang/StringBuffer.indexOf:(Ljava/lang/String;)I
        //   250: bipush          6
        //   252: iadd           
        //   253: new             Ljava/lang/StringBuffer;
        //   256: dup            
        //   257: invokespecial   java/lang/StringBuffer.<init>:()V
        //   260: ldc             "<base href=\""
        //   262: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   265: aload_0         /* this */
        //   266: getfield        uk/co/cjswebdesign/multitab/MultiTabJApplet.contentURL:Ljava/lang/String;
        //   269: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   272: ldc             "\" >"
        //   274: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   277: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   280: invokevirtual   java/lang/StringBuffer.insert:(ILjava/lang/String;)Ljava/lang/StringBuffer;
        //   283: pop            
        //   284: aload_3         /* retVal */
        //   285: iload           x
        //   287: aload           HTMLbuffer
        //   289: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   292: aastore        
        //   293: iinc            x, 1
        //   296: iload           x
        //   298: aload_2         /* pages */
        //   299: arraylength    
        //   300: if_icmplt       202
        //   303: aload_3         /* retVal */
        //   304: astore          14
        //   306: aload           reader
        //   308: ifnull          319
        //   311: aload           reader
        //   313: invokevirtual   java/io/InputStreamReader.close:()V
        //   316: aconst_null    
        //   317: astore          reader
        //   319: aload           buffer
        //   321: ifnull          332
        //   324: aload           buffer
        //   326: invokevirtual   java/io/BufferedInputStream.close:()V
        //   329: aconst_null    
        //   330: astore          buffer
        //   332: aload           in
        //   334: ifnull          345
        //   337: aload           in
        //   339: invokevirtual   java/io/InputStream.close:()V
        //   342: aconst_null    
        //   343: astore          in
        //   345: goto            372
        //   348: astore          iox
        //   350: aload_0         /* this */
        //   351: pop            
        //   352: getstatic       uk/co/cjswebdesign/multitab/MultiTabJApplet.debug:Z
        //   355: ifeq            369
        //   358: getstatic       uk/co/cjswebdesign/multitab/MultiTabJApplet.logger:Ljava/util/logging/Logger;
        //   361: aload           iox
        //   363: invokevirtual   java/io/IOException.getLocalizedMessage:()Ljava/lang/String;
        //   366: invokevirtual   java/util/logging/Logger.warning:(Ljava/lang/String;)V
        //   369: goto            372
        //   372: aload           14
        //   374: areturn        
        //   375: astore          murlx
        //   377: aload_0         /* this */
        //   378: pop            
        //   379: getstatic       uk/co/cjswebdesign/multitab/MultiTabJApplet.debug:Z
        //   382: ifeq            396
        //   385: getstatic       uk/co/cjswebdesign/multitab/MultiTabJApplet.logger:Ljava/util/logging/Logger;
        //   388: aload           murlx
        //   390: invokevirtual   java/net/MalformedURLException.getLocalizedMessage:()Ljava/lang/String;
        //   393: invokevirtual   java/util/logging/Logger.severe:(Ljava/lang/String;)V
        //   396: new             Luk/co/cjswebdesign/multitab/MultiTabJApplet$MultitabException;
        //   399: dup            
        //   400: aload_0         /* this */
        //   401: ldc             "Unable to create URL for pages.html "
        //   403: aload           murlx
        //   405: invokespecial   uk/co/cjswebdesign/multitab/MultiTabJApplet$MultitabException.<init>:(Luk/co/cjswebdesign/multitab/MultiTabJApplet;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   408: athrow         
        //   409: astore          x
        //   411: aload_0         /* this */
        //   412: pop            
        //   413: getstatic       uk/co/cjswebdesign/multitab/MultiTabJApplet.debug:Z
        //   416: ifeq            430
        //   419: getstatic       uk/co/cjswebdesign/multitab/MultiTabJApplet.logger:Ljava/util/logging/Logger;
        //   422: aload           x
        //   424: invokevirtual   java/lang/Exception.getLocalizedMessage:()Ljava/lang/String;
        //   427: invokevirtual   java/util/logging/Logger.severe:(Ljava/lang/String;)V
        //   430: new             Luk/co/cjswebdesign/multitab/MultiTabJApplet$MultitabException;
        //   433: dup            
        //   434: aload_0         /* this */
        //   435: ldc             "Problems reading pages.html "
        //   437: aload           x
        //   439: invokespecial   uk/co/cjswebdesign/multitab/MultiTabJApplet$MultitabException.<init>:(Luk/co/cjswebdesign/multitab/MultiTabJApplet;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   442: athrow         
        //   443: astore          16
        //   445: aload           reader
        //   447: ifnull          458
        //   450: aload           reader
        //   452: invokevirtual   java/io/InputStreamReader.close:()V
        //   455: aconst_null    
        //   456: astore          reader
        //   458: aload           buffer
        //   460: ifnull          471
        //   463: aload           buffer
        //   465: invokevirtual   java/io/BufferedInputStream.close:()V
        //   468: aconst_null    
        //   469: astore          buffer
        //   471: aload           in
        //   473: ifnull          484
        //   476: aload           in
        //   478: invokevirtual   java/io/InputStream.close:()V
        //   481: aconst_null    
        //   482: astore          in
        //   484: goto            511
        //   487: astore          iox
        //   489: aload_0         /* this */
        //   490: pop            
        //   491: getstatic       uk/co/cjswebdesign/multitab/MultiTabJApplet.debug:Z
        //   494: ifeq            508
        //   497: getstatic       uk/co/cjswebdesign/multitab/MultiTabJApplet.logger:Ljava/util/logging/Logger;
        //   500: aload           iox
        //   502: invokevirtual   java/io/IOException.getLocalizedMessage:()Ljava/lang/String;
        //   505: invokevirtual   java/util/logging/Logger.warning:(Ljava/lang/String;)V
        //   508: goto            511
        //   511: aload           16
        //   513: athrow         
        //    Exceptions:
        //  throws uk.co.cjswebdesign.multitab.MultiTabJApplet.MultitabException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name            Signature
        //  -----  ------  ----  --------------  ---------------------------------------------
        //  0      514     0     this            Luk/co/cjswebdesign/multitab/MultiTabJApplet;
        //  0      514     1     url             Ljava/lang/String;
        //  2      512     2     pages           [Ljava/lang/String;
        //  4      510     3     retVal          [Ljava/lang/String;
        //  7      507     4     contentsOfFile  Ljava/lang/String;
        //  10     504     5     pagesURL        Ljava/net/URL;
        //  13     501     6     conn            Ljava/net/URLConnection;
        //  16     498     7     sb              Ljava/lang/StringBuffer;
        //  19     495     8     len             I
        //  22     492     9     in              Ljava/io/InputStream;
        //  25     489     10    buffer          Ljava/io/BufferedInputStream;
        //  28     486     11    reader          Ljava/io/InputStreamReader;
        //  112    263     12    c               I
        //  199    176     13    x               I
        //  241    52      14    HTMLbuffer      Ljava/lang/StringBuffer;
        //  350    22      15    iox             Ljava/io/IOException;
        //  377    137     12    murlx           Ljava/net/MalformedURLException;
        //  411    103     13    x               Ljava/lang/Exception;
        //  489    22      17    iox             Ljava/io/IOException;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  306    345    348    372    Ljava/io/IOException;
        //  28     306    375    409    Ljava/net/MalformedURLException;
        //  28     306    409    443    Ljava/lang/Exception;
        //  28     306    443    514    Any
        //  445    484    487    511    Ljava/io/IOException;
        //  375    445    443    514    Any
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
    
    void logStatus(final String msg) {
        if (MultiTabJApplet.debug) {
            MultiTabJApplet.logger.info(msg);
        }
        this.context.showStatus(msg);
    }
    
    private String replaceDocType(final String html) {
        String temp = html;
        final String regexp = "((?i)(<!DOCTYPE HTML (PUBLIC|SYSTEM))+(.)*(\\s|\\z)*(.)*(\">))";
        final Pattern p = Pattern.compile(regexp);
        final Matcher m = p.matcher(temp);
        synchronized (m) {
            temp = m.replaceAll("");
        }
        return temp;
    }
    
    private String replaceHTMLTag(final String html) {
        String temp = html;
        final String regexp = "((?i)(<html){1}(.)*(>){1})";
        final Pattern p = Pattern.compile(regexp);
        final Matcher m = p.matcher(temp);
        synchronized (m) {
            temp = m.replaceAll("<html>");
        }
        return temp;
    }
    
    public void hyperlinkUpdate(final HyperlinkEvent e) {
        final URL url = e.getURL();
        final HyperlinkEvent.EventType type = e.getEventType();
        final Object o = e.getSource();
        if (type == HyperlinkEvent.EventType.ACTIVATED) {
            try {
                this.context.showDocument(url, this.parameters[0]);
            }
            catch (Exception ex) {
                this.context.showStatus("Unable to open URL: " + url);
                if (MultiTabJApplet.debug) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
    
    static {
        MultiTabJApplet.logger = Logger.getAnonymousLogger();
        MultiTabJApplet.debug = false;
        PARAMETER_NAMES = new String[11];
        MultiTabJApplet.PARAMETER_DEFAULTS = new String[] { "_top", "false", "scroll", "top", "#C0C0C0", "#000000", "#ffffff", "", "default", "default", "#000000" };
    }
    
    class MultitabException extends Exception
    {
        MultitabException() {
            super("MultitabException");
        }
        
        MultitabException(final String msg) {
            super("MultitabException: " + msg);
        }
        
        MultitabException(final String msg, final Throwable cause) {
            super("MultitabException: " + msg, cause);
        }
    }
}
