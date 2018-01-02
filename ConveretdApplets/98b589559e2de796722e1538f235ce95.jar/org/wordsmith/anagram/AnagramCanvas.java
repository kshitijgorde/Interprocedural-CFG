// 
// Decompiled by Procyon v0.5.30
// 

package org.wordsmith.anagram;

import java.awt.GraphicsEnvironment;
import java.util.Iterator;
import java.awt.font.FontRenderContext;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Canvas;

public class AnagramCanvas extends Canvas
{
    private static int ourNextActorIndex;
    private final Configuration myConfiguration;
    private SymbolMapper mySymbolMapper;
    private final BufferedImage myBackgroundImage;
    private BufferedImage myMainImage;
    private Image myBrandingImage;
    private final Font myAnagramFont;
    private Color myAnagramTextColor;
    private Color myAnagramShadowColor;
    private Color myAnagramBackgroundColor;
    private PositionManager myAnimationPositionManager;
    private PositionManager myStaticPositionManager;
    private Graphics2D myMainImageGraphics;
    private double myVerticalGap;
    private static final Point SHADOW_DELTA;
    private final boolean myShouldAddBranding;
    private int myRoundedCornersRadius;
    private static final float SWITCH_NOT_MATCHED_ACTOR_ON_FRAME = 0.1f;
    private static final String[] BANNER_TEXT;
    static /* synthetic */ Class class$org$wordsmith$anagram$AnagramCanvas;
    
    public AnagramCanvas(final Configuration myConfiguration, final URL url, final Redirector redirector) {
        this.myVerticalGap = Double.NaN;
        this.myRoundedCornersRadius = -1;
        this.myConfiguration = myConfiguration;
        this.myAnagramFont = getFont(myConfiguration.getFontName(), myConfiguration.getFontSize(), myConfiguration.isBold(), myConfiguration.isItalic());
        this.myBackgroundImage = null;
        this.myShouldAddBranding = !this.myConfiguration.isSiteAuthorized(url);
        if (this.myShouldAddBranding) {
            this.myBrandingImage = Toolkit.getDefaultToolkit().getImage(((AnagramCanvas.class$org$wordsmith$anagram$AnagramCanvas == null) ? (AnagramCanvas.class$org$wordsmith$anagram$AnagramCanvas = class$("org.wordsmith.anagram.AnagramCanvas")) : AnagramCanvas.class$org$wordsmith$anagram$AnagramCanvas).getResource(this.getBrandingImagePath()));
            this.addMouseListener(new MouseAdapter() {
                public void mouseClicked(final MouseEvent mouseEvent) {
                    redirector.redirectToURL(AnagramCanvas.this.myConfiguration.getRedirectOnClickURL());
                }
            });
        }
        this.myAnimationPositionManager = this.createAnimationPositionManager();
        this.myStaticPositionManager = new PositionManager(1, 0);
    }
    
    private String getBrandingImagePath() {
        return this.isDark(this.getAnagramBackgroundColor()) ? "branding_w.gif" : "branding.gif";
    }
    
    private boolean isDark(final Color color) {
        return color.getBlue() + color.getRed() + color.getGreen() < 382;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.myMainImage, 0, 0, null);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void initMainImage() {
        this.myMainImage = new BufferedImage(this.getWidth(), this.getHeight(), 1);
        (this.myMainImageGraphics = this.myMainImage.createGraphics()).setColor(Color.WHITE);
        this.myMainImageGraphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.initPositionManager(this.myStaticPositionManager, this.createStaticSymbolMapper(), false);
    }
    
    public void initAnimation(final String s, final String s2) {
        this.mySymbolMapper = new SymbolMapper(s, s2);
        if (!this.mySymbolMapper.isValidAnagram()) {
            this.mySymbolMapper = new SymbolMapper("Invalid angrams", "Advising an alarm");
        }
        this.initPositionManager(this.myAnimationPositionManager, this.mySymbolMapper);
    }
    
    private SymbolMapper createStaticSymbolMapper() {
        return new SymbolMapper(AnagramCanvas.BANNER_TEXT, AnagramCanvas.BANNER_TEXT);
    }
    
    private void initPositionManager(final PositionManager positionManager, final SymbolMapper symbolMapper) {
        this.initPositionManager(positionManager, symbolMapper, this.myConfiguration.hasShadow());
    }
    
    private void initPositionManager(final PositionManager positionManager, final SymbolMapper symbolMapper, final boolean b) {
        this.initPositionManager(positionManager, symbolMapper, this.getBounds(), this.myAnagramFont, this.myMainImageGraphics.getFontRenderContext(), b);
    }
    
    private void initPositionManager(final PositionManager positionManager, final SymbolMapper symbolMapper, final Rectangle rectangle, final Font font, final FontRenderContext fontRenderContext, final boolean b) {
        final TextAlignContextImpl textAlignContextImpl = new TextAlignContextImpl(rectangle, font, fontRenderContext);
        this.initPositionManager(positionManager, symbolMapper, new TextAligner(symbolMapper.getSourceText(), textAlignContextImpl), new TextAligner(symbolMapper.getTargetText(), textAlignContextImpl), b);
    }
    
    private void initPositionManager(final PositionManager positionManager, final SymbolMapper symbolMapper, final TextAligner textAligner, final TextAligner textAligner2, final boolean b) {
        final Point point = b ? AnagramCanvas.SHADOW_DELTA : null;
        final float lineHeight = textAligner.getLineHeight(0);
        final Font minFont = this.getMinFont(textAligner, textAligner2);
        final Iterator allMappings = symbolMapper.allMappings();
        while (allMappings.hasNext()) {
            final SymbolMapping symbolMapping = allMappings.next();
            final AnimationActor actor = this.createActor(symbolMapping, point, minFont);
            final SymbolPosition symbolPosition = symbolMapping.isNew() ? null : symbolMapping.getOldPosition();
            final SymbolPosition symbolPosition2 = symbolMapping.isDeleted() ? null : symbolMapping.getNewPosition();
            Point symbolPlacement = null;
            Point symbolPlacement2 = null;
            if (!symbolMapping.isNew()) {
                symbolPlacement = textAligner.getSymbolPlacement(symbolPosition);
            }
            if (!symbolMapping.isDeleted()) {
                symbolPlacement2 = textAligner2.getSymbolPlacement(symbolPosition2);
            }
            if (symbolMapping.isNew()) {
                positionManager.addAppearingActor(actor, symbolPlacement2, 0.1f);
            }
            else if (symbolMapping.isDeleted()) {
                positionManager.addDisappearingActor(actor, symbolPlacement, 0.1f);
            }
            else {
                double verticalGap = this.getVerticalGap();
                if (symbolPosition.compareTo(symbolPosition2) > 0) {
                    verticalGap *= -1.0;
                }
                positionManager.addActor(actor, symbolPlacement, new Point((symbolPlacement.x + symbolPlacement2.x) / 2, (int)((symbolPlacement.y + symbolPlacement2.y) / 2 + verticalGap * lineHeight)), symbolPlacement2);
            }
        }
    }
    
    private Font getMinFont(final TextAligner textAligner, final TextAligner textAligner2) {
        final Font adjustedFont = textAligner.getAdjustedFont();
        final Font adjustedFont2 = textAligner2.getAdjustedFont();
        return (adjustedFont.getSize2D() < adjustedFont2.getSize2D()) ? adjustedFont : adjustedFont2;
    }
    
    private AnimationActor createActor(final SymbolMapping symbolMapping, final Point point, final Font font) {
        if (symbolMapping.isNew()) {
            final char newChar = symbolMapping.getNewChar();
            return new CharacterActorImpl(newChar, newChar, point, font);
        }
        if (symbolMapping.isDeleted()) {
            final char oldChar = symbolMapping.getOldChar();
            return new CharacterActorImpl(oldChar, oldChar, point, font);
        }
        return new CharacterActorImpl(symbolMapping.getOldChar(), symbolMapping.getNewChar(), point, font);
    }
    
    private PositionManager createAnimationPositionManager() {
        return new PositionManager(this.myConfiguration.getSymbolFramesCount(), this.myConfiguration.getSymbolDelayMillis() * this.myConfiguration.getSymbolFramesCount() / this.myConfiguration.getSymbolAnimationTimeMillis());
    }
    
    private static Font getFont(final String s, final int n, final boolean b, final boolean b2) {
        final int fontStyle = getFontStyle(b, b2);
        final Font[] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        Font font = null;
        for (int n2 = 0; font == null && n2 < allFonts.length; ++n2) {
            final Font font2 = allFonts[n2];
            if (font2.getName().equalsIgnoreCase(s)) {
                font = font2;
            }
        }
        return (font == null) ? new Font("Serif", fontStyle, n) : font.deriveFont(fontStyle, n);
    }
    
    private static int getFontStyle(final boolean b, final boolean b2) {
        return (b ? 1 : 0) | (b2 ? 2 : 0);
    }
    
    public void paintAnimationFrame(final int n) {
        synchronized (this.myMainImage) {
            this.paintBackground();
            this.myAnimationPositionManager.placeActors(n);
            this.drawBranding();
            this.repaint();
        }
    }
    
    public void paintBannerFrame() {
        synchronized (this.myMainImage) {
            this.paintBackground();
            this.myStaticPositionManager.placeActors(0);
            this.drawBranding();
            this.repaint();
        }
    }
    
    private void paintBackground() {
        if (this.myBackgroundImage == null) {
            this.myMainImageGraphics.setColor(this.getAnagramBackgroundColor());
            final int roundedCornersRadius = this.getRoundedCornersRadius();
            if (roundedCornersRadius <= 0) {
                this.myMainImageGraphics.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
            else {
                this.myMainImageGraphics.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), roundedCornersRadius, roundedCornersRadius);
            }
        }
        else {
            this.myMainImageGraphics.drawImage(this.myBackgroundImage, 0, 0, null);
        }
    }
    
    private int getRoundedCornersRadius() {
        if (this.myRoundedCornersRadius == -1) {
            this.myRoundedCornersRadius = this.myConfiguration.getRoundedCornersRadius();
        }
        return this.myRoundedCornersRadius;
    }
    
    private void drawBranding() {
        if (this.myBrandingImage != null) {
            final int width = this.myBrandingImage.getWidth(null);
            final int height = this.myBrandingImage.getHeight(null);
            if (height != -1 && width != -1) {
                this.myMainImageGraphics.drawImage(this.myBrandingImage, this.getWidth() - width - this.getRoundedCornersRadius() / 3, this.getHeight() - height, null);
            }
        }
    }
    
    private Color getAnagramBackgroundColor() {
        if (this.myAnagramBackgroundColor == null) {
            this.myAnagramBackgroundColor = this.myConfiguration.getBackgroundColor();
        }
        return this.myAnagramBackgroundColor;
    }
    
    private Color getAnagramShadowColor() {
        if (this.myAnagramShadowColor == null) {
            this.myAnagramShadowColor = this.myConfiguration.getShadowColor();
        }
        return this.myAnagramShadowColor;
    }
    
    private Color getAnagramTextColor() {
        if (this.myAnagramTextColor == null) {
            this.myAnagramTextColor = this.myConfiguration.getTextColor();
        }
        return this.myAnagramTextColor;
    }
    
    private Graphics2D getMainImageGraphics() {
        return this.myMainImageGraphics;
    }
    
    private double getVerticalGap() {
        if (Double.isNaN(this.myVerticalGap)) {
            this.myVerticalGap = this.myConfiguration.getVerticalAnimationGap();
        }
        return this.myVerticalGap;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        AnagramCanvas.ourNextActorIndex = 0;
        SHADOW_DELTA = new Point(3, 3);
        BANNER_TEXT = new String[] { "What's hidden in your name?", "Click to find out!" };
    }
    
    private static class TextAlignContextImpl implements TextAlignContext
    {
        private final FontRenderContext myFontRenderContext;
        private final Rectangle myBounds;
        private final Font myFont;
        
        public TextAlignContextImpl(final Rectangle myBounds, final Font myFont, final FontRenderContext myFontRenderContext) {
            this.myBounds = myBounds;
            this.myFont = myFont;
            this.myFontRenderContext = myFontRenderContext;
        }
        
        public Font getFont() {
            return this.myFont;
        }
        
        public FontRenderContext getFontRenderContext() {
            return this.myFontRenderContext;
        }
        
        public int getTotalHeight() {
            return this.myBounds.height;
        }
        
        public int getTotalWidth() {
            return this.myBounds.width;
        }
    }
    
    private class CharacterActorImpl implements AnimationActor
    {
        private final String myOldText;
        private final String myNewText;
        private final String myIdentity;
        private final Point myShadowDelta;
        private final Font myFont;
        
        public CharacterActorImpl(final char c, final char c2, final Point myShadowDelta, final Font myFont) {
            this.myShadowDelta = myShadowDelta;
            this.myFont = myFont;
            this.myOldText = String.valueOf(c);
            this.myNewText = String.valueOf(c2);
            this.myIdentity = "char:" + AnagramCanvas.ourNextActorIndex++;
        }
        
        public void acceptPosition(final Point point, final int n, final int n2) {
            if (point == null) {
                return;
            }
            final Graphics2D access$200 = AnagramCanvas.this.getMainImageGraphics();
            final Color color = access$200.getColor();
            final Font font = access$200.getFont();
            access$200.setFont(this.myFont);
            final String text = this.getText(n, n2);
            if (this.myShadowDelta != null) {
                access$200.setColor(AnagramCanvas.this.getAnagramShadowColor());
                access$200.drawString(text, point.x + this.myShadowDelta.x, point.y + this.myShadowDelta.y);
            }
            access$200.setColor(AnagramCanvas.this.getAnagramTextColor());
            access$200.drawString(text, point.x, point.y);
            access$200.setColor(color);
            access$200.setFont(font);
        }
        
        public String getIdentity() {
            return this.myIdentity;
        }
        
        protected String getText(final int n, final int n2) {
            return (n * 2 < n2) ? this.getOldText() : this.getNewText();
        }
        
        protected String getOldText() {
            return this.myOldText;
        }
        
        protected String getNewText() {
            return this.myNewText;
        }
    }
}
