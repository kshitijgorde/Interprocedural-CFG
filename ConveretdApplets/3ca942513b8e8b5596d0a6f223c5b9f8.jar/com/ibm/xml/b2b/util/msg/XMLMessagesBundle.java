// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.msg;

public final class XMLMessagesBundle extends ErrorMessageBundle
{
    private static final Object[][] CONTENTS;
    private static final String[] MESSAGE_KEYS;
    
    protected Object[][] getContents() {
        return XMLMessagesBundle.CONTENTS;
    }
    
    public String getString(final int n) {
        return this.getString(XMLMessagesBundle.MESSAGE_KEYS[n]);
    }
    
    static {
        CONTENTS = new Object[][] { { "BadMessageKey", "The error message corresponding to the message key can not be found." }, { "FormatFailed", "An internal error occurred while formatting the following message:\n  " }, { "QuoteRequiredInEntityValue", "The entity value must begin with either a single or double quote character." }, { "InvalidCharInEntityValue", "An invalid XML character (Unicode: 0x{0}) was found in the literal entity value." }, { "InvalidCharInSystemID", "An invalid XML character (Unicode: 0x{0}) was found in the system identifier." }, { "InvalidCharInPublicID", "An invalid XML character (Unicode: 0x{0}) was found in the public identifier." }, { "InvalidCharInDoctypedecl", "An invalid XML character (Unicode: 0x{0}) was found in the document type declaration." }, { "InvalidCharInInternalSubset", "An invalid XML character (Unicode: 0x{0}) was found in the internal subset of the DTD." }, { "InvalidCharInExternalSubset", "An invalid XML character (Unicode: 0x{0}) was found in the external subset of the DTD." }, { "InvalidCharInIgnoreSect", "An invalid XML character (Unicode: 0x{0}) was found in the excluded conditional section." }, { "QuoteRequiredInSystemID", "The system identifier must begin with either a single or double quote character." }, { "SystemIDUnterminated", "The system identifier must end with the matching quote character." }, { "QuoteRequiredInPublicID", "The public identifier must begin with either a single or double quote character." }, { "PublicIDUnterminated", "The public identifier must end with the matching quote character." }, { "PubidCharIllegal", "The character (Unicode: 0x{0}) is not permitted in the public identifier." }, { "EntityValueUnterminated", "The literal value for the entity must end with the matching quote character." }, { "SpaceRequiredBeforeRootElementTypeInDoctypedecl", "White space is required after \"<!DOCTYPE\" in the document type declaration." }, { "RootElementTypeRequired", "The root element type must appear after \"<!DOCTYPE\" in the document type declaration." }, { "DoctypedeclUnterminated", "The document type declaration for root element type \"{0}\" must end with ''>''." }, { "PEReferenceWithinMarkup", "The parameter entity reference \"%{0};\" cannot occur within markup in the internal subset of the DTD." }, { "PEReferenceContainsIncompleteMarkup", "Parameter entity references between declarations must consist of complete markup." }, { "MarkupNotRecognizedInDTD", "The markup declarations contained or pointed to by the document type declaration must be well-formed." }, { "XMLSpaceDeclarationIllegal", "The attribute declaration for \"xml:space\" must be given as an enumerated type whose only possible values are \"default\" and \"preserve\"." }, { "SpaceRequiredBeforeElementTypeInElementDecl", "White space is required after \"<!ELEMENT\" in the element type declaration." }, { "ElementTypeRequiredInElementDecl", "The element type is required in the element type declaration." }, { "SpaceRequiredBeforeContentspecInElementDecl", "White space is required after the element type \"{0}\" in the element type declaration." }, { "ContentspecRequiredInElementDecl", "The constraint is required after the element type \"{0}\" in the element type declaration." }, { "ElementDeclUnterminated", "The declaration for element type \"{0}\" must end with ''>''." }, { "OpenParenOrElementTypeRequiredInChildren", "A ''('' character or an element type is required in the declaration of element type \"{0}\"." }, { "CloseParenRequiredInChildren", "A '')'' is required in the declaration of element type \"{0}\"." }, { "ElementTypeRequiredInMixedContent", "An element type is required in the declaration of element type \"{0}\"." }, { "CloseParenRequiredInMixedContent", "A '')'' is required in the declaration of element type \"{0}\"." }, { "MixedContentUnterminated", "The mixed content model for \"{0}\" must end with \")*\" when the types of child elements are constrained." }, { "SpaceRequiredBeforeElementTypeInAttlistDecl", "White space is required after \"<!ATTLIST\" in the attribute-list declaration." }, { "ElementTypeRequiredInAttlistDecl", "The element type is required in the attribute-list declaration." }, { "SpaceRequiredBeforeAttributeNameInAttDef", "White space is required before the attribute name in the attribute-list declaration for element \"{0}\"." }, { "AttributeNameRequiredInAttDef", "The attribute name must be specified in the attribute-list declaration for element \"{0}\"." }, { "SpaceRequiredBeforeAttTypeInAttDef", "White space is required before the attribute type in the declaration of attribute \"{1}\" for element \"{0}\"." }, { "AttTypeRequiredInAttDef", "The attribute type is required in the declaration of attribute \"{1}\" for element \"{0}\"." }, { "SpaceRequiredBeforeDefaultDeclInAttDef", "White space is required before the attribute default in the declaration of attribute \"{1}\" for element \"{0}\"." }, { "DefaultDeclRequiredInAttDef", "The attribute default is required in the declaration of attribute \"{1}\" for element \"{0}\"." }, { "SpaceRequiredAfterNOTATIONInNotationType", "White space must appear after \"NOTATION\" in the \"{1}\" attribute declaration." }, { "OpenParenRequiredInNotationType", "The ''('' character must follow \"NOTATION\" in the \"{1}\" attribute declaration." }, { "NameRequiredInNotationType", "The notation name is required in the notation type list for the \"{1}\" attribute declaration." }, { "NotationTypeUnterminated", "The notation type list must end with '')'' in the \"{1}\" attribute declaration." }, { "NmtokenRequiredInEnumeration", "The name token is required in the enumerated type list for the \"{1}\" attribute declaration." }, { "EnumerationUnterminated", "The enumerated type list must end with '')'' in the \"{1}\" attribute declaration." }, { "SpaceRequiredAfterFIXEDInDefaultDecl", "White space must appear after \"FIXED\" in the \"{1}\" attribute declaration." }, { "IncludeSectUnterminated", "The included conditional section must end with \"]]>\"." }, { "IgnoreSectUnterminated", "The excluded conditional section must end with \"]]>\"." }, { "NameRequiredInPEReference", "The entity name must immediately follow the '%' in the parameter entity reference." }, { "SemicolonRequiredInPEReference", "The parameter entity reference \"%{0};\" must end with the '';'' delimiter." }, { "SpaceRequiredBeforeEntityNameInEntityDecl", "White space is required after \"<!ENTITY\" in the entity declaration." }, { "SpaceRequiredBeforePercentInPEDecl", "White space is required between \"<!ENTITY\" and the '%' character in the parameter entity declaration." }, { "SpaceRequiredBeforeEntityNameInPEDecl", "White space is required between the '%' and the entity name in the parameter entity declaration." }, { "EntityNameRequiredInEntityDecl", "The name of the entity is required in the entity declaration." }, { "SpaceRequiredAfterEntityNameInEntityDecl", "White space is required between the entity name \"{0}\" and the definition in the entity declaration." }, { "SpaceRequiredBeforeNDataInUnparsedEntityDecl", "White space is required before \"NDATA\" in the declaration for the entity \"{0}\"." }, { "SpaceRequiredBeforeNotationNameInUnparsedEntityDecl", "White space is required between \"NDATA\" and the notation name in the declaration for the entity \"{0}\"." }, { "NotationNameRequiredInUnparsedEntityDecl", "The notation name is required after \"NDATA\" in the declaration for the entity \"{0}\"." }, { "EntityDeclUnterminated", "The declaration for the entity \"{0}\" must end with ''>''." }, { "ExternalIDRequired", "The external entity declaration must begin with either \"SYSTEM\" or \"PUBLIC\"." }, { "SpaceRequiredBeforePubidLiteralInExternalID", "White space is required between \"PUBLIC\" and the public identifier." }, { "SpaceRequiredAfterPubidLiteralInExternalID", "White space is required between the public identifier and the system identifier." }, { "SpaceRequiredBeforeSystemLiteralInExternalID", "White space is required between \"SYSTEM\" and the system identifier." }, { "URIFragmentInSystemID", "The fragment identifier should not be specified as part of the system identifier \"{0}\"." }, { "SpaceRequiredBeforeNotationNameInNotationDecl", "White space is required after \"<!NOTATION\" in the notation declaration." }, { "NotationNameRequiredInNotationDecl", "The name of the notation is required in the notation declaration." }, { "SpaceRequiredAfterNotationNameInNotationDecl", "White space is required after the notation name \"{0}\" in the notation declaration." }, { "NotationDeclUnterminated", "The declaration for the notation \"{0}\" must end with ''>''." }, { "UndeclaredElementInContentSpec", "The content model of element \"{0}\" refers to the undeclared element \"{1}\"." }, { "DuplicateAttDef", "Attribute \"{1}\" is already declared for element type \"{0}\"." }, { "RootElementTypeMustMatchDoctypedecl", "Document root element \"{1}\", must match DOCTYPE root \"{0}\"." }, { "ImproperDeclarationNesting", "The replacement text of parameter entity \"{0}\" must include properly nested declarations." }, { "WhiteSpaceInElementContentWhenStandalone", "White space must not occur between elements declared in an external parsed entity with element content in a standalone document." }, { "ReferenceToExternallyDeclaredEntityWhenStandalone", "The reference to entity \"{0}\" declared in an external parsed entity is not permitted in a standalone document." }, { "ExternalEntityNotPermitted", "The reference to external entity \"{0}\" is not permitted in a standalone document." }, { "AttValueChangedDuringNormalizationWhenStandalone", "The value \"{1}\" of attribute \"{0}\" must not be changed by normalization (to \"{2}\") in a standalone document." }, { "DefaultedAttributeNotSpecified", "Attribute \"{1}\" for element type \"{0}\" has a default value and must be specified in a standalone document." }, { "ContentIncomplete", "The content of element type \"{0}\" is incomplete, it must match \"{1}\"." }, { "ContentInvalid", "The content of element type \"{0}\" must match \"{1}\"." }, { "ElementNotDeclared", "Element type \"{0}\" must be declared." }, { "AttributeNotDeclared", "Attribute \"{1}\" must be declared for element type \"{0}\"." }, { "ElementAlreadyDeclared", "Element type \"{0}\" must not be declared more than once." }, { "ImproperGroupNesting", "The replacement text of parameter entity \"{0}\" must include properly nested pairs of parentheses." }, { "DuplicateTypeInMixedContent", "The element type \"{0}\" was already specified in this content model." }, { "NoNotationOnEmptyElement", "For compatibility, an attribute of type NOTATION must not be declared on an element declared EMPTY." }, { "ENTITIESInvalid", "Attribute value \"{1}\" of type ENTITIES must be the names of one or more unparsed entities." }, { "ENTITYInvalid", "Attribute value \"{1}\" of type ENTITY must be the name of an unparsed entity." }, { "IDDefaultTypeInvalid", "The ID attribute \"{0}\" must have a declared default of \"#IMPLIED\" or \"#REQUIRED\"." }, { "IDInvalid", "Attribute value \"{1}\" of type ID must be a name." }, { "IDNotUnique", "Attribute value \"{1}\" of type ID must be unique within the document." }, { "IDREFInvalid", "Attribute value \"{1}\" of type IDREF must be a name." }, { "IDREFSInvalid", "Attribute value \"{0}\" of type IDREFS must be one or more names." }, { "AttributeValueNotInList", "Attribute \"{0}\" with value \"{1}\" must have a value from the list \"{2}\"." }, { "NMTOKENInvalid", "Attribute value \"{1}\" of type NMTOKEN must be a name token." }, { "NMTOKENSInvalid", "Attribute value \"{0}\" of type NMTOKENS must be one or more name tokens." }, { "ElementWithIDRequired", "An element with the identifier \"{0}\" must appear in the document." }, { "MoreThanOneIDAttribute", "Element type \"{0}\" already has attribute \"{1}\" of type ID, a second attribute \"{2}\" of type ID is not permitted." }, { "MoreThanOneNotationAttribute", "Element type \"{0}\" already has attribute \"{1}\" of type NOTATION, a second attribute \"{2}\" of type NOTATION is not permitted." }, { "FIXEDAttValueInvalid", "Attribute \"{1}\" with value \"{2}\" must have a value of \"{3}\"." }, { "RequiredAttributeNotSpecified", "Attribute \"{1}\" is required and must be specified for element type \"{0}\"." }, { "AttDefaultInvalid", "The default value \"{1}\" must meet the lexical type constraints declared for attribute \"{0}\"." }, { "ImproperConditionalSectionNesting", "The replacement text of parameter entity \"{0}\" must include properly nested conditional sections." }, { "NotationNotDeclaredForNotationTypeAttribute", "The notation \"{2}\" must be declared when referenced in the notation type list for attribute \"{1}\"." }, { "NotationNotDeclaredForUnparsedEntityDecl", "The notation \"{1}\" must be declared when referenced in the unparsed entity declaration for \"{0}\"." }, { "UniqueNotationName", "Only one notation declaration can declare a given Name." }, { "ReferenceToExternalEntity", "The external entity reference \"&{0};\" is not permitted in an attribute value." }, { "PENotDeclared", "The parameter entity \"{0}\" was referenced, but not declared." }, { "ReferenceToUnparsedEntity", "The unparsed entity reference \"&{0};\" is not permitted." }, { "RecursiveReference", "Recursive reference \"&{0};\". (Reference path: {1})" }, { "RecursivePEReference", "Recursive reference \"%{0};\". (Reference path: {1})" }, { "EncodingNotSupported", "The encoding \"{0}\" is not supported." }, { "EncodingRequired", "A parsed entity not encoded in either UTF-8 or UTF-16 must contain an encoding declaration." } };
        MESSAGE_KEYS = new String[] { "QuoteRequiredInEntityValue", "InvalidCharInEntityValue", "InvalidCharInSystemID", "InvalidCharInPublicID", "InvalidCharInDoctypedecl", "InvalidCharInInternalSubset", "InvalidCharInExternalSubset", "InvalidCharInIgnoreSect", "QuoteRequiredInSystemID", "SystemIDUnterminated", "QuoteRequiredInPublicID", "PublicIDUnterminated", "PubidCharIllegal", "EntityValueUnterminated", "SpaceRequiredBeforeRootElementTypeInDoctypedecl", "RootElementTypeRequired", "DoctypedeclUnterminated", "PEReferenceWithinMarkup", "PEReferenceContainsIncompleteMarkup", "MarkupNotRecognizedInDTD", "XMLSpaceDeclarationIllegal", "SpaceRequiredBeforeElementTypeInElementDecl", "ElementTypeRequiredInElementDecl", "SpaceRequiredBeforeContentspecInElementDecl", "ContentspecRequiredInElementDecl", "ElementDeclUnterminated", "OpenParenOrElementTypeRequiredInChildren", "CloseParenRequiredInChildren", "ElementTypeRequiredInMixedContent", "CloseParenRequiredInMixedContent", "MixedContentUnterminated", "SpaceRequiredBeforeElementTypeInAttlistDecl", "ElementTypeRequiredInAttlistDecl", "SpaceRequiredBeforeAttributeNameInAttDef", "AttributeNameRequiredInAttDef", "SpaceRequiredBeforeAttTypeInAttDef", "AttTypeRequiredInAttDef", "SpaceRequiredBeforeDefaultDeclInAttDef", "DefaultDeclRequiredInAttDef", "SpaceRequiredAfterNOTATIONInNotationType", "OpenParenRequiredInNotationType", "NameRequiredInNotationType", "NotationTypeUnterminated", "NmtokenRequiredInEnumeration", "EnumerationUnterminated", "SpaceRequiredAfterFIXEDInDefaultDecl", "IncludeSectUnterminated", "IgnoreSectUnterminated", "NameRequiredInPEReference", "SemicolonRequiredInPEReference", "SpaceRequiredBeforeEntityNameInEntityDecl", "SpaceRequiredBeforePercentInPEDecl", "SpaceRequiredBeforeEntityNameInPEDecl", "EntityNameRequiredInEntityDecl", "SpaceRequiredAfterEntityNameInEntityDecl", "SpaceRequiredBeforeNDataInUnparsedEntityDecl", "SpaceRequiredBeforeNotationNameInUnparsedEntityDecl", "NotationNameRequiredInUnparsedEntityDecl", "EntityDeclUnterminated", "ExternalIDRequired", "SpaceRequiredBeforePubidLiteralInExternalID", "SpaceRequiredAfterPubidLiteralInExternalID", "SpaceRequiredBeforeSystemLiteralInExternalID", "URIFragmentInSystemID", "SpaceRequiredBeforeNotationNameInNotationDecl", "NotationNameRequiredInNotationDecl", "SpaceRequiredAfterNotationNameInNotationDecl", "NotationDeclUnterminated", "UndeclaredElementInContentSpec", "DuplicateAttDef", "RootElementTypeMustMatchDoctypedecl", "ImproperDeclarationNesting", "WhiteSpaceInElementContentWhenStandalone", "ReferenceToExternallyDeclaredEntityWhenStandalone", "ExternalEntityNotPermitted", "AttValueChangedDuringNormalizationWhenStandalone", "DefaultedAttributeNotSpecified", "ContentIncomplete", "ContentInvalid", "ElementNotDeclared", "AttributeNotDeclared", "ElementAlreadyDeclared", "ImproperGroupNesting", "DuplicateTypeInMixedContent", "NoNotationOnEmptyElement", "ENTITIESInvalid", "ENTITYInvalid", "IDDefaultTypeInvalid", "IDInvalid", "IDNotUnique", "IDREFInvalid", "IDREFSInvalid", "AttributeValueNotInList", "NMTOKENInvalid", "NMTOKENSInvalid", "ElementWithIDRequired", "MoreThanOneIDAttribute", "MoreThanOneNotationAttribute", "FIXEDAttValueInvalid", "RequiredAttributeNotSpecified", "AttDefaultInvalid", "ImproperConditionalSectionNesting", "NotationNotDeclaredForNotationTypeAttribute", "NotationNotDeclaredForUnparsedEntityDecl", "UniqueNotationName", "ReferenceToExternalEntity", "PENotDeclared", "ReferenceToUnparsedEntity", "RecursiveReference", "RecursivePEReference", "EncodingNotSupported", "EncodingRequired" };
    }
}
