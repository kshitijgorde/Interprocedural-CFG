// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.sql.Timestamp;
import java.io.IOException;
import anon.crypto.JAPCertificate;
import gui.GUIUtils;
import javax.swing.filechooser.FileFilter;
import gui.SimpleFileFilter;
import javax.swing.JFileChooser;
import java.awt.Window;
import java.awt.Component;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.AbstractButton;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import logging.LogHolder;
import logging.LogType;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import java.io.ByteArrayInputStream;
import java.util.zip.ZipEntry;
import java.util.Hashtable;
import java.util.zip.ZipFile;
import java.io.File;
import anon.util.JAPMessages;
import platform.AbstractOS;
import java.net.MalformedURLException;
import java.net.URL;
import gui.dialog.JAPDialog;

public final class JAPUtil
{
    private static final String MSG_DATE_UNIT;
    static /* synthetic */ Class class$jap$JAPUtil;
    
    public static JAPDialog.ILinkedInformation createDialogBrowserLink(final String s) {
        URL url;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            return null;
        }
        return new JAPDialog.LinkedInformationAdapter() {
            public String getMessage() {
                return url.toString();
            }
            
            public int getType() {
                return 2;
            }
            
            public void clicked(final boolean b) {
                AbstractOS.getInstance().openURL(url);
            }
        };
    }
    
    public static String formatEuroCentValue(final long n) {
        final long n2 = n / 100L;
        final long n3 = n - n2 * 100L;
        return new Long(n2).toString() + getCurrencyDelimiter(JAPMessages.getLocale().getLanguage()) + ((n3 < 10L) ? "0" : "") + new Long(n3).toString() + " Euro";
    }
    
    public static String getCurrencyDelimiter(final String s) {
        if (s.equalsIgnoreCase("en")) {
            return new String(".");
        }
        return new String(",");
    }
    
    public static int applyJarDiff(final File file, final File file2, final byte[] array) {
        try {
            final ZipFile zipFile = new ZipFile(file);
            final Hashtable<String, String> hashtable = new Hashtable<String, String>();
            final Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                final ZipEntry zipEntry = (ZipEntry)entries.nextElement();
                hashtable.put(zipEntry.getName(), zipEntry.getName());
            }
            final ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(array));
            final ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file2));
            zipOutputStream.setLevel(9);
            final byte[] array2 = new byte[5000];
            ZipEntry nextEntry;
            while ((nextEntry = zipInputStream.getNextEntry()) != null) {
                final ZipEntry zipEntry2 = new ZipEntry(nextEntry.getName());
                if (!nextEntry.getName().equalsIgnoreCase("META-INF/INDEX.JD")) {
                    LogHolder.log(7, LogType.MISC, "JARDiff: " + nextEntry.getName());
                    hashtable.remove(nextEntry.getName());
                    zipEntry2.setTime(nextEntry.getTime());
                    zipEntry2.setComment(nextEntry.getComment());
                    zipEntry2.setExtra(nextEntry.getExtra());
                    zipEntry2.setMethod(nextEntry.getMethod());
                    if (nextEntry.getSize() != -1L) {
                        zipEntry2.setSize(nextEntry.getSize());
                    }
                    if (nextEntry.getCrc() != -1L) {
                        zipEntry2.setCrc(nextEntry.getCrc());
                    }
                    zipOutputStream.putNextEntry(zipEntry2);
                    int read;
                    while ((read = zipInputStream.read(array2, 0, 5000)) != -1) {
                        zipOutputStream.write(array2, 0, read);
                    }
                    zipOutputStream.closeEntry();
                }
                else {
                    String line;
                    while ((line = new BufferedReader(new InputStreamReader(zipInputStream)).readLine()) != null) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(line);
                        final String nextToken = stringTokenizer.nextToken();
                        if (nextToken.equalsIgnoreCase("remove")) {
                            final String nextToken2 = stringTokenizer.nextToken();
                            LogHolder.log(7, LogType.MISC, "JARDiff: remove " + nextToken2);
                            hashtable.remove(nextToken2);
                        }
                        else if (nextToken.equalsIgnoreCase("move")) {
                            LogHolder.log(7, LogType.MISC, "JARDiff: move " + stringTokenizer.nextToken());
                        }
                        else {
                            LogHolder.log(7, LogType.MISC, "JARDiff: unkown: " + nextToken);
                        }
                    }
                }
                zipInputStream.closeEntry();
            }
            final Enumeration<String> elements = hashtable.elements();
            while (elements.hasMoreElements()) {
                final String s = elements.nextElement();
                LogHolder.log(7, LogType.MISC, s);
                final ZipEntry entry = zipFile.getEntry(s);
                final ZipEntry zipEntry3 = new ZipEntry(entry.getName());
                zipEntry3.setTime(entry.getTime());
                zipEntry3.setComment(entry.getComment());
                zipEntry3.setExtra(entry.getExtra());
                zipEntry3.setMethod(entry.getMethod());
                if (entry.getSize() != -1L) {
                    zipEntry3.setSize(entry.getSize());
                }
                if (entry.getCrc() != -1L) {
                    zipEntry3.setCrc(entry.getCrc());
                }
                zipOutputStream.putNextEntry(zipEntry3);
                LogHolder.log(7, LogType.MISC, "JARDiff: Getting in..");
                final InputStream inputStream = zipFile.getInputStream(entry);
                LogHolder.log(7, LogType.MISC, "JARDiff: Reading..");
                try {
                    int read2;
                    while ((read2 = inputStream.read(array2, 0, 5000)) != -1) {
                        zipOutputStream.write(array2, 0, read2);
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace(System.out);
                }
                inputStream.close();
                zipOutputStream.closeEntry();
            }
            zipOutputStream.finish();
            zipOutputStream.flush();
            zipOutputStream.close();
            zipFile.close();
            zipInputStream.close();
        }
        catch (Throwable t) {
            t.printStackTrace();
            return -1;
        }
        return 0;
    }
    
    public static void setMnemonic(final AbstractButton abstractButton, final String s) {
        if (abstractButton == null || s == null || s.equals("")) {
            return;
        }
        abstractButton.setMnemonic(s.charAt(0));
    }
    
    public static void setPerfectTableSize(final JTable table, final Dimension dimension) {
        final TableModel model = table.getModel();
        int n = 0;
        int min = 0;
        for (int i = 0; i < model.getColumnCount(); ++i) {
            final TableColumn column = table.getColumnModel().getColumn(i);
            final TableCellRenderer headerRenderer = column.getHeaderRenderer();
            int n2 = column.getPreferredWidth();
            int height = 0;
            if (headerRenderer != null) {
                final Component tableCellRendererComponent = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(), false, false, 0, 0);
                n2 = tableCellRendererComponent.getPreferredSize().width;
                height = tableCellRendererComponent.getPreferredSize().height;
            }
            if (model.getRowCount() > 0) {
                final TableCellRenderer defaultRenderer = table.getDefaultRenderer(model.getColumnClass(i));
                int max = 0;
                for (int j = 0; j < model.getRowCount(); ++j) {
                    final Component tableCellRendererComponent2 = defaultRenderer.getTableCellRendererComponent(table, model.getValueAt(j, i), false, false, j, i);
                    max = Math.max(max, tableCellRendererComponent2.getPreferredSize().width);
                    height += tableCellRendererComponent2.getPreferredSize().height;
                }
                final int max2 = Math.max(n2, max);
                column.setPreferredWidth(max2);
                n += max2;
                if (min == 0) {
                    min = height;
                }
                else {
                    min = Math.min(min, height);
                }
            }
        }
        table.setPreferredScrollableViewportSize(new Dimension(Math.min(dimension.width, n + 30), Math.min(dimension.height, min)));
    }
    
    public static JFileChooser showFileDialog(final Window window) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(0);
        final SimpleFileFilter fileFilter;
        fileChooser.addChoosableFileFilter(fileFilter = new SimpleFileFilter());
        if (fileFilter != null) {
            fileChooser.setFileFilter(fileFilter);
        }
        fileChooser.setFileHidingEnabled(false);
        GUIUtils.showMonitoredFileChooser(fileChooser, window);
        return fileChooser;
    }
    
    public static JAPCertificate openCertificate(final Window window) throws IOException {
        final File selectedFile = showFileDialog(window).getSelectedFile();
        JAPCertificate instance = null;
        if (selectedFile != null) {
            instance = JAPCertificate.getInstance(selectedFile);
            if (instance == null) {
                throw new IOException("Could not create certificate!");
            }
        }
        return instance;
    }
    
    public static String formatTimestamp(final Timestamp timestamp, final boolean b) {
        return formatTimestamp(timestamp, b, null);
    }
    
    public static String formatTimestamp(final Timestamp timestamp, final boolean b, final String s) {
        final String country = JAPMessages.getLocale().getCountry();
        if (s.equalsIgnoreCase("en") && country.equals(Locale.US)) {
            SimpleDateFormat simpleDateFormat;
            if (b) {
                simpleDateFormat = new SimpleDateFormat("MM/dd/yy - HH:mm");
            }
            else {
                simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
            }
            return simpleDateFormat.format(timestamp);
        }
        if (s.equalsIgnoreCase("en")) {
            SimpleDateFormat simpleDateFormat2;
            if (b) {
                simpleDateFormat2 = new SimpleDateFormat("dd/MM/yy - HH:mm");
            }
            else {
                simpleDateFormat2 = new SimpleDateFormat("dd/MM/yy");
            }
            return simpleDateFormat2.format(timestamp);
        }
        SimpleDateFormat simpleDateFormat3;
        if (s.equalsIgnoreCase("de")) {
            if (b) {
                simpleDateFormat3 = new SimpleDateFormat("dd.MM.yyyy - HH:mm");
            }
            else {
                simpleDateFormat3 = new SimpleDateFormat("dd.MM.yyyy");
            }
        }
        else if (b) {
            simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        }
        else {
            simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
        }
        return simpleDateFormat3.format(timestamp);
    }
    
    public static Timestamp getEnddate(final int n, final String s) {
        final Calendar instance = Calendar.getInstance();
        if (s.equals("days") || s.equals("day")) {
            instance.add(5, n);
        }
        else if (s.equalsIgnoreCase("weeks") || s.equalsIgnoreCase("week")) {
            instance.add(3, n);
        }
        else if (s.equalsIgnoreCase("months") || s.equalsIgnoreCase("month")) {
            instance.add(2, n);
        }
        else if (s.equalsIgnoreCase("years") || s.equalsIgnoreCase("year")) {
            instance.add(1, n);
        }
        return new Timestamp(instance.getTime().getTime());
    }
    
    public static String getDuration(final int n, final String s) {
        String s2;
        if (s.equals("days") || s.equals("day")) {
            if (n == 1) {
                s2 = "day";
            }
            else {
                s2 = "days";
            }
        }
        else if (s.equalsIgnoreCase("weeks") || s.equalsIgnoreCase("week")) {
            if (n == 1) {
                s2 = "week";
            }
            else {
                s2 = "weeks";
            }
        }
        else if (s.equalsIgnoreCase("months") || s.equalsIgnoreCase("month")) {
            if (n == 1) {
                s2 = "month";
            }
            else {
                s2 = "months";
            }
        }
        else {
            if (!s.equalsIgnoreCase("years") && !s.equalsIgnoreCase("year")) {
                return n + "";
            }
            if (n == 1) {
                s2 = "year";
            }
            else {
                s2 = "years";
            }
        }
        return n + " " + JAPMessages.getString(JAPUtil.MSG_DATE_UNIT + s2);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        MSG_DATE_UNIT = ((JAPUtil.class$jap$JAPUtil == null) ? (JAPUtil.class$jap$JAPUtil = class$("jap.JAPUtil")) : JAPUtil.class$jap$JAPUtil).getName() + "_";
    }
}
