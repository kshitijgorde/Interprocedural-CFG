// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.widgets.TypedListener;

class StyledTextListener extends TypedListener
{
    StyledTextListener(final SWTEventListener swtEventListener) {
        super(swtEventListener);
    }
    
    public void handleEvent(final Event event) {
        switch (event.type) {
            case 3000: {
                ((ExtendedModifyListener)this.eventListener).modifyText(new ExtendedModifyEvent((StyledTextEvent)event));
                break;
            }
            case 3001: {
                final LineBackgroundEvent lineBackgroundEvent = new LineBackgroundEvent((StyledTextEvent)event);
                ((LineBackgroundListener)this.eventListener).lineGetBackground(lineBackgroundEvent);
                ((StyledTextEvent)event).lineBackground = lineBackgroundEvent.lineBackground;
                break;
            }
            case 3007: {
                final BidiSegmentEvent bidiSegmentEvent = new BidiSegmentEvent((StyledTextEvent)event);
                ((BidiSegmentListener)this.eventListener).lineGetSegments(bidiSegmentEvent);
                ((StyledTextEvent)event).segments = bidiSegmentEvent.segments;
                ((StyledTextEvent)event).segmentsChars = bidiSegmentEvent.segmentsChars;
                break;
            }
            case 3002: {
                final LineStyleEvent lineStyleEvent = new LineStyleEvent((StyledTextEvent)event);
                ((LineStyleListener)this.eventListener).lineGetStyle(lineStyleEvent);
                ((StyledTextEvent)event).ranges = lineStyleEvent.ranges;
                ((StyledTextEvent)event).styles = lineStyleEvent.styles;
                ((StyledTextEvent)event).alignment = lineStyleEvent.alignment;
                ((StyledTextEvent)event).indent = lineStyleEvent.indent;
                ((StyledTextEvent)event).wrapIndent = lineStyleEvent.wrapIndent;
                ((StyledTextEvent)event).justify = lineStyleEvent.justify;
                ((StyledTextEvent)event).bullet = lineStyleEvent.bullet;
                ((StyledTextEvent)event).bulletIndex = lineStyleEvent.bulletIndex;
                ((StyledTextEvent)event).tabStops = lineStyleEvent.tabStops;
                break;
            }
            case 3008: {
                ((PaintObjectListener)this.eventListener).paintObject(new PaintObjectEvent((StyledTextEvent)event));
                break;
            }
            case 3005: {
                final VerifyEvent verifyEvent = new VerifyEvent(event);
                ((VerifyKeyListener)this.eventListener).verifyKey(verifyEvent);
                event.doit = verifyEvent.doit;
                break;
            }
            case 3006: {
                ((TextChangeListener)this.eventListener).textChanged(new TextChangedEvent((StyledTextContent)event.data));
                break;
            }
            case 3003: {
                ((TextChangeListener)this.eventListener).textChanging(new TextChangingEvent((StyledTextContent)event.data, (StyledTextEvent)event));
                break;
            }
            case 3004: {
                ((TextChangeListener)this.eventListener).textSet(new TextChangedEvent((StyledTextContent)event.data));
                break;
            }
            case 3009: {
                final MovementEvent movementEvent = new MovementEvent((StyledTextEvent)event);
                ((MovementListener)this.eventListener).getNextOffset(movementEvent);
                ((StyledTextEvent)event).end = movementEvent.newOffset;
                break;
            }
            case 3010: {
                final MovementEvent movementEvent2 = new MovementEvent((StyledTextEvent)event);
                ((MovementListener)this.eventListener).getPreviousOffset(movementEvent2);
                ((StyledTextEvent)event).end = movementEvent2.newOffset;
                break;
            }
            case 3011: {
                final CaretEvent caretEvent = new CaretEvent((StyledTextEvent)event);
                ((CaretListener)this.eventListener).caretMoved(caretEvent);
                ((StyledTextEvent)event).end = caretEvent.caretOffset;
                break;
            }
        }
    }
}
