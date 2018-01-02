// 
// Decompiled by Procyon v0.5.30
// 

package imaging.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.filechooser.FileFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.io.File;
import java.beans.PropertyChangeEvent;
import java.awt.Dimension;
import javax.swing.JFileChooser;
import java.beans.PropertyChangeListener;
import javax.swing.JLabel;

class Filters extends JLabel implements PropertyChangeListener
{
    private static final int ICON_WIDTH = 100;
    private static final int ICON_HEIGHT = 100;
    private static String fileName;
    private static String sTitle;
    private static String startDir;
    
    static {
        Filters.fileName = "";
        Filters.sTitle = "";
        Filters.startDir = ".";
    }
    
    public static void setTitle(final String title) {
        Filters.sTitle = title;
    }
    
    public static void setDir(final String directory) {
        Filters.startDir = directory;
    }
    
    public Filters(final JFileChooser chooser) {
        this.setVerticalAlignment(0);
        this.setHorizontalAlignment(0);
        chooser.addPropertyChangeListener(this);
        this.setPreferredSize(new Dimension(100, 100));
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent changeEvent) {
        final String changeName = changeEvent.getPropertyName();
        if (changeName.equals("SelectedFileChangedProperty")) {
            final File file = (File)changeEvent.getNewValue();
            if (file != null) {
                ImageIcon icon = new ImageIcon(file.getPath());
                if (icon.getIconWidth() > 100) {
                    icon = new ImageIcon(icon.getImage().getScaledInstance(100, -1, 1));
                    if (icon.getIconHeight() > 100) {
                        icon = new ImageIcon(icon.getImage().getScaledInstance(-1, 100, 1));
                    }
                }
                this.setIcon(icon);
            }
        }
    }
    
    public static String Start() {
        final JFileChooser fileChooser = new JFileChooser(Filters.startDir);
        final LabelAccessory accessory = new LabelAccessory(fileChooser);
        fileChooser.setDialogTitle(Filters.sTitle);
        final ExampleFileFilter filter = new ExampleFileFilter("jpg");
        filter.addExtension("gif");
        filter.setDescription("Jpeg & Gif Files");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setAccessory(accessory);
        fileChooser.addPropertyChangeListener("SelectedFileChangedProperty", accessory);
        final int status = fileChooser.showOpenDialog(null);
        if (status == 0) {
            final File selectedFile = fileChooser.getSelectedFile();
            Filters.fileName = String.valueOf(selectedFile.getParent()) + File.separator + selectedFile.getName();
            System.out.println("filename= " + Filters.fileName);
        }
        return Filters.fileName;
    }
    
    public static class LabelAccessory extends JLabel implements PropertyChangeListener
    {
        private static final int ICON_WIDTH = 100;
        private static final int ICON_HEIGHT = 100;
        
        public LabelAccessory(final JFileChooser chooser) {
            this.setVerticalAlignment(0);
            this.setHorizontalAlignment(0);
            chooser.addPropertyChangeListener(this);
            this.setPreferredSize(new Dimension(100, 100));
        }
        
        @Override
        public void propertyChange(final PropertyChangeEvent changeEvent) {
            final String changeName = changeEvent.getPropertyName();
            if (changeName.equals("SelectedFileChangedProperty")) {
                final File file = (File)changeEvent.getNewValue();
                if (file != null) {
                    ImageIcon icon = new ImageIcon(file.getPath());
                    if (icon.getIconWidth() > 100) {
                        icon = new ImageIcon(icon.getImage().getScaledInstance(100, -1, 1));
                        if (icon.getIconHeight() > 100) {
                            icon = new ImageIcon(icon.getImage().getScaledInstance(-1, 100, 1));
                        }
                    }
                    this.setIcon(icon);
                }
            }
        }
    }
    
    public static class ExampleFileFilter extends FileFilter
    {
        private String TYPE_UNKNOWN;
        private String HIDDEN_FILE;
        private Hashtable<String, ExampleFileFilter> filters;
        private String description;
        private String fullDescription;
        private boolean useExtensionsInDescription;
        
        public ExampleFileFilter() {
            this.TYPE_UNKNOWN = "Type Unknown";
            this.HIDDEN_FILE = "Hidden File";
            this.filters = null;
            this.description = null;
            this.fullDescription = null;
            this.useExtensionsInDescription = true;
            this.filters = new Hashtable<String, ExampleFileFilter>();
        }
        
        public ExampleFileFilter(final String extension) {
            this(extension, null);
        }
        
        public ExampleFileFilter(final String extension, final String description) {
            this();
            if (extension != null) {
                this.addExtension(extension);
            }
            if (description != null) {
                this.setDescription(description);
            }
        }
        
        public ExampleFileFilter(final String[] filters) {
            this(filters, null);
        }
        
        public ExampleFileFilter(final String[] filters, final String description) {
            this();
            for (int i = 0; i < filters.length; ++i) {
                this.addExtension(filters[i]);
            }
            if (description != null) {
                this.setDescription(description);
            }
        }
        
        @Override
        public boolean accept(final File f) {
            if (f != null) {
                if (f.isDirectory()) {
                    return true;
                }
                final String extension = this.getExtension(f);
                if (extension != null && this.filters.get(this.getExtension(f)) != null) {
                    return true;
                }
            }
            return false;
        }
        
        public String getExtension(final File f) {
            if (f != null) {
                final String filename = f.getName();
                final int i = filename.lastIndexOf(46);
                if (i > 0 && i < filename.length() - 1) {
                    return filename.substring(i + 1).toLowerCase();
                }
            }
            return null;
        }
        
        public void addExtension(final String extension) {
            if (this.filters == null) {
                this.filters = new Hashtable<String, ExampleFileFilter>(5);
            }
            this.filters.put(extension.toLowerCase(), this);
            this.fullDescription = null;
        }
        
        @Override
        public String getDescription() {
            if (this.fullDescription == null) {
                if (this.description == null || this.isExtensionListInDescription()) {
                    this.fullDescription = ((this.description == null) ? "(" : (String.valueOf(this.description) + " ("));
                    final Enumeration<String> extensions = this.filters.keys();
                    if (extensions != null) {
                        this.fullDescription = String.valueOf(this.fullDescription) + "." + extensions.nextElement();
                        while (extensions.hasMoreElements()) {
                            this.fullDescription = String.valueOf(this.fullDescription) + ", " + extensions.nextElement();
                        }
                    }
                    this.fullDescription = String.valueOf(this.fullDescription) + ")";
                }
                else {
                    this.fullDescription = this.description;
                }
            }
            return this.fullDescription;
        }
        
        public void setDescription(final String description) {
            this.description = description;
            this.fullDescription = null;
        }
        
        public void setExtensionListInDescription(final boolean b) {
            this.useExtensionsInDescription = b;
            this.fullDescription = null;
        }
        
        public boolean isExtensionListInDescription() {
            return this.useExtensionsInDescription;
        }
    }
}
