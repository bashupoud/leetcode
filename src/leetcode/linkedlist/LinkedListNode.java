package leetcode.linkedlist;

public class LinkedListNode {

    private ListNode head;
    private int size;

    public void addToFront(Integer integer) {
        ListNode node = new ListNode(integer);
        node.setNext(head);
        head = node;
        size++;
    }

    public ListNode removeFromFront() {
        if (isEmpty()) {
            return null;
        }

        ListNode removedNode = head;
        head = head.getNext();
        size--;
        removedNode.setNext(null);
        return removedNode;
    }
    public ListNode getHead(){
        return head;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void printList() {
        ListNode current = head;
        System.out.print("HEAD -> ");
        while (current != null) {
            System.out.print(current);
            System.out.print(" -> ");
            current = current.getNext();
        }
        System.out.println("null");
    }

}
