// 
// Decompiled by Procyon v0.5.30
// 

package classes.anaxee.gui.hand;

import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BasicStroke;
import java.util.Arrays;
import com.neurotec.biometrics.NFPosition;
import java.util.ArrayList;
import java.awt.Stroke;
import java.awt.Color;
import java.util.List;
import javax.swing.JPanel;

public class NHandsComponent extends JPanel
{
    private HandComponent leftHand;
    private HandComponent rightHand;
    private JPanel handsPanel;
    private List<PositionMap> leftHandPositionMappings;
    private List<PositionMap> rightHandPositionMappings;
    private List<List<HandComponent.Position>> positionRelations;
    private Color missingFillColor;
    private Color highlightedFillColor;
    private Color selectedFillColor;
    private Color hooverFillColor;
    private Color missingStrokeColor;
    private Color highlightedStrokeColor;
    private Color selectedStrokeColor;
    private Color hooverStrokeColor;
    private Stroke missingStroke;
    private Stroke highlightedStroke;
    private Stroke selectedStroke;
    private Stroke hooverStroke;
    
    public NHandsComponent() {
        this.setBackground(Color.WHITE);
        this.initGUI();
        this.leftHandPositionMappings = new ArrayList<PositionMap>();
        this.rightHandPositionMappings = new ArrayList<PositionMap>();
        this.positionRelations = new ArrayList<List<HandComponent.Position>>();
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.PLAIN_THUMBS, Arrays.asList(HandComponent.Position.THUMB)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.LEFT_THUMB, Arrays.asList(HandComponent.Position.THUMB)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.LEFT_INDEX_FINGER, Arrays.asList(HandComponent.Position.INDEX_FINGER_PRINT)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.LEFT_MIDDLE_FINGER, Arrays.asList(HandComponent.Position.MIDDLE_FINGER_PRINT)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.LEFT_RING_FINGER, Arrays.asList(HandComponent.Position.RING_FINGER_PRINT)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.LEFT_LITTLE_FINGER, Arrays.asList(HandComponent.Position.LITTLE_FINGER_PRINT)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.PLAIN_LEFT_THUMB, Arrays.asList(HandComponent.Position.THUMB)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.PLAIN_LEFT_FOUR_FINGERS, Arrays.asList(HandComponent.Position.LITTLE_FINGER_PRINT, HandComponent.Position.RING_FINGER_PRINT, HandComponent.Position.MIDDLE_FINGER_PRINT, HandComponent.Position.INDEX_FINGER_PRINT)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.LEFT_FULL_PALM, Arrays.asList(HandComponent.Position.PALM)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.LEFT_LOWER_PALM, Arrays.asList(HandComponent.Position.LOWER_PALM)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.LEFT_UPPER_PALM, Arrays.asList(HandComponent.Position.UPPER_PALM)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.LEFT_INTERDIGITAL, Arrays.asList(HandComponent.Position.INTERDIGITAL)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.LEFT_THENAR, Arrays.asList(HandComponent.Position.THENAR)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.LEFT_HYPOTHENAR, Arrays.asList(HandComponent.Position.HYPOTHENAR)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.LEFT_INDEX_MIDDLE_FINGERS, Arrays.asList(HandComponent.Position.INDEX_FINGER_PRINT, HandComponent.Position.MIDDLE_FINGER_PRINT)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.LEFT_MIDDLE_RING_FINGERS, Arrays.asList(HandComponent.Position.MIDDLE_FINGER_PRINT, HandComponent.Position.RING_FINGER_PRINT)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.LEFT_RING_LITTLE_FINGERS, Arrays.asList(HandComponent.Position.RING_FINGER_PRINT, HandComponent.Position.LITTLE_FINGER_PRINT)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.LEFT_INDEX_MIDDLE_RING_FINGERS, Arrays.asList(HandComponent.Position.INDEX_FINGER_PRINT, HandComponent.Position.MIDDLE_FINGER_PRINT, HandComponent.Position.RING_FINGER_PRINT)));
        this.leftHandPositionMappings.add(new PositionMap(NFPosition.LEFT_MIDDLE_RING_LITTLE_FINGERS, Arrays.asList(HandComponent.Position.MIDDLE_FINGER_PRINT, HandComponent.Position.RING_FINGER_PRINT, HandComponent.Position.LITTLE_FINGER_PRINT)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.PLAIN_THUMBS, Arrays.asList(HandComponent.Position.THUMB)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.RIGHT_THUMB, Arrays.asList(HandComponent.Position.THUMB)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.RIGHT_INDEX_FINGER, Arrays.asList(HandComponent.Position.INDEX_FINGER_PRINT)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.RIGHT_MIDDLE_FINGER, Arrays.asList(HandComponent.Position.MIDDLE_FINGER_PRINT)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.RIGHT_RING_FINGER, Arrays.asList(HandComponent.Position.RING_FINGER_PRINT)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.RIGHT_LITTLE_FINGER, Arrays.asList(HandComponent.Position.LITTLE_FINGER_PRINT)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.PLAIN_RIGHT_THUMB, Arrays.asList(HandComponent.Position.THUMB)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.PLAIN_RIGHT_FOUR_FINGERS, Arrays.asList(HandComponent.Position.LITTLE_FINGER_PRINT, HandComponent.Position.RING_FINGER_PRINT, HandComponent.Position.MIDDLE_FINGER_PRINT, HandComponent.Position.INDEX_FINGER_PRINT)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.RIGHT_FULL_PALM, Arrays.asList(HandComponent.Position.PALM)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.RIGHT_LOWER_PALM, Arrays.asList(HandComponent.Position.LOWER_PALM)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.RIGHT_UPPER_PALM, Arrays.asList(HandComponent.Position.UPPER_PALM)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.RIGHT_INTERDIGITAL, Arrays.asList(HandComponent.Position.INTERDIGITAL)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.RIGHT_THENAR, Arrays.asList(HandComponent.Position.THENAR)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.RIGHT_HYPOTHENAR, Arrays.asList(HandComponent.Position.HYPOTHENAR)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.RIGHT_INDEX_MIDDLE_FINGERS, Arrays.asList(HandComponent.Position.INDEX_FINGER_PRINT, HandComponent.Position.MIDDLE_FINGER_PRINT)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.RIGHT_MIDDLE_RING_FINGERS, Arrays.asList(HandComponent.Position.MIDDLE_FINGER_PRINT, HandComponent.Position.RING_FINGER_PRINT)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.RIGHT_RING_LITTLE_FINGERS, Arrays.asList(HandComponent.Position.RING_FINGER_PRINT, HandComponent.Position.LITTLE_FINGER_PRINT)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.RIGHT_INDEX_MIDDLE_RING_FINGERS, Arrays.asList(HandComponent.Position.INDEX_FINGER_PRINT, HandComponent.Position.MIDDLE_FINGER_PRINT, HandComponent.Position.RING_FINGER_PRINT)));
        this.rightHandPositionMappings.add(new PositionMap(NFPosition.RIGHT_MIDDLE_RING_LITTLE_FINGERS, Arrays.asList(HandComponent.Position.MIDDLE_FINGER_PRINT, HandComponent.Position.RING_FINGER_PRINT, HandComponent.Position.LITTLE_FINGER_PRINT)));
        this.positionRelations.add(Arrays.asList(HandComponent.Position.LITTLE_FINGER, HandComponent.Position.LITTLE_FINGER_PRINT, HandComponent.Position.LITTLE_FINGER_PRINT_MARKER));
        this.positionRelations.add(Arrays.asList(HandComponent.Position.RING_FINGER, HandComponent.Position.RING_FINGER_PRINT, HandComponent.Position.RING_FINGER_PRINT_MARKER));
        this.positionRelations.add(Arrays.asList(HandComponent.Position.MIDDLE_FINGER, HandComponent.Position.MIDDLE_FINGER_PRINT, HandComponent.Position.MIDDLE_FINGER_PRINT_MARKER));
        this.positionRelations.add(Arrays.asList(HandComponent.Position.INDEX_FINGER, HandComponent.Position.INDEX_FINGER_PRINT, HandComponent.Position.INDEX_FINGER_PRINT_MARKER));
        this.positionRelations.add(Arrays.asList(HandComponent.Position.THUMB, HandComponent.Position.THUMB_PRINT, HandComponent.Position.THUMB_PRINT_MARKER));
        this.setSelectedFillColor(Color.GRAY);
        this.setMissingFillColor(Color.PINK);
        this.setHighlightedStrokeColor(Color.BLACK);
        this.setHighlightedStroke(new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f));
        this.setHooverStrokeColor(Color.BLACK);
        this.setHooverStroke(new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f));
    }
    
    private void initGUI() {
        try {
            this.setLayout(null);
            this.setOpaque(false);
            this.setPreferredSize(new Dimension(300, 250));
            this.setLayout(new BorderLayout());
            this.add(this.handsPanel = new JPanel(), "Center");
            this.handsPanel.setOpaque(false);
            this.handsPanel.setLayout(new BoxLayout(this.handsPanel, 0));
            (this.leftHand = new HandComponent()).setOpaque(false);
            this.handsPanel.add(this.leftHand);
            this.rightHand = new HandComponent();
            this.handsPanel.add(this.rightHand);
            this.rightHand.setFlipped(true);
            this.rightHand.setOpaque(false);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void setPositionFillColor(final NFPosition nfPosition, final Color color, final VisualisationMode visualisationMode) {
        final Iterator<HandComponent.Position> iterator = this.getMappedPositions(nfPosition).iterator();
        while (iterator.hasNext()) {
            final HandComponent.Position convertPosition = this.convertPosition(iterator.next(), visualisationMode);
            if (this.isLeftHand(nfPosition)) {
                this.leftHand.setPositionFillColor(convertPosition, color);
            }
            if (this.isRightHand(nfPosition)) {
                this.rightHand.setPositionFillColor(convertPosition, color);
            }
        }
    }
    
    private void setPositionStroke(final NFPosition nfPosition, final Stroke stroke, final VisualisationMode visualisationMode) {
        final Iterator<HandComponent.Position> iterator = this.getMappedPositions(nfPosition).iterator();
        while (iterator.hasNext()) {
            final HandComponent.Position convertPosition = this.convertPosition(iterator.next(), visualisationMode);
            if (this.isLeftHand(nfPosition)) {
                this.leftHand.setPositionStroke(convertPosition, stroke);
            }
            if (this.isRightHand(nfPosition)) {
                this.rightHand.setPositionStroke(convertPosition, stroke);
            }
        }
    }
    
    private void setPositionStrokeColor(final NFPosition nfPosition, final Color color, final VisualisationMode visualisationMode) {
        final Iterator<HandComponent.Position> iterator = this.getMappedPositions(nfPosition).iterator();
        while (iterator.hasNext()) {
            final HandComponent.Position convertPosition = this.convertPosition(iterator.next(), visualisationMode);
            if (this.isLeftHand(nfPosition)) {
                this.leftHand.setPositionStrokeColor(convertPosition, color);
            }
            if (this.isRightHand(nfPosition)) {
                this.rightHand.setPositionStrokeColor(convertPosition, color);
            }
        }
    }
    
    private List<HandComponent.Position> getPositionRelations(final HandComponent.Position position) {
        for (final List<HandComponent.Position> list : this.positionRelations) {
            if (list.contains(position)) {
                return list;
            }
        }
        return null;
    }
    
    private HandComponent.Position convertPosition(final HandComponent.Position position, final VisualisationMode visualisationMode) {
        final List<HandComponent.Position> positionRelations = this.getPositionRelations(position);
        if (positionRelations != null) {
            if (visualisationMode.equals(VisualisationMode.FINGER)) {
                return positionRelations.get(0);
            }
            if (visualisationMode.equals(VisualisationMode.FINGERPRINT)) {
                return positionRelations.get(1);
            }
            if (visualisationMode.equals(VisualisationMode.MARKER)) {
                return positionRelations.get(2);
            }
        }
        return position;
    }
    
    private boolean isLeftHand(final NFPosition nfPosition) {
        final Iterator<PositionMap> iterator = this.leftHandPositionMappings.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getNfPosition().equals(nfPosition)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isRightHand(final NFPosition nfPosition) {
        final Iterator<PositionMap> iterator = this.rightHandPositionMappings.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getNfPosition().equals(nfPosition)) {
                return true;
            }
        }
        return false;
    }
    
    private List<HandComponent.Position> getMappedPositions(final NFPosition nfPosition) {
        final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<HandComponent.Position>();
        for (final PositionMap positionMap : this.rightHandPositionMappings) {
            if (positionMap.getNfPosition().equals(nfPosition)) {
                list.addAll(positionMap.getPositions());
            }
        }
        for (final PositionMap positionMap2 : this.leftHandPositionMappings) {
            if (positionMap2.getNfPosition().equals(nfPosition)) {
                list.addAll(positionMap2.getPositions());
            }
        }
        return (List<HandComponent.Position>)list;
    }
    
    public List<NFPosition> getAffectedPositions(final MouseEvent mouseEvent) {
        if (!(mouseEvent.getSource() instanceof NHandsComponent)) {
            throw new UnsupportedOperationException("Only MouseEents provided by HandComponent are supported.");
        }
        final ArrayList<NFPosition> list = new ArrayList<NFPosition>();
        final List<HandComponent.Position> affectedPositions = this.leftHand.getAffectedPositions(new MouseEvent(this.leftHand, mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), mouseEvent.getX() - this.leftHand.getX(), mouseEvent.getY() - this.leftHand.getY(), mouseEvent.getClickCount(), mouseEvent.isPopupTrigger()));
        final List<HandComponent.Position> affectedPositions2 = this.rightHand.getAffectedPositions(new MouseEvent(this.rightHand, mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), mouseEvent.getX() - this.rightHand.getX(), mouseEvent.getY() - this.rightHand.getY(), mouseEvent.getClickCount(), mouseEvent.isPopupTrigger()));
        for (final PositionMap positionMap : this.leftHandPositionMappings) {
            final Iterator<HandComponent.Position> iterator2 = affectedPositions.iterator();
            while (iterator2.hasNext()) {
                if (positionMap.getPositions().contains(iterator2.next())) {
                    list.add(positionMap.getNfPosition());
                }
            }
        }
        for (final PositionMap positionMap2 : this.rightHandPositionMappings) {
            final Iterator<HandComponent.Position> iterator4 = affectedPositions2.iterator();
            while (iterator4.hasNext()) {
                if (positionMap2.getPositions().contains(iterator4.next())) {
                    list.add(positionMap2.getNfPosition());
                }
            }
        }
        return list;
    }
    
    public void setPositionSelected(final NFPosition nfPosition, final boolean b) {
        if (this.selectedFillColor != null) {
            Color selectedFillColor = null;
            if (b) {
                selectedFillColor = this.selectedFillColor;
            }
            this.setPositionFillColor(nfPosition, selectedFillColor, VisualisationMode.MARKER);
        }
        if (this.selectedStrokeColor != null) {
            Color selectedStrokeColor = null;
            if (b) {
                selectedStrokeColor = this.selectedStrokeColor;
            }
            this.setPositionStrokeColor(nfPosition, selectedStrokeColor, VisualisationMode.MARKER);
        }
        if (this.selectedStroke != null) {
            Stroke selectedStroke = null;
            if (b) {
                selectedStroke = this.selectedStroke;
            }
            this.setPositionStroke(nfPosition, selectedStroke, VisualisationMode.MARKER);
        }
        this.repaint();
    }
    
    public void setPositionHighlighted(final NFPosition nfPosition, final boolean b) {
        if (this.highlightedFillColor != null) {
            Color highlightedFillColor = null;
            if (b) {
                highlightedFillColor = this.highlightedFillColor;
            }
            this.setPositionFillColor(nfPosition, highlightedFillColor, VisualisationMode.MARKER);
        }
        if (this.highlightedStrokeColor != null) {
            Color highlightedStrokeColor = null;
            if (b) {
                highlightedStrokeColor = this.highlightedStrokeColor;
            }
            this.setPositionStrokeColor(nfPosition, highlightedStrokeColor, VisualisationMode.MARKER);
        }
        if (this.highlightedStroke != null) {
            Stroke highlightedStroke = null;
            if (b) {
                highlightedStroke = this.highlightedStroke;
            }
            this.setPositionStroke(nfPosition, highlightedStroke, VisualisationMode.MARKER);
        }
        this.repaint();
    }
    
    public void setPositionHoover(final NFPosition nfPosition, final boolean b) {
        if (this.hooverFillColor != null) {
            Color hooverFillColor = null;
            if (b) {
                hooverFillColor = this.hooverFillColor;
            }
            this.setPositionFillColor(nfPosition, hooverFillColor, VisualisationMode.MARKER);
        }
        if (this.hooverStrokeColor != null) {
            Color hooverStrokeColor = null;
            if (b) {
                hooverStrokeColor = this.hooverStrokeColor;
            }
            this.setPositionStrokeColor(nfPosition, hooverStrokeColor, VisualisationMode.MARKER);
        }
        if (this.hooverStroke != null) {
            Stroke hooverStroke = null;
            if (b) {
                hooverStroke = this.hooverStroke;
            }
            this.setPositionStroke(nfPosition, hooverStroke, VisualisationMode.MARKER);
        }
        this.repaint();
    }
    
    public void setPositionMissing(final NFPosition nfPosition, final boolean b) {
        if (this.missingFillColor != null) {
            Color missingFillColor = null;
            if (b) {
                missingFillColor = this.missingFillColor;
            }
            this.setPositionFillColor(nfPosition, missingFillColor, VisualisationMode.FINGER);
        }
        if (this.missingStrokeColor != null) {
            Color missingStrokeColor = null;
            if (b) {
                missingStrokeColor = this.missingStrokeColor;
            }
            this.setPositionStrokeColor(nfPosition, missingStrokeColor, VisualisationMode.FINGER);
        }
        if (this.missingStroke != null) {
            Stroke missingStroke = null;
            if (b) {
                missingStroke = this.missingStroke;
            }
            this.setPositionStroke(nfPosition, missingStroke, VisualisationMode.FINGER);
        }
        this.repaint();
    }
    
    public void clearPosition(final NFPosition nfPosition) {
        this.setPositionHighlighted(nfPosition, false);
        this.setPositionSelected(nfPosition, false);
        this.setPositionMissing(nfPosition, false);
        this.setPositionHoover(nfPosition, false);
    }
    
    public Color getHooverFillColor() {
        return this.hooverFillColor;
    }
    
    public void setHooverFillColor(final Color hooverFillColor) {
        this.hooverFillColor = hooverFillColor;
    }
    
    public Color getHooverStrokeColor() {
        return this.hooverStrokeColor;
    }
    
    public void setHooverStrokeColor(final Color hooverStrokeColor) {
        this.hooverStrokeColor = hooverStrokeColor;
    }
    
    public Stroke getHooverStroke() {
        return this.hooverStroke;
    }
    
    public void setHooverStroke(final Stroke hooverStroke) {
        this.hooverStroke = hooverStroke;
    }
    
    public Color getMissingFillColor() {
        return this.missingFillColor;
    }
    
    public void setMissingFillColor(final Color missingFillColor) {
        this.missingFillColor = missingFillColor;
    }
    
    public Color getHighlightedFillColor() {
        return this.highlightedFillColor;
    }
    
    public void setHighlightedFillColor(final Color highlightedFillColor) {
        this.highlightedFillColor = highlightedFillColor;
    }
    
    public Color getSelectedFillColor() {
        return this.selectedFillColor;
    }
    
    public void setSelectedFillColor(final Color selectedFillColor) {
        this.selectedFillColor = selectedFillColor;
    }
    
    public Color getMissingStrokeColor() {
        return this.missingStrokeColor;
    }
    
    public void setMissingStrokeColor(final Color missingStrokeColor) {
        this.missingStrokeColor = missingStrokeColor;
    }
    
    public Color getHighlightedStrokeColor() {
        return this.highlightedStrokeColor;
    }
    
    public void setHighlightedStrokeColor(final Color highlightedStrokeColor) {
        this.highlightedStrokeColor = highlightedStrokeColor;
    }
    
    public Color getSelectedStrokeColor() {
        return this.selectedStrokeColor;
    }
    
    public void setSelectedStrokeColor(final Color selectedStrokeColor) {
        this.selectedStrokeColor = selectedStrokeColor;
    }
    
    public Stroke getMissingStroke() {
        return this.missingStroke;
    }
    
    public void setMissingStroke(final Stroke missingStroke) {
        this.missingStroke = missingStroke;
    }
    
    public Stroke getHighlightedStroke() {
        return this.highlightedStroke;
    }
    
    public void setHighlightedStroke(final Stroke highlightedStroke) {
        this.highlightedStroke = highlightedStroke;
    }
    
    public Stroke getSelectedStroke() {
        return this.selectedStroke;
    }
    
    public void setSelectedStroke(final Stroke selectedStroke) {
        this.selectedStroke = selectedStroke;
    }
    
    public void setLeftHandVisible(final boolean visible) {
        this.leftHand.setVisible(visible);
    }
    
    public void setRightHandVisible(final boolean visible) {
        this.rightHand.setVisible(visible);
    }
    
    private enum VisualisationMode
    {
        DEFAULT, 
        FINGERPRINT, 
        FINGER, 
        MARKER;
    }
}
