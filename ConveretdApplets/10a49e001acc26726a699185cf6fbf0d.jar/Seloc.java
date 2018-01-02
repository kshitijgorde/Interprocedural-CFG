import java.awt.Choice;

// 
// Decompiled by Procyon v0.5.30
// 

class Seloc
{
    double latitude;
    double longitude;
    String separator;
    
    public void locMenu(final Choice locChoice) {
        locChoice.addItem("User Input");
        locChoice.addItem("Home");
        locChoice.addItem("EUROPE ---------");
        locChoice.addItem("Ankara");
        locChoice.addItem("Amsterdam");
        locChoice.addItem("Athens");
        locChoice.addItem("Belgrade");
        locChoice.addItem("Bratislava");
        locChoice.addItem("Brussels");
        locChoice.addItem("Bucharest");
        locChoice.addItem("Budapest");
        locChoice.addItem("Copenhagen");
        locChoice.addItem("Dublin");
        locChoice.addItem("Edinburgh");
        locChoice.addItem("Geneva");
        locChoice.addItem("Helsinki");
        locChoice.addItem("Las Palmas");
        locChoice.addItem("Lisbon");
        locChoice.addItem("London");
        locChoice.addItem("Luxembourg");
        locChoice.addItem("Madrid");
        locChoice.addItem("Moscow");
        locChoice.addItem("Oslo");
        locChoice.addItem("Palma de Mallorca");
        locChoice.addItem("Paris");
        locChoice.addItem("Prague");
        locChoice.addItem("Rome");
        locChoice.addItem("Sofia");
        locChoice.addItem("Stockholm");
        locChoice.addItem("Tirana");
        locChoice.addItem("Vienna");
        locChoice.addItem("Warsaw");
        locChoice.addItem("Zurich");
        locChoice.addItem("GERMANY --------");
        locChoice.addItem("Berlin");
        locChoice.addItem("Dortmund");
        locChoice.addItem("Dresden");
        locChoice.addItem("Frankfurt / M");
        locChoice.addItem("Frankfurt / O");
        locChoice.addItem("Hamburg");
        locChoice.addItem("Kassel");
        locChoice.addItem("Kiel");
        locChoice.addItem("Koeln");
        locChoice.addItem("Muenchen");
        locChoice.addItem("Stuttgart");
        locChoice.addItem("Welver");
        locChoice.addItem("50N10E");
        locChoice.addItem("USA ------------");
        locChoice.addItem("Anchorage AK");
        locChoice.addItem("Atlanta GA");
        locChoice.addItem("Augusta ME");
        locChoice.addItem("Baltimore MD");
        locChoice.addItem("Birmingham AL");
        locChoice.addItem("Bismarck ND");
        locChoice.addItem("Boise ID");
        locChoice.addItem("Boston MA");
        locChoice.addItem("Bridgeport CT");
        locChoice.addItem("Charleston WV");
        locChoice.addItem("Chicago IL");
        locChoice.addItem("Cleveland OH");
        locChoice.addItem("Columbia SC");
        locChoice.addItem("Concord NH");
        locChoice.addItem("Dallas TX");
        locChoice.addItem("Denver CO");
        locChoice.addItem("Des Moines IA");
        locChoice.addItem("Detroit MI");
        locChoice.addItem("Dover DE");
        locChoice.addItem("Eugene OR");
        locChoice.addItem("Ft. Lauderdale FL");
        locChoice.addItem("Fort Knox KY");
        locChoice.addItem("Green Bay WI");
        locChoice.addItem("Helena MT");
        locChoice.addItem("Honolulu HI");
        locChoice.addItem("Indianapolis IN");
        locChoice.addItem("Jackson MS");
        locChoice.addItem("Jackson WY");
        locChoice.addItem("Lincoln NE");
        locChoice.addItem("Little Rock AR");
        locChoice.addItem("Los Angeles CA");
        locChoice.addItem("Memphis TN");
        locChoice.addItem("Miami FL");
        locChoice.addItem("Minneapolis MN");
        locChoice.addItem("Montpelier VT");
        locChoice.addItem("New Orleans LA");
        locChoice.addItem("New York NY");
        locChoice.addItem("Oklahoma City OK");
        locChoice.addItem("Philadelphia PA");
        locChoice.addItem("Phoenix AZ");
        locChoice.addItem("Providence RI");
        locChoice.addItem("Raleigh NC");
        locChoice.addItem("Reno NV");
        locChoice.addItem("Topeka KS");
        locChoice.addItem("Saint Louis MO");
        locChoice.addItem("Salt Lake UT");
        locChoice.addItem("San Francisco CA");
        locChoice.addItem("Santa Fe NM");
        locChoice.addItem("Trenton NJ");
        locChoice.addItem("Walla Walla WA");
        locChoice.addItem("Washington");
        locChoice.addItem("CANADA ---------");
        locChoice.addItem("Edmonton");
        locChoice.addItem("Halifax");
        locChoice.addItem("Montreal");
        locChoice.addItem("Quebec");
        locChoice.addItem("Regina");
        locChoice.addItem("Toronto");
        locChoice.addItem("Vancouver");
        locChoice.addItem("Victoria");
        locChoice.addItem("Whitehorse");
        locChoice.addItem("Winnipeg");
        locChoice.addItem("Yellowknife");
        locChoice.addItem("SOUTH AMERICA --");
        locChoice.addItem("Bogota");
        locChoice.addItem("Buenos Aires");
        locChoice.addItem("Caracas");
        locChoice.addItem("La Paz");
        locChoice.addItem("Lima");
        locChoice.addItem("Montevideo");
        locChoice.addItem("Quito");
        locChoice.addItem("Rio de Janeiro");
        locChoice.addItem("Santiago");
        locChoice.addItem("AUSTRALIA, NZ --");
        locChoice.addItem("Auckland");
        locChoice.addItem("Darwin");
        locChoice.addItem("Melbourne");
        locChoice.addItem("Perth");
        locChoice.addItem("Sydney");
        locChoice.addItem("Wellington");
        locChoice.addItem("ASIA -----------");
        locChoice.addItem("Hong Kong");
        locChoice.addItem("Jakarta");
        locChoice.addItem("New Delhi");
        locChoice.addItem("Peking");
        locChoice.addItem("Shanghai");
        locChoice.addItem("Singapore");
        locChoice.addItem("Tokyo");
    }
    
    public void timeMenu(final Choice timeChoice) {
        timeChoice.addItem(" 0 h");
        timeChoice.addItem(" -1 h");
        timeChoice.addItem(" +1 h");
        timeChoice.addItem(" -2 h");
        timeChoice.addItem(" +2 h");
        timeChoice.addItem(" -3 h");
        timeChoice.addItem(" +3 h");
        timeChoice.addItem(" -4 h");
        timeChoice.addItem(" +4 h");
        timeChoice.addItem(" -5 h");
        timeChoice.addItem(" +5 h");
        timeChoice.addItem(" -6 h");
        timeChoice.addItem(" +6 h");
        timeChoice.addItem(" -7 h");
        timeChoice.addItem(" +7 h");
        timeChoice.addItem(" -8 h");
        timeChoice.addItem(" +8 h");
        timeChoice.addItem(" -9 h");
        timeChoice.addItem(" +9 h");
        timeChoice.addItem(" -10 h");
        timeChoice.addItem(" +10 h");
        timeChoice.addItem(" -11 h");
        timeChoice.addItem(" +11 h");
        timeChoice.addItem(" -12 h");
        timeChoice.addItem(" +12 h");
        timeChoice.addItem(" +13 h");
    }
    
    public int getTimeZone(final String timeString) {
        int timeOffset = 0;
        if (timeString.equals(" 0 h")) {
            timeOffset = 0;
        }
        else if (timeString.equals(" -1 h")) {
            timeOffset = -1;
        }
        else if (timeString.equals(" +1 h")) {
            timeOffset = 1;
        }
        else if (timeString.equals(" -2 h")) {
            timeOffset = -2;
        }
        else if (timeString.equals(" +2 h")) {
            timeOffset = 2;
        }
        else if (timeString.equals(" -3 h")) {
            timeOffset = -3;
        }
        else if (timeString.equals(" +3 h")) {
            timeOffset = 3;
        }
        else if (timeString.equals(" -4 h")) {
            timeOffset = -4;
        }
        else if (timeString.equals(" +4 h")) {
            timeOffset = 4;
        }
        else if (timeString.equals(" -5 h")) {
            timeOffset = -5;
        }
        else if (timeString.equals(" +5 h")) {
            timeOffset = 5;
        }
        else if (timeString.equals(" -6 h")) {
            timeOffset = -6;
        }
        else if (timeString.equals(" +6 h")) {
            timeOffset = 6;
        }
        else if (timeString.equals(" -7 h")) {
            timeOffset = -7;
        }
        else if (timeString.equals(" +7 h")) {
            timeOffset = 7;
        }
        else if (timeString.equals(" -8 h")) {
            timeOffset = -8;
        }
        else if (timeString.equals(" +8 h")) {
            timeOffset = 8;
        }
        else if (timeString.equals(" -9 h")) {
            timeOffset = -9;
        }
        else if (timeString.equals(" +9 h")) {
            timeOffset = 9;
        }
        else if (timeString.equals(" -10 h")) {
            timeOffset = -10;
        }
        else if (timeString.equals(" +10 h")) {
            timeOffset = 10;
        }
        else if (timeString.equals(" -11 h")) {
            timeOffset = -11;
        }
        else if (timeString.equals(" +11 h")) {
            timeOffset = 11;
        }
        else if (timeString.equals(" -12 h")) {
            timeOffset = -12;
        }
        else if (timeString.equals(" +12 h")) {
            timeOffset = 12;
        }
        else if (timeString.equals(" +13 h")) {
            timeOffset = 13;
        }
        return timeOffset;
    }
    
    public double getLatLong(final String locString, final int n) {
        double value = 0.0;
        if (locString.equals("User Input")) {
            this.latitude = 0.0;
            this.longitude = 0.0;
        }
        if (locString.equals("Berlin")) {
            this.latitude = 51.62;
            this.longitude = 13.41;
        }
        else if (locString.equals("Dortmund")) {
            this.latitude = 51.52;
            this.longitude = 7.47;
        }
        else if (locString.equals("Hamburg")) {
            this.latitude = 53.55;
            this.longitude = 9.97;
        }
        else if (locString.equals("Muenchen")) {
            this.latitude = 48.15;
            this.longitude = 11.61;
        }
        else if (locString.equals("Berlin")) {
            this.latitude = 52.51;
            this.longitude = 13.41;
        }
        else if (locString.equals("Dresden")) {
            this.latitude = 51.05;
            this.longitude = 13.73;
        }
        else if (locString.equals("Frankfurt / M")) {
            this.latitude = 50.11;
            this.longitude = 8.69;
        }
        else if (locString.equals("Frankfurt / O")) {
            this.latitude = 52.37;
            this.longitude = 14.55;
        }
        else if (locString.equals("Rostock")) {
            this.latitude = 54.09;
            this.longitude = 12.14;
        }
        else if (locString.equals("Koeln")) {
            this.latitude = 50.94;
            this.longitude = 6.96;
        }
        else if (locString.equals("Kiel")) {
            this.latitude = 54.32;
            this.longitude = 10.12;
        }
        else if (locString.equals("Stuttgart")) {
            this.latitude = 48.78;
            this.longitude = 9.18;
        }
        else if (locString.equals("Kassel")) {
            this.latitude = 51.32;
            this.longitude = 9.5;
        }
        else if (locString.equals("Welver")) {
            this.latitude = 51.62;
            this.longitude = 7.97;
        }
        else if (locString.equals("50N10E")) {
            this.latitude = 50.0;
            this.longitude = 10.0;
        }
        else if (locString.equals("Ankara")) {
            this.latitude = 40.13;
            this.longitude = 33.0;
        }
        else if (locString.equals("Amsterdam")) {
            this.latitude = 52.38;
            this.longitude = 4.89;
        }
        else if (locString.equals("Athens")) {
            this.latitude = 37.97;
            this.longitude = 23.72;
        }
        else if (locString.equals("Belgrade")) {
            this.latitude = 44.83;
            this.longitude = 20.46;
        }
        else if (locString.equals("Belgrade")) {
            this.latitude = 44.83;
            this.longitude = 20.46;
        }
        else if (locString.equals("Bratislava")) {
            this.latitude = 48.15;
            this.longitude = 17.12;
        }
        else if (locString.equals("Brussels")) {
            this.latitude = 50.85;
            this.longitude = 4.37;
        }
        else if (locString.equals("Bucharest")) {
            this.latitude = 44.5;
            this.longitude = 26.1;
        }
        else if (locString.equals("Budapest")) {
            this.latitude = 47.49;
            this.longitude = 19.06;
        }
        else if (locString.equals("Copenhagen")) {
            this.latitude = 55.69;
            this.longitude = 12.58;
        }
        else if (locString.equals("Dublin")) {
            this.latitude = 53.39;
            this.longitude = -6.34;
        }
        else if (locString.equals("Edinburgh")) {
            this.latitude = 55.95;
            this.longitude = -3.1;
        }
        else if (locString.equals("Geneva")) {
            this.latitude = 46.2;
            this.longitude = 6.15;
        }
        else if (locString.equals("Helsinki")) {
            this.latitude = 60.16;
            this.longitude = 24.96;
        }
        else if (locString.equals("Las Palmas")) {
            this.latitude = 27.9;
            this.longitude = -15.7;
        }
        else if (locString.equals("Lisbon")) {
            this.latitude = 38.71;
            this.longitude = -9.19;
        }
        else if (locString.equals("London")) {
            this.latitude = 51.51;
            this.longitude = -0.1;
        }
        else if (locString.equals("Luxembourg")) {
            this.latitude = 49.6;
            this.longitude = 6.2;
        }
        else if (locString.equals("Moscow")) {
            this.latitude = 55.76;
            this.longitude = 37.57;
        }
        else if (locString.equals("Madrid")) {
            this.latitude = 40.41;
            this.longitude = -3.69;
        }
        else if (locString.equals("Oslo")) {
            this.latitude = 59.92;
            this.longitude = 10.73;
        }
        else if (locString.equals("Palma de Mallorca")) {
            this.latitude = 39.57;
            this.longitude = 2.65;
        }
        else if (locString.equals("Paris")) {
            this.latitude = 48.84;
            this.longitude = 2.34;
        }
        else if (locString.equals("Prague")) {
            this.latitude = 50.09;
            this.longitude = 14.42;
        }
        else if (locString.equals("Rome")) {
            this.latitude = 41.9;
            this.longitude = 12.48;
        }
        else if (locString.equals("Sofia")) {
            this.latitude = 42.7;
            this.longitude = 23.45;
        }
        else if (locString.equals("Stockholm")) {
            this.latitude = 59.34;
            this.longitude = 18.06;
        }
        else if (locString.equals("Tirana")) {
            this.latitude = 41.2;
            this.longitude = 19.9;
        }
        else if (locString.equals("Warsaw")) {
            this.latitude = 52.22;
            this.longitude = 21.03;
        }
        else if (locString.equals("Vienna")) {
            this.latitude = 48.21;
            this.longitude = 16.38;
        }
        else if (locString.equals("Zurich")) {
            this.latitude = 47.38;
            this.longitude = 8.55;
        }
        else if (locString.equals("Anchorage AK")) {
            this.latitude = 61.17;
            this.longitude = -149.98;
        }
        else if (locString.equals("Atlanta GA")) {
            this.latitude = 33.75;
            this.longitude = -84.38;
        }
        else if (locString.equals("Augusta ME")) {
            this.latitude = 44.31;
            this.longitude = -69.78;
        }
        else if (locString.equals("Baltimore MD")) {
            this.latitude = 39.29;
            this.longitude = -76.61;
        }
        else if (locString.equals("Birmingham AL")) {
            this.latitude = 33.52;
            this.longitude = -86.81;
        }
        else if (locString.equals("Bismarck ND")) {
            this.latitude = 46.81;
            this.longitude = -100.79;
        }
        else if (locString.equals("Boise ID")) {
            this.latitude = 43.61;
            this.longitude = -116.2;
        }
        else if (locString.equals("Boston MA")) {
            this.latitude = 42.33;
            this.longitude = -71.08;
        }
        else if (locString.equals("Bridgeport CT")) {
            this.latitude = 41.18;
            this.longitude = -73.19;
        }
        else if (locString.equals("Charleston WV")) {
            this.latitude = 38.35;
            this.longitude = -81.63;
        }
        else if (locString.equals("Chicago IL")) {
            this.latitude = 41.88;
            this.longitude = -87.63;
        }
        else if (locString.equals("Cleveland OH")) {
            this.latitude = 39.29;
            this.longitude = -76.61;
        }
        else if (locString.equals("Columbia SC")) {
            this.latitude = 45.61;
            this.longitude = -98.31;
        }
        else if (locString.equals("Concord NH")) {
            this.latitude = 43.21;
            this.longitude = -71.54;
        }
        else if (locString.equals("Dallas TX")) {
            this.latitude = 32.79;
            this.longitude = -96.79;
        }
        else if (locString.equals("Denver CO")) {
            this.latitude = 39.73;
            this.longitude = -104.98;
        }
        else if (locString.equals("Des Moines IA")) {
            this.latitude = 41.59;
            this.longitude = -93.62;
        }
        else if (locString.equals("Detroit MI")) {
            this.latitude = 42.38;
            this.longitude = -83.08;
        }
        else if (locString.equals("Dover DE")) {
            this.latitude = 39.13;
            this.longitude = -75.55;
        }
        else if (locString.equals("Eugene OR")) {
            this.latitude = 44.05;
            this.longitude = -123.09;
        }
        else if (locString.equals("Ft. Lauderdale FL")) {
            this.latitude = 26.12;
            this.longitude = -80.14;
        }
        else if (locString.equals("Fort Knox KY")) {
            this.latitude = 37.91;
            this.longitude = -85.97;
        }
        else if (locString.equals("Green Bay WI")) {
            this.latitude = 44.51;
            this.longitude = -88.01;
        }
        else if (locString.equals("Helena MT")) {
            this.latitude = 46.59;
            this.longitude = -112.04;
        }
        else if (locString.equals("Honolulu HI")) {
            this.latitude = 21.31;
            this.longitude = -157.86;
        }
        else if (locString.equals("Indianapolis IN")) {
            this.latitude = 39.77;
            this.longitude = -86.16;
        }
        else if (locString.equals("Jackson MS")) {
            this.latitude = 32.3;
            this.longitude = -90.19;
        }
        else if (locString.equals("Jackson WY")) {
            this.latitude = 43.61;
            this.longitude = -110.73;
        }
        else if (locString.equals("Lincoln NE")) {
            this.latitude = 40.8;
            this.longitude = -96.67;
        }
        else if (locString.equals("Little Rock AR")) {
            this.latitude = 34.75;
            this.longitude = -92.29;
        }
        else if (locString.equals("Los Angeles CA")) {
            this.latitude = 34.05;
            this.longitude = -118.24;
        }
        else if (locString.equals("Memphis TN")) {
            this.latitude = 35.15;
            this.longitude = -90.05;
        }
        else if (locString.equals("Miami FL")) {
            this.latitude = 25.774;
            this.longitude = -80.194;
        }
        else if (locString.equals("Minneapolis MN")) {
            this.latitude = 44.98;
            this.longitude = -93.26;
        }
        else if (locString.equals("Montpelier VT")) {
            this.latitude = 44.26;
            this.longitude = -72.58;
        }
        else if (locString.equals("New Orleans LA")) {
            this.latitude = 29.95;
            this.longitude = -90.07;
        }
        else if (locString.equals("New York NY")) {
            this.latitude = 40.76;
            this.longitude = -73.97;
        }
        else if (locString.equals("Oklahoma City OK")) {
            this.latitude = 35.47;
            this.longitude = -97.52;
        }
        else if (locString.equals("Philadelphia PA")) {
            this.latitude = 39.95;
            this.longitude = -75.16;
        }
        else if (locString.equals("Phoenix AZ")) {
            this.latitude = 33.3;
            this.longitude = -112.03;
        }
        else if (locString.equals("Providence RI")) {
            this.latitude = 41.82;
            this.longitude = -71.41;
        }
        else if (locString.equals("Raleigh NC")) {
            this.latitude = 35.78;
            this.longitude = -78.64;
        }
        else if (locString.equals("Reno NV")) {
            this.latitude = 39.52;
            this.longitude = -119.81;
        }
        else if (locString.equals("Richmond VA")) {
            this.latitude = 37.55;
            this.longitude = -77.46;
        }
        else if (locString.equals("Saint Louis MO")) {
            this.latitude = 38.63;
            this.longitude = -90.18;
        }
        else if (locString.equals("Salt Lake UT")) {
            this.latitude = 40.77;
            this.longitude = -111.88;
        }
        else if (locString.equals("San Francisco CA")) {
            this.latitude = 37.79;
            this.longitude = -122.43;
        }
        else if (locString.equals("Santa Fe NM")) {
            this.latitude = 35.69;
            this.longitude = -105.94;
        }
        else if (locString.equals("Trenton NJ")) {
            this.latitude = 40.22;
            this.longitude = -74.74;
        }
        else if (locString.equals("Topeka KS")) {
            this.latitude = 39.05;
            this.longitude = -95.67;
        }
        else if (locString.equals("Walla Walla WA")) {
            this.latitude = 46.07;
            this.longitude = -118.34;
        }
        else if (locString.equals("Washington")) {
            this.latitude = 38.92;
            this.longitude = -77.0;
        }
        else if (locString.equals("Edmonton")) {
            this.latitude = 53.55;
            this.longitude = -113.5;
        }
        else if (locString.equals("Halifax")) {
            this.latitude = 44.65;
            this.longitude = -63.6;
        }
        else if (locString.equals("Montreal")) {
            this.latitude = 45.5;
            this.longitude = -73.6;
        }
        else if (locString.equals("Vancouver")) {
            this.latitude = 49.32;
            this.longitude = -123.08;
        }
        else if (locString.equals("Whitehorse")) {
            this.latitude = 60.72;
            this.longitude = -135.05;
        }
        else if (locString.equals("Yellowknife")) {
            this.latitude = 62.45;
            this.longitude = -114.35;
        }
        else if (locString.equals("Winnipeg")) {
            this.latitude = 49.88;
            this.longitude = -97.17;
        }
        else if (locString.equals("Victoria")) {
            this.latitude = 48.43;
            this.longitude = -123.35;
        }
        else if (locString.equals("Quebec")) {
            this.latitude = 46.8;
            this.longitude = -71.25;
        }
        else if (locString.equals("Regina")) {
            this.latitude = 50.45;
            this.longitude = -104.62;
        }
        else if (locString.equals("Toronto")) {
            this.latitude = 43.67;
            this.longitude = -79.42;
        }
        else if (locString.equals("Bogota")) {
            this.latitude = 3.03;
            this.longitude = -75.17;
        }
        else if (locString.equals("Buenos Aires")) {
            this.latitude = -34.8;
            this.longitude = -58.0;
        }
        else if (locString.equals("Caracas")) {
            this.latitude = 10.5;
            this.longitude = -66.93;
        }
        else if (locString.equals("La Paz")) {
            this.latitude = -16.5;
            this.longitude = -68.15;
        }
        else if (locString.equals("Lima")) {
            this.latitude = -12.05;
            this.longitude = -77.05;
        }
        else if (locString.equals("Montevideo")) {
            this.latitude = -34.9;
            this.longitude = -56.17;
        }
        else if (locString.equals("Quito")) {
            this.latitude = -0.5;
            this.longitude = -78.5;
        }
        else if (locString.equals("Rio de Janeiro")) {
            this.latitude = -22.88;
            this.longitude = -43.28;
        }
        else if (locString.equals("Santiago")) {
            this.latitude = -33.45;
            this.longitude = -70.67;
        }
        else if (locString.equals("Auckland")) {
            this.latitude = -36.85;
            this.longitude = 174.77;
        }
        else if (locString.equals("Melbourne")) {
            this.latitude = -37.82;
            this.longitude = 144.97;
        }
        else if (locString.equals("Darwin")) {
            this.latitude = -12.47;
            this.longitude = 130.83;
        }
        else if (locString.equals("Perth")) {
            this.latitude = -31.93;
            this.longitude = 115.83;
        }
        else if (locString.equals("Sydney")) {
            this.latitude = -33.87;
            this.longitude = 151.22;
        }
        else if (locString.equals("Wellington")) {
            this.latitude = -41.3;
            this.longitude = 174.78;
        }
        else if (locString.equals("Tokyo")) {
            this.latitude = 35.7;
            this.longitude = 139.77;
        }
        else if (locString.equals("New Delhi")) {
            this.latitude = 28.63;
            this.longitude = 77.2;
        }
        else if (locString.equals("Hong Kong")) {
            this.latitude = 22.5;
            this.longitude = 114.0;
        }
        else if (locString.equals("Jakarta")) {
            this.latitude = -6.0;
            this.longitude = 107.0;
        }
        else if (locString.equals("Peking")) {
            this.latitude = 39.91;
            this.longitude = 116.5;
        }
        else if (locString.equals("Singapore")) {
            this.latitude = 1.27;
            this.longitude = 103.85;
        }
        else if (locString.equals("Shanghai")) {
            this.latitude = 31.22;
            this.longitude = 121.46;
        }
        if (n == 1) {
            value = this.latitude;
        }
        else {
            value = this.longitude;
        }
        return value;
    }
    
    Seloc() {
        this.separator = "---------------";
    }
}
