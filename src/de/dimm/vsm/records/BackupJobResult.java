/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import de.dimm.vsm.fsengine.LazyList;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Administrator
 */
@Entity
public class BackupJobResult  implements Serializable
{
    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;

    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    protected Date startTime;

    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    protected Date endTime;

    boolean ok;

    String status;
   
    @OneToMany(mappedBy = "jobResult")
    private List<BackupVolumeResult> backupVolumeResults;
    
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
        return "BackupJob " + startTime.toString() + " " + (ok?"ok":"nok") + " " + status != null ? status : "";
    }


    @ManyToOne
    Schedule schedule;

    /**
     * @param idx the idx to set
     */
    public void setIdx( long idx )
    {
        this.idx = idx;
    }

    public Schedule getSchedule()
    {
        return schedule;
    }

    public void setSchedule( Schedule sched )
    {
        this.schedule = sched;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public String getStatus()
    {
        return status;
    }

    public boolean isOk()
    {
        return ok;
    }

    public void setEndTime( Date endTime )
    {
        this.endTime = endTime;
    }

    public void setOk( boolean ok )
    {
        this.ok = ok;
    }

    public void setStartTime( Date startTime )
    {
        this.startTime = startTime;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }

    public LazyList<BackupVolumeResult> getBackupVolumeResults()
    {
        return (LazyList<BackupVolumeResult>)backupVolumeResults;
    }

    public void setBackupVolumeResults( LazyList<BackupVolumeResult> backupVolumeResults )
    {
        this.backupVolumeResults = backupVolumeResults;
    }

}
