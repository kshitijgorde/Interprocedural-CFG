// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.msg;

public final class XMLMessagesBundle_de extends ErrorMessageBundle
{
    private static final Object[][] CONTENTS;
    private static final String[] MESSAGE_KEYS;
    
    protected Object[][] getContents() {
        return XMLMessagesBundle_de.CONTENTS;
    }
    
    public String getString(final int n) {
        return this.getString(XMLMessagesBundle_de.MESSAGE_KEYS[n]);
    }
    
    static {
        CONTENTS = new Object[][] { { "BadMessageKey", "The error message corresponding to the message key can not be found." }, { "FormatFailed", "An internal error occurred while formatting the following message:\n  " }, { "QuoteRequiredInEntityValue", "Der Entit\u00e4tenwert muss mit einem einfachen oder doppelten Anf\u00fchrungszeichen beginnen." }, { "InvalidCharInEntityValue", "Im literalen Entit\u00e4tenwert wurde ein ung\u00fcltiges XML-Zeichen (Unicode: 0x{0}) gefunden." }, { "InvalidCharInSystemID", "In der System-ID wurde ein ung\u00fcltiges XML-Zeichen (Unicode: 0x{0}) gefunden." }, { "InvalidCharInPublicID", "In der allgemeinen ID wurde ein ung\u00fcltiges XML-Zeichen (Unicode: 0x{0}) gefunden." }, { "InvalidCharInDoctypedecl", "In der Dokumenttypdeklaration wurde ein ung\u00fcltiges XML-Zeichen (Unicode: 0x{0}) gefunden." }, { "InvalidCharInInternalSubset", "In der internen Untergruppe der Dokumenttypdefinition wurde ein ung\u00fcltiges XML-Zeichen (Unicode: 0x{0}) gefunden." }, { "InvalidCharInExternalSubset", "In der externen Untergruppe der Dokumenttypdefinition wurde ein ung\u00fcltiges XML-Zeichen (Unicode: 0x{0}) gefunden." }, { "InvalidCharInIgnoreSect", "Im ausgeschlossenen bedingten Abschnitt wurde ein ung\u00fcltiges XML-Zeichen (Unicode: 0x{0}) gefunden." }, { "QuoteRequiredInSystemID", "Die System-ID muss mit einem einfachen oder doppelten Anf\u00fchrungszeichen beginnen." }, { "SystemIDUnterminated", "Die System-ID muss mit dem entsprechenden Anf\u00fchrungszeichen enden." }, { "QuoteRequiredInPublicID", "Die allgemeine ID muss mit einem einfachen oder doppelten Anf\u00fchrungszeichen beginnen." }, { "PublicIDUnterminated", "Die allgemeine ID muss mit dem entsprechenden Anf\u00fchrungszeichen enden." }, { "PubidCharIllegal", "Das Zeichen (Unicode: 0x{0}) ist in der allgemeinen ID nicht zul\u00e4ssig." }, { "EntityValueUnterminated", "Der Literalwert f\u00fcr die Entit\u00e4t muss mit dem entsprechenden Anf\u00fchrungszeichen enden." }, { "SpaceRequiredBeforeRootElementTypeInDoctypedecl", "In der Dokumenttypdeklaration ist nach \"<!DOCTYPE\" ein Leerzeichen erforderlich." }, { "RootElementTypeRequired", "Der Stammelementtyp muss in der Dokumenttypdeklaration nach \"<!DOCTYPE\" stehen." }, { "DoctypedeclUnterminated", "Die Dokumenttypdeklaration f\u00fcr den Stammelementtyp \"{0}\" muss mit ''>'' enden." }, { "PEReferenceWithinMarkup", "Der Parameterentit\u00e4tenverweis \"%{0};\" kann nicht innerhalb der Formatierungssteuerzeichen in der internen Untergruppe der Dokumenttypdefinition auftreten." }, { "PEReferenceContainsIncompleteMarkup", "Parameterentit\u00e4tenverweise zwischen Deklarationen m\u00fcssen aus vollst\u00e4ndigen Formatierungssteuerzeichen bestehen." }, { "MarkupNotRecognizedInDTD", "Die Deklarationen f\u00fcr Formatierungssteuerzeichen, die in der Dokumenttypdeklaration enthalten sind bzw. auf die verwiesen wird, m\u00fcssen syntaktisch korrekt sein." }, { "XMLSpaceDeclarationIllegal", "Die Attributdeklaration f\u00fcr \"xml:space\" muss als Aufz\u00e4hlungstyp angegeben werden, dessen m\u00f6gliche Werte nur \"default\" und \"preserve\" sind." }, { "SpaceRequiredBeforeElementTypeInElementDecl", "In der Elementtypdeklaration ist nach \"<!ELEMENT\" ein Leerzeichen erforderlich." }, { "ElementTypeRequiredInElementDecl", "Der Elementtyp ist in der Elementtypdeklaration erforderlich." }, { "SpaceRequiredBeforeContentspecInElementDecl", "In der Elementtypdeklaration ist nach dem Elementtyp \"{0}\" ein Leerzeichen erforderlich." }, { "ContentspecRequiredInElementDecl", "In der Elementtypdeklaration ist nach dem Elementtyp \"{0}\" die Einschr\u00e4nkung erforderlich." }, { "ElementDeclUnterminated", "Die Deklaration f\u00fcr Elementtyp \"{0}\" muss mit ''>'' enden." }, { "OpenParenOrElementTypeRequiredInChildren", "In der Deklaration von Elementtyp \"{0}\" ist ein Zeichen ''('' oder ein Elementtyp erforderlich." }, { "CloseParenRequiredInChildren", "In der Deklaration von Elementtyp \"{0}\" ist das Zeichen '')'' erforderlich." }, { "ElementTypeRequiredInMixedContent", "In der Deklaration von Elementtyp \"{0}\" ist ein Elementtyp erforderlich." }, { "CloseParenRequiredInMixedContent", "In der Deklaration von Elementtyp \"{0}\" ist das Zeichen '')'' erforderlich." }, { "MixedContentUnterminated", "Das Modell mit gemischtem Inhalt f\u00fcr \"{0}\" muss mit \")*\" enden, wenn die Typen untergeordneter Elemente eingeschr\u00e4nkt sind." }, { "SpaceRequiredBeforeElementTypeInAttlistDecl", "In der Attributlistendeklaration ist nach \"<!ATTLIST\" ein Leerzeichen erforderlich." }, { "ElementTypeRequiredInAttlistDecl", "Der Elementtyp ist in der Attributlistendeklaration erforderlich." }, { "SpaceRequiredBeforeAttributeNameInAttDef", "Vor dem Attributnamen in der Attributlistendeklaration f\u00fcr Element \"{0}\" ist ein Leerzeichen erforderlich." }, { "AttributeNameRequiredInAttDef", "Der Attributname muss in der Attributlistendeklaration f\u00fcr Element \"{0}\" angegeben werden." }, { "SpaceRequiredBeforeAttTypeInAttDef", "Vor dem Attributtyp in der Deklaration von Attribut \"{1}\" f\u00fcr Element \"{0}\" ist ein Leerzeichen erforderlich." }, { "AttTypeRequiredInAttDef", "Der Attributtyp ist in der Deklaration von Attribut \"{1}\" f\u00fcr Element \"{0}\" erforderlich." }, { "SpaceRequiredBeforeDefaultDeclInAttDef", "Vor dem Attributstandardwert in der Deklaration von Attribut \"{1}\" f\u00fcr Element\"{0}\" ist ein Leerzeichen erforderlich." }, { "DefaultDeclRequiredInAttDef", "Der Attributstandardwert ist in der Deklaration von Attribut \"{1}\" f\u00fcr Element \"{0}\" erforderlich." }, { "SpaceRequiredAfterNOTATIONInNotationType", "Nach \"NOTATION\" in der Attributdeklaration \"{1}\" muss ein Leerzeichen vorhanden sein." }, { "OpenParenRequiredInNotationType", "Das Zeichen ''('' muss nach \"NOTATION\" in der Attributdeklaration \"{1}\" stehen." }, { "NameRequiredInNotationType", "Der Notationsname ist in der Notationstypenliste f\u00fcr die Attributdeklaration \"{1}\" erforderlich." }, { "NotationTypeUnterminated", "Die Notationstypenliste in der Attributdeklaration \"{1}\" muss mit '')'' enden." }, { "NmtokenRequiredInEnumeration", "Das Namenstoken ist in der Aufz\u00e4hlungstypenliste f\u00fcr die Attributsdeklaration \"{1}\" erforderlich." }, { "EnumerationUnterminated", "Die Aufz\u00e4hlungstypenliste in der Attributdeklaration \"{1}\" muss mit '')'' enden." }, { "SpaceRequiredAfterFIXEDInDefaultDecl", "Nach \"FIXED\" in der Attributdeklaration \"{1}\" muss ein Leerzeichen stehen." }, { "IncludeSectUnterminated", "Der eingeschlossene bedingte Abschnitt muss mit \"]]>\" enden." }, { "IgnoreSectUnterminated", "Der ausgeschlossene bedingte Abschnitt muss mit \"]]>\" enden." }, { "NameRequiredInPEReference", "Der Entit\u00e4tenname muss unmittelbar nach dem '%' im Parameterentit\u00e4tenverweis folgen." }, { "SemicolonRequiredInPEReference", "Der Parameterentit\u00e4tenverweis \"%{0};\" muss mit dem Begrenzer '';'' enden." }, { "SpaceRequiredBeforeEntityNameInEntityDecl", "In der Entit\u00e4tendeklaration ist nach \"<!ENTITY\" ein Leerzeichen erforderlich." }, { "SpaceRequiredBeforePercentInPEDecl", "In der Parameterentit\u00e4tendeklaration ist zwischen \"<!ENTITY\" und dem Zeichen '%' ein Leerzeichen erforderlich." }, { "SpaceRequiredBeforeEntityNameInPEDecl", "In der Parameterentit\u00e4tendeklaration ist zwischen '%' und dem Entit\u00e4tennamen ein Leerzeichen erforderlich." }, { "EntityNameRequiredInEntityDecl", "Der Entit\u00e4tenname ist in der Entit\u00e4tendeklaration erforderlich." }, { "SpaceRequiredAfterEntityNameInEntityDecl", "In der Entit\u00e4tendeklaration ist zwischen dem Entit\u00e4tennamen \"{0}\" und der Definition ein Leerzeichen erforderlich." }, { "SpaceRequiredBeforeNDataInUnparsedEntityDecl", "Vor \"NDATA\" in der Deklaration f\u00fcr die Entit\u00e4t \"{0}\" ist ein Leerzeichen erforderlich." }, { "SpaceRequiredBeforeNotationNameInUnparsedEntityDecl", "Zwischen \"NDATA\" und dem Notationsnamen in der Deklaration f\u00fcr die Entit\u00e4t \"{0}\" ist ein Leerzeichen erforderlich." }, { "NotationNameRequiredInUnparsedEntityDecl", "Der Notationsname ist nach \"NDATA\" in der Deklaration f\u00fcr die Entit\u00e4t \"{0}\" erforderlich." }, { "EntityDeclUnterminated", "Die Deklaration f\u00fcr die Entit\u00e4t \"{0}\" muss mit ''>'' enden." }, { "ExternalIDRequired", "Die externe Entit\u00e4tendeklaration muss mit \"SYSTEM\" oder \"PUBLIC\" beginnen." }, { "SpaceRequiredBeforePubidLiteralInExternalID", "Zwischen \"PUBLIC\" und der allgemeinen ID ist ein Leerzeichen erforderlich." }, { "SpaceRequiredAfterPubidLiteralInExternalID", "Zwischen der allgemeinen ID und der System-ID ist ein Leerzeichen erforderlich." }, { "SpaceRequiredBeforeSystemLiteralInExternalID", "Zwischen \"SYSTEM\" und der System-ID ist ein Leerzeichen erforderlich." }, { "URIFragmentInSystemID", "Die Fragment-ID sollte nicht als Teil der System-ID \"{0}\" angegeben werden." }, { "SpaceRequiredBeforeNotationNameInNotationDecl", "Nach \"<!NOTATION\" in der Notationsdeklaration ist ein Leerzeichen erforderlich." }, { "NotationNameRequiredInNotationDecl", "Der Notationsname ist in der Notationsdeklaration erforderlich." }, { "SpaceRequiredAfterNotationNameInNotationDecl", "Nach dem Notationsnamen \"{0}\" in der Notationsdeklaration ist ein Leerzeichen erforderlich." }, { "NotationDeclUnterminated", "Die Deklaration f\u00fcr die Notation \"{0}\" muss mit ''>'' enden." }, { "UndeclaredElementInContentSpec", "Das Inhaltsmodell von Element \"{0}\" verweist auf das nicht deklarierte Element \"{1}\"." }, { "DuplicateAttDef", "Das Attribut \"{1}\" wurde bereits f\u00fcr Elementtyp \"{0}\" deklariert." }, { "RootElementTypeMustMatchDoctypedecl", "Das Dokumentstammelement \"{1}\" muss DOCTYPE-Root \"{0}\" entsprechen." }, { "ImproperDeclarationNesting", "Der Ersetzungstext der Parameterentit\u00e4t \"{0}\" muss ordnungsgem\u00e4\u00df verschachtelte Deklarationen einschlie\u00dfen." }, { "WhiteSpaceInElementContentWhenStandalone", "Zwischen Elementen, die in einem Standalonedokument in einer externen syntaktisch analysierten Entit\u00e4t mit Elementinhalt deklariert wurden, d\u00fcrfen keine Leerzeichen vorhanden sein." }, { "ReferenceToExternallyDeclaredEntityWhenStandalone", "Der Verweis auf Entit\u00e4t \"{0}\", die in einer externen syntaktisch analysierten Entit\u00e4t deklariert wurde, ist in einem Standalonedokument nicht zul\u00e4ssig." }, { "ExternalEntityNotPermitted", "Der Verweis auf eine externe Entit\u00e4t \"{0}\" ist in einem Standalonedokument nicht zul\u00e4ssig." }, { "AttValueChangedDuringNormalizationWhenStandalone", "Der Wert \"{1}\" von Attribut \"{0}\" darf nicht durch die Normalisierung (in \"{2}\") in einem Standalonedokument ge\u00e4ndert werden." }, { "DefaultedAttributeNotSpecified", "Das Attribut \"{1}\" f\u00fcr Elementtyp \"{0}\" hat einen Standardwert und muss in einem Standalonedokument angegeben werden." }, { "ContentIncomplete", "Der Inhalt von Elementtyp \"{0}\" ist unvollst\u00e4ndig; er muss \"{1}\" entsprechen." }, { "ContentInvalid", "Der Inhalt von Elementtyp \"{0}\" muss \"{1}\" entsprechen." }, { "ElementNotDeclared", "Elementtyp \"{0}\" muss deklariert werden." }, { "AttributeNotDeclared", "Das Attribut \"{1}\" muss f\u00fcr Elementtyp \"{0}\" deklariert werden." }, { "ElementAlreadyDeclared", "Elementtyp \"{0}\" darf nicht mehrmals deklariert werden." }, { "ImproperGroupNesting", "Der Ersetzungstext der Parameterentit\u00e4t \"{0}\" muss ordnungsgem\u00e4\u00df verschachtelte Klammerpaare einschlie\u00dfen." }, { "DuplicateTypeInMixedContent", "Der Elementtyp \"{0}\" wurde bereits in diesem Inhaltsmodell angegeben." }, { "NoNotationOnEmptyElement", "Aus Kompatibilit\u00e4tsgr\u00fcnden darf ein Attribut des Typs NOTATION nicht in einem als EMPTY deklarierten Element deklariert werden." }, { "ENTITIESInvalid", "Der Attributwert \"{0}\" des Typs ENTITIES muss aus dem Namen mindestens einer syntaktisch nicht analysierten Entit\u00e4t bestehen." }, { "ENTITYInvalid", "Der Attributwert \"{1}\" des Typs ENTITY muss der Name einer syntaktisch nicht analysierten Entit\u00e4t sein." }, { "IDDefaultTypeInvalid", "Das ID-Attribut \"{0}\" muss den deklarierten Standardwert \"#IMPLIED\" oder \"#REQUIRED\" haben." }, { "IDInvalid", "Der Attributwert \"{1}\" des Typs ID muss ein Name sein." }, { "IDNotUnique", "Der Attributwert \"{1}\" des Typs ID muss innerhalb des Dokuments eindeutig sein." }, { "IDREFInvalid", "Der Attributwert \"{1}\" des Typs IDREF muss ein Name sein." }, { "IDREFSInvalid", "Der Attributwert \"{0}\" des Typs IDREFS muss aus mindestens einem Namen bestehen." }, { "AttributeValueNotInList", "Das Attribut \"{0}\" mit Wert \"{1}\" muss einen Wert aus der Liste \"{2}\" haben." }, { "NMTOKENInvalid", "Der Attributwert \"{1}\" des Typs NMTOKEN muss ein Namenstoken sein." }, { "NMTOKENSInvalid", "Der Attributwert \"{0}\" des Typs NMTOKENS muss mindestens ein Namenstoken sein." }, { "ElementWithIDRequired", "Im Dokument muss ein Element mit der ID \"{0}\" vorhanden sein." }, { "MoreThanOneIDAttribute", "Der Elementtyp \"{0}\" verf\u00fcgt bereits \u00fcber das Attribut \"{1}\" des Typs ID; ein zweites Attribut \"{2}\" des Typs ID ist nicht zul\u00e4ssig." }, { "MoreThanOneNotationAttribute", "Der Elementtyp \"{0}\" verf\u00fcgt bereits \u00fcber das Attribut \"{1}\" des Typs NOTATION; ein zweites Attribut \"{2}\" des Typs NOTATION ist nicht zul\u00e4ssig." }, { "FIXEDAttValueInvalid", "Das Attribut \"{1}\" mit Wert \"{2}\" muss den Wert \"{3}\" haben." }, { "RequiredAttributeNotSpecified", "Das Attribut \"{1}\" ist erforderlich und muss f\u00fcr Elementtyp \"{0}\" angegeben werden." }, { "AttDefaultInvalid", "Der Standardwert \"{1}\" muss die Einschr\u00e4nkungen f\u00fcr den lexikalischen Typ erf\u00fcllen, die f\u00fcr das Attribut \"{0}\" deklariert sind." }, { "ImproperConditionalSectionNesting", "Der Ersetzungstext der Parameterentit\u00e4t \"{0}\" muss ordnungsgem\u00e4\u00df verschachtelte bedingte Abschnitte enthalten." }, { "NotationNotDeclaredForNotationTypeAttribute", "Die Notation \"{2}\" muss deklariert werden, wenn in der Notationstypenliste f\u00fcr Attribut \"{1}\" auf sie verwiesen wird." }, { "NotationNotDeclaredForUnparsedEntityDecl", "Die Notation \"{1}\" muss deklariert werden, wenn in der Deklaration syntaktisch nicht analysierter Entit\u00e4ten f\u00fcr \"{0}\" auf sie verwiesen wird." }, { "UniqueNotationName", "Nur eine Notationsdeklaration kann einen vorgegebenen Namen deklarieren." }, { "ReferenceToExternalEntity", "Der externe Entit\u00e4tenverweis \"&{0};\" ist in einem Attributwert nicht zul\u00e4ssig." }, { "PENotDeclared", "Auf die Parameterentit\u00e4t \"{0}\" wurde verwiesen, sie wurde aber nicht deklariert." }, { "ReferenceToUnparsedEntity", "Der syntaktisch nicht analysierte Entit\u00e4tenverweis \"&{0};\" ist nicht zul\u00e4ssig." }, { "RecursiveReference", "Rekursiver Verweis \"&{0};\". (Verweispfad: {1})" }, { "RecursivePEReference", "Rekursiver Verweis \"%{0};\". (Verweispfad: {1})" }, { "EncodingNotSupported", "Die Codierung \"{0}\" wird nicht unterst\u00fctzt." }, { "EncodingRequired", "Eine syntaktisch analysierte Entit\u00e4t, die nicht mit UTF-8 oder UTF-16 verschl\u00fcsselt wurde, muss eine Verschl\u00fcsselungsdeklaration enthalten." } };
        MESSAGE_KEYS = new String[] { "QuoteRequiredInEntityValue", "InvalidCharInEntityValue", "InvalidCharInSystemID", "InvalidCharInPublicID", "InvalidCharInDoctypedecl", "InvalidCharInInternalSubset", "InvalidCharInExternalSubset", "InvalidCharInIgnoreSect", "QuoteRequiredInSystemID", "SystemIDUnterminated", "QuoteRequiredInPublicID", "PublicIDUnterminated", "PubidCharIllegal", "EntityValueUnterminated", "SpaceRequiredBeforeRootElementTypeInDoctypedecl", "RootElementTypeRequired", "DoctypedeclUnterminated", "PEReferenceWithinMarkup", "PEReferenceContainsIncompleteMarkup", "MarkupNotRecognizedInDTD", "XMLSpaceDeclarationIllegal", "SpaceRequiredBeforeElementTypeInElementDecl", "ElementTypeRequiredInElementDecl", "SpaceRequiredBeforeContentspecInElementDecl", "ContentspecRequiredInElementDecl", "ElementDeclUnterminated", "OpenParenOrElementTypeRequiredInChildren", "CloseParenRequiredInChildren", "ElementTypeRequiredInMixedContent", "CloseParenRequiredInMixedContent", "MixedContentUnterminated", "SpaceRequiredBeforeElementTypeInAttlistDecl", "ElementTypeRequiredInAttlistDecl", "SpaceRequiredBeforeAttributeNameInAttDef", "AttributeNameRequiredInAttDef", "SpaceRequiredBeforeAttTypeInAttDef", "AttTypeRequiredInAttDef", "SpaceRequiredBeforeDefaultDeclInAttDef", "DefaultDeclRequiredInAttDef", "SpaceRequiredAfterNOTATIONInNotationType", "OpenParenRequiredInNotationType", "NameRequiredInNotationType", "NotationTypeUnterminated", "NmtokenRequiredInEnumeration", "EnumerationUnterminated", "SpaceRequiredAfterFIXEDInDefaultDecl", "IncludeSectUnterminated", "IgnoreSectUnterminated", "NameRequiredInPEReference", "SemicolonRequiredInPEReference", "SpaceRequiredBeforeEntityNameInEntityDecl", "SpaceRequiredBeforePercentInPEDecl", "SpaceRequiredBeforeEntityNameInPEDecl", "EntityNameRequiredInEntityDecl", "SpaceRequiredAfterEntityNameInEntityDecl", "SpaceRequiredBeforeNDataInUnparsedEntityDecl", "SpaceRequiredBeforeNotationNameInUnparsedEntityDecl", "NotationNameRequiredInUnparsedEntityDecl", "EntityDeclUnterminated", "ExternalIDRequired", "SpaceRequiredBeforePubidLiteralInExternalID", "SpaceRequiredAfterPubidLiteralInExternalID", "SpaceRequiredBeforeSystemLiteralInExternalID", "URIFragmentInSystemID", "SpaceRequiredBeforeNotationNameInNotationDecl", "NotationNameRequiredInNotationDecl", "SpaceRequiredAfterNotationNameInNotationDecl", "NotationDeclUnterminated", "UndeclaredElementInContentSpec", "DuplicateAttDef", "RootElementTypeMustMatchDoctypedecl", "ImproperDeclarationNesting", "WhiteSpaceInElementContentWhenStandalone", "ReferenceToExternallyDeclaredEntityWhenStandalone", "ExternalEntityNotPermitted", "AttValueChangedDuringNormalizationWhenStandalone", "DefaultedAttributeNotSpecified", "ContentIncomplete", "ContentInvalid", "ElementNotDeclared", "AttributeNotDeclared", "ElementAlreadyDeclared", "ImproperGroupNesting", "DuplicateTypeInMixedContent", "NoNotationOnEmptyElement", "ENTITIESInvalid", "ENTITYInvalid", "IDDefaultTypeInvalid", "IDInvalid", "IDNotUnique", "IDREFInvalid", "IDREFSInvalid", "AttributeValueNotInList", "NMTOKENInvalid", "NMTOKENSInvalid", "ElementWithIDRequired", "MoreThanOneIDAttribute", "MoreThanOneNotationAttribute", "FIXEDAttValueInvalid", "RequiredAttributeNotSpecified", "AttDefaultInvalid", "ImproperConditionalSectionNesting", "NotationNotDeclaredForNotationTypeAttribute", "NotationNotDeclaredForUnparsedEntityDecl", "UniqueNotationName", "ReferenceToExternalEntity", "PENotDeclared", "ReferenceToUnparsedEntity", "RecursiveReference", "RecursivePEReference", "EncodingNotSupported", "EncodingRequired", "QuoteRequiredInEntityValue", "InvalidCharInEntityValue", "InvalidCharInSystemID", "InvalidCharInPublicID", "InvalidCharInDoctypedecl", "InvalidCharInInternalSubset", "InvalidCharInExternalSubset", "InvalidCharInIgnoreSect", "QuoteRequiredInSystemID", "SystemIDUnterminated", "QuoteRequiredInPublicID", "PublicIDUnterminated", "PubidCharIllegal", "EntityValueUnterminated", "SpaceRequiredBeforeRootElementTypeInDoctypedecl", "RootElementTypeRequired", "DoctypedeclUnterminated", "PEReferenceWithinMarkup", "PEReferenceContainsIncompleteMarkup", "MarkupNotRecognizedInDTD", "XMLSpaceDeclarationIllegal", "SpaceRequiredBeforeElementTypeInElementDecl", "ElementTypeRequiredInElementDecl", "SpaceRequiredBeforeContentspecInElementDecl", "ContentspecRequiredInElementDecl", "ElementDeclUnterminated", "OpenParenOrElementTypeRequiredInChildren", "CloseParenRequiredInChildren", "ElementTypeRequiredInMixedContent", "CloseParenRequiredInMixedContent", "MixedContentUnterminated", "SpaceRequiredBeforeElementTypeInAttlistDecl", "ElementTypeRequiredInAttlistDecl", "SpaceRequiredBeforeAttributeNameInAttDef", "AttributeNameRequiredInAttDef", "SpaceRequiredBeforeAttTypeInAttDef", "AttTypeRequiredInAttDef", "SpaceRequiredBeforeDefaultDeclInAttDef", "DefaultDeclRequiredInAttDef", "SpaceRequiredAfterNOTATIONInNotationType", "OpenParenRequiredInNotationType", "NameRequiredInNotationType", "NotationTypeUnterminated", "NmtokenRequiredInEnumeration", "EnumerationUnterminated", "SpaceRequiredAfterFIXEDInDefaultDecl", "IncludeSectUnterminated", "IgnoreSectUnterminated", "NameRequiredInPEReference", "SemicolonRequiredInPEReference", "SpaceRequiredBeforeEntityNameInEntityDecl", "SpaceRequiredBeforePercentInPEDecl", "SpaceRequiredBeforeEntityNameInPEDecl", "EntityNameRequiredInEntityDecl", "SpaceRequiredAfterEntityNameInEntityDecl", "SpaceRequiredBeforeNDataInUnparsedEntityDecl", "SpaceRequiredBeforeNotationNameInUnparsedEntityDecl", "NotationNameRequiredInUnparsedEntityDecl", "EntityDeclUnterminated", "ExternalIDRequired", "SpaceRequiredBeforePubidLiteralInExternalID", "SpaceRequiredAfterPubidLiteralInExternalID", "SpaceRequiredBeforeSystemLiteralInExternalID", "URIFragmentInSystemID", "SpaceRequiredBeforeNotationNameInNotationDecl", "NotationNameRequiredInNotationDecl", "SpaceRequiredAfterNotationNameInNotationDecl", "NotationDeclUnterminated", "UndeclaredElementInContentSpec", "DuplicateAttDef", "RootElementTypeMustMatchDoctypedecl", "ImproperDeclarationNesting", "WhiteSpaceInElementContentWhenStandalone", "ReferenceToExternallyDeclaredEntityWhenStandalone", "ExternalEntityNotPermitted", "AttValueChangedDuringNormalizationWhenStandalone", "DefaultedAttributeNotSpecified", "ContentIncomplete", "ContentInvalid", "ElementNotDeclared", "AttributeNotDeclared", "ElementAlreadyDeclared", "ImproperGroupNesting", "DuplicateTypeInMixedContent", "NoNotationOnEmptyElement", "ENTITIESInvalid", "ENTITYInvalid", "IDDefaultTypeInvalid", "IDInvalid", "IDNotUnique", "IDREFInvalid", "IDREFSInvalid", "AttributeValueNotInList", "NMTOKENInvalid", "NMTOKENSInvalid", "ElementWithIDRequired", "MoreThanOneIDAttribute", "MoreThanOneNotationAttribute", "FIXEDAttValueInvalid", "RequiredAttributeNotSpecified", "AttDefaultInvalid", "ImproperConditionalSectionNesting", "NotationNotDeclaredForNotationTypeAttribute", "NotationNotDeclaredForUnparsedEntityDecl", "UniqueNotationName", "ReferenceToExternalEntity", "PENotDeclared", "ReferenceToUnparsedEntity", "RecursiveReference", "RecursivePEReference", "EncodingNotSupported", "EncodingRequired", "QuoteRequiredInEntityValue", "InvalidCharInEntityValue", "InvalidCharInSystemID", "InvalidCharInPublicID", "InvalidCharInDoctypedecl", "InvalidCharInInternalSubset", "InvalidCharInExternalSubset", "InvalidCharInIgnoreSect", "QuoteRequiredInSystemID", "SystemIDUnterminated", "QuoteRequiredInPublicID", "PublicIDUnterminated", "PubidCharIllegal", "EntityValueUnterminated", "SpaceRequiredBeforeRootElementTypeInDoctypedecl", "RootElementTypeRequired", "DoctypedeclUnterminated", "PEReferenceWithinMarkup", "PEReferenceContainsIncompleteMarkup", "MarkupNotRecognizedInDTD", "XMLSpaceDeclarationIllegal", "SpaceRequiredBeforeElementTypeInElementDecl", "ElementTypeRequiredInElementDecl", "SpaceRequiredBeforeContentspecInElementDecl", "ContentspecRequiredInElementDecl", "ElementDeclUnterminated", "OpenParenOrElementTypeRequiredInChildren", "CloseParenRequiredInChildren", "ElementTypeRequiredInMixedContent", "CloseParenRequiredInMixedContent", "MixedContentUnterminated", "SpaceRequiredBeforeElementTypeInAttlistDecl", "ElementTypeRequiredInAttlistDecl", "SpaceRequiredBeforeAttributeNameInAttDef", "AttributeNameRequiredInAttDef", "SpaceRequiredBeforeAttTypeInAttDef", "AttTypeRequiredInAttDef", "SpaceRequiredBeforeDefaultDeclInAttDef", "DefaultDeclRequiredInAttDef", "SpaceRequiredAfterNOTATIONInNotationType", "OpenParenRequiredInNotationType", "NameRequiredInNotationType", "NotationTypeUnterminated", "NmtokenRequiredInEnumeration", "EnumerationUnterminated", "SpaceRequiredAfterFIXEDInDefaultDecl", "IncludeSectUnterminated", "IgnoreSectUnterminated", "NameRequiredInPEReference", "SemicolonRequiredInPEReference", "SpaceRequiredBeforeEntityNameInEntityDecl", "SpaceRequiredBeforePercentInPEDecl", "SpaceRequiredBeforeEntityNameInPEDecl", "EntityNameRequiredInEntityDecl", "SpaceRequiredAfterEntityNameInEntityDecl", "SpaceRequiredBeforeNDataInUnparsedEntityDecl", "SpaceRequiredBeforeNotationNameInUnparsedEntityDecl", "NotationNameRequiredInUnparsedEntityDecl", "EntityDeclUnterminated", "ExternalIDRequired", "SpaceRequiredBeforePubidLiteralInExternalID", "SpaceRequiredAfterPubidLiteralInExternalID", "SpaceRequiredBeforeSystemLiteralInExternalID", "URIFragmentInSystemID", "SpaceRequiredBeforeNotationNameInNotationDecl", "NotationNameRequiredInNotationDecl", "SpaceRequiredAfterNotationNameInNotationDecl", "NotationDeclUnterminated", "UndeclaredElementInContentSpec", "DuplicateAttDef", "RootElementTypeMustMatchDoctypedecl", "ImproperDeclarationNesting", "WhiteSpaceInElementContentWhenStandalone", "ReferenceToExternallyDeclaredEntityWhenStandalone", "ExternalEntityNotPermitted", "AttValueChangedDuringNormalizationWhenStandalone", "DefaultedAttributeNotSpecified", "ContentIncomplete", "ContentInvalid", "ElementNotDeclared", "AttributeNotDeclared", "ElementAlreadyDeclared", "ImproperGroupNesting", "DuplicateTypeInMixedContent", "NoNotationOnEmptyElement", "ENTITIESInvalid", "ENTITYInvalid", "IDDefaultTypeInvalid", "IDInvalid", "IDNotUnique", "IDREFInvalid", "IDREFSInvalid", "AttributeValueNotInList", "NMTOKENInvalid", "NMTOKENSInvalid", "ElementWithIDRequired", "MoreThanOneIDAttribute", "MoreThanOneNotationAttribute", "FIXEDAttValueInvalid", "RequiredAttributeNotSpecified", "AttDefaultInvalid", "ImproperConditionalSectionNesting", "NotationNotDeclaredForNotationTypeAttribute", "NotationNotDeclaredForUnparsedEntityDecl", "UniqueNotationName", "ReferenceToExternalEntity", "PENotDeclared", "ReferenceToUnparsedEntity", "RecursiveReference", "RecursivePEReference", "EncodingNotSupported", "EncodingRequired", "QuoteRequiredInEntityValue", "InvalidCharInEntityValue", "InvalidCharInSystemID", "InvalidCharInPublicID", "InvalidCharInDoctypedecl", "InvalidCharInInternalSubset", "InvalidCharInExternalSubset", "InvalidCharInIgnoreSect", "QuoteRequiredInSystemID", "SystemIDUnterminated", "QuoteRequiredInPublicID", "PublicIDUnterminated", "PubidCharIllegal", "EntityValueUnterminated", "SpaceRequiredBeforeRootElementTypeInDoctypedecl", "RootElementTypeRequired", "DoctypedeclUnterminated", "PEReferenceWithinMarkup", "PEReferenceContainsIncompleteMarkup", "MarkupNotRecognizedInDTD", "XMLSpaceDeclarationIllegal", "SpaceRequiredBeforeElementTypeInElementDecl", "ElementTypeRequiredInElementDecl", "SpaceRequiredBeforeContentspecInElementDecl", "ContentspecRequiredInElementDecl", "ElementDeclUnterminated", "OpenParenOrElementTypeRequiredInChildren", "CloseParenRequiredInChildren", "ElementTypeRequiredInMixedContent", "CloseParenRequiredInMixedContent", "MixedContentUnterminated", "SpaceRequiredBeforeElementTypeInAttlistDecl", "ElementTypeRequiredInAttlistDecl", "SpaceRequiredBeforeAttributeNameInAttDef", "AttributeNameRequiredInAttDef", "SpaceRequiredBeforeAttTypeInAttDef", "AttTypeRequiredInAttDef", "SpaceRequiredBeforeDefaultDeclInAttDef", "DefaultDeclRequiredInAttDef", "SpaceRequiredAfterNOTATIONInNotationType", "OpenParenRequiredInNotationType", "NameRequiredInNotationType", "NotationTypeUnterminated", "NmtokenRequiredInEnumeration", "EnumerationUnterminated", "SpaceRequiredAfterFIXEDInDefaultDecl", "IncludeSectUnterminated", "IgnoreSectUnterminated", "NameRequiredInPEReference", "SemicolonRequiredInPEReference", "SpaceRequiredBeforeEntityNameInEntityDecl", "SpaceRequiredBeforePercentInPEDecl", "SpaceRequiredBeforeEntityNameInPEDecl", "EntityNameRequiredInEntityDecl", "SpaceRequiredAfterEntityNameInEntityDecl", "SpaceRequiredBeforeNDataInUnparsedEntityDecl", "SpaceRequiredBeforeNotationNameInUnparsedEntityDecl", "NotationNameRequiredInUnparsedEntityDecl", "EntityDeclUnterminated", "ExternalIDRequired", "SpaceRequiredBeforePubidLiteralInExternalID", "SpaceRequiredAfterPubidLiteralInExternalID", "SpaceRequiredBeforeSystemLiteralInExternalID", "URIFragmentInSystemID", "SpaceRequiredBeforeNotationNameInNotationDecl", "NotationNameRequiredInNotationDecl", "SpaceRequiredAfterNotationNameInNotationDecl", "NotationDeclUnterminated", "UndeclaredElementInContentSpec", "DuplicateAttDef", "RootElementTypeMustMatchDoctypedecl", "ImproperDeclarationNesting", "WhiteSpaceInElementContentWhenStandalone", "ReferenceToExternallyDeclaredEntityWhenStandalone", "ExternalEntityNotPermitted", "AttValueChangedDuringNormalizationWhenStandalone", "DefaultedAttributeNotSpecified", "ContentIncomplete", "ContentInvalid", "ElementNotDeclared", "AttributeNotDeclared", "ElementAlreadyDeclared", "ImproperGroupNesting", "DuplicateTypeInMixedContent", "NoNotationOnEmptyElement", "ENTITIESInvalid", "ENTITYInvalid", "IDDefaultTypeInvalid", "IDInvalid", "IDNotUnique", "IDREFInvalid", "IDREFSInvalid", "AttributeValueNotInList", "NMTOKENInvalid", "NMTOKENSInvalid", "ElementWithIDRequired", "MoreThanOneIDAttribute", "MoreThanOneNotationAttribute", "FIXEDAttValueInvalid", "RequiredAttributeNotSpecified", "AttDefaultInvalid", "ImproperConditionalSectionNesting", "NotationNotDeclaredForNotationTypeAttribute", "NotationNotDeclaredForUnparsedEntityDecl", "UniqueNotationName", "ReferenceToExternalEntity", "PENotDeclared", "ReferenceToUnparsedEntity", "RecursiveReference", "RecursivePEReference", "EncodingNotSupported", "EncodingRequired" };
    }
}