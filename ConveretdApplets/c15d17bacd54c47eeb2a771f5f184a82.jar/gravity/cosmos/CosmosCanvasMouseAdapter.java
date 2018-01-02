// 
// Decompiled by Procyon v0.5.30
// 

package gravity.cosmos;

import java.awt.event.MouseEvent;
import gravity.tools.AssertionException;
import gravity.tools.Assert;
import gravity.tools.Vector2D;
import gravity.gameObjects.GameObject;
import gravity.ILauncher;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;

public class CosmosCanvasMouseAdapter extends MouseAdapter implements MouseMotionListener
{
    private final double DBLCLICKRADIUS;
    private ILauncher _ui;
    private CosmosCanvas _cc;
    private GameObject _gameObject;
    private Vector2D _lastClick;
    private boolean _closingZoomOutBox;
    
    public CosmosCanvasMouseAdapter(final ILauncher ui, final CosmosCanvas cc, final GameObject gameObject) throws AssertionException {
        this.DBLCLICKRADIUS = Math.sqrt(2.2);
        this._closingZoomOutBox = false;
        this._ui = ui;
        this._cc = cc;
        this._gameObject = gameObject;
        Assert.assert(this._ui != null && this._cc != null && this._gameObject != null);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this._cc.isInsideZoomOutBox(new Vector2D(mouseEvent.getX(), mouseEvent.getY()))) {
            this._closingZoomOutBox = true;
            return;
        }
        if (this._ui.getState() == 0) {
            this._ui.setAngle(this.calculateAngle(new Vector2D((mouseEvent.getX() * 1.0 - this._cc.getOrigin().x) / this._cc.getZoomFactor().x - this._gameObject.getCoord().x, (mouseEvent.getY() * 1.0 - this._cc.getOrigin().y) / this._cc.getZoomFactor().y - this._gameObject.getCoord().y)));
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this._closingZoomOutBox) {
            if (this._cc.isInsideZoomOutBox(new Vector2D(mouseEvent.getX(), mouseEvent.getY()))) {
                this._cc.reset(true);
            }
            this._ui.setReady();
            this._closingZoomOutBox = false;
            return;
        }
        switch (this._ui.getState()) {
            case 0: {
                this._ui.setAngle(this.calculateAngle(new Vector2D((mouseEvent.getX() * 1.0 - this._cc.getOrigin().x) / this._cc.getZoomFactor().x - this._gameObject.getCoord().x, (mouseEvent.getY() * 1.0 - this._cc.getOrigin().y) / this._cc.getZoomFactor().y - this._gameObject.getCoord().y)));
                if (this._lastClick == null) {
                    this._lastClick = new Vector2D(mouseEvent.getX(), mouseEvent.getY());
                    break;
                }
                if (Math.pow(this._lastClick.x - mouseEvent.getX(), 2.0) + Math.pow(this._lastClick.y - mouseEvent.getY(), 2.0) <= Math.pow(this.DBLCLICKRADIUS, 2.0)) {
                    this._ui.launch();
                    this._lastClick = null;
                    break;
                }
                this._ui.setReady();
                this._lastClick = new Vector2D(mouseEvent.getX(), mouseEvent.getY());
                break;
            }
            case 1: {
                this._ui.abort();
                break;
            }
            case -1:
            case 2: {
                this._ui.reset();
                break;
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this._closingZoomOutBox) {
            return;
        }
        if (this._ui.getState() == 0) {
            this._ui.setAngle(this.calculateAngle(new Vector2D((mouseEvent.getX() * 1.0 - this._cc.getOrigin().x) / this._cc.getZoomFactor().x - this._gameObject.getCoord().x, (mouseEvent.getY() * 1.0 - this._cc.getOrigin().y) / this._cc.getZoomFactor().y - this._gameObject.getCoord().y)));
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    private double calculateAngle(final Vector2D vector2D) {
        return Math.floor(Math.atan2(-vector2D.y, vector2D.x) * 180.0 / 3.141592653589793 * 100000.0) / 100000.0;
    }
}
