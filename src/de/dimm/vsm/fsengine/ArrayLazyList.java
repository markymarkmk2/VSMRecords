/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.fsengine;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Administrator
 */
public class ArrayLazyList<T> extends LazyList
{
   
    public ArrayLazyList()
    {
        realList = new ArrayList<T>();
    }
    public ArrayLazyList(List<T> l)
    {
        realList = l;
    }

    @Override
    public List<T> realize( GenericEntityManager _handler )
    {
        if (realList == null)
        {
            throw new RuntimeException( "RealList is not initialized" );
        }
        return realList;
    }

    
    @Override
    public boolean add( Object e )
    {
        synchronized(mtx)
        {
            return realList.add(e);
        }
    }

    @Override
    public void unRealize()
    {
    }




}
