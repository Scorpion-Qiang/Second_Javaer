import java.util.*;

class LinkedListPractice {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * @Author 强仔不强
     * @Description 2058. 找出临界点之间的最小和最大距离
     * @Date 20:20 2026/4/3
     * @Param [head]
     * @return int
     **/
    public int getDecimalValue(ListNode head) {
        int ans = 0;
        while (head != null){
            ans *= 2;
            ans += head.val;
            head = head.next;
        }

        return ans;
    }

    /**
     * @Author 强仔不强
     * @Description 2058. 找出临界点之间的最小和最大距离
     * @Date 20:24 2026/4/3
     * @Param [head]
     * @return int[]
     **/
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode pre = head;
        ListNode cur = head.next;
        int i = 1;

        int firstPoint = -1;
        int prePoint = -1;
        int minDistance = Integer.MAX_VALUE;

        while (cur.next != null){
            if( (cur.val > pre.val && cur.val > cur.next.val) || (cur.val < pre.val && cur.val < cur.next.val)){
                if(firstPoint < 0){
                    firstPoint = i;
                }

                if(prePoint > 0){
                    minDistance = Math.min(minDistance, i - prePoint);
                }

                prePoint = i;
            }

            pre = cur;
            cur = cur.next;
            i++;
        }


        if(firstPoint == prePoint){
            return new int[]{-1, -1};
        }

        return new int[]{minDistance, prePoint - firstPoint};
    }
    
    
    /**
     * @Author 强仔不强
     * @Description 2181. 合并零之间的节点
     * @Date 20:48 2026/4/3
     * @Param [head]
     * @return Main.ListNode
     **/
    public ListNode mergeNodes(ListNode head) {
        ListNode ans = new ListNode();
        ListNode ansCur = ans;

        ListNode cur = head.next;
        int sum = 0;
        while (cur != null){
            if(cur.val == 0){
                ansCur.next = new ListNode(sum);
                ansCur = ansCur.next;
                sum = 0;
            } else {
                sum += cur.val;
            }

            cur = cur.next;
        }

        return ans.next;
    }

    // 原地修改 O(1)
    public ListNode mergeNodesIII(ListNode head) {
        ListNode pre = head;
        ListNode cur = head.next;
        int sum = 0;

        while (cur != null){
            if(cur.val == 0){
                cur.val = sum;
                sum = 0;

                pre.next = cur;
                pre = cur;
            } else {
                sum += cur.val;
            }

            cur = cur.next;
        }

        return head.next;
    }

    /**
     * @Author 强仔不强
     * @Description 725. 分隔链表
     * @Date 11:38 2026/4/5
     * @Param [head, k]
     * @return Main.ListNode[]
     **/
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] ans = new ListNode[k];

        ListNode cur = head;
        int cnt = 0;
        while (cur != null){
            cnt++;
            cur = cur.next;
        }

        cur = head;
        if(cnt <= k){
            for (int i = 0; i < cnt; i++) {
                ans[i] = cur;

                ListNode pre = cur;
                cur = cur.next;
                pre.next = null;
            }
            return ans;
        }

        int num1 = cnt / k;
        int num2 = cnt % k;


        for (int i = 0; i < k; i++) {
            ans[i] = cur;

            int cnt1 = 1;
            while (cnt1 < num1){
                cur = cur.next;
                cnt1++;
            }

            if(i < num2){
                cur = cur.next;
            }

            ListNode pre = cur;
            cur = cur.next;
            pre.next = null;

        }

        return ans;
    }
    
    /**
     * @Author 强仔不强
     * @Description 817. 链表组件
     * @Date 14:13 2026/4/5
     * @Param [head, nums]
     * @return int
     **/
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int val : nums){
            set.add(val);
        }

        int ans = 0;
        int cnt = 0;
        ListNode cur = head;

        while (cur != null){
            if(set.contains(cur.val)){
                if(cnt == 0){
                    ans++;
                }

                cnt++;
            } else {
                cnt = 0;
            }

            cur = cur.next;
        }

        return ans;
    }
    
    /**
     * @Author 强仔不强
     * @Description 203. 移除链表元素
     * @Date 14:30 2026/4/5
     * @Param [head, val]
     * @return Main.ListNode
     **/
    public ListNode removeElements(ListNode head, int val) {
        if(head == null){
            return head;
        }

        ListNode h = new ListNode(-1);
        h.next = head;


        ListNode pre = h;
        ListNode cur = h.next;
        while (cur != null){
            if(cur.val == val){
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return h.next;
    }
    
    /**
     * @Author 强仔不强
     * @Description 3217. 从链表中移除在数组中存在的节点
     * @Date 14:39 2026/4/5
     * @Param [nums, head]
     * @return Main.ListNode
     **/
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> set = new HashSet<>();
        for (int val : nums){
            set.add(val);
        }

        ListNode h = new ListNode(0);
        h.next = head;

        ListNode pre = h;
        ListNode cur = h.next;
        while (cur != null){
            if(set.contains(cur.val)){
                pre.next = cur.next;
            } else {
                pre = cur;
            }

            cur = cur.next;
        }

        return h.next;
    }
    
    /**
     * @Author 强仔不强
     * @Description 83. 删除排序链表中的重复元素
     * @Date 14:51 2026/4/5
     * @Param [head]
     * @return Main.ListNode
     **/
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null){
            return head;
        }

        ListNode pre = head;
        ListNode cur = head.next;

        while (cur != null){
            if(cur.val == pre.val){
                pre.next = cur.next;
            } else {
                pre = cur;
            }

            cur = cur.next;
        }

        return head;
    }
    
    /**
     * @Author 强仔不强
     * @Description 82. 删除排序链表中的重复元素 II
     * @Date 14:56 2026/4/5
     * @Param [head]
     * @return Main.ListNode
     **/
    public ListNode deleteDuplicatesII(ListNode head) {
        if(head == null){
            return head;
        }

        ListNode h = new ListNode(101);
        h.next = head;

        ListNode pre = h;
        ListNode cur = h.next;

        while (cur != null){
            ListNode following = cur.next;

            while (following != null && following.val == cur.val){
                following = following.next;
            }

            if(following == cur.next){
                pre = cur;
            } else {
                pre.next = following;
            }
            cur = following;
        }

        return h.next;
    }
    
    /**
     * @Author 强仔不强
     * @Description 237. 删除链表中的节点
     * @Date 15:52 2026/4/5
     * @Param [node]
     * @return void
     **/
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }


    /**
     * @Author 强仔不强
     * @Description 1669. 合并两个链表
     * @Date 16:03 2026/4/5
     * @Param [list1, a, b, list2]
     * @return Main.ListNode
     **/
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode tail2 = list2;
        while (tail2.next != null){
            tail2 = tail2.next;
        }

        ListNode cur = list1;
        int i = 0;
        for (; i < a - 1; i++) {
            cur = cur.next;
        }

        ListNode start = cur;
        for (int j = i; i <= b; i++) {
            cur = cur.next;
        }

        start.next = list2;
        tail2.next = cur;

        return list1;
    }

    /**
     * @Author 强仔不强
     * @Description 2487. 从链表中移除节点
     * @Date 17:00 2026/4/5
     * @Param [head]
     * @return Main.ListNode
     **/
    public ListNode removeNodes(ListNode head) {
        if(head.next == null){
            return head;
        }

        ListNode node = removeNodes(head.next);
        if(node.val > head.val){
            return node;
        }

        head.next = node;
        return head;
    }

    /**
     * @Author 强仔不强
     * @Description 2807. 在链表中插入最大公约数
     * @Date 17:07 2026/4/5
     * @Param [head]
     * @return Main.ListNode
     **/
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        if(head.next == null){
            return head;
        }

        ListNode pre = head;
        ListNode cur = head.next;

        while (cur != null) {
            int val = gcd(pre.val, cur.val);
            ListNode node = new ListNode(val);

            pre.next = node;
            node.next = cur;

            pre = cur;
            cur = cur.next;
        }

        return head;
    }

    public int gcd(int a, int b){
        if(b == 0){
            return a;
        }

        return gcd(b, a % b);
    }

    /**
     * @Author 强仔不强
     * @Description 147. 对链表进行插入排序
     * @Date 20:51 2026/4/5
     * @Param [head]
     * @return Main.ListNode
     **/
    public ListNode insertionSortList(ListNode head) {
        ListNode h = new ListNode(-5001);
        h.next = head;

        ListNode cur = head.next;
        ListNode tail = head;

        while (cur != null){
            if(cur.val >= tail.val){
                tail = cur;
                cur = cur.next;
                continue;
            }


            ListNode node = h;
            while (node.next.val < cur.val){
                node = node.next;
            }

            tail.next = cur.next;

            ListNode node1 = node.next;
            node.next = cur;
            cur.next = node1;

            cur = tail.next;
        }

        return h.next;
    }

    /**
     * @Author 强仔不强
     * @Description LCR 029. 循环有序列表的插入
     * @Date 22:14 2026/4/5
     * @Param [head, insertVal]
     * @return Main.ListNode
     **/
    public ListNode insert(ListNode head, int insertVal) {
        ListNode node = new ListNode(insertVal);
        if(head == null){
            node.next = node;
            return node;
        }

        if(head.next == null){
            head.next = node;
            node.next = head;
            return head;
        }

        ListNode cur = head;
        while (true){

            if(cur.val <= insertVal && cur.next.val >= insertVal){
                break;
            }

            if(cur.val > cur.next.val && (cur.val <= insertVal || cur.next.val >= insertVal)){
                break;
            }

            cur = cur.next;

            if(cur == head){
                break;
            }
        }


        ListNode curNext = cur.next;
        cur.next = node;
        node.next = curNext;

        return head;
    }
    
    /**
     * @Author 强仔不强
     * @Description 206. 反转链表
     * @Date 14:16 2026/4/6
     * @Param [head]
     * @return Main.ListNode
     **/
    // 迭代法 （头插）
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null){
            ListNode nxt = cur.next;
            cur.next = pre;

            pre = cur;
            cur = nxt;
        }

        return pre;
    }

    // 递归（尾插）
    ListNode ans = null;
    public ListNode reverseListII(ListNode head) {
        if(head == null){
            return ans;
        }

        reverse(head);
        return ans;
    }

    public ListNode reverse(ListNode head){
        if(head.next == null){
            ans = head;
            return head;
        }

        ListNode node = reverse(head.next);
        node.next = head;
        head.next = null;

        return head;
    }

    // 递归
    public ListNode reverseListIII(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode revHead = reverseListIII(head.next);
        ListNode tail = head.next;
        tail.next = head;
        head.next = null;

        return revHead;
    }

    /**
     * @Author 强仔不强
     * @Description 92. 反转链表 II
     * @Date 15:49 2026/4/6
     * @Param [head, left, right]
     * @return Main.ListNode
     **/
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == right){
            return head;
        }

        ListNode h = new ListNode();
        h.next = head;

        ListNode p0 = h;
        ListNode cur = head;

        int i = 1;
        while (i < left){
            p0 = cur;
            cur = cur.next;
            i++;
        }

        ListNode pre = null;
        while (i <= right){
            ListNode nxt = cur.next;
            cur.next = pre;

            pre = cur;
            cur = nxt;
            i++;
        }

        ListNode tail = p0.next;
        p0.next = pre;
        tail.next = cur;

        return h.next;
    }
    
    /**
     * @Author 强仔不强
     * @Description 24. 两两交换链表中的节点
     * @Date 16:12 2026/4/6
     * @Param [head]
     * @return Main.ListNode
     **/
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode h = new ListNode();
        h.next = head;

        ListNode p0 = h;
        ListNode pre = head;
        ListNode cur = head.next;

        while (cur != null) {
            ListNode nxt = cur.next;

            // 换
            p0.next = cur;
            cur.next = pre;
            pre.next = nxt;


            if(nxt == null){
                break;
            }

            // 走
            p0 = pre;
            pre = nxt;
            cur = nxt.next;
        }

        return h.next;
    }


    public ListNode swapPairsII(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode p = dummy;
        ListNode cur = head;

        while (cur != null && cur.next != null){
            ListNode nxt1 = cur.next;
            ListNode nxt2 = nxt1.next;

            p.next = nxt1;
            nxt1.next = cur;
            cur.next = nxt2;

            p = cur;
            cur = nxt2;
        }

        return dummy.next;
    }
    

    public ListNode swapPairsIII(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode nxt1 = head.next;
        ListNode nxt2 = swapPairsIII(nxt1.next);
        nxt1.next = head;
        head.next = nxt2;

        return nxt1;
    }
    
    /**
     * @Author 强仔不强
     * @Description
     * @Date 20:58 2026/4/6
     * @Param [head, k]
     * @return Main.ListNode
     **/
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k == 1){
            return head;
        }

        int cnt = 0;
        ListNode cur = head;
        while (cur != null){
            cnt++;
            cur = cur.next;
        }
        int lastIndex = cnt - cnt % k;

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode p0 = dummy;
        ListNode pre = null;
        cur = head;
        int i = 1;

        while (i <= lastIndex){
            ListNode nxt = cur.next;
            cur.next = pre;

            if(i % k == 0){
                ListNode tail = p0.next;
                p0.next = cur;
                tail.next = nxt;

                p0 = tail;
                // 可有可无
                pre = null;
            } else {
                pre = cur;
            }

            cur = nxt;
            i++;
        }


        p0.next = cur;

        return dummy.next;
    }


    public ListNode reverseKGroupII(ListNode head, int k) {
        int n = 0;
        for (ListNode node = head; node != null; node = node.next){
            n++;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode p0 = dummy;
        ListNode pre = null;
        ListNode cur = head;

        for (; n >= k; n -= k){
            for (int i = 0; i < k; i++) {
                ListNode nxt = cur.next;
                cur.next = pre;

                pre = cur;
                cur = nxt;
            }

            ListNode tail = p0.next;
            p0.next = pre;
            tail.next = cur;

            p0 = tail;
            // 可有可无
            pre = null;
        }

        return dummy.next;
    }


    // 递归.
    public ListNode reverseKGroupIII(ListNode head, int k) {
        int n = 0;
        for (ListNode node = head; node != null; node = node.next) {
            n++;
        }

        return reverse(head, n, k);
    }

    public ListNode reverse(ListNode head, int n, int k) {
        if (n < k) {
            return head;
        }

        ListNode pre = null;
        ListNode cur = head;
        for (int i = 0; i < k; i++) {
            ListNode nxt = cur.next;
            cur.next = pre;

            pre = cur;
            cur = nxt;
        }

        head.next = reverse(cur, n - k, k);
        return pre;
    }



    /**
     * @Author 强仔不强
     * @Description 2074. 反转偶数长度组的节点
     * @Date 12:01 2026/4/7
     * @Param [head]
     * @return Main.ListNode
     **/
    public ListNode reverseEvenLengthGroups(ListNode head) {
        int n = 0;
        for (ListNode node = head; node != null; node = node.next){
            n++;
        }


        int k = 2;
        n--;

        ListNode p0 = head;
        ListNode pre = null;
        ListNode cur = head.next;

        while (cur != null){
            k = Math.min(k, n);

            if(k % 2 == 0){
                for (int i = 0; i < k && cur != null; i++) {
                    ListNode nxt = cur.next;
                    cur.next = pre;

                    pre = cur;
                    cur = nxt;
                }

                ListNode nxt = p0.next;
                p0.next = pre;
                nxt.next = cur;
                p0 = nxt;

            } else {
                for (int i = 0; i < k && cur != null; i++) {
                    p0 = cur;
                    cur = cur.next;
                }
            }

            n -= k;
            k++;
        }

        return head;
    }

    public ListNode reverseEvenLengthGroupsII(ListNode head) {
        int n = 0;
        for (ListNode node = head; node != null; node = node.next){
            n++;
        }

        return change(head, 1, n);
    }

    public ListNode change(ListNode head, int k, int n){
        if(head == null){
            return head;
        }

        ListNode cur = head;
        ListNode pre = null;

        if(k % 2 != 0){
            for (int i = 0; i < k; i++) {
                pre = cur;
                cur = cur.next;
            }
        } else {
            for (int i = 0; i < k; i++) {
                ListNode nxt = cur.next;
                cur.next = pre;

                pre = cur;
                cur = nxt;
            }
        }

        ListNode node = change(cur, Math.min(k + 1, n - k), n - k);

        if(k % 2 != 0){
            pre.next = node;
            return head;
        }

        head.next = node;
        return pre;
    }


    /**
     * @Author 强仔不强
     * @Description 
     * @Date 17:42 2026/4/7
     * @Param [head, n]
     * @return Main.ListNode
     **/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode cur = head;

        for (int i = 1; i <= n; i++) {
            cur = cur.next;
        }

        ListNode p0 = dummy;
        ListNode pre = head;
        while (cur != null){
            cur = cur.next;

            p0 = pre;
            pre = pre.next;
        }

        p0.next = pre.next;

        return dummy.next;
    }

}

public class Main {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @Author 强仔不强
     * @Description 144. 二叉树的前序遍历
     * @Date 13:12 2026/4/9
     * @Param [root]
     **/
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        prePrint(ans, root);

        return ans;
    }

    public void prePrint(List<Integer> ans, TreeNode root) {
        if (root == null) {
            return;
        }

        ans.add(root.val);
        prePrint(ans, root.left);
        prePrint(ans, root.right);
    }

    // 迭代
    public List<Integer> preorderTraversalII(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                ans.add(root.val);
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop().right;
            }
        }

        return ans;
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @Author 强仔不强
     * @Description 94. 二叉树的中序遍历
     * @Date 13:18 2026/4/9
     * @Param [root]
     **/
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inPrint(ans, root);

        return ans;
    }

    public void inPrint(List<Integer> ans, TreeNode root) {
        if (root == null) {
            return;
        }

        inPrint(ans, root.left);
        ans.add(root.val);
        inPrint(ans, root.right);
    }

    // 迭代
    public List<Integer> inorderTraversalII(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                ans.add(root.val);
                root = root.right;
            }
        }

        return ans;
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @Author 强仔不强
     * @Description 145. 二叉树的后序遍历
     * @Date 14:35 2026/4/9
     * @Param [root]
     **/
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        postPrint(root, ans);

        return ans;
    }

    public void postPrint(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }

        postPrint(root.left, ans);
        postPrint(root.right, ans);
        ans.add(root.val);
    }

    public List<Integer> postorderTraversalIII(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
                continue;
            }

            root = stack.peek();
            if (root.right == null || root.right == prev) {
                stack.pop();
                ans.add(root.val);

                prev = root;
                root = null;
            } else {
                root = root.right;
            }

        }

        return ans;
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 872. 叶子相似的树
     * @Date 15:51 2026/4/9
     * @Param [root1, root2]
     **/
    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        leaves(root1, list1);
        leaves(root2, list2);

//        return list1.equals(list2);
        return isSame(list1, list2);
    }

    public static void leaves(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            list.add(root.val);
            return;
        }

        leaves(root.left, list);
        leaves(root.right, list);
    }

    public static boolean isSame(List<Integer> list1, List<Integer> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            // list1，get(i) 得到的是 Integer 类型, 不能用 == 比较
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description LCP 44. 开幕式焰火
     * @Date 20:52 2026/4/9
     * @Param [root]
     **/
    public int numColor(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        Dfs(root, set);

        return set.size();
    }

    public void Dfs(TreeNode root, Set<Integer> set) {
        if (root == null) {
            return;
        }

        set.add(root.val);
        Dfs(root.left, set);
        Dfs(root.right, set);
    }


    /**
     * @return int
     * @Author 强仔不强
     * @Description 404. 左叶子之和
     * @Date 20:59 2026/4/9
     * @Param [root]
     **/
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int ans = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            ans += root.left.val;
        }

        ans += sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        return ans;
    }

    public int sumOfLeft(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = sumOfLeft(root.left) + sumOfLeft(root.right);
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }

        return sum;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 671. 二叉树中第二小的节点
     * @Date 22:07 2026/4/9
     * @Param [root]
     **/
    public int findSecondMinimumValue(TreeNode root) {
        if (root.left == null && root.right == null) {
            return -1;
        }

        if (root.left.val != root.right.val) {
            return Math.max(root.left.val, root.right.val);
        }

        int val1 = findSecondMinimumValue(root.left);
        int val2 = findSecondMinimumValue(root.right);

        if (val1 > 0 && val2 > 0) {
            return Math.min(val1, val2);
        }

        if (val1 > 0) {
            return val1;
        }

        return val2;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 104. 二叉树的最大深度
     * @Date 14:30 2026/4/10
     * @Param [root]
     **/
    // 自底向上
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftMaxDep = maxDepth(root.left);
        int rightMaxDep = maxDepth(root.right);

        return Math.max(leftMaxDep, rightMaxDep) + 1;
    }

    // 自顶向下
    int ans = 0;

    public int maxDepthII(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    public void dfs(TreeNode root, int depth) {
        if (root == null) {
            // 不一定是叶子结点.
            ans = Math.max(ans, depth);
            return;
        }

        depth++;
        dfs(root.left, depth);
        dfs(root.right, depth);
    }

    // BFS
    public int maxDepthIII(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int ans = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            ans++;

            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }

        }

        return ans;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 111. 二叉树的最小深度
     * @Date 14:37 2026/4/10
     * @Param [root]
     **/
    // dfs 自底向上
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftMinDep = minDepth(root.left);
        int rightMinDep = minDepth(root.right);


        int depth = Math.min(leftMinDep, rightMinDep);
        if (leftMinDep == 0 || rightMinDep == 0) {
            depth = Math.max(leftMinDep, rightMinDep);
        }

        return depth + 1;
    }

    // 自顶向下
    // int ans = 0;
    public int minDepthII(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    public void dfsII(TreeNode root, int depth) {
        if (root == null) {
            return;
        }

        depth++;
        if (root.left == null && root.right == null) {
            if (ans == 0) {
                ans = depth;
            } else {
                ans = Math.min(ans, depth);
            }

            return;
        }


        dfsII(root.left, depth);
        dfsII(root.right, depth);
    }

    // bfs
    public int minDepthIII(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            depth++;

            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

                size--;
            }
        }

        return -1;
    }


    /**
     * @return void
     * @Author 强仔不强
     * @Description 112. 路径总和
     * @Date 17:33 2026/4/10
     * @Param [args]
     **/
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null && root.val == targetSum) {
            return true;
        }

        targetSum -= root.val;

        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
    }

    // bfs
    public boolean hasPathSumII(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        queue1.offer(root);
        queue2.offer(root.val);

        while (!queue1.isEmpty()) {
            int size = queue1.size();
            while (size > 0) {
                TreeNode node = queue1.poll();
                int val = queue2.poll();
                size--;

                if (node.left == null && node.right == null && val == targetSum) {
                    return true;
                }

                if (node.left != null) {
                    queue1.offer(node.left);
                    queue2.offer(val + node.left.val);
                }

                if (node.right != null) {
                    queue1.offer(node.right);
                    queue2.offer(val + node.right.val);
                }
            }
        }

        return false;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 129. 求根节点到叶节点数字之和
     * @Date 21:12 2026/4/10
     * @Param [root]
     **/
    // int ans = 0;
    public int sumNumbers(TreeNode root) {
        dfsIII(root, 0);
        return ans;
    }

    public void dfsIII(TreeNode root, int val) {
        if (root == null) {
            return;
        }

        val = val * 10 + root.val;
        if (root.left == null && root.right == null) {
            ans += val;
            return;
        }

        dfsIII(root.left, val);
        dfsIII(root.right, val);
    }

    // 有返回值
    public int sumNumbersII(TreeNode root) {
        return sumDfs(root, 0);
    }

    public int sumDfs(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }

        val = val * 10 + root.val;
        if (root.left == null && root.right == null) {
            return val;
        }

        return sumDfs(root.left, val) + sumDfs(root.right, val);
    }

    // bfs
    public int sumNumbersIII(TreeNode root) {
        int ans = 0;

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        queue1.offer(root);
        queue2.offer(root.val);

        while (!queue1.isEmpty()) {
            int size = queue1.size();
            while (size > 0) {
                TreeNode node = queue1.poll();
                int val = queue2.poll();

                if (node.left == null && node.right == null) {
                    ans += val;
                }

                if (node.left != null) {
                    queue1.offer(node.left);
                    queue2.offer(val * 10 + node.left.val);
                }

                if (node.right != null) {
                    queue1.offer(node.right);
                    queue2.offer(val * 10 + node.right.val);
                }

                size--;
            }
        }

        return ans;
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @Author 强仔不强
     * @Description 199. 二叉树的右视图
     * @Date 22:08 2026/4/10
     * @Param [root]
     **/
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                size--;

                if (size == 0) {
                    ans.add(node.val);
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return ans;
    }

    public List<Integer> rightSideViewII(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Set<Integer> depth = new HashSet<>();

        viewDfs(root, 1, ans, depth);

        return ans;
    }

    public void viewDfs(TreeNode root, int d, List<Integer> ans, Set<Integer> depth) {
        if (root == null) {
            return;
        }

        if (!depth.contains(d)) {
            ans.add(root.val);
            depth.add(d);
        }

        d++;
        viewDfs(root.left, d, ans, depth);
        viewDfs(root.right, d, ans, depth);
    }


    public List<Integer> rightSideViewIII(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        viewDFS(root, 0, ans);

        return ans;
    }

    public void viewDFS(TreeNode root, int depth, List<Integer> ans) {
        if (root == null) {
            return;
        }

        if (depth == ans.size()) {
            ans.add(root.val);
        }

        depth++;
        viewDFS(root.right, depth, ans);
        viewDFS(root.left, depth, ans);
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1448. 统计二叉树中好节点的数目
     * @Date 11:22 2026/4/11
     * @Param [root]
     **/
    public int goodNodes(TreeNode root) {
        return gDfs(root, Integer.MIN_VALUE);
    }

    public int gDfs(TreeNode root, int max) {
        if (root == null) {
            return 0;
        }

        int cnt = 0;
        if (root.val >= max) {
            cnt++;
            max = root.val;
        }

        return gDfs(root.left, max) + gDfs(root.right, max) + cnt;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1315. 祖父节点值为偶数的节点和
     * @Date 11:48 2026/4/11
     * @Param [root]
     **/
    public int sumEvenGrandparent(TreeNode root) {
        return sumDfs(root, 0, 0);
    }

    public int sumDfs(TreeNode root, int parent, int grandparent) {
        if (root == null) {
            return 0;
        }

        int val = 0;
        if (grandparent > 0 && grandparent % 2 == 0) {
            val = root.val;
        }

        return sumDfs(root.left, root.val, parent) + sumDfs(root.right, root.val, parent) + val;
    }

    /**
     * @return void
     * @Author 强仔不强
     * @Description 988. 从叶结点开始的最小字符串
     * @Date 20:53 2026/4/12
     * @Param [args]
     **/
    // 自底向上（搞不了）
    public String smallestFromLeaf(TreeNode root) {
        if (root == null) {
            return "";
        }

        char ch = (char) (root.val + 'a');
        String left_string = smallestFromLeaf(root.left) + ch;
        String right_string = smallestFromLeaf(root.right) + ch;

        if (left_string.length() == 1 || right_string.length() == 1) {
            return left_string.length() == 1 ? right_string : left_string;
        }


        return left_string.compareTo(right_string) < 0 ? left_string : right_string;
    }


    // 自顶向下
    // dfs 不带返回值.
    String ret = "";

    public String smallestFromLeafII(TreeNode root) {
        leafDfs(root, "");
        return ret;
    }

    public void leafDfs(TreeNode root, String string) {
        if (root == null) {
            return;
        }

        char ch = (char) (root.val + 'a');
        string = ch + string;
        if (root.left == null && root.right == null) {
            if (ret.length() == 0 || ret.compareTo(string) > 0) {
                ret = string;
            }
            return;
        }

        leafDfs(root.left, string);
        leafDfs(root.right, string);
    }

    // String ret = "";
    public String smallestFromLeafIIV(TreeNode root) {
        leaDfs(root, new StringBuilder());
        return ret;
    }

    public void leaDfs(TreeNode root, StringBuilder s) {
        if (root == null) {
            return;
        }

        s.append((char) (root.val + 'a'));

        if (root.left == null && root.right == null) {
            s.reverse();
            String string = s.toString();
            if (ret.length() == 0 || string.compareTo(ret) < 0) {
                ret = string;
            }

            s.reverse();
        }

        leaDfs(root.left, s);
        leaDfs(root.right, s);
        s.deleteCharAt(s.length() - 1);
    }


    // dfs 带返回值.
    public String smallestFromLeafIII(TreeNode root) {
        return leavesDfs(root, "");
    }

    public String leavesDfs(TreeNode root, String string) {
        if (root == null) {
            return "";
        }

        char ch = (char) (root.val + 'a');
        string = ch + string;
        if (root.left == null && root.right == null) {
            return string;
        }

        String left_string = leavesDfs(root.left, string);
        String right_string = leavesDfs(root.right, string);

        if (left_string.length() == 1 || right_string.length() == 1) {
            return left_string.length() == 1 ? right_string : left_string;
        }

        return left_string.compareTo(right_string) < 0 ? left_string : right_string;
    }

    // bfs
    public String smallestFromLeafIV(TreeNode root) {
        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<String> strings = new LinkedList<>();

        nodes.offer(root);
        String s1 = Character.toString((char) (root.val + 'a'));
        strings.offer(s1);

        String ans = "";
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            String string = strings.poll();
            if (node.left == null && node.right == null) {
                if (ans.length() == 0 || string.compareTo(ans) < 0) {
                    ans = string;
                }
            }

            if (node.left != null) {
                nodes.offer(node.left);
                String s = (char) (node.left.val + 'a') + string;
                strings.offer(s);
            }

            if (node.right != null) {
                nodes.offer(node.right);
                String s = (char) (node.right.val + 'a') + string;
                strings.offer(s);
            }

        }

        return ans;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1026. 节点与其祖先之间的最大差值
     * @Date 12:52 2026/4/14
     * @Param [root]
     **/
    // dfs 不带返回值
    // int ans = 0;
    public int maxAncestorDiff(TreeNode root) {
        maxDfs(root, root.val, root.val);
        return ans;
    }

    public void maxDfs(TreeNode root, int max, int min) {
        if (root == null) {
            return;
        }


//        min = Math.min(root.val, min);
//        max = Math.max(root.val, max)
//        int val = Math.max(max - root.val, root.val - min);
//        ans = Math.max(val, ans);

        int val = Math.max(max - root.val, root.val - min);
        ans = Math.max(val, ans);

        min = Math.min(root.val, min);
        max = Math.max(root.val, max);

        maxDfs(root.left, max, min);
        maxDfs(root.right, max, min);
    }

    // 优化
    public void maxAncestorDfs(TreeNode root, int max, int min) {
        if (root == null) {
            ans = Math.max(ans, max - min);
            return;
        }

        max = Math.max(max, root.val);
        min = Math.min(min, root.val);

        maxAncestorDfs(root.left, max, min);
        maxAncestorDfs(root.right, max, min);
    }


    // dfs 带返回值
    public int maxAncestorDiffII(TreeNode root) {
        return maxADfs(root, root.val, root.val);
    }

    public int maxADfs(TreeNode root, int max, int min) {
        if (root == null) {
            return 0;
        }

        int val = Math.max(max - root.val, root.val - min);

        min = Math.min(root.val, min);
        max = Math.max(root.val, max);

        int left = maxADfs(root.left, max, min);
        int right = maxADfs(root.right, max, min);

        return Math.max(Math.max(left, right), val);
    }


    // dfs 自底向下
    // int[] 最小值 + 最大值.
    public int maxAncestorDiffIV(TreeNode root) {
        maxAncestorDiffDfs(root);
        return ans;
    }

    public int[] maxAncestorDiffDfs(TreeNode root) {
        if (root == null) {
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        }

        int[] p = maxAncestorDiffDfs(root.left);
        int[] q = maxAncestorDiffDfs(root.right);
        int min = Math.min(root.val, Math.min(p[0], q[0]));
        int max = Math.max(root.val, Math.max(p[1], q[1]));

        ans = Math.max(ans, Math.max(root.val - min, max - root.val));
        return new int[]{min, max};
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1022. 从根到叶的二进制数之和
     * @Date 19:27 2026/4/14
     * @Param [root]
     **/
    // int ans = 0;
    public int sumRootToLeaf(TreeNode root) {
        sumRLDfs(root, 0);
        return ans;
    }

    public void sumRLDfs(TreeNode root, int num) {
        if (root == null) {
            return;
        }

//        num = num << 1 | root.val;
        num = num * 2 + root.val;
        if (root.left == null && root.right == null) {
            ans += num;
            return;
        }

        sumRLDfs(root.left, num);
        sumRLDfs(root.right, num);
    }

    public int sumRootToLeafII(TreeNode root) {
        return sumRTLDfs(root, 0);
    }

    public int sumRTLDfs(TreeNode root, int num) {
        if (root == null) {
            return 0;
        }

        num = num * 2 + root.val;
        if (root.left == null && root.right == null) {
            return num;
        }

        return sumRTLDfs(root.left, num) + sumRTLDfs(root.right, num);
    }


    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 623. 在二叉树中增加一行
     * @Date 19:52 2026/4/14
     * @Param [root, val, depth]
     **/
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }

        addDfs(root, 0, val, depth);
        return root;
    }


    public void addDfs(TreeNode root, int d, int val, int depth) {
        if (root == null) {
            return;
        }

        d++;
        if (d == depth - 1) {
            TreeNode node_left = root.left;
            TreeNode node_right = root.right;

            root.left = new TreeNode(val);
            root.right = new TreeNode(val);

            root.left.left = node_left;
            root.right.right = node_right;
            return;
        }

        addDfs(root.left, d, val, depth);
        addDfs(root.right, d, val, depth);
    }

    // Bfs
    public TreeNode addOneRowII(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int deep = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            deep++;

            while (size > 0) {
                TreeNode node = queue.poll();
                size--;

                if (deep == depth - 1) {
                    TreeNode left_node = node.left;
                    TreeNode right_node = node.right;

                    node.left = new TreeNode(val);
                    node.right = new TreeNode(val);

                    node.left.left = left_node;
                    node.right.right = right_node;
                } else {
                    if (node.left != null) {
                        queue.offer(node.left);
                    }

                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
        }

        return root;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1372. 二叉树中的最长交错路径
     * @Date 15:02 2026/4/15
     * @Param [root]
     **/
    // int ans = 0;
    public int longestZigZag(TreeNode root) {
        ZZDfs(root, 0, true);
        return ans;
    }

    public void ZZDfs(TreeNode root, int cnt, boolean isLeft) {
        if (root == null) {
            ans = Math.max(ans, cnt);
            return;
        }

        cnt++;
        ZZDfs(root.left, isLeft ? 0 : cnt, true);
        ZZDfs(root.right, isLeft ? cnt : 0, false);
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 965. 单值二叉树
     * @Date 16:16 2026/4/15
     * @Param [root]
     **/
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean bookL = root.left != null ? root.val == root.left.val : true;
        boolean bookR = root.right != null ? root.val == root.right.val : true;

        return isUnivalTree(root.left) && isUnivalTree(root.right) && bookL && bookR;
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 100. 相同的树
     * @Date 16:24 2026/4/15
     * @Param [p, q]
     **/
    // Dfs
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // Bfs
    public boolean isSameTreeII(TreeNode p, TreeNode q) {
        Queue<TreeNode> p1 = new LinkedList<>();
        Queue<TreeNode> q1 = new LinkedList<>();

        p1.offer(p);
        q1.offer(q);

        while (!p1.isEmpty()) {
            int size = p1.size();
            while (size > 0) {
                TreeNode np = p1.poll();
                TreeNode nq = q1.poll();
                size--;

                if (np == null && nq == null) {
                    continue;
                }

                if (np == null || nq == null) {
                    return false;
                }
                if (np.val != nq.val) {
                    return false;
                }

                p1.offer(np.left);
                p1.offer(np.right);

                q1.offer(nq.left);
                q1.offer(nq.right);
            }
        }

        return true;
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 101. 对称二叉树
     * @Date 17:47 2026/4/15
     * @Param [root]
     **/
    // Dfs
    public boolean isSymmetric(TreeNode root) {
        return isSymmetricDfs(root.left, root.right);
    }

    public boolean isSymmetricDfs(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }

        if (n1 == null || n2 == null) {
            return false;
        }

        if (n1.val != n2.val) {
            return false;
        }

        return isSymmetricDfs(n1.left, n2.right) && isSymmetricDfs(n1.right, n2.left);
    }

    // Bfs
    public boolean isSymmetricII(TreeNode root) {
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();

        q1.offer(root.left);
        q2.offer(root.right);

        while (!q1.isEmpty()) {
            int size = q1.size();
            while (size > 0) {
                TreeNode n1 = q1.poll();
                TreeNode n2 = q2.poll();
                size--;

                if (n1 == null && n2 == null) {
                    continue;
                }
                if (n1 == null || n2 == null) {
                    return false;
                }
                if (n1.val != n2.val) {
                    return false;
                }

                q1.offer(n1.left);
                q1.offer(n1.right);

                q2.offer(n2.right);
                q2.offer(n2.left);
            }
        }
        return true;
    }


    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 951. 翻转等价二叉树
     * @Date 19:30 2026/4/15
     * @Param [root1, root2]
     **/
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.val != root2.val) {
            return false;
        }

        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) ||
                (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }


    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 1379. 找出克隆二叉树中的相同节点
     * @Date 16:30 2026/4/16
     * @Param [original, cloned, target]
     **/
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) {
            return null;
        }

        if (original == target) {
            return cloned;
        }

        TreeNode node_left = getTargetCopy(original.left, cloned.left, target);
        TreeNode node_right = getTargetCopy(original.right, cloned.right, target);

        return node_left == null ? node_right : node_left;
    }


    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 110. 平衡二叉树
     * @Date 16:43 2026/4/16
     * @Param [root]
     **/
    public boolean isBalanced(TreeNode root) {
        return isBalanceDfs(root) >= 0;
    }

    // 返回 二叉树 的高度,  返回 -1 则表示非平衡二叉树, 返回其他值 则表示是 平衡二叉树.
    public int isBalanceDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int node_left = isBalanceDfs(root.left);
        int node_right = isBalanceDfs(root.right);

        if (node_left == -1 || node_right == -1 || Math.abs(node_right - node_left) > 1) {
            return -1;
        }

        return Math.max(node_left, node_right) + 1;
    }

    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 226. 翻转二叉树
     * @Date 17:00 2026/4/16
     * @Param [root]
     **/
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode node_left = invertTree(root.left);
        TreeNode node_right = invertTree(root.right);

        root.left = node_right;
        root.right = node_left;

        return root;
    }


    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 617. 合并二叉树
     * @Date 17:12 2026/4/16
     * @Param [root1, root2]
     **/
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }

        if (root2 == null) {
            return root1;
        }

        TreeNode node_left = mergeTrees(root1.left, root2.left);
        TreeNode node_right = mergeTrees(root1.right, root2.right);

//        root1.val += root2.val;
//        root1.left = node_left;
//        root1.right = node_right;
//        return root1;

        TreeNode node = new TreeNode(root1.val + root2.val);
        node.left = node_left;
        node.right = node_right;

        return node;
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 2331. 计算布尔二叉树的值
     * @Date 17:29 2026/4/16
     * @Param [root]
     **/
    public boolean evaluateTree(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val == 1;
        }

        boolean left = evaluateTree(root.left);
        boolean right = evaluateTree(root.right);

        if (root.val == 2) {
            return left || right;
        }
        return left && right;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 508. 出现次数最多的子树元素和
     * @Date 20:24 2026/4/16
     * @Param [root]
     **/
    public int[] findFrequentTreeSum(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<>();
        findDfs(root, map);

        int maxCnt = 0;
        for (int val : map.values()) {
            maxCnt = Math.max(maxCnt, val);
        }

        List<Integer> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxCnt) {
                ans.add(entry.getKey());
            }
        }

        int n = ans.size();
        int[] ret = new int[n];

        for (int i = 0; i < n; i++) {
            ret[i] = ans.get(i);
        }
        return ret;
    }

    public int findDfs(TreeNode root, HashMap<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }

        int val_left = findDfs(root.left, map);
        int val_right = findDfs(root.right, map);
        int val = val_left + val_right + root.val;

        int cnt = map.getOrDefault(val, 0);
        map.put(val, cnt + 1);

        return val;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 563. 二叉树的坡度
     * @Date 20:43 2026/4/16
     * @Param [root]
     **/
    public int findTilt(TreeNode root) {
        int[] ret = findTiltDfs(root);
        return ret[1];
    }

    // 0 -- 元素之和   1 -- 坡度之和
    public int[] findTiltDfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        int[] node_left = findTiltDfs(root.left);
        int[] node_right = findTiltDfs(root.right);

        int[] ans = new int[2];
        ans[0] = node_left[0] + node_right[0] + root.val;
        ans[1] = node_left[1] + node_right[1] + (Math.abs(node_left[0] - node_right[0]));

        return ans;
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 606. 根据二叉树创建字符串
     * @Date 20:54 2026/4/16
     * @Param [root]
     **/
    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }

        String s_left = tree2str(root.left);
        String s_right = tree2str(root.right);

        StringBuilder sb = new StringBuilder(Integer.toString(root.val));
        if (!s_left.isEmpty() || (s_left.isEmpty() && !s_right.isEmpty())) {
            sb.append('(');
            sb.append(s_left);
            sb.append(')');
        }

        if (!s_right.isEmpty()) {
            sb.append('(');
            sb.append(s_right);
            sb.append(')');
        }

        return sb.toString();
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2265. 统计值等于子树平均值的节点数
     * @Date 21:37 2026/4/16
     * @Param [root]
     **/
    // int ans = 0;
    public int averageOfSubtree(TreeNode root) {
        averageDfs(root);
        return ans;
    }

    public int[] averageDfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        int[] n_left = averageDfs(root.left);
        int[] n_right = averageDfs(root.right);

        int sum = n_left[0] + n_right[0] + root.val;
        int cnt = n_left[1] + n_right[1] + 1;

        if (sum / cnt == root.val) {
            ans++;
        }

        return new int[]{sum, cnt};
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 3319. 第 K 大的完美二叉子树的大小
     * @Date 14:12 2026/4/18
     * @Param [root, k]
     **/
    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        kthLPSDfs(root, queue, k);

        if (queue.size() < k) {
            return -1;
        }
        return queue.poll();
    }

    public int kthLPSDfs(TreeNode root, PriorityQueue<Integer> queue, int k) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            queue.offer(1);

            if (queue.size() > k) {
                queue.poll();
            }

            return 1;
        }

        int n_left = kthLPSDfs(root.left, queue, k);
        int n_right = kthLPSDfs(root.right, queue, k);

        int cnt = n_left + n_right + 1;
        if (n_left > 0 && n_left == n_right) {
            queue.offer(cnt);

            if (queue.size() > k) {
                queue.poll();
            }

            return cnt;
        }

        return 0;
    }

    /**
     * @Author 强仔不强
     * @Description 1339. 分裂二叉树的最大乘积
     * @Date 14:40 2026/4/18
     * @Param [root]
     * @return int
     **/
    long csq = 0;
    private final int MOD = 1000000007;

    public int maxProduct(TreeNode root) {
        long sum = sumDfs(root);

        maxPDfs(root, sum);
        return (int) (csq % MOD);
    }

    public long sumDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return sumDfs(root.left) + sumDfs(root.right) + root.val;
    }

    public long maxPDfs(TreeNode root, long sum) {
        if (root == null) {
            return 0;
        }

        long val = maxPDfs(root.left, sum) + maxPDfs(root.right, sum) + root.val;
        csq = Math.max(csq, val * (sum - val));

        return val;
    }

    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 814. 二叉树剪枝
     * @Date 15:29 2026/4/18
     * @Param [root]
     **/
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode n_left = pruneTree(root.left);
        TreeNode n_right = pruneTree(root.right);

        if (root.val == 1 || n_left != null || n_right != null) {
            root.left = n_left;
            root.right = n_right;
            return root;
        }

        return null;
    }


    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 1325. 删除给定值的叶子节点
     * @Date 15:43 2026/4/18
     * @Param [root, target]
     **/
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }

        TreeNode n_left = removeLeafNodes(root.left, target);
        TreeNode n_right = removeLeafNodes(root.right, target);

        if (n_left == null && n_right == null && root.val == target) {
            return null;
        }

        root.left = n_left;
        root.right = n_right;

        return root;
    }

    /**
     * @return java.util.List<BinaryTree.TreeNode>
     * @Author 强仔不强
     * @Description 1110. 删点成林
     * @Date 15:58 2026/4/18
     * @Param [root, to_delete]
     **/
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> list = new ArrayList<>();

        Set<Integer> set = new HashSet<>();
        for (int val : to_delete) {
            set.add(val);
        }

        root = delDfs(root, list, set);
        if (root != null) {
            list.add(root);
        }

        return list;
    }

    public TreeNode delDfs(TreeNode root, List<TreeNode> list, Set<Integer> set) {
        if (root == null) {
            return null;
        }

        root.left = delDfs(root.left, list, set);
        root.right = delDfs(root.right, list, set);

        if (set.contains(root.val)) {
            if (root.left != null) {
                list.add(root.left);
            }
            if (root.right != null) {
                list.add(root.right);
            }

            return null;
        }

        return root;
    }

    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 538. 把二叉搜索树转换为累加树
     * @Date 16:45 2026/4/18
     * @Param [root]
     **/
    public TreeNode convertBST(TreeNode root) {
        convertDfs(root, 0);
        return root;
    }

    public int convertDfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        int val = root.val;

        int sum_right = convertDfs(root.right, sum);
        sum += sum_right + root.val;
        root.val = sum;

        int sum_left = convertDfs(root.left, sum);

        return sum_left + sum_right + val;
    }


    private int largerSum = 0;

    public TreeNode convertBSTII(TreeNode root) {
        BSDfs(root);
        return root;
    }

    public void BSDfs(TreeNode root) {
        if (root == null) {
            return;
        }

        BSDfs(root.right);
        largerSum += root.val;
        root.val = largerSum;

        BSDfs(root.left);
    }

    /**
     * @Author 强仔不强
     * @Description 865. 具有所有最深节点的最小子树
     * @Date 21:09 2026/4/18
     * @Param [root]
     * @return BinaryTree.TreeNode
     **/
    TreeNode result = null;
    int max = 0;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        subDfs(root, 0);
        return result;
    }

    // 参数 depth 深度
    // 返回值 当前子树叶子节点的最大深度（到最原始 root 的距离.）.
    public int subDfs(TreeNode root, int depth) {
        if (root == null) {
            max = Math.max(max, depth);
            return depth;
        }

        depth++;
        int d_left = subDfs(root.left, depth);
        int d_right = subDfs(root.right, depth);

        if (d_left == d_right && d_left == max) {
            result = root;
        }

        return Math.max(d_left, d_right);
    }


    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 235. 二叉搜索树的最近公共祖先
     * @Date 21:58 2026/4/18
     * @Param [root, p, q]
     **/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int x = root.val;
        if (p.val > x && q.val > x) {
            return lowestCommonAncestor(root.right, p, q);
        }

        if (p.val < x && q.val < x) {
            return lowestCommonAncestor(root.left, p, q);
        }

        return root;
    }

    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 236. 二叉树的最近公共祖先
     * @Date 22:04 2026/4/18
     * @Param [root, p, q]
     **/
    public TreeNode lowestAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }

        if (root == p || root == q) {
            return root;
        }

        TreeNode n_left = lowestAncestor(root.left, p, q);
        TreeNode n_right = lowestAncestor(root.right, p, q);

        if (n_left != null && n_right != null) {
            return root;
        }

        return n_left != null ? n_left : n_right;
    }

    /**
     * @Author 强仔不强
     * @Description
     * @Date 16:42 2026/4/19
     * @Param [root]
     * @return int
     **/
    int csqMax = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        diameterDfs(root);
        return csqMax;
    }

    // 返回值 当前子树 叶子节点的最大深度 （路径上的节点个数.）
    public int diameterDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int n_left = diameterDfs(root.left);
        int n_right = diameterDfs(root.right);

        csqMax = Math.max(csqMax, n_left + n_right);

        return Math.max(n_left, n_right) + 1;

    }

    /**
     * @Author 强仔不强
     * @Description 687. 最长同值路径
     * @Date 17:11 2026/4/19
     * @Param [root]
     * @return int
     **/
    int ansMax = 0;

    public int longestUnivaluePath(TreeNode root) {
        longestDfs(root);
        return ansMax;
    }

    // 返回值, root.val 同值的最大直径.
    public int longestDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }


        int d_left = longestDfs(root.left);
        if (root.left != null && root.val != root.left.val) {
            d_left = 0;
        }

        int d_right = longestDfs(root.right);
        if (root.right != null && root.val != root.right.val) {
            d_right = 0;
        }

        ansMax = Math.max(ansMax, d_left + d_right);

        return Math.max(d_left, d_right) + 1;
    }

    /**
     * @Author 强仔不强
     * @Description 124. 二叉树中的最大路径和
     * @Date 15:19 2026/4/22
     * @Param [root]
     * @return int
     **/
    int retMax = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPSDfs(root);
        return retMax;
    }

    public int maxPSDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int v_left = maxPSDfs(root.left);
        int v_right = maxPSDfs(root.right);

        retMax = Math.max(retMax, v_left + v_right + root.val);

        return Math.max(Math.max(v_left, v_right) + root.val, 0);
    }


    /**
     * @return java.util.List<java.lang.String>
     * @Author 强仔不强
     * @Description 257. 二叉树的所有路径
     * @Date 16:09 2026/4/22
     * @Param [root]
     **/
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        binaryTreePDfs(root, sb, ret);
        return ret;
    }

    public void binaryTreePDfs(TreeNode root, StringBuilder sb, List<String> ret) {
        if (root == null) {
            return;
        }

        int lastSize = sb.length();

        sb.append(root.val);
        if (root.left == null && root.right == null) {
            ret.add(sb.toString());
        }
        sb.append("->");

        binaryTreePDfs(root.left, sb, ret);
        binaryTreePDfs(root.right, sb, ret);

        sb.delete(lastSize, sb.length());
    }

    /**
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @Author 强仔不强
     * @Description 113. 路径总和 II
     * @Date 16:39 2026/4/22
     * @Param [root, targetSum]
     **/
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        pathSumDfs(root, 0, targetSum, ans, list);
        return ans;
    }

    public void pathSumDfs(TreeNode root, int sum, int targetSum, List<List<Integer>> ans, List<Integer> list) {
        if (root == null) {
            return;
        }

        sum += root.val;
        list.add(root.val);

        if (root.left == null && root.right == null && sum == targetSum) {
            List<Integer> l = new ArrayList<>(list);
            ans.add(l);
        }

        pathSumDfs(root.left, sum, targetSum, ans, list);
        pathSumDfs(root.right, sum, targetSum, ans, list);

        list.remove(list.size() - 1);
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1457. 二叉树中的伪回文路径
     * @Date 17:15 2026/4/22
     * @Param [root]
     **/
    public int pseudoPalindromicPaths(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        return pPPDfs(root, set);
    }

    public int pPPDfs(TreeNode root, Set<Integer> singles) {
        if (root == null) {
            return 0;
        }

        if (!singles.remove(root.val)) {
            singles.add(root.val);
        }

        int cnt = pPPDfs(root.left, singles) + pPPDfs(root.right, singles);
        if (root.left == null && root.right == null && singles.size() <= 1) {
            cnt = 1;
        }

        if (!singles.remove(root.val)) {
            singles.add(root.val);
        }

        return cnt;
    }


    // 位运算, 无需回溯时修改变量.
    public int pseudoPalindromicPathsII(TreeNode root) {
        return pDfs(root, 0);
    }

    public int pDfs(TreeNode root, int mask) {
        if (root == null) {
            return 0;
        }

        mask ^= 1 << root.val;
        if (root.left == null && root.right == null) {
            return Integer.bitCount(mask) <= 1 ? 1 : 0;
        }

        return pDfs(root.left, mask) + pDfs(root.right, mask);
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 437. 路径总和 III
     * @Date 11:22 2026/4/23
     * @Param [root, targetSum]
     **/
    public int pathSumIII(TreeNode root, int targetSum) {
        List<Integer> list = new ArrayList<>();
        return pathSumDfs(root, targetSum, list, 0);
    }

    public int pathSumDfs(TreeNode root, int targetSum, List<Integer> list, long sum) {
        if (root == null) {
            return 0;
        }

        list.add(root.val);
        sum += root.val;

        int cnt = pathSumDfs(root.left, targetSum, list, sum) + pathSumDfs(root.right, targetSum, list, sum);
        ;

        for (int i = 0; i < list.size(); i++) {
            if (sum == targetSum) {
                cnt++;
            }
            sum -= list.get(i);
        }

        list.remove(list.size() - 1);
        return cnt;
    }


    /**
     * @return int
     * @Author 强仔不强
     * @Description 560. 和为 K 的子数组
     * @Date 12:27 2026/4/23
     * @Param [nums, k]
     **/
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;

        // 前缀和
        int[] p = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            p[i] = p[i - 1] + nums[i - 1];
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int ret = 0;
        for (int i = 1; i < n + 1; i++) {
            int cnt1 = map.getOrDefault(p[i] - k, 0);
            ret += cnt1;

            int cnt2 = map.getOrDefault(p[i], 0);
            map.put(p[i], cnt2 + 1);
        }

        return ret;
    }

    // Dfs + 前缀和 + 枚举右，维护左.
    public int pathSumIV(TreeNode root, int targetSum) {
        HashMap<Long, Integer> map = new HashMap<>();
        map.put((long) 0, 1);

        return pathSDfs(root, targetSum, 0, map);
    }

    public int pathSDfs(TreeNode root, int targetSum, long sum, HashMap<Long, Integer> map) {
        if (root == null) {
            return 0;
        }

        sum += root.val;
        int cnt = map.getOrDefault(sum - targetSum, 0);

        int num = map.getOrDefault(sum, 0);
        map.put(sum, num + 1);

        cnt += pathSDfs(root.left, targetSum, sum, map) + pathSDfs(root.right, targetSum, sum, map);

        map.put(sum, num);

        return cnt;
    }

    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 700. 二叉搜索树中的搜索
     * @Date 15:34 2026/4/23
     * @Param [root, val]
     **/
    // 递归
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return root;
        }

        if (root.val == val) {
            return root;
        }

        if (root.val > val) {
            return searchBST(root.left, val);
        }
        return searchBST(root.right, val);
    }

    // 迭代
    public TreeNode searchBSTII(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val) {
                break;
            }

            if (root.val > val) {
                root = root.left;
            } else {
                root = root.right;
            }

        }

        return root;
    }


    /**
     * @Author 强仔不强
     * @Description 530. 二叉搜索树的最小绝对差
     * @Date 15:44 2026/4/23
     * @Param [root]
     * @return int
     **/
    // 二叉搜索树的中序遍历有序的.
    int minDiff = Integer.MAX_VALUE;
    int pre = Integer.MIN_VALUE / 2;    // 防止减法 溢出.

    public int getMinimumDifference(TreeNode root) {
        getMDDfs(root);
        return minDiff;
    }

    public void getMDDfs(TreeNode root) {
        if (root == null) {
            return;
        }

        getMDDfs(root.left);
        minDiff = Math.min(minDiff, root.val - pre);
        pre = root.val;

        getMDDfs(root.right);
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 938. 二叉搜索树的范围和
     * @Date 17:02 2026/4/23
     * @Param [root, low, high]
     **/
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        int v_left = root.val <= low ? 0 : rangeSumBST(root.left, low, high);
        int v_right = root.val >= high ? 0 : rangeSumBST(root.right, low, high);

        int sum = v_left + v_right;
        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }

        return sum;

    }

    /**
     * @Author 强仔不强
     * @Description 501. 二叉搜索树中的众数
     * @Date 17:15 2026/4/23
     * @Param [root]
     * @return int[]
     **/
    int p = Integer.MAX_VALUE;
    int maxCnt = 0;
    int cnt = 0;

    // 二叉搜索树的中序遍历是有序的.
    public int[] findMode(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        findDfs(root, list);

        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }

        return ret;
    }

    public void findDfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        findDfs(root.left, list);

        if (p == Integer.MAX_VALUE || p == root.val) {
            cnt++;
        } else {
            cnt = 1;
        }

        if (cnt == maxCnt) {
            list.add(root.val);
        } else if (cnt > maxCnt) {
            maxCnt = cnt;
            list.clear();
            ;
            list.add(root.val);
        }

        p = root.val;

        findDfs(root.right, list);
    }

    /**
     * @Author 强仔不强
     * @Description 230.二叉搜索树中第 K 小的元素
     * @Date 14:27 2026/4/24
     * @Param [root, k]
     * @return int
     **/
    int cntK = 0;
    int ansK = -1;

    public int kthSmallestII(TreeNode root, int k) {
        kthSDfs(root, k);
        return ansK;
    }

    public void kthSDfs(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        kthSDfs(root.left, k);
        cnt++;
        if (cnt >= k) {
            if (cnt == k) {
                ansK = root.val;
            }
            return;
        }

        kthSDfs(root.right, k);
    }


    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }

        int v_left = kthSmallest(root.left, k);

        if (v_left >= 0) {
            return v_left;
        }

        cntK++;
        if (cntK == k) {
            return root.val;
        }

        return kthSmallest(root.right, k);
    }

    /**
     * @Author 强仔不强
     * @Description 98. 验证二叉搜索树
     * @Date 14:50 2026/4/24
     * @Param [root]
     * @return boolean
     **/
    // 中序遍历.
    long pr = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean bLeft = isValidBST(root.left);
        if (!bLeft) {
            return false;
        }

        if (pr != Long.MIN_VALUE && root.val <= pr) {
            return false;
        }

        pr = root.val;

        return isValidBST(root.right);
    }


    // 前序遍历
    public boolean isValidBSTII(TreeNode root) {
        return isVDfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }


    public boolean isVDfs(TreeNode root, long start, long end) {
        if (root == null) {
            return true;
        }

        long x = root.val;
        return (x > start && x < end) &&
                isVDfs(root.left, start, x) &&
                isVDfs(root.right, x, end);

    }

    // 后序遍历
    public boolean isValidBSTIII(TreeNode root) {
        long[] ret = isValidDfs(root);
        return ret[1] != Long.MAX_VALUE;
    }

    // long[0] -- root 为根的子树的最小值.
    // long[1] -- root 为根的子树的最大值.
    public long[] isValidDfs(TreeNode root) {
        if (root == null) {
            return new long[]{Long.MAX_VALUE, Long.MIN_VALUE};
        }

        long[] n_left = isValidDfs(root.left);
        long[] n_right = isValidDfs(root.right);

        if (root.val > n_left[1] && root.val < n_right[0]) {
            return new long[]{
                    Math.min(n_left[0], root.val),
                    Math.max(n_right[1], root.val)};
        }

        return new long[]{Long.MIN_VALUE, Long.MAX_VALUE};
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @Author 强仔不强
     * @Description 1305. 两棵二叉搜索树中的所有元素
     * @Date 16:34 2026/4/24
     * @Param [root1, root2]
     **/
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        inorder(root1, l1);
        inorder(root2, l2);

        List<Integer> ans = new ArrayList<>();

        int s1 = 0;
        int s2 = 0;

        while (s1 < l1.size() && s2 < l2.size()) {
            if (l1.get(s1) <= l2.get(s2)) {
                ans.add(l1.get(s1));
                s1++;
            } else {
                ans.add(l2.get(s2));
                s2++;
            }
        }

        while (s1 < l1.size()) {
            ans.add(l1.get(s1));
            s1++;
        }

        while (s2 < l2.size()) {
            ans.add(l2.get(s2));
            s2++;
        }

        return ans;
    }

    public void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    /**
     * @Author 强仔不强
     * @Description 99. 恢复二叉搜索树
     * @Date 21:40 2026/4/24
     * @Param [root]
     * @return void
     **/
    TreeNode n1 = null;
    TreeNode n2 = null;
    TreeNode pc = null;

    public void recoverTree(TreeNode root) {
        recoverDfs(root);

        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
    }

    public void recoverDfs(TreeNode root) {
        if (root == null) {
            return;
        }

        recoverDfs(root.left);

        if (pc != null && root.val < pc.val) {
            if (n1 == null) {
                n1 = pc;
            }

            n2 = root;
        }
        pc = root;

        recoverDfs(root.right);
    }

    /**
     * @Author 强仔不强
     * @Description 897. 递增顺序搜索树
     * @Date 21:58 2026/4/24
     * @Param [root]
     * @return BinaryTree.TreeNode
     **/
    TreeNode head = null;
    TreeNode pi = null;

    public TreeNode increasingBST(TreeNode root) {
        increasingDfs(root);
        pi.left = null;
        pi.right = null;
        return head;
    }

    public void increasingDfs(TreeNode root) {
        if (root == null) {
            return;
        }

        increasingDfs(root.left);
        if (pi == null) {
            head = root;
        } else {
            pi.left = null;
            pi.right = root;
        }

        pi = root;

        increasingDfs(root.right);
    }

    /**
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @Author 强仔不强
     * @Description 2476. 二叉搜索树最近节点查询
     * @Date 22:17 2026/4/24
     * @Param [root, queries]
     **/
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int target : queries) {
            tMax = -1;
            tMin = -1;

            List<Integer> l = new ArrayList<>();
            getMaxAndMin(root, target);
            l.add(tMax);
            l.add(tMin);

            ans.add(l);
        }

        return ans;
    }

    int tMax = -1;
    int tMin = -1;

    public void getMaxAndMin(TreeNode root, int target) {
        if (root == null) {
            return;
        }

        getMaxAndMin(root.left, target);

        if (root.val <= target) {
            tMax = root.val;
        }

        if (tMin > 0) {
            return;
        }
        if (root.val >= target) {
            tMin = root.val;
            return;
        }

        getMaxAndMin(root.right, target);
    }

    public List<List<Integer>> closestNodesII(TreeNode root, List<Integer> queries) {
        List<Integer> list = new ArrayList<>();

        closestDfs(root, list);

        List<List<Integer>> ans = new ArrayList<>();
        for (int val : queries) {
            List<Integer> l = new ArrayList<>();
            int i1 = getTarget(list, val + 1) - 1;
            int v1 = i1 == -1 ? -1 : list.get(i1);
            l.add(v1);

            int i2 = getTarget(list, val);
            int v2 = i2 == list.size() ? -1 : list.get(i2);
            l.add(v2);

            ans.add(l);
        }

        return ans;
    }

    public void closestDfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        closestDfs(root.left, list);
        list.add(root.val);
        closestDfs(root.right, list);
    }

    // 找到有序的 list 中第一个 >= target 的元素下标.
    public int getTarget(List<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    /**
     * @Author 强仔不强
     * @Description 653. 两数之和 IV - 输入二叉搜索树
     * @Date 14:13 2026/4/25
     * @Param [root, k]
     * @return boolean
     **/
    // 递归（前、中、后序遍历） + 哈希表.
    int min = Integer.MAX_VALUE;

    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return findTDfs(root, set, k);
    }

    public boolean findTDfs(TreeNode root, Set<Integer> set, int k) {
        if (root == null) {
            return false;
        }

        boolean bLeft = findTDfs(root.left, set, k);
        if (bLeft) {
            return true;
        }

        if (min == Integer.MAX_VALUE) {
            min = root.val;
        }

        if (set.contains(k - root.val)) {
            return true;
        }
        if (root.val + min > k) {
            return false;
        }

        set.add(root.val);

        return findTDfs(root.right, set, k);
    }

    // 中序遍历（递归） + 双指针
    public boolean findTargetII(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum == k) {
                return true;
            }

            if (sum > k) {
                right--;
            } else {
                left++;
            }
        }

        return false;
    }

    public void dfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }

    // 迭代 + 中序遍历 + 双指针
    public boolean findTargetIII(TreeNode root, int k) {
        Stack<TreeNode> left_stack = new Stack<>();
        Stack<TreeNode> right_stack = new Stack<>();

        TreeNode node = root;
        while (node != null) {
            left_stack.push(node);
            node = node.left;
        }

        while (root != null) {
            right_stack.push(root);
            root = root.right;
        }

        TreeNode l = left_stack.pop();
        TreeNode r = right_stack.pop();
        while (l.val < r.val) {
            int sum = l.val + r.val;
            if (sum == k) {
                return true;
            }

            if (sum < k) {
                l = l.right;
                while (l != null) {
                    left_stack.push(l);
                    l = l.left;
                }
                l = left_stack.pop();
            } else {
                r = r.left;
                while (r != null) {
                    right_stack.push(r);
                    r = r.right;
                }
                r = right_stack.pop();
            }
        }

        return false;
    }


    /**
     * @return
     * @Author 强仔不强
     * @Description 前中后序遍历（迭代）
     * @Date 14:17 2026/4/28
     * @Param
     **/
    public List<Integer> preTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                list.add(root.val);
                root = root.left;
            } else {
                root = stack.pop().right;
            }
        }

        return list;
    }

    public List<Integer> midTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                list.add(node.val);
                root = node.right;
            }
        }

        return list;
    }

    public List<Integer> backTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = null;

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.peek();
                // 或许没有右子树.
                if (node.right == null || p == node.right) {
                    list.add(node.val);
                    p = stack.pop();
                } else {
                    root = node.right;
                }
            }
        }

        return list;
    }

    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 108. 将有序数组转换为二叉搜索树
     * @Date 15:27 2026/4/28
     * @Param [nums]
     **/
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortDfs(nums, 0, nums.length - 1);
    }

    public TreeNode sortDfs(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = sortDfs(nums, left, mid - 1);
        root.right = sortDfs(nums, mid + 1, right);

        return root;
    }

    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 654. 最大二叉树
     * @Date 17:02 2026/4/28
     * @Param [nums]
     **/
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMBDfs(nums, 0, nums.length - 1);
    }

    public TreeNode constructMBDfs(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int max = -1;
        int index = -1;
        for (int i = left; i <= right; i++) {
            if (max < nums[i]) {
                max = nums[i];
                index = i;
            }
        }


        TreeNode root = new TreeNode(max);
        root.left = constructMBDfs(nums, left, index - 1);
        root.right = constructMBDfs(nums, index + 1, right);

        return root;
    }

    // 单调栈
    public TreeNode constructMaximumBinaryTreeII(int[] nums) {
        Stack<TreeNode> stack = new Stack<>();

        for (int num : nums) {
            TreeNode node = new TreeNode(num);

            while (!stack.isEmpty() && num > stack.peek().val) {
                node.left = stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.peek().right = node;
            }

            stack.push(node);
        }


        while (stack.size() > 1) {
            stack.pop();
        }

        return stack.peek();
    }

    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 998. 最大二叉树 II
     * @Date 18:01 2026/4/28
     * @Param [root, val]
     **/
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);

        if (val > root.val) {
            node.left = root;
            return node;
        }

        TreeNode cur = root;
        while (cur.right != null && cur.right.val > val) {
            cur = cur.right;
        }

        node.left = cur.right;
        cur.right = node;

        return root;
    }

    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 1008. 前序遍历构造二叉搜索树
     * @Date 14:03 2026/4/30
     * @Param [preorder]
     **/
    // 单调栈.
    public TreeNode bstFromPreorder(int[] preorder) {
        int n = preorder.length;
        Stack<TreeNode> stack = new Stack<>();

        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);

        for (int i = 1; i < n; i++) {
            TreeNode node = new TreeNode(preorder[i]);

            TreeNode p = null;
            while (!stack.isEmpty() && preorder[i] > stack.peek().val) {
                p = stack.pop();
            }

            if (p != null) {
                p.right = node;
            } else {
                stack.peek().left = node;
            }

            stack.push(node);
        }

        return root;
    }

    // 二分查找。
    public TreeNode bstFromPreorderII(int[] preorder) {
        return bstFPDfs(preorder, 0, preorder.length - 1);
    }

    public TreeNode bstFPDfs(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }

        int x = preorder[left];
        TreeNode root = new TreeNode(x);

        // 找到 > x 的第一个元素的下标
        int i = find(preorder, left + 1, right, x);
        root.left = bstFPDfs(preorder, left + 1, i - 1);
        root.right = bstFPDfs(preorder, i, right);

        return root;
    }

    public int find(int[] preorder, int left, int right, int x) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (preorder[mid] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    int[] array;
    int index;
    int size;

    public TreeNode bstFromPreorderIII(int[] preorder) {
        array = preorder;
        index = 0;
        size = preorder.length;

        return bstDfs(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // 当前节点的范围是 (start, end);
    public TreeNode bstDfs(int start, int end) {

        if (index == size || array[index] <= start || array[index] >= end) {
            return null;
        }

        int x = array[index];
        TreeNode root = new TreeNode(x);
        index++;

        root.left = bstDfs(start, x);
        root.right = bstDfs(x, end);

        return root;
    }

    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 1382. 将二叉搜索树变平衡
     * @Date 15:57 2026/4/30
     * @Param [root]
     **/
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        // 中序遍历（迭代）.
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                list.add(node.val);
                root = node.right;
            }
        }

        return creatBST(list, 0, list.size() - 1);
    }


    public TreeNode creatBST(List<Integer> list, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = creatBST(list, left, mid - 1);
        root.right = creatBST(list, mid + 1, right);

        return root;
    }


    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 2196. 根据描述创建二叉树
     * @Date 16:32 2026/4/30
     * @Param [descriptions]
     **/
    public TreeNode createBinaryTree(int[][] descriptions) {
        // key -- 父节点; value -- 左孩子、右孩子.
        // 没放叶子节点.
        HashMap<Integer, int[]> map = new HashMap<>();

        // set 找根节点, 即不作为孩子节点的父节点.
        Set<Integer> set = new HashSet<>();

        for (int[] d : descriptions) {
            int parent = d[0];
            int child = d[1];
            int isLeft = d[2];

            set.add(parent);

            int[] a = map.getOrDefault(parent, new int[2]);
            if (isLeft == 1) {
                a[0] = child;
            } else {
                a[1] = child;
            }

            map.put(parent, a);
        }

        for (int[] d : descriptions) {
            set.remove(d[1]);
        }

        int parent = (int) set.toArray()[0];
        return cBTDfs(parent, map);
    }

    public TreeNode cBTDfs(int parent, HashMap<Integer, int[]> map) {
        if (parent == 0) {
            return null;
        }

        TreeNode root = new TreeNode(parent);
        // 叶子结点不在 map 中, 返回 [0, 0];
        int[] children = map.getOrDefault(parent, new int[2]);

        root.left = cBTDfs(children[0], map);
        root.right = cBTDfs(children[1], map);

        return root;
    }

    /**
     * @Author 强仔不强
     * @Description 105. 从前序与中序遍历序列构造二叉树
     * @Date 17:23 2026/4/30
     * @Param [preorder, inorder]
     * @return BinaryTree.TreeNode
     **/
    int[] pre_array;
    int i;
    int length;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        pre_array = preorder;
        i = 0;
        length = pre_array.length;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < length; j++) {
            map.put(inorder[j], j);
        }

        return bTDfs(inorder, 0, length - 1, map);
    }

    // [left, right] inorder 区间.
    public TreeNode bTDfs(int[] inorder, int left, int right, HashMap<Integer, Integer> map) {
        if (i == length) {
            return null;
        }

        if (left > right) {
            return null;
        }

        TreeNode root = new TreeNode(pre_array[i]);
        int k = map.get(pre_array[i]);
        i++;

        root.left = bTDfs(inorder, left, k - 1, map);
        root.right = bTDfs(inorder, k + 1, right, map);

        return root;
    }


    public TreeNode buildTreeII(int[] preorder, int[] inorder) {
        int n = preorder.length;

        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            index.put(inorder[i], i);
        }

        return btDfs(0, n - 1, preorder, index, 0);
    }

    // [preL, preR] preorder 区间.
    public TreeNode btDfs(int preL, int preR, int[] preorder, Map<Integer, Integer> index, int inL) {
        if (preL > preR) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preL]);

        int i = index.get(preorder[preL]);
        // 左子树 [inL, i).
        int leftSize = i - inL;

        int mid = preL + leftSize;

        root.left = btDfs(preL + 1, mid, preorder, index, inL);
        root.right = btDfs(mid + 1, preR, preorder, index, i + 1);

        return root;
    }

    public TreeNode build(int[] preorder, int[] inorder) {
        int n = preorder.length;
        if (n == 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);

        int leftSize = indexOf(inorder, preorder[0]);

        int[] preL = Arrays.copyOfRange(preorder, 1, leftSize + 1);
        int[] preR = Arrays.copyOfRange(preorder, leftSize + 1, n);

        int[] inL = Arrays.copyOfRange(inorder, 0, leftSize);
        int[] inR = Arrays.copyOfRange(inorder, leftSize + 1, n);

        root.left = build(preL, inL);
        root.right = build(preR, inR);

        return root;
    }

    public int indexOf(int[] inorder, int target) {
        int n = inorder.length;
        for (int j = 0; j < n; j++) {
            if (inorder[j] == target) {
                return j;
            }
        }

        return -1;
    }

    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 106. 从中序与后序遍历序列构造二叉树
     * @Date 14:22 2026/5/8
     * @Param [inorder, postorder]
     **/
    public TreeNode buildT(int[] inorder, int[] postorder) {
        int n = inorder.length;
        if (n == 0) {
            return null;
        }

        int x = postorder[n - 1];
        TreeNode root = new TreeNode(x);

        // inorder 中左子树 [0, leftSize).
        int leftSize = indexOf(inorder, x);
        int[] postLeft = Arrays.copyOfRange(postorder, 0, leftSize);
        int[] postRight = Arrays.copyOfRange(postorder, leftSize, n);

        int[] inLeft = Arrays.copyOfRange(inorder, 0, leftSize);
        int[] inRight = Arrays.copyOfRange(inorder, leftSize + 1, n);

        root.left = buildT(inLeft, postLeft);
        root.right = buildT(inRight, postRight);

        return root;
    }


    public TreeNode buildTreeIV(int[] inorder, int[] postorder) {
        int n = inorder.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < n; j++) {
            map.put(inorder[j], j);
        }

        return buildDfs(0, n - 1, 0, postorder, map);
    }

    // [postL, postR]
    public TreeNode buildDfs(int postL, int postR, int inL, int[] postorder, Map<Integer, Integer> map) {
        if (postL > postR) {
            return null;
        }

        int x = postorder[postR];
        TreeNode root = new TreeNode(x);

        int k = map.get(x);
        // inorder 中左子树 [inL, k)
        int leftSize = k - inL;

        root.left = buildDfs(postL, postL + leftSize - 1, inL, postorder, map);
        root.right = buildDfs(postL + leftSize, postR - 1, k + 1, postorder, map);

        return root;
    }

    int i1 = 0;

    public TreeNode buildTreeIIV(int[] inorder, int[] postorder) {
        int n = inorder.length;
        i1 = n - 1;

        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < n; j++) {
            map.put(inorder[j], j);
        }

        return buildTDfs(0, n - 1, map, postorder);
    }

    public TreeNode buildTDfs(int inL, int inR, Map<Integer, Integer> map, int[] postorder) {
        if (i1 < 0) {
            return null;
        }
        if (inL > inR) {
            return null;
        }

        int x = postorder[i1];
        i1--;

        TreeNode root = new TreeNode(x);
        int k = map.get(x);

        root.right = buildTDfs(k + 1, inR, map, postorder);
        root.left = buildTDfs(inL, k - 1, map, postorder);

        return root;
    }

    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 889. 根据前序和后序遍历构造二叉树
     * @Date 15:21 2026/5/8
     * @Param [preorder, postorder]
     **/
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < n; j++) {
            map.put(postorder[j], j);
        }

        return cFPPDfs(0, n - 1, preorder, map, 0);
    }

    public TreeNode cFPPDfs(int preL, int preR, int[] preorder, Map<Integer, Integer> map, int postL) {
        if (preL > preR) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preL]);
        if (preL + 1 > preR) {
            return root;
        }

        int k = map.get(preorder[preL + 1]);
        int leftSize = k - postL + 1;

        root.left = cFPPDfs(preL + 1, preL + leftSize, preorder, map, postL);
        root.right = cFPPDfs(preL + leftSize + 1, preR, preorder, map, k + 1);

        return root;
    }

    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 701. 二叉搜索树中的插入操作
     * @Date 15:37 2026/5/9
     * @Param [root, val]
     **/
    // 迭代
    public TreeNode insertIntoBst(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);

        if (root == null) {
            return node;
        }

        TreeNode cur = root;
        TreeNode pre = null;
        boolean isLeft = false;

        while (cur != null) {
            pre = cur;

            if (cur.val < val) {
                cur = cur.right;
                isLeft = false;
            } else {
                cur = cur.left;
                isLeft = true;
            }
        }

        if (isLeft) {
            pre.left = node;
        } else {
            pre.right = node;
        }

        return root;
    }

    // 递归
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }

    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 450. 删除二叉搜索树中的节点
     * @Date 16:06 2026/5/9
     * @Param [root, key]
     **/
    public TreeNode deleteN(TreeNode root, int key) {
        TreeNode head = new TreeNode(Integer.MAX_VALUE);
        head.left = root;

        TreeNode pre = head;
        TreeNode cur = root;
        boolean isLeft = true;

        while (cur != null) {
            if (cur.val == key) {
                break;
            }

            pre = cur;
            if (cur.val > key) {
                cur = cur.left;
                isLeft = true;
            } else {
                cur = cur.right;
                isLeft = false;
            }
        }

        if (cur == null) {
            return root;
        }

        TreeNode node = cur.right == null ? cur.left : cur.right;
        if (isLeft) {
            pre.left = node;
        } else {
            pre.right = node;
        }

        if (cur.right == null || cur.left == null) {
            return head.left;
        }

        TreeNode removeL = cur.left;
        cur = cur.right;
        while (cur.left != null) {
            cur = cur.left;
        }

        cur.left = removeL;

        return head.left;
    }

    // 递归
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            }

            if (root.left == null) {
                return root.right;
            }

            if (root.right == null) {
                return root.left;
            }

            TreeNode cur = root.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            cur.left = root.left;

            return root.right;
        }

        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }

        return root;
    }

    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 669. 修剪二叉搜索树
     * @Date 17:01 2026/5/9
     * @Param [root, low, high]
     **/
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return root;
        }

        TreeNode n_left = trimBST(root.left, low, high);
        TreeNode n_right = trimBST(root.right, low, high);

        if (root.val >= low && root.val <= high) {
            root.left = n_left;
            root.right = n_right;

            return root;
        }

        if (n_left == null && n_right == null) {
            return null;
        }

        if (n_left == null) {
            return n_right;
        }
        if (n_right == null) {
            return n_left;
        }

        TreeNode cur = n_right;
        while (cur.left != null) {
            cur = cur.left;
        }

        cur.left = n_left;

        return n_right;

    }

    public TreeNode trimBst(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }

        if (root.val < low) {
            return trimBst(root.right, low, high);
        }
        if (root.val > high) {
            return trimBst(root.left, low, high);
        }

        root.left = trimBst(root.left, low, high);
        root.right = trimBst(root.right, low, high);
        return root;
    }

    // 迭代
    public TreeNode trimBSTII(TreeNode root, int low, int high) {
        while (root != null && (root.val < low || root.val > high)) {
            if (root.val < low) {
                root = root.right;
            } else {
                root = root.left;
            }
        }

        if (root == null) {
            return root;
        }

        // 此时 root 在 [low, high] 范围内.
        TreeNode cur = root;
        while (cur != null) {
            while (cur.left != null && cur.left.val < low) {
                cur.left = cur.left.right;
            }

            cur = cur.left;
        }

        cur = root;
        while (cur != null) {
            while (cur.right != null && cur.right.val > high) {
                cur.right = cur.right.left;
            }

            cur = cur.right;
        }

        return root;
    }

    /**
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @Author 强仔不强
     * @Description 102. 二叉树的层序遍历
     * @Date 21:16 2026/5/9
     * @Param [root]
     **/
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();

            while (size > 0) {
                TreeNode node = queue.poll();
                size--;

                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            ans.add(list);
        }

        return ans;
    }

    public List<List<Integer>> levelOrderII(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        levelDfs(root, 0, ans);

        return ans;
    }

    public void levelDfs(TreeNode root, int depth, List<List<Integer>> ans) {
        if (root == null) {
            return;
        }

        if (ans.size() < depth + 1) {
            ans.add(new ArrayList<>());
        }

        ans.get(depth).add(root.val);
        levelDfs(root.left, depth + 1, ans);
        levelDfs(root.right, depth + 1, ans);
    }

    /**
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @Author 强仔不强
     * @Description 103. 二叉树的锯齿形层序遍历
     * @Date 21:43 2026/5/9
     * @Param [root]
     **/
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean outUp = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();

            while (size > 0) {
                TreeNode node = null;
                if (outUp) {
                    node = queue.pollFirst();
                    if (node.left != null) {
                        queue.offerLast(node.left);
                    }
                    if (node.right != null) {
                        queue.offerLast(node.right);
                    }

                } else {
                    node = queue.pollLast();

                    if (node.right != null) {
                        queue.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        queue.offerFirst(node.left);
                    }
                }

                size--;
                list.add(node.val);
            }

            ans.add(list);
            outUp = !outUp;
        }

        return ans;
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @Author 强仔不强
     * @Description 199. 二叉树的右视图
     * @Date 15:51 2026/5/10
     * @Param [root]
     **/
    public List<Integer> rightSideViewVI(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        rightDfs(root, ans, 1);

        return ans;
    }

    public void rightDfs(TreeNode root, List<Integer> ans, int depth) {
        if (root == null) {
            return;
        }

        if (ans.size() < depth) {
            ans.add(root.val);
        }

        rightDfs(root.right, ans, depth + 1);
        rightDfs(root.left, ans, depth + 1);
    }

    public List<Integer> rightSideViewIV(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                size--;

                if (size == 1) {
                    ans.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return ans;
    }

    /**
     * @Author 强仔不强
     * @Description 513. 找树左下角的值
     * @Date 15:51 2026/5/10
     * @Param [root]
     * @return int
     **/
    int answer = 0;
    int maxDep = -1;

    public int findBottomLeftValue(TreeNode root) {
        findDLVDfs(root, 0);
        return answer;
    }

    public void findDLVDfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }

        if (depth > maxDep) {
            answer = root.val;
            maxDep = depth;
        }

        findDLVDfs(root.left, depth + 1);
        findDLVDfs(root.right, depth + 1);
    }

    public int findBottomLeftValueII(TreeNode root) {
        int[] ret = findBLVDfs(root, 1);
        return ret[0];
    }

    public int[] findBLVDfs(TreeNode root, int depth) {
        if (root == null) {
            return new int[]{0, 0};
        }

        if (root.left == null && root.right == null) {
            return new int[]{root.val, depth};
        }

        int[] n_left = findBLVDfs(root.left, depth + 1);
        int[] n_right = findBLVDfs(root.right, depth + 1);

        if (n_right[1] > n_left[1]) {
            return n_right;
        }

        return n_left;
    }

    public int findBottomLeftValueIII(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int ret = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean book = true;

            while (size > 0) {
                TreeNode node = queue.poll();
                size--;

                if (book) {
                    ret = node.val;
                    book = false;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return ret;
    }

    // 从右到左
    public int findBottomLeftValueIV(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.left != null) {
                queue.offer(root.left);
            }

            if (root.right != null) {
                queue.offer(root.right);
            }
        }

        return root.val;
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @Author 强仔不强
     * @Description 515. 在每个树行中找最大值
     * @Date 17:18 2026/5/10
     * @Param [root]
     **/
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        largestDfs(root, 0, ans);
        return ans;
    }

    public void largestDfs(TreeNode root, int depth, List<Integer> ans) {
        if (root == null) {
            return;
        }

        if (ans.size() < depth + 1) {
            ans.add(root.val);
        } else {
            if (ans.get(depth) < root.val) {
                ans.set(depth, root.val);
            }
        }

        largestDfs(root.left, depth + 1, ans);
        largestDfs(root.right, depth + 1, ans);
    }

    public List<Integer> largestValuesII(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if (root == null) {
            return ans;
        }
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            long max = Long.MIN_VALUE;

            while (size > 0) {
                TreeNode node = queue.poll();
                size--;

                max = Math.max(node.val, max);
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            ans.add((int) max);
        }

        return ans;
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @Author 强仔不强
     * @Description 637. 二叉树的层平均值
     * @Date 17:30 2026/5/10
     * @Param [root]
     **/
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int cnt = size;
            double sum = 0;

            while (size > 0) {
                TreeNode node = queue.poll();
                size--;

                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            ans.add(sum / cnt);
        }

        return ans;
    }

    /**
     * @return void
     * @Author 强仔不强
     * @Description 114. 二叉树展开为链表
     * @Date 17:38 2026/5/10
     * @Param [root]
     **/
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten(root.left);
        flatten(root.right);

        if (root.left == null) {
            return;
        }

        if (root.right != null) {
            TreeNode cur = root.left;
            while (cur.right != null) {
                cur = cur.right;
            }
            cur.right = root.right;
        }

        root.right = root.left;
        root.left = null;
    }

    /**
     * @Author 强仔不强
     * @Description 1367. 二叉树中的链表
     * @Date 20:46 2026/5/10
     * @Param [head, root]
     * @return boolean
     **/
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 双递归.
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }

        return isSPDfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    public boolean isSPDfs(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }

        if (root == null) {
            return false;
        }

        if (root.val != head.val) {
            return false;
        }

        return isSPDfs(head.next, root.left) || isSPDfs(head.next, root.right);
    }

    // 单递归.
    private ListNode h;

    public boolean isSubPathII(ListNode head, TreeNode root) {
        this.h = head;
        return isPDfs(h, root);
    }

    public boolean isPDfs(ListNode node, TreeNode root) {
        if (node == null) {
            return true;
        }

        if (root == null) {
            return false;
        }

        if (node.val == root.val) {
            boolean l = isPDfs(node.next, root.left);
            if (l) {
                return true;
            }

            boolean r = isPDfs(node.next, root.right);
            if (r) {
                return true;
            }
        }

        if (node != h) {
            return false;
        }

        return isPDfs(h, root.left) || isPDfs(h, root.right);
    }

    /**
     * @return BinaryTree.TreeNode
     * @Author 强仔不强
     * @Description 109. 有序链表转换二叉搜索树
     * @Date 17:43 2026/5/11
     * @Param [head]
     **/
    // 有序链表转有序数组.
    public TreeNode sortedListToBSTII(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        return creat(list, 0, list.size() - 1);
    }

    public TreeNode creat(List<Integer> list, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(list.get(mid));

        root.left = creat(list, left, mid - 1);
        root.right = creat(list, mid + 1, right);

        return root;
    }

    // 快慢指针 找链表中间节点.
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode fast = head;
        ListNode low = head;
        ListNode preLow = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            preLow = low;
            low = low.next;
        }

        TreeNode root = new TreeNode(low.val);

        if (preLow != null) {
            preLow.next = null;
            root.left = sortedListToBST(head);
        }
        root.right = sortedListToBST(low.next);

        return root;
    }

    ListNode cur;

    public TreeNode sortedListToBSTIII(ListNode head) {
        cur = head;

        int size = 0;
        ListNode node = head;
        while (node != null) {
            size++;
            node = node.next;
        }

        return midDfs(0, size - 1);
    }

    public TreeNode midDfs(int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;

        TreeNode root = new TreeNode();
        root.left = midDfs(left, mid - 1);
        root.val = cur.val;
        cur = cur.next;
        root.right = midDfs(mid + 1, right);

        return root;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @Author 强仔不强
     * @Description 589. N 叉树的前序遍历
     * @Date 14:45 2026/5/12
     * @Param [root]
     **/
    // 递归
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        preDfs(root, ans);

        return ans;
    }

    public void preDfs(Node root, List<Integer> ans) {
        if (root == null) {
            return;
        }

        ans.add(root.val);
        for (Node child : root.children) {
            preDfs(child, ans);
        }
    }

    // 二叉树 前序遍历 迭代写法
    public List<Integer> preorderBT(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(node.val);

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return ans;
    }

    public List<Integer> preorderBTIII(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                ans.add(root.val);
                stack.push(root);

                root = root.left;
            } else {
                root = stack.pop().right;
            }
        }

        return ans;
    }


    public List<Integer> preorderBTII(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                ans.add(root.val);

                if (root.right != null) {
                    stack.push(root.right);
                }
                root = root.left;
            } else {
                root = stack.pop();
            }
        }

        return ans;
    }

    // 迭代
    public List<Integer> preorderII(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            ans.add(node.val);

            List<Node> list = node.children;
            if (list != null) {
                int size = list.size();
                for (int i = size - 1; i >= 0; i--) {
                    stack.push(list.get(i));
                }
            }
        }

        return ans;
    }

    // 二叉树的后序遍历
    public List<Integer> postorderII(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.peek();
                if (node.right == null || pre == node.right) {
                    ans.add(node.val);
                    pre = stack.pop();
                } else {
                    root = node.right;
                }
            }
        }
        return ans;
    }


    public List<Integer> postorderIII(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        TreeNode pre = new TreeNode();
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if ((node.left == null && node.right == null) ||
                    pre == node.right || pre == node.left) {
                ans.add(node.val);
                pre = stack.pop();
                continue;
            }

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return ans;
    }

    // 迭代
    public List<Integer> postorderIV(Node root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        Node pre = null;
        while (!stack.isEmpty()){
            Node node = stack.peek();
            List<Node> list = node.children;
            // 如果 root 没有子树, 那么 list.size == 0;
            if(list.size() == 0 || pre == list.get(list.size() - 1)){
                ans.add(node.val);
                pre = stack.pop();
                continue;
            }

            for (int i = list.size() - 1; i >= 0; i--){
                stack.push(list.get(i));
            }

        }

        return ans;
    }


    /**
     * @Author 强仔不强
     * @Description 590. N 叉树的后序遍历
     * @Date 14:53 2026/5/12
     * @Param [root]
     * @return java.util.List<java.lang.Integer>
     **/
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        postDfs(root, ans);

        return ans;
    }

    public void postDfs(Node root, List<Integer> ans){
        if(root == null){
            return;
        }

        for (Node child : root.children){
            postDfs(child, ans);
        }
        ans.add(root.val);
    }
    
    /**
     * @Author 强仔不强
     * @Description 559. N 叉树的最大深度
     * @Date 20:14 2026/5/12
     * @Param [root]
     * @return int
     **/
    public int maxDepthN(Node root) {
        // 此判断条件 仅针对整棵树的根节点.
        if(root == null){
            return 0;
        }

        int max = 0;
        for (Node node : root.children){
            int val = maxDepthN(node);
            max = Math.max(max, val);
        }

        return max + 1;
    }

    int maxN = 0;
    public int maxDepth(Node root) {
        if(root == null){
            return 0;
        }
        maxDNDfs(root, 1);
        return maxN;
    }

    public void maxDNDfs(Node root, int depth){
        maxN =  Math.max(maxN, depth);
        for (Node node : root.children){
            maxDNDfs(node, depth + 1);
        }
    }
    
    
    /**
     * @Author 强仔不强
     * @Description 429. N 叉树的层序遍历
     * @Date 20:35 2026/5/12
     * @Param [root]
     * @return java.util.List<java.util.List<java.lang.Integer>>
     **/
    // BFS
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();

            while (size > 0){
                Node node = queue.poll();
                size--;
                list.add(node.val);

                for (Node n : node.children){
                    queue.offer(n);
                }
            }

            ans.add(list);
        }

        return ans;
    }

    public List<List<Integer>> levelOrderIII(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }

        lDDfs(root, ans, 0);
        return ans;
    }

    public void lDDfs(Node root, List<List<Integer>> ans, int depth){
        if(ans.size() < depth + 1){
            ans.add(new ArrayList<>());
        }

        List<Integer> list = ans.get(depth);
        list.add(root.val);

        for (Node node : root.children){
            lDDfs(node, ans, depth + 1);
        }
    }
    
    /**
     * @Author 强仔不强
     * @Description 987. 二叉树的垂序遍历
     * @Date 14:44 2026/5/13
     * @Param [root]
     * @return java.util.List<java.util.List<java.lang.Integer>>
     **/
    // 可以不用 Pair, 用 int[]（长度为 2）也可以.
    class Pair{
        int row;
        int val;

        public Pair(int row, int val){
            this.row = row;
            this.val = val;
        }
    }
    int minCol = Integer.MAX_VALUE;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, List<Pair>> map = new HashMap<>();
        vTDfs(root, map, 0, 0);


        List<List<Integer>> ans = new ArrayList<>();
        for (int i = minCol; i < minCol + map.size(); i++) {
            List<Pair> l1 = map.get(i);
            l1.sort(new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    if(o1.row == o2.row){
                        return o1.val - o2.val;
                    }
                    return o1.row - o2.row;
                }
            });

            List<Integer> l2 = new ArrayList<>();
            for (Pair p : l1){
                l2.add(p.val);
            }

            ans.add(l2);
        }

        return ans;
    }

    public void vTDfs(TreeNode root, Map<Integer, List<Pair>> map, int row, int col){
        if(root == null){
            return;
        }

        Pair p = new Pair(row, root.val);
        List<Pair> list = map.getOrDefault(col, new ArrayList<>());
        list.add(p);
        map.put(col, list);

        minCol = Math.min(minCol, col);

        vTDfs(root.left, map, row + 1, col - 1);
        vTDfs(root.right, map, row + 1, col + 1);
    }

    /**
     * @Author 强仔不强
     * @Description 655. 输出二叉树
     * @Date 16:26 2026/5/13
     * @Param [root]
     * @return java.util.List<java.util.List<java.lang.String>>
     **/
    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root) - 1;
        int n = (int) Math.pow(2, height + 1) - 1;

        List<List<String>> ans = new ArrayList<>(height);
        for (int i = 0; i < height + 1; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                list.add("");
            }
            ans.add(list);
        }

        fill(root, ans, 0, (n - 1) / 2, height);
        return ans;
    }

    public int getHeight(TreeNode root){
        if(root == null){
            return 0;
        }

        int lh = getHeight(root.left);
        int rh = getHeight(root.right);

        return Math.max(lh, rh) + 1;
    }

    public void fill(TreeNode root, List<List<String>> ans, int r, int c, int height){
        if(root == null){
            return;
        }

        String s = Integer.toString(root.val);
        ans.get(r).set(c, s);

        fill(root.left, ans, r + 1, c - (int) Math.pow(2, (height - r - 1)), height);
        fill(root.right, ans, r + 1, c + (int) Math.pow(2, (height - r - 1)), height);
    }

    /**
     * @Author 强仔不强
     * @Description 2368. 受限条件下可到达节点的数目
     * @Date 21:28 2026/5/13
     * @Param [n, edges, restricted]
     * @return int
     **/
    public static int reachableNodes(int n, int[][] edges, int[] restricted) {
        Set<Integer> set = new HashSet<>();
        for (int val : restricted){
            set.add(val);
        }

        List<Integer>[] group = new List[n];
        for (int i = 0; i < n; i++) {
            group[i] = new ArrayList<>();
        }


        for (int[] a : edges){
            int x = a[0];
            int y = a[1];

            if(set.contains(x) || set.contains(y)){
                continue;
            }

            group[x].add(y);
            group[y].add(x);
        }

        return reachDfs(group, 0, -1);
    }

    public static int reachDfs(List<Integer>[] group, int cur, int pre){
        int cnt = 1;
        for (int val : group[cur]){
            if(val == pre){
                continue;
            }

            cnt += reachDfs(group, val, cur);
        }

        return cnt;
    }


    public static void main(String[] args) {
        System.out.println("hello, world!");
    }

}



class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;

      TreeNode() {
      }

      TreeNode(int val) {
          this.val = val;
      }

      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

class FindElements {
    Set<Integer> set = new HashSet<>();

    public FindElements(TreeNode root) {
        findE(root, this.set, 0);
    }

    public void findE(TreeNode root, Set<Integer> set, int val){
        if(root == null){
            return;
        }

        set.add(val);
        findE(root.left, set, val * 2 + 1);
        findE(root.right, set, val * 2 + 2);
    }

    public boolean find(int target) {
        return set.contains(target);
    }
}



class Web{
    /**
     * @Author 强仔不强
     * @Description 200. 岛屿数量
     * @Date 15:59 2026/4/11
     * @Param [grid]
     * @return int
     **/
    public static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // DFS 带标记数组.
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // book[i][j] = true 表示此陆地已经走过了
        // 也可以将 grid[i][j] 由 '1' 改为 '2' 表示此陆地已经走过了. 或者直接将 grid[i][j] 由 '1' 改成 '0'.
        boolean[][] book = new boolean[m][n];
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == '1' && !book[i][j]){
                    ans++;
                    islandDfs(grid, book, i, j, m, n);
                }
            }
        }

        return ans;
    }

    public void islandDfs(char[][] grid, boolean[][] book, int i, int j, int m, int n){
        if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0' || book[i][j]){
            return;
        }

        book[i][j] = true;
        for (int[] dir : DIRS){
            int x = i + dir[0];
            int y = j + dir[1];
            islandDfs(grid, book, x, y, m, n);
        }
    }

    // DFS 直接修改 grid
    public int numIslandsII(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == '1'){
                    ans++;
                    landDfs(grid, i, j, m, n);
                }
            }
        }

        return ans;
    }

    public void landDfs(char[][] grid, int i, int j, int m, int n){
        if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1'){
            return;
        }

        grid[i][j] = '2';
        for (int[] dir : DIRS){
            int x = i + dir[0];
            int y = j + dir[1];
            landDfs(grid, x, y, m, n);
        }
    }

    // BFS
    class Pos{
        int x;
        int y;

        public Pos(int i, int j){
            this.x = i;
            this.y = j;
        }
    }
    public int numIslandsIII(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == '1'){
                    ans++;
                    islandsBfs(grid, i, j);
                }
            }
        }

        return ans;
    }
    public void islandsBfs(char[][] grid, int i, int j){
        int m = grid.length;
        int n = grid[0].length;

        Queue<Pos> queue = new LinkedList<>();
        Pos pos = new Pos(i, j);
        queue.offer(pos);
        grid[i][j] = '0';

        while (!queue.isEmpty()){
            Pos p = queue.poll();

            for (int[] dir : DIRS){
                int x = p.x + dir[0];
                int y = p.y + dir[1];

                if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != '1'){
                    continue;
                }

                queue.offer(new Pos(x, y));
                grid[x][y] = '0';
            }
        }
    }

    // 并查集
    static class UnionFind{
        int[] p;
        int size = 0;

        public UnionFind(int n){
            p = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }

            size = n;
        }

        public int find(int x){
            if(p[x] != x){
                p[x] = find(p[x]);
            }

            return p[x];
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);

            if(rootX != rootY) {
                p[rootX] = rootY;
                size--;
            }
        }
    }

    public static int numIslandsIV(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        UnionFind uf = new UnionFind(m * n);
        int cntZero = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == '0'){
                    cntZero++;
                    continue;
                }

                for (int[] dir : DIRS){
                    int x = i + dir[0];
                    int y = j + dir[1];

                    if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '0'){
                        continue;
                    }

                    uf.union(i * n + j, x * n + y);
                }
            }
        }

        return uf.size - cntZero;
    }
}