// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.util.Arrays;
import java.awt.GraphicsEnvironment;
import java.awt.Container;
import java.awt.Font;
import javax.swing.text.JTextComponent;
import java.awt.Component;
import java.awt.Insets;
import org.jruby.demo.TextAreaReadline;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.io.PrintStream;
import org.jruby.anno.JRubyMethod;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.runtime.ThreadContext;
import org.jruby.javasupport.JavaUtil;
import java.awt.GraphicsConfiguration;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.lang.reflect.InvocationTargetException;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.URL;
import java.io.InputStream;
import org.jruby.runtime.Block;
import java.awt.image.VolatileImage;
import org.jruby.runtime.builtin.IRubyObject;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;

public class JRubyApplet extends Applet
{
    private Ruby runtime;
    private boolean doubleBuffered;
    private Color backgroundColor;
    private RubyProc startProc;
    private RubyProc stopProc;
    private RubyProc destroyProc;
    private RubyProc paintProc;
    private Graphics priorGraphics;
    private IRubyObject wrappedGraphics;
    private VolatileImage backBuffer;
    private Graphics backBufferGraphics;
    private Facade facade;
    
    public JRubyApplet() {
        this.doubleBuffered = true;
        this.backgroundColor = Color.WHITE;
    }
    
    private static RubyProc blockToProc(final Ruby runtime, final Block block) {
        if (block.isGiven()) {
            RubyProc proc = block.getProcObject();
            if (proc == null) {
                proc = RubyProc.newProc(runtime, block, block.type);
            }
            return proc;
        }
        return null;
    }
    
    private boolean getBooleanParameter(final String name, final boolean defaultValue) {
        final String value = this.getParameter(name);
        if (value != null) {
            return value.equals("true");
        }
        return defaultValue;
    }
    
    private InputStream getCodeResourceAsStream(final String name) {
        if (name == null) {
            return null;
        }
        try {
            final URL directURL = new URL(this.getCodeBase(), name);
            return directURL.openStream();
        }
        catch (IOException e) {
            return JRubyApplet.class.getClassLoader().getResourceAsStream(name);
        }
    }
    
    private static void safeInvokeAndWait(final Runnable runnable) throws InvocationTargetException, InterruptedException {
        if (EventQueue.isDispatchThread()) {
            try {
                runnable.run();
                return;
            }
            catch (Exception e) {
                throw new InvocationTargetException(e);
            }
        }
        EventQueue.invokeAndWait(runnable);
    }
    
    public void init() {
        super.init();
        if (this.getBooleanParameter("jruby.console", false)) {
            this.facade = new ConsoleFacade(this.getParameter("jruby.banner"));
        }
        else {
            this.facade = new TrivialFacade();
        }
        synchronized (this) {
            if (this.runtime != null) {
                return;
            }
            final RubyInstanceConfig config = new RubyInstanceConfig() {
                {
                    this.setInput(JRubyApplet.this.facade.getInputStream());
                    this.setOutput(JRubyApplet.this.facade.getOutputStream());
                    this.setError(JRubyApplet.this.facade.getErrorStream());
                    this.setObjectSpaceEnabled(JRubyApplet.this.getBooleanParameter("jruby.objectspace", false));
                }
            };
            Ruby.setSecurityRestricted(true);
            this.runtime = Ruby.newInstance(config);
        }
        final String scriptName = this.getParameter("jruby.script");
        final InputStream scriptStream = this.getCodeResourceAsStream(scriptName);
        final String evalString = this.getParameter("jruby.eval");
        try {
            safeInvokeAndWait(new Runnable() {
                public void run() {
                    JRubyApplet.this.setLayout(new BorderLayout());
                    JRubyApplet.this.facade.attach(JRubyApplet.this.runtime, JRubyApplet.this);
                    if (scriptStream != null) {
                        JRubyApplet.this.runtime.runFromMain(scriptStream, scriptName);
                    }
                    if (evalString != null) {
                        JRubyApplet.this.runtime.evalScriptlet(evalString);
                    }
                }
            });
        }
        catch (InterruptedException e2) {}
        catch (InvocationTargetException e) {
            throw new RuntimeException("Error running script", e.getCause());
        }
    }
    
    private void invokeCallback(final RubyProc proc, final IRubyObject[] args) {
        if (proc == null) {
            return;
        }
        final Ruby ruby = this.runtime;
        try {
            safeInvokeAndWait(new Runnable() {
                public void run() {
                    proc.call(ruby.getCurrentContext(), args);
                }
            });
        }
        catch (InterruptedException e2) {}
        catch (InvocationTargetException e) {
            throw new RuntimeException("Ruby callback failed", e.getCause());
        }
    }
    
    public synchronized void setBackgroundColor(final Color color) {
        this.backgroundColor = color;
        this.repaint();
    }
    
    public synchronized Color getBackgroundColor() {
        return this.backgroundColor;
    }
    
    public synchronized boolean isDoubleBuffered() {
        return this.doubleBuffered;
    }
    
    public synchronized void setDoubleBuffered(final boolean shouldBuffer) {
        this.doubleBuffered = shouldBuffer;
        this.repaint();
    }
    
    public synchronized void start() {
        super.start();
        this.invokeCallback(this.startProc, new IRubyObject[0]);
    }
    
    public synchronized void stop() {
        this.invokeCallback(this.stopProc, new IRubyObject[0]);
        super.stop();
    }
    
    public synchronized void destroy() {
        try {
            this.invokeCallback(this.destroyProc, new IRubyObject[0]);
        }
        finally {
            this.facade.destroy();
            final Ruby ruby = this.runtime;
            this.runtime = null;
            this.startProc = null;
            this.stopProc = null;
            this.destroyProc = null;
            this.paintProc = null;
            this.priorGraphics = null;
            this.wrappedGraphics = null;
            ruby.tearDown();
            super.destroy();
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public synchronized void paint(final Graphics g) {
        if (this.doubleBuffered) {
            this.paintBuffered(g);
        }
        else {
            this.paintUnbuffered(g);
        }
    }
    
    private synchronized void paintBuffered(final Graphics g) {
        do {
            final GraphicsConfiguration config = this.getGraphicsConfiguration();
            final int width = this.getWidth();
            final int height = this.getHeight();
            if (this.backBuffer == null || width != this.backBuffer.getWidth() || height != this.backBuffer.getHeight() || this.backBuffer.validate(config) == 2) {
                if (this.backBuffer != null) {
                    this.backBufferGraphics.dispose();
                    this.backBufferGraphics = null;
                    this.backBuffer.flush();
                    this.backBuffer = null;
                }
                this.backBuffer = config.createCompatibleVolatileImage(width, height);
                this.backBufferGraphics = this.backBuffer.createGraphics();
            }
            this.backBufferGraphics.setClip(g.getClip());
            this.paintUnbuffered(this.backBufferGraphics);
            g.drawImage(this.backBuffer, 0, 0, this);
        } while (this.backBuffer.contentsLost());
    }
    
    private synchronized void paintUnbuffered(final Graphics g) {
        if (this.backgroundColor != null) {
            g.setColor(this.backgroundColor);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        if (this.paintProc != null) {
            if (this.priorGraphics != g) {
                this.wrappedGraphics = JavaUtil.convertJavaToUsableRubyObject(this.runtime, g);
                this.priorGraphics = g;
            }
            final ThreadContext context = this.runtime.getCurrentContext();
            this.paintProc.call(context, new IRubyObject[] { this.wrappedGraphics });
        }
        super.paint(g);
    }
    
    public static class RubyMethods
    {
        @JRubyMethod
        public static IRubyObject on_start(final IRubyObject recv, final Block block) {
            final JRubyApplet applet = (JRubyApplet)JavaEmbedUtils.rubyToJava(recv);
            synchronized (applet) {
                applet.startProc = blockToProc(applet.runtime, block);
            }
            return recv;
        }
        
        @JRubyMethod
        public static IRubyObject on_stop(final IRubyObject recv, final Block block) {
            final JRubyApplet applet = (JRubyApplet)JavaEmbedUtils.rubyToJava(recv);
            synchronized (applet) {
                applet.stopProc = blockToProc(applet.runtime, block);
            }
            return recv;
        }
        
        @JRubyMethod
        public static IRubyObject on_destroy(final IRubyObject recv, final Block block) {
            final JRubyApplet applet = (JRubyApplet)JavaEmbedUtils.rubyToJava(recv);
            synchronized (applet) {
                applet.destroyProc = blockToProc(applet.runtime, block);
            }
            return recv;
        }
        
        @JRubyMethod
        public static IRubyObject on_paint(final IRubyObject recv, final Block block) {
            final JRubyApplet applet = (JRubyApplet)JavaEmbedUtils.rubyToJava(recv);
            synchronized (applet) {
                applet.paintProc = blockToProc(applet.runtime, block);
                applet.repaint();
            }
            return recv;
        }
    }
    
    private static class TrivialFacade implements Facade
    {
        public InputStream getInputStream() {
            return System.in;
        }
        
        public PrintStream getOutputStream() {
            return System.out;
        }
        
        public PrintStream getErrorStream() {
            return System.err;
        }
        
        public void attach(final Ruby runtime, final Applet applet) {
            final IRubyObject wrappedApplet = JavaEmbedUtils.javaToRuby(runtime, applet);
            runtime.defineGlobalConstant("JRUBY_APPLET", wrappedApplet);
            wrappedApplet.getMetaClass().defineAnnotatedMethods(RubyMethods.class);
        }
        
        public void destroy() {
        }
    }
    
    private static class ConsoleFacade implements Facade
    {
        private JTextPane textPane;
        private JScrollPane scrollPane;
        private TextAreaReadline adaptor;
        private InputStream inputStream;
        private PrintStream outputStream;
        private PrintStream errorStream;
        
        public ConsoleFacade(String bannerText) {
            (this.textPane = new JTextPane()).setMargin(new Insets(4, 4, 0, 4));
            this.textPane.setCaretColor(new Color(164, 0, 0));
            this.textPane.setBackground(new Color(242, 242, 242));
            this.textPane.setForeground(new Color(164, 0, 0));
            final Font font = this.findFont("Monospaced", 0, 14, new String[] { "Monaco", "Andale Mono" });
            this.textPane.setFont(font);
            (this.scrollPane = new JScrollPane(this.textPane)).setDoubleBuffered(true);
            if (bannerText != null) {
                bannerText = "  " + bannerText + "  \n\n";
            }
            this.adaptor = new TextAreaReadline((JTextComponent)this.textPane, bannerText);
            this.inputStream = this.adaptor.getInputStream();
            this.outputStream = new PrintStream(this.adaptor.getOutputStream());
            this.errorStream = new PrintStream(this.adaptor.getOutputStream());
        }
        
        public InputStream getInputStream() {
            return this.inputStream;
        }
        
        public PrintStream getOutputStream() {
            return this.outputStream;
        }
        
        public PrintStream getErrorStream() {
            return this.errorStream;
        }
        
        public void attach(final Ruby runtime, final Applet applet) {
            this.adaptor.hookIntoRuntime(runtime);
            applet.add(this.scrollPane);
            applet.validate();
        }
        
        public void destroy() {
            final Container parent = this.scrollPane.getParent();
            this.adaptor.shutdown();
            if (parent != null) {
                parent.remove(this.scrollPane);
            }
        }
        
        private Font findFont(final String otherwise, final int style, final int size, final String[] families) {
            final String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
            Arrays.sort(fonts);
            for (int i = 0; i < families.length; ++i) {
                if (Arrays.binarySearch(fonts, families[i]) >= 0) {
                    return new Font(families[i], style, size);
                }
            }
            return new Font(otherwise, style, size);
        }
    }
    
    private interface Facade
    {
        InputStream getInputStream();
        
        PrintStream getOutputStream();
        
        PrintStream getErrorStream();
        
        void attach(final Ruby p0, final Applet p1);
        
        void destroy();
    }
}
