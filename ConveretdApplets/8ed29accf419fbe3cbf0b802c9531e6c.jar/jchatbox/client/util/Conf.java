// 
// Decompiled by Procyon v0.5.30
// 

package jchatbox.client.util;

import java.net.URLConnection;
import java.io.ByteArrayOutputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import nanoxml.XMLElement;
import java.net.URL;
import java.awt.Image;
import java.util.Hashtable;

public class Conf
{
    public static final String VERSION = "2.5";
    public static String URL;
    public static String PARAMETERSMISSING;
    public static String MULTILANGUAGE;
    public static boolean UPDOWN;
    public static int NAMELENGTH;
    public static char COPY;
    public static String RIGHT;
    public static String SEPARATOR2;
    public static String PREPRIVMSG;
    public static String DEFAULTLANGUAGE;
    public static Hashtable RESOURCES;
    public static int APPLET_WIDTH;
    public static int APPLET_HEIGHT;
    public static String XMLFILE;
    public static String SKINPATH;
    public static String LOGINPANEL_COLOR;
    public static String LOGINPANEL_IMAGE;
    public static String LOGINPANEL_FONTNAME;
    public static int LOGINPANEL_FONTSIZE;
    public static int LOGINPANEL_FONTSTYLE;
    public static Image LOGINPANEL_PICTURE;
    public static String LOGINPANEL_USERNAME_LABEL_COLOR;
    public static String LOGINPANEL_USERNAME_LABEL_IMAGE;
    public static String LOGINPANEL_USERNAME_LABEL;
    public static boolean LOGINPANEL_USERNAME_LABEL_MULTILANGUAGE;
    public static int LOGINPANEL_USERNAME_LABEL_X;
    public static int LOGINPANEL_USERNAME_LABEL_Y;
    public static int LOGINPANEL_USERNAME_LABEL_W;
    public static int LOGINPANEL_USERNAME_LABEL_H;
    public static Image LOGINPANEL_USERNAME_LABEL_PICTURE;
    public static String LOGINPANEL_CHATROOMS_LABEL_COLOR;
    public static String LOGINPANEL_CHATROOMS_LABEL_IMAGE;
    public static String LOGINPANEL_CHATROOMS_LABEL;
    public static boolean LOGINPANEL_CHATROOMS_LABEL_MULTILANGUAGE;
    public static int LOGINPANEL_CHATROOMS_LABEL_X;
    public static int LOGINPANEL_CHATROOMS_LABEL_Y;
    public static int LOGINPANEL_CHATROOMS_LABEL_W;
    public static int LOGINPANEL_CHATROOMS_LABEL_H;
    public static Image LOGINPANEL_CHATROOMS_LABEL_PICTURE;
    public static String LOGINPANEL_NAME_LABEL_COLOR;
    public static String LOGINPANEL_NAME_LABEL_IMAGE;
    public static String LOGINPANEL_NAME_LABEL;
    public static boolean LOGINPANEL_NAME_LABEL_MULTILANGUAGE;
    public static int LOGINPANEL_NAME_LABEL_X;
    public static int LOGINPANEL_NAME_LABEL_Y;
    public static int LOGINPANEL_NAME_LABEL_W;
    public static int LOGINPANEL_NAME_LABEL_H;
    public static Image LOGINPANEL_NAME_LABEL_PICTURE;
    public static String LOGINPANEL_DATE_LABEL_COLOR;
    public static String LOGINPANEL_DATE_LABEL_IMAGE;
    public static String LOGINPANEL_DATE_LABEL;
    public static boolean LOGINPANEL_DATE_LABEL_MULTILANGUAGE;
    public static int LOGINPANEL_DATE_LABEL_X;
    public static int LOGINPANEL_DATE_LABEL_Y;
    public static int LOGINPANEL_DATE_LABEL_W;
    public static int LOGINPANEL_DATE_LABEL_H;
    public static Image LOGINPANEL_DATE_LABEL_PICTURE;
    public static String LOGINPANEL_SUBJECT_LABEL_COLOR;
    public static String LOGINPANEL_SUBJECT_LABEL_IMAGE;
    public static String LOGINPANEL_SUBJECT_LABEL;
    public static boolean LOGINPANEL_SUBJECT_LABEL_MULTILANGUAGE;
    public static int LOGINPANEL_SUBJECT_LABEL_X;
    public static int LOGINPANEL_SUBJECT_LABEL_Y;
    public static int LOGINPANEL_SUBJECT_LABEL_W;
    public static int LOGINPANEL_SUBJECT_LABEL_H;
    public static Image LOGINPANEL_SUBJECT_LABEL_PICTURE;
    public static String LOGINPANEL_WELCOME_LABEL_COLOR;
    public static String LOGINPANEL_WELCOME_LABEL_IMAGE;
    public static String LOGINPANEL_WELCOME_LABEL;
    public static int LOGINPANEL_WELCOME_LABEL_X;
    public static int LOGINPANEL_WELCOME_LABEL_Y;
    public static int LOGINPANEL_WELCOME_LABEL_W;
    public static int LOGINPANEL_WELCOME_LABEL_H;
    public static Image LOGINPANEL_WELCOME_LABEL_PICTURE;
    public static String LOGINPANEL_ERROR_LABEL_COLOR;
    public static String LOGINPANEL_ERROR_LABEL_IMAGE;
    public static String LOGINPANEL_ERROR_LABEL;
    public static int LOGINPANEL_ERROR_LABEL_X;
    public static int LOGINPANEL_ERROR_LABEL_Y;
    public static int LOGINPANEL_ERROR_LABEL_W;
    public static int LOGINPANEL_ERROR_LABEL_H;
    public static Image LOGINPANEL_ERROR_LABEL_PICTURE;
    public static String LOGINPANEL_USERNAME_TEXTFIELD_BGCOLOR;
    public static String LOGINPANEL_USERNAME_TEXTFIELD_COLOR;
    public static int LOGINPANEL_USERNAME_TEXTFIELD_X;
    public static int LOGINPANEL_USERNAME_TEXTFIELD_Y;
    public static int LOGINPANEL_USERNAME_TEXTFIELD_W;
    public static int LOGINPANEL_USERNAME_TEXTFIELD_H;
    public static String LOGINPANEL_CHATROOMS_CHOICE_FIRSTITEM;
    public static boolean LOGINPANEL_CHATROOMS_CHOICE_FIRSTITEM_MULTILANGUAGE;
    public static String LOGINPANEL_CHATROOMS_CHOICE_BGCOLOR;
    public static String LOGINPANEL_CHATROOMS_CHOICE_COLOR;
    public static int LOGINPANEL_CHATROOMS_CHOICE_X;
    public static int LOGINPANEL_CHATROOMS_CHOICE_Y;
    public static int LOGINPANEL_CHATROOMS_CHOICE_W;
    public static int LOGINPANEL_CHATROOMS_CHOICE_H;
    public static String LOGINPANEL_LOGIN_BUTTON_BGCOLOR;
    public static String LOGINPANEL_LOGIN_BUTTON_COLOR;
    public static String LOGINPANEL_LOGIN_BUTTON_LABEL;
    public static boolean LOGINPANEL_LOGIN_BUTTON_LABEL_MULTILANGUAGE;
    public static String LOGINPANEL_LOGIN_BUTTON_IMAGE;
    public static String LOGINPANEL_LOGIN_BUTTON_PRESSEDIMAGE;
    public static int LOGINPANEL_LOGIN_BUTTON_X;
    public static int LOGINPANEL_LOGIN_BUTTON_Y;
    public static int LOGINPANEL_LOGIN_BUTTON_W;
    public static int LOGINPANEL_LOGIN_BUTTON_H;
    public static String CHATPANEL_COLOR;
    public static String CHATPANEL_IMAGE;
    public static String CHATPANEL_FONTNAME;
    public static int CHATPANEL_FONTSIZE;
    public static int CHATPANEL_FONTSTYLE;
    public static Image CHATPANEL_PICTURE;
    public static String CHATPANEL_MESSAGE_LABEL_COLOR;
    public static String CHATPANEL_MESSAGE_LABEL_IMAGE;
    public static String CHATPANEL_MESSAGE_LABEL;
    public static boolean CHATPANEL_MESSAGE_LABEL_MULTILANGUAGE;
    public static int CHATPANEL_MESSAGE_LABEL_X;
    public static int CHATPANEL_MESSAGE_LABEL_Y;
    public static int CHATPANEL_MESSAGE_LABEL_W;
    public static int CHATPANEL_MESSAGE_LABEL_H;
    public static Image CHATPANEL_MESSAGE_LABEL_PICTURE;
    public static String CHATPANEL_NAME_LABEL_COLOR;
    public static String CHATPANEL_NAME_LABEL_IMAGE;
    public static String CHATPANEL_NAME_LABEL;
    public static boolean CHATPANEL_NAME_LABEL_MULTILANGUAGE;
    public static int CHATPANEL_NAME_LABEL_X;
    public static int CHATPANEL_NAME_LABEL_Y;
    public static int CHATPANEL_NAME_LABEL_W;
    public static int CHATPANEL_NAME_LABEL_H;
    public static Image CHATPANEL_NAME_LABEL_PICTURE;
    public static String CHATPANEL_MESSAGE_TEXTFIELD_BGCOLOR;
    public static String CHATPANEL_MESSAGE_TEXTFIELD_COLOR;
    public static int CHATPANEL_MESSAGE_TEXTFIELD_X;
    public static int CHATPANEL_MESSAGE_TEXTFIELD_Y;
    public static int CHATPANEL_MESSAGE_TEXTFIELD_W;
    public static int CHATPANEL_MESSAGE_TEXTFIELD_H;
    public static String CHATPANEL_SEND_BUTTON_BGCOLOR;
    public static String CHATPANEL_SEND_BUTTON_COLOR;
    public static String CHATPANEL_SEND_BUTTON_LABEL;
    public static boolean CHATPANEL_SEND_BUTTON_LABEL_MULTILANGUAGE;
    public static String CHATPANEL_SEND_BUTTON_IMAGE;
    public static String CHATPANEL_SEND_BUTTON_PRESSEDIMAGE;
    public static int CHATPANEL_SEND_BUTTON_X;
    public static int CHATPANEL_SEND_BUTTON_Y;
    public static int CHATPANEL_SEND_BUTTON_W;
    public static int CHATPANEL_SEND_BUTTON_H;
    public static String CHATPANEL_REFRESH_BUTTON_BGCOLOR;
    public static String CHATPANEL_REFRESH_BUTTON_COLOR;
    public static String CHATPANEL_REFRESH_BUTTON_LABEL;
    public static boolean CHATPANEL_REFRESH_BUTTON_LABEL_MULTILANGUAGE;
    public static String CHATPANEL_REFRESH_BUTTON_IMAGE;
    public static String CHATPANEL_REFRESH_BUTTON_PRESSEDIMAGE;
    public static int CHATPANEL_REFRESH_BUTTON_X;
    public static int CHATPANEL_REFRESH_BUTTON_Y;
    public static int CHATPANEL_REFRESH_BUTTON_W;
    public static int CHATPANEL_REFRESH_BUTTON_H;
    public static String CHATPANEL_LOGOUT_BUTTON_BGCOLOR;
    public static String CHATPANEL_LOGOUT_BUTTON_COLOR;
    public static String CHATPANEL_LOGOUT_BUTTON_LABEL;
    public static boolean CHATPANEL_LOGOUT_BUTTON_LABEL_MULTILANGUAGE;
    public static String CHATPANEL_LOGOUT_BUTTON_IMAGE;
    public static String CHATPANEL_LOGOUT_BUTTON_PRESSEDIMAGE;
    public static int CHATPANEL_LOGOUT_BUTTON_X;
    public static int CHATPANEL_LOGOUT_BUTTON_Y;
    public static int CHATPANEL_LOGOUT_BUTTON_W;
    public static int CHATPANEL_LOGOUT_BUTTON_H;
    public static String CHATPANEL_MESSAGES_TEXTAREA_JOIN;
    public static boolean CHATPANEL_MESSAGES_TEXTAREA_JOIN_MULTILANGUAGE;
    public static String CHATPANEL_MESSAGES_TEXTAREA_QUIT;
    public static boolean CHATPANEL_MESSAGES_TEXTAREA_QUIT_MULTILANGUAGE;
    public static String CHATPANEL_MESSAGES_TEXTAREA_PRIVATE;
    public static boolean CHATPANEL_MESSAGES_TEXTAREA_PRIVATE_MULTILANGUAGE;
    public static String CHATPANEL_MESSAGES_TEXTAREA_ALL;
    public static boolean CHATPANEL_MESSAGES_TEXTAREA_ALL_MULTILANGUAGE;
    public static String CHATPANEL_MESSAGES_TEXTAREA_BGCOLOR;
    public static String CHATPANEL_MESSAGES_TEXTAREA_COLOR;
    public static int CHATPANEL_MESSAGES_TEXTAREA_X;
    public static int CHATPANEL_MESSAGES_TEXTAREA_Y;
    public static int CHATPANEL_MESSAGES_TEXTAREA_W;
    public static int CHATPANEL_MESSAGES_TEXTAREA_H;
    public static String CHATPANEL_USERS_LIST_TITLE;
    public static boolean CHATPANEL_USERS_LIST_TITLE_MULTILANGUAGE;
    public static String CHATPANEL_USERS_LIST_SEPARATOR;
    public static String CHATPANEL_USERS_LIST_BGCOLOR;
    public static String CHATPANEL_USERS_LIST_COLOR;
    public static int CHATPANEL_USERS_LIST_X;
    public static int CHATPANEL_USERS_LIST_Y;
    public static int CHATPANEL_USERS_LIST_W;
    public static int CHATPANEL_USERS_LIST_H;
    public static String INVALIDXML;
    public static String INVALIDRESPONSE;
    public static String PLEASELOGOUT;
    public static String NAMETOOLONG;
    
    public static boolean checkMultilanguage() {
        boolean b = false;
        if (Conf.LOGINPANEL_USERNAME_LABEL.equalsIgnoreCase(Conf.MULTILANGUAGE)) {
            Conf.LOGINPANEL_USERNAME_LABEL_MULTILANGUAGE = true;
            b = true;
        }
        if (Conf.LOGINPANEL_CHATROOMS_LABEL.equalsIgnoreCase(Conf.MULTILANGUAGE)) {
            Conf.LOGINPANEL_CHATROOMS_LABEL_MULTILANGUAGE = true;
            b = true;
        }
        if (Conf.LOGINPANEL_NAME_LABEL.equalsIgnoreCase(Conf.MULTILANGUAGE)) {
            Conf.LOGINPANEL_NAME_LABEL_MULTILANGUAGE = true;
            b = true;
        }
        if (Conf.LOGINPANEL_DATE_LABEL.equalsIgnoreCase(Conf.MULTILANGUAGE)) {
            Conf.LOGINPANEL_DATE_LABEL_MULTILANGUAGE = true;
            b = true;
        }
        if (Conf.LOGINPANEL_SUBJECT_LABEL.equalsIgnoreCase(Conf.MULTILANGUAGE)) {
            Conf.LOGINPANEL_SUBJECT_LABEL_MULTILANGUAGE = true;
            b = true;
        }
        if (Conf.LOGINPANEL_CHATROOMS_CHOICE_FIRSTITEM.equalsIgnoreCase(Conf.MULTILANGUAGE)) {
            Conf.LOGINPANEL_CHATROOMS_CHOICE_FIRSTITEM_MULTILANGUAGE = true;
            b = true;
        }
        if (Conf.LOGINPANEL_LOGIN_BUTTON_LABEL.equalsIgnoreCase(Conf.MULTILANGUAGE)) {
            Conf.LOGINPANEL_LOGIN_BUTTON_LABEL_MULTILANGUAGE = true;
            b = true;
        }
        if (Conf.CHATPANEL_MESSAGE_LABEL.equalsIgnoreCase(Conf.MULTILANGUAGE)) {
            Conf.CHATPANEL_MESSAGE_LABEL_MULTILANGUAGE = true;
            b = true;
        }
        if (Conf.CHATPANEL_SEND_BUTTON_LABEL.equalsIgnoreCase(Conf.MULTILANGUAGE)) {
            Conf.CHATPANEL_SEND_BUTTON_LABEL_MULTILANGUAGE = true;
            b = true;
        }
        if (Conf.CHATPANEL_REFRESH_BUTTON_LABEL.equalsIgnoreCase(Conf.MULTILANGUAGE)) {
            Conf.CHATPANEL_REFRESH_BUTTON_LABEL_MULTILANGUAGE = true;
            b = true;
        }
        if (Conf.CHATPANEL_LOGOUT_BUTTON_LABEL.equalsIgnoreCase(Conf.MULTILANGUAGE)) {
            Conf.CHATPANEL_LOGOUT_BUTTON_LABEL_MULTILANGUAGE = true;
            b = true;
        }
        if (Conf.CHATPANEL_MESSAGES_TEXTAREA_JOIN.equalsIgnoreCase(Conf.MULTILANGUAGE)) {
            Conf.CHATPANEL_MESSAGES_TEXTAREA_JOIN_MULTILANGUAGE = true;
            b = true;
        }
        if (Conf.CHATPANEL_MESSAGES_TEXTAREA_QUIT.equalsIgnoreCase(Conf.MULTILANGUAGE)) {
            Conf.CHATPANEL_MESSAGES_TEXTAREA_QUIT_MULTILANGUAGE = true;
            b = true;
        }
        if (Conf.CHATPANEL_MESSAGES_TEXTAREA_PRIVATE.equalsIgnoreCase(Conf.MULTILANGUAGE)) {
            Conf.CHATPANEL_MESSAGES_TEXTAREA_PRIVATE_MULTILANGUAGE = true;
            b = true;
        }
        if (Conf.CHATPANEL_MESSAGES_TEXTAREA_ALL.equalsIgnoreCase(Conf.MULTILANGUAGE)) {
            Conf.CHATPANEL_MESSAGES_TEXTAREA_ALL_MULTILANGUAGE = true;
            b = true;
        }
        if (Conf.CHATPANEL_USERS_LIST_TITLE.equalsIgnoreCase(Conf.MULTILANGUAGE)) {
            Conf.CHATPANEL_USERS_LIST_TITLE_MULTILANGUAGE = true;
            b = true;
        }
        if (Conf.CHATPANEL_NAME_LABEL.equalsIgnoreCase(Conf.MULTILANGUAGE)) {
            Conf.CHATPANEL_NAME_LABEL_MULTILANGUAGE = true;
            b = true;
        }
        return b;
    }
    
    public static void loadResources(final String s) {
        if (Conf.LOGINPANEL_USERNAME_LABEL_MULTILANGUAGE) {
            Conf.LOGINPANEL_USERNAME_LABEL = Conf.RESOURCES.get(s).get("login.username.label");
        }
        if (Conf.LOGINPANEL_CHATROOMS_LABEL_MULTILANGUAGE) {
            Conf.LOGINPANEL_CHATROOMS_LABEL = String.valueOf(String.valueOf(Conf.RESOURCES.get(s).get("login.join.link"))).concat(String.valueOf(String.valueOf(Conf.RESOURCES.get(s).get("login.join.label"))));
        }
        if (Conf.LOGINPANEL_NAME_LABEL_MULTILANGUAGE) {
            Conf.LOGINPANEL_NAME_LABEL = Conf.RESOURCES.get(s).get("login.chatroom.label");
        }
        if (Conf.LOGINPANEL_DATE_LABEL_MULTILANGUAGE) {
            Conf.LOGINPANEL_DATE_LABEL = Conf.RESOURCES.get(s).get("login.open.label");
        }
        if (Conf.LOGINPANEL_SUBJECT_LABEL_MULTILANGUAGE) {
            Conf.LOGINPANEL_SUBJECT_LABEL = Conf.RESOURCES.get(s).get("login.subject.label");
        }
        if (Conf.LOGINPANEL_CHATROOMS_CHOICE_FIRSTITEM_MULTILANGUAGE) {
            Conf.LOGINPANEL_CHATROOMS_CHOICE_FIRSTITEM = Conf.RESOURCES.get(s).get("login.list.label");
        }
        if (Conf.LOGINPANEL_LOGIN_BUTTON_LABEL_MULTILANGUAGE) {
            Conf.LOGINPANEL_LOGIN_BUTTON_LABEL = Conf.RESOURCES.get(s).get("login.join.link");
        }
        if (Conf.CHATPANEL_MESSAGE_LABEL_MULTILANGUAGE) {
            Conf.CHATPANEL_MESSAGE_LABEL = Conf.RESOURCES.get(s).get("board.message.label");
        }
        if (Conf.CHATPANEL_SEND_BUTTON_LABEL_MULTILANGUAGE) {
            Conf.CHATPANEL_SEND_BUTTON_LABEL = Conf.RESOURCES.get(s).get("board.send.link");
        }
        if (Conf.CHATPANEL_REFRESH_BUTTON_LABEL_MULTILANGUAGE) {
            Conf.CHATPANEL_REFRESH_BUTTON_LABEL = Conf.RESOURCES.get(s).get("board.refresh.link");
        }
        if (Conf.CHATPANEL_LOGOUT_BUTTON_LABEL_MULTILANGUAGE) {
            Conf.CHATPANEL_LOGOUT_BUTTON_LABEL = Conf.RESOURCES.get(s).get("board.logout.link");
        }
        if (Conf.CHATPANEL_MESSAGES_TEXTAREA_JOIN_MULTILANGUAGE) {
            Conf.CHATPANEL_MESSAGES_TEXTAREA_JOIN = Conf.RESOURCES.get(s).get("chatroom.join.label");
        }
        if (Conf.CHATPANEL_MESSAGES_TEXTAREA_QUIT_MULTILANGUAGE) {
            Conf.CHATPANEL_MESSAGES_TEXTAREA_QUIT = Conf.RESOURCES.get(s).get("chatroom.quit.label");
        }
        if (Conf.CHATPANEL_MESSAGES_TEXTAREA_PRIVATE_MULTILANGUAGE) {
            Conf.CHATPANEL_MESSAGES_TEXTAREA_PRIVATE = String.valueOf(String.valueOf(Conf.RESOURCES.get(s).get("privateboard.send.link"))).concat(String.valueOf(String.valueOf(Conf.RESOURCES.get(s).get("privateboard.send.label"))));
        }
        if (Conf.CHATPANEL_MESSAGES_TEXTAREA_ALL_MULTILANGUAGE) {
            Conf.CHATPANEL_MESSAGES_TEXTAREA_ALL = String.valueOf(String.valueOf(Conf.RESOURCES.get(s).get("board.send.link"))).concat(String.valueOf(String.valueOf(Conf.RESOURCES.get(s).get("board.send.label"))));
        }
        if (Conf.CHATPANEL_USERS_LIST_TITLE_MULTILANGUAGE) {
            Conf.CHATPANEL_USERS_LIST_TITLE = Conf.RESOURCES.get(s).get("chatroom.userlist.label");
        }
        if (Conf.CHATPANEL_NAME_LABEL_MULTILANGUAGE) {
            Conf.CHATPANEL_NAME_LABEL = Conf.RESOURCES.get(s).get("board.chatroom.label");
        }
    }
    
    public static void loadSkin(final URL url) throws Exception {
        Conf.LOGINPANEL_PICTURE = null;
        Conf.LOGINPANEL_USERNAME_LABEL_MULTILANGUAGE = false;
        Conf.LOGINPANEL_CHATROOMS_LABEL_MULTILANGUAGE = false;
        Conf.LOGINPANEL_NAME_LABEL_MULTILANGUAGE = false;
        Conf.LOGINPANEL_DATE_LABEL_MULTILANGUAGE = false;
        Conf.LOGINPANEL_SUBJECT_LABEL_MULTILANGUAGE = false;
        Conf.LOGINPANEL_CHATROOMS_CHOICE_FIRSTITEM_MULTILANGUAGE = false;
        Conf.LOGINPANEL_LOGIN_BUTTON_LABEL_MULTILANGUAGE = false;
        Conf.CHATPANEL_PICTURE = null;
        Conf.CHATPANEL_MESSAGE_LABEL_MULTILANGUAGE = false;
        Conf.CHATPANEL_SEND_BUTTON_LABEL_MULTILANGUAGE = false;
        Conf.CHATPANEL_REFRESH_BUTTON_LABEL_MULTILANGUAGE = false;
        Conf.CHATPANEL_LOGOUT_BUTTON_LABEL_MULTILANGUAGE = false;
        Conf.CHATPANEL_MESSAGES_TEXTAREA_JOIN_MULTILANGUAGE = false;
        Conf.CHATPANEL_MESSAGES_TEXTAREA_QUIT_MULTILANGUAGE = false;
        Conf.CHATPANEL_MESSAGES_TEXTAREA_PRIVATE_MULTILANGUAGE = false;
        Conf.CHATPANEL_MESSAGES_TEXTAREA_ALL_MULTILANGUAGE = false;
        Conf.CHATPANEL_USERS_LIST_TITLE_MULTILANGUAGE = false;
        Conf.CHATPANEL_NAME_LABEL_MULTILANGUAGE = false;
        final StringBuffer loadXML = loadXML(url);
        Debug.log(5, "jchatbox.client.util.Conf : XML SKIN :".concat(String.valueOf(String.valueOf(loadXML.toString()))));
        final XMLElement xmlElement = new XMLElement();
        xmlElement.parseString(loadXML.toString(), 0);
        if (!xmlElement.getTagName().equalsIgnoreCase("JCBSKIN")) {
            Debug.log(0, "jchatbox.client.util.Conf : Invalid skin file");
        }
        else {
            final Vector children = xmlElement.getChildren();
            for (int i = 0; i < children.size(); ++i) {
                final XMLElement xmlElement2 = children.elementAt(i);
                if (xmlElement2.getTagName().equalsIgnoreCase("LOGINPANEL")) {
                    Conf.LOGINPANEL_FONTNAME = xmlElement2.getProperty("FONTNAME", "Helvetica");
                    Conf.LOGINPANEL_FONTSIZE = Integer.parseInt(xmlElement2.getProperty("FONTSIZE", "12"));
                    final String property = xmlElement2.getProperty("FONTSTYLE", "bold");
                    if (property.equalsIgnoreCase("bold")) {
                        Conf.LOGINPANEL_FONTSTYLE = 1;
                    }
                    else if (property.equalsIgnoreCase("plain")) {
                        Conf.LOGINPANEL_FONTSTYLE = 0;
                    }
                    else if (property.equalsIgnoreCase("italic")) {
                        Conf.LOGINPANEL_FONTSTYLE = 2;
                    }
                    Conf.LOGINPANEL_COLOR = xmlElement2.getProperty("BGCOLOR", "CCDDFF");
                    Conf.LOGINPANEL_IMAGE = xmlElement2.getProperty("BGIMAGE", (String)null);
                    final Vector children2 = xmlElement2.getChildren();
                    for (int j = 0; j < children2.size(); ++j) {
                        final XMLElement xmlElement3 = children2.elementAt(j);
                        if (xmlElement3.getTagName().equalsIgnoreCase("LABEL")) {
                            final String property2 = xmlElement3.getProperty("ID", (String)null);
                            if (property2 != null) {
                                if (property2.equalsIgnoreCase("USERNAME")) {
                                    Conf.LOGINPANEL_USERNAME_LABEL_X = xmlElement3.getProperty("X", 7);
                                    Conf.LOGINPANEL_USERNAME_LABEL_Y = xmlElement3.getProperty("Y", 14);
                                    Conf.LOGINPANEL_USERNAME_LABEL_W = xmlElement3.getProperty("WIDTH", 70);
                                    Conf.LOGINPANEL_USERNAME_LABEL_H = xmlElement3.getProperty("HEIGHT", 23);
                                    Conf.LOGINPANEL_USERNAME_LABEL_COLOR = xmlElement3.getProperty("COLOR", "000022");
                                    Conf.LOGINPANEL_USERNAME_LABEL = xmlElement3.getContents();
                                }
                                else if (property2.equalsIgnoreCase("CHATROOMS")) {
                                    Conf.LOGINPANEL_CHATROOMS_LABEL_X = xmlElement3.getProperty("X", 5);
                                    Conf.LOGINPANEL_CHATROOMS_LABEL_Y = xmlElement3.getProperty("Y", 86);
                                    Conf.LOGINPANEL_CHATROOMS_LABEL_W = xmlElement3.getProperty("WIDTH", 73);
                                    Conf.LOGINPANEL_CHATROOMS_LABEL_H = xmlElement3.getProperty("HEIGHT", 22);
                                    Conf.LOGINPANEL_CHATROOMS_LABEL_COLOR = xmlElement3.getProperty("COLOR", "000022");
                                    Conf.LOGINPANEL_CHATROOMS_LABEL = xmlElement3.getContents();
                                }
                                else if (property2.equalsIgnoreCase("NAME")) {
                                    Conf.LOGINPANEL_NAME_LABEL_X = xmlElement3.getProperty("X", 300);
                                    Conf.LOGINPANEL_NAME_LABEL_Y = xmlElement3.getProperty("Y", 12);
                                    Conf.LOGINPANEL_NAME_LABEL_W = xmlElement3.getProperty("WIDTH", 190);
                                    Conf.LOGINPANEL_NAME_LABEL_H = xmlElement3.getProperty("HEIGHT", 22);
                                    Conf.LOGINPANEL_NAME_LABEL_COLOR = xmlElement3.getProperty("COLOR", "000022");
                                    Conf.LOGINPANEL_NAME_LABEL = xmlElement3.getContents();
                                }
                                else if (property2.equalsIgnoreCase("DATE")) {
                                    Conf.LOGINPANEL_DATE_LABEL_X = xmlElement3.getProperty("X", 300);
                                    Conf.LOGINPANEL_DATE_LABEL_Y = xmlElement3.getProperty("Y", 35);
                                    Conf.LOGINPANEL_DATE_LABEL_W = xmlElement3.getProperty("WIDTH", 190);
                                    Conf.LOGINPANEL_DATE_LABEL_H = xmlElement3.getProperty("HEIGHT", 22);
                                    Conf.LOGINPANEL_DATE_LABEL_COLOR = xmlElement3.getProperty("COLOR", "000022");
                                    Conf.LOGINPANEL_DATE_LABEL = xmlElement3.getContents();
                                }
                                else if (property2.equalsIgnoreCase("SUBJECT")) {
                                    Conf.LOGINPANEL_SUBJECT_LABEL_X = xmlElement3.getProperty("X", 300);
                                    Conf.LOGINPANEL_SUBJECT_LABEL_Y = xmlElement3.getProperty("Y", 55);
                                    Conf.LOGINPANEL_SUBJECT_LABEL_W = xmlElement3.getProperty("WIDTH", 190);
                                    Conf.LOGINPANEL_SUBJECT_LABEL_H = xmlElement3.getProperty("HEIGHT", 22);
                                    Conf.LOGINPANEL_SUBJECT_LABEL_COLOR = xmlElement3.getProperty("COLOR", "000022");
                                    Conf.LOGINPANEL_SUBJECT_LABEL = xmlElement3.getContents();
                                }
                                else if (property2.equalsIgnoreCase("ERROR")) {
                                    Conf.LOGINPANEL_ERROR_LABEL_X = xmlElement3.getProperty("X", 5);
                                    Conf.LOGINPANEL_ERROR_LABEL_Y = xmlElement3.getProperty("Y", 42);
                                    Conf.LOGINPANEL_ERROR_LABEL_W = xmlElement3.getProperty("WIDTH", 267);
                                    Conf.LOGINPANEL_ERROR_LABEL_H = xmlElement3.getProperty("HEIGHT", 22);
                                    Conf.LOGINPANEL_ERROR_LABEL_COLOR = xmlElement3.getProperty("COLOR", "FF0000");
                                    Conf.LOGINPANEL_ERROR_LABEL = xmlElement3.getContents();
                                }
                                else if (property2.equalsIgnoreCase("WELCOME")) {
                                    Conf.LOGINPANEL_WELCOME_LABEL_X = xmlElement3.getProperty("X", 125);
                                    Conf.LOGINPANEL_WELCOME_LABEL_Y = xmlElement3.getProperty("Y", 164);
                                    Conf.LOGINPANEL_WELCOME_LABEL_W = xmlElement3.getProperty("WIDTH", 228);
                                    Conf.LOGINPANEL_WELCOME_LABEL_H = xmlElement3.getProperty("HEIGHT", 51);
                                    Conf.LOGINPANEL_WELCOME_LABEL_COLOR = xmlElement3.getProperty("COLOR", "0000FF");
                                    Conf.LOGINPANEL_WELCOME_LABEL = xmlElement3.getContents();
                                }
                            }
                        }
                        else if (xmlElement3.getTagName().equalsIgnoreCase("TEXTFIELD")) {
                            final String property3 = xmlElement3.getProperty("ID", (String)null);
                            if (property3 != null && property3.equalsIgnoreCase("USERNAME")) {
                                Conf.LOGINPANEL_USERNAME_TEXTFIELD_X = xmlElement3.getProperty("X", 79);
                                Conf.LOGINPANEL_USERNAME_TEXTFIELD_Y = xmlElement3.getProperty("Y", 16);
                                Conf.LOGINPANEL_USERNAME_TEXTFIELD_W = xmlElement3.getProperty("WIDTH", 117);
                                Conf.LOGINPANEL_USERNAME_TEXTFIELD_H = xmlElement3.getProperty("HEIGHT", 20);
                                Conf.LOGINPANEL_USERNAME_TEXTFIELD_COLOR = xmlElement3.getProperty("COLOR", "000000");
                                Conf.LOGINPANEL_USERNAME_TEXTFIELD_BGCOLOR = xmlElement3.getProperty("BGCOLOR", "FFFFFF");
                            }
                        }
                        else if (xmlElement3.getTagName().equalsIgnoreCase("CHOICE")) {
                            final String property4 = xmlElement3.getProperty("ID", (String)null);
                            if (property4 != null && property4.equalsIgnoreCase("ROOMS")) {
                                Conf.LOGINPANEL_CHATROOMS_CHOICE_X = xmlElement3.getProperty("X", 81);
                                Conf.LOGINPANEL_CHATROOMS_CHOICE_Y = xmlElement3.getProperty("Y", 97);
                                Conf.LOGINPANEL_CHATROOMS_CHOICE_W = xmlElement3.getProperty("WIDTH", 130);
                                Conf.LOGINPANEL_CHATROOMS_CHOICE_H = xmlElement3.getProperty("HEIGHT", 20);
                                Conf.LOGINPANEL_CHATROOMS_CHOICE_COLOR = xmlElement3.getProperty("COLOR", "000000");
                                Conf.LOGINPANEL_CHATROOMS_CHOICE_BGCOLOR = xmlElement3.getProperty("BGCOLOR", "FFFFFF");
                                Conf.LOGINPANEL_CHATROOMS_CHOICE_FIRSTITEM = xmlElement3.getProperty("FIRSTITEM", "Select a chatroom");
                            }
                        }
                        else if (xmlElement3.getTagName().equalsIgnoreCase("BUTTON")) {
                            final String property5 = xmlElement3.getProperty("ID", (String)null);
                            if (property5 != null && property5.equalsIgnoreCase("LOGIN")) {
                                Conf.LOGINPANEL_LOGIN_BUTTON_X = xmlElement3.getProperty("X", 204);
                                Conf.LOGINPANEL_LOGIN_BUTTON_Y = xmlElement3.getProperty("Y", 16);
                                Conf.LOGINPANEL_LOGIN_BUTTON_W = xmlElement3.getProperty("WIDTH", 68);
                                Conf.LOGINPANEL_LOGIN_BUTTON_H = xmlElement3.getProperty("HEIGHT", 21);
                                Conf.LOGINPANEL_LOGIN_BUTTON_COLOR = xmlElement3.getProperty("COLOR", "000000");
                                Conf.LOGINPANEL_LOGIN_BUTTON_BGCOLOR = xmlElement3.getProperty("BGCOLOR", "CCCCCC");
                                Conf.LOGINPANEL_LOGIN_BUTTON_LABEL = xmlElement3.getContents();
                            }
                        }
                    }
                }
                else if (xmlElement2.getTagName().equalsIgnoreCase("CHATPANEL")) {
                    Conf.CHATPANEL_FONTNAME = xmlElement2.getProperty("FONTNAME", "Helvetica");
                    Conf.CHATPANEL_FONTSIZE = Integer.parseInt(xmlElement2.getProperty("FONTSIZE", "12"));
                    final String property6 = xmlElement2.getProperty("FONTSTYLE", "bold");
                    if (property6.equalsIgnoreCase("bold")) {
                        Conf.CHATPANEL_FONTSTYLE = 1;
                    }
                    else if (property6.equalsIgnoreCase("plain")) {
                        Conf.CHATPANEL_FONTSTYLE = 0;
                    }
                    else if (property6.equalsIgnoreCase("italic")) {
                        Conf.CHATPANEL_FONTSTYLE = 2;
                    }
                    Conf.CHATPANEL_COLOR = xmlElement2.getProperty("BGCOLOR", "CCDDFF");
                    Conf.CHATPANEL_IMAGE = xmlElement2.getProperty("BGIMAGE", (String)null);
                    final Vector children3 = xmlElement2.getChildren();
                    for (int k = 0; k < children3.size(); ++k) {
                        final XMLElement xmlElement4 = children3.elementAt(k);
                        if (xmlElement4.getTagName().equalsIgnoreCase("LABEL")) {
                            final String property7 = xmlElement4.getProperty("ID", (String)null);
                            if (property7 != null) {
                                if (property7.equalsIgnoreCase("MESSAGE")) {
                                    Conf.CHATPANEL_MESSAGE_LABEL_X = xmlElement4.getProperty("X", 4);
                                    Conf.CHATPANEL_MESSAGE_LABEL_Y = xmlElement4.getProperty("Y", 268);
                                    Conf.CHATPANEL_MESSAGE_LABEL_W = xmlElement4.getProperty("WIDTH", 63);
                                    Conf.CHATPANEL_MESSAGE_LABEL_H = xmlElement4.getProperty("HEIGHT", 22);
                                    Conf.CHATPANEL_MESSAGE_LABEL_COLOR = xmlElement4.getProperty("COLOR", "000000");
                                    Conf.CHATPANEL_MESSAGE_LABEL = xmlElement4.getContents();
                                }
                                else if (property7.equalsIgnoreCase("NAME")) {
                                    Conf.CHATPANEL_NAME_LABEL_X = xmlElement4.getProperty("X", 4);
                                    Conf.CHATPANEL_NAME_LABEL_Y = xmlElement4.getProperty("Y", 295);
                                    Conf.CHATPANEL_NAME_LABEL_W = xmlElement4.getProperty("WIDTH", 190);
                                    Conf.CHATPANEL_NAME_LABEL_H = xmlElement4.getProperty("HEIGHT", 22);
                                    Conf.CHATPANEL_NAME_LABEL_COLOR = xmlElement4.getProperty("COLOR", "000000");
                                    Conf.CHATPANEL_NAME_LABEL = xmlElement4.getContents();
                                }
                            }
                        }
                        else if (xmlElement4.getTagName().equalsIgnoreCase("TEXTFIELD")) {
                            final String property8 = xmlElement4.getProperty("ID", (String)null);
                            if (property8 != null && property8.equalsIgnoreCase("MESSAGE")) {
                                Conf.CHATPANEL_MESSAGE_TEXTFIELD_X = xmlElement4.getProperty("X", 65);
                                Conf.CHATPANEL_MESSAGE_TEXTFIELD_Y = xmlElement4.getProperty("Y", 270);
                                Conf.CHATPANEL_MESSAGE_TEXTFIELD_W = xmlElement4.getProperty("WIDTH", 250);
                                Conf.CHATPANEL_MESSAGE_TEXTFIELD_H = xmlElement4.getProperty("HEIGHT", 21);
                                Conf.CHATPANEL_MESSAGE_TEXTFIELD_COLOR = xmlElement4.getProperty("COLOR", "000000");
                                Conf.CHATPANEL_MESSAGE_TEXTFIELD_BGCOLOR = xmlElement4.getProperty("BGCOLOR", "FFFFFF");
                            }
                        }
                        else if (xmlElement4.getTagName().equalsIgnoreCase("TEXTAREA")) {
                            final String property9 = xmlElement4.getProperty("ID", (String)null);
                            if (property9 != null && property9.equalsIgnoreCase("MESSAGES")) {
                                Conf.CHATPANEL_MESSAGES_TEXTAREA_X = xmlElement4.getProperty("X", 10);
                                Conf.CHATPANEL_MESSAGES_TEXTAREA_Y = xmlElement4.getProperty("Y", 13);
                                Conf.CHATPANEL_MESSAGES_TEXTAREA_W = xmlElement4.getProperty("WIDTH", 368);
                                Conf.CHATPANEL_MESSAGES_TEXTAREA_H = xmlElement4.getProperty("HEIGHT", 245);
                                Conf.CHATPANEL_MESSAGES_TEXTAREA_COLOR = xmlElement4.getProperty("COLOR", "000000");
                                Conf.CHATPANEL_MESSAGES_TEXTAREA_BGCOLOR = xmlElement4.getProperty("BGCOLOR", "FFFFFF");
                                Conf.CHATPANEL_MESSAGES_TEXTAREA_JOIN = xmlElement4.getProperty("JOINMSG", " has joined the chatroom");
                                Conf.CHATPANEL_MESSAGES_TEXTAREA_QUIT = xmlElement4.getProperty("QUITMSG", " has left the chatroom");
                                Conf.CHATPANEL_MESSAGES_TEXTAREA_PRIVATE = xmlElement4.getProperty("PRIVATEMSG", "Next messages are sent to : ");
                                Conf.CHATPANEL_MESSAGES_TEXTAREA_ALL = xmlElement4.getProperty("ALLMSG", "Next messages are sent to ALL chatroom users.");
                            }
                        }
                        else if (xmlElement4.getTagName().equalsIgnoreCase("LIST")) {
                            final String property10 = xmlElement4.getProperty("ID", (String)null);
                            if (property10 != null && property10.equalsIgnoreCase("USERS")) {
                                Conf.CHATPANEL_USERS_LIST_X = xmlElement4.getProperty("X", 390);
                                Conf.CHATPANEL_USERS_LIST_Y = xmlElement4.getProperty("Y", 13);
                                Conf.CHATPANEL_USERS_LIST_W = xmlElement4.getProperty("WIDTH", 100);
                                Conf.CHATPANEL_USERS_LIST_H = xmlElement4.getProperty("HEIGHT", 245);
                                Conf.CHATPANEL_USERS_LIST_COLOR = xmlElement4.getProperty("COLOR", "000066");
                                Conf.CHATPANEL_USERS_LIST_BGCOLOR = xmlElement4.getProperty("BGCOLOR", "FFFFFF");
                                Conf.CHATPANEL_USERS_LIST_TITLE = xmlElement4.getProperty("TITLE", "> ALL USERS");
                                Conf.CHATPANEL_USERS_LIST_SEPARATOR = xmlElement4.getProperty("SEPARATOR", "===========");
                            }
                        }
                        else if (xmlElement4.getTagName().equalsIgnoreCase("BUTTON")) {
                            final String property11 = xmlElement4.getProperty("ID", (String)null);
                            if (property11 != null) {
                                if (property11.equalsIgnoreCase("SEND")) {
                                    Conf.CHATPANEL_SEND_BUTTON_X = xmlElement4.getProperty("X", 324);
                                    Conf.CHATPANEL_SEND_BUTTON_Y = xmlElement4.getProperty("Y", 270);
                                    Conf.CHATPANEL_SEND_BUTTON_W = xmlElement4.getProperty("WIDTH", 68);
                                    Conf.CHATPANEL_SEND_BUTTON_H = xmlElement4.getProperty("HEIGHT", 21);
                                    Conf.CHATPANEL_SEND_BUTTON_COLOR = xmlElement4.getProperty("COLOR", "000055");
                                    Conf.CHATPANEL_SEND_BUTTON_BGCOLOR = xmlElement4.getProperty("BGCOLOR", "CCCCCC");
                                    Conf.CHATPANEL_SEND_BUTTON_LABEL = xmlElement4.getContents();
                                }
                                else if (property11.equalsIgnoreCase("REFRESH")) {
                                    Conf.CHATPANEL_REFRESH_BUTTON_X = xmlElement4.getProperty("X", 396);
                                    Conf.CHATPANEL_REFRESH_BUTTON_Y = xmlElement4.getProperty("Y", 270);
                                    Conf.CHATPANEL_REFRESH_BUTTON_W = xmlElement4.getProperty("WIDTH", 70);
                                    Conf.CHATPANEL_REFRESH_BUTTON_H = xmlElement4.getProperty("HEIGHT", 21);
                                    Conf.CHATPANEL_REFRESH_BUTTON_COLOR = xmlElement4.getProperty("COLOR", "000000");
                                    Conf.CHATPANEL_REFRESH_BUTTON_BGCOLOR = xmlElement4.getProperty("BGCOLOR", "CCCCCC");
                                    Conf.CHATPANEL_REFRESH_BUTTON_LABEL = xmlElement4.getContents();
                                }
                                else if (property11.equalsIgnoreCase("LOGOUT")) {
                                    Conf.CHATPANEL_LOGOUT_BUTTON_X = xmlElement4.getProperty("X", 396);
                                    Conf.CHATPANEL_LOGOUT_BUTTON_Y = xmlElement4.getProperty("Y", 294);
                                    Conf.CHATPANEL_LOGOUT_BUTTON_W = xmlElement4.getProperty("WIDTH", 70);
                                    Conf.CHATPANEL_LOGOUT_BUTTON_H = xmlElement4.getProperty("HEIGHT", 21);
                                    Conf.CHATPANEL_LOGOUT_BUTTON_COLOR = xmlElement4.getProperty("COLOR", "000000");
                                    Conf.CHATPANEL_LOGOUT_BUTTON_BGCOLOR = xmlElement4.getProperty("BGCOLOR", "CCCCCC");
                                    Conf.CHATPANEL_LOGOUT_BUTTON_LABEL = xmlElement4.getContents();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    static StringBuffer loadXML(final URL url) throws Exception {
        final StringBuffer sb = new StringBuffer();
        final URLConnection openConnection = url.openConnection();
        openConnection.setDoOutput(true);
        openConnection.setDoInput(true);
        openConnection.setUseCaches(false);
        openConnection.setAllowUserInteraction(false);
        openConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(String.valueOf(String.valueOf(line)).concat("\n"));
        }
        bufferedReader.close();
        return sb;
    }
    
    static {
        Conf.URL = "http://localhost/jchatbox/xml_connector/processor.jsp";
        Conf.PARAMETERSMISSING = "PARAMETERSMISSING";
        Conf.MULTILANGUAGE = "multilanguage";
        Conf.UPDOWN = true;
        Conf.NAMELENGTH = 14;
        Conf.COPY = '©';
        Conf.RIGHT = String.valueOf(String.valueOf(Conf.COPY)).concat(" JavaZOOM");
        Conf.SEPARATOR2 = "___________";
        Conf.PREPRIVMSG = "\n----- ";
        Conf.DEFAULTLANGUAGE = "english";
        Conf.RESOURCES = null;
        Conf.APPLET_WIDTH = 500;
        Conf.APPLET_HEIGHT = 320;
        Conf.XMLFILE = "jcbskin.xml";
        Conf.SKINPATH = ".";
        Conf.LOGINPANEL_COLOR = "CCDDFF";
        Conf.LOGINPANEL_IMAGE = null;
        Conf.LOGINPANEL_FONTNAME = "Helvetica";
        Conf.LOGINPANEL_FONTSIZE = 12;
        Conf.LOGINPANEL_FONTSTYLE = 1;
        Conf.LOGINPANEL_PICTURE = null;
        Conf.LOGINPANEL_USERNAME_LABEL_COLOR = "000022";
        Conf.LOGINPANEL_USERNAME_LABEL_IMAGE = null;
        Conf.LOGINPANEL_USERNAME_LABEL = "Username :";
        Conf.LOGINPANEL_USERNAME_LABEL_MULTILANGUAGE = false;
        Conf.LOGINPANEL_USERNAME_LABEL_X = 7;
        Conf.LOGINPANEL_USERNAME_LABEL_Y = 14;
        Conf.LOGINPANEL_USERNAME_LABEL_W = 70;
        Conf.LOGINPANEL_USERNAME_LABEL_H = 23;
        Conf.LOGINPANEL_USERNAME_LABEL_PICTURE = null;
        Conf.LOGINPANEL_CHATROOMS_LABEL_COLOR = "000022";
        Conf.LOGINPANEL_CHATROOMS_LABEL_IMAGE = null;
        Conf.LOGINPANEL_CHATROOMS_LABEL = "Chatrooms :";
        Conf.LOGINPANEL_CHATROOMS_LABEL_MULTILANGUAGE = false;
        Conf.LOGINPANEL_CHATROOMS_LABEL_X = 5;
        Conf.LOGINPANEL_CHATROOMS_LABEL_Y = 86;
        Conf.LOGINPANEL_CHATROOMS_LABEL_W = 73;
        Conf.LOGINPANEL_CHATROOMS_LABEL_H = 22;
        Conf.LOGINPANEL_CHATROOMS_LABEL_PICTURE = null;
        Conf.LOGINPANEL_NAME_LABEL_COLOR = "000022";
        Conf.LOGINPANEL_NAME_LABEL_IMAGE = null;
        Conf.LOGINPANEL_NAME_LABEL = "Chatroom : ";
        Conf.LOGINPANEL_NAME_LABEL_MULTILANGUAGE = false;
        Conf.LOGINPANEL_NAME_LABEL_X = 300;
        Conf.LOGINPANEL_NAME_LABEL_Y = 12;
        Conf.LOGINPANEL_NAME_LABEL_W = 190;
        Conf.LOGINPANEL_NAME_LABEL_H = 22;
        Conf.LOGINPANEL_NAME_LABEL_PICTURE = null;
        Conf.LOGINPANEL_DATE_LABEL_COLOR = "000022";
        Conf.LOGINPANEL_DATE_LABEL_IMAGE = null;
        Conf.LOGINPANEL_DATE_LABEL = "Opening : ";
        Conf.LOGINPANEL_DATE_LABEL_MULTILANGUAGE = false;
        Conf.LOGINPANEL_DATE_LABEL_X = 300;
        Conf.LOGINPANEL_DATE_LABEL_Y = 35;
        Conf.LOGINPANEL_DATE_LABEL_W = 190;
        Conf.LOGINPANEL_DATE_LABEL_H = 22;
        Conf.LOGINPANEL_DATE_LABEL_PICTURE = null;
        Conf.LOGINPANEL_SUBJECT_LABEL_COLOR = "000022";
        Conf.LOGINPANEL_SUBJECT_LABEL_IMAGE = null;
        Conf.LOGINPANEL_SUBJECT_LABEL = "Subject : ";
        Conf.LOGINPANEL_SUBJECT_LABEL_MULTILANGUAGE = false;
        Conf.LOGINPANEL_SUBJECT_LABEL_X = 300;
        Conf.LOGINPANEL_SUBJECT_LABEL_Y = 55;
        Conf.LOGINPANEL_SUBJECT_LABEL_W = 190;
        Conf.LOGINPANEL_SUBJECT_LABEL_H = 22;
        Conf.LOGINPANEL_SUBJECT_LABEL_PICTURE = null;
        Conf.LOGINPANEL_WELCOME_LABEL_COLOR = "0000FF";
        Conf.LOGINPANEL_WELCOME_LABEL_IMAGE = null;
        Conf.LOGINPANEL_WELCOME_LABEL = "Welcome to jChatBox client Applet.";
        Conf.LOGINPANEL_WELCOME_LABEL_X = 125;
        Conf.LOGINPANEL_WELCOME_LABEL_Y = 164;
        Conf.LOGINPANEL_WELCOME_LABEL_W = 228;
        Conf.LOGINPANEL_WELCOME_LABEL_H = 51;
        Conf.LOGINPANEL_WELCOME_LABEL_PICTURE = null;
        Conf.LOGINPANEL_ERROR_LABEL_COLOR = "FF0000";
        Conf.LOGINPANEL_ERROR_LABEL_IMAGE = null;
        Conf.LOGINPANEL_ERROR_LABEL = "";
        Conf.LOGINPANEL_ERROR_LABEL_X = 5;
        Conf.LOGINPANEL_ERROR_LABEL_Y = 42;
        Conf.LOGINPANEL_ERROR_LABEL_W = 267;
        Conf.LOGINPANEL_ERROR_LABEL_H = 22;
        Conf.LOGINPANEL_ERROR_LABEL_PICTURE = null;
        Conf.LOGINPANEL_USERNAME_TEXTFIELD_BGCOLOR = "9999FF";
        Conf.LOGINPANEL_USERNAME_TEXTFIELD_COLOR = "000055";
        Conf.LOGINPANEL_USERNAME_TEXTFIELD_X = 79;
        Conf.LOGINPANEL_USERNAME_TEXTFIELD_Y = 16;
        Conf.LOGINPANEL_USERNAME_TEXTFIELD_W = 117;
        Conf.LOGINPANEL_USERNAME_TEXTFIELD_H = 20;
        Conf.LOGINPANEL_CHATROOMS_CHOICE_FIRSTITEM = "Select a chatroom";
        Conf.LOGINPANEL_CHATROOMS_CHOICE_FIRSTITEM_MULTILANGUAGE = false;
        Conf.LOGINPANEL_CHATROOMS_CHOICE_BGCOLOR = "CCCCFF";
        Conf.LOGINPANEL_CHATROOMS_CHOICE_COLOR = "000099";
        Conf.LOGINPANEL_CHATROOMS_CHOICE_X = 81;
        Conf.LOGINPANEL_CHATROOMS_CHOICE_Y = 87;
        Conf.LOGINPANEL_CHATROOMS_CHOICE_W = 130;
        Conf.LOGINPANEL_CHATROOMS_CHOICE_H = 20;
        Conf.LOGINPANEL_LOGIN_BUTTON_BGCOLOR = "CCCCCC";
        Conf.LOGINPANEL_LOGIN_BUTTON_COLOR = "000099";
        Conf.LOGINPANEL_LOGIN_BUTTON_LABEL = "Enter";
        Conf.LOGINPANEL_LOGIN_BUTTON_LABEL_MULTILANGUAGE = false;
        Conf.LOGINPANEL_LOGIN_BUTTON_IMAGE = null;
        Conf.LOGINPANEL_LOGIN_BUTTON_PRESSEDIMAGE = null;
        Conf.LOGINPANEL_LOGIN_BUTTON_X = 204;
        Conf.LOGINPANEL_LOGIN_BUTTON_Y = 16;
        Conf.LOGINPANEL_LOGIN_BUTTON_W = 68;
        Conf.LOGINPANEL_LOGIN_BUTTON_H = 21;
        Conf.CHATPANEL_COLOR = "CCDDFF";
        Conf.CHATPANEL_IMAGE = null;
        Conf.CHATPANEL_FONTNAME = "Helvetica";
        Conf.CHATPANEL_FONTSIZE = 12;
        Conf.CHATPANEL_FONTSTYLE = 1;
        Conf.CHATPANEL_PICTURE = null;
        Conf.CHATPANEL_MESSAGE_LABEL_COLOR = "000022";
        Conf.CHATPANEL_MESSAGE_LABEL_IMAGE = null;
        Conf.CHATPANEL_MESSAGE_LABEL = "Message :";
        Conf.CHATPANEL_MESSAGE_LABEL_MULTILANGUAGE = false;
        Conf.CHATPANEL_MESSAGE_LABEL_X = 4;
        Conf.CHATPANEL_MESSAGE_LABEL_Y = 268;
        Conf.CHATPANEL_MESSAGE_LABEL_W = 63;
        Conf.CHATPANEL_MESSAGE_LABEL_H = 22;
        Conf.CHATPANEL_MESSAGE_LABEL_PICTURE = null;
        Conf.CHATPANEL_NAME_LABEL_COLOR = "000022";
        Conf.CHATPANEL_NAME_LABEL_IMAGE = null;
        Conf.CHATPANEL_NAME_LABEL = "Chatroom :";
        Conf.CHATPANEL_NAME_LABEL_MULTILANGUAGE = false;
        Conf.CHATPANEL_NAME_LABEL_X = 4;
        Conf.CHATPANEL_NAME_LABEL_Y = 295;
        Conf.CHATPANEL_NAME_LABEL_W = 190;
        Conf.CHATPANEL_NAME_LABEL_H = 22;
        Conf.CHATPANEL_NAME_LABEL_PICTURE = null;
        Conf.CHATPANEL_MESSAGE_TEXTFIELD_BGCOLOR = "FFFFFF";
        Conf.CHATPANEL_MESSAGE_TEXTFIELD_COLOR = "000000";
        Conf.CHATPANEL_MESSAGE_TEXTFIELD_X = 65;
        Conf.CHATPANEL_MESSAGE_TEXTFIELD_Y = 270;
        Conf.CHATPANEL_MESSAGE_TEXTFIELD_W = 250;
        Conf.CHATPANEL_MESSAGE_TEXTFIELD_H = 21;
        Conf.CHATPANEL_SEND_BUTTON_BGCOLOR = "EEEEEE";
        Conf.CHATPANEL_SEND_BUTTON_COLOR = "000099";
        Conf.CHATPANEL_SEND_BUTTON_LABEL = "Send";
        Conf.CHATPANEL_SEND_BUTTON_LABEL_MULTILANGUAGE = false;
        Conf.CHATPANEL_SEND_BUTTON_IMAGE = null;
        Conf.CHATPANEL_SEND_BUTTON_PRESSEDIMAGE = null;
        Conf.CHATPANEL_SEND_BUTTON_X = 324;
        Conf.CHATPANEL_SEND_BUTTON_Y = 270;
        Conf.CHATPANEL_SEND_BUTTON_W = 68;
        Conf.CHATPANEL_SEND_BUTTON_H = 21;
        Conf.CHATPANEL_REFRESH_BUTTON_BGCOLOR = "EEEEEE";
        Conf.CHATPANEL_REFRESH_BUTTON_COLOR = "000000";
        Conf.CHATPANEL_REFRESH_BUTTON_LABEL = "Refresh";
        Conf.CHATPANEL_REFRESH_BUTTON_LABEL_MULTILANGUAGE = false;
        Conf.CHATPANEL_REFRESH_BUTTON_IMAGE = null;
        Conf.CHATPANEL_REFRESH_BUTTON_PRESSEDIMAGE = null;
        Conf.CHATPANEL_REFRESH_BUTTON_X = 396;
        Conf.CHATPANEL_REFRESH_BUTTON_Y = 270;
        Conf.CHATPANEL_REFRESH_BUTTON_W = 70;
        Conf.CHATPANEL_REFRESH_BUTTON_H = 21;
        Conf.CHATPANEL_LOGOUT_BUTTON_BGCOLOR = "CCCCCC";
        Conf.CHATPANEL_LOGOUT_BUTTON_COLOR = "330000";
        Conf.CHATPANEL_LOGOUT_BUTTON_LABEL = "Logout";
        Conf.CHATPANEL_LOGOUT_BUTTON_LABEL_MULTILANGUAGE = false;
        Conf.CHATPANEL_LOGOUT_BUTTON_IMAGE = null;
        Conf.CHATPANEL_LOGOUT_BUTTON_PRESSEDIMAGE = null;
        Conf.CHATPANEL_LOGOUT_BUTTON_X = 396;
        Conf.CHATPANEL_LOGOUT_BUTTON_Y = 294;
        Conf.CHATPANEL_LOGOUT_BUTTON_W = 70;
        Conf.CHATPANEL_LOGOUT_BUTTON_H = 21;
        Conf.CHATPANEL_MESSAGES_TEXTAREA_JOIN = " has joined the chatroom.";
        Conf.CHATPANEL_MESSAGES_TEXTAREA_JOIN_MULTILANGUAGE = false;
        Conf.CHATPANEL_MESSAGES_TEXTAREA_QUIT = " has left the chatroom.";
        Conf.CHATPANEL_MESSAGES_TEXTAREA_QUIT_MULTILANGUAGE = false;
        Conf.CHATPANEL_MESSAGES_TEXTAREA_PRIVATE = "Next messages are sent to : ";
        Conf.CHATPANEL_MESSAGES_TEXTAREA_PRIVATE_MULTILANGUAGE = false;
        Conf.CHATPANEL_MESSAGES_TEXTAREA_ALL = "Next messages are sent to ALL chatroom users.";
        Conf.CHATPANEL_MESSAGES_TEXTAREA_ALL_MULTILANGUAGE = false;
        Conf.CHATPANEL_MESSAGES_TEXTAREA_BGCOLOR = "FFFFFF";
        Conf.CHATPANEL_MESSAGES_TEXTAREA_COLOR = "000000";
        Conf.CHATPANEL_MESSAGES_TEXTAREA_X = 10;
        Conf.CHATPANEL_MESSAGES_TEXTAREA_Y = 13;
        Conf.CHATPANEL_MESSAGES_TEXTAREA_W = 368;
        Conf.CHATPANEL_MESSAGES_TEXTAREA_H = 245;
        Conf.CHATPANEL_USERS_LIST_TITLE = "> ALL USERS";
        Conf.CHATPANEL_USERS_LIST_TITLE_MULTILANGUAGE = false;
        Conf.CHATPANEL_USERS_LIST_SEPARATOR = "===========";
        Conf.CHATPANEL_USERS_LIST_BGCOLOR = "FFFFFF";
        Conf.CHATPANEL_USERS_LIST_COLOR = "000066";
        Conf.CHATPANEL_USERS_LIST_X = 390;
        Conf.CHATPANEL_USERS_LIST_Y = 13;
        Conf.CHATPANEL_USERS_LIST_W = 100;
        Conf.CHATPANEL_USERS_LIST_H = 245;
        Conf.INVALIDXML = "Invalid response (XML) from jChatBox server.";
        Conf.INVALIDRESPONSE = "Invalid response from server.";
        Conf.PLEASELOGOUT = "Please logout and try again ...";
        Conf.NAMETOOLONG = "Username too long.";
    }
}
