import java.util.NoSuchElementException;

public class LList {
    private Link head;
    private Link curr;
    private Link tail;
    private int size;


    LList(int size) {
        this();
    }
    LList(){
        clear();
    }


    public void moveToStart(){
        curr=head.next();
    }
    public void moveToEnd(){
        curr=tail;
    }

    public void clear(){
        curr=tail=new Link(null);
        head=new Link(tail);
        size=0;
    }

    public boolean append(Object obj){
        tail.setNext(new Link(null));
        tail.setVal(obj);
        tail=tail.next();
        size++;
        return true;
    }

    public boolean insert(Object obj){
        curr.setNext(new Link(curr.getVal(), curr.next()));
        curr.setVal(obj);
        if ( tail== curr)
            tail=curr.next();
        size++;
        return true;
    }

    public Object remove() throws NoSuchElementException {
        if (curr == tail) {
            throw new NoSuchElementException("You cannot remove the tail! Curr: " + curr + "Tail: " + size);
        }
        Object obj = curr.getVal();
        curr.setVal(curr.next().getVal());
        if (curr.next()==tail)
            tail=curr;
        size--;
        return obj;
    }

    public void next() {
        if (curr!= tail)
            curr=curr.next();
        }

    public void prev() {
        if (head.next()==curr)
            return;
        Link temp=head;
        while (temp.next() != curr)
            temp=temp.next();
        curr=temp;
    }

    public int length() {
        return size;
    }

    public int currPos() {
        Link temp = head.next();
        int i;
        for (i = 0; curr != temp; i++)
            temp = temp.next();
        return i;
    }

    public boolean moveToPos(int pos) {
        if ((pos < 0) || (pos > size))
            return false;
        curr = head.next();
        for (int i = 0; i < pos; i++)
            curr = curr.next();
        return true;
    }




    }

















}
