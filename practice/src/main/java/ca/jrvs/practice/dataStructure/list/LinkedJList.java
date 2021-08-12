package ca.jrvs.practice.dataStructure.list;

public class LinkedJList implements JList{

  static int size;

  transient MyNode<Object> first;
  transient MyNode<Object> last;

  private static class MyNode<E> {
    E item;
    MyNode<E> next;
    MyNode<E> prev;

    MyNode(MyNode<E> prev, E element, MyNode<E> next) {
      this.item = element;
      this.next = next;
      this.prev = prev;
    }
  }

  @Override
  public boolean add(Object o) {
      final MyNode<Object> l = last;
      final MyNode<Object> newNode = new MyNode<Object>(l, o, null);
      last = newNode;
      if(l==null){
        first=newNode;
      }
      else {
        l.next = newNode;
      }
      size++;
    return true;
  }

  @Override
  public Object[] toArray() {
    Object[] result = new Object[size];
    int i = 0;
    for (MyNode<Object> x = first; x != null; x = x.next) {
      result[i] = x.item;
      i++;
    }
    return result;
  }

  @Override
  public int size() {
    return size;

  }

  @Override
  public boolean isEmpty() {
    return size==0;
  }

  @Override
  public int indexOf(Object o) {
    int index = 0;
    if(o==null){
      for(MyNode<Object> x = first; x!=null;x=x.next){
        if (x.item==null)
          return index;
        index++;
      }
    }else {
      for (MyNode<Object> x = first; x!=null;x=x.next){
        if (o.equals(x.item))
          return index;
        index++;
      }
    }
    return -1;
  }

  @Override
  public boolean contains(Object o) {
    for (MyNode<Object> x = first; x!=null;x=x.next){
      if (o.equals(x.item))
        return true;
    }
    return false;
  }

  @Override
  public Object get(int index) {
    if(index>=size)
      throw new IndexOutOfBoundsException();
    return node(index).item;
  }

  @Override
  public Object remove(int index) {
    MyNode x = node(index);
    final Object element = x.item;
    final MyNode<Object> next = x.next;
    final MyNode<Object> prev = x.prev;

    if (prev == null) {
      first = next;
    } else {
      prev.next = next;
      x.prev = null;
    }

    if (next == null) {
      last = prev;
    } else {
      next.prev = prev;
      x.next = null;
    }

    x.item = null;
    size--;
    return element;
  }

  @Override
  public void clear() {
    for (MyNode<Object> x = first; x != null; ) {
      MyNode<Object> next = x.next;
      x.item = null;
      x.next = null;
      x.prev = null;
      x = next;
    }
    first = last = null;
    size = 0;
  }

  MyNode<Object> node(int index){
    MyNode<Object> x;
    if (index <(size >> 1)){
      x = first;
      for (int i = 0; i< index; i++)
        x=x.next;
    }else {
      x = last;
      for (int i = size-1; i>index; i--)
        x=x.prev;
    }
    return x;
  }
}
