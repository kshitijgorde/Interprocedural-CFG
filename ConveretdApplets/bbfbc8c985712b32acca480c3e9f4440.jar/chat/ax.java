// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class ax
{
    public static String[] a;
    public static String[] b;
    
    public static String a(final String s) {
        for (int i = 0; i < ax.a.length; ++i) {
            if (s.equalsIgnoreCase(ax.a[i])) {
                return ax.b[i];
            }
        }
        return null;
    }
    
    static {
        ax.a = new String[] { "DZ", "BH", "DJ", "EG", "ET", "ER", "IR", "IQ", "JO", "KW", "LB", "LY", "ML", "MR", "MA", "OM", "PS", "QA", "SA", "SO", "SD", "SY", "TN", "AE", "YE", "AF", "AL", "AS", "AD", "AO", "AI", "AQ", "AG", "AR", "AM", "AW", "AU", "AT", "AZ", "BD", "BB", "BY", "BE", "BZ", "BJ", "BM", "BT", "BO", "BA", "BW", "BV", "BR", "IO", "VG", "BN", "BG", "BF", "MM", "BI", "KH", "CM", "CA", "CV", "KY", "CF", "TD", "CL", "CN", "CX", "CC", "CO", "KM", "CD", "CG", "CK", "CR", "CI", "HR", "CU", "CY", "CZ", "DK", "DM", "DO", "TP", "EC", "SV", "GQ", "EE", "FK", "FO", "FJ", "FI", "FR", "FX", "GF", "PF", "TF", "GA", "GE", "DE", "GH", "GI", "GR", "GL", "GD", "GP", "GU", "GT", "GG", "GN", "GW", "GY", "HT", "HM", "VA", "HN", "HK", "HU", "IS", "IN", "ID", "IE", "IL", "IT", "JM", "JP", "JE", "KZ", "KE", "KI", "KP", "KR", "KG", "LA", "LV", "LS", "LR", "LI", "LT", "LU", "MO", "MK", "MG", "MW", "MY", "MV", "MT", "IM", "MH", "MQ", "MU", "YT", "MX", "FM", "MD", "MC", "MN", "MS", "MZ", "NA", "NR", "NP", "AN", "NL", "NC", "NZ", "NI", "NE", "NG", "NU", "NF", "MP", "NO", "PK", "PW", "PA", "PG", "PY", "PE", "PH", "PN", "PL", "PT", "PR", "RE", "RO", "RU", "RW", "ST", "SH", "KN", "LC", "PM", "VC", "WS", "SM", "SN", "SC", "SL", "SG", "SK", "SI", "SB", "ZA", "GS", "ES", "LK", "SR", "SJ", "SZ", "SE", "CH", "TW", "TJ", "TZ", "TH", "BS", "GM", "TG", "TK", "TO", "TT", "TR", "TM", "TC", "TV", "UG", "UA", "UK", "UM", "US", "UY", "UZ", "VU", "VE", "VN", "VI", "WF", "EH", "YU", "ZM", "ZW" };
        ax.b = new String[] { "Algeria", "Bahrain", "Djibouti", "Egypt", "Ethiopia", "Eritrea", "Iran", "Iraq", "Jordan", "Kuwait", "Lebanon", "Libya", "Mali", "Mauritania", "Morocco", "Oman", "Palestinian Territory, Occupied", "Qatar", "Saudi Arabia", "Somalia", "Sudan", "Syria", "Tunisia", "United Arab Emirates", "Yemen", "Afghanistan", "Albania", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burma", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo, Democratic Republic of the", "Congo, Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "El Salvador", "Equatorial Guinea", "Estonia", "Falkland Islands (Islas Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France, Metropolitan", "French Guiana", "French Polynesia", "French Southern and Antarctic Lands", "Gabon", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guernsey", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard Island and McDonald Islands", "Holy See (Vatican City)", "Honduras", "Hong Kong (SAR)", "Hungary", "Iceland", "India", "Indonesia", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jersey", "Kazakhstan", "Kenya", "Kiribati", "Korea, North", "Korea, South", "Kyrgyzstan", "Laos", "Latvia", "Lesotho", "Liberia", "Liechtenstein", "Lithuania", "Luxembourg", "Macao", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Malta", "Man, Isle of", "Marshall Islands", "Martinique", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova", "Monaco", "Mongolia", "Montserrat", "Mozambique", "Namibia", "Nauru", "Nepal", "Netherlands Antilles", "Netherlands", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn Islands", "Poland", "Portugal", "Puerto Rico", "R\u00e9union", "Romania", "Russia", "Rwanda", "Sao Tome and Principe", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "Suriname", "Svalbard", "Swaziland", "Sweden", "Switzerland", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "The Bahamas", "The Gambia", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Kingdom", "United States Minor Outlying Islands", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands", "Wallis and Futuna", "Western Sahara", "Yugoslavia", "Zambia", "Zimbabwe" };
    }
}