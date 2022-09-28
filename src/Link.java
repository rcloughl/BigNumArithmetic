public class Link {
    private Object o;
    private Link l;

    Link(Object obj, Link lnk) {
        this.o = obj;
        this.l=lnk;
    }

    Link(Link lnk){
        this.o=null;
        this.l=lnk;
    }

    Link next(){
        return l;
    }

    Link setNext(Link lnk){
        return this.l=lnk;
    }

    Object getVal(){
        return this.o;
    }

    Object setVal(Object obj){
        return this.o=obj;
    }

}
