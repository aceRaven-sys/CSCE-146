// CSCE 146 RAVEN JAIME

public class GenLL<T>
{
    private class ListNode
    {
        T data;
        ListNode link;
        public ListNode(T aData, ListNode aLink)
        {
            data = aData;
            link = aLink;
        }
    }
    
        private ListNode head;
        private ListNode current;
        private ListNode previous;

        public GenLL() 
        {
            head = current = previous = null;
        }

        public void add(T aData)
        {
            ListNode newNode = new ListNode(aData, null);
            if (head == null){ // head == null means that the list is EMPTY and sets the head to the newnode and finishes the method
                head = current = newNode;
                return;
            }
            ListNode temp = head;
            while (temp.link != null)
                temp = temp.link; // Moves to the next item
            temp.link = newNode; // sets the newNode to the end of the list.
        }
        public void print(){
            ListNode temp = head;
            while (temp != null){
                System.out.println(temp.data);
                temp = temp.link;
            }
        }

        public void addAfterCurrent(T aData)
        {
            if (current == null)
                return;
            ListNode newNode = new ListNode(aData, current.link);
            current.link = newNode;
        }

        public T getCurrent()
        {
            if(current == null)
                return null;
            return current.data;
        }

        public void setCurrent(T aData)
        {
            if (aData == null || current == null)
                return;
            current.data = aData;

        }

        public void gotoNext()
        {
            if(current == null)
                return;
            previous = current;
            current = current.link;
        }

        public void reset()
        {
            current = head;
            previous = null;
        }

        public boolean hasMore()
        {
            return current != null;
        }

        public void removeCurrent()
        {
            if (current == head)
            {
                head = head.link;
                current = head;
            }
            else
            {
                previous.link = current.link;
                current = current.link;
            }
        }
}