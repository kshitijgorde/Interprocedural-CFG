// 
// Decompiled by Procyon v0.5.30
// 

package hanzilookup;

import java.util.EventObject;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.Action;
import javax.swing.AbstractAction;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import javax.swing.DefaultListCellRenderer;
import kiang.swing.JClickableList;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import hanzilookup.data.CharacterDescriptor;
import hanzilookup.ui.WrittenCharacter;
import hanzilookup.data.StrokesMatcher;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import java.util.prefs.Preferences;
import java.util.Iterator;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.LinkedHashSet;
import hanzilookup.data.ResourceStrokesStreamProvider;
import java.io.IOException;
import hanzilookup.data.MemoryStrokesStreamProvider;
import hanzilookup.data.StrokesParser;
import java.awt.Font;
import java.io.InputStream;
import java.awt.Dimension;
import java.util.Set;
import hanzilookup.data.MatcherThread;
import hanzilookup.data.StrokesDataSource;
import javax.swing.JList;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import hanzilookup.ui.CharacterCanvas;
import javax.swing.JPanel;

public class HanziLookup extends JPanel
{
    public static final String SEARCH_TYPE_PREF_KEY = "search_type";
    public static final String LOOSENESS_PREF_KEY = "looseness";
    public static final String AUTOLOOKUP_PREF_KEY = "autolookup";
    public static final String MATCH_COUNT_PREF_KEY = "match_count";
    public static final String LOOKUP_MACRO_PREF_CODE_KEY = "lookup_macro_code";
    public static final String LOOKUP_MACRO_PREF_MODIFIERS_KEY = "lookup_macro_modifiers";
    public static final String CLEAR_MACRO_PREF_CODE_KEY = "clear_macro_code";
    public static final String CLEAR_MACRO_PREF_MODIFIERS_KEY = "clear_macro_modifiers";
    public static final String UNDO_MACRO_PREF_KEY = "undo_macro_code";
    public static final String UNDO_MACRO_PREF_MODIFIERS = "undo_macro_modifiers";
    private int searchType;
    private CharacterCanvas inputCanvas;
    private JButton lookupButton;
    private JButton clearButton;
    private JButton undoButton;
    private KeyStroke lookupMacro;
    private KeyStroke clearMacro;
    private KeyStroke undoMacro;
    private JList candidatesList;
    private StrokesDataSource strokesDataSource;
    private boolean autoLookup;
    private double looseness;
    private int numResults;
    private MatcherThread matcherThread;
    private Set characterHandlers;
    private static final Dimension CANVAS_DIMENSION;
    private static final Dimension SELECTION_DIMENSION;
    
    static {
        CANVAS_DIMENSION = new Dimension(235, 235);
        SELECTION_DIMENSION = new Dimension(275, 130);
    }
    
    public HanziLookup(final InputStream strokesIn, final InputStream typesIn, final Font font) throws IOException {
        this(new StrokesDataSource(new MemoryStrokesStreamProvider(StrokesParser.getStrokeBytes(strokesIn, typesIn))), font);
    }
    
    public HanziLookup(final InputStream compiledData, final Font font) throws IOException {
        this(new StrokesDataSource(new MemoryStrokesStreamProvider(compiledData)), font);
    }
    
    public HanziLookup(final String compiledDataResourcePath, final Font font) throws IOException {
        this(new StrokesDataSource(new ResourceStrokesStreamProvider(compiledDataResourcePath)), font);
    }
    
    public HanziLookup(final StrokesDataSource strokesDataSource, final Font font) {
        this.autoLookup = true;
        this.looseness = 0.25;
        this.numResults = 15;
        this.characterHandlers = new LinkedHashSet();
        this.strokesDataSource = strokesDataSource;
        this.initMatcherThread();
        this.initUI(font);
    }
    
    private void initMatcherThread() {
        (this.matcherThread = new MatcherThread()).addResultsHandler(new MatcherThread.ResultsHandler() {
            public void handleResults(final Character[] results) {
                HanziLookup.this.handleResults(results);
            }
        });
        this.matcherThread.setDaemon(true);
        this.matcherThread.setPriority(5);
        this.matcherThread.start();
    }
    
    private void initUI(final Font font) {
        final JPanel inputPanel = this.buildInputPanel();
        final JPanel selectPanel = this.buildSelectionPanel();
        this.setFont(font);
        this.setLayout(new BorderLayout());
        this.add(inputPanel, "North");
        this.add(selectPanel, "Center");
        final KeyStroke lookupMacro = KeyStroke.getKeyStroke(76, 8);
        final KeyStroke clearMacro = KeyStroke.getKeyStroke(67, 8);
        final KeyStroke undoMacro = KeyStroke.getKeyStroke(90, 2);
        this.registerLookupMacro(lookupMacro);
        this.registerClearMacro(clearMacro);
        this.registerUndoMacro(undoMacro);
    }
    
    public void addCharacterReceiver(final CharacterSelectionListener listener) {
        synchronized (this.characterHandlers) {
            this.characterHandlers.add(listener);
        }
        // monitorexit(this.characterHandlers)
    }
    
    public void removeCharacterReceiver(final CharacterSelectionListener listener) {
        synchronized (this.characterHandlers) {
            this.characterHandlers.remove(listener);
        }
        // monitorexit(this.characterHandlers)
    }
    
    private void notifyReceivers(final char result) {
        synchronized (this.characterHandlers) {
            for (final CharacterSelectionListener listener : this.characterHandlers) {
                listener.characterSelected(new CharacterSelectionEvent(this, result, null));
            }
        }
        // monitorexit(this.characterHandlers)
    }
    
    public void loadOptionsFromPreferences(final Preferences prefs) {
        this.searchType = prefs.getInt("search_type", this.searchType);
        this.looseness = prefs.getDouble("looseness", this.looseness);
        this.autoLookup = prefs.getBoolean("autolookup", this.autoLookup);
        this.numResults = prefs.getInt("match_count", this.numResults);
        final int lookupMacroCode = prefs.getInt("lookup_macro_code", this.lookupMacro.getKeyCode());
        final int lookupMacroModifiers = prefs.getInt("lookup_macro_modifiers", this.lookupMacro.getModifiers());
        this.registerLookupMacro(KeyStroke.getKeyStroke(lookupMacroCode, lookupMacroModifiers));
        final int clearMacroCode = prefs.getInt("clear_macro_code", this.clearMacro.getKeyCode());
        final int clearMacroModifiers = prefs.getInt("clear_macro_modifiers", this.clearMacro.getModifiers());
        this.registerClearMacro(KeyStroke.getKeyStroke(clearMacroCode, clearMacroModifiers));
        final int undoMacroCode = prefs.getInt("undo_macro_code", this.undoMacro.getKeyCode());
        final int undoMacroModifiers = prefs.getInt("undo_macro_modifiers", this.undoMacro.getModifiers());
        this.registerUndoMacro(KeyStroke.getKeyStroke(undoMacroCode, undoMacroModifiers));
    }
    
    public void writeOptionsToPreferences(final Preferences prefs) {
        prefs.putInt("search_type", this.searchType);
        prefs.putDouble("looseness", this.looseness);
        prefs.putBoolean("autolookup", this.autoLookup);
        prefs.putInt("match_count", this.numResults);
        prefs.putInt("lookup_macro_code", this.lookupMacro.getKeyCode());
        prefs.putInt("lookup_macro_modifiers", this.lookupMacro.getModifiers());
        prefs.putInt("clear_macro_code", this.clearMacro.getKeyCode());
        prefs.putInt("clear_macro_modifiers", this.clearMacro.getModifiers());
        prefs.putInt("undo_macro_code", this.undoMacro.getKeyCode());
        prefs.putInt("undo_macro_modifiers", this.undoMacro.getModifiers());
    }
    
    void runLookup() {
        synchronized (this.candidatesList) {
            this.candidatesList.setModel(new DefaultListModel());
        }
        // monitorexit(this.candidatesList)
        final WrittenCharacter writtenCharacter = this.inputCanvas.getCharacter();
        if (writtenCharacter.getStrokeList().size() == 0) {
            this.handleResults(new Character[0]);
            return;
        }
        final CharacterDescriptor inputDescriptor = writtenCharacter.buildCharacterDescriptor();
        final boolean searchTraditional = this.searchType == 0 || this.searchType == 2;
        final boolean searchSimplified = this.searchType == 0 || this.searchType == 1;
        final StrokesMatcher matcher = new StrokesMatcher(inputDescriptor, searchTraditional, searchSimplified, this.looseness, this.numResults, this.strokesDataSource);
        this.matcherThread.setStrokesMatcher(matcher);
    }
    
    private void handleResults(final Character[] results) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HanziLookup.this.candidatesList.setListData(results);
                HanziLookup.this.lookupButton.setEnabled(true);
            }
        });
    }
    
    private void clear() {
        this.inputCanvas.clear();
        this.inputCanvas.repaint();
        synchronized (this.candidatesList) {
            this.candidatesList.setListData(new Object[0]);
            this.candidatesList.repaint();
        }
        // monitorexit(this.candidatesList)
    }
    
    private void undo() {
        this.inputCanvas.undo();
        if (this.autoLookup && this.inputCanvas.getCharacter().getStrokeList().size() > 0) {
            this.runLookup();
        }
        else {
            this.handleResults(new Character[0]);
        }
        this.inputCanvas.repaint();
    }
    
    private void lookup() {
        this.lookupButton.setEnabled(false);
        this.runLookup();
    }
    
    private JPanel buildInputPanel() {
        (this.inputCanvas = new CharacterCanvas()).setPreferredSize(HanziLookup.CANVAS_DIMENSION);
        this.inputCanvas.addStrokesListener(new CharacterCanvas.StrokesListener() {
            public void strokeFinished(final CharacterCanvas.StrokeEvent e) {
                HanziLookup.this.strokeFinished(e);
            }
        });
        final JPanel canvasButtonPanel = this.buildCanvasButtons();
        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(this.inputCanvas, "Center");
        inputPanel.add(canvasButtonPanel, "South");
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter character"));
        return inputPanel;
    }
    
    private JPanel buildCanvasButtons() {
        this.lookupButton = new JButton("Lookup");
        this.clearButton = new JButton("Clear");
        this.undoButton = new JButton("Undo");
        final ActionListener canvasButtonListener = new ActionListener() {
            public void actionPerformed(final ActionEvent ae) {
                if (ae.getSource() == HanziLookup.this.lookupButton) {
                    HanziLookup.this.lookup();
                }
                else if (ae.getSource() == HanziLookup.this.clearButton) {
                    HanziLookup.this.clear();
                }
                else if (ae.getSource() == HanziLookup.this.undoButton) {
                    HanziLookup.this.undo();
                }
            }
        };
        this.lookupButton.addActionListener(canvasButtonListener);
        this.clearButton.addActionListener(canvasButtonListener);
        this.undoButton.addActionListener(canvasButtonListener);
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(this.lookupButton);
        buttonPanel.add(this.undoButton);
        buttonPanel.add(this.clearButton);
        return buttonPanel;
    }
    
    private JPanel buildSelectionPanel() {
        this.candidatesList = this.buildCandidatesList();
        final JScrollPane candidatesPane = new JScrollPane(this.candidatesList);
        candidatesPane.setVerticalScrollBarPolicy(22);
        final JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new BorderLayout());
        selectionPanel.add(candidatesPane, "Center");
        selectionPanel.setPreferredSize(HanziLookup.SELECTION_DIMENSION);
        selectionPanel.setBorder(BorderFactory.createTitledBorder("Select character"));
        return selectionPanel;
    }
    
    private JList buildCandidatesList() {
        final JClickableList candidatesList = new JClickableList();
        candidatesList.setSelectionMode(0);
        candidatesList.setLayoutOrientation(2);
        candidatesList.setVisibleRowCount(-1);
        ((DefaultListCellRenderer)candidatesList.getCellRenderer()).setHorizontalAlignment(0);
        candidatesList.addListClickedListener(new JClickableList.ListClickedListener() {
            public void listClicked(final JClickableList.ListClickedEvent e) {
                final Character selectedChar = candidatesList.getSelectedValue();
                if (selectedChar != null) {
                    HanziLookup.this.notifyReceivers(selectedChar);
                }
            }
        });
        return candidatesList;
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        if (font != null && this.candidatesList != null) {
            this.candidatesList.setFont(font);
            this.resetListCellWidth(font);
        }
    }
    
    protected void validateTree() {
        super.validateTree();
        this.resetListCellWidth(this.candidatesList.getFont());
    }
    
    private void resetListCellWidth(final Font font) {
        final FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        int listWidth = this.candidatesList.getWidth();
        listWidth = ((listWidth == 0) ? ((int)this.candidatesList.getPreferredSize().getWidth()) : listWidth);
        final int cellWidth = 2 * metrics.charWidth('\u4e00');
        this.candidatesList.setFixedCellWidth(cellWidth);
    }
    
    public void setSearchType(final int searchType) {
        if (searchType != 0 && searchType != 1 && searchType != 2) {
            throw new IllegalArgumentException("searchType invalid!");
        }
        final int previousSearchType = this.searchType;
        this.searchType = searchType;
        if (this.autoLookup && previousSearchType != searchType) {
            this.runLookup();
        }
    }
    
    public int getSearchType() {
        return this.searchType;
    }
    
    public void setNumResults(final int numResults) {
        if (numResults < 1) {
            throw new IllegalArgumentException("numResults must be at least 1!");
        }
        this.numResults = numResults;
    }
    
    public int getNumResults() {
        return this.numResults;
    }
    
    public void setAutoLookup(final boolean autoLookup) {
        this.autoLookup = autoLookup;
    }
    
    public boolean getAutoLookup() {
        return this.autoLookup;
    }
    
    public void setLooseness(final double looseness) {
        if (looseness < 0.0 || looseness > 1.0) {
            throw new IllegalArgumentException("looseness must be between 0.0 and 1.0!");
        }
        this.looseness = looseness;
    }
    
    public double getLooseness() {
        return this.looseness;
    }
    
    private void strokeFinished(final CharacterCanvas.StrokeEvent e) {
        if (this.autoLookup) {
            this.runLookup();
        }
    }
    
    public KeyStroke getLookupMacro() {
        return this.lookupMacro;
    }
    
    public KeyStroke getUndoMacro() {
        return this.undoMacro;
    }
    
    public KeyStroke getClearMacro() {
        return this.clearMacro;
    }
    
    public void registerLookupMacro(final KeyStroke keyStroke) {
        final String LOOKUP_COMMAND_KEY = "hanzilookup.lookup";
        final Action lookupAction = new AbstractAction() {
            public void actionPerformed(final ActionEvent e) {
                HanziLookup.this.lookupButton.doClick();
            }
        };
        this.registerMacro(keyStroke, this.lookupMacro, "hanzilookup.lookup", lookupAction);
        this.lookupMacro = keyStroke;
    }
    
    public void registerClearMacro(final KeyStroke keyStroke) {
        final String CLEAR_COMMAND_KEY = "hanzilookup.clear";
        final Action clearAction = new AbstractAction() {
            public void actionPerformed(final ActionEvent e) {
                HanziLookup.this.clearButton.doClick();
            }
        };
        this.registerMacro(keyStroke, this.clearMacro, "hanzilookup.clear", clearAction);
        this.clearMacro = keyStroke;
    }
    
    public void registerUndoMacro(final KeyStroke keyStroke) {
        final String CLEAR_COMMAND_KEY = "hanzilookup.undo";
        final Action undoAction = new AbstractAction() {
            public void actionPerformed(final ActionEvent e) {
                HanziLookup.this.undoButton.doClick();
            }
        };
        this.registerMacro(keyStroke, this.undoMacro, "hanzilookup.undo", undoAction);
        this.undoMacro = keyStroke;
    }
    
    private void registerMacro(final KeyStroke newKeyStroke, final KeyStroke oldKeyStroke, final String command, final Action action) {
        final InputMap inputMap = this.getInputMap(2);
        synchronized (inputMap) {
            if (oldKeyStroke != null) {
                inputMap.remove(oldKeyStroke);
            }
            inputMap.put(newKeyStroke, command);
        }
        // monitorexit(inputMap)
        final ActionMap actionMap = this.getActionMap();
        synchronized (actionMap) {
            actionMap.put(command, action);
        }
        // monitorexit(actionMap)
    }
    
    public static class CharacterSelectionEvent extends EventObject
    {
        private char character;
        
        private CharacterSelectionEvent(final Object source, final char character) {
            super(source);
            this.character = character;
        }
        
        public char getSelectedCharacter() {
            return this.character;
        }
    }
    
    public interface CharacterSelectionListener
    {
        void characterSelected(final CharacterSelectionEvent p0);
    }
}
