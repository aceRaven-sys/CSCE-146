/* Raven Jaime CSCE 146 */
public interface QueueI <T>
{
    public void Enqueue(T aData);
    public T Dequeue();
    public T peek();
    public void print();
}