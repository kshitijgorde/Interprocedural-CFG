// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.util.List;
import com.itt.J2KViewer.util.SecurityUtil;
import java.text.DecimalFormat;
import java.awt.Rectangle;
import com.itt.J2KViewer.util.ImageUtils;
import java.beans.PropertyVetoException;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.itt.J2KViewer.util.Base64;
import javax.xml.transform.Transformer;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import javax.xml.parsers.ParserConfigurationException;
import com.itt.J2KViewer.util.XMLValidator;
import javax.xml.transform.TransformerException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import java.io.Writer;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import org.w3c.dom.Node;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Properties;
import com.itt.J2KViewer.util.WebUtils;
import com.itt.J2KViewer.imagetools.NITFChipper;
import com.itt.J2KViewer.util.Helper;
import java.io.InputStream;
import com.itt.J2KViewer.util.J2KViewerW3cXmlUtilities;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import javax.swing.JFileChooser;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import java.io.BufferedReader;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.GridBagConstraints;
import java.net.HttpURLConnection;
import java.net.URL;
import com.itt.J2KViewer.georvm.NITFGeoUtils;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.util.Log;
import javax.swing.JDialog;

public class ChipperDialog extends JDialog
{
    private static final long serialVersionUID = 1L;
    private static Log log;
    private final int ROW_0 = 0;
    private final int ROW_1 = 1;
    private final int ROW_2 = 2;
    private final int ROW_3 = 3;
    private final int ROW_4 = 4;
    private final int ROW_5 = 5;
    private final int ROW_6 = 6;
    private final int ROW_7 = 7;
    private final int ROW_8 = 8;
    private final int ROW_9 = 9;
    private final int ROW_10 = 10;
    private final int ROW_11 = 11;
    private final int ROW_12 = 12;
    private final int ROW_13 = 13;
    private final int ROW_14 = 14;
    private final int COLUMN_0 = 0;
    private final int COLUMN_1 = 1;
    private final int COLUMN_2 = 2;
    private ViewCentral viewCentral;
    private NITFGeoUtils nitfGeoUtils;
    private URL url;
    private HttpURLConnection connection;
    private GridBagConstraints constraintsLabel;
    private GridBagConstraints constraintsField;
    private Point chipStart;
    private Point chipEnd;
    private JPanel dataPanel;
    private JPanel buttonPanel;
    private JLabel imagePathLabel;
    private JLabel upperLeftLabel;
    private JLabel lowerRightLabel;
    private JLabel targetResolutionLabel;
    private JLabel outputFileLabel;
    private JLabel conversionOptionsLabel;
    private JLabel ftpHostLabel;
    private JLabel ftpHostPathLabel;
    private JLabel ftpHostLoginLabel;
    private JLabel ftpHostPasswdLabel;
    private JLabel sourceCoordinateTypeLabel;
    private JLabel serviceTypeLabel;
    private JLabel compressionOptionsLabel;
    private JTextField imagePathText;
    private JTextField upperLeftText;
    private JTextField lowerRightText;
    private JTextField outputFileField;
    private JTextField ftpHostField;
    private JTextField ftpHostPathField;
    private JTextField ftpHostLoginField;
    private JPasswordField ftpHostPasswdField;
    private JComboBox resolutionList;
    private JComboBox conversionOptionsList;
    private JComboBox coordinateTypesList;
    private JComboBox serviceTypeList;
    private JComboBox compressionOptionsList;
    private JButton sendButton;
    private JButton cancelButton;
    private JButton chooserButton;
    private String userId;
    private String outputFileDisplay;
    private String fileNameOnly;
    private String imageFilePath;
    private String imageryDirectory;
    private String targetResolutionString;
    private String qualityLayerString;
    private String upperLeftXString;
    private String upperLeftYString;
    private String lowerRightXString;
    private String lowerRightYString;
    private String upperCoordinates;
    private String lowerCoordinates;
    private String[] upperLeftGeoCoors;
    private String[] lowerRightGeoCoors;
    private String fileFormat;
    private int resolutionLevel;
    private String[] compressionOptions;
    private String chippingServiceURL;
    private int discardLevel;
    private int qualityLayer;
    private int upperLeftX;
    private int upperLeftY;
    private int lowerRightX;
    private int lowerRightY;
    private BufferedReader bufferedReader;
    private JFrame parentFrame;
    static /* synthetic */ Class class$com$itt$J2KViewer$gui$ChipperDialog;
    
    public ChipperDialog(final JFrame parentFrame, final boolean b, final ViewCentral viewCentral, final String userId) {
        super(parentFrame, b);
        this.url = null;
        this.connection = null;
        this.constraintsLabel = new GridBagConstraints();
        this.constraintsField = new GridBagConstraints();
        this.chipStart = null;
        this.chipEnd = null;
        this.dataPanel = null;
        this.buttonPanel = null;
        this.imagePathLabel = null;
        this.upperLeftLabel = null;
        this.lowerRightLabel = null;
        this.targetResolutionLabel = null;
        this.outputFileLabel = null;
        this.conversionOptionsLabel = null;
        this.ftpHostLabel = null;
        this.ftpHostPathLabel = null;
        this.ftpHostLoginLabel = null;
        this.ftpHostPasswdLabel = null;
        this.sourceCoordinateTypeLabel = null;
        this.serviceTypeLabel = null;
        this.compressionOptionsLabel = null;
        this.imagePathText = null;
        this.upperLeftText = null;
        this.lowerRightText = null;
        this.outputFileField = null;
        this.ftpHostField = null;
        this.ftpHostPathField = null;
        this.ftpHostLoginField = null;
        this.ftpHostPasswdField = null;
        this.resolutionList = null;
        this.conversionOptionsList = null;
        this.coordinateTypesList = null;
        this.serviceTypeList = null;
        this.compressionOptionsList = null;
        this.sendButton = null;
        this.cancelButton = null;
        this.chooserButton = null;
        this.userId = null;
        this.outputFileDisplay = null;
        this.fileNameOnly = null;
        this.imageFilePath = null;
        this.imageryDirectory = null;
        this.targetResolutionString = null;
        this.qualityLayerString = null;
        this.upperLeftXString = null;
        this.upperLeftYString = null;
        this.lowerRightXString = null;
        this.lowerRightYString = null;
        this.upperCoordinates = null;
        this.lowerCoordinates = null;
        this.upperLeftGeoCoors = null;
        this.lowerRightGeoCoors = null;
        this.fileFormat = "NITF";
        this.resolutionLevel = 1;
        this.compressionOptions = new String[] { "No Compression" };
        this.chippingServiceURL = null;
        this.discardLevel = -1;
        this.qualityLayer = -1;
        this.upperLeftX = -1;
        this.upperLeftY = -1;
        this.lowerRightX = -1;
        this.lowerRightY = -1;
        this.parentFrame = null;
        this.parentFrame = parentFrame;
        this.setDefaultCloseOperation(2);
        this.viewCentral = viewCentral;
        this.nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
        this.userId = userId;
        if (this.userId != null) {
            this.userId = this.userId.replaceAll(" ", "_");
        }
        this.chippingServiceURL = this.viewCentral.getPropertyManager().getChippingServiceURL();
        this.getChipParameters();
        this.processImageFilePath();
        final Point mouseReleased = this.viewCentral.getPropertyManager().getMouseReleased();
        this.setLocation(mouseReleased.x / 2, mouseReleased.y / 2);
        this.setTitle("Chip Parameters:");
        (this.dataPanel = new JPanel()).setLayout(new GridBagLayout());
        this.dataPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        this.constraintsLabel.fill = 2;
        this.constraintsLabel.insets = new Insets(5, 5, 0, 0);
        this.constraintsField.fill = 2;
        this.constraintsField.insets = new Insets(5, 5, 0, 0);
        this.constraintsField.gridwidth = 1;
        this.buildImageFilePath();
        this.buildSourceCoordinateType();
        this.buildUpperLeftCoordinates();
        this.buildLowerRightCoordinates();
        this.buildTargetResolution();
        this.buildConversionOptions();
        this.buildServiceTypeOptions();
        this.buildCompressionOptions();
        this.buildOutputFileNameDisplayClient();
        this.getContentPane().add(this.dataPanel, "Center");
        this.buildButtonPanel();
        this.getContentPane().add(this.buttonPanel, "South");
        this.pack();
        this.setSize(new Dimension(this.getPreferredSize().width, this.getPreferredSize().height));
    }
    
    private void sendDataToChipperService() {
        try {
            final String string = "ChipRequest=" + URLEncoder.encode(this.prepare(), "UTF-8");
            this.chippingServiceURL = this.viewCentral.getPropertyManager().getChippingServiceURL();
            this.url = new URL(this.chippingServiceURL);
            (this.connection = (HttpURLConnection)this.url.openConnection()).setRequestProperty("CONTENT-TYPE", "application/x-www-form-urlencoded");
            this.connection.setDoOutput(true);
            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.connection.getOutputStream());
            outputStreamWriter.write(string);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            try {
                this.bufferedReader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
                final StringBuffer sb = new StringBuffer();
                String line;
                while ((line = this.bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                ChipperDialog.log.info("\n" + sb.toString() + "\n");
                JOptionPane.showMessageDialog(this.dataPanel, "Your order has been submitted. Please go to the \nOrder Download Status page for more information.", "Success!", 1);
                this.bufferedReader.close();
            }
            catch (FileNotFoundException ex) {
                ChipperDialog.log.error(ex.getMessage());
                JOptionPane.showMessageDialog(this.dataPanel, "Server responded with an error code.", "FileNotFoundException!", 0);
            }
            catch (IOException ex2) {
                ChipperDialog.log.error(ex2.getMessage());
                JOptionPane.showMessageDialog(this.dataPanel, "Server responded with an error code.", "IOException!", 0);
            }
        }
        catch (MalformedURLException ex3) {
            ex3.printStackTrace();
            ChipperDialog.log.error("Failed to set Chipping Service URL!", ex3);
            JOptionPane.showMessageDialog(this.dataPanel, "Server responded with an error code.\nFailed to set Chipping Service URL!", "Connection Error!", 0);
        }
        catch (ConnectException ex4) {
            ex4.printStackTrace();
            ChipperDialog.log.error("Failed to connect to the Chipping Service servlet!", ex4);
            JOptionPane.showMessageDialog(this.dataPanel, "Server responded with an error code.\nFailed to connect to the service!", "Connection Error!", 0);
        }
        catch (IOException ex5) {
            ex5.printStackTrace();
            ChipperDialog.log.error("Failed to connect to the Chipping Service servlet!", ex5);
            JOptionPane.showMessageDialog(this.dataPanel, "Server responded with an error code.\nFailed to connect to the Chipping Service servlet!", "Connection Error!", 0);
        }
    }
    
    public void sendRequestToServerChipper() {
        this.chippingServiceURL = this.buildServerRequest();
        try {
            this.url = new URL(this.chippingServiceURL);
            this.connection = (HttpURLConnection)this.url.openConnection();
            final String headerField = this.connection.getHeaderField("Content-Type");
            final String headerField2 = this.connection.getHeaderField("Content-Disposition");
            if (!"text/xml".equals(headerField)) {
                final String substring = headerField2.substring(headerField2.indexOf("filename=") + 9);
                System.out.println("Filename : " + substring);
                final JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File(substring));
                if (fileChooser.showDialog(this, "Done") == 0) {
                    final File selectedFile = fileChooser.getSelectedFile();
                    final InputStream inputStream = this.connection.getInputStream();
                    final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(selectedFile));
                    int read;
                    while ((read = inputStream.read()) != -1) {
                        bufferedOutputStream.write(read);
                    }
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    inputStream.close();
                }
            }
            else {
                final InputStream inputStream2 = this.connection.getInputStream();
                final String nodeValue = J2KViewerW3cXmlUtilities.getChildElement(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream2).getDocumentElement(), "ServiceException").getFirstChild().getNodeValue();
                inputStream2.close();
                JOptionPane.showMessageDialog(this, nodeValue, "Error Performing Chip", 0);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error Performing Chip", 0);
        }
    }
    
    private String buildServerRequest() {
        final StringBuffer sb = new StringBuffer(this.viewCentral.getPropertyManager().getChippingServiceURL());
        sb.append("?");
        sb.append("REQUEST=");
        sb.append("GetCoverage");
        sb.append("&");
        sb.append("COVERAGE=");
        sb.append(this.fileNameOnly);
        sb.append("&");
        sb.append("CRS=");
        sb.append("Image");
        sb.append("&");
        sb.append("FORMAT=");
        sb.append((String)this.conversionOptionsList.getSelectedItem());
        sb.append("&");
        sb.append("BBOX=");
        sb.append(this.upperCoordinates + "," + this.lowerCoordinates);
        return sb.toString();
    }
    
    private int sendDataToClientChipper() {
        final File file = new File(this.outputFileField.getText());
        final File parentFile = file.getParentFile();
        try {
            System.out.println("Path: " + parentFile.getCanonicalPath());
            this.viewCentral.getPropertyManager().setChipperOutputDirectory(parentFile.getCanonicalPath());
        }
        catch (Exception ex3) {
            this.viewCentral.getPropertyManager().setChipperOutputDirectory(parentFile.getAbsolutePath());
        }
        int showConfirmDialog = 0;
        if (file.exists()) {
            showConfirmDialog = JOptionPane.showConfirmDialog(this, "File already exists! Overwrite?");
        }
        if (showConfirmDialog == 0) {
            final String property = System.getProperty("os.name");
            String property2 = new String();
            try {
                final Properties envVars = Helper.getEnvVars();
                final Enumeration<?> propertyNames = envVars.propertyNames();
                while (propertyNames.hasMoreElements()) {
                    final String s = (String)propertyNames.nextElement();
                    if (s.equalsIgnoreCase("javaidljar")) {
                        property2 = envVars.getProperty(s);
                        break;
                    }
                }
            }
            catch (Throwable t) {
                ChipperDialog.log.info("Failed checking availability of chipping.");
                System.out.println(t.toString());
                JOptionPane.showMessageDialog(this, t.getMessage(), "Chipping error: " + t.toString(), 0);
            }
            if (property.indexOf("Windows") > -1) {
                System.out.println("Detected Windows");
                if (property2.endsWith("jar")) {
                    final NITFChipper chipperInstance = NITFChipper.getChipperInstance();
                    this.viewCentral.getPropertyManager().setChipper(chipperInstance);
                    if (chipperInstance.getState() == NITFChipper.UNINITIALIZED) {
                        chipperInstance.init();
                    }
                    chipperInstance.addChipJob(WebUtils.URLDecode(this.viewCentral.getPropertyManager().getImageURL()), this.outputFileField.getText(), this.upperLeftX, this.upperLeftY, this.lowerRightX, this.lowerRightY, (String)this.compressionOptionsList.getSelectedItem());
                }
                else {
                    System.out.println("Trying alternate installation");
                    final String urlDecode = WebUtils.URLDecode(this.viewCentral.getPropertyManager().getImageURL());
                    final StringBuffer sb = new StringBuffer("\"" + property2 + File.separator + "IDL70\\bin\\bin.x86\\idlrt\"");
                    final StringBuffer sb2 = new StringBuffer("-em=");
                    sb2.append("\"" + property2 + File.separator + "IDL70\\lib\\hook\\NITFChipper.sav\"");
                    sb2.append(" -args ");
                    sb2.append("\"" + urlDecode + "\"");
                    sb2.append(" \"" + this.outputFileField.getText() + "\" ");
                    sb2.append(this.upperLeftX + " " + this.lowerRightX + " " + this.upperLeftY + " " + this.lowerRightY + " ");
                    sb2.append("\"" + (String)this.compressionOptionsList.getSelectedItem() + "\" ");
                    sb2.append("\"\" \"\"");
                    sb2.append(" \"" + this.fileFormat + "\" ");
                    sb2.append(this.resolutionLevel);
                    System.out.println(sb.toString() + " " + sb2.toString());
                    final String[] array = { sb.toString(), sb2.toString() };
                    try {
                        final Process exec = Runtime.getRuntime().exec(sb.toString() + " " + sb2.toString());
                        final StreamGobbler streamGobbler = new StreamGobbler(exec.getErrorStream(), "ERROR");
                        final StreamGobbler streamGobbler2 = new StreamGobbler(exec.getInputStream(), "OUTPUT");
                        streamGobbler.start();
                        streamGobbler2.start();
                    }
                    catch (Exception ex) {
                        System.out.println("ERROR : Could not execute the NITFChipper");
                        System.out.println(ex.toString());
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Chipping error: " + ex.toString(), 0);
                    }
                }
            }
            else {
                final String[] array2 = { property2 + File.separator + "idl70/idl", "-em=" + property2 + File.separator + "idl70/lib/hook/NITFChipper.sav", "-args", WebUtils.URLDecode(this.viewCentral.getPropertyManager().getImageURL()), this.outputFileField.getText(), Integer.toString(this.upperLeftX), Integer.toString(this.lowerRightX), Integer.toString(this.upperLeftY), Integer.toString(this.lowerRightY), (String)this.compressionOptionsList.getSelectedItem(), "", "", this.fileFormat, Integer.toString(this.resolutionLevel) };
                try {
                    final Process exec2 = Runtime.getRuntime().exec(array2);
                    final StreamGobbler streamGobbler3 = new StreamGobbler(exec2.getErrorStream(), "ERROR");
                    final StreamGobbler streamGobbler4 = new StreamGobbler(exec2.getInputStream(), "OUTPUT");
                    streamGobbler3.start();
                    streamGobbler4.start();
                }
                catch (Exception ex2) {
                    System.out.println("ERROR : Could not execute the NITFChipper");
                    System.out.println(ex2.toString());
                    JOptionPane.showMessageDialog(this, ex2.getMessage(), "Chipping error: " + ex2.toString(), 0);
                }
            }
        }
        return showConfirmDialog;
    }
    
    private int getServerReply() throws IOException, NullPointerException {
        try {
            final String substring = this.getFullServerReply().substring(0, 3);
            if (substring != null) {
                return Integer.parseInt(substring);
            }
        }
        catch (NullPointerException ex) {
            return 500;
        }
        return 500;
    }
    
    private String getFullServerReply() throws IOException, SocketException, NullPointerException {
        String line = null;
        try {
            do {
                line = this.bufferedReader.readLine();
            } while (!Character.isDigit(line.charAt(0)) || !Character.isDigit(line.charAt(1)) || !Character.isDigit(line.charAt(2)) || line.charAt(3) != ' ');
            return line;
        }
        catch (SocketException ex) {}
        catch (IOException ex2) {}
        catch (NullPointerException ex3) {}
        return line;
    }
    
    private boolean isPositiveCompleteResponse(final int n) {
        return n >= 200 && n < 300;
    }
    
    protected String prepare() {
        final String chippingCredentials = this.viewCentral.getPropertyManager().getChippingCredentials();
        final String chippingResultKey = this.viewCentral.getPropertyManager().getChippingResultKey();
        final String destinationCredentials = this.getDestinationCredentials();
        final String chippingFTPHost = this.viewCentral.getPropertyManager().getChippingFTPHost();
        final String chippingFTPHostPath = this.viewCentral.getPropertyManager().getChippingFTPHostPath();
        final String text = this.outputFileField.getText();
        final String string = this.conversionOptionsList.getSelectedItem().toString();
        final String string2 = this.coordinateTypesList.getSelectedItem().toString();
        String string3 = null;
        String string4 = null;
        String string5 = null;
        String string6 = null;
        String s = null;
        if ("Pixel Coordinates".equals(string2)) {
            s = "PIXEL_COORDS";
            string3 = Integer.toString(this.upperLeftX);
            string4 = Integer.toString(this.upperLeftY);
            string5 = Integer.toString(this.lowerRightX);
            string6 = Integer.toString(this.lowerRightY);
        }
        else if ("Geospatial".equals(string2)) {
            s = "WGS84";
            string3 = this.upperLeftGeoCoors[0];
            string4 = this.upperLeftGeoCoors[1];
            string5 = this.lowerRightGeoCoors[0];
            string6 = this.lowerRightGeoCoors[1];
        }
        final String string7 = this.resolutionList.getSelectedItem().toString();
        final String s2 = "r0";
        final Document document = J2KViewerW3cXmlUtilities.createDocument();
        final Element element = document.createElement("chipOrder");
        document.appendChild(element);
        J2KViewerW3cXmlUtilities.addChildElement(document, element, "credentials", chippingCredentials);
        J2KViewerW3cXmlUtilities.addChildElement(document, element, "resultKey", chippingResultKey);
        J2KViewerW3cXmlUtilities.addChildElement(document, element, "destinationCredentials", destinationCredentials);
        J2KViewerW3cXmlUtilities.addChildElement(document, element, "destinationHost", chippingFTPHost);
        J2KViewerW3cXmlUtilities.addChildElement(document, element, "destinationPath", chippingFTPHostPath);
        J2KViewerW3cXmlUtilities.addChildElement(document, element, "destinationFileName", text);
        J2KViewerW3cXmlUtilities.addChildElement(document, element, "conversion", string);
        J2KViewerW3cXmlUtilities.addChildElement(document, element, "upperLeftX", string3);
        J2KViewerW3cXmlUtilities.addChildElement(document, element, "upperLeftY", string4);
        J2KViewerW3cXmlUtilities.addChildElement(document, element, "lowerRightX", string5);
        J2KViewerW3cXmlUtilities.addChildElement(document, element, "lowerRightY", string6);
        J2KViewerW3cXmlUtilities.addChildElement(document, element, "targetResolution", string7);
        J2KViewerW3cXmlUtilities.addChildElement(document, element, "sourceResolution", s2);
        J2KViewerW3cXmlUtilities.addChildElement(document, element, "sourceCoordinateType", s);
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        }
        catch (TransformerConfigurationException ex) {
            ChipperDialog.log.error(ex.getMessage());
        }
        catch (TransformerFactoryConfigurationError transformerFactoryConfigurationError) {
            ChipperDialog.log.error(transformerFactoryConfigurationError.getMessage());
        }
        transformer.setOutputProperty("indent", "yes");
        final StreamResult streamResult = new StreamResult(new StringWriter());
        final DOMSource domSource = new DOMSource(document);
        try {
            transformer.transform(domSource, streamResult);
        }
        catch (TransformerException ex2) {
            ChipperDialog.log.error(ex2.getMessage());
        }
        final String string8 = streamResult.getWriter().toString();
        final XMLValidator xmlValidator = new XMLValidator();
        try {
            if (xmlValidator.validate(document)) {
                return string8;
            }
        }
        catch (IOException ex3) {
            ChipperDialog.log.error(ex3.getMessage());
            ex3.printStackTrace();
        }
        catch (ParserConfigurationException ex4) {
            ChipperDialog.log.error(ex4.getMessage());
            ex4.printStackTrace();
        }
        return null;
    }
    
    private String getDestinationCredentials() {
        return Base64.encodeBytes((this.viewCentral.getPropertyManager().getChippingFTPHostLogin() + ":" + this.viewCentral.getPropertyManager().getChippingFTPHostPasswd()).getBytes());
    }
    
    private String processImageFilePath() {
        this.imageFilePath = this.imageFilePath.replace('/', '\\');
        this.imageFilePath = this.imageFilePath.replaceAll("\\\\", "\\\\\\\\");
        this.imageFilePath = this.imageryDirectory + this.imageFilePath;
        this.fileNameOnly = this.imageFilePath.substring(this.imageFilePath.lastIndexOf(92) + 1);
        return this.imageFilePath;
    }
    
    private String[] processOptions(final String s) {
        String[] split = null;
        if (s != null) {
            split = s.split(":");
        }
        return split;
    }
    
    private void buildImageFilePath() {
        this.imagePathLabel = new JLabel("Image File:  ");
        this.constraintsLabel.gridx = 0;
        this.constraintsLabel.gridy = 0;
        this.dataPanel.add(this.imagePathLabel, this.constraintsLabel);
        (this.imagePathText = new JTextField(this.fileNameOnly)).setEditable(false);
        this.imagePathText.setColumns(35);
        this.constraintsField.gridx = 1;
        this.constraintsField.gridy = 0;
        this.dataPanel.add(this.imagePathText, this.constraintsField);
    }
    
    private void buildSourceCoordinateType() {
        this.sourceCoordinateTypeLabel = new JLabel("Coordinate Type:  ");
        this.constraintsLabel.gridx = 0;
        this.constraintsLabel.gridy = 1;
        this.dataPanel.add(this.sourceCoordinateTypeLabel, this.constraintsLabel);
        (this.coordinateTypesList = new JComboBox((E[])new String[] { "Pixel Coordinates", "Geospatial" })).setSelectedIndex(0);
        this.coordinateTypesList.setEditable(false);
        this.coordinateTypesList.setEnabled(false);
        this.coordinateTypesList.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ChipperDialog.this.coordinateTypeActionPerformed(actionEvent);
            }
        });
        this.constraintsField.gridx = 1;
        this.constraintsField.gridy = 1;
        this.dataPanel.add(this.coordinateTypesList, this.constraintsField);
    }
    
    private void buildUpperLeftCoordinates() {
        this.upperLeftLabel = new JLabel("Upper Left  (x,y):  ");
        this.constraintsLabel.gridx = 0;
        this.constraintsLabel.gridy = 2;
        this.dataPanel.add(this.upperLeftLabel, this.constraintsLabel);
        (this.upperLeftText = new JTextField(this.upperCoordinates)).setEditable(false);
        this.constraintsField.gridx = 1;
        this.constraintsField.gridy = 2;
        this.dataPanel.add(this.upperLeftText, this.constraintsField);
    }
    
    private void buildLowerRightCoordinates() {
        this.lowerRightLabel = new JLabel("Lower Right (x,y):  ");
        this.constraintsLabel.gridx = 0;
        this.constraintsLabel.gridy = 3;
        this.dataPanel.add(this.lowerRightLabel, this.constraintsLabel);
        (this.lowerRightText = new JTextField(this.lowerCoordinates)).setEditable(false);
        this.constraintsField.gridx = 1;
        this.constraintsField.gridy = 3;
        this.dataPanel.add(this.lowerRightText, this.constraintsField);
    }
    
    private void buildTargetResolution() {
        this.targetResolutionLabel = new JLabel("Target Resolution for Output:  ");
        this.constraintsLabel.gridx = 0;
        this.constraintsLabel.gridy = 4;
        this.dataPanel.add(this.targetResolutionLabel, this.constraintsLabel);
        final int maxDiscardedZoomLevels = this.viewCentral.getPropertyManager().getMaxDiscardedZoomLevels();
        final String[] array = new String[maxDiscardedZoomLevels + 1];
        for (int i = 0; i < maxDiscardedZoomLevels + 1; ++i) {
            array[i] = "1:" + Integer.toString((int)Math.pow(2.0, i));
        }
        (this.resolutionList = new JComboBox(array)).setSelectedIndex(0);
        this.resolutionList.setEditable(false);
        this.resolutionList.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ChipperDialog.this.resolutionLevel = (int)Math.pow(2.0, ChipperDialog.this.resolutionList.getSelectedIndex());
            }
        });
        this.constraintsField.gridx = 1;
        this.constraintsField.gridy = 4;
        this.dataPanel.add(this.resolutionList, this.constraintsField);
    }
    
    private void buildConversionOptions() {
        this.conversionOptionsLabel = new JLabel("Format Options for Output:  ");
        this.constraintsLabel.gridx = 0;
        this.constraintsLabel.gridy = 5;
        this.dataPanel.add(this.conversionOptionsLabel, this.constraintsLabel);
        if (this.viewCentral.getPropertyManager().hasUUID() && !this.isXmlDataPresent()) {
            this.conversionOptionsList = new JComboBox((E[])new String[] { "GeoTIFF" });
            this.fileFormat = "TIFF";
        }
        else {
            this.conversionOptionsList = new JComboBox((E[])new String[] { "NITF", "GeoTIFF" });
        }
        this.conversionOptionsList.setSelectedIndex(0);
        this.conversionOptionsList.setEditable(false);
        this.conversionOptionsList.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final String s = (String)ChipperDialog.this.conversionOptionsList.getSelectedItem();
                if (s.equals("NITF")) {
                    ChipperDialog.this.fileFormat = "NITF";
                }
                else if (s.equals("GeoTIFF")) {
                    ChipperDialog.this.fileFormat = "TIFF";
                }
                ChipperDialog.this.changeOuputExtension();
            }
        });
        this.constraintsField.gridx = 1;
        this.constraintsField.gridy = 5;
        this.dataPanel.add(this.conversionOptionsList, this.constraintsField);
    }
    
    private void buildServiceTypeOptions() {
        this.serviceTypeLabel = new JLabel("Service Type:  ");
        this.constraintsLabel.gridx = 0;
        this.constraintsLabel.gridy = 6;
        this.dataPanel.add(this.serviceTypeLabel, this.constraintsLabel);
        final String chippingServiceType = this.viewCentral.getPropertyManager().getChippingServiceType();
        String[] array;
        if (chippingServiceType.equals("BOTH")) {
            array = new String[] { "CLIENT", "SERVER" };
        }
        else if (chippingServiceType.equals("CLIENT")) {
            array = new String[] { "CLIENT" };
        }
        else {
            array = new String[] { "SERVER" };
        }
        (this.serviceTypeList = new JComboBox(array)).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ChipperDialog.this.serviceTypeChanged();
            }
        });
        this.constraintsField.gridx = 1;
        this.constraintsField.gridy = 6;
        this.dataPanel.add(this.serviceTypeList, this.constraintsField);
    }
    
    private void buildCompressionOptions() {
        this.compressionOptionsLabel = new JLabel("Compression Profile: ");
        this.constraintsLabel.gridx = 0;
        this.constraintsLabel.gridy = 7;
        this.dataPanel.add(this.compressionOptionsLabel, this.constraintsLabel);
        this.compressionOptionsList = new JComboBox((E[])this.compressionOptions);
        this.constraintsField.gridx = 1;
        this.constraintsField.gridy = 7;
        this.dataPanel.add(this.compressionOptionsList, this.constraintsField);
        if ("SERVER".equals(this.serviceTypeList.getSelectedItem())) {
            this.compressionOptionsList.setEnabled(false);
        }
    }
    
    private void buildFTPHostField() {
        this.ftpHostLabel = new JLabel("FTP Host:  ");
        this.constraintsLabel.gridx = 0;
        this.constraintsLabel.gridy = 7;
        this.dataPanel.add(this.ftpHostLabel, this.constraintsLabel);
        (this.ftpHostField = new JTextField(this.viewCentral.getPropertyManager().getChippingFTPHost())).setColumns(35);
        this.ftpHostField.setEditable(true);
        this.constraintsField.gridx = 1;
        this.constraintsField.gridy = 7;
        this.dataPanel.add(this.ftpHostField, this.constraintsField);
    }
    
    private void buildFTPHostPath() {
        this.ftpHostPathLabel = new JLabel("FTP Host Path: ");
        this.constraintsLabel.gridx = 0;
        this.constraintsLabel.gridy = 8;
        this.dataPanel.add(this.ftpHostPathLabel, this.constraintsLabel);
        (this.ftpHostPathField = new JTextField(this.viewCentral.getPropertyManager().getChippingFTPHostPath())).setColumns(35);
        this.ftpHostPathField.setEditable(true);
        this.constraintsField.gridx = 1;
        this.constraintsField.gridy = 8;
        this.dataPanel.add(this.ftpHostPathField, this.constraintsField);
    }
    
    private void buildFTPHostLogin() {
        this.ftpHostLoginLabel = new JLabel("FTP Host User Name: ");
        this.constraintsLabel.gridx = 0;
        this.constraintsLabel.gridy = 9;
        this.dataPanel.add(this.ftpHostLoginLabel, this.constraintsLabel);
        (this.ftpHostLoginField = new JTextField(this.viewCentral.getPropertyManager().getChippingFTPHostLogin())).setColumns(35);
        this.ftpHostLoginField.setEditable(true);
        this.constraintsField.gridx = 1;
        this.constraintsField.gridy = 9;
        this.dataPanel.add(this.ftpHostLoginField, this.constraintsField);
    }
    
    private void buildFTPHostPasswd() {
        this.ftpHostPasswdLabel = new JLabel("FTP Host Password: ");
        this.constraintsLabel.gridx = 0;
        this.constraintsLabel.gridy = 10;
        this.dataPanel.add(this.ftpHostPasswdLabel, this.constraintsLabel);
        (this.ftpHostPasswdField = new JPasswordField(this.viewCentral.getPropertyManager().getChippingFTPHostPasswd())).setEditable(true);
        this.constraintsField.gridx = 1;
        this.constraintsField.gridy = 10;
        this.dataPanel.add(this.ftpHostPasswdField, this.constraintsField);
    }
    
    private void buildOutputFileNameDisplayClient() {
        this.outputFileLabel = new JLabel("Output File:  ");
        this.constraintsLabel.gridx = 0;
        this.constraintsLabel.gridy = 11;
        this.dataPanel.add(this.outputFileLabel, this.constraintsLabel);
        final String replaceAll = this.fileNameOnly.replaceAll("\\.JP2", "").replaceAll("\\.jp2", "").replaceAll("\\.ntf", "").replaceAll("\\.NTF", "");
        String s;
        if (this.fileFormat.equals("NITF")) {
            s = replaceAll + ".ntf";
        }
        else {
            s = replaceAll + ".tif";
        }
        final String property = System.getProperty("user.home");
        if (this.viewCentral.getPropertyManager().getChipperOutputDirectory() == null) {
            this.outputFileField = new JTextField(property + File.separator + s);
        }
        else {
            this.outputFileField = new JTextField(this.viewCentral.getPropertyManager().getChipperOutputDirectory() + File.separator + s);
        }
        this.outputFileField.setEditable(true);
        this.outputFileField.setColumns(35);
        this.constraintsField.gridx = 1;
        this.constraintsField.gridy = 11;
        this.constraintsField.gridwidth = 1;
        this.dataPanel.add(this.outputFileField, this.constraintsField);
        (this.chooserButton = new JButton()).setText("...");
        this.chooserButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ChipperDialog.this.chooserButtonActionPerformed();
            }
        });
        this.constraintsField.gridx = 2;
        this.constraintsField.gridy = 11;
        this.constraintsField.gridwidth = 1;
        if ("SERVER".equals(this.serviceTypeList.getSelectedItem())) {
            this.outputFileField.setEnabled(false);
        }
        this.dataPanel.add(this.chooserButton, this.constraintsField);
    }
    
    private void buildButtonPanel() {
        (this.buttonPanel = new JPanel()).setLayout(new FlowLayout(0));
        this.buttonPanel.setBorder(new EmptyBorder(new Insets(3, 3, 3, 3)));
        this.buttonPanel.setLayout(new FlowLayout(2));
        (this.sendButton = new JButton()).setMnemonic('S');
        this.sendButton.setText("Send");
        this.sendButton.setToolTipText("Close dialog and send chip coordinates");
        this.sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ChipperDialog.this.sendButtonActionPerformed(actionEvent);
            }
        });
        this.buttonPanel.add(this.sendButton);
        (this.cancelButton = new JButton()).setMnemonic('C');
        this.cancelButton.setText("Cancel");
        this.cancelButton.setToolTipText("Close dialog without sending chip coordinates");
        this.cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ChipperDialog.this.cancelButtonActionPerformed(actionEvent);
            }
        });
        this.buttonPanel.add(this.cancelButton);
    }
    
    private void cancelButtonActionPerformed(final ActionEvent actionEvent) {
        this.closeDialog(false);
    }
    
    private void sendButtonActionPerformed(final ActionEvent actionEvent) {
        this.closeDialog(true);
    }
    
    private void coordinateTypeActionPerformed(final ActionEvent actionEvent) {
        final String s = (String)this.coordinateTypesList.getSelectedItem();
        if ("Pixel Coordinates".equals(s)) {
            this.upperLeftText.setText(this.upperCoordinates);
            this.lowerRightText.setText(this.lowerCoordinates);
        }
        else if ("Geospatial".equals(s)) {
            this.upperLeftText.setText(this.upperLeftGeoCoors[1] + ", " + this.upperLeftGeoCoors[0]);
            this.lowerRightText.setText(this.lowerRightGeoCoors[1] + ", " + this.lowerRightGeoCoors[0]);
        }
    }
    
    private void closeDialog(final boolean b) {
        if (b) {
            if ("SERVER".equals(this.serviceTypeList.getSelectedItem())) {
                this.sendRequestToServerChipper();
                this.cleanUp();
            }
            else {
                final int sendDataToClientChipper = this.sendDataToClientChipper();
                if (sendDataToClientChipper == 0 || sendDataToClientChipper == 2) {
                    this.cleanUp();
                }
            }
        }
        else {
            this.cleanUp();
        }
    }
    
    private void cleanUp() {
        try {
            this.viewCentral.getPropertyManager().setTransformationMode(0);
        }
        catch (PropertyVetoException ex) {
            ChipperDialog.log.error("Failed to reset mode to panning!", ex);
        }
        this.viewCentral.getPropertyManager().resetChip();
        this.viewCentral.getMainImagePanel().repaint();
        this.dispose();
    }
    
    private void getChipParameters() {
        final Rectangle totalDimensions = this.viewCentral.getPropertyManager().getTotalDimensions();
        (this.imageFilePath = WebUtils.URLDecode(this.viewCentral.getPropertyManager().getImageURL())).trim();
        this.chipStart = this.viewCentral.getPropertyManager().getChipStart();
        this.chipEnd = this.viewCentral.getPropertyManager().getChipEnd();
        final Rectangle rectangleFromTwoPoints = ImageUtils.getRectangleFromTwoPoints(this.chipStart, this.chipEnd);
        this.upperLeftX = rectangleFromTwoPoints.x;
        this.upperLeftXString = String.valueOf(rectangleFromTwoPoints.x).trim();
        this.upperLeftY = rectangleFromTwoPoints.y;
        this.upperLeftYString = String.valueOf(rectangleFromTwoPoints.y).trim();
        (this.upperCoordinates = this.upperLeftXString + "," + this.upperLeftYString).trim();
        this.lowerRightX = rectangleFromTwoPoints.x + rectangleFromTwoPoints.width;
        if (this.lowerRightX == totalDimensions.width) {
            --this.lowerRightX;
            this.lowerRightXString = String.valueOf(rectangleFromTwoPoints.x + rectangleFromTwoPoints.width - 1).trim();
        }
        else {
            this.lowerRightXString = String.valueOf(rectangleFromTwoPoints.x + rectangleFromTwoPoints.width).trim();
        }
        this.lowerRightY = rectangleFromTwoPoints.y + rectangleFromTwoPoints.height;
        if (this.lowerRightY == totalDimensions.height) {
            --this.lowerRightY;
            this.lowerRightYString = String.valueOf(rectangleFromTwoPoints.y + rectangleFromTwoPoints.height - 1).trim();
        }
        else {
            this.lowerRightYString = String.valueOf(rectangleFromTwoPoints.y + rectangleFromTwoPoints.height).trim();
        }
        (this.lowerCoordinates = this.lowerRightXString + "," + this.lowerRightYString).trim();
        this.upperLeftGeoCoors = this.formatGeoCoordChipperString(this.upperLeftX, this.upperLeftY);
        this.lowerRightGeoCoors = this.formatGeoCoordChipperString(this.lowerRightX, this.lowerRightY);
        this.discardLevel = this.viewCentral.getPropertyManager().getDiscardedZoomLevels();
        (this.targetResolutionString = String.valueOf(this.discardLevel)).trim();
        this.qualityLayer = this.viewCentral.getPropertyManager().getQualityLayers();
        (this.qualityLayerString = String.valueOf(this.qualityLayer)).trim();
    }
    
    private String[] formatGeoCoordChipperString(final int n, final int n2) {
        if (this.nitfGeoUtils != null && this.nitfGeoUtils.isReady()) {
            final double[] bilinearGeoInterpolate = this.nitfGeoUtils.bilinearGeoInterpolate(n, n2);
            final DecimalFormat decimalFormat = new DecimalFormat("00");
            final DecimalFormat decimalFormat2 = new DecimalFormat("000");
            final DecimalFormat decimalFormat3 = new DecimalFormat("00");
            final double abs = Math.abs(bilinearGeoInterpolate[0]);
            final double abs2 = Math.abs(bilinearGeoInterpolate[1]);
            final double n3 = abs2 % 1.0;
            final double n4 = abs % 1.0;
            final double n5 = n3 * 60.0;
            final double n6 = n4 * 60.0;
            return new String[] { "".concat(decimalFormat.format((int)abs2)).concat(decimalFormat3.format((int)n5)).concat(decimalFormat3.format((int)(n5 % 1.0 * 60.0))).concat((bilinearGeoInterpolate[1] >= 0.0) ? "N" : "S"), "".concat(decimalFormat2.format((int)abs)).concat(decimalFormat3.format((int)n6)).concat(decimalFormat3.format((int)(n6 % 1.0 * 60.0))).concat((bilinearGeoInterpolate[0] >= 0.0) ? "E" : "W") };
        }
        return new String[] { "", "" };
    }
    
    private void serviceTypeChanged() {
        if (((String)this.serviceTypeList.getSelectedItem()).equals("CLIENT")) {
            this.compressionOptionsList.setEnabled(true);
            this.outputFileField.setEnabled(true);
            this.resolutionList.setEnabled(true);
            this.validate();
            this.pack();
        }
        else {
            this.compressionOptionsList.setEnabled(false);
            this.outputFileField.setEnabled(false);
            this.resolutionList.setSelectedIndex(0);
            this.resolutionList.setEnabled(false);
        }
    }
    
    private void saveValuesToPropertyManager() {
        this.viewCentral.getPropertyManager().setChippingFTPHost(this.ftpHostField.getText());
        this.viewCentral.getPropertyManager().setChippingFTPHostPath(this.ftpHostPathField.getText());
        this.viewCentral.getPropertyManager().setChippingFTPHostLogin(this.ftpHostLoginField.getText());
        this.viewCentral.getPropertyManager().setChippingFTPHostPasswd(new String(this.ftpHostPasswdField.getPassword()));
    }
    
    private void chooserButtonActionPerformed() {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(this.outputFileField.getText()));
        if (fileChooser.showDialog(this, "Done") == 0) {
            this.outputFileField.setText(fileChooser.getSelectedFile().toString());
        }
    }
    
    private void changeOuputExtension() {
        final String text = this.outputFileField.getText();
        if (this.fileFormat.equals("NITF") && !text.endsWith(".ntf")) {
            this.outputFileField.setText(text.substring(0, text.lastIndexOf(".")) + ".ntf");
        }
        else if (this.fileFormat.equals("TIFF") && !text.endsWith(".tif")) {
            this.outputFileField.setText(text.substring(0, text.lastIndexOf(".")) + ".tif");
        }
    }
    
    private boolean isXmlDataPresent() {
        final List xmlBoxes = this.viewCentral.getXmlPropertiesParser().getXMLBoxes();
        for (int i = 0; i < xmlBoxes.size(); ++i) {
            if (SecurityUtil.checkXMLBox(xmlBoxes.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        ChipperDialog.log = new Log((ChipperDialog.class$com$itt$J2KViewer$gui$ChipperDialog == null) ? (ChipperDialog.class$com$itt$J2KViewer$gui$ChipperDialog = class$("com.itt.J2KViewer.gui.ChipperDialog")) : ChipperDialog.class$com$itt$J2KViewer$gui$ChipperDialog);
    }
    
    class StreamGobbler extends Thread
    {
        InputStream is;
        String type;
        
        StreamGobbler(final InputStream is, final String type) {
            this.is = is;
            this.type = type;
        }
        
        public void run() {
            try {
                String line;
                while ((line = new BufferedReader(new InputStreamReader(this.is)).readLine()) != null) {
                    System.out.println(this.type + ">" + line);
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
