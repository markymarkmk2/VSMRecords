/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.net;

import de.dimm.vsm.records.FileSystemElemAttributes;
import de.dimm.vsm.records.FileSystemElemNode;
import java.io.File;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class RemoteFSElem implements Serializable
{
    static final long serialVersionUID = 1L;

    public static final char STREAMINFO_NONE = 0;
    public static final char STREAMINFO_NTFS = 'n';
    public static final char STREAMINFO_APPLEDOUBLE = 'a';
    public static final char STREAMINFO_ETHERSHARE = 'e';

    public static final char ACLINFO_NONE = 0;
    public static final char ACLINFO_OSX = 'a';
    public static final char ACLINFO_POSIX = 'p';
    public static final char ACLINFO_NFSv4 = 'n';
    public static final char ACLINFO_WIN = 'w';
    public static final char ACLINFO_ES = 'e';
    public static final String LAZY_ACLINFO = "LAZYACL";



    
    private String path;
    private String typ;
    private long mtimeMs;
    private long ctimeMs;
    private long atimeMs;
    private long dataSize;
    private long xaDataSize;
    private char separatorChar;
    private char pathSeparatorChar;
    private int streaminfo;
    private int aclinfo;
    private int posixMode;
    private int uid;
    private int gid;
    private String uidName;
    private String gidName;

    long dbIdx;
    long fileHandle;
    private String linkPath;
    private String aclinfoData;
    boolean deleted;

    long dbAttrIdx;
    long stDev;
    
    boolean virtualFS;
    boolean multVersions;

    public RemoteFSElem()
    {
        this(-1, null, FileSystemElemNode.FT_FILE, 0,0,0,0,0);
    }
    public RemoteFSElem( File f)
    {
        this(-1, f.getAbsolutePath(), f.isDirectory() ? FileSystemElemNode.FT_DIR : FileSystemElemNode.FT_FILE, f.lastModified(), f.lastModified(), f.lastModified(), f.length(), 0);
    }

    public RemoteFSElem( String path, String typ, long mtimeMs, long ctimeMs, long atimeMs, long dataSize, long XADataSize)
    {
        this(-1, path, typ, mtimeMs, ctimeMs, atimeMs, dataSize, XADataSize);
    }
    public RemoteFSElem( long idx, String path, String typ, long mtimeMs, long ctimeMs, long atimeMs, long dataSize, long XADataSize )
    {
        this.dbIdx = idx;
        this.path = path;
        this.typ = typ;
        this.mtimeMs = mtimeMs;
        this.ctimeMs = ctimeMs;
        this.atimeMs = atimeMs;
        this.dataSize = dataSize;
        this.xaDataSize = XADataSize;
        separatorChar = File.separatorChar;
        pathSeparatorChar = File.pathSeparatorChar;
        fileHandle = -1;
        deleted = false;
    }

    public static RemoteFSElem createDir( String path )
    {
        long now = System.currentTimeMillis();
        return new RemoteFSElem( path, FileSystemElemNode.FT_DIR, now, now, now, 0, 0 );
    }

    public void setUnixStylePath( boolean b )
    {
        if (b)
        {
            separatorChar = '/';
            pathSeparatorChar = ':';
        }
        else
        {
            separatorChar = '\\';
            pathSeparatorChar = ';';
        }
    }

    public void setDeleted( boolean deleted )
    {
        this.deleted = deleted;
    }

    public boolean isDeleted()
    {
        return deleted;
    }
    

    public void setAclinfoData( String aclinfoData )
    {
        this.aclinfoData = aclinfoData;
    }

    public String getAclinfoData()
    {
        return aclinfoData;
    }

    public boolean isLazyAclInfo()
    {
        if (aclinfoData != null && aclinfoData.equals(LAZY_ACLINFO))
            return true;

        return false;
    }

    
    public final void setPosixData( int posixMode, int uid, int gid, String uidName, String gidName)
    {
        this.posixMode = posixMode;
        this.uid = uid;
        this.gid = gid;
        this.uidName = uidName;
        this.gidName = gidName;
    }
    public final void setAcl( FileSystemElemAttributes attr )
    {
        this.aclinfoData = attr.getAclInfoData();
        this.aclinfo = attr.getAclinfo();
    }


    public RemoteFSElem( FileSystemElemNode node, FileSystemElemAttributes attr )
    {
        this( node.getIdx(), attr.getName(), node.getTyp(),
                attr.getModificationDateMs(),attr.getCreationDateMs(),
                attr.getAccessDateMs(),attr.getFsize(), attr.getStreamSize());

        setPosixData(attr.getPosixMode(), attr.getUid(), attr.getGid(), attr.getUidName(), attr.getGidName());

        setAcl( attr );

        deleted = attr.isDeleted();
        dbAttrIdx = attr.getIdx();
                    
    }

    public long getIdx()
    {
        return dbIdx;
    }

    public long getAttrIdx()
    {
        return dbAttrIdx;
    }


    public void setIdx( long dbIdx )
    {
        this.dbIdx = dbIdx;
    }

    public void setFileHandle( long fileHandle )
    {
        this.fileHandle = fileHandle;
    }

    public long getFileHandle()
    {
        return fileHandle;
    }

    public void setDataSize( long dataSize )
    {
        this.dataSize = dataSize;
    }

    public void setXaDataSize( long xaDataSize )
    {
        this.xaDataSize = xaDataSize;
    }


    


    /**
     * @return the path
     */
    public String getPath()
    {
        return path;
    }

    /**
     * @return the dir
     */
    public boolean isDirectory()
    {
        return FileSystemElemNode.isDirectory(typ);
    }
    public boolean isSymbolicLink()
    {
        return FileSystemElemNode.isSymbolicLink(typ);
    }
    public  boolean isHardLink()
    {
        return FileSystemElemNode.isHardLink(typ);
    }
    public boolean isFile()
    {
        return FileSystemElemNode.isFile(typ);
    }

    /**
     * @return the mtimeMs
     */
    public Date getMtime()
    {
        return new Date(mtimeMs);
    }

    /**
     * @return the ctimeMs
     */
    public Date getCtime()
    {
        return new Date(ctimeMs);
    }

    /**
     * @return the atimeMs
     */
    public Date getAtime()
    {
        return new Date(atimeMs);
    }
    /**
     * @return the mtimeMs
     */
    public long getMtimeMs()
    {
        return mtimeMs;
    }

    /**
     * @return the ctimeMs
     */
    public long getCtimeMs()
    {
        return ctimeMs;
    }

    /**
     * @return the atimeMs
     */
    public long getAtimeMs()
    {
        return atimeMs;
    }

    /**
     * @return the dataSize
     */
    public long getDataSize()
    {
        return dataSize;
    }

    public long getStreamSize()
    {
        return xaDataSize;
    }


    @Override
    public String toString()
    {
        
        return path;
    }

    public char getSeparatorChar()
    {
        return separatorChar;
    }

    public char getPathSeparatorChar()
    {
        return pathSeparatorChar;
    }

    public String getName()
    {
        int idx = path.lastIndexOf(separatorChar);
        if (idx >= 0 && idx < path.length() - 1)
            return path.substring(idx + 1);
        return path;
    }

    public int getPosixMode()
    {
        return posixMode;
    }

    public int getUid()
    {
        return uid;
    }

    public int getGid()
    {
        return gid;
    }

    public long getBase_ts()
    {
        return 0;
    }

    public void setStreaminfo( int streaminfo )
    {
        this.streaminfo = streaminfo;
    }

    public int getStreaminfo()
    {
        return streaminfo;
    }

    public void setAclinfo( int aclinfo )
    {
        this.aclinfo = aclinfo;
    }

    public int getAclinfo()
    {
        return aclinfo;
    }

    public String getLinkPath()
    {
        return linkPath;
    }

    public void setLinkPath( String linkPath )
    {
        this.linkPath = linkPath;
    }

    public String getUidName()
    {
        return uidName;
    }

    public String getGidName()
    {
        return gidName;
    }

    public void makeAbsolut( RemoteFSElem slash )
    {
        if (!path.startsWith("/"))
        {
            path = slash.getPath() + path;
    
        }
    }

    void setPath(String uPath) {
        path = uPath;
    }

    public boolean isVirtualFS()
    {
        return virtualFS;
    }

    public void setVirtualFS( boolean virtualFS )
    {
        this.virtualFS = virtualFS;
    }

    public void setStDev( long stDev )
    {
        this.stDev = stDev;
    }

    public long getStDev()
    {
        return stDev;
    }

    public void setAtimeMs( long l )
    {
        atimeMs = l;
    }
    public void setCtimeMs( long l )
    {
        ctimeMs = l;
    }
    public void setMtimeMs( long l )
    {
        mtimeMs = l;
    }

    public boolean isMultVersions()
    {
        return multVersions;
    }

    public final void setMultVersions( boolean multVersions )
    {
        this.multVersions = multVersions;
    }
    
    
}
