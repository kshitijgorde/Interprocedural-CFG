// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.schema;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Locale;
import org.apache.xerces.utils.XMLMessageProvider;

public class SchemaMessageProvider implements XMLMessageProvider
{
    public static final String SCHEMA_DOMAIN = "http://www.w3.org/TR/xml-schema-1";
    private Locale fLocale;
    private ResourceBundle fResourceBundle;
    public static final int MSG_BAD_MAJORCODE = 0;
    public static final int MSG_FORMAT_FAILURE = 1;
    public static final int NoValidatorFor = 2;
    public static final int IncorrectDatatype = 3;
    public static final int AttMissingType = 4;
    public static final int NotADatatype = 5;
    public static final int TextOnlyContentWithType = 6;
    public static final int FeatureUnsupported = 7;
    public static final int NestedOnlyInElemOnly = 8;
    public static final int EltRefOnlyInMixedElemOnly = 9;
    public static final int OnlyInEltContent = 10;
    public static final int OrderIsAll = 11;
    public static final int DatatypeWithType = 12;
    public static final int DatatypeQualUnsupported = 13;
    public static final int GroupContentRestricted = 14;
    public static final int UnknownBaseDatatype = 15;
    public static final int BadAttWithRef = 16;
    public static final int NoContentForRef = 17;
    public static final int IncorrectDefaultType = 18;
    public static final int IllegalAttContent = 19;
    public static final int ValueNotInteger = 20;
    public static final int DatatypeError = 21;
    public static final int TypeAlreadySet = 22;
    public static final int GenericError = 23;
    public static final int UnclassifiedError = 24;
    public static final int ContentError = 25;
    public static final int AnnotationError = 26;
    public static final int ListUnionRestrictionError = 27;
    public static final int ProhibitedAttributePresent = 28;
    public static final int UniqueNotEnoughValues = 29;
    public static final int KeyNotEnoughValues = 30;
    public static final int KeyRefNotEnoughValues = 31;
    public static final int DuplicateField = 32;
    public static final int DuplicateUnique = 33;
    public static final int DuplicateKey = 34;
    public static final int KeyNotFound = 35;
    public static final int UnknownField = 36;
    public static final int KeyRefReferNotFound = 37;
    public static final int MSG_MAX_CODE = 38;
    public static final int MSG_NONE = 0;
    public static final String[] fgMessageKeys;
    
    public SchemaMessageProvider() {
        this.fLocale = null;
        this.fResourceBundle = null;
    }
    
    public void setLocale(final Locale fLocale) {
        this.fLocale = fLocale;
    }
    
    public Locale getLocale() {
        return this.fLocale;
    }
    
    public String createMessage(final Locale locale, int n, final int n2, final Object[] array) {
        boolean b = false;
        if (this.fResourceBundle == null || locale != this.fLocale) {
            if (locale != null) {
                this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.msg.SchemaMessages", locale);
            }
            if (this.fResourceBundle == null) {
                this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.msg.SchemaMessages");
            }
        }
        if (n < 0 || n >= SchemaMessageProvider.fgMessageKeys.length) {
            n = 0;
            b = true;
        }
        final String s = SchemaMessageProvider.fgMessageKeys[n];
        String s2 = this.fResourceBundle.getString(s);
        if (array != null) {
            try {
                s2 = MessageFormat.format(s2, array);
            }
            catch (Exception ex) {
                s2 = this.fResourceBundle.getString(SchemaMessageProvider.fgMessageKeys[1]) + " " + this.fResourceBundle.getString(s);
            }
        }
        if (b) {
            throw new RuntimeException(s2);
        }
        return s2;
    }
    
    static {
        fgMessageKeys = new String[] { "BadMajorCode", "FormatFailed", "NoValidatorFor", "IncorrectDatatype", "AttMissingType", "NotADatatype", "TextOnlyContentWithType", "FeatureUnsupported", "NestedOnlyInElemOnly", "EltRefOnlyInMixedElemOnly", "OnlyInEltContent", "OrderIsAll", "DatatypeWithType", "DatatypeQualUnsupported", "GroupContentRestricted", "UnknownBaseDatatype", "BadAttWithRef", "NoContentForRef", "IncorrectDefaultType", "IllegalAttContent", "ValueNotInteger", "DatatypeError", "TypeAlreadySet", "GenericError", "UnclassifiedError", "ContentError", "AnnotationError", "ListUnionRestrictionError", "ProhibitedAttributePresent", "UniqueNotEnoughValues", "KeyNotEnoughValues", "KeyRefNotEnoughValues", "DuplicateField", "DuplicateUnique", "DuplicateKey", "KeyNotFound", "UnknownField", "KeyRefReferNotFound" };
    }
}
