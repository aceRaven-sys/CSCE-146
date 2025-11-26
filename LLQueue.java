/* Raven Jaime CSCE 146 - Watched the youtube lecture 
 * 
 * Everything is the exact same to a LinkedList the only change is that we only need to worry about the first and last element
 * In the queue. We add from the tail, and we remove from the head. We don't need to worry about what's in between.
*/

public class LLQueue <T> implements QueueI<T>
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
    private ListNode tail;

    public LLQueue()
    {
        head = tail = null;
    }

    public void Enqueue(T aData)
    {
        ListNode newNode = new ListNode(aData, null);
        if(head == null)
        {
            head = tail = newNode;
            return;
        }
        tail.link = newNode;
        tail = tail.link;
    }

    public T Dequeue()
    {
        if (head == null)
            return null;
        T ret = head.data;    
        head = head.link;
        return ret;
    }

    public T peek() // The only time we need to know what's in the queue. Checking the next item that will be grabbed in the line
    {
        if(head == null)
            return null;
        return head.data;
    }

    public void print()
    {
        for(ListNode temp = head; temp!=null; temp = temp.link)
        {
            System.out.println(temp.data);
        }
    }
}
