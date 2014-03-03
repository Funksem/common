package de.funksem.common.date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Diese Klasse berechnet Feiertage in Abh�ngikeit des Bundeslandes.
 * 
 * @author hartmann_t
 */
public enum FeiertageRegion
{

    /**
	 *
	 */
    Baden_Wuerttemberg("BW"),
    /**
	 *
	 */
    Bayern("BY"),
    /**
	 *
	 */
    Berlin("BE"),
    /**
	 *
	 */
    Brandenburg("BB"),
    /**
	 *
	 */
    Bremen("HB"),
    /**
	 *
	 */
    Hamburg("HH"),
    /**
	 *
	 */
    Hessen("HE"),
    /**
	 *
	 */
    Mecklenburg_Vorpommern("MV"),
    /**
	 *
	 */
    Niedersachsen("NI"),
    /**
	 *
	 */
    Nordrhein_Westfalen("NW"),
    /**
	 *
	 */
    Rheinland_Pfalz("RP"),
    /**
	 *
	 */
    Saarland("SL"),
    /**
	 *
	 */
    Sachsen("SN"),
    /**
	 *
	 */
    Sachsen_Anhalt("ST"),
    /**
	 *
	 */
    Schleswig_Holstein("SH"),
    /**
	 *
	 */
    Thueringen("TH");

    private final String kuerzel;
    private final int[] matrix;

    /**
     * Erzeugt eine Instanz von {@link FeiertageRegion}.
     * 
     * @param kuerzel Bundesland Kurzbezeichnung
     */
    private FeiertageRegion(String kuerzel)
    {
        this.kuerzel = kuerzel;
        matrix = FeiertageMatrix.FOR_ALL[ordinal()];
    }

    public String getKuerzel()
    {
        return kuerzel;
    }

    public int[] getFeiertageMatrix()
    {
        return matrix.clone();
    }

    /**
     * Definition der Bundesland abh�ngigen Feiertage.
     */
    private static final class FeiertageMatrix
    {
        /**
         * Erzeugt eine Instanz von FeiertageMatrix.
         */
        private FeiertageMatrix()
        {

        }

        /**
         * Feiertagematrix f�r alle Bundesl�nder<br>
         * 0 - kein Feriertag<br>
         * 1 - Feiertag<br>
         * <br>
         * Reihenfolge der Felder:<br>
         * <ul>
         * <li>Neujahrstag</li>
         * <li>Heilige Drei K�nige</li>
         * <li>Karfreitag</li>
         * <li>Ostermontag</li>
         * <li>Tag der Arbeit</li>
         * <li>Christi Himmelfahrt</li>
         * <li>Pfingstmontag</li>
         * <li>Fronleichnam</li>
         * <li>Mari� Himmelfahrt</li>
         * <li>Tag der Deutschen Einheit</li>
         * <li>Reformationstag</li>
         * <li>Allerheiligen</li>
         * <li>Bu�- und Bettag</li>
         * <li>1. Weihnachtstag</li>
         * <li>2. Weihnachtstag</li>
         * </ul>
         */
        public static final int[][] FOR_ALL = { { 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 }, // Baden-W�rttemberg
                { 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 }, // Bayern
                { 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 }, // Berlin
                { 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 }, // Brandenburg
                { 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 }, // Bremen
                { 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 }, // Hamburg
                { 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 }, // Hessen
                { 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 }, // Mecklenburg-Vorpommern
                { 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 }, // Niedersachsen
                { 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1 }, // Nordrhein-Westfalen
                { 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 }, // Rheinland-Pfalz
                { 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 }, // Saarland
                { 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 }, // Sachsen
                { 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 }, // Sachsen-Anhalt
                { 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 }, // Schleswig-Holstein
                { 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1 }, // Th�ringen
        };
    }

    /**
     * Setzen der Feiertage.
     * 
     * @param year Jahr
     * @return Liste mit Feiertagen
     */
    public static List<GregorianCalendar> identifyFeiertage(int year)
    {
        final List<GregorianCalendar> holidays = new ArrayList<GregorianCalendar>();
        // Ostern vorab berechnen
        final GregorianCalendar ostersonntag = MoreDate.osterdatum(year);
        GregorianCalendar tmp;
        //CHECKSTYLE:OFF Konstanten sind hier m.E. nicht sinnvoll
        // Neujahr
        holidays.add(new GregorianCalendar(year, Calendar.JANUARY, 1));
        // Heilige drei K�nige
        holidays.add(new GregorianCalendar(year, Calendar.JANUARY, 6));
        // Karfreitag
        tmp = (GregorianCalendar) ostersonntag.clone();
        tmp.add(Calendar.DAY_OF_MONTH, -2);
        holidays.add(tmp);
        // Ostermontag
        tmp = (GregorianCalendar) ostersonntag.clone();
        tmp.add(Calendar.DAY_OF_MONTH, +1);
        holidays.add(tmp);
        // Tag der Arbeit
        holidays.add(new GregorianCalendar(year, Calendar.MAY, 1));
        // Christi Himmelfahrt
        tmp = (GregorianCalendar) ostersonntag.clone();
        tmp.add(Calendar.DAY_OF_MONTH, +39);
        holidays.add(tmp);
        // Pfingstmontag
        tmp = (GregorianCalendar) ostersonntag.clone();
        tmp.add(Calendar.DAY_OF_MONTH, +50);
        holidays.add(tmp);
        // Fronleichnam
        tmp = (GregorianCalendar) ostersonntag.clone();
        tmp.add(Calendar.DAY_OF_MONTH, +60);
        holidays.add(tmp);
        // Mari� Himmelfahrt
        holidays.add(new GregorianCalendar(year, Calendar.AUGUST, 15));
        // Tag der Deutschen Einheit
        holidays.add(new GregorianCalendar(year, Calendar.OCTOBER, 3));
        // Reformationstag
        holidays.add(new GregorianCalendar(year, Calendar.OCTOBER, 31));
        // Allerheiligen
        holidays.add(new GregorianCalendar(year, Calendar.NOVEMBER, 1));
        // Bu�- und Bettag
        tmp = new GregorianCalendar(year, Calendar.NOVEMBER, 23);
        final int day = tmp.get(Calendar.DAY_OF_WEEK);
        int diff = 0;
        switch (day)
        {
            case Calendar.THURSDAY:
                diff = -1;
            break;
            case Calendar.FRIDAY:
                diff = -2;
            break;
            case Calendar.SATURDAY:
                diff = -3;
            break;
            case Calendar.SUNDAY:
                diff = -4;
            break;
            case Calendar.MONDAY:
                diff = -5;
            break;
            case Calendar.TUESDAY:
                diff = -6;
            break;
            case Calendar.WEDNESDAY:
                diff = -7;
            break;
            default:
            break;
        }
        tmp.add(Calendar.DAY_OF_MONTH, diff);
        holidays.add(tmp);
        // 1. Weihnachtstag
        holidays.add(new GregorianCalendar(year, Calendar.DECEMBER, 25));
        // 2. Weihnachtstag
        holidays.add(new GregorianCalendar(year, Calendar.DECEMBER, 26));
        return holidays;
        //CHECKSTYLE:ON
    }

}
