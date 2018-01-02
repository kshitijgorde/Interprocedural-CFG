// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Locale;

public class XMLMessages implements XMLMessageProvider
{
    public static final String XML_DOMAIN = "http://www.w3.org/TR/1998/REC-xml-19980210";
    public static final String XMLNS_DOMAIN = "http://www.w3.org/TR/1999/REC-xml-names-19990114";
    private Locale fLocale;
    private ResourceBundle fResourceBundle;
    public static final int MSG_BAD_MAJORCODE = 0;
    public static final int MSG_FORMAT_FAILURE = 1;
    public static final int MSG_LESSTHAN_IN_ATTVALUE = 2;
    public static final int MSG_ROOT_ELEMENT_TYPE = 3;
    public static final int MSG_IDREFS_INVALID = 4;
    public static final int MSG_NMTOKENS_INVALID = 5;
    public static final int MSG_RESERVED_PITARGET = 6;
    public static final int MSG_SPACE_REQUIRED_IN_PI = 7;
    public static final int MSG_INVALID_CHAR_IN_PI = 8;
    public static final int MSG_DASH_DASH_IN_COMMENT = 9;
    public static final int MSG_INVALID_CHAR_IN_COMMENT = 10;
    public static final int MSG_INVALID_CHARREF = 11;
    public static final int MSG_INVALID_CHAR_IN_ATTVALUE = 12;
    public static final int MSG_QUOTE_REQUIRED_IN_ATTVALUE = 13;
    public static final int MSG_NAME_REQUIRED_IN_REFERENCE = 14;
    public static final int MSG_SEMICOLON_REQUIRED_IN_REFERENCE = 15;
    public static final int MSG_DIGIT_REQUIRED_IN_CHARREF = 16;
    public static final int MSG_HEXDIGIT_REQUIRED_IN_CHARREF = 17;
    public static final int MSG_SEMICOLON_REQUIRED_IN_CHARREF = 18;
    public static final int MSG_QUOTE_REQUIRED_IN_SYSTEMID = 19;
    public static final int MSG_INVALID_CHAR_IN_SYSTEMID = 20;
    public static final int MSG_QUOTE_REQUIRED_IN_PUBLICID = 21;
    public static final int MSG_INVALID_CHAR_IN_PUBLICID = 22;
    public static final int MSG_INCLUDESECT_UNTERMINATED = 23;
    public static final int MSG_IGNORESECT_UNTERMINATED = 24;
    public static final int MSG_INVALID_CHAR_IN_IGNORESECT = 25;
    public static final int MSG_ELEMENT_UNTERMINATED = 26;
    public static final int MSG_EQ_REQUIRED_IN_ATTRIBUTE = 27;
    public static final int MSG_ATTRIBUTE_NOT_UNIQUE = 28;
    public static final int MSG_ENCODINGDECL_REQUIRED = 29;
    public static final int MSG_VERSIONINFO_REQUIRED = 30;
    public static final int MSG_EQ_REQUIRED_IN_XMLDECL = 31;
    public static final int MSG_EQ_REQUIRED_IN_TEXTDECL = 32;
    public static final int MSG_QUOTE_REQUIRED_IN_XMLDECL = 33;
    public static final int MSG_QUOTE_REQUIRED_IN_TEXTDECL = 34;
    public static final int MSG_INVALID_CHAR_IN_XMLDECL = 35;
    public static final int MSG_INVALID_CHAR_IN_TEXTDECL = 36;
    public static final int MSG_VERSIONINFO_INVALID = 37;
    public static final int MSG_VERSION_NOT_SUPPORTED = 38;
    public static final int MSG_SPACE_REQUIRED_IN_TEXTDECL = 39;
    public static final int MSG_ENCODINGDECL_INVALID = 40;
    public static final int MSG_SDDECL_INVALID = 41;
    public static final int MSG_XMLDECL_UNTERMINATED = 42;
    public static final int MSG_TEXTDECL_UNTERMINATED = 43;
    public static final int MSG_INVALID_CHAR_IN_INTERNAL_SUBSET = 44;
    public static final int MSG_INVALID_CHAR_IN_EXTERNAL_SUBSET = 45;
    public static final int MSG_INVALID_CHAR_IN_ENTITYVALUE = 46;
    public static final int MSG_MIXED_CONTENT_UNTERMINATED = 47;
    public static final int MSG_NAME_REQUIRED_IN_PEREFERENCE = 48;
    public static final int MSG_SEMICOLON_REQUIRED_IN_PEREFERENCE = 49;
    public static final int MSG_EXTERNALID_REQUIRED = 50;
    public static final int MSG_PEREFERENCE_WITHIN_MARKUP = 51;
    public static final int MSG_INVALID_CHAR_IN_PROLOG = 52;
    public static final int MSG_INVALID_CHAR_IN_MISC = 53;
    public static final int MSG_INVALID_CHAR_IN_CDSECT = 54;
    public static final int MSG_INVALID_CHAR_IN_CONTENT = 55;
    public static final int MSG_ETAG_REQUIRED = 56;
    public static final int MSG_ETAG_UNTERMINATED = 57;
    public static final int MSG_ATTRIBUTE_NAME_REQUIRED_IN_ATTDEF = 58;
    public static final int MSG_ATTTYPE_REQUIRED_IN_ATTDEF = 59;
    public static final int MSG_PUBIDCHAR_ILLEGAL = 60;
    public static final int MSG_ENCODING_NOT_SUPPORTED = 61;
    public static final int MSG_ENTITY_NOT_DECLARED = 62;
    public static final int MSG_REFERENCE_TO_UNPARSED_ENTITY = 63;
    public static final int MSG_REFERENCE_TO_EXTERNAL_ENTITY = 64;
    public static final int MSG_XML_LANG_INVALID = 65;
    public static final int MSG_CDSECT_UNTERMINATED = 66;
    public static final int MSG_DUPLICATE_TYPE_IN_MIXED_CONTENT = 67;
    public static final int MSG_ELEMENT_ENTITY_MISMATCH = 68;
    public static final int MSG_ID_DEFAULT_TYPE_INVALID = 69;
    public static final int MSG_ENCODING_REQUIRED = 70;
    public static final int MSG_RECURSIVE_REFERENCE = 71;
    public static final int MSG_RECURSIVE_PEREFERENCE = 72;
    public static final int MSG_IMPROPER_DECLARATION_NESTING = 73;
    public static final int MSG_IMPROPER_GROUP_NESTING = 74;
    public static final int MSG_ID_INVALID = 75;
    public static final int MSG_ID_NOT_UNIQUE = 76;
    public static final int MSG_IDREF_INVALID = 77;
    public static final int MSG_NMTOKEN_INVALID = 78;
    public static final int MSG_ENTITY_INVALID = 79;
    public static final int MSG_ENTITIES_INVALID = 80;
    public static final int MSG_ELEMENT_WITH_ID_REQUIRED = 81;
    public static final int MSG_ATTRIBUTE_NOT_DECLARED = 82;
    public static final int MSG_ELEMENT_NOT_DECLARED = 83;
    public static final int MSG_AVAILABLE1 = 84;
    public static final int MSG_DUPLICATE_ATTDEF = 85;
    public static final int MSG_MORE_THAN_ONE_ID_ATTRIBUTE = 86;
    public static final int MSG_CONTENT_INVALID = 87;
    public static final int MSG_CONTENT_INCOMPLETE = 88;
    public static final int MSG_ELEMENT_ALREADY_DECLARED = 89;
    public static final int MSG_ATTRIBUTE_VALUE_NOT_IN_LIST = 90;
    public static final int MSG_AVAILABLE2 = 91;
    public static final int MSG_UNDECLARED_ELEMENT_IN_CONTENTSPEC = 92;
    public static final int MSG_FIXED_ATTVALUE_INVALID = 93;
    public static final int MSG_REQUIRED_ATTRIBUTE_NOT_SPECIFIED = 94;
    public static final int MSG_DEFAULTED_ATTRIBUTE_NOT_SPECIFIED = 95;
    public static final int MSG_AVAILABLE3 = 96;
    public static final int MSG_AVAILABLE4 = 97;
    public static final int MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN = 98;
    public static final int MSG_AVAILABLE5 = 99;
    public static final int MSG_SYSTEMID_UNTERMINATED = 100;
    public static final int MSG_PUBLICID_UNTERMINATED = 101;
    public static final int MSG_EXTERNAL_ENTITY_NOT_PERMITTED = 102;
    public static final int MSG_AVAILABLE6 = 103;
    public static final int MSG_XMLDECL_MUST_BE_FIRST = 104;
    public static final int MSG_TEXTDECL_MUST_BE_FIRST = 105;
    public static final int MSG_ELEMENTDECL_UNTERMINATED = 106;
    public static final int MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_PEDECL = 107;
    public static final int MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_ENTITYDECL = 108;
    public static final int MSG_SPACE_REQUIRED_BEFORE_PERCENT_IN_PEDECL = 109;
    public static final int MSG_ENTITY_NAME_REQUIRED_IN_ENTITYDECL = 110;
    public static final int MSG_SPACE_REQUIRED_AFTER_ENTITY_NAME_IN_ENTITYDECL = 111;
    public static final int MSG_ENTITYDECL_UNTERMINATED = 112;
    public static final int MSG_NOTATION_NAME_REQUIRED_FOR_UNPARSED_ENTITYDECL = 113;
    public static final int MSG_NOTATION_NOT_DECLARED_FOR_UNPARSED_ENTITYDECL = 114;
    public static final int MSG_NAME_REQUIRED_IN_NOTATIONTYPE = 115;
    public static final int MSG_NMTOKEN_REQUIRED_IN_ENUMERATION = 116;
    public static final int MSG_NOTATION_NOT_DECLARED_FOR_NOTATIONTYPE_ATTRIBUTE = 117;
    public static final int MSG_NOTATIONTYPE_UNTERMINATED = 118;
    public static final int MSG_ENUMERATION_UNTERMINATED = 119;
    public static final int MSG_NOTATION_NAME_REQUIRED_IN_NOTATIONDECL = 120;
    public static final int MSG_MORE_THAN_ONE_NOTATION_ATTRIBUTE = 121;
    public static final int MSG_NOTATIONDECL_UNTERMINATED = 122;
    public static final int MSG_ATTVALUE_CHANGED_DURING_NORMALIZATION_WHEN_STANDALONE = 123;
    public static final int MSG_CDEND_IN_CONTENT = 124;
    public static final int MSG_ELEMENT_TYPE_REQUIRED_IN_ATTLISTDECL = 125;
    public static final int MSG_TWO_COLONS_IN_QNAME = 126;
    public static final int MSG_MARKUP_NOT_RECOGNIZED_IN_CONTENT = 127;
    public static final int MSG_MARKUP_NOT_RECOGNIZED_IN_MISC = 128;
    public static final int MSG_MARKUP_NOT_RECOGNIZED_IN_PROLOG = 129;
    public static final int MSG_OPEN_PAREN_REQUIRED_IN_NOTATIONTYPE = 130;
    public static final int MSG_PITARGET_REQUIRED = 131;
    public static final int MSG_REFERENCE_TO_EXTERNALLY_DECLARED_ENTITY_WHEN_STANDALONE = 132;
    public static final int MSG_URI_FRAGMENT_IN_SYSTEMID = 133;
    public static final int MSG_ROOT_ELEMENT_REQUIRED = 134;
    public static final int MSG_SPACE_REQUIRED_AFTER_FIXED_IN_DEFAULTDECL = 135;
    public static final int MSG_SPACE_REQUIRED_AFTER_NOTATION_IN_NOTATIONTYPE = 136;
    public static final int MSG_SPACE_REQUIRED_AFTER_NOTATION_NAME_IN_NOTATIONDECL = 137;
    public static final int MSG_SPACE_REQUIRED_BEFORE_ATTRIBUTE_NAME_IN_ATTDEF = 138;
    public static final int MSG_SPACE_REQUIRED_BEFORE_ATTTYPE_IN_ATTDEF = 139;
    public static final int MSG_SPACE_REQUIRED_BEFORE_DEFAULTDECL_IN_ATTDEF = 140;
    public static final int MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ATTLISTDECL = 141;
    public static final int MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_NOTATIONDECL = 142;
    public static final int MSG_WHITE_SPACE_IN_ELEMENT_CONTENT_WHEN_STANDALONE = 143;
    public static final int MSG_XML_SPACE_DECLARATION_ILLEGAL = 144;
    public static final int MSG_CLOSE_PAREN_REQUIRED_IN_MIXED = 145;
    public static final int MSG_CONTENTSPEC_REQUIRED_IN_ELEMENTDECL = 146;
    public static final int MSG_DOCTYPEDECL_UNTERMINATED = 147;
    public static final int MSG_ELEMENT_TYPE_REQUIRED_IN_ELEMENTDECL = 148;
    public static final int MSG_ELEMENT_TYPE_REQUIRED_IN_MIXED_CONTENT = 149;
    public static final int MSG_MARKUP_NOT_RECOGNIZED_IN_DTD = 150;
    public static final int MSG_ATTRIBUTE_VALUE_UNTERMINATED = 151;
    public static final int MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN = 152;
    public static final int MSG_ROOT_ELEMENT_TYPE_REQUIRED = 153;
    public static final int MSG_SPACE_REQUIRED_AFTER_PUBIDLITERAL_IN_EXTERNALID = 154;
    public static final int MSG_SPACE_REQUIRED_BEFORE_CONTENTSPEC_IN_ELEMENTDECL = 155;
    public static final int MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ELEMENTDECL = 156;
    public static final int MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_UNPARSED_ENTITYDECL = 157;
    public static final int MSG_SPACE_REQUIRED_BEFORE_PUBIDLITERAL_IN_EXTERNALID = 158;
    public static final int MSG_SPACE_REQUIRED_BEFORE_ROOT_ELEMENT_TYPE_IN_DOCTYPEDECL = 159;
    public static final int MSG_SPACE_REQUIRED_BEFORE_SYSTEMLITERAL_IN_EXTERNALID = 160;
    public static final int MSG_REFERENCE_NOT_IN_ONE_ENTITY = 161;
    public static final int MSG_COMMENT_NOT_IN_ONE_ENTITY = 162;
    public static final int MSG_COMMENT_UNTERMINATED = 163;
    public static final int MSG_PI_UNTERMINATED = 164;
    public static final int MSG_PI_NOT_IN_ONE_ENTITY = 165;
    public static final int MSG_REFERENCE_UNTERMINATED = 166;
    public static final int MSG_PREFIX_DECLARED = 167;
    public static final int MSG_ATT_DEFAULT_INVALID = 168;
    public static final int MSG_GENERIC_SCHEMA_ERROR = 169;
    public static final int MSG_MAX_CODE = 200;
    private static final String[] fgMessageKeys;
    public static final int VC_ROOT_ELEMENT_TYPE = 1;
    public static final int VC_IDREF = 2;
    public static final int VC_NAME_TOKEN = 3;
    public static final int P17_RESERVED_PITARGET = 4;
    public static final int P16_WHITESPACE_REQUIRED = 5;
    public static final int P16_INVALID_CHARACTER = 6;
    public static final int P15_DASH_DASH = 7;
    public static final int P15_INVALID_CHARACTER = 8;
    public static final int WFC_LEGAL_CHARACTER = 9;
    public static final int P10_INVALID_CHARACTER = 10;
    public static final int WFC_NO_LESSTHAN_IN_ATTVALUE = 11;
    public static final int P10_QUOTE_REQUIRED = 12;
    public static final int P68_NAME_REQUIRED = 13;
    public static final int P68_SEMICOLON_REQUIRED = 14;
    public static final int P66_DIGIT_REQUIRED = 15;
    public static final int P66_HEXDIGIT_REQUIRED = 16;
    public static final int P66_SEMICOLON_REQUIRED = 17;
    public static final int P11_QUOTE_REQUIRED = 18;
    public static final int P11_INVALID_CHARACTER = 19;
    public static final int P12_QUOTE_REQUIRED = 20;
    public static final int P12_INVALID_CHARACTER = 21;
    public static final int P62_UNTERMINATED = 22;
    public static final int P63_UNTERMINATED = 23;
    public static final int P65_INVALID_CHARACTER = 24;
    public static final int P40_UNTERMINATED = 25;
    public static final int P41_EQ_REQUIRED = 26;
    public static final int WFC_UNIQUE_ATT_SPEC = 27;
    public static final int P77_ENCODINGDECL_REQUIRED = 28;
    public static final int P23_VERSIONINFO_REQUIRED = 29;
    public static final int P24_EQ_REQUIRED = 30;
    public static final int P32_EQ_REQUIRED = 31;
    public static final int P80_EQ_REQUIRED = 32;
    public static final int P24_QUOTE_REQUIRED = 33;
    public static final int P32_QUOTE_REQUIRED = 34;
    public static final int P80_QUOTE_REQUIRED = 35;
    public static final int P26_INVALID_CHARACTER = 36;
    public static final int P32_INVALID_CHARACTER = 37;
    public static final int P81_INVALID_CHARACTER = 38;
    public static final int P26_INVALID_VALUE = 39;
    public static final int P26_NOT_SUPPORTED = 40;
    public static final int P80_WHITESPACE_REQUIRED = 41;
    public static final int P81_INVALID_VALUE = 42;
    public static final int P32_INVALID_VALUE = 43;
    public static final int P23_UNTERMINATED = 44;
    public static final int P77_UNTERMINATED = 45;
    public static final int P28_INVALID_CHARACTER = 46;
    public static final int P30_INVALID_CHARACTER = 47;
    public static final int P9_INVALID_CHARACTER = 48;
    public static final int P51_UNTERMINATED = 49;
    public static final int P69_NAME_REQUIRED = 50;
    public static final int P69_SEMICOLON_REQUIRED = 51;
    public static final int P75_INVALID = 52;
    public static final int WFC_PES_IN_INTERNAL_SUBSET = 53;
    public static final int P22_INVALID_CHARACTER = 54;
    public static final int P27_INVALID_CHARACTER = 55;
    public static final int P20_INVALID_CHARACTER = 56;
    public static final int P43_INVALID_CHARACTER = 57;
    public static final int P39_UNTERMINATED = 58;
    public static final int P42_UNTERMINATED = 59;
    public static final int P81_NOT_SUPPORTED = 60;
    public static final int WFC_ENTITY_DECLARED = 61;
    public static final int VC_ENTITY_DECLARED = 62;
    public static final int WFC_PARSED_ENTITY = 63;
    public static final int WFC_NO_EXTERNAL_ENTITY_REFERENCES = 64;
    public static final int P33_INVALID = 65;
    public static final int P18_UNTERMINATED = 66;
    public static final int VC_NO_DUPLICATE_TYPES = 67;
    public static final int P78_NOT_WELLFORMED = 68;
    public static final int VC_ID_ATTRIBUTE_DEFAULT = 69;
    public static final int P53_NAME_REQUIRED = 70;
    public static final int P53_ATTTYPE_REQUIRED = 71;
    public static final int P81_REQUIRED = 72;
    public static final int WFC_NO_RECURSION = 73;
    public static final int VC_PROPER_DECLARATION_PE_NESTING = 74;
    public static final int VC_PROPER_GROUP_PE_NESTING = 75;
    public static final int VC_ID = 76;
    public static final int VC_ENTITY_NAME = 77;
    public static final int VC_ATTRIBUTE_VALUE_TYPE = 78;
    public static final int VC_ELEMENT_VALID = 79;
    public static final int VC_STANDALONE_DOCUMENT_DECLARATION = 80;
    public static final int VC_ONE_ID_PER_ELEMENT_TYPE = 81;
    public static final int VC_UNIQUE_ELEMENT_TYPE_DECLARATION = 82;
    public static final int P45_UNDECLARED_ELEMENT_IN_CONTENTSPEC = 83;
    public static final int VC_NOTATION_ATTRIBUTES = 84;
    public static final int P53_DUPLICATE = 85;
    public static final int VC_ENUMERATION = 86;
    public static final int VC_FIXED_ATTRIBUTE_DEFAULT = 87;
    public static final int VC_REQUIRED_ATTRIBUTE = 88;
    public static final int VC_NOTATION_DECLARED = 89;
    public static final int P58_NAME_REQUIRED = 90;
    public static final int P58_UNTERMINATED = 91;
    public static final int P59_NMTOKEN_REQUIRED = 92;
    public static final int P59_UNTERMINATED = 93;
    public static final int P70_SPACE = 94;
    public static final int P70_REQUIRED_NAME = 95;
    public static final int P70_REQUIRED_SPACE = 96;
    public static final int P71_UNTERMINATED = 97;
    public static final int P72_SPACE = 98;
    public static final int P72_UNTERMINATED = 99;
    public static final int P76_REQUIRED = 100;
    public static final int P82_NAME_REQUIRED = 101;
    public static final int P82_SPACE_REQUIRED = 102;
    public static final int P82_UNTERMINATED = 103;
    public static final int P14_INVALID = 104;
    public static final int P16_PITARGET_REQUIRED = 105;
    public static final int P16_REQUIRED = 106;
    public static final int P1_ELEMENT_REQUIRED = 107;
    public static final int P22_NOT_RECOGNIZED = 108;
    public static final int P27_NOT_RECOGNIZED = 109;
    public static final int P43_NOT_RECOGNIZED = 110;
    public static final int P52_ELEMENT_TYPE_REQUIRED = 111;
    public static final int P52_SPACE_REQUIRED = 112;
    public static final int P53_SPACE_REQUIRED = 113;
    public static final int P58_OPEN_PAREN_REQUIRED = 114;
    public static final int P58_SPACE_REQUIRED = 115;
    public static final int P60_SPACE_REQUIRED = 116;
    public static final int S2_10_DECLARATION_ILLEGAL = 117;
    public static final int P39_ELEMENT_TYPE_REQUIRED = 118;
    public static final int P28_ROOT_ELEMENT_TYPE_REQUIRED = 119;
    public static final int P28_SPACE_REQUIRED = 120;
    public static final int P28_UNTERMINATED = 121;
    public static final int P29_NOT_RECOGNIZED = 122;
    public static final int P45_CONTENTSPEC_REQUIRED = 123;
    public static final int P45_ELEMENT_TYPE_REQUIRED = 124;
    public static final int P45_SPACE_REQUIRED = 125;
    public static final int P45_UNTERMINATED = 126;
    public static final int P47_CLOSE_PAREN_REQUIRED = 127;
    public static final int P47_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED = 128;
    public static final int P51_CLOSE_PAREN_REQUIRED = 129;
    public static final int P51_ELEMENT_TYPE_REQUIRED = 130;
    public static final int P75_SPACE_REQUIRED = 131;
    public static final int P76_SPACE_REQUIRED = 132;
    public static final int P15_UNTERMINATED = 133;
    public static final int P16_UNTERMINATED = 134;
    public static final int P67_UNTERMINATED = 135;
    public static final int P10_UNTERMINATED = 136;
    public static final int P22_XMLDECL_MUST_BE_FIRST = 137;
    public static final int P30_TEXTDECL_MUST_BE_FIRST = 138;
    public static final int P5_INVALID_CHARACTER = 139;
    public static final int P11_UNTERMINATED = 140;
    public static final int P12_UNTERMINATED = 141;
    public static final int P11_URI_FRAGMENT = 142;
    public static final int VC_ONE_NOTATION_PER_ELEMENT_TYPE = 143;
    public static final int NC_PREFIX_DECLARED = 144;
    public static final int VC_ATTRIBUTE_DEFAULT_LEGAL = 145;
    public static final int SCHEMA_GENERIC_ERROR = 146;
    public static final int CONSTRAINT_MAX_CODE = 200;
    
    public XMLMessages() {
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
                this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.msg.XMLMessages", locale);
            }
            if (this.fResourceBundle == null) {
                this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.msg.XMLMessages");
            }
        }
        if (n < 0 || n >= XMLMessages.fgMessageKeys.length - 1) {
            n = 0;
            b = true;
        }
        final String s = XMLMessages.fgMessageKeys[n];
        String s2 = this.fResourceBundle.getString(s);
        if (array != null) {
            try {
                s2 = MessageFormat.format(s2, array);
            }
            catch (Exception ex) {
                s2 = this.fResourceBundle.getString(XMLMessages.fgMessageKeys[1]) + " " + this.fResourceBundle.getString(s);
            }
        }
        if (b) {
            throw new RuntimeException(s2);
        }
        return s2;
    }
    
    static {
        fgMessageKeys = new String[] { "BadMajorCode", "FormatFailed", "LessthanInAttValue", "RootElementTypeMustMatchDoctypedecl", "IDREFSInvalid", "NMTOKENSInvalid", "ReservedPITarget", "SpaceRequiredInPI", "InvalidCharInPI", "DashDashInComment", "InvalidCharInComment", "InvalidCharRef", "InvalidCharInAttValue", "QuoteRequiredInAttValue", "NameRequiredInReference", "SemicolonRequiredInReference", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "QuoteRequiredInSystemID", "InvalidCharInSystemID", "QuoteRequiredInPublicID", "InvalidCharInPublicID", "IncludeSectUnterminated", "IgnoreSectUnterminated", "InvalidCharInIgnoreSect", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "EncodingDeclRequired", "VersionInfoRequired", "EqRequiredInXMLDecl", "EqRequiredInTextDecl", "QuoteRequiredInXMLDecl", "QuoteRequiredInTextDecl", "InvalidCharInXMLDecl", "InvalidCharInTextDecl", "VersionInfoInvalid", "VersionNotSupported", "SpaceRequiredInTextDecl", "EncodingDeclInvalid", "SDDeclInvalid", "XMLDeclUnterminated", "TextDeclUnterminated", "InvalidCharInInternalSubset", "InvalidCharInExternalSubset", "InvalidCharInEntityValue", "MixedContentUnterminated", "NameRequiredInPEReference", "SemicolonRequiredInPEReference", "ExternalIDRequired", "PEReferenceWithinMarkup", "InvalidCharInProlog", "InvalidCharInMisc", "InvalidCharInCDSect", "InvalidCharInContent", "ETagRequired", "ETagUnterminated", "AttNameRequiredInAttDef", "AttTypeRequiredInAttDef", "PubidCharIllegal", "EncodingNotSupported", "EntityNotDeclared", "ReferenceToUnparsedEntity", "ReferenceToExternalEntity", "XMLLangInvalid", "CDSectUnterminated", "DuplicateTypeInMixedContent", "ElementEntityMismatch", "IDDefaultTypeInvalid", "EncodingRequired", "RecursiveReference", "RecursivePEReference", "ImproperDeclarationNesting", "ImproperGroupNesting", "IDInvalid", "IDNotUnique", "IDREFInvalid", "NMTOKENInvalid", "ENTITYInvalid", "ENTITIESInvalid", "MSG_ELEMENT_WITH_ID_REQUIRED", "MSG_ATTRIBUTE_NOT_DECLARED", "MSG_ELEMENT_NOT_DECLARED", "MSG_AVAILABLE1", "MSG_DUPLICATE_ATTDEF", "MSG_MORE_THAN_ONE_ID_ATTRIBUTE", "MSG_CONTENT_INVALID", "MSG_CONTENT_INCOMPLETE", "MSG_ELEMENT_ALREADY_DECLARED", "MSG_ATTRIBUTE_VALUE_NOT_IN_LIST", "MSG_AVAILABLE2", "UndeclaredElementInContentSpec", "MSG_FIXED_ATTVALUE_INVALID", "MSG_REQUIRED_ATTRIBUTE_NOT_SPECIFIED", "MSG_DEFAULTED_ATTRIBUTE_NOT_SPECIFIED", "MSG_AVAILABLE3", "MSG_AVAILABLE4", "MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN", "MSG_AVAILABLE5", "SystemIDUnterminated", "PublicIDUnterminated", "MSG_EXTERNAL_ENTITY_NOT_PERMITTED", "MSG_AVAILABLE6", "XMLDeclMustBeFirst", "TextDeclMustBeFirst", "ElementDeclUnterminated", "MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_PEDECL", "MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_ENTITYDECL", "MSG_SPACE_REQUIRED_BEFORE_PERCENT_IN_PEDECL", "MSG_ENTITY_NAME_REQUIRED_IN_ENTITYDECL", "MSG_SPACE_REQUIRED_AFTER_ENTITY_NAME_IN_ENTITYDECL", "EntityDeclUnterminated", "MSG_NOTATION_NAME_REQUIRED_FOR_UNPARSED_ENTITYDECL", "MSG_NOTATION_NOT_DECLARED_FOR_UNPARSED_ENTITYDECL", "MSG_NAME_REQUIRED_IN_NOTATIONTYPE", "MSG_NMTOKEN_REQUIRED_IN_ENUMERATION", "MSG_NOTATION_NOT_DECLARED_FOR_NOTATIONTYPE_ATTRIBUTE", "NotationTypeUnterminated", "EnumerationUnterminated", "MSG_NOTATION_NAME_REQUIRED_IN_NOTATIONDECL", "MSG_MORE_THAN_ONE_NOTATION_ATTRIBUTE", "NotationDeclUnterminated", "MSG_ATTVALUE_CHANGED_DURING_NORMALIZATION_WHEN_STANDALONE", "CDEndInContent", "MSG_ELEMENT_TYPE_REQUIRED_IN_ATTLISTDECL", "TwoColonsInQName", "MarkupNotRecognizedInContent", "MarkupNotRecognizedInMisc", "MarkupNotRecognizedInProlog", "MSG_OPEN_PAREN_REQUIRED_IN_NOTATIONTYPE", "PITargetRequired", "MSG_REFERENCE_TO_EXTERNALLY_DECLARED_ENTITY_WHEN_STANDALONE", "MSG_URI_FRAGMENT_IN_SYSTEMID", "RootElementRequired", "MSG_SPACE_REQUIRED_AFTER_FIXED_IN_DEFAULTDECL", "MSG_SPACE_REQUIRED_AFTER_NOTATION_IN_NOTATIONTYPE", "MSG_SPACE_REQUIRED_AFTER_NOTATION_NAME_IN_NOTATIONDECL", "MSG_SPACE_REQUIRED_BEFORE_ATTRIBUTE_NAME_IN_ATTDEF", "MSG_SPACE_REQUIRED_BEFORE_ATTTYPE_IN_ATTDEF", "MSG_SPACE_REQUIRED_BEFORE_DEFAULTDECL_IN_ATTDEF", "MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ATTLISTDECL", "MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_NOTATIONDECL", "MSG_WHITE_SPACE_IN_ELEMENT_CONTENT_WHEN_STANDALONE", "MSG_XML_SPACE_DECLARATION_ILLEGAL", "MSG_CLOSE_PAREN_REQUIRED_IN_MIXED", "MSG_CONTENTSPEC_REQUIRED_IN_ELEMENTDECL", "DoctypedeclUnterminated", "MSG_ELEMENT_TYPE_REQUIRED_IN_ELEMENTDECL", "MSG_ELEMENT_TYPE_REQUIRED_IN_MIXED_CONTENT", "MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", "AttributeValueUnterminated", "MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN", "MSG_ROOT_ELEMENT_TYPE_REQUIRED", "MSG_SPACE_REQUIRED_AFTER_PUBIDLITERAL_IN_EXTERNALID", "MSG_SPACE_REQUIRED_BEFORE_CONTENTSPEC_IN_ELEMENTDECL", "MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ELEMENTDECL", "MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_UNPARSED_ENTITYDECL", "MSG_SPACE_REQUIRED_BEFORE_PUBIDLITERAL_IN_EXTERNALID", "MSG_SPACE_REQUIRED_BEFORE_ROOT_ELEMENT_TYPE_IN_DOCTYPEDECL", "MSG_SPACE_REQUIRED_BEFORE_SYSTEMLITERAL_IN_EXTERNALID", "ReferenceNotInOneEntity", "CommentNotInOneEntity", "CommentUnterminated", "PIUnterminated", "PINotInOneEntity", "ReferenceUnterminated", "PrefixDeclared", "MSG_ATT_DEFAULT_INVALID", "MSG_GENERIC_SCHEMA_ERROR", "" };
    }
}
