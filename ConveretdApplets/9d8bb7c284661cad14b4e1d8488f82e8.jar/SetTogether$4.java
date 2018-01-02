import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class SetTogether$4 extends MouseAdapter
{
    private final SetTogether \u026c;
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.\u026c.\u0139.inRoom()) {
            this.\u026c.\u0139.unblock();
            this.\u026c.\u03c7.hide();
            this.\u026c.\u03cd.setText(this.\u026c.\u0271B);
            this.\u026c.\u03ce.setText(this.\u026c.\u0272B);
            this.\u026c.\u03d0.setText(this.\u026c.\u0273B);
            this.\u026c.\u03cc = 0;
            this.\u026c.\u03ca.show();
            return;
        }
        if (this.\u026c.\u0139.enterWaitingRoom(this.\u026c.\u026b, String.valueOf(this.\u026c.\u028f))) {
            this.\u026c.\u013f = this.\u026c.\u0139.getWaitingRoomName();
            this.\u026c.\u0292B = true;
            this.\u026c.\u0253B.setText(this.\u026c.\u027eB);
            this.\u026c.\u0254B.setText(this.\u026c.\u027fB);
            this.\u026c.\u0255B.setText(this.\u026c.\u0280B);
            this.\u026c.\u042c.hide();
            return;
        }
        final String errorMessage = this.\u026c.\u0139.getErrorMessage();
        if (errorMessage.length() > 15) {
            this.\u026c.\u016cB.setText(errorMessage.substring(0, 15));
            this.\u026c.\u016dB.setText(errorMessage.substring(15));
        }
        else {
            this.\u026c.\u016cB.setText(errorMessage);
        }
        System.out.println("error [" + errorMessage + "]");
        this.\u026c.\u015cB.show(true);
        this.\u026c.\u0158B.show(false);
        this.\u026c.\u03c9.show(false);
        this.\u026c.\u0164B.show(true);
        this.\u026c.\u0139.unblock();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.\u026c.\u042c.setMouseEnter();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.\u026c.\u042c.setMouseExit();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.\u026c.\u042c.setMouseDown();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.\u026c.\u042c.setMouseUp();
    }
    
    SetTogether$4(final SetTogether \u026c) {
        this.\u026c = \u026c;
    }
}
