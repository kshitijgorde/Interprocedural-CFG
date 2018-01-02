// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.xml;

import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.EntityResolver;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.Reader;
import javax.xml.parsers.FactoryConfigurationError;
import org.xml.sax.InputSource;
import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import org.apache.log4j.LogManager;
import java.lang.reflect.Method;
import org.apache.log4j.Level;
import org.apache.log4j.or.RendererMap;
import org.apache.log4j.spi.RendererSupport;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.config.PropertySetter;
import org.apache.log4j.helpers.Loader;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.log4j.helpers.LogLog;
import org.w3c.dom.Element;
import org.apache.log4j.Appender;
import org.w3c.dom.Document;
import org.apache.log4j.spi.LoggerRepository;
import java.util.Properties;
import java.util.Hashtable;
import org.apache.log4j.spi.Configurator;

public class DOMConfigurator implements Configurator
{
    static final String CONFIGURATION_TAG = "log4j:configuration";
    static final String OLD_CONFIGURATION_TAG = "configuration";
    static final String RENDERER_TAG = "renderer";
    static final String APPENDER_TAG = "appender";
    static final String APPENDER_REF_TAG = "appender-ref";
    static final String PARAM_TAG = "param";
    static final String LAYOUT_TAG = "layout";
    static final String CATEGORY = "category";
    static final String LOGGER = "logger";
    static final String LOGGER_REF = "logger-ref";
    static final String CATEGORY_FACTORY_TAG = "categoryFactory";
    static final String NAME_ATTR = "name";
    static final String CLASS_ATTR = "class";
    static final String VALUE_ATTR = "value";
    static final String ROOT_TAG = "root";
    static final String ROOT_REF = "root-ref";
    static final String LEVEL_TAG = "level";
    static final String PRIORITY_TAG = "priority";
    static final String FILTER_TAG = "filter";
    static final String ERROR_HANDLER_TAG = "errorHandler";
    static final String REF_ATTR = "ref";
    static final String ADDITIVITY_ATTR = "additivity";
    static final String THRESHOLD_ATTR = "threshold";
    static final String CONFIG_DEBUG_ATTR = "configDebug";
    static final String INTERNAL_DEBUG_ATTR = "debug";
    static final String RENDERING_CLASS_ATTR = "renderingClass";
    static final String RENDERED_CLASS_ATTR = "renderedClass";
    static final String EMPTY_STR = "";
    static final Class[] ONE_STRING_PARAM;
    static final String dbfKey = "javax.xml.parsers.DocumentBuilderFactory";
    Hashtable appenderBag;
    Properties props;
    LoggerRepository repository;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$apache$log4j$spi$ErrorHandler;
    static /* synthetic */ Class class$org$apache$log4j$spi$Filter;
    static /* synthetic */ Class class$org$apache$log4j$spi$LoggerFactory;
    
    public DOMConfigurator() {
        this.appenderBag = new Hashtable();
    }
    
    protected Appender findAppenderByName(final Document doc, final String appenderName) {
        Appender appender = this.appenderBag.get(appenderName);
        if (appender != null) {
            return appender;
        }
        Element element = null;
        final NodeList list = doc.getElementsByTagName("appender");
        for (int t = 0; t < list.getLength(); ++t) {
            final Node node = list.item(t);
            final NamedNodeMap map = node.getAttributes();
            final Node attrNode = map.getNamedItem("name");
            if (appenderName.equals(attrNode.getNodeValue())) {
                element = (Element)node;
                break;
            }
        }
        if (element == null) {
            LogLog.error("No appender named [" + appenderName + "] could be found.");
            return null;
        }
        appender = this.parseAppender(element);
        this.appenderBag.put(appenderName, appender);
        return appender;
    }
    
    protected Appender findAppenderByReference(final Element appenderRef) {
        final String appenderName = this.subst(appenderRef.getAttribute("ref"));
        final Document doc = appenderRef.getOwnerDocument();
        return this.findAppenderByName(doc, appenderName);
    }
    
    protected Appender parseAppender(final Element appenderElement) {
        final String className = this.subst(appenderElement.getAttribute("class"));
        LogLog.debug("Class name: [" + className + ']');
        try {
            final Object instance = Loader.loadClass(className).newInstance();
            final Appender appender = (Appender)instance;
            final PropertySetter propSetter = new PropertySetter(appender);
            appender.setName(this.subst(appenderElement.getAttribute("name")));
            final NodeList children = appenderElement.getChildNodes();
            for (int length = children.getLength(), loop = 0; loop < length; ++loop) {
                final Node currentNode = children.item(loop);
                if (currentNode.getNodeType() == 1) {
                    final Element currentElement = (Element)currentNode;
                    if (currentElement.getTagName().equals("param")) {
                        this.setParameter(currentElement, propSetter);
                    }
                    else if (currentElement.getTagName().equals("layout")) {
                        appender.setLayout(this.parseLayout(currentElement));
                    }
                    else if (currentElement.getTagName().equals("filter")) {
                        this.parseFilters(currentElement, appender);
                    }
                    else if (currentElement.getTagName().equals("errorHandler")) {
                        this.parseErrorHandler(currentElement, appender);
                    }
                    else if (currentElement.getTagName().equals("appender-ref")) {
                        final String refName = this.subst(currentElement.getAttribute("ref"));
                        if (appender instanceof AppenderAttachable) {
                            final AppenderAttachable aa = (AppenderAttachable)appender;
                            LogLog.debug("Attaching appender named [" + refName + "] to appender named [" + appender.getName() + "].");
                            aa.addAppender(this.findAppenderByReference(currentElement));
                        }
                        else {
                            LogLog.error("Requesting attachment of appender named [" + refName + "] to appender named [" + appender.getName() + "] which does not implement org.apache.log4j.spi.AppenderAttachable.");
                        }
                    }
                }
            }
            propSetter.activate();
            return appender;
        }
        catch (Exception oops) {
            LogLog.error("Could not create an Appender. Reported error follows.", oops);
            return null;
        }
    }
    
    protected void parseErrorHandler(final Element element, final Appender appender) {
        final ErrorHandler eh = (ErrorHandler)OptionConverter.instantiateByClassName(this.subst(element.getAttribute("class")), (DOMConfigurator.class$org$apache$log4j$spi$ErrorHandler == null) ? (DOMConfigurator.class$org$apache$log4j$spi$ErrorHandler = class$("org.apache.log4j.spi.ErrorHandler")) : DOMConfigurator.class$org$apache$log4j$spi$ErrorHandler, null);
        if (eh != null) {
            eh.setAppender(appender);
            final PropertySetter propSetter = new PropertySetter(eh);
            final NodeList children = element.getChildNodes();
            for (int length = children.getLength(), loop = 0; loop < length; ++loop) {
                final Node currentNode = children.item(loop);
                if (currentNode.getNodeType() == 1) {
                    final Element currentElement = (Element)currentNode;
                    final String tagName = currentElement.getTagName();
                    if (tagName.equals("param")) {
                        this.setParameter(currentElement, propSetter);
                    }
                    else if (tagName.equals("appender-ref")) {
                        eh.setBackupAppender(this.findAppenderByReference(currentElement));
                    }
                    else if (tagName.equals("logger-ref")) {
                        final String loggerName = currentElement.getAttribute("ref");
                        final Logger logger = this.repository.getLogger(loggerName);
                        eh.setLogger(logger);
                    }
                    else if (tagName.equals("root-ref")) {
                        final Logger root = this.repository.getRootLogger();
                        eh.setLogger(root);
                    }
                }
            }
            propSetter.activate();
            appender.setErrorHandler(eh);
        }
    }
    
    protected void parseFilters(final Element element, final Appender appender) {
        final String clazz = this.subst(element.getAttribute("class"));
        final Filter filter = (Filter)OptionConverter.instantiateByClassName(clazz, (DOMConfigurator.class$org$apache$log4j$spi$Filter == null) ? (DOMConfigurator.class$org$apache$log4j$spi$Filter = class$("org.apache.log4j.spi.Filter")) : DOMConfigurator.class$org$apache$log4j$spi$Filter, null);
        if (filter != null) {
            final PropertySetter propSetter = new PropertySetter(filter);
            final NodeList children = element.getChildNodes();
            for (int length = children.getLength(), loop = 0; loop < length; ++loop) {
                final Node currentNode = children.item(loop);
                if (currentNode.getNodeType() == 1) {
                    final Element currentElement = (Element)currentNode;
                    final String tagName = currentElement.getTagName();
                    if (tagName.equals("param")) {
                        this.setParameter(currentElement, propSetter);
                    }
                }
            }
            propSetter.activate();
            LogLog.debug("Adding filter of type [" + filter.getClass() + "] to appender named [" + appender.getName() + "].");
            appender.addFilter(filter);
        }
    }
    
    protected void parseCategory(final Element loggerElement) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: aload_1         /* loggerElement */
        //     2: ldc             "name"
        //     4: invokeinterface org/w3c/dom/Element.getAttribute:(Ljava/lang/String;)Ljava/lang/String;
        //     9: invokevirtual   org/apache/log4j/xml/DOMConfigurator.subst:(Ljava/lang/String;)Ljava/lang/String;
        //    12: astore_2        /* catName */
        //    13: aload_0         /* this */
        //    14: aload_1         /* loggerElement */
        //    15: ldc             "class"
        //    17: invokeinterface org/w3c/dom/Element.getAttribute:(Ljava/lang/String;)Ljava/lang/String;
        //    22: invokevirtual   org/apache/log4j/xml/DOMConfigurator.subst:(Ljava/lang/String;)Ljava/lang/String;
        //    25: astore          className
        //    27: ldc             ""
        //    29: aload           className
        //    31: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    34: ifeq            56
        //    37: ldc             "Retreiving an instance of org.apache.log4j.Logger."
        //    39: invokestatic    org/apache/log4j/helpers/LogLog.debug:(Ljava/lang/String;)V
        //    42: aload_0         /* this */
        //    43: getfield        org/apache/log4j/xml/DOMConfigurator.repository:Lorg/apache/log4j/spi/LoggerRepository;
        //    46: aload_2         /* catName */
        //    47: invokeinterface org/apache/log4j/spi/LoggerRepository.getLogger:(Ljava/lang/String;)Lorg/apache/log4j/Logger;
        //    52: astore_3        /* cat */
        //    53: goto            156
        //    56: new             Ljava/lang/StringBuffer;
        //    59: dup            
        //    60: invokespecial   java/lang/StringBuffer.<init>:()V
        //    63: ldc             "Desired logger sub-class: ["
        //    65: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    68: aload           className
        //    70: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    73: bipush          93
        //    75: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //    78: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //    81: invokestatic    org/apache/log4j/helpers/LogLog.debug:(Ljava/lang/String;)V
        //    84: aload           className
        //    86: invokestatic    org/apache/log4j/helpers/Loader.loadClass:(Ljava/lang/String;)Ljava/lang/Class;
        //    89: astore          clazz
        //    91: aload           clazz
        //    93: ldc             "getLogger"
        //    95: getstatic       org/apache/log4j/xml/DOMConfigurator.ONE_STRING_PARAM:[Ljava/lang/Class;
        //    98: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //   101: astore          getInstanceMethod
        //   103: aload           getInstanceMethod
        //   105: aconst_null    
        //   106: iconst_1       
        //   107: anewarray       Ljava/lang/Object;
        //   110: dup            
        //   111: iconst_0       
        //   112: aload_2         /* catName */
        //   113: aastore        
        //   114: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //   117: checkcast       Lorg/apache/log4j/Logger;
        //   120: astore_3        /* cat */
        //   121: goto            156
        //   124: astore          oops
        //   126: new             Ljava/lang/StringBuffer;
        //   129: dup            
        //   130: invokespecial   java/lang/StringBuffer.<init>:()V
        //   133: ldc             "Could not retrieve category ["
        //   135: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   138: aload_2         /* catName */
        //   139: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   142: ldc             "]. Reported error follows."
        //   144: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   147: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   150: aload           oops
        //   152: invokestatic    org/apache/log4j/helpers/LogLog.error:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   155: return         
        //   156: aload_3        
        //   157: dup            
        //   158: astore          5
        //   160: monitorenter   
        //   161: aload_0         /* this */
        //   162: aload_1         /* loggerElement */
        //   163: ldc             "additivity"
        //   165: invokeinterface org/w3c/dom/Element.getAttribute:(Ljava/lang/String;)Ljava/lang/String;
        //   170: invokevirtual   org/apache/log4j/xml/DOMConfigurator.subst:(Ljava/lang/String;)Ljava/lang/String;
        //   173: iconst_1       
        //   174: invokestatic    org/apache/log4j/helpers/OptionConverter.toBoolean:(Ljava/lang/String;Z)Z
        //   177: istore          additivity
        //   179: new             Ljava/lang/StringBuffer;
        //   182: dup            
        //   183: invokespecial   java/lang/StringBuffer.<init>:()V
        //   186: ldc             "Setting ["
        //   188: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   191: aload_3        
        //   192: invokevirtual   org/apache/log4j/Category.getName:()Ljava/lang/String;
        //   195: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   198: ldc             "] additivity to ["
        //   200: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   203: iload           additivity
        //   205: invokevirtual   java/lang/StringBuffer.append:(Z)Ljava/lang/StringBuffer;
        //   208: ldc             "]."
        //   210: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   213: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   216: invokestatic    org/apache/log4j/helpers/LogLog.debug:(Ljava/lang/String;)V
        //   219: aload_3        
        //   220: iload           additivity
        //   222: invokevirtual   org/apache/log4j/Category.setAdditivity:(Z)V
        //   225: aload_0         /* this */
        //   226: aload_1         /* loggerElement */
        //   227: aload_3        
        //   228: iconst_0       
        //   229: invokevirtual   org/apache/log4j/xml/DOMConfigurator.parseChildrenOfLoggerElement:(Lorg/w3c/dom/Element;Lorg/apache/log4j/Logger;Z)V
        //   232: aload           5
        //   234: monitorexit    
        //   235: goto            246
        //   238: astore          7
        //   240: aload           5
        //   242: monitorexit    
        //   243: aload           7
        //   245: athrow         
        //   246: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name               Signature
        //  -----  ------  ----  -----------------  --------------------------------------
        //  53     3       3     cat                Lorg/apache/log4j/Logger;
        //  91     30      5     clazz              Ljava/lang/Class;
        //  103    18      6     getInstanceMethod  Ljava/lang/reflect/Method;
        //  121    3       3     cat                Lorg/apache/log4j/Logger;
        //  126    30      5     oops               Ljava/lang/Exception;
        //  179    53      6     additivity         Z
        //  0      247     0     this               Lorg/apache/log4j/xml/DOMConfigurator;
        //  0      247     1     loggerElement      Lorg/w3c/dom/Element;
        //  13     234     2     catName            Ljava/lang/String;
        //  27     220     4     className          Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  84     121    124    156    Ljava/lang/Exception;
        //  161    235    238    246    Any
        //  238    243    238    246    Any
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
    
    protected void parseCategoryFactory(final Element factoryElement) {
        final String className = this.subst(factoryElement.getAttribute("class"));
        if ("".equals(className)) {
            LogLog.error("Category Factory tag class attribute not found.");
            LogLog.debug("No Category Factory configured.");
        }
        else {
            LogLog.debug("Desired category factory: [" + className + ']');
            final Object catFactory = OptionConverter.instantiateByClassName(className, (DOMConfigurator.class$org$apache$log4j$spi$LoggerFactory == null) ? (DOMConfigurator.class$org$apache$log4j$spi$LoggerFactory = class$("org.apache.log4j.spi.LoggerFactory")) : DOMConfigurator.class$org$apache$log4j$spi$LoggerFactory, null);
            final PropertySetter propSetter = new PropertySetter(catFactory);
            Element currentElement = null;
            Node currentNode = null;
            final NodeList children = factoryElement.getChildNodes();
            for (int length = children.getLength(), loop = 0; loop < length; ++loop) {
                currentNode = children.item(loop);
                if (currentNode.getNodeType() == 1) {
                    currentElement = (Element)currentNode;
                    if (currentElement.getTagName().equals("param")) {
                        this.setParameter(currentElement, propSetter);
                    }
                }
            }
        }
    }
    
    protected void parseRoot(final Element rootElement) {
        final Logger root = this.repository.getRootLogger();
        synchronized (root) {
            this.parseChildrenOfLoggerElement(rootElement, root, true);
        }
    }
    
    protected void parseChildrenOfLoggerElement(final Element catElement, final Logger cat, final boolean isRoot) {
        final PropertySetter propSetter = new PropertySetter(cat);
        cat.removeAllAppenders();
        final NodeList children = catElement.getChildNodes();
        for (int length = children.getLength(), loop = 0; loop < length; ++loop) {
            final Node currentNode = children.item(loop);
            if (currentNode.getNodeType() == 1) {
                final Element currentElement = (Element)currentNode;
                final String tagName = currentElement.getTagName();
                if (tagName.equals("appender-ref")) {
                    final Element appenderRef = (Element)currentNode;
                    final Appender appender = this.findAppenderByReference(appenderRef);
                    final String refName = this.subst(appenderRef.getAttribute("ref"));
                    if (appender != null) {
                        LogLog.debug("Adding appender named [" + refName + "] to category [" + cat.getName() + "].");
                    }
                    else {
                        LogLog.debug("Appender named [" + refName + "] not found.");
                    }
                    cat.addAppender(appender);
                }
                else if (tagName.equals("level")) {
                    this.parseLevel(currentElement, cat, isRoot);
                }
                else if (tagName.equals("priority")) {
                    this.parseLevel(currentElement, cat, isRoot);
                }
                else if (tagName.equals("param")) {
                    this.setParameter(currentElement, propSetter);
                }
            }
        }
        propSetter.activate();
    }
    
    protected Layout parseLayout(final Element layout_element) {
        final String className = this.subst(layout_element.getAttribute("class"));
        LogLog.debug("Parsing layout of class: \"" + className + "\"");
        try {
            final Object instance = Loader.loadClass(className).newInstance();
            final Layout layout = (Layout)instance;
            final PropertySetter propSetter = new PropertySetter(layout);
            final NodeList params = layout_element.getChildNodes();
            for (int length = params.getLength(), loop = 0; loop < length; ++loop) {
                final Node currentNode = params.item(loop);
                if (currentNode.getNodeType() == 1) {
                    final Element currentElement = (Element)currentNode;
                    final String tagName = currentElement.getTagName();
                    if (tagName.equals("param")) {
                        this.setParameter(currentElement, propSetter);
                    }
                }
            }
            propSetter.activate();
            return layout;
        }
        catch (Exception oops) {
            LogLog.error("Could not create the Layout. Reported error follows.", oops);
            return null;
        }
    }
    
    protected void parseRenderer(final Element element) {
        final String renderingClass = this.subst(element.getAttribute("renderingClass"));
        final String renderedClass = this.subst(element.getAttribute("renderedClass"));
        if (this.repository instanceof RendererSupport) {
            RendererMap.addRenderer((RendererSupport)this.repository, renderedClass, renderingClass);
        }
    }
    
    protected void parseLevel(final Element element, final Logger logger, final boolean isRoot) {
        String catName = logger.getName();
        if (isRoot) {
            catName = "root";
        }
        final String priStr = this.subst(element.getAttribute("value"));
        LogLog.debug("Level value for " + catName + " is  [" + priStr + "].");
        if ("inherited".equalsIgnoreCase(priStr) || "null".equalsIgnoreCase(priStr)) {
            if (isRoot) {
                LogLog.error("Root level cannot be inherited. Ignoring directive.");
            }
            else {
                logger.setLevel(null);
            }
        }
        else {
            final String className = this.subst(element.getAttribute("class"));
            if ("".equals(className)) {
                logger.setLevel(OptionConverter.toLevel(priStr, Level.DEBUG));
            }
            else {
                LogLog.debug("Desired Level sub-class: [" + className + ']');
                try {
                    final Class clazz = Loader.loadClass(className);
                    final Method toLevelMethod = clazz.getMethod("toLevel", (Class[])DOMConfigurator.ONE_STRING_PARAM);
                    final Level pri = (Level)toLevelMethod.invoke(null, priStr);
                    logger.setLevel(pri);
                }
                catch (Exception oops) {
                    LogLog.error("Could not create level [" + priStr + "]. Reported error follows.", oops);
                    return;
                }
            }
        }
        LogLog.debug(catName + " level set to " + logger.getLevel());
    }
    
    protected void setParameter(final Element elem, final PropertySetter propSetter) {
        final String name = this.subst(elem.getAttribute("name"));
        String value = elem.getAttribute("value");
        value = this.subst(OptionConverter.convertSpecialChars(value));
        propSetter.setProperty(name, value);
    }
    
    public static void configure(final Element element) {
        final DOMConfigurator configurator = new DOMConfigurator();
        configurator.doConfigure(element, LogManager.getLoggerRepository());
    }
    
    public static void configureAndWatch(final String configFilename) {
        configureAndWatch(configFilename, 60000L);
    }
    
    public static void configureAndWatch(final String configFilename, final long delay) {
        final XMLWatchdog xdog = new XMLWatchdog(configFilename);
        xdog.setDelay(delay);
        xdog.start();
    }
    
    public void doConfigure(final String filename, final LoggerRepository repository) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);
            this.doConfigure(fis, repository);
        }
        catch (IOException e) {
            LogLog.error("Could not open [" + filename + "].", e);
        }
        finally {
            if (fis != null) {
                try {
                    fis.close();
                }
                catch (IOException e2) {
                    LogLog.error("Could not close [" + filename + "].", e2);
                }
            }
        }
    }
    
    public void doConfigure(final URL url, final LoggerRepository repository) {
        try {
            this.doConfigure(url.openStream(), repository);
        }
        catch (IOException e) {
            LogLog.error("Could not open [" + url + "].", e);
        }
    }
    
    public void doConfigure(final InputStream inputStream, final LoggerRepository repository) throws FactoryConfigurationError {
        this.doConfigure(new InputSource(inputStream), repository);
    }
    
    public void doConfigure(final Reader reader, final LoggerRepository repository) throws FactoryConfigurationError {
        this.doConfigure(new InputSource(reader), repository);
    }
    
    protected void doConfigure(final InputSource inputSource, final LoggerRepository repository) throws FactoryConfigurationError {
        DocumentBuilderFactory dbf = null;
        this.repository = repository;
        try {
            LogLog.debug("System property is :" + OptionConverter.getSystemProperty("javax.xml.parsers.DocumentBuilderFactory", null));
            dbf = DocumentBuilderFactory.newInstance();
            LogLog.debug("Standard DocumentBuilderFactory search succeded.");
            LogLog.debug("DocumentBuilderFactory is: " + dbf.getClass().getName());
        }
        catch (FactoryConfigurationError fce) {
            final Exception e = fce.getException();
            LogLog.debug("Could not instantiate a DocumentBuilderFactory.", e);
            throw fce;
        }
        try {
            dbf.setValidating(true);
            final DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            docBuilder.setErrorHandler(new SAXErrorHandler());
            docBuilder.setEntityResolver(new Log4jEntityResolver());
            inputSource.setSystemId("dummy://log4j.dtd");
            final Document doc = docBuilder.parse(inputSource);
            this.parse(doc.getDocumentElement());
        }
        catch (Exception e2) {
            LogLog.error("Could not parse input source [" + inputSource + "].", e2);
        }
    }
    
    public void doConfigure(final Element element, final LoggerRepository repository) {
        this.repository = repository;
        this.parse(element);
    }
    
    public static void configure(final String filename) throws FactoryConfigurationError {
        new DOMConfigurator().doConfigure(filename, LogManager.getLoggerRepository());
    }
    
    public static void configure(final URL url) throws FactoryConfigurationError {
        new DOMConfigurator().doConfigure(url, LogManager.getLoggerRepository());
    }
    
    protected void parse(final Element element) {
        final String rootElementName = element.getTagName();
        if (!rootElementName.equals("log4j:configuration")) {
            if (!rootElementName.equals("configuration")) {
                LogLog.error("DOM element is - not a <log4j:configuration> element.");
                return;
            }
            LogLog.warn("The <configuration> element has been deprecated.");
            LogLog.warn("Use the <log4j:configuration> element instead.");
        }
        final String debugAttrib = this.subst(element.getAttribute("debug"));
        LogLog.debug("debug attribute= \"" + debugAttrib + "\".");
        if (!debugAttrib.equals("") && !debugAttrib.equals("null")) {
            LogLog.setInternalDebugging(OptionConverter.toBoolean(debugAttrib, true));
        }
        else {
            LogLog.debug("Ignoring debug attribute.");
        }
        final String confDebug = this.subst(element.getAttribute("configDebug"));
        if (!confDebug.equals("") && !confDebug.equals("null")) {
            LogLog.warn("The \"configDebug\" attribute is deprecated.");
            LogLog.warn("Use the \"debug\" attribute instead.");
            LogLog.setInternalDebugging(OptionConverter.toBoolean(confDebug, true));
        }
        final String thresholdStr = this.subst(element.getAttribute("threshold"));
        LogLog.debug("Threshold =\"" + thresholdStr + "\".");
        if (!"".equals(thresholdStr) && !"null".equals(thresholdStr)) {
            this.repository.setThreshold(thresholdStr);
        }
        String tagName = null;
        Element currentElement = null;
        Node currentNode = null;
        final NodeList children = element.getChildNodes();
        final int length = children.getLength();
        for (int loop = 0; loop < length; ++loop) {
            currentNode = children.item(loop);
            if (currentNode.getNodeType() == 1) {
                currentElement = (Element)currentNode;
                tagName = currentElement.getTagName();
                if (tagName.equals("categoryFactory")) {
                    this.parseCategoryFactory(currentElement);
                }
            }
        }
        for (int loop = 0; loop < length; ++loop) {
            currentNode = children.item(loop);
            if (currentNode.getNodeType() == 1) {
                currentElement = (Element)currentNode;
                tagName = currentElement.getTagName();
                if (tagName.equals("category") || tagName.equals("logger")) {
                    this.parseCategory(currentElement);
                }
                else if (tagName.equals("root")) {
                    this.parseRoot(currentElement);
                }
                else if (tagName.equals("renderer")) {
                    this.parseRenderer(currentElement);
                }
            }
        }
    }
    
    protected String subst(final String value) {
        try {
            return OptionConverter.substVars(value, this.props);
        }
        catch (IllegalArgumentException e) {
            LogLog.warn("Could not perform variable substitution.", e);
            return value;
        }
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        ONE_STRING_PARAM = new Class[] { (DOMConfigurator.class$java$lang$String == null) ? (DOMConfigurator.class$java$lang$String = class$("java.lang.String")) : DOMConfigurator.class$java$lang$String };
    }
}
