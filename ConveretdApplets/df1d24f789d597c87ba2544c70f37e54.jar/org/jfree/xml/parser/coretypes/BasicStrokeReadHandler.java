// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser.coretypes;

import java.util.StringTokenizer;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import java.awt.BasicStroke;
import org.jfree.xml.parser.AbstractXmlReadHandler;

public class BasicStrokeReadHandler extends AbstractXmlReadHandler
{
    private BasicStroke stroke;
    
    protected void startParsing(final Attributes attributes) throws SAXException {
        final int int1 = Integer.parseInt(attributes.getValue("endCap"));
        final int int2 = Integer.parseInt(attributes.getValue("lineJoin"));
        final float float1 = Float.parseFloat(attributes.getValue("lineWidth"));
        final float float2 = Float.parseFloat(attributes.getValue("miterLimit"));
        final String value = attributes.getValue("dashArray");
        if (value != null) {
            this.stroke = new BasicStroke(float1, int1, int2, float2, this.parseDashArray(value), Float.parseFloat(attributes.getValue("dashPhase")));
        }
        else {
            this.stroke = new BasicStroke(float1, int1, int2, float2);
        }
    }
    
    private float[] parseDashArray(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        final float[] array = new float[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Float.parseFloat(stringTokenizer.nextToken());
        }
        return array;
    }
    
    public Object getObject() {
        return this.stroke;
    }
}
