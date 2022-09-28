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

    public void setNext(Link lnk){
        
    }


    public void append(Object obj){
        tail.setNext(new Link(null));
        tail.setElement(obj);
        tail=tail.next();
        size++;
    }


















}
