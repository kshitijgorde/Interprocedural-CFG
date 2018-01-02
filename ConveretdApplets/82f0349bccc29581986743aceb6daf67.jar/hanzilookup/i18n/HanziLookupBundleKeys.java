// 
// Decompiled by Procyon v0.5.30
// 

package hanzilookup.i18n;

import java.util.Iterator;
import java.util.ListResourceBundle;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class HanziLookupBundleKeys
{
    public static final String OK_BUNDLE_KEY = "ok";
    public static final String SETTINGS_BUNDLE_KEY = "settings";
    public static final String SAVE_SETTINGS_BUNDLE_KEY = "save_settings";
    public static final String CHARACTER_TYPE_BUNDLE_KEY = "character_type_bundle_key";
    public static final String SIMPLIFIED_TYPE_BUNDLE_KEY = "simplified_character_type";
    public static final String TRADITIONAL_TYPE_BUNDLE_KEY = "traditional_character_type";
    public static final String BOTH_TYPES_BUNDLE_KEY = "both_character_types";
    public static final String LOOKUP_OPTIONS_BUNDLE_KEY = "lookup_options";
    public static final String CHOOSE_FONT_BUNDLE_KEY = "choose_font";
    public static final String OPTIONS_BUNDLE_KEY = "options";
    public static final String AUTO_LOOKUP_BUNDLE_KEY = "auto_lookup";
    public static final String LOOKUP_LOOSENESS_BUNDLE_KEY = "lookup_looseness";
    public static final String MATCH_COUNT_BUNDLE_KEY = "match_count";
    public static final String TYPE_MACRO_BUNDLE_KEY = "type_macro";
    public static final String LOOKUP_MACRO_BUNDLE_KEY = "lookup_macro";
    public static final String UNDO_MACRO_BUNDLE_KEY = "undo_macro";
    public static final String CLEAR_MACRO_BUNDLE_KEY = "clear_macro";
    public static ResourceBundle DEFAULT_ENGLISH_BUNDLE;
    public static Map DEFAULT_ENGLISH_CONTENTS;
    
    static {
        final Map contents = new HashMap();
        contents.put("ok", "Ok");
        contents.put("settings", "Settings");
        contents.put("save_settings", "Save Settings");
        contents.put("character_type_bundle_key", "Character Mode");
        contents.put("simplified_character_type", "Simplified");
        contents.put("traditional_character_type", "Traditional");
        contents.put("both_character_types", "Both");
        contents.put("lookup_options", "Lookup options");
        contents.put("choose_font", "Choose Font");
        contents.put("options", "Options");
        contents.put("auto_lookup", "Auto Lookup");
        contents.put("lookup_looseness", "Lookup Looseness");
        contents.put("match_count", "Match Count");
        contents.put("type_macro", "(type macro)");
        contents.put("lookup_macro", "Lookup macro: ");
        contents.put("undo_macro", "Undo macro: ");
        contents.put("clear_macro", "Clear macro: ");
        HanziLookupBundleKeys.DEFAULT_ENGLISH_CONTENTS = Collections.unmodifiableMap((Map<?, ?>)contents);
        HanziLookupBundleKeys.DEFAULT_ENGLISH_BUNDLE = new DefaultEnglishBundle(null);
    }
    
    public static class DefaultEnglishBundle extends ListResourceBundle
    {
        public Object[][] getContents() {
            final Object[][] contents = new Object[HanziLookupBundleKeys.DEFAULT_ENGLISH_CONTENTS.size()][2];
            final Iterator entryIter = HanziLookupBundleKeys.DEFAULT_ENGLISH_CONTENTS.entrySet().iterator();
            for (int i = 0; i < contents.length; ++i) {
                final Map.Entry entry = entryIter.next();
                contents[i][0] = entry.getKey();
                contents[i][1] = entry.getValue();
            }
            return contents;
        }
    }
}
