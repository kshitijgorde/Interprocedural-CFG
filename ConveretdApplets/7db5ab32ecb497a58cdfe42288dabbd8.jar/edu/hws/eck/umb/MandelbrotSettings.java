// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.IOException;
import edu.hws.eck.umb.util.I18n;
import org.w3c.dom.Element;
import edu.hws.eck.umb.palette.PaletteIO;
import java.awt.Dimension;
import edu.hws.eck.umb.palette.PaletteMapping;
import java.awt.Color;
import edu.hws.eck.umb.palette.Palette;
import java.math.BigDecimal;

public class MandelbrotSettings
{
    private BigDecimal[] limits;
    private Palette palette;
    private Color mandelbrotColor;
    private PaletteMapping paletteMapping;
    private int maxIterations;
    private boolean highPrecisionEnabled;
    private Dimension imageSize;
    
    public MandelbrotSettings() {
        this.limits = new BigDecimal[] { new BigDecimal(-2.333), new BigDecimal(1), new BigDecimal(-1.25), new BigDecimal(1.25) };
        this.palette = new Palette();
        this.mandelbrotColor = Color.BLACK;
        this.paletteMapping = new PaletteMapping();
        this.maxIterations = 100;
        this.highPrecisionEnabled = true;
        this.imageSize = null;
    }
    
    public MandelbrotSettings(final MandelbrotDisplay mandelbrotDisplay) {
        this.limits = mandelbrotDisplay.getLimitsRequested();
        this.maxIterations = mandelbrotDisplay.getMaxIterations();
        this.palette = mandelbrotDisplay.getCopyOfPalette();
        this.mandelbrotColor = mandelbrotDisplay.getMandelbrotColor();
        this.paletteMapping = new PaletteMapping(mandelbrotDisplay.getPaletteLength(), mandelbrotDisplay.getPaletteOffset());
        this.highPrecisionEnabled = mandelbrotDisplay.getHighPrecisionEnabled();
        this.imageSize = mandelbrotDisplay.getImageSize();
    }
    
    public BigDecimal[] getLimits() {
        return this.limits;
    }
    
    public void setLimits(final BigDecimal[] limits) {
        this.limits = limits;
    }
    
    public Palette getPalette() {
        return this.palette;
    }
    
    public void setPalette(final Palette palette) {
        this.palette = palette;
    }
    
    public Color getMandelbrotColor() {
        return this.mandelbrotColor;
    }
    
    public void setMandelbrotColor(final Color mandelbrotColor) {
        this.mandelbrotColor = mandelbrotColor;
    }
    
    public PaletteMapping getPaletteMapping() {
        return this.paletteMapping;
    }
    
    public void setPaletteMapping(final PaletteMapping paletteMapping) {
        this.paletteMapping = paletteMapping;
    }
    
    public int getMaxIterations() {
        return this.maxIterations;
    }
    
    public void setMaxIterations(final int maxIterations) {
        this.maxIterations = maxIterations;
    }
    
    public boolean isHighPrecisionEnabled() {
        return this.highPrecisionEnabled;
    }
    
    public void setHighPrecisionEnabled(final boolean highPrecisionEnabled) {
        this.highPrecisionEnabled = highPrecisionEnabled;
    }
    
    public Dimension getImageSize() {
        return this.imageSize;
    }
    
    public void setImageSize(final Dimension imageSize) {
        this.imageSize = imageSize;
    }
    
    public String toXML() {
        final StringBuffer sb = new StringBuffer();
        sb.append("<mandelbrot_settings_2>\n");
        if (this.imageSize != null) {
            sb.append("<image_size width='" + this.imageSize.width + "' height='" + this.imageSize.height + "'/>\n");
        }
        sb.append("<limits>\n");
        sb.append("   <xmin>" + this.limits[0] + "</xmin>\n");
        sb.append("   <xmax>" + this.limits[1] + "</xmax>\n");
        sb.append("   <ymin>" + this.limits[2] + "</ymin>\n");
        sb.append("   <ymax>" + this.limits[3] + "</ymax>\n");
        sb.append("</limits>\n");
        sb.append(PaletteIO.paletteToXML(this.palette));
        sb.append("<mandelbrot_color r='" + this.mandelbrotColor.getRed() + "' g='" + this.mandelbrotColor.getGreen() + "' b='" + this.mandelbrotColor.getBlue() + "'/>\n");
        sb.append("<palette_mapping length='" + this.paletteMapping.getLength() + "' offset='" + this.paletteMapping.getOffset() + "'/>\n");
        sb.append("<max_iterations value='" + this.maxIterations + "'/>\n");
        sb.append("<high_precision_enabled value='" + this.highPrecisionEnabled + "'/>\n");
        sb.append("</mandelbrot_settings_2>\n");
        return sb.toString();
    }
    
    public static MandelbrotSettings createFromXML(final Element element) throws IOException {
        final MandelbrotSettings mandelbrotSettings = new MandelbrotSettings();
        if (!element.getNodeName().equals("mandelbrot_settings_2")) {
            throw new IOException(I18n.tr("mandelbrotSettings.xmlError.NotASettingsElement", new Object[0]));
        }
        final NodeList childNodes = element.getChildNodes();
        for (int length = childNodes.getLength(), i = 0; i < length; ++i) {
            final Node item = childNodes.item(i);
            if (item instanceof Element) {
                final Element element2 = (Element)item;
                final String nodeName = element2.getNodeName();
                if (nodeName.equals("palette")) {
                    mandelbrotSettings.palette = PaletteIO.xmlToPalette(element2);
                }
                else if (nodeName.equals("palette_mapping")) {
                    final int attributeToInt = attributeToInt(element2, "length");
                    if (attributeToInt < 0) {
                        throw new IOException(I18n.tr("mandelbrotSettings.xmlError.attributeCantBeNegative", "palette_mapping", "length"));
                    }
                    final int attributeToInt2 = attributeToInt(element2, "offset");
                    if (attributeToInt < 0) {
                        throw new IOException(I18n.tr("mandelbrotSettings.xmlError.attributeCantBeNegative", "palette_mapping", "offset"));
                    }
                    mandelbrotSettings.paletteMapping = new PaletteMapping(attributeToInt, attributeToInt2);
                }
                else if (nodeName.equals("mandelbrot_color")) {
                    final int attributeToInt3 = attributeToInt(element2, "r");
                    final int attributeToInt4 = attributeToInt(element2, "g");
                    final int attributeToInt5 = attributeToInt(element2, "b");
                    if (attributeToInt3 < 0 || attributeToInt3 > 255 || attributeToInt4 < 0 || attributeToInt4 > 255 || attributeToInt5 < 0 || attributeToInt5 > 255) {
                        throw new IOException(I18n.tr("mandelbrotSettings.xmlError.badColorComponent", new Object[0]));
                    }
                    mandelbrotSettings.mandelbrotColor = new Color(attributeToInt3, attributeToInt4, attributeToInt5);
                }
                else if (nodeName.equals("max_iterations")) {
                    final int attributeToInt6 = attributeToInt(element2, "value");
                    if (attributeToInt6 <= 0) {
                        throw new IOException(I18n.tr("mandelbrotSettings.xmlError.attributemustBePositive", element2.getNodeName(), "value"));
                    }
                    mandelbrotSettings.maxIterations = attributeToInt6;
                }
                else if (nodeName.equals("high_precision_enabled")) {
                    final String attribute = element2.getAttribute("value");
                    if (attribute.equalsIgnoreCase("true")) {
                        mandelbrotSettings.highPrecisionEnabled = true;
                    }
                    else {
                        if (!attribute.equalsIgnoreCase("false")) {
                            throw new IOException(I18n.tr("mandelbrotSettings.xmlError.attributemustBeBoolean", element2.getNodeName(), "value"));
                        }
                        mandelbrotSettings.highPrecisionEnabled = false;
                    }
                }
                else if (nodeName.equals("image_size")) {
                    final int attributeToInt7 = attributeToInt(element2, "width");
                    if (attributeToInt7 <= 0) {
                        throw new IOException(I18n.tr("mandelbrotSettings.xmlError.attributemustBePositive", element2.getNodeName(), "width"));
                    }
                    final int attributeToInt8 = attributeToInt(element2, "height");
                    if (attributeToInt8 <= 0) {
                        throw new IOException(I18n.tr("mandelbrotSettings.xmlError.attributemustBePositive", element2.getNodeName(), "height"));
                    }
                    mandelbrotSettings.imageSize = new Dimension(attributeToInt7, attributeToInt8);
                }
                else if (nodeName.equals("limits")) {
                    final BigDecimal limitFromLimitsElement = getLimitFromLimitsElement(element2, "xmin");
                    final BigDecimal limitFromLimitsElement2 = getLimitFromLimitsElement(element2, "xmax");
                    final BigDecimal limitFromLimitsElement3 = getLimitFromLimitsElement(element2, "ymin");
                    final BigDecimal limitFromLimitsElement4 = getLimitFromLimitsElement(element2, "ymax");
                    if (limitFromLimitsElement2.compareTo(limitFromLimitsElement) <= 0 || limitFromLimitsElement4.compareTo(limitFromLimitsElement3) <= 0) {
                        throw new IOException(I18n.tr("mandelbrotSettings.xmlError.LimitsOutOfOrder", new Object[0]));
                    }
                    mandelbrotSettings.limits = new BigDecimal[] { limitFromLimitsElement, limitFromLimitsElement2, limitFromLimitsElement3, limitFromLimitsElement4 };
                }
            }
        }
        return mandelbrotSettings;
    }
    
    private static int attributeToInt(final Element element, final String s) throws IOException {
        final String attribute = element.getAttribute(s);
        if (attribute == null || attribute.length() == 0) {
            throw new IOException(I18n.tr("mandelbrotSettings.xmlError.missingAttribute", element.getNodeName(), s));
        }
        try {
            return Integer.parseInt(attribute);
        }
        catch (NumberFormatException ex) {
            throw new IOException(I18n.tr("mandelbrotSettings.xmlError.badAttributeValue", element.getNodeName(), s));
        }
    }
    
    private static BigDecimal getLimitFromLimitsElement(final Element element, final String s) throws IOException {
        final NodeList elementsByTagName = element.getElementsByTagName(s);
        if (elementsByTagName.getLength() == 0) {
            throw new IOException(I18n.tr("mandelbrotSettings.xmlError.missingValueInLimits", s));
        }
        if (elementsByTagName.getLength() > 1) {
            throw new IOException(I18n.tr("mandelbrotSettings.xmlError.extraValueInLimits", s));
        }
        final String textContent = ((Element)elementsByTagName.item(0)).getTextContent();
        try {
            return new BigDecimal(textContent);
        }
        catch (NumberFormatException ex) {
            throw new IOException(I18n.tr("mandelbrotSettings.xmlError.badValueInLimits", s));
        }
    }
}
