package leetcode.linkedlist;


/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example:
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        LinkedListNode list1 = new LinkedListNode();
        // Digit will be stored in reverse order
        list1.addToFront(8);
        list1.addToFront(4);
        list1.addToFront(2);

        LinkedListNode list2 = new LinkedListNode();
        // Digit will be stored in a reverse order (842+837= 1679)
        list2.addToFront(8);
        list2.addToFront(3);
        list2.addToFront(7);

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();


        ListNode result = addTwoNumbers.addTwoNumbers(list1.getHead(), list2.getHead());
        System.out.println(result.toString());
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        ListNode p = l1, q = l2;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;

            int sum = carry + x + y;
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;


        }
        if (carry > 0) {
            current.next = new ListNode(carry);
        }
        // remove the added first element and return
        return dummyHead.next;

    }

}
