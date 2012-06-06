/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import de.dimm.vsm.fsengine.ArrayLazyList;
import de.dimm.vsm.fsengine.LazyList;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
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
public class Schedule  implements Serializable
{
    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long idx;

    private String name;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creation;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date scheduleStart;

    private boolean disabled;

    @OneToMany(mappedBy = "sched", orphanRemoval=true, cascade = {CascadeType.REMOVE,CascadeType.DETACH})
    private List<Job> jobs;

    @OneToMany(mappedBy = "sched", orphanRemoval=true, cascade = {CascadeType.REMOVE,CascadeType.DETACH})
    private List<ClientInfo> clientList;

    @ManyToOne
    StoragePool pool;

    private boolean isCycle;
    private long cycleLengthMs;  // ZYKLUSDAUER

    public Schedule()
    {
        jobs = new ArrayLazyList<Job>();
        clientList = new ArrayLazyList<ClientInfo>();
    }


    /**
     * @return the isCycle
     */
    public boolean getIsCycle()
    {
        return isCycle;
    }

    /**
     * @param isCycle the isCycle to set
     */
    public void setIsCycle( boolean isCycle )
    {
        this.isCycle = isCycle;
    }

    /**
     * @return the cycleLengthMs
     */
    public long getCycleLengthMs()
    {
        return cycleLengthMs;
    }

    /**
     * @param cycleLengthMs the cycleLengthMs to set
     */
    public void setCycleLengthMs( long cycleLengthMs )
    {
        this.cycleLengthMs = cycleLengthMs;
    }


    /**
     * @return the idx
     */
    public long getIdx()
    {
        return idx;
    }

    /**
     * @param idx the idx to set
     */
    public void setIdx( long idx )
    {
        this.idx = idx;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName( String name )
    {
        this.name = name;
    }


    /**
     * @return the creation
     */
    public Date getCreation()
    {
        return creation;
    }

    /**
     * @param creation the creation to set
     */
    public void setCreation( Date creation )
    {
        this.creation = creation;
    }


    public void setClientList( LazyList<ClientInfo> clientList )
    {
        this.clientList = clientList;
    }

    public LazyList<ClientInfo> getClientList()
    {
        return (LazyList<ClientInfo>)clientList;
    }

    public LazyList<Job> getJobs()
    {
        return (LazyList<Job>)jobs;
    }

    public void setJobs( LazyList<Job> jobs )
    {
        this.jobs = jobs;
    }

    public void setPool( StoragePool pool )
    {
        this.pool = pool;
    }

    public StoragePool getPool()
    {
        return pool;
    }

    public void setScheduleStart( Date scheduleStart )
    {
        this.scheduleStart = scheduleStart;
    }

    public Date getScheduleStart()
    {
        return scheduleStart;
    }

    @Override
    public String toString()
    {
        return getName();
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


}
