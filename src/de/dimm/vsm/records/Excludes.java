/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.records;

import de.dimm.vsm.net.RemoteFSElem;
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
public class Excludes implements Serializable
{
    public static final String MD_BEGINS_WITH = "begins";
    public static final String MD_ENDS_WITH = "ends";
    public static final String MD_EXACTLY = "exact";
    public static final String MD_CONTAINS = "contains";
    public static final String MD_REGEXP = "regexp";
    public static final String MD_OLDER_THAN = "older";
    public static final String MD_NEWER_THAN = "newer";
    private String argument;
    private boolean isDir;
    private boolean isFullPath;
    private boolean includeMatches;
    private boolean ignorecase;
    private String mode;

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;

    @ManyToOne
    ClientInfo clinfo;

    public Excludes()
    {
    }

    public Excludes( String argument, boolean isDir, boolean isFullPath, boolean includeMatches, boolean ignorecase, String mode )
    {
        this.argument = argument;
        this.isDir = isDir;
        this.isFullPath = isFullPath;
        this.includeMatches = includeMatches;
        this.ignorecase = ignorecase;
        this.mode = mode;
    }

    public void clone( Excludes exc )
    {
        this.argument = exc.argument;
        this.isDir = exc.isDir;
        this.isFullPath = exc.isFullPath;
        this.includeMatches = exc.includeMatches;
        this.ignorecase = exc.ignorecase;
        this.mode = exc.mode;
        this.clinfo = exc.clinfo;
        this.idx = exc.idx;
    }



    public long getIdx()
    {
        return idx;
    }

    public void setIdx( long idx )
    {
        this.idx = idx;
    }

    public ClientInfo getClinfo()
    {
        return clinfo;
    }

    public void setClinfo( ClientInfo clinfo )
    {
        this.clinfo = clinfo;
    }

    

    @Override
    public String toString()
    {
        return mode + " " + argument;
    }


    /**
     * @return the argument
     */
    public String getArgument()
    {
        return argument;
    }

    /**
     * @param argument the argument to set
     */
    public void setArgument( String argument )
    {
        this.argument = argument;
    }

    /**
     * @return the isDir
     */
    public boolean getIsDir()
    {
        return isDir;
    }

    /**
     * @param isDir the isDir to set
     */
    public void setIsDir( boolean isDir )
    {
        this.isDir = isDir;
    }

    /**
     * @return the isFullPath
     */
    public boolean getIsFullPath()
    {
        return isFullPath;
    }

    /**
     * @param isFullPath the isFullPath to set
     */
    public void setIsFullPath( boolean isFullPath )
    {
        this.isFullPath = isFullPath;
    }

    /**
     * @return the includeMatches
     */
    public boolean getIncludeMatches()
    {
        return includeMatches;
    }

    /**
     * @param includeMatches the includeMatches to set
     */
    public void setIncludeMatches( boolean includeMatches )
    {
        this.includeMatches = includeMatches;
    }

    /**
     * @return the mode
     */
    public String getMode()
    {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode( String mode )
    {
        this.mode = mode;
    }

    public boolean isIgnorecase()
    {
        return ignorecase;
    }

    public void setIgnorecase( boolean ignorecase )
    {
        this.ignorecase = ignorecase;
    }

    private static boolean _checkExclude( Excludes excludes, RemoteFSElem remoteFSElem )
    {
        if (excludes.getIsDir() != remoteFSElem.isDirectory())
            return false;

        String file = remoteFSElem.getPath();

        if (!excludes.getIsFullPath())
            file = remoteFSElem.getName();

        String arg = excludes.getArgument();
        if (excludes.isIgnorecase())
        {
            file = file.toLowerCase();
            arg = arg.toLowerCase();
        }

        arg = arg.replace('\\', '/');
        file = file.replace('\\', '/');

        if (excludes.getMode().equals(Excludes.MD_BEGINS_WITH))
        {
            if (file.startsWith(arg))
                return true;
        }
        else if(excludes.getMode().equals(Excludes.MD_CONTAINS))
        {
            if (file.indexOf(arg) >= 0)
                return true;
        }
        else if (excludes.getMode().equals(Excludes.MD_ENDS_WITH))
        {
            if (file.endsWith(arg))
                return true;
        }
        else if (excludes.getMode().equals(Excludes.MD_EXACTLY))
        {
            if (file.equals(arg))
                return true;
        }
        else if (excludes.getMode().equals(Excludes.MD_REGEXP))
        {
            if (file.matches(arg))
                return true;
        }

        return false;


    }
    public static boolean checkExclude( Excludes excludes, RemoteFSElem remoteFSElem )
    {
        boolean ret = _checkExclude(excludes, remoteFSElem);
        return excludes.getIncludeMatches() ? !ret : ret;
    }
    
}
