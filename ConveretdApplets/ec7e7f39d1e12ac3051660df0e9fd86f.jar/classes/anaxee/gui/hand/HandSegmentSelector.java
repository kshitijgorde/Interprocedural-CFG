// 
// Decompiled by Procyon v0.5.30
// 

package classes.anaxee.gui.hand;

import java.io.IOException;
import javax.swing.SwingUtilities;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.plaf.metal.MetalToolTipUI;
import javax.swing.plaf.ComponentUI;
import java.awt.Font;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ToolTipManager;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import javax.swing.JComponent;
import javax.swing.JToolTip;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.UIManager;
import java.util.LinkedHashMap;
import javax.swing.event.EventListenerList;
import com.neurotec.biometrics.NFPosition;
import java.util.HashMap;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JPanel;

public final class HandSegmentSelector extends JPanel
{
    private NHandsComponent hands;
    private JToolBar toolBar;
    private JToggleButton amputatedToggleButton;
    private SelectionMode selectionMode;
    private Scenario scenario;
    private HashMap<NFPosition, Segment> allSegments;
    private HashMap<NFPosition, Segment> availableSegments;
    private NFPosition favoredPosition;
    private EventListenerList listenerList;
    boolean isMouseClicked;
    NFPosition current_finger;
    boolean is_mode_debug;
    
    public HandSegmentSelector() {
        this.selectionMode = SelectionMode.FINGER;
        this.scenario = Scenario.PLAIN_FINGER;
        this.allSegments = new LinkedHashMap<NFPosition, Segment>();
        this.availableSegments = new LinkedHashMap<NFPosition, Segment>();
        this.favoredPosition = null;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.listenerList = new EventListenerList();
        this.isMouseClicked = false;
        this.current_finger = NFPosition.UNKNOWN;
        this.is_mode_debug = false;
        this.setBackground(Color.WHITE);
        this.initGUI();
        this.initPositions();
    }
    
    public SelectionMode getSelectionMode() {
        return this.selectionMode;
    }
    
    public Scenario getScenario() {
        return this.scenario;
    }
    
    public void setScenario(final Scenario scenario) {
        final Scenario scenario2 = this.scenario;
        this.scenario = scenario;
        if (!scenario2.equals(scenario)) {
            this.onScenarioChanged();
        }
    }
    
    public List<NFPosition> getSelectedPositions() {
        final ArrayList<NFPosition> list = new ArrayList<NFPosition>();
        for (final Segment segment : this.availableSegments.values()) {
            if (segment.isSelected()) {
                list.add(segment.getPosition());
            }
        }
        return list;
    }
    
    public NFPosition getHightlightedPosition() {
        for (final Segment segment : this.availableSegments.values()) {
            if (segment.isHighlighted()) {
                return segment.getPosition();
            }
        }
        return null;
    }
    
    public List<NFPosition> getMissingPositions() {
        final ArrayList<NFPosition> list = new ArrayList<NFPosition>();
        for (final Segment segment : this.availableSegments.values()) {
            if (segment.isMissing()) {
                list.add(segment.getPosition());
            }
        }
        return list;
    }
    
    public void setHighlighted(final NFPosition nfPosition, final boolean highlighted) {
        final Segment segment = this.availableSegments.get(nfPosition);
        if (segment != null) {
            segment.setHighlighted(highlighted);
        }
    }
    
    public void setSelected(final NFPosition nfPosition, final boolean selected) {
        final Segment segment = this.availableSegments.get(nfPosition);
        if (segment != null) {
            segment.setSelected(selected);
        }
    }
    
    public void setSelectedAll(final boolean selected) {
        for (final Segment segment : this.availableSegments.values()) {
            if (!segment.isMissing()) {
                segment.setSelected(selected);
            }
        }
    }
    
    public void setMissing(final NFPosition nfPosition, final boolean missing) {
        final Segment segment = this.availableSegments.get(nfPosition);
        if (segment != null) {
            segment.setMissing(missing);
        }
    }
    
    public void clear() {
        final Iterator<Segment> iterator = this.availableSegments.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().clear();
        }
    }
    
    public void clearHoover() {
        final Iterator<Segment> iterator = this.availableSegments.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().setHovered(false);
        }
    }
    
    public void clearHighlighting() {
        final Iterator<Segment> iterator = this.availableSegments.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().setHighlighted(false);
        }
    }
    
    public void clearSelection() {
        for (final Segment segment : this.availableSegments.values()) {
            if (segment.isSelected()) {
                segment.setSelected(false);
            }
        }
    }
    
    private void onScenarioChanged() {
        this.clear();
        this.availableSegments.clear();
        this.selectionMode = this.scenario.getSelectionMode();
        final List<NFPosition> allowedPositions = this.scenario.getAllowedPositions();
        if (allowedPositions != null) {
            for (final NFPosition nfPosition : allowedPositions) {
                final Segment segment = this.allSegments.get(nfPosition);
                if (segment != null) {
                    this.availableSegments.put(nfPosition, segment);
                }
            }
        }
        this.setSelectedAll(false);
        this.setEnabled(!this.selectionMode.equals(SelectionMode.NONE));
        this.hands.setToolTipText(null);
    }
    
    private void onMouseMoved(final List<NFPosition> list) {
        this.clearHoover();
        Segment affectedSegment;
        if (this.favoredPosition != null) {
            affectedSegment = this.availableSegments.get(this.favoredPosition);
        }
        else {
            affectedSegment = this.getAffectedSegment(list);
        }
        this.showToolTip(list);
        if (affectedSegment != null && !affectedSegment.isMissing()) {
            affectedSegment.setHovered(true);
        }
    }
    
    private void onMousePressed(final List<NFPosition> list, final boolean b) {
        Segment affectedSegment = null;
        if (this.selectionMode == SelectionMode.PALMS) {
            this.clearSelection();
            if (b) {
                if (list.size() > 1) {
                    this.favoredPosition = list.get(1);
                    this.showToolTip(this.getAvailablePositions(list));
                    affectedSegment = this.allSegments.get(this.favoredPosition);
                    if (affectedSegment != null) {
                        this.clearHoover();
                        affectedSegment.setHovered(true);
                    }
                }
            }
            else if (list.size() != 0) {
                affectedSegment = this.allSegments.get(list.get(0));
            }
        }
        else {
            affectedSegment = this.getAffectedSegment(list);
        }
        if (affectedSegment != null) {
            this.setSelectedAll(false);
            affectedSegment.setSelected(!affectedSegment.isSelected());
        }
    }
    
    private void showToolTip(final List<NFPosition> list) {
        this.hands.setToolTipText(this.createToolTipMessage(list));
    }
    
    private String createToolTipMessage(final List<NFPosition> list) {
        if (list.size() == 0) {
            return null;
        }
        String s = "Position: " + list.get(0);
        if (list.size() > 1) {
            String s2 = s + "\nThis is also: ";
            for (int i = 1; i < list.size(); ++i) {
                s2 = s2 + list.get(i).toString() + ", ";
            }
            s = s2 + "\nClick right mouse button to select other palm part.";
        }
        return s;
    }
    
    private List<NFPosition> getAvailablePositions(final List<NFPosition> list) {
        final ArrayList<NFPosition> list2 = new ArrayList<NFPosition>();
        final List<NFPosition> allowedPositions = this.scenario.getAllowedPositions();
        for (final NFPosition nfPosition : list) {
            if (allowedPositions.contains(nfPosition)) {
                list2.add(nfPosition);
            }
        }
        if (this.favoredPosition != null && list2.contains(this.favoredPosition)) {
            while (this.favoredPosition != list2.get(0)) {
                final NFPosition nfPosition2 = list2.get(0);
                list2.remove(0);
                list2.add(nfPosition2);
            }
        }
        return list2;
    }
    
    private Segment getAffectedSegment(final List<NFPosition> list) {
        for (final NFPosition nfPosition : list) {
            if (this.availableSegments.get(nfPosition) != null) {
                return this.availableSegments.get(nfPosition);
            }
        }
        return null;
    }
    
    private void initPositions() {
        this.allSegments.put(NFPosition.LEFT_LITTLE_FINGER, new Segment(NFPosition.LEFT_LITTLE_FINGER));
        this.allSegments.put(NFPosition.LEFT_RING_FINGER, new Segment(NFPosition.LEFT_RING_FINGER));
        this.allSegments.put(NFPosition.LEFT_MIDDLE_FINGER, new Segment(NFPosition.LEFT_MIDDLE_FINGER));
        this.allSegments.put(NFPosition.LEFT_INDEX_FINGER, new Segment(NFPosition.LEFT_INDEX_FINGER));
        this.allSegments.put(NFPosition.LEFT_THUMB, new Segment(NFPosition.LEFT_THUMB));
        this.allSegments.put(NFPosition.RIGHT_THUMB, new Segment(NFPosition.RIGHT_THUMB));
        this.allSegments.put(NFPosition.RIGHT_INDEX_FINGER, new Segment(NFPosition.RIGHT_INDEX_FINGER));
        this.allSegments.put(NFPosition.RIGHT_MIDDLE_FINGER, new Segment(NFPosition.RIGHT_MIDDLE_FINGER));
        this.allSegments.put(NFPosition.RIGHT_RING_FINGER, new Segment(NFPosition.RIGHT_RING_FINGER));
        this.allSegments.put(NFPosition.RIGHT_LITTLE_FINGER, new Segment(NFPosition.RIGHT_LITTLE_FINGER));
        this.allSegments.put(NFPosition.PLAIN_LEFT_FOUR_FINGERS, new Segment(NFPosition.PLAIN_LEFT_FOUR_FINGERS));
        this.allSegments.put(NFPosition.PLAIN_RIGHT_FOUR_FINGERS, new Segment(NFPosition.PLAIN_RIGHT_FOUR_FINGERS));
        this.allSegments.put(NFPosition.PLAIN_LEFT_THUMB, new Segment(NFPosition.PLAIN_LEFT_THUMB));
        this.allSegments.put(NFPosition.PLAIN_RIGHT_THUMB, new Segment(NFPosition.PLAIN_RIGHT_THUMB));
        this.allSegments.put(NFPosition.PLAIN_THUMBS, new Segment(NFPosition.PLAIN_THUMBS));
        this.allSegments.put(NFPosition.LEFT_FULL_PALM, new Segment(NFPosition.LEFT_FULL_PALM));
        this.allSegments.put(NFPosition.LEFT_LOWER_PALM, new Segment(NFPosition.LEFT_LOWER_PALM));
        this.allSegments.put(NFPosition.LEFT_UPPER_PALM, new Segment(NFPosition.LEFT_UPPER_PALM));
        this.allSegments.put(NFPosition.LEFT_HYPOTHENAR, new Segment(NFPosition.LEFT_HYPOTHENAR));
        this.allSegments.put(NFPosition.LEFT_INTERDIGITAL, new Segment(NFPosition.LEFT_INTERDIGITAL));
        this.allSegments.put(NFPosition.LEFT_THENAR, new Segment(NFPosition.LEFT_THENAR));
        this.allSegments.put(NFPosition.RIGHT_FULL_PALM, new Segment(NFPosition.RIGHT_FULL_PALM));
        this.allSegments.put(NFPosition.RIGHT_LOWER_PALM, new Segment(NFPosition.RIGHT_LOWER_PALM));
        this.allSegments.put(NFPosition.RIGHT_UPPER_PALM, new Segment(NFPosition.RIGHT_UPPER_PALM));
        this.allSegments.put(NFPosition.RIGHT_HYPOTHENAR, new Segment(NFPosition.RIGHT_HYPOTHENAR));
        this.allSegments.put(NFPosition.RIGHT_INTERDIGITAL, new Segment(NFPosition.RIGHT_INTERDIGITAL));
        this.allSegments.put(NFPosition.RIGHT_THENAR, new Segment(NFPosition.RIGHT_THENAR));
    }
    
    private void initGUI() {
        try {
            this.setLayout(null);
            this.setOpaque(false);
            this.setPreferredSize(new Dimension(300, 250));
            this.setLayout(new BorderLayout());
            (this.toolBar = new JToolBar()).setOpaque(false);
            this.toolBar.setFloatable(false);
            this.add(this.toolBar, "North");
            this.add(this.hands = new NHandsComponent() {
                @Override
                public Point getToolTipLocation(final MouseEvent mouseEvent) {
                    return new Point(0, -20);
                }
                
                @Override
                public JToolTip createToolTip() {
                    final MultiLineToolTip multiLineToolTip = new MultiLineToolTip();
                    multiLineToolTip.setComponent(this);
                    return multiLineToolTip;
                }
            }, "Center");
            this.hands.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(final MouseEvent mouseEvent) {
                    if (HandSegmentSelector.this.isEnabled()) {
                        final List access$200 = HandSegmentSelector.this.getAvailablePositions(HandSegmentSelector.this.hands.getAffectedPositions(mouseEvent));
                        HandSegmentSelector.this.onMousePressed(access$200, mouseEvent.getButton() == 3);
                        try {
                            if (access$200.size() > 0) {
                                HandSegmentSelector.this.current_finger = access$200.get(0);
                            }
                            HandSegmentSelector.this.isMouseClicked = true;
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                
                @Override
                public void mouseExited(final MouseEvent mouseEvent) {
                    HandSegmentSelector.this.hands.setToolTipText(null);
                    HandSegmentSelector.this.clearHoover();
                }
            });
            this.hands.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(final MouseEvent mouseEvent) {
                    if (HandSegmentSelector.this.isEnabled()) {
                        HandSegmentSelector.this.onMouseMoved(HandSegmentSelector.this.getAvailablePositions(HandSegmentSelector.this.hands.getAffectedPositions(mouseEvent)));
                    }
                }
            });
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(final MouseEvent mouseEvent) {
                    HandSegmentSelector.this.hands.setToolTipText(null);
                }
            });
            ToolTipManager.sharedInstance().setInitialDelay(0);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        this.listenerList.add(ChangeListener.class, changeListener);
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        this.listenerList.remove(ChangeListener.class, changeListener);
    }
    
    private synchronized void fireStateChanged() {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ChangeListener.class) {
                ((ChangeListener)listenerList[i + 1]).stateChanged(new ChangeEvent(this));
            }
        }
    }
    
    public boolean mouseClickedOnHands() {
        return this.isMouseClicked;
    }
    
    public void setMouseClickedStatus(final boolean isMouseClicked) {
        this.isMouseClicked = isMouseClicked;
        if (this.is_mode_debug) {
            System.out.println("mouse clicked status setted ");
        }
    }
    
    public NFPosition getCurrentFingerSelectedPosition() {
        return this.current_finger;
    }
    
    public void resetCurrentFinger() {
        this.current_finger = NFPosition.UNKNOWN;
    }
    
    public void setUseDebugMode(final boolean is_mode_debug) {
        this.is_mode_debug = is_mode_debug;
    }
    
    public enum SelectionMode
    {
        NONE(0), 
        FINGER(1), 
        SLAPS_AND_TWO_THUMBS(2), 
        SLAPS_AND_SEPARATE_THUMBS(3), 
        PALMS(4);
        
        private int value;
        
        private SelectionMode(final int value) {
            this.value = value;
        }
    }
    
    final class Segment
    {
        private NFPosition position;
        private boolean selected;
        private boolean highlighted;
        private boolean hovered;
        private boolean missing;
        private boolean isLeftHand;
        private boolean isRightHand;
        private boolean isLeftAndRightHand;
        private boolean isSingle;
        
        public Segment(final NFPosition nfPosition) {
            this.selected = false;
            this.highlighted = false;
            this.hovered = false;
            this.missing = false;
            this.isLeftHand = false;
            this.isRightHand = false;
            this.isLeftAndRightHand = false;
            this.isSingle = false;
            this.position = nfPosition;
            this.isLeftHand = NFPosition.isLeft(nfPosition);
            this.isRightHand = NFPosition.isRight(nfPosition);
            this.isLeftAndRightHand = NFPosition.isLeftAndRight(nfPosition);
            this.isSingle = NFPosition.isSingleFinger(this.position);
        }
        
        public NFPosition getPosition() {
            return this.position;
        }
        
        public boolean isLeftHand() {
            return this.isLeftHand;
        }
        
        public boolean isRightHand() {
            return this.isRightHand;
        }
        
        public boolean isLeftAndRightHand() {
            return this.isLeftAndRightHand;
        }
        
        public boolean isSingle() {
            return this.isSingle;
        }
        
        public boolean isHovered() {
            return this.hovered;
        }
        
        public void setHovered(final boolean hovered) {
            if (this.isHovered() != hovered) {
                this.hovered = hovered;
                HandSegmentSelector.this.hands.setPositionHoover(this.position, hovered);
                HandSegmentSelector.this.fireStateChanged();
            }
        }
        
        public boolean isSelected() {
            return this.selected;
        }
        
        public void setSelected(final boolean selected) {
            if (this.isSelected() != selected) {
                this.selected = selected;
                HandSegmentSelector.this.hands.setPositionSelected(this.position, selected);
                HandSegmentSelector.this.fireStateChanged();
            }
        }
        
        public boolean isHighlighted() {
            return this.highlighted;
        }
        
        public void setHighlighted(final boolean highlighted) {
            if (this.isHighlighted() != highlighted) {
                this.highlighted = highlighted;
                HandSegmentSelector.this.hands.setPositionHighlighted(this.position, highlighted);
                HandSegmentSelector.this.fireStateChanged();
            }
        }
        
        public boolean isMissing() {
            return this.missing;
        }
        
        public void setMissing(final boolean b) {
            final boolean missing = this.isMissing();
            this.missing = b;
            if (missing != b) {
                this.missing = b;
                HandSegmentSelector.this.hands.setPositionMissing(this.position, b);
                HandSegmentSelector.this.fireStateChanged();
            }
        }
        
        public void clear() {
            this.selected = false;
            this.highlighted = false;
            this.missing = false;
            this.hovered = false;
            HandSegmentSelector.this.hands.clearPosition(this.position);
            HandSegmentSelector.this.fireStateChanged();
        }
        
        @Override
        public String toString() {
            return "Segment [NFPosition=" + this.getPosition() + ", Highlighted=" + this.isHighlighted() + ", Missing=" + this.isMissing() + ", Hovered=" + this.isHovered() + ", Selected=" + this.isSelected() + "]";
        }
    }
    
    class MultiLineToolTip extends JToolTip
    {
        public MultiLineToolTip() {
            this.setFont(new Font("Segoe UI", 0, 11));
            this.setUI(new MultiLineToolTipUI());
        }
    }
    
    class MultiLineToolTipUI extends MetalToolTipUI
    {
        private String[] strs;
        
        @Override
        public void paint(final Graphics graphics, final JComponent component) {
            if (this.strs == null) {
                return;
            }
            final FontMetrics fontMetrics = component.getFontMetrics(component.getFont());
            final Dimension size = component.getSize();
            graphics.setColor(component.getBackground());
            graphics.fillRect(0, 0, size.width, size.height);
            graphics.setColor(component.getForeground());
            if (this.strs != null) {
                for (int i = 0; i < this.strs.length; ++i) {
                    if (this.strs[i] != null) {
                        graphics.drawString(this.strs[i], 3, fontMetrics.getHeight() * (i + 1));
                    }
                }
            }
            if (HandSegmentSelector.this.is_mode_debug) {
                System.out.println("inside paint of HandSegmentSelector");
            }
        }
        
        @Override
        public Dimension getPreferredSize(final JComponent component) {
            final FontMetrics fontMetrics = component.getFontMetrics(component.getFont());
            final String tipText = ((JToolTip)component).getTipText();
            if (tipText == null) {
                this.strs = null;
                component.repaint();
                return new Dimension(0, 0);
            }
            final BufferedReader bufferedReader = new BufferedReader(new StringReader(tipText));
            int n = 0;
            final ArrayList<String> list = new ArrayList<String>();
            try {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    final int computeStringWidth = SwingUtilities.computeStringWidth(fontMetrics, line);
                    n = ((n < computeStringWidth) ? computeStringWidth : n);
                    list.add(line);
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            final int size = list.size();
            this.strs = new String[size];
            int n2 = 0;
            final Iterator<Object> iterator = list.iterator();
            while (iterator.hasNext()) {
                this.strs[n2++] = iterator.next();
            }
            final int n3 = fontMetrics.getHeight() * size;
            component.repaint();
            return new Dimension(n + 6, n3 + 4);
        }
    }
}
