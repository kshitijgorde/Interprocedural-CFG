// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.io;

import prefuse.data.io.GraphReader;
import prefuse.data.io.GraphMLReader;
import prefuse.data.io.TreeMLReader;
import prefuse.data.Graph;
import prefuse.util.StringLib;
import java.util.logging.Logger;
import prefuse.data.io.TableReader;
import prefuse.data.io.DelimitedTextTableReader;
import javax.swing.filechooser.FileFilter;
import prefuse.data.io.CSVTableReader;
import javax.swing.JFileChooser;
import prefuse.data.Table;
import java.awt.Component;
import prefuse.util.collections.ByteArrayList;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.net.URL;

public class IOLib
{
    public static boolean isUrlString(final String s) {
        return s.startsWith("http:/") || s.startsWith("ftp:/") || s.startsWith("file:/");
    }
    
    public static URL urlFromString(final String s) {
        return urlFromString(s, null, true);
    }
    
    public static URL urlFromString(final String s, final Class clazz, final boolean b) {
        URL url = null;
        if (isUrlString(s)) {
            try {
                url = new URL(s);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            if (clazz != null) {
                url = clazz.getResource(s);
            }
            else {
                url = IOLib.class.getResource(s);
            }
            if (url == null && !s.startsWith("/")) {
                url = IOLib.class.getResource("/" + s);
            }
            if (b && url == null && new File(s).exists()) {
                try {
                    url = new URL("file:///" + s);
                }
                catch (Exception ex2) {}
            }
        }
        return url;
    }
    
    public static InputStream streamFromString(final String s) throws IOException {
        InputStream openStream = null;
        final URL urlFromString = urlFromString(s, null, false);
        if (urlFromString != null) {
            openStream = urlFromString.openStream();
        }
        else {
            final File file = new File(s);
            if (file.exists()) {
                openStream = new FileInputStream(file);
            }
        }
        if (openStream == null) {
            return null;
        }
        if (isGZipFile(s)) {
            return new GZIPInputStream(openStream);
        }
        return openStream;
    }
    
    public static String getExtension(final File file) {
        return (file != null) ? getExtension(file.getName()) : null;
    }
    
    public static boolean isGZipFile(final String s) {
        final String extension = getExtension(s);
        return "gz".equals(extension) || "z".equals(extension);
    }
    
    public static boolean isZipFile(final String s) {
        return "zip".equals(getExtension(s));
    }
    
    public static String getExtension(final String s) {
        final int lastIndex = s.lastIndexOf(46);
        if (lastIndex > 0 && lastIndex < s.length() - 1) {
            return s.substring(lastIndex + 1).toLowerCase();
        }
        return null;
    }
    
    public static ByteArrayList readAsBytes(final InputStream inputStream) throws IOException {
        final ByteArrayList list = new ByteArrayList();
        final byte[] array = new byte[8192];
        int read;
        while ((read = inputStream.read(array)) >= 0) {
            list.add(array, 0, read);
        }
        return list;
    }
    
    public static String readAsString(final InputStream inputStream) throws IOException {
        final StringBuffer sb = new StringBuffer();
        final byte[] array = new byte[8192];
        int read;
        while ((read = inputStream.read(array)) >= 0) {
            sb.append(new String(array, 0, read));
        }
        return sb.toString();
    }
    
    public static String readAsString(final String s) throws IOException {
        return readAsString(streamFromString(s));
    }
    
    public static Table getTableFile(final Component component) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogType(0);
        fileChooser.setDialogTitle("Open Table File");
        fileChooser.setAcceptAllFileFilterUsed(false);
        final SimpleFileFilter fileFilter = new SimpleFileFilter("csv", "Comma Separated Values (CSV) File (*.csv)", new CSVTableReader());
        fileFilter.addExtension("gz");
        fileChooser.setFileFilter(fileFilter);
        final SimpleFileFilter fileFilter2 = new SimpleFileFilter("txt", "Pipe-Delimited Text File (*.txt)", new DelimitedTextTableReader("|"));
        fileFilter2.addExtension("gz");
        fileChooser.setFileFilter(fileFilter2);
        final SimpleFileFilter fileFilter3 = new SimpleFileFilter("txt", "Tab-Delimited Text File (*.txt)", new DelimitedTextTableReader());
        fileFilter3.addExtension("gz");
        fileChooser.setFileFilter(fileFilter3);
        if (fileChooser.showOpenDialog(component) != 0) {
            return null;
        }
        final File selectedFile = fileChooser.getSelectedFile();
        final TableReader tableReader = (TableReader)((SimpleFileFilter)fileChooser.getFileFilter()).getUserData();
        try {
            return tableReader.readTable(streamFromString(selectedFile.getAbsolutePath()));
        }
        catch (Exception ex) {
            Logger.getLogger(IOLib.class.getName()).warning(ex.getMessage() + "\n" + StringLib.getStackTrace(ex));
            return null;
        }
    }
    
    public static Graph getGraphFile(final Component component) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogType(0);
        fileChooser.setDialogTitle("Open Graph or Tree File");
        fileChooser.setAcceptAllFileFilterUsed(false);
        final SimpleFileFilter fileFilter = new SimpleFileFilter("xml", "TreeML File (*.xml, *.treeml)", new TreeMLReader());
        fileFilter.addExtension("treeml");
        fileFilter.addExtension("gz");
        fileChooser.setFileFilter(fileFilter);
        final SimpleFileFilter fileFilter2 = new SimpleFileFilter("xml", "GraphML File (*.xml, *.graphml)", new GraphMLReader());
        fileFilter2.addExtension("graphml");
        fileFilter2.addExtension("gz");
        fileChooser.setFileFilter(fileFilter2);
        if (fileChooser.showOpenDialog(component) != 0) {
            return null;
        }
        final File selectedFile = fileChooser.getSelectedFile();
        final GraphReader graphReader = (GraphReader)((SimpleFileFilter)fileChooser.getFileFilter()).getUserData();
        try {
            return graphReader.readGraph(streamFromString(selectedFile.getAbsolutePath()));
        }
        catch (Exception ex) {
            Logger.getLogger(IOLib.class.getName()).warning(ex.getMessage() + "\n" + StringLib.getStackTrace(ex));
            return null;
        }
    }
}
