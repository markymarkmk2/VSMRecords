/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Administrator
 */
@Entity
public class RetentionWindow  implements Serializable
{
    static final long serialVersionUID = 1L;
    
    public static final String DAILY = "d";
    public static final String WEEKLY = "w";
    public static final String YEARLY = "y";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;

    @ManyToOne
    private Retention retention;
    
    private boolean disabled;
    private String cycleString;
    private long startOffsetStartMs;  // RELATIVE UHRZEIT AM TAG
    private int  startDayNumber;       // WENN ZYKLUSDAUER > 1d, DANN NUMMER DES TAGES IM ZYKLUS, DER WOCHENTAG ERGIBTS SICH AUS DEM GÜLTIG AB
    private int  startWeekNumber;       // WENN ZYKLUSDAUER > 1w, DANN NUMMER DER WOCHE IM ZYKLUS
    private long endOffsetStartMs;  // RELATIVE UHRZEIT AM TAG
    private int  endDayNumber;       // WENN ZYKLUSDAUER > 1d, DANN NUMMER DES TAGES IM ZYKLUS, DER WOCHENTAG ERGIBTS SICH AUS DEM GÜLTIG AB
    private int  endWeekNumber;       // WENN ZYKLUSDAUER > 1d, DANN NUMMER DES WOCHE IM ZYKLUS

    private boolean negated;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date retentionStart;

    /**
     * @return the idx
     */
    public long getIdx()
    {
        return idx;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        long startOffsetS = getStartOffsetStartMs() / 1000;
        int sh = (int)(startOffsetS / 3600);
        int sm = (int)((startOffsetS/60) % 60);

        long endOffsetS = getEndOffsetStartMs() / 1000;
        int eh = (int)(endOffsetS / 3600);
        int em = (int)((endOffsetS/60) % 60);
        
        String startTimeStr = String.format("%02d:%02d", sh, sm);
        String endTimeStr = String.format("%02d:%02d", eh, em);        

        if (getDisabled())
        {
            sb.append("Gesperrt");
            sb.append(" ");
        }
        if (DAILY.equals(cycleString)) {
            sb.append("d ");
            sb.append(startTimeStr);            
            sb.append(" - ");
            sb.append(endTimeStr);            
        }
        if (WEEKLY.equals(cycleString)) {
            DateFormatSymbols symbols = new DateFormatSymbols();
            String[] dayNames = symbols.getShortWeekdays();
            sb.append("w ");
            sb.append(dayNames[startDayNumber + 1]);
            sb.append(" ");
            sb.append(startTimeStr);
            sb.append(" - ");
            sb.append(dayNames[endDayNumber + 1]);
            sb.append(" ");
            sb.append(endTimeStr);    
        }
        if (YEARLY.equals(cycleString)) {
            DateFormatSymbols symbols = new DateFormatSymbols();
            GregorianCalendar cal = new GregorianCalendar();
            cal.setWeekDate(cal.getWeekYear(), startWeekNumber + 1, startDayNumber + 1);
            SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
            String[] dayNames = symbols.getShortWeekdays();
            sb.append("y ");
            sb.append(sdf.format(new Date(cal.getTimeInMillis())));
            sb.append(" ");
            sb.append(startTimeStr);
            sb.append(" - ");
            cal.setWeekDate(cal.getWeekYear(), endWeekNumber + 1, endDayNumber + 1);
            sb.append(sdf.format(new Date(cal.getTimeInMillis())));            
            sb.append(" ");
            sb.append(endTimeStr);    
        }
        return sb.toString();
    }


   
    /**
     * @param idx the idx to set
     */
    public void setIdx( long idx )
    {
        this.idx = idx;
    }

    /**
     * @return the disabled
     */
    public boolean getDisabled()
    {
        return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled( boolean disabled )
    {
        this.disabled = disabled;
    }

    /**
     * @return the offsetStartMs
     */
    public long getStartOffsetStartMs()
    {
        return startOffsetStartMs;
    }

    /**
     * @param offsetStartMs the offsetStartMs to set
     */
    public void setStartOffsetStartMs( long offsetStartMs )
    {
        this.startOffsetStartMs = offsetStartMs;
    }
   
    public void setStartDayNumber( int dayNumber )
    {
        this.startDayNumber = dayNumber;
    }

    public int getstartDayNumber()
    {
        return startDayNumber;
    }

    public long getEndOffsetStartMs() {
        return endOffsetStartMs;
    }

    public int getEndDayNumber() {
        return endDayNumber;
    }

    public int getEndWeekNumber() {
        return endWeekNumber;
    }

    public Date getRetentionStart() {
        return retentionStart;
    }

    public int getStartDayNumber() {
        return startDayNumber;
    }

    public int getStartWeekNumber() {
        return startWeekNumber;
    }

    public void setCycleString( String cycleString ) {
        this.cycleString = cycleString;
    }

    public String getCycleString() {
        return cycleString;
    }

    public void setEndDayNumber( int endDayNumber ) {
        this.endDayNumber = endDayNumber;
    }

    public void setEndOffsetStartMs( long endOffsetStartMs ) {
        this.endOffsetStartMs = endOffsetStartMs;
    }

    public void setEndWeekNumber( int endWeekNumber ) {
        this.endWeekNumber = endWeekNumber;
    }

    public void setRetentionStart( Date retentionStart ) {
        this.retentionStart = retentionStart;
    }

    public void setStartWeekNumber( int startWeekNumber ) {
        this.startWeekNumber = startWeekNumber;
    }

    public Retention getRetention() {
        return retention;
    }

    public void setRetention( Retention retention ) {
        this.retention = retention;
    }

    public boolean isNegated() {
        return negated;
    }

    public void setNegated( boolean negated ) {
        this.negated = negated;
    }
    
    
    
    
}
