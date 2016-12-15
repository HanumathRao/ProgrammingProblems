package asterdata;

public class List<K> {
    private ListNode<K> head;

    public static class ListNode<K> {
        private K data;
        private ListNode<K> next;

        public ListNode(K datum) {
            this.data = datum;
        }

        public void setNext(ListNode<K> n){
            this.next = n;
        }

        public ListNode<K> getNext() {
            return this.next;
        }
    }

    public List() {

    }

    public void add(K elem) {
        if (head==null)
            head = new ListNode<>(elem);
        else {
            ListNode<K> temp = new ListNode<K>(elem);
            temp.setNext(temp);
            head = temp;
        }
    }

    public ListNode<K> getNext(ListNode<K> node){
        return node.getNext();
    }
}