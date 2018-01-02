// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

import java.util.NoSuchElementException;
import java.util.Enumeration;

public final class Constants
{
    public static final String SAX_FEATURE_PREFIX = "http://xml.org/sax/features/";
    public static final String NAMESPACES_FEATURE = "namespaces";
    public static final String NAMESPACE_PREFIXES_FEATURE = "namespace-prefixes";
    public static final String STRING_INTERNING_FEATURE = "string-interning";
    public static final String VALIDATION_FEATURE = "validation";
    public static final String EXTERNAL_GENERAL_ENTITIES_FEATURE = "external-general-entities";
    public static final String EXTERNAL_PARAMETER_ENTITIES_FEATURE = "external-parameter-entities";
    public static final String SAX_PROPERTY_PREFIX = "http://xml.org/sax/properties/";
    public static final String DECLARATION_HANDLER_PROPERTY = "declaration-handler";
    public static final String LEXICAL_HANDLER_PROPERTY = "lexical-handler";
    public static final String DOM_NODE_PROPERTY = "dom-node";
    public static final String XML_STRING_PROPERTY = "xml-string";
    public static final String JAXP_PROPERTY_PREFIX = "http://java.sun.com/xml/jaxp/properties/";
    public static final String SCHEMA_SOURCE = "schemaSource";
    public static final String SCHEMA_LANGUAGE = "schemaLanguage";
    public static final String INCLUDE_COMMENTS_FEATURE = "include-comments";
    public static final String CREATE_CDATA_NODES_FEATURE = "create-cdata-nodes";
    public static final String LOAD_AS_INFOSET = "load-as-infoset";
    public static final String DOM_CANONICAL_FORM = "canonical-form";
    public static final String DOM_CDATA_SECTIONS = "cdata-sections";
    public static final String DOM_COMMENTS = "comments";
    public static final String DOM_CHARSET_OVERRIDES_XML_ENCODING = "charset-overrides-xml-encoding";
    public static final String DOM_DATATYPE_NORMALIZATION = "datatype-normalization";
    public static final String DOM_ENTITIES = "entities";
    public static final String DOM_INFOSET = "infoset";
    public static final String DOM_NAMESPACES = "namespaces";
    public static final String DOM_NAMESPACE_DECLARATIONS = "namespace-declarations";
    public static final String DOM_SUPPORTED_MEDIATYPES_ONLY = "supported-mediatypes-only";
    public static final String DOM_VALIDATE_IF_SCHEMA = "validate-if-schema";
    public static final String DOM_VALIDATE = "validate";
    public static final String DOM_WHITESPACE_IN_ELEMENT_CONTENT = "whitespace-in-element-content";
    public static final String DOM_DISCARD_DEFAULT_CONTENT = "discard-default-content";
    public static final String DOM_NORMALIZE_CHARACTERS = "normalize-characters";
    public static final String DOM_SPLIT_CDATA = "split-cdata-sections";
    public static final String DOM_FORMAT_PRETTY_PRINT = "format-pretty-print";
    public static final String XERCES_FEATURE_PREFIX = "http://apache.org/xml/features/";
    public static final String SCHEMA_VALIDATION_FEATURE = "validation/schema";
    public static final String SCHEMA_NORMALIZED_VALUE = "validation/schema/normalized-value";
    public static final String SCHEMA_ELEMENT_DEFAULT = "validation/schema/element-default";
    public static final String SCHEMA_FULL_CHECKING = "validation/schema-full-checking";
    public static final String SCHEMA_AUGMENT_PSVI = "validation/schema/augment-psvi";
    public static final String DYNAMIC_VALIDATION_FEATURE = "validation/dynamic";
    public static final String WARN_ON_DUPLICATE_ATTDEF_FEATURE = "validation/warn-on-duplicate-attdef";
    public static final String WARN_ON_UNDECLARED_ELEMDEF_FEATURE = "validation/warn-on-undeclared-elemdef";
    public static final String WARN_ON_DUPLICATE_ENTITYDEF_FEATURE = "warn-on-duplicate-entitydef";
    public static final String ALLOW_JAVA_ENCODINGS_FEATURE = "allow-java-encodings";
    public static final String DISALLOW_DOCTYPE_DECL_FEATURE = "disallow-doctype-decl";
    public static final String CONTINUE_AFTER_FATAL_ERROR_FEATURE = "continue-after-fatal-error";
    public static final String LOAD_DTD_GRAMMAR_FEATURE = "nonvalidating/load-dtd-grammar";
    public static final String LOAD_EXTERNAL_DTD_FEATURE = "nonvalidating/load-external-dtd";
    public static final String DEFER_NODE_EXPANSION_FEATURE = "dom/defer-node-expansion";
    public static final String CREATE_ENTITY_REF_NODES_FEATURE = "dom/create-entity-ref-nodes";
    public static final String INCLUDE_IGNORABLE_WHITESPACE = "dom/include-ignorable-whitespace";
    public static final String DEFAULT_ATTRIBUTE_VALUES_FEATURE = "validation/default-attribute-values";
    public static final String VALIDATE_CONTENT_MODELS_FEATURE = "validation/validate-content-models";
    public static final String VALIDATE_DATATYPES_FEATURE = "validation/validate-datatypes";
    public static final String NOTIFY_CHAR_REFS_FEATURE = "scanner/notify-char-refs";
    public static final String NOTIFY_BUILTIN_REFS_FEATURE = "scanner/notify-builtin-refs";
    public static final String XERCES_PROPERTY_PREFIX = "http://apache.org/xml/properties/";
    public static final String CURRENT_ELEMENT_NODE_PROPERTY = "dom/current-element-node";
    public static final String DOCUMENT_CLASS_NAME_PROPERTY = "dom/document-class-name";
    public static final String SYMBOL_TABLE_PROPERTY = "internal/symbol-table";
    public static final String ERROR_REPORTER_PROPERTY = "internal/error-reporter";
    public static final String ERROR_HANDLER_PROPERTY = "internal/error-handler";
    public static final String ENTITY_MANAGER_PROPERTY = "internal/entity-manager";
    public static final String BUFFER_SIZE_PROPERTY = "input-buffer-size";
    public static final String ENTITY_EXPANSION_LIMIT = "entity-expansion-limit";
    public static final String ENTITY_RESOLVER_PROPERTY = "internal/entity-resolver";
    public static final String XMLGRAMMAR_POOL_PROPERTY = "internal/grammar-pool";
    public static final String DATATYPE_VALIDATOR_FACTORY_PROPERTY = "internal/datatype-validator-factory";
    public static final String DOCUMENT_SCANNER_PROPERTY = "internal/document-scanner";
    public static final String DTD_SCANNER_PROPERTY = "internal/dtd-scanner";
    public static final String DTD_PROCESSOR_PROPERTY = "internal/dtd-processor";
    public static final String VALIDATOR_PROPERTY = "internal/validator";
    public static final String DTD_VALIDATOR_PROPERTY = "internal/validator/dtd";
    public static final String SCHEMA_VALIDATOR_PROPERTY = "internal/validator/schema";
    public static final String SCHEMA_LOCATION = "schema/external-schemaLocation";
    public static final String SCHEMA_NONS_LOCATION = "schema/external-noNamespaceSchemaLocation";
    public static final String NAMESPACE_BINDER_PROPERTY = "internal/namespace-binder";
    public static final String VALIDATION_MANAGER_PROPERTY = "internal/validation-manager";
    public static final String ELEMENT_PSVI = "ELEMENT_PSVI";
    public static final String ATTRIBUTE_PSVI = "ATTRIBUTE_PSVI";
    private static final String[] fgSAXFeatures;
    private static final String[] fgSAXProperties;
    private static final String[] fgXercesFeatures;
    private static final String[] fgXercesProperties;
    private static final Enumeration fgEmptyEnumeration;
    
    public static Enumeration getSAXFeatures() {
        return (Constants.fgSAXFeatures.length > 0) ? new ArrayEnumeration(Constants.fgSAXFeatures) : Constants.fgEmptyEnumeration;
    }
    
    public static Enumeration getSAXProperties() {
        return (Constants.fgSAXProperties.length > 0) ? new ArrayEnumeration(Constants.fgSAXProperties) : Constants.fgEmptyEnumeration;
    }
    
    public static Enumeration getXercesFeatures() {
        return (Constants.fgXercesFeatures.length > 0) ? new ArrayEnumeration(Constants.fgXercesFeatures) : Constants.fgEmptyEnumeration;
    }
    
    public static Enumeration getXercesProperties() {
        return (Constants.fgXercesProperties.length > 0) ? new ArrayEnumeration(Constants.fgXercesProperties) : Constants.fgEmptyEnumeration;
    }
    
    public static void main(final String[] argv) {
        print("SAX features:", "http://xml.org/sax/features/", Constants.fgSAXFeatures);
        print("SAX properties:", "http://xml.org/sax/properties/", Constants.fgSAXProperties);
        print("Xerces features:", "http://apache.org/xml/features/", Constants.fgXercesFeatures);
        print("Xerces properties:", "http://apache.org/xml/properties/", Constants.fgXercesProperties);
    }
    
    private static void print(final String header, final String prefix, final Object[] array) {
        System.out.print(header);
        if (array.length > 0) {
            System.out.println();
            for (int i = 0; i < array.length; ++i) {
                System.out.print("  ");
                System.out.print(prefix);
                System.out.println(array[i]);
            }
        }
        else {
            System.out.println(" none.");
        }
    }
    
    static {
        fgSAXFeatures = new String[] { "namespaces", "namespace-prefixes", "string-interning", "validation", "external-general-entities", "external-parameter-entities" };
        fgSAXProperties = new String[] { "declaration-handler", "lexical-handler", "dom-node", "xml-string" };
        fgXercesFeatures = new String[] { "validation/schema", "validation/schema-full-checking", "validation/dynamic", "validation/warn-on-duplicate-attdef", "validation/warn-on-undeclared-elemdef", "allow-java-encodings", "continue-after-fatal-error", "nonvalidating/load-dtd-grammar", "nonvalidating/load-external-dtd", "dom/create-entity-ref-nodes", "dom/include-ignorable-whitespace", "validation/default-attribute-values", "validation/validate-content-models", "validation/validate-datatypes", "scanner/notify-char-refs" };
        fgXercesProperties = new String[] { "dom/current-element-node", "dom/document-class-name", "internal/symbol-table", "internal/error-handler", "internal/error-reporter", "internal/entity-manager", "internal/entity-resolver", "internal/grammar-pool", "internal/datatype-validator-factory", "internal/document-scanner", "internal/dtd-scanner", "internal/validator", "schema/external-schemaLocation", "schema/external-noNamespaceSchemaLocation", "internal/validation-manager" };
        fgEmptyEnumeration = new ArrayEnumeration(new Object[0]);
    }
    
    static class ArrayEnumeration implements Enumeration
    {
        private Object[] array;
        private int index;
        
        public ArrayEnumeration(final Object[] array) {
            this.array = array;
        }
        
        public boolean hasMoreElements() {
            return this.index < this.array.length;
        }
        
        public Object nextElement() {
            if (this.index < this.array.length) {
                return this.array[this.index++];
            }
            throw new NoSuchElementException();
        }
    }
}
