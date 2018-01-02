// 
// Decompiled by Procyon v0.5.30
// 

package mika.util;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.FontMetrics;
import mika.system.S_Debug;
import mika.system.S_TextReader;
import java.awt.Image;
import java.awt.Container;
import java.awt.Component;
import java.awt.Color;
import mika.graphics.G_Font;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.TextField;

public class U_Form
{
    String m_strAlias;
    public TextField m_tfAlias;
    int m_iAliasX;
    int m_iAliasY;
    int m_iAliasW;
    String m_strEmail;
    TextField m_tfEmail;
    int m_iEmailX;
    int m_iEmailY;
    int m_iEmailW;
    String m_strPhone;
    TextField m_tfPhone;
    int m_iPhoneX;
    int m_iPhoneY;
    int m_iPhoneW;
    String m_strCity;
    TextField m_tfCity;
    int m_iCityX;
    int m_iCityY;
    int m_iCityW;
    String m_strAreaCode;
    TextField m_tfAreaCode;
    int m_iAreaCodeX;
    int m_iAreaCodeY;
    int m_iAreaCodeW;
    String m_strStreet;
    TextField m_tfStreet;
    int m_iStreetX;
    int m_iStreetY;
    int m_iStreetW;
    String m_strName;
    TextField m_tfName;
    int m_iNameX;
    int m_iNameY;
    int m_iNameW;
    String m_strAllowed;
    Checkbox m_cbAllowed;
    int m_iAllowedX;
    int m_iAllowedY;
    int m_iAllowedW;
    String m_strAge;
    TextField m_tfAge;
    int m_iAgeX;
    int m_iAgeY;
    int m_iAgeW;
    CheckboxGroup m_cgSex;
    String m_strSexMale;
    Checkbox m_cbSexMale;
    String m_strSexFemale;
    Checkbox m_cbSexFemale;
    int m_iSexX;
    int m_iSexY;
    G_Font m_Font;
    Color m_clrText;
    Color m_clrControlBackground;
    final int ALIAS_INDEX = 0;
    final int NAME_INDEX = 1;
    final int AGE_INDEX = 2;
    final int SEX_FEMALE_INDEX = 3;
    final int SEX_MALE_INDEX = 4;
    final int STREET_INDEX = 5;
    final int AREA_CODE_INDEX = 6;
    final int CITY_INDEX = 7;
    final int PHONE_INDEX = 8;
    final int EMAIL_INDEX = 9;
    final int ALLOWED_INDEX = 10;
    final int MAX_COMPONENTS = 11;
    Component[] m_component;
    boolean[] m_bHasFocus;
    Container m_parent;
    boolean m_bFullForm;
    private Image m_background;
    private int m_xofs;
    private int m_yofs;
    
    public String getAlias() {
        return this.m_tfAlias.getText();
    }
    
    public String getEmail() {
        return this.m_tfEmail.getText();
    }
    
    public String getPhone() {
        return this.m_tfPhone.getText();
    }
    
    public String getCity() {
        return this.m_tfCity.getText();
    }
    
    public String getAreaCode() {
        return this.m_tfAreaCode.getText();
    }
    
    public String getStreet() {
        return this.m_tfStreet.getText();
    }
    
    public String getName() {
        return this.m_tfName.getText();
    }
    
    public boolean getCheckboxState() {
        return this.m_cbAllowed.getState();
    }
    
    public String getAge() {
        return this.m_tfAge.getText();
    }
    
    public String getSex() {
        if (this.m_cbSexMale.getState()) {
            return this.m_strSexMale;
        }
        if (this.m_cbSexFemale.getState()) {
            return this.m_strSexFemale;
        }
        return "-";
    }
    
    public void setBackground(final Image background, final int xofs, final int yofs) {
        this.m_background = background;
        this.m_xofs = xofs;
        this.m_yofs = yofs;
    }
    
    public U_Form(final Container parent) {
        this.m_strAlias = "Alias:";
        this.m_strEmail = "Email:";
        this.m_strPhone = "Phone:";
        this.m_strCity = "City:";
        this.m_strAreaCode = "Area code:";
        this.m_strStreet = "Street:";
        this.m_strName = "Name:";
        this.m_strAllowed = "";
        this.m_strAge = "Age:";
        this.m_strSexMale = "Male";
        this.m_strSexFemale = "Female";
        this.m_clrText = Color.white;
        this.m_clrControlBackground = Color.black;
        this.m_component = new Component[11];
        this.m_bHasFocus = new boolean[11];
        this.m_bFullForm = false;
        this.m_parent = parent;
        for (int i = 0; i < 11; ++i) {
            this.m_bHasFocus[i] = false;
        }
    }
    
    public U_Form(final Container parent, final S_TextReader s_TextReader) {
        this.m_strAlias = "Alias:";
        this.m_strEmail = "Email:";
        this.m_strPhone = "Phone:";
        this.m_strCity = "City:";
        this.m_strAreaCode = "Area code:";
        this.m_strStreet = "Street:";
        this.m_strName = "Name:";
        this.m_strAllowed = "";
        this.m_strAge = "Age:";
        this.m_strSexMale = "Male";
        this.m_strSexFemale = "Female";
        this.m_clrText = Color.white;
        this.m_clrControlBackground = Color.black;
        this.m_component = new Component[11];
        this.m_bHasFocus = new boolean[11];
        this.m_bFullForm = false;
        this.m_parent = parent;
        this.readForm(s_TextReader);
    }
    
    public void readFont(final S_TextReader s_TextReader) throws Exception {
        this.m_Font = new G_Font(s_TextReader);
        this.setTextColor(new Color(s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue()));
        this.setControlBackgroundColor(new Color(s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue()));
    }
    
    public void readForm(final S_TextReader s_TextReader) {
        try {
            for (int i = 0; i < 11; ++i) {
                this.m_bHasFocus[i] = false;
            }
            this.readFont(s_TextReader);
            this.m_bFullForm = Boolean.valueOf(s_TextReader.readStringValue());
            this.addAliasField(s_TextReader.readLine(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
            if (this.m_bFullForm) {
                this.addNameField(s_TextReader.readLine(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
                this.addAgeField(s_TextReader.readLine(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
                this.addSexCombobox(s_TextReader.readLine(), s_TextReader.readLine(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
                this.addStreetField(s_TextReader.readLine(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
                this.addAreaCodeField(s_TextReader.readLine(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
                this.addCityField(s_TextReader.readLine(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
                this.addPhoneField(s_TextReader.readLine(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
                this.addEmailField(s_TextReader.readLine(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
                this.addCheckbox(s_TextReader.readLine(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
            }
        }
        catch (Exception ex) {
            S_Debug.print(ex);
        }
    }
    
    public void setFocusToAliasField() {
        try {
            for (int i = 0; i < 11; ++i) {
                this.m_bHasFocus[i] = false;
            }
            this.m_component[0].requestFocus();
            this.m_bHasFocus[0] = true;
        }
        catch (Exception ex) {
            S_Debug.print(ex);
        }
    }
    
    public void setTextColor(final Color clrText) {
        this.m_clrText = clrText;
    }
    
    public Color getTextColor() {
        return this.m_clrText;
    }
    
    public void setControlBackgroundColor(final Color clrControlBackground) {
        this.m_clrControlBackground = clrControlBackground;
    }
    
    public Color getControlBackgroundColor() {
        return this.m_clrControlBackground;
    }
    
    public G_Font getFont() {
        return this.m_Font;
    }
    
    public void addCheckbox(final String s, final int iAllowedX, final int iAllowedY, final int iAllowedW) {
        this.m_strAllowed = s.replace('_', ' ');
        this.m_cbAllowed = new Checkbox(this.m_strAllowed);
        this.m_component[10] = this.m_cbAllowed;
        this.m_parent.add(this.m_cbAllowed);
        this.m_iAllowedX = iAllowedX;
        this.m_iAllowedY = iAllowedY;
        this.m_iAllowedW = iAllowedW;
        this.m_cbAllowed.setBackground(this.m_clrControlBackground);
        this.m_cbAllowed.setForeground(this.m_clrText);
        this.m_cbAllowed.setState(true);
    }
    
    public void addAliasField(final String s, final int iAliasX, final int iAliasY, final int iAliasW) {
        this.m_strAlias = s.replace('_', ' ');
        (this.m_tfAlias = new TextField()).setBackground(Color.white);
        this.m_component[0] = this.m_tfAlias;
        this.m_parent.add(this.m_tfAlias);
        this.m_iAliasX = iAliasX;
        this.m_iAliasY = iAliasY;
        this.m_iAliasW = iAliasW;
    }
    
    public void addNameField(final String s, final int iNameX, final int iNameY, final int iNameW) {
        this.m_strName = s.replace('_', ' ');
        this.m_tfName = new TextField();
        this.m_component[1] = this.m_tfName;
        this.m_tfName.setBackground(Color.white);
        this.m_parent.add(this.m_tfName);
        this.m_iNameX = iNameX;
        this.m_iNameY = iNameY;
        this.m_iNameW = iNameW;
    }
    
    public void addStreetField(final String s, final int iStreetX, final int iStreetY, final int iStreetW) {
        this.m_strStreet = s.replace('_', ' ');
        this.m_tfStreet = new TextField();
        this.m_component[5] = this.m_tfStreet;
        this.m_tfStreet.setBackground(Color.white);
        this.m_parent.add(this.m_tfStreet);
        this.m_iStreetX = iStreetX;
        this.m_iStreetY = iStreetY;
        this.m_iStreetW = iStreetW;
    }
    
    public void addAreaCodeField(final String s, final int iAreaCodeX, final int iAreaCodeY, final int iAreaCodeW) {
        this.m_strAreaCode = s.replace('_', ' ');
        this.m_tfAreaCode = new TextField();
        this.m_component[6] = this.m_tfAreaCode;
        this.m_tfAreaCode.setBackground(Color.white);
        this.m_parent.add(this.m_tfAreaCode);
        this.m_iAreaCodeX = iAreaCodeX;
        this.m_iAreaCodeY = iAreaCodeY;
        this.m_iAreaCodeW = iAreaCodeW;
    }
    
    public void addCityField(final String s, final int iCityX, final int iCityY, final int iCityW) {
        this.m_strCity = s.replace('_', ' ');
        this.m_tfCity = new TextField();
        this.m_component[7] = this.m_tfCity;
        this.m_tfCity.setBackground(Color.white);
        this.m_parent.add(this.m_tfCity);
        this.m_iCityX = iCityX;
        this.m_iCityY = iCityY;
        this.m_iCityW = iCityW;
    }
    
    public void addPhoneField(final String s, final int iPhoneX, final int iPhoneY, final int iPhoneW) {
        this.m_strPhone = s.replace('_', ' ');
        this.m_tfPhone = new TextField();
        this.m_component[8] = this.m_tfPhone;
        this.m_tfPhone.setBackground(Color.white);
        this.m_parent.add(this.m_tfPhone);
        this.m_iPhoneX = iPhoneX;
        this.m_iPhoneY = iPhoneY;
        this.m_iPhoneW = iPhoneW;
    }
    
    public void addEmailField(final String s, final int iEmailX, final int iEmailY, final int iEmailW) {
        this.m_strEmail = s.replace('_', ' ');
        this.m_tfEmail = new TextField();
        this.m_component[9] = this.m_tfEmail;
        this.m_tfEmail.setBackground(Color.white);
        this.m_parent.add(this.m_tfEmail);
        this.m_iEmailX = iEmailX;
        this.m_iEmailY = iEmailY;
        this.m_iEmailW = iEmailW;
    }
    
    public void addAgeField(final String s, final int iAgeX, final int iAgeY, final int iAgeW) {
        this.m_strAge = s.replace('_', ' ');
        this.m_tfAge = new TextField();
        this.m_component[2] = this.m_tfAge;
        this.m_tfAge.setBackground(Color.white);
        this.m_parent.add(this.m_tfAge);
        this.m_iAgeX = iAgeX;
        this.m_iAgeY = iAgeY;
        this.m_iAgeW = iAgeW;
    }
    
    public void addSexCombobox(final String s, final String s2, final int iSexX, final int iSexY) {
        this.m_strSexMale = s.replace('_', ' ');
        this.m_strSexFemale = s2.replace('_', ' ');
        this.m_cgSex = new CheckboxGroup();
        this.m_iSexX = iSexX;
        this.m_iSexY = iSexY;
        this.m_cbSexFemale = new Checkbox(this.m_strSexFemale, this.m_cgSex, true);
        this.m_component[3] = this.m_cbSexFemale;
        this.m_parent.add(this.m_cbSexFemale);
        this.m_cbSexFemale.setBackground(this.m_clrControlBackground);
        this.m_cbSexFemale.setForeground(this.m_clrText);
        this.m_cbSexMale = new Checkbox(this.m_strSexMale, this.m_cgSex, false);
        this.m_component[4] = this.m_cbSexMale;
        this.m_parent.add(this.m_cbSexMale);
        this.m_cbSexMale.setBackground(this.m_clrControlBackground);
        this.m_cbSexMale.setForeground(this.m_clrText);
    }
    
    public void show() {
        for (int i = 0; i < 11; ++i) {
            if (this.m_component[i] != null) {
                this.m_component[i].show();
            }
        }
    }
    
    public void hide() {
        for (int i = 0; i < 11; ++i) {
            if (this.m_component[i] != null) {
                this.m_component[i].hide();
            }
        }
    }
    
    public void repositionElements() {
        FontMetrics fontMetrics = null;
        for (int i = 0; i < 11; ++i) {
            if (this.m_component[i] != null) {
                fontMetrics = this.m_component[i].getFontMetrics(this.m_Font.getFont());
            }
        }
        final int descent = fontMetrics.getDescent();
        if (this.m_tfAlias != null) {
            this.m_tfAlias.reshape(this.m_iAliasX, this.m_iAliasY + descent, this.m_iAliasW, this.m_tfAlias.minimumSize().height);
        }
        if (this.m_tfName != null) {
            this.m_tfName.reshape(this.m_iNameX, this.m_iNameY + descent, this.m_iNameW, this.m_tfName.minimumSize().height);
        }
        if (this.m_tfStreet != null) {
            this.m_tfStreet.reshape(this.m_iStreetX, this.m_iStreetY + descent, this.m_iStreetW, this.m_tfStreet.minimumSize().height);
        }
        if (this.m_tfAreaCode != null) {
            this.m_tfAreaCode.reshape(this.m_iAreaCodeX, this.m_iAreaCodeY + descent, this.m_iAreaCodeW, this.m_tfAreaCode.minimumSize().height);
        }
        if (this.m_tfCity != null) {
            this.m_tfCity.reshape(this.m_iCityX, this.m_iCityY + descent, this.m_iCityW, this.m_tfCity.minimumSize().height);
        }
        if (this.m_tfPhone != null) {
            this.m_tfPhone.reshape(this.m_iPhoneX, this.m_iPhoneY + descent, this.m_iPhoneW, this.m_tfPhone.minimumSize().height);
        }
        if (this.m_tfEmail != null) {
            this.m_tfEmail.reshape(this.m_iEmailX, this.m_iEmailY + descent, this.m_iEmailW, this.m_tfEmail.minimumSize().height);
        }
        if (this.m_cbAllowed != null) {
            this.m_cbAllowed.reshape(this.m_iAllowedX, this.m_iAllowedY, this.m_iAllowedW, this.m_cbAllowed.minimumSize().height);
        }
        if (this.m_tfAge != null) {
            this.m_tfAge.reshape(this.m_iAgeX, this.m_iAgeY + descent, this.m_iAgeW, this.m_tfAge.minimumSize().height);
        }
        if (this.m_cbSexFemale != null && this.m_cbSexMale != null) {
            int n = fontMetrics.stringWidth(this.m_strSexFemale) * 2;
            if (fontMetrics.stringWidth(this.m_strSexMale) * 2 > n) {
                n = fontMetrics.stringWidth(this.m_strSexMale) * 2;
            }
            this.m_cbSexMale.reshape(this.m_iSexX, this.m_iSexY, n, this.m_cbSexMale.minimumSize().height);
            this.m_cbSexFemale.reshape(this.m_iSexX, this.m_iSexY + this.m_cbSexMale.minimumSize().height, n, this.m_cbSexFemale.minimumSize().height);
        }
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void keyUp(final Event event, final int n) {
        switch (n) {
            case 9: {
                for (int i = 0; i < 11; ++i) {
                    if (this.m_component[i] != null && this.m_bHasFocus[i]) {
                        for (int j = 1; j < 11; ++j) {
                            if (this.m_component[j] != null) {
                                final int n2 = (i + j) % 11;
                                this.m_component[n2].requestFocus();
                                this.m_bHasFocus[i] = false;
                                this.m_bHasFocus[n2] = true;
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
}
