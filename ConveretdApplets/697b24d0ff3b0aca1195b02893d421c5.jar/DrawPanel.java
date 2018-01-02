import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import javax.swing.ImageIcon;
import java.awt.Paint;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Point;
import java.util.Vector;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, Runnable
{
    private static final int HEIGHT = 500;
    private static final int WIDTH = 550;
    private static final int SCRIBBLE = 1;
    private static final int SPRAY = 2;
    private static final int DELETE = 3;
    private static final int NODES = 4;
    private static final int S1 = 4;
    private static final int S2 = 5;
    private static final int S3 = 6;
    private static final int S4 = 9;
    private static final int S5 = 10;
    private static final int S6 = 11;
    private static final int DELETE_SIZE = 32;
    private static final int SMALL_OFFSET = 7;
    private static final int BIG_OFFSET = 15;
    private static final int FILL = 7;
    private static final int STROKE = 8;
    private BufferedImage currentImage;
    private BufferedImage oldImage;
    private BufferedImage tempImage;
    private BufferedImage storedImage;
    private Color drawColor;
    private Color demColor;
    private Color repColor;
    private Color nonColor;
    private Polygon[] statesEdge;
    private String[] statesName;
    private int[] statesX;
    private int[] statesY;
    private int[] statesVote;
    private int[] xPoints;
    private int[] yPoints;
    private int numPoints;
    private int demVotes;
    private int repVotes;
    private int i;
    private int j;
    private int tempx;
    private int tempy;
    private int lastY;
    private int lastX;
    private int bgColor;
    private int mode;
    private PanelMediator mediator;
    private static Image mImage;
    private Cursor scribblePointer;
    private Cursor sprayPointer;
    private Cursor rubberPointer;
    private Cursor strokePointer;
    private Cursor fillPointer;
    private Cursor stampPointer;
    private boolean oldIsOld;
    private Thread runner;
    private int tX;
    private int tY;
    private boolean stop;
    private boolean checkUp;
    private boolean checkDown;
    private int fillX;
    private int fillY;
    private int picNo;
    private Vector fillPoints;
    private Point addPoint;
    public URL URL_STRING;
    private URL stamp1;
    private URL stamp2;
    private URL stamp3;
    private URL stamp4;
    private URL stamp5;
    private URL stamp6;
    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;
    private Image image5;
    private Image image6;
    private Image bg0;
    private Image bg1;
    private Image bg2;
    private Image bg3;
    private Image bgImage;
    
    public DrawPanel(final PanelMediator mediator) {
        this.drawColor = Color.blue;
        this.demColor = Color.blue;
        this.repColor = Color.red;
        this.nonColor = Color.white;
        this.mode = 7;
        this.oldIsOld = true;
        this.stop = true;
        this.checkUp = false;
        this.checkDown = false;
        this.picNo = 0;
        this.statesEdge = new Polygon[52];
        this.statesName = new String[52];
        this.statesX = new int[52];
        this.statesY = new int[52];
        this.statesVote = new int[52];
        final int[] array = { 299, 310, 310, 336, 328, 299 };
        final int[] array2 = { 300, 300, 297, 297, 257, 257 };
        final int[] array3 = { 68, 76, 92, 89, 71 };
        final int[] array4 = { 320, 323, 317, 299, 299 };
        final int[] array5 = { 114, 142, 142, 117, 114 };
        final int[] array6 = { 232, 242, 196, 191, 195 };
        final int[] array7 = { 243, 269, 272, 275, 265, 235, 241 };
        final int[] array8 = { 260, 261, 254, 240, 232, 232, 252 };
        final int[] array9 = { 39, 114, 114, 96, 98, 23, 9, 19 };
        final int[] array10 = { 236, 232, 195, 168, 154, 132, 176, 213 };
        final int[] array11 = { 142, 163, 180, 180, 180, 144 };
        final int[] array12 = { 196, 198, 200, 179, 165, 165 };
        final int[] array13 = { 465, 499, 491, 459 };
        final int[] array14 = { 148, 127, 99, 107 };
        final int[] array15 = { 439, 456, 458, 439, 427 };
        final int[] array16 = { 219, 219, 211, 192, 173 };
        final int[] array17 = { 386, 402, 417, 402 };
        final int[] array18 = { 198, 213, 198, 183 };
        final int[] array19 = { 310, 335, 353, 365, 372, 421, 427, 394, 336, 310 };
        final int[] array20 = { 300, 306, 303, 306, 357, 405, 363, 300, 297, 297 };
        final int[] array21 = { 336, 394, 395, 360, 337, 328 };
        final int[] array22 = { 297, 300, 294, 257, 257, 257 };
        final int[] array23 = { 179, 199, 180, 168 };
        final int[] array24 = { 352, 333, 312, 320 };
        final int[] array25 = { 110, 120, 135, 137, 130, 128, 118, 114 };
        final int[] array26 = { 157, 159, 162, 146, 144, 130, 128, 139 };
        final int[] array27 = { 278, 288, 292, 290, 285, 255, 237 };
        final int[] array28 = { 237, 228, 205, 149, 143, 145, 174 };
        final int[] array29 = { 288, 308, 320, 309, 293, 290, 292 };
        final int[] array30 = { 228, 221, 206, 146, 148, 149, 205 };
        final int[] array31 = { 209, 237, 255, 252, 202, 202 };
        final int[] array32 = { 174, 174, 145, 143, 152, 157 };
        final int[] array33 = { 180, 230, 230, 211, 180 };
        final int[] array34 = { 200, 202, 196, 179, 179 };
        final int[] array35 = { 278, 339, 351, 345, 320, 308, 288 };
        final int[] array36 = { 237, 233, 218, 209, 206, 221, 228 };
        final int[] array37 = { 245, 281, 292, 287, 265, 271, 269, 243 };
        final int[] array38 = { 297, 309, 302, 287, 287, 271, 261, 260 };
        final int[] array39 = { 483, 495, 482, 474, 471 };
        final int[] array40 = { 65, 40, 19, 20, 33 };
        final int[] array41 = { 375, 385, 402, 417, 402, 434, 456, 439, 427, 372 };
        final int[] array42 = { 189, 180, 183, 198, 213, 233, 219, 219, 173, 177 };
        final int[] array43 = { 459, 491, 515, 523, 539, 483, 458, 450 };
        final int[] array44 = { 107, 99, 98, 112, 111, 65, 75, 75 };
        final int[] array45 = { 293, 309, 339, 348, 343, 336, 337, 309, 292, 297 };
        final int[] array46 = { 148, 146, 140, 116, 101, 105, 92, 79, 106, 127 };
        final int[] array47 = { 202, 252, 240, 245, 196, 201 };
        final int[] array48 = { 152, 143, 130, 100, 129, 143 };
        final int[] array49 = { 265, 287, 292, 299, 299, 272, 269, 271 };
        final int[] array50 = { 287, 287, 302, 300, 257, 254, 261, 271 };
        final int[] array51 = { 235, 265, 275, 278, 237, 209, 211, 230, 230 };
        final int[] array52 = { 232, 232, 240, 237, 174, 174, 179, 196, 202 };
        final int[] array53 = { 130, 137, 161, 163, 163, 128 };
        final int[] array54 = { 144, 146, 147, 145, 135, 130 };
        final int[] array55 = { 161, 180, 180, 211, 209, 202, 161 };
        final int[] array56 = { 165, 165, 179, 179, 174, 157, 157 };
        final int[] array57 = { 114, 117, 120, 110, 98, 96 };
        final int[] array58 = { 195, 191, 159, 157, 154, 168 };
        final int[] array59 = { 458, 483, 471, 462 };
        final int[] array60 = { 75, 65, 33, 40 };
        final int[] array61 = { 427, 439, 458, 470, 465, 427, 427, 435 };
        final int[] array62 = { 173, 192, 211, 185, 148, 131, 152, 163 };
        final int[] array63 = { 142, 149, 160, 163, 163, 142 };
        final int[] array64 = { 242, 235, 235, 202, 198, 196 };
        final int[] array65 = { 360, 415, 427, 465, 459, 450, 442, 410, 401, 377 };
        final int[] array66 = { 127, 117, 131, 148, 107, 75, 46, 56, 90, 98 };
        final int[] array67 = { 337, 360, 387, 420, 437, 434, 366 };
        final int[] array68 = { 257, 257, 252, 268, 253, 233, 233 };
        final int[] array69 = { 163, 201, 196, 163 };
        final int[] array70 = { 145, 143, 129, 135 };
        final int[] array71 = { 320, 345, 368, 360, 339, 309 };
        final int[] array72 = { 206, 209, 177, 127, 140, 146 };
        final int[] array73 = { 163, 209, 209, 241, 235, 230, 180, 163 };
        final int[] array74 = { 202, 206, 246, 252, 232, 202, 200, 198 };
        final int[] array75 = { 23, 98, 110, 114, 25 };
        final int[] array76 = { 132, 154, 157, 139, 127 };
        final int[] array77 = { 368, 372, 427, 435, 427, 427, 415, 360 };
        final int[] array78 = { 177, 177, 173, 163, 152, 131, 117, 127 };
        final int[] array79 = { 499, 523, 515, 491 };
        final int[] array80 = { 127, 112, 98, 99 };
        final int[] array81 = { 395, 420, 387, 360 };
        final int[] array82 = { 294, 268, 252, 257 };
        final int[] array83 = { 161, 202, 202, 201, 163, 161 };
        final int[] array84 = { 157, 157, 152, 143, 145, 147 };
        final int[] array85 = { 272, 299, 328, 337, 366, 339, 278, 275 };
        final int[] array86 = { 254, 257, 257, 257, 233, 233, 237, 240 };
        final int[] array87 = { 149, 182, 198, 232, 236, 245, 243, 241, 209, 209, 163, 160 };
        final int[] array88 = { 235, 276, 263, 312, 302, 297, 260, 252, 246, 206, 202, 235 };
        final int[] array89 = { 117, 142, 144, 135, 135, 120 };
        final int[] array90 = { 191, 196, 165, 165, 162, 159 };
        final int[] array91 = { 450, 458, 462, 442 };
        final int[] array92 = { 75, 75, 40, 46 };
        final int[] array93 = { 339, 366, 434, 402, 386, 402, 385, 369, 351 };
        final int[] array94 = { 233, 233, 233, 213, 198, 183, 180, 210, 218 };
        final int[] array95 = { 25, 114, 118, 51, 50, 32, 29 };
        final int[] array96 = { 127, 139, 128, 106, 112, 102, 102 };
        final int[] array97 = { 351, 369, 385, 375, 372, 368, 345 };
        final int[] array98 = { 218, 210, 180, 189, 177, 177, 209 };
        final int[] array99 = { 252, 255, 285, 287, 281, 245, 240 };
        final int[] array100 = { 143, 145, 143, 116, 106, 100, 130 };
        final int[] array101 = { 135, 143, 161, 161, 161, 137, 135 };
        final int[] array102 = { 165, 165, 165, 157, 147, 146, 162 };
        this.statesEdge[1] = new Polygon(array, array2, array.length);
        this.statesEdge[2] = new Polygon(array3, array4, array3.length);
        this.statesEdge[3] = new Polygon(array5, array6, array5.length);
        this.statesEdge[4] = new Polygon(array7, array8, array7.length);
        this.statesEdge[5] = new Polygon(array9, array10, array9.length);
        this.statesEdge[6] = new Polygon(array11, array12, array11.length);
        this.statesEdge[7] = new Polygon(array13, array14, array13.length);
        this.statesEdge[8] = new Polygon(array15, array16, array15.length);
        this.statesEdge[9] = new Polygon(array17, array18, array17.length);
        this.statesEdge[10] = new Polygon(array19, array20, array19.length);
        this.statesEdge[11] = new Polygon(array21, array22, array21.length);
        this.statesEdge[12] = new Polygon(array23, array24, array23.length);
        this.statesEdge[13] = new Polygon(array25, array26, array25.length);
        this.statesEdge[14] = new Polygon(array27, array28, array27.length);
        this.statesEdge[15] = new Polygon(array29, array30, array29.length);
        this.statesEdge[16] = new Polygon(array31, array32, array31.length);
        this.statesEdge[17] = new Polygon(array33, array34, array33.length);
        this.statesEdge[18] = new Polygon(array35, array36, array35.length);
        this.statesEdge[19] = new Polygon(array37, array38, array37.length);
        this.statesEdge[20] = new Polygon(array39, array40, array39.length);
        this.statesEdge[21] = new Polygon(array41, array42, array41.length);
        this.statesEdge[22] = new Polygon(array43, array44, array43.length);
        this.statesEdge[23] = new Polygon(array45, array46, array45.length);
        this.statesEdge[24] = new Polygon(array47, array48, array47.length);
        this.statesEdge[25] = new Polygon(array49, array50, array49.length);
        this.statesEdge[26] = new Polygon(array51, array52, array51.length);
        this.statesEdge[27] = new Polygon(array53, array54, array53.length);
        this.statesEdge[28] = new Polygon(array55, array56, array55.length);
        this.statesEdge[29] = new Polygon(array57, array58, array57.length);
        this.statesEdge[30] = new Polygon(array59, array60, array59.length);
        this.statesEdge[31] = new Polygon(array61, array62, array61.length);
        this.statesEdge[32] = new Polygon(array63, array64, array63.length);
        this.statesEdge[33] = new Polygon(array65, array66, array65.length);
        this.statesEdge[34] = new Polygon(array67, array68, array67.length);
        this.statesEdge[35] = new Polygon(array69, array70, array69.length);
        this.statesEdge[36] = new Polygon(array71, array72, array71.length);
        this.statesEdge[37] = new Polygon(array73, array74, array73.length);
        this.statesEdge[38] = new Polygon(array75, array76, array75.length);
        this.statesEdge[39] = new Polygon(array77, array78, array77.length);
        this.statesEdge[40] = new Polygon(array79, array80, array79.length);
        this.statesEdge[41] = new Polygon(array81, array82, array81.length);
        this.statesEdge[42] = new Polygon(array83, array84, array83.length);
        this.statesEdge[43] = new Polygon(array85, array86, array85.length);
        this.statesEdge[44] = new Polygon(array87, array88, array87.length);
        this.statesEdge[45] = new Polygon(array89, array90, array89.length);
        this.statesEdge[46] = new Polygon(array91, array92, array91.length);
        this.statesEdge[47] = new Polygon(array93, array94, array93.length);
        this.statesEdge[48] = new Polygon(array95, array96, array95.length);
        this.statesEdge[49] = new Polygon(array97, array98, array97.length);
        this.statesEdge[50] = new Polygon(array99, array100, array99.length);
        this.statesEdge[51] = new Polygon(array101, array102, array101.length);
        this.statesName[1] = "AL";
        this.statesX[1] = 309;
        this.statesY[1] = 283;
        this.statesVote[1] = 9;
        this.statesName[2] = "AK";
        this.statesX[2] = 73;
        this.statesY[2] = 315;
        this.statesVote[2] = 3;
        this.statesName[3] = "AZ";
        this.statesX[3] = 120;
        this.statesY[3] = 220;
        this.statesVote[3] = 10;
        this.statesName[4] = "AR";
        this.statesX[4] = 249;
        this.statesY[4] = 253;
        this.statesVote[4] = 6;
        this.statesName[5] = "CA";
        this.statesX[5] = 50;
        this.statesY[5] = 195;
        this.statesVote[5] = 55;
        this.statesName[6] = "CO";
        this.statesX[6] = 153;
        this.statesY[6] = 187;
        this.statesVote[6] = 9;
        this.statesName[7] = "CT";
        this.statesX[7] = 470;
        this.statesY[7] = 129;
        this.statesVote[7] = 7;
        this.statesName[8] = "DE";
        this.statesX[8] = 440;
        this.statesY[8] = 218;
        this.statesVote[8] = 3;
        this.statesName[9] = "DC";
        this.statesX[9] = 394;
        this.statesY[9] = 203;
        this.statesVote[9] = 3;
        this.statesName[10] = "FL";
        this.statesX[10] = 385;
        this.statesY[10] = 350;
        this.statesVote[10] = 27;
        this.statesName[11] = "GA";
        this.statesX[11] = 346;
        this.statesY[11] = 285;
        this.statesVote[11] = 15;
        this.statesName[12] = "HI";
        this.statesX[12] = 176;
        this.statesY[12] = 336;
        this.statesVote[12] = 4;
        this.statesName[13] = "ID";
        this.statesX[13] = 117;
        this.statesY[13] = 152;
        this.statesVote[13] = 4;
        this.statesName[14] = "IL";
        this.statesX[14] = 260;
        this.statesY[14] = 185;
        this.statesVote[14] = 21;
        this.statesName[15] = "IN";
        this.statesX[15] = 296;
        this.statesY[15] = 190;
        this.statesVote[15] = 11;
        this.statesName[16] = "IA";
        this.statesX[16] = 218;
        this.statesY[16] = 167;
        this.statesVote[16] = 7;
        this.statesName[17] = "KS";
        this.statesX[17] = 195;
        this.statesY[17] = 195;
        this.statesVote[17] = 6;
        this.statesName[18] = "KY";
        this.statesX[18] = 320;
        this.statesY[18] = 225;
        this.statesVote[18] = 8;
        this.statesName[19] = "LA";
        this.statesX[19] = 250;
        this.statesY[19] = 280;
        this.statesVote[19] = 9;
        this.statesName[20] = "ME";
        this.statesX[20] = 476;
        this.statesY[20] = 44;
        this.statesVote[20] = 4;
        this.statesName[21] = "MD";
        this.statesX[21] = 415;
        this.statesY[21] = 215;
        this.statesVote[21] = 10;
        this.statesName[22] = "MA";
        this.statesX[22] = 470;
        this.statesY[22] = 90;
        this.statesVote[22] = 12;
        this.statesName[23] = "MI";
        this.statesX[23] = 312;
        this.statesY[23] = 123;
        this.statesVote[23] = 17;
        this.statesName[24] = "MN";
        this.statesX[24] = 215;
        this.statesY[24] = 136;
        this.statesVote[24] = 10;
        this.statesName[25] = "MS";
        this.statesX[25] = 277;
        this.statesY[25] = 277;
        this.statesVote[25] = 6;
        this.statesName[26] = "MO";
        this.statesX[26] = 237;
        this.statesY[26] = 213;
        this.statesVote[26] = 11;
        this.statesName[27] = "MT";
        this.statesX[27] = 139;
        this.statesY[27] = 145;
        this.statesVote[27] = 3;
        this.statesName[28] = "NE";
        this.statesX[28] = 186;
        this.statesY[28] = 174;
        this.statesVote[28] = 5;
        this.statesName[29] = "NV";
        this.statesX[29] = 102;
        this.statesY[29] = 173;
        this.statesVote[29] = 5;
        this.statesName[30] = "NH";
        this.statesX[30] = 461;
        this.statesY[30] = 62;
        this.statesVote[30] = 4;
        this.statesName[31] = "NJ";
        this.statesX[31] = 445;
        this.statesY[31] = 175;
        this.statesVote[31] = 15;
        this.statesName[32] = "NM";
        this.statesX[32] = 143;
        this.statesY[32] = 220;
        this.statesVote[32] = 5;
        this.statesName[33] = "NY";
        this.statesX[33] = 422;
        this.statesY[33] = 102;
        this.statesVote[33] = 31;
        this.statesName[34] = "NC";
        this.statesX[34] = 396;
        this.statesY[34] = 251;
        this.statesVote[34] = 15;
        this.statesName[35] = "ND";
        this.statesX[35] = 177;
        this.statesY[35] = 142;
        this.statesVote[35] = 3;
        this.statesName[36] = "OH";
        this.statesX[36] = 332;
        this.statesY[36] = 175;
        this.statesVote[36] = 20;
        this.statesName[37] = "OK";
        this.statesX[37] = 214;
        this.statesY[37] = 232;
        this.statesVote[37] = 7;
        this.statesName[38] = "OR";
        this.statesX[38] = 82;
        this.statesY[38] = 148;
        this.statesVote[38] = 7;
        this.statesName[39] = "PA";
        this.statesX[39] = 389;
        this.statesY[39] = 153;
        this.statesVote[39] = 21;
        this.statesName[40] = "RI";
        this.statesX[40] = 501;
        this.statesY[40] = 116;
        this.statesVote[40] = 4;
        this.statesName[41] = "SC";
        this.statesX[41] = 386;
        this.statesY[41] = 276;
        this.statesVote[41] = 8;
        this.statesName[42] = "SD";
        this.statesX[42] = 178;
        this.statesY[42] = 155;
        this.statesVote[42] = 3;
        this.statesName[43] = "TN";
        this.statesX[43] = 308;
        this.statesY[43] = 252;
        this.statesVote[43] = 11;
        this.statesName[44] = "TX";
        this.statesX[44] = 179;
        this.statesY[44] = 242;
        this.statesVote[44] = 34;
        this.statesName[45] = "UT";
        this.statesX[45] = 123;
        this.statesY[45] = 185;
        this.statesVote[45] = 5;
        this.statesName[46] = "VT";
        this.statesX[46] = 446;
        this.statesY[46] = 57;
        this.statesVote[46] = 3;
        this.statesName[47] = "VA";
        this.statesX[47] = 377;
        this.statesY[47] = 223;
        this.statesVote[47] = 13;
        this.statesName[48] = "WA";
        this.statesX[48] = 62;
        this.statesY[48] = 127;
        this.statesVote[48] = 11;
        this.statesName[49] = "WV";
        this.statesX[49] = 357;
        this.statesY[49] = 203;
        this.statesVote[49] = 5;
        this.statesName[50] = "WI";
        this.statesX[50] = 260;
        this.statesY[50] = 130;
        this.statesVote[50] = 10;
        this.statesName[51] = "WY";
        this.statesX[51] = 142;
        this.statesY[51] = 160;
        this.statesVote[51] = 3;
        this.mediator = mediator;
        this.URL_STRING = mediator.URL_STRING;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setMinimumSize(new Dimension(550, 500));
        this.setMaximumSize(new Dimension(550, 500));
        this.currentImage = new BufferedImage(550, 500, 2);
        final Graphics2D graphics = this.currentImage.createGraphics();
        graphics.fillRect(0, 0, 550, 500);
        this.oldImage = new BufferedImage(550, 500, 2);
        this.storedImage = new BufferedImage(550, 500, 2);
        this.setBackground(Color.white);
        graphics.setPaint(this.demColor);
        graphics.drawString("Democratic votes are in blue.", 5, 440);
        graphics.setPaint(this.repColor);
        graphics.drawString("Republican votes are in red.", 180, 440);
        graphics.setPaint(Color.black);
        graphics.drawString("It takes 270 electoral votes to win.", 355, 440);
        this.fillPoints = new Vector();
        try {
            this.bg0 = new ImageIcon(new URL(this.URL_STRING + "images/background.gif")).getImage();
            this.backGroundImage();
            this.countAllTheVotes();
            graphics.setPaint(Color.black);
            graphics.drawString("Click here to reset the electoral map.         Click on a state to change its vote.", 5, 15);
            for (int i = 1; i < 52; ++i) {
                graphics.drawString(this.statesName[i], this.statesX[i], this.statesY[i]);
            }
        }
        catch (MalformedURLException ex) {}
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        try {
            this.fillPointer = defaultToolkit.createCustomCursor(defaultToolkit.getImage(new URL(this.URL_STRING + "images/fill2.gif")), new Point(6, 28), "Fill");
        }
        catch (Exception ex2) {}
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
            this.stop = false;
        }
    }
    
    public void run() {
        while (!this.stop) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void stop() {
        this.runner = null;
    }
    
    public void paintComponent(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        super.paintComponent(graphics2D);
        graphics2D.drawImage(this.currentImage, 0, 0, this);
    }
    
    public void setColor(final Color drawColor) {
        this.drawColor = drawColor;
    }
    
    public void setMode(final int mode) {
        this.mode = mode;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.tempImage = this.storedImage;
        this.storedImage = this.oldImage;
        this.oldImage = this.tempImage;
        this.lastX = mouseEvent.getX();
        this.lastY = mouseEvent.getY();
        try {
            final Graphics2D graphics = this.currentImage.createGraphics();
            boolean b = false;
            int n = 1;
            if (mouseEvent.getX() < 210 & mouseEvent.getY() < 20) {
                this.picNo = 0;
                this.backGroundImage();
                graphics.setPaint(Color.black);
                graphics.drawString("Click here to reset the electoral map.         Click on a state to change its vote.", 5, 15);
                for (int i = 1; i < 52; ++i) {
                    graphics.drawString(this.statesName[i], this.statesX[i], this.statesY[i]);
                }
            }
            else {
                while (n < 52 & !b) {
                    if (this.statesEdge[n].contains(mouseEvent.getX(), mouseEvent.getY())) {
                        if (this.currentImage.getRGB(this.statesX[n], this.statesY[n]) == this.nonColor.getRGB()) {
                            graphics.setPaint(this.nonColor);
                            this.drawColor = this.demColor;
                        }
                        if (this.currentImage.getRGB(this.statesX[n], this.statesY[n]) == this.demColor.getRGB()) {
                            graphics.setPaint(this.demColor);
                            this.drawColor = this.repColor;
                        }
                        if (this.currentImage.getRGB(this.statesX[n], this.statesY[n]) == this.repColor.getRGB()) {
                            graphics.setPaint(this.repColor);
                            this.drawColor = this.nonColor;
                        }
                        graphics.drawString(this.statesName[n], this.statesX[n], this.statesY[n]);
                        this.repaint();
                        this.fillPoints.clear();
                        this.fillPoints.add(new Point(this.statesX[n], this.statesY[n]));
                        this.floodFill(this.statesX[n], this.statesY[n]);
                        b = true;
                    }
                    ++n;
                }
            }
            this.countAllTheVotes();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.stop();
        this.stop = true;
        this.storedImage.setData(this.currentImage.getData());
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(this.fillPointer);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void backGroundImage() {
        try {
            this.bgImage = this.bg0;
        }
        catch (Exception ex) {}
        this.oldImage.setData(this.currentImage.getData());
        this.currentImage.createGraphics().drawImage(this.bgImage, 0, 0, this);
        this.repaint();
        this.storedImage.setData(this.currentImage.getData());
    }
    
    public void floodFill(final int n, final int n2) throws Exception {
        this.bgColor = this.currentImage.getRGB(n, n2);
        if (this.bgColor != this.drawColor.getRGB()) {
            this.setCursor(Cursor.getPredefinedCursor(3));
            while (!this.fillPoints.isEmpty()) {
                final Point point = this.fillPoints.elementAt(0);
                this.fillPoints.remove(0);
                this.fillX = (int)point.getX();
                this.fillY = (int)point.getY();
                if (this.fillY > 0) {
                    if (this.currentImage.getRGB(this.fillX, this.fillY - 1) != this.bgColor) {
                        this.checkUp = true;
                    }
                    else {
                        this.checkUp = false;
                        this.addPoint = new Point(this.fillX, this.fillY - 1);
                        if (!this.fillPoints.contains(this.addPoint)) {
                            this.fillPoints.add(new Point(this.fillX, this.fillY - 1));
                        }
                    }
                }
                if (this.fillY < 499) {
                    if (this.currentImage.getRGB(this.fillX, this.fillY + 1) != this.bgColor) {
                        this.checkDown = true;
                    }
                    else {
                        this.checkDown = false;
                        this.addPoint = new Point(this.fillX, this.fillY + 1);
                        if (!this.fillPoints.contains(this.addPoint)) {
                            this.fillPoints.add(new Point(this.fillX, this.fillY + 1));
                        }
                    }
                }
                this.currentImage.setRGB(this.fillX, this.fillY, this.drawColor.getRGB());
                if (this.fillX > 0) {
                    this.fillLeft(this.checkUp, this.checkDown, this.fillX - 1, this.fillY);
                }
                if (this.fillX < 549) {
                    this.fillRight(this.checkUp, this.checkDown, this.fillX + 1, this.fillY);
                }
            }
            this.fillPoints.clear();
            this.repaint();
            this.setCursor(this.fillPointer);
        }
    }
    
    public void fillLeft(boolean b, boolean b2, final int n, final int n2) {
        if (this.currentImage.getRGB(n, n2) == this.bgColor) {
            this.currentImage.setRGB(n, n2, this.drawColor.getRGB());
            if (n2 > 0 && n2 < 499) {
                if (this.currentImage.getRGB(n, n2 - 1) != this.bgColor) {
                    b = true;
                }
                else if (b) {
                    this.addPoint = new Point(n, n2 - 1);
                    if (!this.fillPoints.contains(this.addPoint)) {
                        this.fillPoints.add(new Point(n, n2 - 1));
                    }
                    b = false;
                }
                if (this.currentImage.getRGB(n, n2 + 1) != this.bgColor) {
                    b2 = true;
                }
                else if (b2) {
                    this.addPoint = new Point(n, n2 + 1);
                    if (!this.fillPoints.contains(this.addPoint)) {
                        this.fillPoints.add(new Point(n, n2 + 1));
                    }
                    b2 = false;
                }
            }
            if (n > 0) {
                this.fillLeft(b, b2, n - 1, n2);
            }
        }
    }
    
    public void fillRight(boolean b, boolean b2, final int n, final int n2) {
        if (this.currentImage.getRGB(n, n2) == this.bgColor) {
            this.currentImage.setRGB(n, n2, this.drawColor.getRGB());
            if (n2 > 0 && n2 < 499) {
                if (this.currentImage.getRGB(n, n2 - 1) != this.bgColor) {
                    b = true;
                }
                else if (b) {
                    this.addPoint = new Point(n, n2 - 1);
                    if (!this.fillPoints.contains(this.addPoint)) {
                        this.fillPoints.add(new Point(n, n2 - 1));
                    }
                    b = false;
                }
                if (this.currentImage.getRGB(n, n2 + 1) != this.bgColor) {
                    b2 = true;
                }
                else if (b2) {
                    this.addPoint = new Point(n, n2 + 1);
                    if (!this.fillPoints.contains(this.addPoint)) {
                        this.fillPoints.add(new Point(n, n2 + 1));
                    }
                    b2 = false;
                }
            }
            if (n < 549) {
                this.fillRight(b, b2, n + 1, n2);
            }
        }
    }
    
    public void countAllTheVotes() {
        final Graphics2D graphics = this.currentImage.createGraphics();
        final Graphics2D graphics2 = this.currentImage.createGraphics();
        this.repVotes = 0;
        this.demVotes = 0;
        graphics.setPaint(Color.black);
        this.i = 1;
        while (this.i < 52) {
            if (this.currentImage.getRGB(this.statesX[this.i], this.statesY[this.i]) == this.repColor.getRGB()) {
                this.repVotes += this.statesVote[this.i];
            }
            if (this.currentImage.getRGB(this.statesX[this.i], this.statesY[this.i]) == this.demColor.getRGB()) {
                this.demVotes += this.statesVote[this.i];
            }
            graphics.drawString(this.statesName[this.i], this.statesX[this.i], this.statesY[this.i]);
            ++this.i;
        }
        graphics.setPaint(Color.white);
        graphics2.fillRect(0, 445, 550, 500);
        graphics.setStroke(new BasicStroke(5.0f, 1, 1));
        graphics.drawLine(0 + this.demVotes, 465, 545, 465);
        graphics.drawLine(0 + this.repVotes, 480, 545, 480);
        graphics.setPaint(this.demColor);
        if (this.demVotes > 0) {
            graphics.drawLine(0, 465, 0 + this.demVotes, 465);
        }
        if (this.demVotes > 12) {
            graphics.drawString(String.valueOf(this.demVotes), this.demVotes - 15, 460);
        }
        else {
            graphics.drawString(String.valueOf(this.demVotes), 5, 460);
        }
        graphics.setPaint(this.repColor);
        if (this.repVotes > 0) {
            graphics.drawLine(0, 490, 0 + this.repVotes, 490);
        }
        if (this.repVotes > 12) {
            graphics.drawString(String.valueOf(this.repVotes), this.repVotes - 15, 485);
        }
        else {
            graphics.drawString(String.valueOf(this.repVotes), 5, 485);
        }
        this.repaint();
    }
}
