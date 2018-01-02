// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.msg;

public final class DocumentEntityMessagesBundle_de extends ErrorMessageBundle
{
    private static final Object[][] CONTENTS;
    private static final String[] MESSAGE_KEYS;
    
    protected Object[][] getContents() {
        return DocumentEntityMessagesBundle_de.CONTENTS;
    }
    
    public String getString(final int n) {
        return this.getString(DocumentEntityMessagesBundle_de.MESSAGE_KEYS[n]);
    }
    
    static {
        CONTENTS = new Object[][] { { "BadMessageKey", "The error message corresponding to the message key can not be found." }, { "FormatFailed", "An internal error occurred while formatting the following message:\n  " }, { "RootElementRequired", "Das Stammelement ist in einem syntaktisch korrekten Dokument erforderlich." }, { "InvalidCharInCDSect", "Im Abschnitt CDATA wurde ein ung\u00fcltiges XML-Zeichen (Unicode: 0x{0}) gefunden." }, { "InvalidCharInContent", "Im Elementinhalt des Dokuments wurde ein ung\u00fcltiges XML-Zeichen (Unicode: 0x{0}) gefunden." }, { "InvalidCharInMisc", "In den Formatierungssteuerzeichen nach dem Elementinhalt wurde ein ung\u00fcltiges XML-Zeichen (Unicode: 0x{0}) gefunden." }, { "InvalidCharInProlog", "Im Prolog des Dokuments wurde ein ung\u00fcltiges XML-Zeichen (Unicode: 0x{0}) gefunden." }, { "CDEndInContent", "Die Zeichenfolge \"]]>\" darf im Inhalt nur erscheinen, wenn sie das Ende eines Abschnitts CDATA markiert." }, { "CDSectUnterminated", "Der Abschnitt CDATA muss mit \"]]>\" enden." }, { "EqRequiredInXMLDecl", "Das Zeichen ''='' muss nach \"{0}\" in der XML-Deklaration stehen." }, { "QuoteRequiredInXMLDecl", "Der Wert nach \"{0}\" in der XML-Deklaration muss eine Zeichenfolge in Anf\u00fchrungszeichen sein." }, { "XMLDeclUnterminated", "Die XML-Deklaration muss mit \"?>\" enden." }, { "VersionInfoRequired", "Die Version ist in der XML-Deklaration erforderlich." }, { "MarkupNotRecognizedInProlog", "Die Formatierungssteuerzeichen im Dokument vor dem Stammelement m\u00fcssen syntaktisch korrekt sein." }, { "MarkupNotRecognizedInMisc", "Die Formatierungssteuerzeichen im Dokument nach dem Stammelement m\u00fcssen syntaktisch korrekt sein." }, { "SDDeclInvalid", "Der Deklarationswert f\u00fcr ein Standalonedokument muss \"yes\" oder \"no\" sein, nicht \"{0}\"." }, { "ETagRequired", "Der Elementtyp \"{0}\" entspricht nicht dem erwarteten Endbefehl \"</{1}>\"." }, { "ElementUnterminated", "Nach dem Elementtyp \"{0}\" m\u00fcssen Attributspezifikationen, \">\" oder \"/>\" folgen." }, { "EqRequiredInAttribute", "Nach dem Attributnamen \"{1}\" muss das Zeichen ''='' folgen." }, { "AttributeNotUnique", "Das Attribut \"{1}\" wurde bereits f\u00fcr Element \"{0}\" angegeben." }, { "ETagUnterminated", "Der Endbefehl f\u00fcr den Elementtyp \"{0}\" muss mit einem Begrenzer ''>'' beendet werden." }, { "MarkupNotRecognizedInContent", "Der Elementinhalt muss aus syntaktisch korrekten Zeichendaten oder Formatierungssteuerzeichen bestehen." }, { "ElementEntityMismatch", "Das Element \"{0}\" muss innerhalb derselben Entit\u00e4t starten und enden." }, { "InvalidCharInAttValue", "Im Wert des Attributs \"{1}\" wurde ein ung\u00fcltiges XML-Zeichen (Unicode: 0x{2}) gefunden." }, { "InvalidCharInComment", "Im Kommentar wurde ein ung\u00fcltiges XML-Zeichen (Unicode: 0x{0}) gefunden." }, { "InvalidCharInPI", "In der Verarbeitungsanweisung wurde ein ung\u00fcltiges XML-Zeichen (Unicode: 0x{0}) gefunden." }, { "QuoteRequiredInAttValue", "Der Wert des Attributs \"{1}\" muss mit einem einfachen oder doppelten Anf\u00fchrungszeichen beginnen." }, { "LessthanInAttValue", "Der Wert des Attributs \"{1}\" darf nicht das Zeichen ''<'' enthalten." }, { "AttributeValueUnterminated", "Der Wert des Attributs \"{1}\" muss mit dem entsprechenden Anf\u00fchrungszeichen enden." }, { "InvalidCommentStart", "Der Kommentar muss mit \"<!--\" beginnen." }, { "DashDashInComment", "Die Zeichenfolge \"--\" ist innerhalb von Kommentaren nicht zul\u00e4ssig." }, { "CommentUnterminated", "Der Kommentar muss mit \"-->\" enden." }, { "PITargetRequired", "Die Verarbeitungsanweisung muss mit dem Namen des Ziels beginnen." }, { "SpaceRequiredInPI", "Zwischen dem Ziel und den Daten der Verarbeitungsanweisung ist ein Leerzeichen erforderlich." }, { "PIUnterminated", "Die Verarbeitungsanweisung muss mit \"?>\" enden." }, { "ReservedPITarget", "Das \"[xX][mM][lL]\" entsprechende Verarbeitungsanweisungsziel ist nicht zul\u00e4ssig." }, { "VersionNotSupported", "Die XML-Version \"{0}\" wird nicht unterst\u00fctzt." }, { "DigitRequiredInCharRef", "Nach \"&#\" in einem Zeichenverweis muss unmittelbar eine Dezimaldarstellung folgen." }, { "HexdigitRequiredInCharRef", "Nach \"&#x\" in einem Zeichenverweis muss unmittelbar eine hexadezimale Darstellung folgen." }, { "SemicolonRequiredInCharRef", "Der Zeichenverweis muss mit dem Begrenzer ';' enden." }, { "InvalidCharRef", "Zeichenverweis \"&#{0}\" ist ein ung\u00fcltiges XML-Zeichen." }, { "NameRequiredInReference", "Der Entit\u00e4tenname muss unmittelbar nach dem '&' im Entit\u00e4tenverweis folgen." }, { "SemicolonRequiredInReference", "Der Verweis auf Entit\u00e4t \"{0}\" muss mit dem Begrenzer '';'' enden." }, { "EqRequiredInTextDecl", "Das Zeichen ''='' muss nach \"{0}\" in der Textdeklaration stehen." }, { "QuoteRequiredInTextDecl", "Der Wert nach \"{0}\" in der Textdeklaration muss eine Zeichenfolge in Anf\u00fchrungszeichen sein." }, { "SpaceRequiredInTextDecl", "Zwischen der Versions- und der Verschl\u00fcsselungsdeklaration ist ein Leerzeichen erforderlich." }, { "TextDeclUnterminated", "Die Textdeklaration muss mit \"?>\" enden." }, { "EncodingDeclRequired", "Die Verschl\u00fcsselungsdeklaration ist in der Textdeklaration erforderlich." }, { "EncodingDeclInvalid", "Ung\u00fcltiger Verschl\u00fcsselungsname \"{0}\"." }, { "EntityNotDeclared", "Auf die allgemeine Entit\u00e4t \"{0}\" wurde verwiesen, sie wurde aber nicht deklariert." }, { "ColonInName", "Namensbereiche lassen ':' au\u00dfer in Elementtypen oder Attributnamen nicht zu." }, { "TwoColonsInQName", "Namensbereiche erlauben nur ein ':' in Elementtypen oder Attributnamen." }, { "PrefixDeclared", "Das Namensbereichspr\u00e4fix \"{0}\" war nicht deklariert." }, { "PrefixLegal", "Das Namensbereichspr\u00e4fix \"xml\" ist nicht an einen g\u00fcltigen Namensbereichsnamen gebunden." }, { "NamespaceNameEmpty", "Der f\u00fcr Pr\u00e4fix \"{0}\" deklarierte Namensbereichsname darf nicht leer sein." }, { "NamespaceReserved", "Das Namensbereichspr\u00e4fix \"{0}\" ist an den reservierten Namensbereichsnamen \"{1}\" gebunden." }, { "NamespacePrefixReserved", "Das Namensbereichspr\u00e4fix \"xmlns\" darf nicht deklariert werden." } };
        MESSAGE_KEYS = new String[] { "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved" };
    }
}
