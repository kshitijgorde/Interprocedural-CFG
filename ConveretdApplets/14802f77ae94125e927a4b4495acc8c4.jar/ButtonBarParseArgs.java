// 
// Decompiled by Procyon v0.5.30
// 

public class ButtonBarParseArgs extends ButtonBar
{
    public ButtonBarParseArgs(final ButtonBar buttonBar) {
        final String parameter = buttonBar.getParameter("buttnum");
        try {
            ButtonBar.buttnum = Integer.parseInt(parameter);
        }
        catch (Exception ex) {
            ButtonBar.buttnum = 0;
        }
        ButtonBar.mapx1 = new int[ButtonBar.buttnum];
        ButtonBar.mapy1 = new int[ButtonBar.buttnum];
        ButtonBar.mapx2 = new int[ButtonBar.buttnum];
        ButtonBar.mapy2 = new int[ButtonBar.buttnum];
        ButtonBar.msgnum = new int[ButtonBar.buttnum];
        ButtonBar.x1 = new int[ButtonBar.buttnum];
        ButtonBar.y1 = new int[ButtonBar.buttnum];
        ButtonBar.left = new boolean[ButtonBar.buttnum];
        ButtonBar.msgtext = new String[ButtonBar.buttnum][15];
        ButtonBar.textURL = new String[ButtonBar.buttnum][15];
        for (int i = 0; i < ButtonBar.buttnum; ++i) {
            Boolean value = new Boolean(false);
            final String parameter2 = buttonBar.getParameter("left" + i);
            try {
                value = Boolean.valueOf(parameter2);
            }
            catch (Exception ex2) {
                ButtonBar.left[i] = false;
            }
            ButtonBar.left[i] = value;
            final String parameter3 = buttonBar.getParameter("mapx1" + i);
            try {
                ButtonBar.mapx1[i] = Integer.parseInt(parameter3);
            }
            catch (Exception ex3) {
                ButtonBar.mapx1[i] = 0;
            }
            final String parameter4 = buttonBar.getParameter("mapy1" + i);
            try {
                ButtonBar.mapy1[i] = Integer.parseInt(parameter4);
            }
            catch (Exception ex4) {
                ButtonBar.mapy1[i] = 0;
            }
            final String parameter5 = buttonBar.getParameter("mapx2" + i);
            try {
                ButtonBar.mapx2[i] = Integer.parseInt(parameter5);
            }
            catch (Exception ex5) {
                ButtonBar.mapx2[i] = 0;
            }
            final String parameter6 = buttonBar.getParameter("mapy2" + i);
            try {
                ButtonBar.mapy2[i] = Integer.parseInt(parameter6);
            }
            catch (Exception ex6) {
                ButtonBar.mapy2[i] = 0;
            }
            final String parameter7 = buttonBar.getParameter("msgnum" + i);
            try {
                ButtonBar.msgnum[i] = Integer.parseInt(parameter7);
            }
            catch (Exception ex7) {
                ButtonBar.msgnum[i] = 0;
            }
            final String parameter8 = buttonBar.getParameter("x1" + i);
            try {
                ButtonBar.x1[i] = Integer.parseInt(parameter8);
            }
            catch (Exception ex8) {
                ButtonBar.x1[i] = 0;
            }
            final String parameter9 = buttonBar.getParameter("y1" + i);
            try {
                ButtonBar.y1[i] = Integer.parseInt(parameter9);
            }
            catch (Exception ex9) {
                ButtonBar.y1[i] = 0;
            }
            for (int j = 0; j < ButtonBar.msgnum[i]; ++j) {
                ButtonBar.msgtext[i][j] = buttonBar.getParameter("msgtext" + i + j);
                try {
                    ButtonBar.textURL[i][j] = buttonBar.getParameter("textURL" + i + j);
                }
                catch (Exception ex10) {
                    ButtonBar.textURL[i][j] = "";
                }
            }
        }
        ButtonBar.fontname = buttonBar.getParameter("font");
        if (ButtonBar.fontname == null) {
            ButtonBar.fontname = new String("Arial");
        }
        final String parameter10 = buttonBar.getParameter("fontsize");
        try {
            ButtonBar.fontsize = Integer.parseInt(parameter10);
        }
        catch (Exception ex11) {
            ButtonBar.fontsize = 36;
        }
    }
}
