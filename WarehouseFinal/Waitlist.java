public class Waitlist {
    private List list = new LinkedList();

    public Waitlist(Waitlist copyList) {
        
    }

    public Iterator getWaitlist() {
        return list.Iterator();
    }
}