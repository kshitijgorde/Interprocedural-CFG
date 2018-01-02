// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.widgets.TypedListener;

class DNDListener extends TypedListener
{
    Widget dndWidget;
    
    DNDListener(final SWTEventListener swtEventListener) {
        super(swtEventListener);
    }
    
    public void handleEvent(final Event event) {
        switch (event.type) {
            case 2008: {
                final DragSourceEvent dragSourceEvent = new DragSourceEvent((DNDEvent)event);
                final DragSourceEffect dragSourceEffect = ((DragSource)this.dndWidget).getDragSourceEffect();
                if (dragSourceEffect != null) {
                    dragSourceEffect.dragStart(dragSourceEvent);
                }
                ((DragSourceListener)this.eventListener).dragStart(dragSourceEvent);
                dragSourceEvent.updateEvent((DNDEvent)event);
                break;
            }
            case 2000: {
                final DragSourceEvent dragSourceEvent2 = new DragSourceEvent((DNDEvent)event);
                final DragSourceEffect dragSourceEffect2 = ((DragSource)this.dndWidget).getDragSourceEffect();
                if (dragSourceEffect2 != null) {
                    dragSourceEffect2.dragFinished(dragSourceEvent2);
                }
                ((DragSourceListener)this.eventListener).dragFinished(dragSourceEvent2);
                dragSourceEvent2.updateEvent((DNDEvent)event);
                break;
            }
            case 2001: {
                final DragSourceEvent dragSourceEvent3 = new DragSourceEvent((DNDEvent)event);
                final DragSourceEffect dragSourceEffect3 = ((DragSource)this.dndWidget).getDragSourceEffect();
                if (dragSourceEffect3 != null) {
                    dragSourceEffect3.dragSetData(dragSourceEvent3);
                }
                ((DragSourceListener)this.eventListener).dragSetData(dragSourceEvent3);
                dragSourceEvent3.updateEvent((DNDEvent)event);
                break;
            }
            case 2002: {
                final DropTargetEvent dropTargetEvent = new DropTargetEvent((DNDEvent)event);
                ((DropTargetListener)this.eventListener).dragEnter(dropTargetEvent);
                final DropTargetEffect dropTargetEffect = ((DropTarget)this.dndWidget).getDropTargetEffect();
                if (dropTargetEffect != null) {
                    dropTargetEffect.dragEnter(dropTargetEvent);
                }
                dropTargetEvent.updateEvent((DNDEvent)event);
                break;
            }
            case 2003: {
                final DropTargetEvent dropTargetEvent2 = new DropTargetEvent((DNDEvent)event);
                ((DropTargetListener)this.eventListener).dragLeave(dropTargetEvent2);
                final DropTargetEffect dropTargetEffect2 = ((DropTarget)this.dndWidget).getDropTargetEffect();
                if (dropTargetEffect2 != null) {
                    dropTargetEffect2.dragLeave(dropTargetEvent2);
                }
                dropTargetEvent2.updateEvent((DNDEvent)event);
                break;
            }
            case 2004: {
                final DropTargetEvent dropTargetEvent3 = new DropTargetEvent((DNDEvent)event);
                ((DropTargetListener)this.eventListener).dragOver(dropTargetEvent3);
                final DropTargetEffect dropTargetEffect3 = ((DropTarget)this.dndWidget).getDropTargetEffect();
                if (dropTargetEffect3 != null) {
                    dropTargetEffect3.dragOver(dropTargetEvent3);
                }
                dropTargetEvent3.updateEvent((DNDEvent)event);
                break;
            }
            case 2006: {
                final DropTargetEvent dropTargetEvent4 = new DropTargetEvent((DNDEvent)event);
                ((DropTargetListener)this.eventListener).drop(dropTargetEvent4);
                final DropTargetEffect dropTargetEffect4 = ((DropTarget)this.dndWidget).getDropTargetEffect();
                if (dropTargetEffect4 != null) {
                    dropTargetEffect4.drop(dropTargetEvent4);
                }
                dropTargetEvent4.updateEvent((DNDEvent)event);
                break;
            }
            case 2007: {
                final DropTargetEvent dropTargetEvent5 = new DropTargetEvent((DNDEvent)event);
                ((DropTargetListener)this.eventListener).dropAccept(dropTargetEvent5);
                final DropTargetEffect dropTargetEffect5 = ((DropTarget)this.dndWidget).getDropTargetEffect();
                if (dropTargetEffect5 != null) {
                    dropTargetEffect5.dropAccept(dropTargetEvent5);
                }
                dropTargetEvent5.updateEvent((DNDEvent)event);
                break;
            }
            case 2005: {
                final DropTargetEvent dropTargetEvent6 = new DropTargetEvent((DNDEvent)event);
                ((DropTargetListener)this.eventListener).dragOperationChanged(dropTargetEvent6);
                final DropTargetEffect dropTargetEffect6 = ((DropTarget)this.dndWidget).getDropTargetEffect();
                if (dropTargetEffect6 != null) {
                    dropTargetEffect6.dragOperationChanged(dropTargetEvent6);
                }
                dropTargetEvent6.updateEvent((DNDEvent)event);
                break;
            }
        }
    }
}
