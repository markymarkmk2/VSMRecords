/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import java.io.Serializable;
import java.util.Date;
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
public class BackupVolumeResult  implements Serializable
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
    @ManyToOne
    private BackupJobResult jobResult;

    @ManyToOne
    private ClientVolume volume;
    private long filesChecked;
    private long filesTransfered;
    private long dataChecked;
    private long dataTransfered;

   
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
        return "BackupVol " + volume.toString() + " " + (ok?"ok":"nok") + " " + status != null ? status : "";
    }

    /**
     * @param idx the idx to set
     */
    public void setIdx( long idx )
    {
        this.idx = idx;
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

    /**
     * @return the jobResult
     */
    public BackupJobResult getJobResult()
    {
        return jobResult;
    }

    /**
     * @param jobResult the jobResult to set
     */
    public void setJobResult( BackupJobResult jobResult )
    {
        this.jobResult = jobResult;
    }

    /**
     * @return the volume
     */
    public ClientVolume getVolume()
    {
        return volume;
    }

    /**
     * @param volume the volume to set
     */
    public void setVolume( ClientVolume volume )
    {
        this.volume = volume;
    }

    /**
     * @return the filesChecked
     */
    public long getFilesChecked()
    {
        return filesChecked;
    }

    /**
     * @param filesChecked the filesChecked to set
     */
    public void setFilesChecked( long filesChecked )
    {
        this.filesChecked = filesChecked;
    }

    /**
     * @return the filesTransfered
     */
    public long getFilesTransfered()
    {
        return filesTransfered;
    }

    /**
     * @param filesTransfered the filesTransfered to set
     */
    public void setFilesTransfered( long filesTransfered )
    {
        this.filesTransfered = filesTransfered;
    }

    /**
     * @return the dataChecked
     */
    public long getDataChecked()
    {
        return dataChecked;
    }

    /**
     * @param dataChecked the dataChecked to set
     */
    public void setDataChecked( long dataChecked )
    {
        this.dataChecked = dataChecked;
    }

    /**
     * @return the dataTransfered
     */
    public long getDataTransfered()
    {
        return dataTransfered;
    }

    /**
     * @param dataTransfered the dataTransfered to set
     */
    public void setDataTransfered( long dataTransfered )
    {
        this.dataTransfered = dataTransfered;
    }

}
