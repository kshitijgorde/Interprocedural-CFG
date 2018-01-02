// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

import com.mindprod.common15.FontFactory;
import java.awt.Font;

public enum TargetOption
{
    TO_HTML((Translator)new HTMLReservedChars(), (TextProcessor)new HTMLTouchUp(), "to HTML, including &entities") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] raw text to upper; click Convert; then copy[ctrl-c] cooked HTML from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] raw text to the clipboard; click Convert Clipboard; then paste[ctrl-v] cooked HTML.";
        }
    }, 
    TO_XML((Translator)new XMLReservedChars(), (TextProcessor)new HTMLTouchUp(), "to XML, including &entity references") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] raw text to upper; click Convert; then copy[ctrl-c] cooked HTML from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] raw text to the clipboard; click Convert Clipboard; then paste[ctrl-v] cooked HTML.";
        }
    }, 
    TO_JAVA_LITERAL((Translator)null, (TextProcessor)new ToJavaStringLiteral(), "to Java String literal") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] raw text to upper; click Convert; then copy[ctrl-c] cooked Java code from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] raw text to the clipboard; click Convert Clipboard; then paste[ctrl-v] cooked Java.";
        }
    }, 
    TO_CSV((Translator)null, (TextProcessor)new ToCSV(), "to CSV field") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] raw text to upper; click Convert; then copy[ctrl-c] CSV field from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] raw text to the clipboard; click Convert Clipboard; then paste[ctrl-v] CSV field.";
        }
    }, 
    TO_JAVA_CHARARRY((Translator)null, (TextProcessor)new ToJavaCharArrayLiteral(), "to Java char[] literal") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] raw text to upper; click Convert; then copy[ctrl-c] cooked Java code from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] raw text to the clipboard; click Convert Clipboard; then paste[ctrl-v] cooked Java.";
        }
    }, 
    STRIP_HTML_TAGS((Translator)new PlainReservedChars(), (TextProcessor)new StripHTMLTags(), "Remove HTML <tags>") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] HTML text to upper; click Convert; then copy[ctrl-c] stripped text from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] HTML text to the clipboard; click Convert Clipboard; then paste[ctrl-v] stripped text.";
        }
    }, 
    STRIP_XML_TAGS((Translator)new PlainReservedChars(), (TextProcessor)new StripXMLTags(), "Remove XML <tags>") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] HTML text to upper; click Convert; then copy[ctrl-c] stripped text from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] HTML text to the clipboard; click Convert Clipboard; then paste[ctrl-v] stripped text.";
        }
    }, 
    STRIP_HTML_ENTITIES((Translator)new PlainReservedChars(), (TextProcessor)new StripHTMLEntities(), "Convert HTML &entities, to plain") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] HTML text to upper; click Convert; then copy[ctrl-c] stripped text from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] HTML text to the clipboard; click Convert Clipboard; then paste[ctrl-v] stripped text.";
        }
    }, 
    STRIP_XML_ENTITIES((Translator)new PlainReservedChars(), (TextProcessor)new StripXMLEntities(), "Convert XML &entity references, to plain") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] HTML text to upper; click Convert; then copy[ctrl-c] stripped text from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] HTML text to the clipboard; click Convert Clipboard; then paste[ctrl-v] stripped text.";
        }
    }, 
    ENCODE_URL((Translator)new EncodeURL(), (TextProcessor)null, "encode a URL using %xx") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] raw URL to upper; click Convert; then copy[ctrl-c] encoded URL from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] raw URL to the clipboard; click Convert Clipboard; then paste[ctrl-v] encoded URL.";
        }
    }, 
    DECODE_URL((Translator)new DecodeURL(), (TextProcessor)null, "decode a URL containing %xx") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] encoded URL to upper; click Convert; then copy[ctrl-c] plain decoded URL from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] encoded URL to the clipboard; click Convert Clipboard; then paste[ctrl-v] plain decoded URL.";
        }
    }, 
    PLAIN((Translator)new PlainReservedChars(), (TextProcessor)null, "Remove control chars (except tab)"), 
    COLLAPSE_SPACES((Translator)new PlainReservedChars(), (TextProcessor)new CollapseEmbeddedSpaces(), "Collapse multiple spaces"), 
    COLLAPSE_LINES((Translator)new PlainReservedChars(), (TextProcessor)new CollapseBlankLines(), "Collapse multiple blank lines"), 
    ALIGN((Translator)new PlainReservedChars(), (TextProcessor)new Align(), "Align text in columns"), 
    ALIGN_JAVA((Translator)new PlainReservedChars(), (TextProcessor)new AlignJava(), "Align Java source in columns"), 
    UPPER((Translator)new PlainReservedChars(), (TextProcessor)new ToUpperCase(), "TO UPPER CASE"), 
    LOWER((Translator)new PlainReservedChars(), (TextProcessor)new ToLowerCase(), "to lower case"), 
    TITLE((Translator)new PlainReservedChars(), (TextProcessor)new ToBookCase(), "To Book Title Case"), 
    JAVA_REGEX_SEARCH((Translator)new JavaRegexSearch(), (TextProcessor)null, "To Search Regex (Java)") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] raw text to upper; click Convert; then copy[ctrl-c] cooked search string from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Type or paste[ctrl-v] raw text to the clipboard; click Convert Clipboard; then paste[ctrl-v] cooked search string.";
        }
    }, 
    JAVA_REGEX_SEARCH_STRING((Translator)new JavaRegexSearchString(), (TextProcessor)null, "To Search Regex (Java String)") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] raw text to upper; click Convert; then copy[ctrl-c] cooked search string from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Type or paste[ctrl-v] raw text to the clipboard; click Convert Clipboard; then paste[ctrl-v] cooked search string.";
        }
    }, 
    JAVA_REGEX_SEARCH_CSV((Translator)new JavaRegexSearchCSV(), (TextProcessor)null, "To Search Regex (Java CSV)") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] raw text to upper; click Convert; then copy[ctrl-c] CSV field from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Type or paste[ctrl-v] raw text to the clipboard; click Convert Clipboard; then paste[ctrl-v] CSV field.";
        }
    }, 
    JAVA_REGEX_REPLACE((Translator)new JavaRegexReplace(), (TextProcessor)null, "To Replace Regex (Java)") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] raw text to upper; click Convert; then copy[ctrl-c] cooked replace string from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] raw text to the clipboard; click Convert Clipboard; then paste[ctrl-v] cooked replace string.";
        }
    }, 
    JAVA_REGEX_REPLACE_STRING((Translator)new JavaRegexReplaceString(), (TextProcessor)null, "To Replace Regex (Java String)") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] raw text to upper; click Convert; then copy[ctrl-c] cooked replace string from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] raw text to the clipboard; click Convert Clipboard; then paste[ctrl-v] cooked replace string.";
        }
    }, 
    FUNDUC_REGEX_SEARCH((Translator)new FunducRegexSearch(), (TextProcessor)null, "To Search Regex (Funduc)") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] raw text to upper; click Convert; then copy[ctrl-c] cooked search string from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] raw text to the clipboard; click Convert Clipboard; then paste[ctrl-v] cooked search string.";
        }
    }, 
    FUNDUC_REGEX_REPLACE((Translator)new FunducRegexReplace(), (TextProcessor)null, "To Replace Regex (Funduc)") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] raw text to upper; click Convert; then copy[ctrl-c] cooked replace string from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] raw text to the clipboard; click Convert Clipboard; then paste[ctrl-v] cooked replace string.";
        }
    }, 
    VSLICK_REGEX_SEARCH((Translator)new VSlickRegexSearch(), (TextProcessor)null, "To Search Regex (Vslick)") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] raw text to upper; click Convert; then copy[ctrl-c] cooked search string from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] raw text to the clipboard; click Convert Clipboard; then paste[ctrl-v] cooked search string.";
        }
    }, 
    VSLICK_REGEX_REPLACE((Translator)new VSlickRegexReplace(), (TextProcessor)null, "To Replace Regex (VSlick)") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] raw text to upper; click Convert; then copy[ctrl-c] cooked replace string from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] raw text to the clipboard; click Convert Clipboard; then paste[ctrl-v] cooked replace string.";
        }
    }, 
    SPAN((Translator)null, (TextProcessor)new Span(), "To determine the span, raw regex to bypass string") {
        public String getAppletInstructions() {
            return "Type or paste[ctrl-v] raw text to upper; click Convert; then copy[ctrl-c] regex from lower.";
        }
        
        public String getApplicationInstructions() {
            return "Copy[ctrl-c] raw text to the clipboard; click Convert Clipboard; then paste[ctrl-v] regex.";
        }
    };
    
    private static final Font monoFont;
    private static final Font regularFont;
    private final String desc;
    private final TextProcessor processor;
    private final Translator translator;
    
    public String getAppletInstructions() {
        return "Type or paste[ctrl-v] raw text to upper; click Convert; then copy[ctrl-c] tidied text from lower.";
    }
    
    public String getApplicationInstructions() {
        return "Copy[ctrl-c] raw text to the clipboard; click Convert Clipboard; then paste[ctrl-v] tidied text.";
    }
    
    public final Font getFont() {
        switch (this) {
            default: {
                return TargetOption.regularFont;
            }
            case ALIGN:
            case ALIGN_JAVA: {
                return TargetOption.monoFont;
            }
        }
    }
    
    public final TextProcessor getProcessor() {
        return this.processor;
    }
    
    public final Translator getTranslator() {
        return this.translator;
    }
    
    public final String toString() {
        return this.desc;
    }
    
    private TargetOption(final Translator translator, final TextProcessor processor, final String desc) {
        this.translator = translator;
        this.processor = processor;
        this.desc = desc;
    }
    
    static {
        monoFont = FontFactory.build("Monospaced", 0, 13);
        regularFont = FontFactory.build("Dialog", 0, 13);
    }
}
