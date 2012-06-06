
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import de.dimm.vsm.fsengine.ArrayLazyList;
import de.dimm.vsm.fsengine.GenericEntityManager;
import de.dimm.vsm.fsengine.LazyList;
import de.dimm.vsm.net.RemoteFSElem;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 *
 * @author mw
 *  */
@Entity
public class HotFolder implements Serializable
{
    static final long serialVersionUID = 1L;

    public static final String HF_DIRS = "dir";
    public static final String HF_FILES = "files";
    public static final String HF_ALL = "all";

    public static final String HOTFOLDERBASE = "Hotfolder";  // USED IN FILESYSTEM AS BASE


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long idx;

    private String name;

    long poolIdx;
//    @ManyToOne
//    private StoragePool pool;

    private boolean disabled;
    private boolean atomicEval;  // TEST FIRST FOLDER AND RESTART AT BEGINNING ON SUCCESS
    private String acceptString;

    long settleTime;
    String ip;
    int port;
    private RemoteFSElem  mountPath;
    String filter;

    String basePath;
    private boolean createDateSubdir;

    private boolean mmArchive;
    String mmMediaType;
    private boolean mmVerify;

    String mmIP;
    String mmMountPath;


    @OneToMany(mappedBy = "hotfolder", orphanRemoval=true, cascade = {CascadeType.REMOVE,CascadeType.DETACH})
    private List<HotFolderError> errlist;

    public HotFolder()
    {
        errlist = new ArrayLazyList<HotFolderError>();
    }
  

    @Override
    public String toString()
    {
        return ip + ":" + port + "/" + mountPath;
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

    public void setPoolIdx( long poolIdx )
    {
        this.poolIdx = poolIdx;
    }

    public long getPoolIdx()
    {
        return poolIdx;
    }


   
//
//    public StoragePool getPool()
//    {
//        return pool;
//    }
//
//    public void setPool( StoragePool storagePool )
//    {
//        this.pool = storagePool;
//    }

    public void setSettleTime( long settleTime )
    {
        this.settleTime = settleTime;
    }

    public long getSettleTime()
    {
        return settleTime;
    }

    public void setDisabled( boolean disabled )
    {
        this.disabled = disabled;
    }

    public boolean isDisabled()
    {
        return disabled;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp( String ip )
    {
        this.ip = ip;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort( int port )
    {
        this.port = port;
    }

    public void setMountPath( RemoteFSElem mountPath )
    {
        this.mountPath = mountPath;
    }

    public RemoteFSElem getMountPath()
    {
        return mountPath;
    }

    public String getFilter()
    {
        return filter;
    }

    public void setFilter( String filter )
    {
        this.filter = filter;
    }

    public String getAcceptString()
    {
        return acceptString;
    }

    public void setAcceptString( String acceptString )
    {
        this.acceptString = acceptString;
    }

    public boolean onlyDirs()
    {
        if (acceptString == null)
            return false;

        return acceptString.equals(HF_DIRS);
    }
    public boolean onlyFiles()
    {
        if (acceptString == null)
            return false;
        
        return acceptString.equals(HF_FILES);
    }

    public void setAtomicEval( boolean atomicEval )
    {
        this.atomicEval = atomicEval;
    }

    public boolean isAtomicEval()
    {
        return atomicEval;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public boolean isCreateDateSubdir()
    {
        return createDateSubdir;
    }

    public void setCreateDateSubdir( boolean createDateSubdir )
    {
        this.createDateSubdir = createDateSubdir;
    }

    public void setBasePath( String basePath )
    {
        this.basePath = basePath;
    }

    public String getBasePath()
    {
        return basePath;
    }

    public void setMmVerify( boolean mmVerify )
    {
        this.mmVerify = mmVerify;
    }

    public void setMmMediaType( String mmMediaType )
    {
        this.mmMediaType = mmMediaType;
    }

    public void setMmArchive( boolean mmArchive )
    {
        this.mmArchive = mmArchive;
    }

    public boolean isMmVerify()
    {
        return mmVerify;
    }

    public boolean isMmArchive()
    {
        return mmArchive;
    }

    public String getMmMediaType()
    {
        return mmMediaType;
    }

    public LazyList<HotFolderError> getErrlist()
    {

        return (LazyList<HotFolderError>)errlist;
    }
    public List<HotFolderError> getErrlist(GenericEntityManager em)
    {
        return ((LazyList<HotFolderError>)errlist).getList(em);
    }

    public void setErrlist( LazyList<HotFolderError> errlist )
    {
        this.errlist = errlist;
    }

    public String getMmIP()
    {
        return mmIP;
    }

    public String getMmMountPath()
    {
        return mmMountPath;
    }

    public void setMmMountPath( String mmMountPath )
    {
        this.mmMountPath = mmMountPath;
    }

    public void setMmIP( String mmIP )
    {
        this.mmIP = mmIP;
    }

    

    
}

