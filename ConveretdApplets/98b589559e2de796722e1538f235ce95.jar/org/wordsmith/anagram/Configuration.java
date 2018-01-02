// 
// Decompiled by Procyon v0.5.30
// 

package org.wordsmith.anagram;

import java.awt.Color;
import java.net.URL;

public interface Configuration
{
    public static final String PARAM_IS_REMOTE_STRINGS = "remoteAnagram";
    public static final String PARAM_SOURCE_STRING = "sourceString";
    public static final String PARAM_TARGET_STRING = "targetString";
    public static final String PARAM_FONT_NAME = "font";
    public static final String PARAM_FONT_SIZE = "fontSize";
    public static final int DEFAULT_VALUE_FONT_SIZE = 32;
    public static final String PARAM_FONT_BOLD = "bold";
    public static final String PARAM_FONT_ITALIC = "italic";
    public static final String PARAM_ADD_SHADOW = "shadow";
    public static final String PARAM_ROUNDED_CORNERS_RADIUS = "roundedCornersRadius";
    public static final String PARAM_TOTAL_FRAMES = "symbolFrames";
    public static final int DEFAULT_VALUE_TOTAL_FRAMES = 20;
    public static final String PARAM_TOTAL_TIME = "symbolTimeMS";
    public static final int DEFAULT_VALUE_TOTAL_TIME = 1000;
    public static final String PARAM_DELAY_BETWEEN_SYMBOLS = "symbolDelayMS";
    public static final int DEFAULT_VALUE_DELAY_BETWEEN_SYMBOLS = 50;
    public static final String PARAM_PAUSE = "pauseTimeMS";
    public static final String PARAM_VERTICAL_ANIMATION_GAP = "verticalGap";
    public static final double DEFAULT_VALUE_VERTICAL_ANIMATION_GAP = 0.5;
    public static final String PARAM_ENDLESS = "endless";
    public static final String PARAM_TEXT_COLOR = "textColor";
    public static final String PARAM_SHADOW_COLOR = "shadowColor";
    public static final String PARAM_BACKGROUND_COLOR = "backgroundColor";
    public static final String[] ALLOWED_DOCUMENT_URL_PREFIXES = { "wordsmith.org", "www.wordsmith.org", "www2.wordsmith.org", "wordpix.org", "www.wordpix.org" };
    public static final String DEFAULT_REDIRECT_URL = "http://wordsmith.org/anagram";
    public static final String DEFAULT_REMOTE_ANAGRAMS_URL = "http://wordsmith.org/anagram/anagram.txt";
    
    boolean isSiteAuthorized(final URL p0);
    
    URL getRedirectOnClickURL();
    
    AnagramStringsSupplier getStringSupplier();
    
    String getFontName();
    
    int getFontSize();
    
    boolean isBold();
    
    boolean isItalic();
    
    boolean hasShadow();
    
    int getRoundedCornersRadius();
    
    int getSymbolFramesCount();
    
    int getSymbolAnimationTimeMillis();
    
    int getSymbolDelayMillis();
    
    int getBannerTimeMS();
    
    boolean shouldReverse();
    
    boolean isEndless();
    
    int getPauseInMillisecs();
    
    Color getTextColor();
    
    Color getShadowColor();
    
    Color getBackgroundColor();
    
    double getVerticalAnimationGap();
}
