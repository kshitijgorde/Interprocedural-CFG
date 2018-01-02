// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.ui;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import javax.swing.event.DocumentEvent;
import java.awt.Font;
import javax.swing.text.Document;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.border.Border;
import java.awt.Dimension;
import prefuse.data.Tuple;
import prefuse.data.event.TupleSetListener;
import prefuse.Visualization;
import prefuse.data.search.PrefixSearchTupleSet;
import prefuse.util.ColorLib;
import prefuse.data.tuple.TupleSet;
import java.awt.Color;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import prefuse.data.search.SearchTupleSet;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentListener;
import javax.swing.JPanel;

public class JSearchPanel extends JPanel implements DocumentListener, ActionListener
{
    private Object m_lock;
    private SearchTupleSet m_searcher;
    private JTextField m_queryF;
    private JLabel m_resultL;
    private JLabel m_searchL;
    private Box m_sbox;
    private String[] m_fields;
    private Color m_cancelColor;
    private boolean m_includeHitCount;
    private boolean m_monitorKeys;
    private boolean m_autoIndex;
    private boolean m_showBorder;
    private boolean m_showCancel;
    
    public JSearchPanel(final SearchTupleSet set, final String s) {
        this(set, s, false);
    }
    
    public JSearchPanel(final SearchTupleSet set, final String s, final boolean b) {
        this(null, set, new String[] { s }, false, b);
    }
    
    public JSearchPanel(final TupleSet set, final SearchTupleSet set2, final String[] fields, final boolean autoIndex, final boolean monitorKeys) {
        this.m_queryF = new JTextField(15);
        this.m_resultL = new JLabel("          ");
        this.m_searchL = new JLabel("search >> ");
        this.m_sbox = new Box(0);
        this.m_cancelColor = ColorLib.getColor(255, 75, 75);
        this.m_includeHitCount = false;
        this.m_monitorKeys = false;
        this.m_autoIndex = true;
        this.m_showBorder = true;
        this.m_showCancel = true;
        this.m_lock = new Object();
        this.m_fields = fields;
        this.m_autoIndex = autoIndex;
        this.m_monitorKeys = monitorKeys;
        this.m_searcher = ((set2 != null) ? set2 : new PrefixSearchTupleSet());
        this.init(set);
    }
    
    public JSearchPanel(final Visualization visualization, final String s) {
        this(visualization, Visualization.ALL_ITEMS, s, true);
    }
    
    public JSearchPanel(final Visualization visualization, final String s, final String s2) {
        this(visualization, s, s2, true);
    }
    
    public JSearchPanel(final Visualization visualization, final String s, final String s2, final boolean b) {
        this(visualization, s, Visualization.SEARCH_ITEMS, new String[] { s2 }, b, false);
    }
    
    public JSearchPanel(final Visualization visualization, final String s, final String s2, final boolean b, final boolean b2) {
        this(visualization, s, Visualization.SEARCH_ITEMS, new String[] { s2 }, b, true);
    }
    
    public JSearchPanel(final Visualization visualization, final String s, final String s2, final String s3, final boolean b, final boolean b2) {
        this(visualization, s, s2, new String[] { s3 }, b, b2);
    }
    
    public JSearchPanel(final Visualization lock, final String s, final String s2, final String[] fields, final boolean autoIndex, final boolean monitorKeys) {
        this.m_queryF = new JTextField(15);
        this.m_resultL = new JLabel("          ");
        this.m_searchL = new JLabel("search >> ");
        this.m_sbox = new Box(0);
        this.m_cancelColor = ColorLib.getColor(255, 75, 75);
        this.m_includeHitCount = false;
        this.m_monitorKeys = false;
        this.m_autoIndex = true;
        this.m_showBorder = true;
        this.m_showCancel = true;
        this.m_lock = lock;
        this.m_fields = fields;
        this.m_autoIndex = autoIndex;
        this.m_monitorKeys = monitorKeys;
        final TupleSet group = lock.getGroup(s2);
        if (group != null) {
            if (!(group instanceof SearchTupleSet)) {
                throw new IllegalStateException("Search focus set not instance of SearchTupleSet!");
            }
            this.m_searcher = (SearchTupleSet)group;
        }
        else {
            lock.addFocusGroup(s2, this.m_searcher = new PrefixSearchTupleSet());
        }
        this.init(lock.getGroup(s));
    }
    
    private void init(final TupleSet set) {
        if (this.m_autoIndex && set != null) {
            for (int i = 0; i < this.m_fields.length; ++i) {
                this.m_searcher.index(set.tuples(), this.m_fields[i]);
            }
            set.addTupleSetListener(new TupleSetListener() {
                public void tupleSetChanged(final TupleSet set, final Tuple[] array, final Tuple[] array2) {
                    if (array != null) {
                        for (int i = 0; i < array.length; ++i) {
                            for (int j = 0; j < JSearchPanel.this.m_fields.length; ++j) {
                                JSearchPanel.this.m_searcher.index(array[i], JSearchPanel.this.m_fields[j]);
                            }
                        }
                    }
                    if (array2 != null && JSearchPanel.this.m_searcher.isUnindexSupported()) {
                        for (int k = 0; k < array2.length; ++k) {
                            for (int l = 0; l < JSearchPanel.this.m_fields.length; ++l) {
                                JSearchPanel.this.m_searcher.unindex(array2[k], JSearchPanel.this.m_fields[l]);
                            }
                        }
                    }
                }
            });
        }
        this.m_queryF.addActionListener(this);
        if (this.m_monitorKeys) {
            this.m_queryF.getDocument().addDocumentListener(this);
        }
        this.m_queryF.setMaximumSize(new Dimension(400, 100));
        this.m_queryF.setPreferredSize(new Dimension(200, 20));
        this.m_queryF.setBorder(null);
        this.setBackground(Color.WHITE);
        this.initUI();
    }
    
    private void initUI() {
        this.removeAll();
        this.setLayout(new BoxLayout(this, 0));
        this.m_sbox.removeAll();
        this.m_sbox.add(Box.createHorizontalStrut(3));
        this.m_sbox.add(this.m_queryF);
        this.m_sbox.add(Box.createHorizontalStrut(3));
        if (this.m_showCancel) {
            this.m_sbox.add(new CancelButton());
            this.m_sbox.add(Box.createHorizontalStrut(3));
        }
        if (this.m_showBorder) {
            this.m_sbox.setBorder(BorderFactory.createLineBorder(this.getForeground()));
        }
        else {
            this.m_sbox.setBorder(null);
        }
        this.m_sbox.setMaximumSize(new Dimension(400, 100));
        this.m_sbox.setPreferredSize(new Dimension(171, 20));
        final Box box = new Box(0);
        if (this.m_includeHitCount) {
            box.add(this.m_resultL);
            box.add(Box.createHorizontalStrut(10));
        }
        box.add(this.m_searchL);
        box.add(Box.createHorizontalStrut(3));
        box.add(this.m_sbox);
        this.add(box);
    }
    
    public void requestFocus() {
        this.m_queryF.requestFocus();
    }
    
    public void setLock(final Object lock) {
        this.m_lock = lock;
    }
    
    public void setShowResultCount(final boolean includeHitCount) {
        this.m_includeHitCount = includeHitCount;
        this.initUI();
        this.validate();
    }
    
    public void setShowBorder(final boolean showBorder) {
        this.m_showBorder = showBorder;
        this.initUI();
        this.validate();
    }
    
    public void setShowCancel(final boolean showCancel) {
        this.m_showCancel = showCancel;
        this.initUI();
        this.validate();
    }
    
    protected void searchUpdate() {
        final String text = this.m_queryF.getText();
        synchronized (this.m_lock) {
            this.m_searcher.search(text);
            if (this.m_searcher.getQuery().length() == 0) {
                this.m_resultL.setText(null);
            }
            else {
                final int tupleCount = this.m_searcher.getTupleCount();
                this.m_resultL.setText(tupleCount + " match" + ((tupleCount == 1) ? "" : "es"));
            }
        }
    }
    
    public void setQuery(final String text) {
        final Document document = this.m_queryF.getDocument();
        document.removeDocumentListener(this);
        this.m_queryF.setText(text);
        if (this.m_monitorKeys) {
            document.addDocumentListener(this);
        }
        this.searchUpdate();
    }
    
    public String getQuery() {
        return this.m_queryF.getText();
    }
    
    public void setCancelColor(final Color cancelColor) {
        this.m_cancelColor = cancelColor;
    }
    
    public void setBackground(final Color color) {
        super.setBackground(color);
        if (this.m_queryF != null) {
            this.m_queryF.setBackground(color);
        }
        if (this.m_resultL != null) {
            this.m_resultL.setBackground(color);
        }
        if (this.m_searchL != null) {
            this.m_searchL.setBackground(color);
        }
    }
    
    public void setForeground(final Color foreground) {
        super.setForeground(foreground);
        if (this.m_queryF != null) {
            this.m_queryF.setForeground(foreground);
            this.m_queryF.setCaretColor(foreground);
        }
        if (this.m_resultL != null) {
            this.m_resultL.setForeground(foreground);
        }
        if (this.m_searchL != null) {
            this.m_searchL.setForeground(foreground);
        }
        if (this.m_sbox != null && this.m_showBorder) {
            this.m_sbox.setBorder(BorderFactory.createLineBorder(foreground));
        }
    }
    
    public void setOpaque(final boolean b) {
        super.setOpaque(b);
        if (this.m_queryF != null) {
            this.m_queryF.setOpaque(b);
        }
        if (this.m_resultL != null) {
            this.m_resultL.setOpaque(b);
        }
        if (this.m_searchL != null) {
            this.m_searchL.setOpaque(b);
        }
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        if (this.m_queryF != null) {
            this.m_queryF.setFont(font);
        }
        if (this.m_resultL != null) {
            this.m_resultL.setFont(font);
        }
        if (this.m_searchL != null) {
            this.m_searchL.setFont(font);
        }
    }
    
    public void setLabelText(final String text) {
        this.m_searchL.setText(text);
    }
    
    public void changedUpdate(final DocumentEvent documentEvent) {
        this.searchUpdate();
    }
    
    public void insertUpdate(final DocumentEvent documentEvent) {
        this.searchUpdate();
    }
    
    public void removeUpdate(final DocumentEvent documentEvent) {
        this.searchUpdate();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.m_queryF) {
            this.searchUpdate();
        }
    }
    
    public class CancelButton extends JComponent implements MouseListener
    {
        private boolean hover;
        private int[] outline;
        private int[] fill;
        
        public CancelButton() {
            this.hover = false;
            this.outline = new int[] { 0, 0, 2, 0, 4, 2, 5, 2, 7, 0, 9, 0, 9, 2, 7, 4, 7, 5, 9, 7, 9, 9, 7, 9, 5, 7, 4, 7, 2, 9, 0, 9, 0, 7, 2, 5, 2, 4, 0, 2, 0, 0 };
            this.fill = new int[] { 1, 1, 8, 8, 1, 2, 7, 8, 2, 1, 8, 7, 7, 1, 1, 7, 8, 2, 2, 8, 1, 8, 8, 1 };
            final Dimension maximumSize = new Dimension(10, 10);
            this.setPreferredSize(maximumSize);
            this.setMinimumSize(maximumSize);
            this.setMaximumSize(maximumSize);
            this.setFocusable(false);
            this.addMouseListener(this);
        }
        
        public void paintComponent(final Graphics graphics) {
            if (this.hover) {
                graphics.setColor(JSearchPanel.this.m_cancelColor);
                for (int n = 0; n + 3 < this.fill.length; n += 4) {
                    graphics.drawLine(this.fill[n], this.fill[n + 1], this.fill[n + 2], this.fill[n + 3]);
                }
            }
            graphics.setColor(JSearchPanel.this.getForeground());
            for (int n2 = 0; n2 + 3 < this.outline.length; n2 += 2) {
                graphics.drawLine(this.outline[n2], this.outline[n2 + 1], this.outline[n2 + 2], this.outline[n2 + 3]);
            }
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
            JSearchPanel.this.setQuery(null);
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            this.hover = true;
            this.repaint();
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            this.hover = false;
            this.repaint();
        }
    }
}
