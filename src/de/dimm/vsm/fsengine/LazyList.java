/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.fsengine;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.eclipse.persistence.indirection.IndirectContainer;
import org.eclipse.persistence.indirection.ValueHolderInterface;

/**
 *
 * @author Administrator
 */
public abstract class LazyList<T> implements List<T>, IndirectContainer
{    
    protected List<T> realList;
    protected final String mtx = "";

    public LazyList()
    {
        realList = null;
    }

    @Override
    public ValueHolderInterface getValueHolder()
    {
        throw new UnsupportedOperationException("Invalid Function Call for LazyList");
    }

    @Override
    public void setValueHolder( ValueHolderInterface valueHolder )
    {
        throw new UnsupportedOperationException("Invalid Function Call for LazyList");
    }

    @Override
    public boolean isInstantiated()
    {
        throw new UnsupportedOperationException("Invalid Function Call for LazyList");
    }

    // ENTRY FROM DB CREATION
    public LazyList( Class<T> cl, String fieldname, long ownerIdx)
    {
        realList = null;
    }

  
    public abstract void realize(GenericEntityManager _handler);
   

    public boolean isRealized()
    {
        synchronized(mtx)
        {
            return realList != null;
        }
    }

    public void unRealize()
    {
        synchronized(mtx)
        {
            if (realList != null)
            {
                realList.clear();
                realList = null;
            }
        }
    }

    @Override
    public String toString()
    {
        synchronized(mtx)
        {
            if (isRealized())
                return realList.toString();
        }

        return "unrealized";
    }


//    @Override
    public boolean isEmpty(GenericEntityManager handler)
    {
        synchronized(mtx)
        {
            realize(handler);
            return realList.isEmpty();
        }
    }

 //   @Override
    public int size(GenericEntityManager handler)
    {
        synchronized(mtx)
        {
            realize(handler);
            return realList.size();
        }
    }



 //   @Override
    public boolean contains( GenericEntityManager handler, Object o )
    {
        long idx = handler.getIdx(o);
        synchronized(mtx)
        {
            realize(handler);
            for (int i = 0; i < realList.size(); i++)
            {
                Object object = realList.get(i);
                long _idx = handler.getIdx(object);
                if (_idx == idx)
                {
                    return true;
                }
            }
            return false;
        }
    }

//    @Override
    public Iterator iterator(GenericEntityManager handler)
    {
        synchronized(mtx)
        {
            realize( handler );
            return realList.iterator();
        }
    }

//    @Override
    public Object[] toArray(GenericEntityManager handler)
    {
        synchronized(mtx)
        {
            realize( handler );
            return realList.toArray();
        }
    }

 //   @Override
    public Object[] toArray( GenericEntityManager handler, Object[] a )
    {
        synchronized(mtx)
        {
            realize( handler );
            return realList.toArray(a);
        }
    }

//    @Override
    public boolean add( GenericEntityManager handler, T e )
    {
        synchronized(mtx)
        {
            realize( handler );
            return realList.add(e);
        }
    }

//    @Override
    public boolean remove( GenericEntityManager handler, T o )
    {
        long idx = handler.getIdx(o);
        synchronized(mtx)
        {
            realize( handler );
            for (int i = 0; i < realList.size(); i++)
            {
                T object = realList.get(i);
                long _idx = handler.getIdx(object);
                if (_idx == idx)
                {
                    realList.remove(object);
                    return true;
                }
            }
            return false;
        }
    }

//    @Override
    public boolean containsAll( GenericEntityManager handler, Collection c )
    {
        synchronized(mtx)
        {
            realize( handler );
            return realList.containsAll(c);
        }
    }

//    @Override
    public boolean addAll( GenericEntityManager handler, Collection c )
    {
        synchronized(mtx)
        {
            realize( handler );
            return realList.addAll(c);
        }
    }

//    @Override
    public boolean addAll( GenericEntityManager handler, int index, Collection c )
    {
        synchronized(mtx)
        {
            realize( handler );
            return realList.addAll(index, c);
        }
    }

//    @Override
    public boolean removeAll( GenericEntityManager handler, Collection c )
    {
        synchronized(mtx)
        {
            realize( handler );
            return realList.removeAll(c);
        }
    }

 //   @Override
    public boolean retainAll( GenericEntityManager handler, Collection c )
    {
        synchronized(mtx)
        {
            realize( handler );
            return realList.retainAll(c);
        }
    }

//    @Override
    public void clear(GenericEntityManager handler)
    {
        synchronized(mtx)
        {
            if (!isRealized())
                return;

            realize( handler );
            realList.clear();
        }
    }

 //   @Override
    public T get( GenericEntityManager handler, int index )
    {
        synchronized(mtx)
        {
            realize( handler );
            return realList.get(index);
        }
    }

//    @Override
    public Object set( GenericEntityManager handler, int index, T element )
    {
        synchronized(mtx)
        {
            realize( handler );
            return realList.set(index, element);
        }
    }

//    @Override
    public void add( GenericEntityManager handler, int index, T element )
    {
        synchronized(mtx)
        {
            realize( handler );
            realList.add(index, element);
        }
    }

//    @Override
    public Object remove( GenericEntityManager handler, int index )
    {
        synchronized(mtx)
        {
            realize( handler );
            return realList.remove(index);
        }
    }

 //   @Override
    public int indexOf( GenericEntityManager handler, Object o )
    {
        long idx = handler.getIdx(o);
        synchronized(mtx)
        {
            realize( handler );
            for (int i = 0; i < realList.size(); i++)
            {
                Object object = realList.get(i);
                long _idx = handler.getIdx(object);
                if (_idx == idx)
                {

                    return i;
                }
            }
            return -1;
        }
    }

//    @Override
    public int lastIndexOf( GenericEntityManager handler, Object o )
    {
        long idx = handler.getIdx(o);
        synchronized(mtx)
        {
            realize( handler );
            for (int i = realList.size() - 1; i >= 0; i--)
            {
                Object object = realList.get(i);
                long _idx = handler.getIdx(object);
                if (_idx == idx)
                {

                    return i;
                }
            }
            return -1;
        }
    }

//    @Override
    public ListIterator listIterator(GenericEntityManager handler)
    {
        synchronized(mtx)
        {
            realize( handler );
            return realList.listIterator();
        }
    }

//    @Override
    public ListIterator listIterator( GenericEntityManager handler, int index )
    {
        synchronized(mtx)
        {
            realize( handler );
            return realList.listIterator(index);
        }
    }

//    @Override
    public List subList( GenericEntityManager handler, int fromIndex, int toIndex )
    {
        synchronized(mtx)
        {
            realize( handler );
            return realList.subList(fromIndex, toIndex);
        }
    }

   
    public List<T> getList( GenericEntityManager handler )
    {
        synchronized(mtx)
        {
            realize( handler );
            return realList;
        }
    }
    public List<T> getList()
    {
        synchronized(mtx)
        {
                    if (!isRealized())
            throw new RuntimeException( "Unrealized list in getList " + this.toString() );
        
            return realList;
        }
    }

    public boolean addIfRealized( T ajfl )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.add(ajfl);
            }
            return false;
        }
    }

    public boolean removeIfRealized( T ajfl )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.remove(ajfl);
            }
            // WE ASSUME OBJECT WOULD BE IN LIST IT WERE REALIZED
            return true;
        }
    }

    @Override
    public boolean add( T e )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.add(e);
            }

            throw new UnsupportedOperationException("Invalid Function Call for LazyList");
        }
    }

    @Override
    public void clear()
    {
        synchronized(mtx)
        {
            unRealize();
        }
    }

    @Override
    public boolean contains( Object o )
    {
        synchronized(mtx)
        {
                if (isRealized())
            {
                return realList.contains((T)o);
            }
            throw new UnsupportedOperationException("Invalid Function Call 1 for LazyList");
        }
    }

    @Override
    public boolean containsAll( Collection<?> c )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.containsAll(c);
            }
            throw new UnsupportedOperationException("Invalid Function Call 2 for LazyList");
        }
    }

    @Override
    public boolean isEmpty()
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.isEmpty();
            }
            throw new UnsupportedOperationException("Invalid Function Call 3 for LazyList");
        }
    }

    @Override
    public Iterator<T> iterator()
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.iterator();
            }
            throw new UnsupportedOperationException("Invalid Function Call 4 for LazyList");
        }
    }

    @Override
    public boolean remove( Object o )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.remove((T)o);
            }
            throw new UnsupportedOperationException("Invalid Function Call 5 for LazyList");
        }
    }

    @Override
    public boolean removeAll( Collection<?> c )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.removeAll(c);
            }
            throw new UnsupportedOperationException("Invalid Function Call 6 for LazyList");
        }
    }

    @Override
    public boolean retainAll( Collection<?> c )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.retainAll(c);
            }
            throw new UnsupportedOperationException("Invalid Function Call 7 for LazyList");
        }
    }

    @Override
    public int size()
    {
        synchronized(mtx)
        {
            if (isRealized())
                return realList.size();
        }
        return 0;
    }

    @Override
    public Object[] toArray()
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.toArray();
            }
            throw new UnsupportedOperationException("Invalid Function Call 8 for LazyList");
        }
    }

    @Override
    public <T> T[] toArray( T[] a )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.toArray(a);
            }
            throw new UnsupportedOperationException("Invalid Function Call 9 for LazyList");
        }
    }

    @Override
    public boolean addAll( Collection<? extends T> c )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.addAll(c);
            }
            throw new UnsupportedOperationException("Invalid Function Call 10 for LazyList");
        }
    }

    @Override
    public void add( int index, T element )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                realList.add(index, element);
            }
            throw new UnsupportedOperationException("Invalid Function Call 11 for LazyList");
        }
    }

    @Override
    public boolean addAll( int index, Collection<? extends T> c )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.addAll(index, c);
            }
            throw new UnsupportedOperationException("Invalid Function Call 12 for LazyList");
        }
    }

    @Override
    public T get( int index )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.get(index);
            }
            throw new UnsupportedOperationException("Invalid Function Call 13 for LazyList");
        }
    }

    @Override
    public int indexOf( Object o )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.indexOf(o);
            }
            throw new UnsupportedOperationException("Invalid Function Call 14 for LazyList");
        }
    }

    @Override
    public int lastIndexOf( Object o )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.lastIndexOf(o);
            }
            throw new UnsupportedOperationException("Invalid Function Call 15 for LazyList");
        }
    }

    @Override
    public ListIterator<T> listIterator()
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.listIterator();
            }
            throw new UnsupportedOperationException("Invalid Function Call 16 for LazyList");
        }
    }

    @Override
    public ListIterator<T> listIterator( int index )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.listIterator(index);
            }
            throw new UnsupportedOperationException("Invalid Function Call 17 for LazyList");
        }
    }

    @Override
    public T remove( int index )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.remove(index);
            }
            throw new UnsupportedOperationException("Invalid Function Call 18 for LazyList");
        }
    }

    @Override
    public T set( int index, T element )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.set(index, element);
            }
            throw new UnsupportedOperationException("Invalid Function Call 19 for LazyList");
        }
    }

    @Override
    public List<T> subList( int fromIndex, int toIndex )
    {
        synchronized(mtx)
        {
            if (isRealized())
            {
                return realList.subList(fromIndex, toIndex);
            }
            throw new UnsupportedOperationException("Invalid Function Call 20 for LazyList");
        }
    }
}