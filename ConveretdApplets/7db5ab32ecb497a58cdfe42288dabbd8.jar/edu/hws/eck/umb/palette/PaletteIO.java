// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb.palette;

import edu.hws.eck.umb.util.Util;
import java.io.PrintWriter;
import org.w3c.dom.Document;
import java.io.File;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.io.IOException;
import edu.hws.eck.umb.util.I18n;
import org.w3c.dom.Element;
import edu.hws.eck.umb.util.SimpleFileChooser;

public class PaletteIO
{
    private static SimpleFileChooser fileChooser;
    
    public static String paletteToXML(final Palette palette) {
        final StringBuffer sb = new StringBuffer();
        sb.append("<palette colorType=");
        sb.append((palette.getColorType() == 1) ? "'HSB'" : "'RGB'");
        sb.append(">\n");
        if (!palette.getMirrorOutOfRangeComponents()) {
            sb.append("   <mirrorOutOfRangeComponents value='" + palette.getMirrorOutOfRangeComponents() + "'/>\n");
        }
        for (int divisionPointCount = palette.getDivisionPointCount(), i = 0; i < divisionPointCount; ++i) {
            sb.append("   <divisionPoint");
            sb.append(" position='" + palette.getDivisionPoint(i) + "'");
            sb.append(" color='");
            final float[] divisionPointColorComponents = palette.getDivisionPointColorComponents(i);
            sb.append(divisionPointColorComponents[0]);
            sb.append(';');
            sb.append(divisionPointColorComponents[1]);
            sb.append(';');
            sb.append(divisionPointColorComponents[2]);
            sb.append("'/>\n");
        }
        sb.append("</palette>\n");
        return sb.toString();
    }
    
    public static Palette xmlToPalette(final Element element) throws IOException {
        if (!element.getNodeName().equals("palette")) {
            throw new IOException(I18n.tr("paletteIO.xmlToPalette.error.elementIsNotPalette", new Object[0]));
        }
        final String attribute = element.getAttribute("colorType");
        boolean b;
        if (attribute == null || attribute.equalsIgnoreCase("HSB")) {
            b = true;
        }
        else {
            if (!attribute.equalsIgnoreCase("RGB")) {
                throw new IOException(I18n.tr("paletteIO.xmlToPalette.error.illegalAtrributeValue", "palette", "colorType", attribute));
            }
            b = false;
        }
        boolean b2 = true;
        final ArrayList<Double> list = new ArrayList<Double>();
        final ArrayList<float[]> list2 = new ArrayList<float[]>();
        final NodeList childNodes = element.getChildNodes();
        final int length = childNodes.getLength();
        double n = -1.0;
        for (int i = 0; i < length; ++i) {
            final Node item = childNodes.item(i);
            if (item instanceof Element) {
                final Element element2 = (Element)item;
                final String nodeName = element2.getNodeName();
                if (nodeName.equals("mirrorOutOfRangeComponents")) {
                    final String attribute2 = element2.getAttribute("value");
                    if (attribute2 != null && attribute2.equalsIgnoreCase("false")) {
                        b2 = false;
                    }
                    else if (attribute2 != null && !attribute2.equalsIgnoreCase("true")) {
                        throw new IOException(I18n.tr("paletteIO.xmlToPalette.error.illegalAtrributeValue", attribute2));
                    }
                }
                else if (nodeName.equals("divisionPoint")) {
                    final String attribute3 = element2.getAttribute("position");
                    final String attribute4 = element2.getAttribute("color");
                    if (attribute3 == null) {
                        throw new IOException(I18n.tr("paletteIO.xmlToPalette.error.missingAtrributeValue", "divisionPoint", "position"));
                    }
                    if (attribute4 == null) {
                        throw new IOException(I18n.tr("paletteIO.xmlToPalette.error.missingAtrributeValue", "divisionPoint", "color"));
                    }
                    final float[] array = new float[3];
                    double double1;
                    try {
                        double1 = Double.parseDouble(attribute3);
                    }
                    catch (NumberFormatException ex) {
                        throw new IOException(I18n.tr("", "divisionPoint", "position", attribute3));
                    }
                    try {
                        final String[] split = attribute4.split("[;, ]+");
                        array[0] = Float.parseFloat(split[0]);
                        array[1] = Float.parseFloat(split[1]);
                        array[2] = Float.parseFloat(split[2]);
                    }
                    catch (Exception ex2) {
                        throw new IOException(I18n.tr("", "divisionPoint", "color", attribute4));
                    }
                    if (n == -1.0 && double1 != 0.0) {
                        throw new IOException(I18n.tr("paletteIO.xmlToPalette.error.firstPositionMustBeZero", new Object[0]));
                    }
                    if (double1 <= n) {
                        throw new IOException(I18n.tr("paletteIO.xmlToPalette.error.positionsNotInIncreasingOrder", new Object[0]));
                    }
                    list.add(double1);
                    list2.add(array);
                    n = double1;
                }
            }
        }
        if (n != 1.0) {
            throw new IOException(I18n.tr("paletteIO.xmlToPalette.error.lastPositionMustBeOne", new Object[0]));
        }
        return new Palette(b ? 1 : 0, b2, list, list2);
    }
    
    public static Palette doOpen(final Component component) {
        final File inputFile = getSimpleFileChooser().getInputFile(component, I18n.tr("paletteIO.openDialog.title", new Object[0]));
        if (inputFile == null) {
            return null;
        }
        Document parse;
        try {
            parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputFile);
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(component, I18n.tr("paletteIO.openDialog.error.cantReadFile", inputFile.getName(), ex.getMessage()));
            return null;
        }
        catch (SAXException ex4) {
            JOptionPane.showMessageDialog(component, I18n.tr("paletteIO.openDialog.error.fileIsNotXML", inputFile.getName()));
            return null;
        }
        catch (ParserConfigurationException ex2) {
            JOptionPane.showMessageDialog(component, I18n.tr("paletteIO.openDialog.error.cantReadFile", inputFile.getName(), ex2.getMessage()));
            return null;
        }
        try {
            final Palette xmlToPalette = xmlToPalette(parse.getDocumentElement());
            saveDirectoryPref();
            return xmlToPalette;
        }
        catch (IOException ex3) {
            JOptionPane.showMessageDialog(component, I18n.tr("paletteIO.openDialog.error.fileIsNotPalette", inputFile.getName(), ex3.getMessage()));
            return null;
        }
    }
    
    public static boolean doSave(final Component component, final Palette palette) {
        final File outputFile = getSimpleFileChooser().getOutputFile(component, I18n.tr("paletteIO.saveDialog.title", new Object[0]), I18n.tr("paletteIO.saveDialog.defaultFileName", new Object[0]));
        if (outputFile == null) {
            return false;
        }
        try {
            final PrintWriter printWriter = new PrintWriter(outputFile);
            printWriter.print("<?xml version='1.0'?>\n");
            printWriter.print(paletteToXML(palette));
            printWriter.flush();
            printWriter.close();
            if (printWriter.checkError()) {
                throw new Exception(I18n.tr("paletteIO.saveDialog.error.genericWriteError", new Object[0]));
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(component, I18n.tr("paletteIO.saveDialog.error.cantWriteFile", outputFile.getName(), ex.getMessage()));
            return false;
        }
        saveDirectoryPref();
        return true;
    }
    
    private static SimpleFileChooser getSimpleFileChooser() {
        if (PaletteIO.fileChooser == null) {
            PaletteIO.fileChooser = new SimpleFileChooser();
            final String pref = Util.getPref("palette.defaultDirectory");
            if (pref != null) {
                PaletteIO.fileChooser.setDefaultDirectory(pref);
            }
        }
        return PaletteIO.fileChooser;
    }
    
    private static void saveDirectoryPref() {
        Util.setPref("palette.defaultDirectory", PaletteIO.fileChooser.getCurrentDirectory());
    }
}
