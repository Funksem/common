package de.funksem.common.date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Diese Klasse berechnet Feiertage.
 * 
 * @author hartmann_t
 */
public class FeiertageHelper extends GregorianCalendar
{
    private static final long serialVersionUID = 1L;

    /** Liste mit Feiertagen */
    List<GregorianCalendar> holidays = new ArrayList<GregorianCalendar>();

    /** Region - Bundesland (Default ist NRW) */
    FeiertageRegion nRegion = FeiertageRegion.Nordrhein_Westfalen;

    // ----------------- Constructors ---------------------------------------------
    /**
     * Erzeugt eine Instanz von {@link FeiertageHelper}.<br>
     * .. mit dem aktuellen Datum.
     */
    public FeiertageHelper()
    {
        super();
        setHolidays();
    }

    /**
     * Erzeugt eine Instanz von {@link FeiertageHelper}.
     * 
     * @param year Jahr
     * @param month Monat
     * @param day Tag
     */
    public FeiertageHelper(int year, int month, int day)
    {
        super();
        super.set(year, month, day);
        setHolidays();
    }

    /**
     * Erzeugt eine Instanz von {@link FeiertageHelper}.
     * 
     * @param d Instanz von {@link Calendar}
     */
    public FeiertageHelper(Calendar d)
    {
        this(d.get(Calendar.YEAR), d.get(Calendar.MONTH), d.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Erzeugt eine Instanz von {@link FeiertageHelper}.
     * 
     * @param d Instanz von {@link GregorianCalendar}
     */
    public FeiertageHelper(GregorianCalendar d)
    {
        this(d.get(Calendar.YEAR), d.get(Calendar.MONTH), d.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Erzeugt eine Instanz von {@link FeiertageHelper}.<br>
     * .. mit vorgegebenen Jahr, dann wird der 1. Januar gesetzt.
     * 
     * @param year Jahr
     */
    public FeiertageHelper(int year)
    {
        this(year, Calendar.JANUARY, 1);
    }

    // ---------------------------------------------------------------------------

    public void setRegion(FeiertageRegion region)
    {
        nRegion = region;
    }

    /**
     * Setzen der Feiertage.
     */
    private void setHolidays()
    {
        final int year = get(GregorianCalendar.YEAR);
        holidays.addAll(FeiertageRegion.identifyFeiertage(year));
    }

    /**
     * Vergleicht zwei Datum, anhand von Jahr, Monat und Tag.
     * 
     * @param gc Vergleich Datum
     * @return <code> true </code> wenn
     */
    private boolean compareDates(GregorianCalendar gc)
    {
        return ((gc != null) && (get(GregorianCalendar.DAY_OF_MONTH) == gc.get(DAY_OF_MONTH))
            && (get(GregorianCalendar.MONTH) == gc.get(GregorianCalendar.MONTH)) && (get(GregorianCalendar.YEAR) == gc
                .get(GregorianCalendar.YEAR)));
    }

    /**
     * Ist Datum ein Feiertag.
     * 
     * @return <code> true </code> wenn das Datum ein Feiertag ist
     */
    public boolean isHoliday()
    {
        boolean ret = false;

        for (int i = 0; i < holidays.size(); i++)
        {
            if (compareDates(holidays.get(i)))
            {
                ret = nRegion.getFeiertageMatrix()[i] == 1;
                break;
            }
        }
        return ret;
    }
}