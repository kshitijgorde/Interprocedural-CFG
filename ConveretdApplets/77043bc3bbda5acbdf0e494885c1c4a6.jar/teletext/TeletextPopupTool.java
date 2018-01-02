// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

import java.awt.MenuItem;
import java.awt.event.ActionListener;
import java.awt.Menu;
import java.awt.PopupMenu;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class TeletextPopupTool extends TeletextTool implements TeletextToolInterface
{
    public void implementMousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x4) > 0) {
            this.getPageCanvas().moveCursorToPixel(mouseEvent.getX(), mouseEvent.getY());
            this.getPageCanvas().repaint();
            this.managePopup(mouseEvent);
        }
    }
    
    public void implementMouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void implementMouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void managePopup(final MouseEvent mouseEvent) {
        final PopupMenu popupMenu = new PopupMenu("Teletext");
        this.getPageCanvas().add(popupMenu);
        final Menu menu = new Menu("Codes");
        menu.addActionListener(this.getPageCanvas());
        final Menu menu2 = new Menu("Text codes");
        menu2.addActionListener(this.getPageCanvas());
        final MenuItem menuItem = new MenuItem("BLACK");
        menuItem.setActionCommand("insertAlphaBlackCode");
        menu2.add(menuItem);
        final MenuItem menuItem2 = new MenuItem("RED");
        menuItem2.setActionCommand("insertAlphaRedCode");
        menu2.add(menuItem2);
        final MenuItem menuItem3 = new MenuItem("GREEN");
        menuItem3.setActionCommand("insertAlphaGreenCode");
        menu2.add(menuItem3);
        final MenuItem menuItem4 = new MenuItem("YELLOW");
        menuItem4.setActionCommand("insertAlphaYellowCode");
        menu2.add(menuItem4);
        final MenuItem menuItem5 = new MenuItem("BLUE");
        menuItem5.setActionCommand("insertAlphaBlueCode");
        menu2.add(menuItem5);
        final MenuItem menuItem6 = new MenuItem("MAGENTA");
        menuItem6.setActionCommand("insertAlphaMagentaCode");
        menu2.add(menuItem6);
        final MenuItem menuItem7 = new MenuItem("CYAN");
        menuItem7.setActionCommand("insertAlphaCyanCode");
        menu2.add(menuItem7);
        final MenuItem menuItem8 = new MenuItem("WHITE");
        menuItem8.setActionCommand("insertAlphaWhiteCode");
        menu2.add(menuItem8);
        final Menu menu3 = new Menu("Graphics codes");
        menu3.addActionListener(this.getPageCanvas());
        final MenuItem menuItem9 = new MenuItem("BLACK");
        menuItem9.setActionCommand("insertGraphBlackCode");
        menu3.add(menuItem9);
        final MenuItem menuItem10 = new MenuItem("RED");
        menuItem10.setActionCommand("insertGraphRedCode");
        menu3.add(menuItem10);
        final MenuItem menuItem11 = new MenuItem("GREEN");
        menuItem11.setActionCommand("insertGraphGreenCode");
        menu3.add(menuItem11);
        final MenuItem menuItem12 = new MenuItem("YELLOW");
        menuItem12.setActionCommand("insertGraphYellowCode");
        menu3.add(menuItem12);
        final MenuItem menuItem13 = new MenuItem("BLUE");
        menuItem13.setActionCommand("insertGraphBlueCode");
        menu3.add(menuItem13);
        final MenuItem menuItem14 = new MenuItem("MAGENTA");
        menuItem14.setActionCommand("insertGraphMagentaCode");
        menu3.add(menuItem14);
        final MenuItem menuItem15 = new MenuItem("CYAN");
        menuItem15.setActionCommand("insertGraphCyanCode");
        menu3.add(menuItem15);
        final MenuItem menuItem16 = new MenuItem("WHITE");
        menuItem16.setActionCommand("insertGraphWhiteCode");
        menu3.add(menuItem16);
        final Menu menu4 = new Menu("Other codes");
        menu4.addActionListener(this.getPageCanvas());
        final MenuItem menuItem17 = new MenuItem("STEADY");
        menuItem17.setActionCommand("insertSteadyCode");
        menu4.add(menuItem17);
        final MenuItem menuItem18 = new MenuItem("FLASH");
        menuItem18.setActionCommand("insertFlashCode");
        menu4.add(menuItem18);
        final MenuItem menuItem19 = new MenuItem("END BOX");
        menuItem19.setActionCommand("insertEndBoxCode");
        menu4.add(menuItem19);
        final MenuItem menuItem20 = new MenuItem("START BOX");
        menuItem20.setActionCommand("insertStartBoxCode");
        menu4.add(menuItem20);
        final MenuItem menuItem21 = new MenuItem("NORMAL HEIGHT");
        menuItem21.setActionCommand("insertNormalHeightCode");
        menu4.add(menuItem21);
        final MenuItem menuItem22 = new MenuItem("DOUBLE HEIGHT");
        menuItem22.setActionCommand("insertDoubleHeightCode");
        menu4.add(menuItem22);
        final MenuItem menuItem23 = new MenuItem("CONCEAL");
        menuItem23.setActionCommand("insertConcealCode");
        menu4.add(menuItem23);
        final MenuItem menuItem24 = new MenuItem("GLUE GRAPHICS");
        menuItem24.setActionCommand("insertGlueGraphicsCode");
        menu4.add(menuItem24);
        final MenuItem menuItem25 = new MenuItem("SEPARATE GRAPHICS");
        menuItem25.setActionCommand("insertSeparateGraphicsCode");
        menu4.add(menuItem25);
        final MenuItem menuItem26 = new MenuItem("AUTONUMBER");
        menuItem26.setActionCommand("insertAutonumberCode");
        menu4.add(menuItem26);
        final MenuItem menuItem27 = new MenuItem("BLACK BACKGROUND");
        menuItem27.setActionCommand("insertBlackBackgroundCode");
        menu4.add(menuItem27);
        final MenuItem menuItem28 = new MenuItem("NEW BACKGROUND");
        menuItem28.setActionCommand("insertNewBackgroundCode");
        menu4.add(menuItem28);
        final MenuItem menuItem29 = new MenuItem("HOLD GRAPHICS");
        menuItem29.setActionCommand("insertHoldGraphicsCode");
        menu4.add(menuItem29);
        final MenuItem menuItem30 = new MenuItem("RELEASE GRAPHICS");
        menuItem30.setActionCommand("insertReleaseGraphicsCode");
        menu4.add(menuItem30);
        menu.add(menu2);
        menu.add(menu3);
        menu.add(menu4);
        popupMenu.add(menu);
        final Menu menu5 = new Menu("Graphics functions");
        menu5.addActionListener(this.getPageCanvas());
        final MenuItem menuItem31 = new MenuItem("Select");
        menuItem31.setActionCommand("graphics:select");
        menu5.add(menuItem31);
        final MenuItem menuItem32 = new MenuItem("Line");
        menuItem32.setActionCommand("graphics:line");
        menu5.add(menuItem32);
        final MenuItem menuItem33 = new MenuItem("Rectangle");
        menuItem33.setActionCommand("graphics:rectangle");
        menu5.add(menuItem33);
        final MenuItem menuItem34 = new MenuItem("Ellipse");
        menuItem34.setActionCommand("graphics:ellipse");
        menu5.add(menuItem34);
        popupMenu.add(menu5);
        popupMenu.addActionListener(this.getPageCanvas());
        popupMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
    }
}
