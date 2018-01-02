// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.msg;

public final class DocumentEntityMessagesBundle_pt_BR extends ErrorMessageBundle
{
    private static final Object[][] CONTENTS;
    private static final String[] MESSAGE_KEYS;
    
    protected Object[][] getContents() {
        return DocumentEntityMessagesBundle_pt_BR.CONTENTS;
    }
    
    public String getString(final int n) {
        return this.getString(DocumentEntityMessagesBundle_pt_BR.MESSAGE_KEYS[n]);
    }
    
    static {
        CONTENTS = new Object[][] { { "BadMessageKey", "The error message corresponding to the message key can not be found." }, { "FormatFailed", "An internal error occurred while formatting the following message:\n  " }, { "RootElementRequired", "O elemento raiz \u00e9 requerido em um documento corretamente formado." }, { "InvalidCharInCDSect", "Um caractere XML inv\u00e1lido XML (Unicode: 0x{0}) foi encontrado na se\u00e7\u00e3o CDATA." }, { "InvalidCharInContent", "Um caractere XML inv\u00e1lido (Unicode: 0x{0}) foi encontrado no conte\u00fado de elementos do documento." }, { "InvalidCharInMisc", "Um caractere XML inv\u00e1lido (Unicode: 0x{0}) foi encontrado na marca\u00e7\u00e3o ap\u00f3s o final do conte\u00fado de elementos." }, { "InvalidCharInProlog", "Um caractere XML inv\u00e1lido (Unicode: 0x{0}) foi encontrado no prolog do documento." }, { "CDEndInContent", "A seq\u00fc\u00eancia de caracteres \"]]>\" n\u00e3o deve aparecer no conte\u00fado, a menos que utilizada para marcar o final de uma se\u00e7\u00e3o CDATA." }, { "CDSectUnterminated", "A se\u00e7\u00e3o CDATA deve terminar com \"]]>\"." }, { "EqRequiredInXMLDecl", "O caractere ''='' deve seguir \"{0}\" na declara\u00e7\u00e3o XML." }, { "QuoteRequiredInXMLDecl", "O valor ap\u00f3s \"{0}\" na declara\u00e7\u00e3o XML deve ser uma cadeia entre aspas." }, { "XMLDeclUnterminated", "A declara\u00e7\u00e3o XML deve terminar com \"?>\"." }, { "VersionInfoRequired", "A vers\u00e3o \u00e9 requerida na declara\u00e7\u00e3o XML." }, { "MarkupNotRecognizedInProlog", "A marca\u00e7\u00e3o no documento antecedente ao elemento raiz deve estar corretamente formada." }, { "MarkupNotRecognizedInMisc", "A marca\u00e7\u00e3o no documento ap\u00f3s o elemento raiz deve estar corretamente formada." }, { "SDDeclInvalid", "O valor da declara\u00e7\u00e3o de documento independente deve ser \"yes\" ou \"no\" e n\u00e3o \"{0}\"." }, { "ETagRequired", "O tipo de elemento \"{0}\" n\u00e3o corresponde \u00e0 marca\u00e7\u00e3o final esperada \"</{1}>\"." }, { "ElementUnterminated", "O tipo de elemento \"{0}\" deve ser seguido pelas especifica\u00e7\u00f5es de atributo \">\" ou \"/>\"." }, { "EqRequiredInAttribute", "O nome do atributo \"{1}\" deve ser seguido pelo caractere ''=''." }, { "AttributeNotUnique", "O atributo \"{1}\" j\u00e1 foi especificado para o elemento \"{0}\"." }, { "ETagUnterminated", "A marca\u00e7\u00e3o final para o tipo de elemento \"{0}\" deve terminar com um delimitador ''>''." }, { "MarkupNotRecognizedInContent", "O conte\u00fado dos elementos deve consistir de marca\u00e7\u00e3o ou dados de caractere corretamente formados." }, { "ElementEntityMismatch", "O elemento \"{0}\" deve come\u00e7ar e terminar na mesma entidade." }, { "InvalidCharInAttValue", "Um caractere XML inv\u00e1lido (Unicode: 0x{2}) foi encontrado no valor do atributo \"{1}\"." }, { "InvalidCharInComment", "Um caractere XML inv\u00e1lido (Unicode: 0x{0}) foi encontrado no coment\u00e1rio." }, { "InvalidCharInPI", "Um caractere XML inv\u00e1lido (Unicode: 0x{0}) foi encontrado na instru\u00e7\u00e3o de processamento." }, { "QuoteRequiredInAttValue", "O valor do atributo \"{1}\" deve come\u00e7ar com um caractere de aspas simples ou duplas." }, { "LessthanInAttValue", "O valor do atributo \"{1}\" n\u00e3o deve conter o caractere ''<''." }, { "AttributeValueUnterminated", "O valor do atributo \"{1}\" deve terminar com o caractere de aspas correspondente." }, { "InvalidCommentStart", "O coment\u00e1rio deve come\u00e7ar com \"<!--\"." }, { "DashDashInComment", "A cadeia \"--\" n\u00e3o \u00e9 permitida dentro dos coment\u00e1rios." }, { "CommentUnterminated", "O coment\u00e1rio deve terminar com \"-->\"." }, { "PITargetRequired", "A instru\u00e7\u00e3o de processamento deve iniciar com o nome do destino." }, { "SpaceRequiredInPI", "Um espa\u00e7o em branco \u00e9 requerido entre o destino da instru\u00e7\u00e3o de processamento e os dados." }, { "PIUnterminated", "A instru\u00e7\u00e3o de processamento deve terminar com \"?>\"." }, { "ReservedPITarget", "A correspond\u00eancia do destino da instru\u00e7\u00e3o de processamento \"[xX][mM][lL]\" n\u00e3o \u00e9 permitida." }, { "VersionNotSupported", "A vers\u00e3o XML \"{0}\" n\u00e3o \u00e9 suportada." }, { "DigitRequiredInCharRef", "A representa\u00e7\u00e3o decimal deve seguir imediatamente o \"&#\" em uma refer\u00eancia de caractere." }, { "HexdigitRequiredInCharRef", "Uma representa\u00e7\u00e3o hexadecimal deve seguir imediatamente o \"&#x\" em uma refer\u00eancia de caractere." }, { "SemicolonRequiredInCharRef", "A refer\u00eancia de caractere deve terminar com o delimitador ';' ." }, { "InvalidCharRef", "A refer\u00eancia de caractere \"&#{0}\" \u00e9 um caractere XML inv\u00e1lido." }, { "NameRequiredInReference", "O nome da entidade deve seguir imediatamente o '&' na refer\u00eancia de entidade." }, { "SemicolonRequiredInReference", "A refer\u00eancia \u00e0 entidade \"{0}\" deve terminar com o delimitador '';''." }, { "EqRequiredInTextDecl", "O caractere ''='' deve seguir \"{0}\" na declara\u00e7\u00e3o de texto." }, { "QuoteRequiredInTextDecl", "O valor ap\u00f3s \"{0}\" na declara\u00e7\u00e3o de texto deve ser uma cadeia entre aspas." }, { "SpaceRequiredInTextDecl", "O espa\u00e7o em branco \u00e9 necess\u00e1rio entre a vers\u00e3o e a declara\u00e7\u00e3o de codifica\u00e7\u00e3o." }, { "TextDeclUnterminated", "A declara\u00e7\u00e3o de texto deve terminar com \"?>\"." }, { "EncodingDeclRequired", "A declara\u00e7\u00e3o de codifica\u00e7\u00e3o \u00e9 requerida na declara\u00e7\u00e3o de texto." }, { "EncodingDeclInvalid", "Nome de codifica\u00e7\u00e3o inv\u00e1lido \"{0}\"." }, { "EntityNotDeclared", "A entidade geral \"{0}\" foi referenciada, mas n\u00e3o foi declarada." }, { "ColonInName", "Espa\u00e7os de nomes n\u00e3o permitem ':', exceto em tipos de elementos ou em nomes de atributos." }, { "TwoColonsInQName", "Espa\u00e7os de nomes apenas permitem um ':' em tipos de elementos ou nomes de atributos." }, { "PrefixDeclared", "O prefixo do espa\u00e7o de nomes \"{0}\" n\u00e3o foi declarado." }, { "PrefixLegal", "O prefixo do espa\u00e7o de nomes \"xml\" n\u00e3o est\u00e1 ligado a um nome do espa\u00e7o de nomes v\u00e1lido." }, { "NamespaceNameEmpty", "O nome do espa\u00e7o de nomes declarado para o prefixo \"{0}\" n\u00e3o pode estar vazio." }, { "NamespaceReserved", "O prefixo do espa\u00e7o de nomes \"{0}\" est\u00e1 ligado ao nome do espa\u00e7o de nomes reservado \"{1}\"." }, { "NamespacePrefixReserved", "O prefixo do espa\u00e7o de nomes \"xmlns\" n\u00e3o deve ser declarado." } };
        MESSAGE_KEYS = new String[] { "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved" };
    }
}
