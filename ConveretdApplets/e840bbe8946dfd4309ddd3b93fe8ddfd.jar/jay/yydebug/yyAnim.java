// 
// Decompiled by Procyon v0.5.30
// 

package jay.yydebug;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.PrintStream;
import java.io.InputStream;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Font;
import java.awt.Frame;

public class yyAnim extends Frame implements yyDebug
{
    protected static int nFrames;
    public static final int IN = 1;
    public static final int OUT = 2;
    protected yyAnimPanel panel;
    protected Thread eventThread;
    protected boolean outputBreak;
    
    public yyAnim(final String s, final int n) {
        this(System.class, s, n);
    }
    
    public yyAnim(final Class clazz, final String s, final int n) {
        super(s);
        ++yyAnim.nFrames;
        this.outputBreak = false;
        final Font font = new Font("Monospaced", 0, 12);
        final MenuBar menuBar = new MenuBar();
        final Menu menu = new Menu("yyAnim");
        final MenuItem menuItem = new MenuItem("Quit");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    clazz.getMethod("exit", Integer.TYPE).invoke(null, new Integer(0));
                }
                catch (Exception ex) {
                    System.exit(0);
                }
            }
        });
        menu.add(menuItem);
        menuBar.add(menu);
        this.setMenuBar(menuBar);
        this.add(this.panel = new yyAnimPanel(font), "Center");
        if ((n & 0x3) != 0x0) {
            final Panel panel = new Panel(new BorderLayout());
            switch (n) {
                case 1: {
                    panel.add(new Label("terminal input"), "North");
                    break;
                }
                case 2:
                case 3: {
                    final Checkbox checkbox;
                    panel.add(checkbox = new Checkbox(((n & 0x1) != 0x0) ? "terminal i/o" : "terminal output", this.outputBreak), "North");
                    checkbox.addItemListener(new ItemListener() {
                        public void itemStateChanged(final ItemEvent itemEvent) {
                            yyAnim.this.eventThread = Thread.currentThread();
                            yyAnim.this.outputBreak = (itemEvent.getStateChange() == 1);
                        }
                    });
                    break;
                }
            }
            final TextArea textArea;
            panel.add(textArea = new TextArea(10, 50), "Center");
            textArea.setBackground(Color.white);
            textArea.setFont(font);
            if ((n & 0x1) != 0x0) {
                final yyInputStream in = new yyInputStream();
                textArea.addKeyListener(in);
                textArea.setEditable(true);
                try {
                    clazz.getMethod("setIn", InputStream.class).invoke(null, in);
                }
                catch (Exception ex) {
                    System.setIn(in);
                }
            }
            if ((n & 0x2) != 0x0) {
                final yyPrintStream yyPrintStream = new yyPrintStream() {
                    public void close() {
                    }
                    
                    public void write(final byte[] array, final int n, final int n2) {
                        final String s = new String(array, n, n2);
                        textArea.append(s);
                        textArea.setCaretPosition(textArea.getText().length());
                        if (yyAnim.this.outputBreak && s.indexOf("\n") >= 0 && yyAnim.this.eventThread != null && Thread.currentThread() != yyAnim.this.eventThread) {
                            try {
                                synchronized (yyAnim.this.panel) {
                                    yyAnim.this.panel.wait();
                                }
                            }
                            catch (InterruptedException ex) {}
                        }
                    }
                    
                    public void write(final int n) {
                        this.write(new byte[] { (byte)n }, 0, 1);
                    }
                };
                try {
                    clazz.getMethod("setOut", PrintStream.class).invoke(null, yyPrintStream);
                }
                catch (Exception ex2) {
                    System.setOut(yyPrintStream);
                }
                try {
                    clazz.getMethod("setErr", PrintStream.class).invoke(null, yyPrintStream);
                }
                catch (Exception ex3) {
                    System.setErr(yyPrintStream);
                }
            }
            this.add(panel, "South");
        }
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                yyAnim.this.dispose();
                if (--yyAnim.nFrames <= 0) {
                    try {
                        clazz.getMethod("exit", Integer.TYPE).invoke(null, new Integer(0));
                    }
                    catch (Exception ex) {
                        System.exit(0);
                    }
                }
            }
        });
        this.pack();
        setStaggeredLocation(this);
        this.show();
    }
    
    public static void setStaggeredLocation(final Component component) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension preferredSize = component.getPreferredSize();
        int n = (screenSize.width - preferredSize.width) / 2 + (yyAnim.nFrames - 1) * 32;
        if (n < 32) {
            n = 32;
        }
        else if (n > screenSize.width - 128) {
            n = screenSize.width - 128;
        }
        int n2 = (screenSize.height - preferredSize.height) / 2 + (yyAnim.nFrames - 1) * 32;
        if (n2 < 32) {
            n2 = 32;
        }
        else if (n2 > screenSize.height - 128) {
            n2 = screenSize.height - 128;
        }
        component.setLocation(n, n2);
    }
    
    public synchronized void lex(final int n, final int n2, final String s, final Object o) {
        this.panel.lex(n, n2, s, o);
    }
    
    public void shift(final int n, final int n2, final int n3) {
        this.panel.shift(n, n2, n3);
    }
    
    public void discard(final int n, final int n2, final String s, final Object o) {
        this.panel.discard(n, n2, s, o);
    }
    
    public void shift(final int n, final int n2) {
        this.panel.shift(n, n2);
    }
    
    public synchronized void accept(final Object o) {
        this.panel.accept(o);
    }
    
    public void error(final String s) {
        this.panel.error(s);
    }
    
    public void reject() {
        this.panel.reject();
    }
    
    public synchronized void push(final int n, final Object o) {
        this.panel.push(n, o);
    }
    
    public synchronized void pop(final int n) {
        this.panel.pop(n);
    }
    
    public synchronized void reduce(final int n, final int n2, final int n3, final String s, final int n4) {
        this.panel.reduce(n, n2, n3, s, n4);
    }
}
