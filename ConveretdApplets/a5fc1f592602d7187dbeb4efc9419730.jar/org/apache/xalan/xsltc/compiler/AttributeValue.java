// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

abstract class AttributeValue extends Expression
{
    public static final AttributeValue create(final SyntaxTreeNode parent, final String text, final Parser parser) {
        AttributeValue result;
        if (text.indexOf(123) != -1) {
            result = new AttributeValueTemplate(text, parser, parent);
        }
        else if (text.indexOf(125) != -1) {
            result = new AttributeValueTemplate(text, parser, parent);
        }
        else {
            result = new SimpleAttributeValue(text);
            result.setParser(parser);
            result.setParent(parent);
        }
        return result;
    }
}
