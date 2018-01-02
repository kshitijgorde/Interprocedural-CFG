// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.msg;

public final class DocumentEntityMessagesBundle_es extends ErrorMessageBundle
{
    private static final Object[][] CONTENTS;
    private static final String[] MESSAGE_KEYS;
    
    protected Object[][] getContents() {
        return DocumentEntityMessagesBundle_es.CONTENTS;
    }
    
    public String getString(final int n) {
        return this.getString(DocumentEntityMessagesBundle_es.MESSAGE_KEYS[n]);
    }
    
    static {
        CONTENTS = new Object[][] { { "BadMessageKey", "The error message corresponding to the message key can not be found." }, { "FormatFailed", "An internal error occurred while formatting the following message:\n  " }, { "RootElementRequired", "Se necesita el elemento ra\u00edz en un documento bien formado." }, { "InvalidCharInCDSect", "Se ha encontrado un car\u00e1cter XML no v\u00e1lido (Unicode: 0x{0}) en la secci\u00f3n CDATA." }, { "InvalidCharInContent", "Se ha encontrado un car\u00e1cter XML no v\u00e1lido (Unicode: 0x{0}) en el contenido del elemento del documento." }, { "InvalidCharInMisc", "Se ha encontrado un car\u00e1cter XML no v\u00e1lido (Unicode: 0x{0}) en la marca despu\u00e9s del final del contenido del elemento." }, { "InvalidCharInProlog", "Se ha encontrado un car\u00e1cter XML no v\u00e1lido (Unicode: 0x{0}) en el pr\u00f3logo del documento." }, { "CDEndInContent", "La secuencia de caracteres \"]]>\" no debe aparecer en el contenido a menos que se utilice para marcar el final de una secci\u00f3n CDATA." }, { "CDSectUnterminated", "La secci\u00f3n CDATA debe terminar con \"]]>\"." }, { "EqRequiredInXMLDecl", "El car\u00e1cter '' = '' debe seguir a \"{0}\" en la declaraci\u00f3n XML." }, { "QuoteRequiredInXMLDecl", "El valor que sigue a \"{0}\" en la declaraci\u00f3n XML debe ser una serie entrecomillada." }, { "XMLDeclUnterminated", "La declaraci\u00f3n XML debe terminar con \"?>\"." }, { "VersionInfoRequired", "Se precisa la versi\u00f3n en la declaraci\u00f3n XML." }, { "MarkupNotRecognizedInProlog", "La marca del documento que precede al elemento ra\u00edz debe estar bien formada." }, { "MarkupNotRecognizedInMisc", "La marca del documento que sigue al elemento ra\u00edz debe estar bien formada." }, { "SDDeclInvalid", "El valor de la declaraci\u00f3n de documento independiente debe ser \"yes\" o \"no\" y no \"{0}\"." }, { "ETagRequired", "El tipo de elemento \"{0}\" no coincide con el c\u00f3digo de fin \"</{1}>\" esperado." }, { "ElementUnterminated", "El tipo de elemento \"{0}\" debe ir seguido por una de estas especificaciones de atributo: \">\" o \"/>\"." }, { "EqRequiredInAttribute", "El nombre de atributo \"{1}\" debe ir seguido por el car\u00e1cter '' = ''." }, { "AttributeNotUnique", "Ya se hab\u00eda especificado el atributo \"{1}\" para el elemento \"{0}\"." }, { "ETagUnterminated", "El c\u00f3digo de fin para el tipo de elemento \"{0}\" debe finalizar con un delimitador ''>''." }, { "MarkupNotRecognizedInContent", "El contenido de los elementos debe contener datos de caracteres o marcas bien formados." }, { "ElementEntityMismatch", "El elemento \"{0}\" debe comenzar y terminar dentro de la misma entidad." }, { "InvalidCharInAttValue", "Se ha encontrado un car\u00e1cter XML no v\u00e1lido (Unicode: 0x{2}) en el valor del atributo \"{1}\"." }, { "InvalidCharInComment", "Se ha encontrado un car\u00e1cter XML no v\u00e1lido (Unicode: 0x{0}) en el comentario." }, { "InvalidCharInPI", "Se ha encontrado un car\u00e1cter XML no v\u00e1lido (Unicode: 0x{0}) en la instrucci\u00f3n de proceso." }, { "QuoteRequiredInAttValue", "El valor del atributo \"{1}\" debe comenzar con un car\u00e1cter de comilla simple o doble." }, { "LessthanInAttValue", "El valor del atributo \"{1}\" no debe contener el car\u00e1cter ''<''." }, { "AttributeValueUnterminated", "El valor del atributo \"{1}\" debe terminar con el car\u00e1cter de comilla correspondiente." }, { "InvalidCommentStart", "El comentario debe empezar con \"<!--\"." }, { "DashDashInComment", "No se permite la serie \"--\" dentro de los comentarios." }, { "CommentUnterminated", "El comentario debe terminar con \"-->\"." }, { "PITargetRequired", "La instrucci\u00f3n de proceso debe comenzar con el nombre del destino." }, { "SpaceRequiredInPI", "Se precisa un espacio en blanco entre el destino y los datos de la instrucci\u00f3n de proceso." }, { "PIUnterminated", "La instrucci\u00f3n de proceso debe terminar con \"?>\"." }, { "ReservedPITarget", "El destino de la instrucci\u00f3n de proceso correspondiente \"[xX][mM][lL]\" no est\u00e1 permitido." }, { "VersionNotSupported", "La versi\u00f3n de XML \"{0}\" no est\u00e1 soportada." }, { "DigitRequiredInCharRef", "Una representaci\u00f3n decimal debe ir inmediatamente despu\u00e9s de \"&#\" en una referencia de car\u00e1cter." }, { "HexdigitRequiredInCharRef", "Una representaci\u00f3n hexadecimal debe ir inmediatamente despu\u00e9s de \"&#x\" en una referencia de car\u00e1cter." }, { "SemicolonRequiredInCharRef", "La referencia de car\u00e1cter debe terminar con el delimitador ';'." }, { "InvalidCharRef", "La referencia de car\u00e1cter \"&#{0}\" es un car\u00e1cter XML no v\u00e1lido." }, { "NameRequiredInReference", "El nombre de entidad debe ir inmediatamente despu\u00e9s de '&' en la referencia de entidad." }, { "SemicolonRequiredInReference", "La referencia a la entidad \"{0}\" debe terminar con el delimitador '';''." }, { "EqRequiredInTextDecl", "El car\u00e1cter '' = '' debe seguir a \"{0}\" en la declaraci\u00f3n de texto." }, { "QuoteRequiredInTextDecl", "El valor que sigue a \"{0}\" en la declaraci\u00f3n de texto debe ser una serie entrecomillada." }, { "SpaceRequiredInTextDecl", "Se precisa un espacio en blanco entre la versi\u00f3n y la declaraci\u00f3n de codificaci\u00f3n." }, { "TextDeclUnterminated", "La declaraci\u00f3n de texto debe terminar con \"?>\"." }, { "EncodingDeclRequired", "Se precisa la declaraci\u00f3n de codificaci\u00f3n en la declaraci\u00f3n de texto." }, { "EncodingDeclInvalid", "Nombre de codificaci\u00f3n no v\u00e1lido: \"{0}\"." }, { "EntityNotDeclared", "Se ha hecho referencia a la entidad general \"{0}\" pero no est\u00e1 declarada." }, { "ColonInName", "Los espacios de nombres rechazan ':' excepto en los tipos de elemento o nombres de atributo." }, { "TwoColonsInQName", "Los espacios de nombres permiten un solo ':' en los tipos de elemento o nombres de atributo." }, { "PrefixDeclared", "No se ha declarado el prefijo de espacio de nombres \"{0}\"." }, { "PrefixLegal", "El prefijo de espacio de nombres \"xml\" no est\u00e1 enlazado con un nombre de espacio de nombres legal." }, { "NamespaceNameEmpty", "El nombre del espacio de nombres declarado para el prefijo \"{0}\" no puede estar vac\u00edo." }, { "NamespaceReserved", "El prefijo de espacio de nombres \"{0}\" est\u00e1 enlazado con el nombre reservado de espacio de nombres \"{1}\"." }, { "NamespacePrefixReserved", "No debe declararse el prefijo de espacio de nombres \"xmlns\"." } };
        MESSAGE_KEYS = new String[] { "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved", "RootElementRequired", "InvalidCharInCDSect", "InvalidCharInContent", "InvalidCharInMisc", "InvalidCharInProlog", "CDEndInContent", "CDSectUnterminated", "EqRequiredInXMLDecl", "QuoteRequiredInXMLDecl", "XMLDeclUnterminated", "VersionInfoRequired", "MarkupNotRecognizedInProlog", "MarkupNotRecognizedInMisc", "SDDeclInvalid", "ETagRequired", "ElementUnterminated", "EqRequiredInAttribute", "AttributeNotUnique", "ETagUnterminated", "MarkupNotRecognizedInContent", "ElementEntityMismatch", "InvalidCharInAttValue", "InvalidCharInComment", "InvalidCharInPI", "QuoteRequiredInAttValue", "LessthanInAttValue", "AttributeValueUnterminated", "InvalidCommentStart", "DashDashInComment", "CommentUnterminated", "PITargetRequired", "SpaceRequiredInPI", "PIUnterminated", "ReservedPITarget", "VersionNotSupported", "DigitRequiredInCharRef", "HexdigitRequiredInCharRef", "SemicolonRequiredInCharRef", "InvalidCharRef", "NameRequiredInReference", "SemicolonRequiredInReference", "EqRequiredInTextDecl", "QuoteRequiredInTextDecl", "SpaceRequiredInTextDecl", "TextDeclUnterminated", "EncodingDeclRequired", "EncodingDeclInvalid", "EntityNotDeclared", "ColonInName", "TwoColonsInQName", "PrefixDeclared", "PrefixLegal", "NamespaceNameEmpty", "NamespaceReserved", "NamespacePrefixReserved" };
    }
}