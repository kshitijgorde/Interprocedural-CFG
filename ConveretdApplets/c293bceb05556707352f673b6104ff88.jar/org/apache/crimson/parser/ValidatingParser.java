// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.parser;

import java.util.StringTokenizer;
import org.apache.crimson.util.XmlNames;
import org.xml.sax.SAXException;
import java.util.Enumeration;
import org.xml.sax.ErrorHandler;

public class ValidatingParser extends Parser2
{
    private SimpleHashtable ids;
    private final EmptyValidator EMPTY;
    
    public ValidatingParser() {
        this.ids = new SimpleHashtable();
        this.EMPTY = new EmptyValidator();
        this.setIsValidating(true);
    }
    
    public ValidatingParser(final boolean rejectValidityErrors) {
        this();
        if (rejectValidityErrors) {
            this.setErrorHandler((ErrorHandler)new ValidatingParser.ValidatingParser$1(this));
        }
    }
    
    void afterRoot() throws SAXException {
        final Enumeration e = this.ids.keys();
        while (e.hasMoreElements()) {
            final String id = e.nextElement();
            final Boolean value = (Boolean)this.ids.get(id);
            if (Boolean.FALSE == value) {
                this.error("V-024", new Object[] { id });
            }
        }
    }
    
    void afterDocument() {
        this.ids.clear();
    }
    
    void validateAttributeSyntax(final AttributeDecl attr, String value) throws SAXException {
        if ("ID" == attr.type) {
            if (!XmlNames.isName(value)) {
                this.error("V-025", new Object[] { value });
            }
            final Boolean b = (Boolean)this.ids.getNonInterned(value);
            if (b == null || b.equals(Boolean.FALSE)) {
                this.ids.put(value.intern(), Boolean.TRUE);
            }
            else {
                this.error("V-026", new Object[] { value });
            }
        }
        else if ("IDREF" == attr.type) {
            if (!XmlNames.isName(value)) {
                this.error("V-027", new Object[] { value });
            }
            final Boolean b = (Boolean)this.ids.getNonInterned(value);
            if (b == null) {
                this.ids.put(value.intern(), Boolean.FALSE);
            }
        }
        else if ("IDREFS" == attr.type) {
            final StringTokenizer tokenizer = new StringTokenizer(value);
            boolean sawValue = false;
            while (tokenizer.hasMoreTokens()) {
                value = tokenizer.nextToken();
                if (!XmlNames.isName(value)) {
                    this.error("V-027", new Object[] { value });
                }
                final Boolean b2 = (Boolean)this.ids.getNonInterned(value);
                if (b2 == null) {
                    this.ids.put(value.intern(), Boolean.FALSE);
                }
                sawValue = true;
            }
            if (!sawValue) {
                this.error("V-039", null);
            }
        }
        else if ("NMTOKEN" == attr.type) {
            if (!XmlNames.isNmtoken(value)) {
                this.error("V-028", new Object[] { value });
            }
        }
        else if ("NMTOKENS" == attr.type) {
            final StringTokenizer tokenizer = new StringTokenizer(value);
            boolean sawValue2 = false;
            while (tokenizer.hasMoreTokens()) {
                value = tokenizer.nextToken();
                if (!XmlNames.isNmtoken(value)) {
                    this.error("V-028", new Object[] { value });
                }
                sawValue2 = true;
            }
            if (!sawValue2) {
                this.error("V-032", null);
            }
        }
        else if ("ENUMERATION" == attr.type) {
            for (int i = 0; i < attr.values.length; ++i) {
                if (value.equals(attr.values[i])) {
                    return;
                }
            }
            this.error("V-029", new Object[] { value });
        }
        else if ("NOTATION" == attr.type) {
            for (int i = 0; i < attr.values.length; ++i) {
                if (value.equals(attr.values[i])) {
                    return;
                }
            }
            this.error("V-030", new Object[] { value });
        }
        else if ("ENTITY" == attr.type) {
            if (!this.isUnparsedEntity(value)) {
                this.error("V-031", new Object[] { value });
            }
        }
        else if ("ENTITIES" == attr.type) {
            final StringTokenizer tokenizer = new StringTokenizer(value);
            boolean sawValue2 = false;
            while (tokenizer.hasMoreTokens()) {
                value = tokenizer.nextToken();
                if (!this.isUnparsedEntity(value)) {
                    this.error("V-031", new Object[] { value });
                }
                sawValue2 = true;
            }
            if (!sawValue2) {
                this.error("V-040", null);
            }
        }
        else if ("CDATA" != attr.type) {
            throw new InternalError(attr.type);
        }
    }
    
    ContentModel newContentModel(final String tag) {
        return new ContentModel(tag);
    }
    
    ContentModel newContentModel(final char type, final ContentModel next) {
        return new ContentModel(type, next);
    }
    
    ElementValidator newValidator(final ElementDecl element) {
        if (element.validator != null) {
            return element.validator;
        }
        if (element.model != null) {
            return new ChildrenValidator(element);
        }
        if (element.contentType == null || "ANY" == element.contentType) {
            element.validator = ElementValidator.ANY;
        }
        else if ("EMPTY" == element.contentType) {
            element.validator = this.EMPTY;
        }
        else {
            element.validator = new MixedValidator(element);
        }
        return element.validator;
    }
    
    private boolean isUnparsedEntity(final String name) {
        final Object e = super.entities.getNonInterned(name);
        return e != null && e instanceof ExternalEntity && ((ExternalEntity)e).notation != null;
    }
    
    class EmptyValidator extends ElementValidator
    {
        public void consume(final String token) throws SAXException {
            ValidatingParser.this.error("V-033", null);
        }
        
        public void text() throws SAXException {
            ValidatingParser.this.error("V-033", null);
        }
    }
    
    class MixedValidator extends ElementValidator
    {
        private ElementDecl element;
        
        MixedValidator(final ElementDecl element) {
            this.element = element;
        }
        
        public void consume(final String type) throws SAXException {
            final String model = this.element.contentType;
            int index = 8;
            while ((index = model.indexOf(type, index + 1)) >= 9) {
                if (model.charAt(index - 1) != '|') {
                    continue;
                }
                final char c = model.charAt(index + type.length());
                if (c == '|' || c == ')') {
                    return;
                }
            }
            ValidatingParser.this.error("V-034", new Object[] { this.element.name, type, model });
        }
    }
    
    class ChildrenValidator extends ElementValidator
    {
        private ContentModelState state;
        private String name;
        
        ChildrenValidator(final ElementDecl element) {
            this.state = new ContentModelState(element.model);
            this.name = element.name;
        }
        
        public void consume(final String token) throws SAXException {
            if (this.state == null) {
                ValidatingParser.this.error("V-035", new Object[] { this.name, token });
            }
            else {
                try {
                    this.state = this.state.advance(token);
                }
                catch (EndOfInputException e) {
                    ValidatingParser.this.error("V-036", new Object[] { this.name, token });
                }
            }
        }
        
        public void text() throws SAXException {
            ValidatingParser.this.error("V-037", new Object[] { this.name });
        }
        
        public void done() throws SAXException {
            if (this.state != null && !this.state.terminate()) {
                ValidatingParser.this.error("V-038", new Object[] { this.name });
            }
        }
    }
}
