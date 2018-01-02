// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.util.regex.Matcher;
import java.util.Calendar;
import java.util.regex.Pattern;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.KeyStroke;
import java.awt.Toolkit;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import java.awt.Container;
import java.awt.event.WindowListener;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.imageio.stream.ImageOutputStream;
import java.io.OutputStream;
import javax.imageio.ImageWriteParam;
import javax.imageio.metadata.IIOMetadata;
import java.util.List;
import java.awt.image.RenderedImage;
import javax.imageio.IIOImage;
import java.util.Locale;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.io.ByteArrayOutputStream;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Collection;
import wordle.core.c.e;
import wordle.core.fitness.CenterLine;
import cue.lang.stop.StopWords;
import cue.lang.unicode.b;
import cue.lang.c;
import wordle.core.C;
import java.util.StringTokenizer;
import java.io.IOException;
import java.awt.EventQueue;
import wordle.core.c.f;
import java.awt.event.ComponentListener;
import java.text.SimpleDateFormat;
import java.awt.KeyboardFocusManager;
import java.util.HashMap;
import java.util.Map;
import java.text.DateFormat;
import wordle.core.c.a;
import wordle.core.r;
import wordle.core.t;
import wordle.core.n;
import wordle.core.c.d;
import wordle.core.J;
import java.util.concurrent.Executor;
import javax.swing.JApplet;
import javax.swing.AbstractButton;
import java.awt.event.KeyEvent;
import java.awt.KeyEventPostProcessor;

final class h implements KeyEventPostProcessor
{
    public final boolean postProcessKeyEvent(final KeyEvent keyEvent) {
        if (!WordleApplet.m) {
            return false;
        }
        if (keyEvent.getID() != 400) {
            return false;
        }
        final AbstractButton abstractButton;
        if ((abstractButton = WordleApplet.n.get(keyEvent.getKeyChar())) == null) {
            return false;
        }
        abstractButton.doClick();
        return true;
    }
}