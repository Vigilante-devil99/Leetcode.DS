class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevGroupEnd = dummy;

        while (true) {
            ListNode kth = prevGroupEnd;
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }

            if (kth == null) {
                break;
            }

            ListNode nextGroupStart = kth.next;
            ListNode curr = prevGroupEnd.next;
            ListNode prev = nextGroupStart;

            while (curr != nextGroupStart) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            ListNode temp = prevGroupEnd.next;
            prevGroupEnd.next = kth;
            prevGroupEnd = temp;
        }

        return dummy.next;
    }
}
