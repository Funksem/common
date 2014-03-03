package de.funksem.common.date;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Diese Klasse stellt weitere Methoden f�r Zeit und Datum bereit.
 * 
 * @author hartmann_t
 */
public final class MoreDate
{
    /**
     * direkte Instanzbildung unterbinden
     */
    private MoreDate()
    {
    }

    /**
     * Liefert den n�chsten Werktag.
     * 
     * @param cal Datum
     * @param region Region - Bundesland
     * @return n�chster Werktag
     */
    public static GregorianCalendar nextWorkingDay(GregorianCalendar cal, FeiertageRegion region)
    {
        if ((cal != null) && !isWorkingDay(cal, region))
        {
            cal.add(Calendar.DAY_OF_MONTH, 1);
            nextWorkingDay(cal, region);
        }
        return cal;
    }

    /**
     * Ermittelt ob das �bergebene Datum ein Arbeitstag ist
     * 
     * @param cal zu untersuchendes Datum
     * @param region Region - Bundesland
     * @return <code> true </code> wenn das Datum ein Werktag ist
     */
    public static boolean isWorkingDay(GregorianCalendar cal, FeiertageRegion region)
    {
        final FeiertageHelper helper = new FeiertageHelper(cal);
        if (region != null)
        {
            helper.setRegion(region);
        }
        return !(isWeekend(cal) || helper.isHoliday());
    }

    /**
     * Ermittelt ob das �bergebene Datum auf einem Wochenende (Sa, So) liegt.
     * 
     * @param cal zu untersuchendes Datum
     * @return <code> true </code> wenn das Datum auf einem Wochenende liegt.
     */
    public static boolean isWeekend(GregorianCalendar cal)
    {
        return ((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (cal
            .get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY));
    }

    /**
     * Berechne Ostersonntag.
     * 
     * @param jahr Jahr
     * @return Datum von Ostersonntag
     */
    public static GregorianCalendar osterdatum(int jahr)
    {
        //CHECKSTYLE:OFF
        int a, b, c, d, e, f, g, h, i, j, k, l; //NOPMD
        int x, mon, tag; //NOPMD
        if (jahr <= 1583)
        {
            // julianisch --- obsoleted
            a = jahr % 4;
            b = jahr % 7;
            c = jahr % 19;
            d = ((19 * c) + 15) % 30;
            e = ((((2 * a) + (4 * b)) - d) + 34) % 7;
            x = d + e + 114;
            mon = x / 31;
            tag = (x % 31) + 1;
        }
        else
        {
            // gregorianisch
            a = jahr % 19;
            b = jahr / 100;
            c = jahr % 100;
            d = b / 4;
            e = b % 4;
            f = (b + 8) / 25;
            g = ((b - f) + 1) / 3;
            h = ((((19 * a) + b) - d - g) + 15) % 30;
            i = c / 4;
            j = c % 4;
            k = ((32 + (2 * e) + (2 * i)) - h - j) % 7;
            l = (a + (11 * h) + (22 * k)) / 451;
            x = ((h + k) - (7 * l)) + 114;
            mon = x / 31;
            tag = (x % 31) + 1;
        }
        //CHECKSTYLE:ON
        return new GregorianCalendar(jahr, mon - 1, tag);
    }
}
