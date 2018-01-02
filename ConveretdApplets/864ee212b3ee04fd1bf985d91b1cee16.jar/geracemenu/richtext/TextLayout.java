// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

import java.awt.Component;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.Dimension;
import geracemenu.TTComponent;
import java.util.Hashtable;
import geracemenu.util.VArray;

public class TextLayout extends RichTextToken
{
    private static final String CACHE_ISCLICKABLE;
    private static final String CACHE_CLICKABLE;
    private static /* synthetic */ Class class$Lgeracemenu$richtext$RichTextLine;
    private VArray lines;
    private Text text;
    private Hashtable cache;
    private TTComponent container;
    
    public Dimension getSize() {
        if (super.size == null) {
            int n = 0;
            int max = 0;
            for (int i = 0; i < this.lines.size(); ++i) {
                final RichTextLine richTextLine = (RichTextLine)this.lines.get(i);
                n += richTextLine.getSize().height;
                max = Math.max(max, richTextLine.getSize().width);
            }
            super.size = new Dimension(max, n);
        }
        return super.size;
    }
    
    public ClickableTextAction getClickableTextAction() {
        if (this.cache.get(TextLayout.CACHE_CLICKABLE) == null) {
            final Enumeration textStyles = this.text.getTextStyles();
            while (textStyles.hasMoreElements()) {
                final TextStyle textStyle = textStyles.nextElement();
                if (textStyle.isClickable()) {
                    this.cache.put(TextLayout.CACHE_CLICKABLE, textStyle.getClickableTextAction());
                    break;
                }
            }
        }
        return this.cache.get(TextLayout.CACHE_CLICKABLE);
    }
    
    public boolean isClickable() {
        if (this.cache.get(TextLayout.CACHE_ISCLICKABLE) == null) {
            final Enumeration textStyles = this.text.getTextStyles();
            while (textStyles.hasMoreElements()) {
                if (textStyles.nextElement().isClickable()) {
                    this.cache.put(TextLayout.CACHE_ISCLICKABLE, new Boolean(true));
                    break;
                }
            }
            if (this.cache.get(TextLayout.CACHE_ISCLICKABLE) == null) {
                this.cache.put(TextLayout.CACHE_ISCLICKABLE, new Boolean(false));
            }
        }
        return this.cache.get(TextLayout.CACHE_ISCLICKABLE);
    }
    
    public final Text getRichText() {
        return this.text;
    }
    
    public void paint(final Graphics graphics, final Point point) {
        final Insets insets = this.container.getInsets();
        final int size = this.lines.size();
        int i = 0;
        int top = insets.top;
        while (i < size) {
            final RichTextLine richTextLine = (RichTextLine)this.lines.get(i);
            richTextLine.paint(graphics, new Point(insets.left, top));
            top += richTextLine.getSize().height;
            ++i;
        }
    }
    
    public RichTextLine[] getLines() {
        return (RichTextLine[])this.lines.getArray();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public TextLayout(final Component component, final String s) {
        super(null);
        this.cache = new Hashtable();
        this.lines = new VArray((TextLayout.class$Lgeracemenu$richtext$RichTextLine != null) ? TextLayout.class$Lgeracemenu$richtext$RichTextLine : (TextLayout.class$Lgeracemenu$richtext$RichTextLine = class$("geracemenu.richtext.RichTextLine")));
        this.container = (TTComponent)component;
        this.text = new Text("");
        final RichTextTokenizer richTextTokenizer = new RichTextTokenizer(s);
        try {
            while (richTextTokenizer.hasMoreTokens()) {
                final Text nextToken;
                if ((nextToken = richTextTokenizer.nextToken()) != null) {
                    this.text.append(nextToken);
                }
            }
        }
        catch (TagSyntaxException ex) {
            System.out.println("Tag syntax exception [" + ex.getTagName() + ": " + ex.getMessage() + ']');
        }
        catch (RuntimeException ex2) {
            System.out.println("Richtext tokenizer exception [" + ex2.getMessage() + ']');
        }
        finally {
            int alignment = 2;
            RichTextLine richTextLine = new RichTextLine(component, 2);
            final TextEnumerator textEnumerator = new TextEnumerator(this.text);
            while (textEnumerator.hasMoreElements()) {
                final RichTextToken richTextToken = textEnumerator.nextElement();
                richTextLine.append(richTextToken);
                if (alignment == 2) {
                    alignment = richTextToken.getTextStyle().getAlignment();
                }
                if (richTextToken instanceof NewLineToken) {
                    richTextLine.container = this;
                    this.lines.append(richTextLine);
                    richTextLine = new RichTextLine(component, 2);
                    richTextLine.append(richTextToken);
                }
            }
            richTextLine.container = this;
            this.lines.append(richTextLine);
            for (int i = 0; i < this.lines.size(); ++i) {
                ((RichTextLine)this.lines.get(i)).setAlignment(alignment);
            }
        }
    }
    
    static {
        CACHE_ISCLICKABLE = "cache_isclickable".intern();
        CACHE_CLICKABLE = "cache_clickable".intern();
    }
}
