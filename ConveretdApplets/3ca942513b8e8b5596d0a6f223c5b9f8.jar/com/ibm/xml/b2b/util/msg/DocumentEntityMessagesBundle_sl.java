// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.msg;

public final class DocumentEntityMessagesBundle_sl extends ErrorMessageBundle
{
    private static final Object[][] CONTENTS;
    private static final String[] MESSAGE_KEYS;
    
    protected Object[][] getContents() {
        return DocumentEntityMessagesBundle_sl.CONTENTS;
    }
    
    public String getString(final int n) {
        return this.getString(DocumentEntityMessagesBundle_sl.MESSAGE_KEYS[n]);
    }
    
    static {
        CONTENTS = new Object[][] { { "BadMessageKey", "The error message corresponding to the message key can not be found." }, { "FormatFailed", "An internal error occurred while formatting the following message:\n  " }, { "RootElementRequired", "Korenski element je zahtevan v pravilno oblikovanem dokumentu." }, { "InvalidCharInCDSect", "V odseku CDATA je bil najden neveljaven XML znak(Unicode: 0x{0})." }, { "InvalidCharInContent", "V vsebini elementa dokumenta je bil najden neveljaven XML znak (Unicode: 0x{0})." }, { "InvalidCharInMisc", "V oznaki za koncem vsebine elementa je bil najden neveljaven XML znak (Unicode: 0x{0})." }, { "InvalidCharInProlog", "V uvodu dokumenta je bil najden neveljaven XML znak (Unicode: 0x{0})." }, { "CDEndInContent", "Zaporedje znakov \"]]>\" se sme pojaviti v vsebini samo v primeru, ko je uporabljeno za ozna\u010ditev konca odseka CDATA." }, { "CDSectUnterminated", "Odsek CDATA se mora kon\u010dati z \"]]>\"." }, { "EqRequiredInXMLDecl", "Znak '' = '' mora slediti \"{0}\" v XML deklaraciji." }, { "QuoteRequiredInXMLDecl", "Vrednost, ki sledi za \"{0}\" v XML deklaraciji, mora biti niz v narekovajih." }, { "XMLDeclUnterminated", "XML deklaracija se mora kon\u010dati z \"?>\"." }, { "VersionInfoRequired", "Razli\u010dica se zahteva v XML deklaraciji." }, { "MarkupNotRecognizedInProlog", "Oznaka v dokumentu pred korenskim elementom mora biti pravilno oblikovana." }, { "MarkupNotRecognizedInMisc", "Oznaka v dokumentu za korenskim elementom mora biti pravilno oblikovana." }, { "SDDeclInvalid", "Vrednost deklaracije samostojnega dokumenta mora biti \"da\" ali \"ne\", ne pa \"{0}\"." }, { "ETagRequired", "Tip elementa \"{0}\" ne ustreza pri\u010dakovani kon\u010dni oznaki \"</{1}>\"." }, { "ElementUnterminated", "Tipu elementa \"{0}\" mora slediti ena izmed specifikacij atributa, \">\" or \"/>\"." }, { "EqRequiredInAttribute", "Imenu atributa \"{1}\" mora slediti znak '' = ''." }, { "AttributeNotUnique", "Atribut \"{1}\" je \u017ee bil naveden za element \"{0}\"." }, { "ETagUnterminated", "Kon\u010dna oznaka za tip elementa \"{0}\" se mora kon\u010dati z lo\u010dilom ''>''." }, { "MarkupNotRecognizedInContent", "Vsebino elementov morajo sestavljati pravilno oblikovani znakovni podatki ali oznake." }, { "ElementEntityMismatch", "Element \"{0}\" se mora za\u010deti in kon\u010dati v isti entiteti." }, { "InvalidCharInAttValue", "Neveljaven XML znak (Unicode: 0x{2}) je bil najden v vrednosti atributa \"{1}\"." }, { "InvalidCharInComment", "V komentarju je bil najden neveljaven XML znak(Unicode: 0x{0})." }, { "InvalidCharInPI", "V navodilu za obdelavo je bil najden neveljaven XML znak(Unicode: 0x{0})." }, { "QuoteRequiredInAttValue", "Vrednost atributa \"{1}\" se mora za\u010deti z znakom enojnega ali dvojnega narekovaja." }, { "LessthanInAttValue", "Vrednost atributa \"{1}\" ne sme vsebovati znaka ''<''." }, { "AttributeValueUnterminated", "Vrednost za atribut \"{1}\" se mora kon\u010dati z ustreznim znakom narekovaja." }, { "InvalidCommentStart", "Komentar se mora za\u010deti z \"<!--\"." }, { "DashDashInComment", "Niz \"--\" ni dovoljen v komentarjih." }, { "CommentUnterminated", "Komentar se mora kon\u010dati s \"-->\"." }, { "PITargetRequired", "Navodilo za obdelavo se mora za\u010deti z imenom cilja." }, { "SpaceRequiredInPI", "Med ciljem navodila za obdelavo in podatki se zahteva prazen prostor." }, { "PIUnterminated", "Navodilo za obdelavo se mora kon\u010dati z \"?>\"." }, { "ReservedPITarget", "Cilj navodila za obdelavo, ki se ujema s \"[xX][mM][lL]\", ni dovoljen." }, { "VersionNotSupported", "XML razli\u010dica \"{0}\" ni podprta." }, { "DigitRequiredInCharRef", "\"&#\" mora v znakovnemu sklicu neposredno slediti dvoji\u0161ki prikaz." }, { "HexdigitRequiredInCharRef", "\"&#x\" mora v znakovnemu sklicu neposredno slediti \u0161estnajsti\u0161ki prikaz." }, { "SemicolonRequiredInCharRef", "Znakovni sklic se mora kon\u010dati z lo\u010dilom ';'." }, { "InvalidCharRef", "Znakovni sklic \"&#{0}\" je neveljavni XML znak." }, { "NameRequiredInReference", "'&' v znakovnemu sklicu mora neposredno slediti ime entitete." }, { "SemicolonRequiredInReference", "Sklic na entiteto \"{0}\" se mora kon\u010dati z lo\u010dilom '';''." }, { "EqRequiredInTextDecl", "Znak '' = '' mora slediti \"{0}\" v deklaraciji besedila." }, { "QuoteRequiredInTextDecl", "Vrednost, ki sledi za \"{0}\" v deklaraciji besedila, mora biti niz v narekovajih." }, { "SpaceRequiredInTextDecl", "Med razli\u010dico in deklaracijo kodiranja se zahteva prazen prostor." }, { "TextDeclUnterminated", "Deklaracija besedila se mora kon\u010dati z \"?>\"." }, { "EncodingDeclRequired", "V deklaraciji besedila se zahteva deklaracija kodiranja." }, { "EncodingDeclInvalid", "Neveljavno ime kodiranja \"{0}\"." }, { "EntityNotDeclared", "Splo\u0161na entiteta \"{0}\" je bila sklicevana, a ne razgla\u0161ena." }, { "ColonInName", "Imenski prostori ne dopu\u0161\u010dajo ':', razen v tipih elementov ali imenih atributov." }, { "TwoColonsInQName", "Imenski prostori dopu\u0161\u010dajo samo en ':' v tipih elementov ali imenih atributov." }, { "PrefixDeclared", "Predpona imenskega prostora \"{0}\" ni bila razgla\u0161ena." }, { "PrefixLegal", "Predpona imenskega prostora \"xml\" ni vezana na veljavno ime imenskega prostora." }, { "NamespaceNameEmpty", "Ime imenskega prostora, razgla\u0161en za predpono \"{0}\" morda ni prazen." }, { "NamespaceReserved", "Predpona imenskega prostora \"{0}\" je vezana na rezervirano ime imenskega prostra \"{1}\"." }, { "NamespacePrefixReserved", "Predpona imenskega prostora \"xmlns\" ne sme biti razgla\u0161ena." } };
        MESSAGE_KEYS = new String[] { "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved" };
    }
}
