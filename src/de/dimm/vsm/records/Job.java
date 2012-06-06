/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Administrator
 */
@Entity
public class Job  implements Serializable
{
    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;

    private boolean disabled;
    private long offsetStartMs;  // RELATIVE UHRZEIT AM TAG
    private int  dayNumber;       // WENN ZYKLUSDAUER > 1d, DANN NUMMER DES TAGES IM ZYKLUS, DER WOCHENTAG ERGIBTS SICH AUS DEM SCHEDULE GÜLTIG AB
    private boolean overrideSnapshotEnabled;


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

        if (getDisabled())
        {
            sb.append("Gesperrt");
            sb.append(" ");
        }
        if (getSched() != null && getSched().getCycleLengthMs() > 86400000)
        {
            if (getDayNumber() >= 0)
            {
                sb.append("Tag");
                sb.append(" ");
                sb.append(getDayNumber());
                sb.append(" ");
            }
        }
        long offsetS = getOffsetStartMs() / 1000;
        sb.append(offsetS / 3600);
        sb.append(":");
        sb.append((offsetS/60) % 60);



        return sb.toString();
        
    }


    @ManyToOne
    Schedule sched;

    /**
     * @param idx the idx to set
     */
    public void setIdx( long idx )
    {
        this.idx = idx;
    }

    public Schedule getSched()
    {
        return sched;
    }

    public void setSched( Schedule sched )
    {
        this.sched = sched;
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
    public long getOffsetStartMs()
    {
        return offsetStartMs;
    }

    /**
     * @param offsetStartMs the offsetStartMs to set
     */
    public void setOffsetStartMs( long offsetStartMs )
    {
        this.offsetStartMs = offsetStartMs;
    }

    /**
     * @return the overrideSnapshotEnabled
     */
    public boolean getOverrideSnapshotEnabled()
    {
        return overrideSnapshotEnabled;
    }

    /**
     * @param overrideSnapshotEnabled the overrideSnapshotEnabled to set
     */
    public void setOverrideSnapshotEnabled( boolean overrideSnapshotEnabled )
    {
        this.overrideSnapshotEnabled = overrideSnapshotEnabled;
    }

    public void setDayNumber( int dayNumber )
    {
        this.dayNumber = dayNumber;
    }

    public int getDayNumber()
    {
        return dayNumber;
    }


}
