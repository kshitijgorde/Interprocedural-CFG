// 
// Decompiled by Procyon v0.5.30
// 

package processing.core;

import java.awt.image.WritableRaster;
import java.awt.image.Raster;
import java.awt.PaintContext;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.image.ColorModel;
import java.awt.geom.Point2D;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.awt.Paint;
import processing.xml.XMLElement;

public class PShapeSVG extends PShape
{
    XMLElement element;
    float opacity;
    float strokeOpacity;
    float fillOpacity;
    Gradient strokeGradient;
    Paint strokeGradientPaint;
    String strokeName;
    Gradient fillGradient;
    Paint fillGradientPaint;
    String fillName;
    
    public PShapeSVG(final PApplet pApplet, final String s) {
        this(new XMLElement(pApplet, s));
    }
    
    public PShapeSVG(final XMLElement xmlElement) {
        this(null, xmlElement);
        if (!xmlElement.getName().equals("svg")) {
            throw new RuntimeException("root is not <svg>, it's <" + xmlElement.getName() + ">");
        }
        final String stringAttribute = xmlElement.getStringAttribute("viewBox");
        if (stringAttribute != null) {
            final int[] int1 = PApplet.parseInt(PApplet.splitTokens(stringAttribute));
            this.width = int1[2];
            this.height = int1[3];
        }
        final String stringAttribute2 = xmlElement.getStringAttribute("width");
        final String stringAttribute3 = xmlElement.getStringAttribute("height");
        if (stringAttribute2 != null) {
            this.width = parseUnitSize(stringAttribute2);
            this.height = parseUnitSize(stringAttribute3);
        }
        else if (this.width == 0.0f || this.height == 0.0f) {
            PGraphics.showWarning("The width and/or height is not readable in the <svg> tag of this file.");
            this.width = 1.0f;
            this.height = 1.0f;
        }
        this.parseChildren(xmlElement);
    }
    
    public PShapeSVG(final PShapeSVG pShapeSVG, final XMLElement element) {
        if (pShapeSVG == null) {
            this.stroke = false;
            this.strokeColor = -16777216;
            this.strokeWeight = 1.0f;
            this.strokeCap = 1;
            this.strokeJoin = 8;
            this.strokeGradient = null;
            this.strokeGradientPaint = null;
            this.strokeName = null;
            this.fill = true;
            this.fillColor = -16777216;
            this.fillGradient = null;
            this.fillGradientPaint = null;
            this.fillName = null;
            this.strokeOpacity = 1.0f;
            this.fillOpacity = 1.0f;
            this.opacity = 1.0f;
        }
        else {
            this.stroke = pShapeSVG.stroke;
            this.strokeColor = pShapeSVG.strokeColor;
            this.strokeWeight = pShapeSVG.strokeWeight;
            this.strokeCap = pShapeSVG.strokeCap;
            this.strokeJoin = pShapeSVG.strokeJoin;
            this.strokeGradient = pShapeSVG.strokeGradient;
            this.strokeGradientPaint = pShapeSVG.strokeGradientPaint;
            this.strokeName = pShapeSVG.strokeName;
            this.fill = pShapeSVG.fill;
            this.fillColor = pShapeSVG.fillColor;
            this.fillGradient = pShapeSVG.fillGradient;
            this.fillGradientPaint = pShapeSVG.fillGradientPaint;
            this.fillName = pShapeSVG.fillName;
            this.opacity = pShapeSVG.opacity;
        }
        this.element = element;
        this.name = element.getStringAttribute("id");
        this.visible = !element.getStringAttribute("display", "inline").equals("none");
        final String stringAttribute = element.getStringAttribute("transform");
        if (stringAttribute != null) {
            this.matrix = parseMatrix(stringAttribute);
        }
        this.parseColors(element);
        this.parseChildren(element);
    }
    
    protected void parseChildren(final XMLElement xmlElement) {
        final XMLElement[] children = xmlElement.getChildren();
        this.children = new PShape[children.length];
        this.childCount = 0;
        final XMLElement[] array = children;
        for (int length = array.length, i = 0; i < length; ++i) {
            final PShape child = this.parseChild(array[i]);
            if (child != null) {
                this.addChild(child);
            }
        }
    }
    
    protected PShape parseChild(final XMLElement xmlElement) {
        final String name = xmlElement.getName();
        PShapeSVG pShapeSVG = null;
        if (name.equals("g")) {
            pShapeSVG = new PShapeSVG(this, xmlElement);
        }
        else if (name.equals("defs")) {
            pShapeSVG = new PShapeSVG(this, xmlElement);
        }
        else if (name.equals("line")) {
            pShapeSVG = new PShapeSVG(this, xmlElement);
            pShapeSVG.parseLine();
        }
        else if (name.equals("circle")) {
            pShapeSVG = new PShapeSVG(this, xmlElement);
            pShapeSVG.parseEllipse(true);
        }
        else if (name.equals("ellipse")) {
            pShapeSVG = new PShapeSVG(this, xmlElement);
            pShapeSVG.parseEllipse(false);
        }
        else if (name.equals("rect")) {
            pShapeSVG = new PShapeSVG(this, xmlElement);
            pShapeSVG.parseRect();
        }
        else if (name.equals("polygon")) {
            pShapeSVG = new PShapeSVG(this, xmlElement);
            pShapeSVG.parsePoly(true);
        }
        else if (name.equals("polyline")) {
            pShapeSVG = new PShapeSVG(this, xmlElement);
            pShapeSVG.parsePoly(false);
        }
        else if (name.equals("path")) {
            pShapeSVG = new PShapeSVG(this, xmlElement);
            pShapeSVG.parsePath();
        }
        else {
            if (name.equals("radialGradient")) {
                return new RadialGradient(this, xmlElement);
            }
            if (name.equals("linearGradient")) {
                return new LinearGradient(this, xmlElement);
            }
            if (name.equals("text")) {
                PGraphics.showWarning("Text in SVG files is not currently supported, convert text to outlines instead.");
            }
            else if (name.equals("filter")) {
                PGraphics.showWarning("Filters are not supported.");
            }
            else if (name.equals("mask")) {
                PGraphics.showWarning("Masks are not supported.");
            }
            else {
                PGraphics.showWarning("Ignoring  <" + name + "> tag.");
            }
        }
        return pShapeSVG;
    }
    
    protected void parseLine() {
        this.kind = 4;
        this.family = 1;
        this.params = new float[] { this.element.getFloatAttribute("x1"), this.element.getFloatAttribute("y1"), this.element.getFloatAttribute("x2"), this.element.getFloatAttribute("y2") };
    }
    
    protected void parseEllipse(final boolean b) {
        this.kind = 31;
        this.family = 1;
        (this.params = new float[4])[0] = this.element.getFloatAttribute("cx");
        this.params[1] = this.element.getFloatAttribute("cy");
        float n;
        float floatAttribute;
        if (b) {
            floatAttribute = (n = this.element.getFloatAttribute("r"));
        }
        else {
            n = this.element.getFloatAttribute("rx");
            floatAttribute = this.element.getFloatAttribute("ry");
        }
        final float[] params = this.params;
        final int n2 = 0;
        params[n2] -= n;
        final float[] params2 = this.params;
        final int n3 = 1;
        params2[n3] -= floatAttribute;
        this.params[2] = n * 2.0f;
        this.params[3] = floatAttribute * 2.0f;
    }
    
    protected void parseRect() {
        this.kind = 30;
        this.family = 1;
        this.params = new float[] { this.element.getFloatAttribute("x"), this.element.getFloatAttribute("y"), this.element.getFloatAttribute("width"), this.element.getFloatAttribute("height") };
    }
    
    protected void parsePoly(final boolean close) {
        this.family = 2;
        this.close = close;
        final String stringAttribute = this.element.getStringAttribute("points");
        if (stringAttribute != null) {
            final String[] splitTokens = PApplet.splitTokens(stringAttribute);
            this.vertexCount = splitTokens.length;
            this.vertices = new float[this.vertexCount][2];
            for (int i = 0; i < this.vertexCount; ++i) {
                final String[] split = PApplet.split(splitTokens[i], ',');
                this.vertices[i][0] = Float.valueOf(split[0]);
                this.vertices[i][1] = Float.valueOf(split[1]);
            }
        }
    }
    
    protected void parsePath() {
        this.family = 2;
        this.kind = 0;
        final String stringAttribute = this.element.getStringAttribute("d");
        if (stringAttribute == null) {
            return;
        }
        final char[] charArray = stringAttribute.toCharArray();
        final StringBuffer sb = new StringBuffer();
        boolean b = false;
        for (int i = 0; i < charArray.length; ++i) {
            final char c = charArray[i];
            boolean b2 = false;
            if (c == 'M' || c == 'm' || c == 'L' || c == 'l' || c == 'H' || c == 'h' || c == 'V' || c == 'v' || c == 'C' || c == 'c' || c == 'S' || c == 's' || c == 'Q' || c == 'q' || c == 'T' || c == 't' || c == 'Z' || c == 'z' || c == ',') {
                b2 = true;
                if (i != 0) {
                    sb.append("|");
                }
            }
            if (c == 'Z' || c == 'z') {
                b2 = false;
            }
            if (c == '-' && !b) {
                sb.append("|");
            }
            if (c != ',') {
                sb.append(c);
            }
            if (b2 && c != ',' && c != '-') {
                sb.append("|");
            }
            b = b2;
        }
        final String[] splitTokens = PApplet.splitTokens(sb.toString(), "| \t\n\r\f ");
        this.vertices = new float[splitTokens.length][2];
        this.vertexCodes = new int[splitTokens.length];
        float n = 0.0f;
        float n2 = 0.0f;
        int j = 0;
        while (j < splitTokens.length) {
            switch (splitTokens[j].charAt(0)) {
                case 'M': {
                    n = PApplet.parseFloat(splitTokens[j + 1]);
                    n2 = PApplet.parseFloat(splitTokens[j + 2]);
                    this.parsePathMoveto(n, n2);
                    j += 3;
                    continue;
                }
                case 'm': {
                    n += PApplet.parseFloat(splitTokens[j + 1]);
                    n2 += PApplet.parseFloat(splitTokens[j + 2]);
                    this.parsePathMoveto(n, n2);
                    j += 3;
                    continue;
                }
                case 'L': {
                    n = PApplet.parseFloat(splitTokens[j + 1]);
                    n2 = PApplet.parseFloat(splitTokens[j + 2]);
                    this.parsePathLineto(n, n2);
                    j += 3;
                    continue;
                }
                case 'l': {
                    n += PApplet.parseFloat(splitTokens[j + 1]);
                    n2 += PApplet.parseFloat(splitTokens[j + 2]);
                    this.parsePathLineto(n, n2);
                    j += 3;
                    continue;
                }
                case 'H': {
                    n = PApplet.parseFloat(splitTokens[j + 1]);
                    this.parsePathLineto(n, n2);
                    j += 2;
                    continue;
                }
                case 'h': {
                    n += PApplet.parseFloat(splitTokens[j + 1]);
                    this.parsePathLineto(n, n2);
                    j += 2;
                    continue;
                }
                case 'V': {
                    n2 = PApplet.parseFloat(splitTokens[j + 1]);
                    this.parsePathLineto(n, n2);
                    j += 2;
                    continue;
                }
                case 'v': {
                    n2 += PApplet.parseFloat(splitTokens[j + 1]);
                    this.parsePathLineto(n, n2);
                    j += 2;
                    continue;
                }
                case 'C': {
                    final float float1 = PApplet.parseFloat(splitTokens[j + 1]);
                    final float float2 = PApplet.parseFloat(splitTokens[j + 2]);
                    final float float3 = PApplet.parseFloat(splitTokens[j + 3]);
                    final float float4 = PApplet.parseFloat(splitTokens[j + 4]);
                    final float float5 = PApplet.parseFloat(splitTokens[j + 5]);
                    final float float6 = PApplet.parseFloat(splitTokens[j + 6]);
                    this.parsePathCurveto(float1, float2, float3, float4, float5, float6);
                    n = float5;
                    n2 = float6;
                    j += 7;
                    continue;
                }
                case 'c': {
                    final float n3 = n + PApplet.parseFloat(splitTokens[j + 1]);
                    final float n4 = n2 + PApplet.parseFloat(splitTokens[j + 2]);
                    final float n5 = n + PApplet.parseFloat(splitTokens[j + 3]);
                    final float n6 = n2 + PApplet.parseFloat(splitTokens[j + 4]);
                    final float n7 = n + PApplet.parseFloat(splitTokens[j + 5]);
                    final float n8 = n2 + PApplet.parseFloat(splitTokens[j + 6]);
                    this.parsePathCurveto(n3, n4, n5, n6, n7, n8);
                    n = n7;
                    n2 = n8;
                    j += 7;
                    continue;
                }
                case 'S': {
                    final float n9 = this.vertices[this.vertexCount - 2][0];
                    final float n10 = this.vertices[this.vertexCount - 2][1];
                    final float n11 = this.vertices[this.vertexCount - 1][0];
                    final float n12 = this.vertices[this.vertexCount - 1][1];
                    final float n13 = n11 + (n11 - n9);
                    final float n14 = n12 + (n12 - n10);
                    final float float7 = PApplet.parseFloat(splitTokens[j + 1]);
                    final float float8 = PApplet.parseFloat(splitTokens[j + 2]);
                    final float float9 = PApplet.parseFloat(splitTokens[j + 3]);
                    final float float10 = PApplet.parseFloat(splitTokens[j + 4]);
                    this.parsePathCurveto(n13, n14, float7, float8, float9, float10);
                    n = float9;
                    n2 = float10;
                    j += 5;
                    continue;
                }
                case 's': {
                    final float n15 = this.vertices[this.vertexCount - 2][0];
                    final float n16 = this.vertices[this.vertexCount - 2][1];
                    final float n17 = this.vertices[this.vertexCount - 1][0];
                    final float n18 = this.vertices[this.vertexCount - 1][1];
                    final float n19 = n17 + (n17 - n15);
                    final float n20 = n18 + (n18 - n16);
                    final float n21 = n + PApplet.parseFloat(splitTokens[j + 1]);
                    final float n22 = n2 + PApplet.parseFloat(splitTokens[j + 2]);
                    final float n23 = n + PApplet.parseFloat(splitTokens[j + 3]);
                    final float n24 = n2 + PApplet.parseFloat(splitTokens[j + 4]);
                    this.parsePathCurveto(n19, n20, n21, n22, n23, n24);
                    n = n23;
                    n2 = n24;
                    j += 5;
                    continue;
                }
                case 'Q': {
                    final float float11 = PApplet.parseFloat(splitTokens[j + 1]);
                    final float float12 = PApplet.parseFloat(splitTokens[j + 2]);
                    final float float13 = PApplet.parseFloat(splitTokens[j + 3]);
                    final float float14 = PApplet.parseFloat(splitTokens[j + 4]);
                    this.parsePathQuadto(n, n2, float11, float12, float13, float14);
                    n = float13;
                    n2 = float14;
                    j += 5;
                    continue;
                }
                case 'q': {
                    final float n25 = n + PApplet.parseFloat(splitTokens[j + 1]);
                    final float n26 = n2 + PApplet.parseFloat(splitTokens[j + 2]);
                    final float n27 = n + PApplet.parseFloat(splitTokens[j + 3]);
                    final float n28 = n2 + PApplet.parseFloat(splitTokens[j + 4]);
                    this.parsePathQuadto(n, n2, n25, n26, n27, n28);
                    n = n27;
                    n2 = n28;
                    j += 5;
                    continue;
                }
                case 'T': {
                    final float n29 = this.vertices[this.vertexCount - 2][0];
                    final float n30 = this.vertices[this.vertexCount - 2][1];
                    final float n31 = this.vertices[this.vertexCount - 1][0];
                    final float n32 = this.vertices[this.vertexCount - 1][1];
                    final float n33 = n31 + (n31 - n29);
                    final float n34 = n32 + (n32 - n30);
                    final float float15 = PApplet.parseFloat(splitTokens[j + 1]);
                    final float float16 = PApplet.parseFloat(splitTokens[j + 2]);
                    this.parsePathQuadto(n, n2, n33, n34, float15, float16);
                    n = float15;
                    n2 = float16;
                    j += 3;
                    continue;
                }
                case 't': {
                    final float n35 = this.vertices[this.vertexCount - 2][0];
                    final float n36 = this.vertices[this.vertexCount - 2][1];
                    final float n37 = this.vertices[this.vertexCount - 1][0];
                    final float n38 = this.vertices[this.vertexCount - 1][1];
                    final float n39 = n37 + (n37 - n35);
                    final float n40 = n38 + (n38 - n36);
                    final float n41 = n + PApplet.parseFloat(splitTokens[j + 1]);
                    final float n42 = n2 + PApplet.parseFloat(splitTokens[j + 2]);
                    this.parsePathQuadto(n, n2, n39, n40, n41, n42);
                    n = n41;
                    n2 = n42;
                    j += 3;
                    continue;
                }
                case 'Z':
                case 'z': {
                    this.close = true;
                    ++j;
                    continue;
                }
                default: {
                    final String join = PApplet.join(PApplet.subset(splitTokens, 0, j), ",");
                    final String join2 = PApplet.join(PApplet.subset(splitTokens, j), ",");
                    System.err.println("parsed: " + join);
                    System.err.println("unparsed: " + join2);
                    if (splitTokens[j].equals("a") || splitTokens[j].equals("A")) {
                        throw new RuntimeException("Sorry, elliptical arc support for SVG files is not yet implemented (See bug #996 for details)");
                    }
                    throw new RuntimeException("shape command not handled: " + splitTokens[j]);
                }
            }
        }
    }
    
    private void parsePathVertex(final float n, final float n2) {
        if (this.vertexCount == this.vertices.length) {
            final float[][] vertices = new float[this.vertexCount << 1][2];
            System.arraycopy(this.vertices, 0, vertices, 0, this.vertexCount);
            this.vertices = vertices;
        }
        this.vertices[this.vertexCount][0] = n;
        this.vertices[this.vertexCount][1] = n2;
        ++this.vertexCount;
    }
    
    private void parsePathCode(final int n) {
        if (this.vertexCodeCount == this.vertexCodes.length) {
            this.vertexCodes = PApplet.expand(this.vertexCodes);
        }
        this.vertexCodes[this.vertexCodeCount++] = n;
    }
    
    private void parsePathMoveto(final float n, final float n2) {
        if (this.vertexCount > 0) {
            this.parsePathCode(3);
        }
        this.parsePathCode(0);
        this.parsePathVertex(n, n2);
    }
    
    private void parsePathLineto(final float n, final float n2) {
        this.parsePathCode(0);
        this.parsePathVertex(n, n2);
    }
    
    private void parsePathCurveto(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.parsePathCode(1);
        this.parsePathVertex(n, n2);
        this.parsePathVertex(n3, n4);
        this.parsePathVertex(n5, n6);
    }
    
    private void parsePathQuadto(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.parsePathCode(1);
        this.parsePathVertex(n + (n3 - n) * 2.0f / 3.0f, n2 + (n4 - n2) * 2.0f / 3.0f);
        this.parsePathVertex(n5 + (n3 - n5) * 2.0f / 3.0f, n6 + (n4 - n6) * 2.0f / 3.0f);
        this.parsePathVertex(n5, n6);
    }
    
    protected static PMatrix2D parseMatrix(final String s) {
        final String[] match = PApplet.match(s, "\\s*(\\w+)\\((.*)\\)");
        if (match == null) {
            System.err.println("Could not parse transform " + s);
            return null;
        }
        final float[] float1 = PApplet.parseFloat(PApplet.splitTokens(match[2], ", "));
        if (match[1].equals("matrix")) {
            return new PMatrix2D(float1[0], float1[2], float1[4], float1[1], float1[3], float1[5]);
        }
        if (match[1].equals("translate")) {
            return new PMatrix2D(1.0f, 0.0f, float1[0], 0.0f, 1.0f, (float1.length == 2) ? float1[1] : float1[0]);
        }
        if (match[1].equals("scale")) {
            return new PMatrix2D(float1[0], 0.0f, 0.0f, 0.0f, (float1.length == 2) ? float1[1] : float1[0], 0.0f);
        }
        if (match[1].equals("rotate")) {
            final float n = float1[0];
            if (float1.length == 1) {
                final float cos = PApplet.cos(n);
                final float sin = PApplet.sin(n);
                return new PMatrix2D(cos, -sin, 0.0f, sin, cos, 0.0f);
            }
            if (float1.length == 3) {
                final PMatrix2D pMatrix2D = new PMatrix2D(0.0f, 1.0f, float1[1], 1.0f, 0.0f, float1[2]);
                pMatrix2D.rotate(float1[0]);
                pMatrix2D.translate(-float1[1], -float1[2]);
                return pMatrix2D;
            }
        }
        else {
            if (match[1].equals("skewX")) {
                return new PMatrix2D(1.0f, 0.0f, 1.0f, PApplet.tan(float1[0]), 0.0f, 0.0f);
            }
            if (match[1].equals("skewY")) {
                return new PMatrix2D(1.0f, 0.0f, 1.0f, 0.0f, PApplet.tan(float1[0]), 0.0f);
            }
        }
        return null;
    }
    
    protected void parseColors(final XMLElement xmlElement) {
        if (xmlElement.hasAttribute("opacity")) {
            this.setOpacity(xmlElement.getStringAttribute("opacity"));
        }
        if (xmlElement.hasAttribute("stroke")) {
            this.setStroke(xmlElement.getStringAttribute("stroke"));
        }
        if (xmlElement.hasAttribute("stroke-width")) {
            this.setStrokeWeight(xmlElement.getStringAttribute("stroke-width"));
        }
        if (xmlElement.hasAttribute("stroke-linejoin")) {
            this.setStrokeJoin(xmlElement.getStringAttribute("stroke-linejoin"));
        }
        if (xmlElement.hasAttribute("stroke-linecap")) {
            this.setStrokeCap(xmlElement.getStringAttribute("stroke-linecap"));
        }
        if (xmlElement.hasAttribute("fill")) {
            this.setFill(xmlElement.getStringAttribute("fill"));
        }
        if (xmlElement.hasAttribute("style")) {
            final String[] splitTokens = PApplet.splitTokens(xmlElement.getStringAttribute("style"), ";");
            for (int i = 0; i < splitTokens.length; ++i) {
                final String[] splitTokens2 = PApplet.splitTokens(splitTokens[i], ":");
                splitTokens2[0] = PApplet.trim(splitTokens2[0]);
                if (splitTokens2[0].equals("fill")) {
                    this.setFill(splitTokens2[1]);
                }
                else if (splitTokens2[0].equals("fill-opacity")) {
                    this.setFillOpacity(splitTokens2[1]);
                }
                else if (splitTokens2[0].equals("stroke")) {
                    this.setStroke(splitTokens2[1]);
                }
                else if (splitTokens2[0].equals("stroke-width")) {
                    this.setStrokeWeight(splitTokens2[1]);
                }
                else if (splitTokens2[0].equals("stroke-linecap")) {
                    this.setStrokeCap(splitTokens2[1]);
                }
                else if (splitTokens2[0].equals("stroke-linejoin")) {
                    this.setStrokeJoin(splitTokens2[1]);
                }
                else if (splitTokens2[0].equals("stroke-opacity")) {
                    this.setStrokeOpacity(splitTokens2[1]);
                }
                else if (splitTokens2[0].equals("opacity")) {
                    this.setOpacity(splitTokens2[1]);
                }
            }
        }
    }
    
    void setOpacity(final String s) {
        this.opacity = PApplet.parseFloat(s);
        this.strokeColor = ((int)(this.opacity * 255.0f) << 24 | (this.strokeColor & 0xFFFFFF));
        this.fillColor = ((int)(this.opacity * 255.0f) << 24 | (this.fillColor & 0xFFFFFF));
    }
    
    void setStrokeWeight(final String s) {
        this.strokeWeight = PApplet.parseFloat(s);
    }
    
    void setStrokeOpacity(final String s) {
        this.strokeOpacity = PApplet.parseFloat(s);
        this.strokeColor = ((int)(this.strokeOpacity * 255.0f) << 24 | (this.strokeColor & 0xFFFFFF));
    }
    
    void setStroke(final String s) {
        final int n = this.strokeColor & 0xFF000000;
        if (s.equals("none")) {
            this.stroke = false;
        }
        else if (s.startsWith("#")) {
            this.stroke = true;
            this.strokeColor = (n | (Integer.parseInt(s.substring(1), 16) & 0xFFFFFF));
        }
        else if (s.startsWith("rgb")) {
            this.stroke = true;
            this.strokeColor = (n | parseRGB(s));
        }
        else if (s.startsWith("url(#")) {
            this.strokeName = s.substring(5, s.length() - 1);
            final PShape child = this.findChild(this.strokeName);
            if (child instanceof Gradient) {
                this.strokeGradient = (Gradient)child;
                this.strokeGradientPaint = this.calcGradientPaint(this.strokeGradient);
            }
            else {
                System.err.println("url " + this.strokeName + " refers to unexpected data");
            }
        }
    }
    
    void setStrokeJoin(final String s) {
        if (!s.equals("inherit")) {
            if (s.equals("miter")) {
                this.strokeJoin = 8;
            }
            else if (s.equals("round")) {
                this.strokeJoin = 2;
            }
            else if (s.equals("bevel")) {
                this.strokeJoin = 32;
            }
        }
    }
    
    void setStrokeCap(final String s) {
        if (!s.equals("inherit")) {
            if (s.equals("butt")) {
                this.strokeCap = 1;
            }
            else if (s.equals("round")) {
                this.strokeCap = 2;
            }
            else if (s.equals("square")) {
                this.strokeCap = 4;
            }
        }
    }
    
    void setFillOpacity(final String s) {
        this.fillOpacity = PApplet.parseFloat(s);
        this.fillColor = ((int)(this.fillOpacity * 255.0f) << 24 | (this.fillColor & 0xFFFFFF));
    }
    
    void setFill(final String s) {
        final int n = this.fillColor & 0xFF000000;
        if (s.equals("none")) {
            this.fill = false;
        }
        else if (s.startsWith("#")) {
            this.fill = true;
            this.fillColor = (n | (Integer.parseInt(s.substring(1), 16) & 0xFFFFFF));
        }
        else if (s.startsWith("rgb")) {
            this.fill = true;
            this.fillColor = (n | parseRGB(s));
        }
        else if (s.startsWith("url(#")) {
            this.fillName = s.substring(5, s.length() - 1);
            final PShape child = this.findChild(this.fillName);
            if (child instanceof Gradient) {
                this.fill = true;
                this.fillGradient = (Gradient)child;
                this.fillGradientPaint = this.calcGradientPaint(this.fillGradient);
            }
            else {
                System.err.println("url " + this.fillName + " refers to unexpected data");
            }
        }
    }
    
    protected static int parseRGB(final String s) {
        final int[] int1 = PApplet.parseInt(PApplet.splitTokens(s.substring(s.indexOf(40) + 1, s.indexOf(41)), ", "));
        return int1[0] << 16 | int1[1] << 8 | int1[2];
    }
    
    protected static HashMap<String, String> parseStyleAttributes(final String s) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        final String[] split = s.split(";");
        for (int i = 0; i < split.length; ++i) {
            final String[] split2 = split[i].split(":");
            hashMap.put(split2[0], split2[1]);
        }
        return hashMap;
    }
    
    protected static float parseUnitSize(final String s) {
        final int n = s.length() - 2;
        if (s.endsWith("pt")) {
            return PApplet.parseFloat(s.substring(0, n)) * 1.25f;
        }
        if (s.endsWith("pc")) {
            return PApplet.parseFloat(s.substring(0, n)) * 15.0f;
        }
        if (s.endsWith("mm")) {
            return PApplet.parseFloat(s.substring(0, n)) * 3.543307f;
        }
        if (s.endsWith("cm")) {
            return PApplet.parseFloat(s.substring(0, n)) * 35.43307f;
        }
        if (s.endsWith("in")) {
            return PApplet.parseFloat(s.substring(0, n)) * 90.0f;
        }
        if (s.endsWith("px")) {
            return PApplet.parseFloat(s.substring(0, n));
        }
        return PApplet.parseFloat(s);
    }
    
    protected Paint calcGradientPaint(final Gradient gradient) {
        if (gradient instanceof LinearGradient) {
            final LinearGradient linearGradient = (LinearGradient)gradient;
            return new LinearGradientPaint(linearGradient.x1, linearGradient.y1, linearGradient.x2, linearGradient.y2, linearGradient.offset, linearGradient.color, linearGradient.count, this.opacity);
        }
        if (gradient instanceof RadialGradient) {
            final RadialGradient radialGradient = (RadialGradient)gradient;
            return new RadialGradientPaint(radialGradient.cx, radialGradient.cy, radialGradient.r, radialGradient.offset, radialGradient.color, radialGradient.count, this.opacity);
        }
        return null;
    }
    
    protected void styles(final PGraphics pGraphics) {
        super.styles(pGraphics);
        if (pGraphics instanceof PGraphicsJava2D) {
            final PGraphicsJava2D pGraphicsJava2D = (PGraphicsJava2D)pGraphics;
            if (this.strokeGradient != null) {
                pGraphicsJava2D.strokeGradient = true;
                pGraphicsJava2D.strokeGradientObject = this.strokeGradientPaint;
            }
            if (this.fillGradient != null) {
                pGraphicsJava2D.fillGradient = true;
                pGraphicsJava2D.fillGradientObject = this.fillGradientPaint;
            }
        }
    }
    
    public PShape getChild(final String s) {
        PShape pShape = super.getChild(s);
        if (pShape == null) {
            pShape = super.getChild(s.replace(' ', '_'));
        }
        if (pShape != null) {
            pShape.width = this.width;
            pShape.height = this.height;
        }
        return pShape;
    }
    
    public void print() {
        PApplet.println(this.element.toString());
    }
    
    static class Gradient extends PShapeSVG
    {
        AffineTransform transform;
        float[] offset;
        int[] color;
        int count;
        
        public Gradient(final PShapeSVG pShapeSVG, final XMLElement xmlElement) {
            super(pShapeSVG, xmlElement);
            final XMLElement[] children = xmlElement.getChildren();
            this.offset = new float[children.length];
            this.color = new int[children.length];
            for (int i = 0; i < children.length; ++i) {
                final XMLElement xmlElement2 = children[i];
                if (xmlElement2.getName().equals("stop")) {
                    this.offset[this.count] = xmlElement2.getFloatAttribute("offset");
                    final HashMap<String, String> styleAttributes = PShapeSVG.parseStyleAttributes(xmlElement2.getStringAttribute("style"));
                    String s = styleAttributes.get("stop-color");
                    if (s == null) {
                        s = "#000000";
                    }
                    String s2 = styleAttributes.get("stop-opacity");
                    if (s2 == null) {
                        s2 = "1";
                    }
                    this.color[this.count] = ((int)(PApplet.parseFloat(s2) * 255.0f) << 24 | Integer.parseInt(s.substring(1), 16));
                    ++this.count;
                }
            }
            this.offset = PApplet.subset(this.offset, 0, this.count);
            this.color = PApplet.subset(this.color, 0, this.count);
        }
    }
    
    class LinearGradient extends Gradient
    {
        float x1;
        float y1;
        float x2;
        float y2;
        
        public LinearGradient(final PShapeSVG pShapeSVG, final XMLElement xmlElement) {
            super(pShapeSVG, xmlElement);
            this.x1 = xmlElement.getFloatAttribute("x1");
            this.y1 = xmlElement.getFloatAttribute("y1");
            this.x2 = xmlElement.getFloatAttribute("x2");
            this.y2 = xmlElement.getFloatAttribute("y2");
            final String stringAttribute = xmlElement.getStringAttribute("gradientTransform");
            if (stringAttribute != null) {
                final float[] value = PShapeSVG.parseMatrix(stringAttribute).get(null);
                this.transform = new AffineTransform(value[0], value[3], value[1], value[4], value[2], value[5]);
                final Point2D transform = this.transform.transform(new Point2D.Float(this.x1, this.y1), null);
                final Point2D transform2 = this.transform.transform(new Point2D.Float(this.x2, this.y2), null);
                this.x1 = (float)transform.getX();
                this.y1 = (float)transform.getY();
                this.x2 = (float)transform2.getX();
                this.y2 = (float)transform2.getY();
            }
        }
    }
    
    class RadialGradient extends Gradient
    {
        float cx;
        float cy;
        float r;
        
        public RadialGradient(final PShapeSVG pShapeSVG, final XMLElement xmlElement) {
            super(pShapeSVG, xmlElement);
            this.cx = xmlElement.getFloatAttribute("cx");
            this.cy = xmlElement.getFloatAttribute("cy");
            this.r = xmlElement.getFloatAttribute("r");
            final String stringAttribute = xmlElement.getStringAttribute("gradientTransform");
            if (stringAttribute != null) {
                final float[] value = PShapeSVG.parseMatrix(stringAttribute).get(null);
                this.transform = new AffineTransform(value[0], value[3], value[1], value[4], value[2], value[5]);
                final Point2D transform = this.transform.transform(new Point2D.Float(this.cx, this.cy), null);
                final Point2D transform2 = this.transform.transform(new Point2D.Float(this.cx + this.r, this.cy), null);
                this.cx = (float)transform.getX();
                this.cy = (float)transform.getY();
                this.r = (float)(transform2.getX() - transform.getX());
            }
        }
    }
    
    class LinearGradientPaint implements Paint
    {
        float x1;
        float y1;
        float x2;
        float y2;
        float[] offset;
        int[] color;
        int count;
        float opacity;
        
        public LinearGradientPaint(final float x1, final float y1, final float x2, final float y2, final float[] offset, final int[] color, final int count, final float opacity) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.offset = offset;
            this.color = color;
            this.count = count;
            this.opacity = opacity;
        }
        
        public PaintContext createContext(final ColorModel colorModel, final Rectangle rectangle, final Rectangle2D rectangle2D, final AffineTransform affineTransform, final RenderingHints renderingHints) {
            final Point2D transform = affineTransform.transform(new Point2D.Float(this.x1, this.y1), null);
            final Point2D transform2 = affineTransform.transform(new Point2D.Float(this.x2, this.y2), null);
            return new LinearGradientContext((float)transform.getX(), (float)transform.getY(), (float)transform2.getX(), (float)transform2.getY());
        }
        
        public int getTransparency() {
            return 3;
        }
        
        public class LinearGradientContext implements PaintContext
        {
            int ACCURACY;
            float tx1;
            float ty1;
            float tx2;
            float ty2;
            
            public LinearGradientContext(final float tx1, final float ty1, final float tx2, final float ty2) {
                this.ACCURACY = 2;
                this.tx1 = tx1;
                this.ty1 = ty1;
                this.tx2 = tx2;
                this.ty2 = ty2;
            }
            
            public void dispose() {
            }
            
            public ColorModel getColorModel() {
                return ColorModel.getRGBdefault();
            }
            
            public Raster getRaster(final int n, final int n2, final int n3, final int n4) {
                final WritableRaster compatibleWritableRaster = this.getColorModel().createCompatibleWritableRaster(n3, n4);
                final int[] array = new int[n3 * n4 * 4];
                float n5 = this.tx2 - this.tx1;
                float n6 = this.ty2 - this.ty1;
                final float n7 = (float)Math.sqrt(n5 * n5 + n6 * n6);
                if (n7 != 0.0f) {
                    n5 /= n7;
                    n6 /= n7;
                }
                final int n8 = (int)PApplet.dist(this.tx1, this.ty1, this.tx2, this.ty2) * this.ACCURACY;
                if (n8 <= 0) {
                    int n9 = 0;
                    for (int i = 0; i < n4; ++i) {
                        for (int j = 0; j < n3; ++j) {
                            array[n9++] = 0;
                            array[n9++] = 0;
                            array[n9++] = 0;
                            array[n9++] = 255;
                        }
                    }
                }
                else {
                    final int[][] array2 = new int[n8][4];
                    int n10 = 0;
                    for (int k = 1; k < LinearGradientPaint.this.count; ++k) {
                        final int n11 = LinearGradientPaint.this.color[k - 1];
                        final int n12 = LinearGradientPaint.this.color[k];
                        final int n13 = (int)(LinearGradientPaint.this.offset[k] * (n8 - 1));
                        for (int l = n10; l <= n13; ++l) {
                            final float norm = PApplet.norm(l, n10, n13);
                            array2[l][0] = (int)PApplet.lerp(n11 >> 16 & 0xFF, n12 >> 16 & 0xFF, norm);
                            array2[l][1] = (int)PApplet.lerp(n11 >> 8 & 0xFF, n12 >> 8 & 0xFF, norm);
                            array2[l][2] = (int)PApplet.lerp(n11 & 0xFF, n12 & 0xFF, norm);
                            array2[l][3] = (int)(PApplet.lerp(n11 >> 24 & 0xFF, n12 >> 24 & 0xFF, norm) * LinearGradientPaint.this.opacity);
                        }
                        n10 = n13;
                    }
                    int n14 = 0;
                    for (int n15 = 0; n15 < n4; ++n15) {
                        for (int n16 = 0; n16 < n3; ++n16) {
                            int n17 = (int)(((n + n16 - this.tx1) * n5 + (n2 + n15 - this.ty1) * n6) * this.ACCURACY);
                            if (n17 < 0) {
                                n17 = 0;
                            }
                            if (n17 > array2.length - 1) {
                                n17 = array2.length - 1;
                            }
                            array[n14++] = array2[n17][0];
                            array[n14++] = array2[n17][1];
                            array[n14++] = array2[n17][2];
                            array[n14++] = array2[n17][3];
                        }
                    }
                }
                compatibleWritableRaster.setPixels(0, 0, n3, n4, array);
                return compatibleWritableRaster;
            }
        }
    }
    
    class RadialGradientPaint implements Paint
    {
        float cx;
        float cy;
        float radius;
        float[] offset;
        int[] color;
        int count;
        float opacity;
        
        public RadialGradientPaint(final float cx, final float cy, final float radius, final float[] offset, final int[] color, final int count, final float opacity) {
            this.cx = cx;
            this.cy = cy;
            this.radius = radius;
            this.offset = offset;
            this.color = color;
            this.count = count;
            this.opacity = opacity;
        }
        
        public PaintContext createContext(final ColorModel colorModel, final Rectangle rectangle, final Rectangle2D rectangle2D, final AffineTransform affineTransform, final RenderingHints renderingHints) {
            return new RadialGradientContext();
        }
        
        public int getTransparency() {
            return 3;
        }
        
        public class RadialGradientContext implements PaintContext
        {
            int ACCURACY;
            
            public RadialGradientContext() {
                this.ACCURACY = 5;
            }
            
            public void dispose() {
            }
            
            public ColorModel getColorModel() {
                return ColorModel.getRGBdefault();
            }
            
            public Raster getRaster(final int n, final int n2, final int n3, final int n4) {
                final WritableRaster compatibleWritableRaster = this.getColorModel().createCompatibleWritableRaster(n3, n4);
                final int n5 = (int)RadialGradientPaint.this.radius * this.ACCURACY;
                final int[][] array = new int[n5][4];
                int n6 = 0;
                for (int i = 1; i < RadialGradientPaint.this.count; ++i) {
                    final int n7 = RadialGradientPaint.this.color[i - 1];
                    final int n8 = RadialGradientPaint.this.color[i];
                    final int n9 = (int)(RadialGradientPaint.this.offset[i] * (n5 - 1));
                    for (int j = n6; j <= n9; ++j) {
                        final float norm = PApplet.norm(j, n6, n9);
                        array[j][0] = (int)PApplet.lerp(n7 >> 16 & 0xFF, n8 >> 16 & 0xFF, norm);
                        array[j][1] = (int)PApplet.lerp(n7 >> 8 & 0xFF, n8 >> 8 & 0xFF, norm);
                        array[j][2] = (int)PApplet.lerp(n7 & 0xFF, n8 & 0xFF, norm);
                        array[j][3] = (int)(PApplet.lerp(n7 >> 24 & 0xFF, n8 >> 24 & 0xFF, norm) * RadialGradientPaint.this.opacity);
                    }
                    n6 = n9;
                }
                final int[] array2 = new int[n3 * n4 * 4];
                int n10 = 0;
                for (int k = 0; k < n4; ++k) {
                    for (int l = 0; l < n3; ++l) {
                        final int min = PApplet.min((int)(PApplet.dist(RadialGradientPaint.this.cx, RadialGradientPaint.this.cy, n + l, n2 + k) * this.ACCURACY), array.length - 1);
                        array2[n10++] = array[min][0];
                        array2[n10++] = array[min][1];
                        array2[n10++] = array[min][2];
                        array2[n10++] = array[min][3];
                    }
                }
                compatibleWritableRaster.setPixels(0, 0, n3, n4, array2);
                return compatibleWritableRaster;
            }
        }
    }
}
