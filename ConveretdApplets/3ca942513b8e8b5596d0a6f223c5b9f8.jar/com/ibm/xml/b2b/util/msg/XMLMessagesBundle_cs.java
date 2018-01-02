// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.msg;

public final class XMLMessagesBundle_cs extends ErrorMessageBundle
{
    private static final Object[][] CONTENTS;
    private static final String[] MESSAGE_KEYS;
    
    protected Object[][] getContents() {
        return XMLMessagesBundle_cs.CONTENTS;
    }
    
    public String getString(final int n) {
        return this.getString(XMLMessagesBundle_cs.MESSAGE_KEYS[n]);
    }
    
    static {
        CONTENTS = new Object[][] { { "BadMessageKey", "The error message corresponding to the message key can not be found." }, { "FormatFailed", "An internal error occurred while formatting the following message:\n  " }, { "QuoteRequiredInEntityValue", "Hodnota entity mus\u00ed za\u010d\u00ednat znakem jednoduch\u00fdch nebo dvojit\u00fdch uvozovek." }, { "InvalidCharInEntityValue", "V hodnot\u011b entity liter\u00e1lu byl zji\u0161t\u011bn neplatn\u00fd znak XML (Unicode: 0x{0})." }, { "InvalidCharInSystemID", "V identifik\u00e1toru syst\u00e9mu byl zji\u0161t\u011bn neplatn\u00fd znak XML (Unicode: 0x{0})." }, { "InvalidCharInPublicID", "Ve ve\u0159ejn\u00e9m identifik\u00e1toru byl zji\u0161t\u011bn neplatn\u00fd znak XML (Unicode: 0x{0})." }, { "InvalidCharInDoctypedecl", "V deklaraci typu dokumentu byl nalezen neplatn\u00fd znak XML (Unicode: 0x{0})." }, { "InvalidCharInInternalSubset", "Ve vnit\u0159n\u00ed podmno\u017ein\u011b DTD byl zji\u0161t\u011bn neplatn\u00fd znak XML (Unicode: 0x{0})." }, { "InvalidCharInExternalSubset", "V extern\u00ed podmno\u017ein\u011b DTD by zji\u0161t\u011bn neplatn\u00fd znak XML (Unicode: 0x{0})." }, { "InvalidCharInIgnoreSect", "Ve vylou\u010den\u00e9 podm\u00ednkov\u00e9 sekci byl zji\u0161t\u011bn neplatn\u00fd znak XML (Unicode: 0x{0})." }, { "QuoteRequiredInSystemID", "Identifik\u00e1tor syst\u00e9mu mus\u00ed za\u010d\u00ednat znakem jednoduch\u00fdch nebo dvojit\u00fdch uvozovek." }, { "SystemIDUnterminated", "Identifik\u00e1tor syst\u00e9mu mus\u00ed b\u00fdt ukon\u010den odpov\u00eddaj\u00edc\u00edm znakem uvozovek." }, { "QuoteRequiredInPublicID", "Ve\u0159ejn\u00fd identifik\u00e1tor mus\u00ed za\u010d\u00ednat znakem jednoduch\u00fdch nebo dvojit\u00fdch uvozovek." }, { "PublicIDUnterminated", "Ve\u0159ejn\u00fd identifik\u00e1tor mus\u00ed b\u00fdt ukon\u010den odpov\u00eddaj\u00edc\u00edm znakem uvozovek." }, { "PubidCharIllegal", "Ve ve\u0159ejn\u00e9m identifik\u00e1toru nen\u00ed povolen znak (Unicode: 0x{0})." }, { "EntityValueUnterminated", "Hodnota liter\u00e1lu pro entitu mus\u00ed b\u00fdt ukon\u010dena odpov\u00eddaj\u00edc\u00edm znakem uvozovek." }, { "SpaceRequiredBeforeRootElementTypeInDoctypedecl", "Za \u0159et\u011bzcem  \"<!DOCTYPE\" v deklaraci typu dokumentu je po\u017eadov\u00e1na mezera." }, { "RootElementTypeRequired", "Za \u0159et\u011bzcem \"<!DOCTYPE\" v deklaraci typu dokumentu mus\u00ed b\u00fdt uveden typ ko\u0159enov\u00e9ho prvku." }, { "DoctypedeclUnterminated", "Deklarace typu dokumentu typu ko\u0159enov\u00e9ho prvku \"{0}\" mus\u00ed b\u00fdt ukon\u010dena odd\u011blova\u010dem ''>''." }, { "PEReferenceWithinMarkup", "Odkaz entity parametru \"%{0};\" se nesm\u00ed vyskytovat uvnit\u0159 k\u00f3du ve vnit\u0159n\u00ed podmno\u017ein\u011b DTD." }, { "PEReferenceContainsIncompleteMarkup", "Parametrick\u00e9 odkazy na entitu v r\u00e1mci deklarac\u00ed mus\u00ed sest\u00e1vat z \u00fapln\u00e9 zna\u010dky." }, { "MarkupNotRecognizedInDTD", "Deklarace k\u00f3du obsa\u017een\u00e9 nebo odkazovan\u00e9 v deklaraci typu dokumentu mus\u00ed b\u00fdt spr\u00e1vn\u011b form\u00e1tov\u00e1ny." }, { "XMLSpaceDeclarationIllegal", "Deklarace atributu \"xml:space\" mus\u00ed b\u00fdt uvedena jako v\u00fd\u010dtov\u00fd typ, kter\u00fd obsahuje pouze mo\u017en\u00e9 hodnoty \"default\" a \"preserve\"." }, { "SpaceRequiredBeforeElementTypeInElementDecl", "Za \u0159et\u011bzcem  \"<!ELEMENT\" v deklaraci typu prvku je po\u017eadov\u00e1na mezera." }, { "ElementTypeRequiredInElementDecl", "V deklaraci typu prvku je po\u017eadov\u00e1n typ prvku." }, { "SpaceRequiredBeforeContentspecInElementDecl", "Za typem prvku \"{0}\" v deklaraci typu prvku je po\u017eadov\u00e1na mezera." }, { "ContentspecRequiredInElementDecl", "Za typem prvku \"{0}\" v deklaraci typu prvku je po\u017eadov\u00e1no omezen\u00ed." }, { "ElementDeclUnterminated", "Deklarace typu prvku \"{0}\" mus\u00ed b\u00fdt ukon\u010dena odd\u011blova\u010dem ''>''." }, { "OpenParenOrElementTypeRequiredInChildren", "V deklaraci typu prvku \"{0}\" je po\u017eadov\u00e1n znak ''('' nebo typ prvku." }, { "CloseParenRequiredInChildren", "V deklaraci typu prvku \"{0}\" je po\u017eadov\u00e1n znak '')''." }, { "ElementTypeRequiredInMixedContent", "V deklaraci typu prvku \"{0}\" je po\u017eadov\u00e1n typ prvku." }, { "CloseParenRequiredInMixedContent", "V deklaraci typu prvku \"{0}\" je po\u017eadov\u00e1n znak '')''." }, { "MixedContentUnterminated", "Pokud jsou omezeny typy pod\u0159\u00edzen\u00fdch prvk\u016f, mus\u00ed b\u00fdt model sm\u00ed\u0161en\u00e9ho obsahu \"{0}\" ukon\u010den znaky \")*\"." }, { "SpaceRequiredBeforeElementTypeInAttlistDecl", "Za \u0159et\u011bzcem  \"<!ATTLIST\" v deklaraci seznamu atribut\u016f je po\u017eadov\u00e1na mezera." }, { "ElementTypeRequiredInAttlistDecl", "V deklaraci seznamu atribut\u016f je po\u017eadov\u00e1n typ prvku." }, { "SpaceRequiredBeforeAttributeNameInAttDef", "P\u0159ed n\u00e1zvem atributu v deklaraci seznamu atribut\u016f prvku \"{0}\" je po\u017eadov\u00e1na mezera." }, { "AttributeNameRequiredInAttDef", "V deklaraci seznamu atribut\u016f prvku \"{0}\" mus\u00ed b\u00fdt zad\u00e1n n\u00e1zev atributu." }, { "SpaceRequiredBeforeAttTypeInAttDef", "P\u0159ed typem atributu v deklaraci atributu \"{1}\" prvku \"{0}\" je po\u017eadov\u00e1na mezera." }, { "AttTypeRequiredInAttDef", "V deklaraci atributu \"{1}\" prvku \"{0}\" je po\u017eadov\u00e1n typ atributu." }, { "SpaceRequiredBeforeDefaultDeclInAttDef", "P\u0159ed v\u00fdchoz\u00ed hodnotou atributu v deklaraci atributu \"{1}\" prvku \"{0}\" je po\u017eadov\u00e1na mezera." }, { "DefaultDeclRequiredInAttDef", "V deklaraci atributu \"{1}\" prvku \"{0}\" je po\u017eadov\u00e1na v\u00fdchoz\u00ed hodnota atributu." }, { "SpaceRequiredAfterNOTATIONInNotationType", "Za \u0159et\u011bzcem \"NOTATION\" v deklaraci atribut\u016f \"{1}\" je po\u017eadov\u00e1na mezera." }, { "OpenParenRequiredInNotationType", "Za \u0159et\u011bzcem \"NOTATION\" v deklaraci atribut\u016f \"{1}\" mus\u00ed n\u00e1sledovat znak ''(''." }, { "NameRequiredInNotationType", "V seznamu typu notace pro deklaraci atribut\u016f \"{1}\" je po\u017eadov\u00e1n n\u00e1zev notace." }, { "NotationTypeUnterminated", "Seznam typu notace v deklaraci atribut\u016f \"{1}\" mus\u00ed b\u00fdt ukon\u010den znakem '')''." }, { "NmtokenRequiredInEnumeration", "V seznamu v\u00fd\u010dtov\u00fdch typ\u016f deklarace atribut\u016f \"{1}\" je po\u017eadov\u00e1n token n\u00e1zvu." }, { "EnumerationUnterminated", "Seznam v\u00fd\u010dtov\u00fdch typ\u016f deklarace atribut\u016f \"{1}\" mus\u00ed b\u00fdt ukon\u010den znakem '')''." }, { "SpaceRequiredAfterFIXEDInDefaultDecl", "Za \u0159et\u011bzcem \"FIXED\" v deklaraci atribut\u016f \"{1}\" je po\u017eadov\u00e1na mezera." }, { "IncludeSectUnterminated", "Zahrnut\u00e1 podm\u00ednkov\u00e1 sekce mus\u00ed kon\u010dit znaky \"]]>\"." }, { "IgnoreSectUnterminated", "Vylou\u010den\u00e1 podm\u00ednkov\u00e1 sekce mus\u00ed kon\u010dit znaky \"]]>\"." }, { "NameRequiredInPEReference", "Po znaku '%' v odkazu entity parametru mus\u00ed bezprost\u0159edn\u011b n\u00e1sledovat n\u00e1zev entity." }, { "SemicolonRequiredInPEReference", "Odkaz entity parametru \"%{0};\" mus\u00ed b\u00fdt ukon\u010den odd\u011blova\u010dem  '';''." }, { "SpaceRequiredBeforeEntityNameInEntityDecl", "Za \u0159et\u011bzcem  \"<!ENTITY\" v deklaraci entity je po\u017eadov\u00e1na mezera." }, { "SpaceRequiredBeforePercentInPEDecl", "Mezi \u0159et\u011bzcem \"<!ENTITY\" a znakem '%' v deklaraci entity parametru je po\u017eadov\u00e1na mezera." }, { "SpaceRequiredBeforeEntityNameInPEDecl", "Mezi znakem '%' a n\u00e1zvem entity v deklaraci entity parametru je po\u017eadov\u00e1na mezera." }, { "EntityNameRequiredInEntityDecl", "V deklaraci entity je po\u017eadov\u00e1n n\u00e1zev entity." }, { "SpaceRequiredAfterEntityNameInEntityDecl", "Mezi n\u00e1zvem entity \"{0}\" a definic\u00ed v deklaraci entity je po\u017eadov\u00e1na mezera." }, { "SpaceRequiredBeforeNDataInUnparsedEntityDecl", "P\u0159ed \u0159et\u011bzcem \"NDATA\" v deklaraci entity \"{0}\" je po\u017eadov\u00e1na mezera." }, { "SpaceRequiredBeforeNotationNameInUnparsedEntityDecl", "Mezi \u0159et\u011bzcem \"NDATA\" a n\u00e1zvem notace v deklaraci entity \"{0}\" je po\u017eadov\u00e1na mezera." }, { "NotationNameRequiredInUnparsedEntityDecl", "Za \u0159et\u011bzcem \"NDATA\" v deklaraci entity \"{0}\" je po\u017eadov\u00e1n n\u00e1zev notace." }, { "EntityDeclUnterminated", "Deklarace entity \"{0}\" mus\u00ed b\u00fdt ukon\u010dena odd\u011blova\u010dem ''>''." }, { "ExternalIDRequired", "Extern\u00ed deklarace entity mus\u00ed za\u010d\u00ednat \u0159et\u011bzcem \"SYSTEM\" nebo \"PUBLIC\"." }, { "SpaceRequiredBeforePubidLiteralInExternalID", "Mezi \u0159et\u011bzcem \"PUBLIC\" a ve\u0159ejn\u00fdm identifik\u00e1torem je po\u017eadov\u00e1na mezera." }, { "SpaceRequiredAfterPubidLiteralInExternalID", "Mezi ve\u0159ejn\u00fdm identifik\u00e1torem a identifik\u00e1torem syst\u00e9mu je po\u017eadov\u00e1na mezera." }, { "SpaceRequiredBeforeSystemLiteralInExternalID", "Mezi \u0159et\u011bzcem \"SYSTEM\" a identifik\u00e1torem syst\u00e9mu je po\u017eadov\u00e1na mezera." }, { "URIFragmentInSystemID", "Identifik\u00e1tor syst\u00e9mu by se nem\u011bl zad\u00e1vat jako \u010d\u00e1st identifik\u00e1toru syst\u00e9mu \"{0}\"." }, { "SpaceRequiredBeforeNotationNameInNotationDecl", "Za \u0159et\u011bzcem  \"<!NOTATION\" v deklaraci notace je po\u017eadov\u00e1na mezera." }, { "NotationNameRequiredInNotationDecl", "V deklaraci notace je po\u017eadov\u00e1n n\u00e1zev notace." }, { "SpaceRequiredAfterNotationNameInNotationDecl", "Za n\u00e1zvem notace  \"{0}\" v deklaraci notace je po\u017eadov\u00e1na mezera." }, { "NotationDeclUnterminated", "Deklarace notace \"{0}\" mus\u00ed b\u00fdt ukon\u010dena odd\u011blova\u010dem ''>''." }, { "UndeclaredElementInContentSpec", "Model obsahu prvku \"{0}\" se odkazuje na nedeklarovan\u00fd prvek \"{1}\"." }, { "DuplicateAttDef", "Atribut \"{1}\" typu prvku \"{0}\" je ji\u017e deklarov\u00e1n." }, { "RootElementTypeMustMatchDoctypedecl", "Prvek ko\u0159ene dokumentu \"{1}\" mus\u00ed odpov\u00eddat ko\u0159enu DOCTYPE \"{0}\"." }, { "ImproperDeclarationNesting", "Text z\u00e1m\u011bny entity parametru \"{0}\" mus\u00ed obsahovat spr\u00e1vn\u011b vno\u0159en\u00e9 deklarace." }, { "WhiteSpaceInElementContentWhenStandalone", "Mezi prvky deklarovan\u00fdmi v extern\u00ed analyzovan\u00e9 entit\u011b s kontextem prvku v samostatn\u00e9m dokumentu nesm\u00ed b\u00fdt mezera." }, { "ReferenceToExternallyDeclaredEntityWhenStandalone", "Odkaz na entitu \"{0}\" deklarovan\u00fd v extern\u00ed analyzovan\u00e9 entit\u011b nen\u00ed v samostatn\u00e9m dokumentu povolen." }, { "ExternalEntityNotPermitted", "Odkaz na extern\u00ed entitu \"{0}\" nen\u00ed v samostatn\u00e9m dokumentu povolen." }, { "AttValueChangedDuringNormalizationWhenStandalone", "Hodnota \"{1}\"  atributu \"{0}\" se nesm\u00ed zm\u011bnit normalizac\u00ed (na \"{2}\") v samostatn\u00e9m dokumentu." }, { "DefaultedAttributeNotSpecified", "Atribut \"{1}\" typu prvku \"{0}\" m\u00e1 v\u00fdchoz\u00ed hodnotu a mus\u00ed b\u00fdt zad\u00e1n v samostatn\u00e9m dokumentu." }, { "ContentIncomplete", "Obsah typu prvku \"{0}\" nen\u00ed \u00fapln\u00fd. Mus\u00ed odpov\u00eddat hodnot\u011b: \"{1}\"." }, { "ContentInvalid", "Obsah typu prvku \"{0}\" mus\u00ed odpov\u00eddat hodnot\u011b: \"{1}\"." }, { "ElementNotDeclared", "Je nutn\u00e9 deklarovat typ prvku \"{0}\"." }, { "AttributeNotDeclared", "Je nutn\u00e9 deklarovat atribut \"{1}\" typu prvku \"{0}\"." }, { "ElementAlreadyDeclared", "Typ prvku \"{0}\" se nesm\u00ed deklarovat v\u00edcekr\u00e1t." }, { "ImproperGroupNesting", "Text z\u00e1m\u011bny entity parametru \"{0}\" mus\u00ed obsahovat spr\u00e1vn\u011b vno\u0159en\u00e9 dvojice z\u00e1vorek." }, { "DuplicateTypeInMixedContent", "Typ prvku \"{0}\" ji\u017e byl v obsahu modelu ur\u010den." }, { "NoNotationOnEmptyElement", "Z d\u016fvod\u016f kompatibility nesm\u00ed b\u00fdt atribut typu NOTATION deklarov\u00e1n na prvku deklarovan\u00e9m jako EMPTY." }, { "ENTITIESInvalid", "Hodnotou atributu \"{1}\" typu ENTITIES mus\u00ed b\u00fdt n\u00e1zvy jedn\u00e9 nebo v\u00edce neanalyzovan\u00fdch entit." }, { "ENTITYInvalid", "Hodnotou atributu \"{1}\" typu ENTITY mus\u00ed b\u00fdt n\u00e1zev neanalyzovan\u00e9 entity." }, { "IDDefaultTypeInvalid", "ID atributu \"{0}\" mus\u00ed m\u00edt deklarovanou hodnotu \"#IMPLIED\" nebo \"#REQUIRED\"." }, { "IDInvalid", "Hodnotou atributu \"{0}\" typu ID mus\u00ed b\u00fdt n\u00e1zev." }, { "IDNotUnique", "Hodnota atributu \"{0}\" typu ID mus\u00ed b\u00fdt v dokumentu jedine\u010dn\u00e1." }, { "IDREFInvalid", "Hodnotou atributu \"{0}\" typu IDREF mus\u00ed b\u00fdt n\u00e1zev." }, { "IDREFSInvalid", "Hodnotou atributu \"{0}\" typu IDREFS mus\u00ed b\u00fdt jeden nebo v\u00edce n\u00e1zv\u016f." }, { "AttributeValueNotInList", "Atribut \"{0}\" s hodnotou \"{1}\"  mus\u00ed m\u00edt hodnotu ze seznamu \"{2}\"." }, { "NMTOKENInvalid", "Hodnotou atributu \"{0}\" typu NMTOKEN mus\u00ed b\u00fdt token n\u00e1zvu." }, { "NMTOKENSInvalid", "Hodnotou atributu \"{0}\" typu NMTOKENS mus\u00ed b\u00fdt jeden nebo v\u00edce token\u016f n\u00e1zv\u016f." }, { "ElementWithIDRequired", "V dokumentu mus\u00ed b\u00fdt obsa\u017een prvek s identifik\u00e1torem \"{0}\"." }, { "MoreThanOneIDAttribute", "Typ prvku \"{0}\" ji\u017e m\u00e1 atribut \"{1}\" pro ID typu. Druh\u00fd atribut \"{2}\" nen\u00ed pro ID typu povolen." }, { "MoreThanOneNotationAttribute", "Typ prvku \"{0}\" ji\u017e m\u00e1 atribut \"{1}\" typu NOTATION. Druh\u00fd atribut \"{2}\" typu NOTATION nen\u00ed povolen." }, { "FIXEDAttValueInvalid", "Atribut \"{1}\" s hodnotou \"{2}\" mus\u00ed m\u00edt hodnotu \"{3}\"." }, { "RequiredAttributeNotSpecified", "Atribut \"{1}\" typu prvku \"{0}\" je po\u017eadov\u00e1n a je nutn\u00e9 jej zadat." }, { "AttDefaultInvalid", "V\u00fdchoz\u00ed hodnota \"{1}\" mus\u00ed vyhovovat lexik\u00e1ln\u00edm omezen\u00edm deklarovan\u00fdm pro atribut \"{0}\"." }, { "ImproperConditionalSectionNesting", "Text pro nahrazen\u00ed entity parametru \"{0}\" mus\u00ed obsahovat spr\u00e1vn\u011b vno\u0159en\u00e9 podm\u00ednkov\u00e9 sekce." }, { "NotationNotDeclaredForNotationTypeAttribute", "Je nutn\u00e9 deklarovat notaci \"{2}\", je-li odkazov\u00e1na v seznamu typu notac\u00ed atributu \"{1}\"." }, { "NotationNotDeclaredForUnparsedEntityDecl", "Je nutn\u00e9 deklarovat notaci \"{1}\", je-li odkazov\u00e1na v deklaraci neanalyzovan\u00e9 entity pro \"{0}\"." }, { "UniqueNotationName", "Dan\u00fd n\u00e1zev (Name) m\u016f\u017ee b\u00fdt deklarov\u00e1n pouze v jedn\u00e9 deklaraci notace." }, { "ReferenceToExternalEntity", "Odkaz na extern\u00ed entitu \"&{0};\" nen\u00ed v hodnot\u011b atributu povolen." }, { "PENotDeclared", "Entita parametru \"{0}\" byla odkazov\u00e1na, ale nebyla deklarov\u00e1na." }, { "ReferenceToUnparsedEntity", "Odkaz na neanalyzovanou entitu \"&{0};\" nen\u00ed povolen." }, { "RecursiveReference", "Rekurentn\u00ed odkaz \"&{0};\". (Cesta odkazu: {1})" }, { "RecursivePEReference", "Rekurentn\u00ed odkaz \"%{0};\". (Cesta odkazu: {1})" }, { "EncodingNotSupported", "K\u00f3dov\u00e1n\u00ed \"{0}\" nen\u00ed podporov\u00e1no." }, { "EncodingRequired", "Analyzovan\u00e1 entita k\u00f3dovan\u00e1 v k\u00f3dov\u00e1n\u00ed UTF-8 nebo UTF-16 mus\u00ed obsahovat deklaraci k\u00f3dov\u00e1n\u00ed." } };
        MESSAGE_KEYS = new String[] { "QuoteRequiredInEntityValue", "InvalidCharInEntityValue", "InvalidCharInSystemID", "InvalidCharInPublicID", "InvalidCharInDoctypedecl", "InvalidCharInInternalSubset", "InvalidCharInExternalSubset", "InvalidCharInIgnoreSect", "QuoteRequiredInSystemID", "SystemIDUnterminated", "QuoteRequiredInPublicID", "PublicIDUnterminated", "PubidCharIllegal", "EntityValueUnterminated", "SpaceRequiredBeforeRootElementTypeInDoctypedecl", "RootElementTypeRequired", "DoctypedeclUnterminated", "PEReferenceWithinMarkup", "PEReferenceContainsIncompleteMarkup", "MarkupNotRecognizedInDTD", "XMLSpaceDeclarationIllegal", "SpaceRequiredBeforeElementTypeInElementDecl", "ElementTypeRequiredInElementDecl", "SpaceRequiredBeforeContentspecInElementDecl", "ContentspecRequiredInElementDecl", "ElementDeclUnterminated", "OpenParenOrElementTypeRequiredInChildren", "CloseParenRequiredInChildren", "ElementTypeRequiredInMixedContent", "CloseParenRequiredInMixedContent", "MixedContentUnterminated", "SpaceRequiredBeforeElementTypeInAttlistDecl", "ElementTypeRequiredInAttlistDecl", "SpaceRequiredBeforeAttributeNameInAttDef", "AttributeNameRequiredInAttDef", "SpaceRequiredBeforeAttTypeInAttDef", "AttTypeRequiredInAttDef", "SpaceRequiredBeforeDefaultDeclInAttDef", "DefaultDeclRequiredInAttDef", "SpaceRequiredAfterNOTATIONInNotationType", "OpenParenRequiredInNotationType", "NameRequiredInNotationType", "NotationTypeUnterminated", "NmtokenRequiredInEnumeration", "EnumerationUnterminated", "SpaceRequiredAfterFIXEDInDefaultDecl", "IncludeSectUnterminated", "IgnoreSectUnterminated", "NameRequiredInPEReference", "SemicolonRequiredInPEReference", "SpaceRequiredBeforeEntityNameInEntityDecl", "SpaceRequiredBeforePercentInPEDecl", "SpaceRequiredBeforeEntityNameInPEDecl", "EntityNameRequiredInEntityDecl", "SpaceRequiredAfterEntityNameInEntityDecl", "SpaceRequiredBeforeNDataInUnparsedEntityDecl", "SpaceRequiredBeforeNotationNameInUnparsedEntityDecl", "NotationNameRequiredInUnparsedEntityDecl", "EntityDeclUnterminated", "ExternalIDRequired", "SpaceRequiredBeforePubidLiteralInExternalID", "SpaceRequiredAfterPubidLiteralInExternalID", "SpaceRequiredBeforeSystemLiteralInExternalID", "URIFragmentInSystemID", "SpaceRequiredBeforeNotationNameInNotationDecl", "NotationNameRequiredInNotationDecl", "SpaceRequiredAfterNotationNameInNotationDecl", "NotationDeclUnterminated", "UndeclaredElementInContentSpec", "DuplicateAttDef", "RootElementTypeMustMatchDoctypedecl", "ImproperDeclarationNesting", "WhiteSpaceInElementContentWhenStandalone", "ReferenceToExternallyDeclaredEntityWhenStandalone", "ExternalEntityNotPermitted", "AttValueChangedDuringNormalizationWhenStandalone", "DefaultedAttributeNotSpecified", "ContentIncomplete", "ContentInvalid", "ElementNotDeclared", "AttributeNotDeclared", "ElementAlreadyDeclared", "ImproperGroupNesting", "DuplicateTypeInMixedContent", "NoNotationOnEmptyElement", "ENTITIESInvalid", "ENTITYInvalid", "IDDefaultTypeInvalid", "IDInvalid", "IDNotUnique", "IDREFInvalid", "IDREFSInvalid", "AttributeValueNotInList", "NMTOKENInvalid", "NMTOKENSInvalid", "ElementWithIDRequired", "MoreThanOneIDAttribute", "MoreThanOneNotationAttribute", "FIXEDAttValueInvalid", "RequiredAttributeNotSpecified", "AttDefaultInvalid", "ImproperConditionalSectionNesting", "NotationNotDeclaredForNotationTypeAttribute", "NotationNotDeclaredForUnparsedEntityDecl", "UniqueNotationName", "ReferenceToExternalEntity", "PENotDeclared", "ReferenceToUnparsedEntity", "RecursiveReference", "RecursivePEReference", "EncodingNotSupported", "EncodingRequired", "QuoteRequiredInEntityValue", "InvalidCharInEntityValue", "InvalidCharInSystemID", "InvalidCharInPublicID", "InvalidCharInDoctypedecl", "InvalidCharInInternalSubset", "InvalidCharInExternalSubset", "InvalidCharInIgnoreSect", "QuoteRequiredInSystemID", "SystemIDUnterminated", "QuoteRequiredInPublicID", "PublicIDUnterminated", "PubidCharIllegal", "EntityValueUnterminated", "SpaceRequiredBeforeRootElementTypeInDoctypedecl", "RootElementTypeRequired", "DoctypedeclUnterminated", "PEReferenceWithinMarkup", "PEReferenceContainsIncompleteMarkup", "MarkupNotRecognizedInDTD", "XMLSpaceDeclarationIllegal", "SpaceRequiredBeforeElementTypeInElementDecl", "ElementTypeRequiredInElementDecl", "SpaceRequiredBeforeContentspecInElementDecl", "ContentspecRequiredInElementDecl", "ElementDeclUnterminated", "OpenParenOrElementTypeRequiredInChildren", "CloseParenRequiredInChildren", "ElementTypeRequiredInMixedContent", "CloseParenRequiredInMixedContent", "MixedContentUnterminated", "SpaceRequiredBeforeElementTypeInAttlistDecl", "ElementTypeRequiredInAttlistDecl", "SpaceRequiredBeforeAttributeNameInAttDef", "AttributeNameRequiredInAttDef", "SpaceRequiredBeforeAttTypeInAttDef", "AttTypeRequiredInAttDef", "SpaceRequiredBeforeDefaultDeclInAttDef", "DefaultDeclRequiredInAttDef", "SpaceRequiredAfterNOTATIONInNotationType", "OpenParenRequiredInNotationType", "NameRequiredInNotationType", "NotationTypeUnterminated", "NmtokenRequiredInEnumeration", "EnumerationUnterminated", "SpaceRequiredAfterFIXEDInDefaultDecl", "IncludeSectUnterminated", "IgnoreSectUnterminated", "NameRequiredInPEReference", "SemicolonRequiredInPEReference", "SpaceRequiredBeforeEntityNameInEntityDecl", "SpaceRequiredBeforePercentInPEDecl", "SpaceRequiredBeforeEntityNameInPEDecl", "EntityNameRequiredInEntityDecl", "SpaceRequiredAfterEntityNameInEntityDecl", "SpaceRequiredBeforeNDataInUnparsedEntityDecl", "SpaceRequiredBeforeNotationNameInUnparsedEntityDecl", "NotationNameRequiredInUnparsedEntityDecl", "EntityDeclUnterminated", "ExternalIDRequired", "SpaceRequiredBeforePubidLiteralInExternalID", "SpaceRequiredAfterPubidLiteralInExternalID", "SpaceRequiredBeforeSystemLiteralInExternalID", "URIFragmentInSystemID", "SpaceRequiredBeforeNotationNameInNotationDecl", "NotationNameRequiredInNotationDecl", "SpaceRequiredAfterNotationNameInNotationDecl", "NotationDeclUnterminated", "UndeclaredElementInContentSpec", "DuplicateAttDef", "RootElementTypeMustMatchDoctypedecl", "ImproperDeclarationNesting", "WhiteSpaceInElementContentWhenStandalone", "ReferenceToExternallyDeclaredEntityWhenStandalone", "ExternalEntityNotPermitted", "AttValueChangedDuringNormalizationWhenStandalone", "DefaultedAttributeNotSpecified", "ContentIncomplete", "ContentInvalid", "ElementNotDeclared", "AttributeNotDeclared", "ElementAlreadyDeclared", "ImproperGroupNesting", "DuplicateTypeInMixedContent", "NoNotationOnEmptyElement", "ENTITIESInvalid", "ENTITYInvalid", "IDDefaultTypeInvalid", "IDInvalid", "IDNotUnique", "IDREFInvalid", "IDREFSInvalid", "AttributeValueNotInList", "NMTOKENInvalid", "NMTOKENSInvalid", "ElementWithIDRequired", "MoreThanOneIDAttribute", "MoreThanOneNotationAttribute", "FIXEDAttValueInvalid", "RequiredAttributeNotSpecified", "AttDefaultInvalid", "ImproperConditionalSectionNesting", "NotationNotDeclaredForNotationTypeAttribute", "NotationNotDeclaredForUnparsedEntityDecl", "UniqueNotationName", "ReferenceToExternalEntity", "PENotDeclared", "ReferenceToUnparsedEntity", "RecursiveReference", "RecursivePEReference", "EncodingNotSupported", "EncodingRequired", "QuoteRequiredInEntityValue", "InvalidCharInEntityValue", "InvalidCharInSystemID", "InvalidCharInPublicID", "InvalidCharInDoctypedecl", "InvalidCharInInternalSubset", "InvalidCharInExternalSubset", "InvalidCharInIgnoreSect", "QuoteRequiredInSystemID", "SystemIDUnterminated", "QuoteRequiredInPublicID", "PublicIDUnterminated", "PubidCharIllegal", "EntityValueUnterminated", "SpaceRequiredBeforeRootElementTypeInDoctypedecl", "RootElementTypeRequired", "DoctypedeclUnterminated", "PEReferenceWithinMarkup", "PEReferenceContainsIncompleteMarkup", "MarkupNotRecognizedInDTD", "XMLSpaceDeclarationIllegal", "SpaceRequiredBeforeElementTypeInElementDecl", "ElementTypeRequiredInElementDecl", "SpaceRequiredBeforeContentspecInElementDecl", "ContentspecRequiredInElementDecl", "ElementDeclUnterminated", "OpenParenOrElementTypeRequiredInChildren", "CloseParenRequiredInChildren", "ElementTypeRequiredInMixedContent", "CloseParenRequiredInMixedContent", "MixedContentUnterminated", "SpaceRequiredBeforeElementTypeInAttlistDecl", "ElementTypeRequiredInAttlistDecl", "SpaceRequiredBeforeAttributeNameInAttDef", "AttributeNameRequiredInAttDef", "SpaceRequiredBeforeAttTypeInAttDef", "AttTypeRequiredInAttDef", "SpaceRequiredBeforeDefaultDeclInAttDef", "DefaultDeclRequiredInAttDef", "SpaceRequiredAfterNOTATIONInNotationType", "OpenParenRequiredInNotationType", "NameRequiredInNotationType", "NotationTypeUnterminated", "NmtokenRequiredInEnumeration", "EnumerationUnterminated", "SpaceRequiredAfterFIXEDInDefaultDecl", "IncludeSectUnterminated", "IgnoreSectUnterminated", "NameRequiredInPEReference", "SemicolonRequiredInPEReference", "SpaceRequiredBeforeEntityNameInEntityDecl", "SpaceRequiredBeforePercentInPEDecl", "SpaceRequiredBeforeEntityNameInPEDecl", "EntityNameRequiredInEntityDecl", "SpaceRequiredAfterEntityNameInEntityDecl", "SpaceRequiredBeforeNDataInUnparsedEntityDecl", "SpaceRequiredBeforeNotationNameInUnparsedEntityDecl", "NotationNameRequiredInUnparsedEntityDecl", "EntityDeclUnterminated", "ExternalIDRequired", "SpaceRequiredBeforePubidLiteralInExternalID", "SpaceRequiredAfterPubidLiteralInExternalID", "SpaceRequiredBeforeSystemLiteralInExternalID", "URIFragmentInSystemID", "SpaceRequiredBeforeNotationNameInNotationDecl", "NotationNameRequiredInNotationDecl", "SpaceRequiredAfterNotationNameInNotationDecl", "NotationDeclUnterminated", "UndeclaredElementInContentSpec", "DuplicateAttDef", "RootElementTypeMustMatchDoctypedecl", "ImproperDeclarationNesting", "WhiteSpaceInElementContentWhenStandalone", "ReferenceToExternallyDeclaredEntityWhenStandalone", "ExternalEntityNotPermitted", "AttValueChangedDuringNormalizationWhenStandalone", "DefaultedAttributeNotSpecified", "ContentIncomplete", "ContentInvalid", "ElementNotDeclared", "AttributeNotDeclared", "ElementAlreadyDeclared", "ImproperGroupNesting", "DuplicateTypeInMixedContent", "NoNotationOnEmptyElement", "ENTITIESInvalid", "ENTITYInvalid", "IDDefaultTypeInvalid", "IDInvalid", "IDNotUnique", "IDREFInvalid", "IDREFSInvalid", "AttributeValueNotInList", "NMTOKENInvalid", "NMTOKENSInvalid", "ElementWithIDRequired", "MoreThanOneIDAttribute", "MoreThanOneNotationAttribute", "FIXEDAttValueInvalid", "RequiredAttributeNotSpecified", "AttDefaultInvalid", "ImproperConditionalSectionNesting", "NotationNotDeclaredForNotationTypeAttribute", "NotationNotDeclaredForUnparsedEntityDecl", "UniqueNotationName", "ReferenceToExternalEntity", "PENotDeclared", "ReferenceToUnparsedEntity", "RecursiveReference", "RecursivePEReference", "EncodingNotSupported", "EncodingRequired" };
    }
}
