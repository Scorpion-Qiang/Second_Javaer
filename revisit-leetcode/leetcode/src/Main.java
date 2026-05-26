import java.util.*;

public class Main {
    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 1. 两数之和
     * @Date 20:35 2025/5/31
     * @Param [nums, target]
     **/
    // 数组排序 + 双指针
    public static int[] twoSum(int[] nums, int target) {
        int[] newNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int val = nums[left] + nums[right];
            if (val == target) {
                break;
            }
            if (val < target) {
                left++;
            } else {
                right--;
            }
        }

        int[] ret = {-1, -1};
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[left] == newNums[i] || nums[right] == newNums[i]) {
                ret[j] = i;
                j++;
            }
        }

        return ret;
    }

    // map
    public static int[] two_Sum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        int[] ret = {-1, -1};
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                ret[0] = i;
                ret[1] = map.get(target - nums[i]);
                break;
            }
            map.put(nums[i], i);
        }
        return ret;
    }

    // 双重 for 循环
    public static int[] two__Sum(int[] nums, int target) {
        int[] ret = {-1, -1};
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    ret[0] = i;
                    ret[1] = j;
                }
            }
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1456. 定长子串中元音的最大数目
     * @Date 02:05 2025/6/1
     * @Param [s, k]
     **/
    public static int maxVowels(String s, int k) {
        char[] chars = {'a', 'e', 'i', 'o', 'u'};
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            set.add(chars[i]);
        }

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        int num = 0;


        while (end < k) {
            if (set.contains(s.charAt(end))) {
                num++;
            }
            end++;
        }


        int ret = num;
        while (end < s.length()) {
            if (set.contains(s.charAt(end))) {
                num++;
            }
            if (set.contains(s.charAt(start))) {
                num--;
            }
            ret = Math.max(ret, num);
            end++;
            start++;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1456. 定长子串中元音的最大数目
     * @Date 19:36 2025/6/2
     * @Param [s, k]
     **/
    public static int max_Vowels(String s, int k) {
        char[] chars = {'a', 'e', 'i', 'o', 'u'};
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            set.add(chars[i]);
        }

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        int num = 0;

        int ret = 0;
        while (end < s.length()) {
            if (set.contains(s.charAt(end))) {
                num++;
            }
            end++;

            if (end <= k) {
                ret = num;
                continue;
            }

            if (set.contains(s.charAt(start))) {
                num--;
            }
            start++;

            ret = Math.max(ret, num);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description
     * @Date 20:16 2025/6/2
     * @Param [s, k]
     **/
    public static int max__Vowels(String s, int k) {
        char[] chars = {'a', 'e', 'i', 'o', 'u'};
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            set.add(chars[i]);
        }

        // 滑动窗口 [i - k, i)
        int i = 0;
        int num = 0;
        int ret = 0;

        while (i < s.length()) {
            if (set.contains(s.charAt(i))) {
                num++;
            }
            i++;

            if (i == k) {
                ret = num;
            }
            if (i <= k) {
                continue;
            }

            if (set.contains(chars[i - 1 - k])) {
                num--;
            }

            ret = Math.max(ret, num);
        }

        return ret;
    }


    /**
     * @return double
     * @Author 强仔不强
     * @Description 643. 子数组最大平均数 I
     * @Date 02:39 2025/6/1
     * @Param [nums, k]
     **/
    public static double findMaxAverage(int[] nums, int k) {
        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;

        int sum = 0;
        while (end < k) {
            sum += nums[end];
            end++;
        }

        int ret = sum;
        while (end < nums.length) {
            sum += nums[end] - nums[start];
            ret = Math.max(ret, sum);
            end++;
            start++;
        }

        return ret / (k * 1.0);
    }

    public static double find_MaxAverage(int[] nums, int k) {
        // 滑动窗口 [i - k, i)
        int i = 0;
        int sum = 0;
        int ret = Integer.MIN_VALUE;

        while (i < nums.length) {
            sum += nums[i];
            i++;

            if (i == k) {
                ret = sum;
            }
            if (i <= k) {
                continue;
            }

            sum -= nums[i - 1 - k];
            ret = Math.max(ret, sum);
        }

        return ret / (k * 1.0);
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1343. 大小为 K 且平均值大于等于阈值的子数组数目
     * @Date 04:19 2025/6/1
     * @Param [arr, k, threshold]
     **/
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int ret = 0;

        threshold *= k;
        int start = 0;
        int end = 0;

        int sum = 0;
        while (end < k) {
            sum += arr[end];
            end++;
        }
        if (sum >= threshold) {
            ret++;
        }

        while (end < arr.length) {
            sum += arr[end] - arr[start];
            if (sum >= threshold) {
                ret++;
            }
            start++;
            end++;
        }

        return ret;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 2090. 半径为 k 的子数组平均值
     * @Date 04:29 2025/6/1
     * @Param [nums, k]
     **/
    public int[] getAverages(int[] nums, int k) {

        int[] ret = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ret[i] = -1;
        }

        // 滑动窗口长度
        int len = k * 2 + 1;
        if (len > nums.length) {
            return ret;
        }

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        long sum = 0;

        while (end < len) {
            sum += nums[end];
            end++;
        }
        ret[k] = (int) (sum / len);

        while (end < nums.length) {
            sum += nums[end] - nums[start];
            k++;
            ret[k] = (int) (sum / len);
            end++;
            start++;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2379. 得到 K 个黑块的最少涂色次数
     * @Date 05:10 2025/6/1
     * @Param [blocks, k]
     **/
    public static int minimumRecolors(String blocks, int k) {
        char[] chars = blocks.toCharArray();

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        int num = 0;

        while (end < k) {
            if (chars[end] == 'W') {
                num++;
            }
            end++;
        }
        // 此时窗口为 [0, k)

        int ret = num;
        while (end < chars.length) {
            if (chars[end] == 'W') {
                num++;
            }
            if (chars[start] == 'W') {
                num--;
            }
            ret = Math.min(ret, num);
            end++;
            start++;
        }

        return ret;
    }

    public static int minimum_Recolors(String blocks, int k) {
        char[] chars = blocks.toCharArray();

        // 滑动窗口 [end - k, end)
        int end = 0;
        int num = 0;
        int ret = Integer.MIN_VALUE;

        while (end < chars.length) {
            if (chars[end] == 'W') {
                num++;
            }
            end++;
            if (end == k) {
                ret = num;
            }
            if (end <= k) {
                continue;
            }

            if (chars[end - 1 - k] == 'W') {
                num--;
            }
            ret = Math.min(ret, num);
        }

        return ret;
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description 2841. 几乎唯一子数组的最大和
     * @Date 06:47 2025/6/1
     * @Param [nums, m, k]  nums[i] > 0
     **/
    public static long maxSum(List<Integer> nums, int m, int k) {
        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        // 最终结果: 几乎唯一子数组的最大和
        long ret = 0;
        // 记录当前窗口的 元素（key）及其出现次数（value）
        HashMap<Integer, Integer> map = new HashMap<>();
        // 当前滑动窗口中各元素的和
        long sum = 0;
        while (end < k) {
            int elment = nums.get(end);
            sum += elment;
            end++;

            int value = map.getOrDefault(elment, 0);
            map.put(elment, value + 1);
        }
        // 判断当前窗口是不是几乎唯一子数组
        if (map.size() >= m) {
            ret = sum;
        }


        while (end < nums.size()) {
            int startElement = nums.get(start);
            int endElemnet = nums.get(end);
            // 更新 sum
            sum += endElemnet - startElement;

            // 更新 map
            int startValue = map.get(startElement);
            if (startValue == 1) {
                map.remove(startElement);
            } else {
                map.put(startElement, startValue - 1);
            }
            int endValue = map.getOrDefault(endElemnet, 0);
            map.put(endElemnet, endValue + 1);
            // 只有当前窗口为 几乎唯一子数组，才有资格参与到最终结果中
            if (map.size() >= m) {
                ret = Math.max(ret, sum);
            }

            end++;
            start++;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1423. 可获得的最大点数
     * @Date 08:29 2025/6/1
     * @Param [cardPoints, k]
     **/
    // [0, k)往前滑
    public int maxScore(int[] cardPoints, int k) {
        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;

        int sum = 0;
        while (end < k) {
            sum += cardPoints[end];
            end++;
        }
        // 窗口为 [0, k)

        int ret = sum;

        while (end > 0) {
            if (start == 0) {
                start = cardPoints.length - 1;
            } else {
                start--;
            }
            end--;
            sum += cardPoints[start] - cardPoints[end];
            ret = Math.max(ret, sum);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1423. 可获得的最大点数
     * @Date 12:29 2025/6/4
     * @Param [cardPoints, k]
     **/
    // [n - k, 0)往后滑
    public int max_Score(int[] cardPoints, int k) {
        // 滑动窗口 [start, end)
        int start = cardPoints.length - k;
        int end = start;

        int sum = 0;
        while (end < cardPoints.length) {
            sum += cardPoints[end];
            end++;
        }
        end = 0;
        // 窗口为 [cardPoints.length - k, cardPoints.length) ——> [cardPoints.length - k, 0)

        int ret = sum;

        while (start < cardPoints.length) {
            sum += cardPoints[end] - cardPoints[start];
            ret = Math.max(ret, sum);
            end++;
            start++;
        }

        return ret;
    }

    // 长度为 n - k 的滑动窗口
    public int max__Score(int[] cardPoints, int k) {
        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;

        // 目前窗口元素和
        int sum = 0;


        while (end < cardPoints.length - k) {
            sum += cardPoints[end];
            end++;
        }
        // 窗口元素和的最小值
        int ret = sum;
        // 整个数组元素和
        int allSum = sum;

        while (end < cardPoints.length) {
            sum += cardPoints[end] - cardPoints[start];
            ret = Math.min(ret, sum);
            allSum += cardPoints[end];
            end++;
            start++;
        }

        return allSum - ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1052. 爱生气的书店老板
     * @Date 15:24 2025/6/1
     * @Param [customers, grumpy, minutes]
     **/
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        // 不设置冷静时段，满意顾客的总数
        int ret = 0;
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                ret += customers[i];
            }
        }

        // 滑动窗口 [start, end) 表示冷静时段
        // 找到挽回顾客数量的最大值
        int start = 0;
        int end = 0;

        // 某个冷静时段，挽回顾客的数量
        int sum = 0;

        while (end < minutes) {
            if (grumpy[end] == 1) {
                sum += customers[end];
            }
            end++;
        }
        // 目前窗口为 [0, minutes)
        // 目前挽回顾客数量的最大值
        int maxValue = sum;

        while (end < customers.length) {
            if (grumpy[end] == 1) {
                sum += customers[end];
            }
            if (grumpy[start] == 1) {
                sum -= customers[start];
            }

            maxValue = Math.max(sum, maxValue);
            end++;
            start++;
        }
        return ret + maxValue;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 1652. 拆炸弹
     * @Date 15:43 2025/6/1
     * @Param [code, k]
     **/
    public int[] decrypt(int[] code, int k) {
        int[] ret = new int[code.length];
        if (k == 0) {
            return ret;
        }

        if (k > 0) {
            // 滑动窗口 [start, end)
            int start = 1;
            int end = 1;
            int sum = 0;
            while (end < k + 1) {
                sum += code[end];
                end++;
            }
            ret[0] = sum;
            if (end == code.length) {
                end = 0;
            }
            // 窗口为 [1, k + 1)

            // 窗口向后滑动
            for (int i = 1; i < code.length; i++) {
                sum += code[end] - code[start];
                ret[i] = sum;
                end++;
                start++;
                if (end == code.length) {
                    end = 0;
                }
                if (start == code.length) {
                    start = 0;
                }
            }
        } else {

            // 滑动窗口 (left, right]
            int right = code.length - 2;
            int left = code.length - 2;
            int sum = 0;
            while (left > right + k) {
                sum += code[left];
                left--;
            }
            ret[code.length - 1] = sum;
            if (left == -1) {
                left = code.length - 1;
            }
            // 窗口为 (code.length - 2 - k, code.length - 2]

            // 窗口向前滑动
            for (int i = code.length - 2; i >= 0; i--) {
                sum += code[left] - code[right];
                ret[i] = sum;
                left--;
                right--;
                if (left == -1) {
                    left = code.length - 1;
                }
                if (right == -1) {
                    right = code.length - 1;
                }
            }
        }
        return ret;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 1652. 拆炸弹
     * @Date 17:43 2025/6/4
     * @Param [code, k]
     **/
    public static int[] decrypt_(int[] code, int k) {
        int[] ret = new int[code.length];
        if (k == 0) {
            return ret;
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        if (k > 0) {
            // 滑动窗口 [start, end)
            start = 1;
            end = 1;
            while (end < k + 1) {
                sum += code[end];
                end++;
            }
            // 窗口为 [1, k + 1)
            ret[0] = sum;
            if (end == code.length) {
                end = 0;
            }

        } else {
            // 滑动窗口 [start, end)
            start = code.length + k;
            end = start;
            while (end < code.length) {
                sum += code[end];
                end++;
            }
            ret[0] = sum;
            end = 0;
            // 窗口为 [code.length - k, 0)
        }

        // 窗口滑动
        for (int i = 1; i < code.length; i++) {
            sum += code[end] - code[start];
            ret[i] = sum;
            end++;
            start++;
            if (end == code.length) {
                end = 0;
            }
            if (start == code.length) {
                start = 0;
            }
        }
        return ret;
    }


    /**
     * @return int
     * @Author 强仔不强
     * @Description 3439. 重新安排会议得到最多空余时间 I
     * @Date 19:12 2025/6/4
     * @Param [eventTime, k, startTime, endTime]
     **/
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        List<Integer> spare = new ArrayList<>();
        if (startTime[0] > 0) {
            spare.add(startTime[0]);
        }
        for (int i = 1; i < n; i++) {
            spare.add(startTime[i] - endTime[i - 1]);
        }
        if (endTime[n - 1] < eventTime) {
            spare.add(eventTime - endTime[n - 1]);
        }


        // 窗口长度 k + 1, 窗口为 [left, right)，第一个窗口为 [0, k + 1)
        int left = 0;
        int right = 0;
        int sum = 0;
        int ret = 0;

        // 窗口长度
        int len = Math.min(spare.size(), k + 1);
        while (right < spare.size()) {
            sum += spare.get(right);
            right++;

            if (right == len) {
                ret = sum;
            }
            if (right <= len) {
                continue;
            }

            sum -= spare.get(left);
            left++;

            ret = Math.max(ret, sum);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 3439. 重新安排会议得到最多空余时间 I   优化
     * @Date 09:04 2025/6/14
     * @Param [eventTime, k, startTime, endTime]
     **/
    public int max__FreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        // 会议数量
        int n = startTime.length;
        // 间隔时间段, 可以是 0
        int[] spare = new int[n + 1];
        spare[0] = startTime[0];
        for (int i = 1; i < n; i++) {
            spare[i] = startTime[i] - endTime[i - 1];
        }
        spare[n] = eventTime - endTime[n - 1];

        // 滑动窗口 [left, right)
        int left = 0;
        int right = 0;
        int sum = 0;
        int ret = 0;

        while (right < n + 1) {
            sum += spare[right];
            right++;

            if (right == k + 1) {
                ret = sum;
            }
            if (right <= k + 1) {
                continue;
            }

            sum -= spare[left];
            left++;

            ret = Math.max(ret, sum);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2134. 最少交换次数来组合所有的 1 II
     * @Date 22:18 2025/6/4
     * @Param [nums]
     **/
    public int minSwaps(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            }
        }

        if (count == nums.length) {
            return 0;
        }

        // 滑动窗口 [start, end), 第一个窗口为 [0, count), count < nums.length
        int start = 0;
        int end = 0;
        int value = 0;
        while (end < count) {
            if (nums[end] == 0) {
                value++;
            }
            end++;
        }
        int ret = value;

        while (start < nums.length - 1) {
            if (nums[end] == 0) {
                value++;
            }
            if (nums[start] == 0) {
                value--;
            }
            end = (end + 1) % nums.length;
            start++;

            ret = Math.min(ret, value);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1297. 子串的最大出现次数
     * @Date 22:49 2025/6/4
     * @Param [s, maxLetters, minSize, maxSize]
     **/
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<String, Integer> ret = new HashMap<>();
        // 滑动窗口长度 minSize, 为 [start, end), 第一个窗口为 [0, minSize)
        int start = 0;
        int end = 0;
        while (end < chars.length) {
            int value = map.getOrDefault(chars[end], 0);
            map.put(chars[end], value + 1);
            end++;

            if (end == minSize && map.size() <= maxLetters) {
                ret.put(new String(chars, start, minSize), 1);
            }
            if (end <= minSize) {
                continue;
            }

            int count = map.get(chars[start]);
            if (count == 1) {
                map.remove(chars[start]);
            } else {
                map.put(chars[start], count - 1);
            }
            start++;

            if (map.size() <= maxLetters) {
                int val = ret.getOrDefault(new String(chars, start, minSize), 0);
                ret.put(new String(chars, start, minSize), val + 1);
            }
        }

        int num = 0;
        for (Map.Entry<String, Integer> entry : ret.entrySet()) {
            num = Math.max(entry.getValue(), num);
        }

        return num;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 2653. 滑动子数组的美丽值
     * 一个大根堆
     * @Date 23:32 2025/6/4
     * @Param [nums, k, x]
     **/
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length;
        int[] ret = new int[n - k + 1];

        for (int i = 0; i < ret.length; i++) {
            // 窗口为 [i, i + k)
            ret[i] = isNagative(nums, i, i + k, x);
        }

        return ret;
    }

    public int isNagative(int[] nums, int start, int end, int x) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(x, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // 创建长度为 x 的大根堆, 大根堆中存储前 x 小的元素, 堆顶元素为第 x 小的元素.
        for (int i = start; i < end; i++) {
            if (i < start + x) {
                queue.offer(nums[i]);
            } else {
                if (nums[i] < queue.peek()) {
                    queue.poll();
                    queue.offer(nums[i]);
                }
            }
        }

        int ret = queue.peek();
        return Math.min(ret, 0);
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 2653. 滑动子数组的美丽值
     * 计数数组
     * @Date 18:20 2025/6/6
     * @Param [nums, k, x]
     **/
    public static int[] get_SubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length;
        int[] cnt = new int[101];
        int[] ret = new int[n - k + 1];

        // 记录窗口中负数个数
        int nagtive = 0;
        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        while (end < n) {
            if (nums[end] < 0) {
                nagtive++;
            }
            cnt[nums[end] + 50]++;
            end++;

            if (end == k) {
                System.out.println(Arrays.toString(cnt));
                ret[start] = isBeauty(cnt, x, nagtive);
            }
            if (end <= k) {
                continue;
            }

            if (nums[start] < 0) {
                nagtive--;
            }
            cnt[nums[start] + 50]--;
            start++;
            ret[start] = isBeauty(cnt, x, nagtive);
        }

        return ret;
    }

    public static int isBeauty(int[] cnt, int x, int nagtive) {
        if (nagtive < x) {
            return 0;
        }
        int i = 0;
        for (; i < cnt.length; i++) {
            x -= cnt[i];
            if (x <= 0) {
                break;
            }
        }
        return Math.min(i - 50, 0);
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 2653. 滑动子数组的美丽值
     * 大根堆 + 小根堆
     * @Date 18:46 2025/6/11
     * @Param [nums, k, x]
     **/
    public static int[] get__SubarrayBeauty(int[] nums, int k, int x) {
        PriorityQueue<Integer> bigQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        PriorityQueue<Integer> smallQueue = new PriorityQueue<>();


        int n = nums.length;
        int[] ret = new int[n - k + 1];

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;

        while (end < k) {
            if (end < x) {
                bigQueue.offer(nums[end]);
            } else {
                if (nums[end] < bigQueue.peek()) {
                    int val = bigQueue.poll();
                    bigQueue.offer(nums[end]);
                    smallQueue.offer(val);
                } else {
                    smallQueue.offer(nums[end]);
                }
            }
            end++;
        }
        ret[start] = Math.min(bigQueue.peek(), 0);


        while (end < n) {
            // 如果 k == x, 小根堆为空!!!
            if (smallQueue.isEmpty()) {
                bigQueue.remove(nums[start]);
                bigQueue.offer(nums[end]);
                start++;
                end++;
                ret[start] = Math.min(bigQueue.peek(), 0);
                continue;
            }


            if (nums[start] <= bigQueue.peek()) {
                bigQueue.remove(nums[start]);
                bigQueue.offer(smallQueue.poll());
            } else {
                smallQueue.remove(nums[start]);
            }
            start++;

            if (nums[end] < bigQueue.peek()) {
                int val = bigQueue.poll();
                bigQueue.offer(nums[end]);
                smallQueue.offer(val);
            } else {
                smallQueue.offer(nums[end]);
            }
            end++;

            ret[start] = Math.min(bigQueue.peek(), 0);
        }
        return ret;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 2653. 滑动子数组的美丽值
     * 大根堆 + 小根堆 + 延迟删除
     * @Date 18:45 2025/6/11
     * @Param [nums, k, x]
     **/
    public static int[] get___SubarrayBeauty(int[] nums, int k, int x) {
        PriorityQueue<Integer> bigQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        PriorityQueue<Integer> smallQueue = new PriorityQueue<>();
        HashMap<Integer, Integer> map = new HashMap<>();


        int n = nums.length;
        int[] ret = new int[n - k + 1];

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;

        while (end < k) {
            bigQueue.offer(nums[end]);
            end++;

            if (end > x) {
                smallQueue.offer(bigQueue.poll());
            }
        }
        ret[start] = Math.min(bigQueue.peek(), 0);

        while (end < n) {
            int val = map.getOrDefault(nums[start], 0);
            map.put(nums[start], val + 1);

            if (!smallQueue.isEmpty() && nums[start] <= bigQueue.peek()) {
                bigQueue.offer(smallQueue.poll());
            }
            start++;

            if (k == x) {
                bigQueue.offer(nums[end]);
            } else {
                if (nums[end] < bigQueue.peek()) {
                    bigQueue.offer(nums[end]);
                    smallQueue.offer(bigQueue.poll());
                } else {
                    smallQueue.offer(nums[end]);
                }
            }
            end++;

            while (map.getOrDefault(bigQueue.peek(), 0) > 0) {
                int p = bigQueue.poll();
                map.put(p, map.get(p) - 1);
            }
            while (!smallQueue.isEmpty() && map.getOrDefault(smallQueue.peek(), 0) > 0) {
                int p = smallQueue.poll();
                map.put(p, map.get(p) - 1);
            }

            ret[start] = Math.min(bigQueue.peek(), 0);
        }
        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1888. 使二进制字符串字符交替的最少反转次数
     * @Date 09:34 2025/6/5
     * @Param [s]
     **/
    public static int minFlips(String s) {
        String string = s + s;
        char[] chars = string.toCharArray();

        int ret = 0;
        int oneOdd = 0;
        int oneEven = 0;
        int zeroOdd = 0;
        int zeroEven = 0;

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;

        while (start < s.length()) {
            if (end % 2 == 0) {
                if (chars[end] == '0') {
                    zeroEven++;
                } else {
                    oneEven++;
                }
            } else {
                if (chars[end] == '0') {
                    zeroOdd++;
                } else {
                    oneOdd++;
                }
            }
            end++;

            if (end == s.length()) {
                ret = Math.min(zeroEven + oneOdd, zeroOdd + oneEven);
            }
            if (end <= s.length()) {
                continue;
            }

            if (start % 2 == 0) {
                if (chars[start] == '0') {
                    zeroEven--;
                } else {
                    oneEven--;
                }
            } else {
                if (chars[start] == '0') {
                    zeroOdd--;
                } else {
                    oneOdd--;
                }
            }
            start++;

            int value = Math.min(oneOdd + zeroEven, zeroOdd + oneEven);
            ret = Math.min(ret, value);
        }

        return ret;
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 567. 字符串的排列
     * @Date 17:01 2025/6/5
     * @Param [s1, s2]
     **/
    public boolean checkInclusion(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            int value = map.getOrDefault(chars1[i], 0);
            map.put(chars1[i], value + 1);
        }

        int start = 0;
        int end = 0;
        while (end < s2.length()) {
            int value = window.getOrDefault(chars2[end], 0);
            window.put(chars2[end], value + 1);
            end++;

            if (end == s1.length()) {
                if (same(map, window)) {
                    return true;
                }
            }
            if (end <= s1.length()) {
                continue;
            }


            int val = window.get(chars2[start]);
            if (val == 1) {
                window.remove(chars2[start]);
            } else {
                window.put(chars2[start], val - 1);
            }
            start++;


            if (same(map, window)) {
                return true;
            }

        }

        return false;
    }

    public boolean same(HashMap<Character, Integer> map1, HashMap<Character, Integer> map2) {
        if (map1.size() != map2.size()) {
            return false;
        }
        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            // (map2.getOrDefault(entry.getKey(), 0) != entry.getValue()) 有一个测试用例过不了
            if (!map2.getOrDefault(entry.getKey(), 0).equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }


    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 567. 字符串的排列
     * @Date 14:58 2025/6/14
     * @Param [s1, s2]
     **/
    public boolean check_Inclusion(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];

        for (int i = 0; i < chars1.length; i++) {
            cnt1[chars1[i] - 'a']++;
        }

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        while (end < chars2.length) {
            cnt2[chars2[end] - 'a']++;
            end++;

            if (end == chars1.length) {
                if (same(cnt1, cnt2)) {
                    return true;
                }
            }
            if (end <= chars1.length) {
                continue;
            }

            cnt2[chars2[start] - 'a']--;
            start++;

            if (same(cnt1, cnt2)) {
                return true;
            }
        }

        return false;
    }

    public boolean same(int[] cnt1, int[] cnt2) {
        for (int i = 0; i < cnt1.length; i++) {
            if (cnt1[i] != cnt2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @Author 强仔不强
     * @Description 438. 找到字符串中所有字母异位词
     * @Date 17:48 2025/6/5
     * @Param [s, p]
     **/
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        char[] chars1 = p.toCharArray();
        char[] chars2 = s.toCharArray();

        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        for (int i = 0; i < p.length(); i++) {
            int value = map.getOrDefault(chars1[i], 0);
            map.put(chars1[i], value + 1);
        }

        int start = 0;
        int end = 0;
        while (end < s.length()) {
            int value = window.getOrDefault(chars2[end], 0);
            window.put(chars2[end], value + 1);
            end++;

            if (end == p.length()) {
                if (same(map, window)) {
                    list.add(start);
                }
            }
            if (end <= p.length()) {
                continue;
            }


            int val = window.get(chars2[start]);
            if (val == 1) {
                window.remove(chars2[start]);
            } else {
                window.put(chars2[start], val - 1);
            }
            start++;


            if (same(map, window)) {
                list.add(start);
            }

        }

        return list;
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 2156. 查找给定哈希值的子串
     * @Date 18:47 2025/6/11
     * @Param [s, power, modulo, k, hashValue]
     **/
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        int hash = 0;
        char[] chars = s.toCharArray();

        // 滑动窗口 [start, end);
        int start = 0;
        int end = 0;
        while (end < chars.length) {
            int val = Math.min(end, k);
            hash += (int) ((chars[end] - 96) * Math.pow(power, val));
            end++;

            if (end == k) {
                if (hash % modulo == hashValue) {
                    return new String(chars, 0, k);
                }
            }
            if (end <= k) {
                continue;
            }
        }
        return null;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2269. 找到一个数字的 K 美丽值
     * @Date 16:21 2025/6/14
     * @Param [num, k]
     **/
    public int divisorSubstrings(int num, int k) {
        char[] chars = Integer.toString(num).toCharArray();

        int start = 0;
        int end = 0;
        int val = 0;
        int ret = 0;

        final int CNT = (int) Math.pow(10, k);
        while (end < chars.length) {
            val = val * 10 + (chars[end] - '0');
            end++;

            if (end == k) {
                if (val != 0 && num % val == 0) {
                    ret++;
                }
            }

            if (end <= k) {
                continue;
            }

            val -= (chars[start] - '0') * CNT;
            start++;

            if (val != 0 && num % val == 0) {
                ret++;
            }
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1984. 学生分数的最小差值
     * @Date 16:53 2025/6/14
     * @Param [nums, k]
     **/
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);

        int ret = Integer.MAX_VALUE;
        for (int i = k - 1; i < nums.length; i++) {
            ret = Math.min(ret, nums[i] - nums[i - k + 1]);
        }
        return ret;
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 1461. 检查一个字符串是否包含所有长度为 K 的二进制子串
     * @Date 17:00 2025/6/14
     * @Param [s, k]
     **/
    public boolean hasAllCodes(String s, int k) {
        Set<String> set = new HashSet<>();
        StringBuffer buffer = new StringBuffer();
        making(set, k, buffer);

        for (int i = 0; i <= s.length() - k; i++) {
            String string = s.substring(i, i + k);
            set.remove(string);
        }
        return set.isEmpty();
    }

    public static void making(Set<String> set, int k, StringBuffer buffer) {
        if (k == 0) {
            set.add(buffer.toString());
            return;
        }

        buffer.append('0');
        making(set, k - 1, buffer);
        buffer.deleteCharAt(buffer.length() - 1);

        buffer.append('1');
        making(set, k - 1, buffer);
        buffer.deleteCharAt(buffer.length() - 1);
    }


    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 1461. 检查一个字符串是否包含所有长度为 K 的二进制子串
     * @Date 18:41 2025/6/14
     * @Param [s, k]
     **/
    public boolean has_AllCodes(String s, int k) {
        char[] chars = s.toCharArray();

        final int VAl = (int) Math.pow(2, k);
        int[] arr = new int[VAl];

        for (int i = 0; i <= s.length() - k; i++) {
            String string = s.substring(i, i + k);
            arr[change(string, k)]++;
        }

        for (int i = 0; i < VAl; i++) {
            if (arr[i] == 0) {
                return false;
            }
        }

        return true;
    }

    public int change(String s, int k) {
        char[] chars = s.toCharArray();
        int val = 0;
        for (int i = 0; i < k; i++) {
            if (chars[i] == '1') {
                val += (int) Math.pow(2, k - 1 - i);
            }
        }
        return val;
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 1461. 检查一个字符串是否包含所有长度为 K 的二进制子串
     * @Date 22:54 2025/6/14
     * @Param [s, k]
     **/
    public boolean has__AllCodes(String s, int k) {
        final int CNT = (int) Math.pow(2, k);
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i <= s.length() - k; i++) {
            String string = s.substring(i, i + k);
            set.add(string);
        }

        return set.size() == CNT;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 3. 无重复字符的最长子串
     * @Date 23:06 2025/6/14
     * @Param [s]
     **/
    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        int ret = 0;

        while (end < chars.length) {
            if (set.contains(chars[end])) {
                set.remove(chars[start]);
                start++;
                continue;
            }
            set.add(chars[end]);
            end++;
            ret = Math.max(end - start, ret);
        }

        return ret;
    }

    public static int length_OfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int[] cnt = new int[128];

        int start = 0;
        int end = 0;
        int ret = 0;

        while (end < chars.length) {
            char c = chars[end];
            cnt[c]++;
            end++;

            // == 2
            while (cnt[c] > 1) {
                cnt[chars[start]]--;
                start++;
            }

            ret = Math.max(ret, end - start);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 3090. 每个字符最多出现两次的最长子字符串
     * @Date 10:24 2025/6/15
     * @Param [s]
     **/
    public int maximumLengthSubstring(String s) {
        char[] chars = s.toCharArray();
        // cnt 统计当前窗口中的字母
        int[] cnt = new int[128];

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        int ret = 0;

        while (end < chars.length) {
            if (cnt[chars[end]] == 2) {
                cnt[chars[start]]--;
                start++;
                continue;
            }
            cnt[chars[end]]++;
            end++;
            ret = Math.max(end - start, ret);
        }

        return ret;
    }


    /**
     * @return int
     * @Author 强仔不强
     * @Description 3090. 每个字符最多出现两次的最长子字符串
     * @Date 16:20 2025/6/16
     * @Param [s]
     **/
    public int maximum_LengthSubstring(String s) {
        char[] chars = s.toCharArray();
        int[] cnt = new int[128];

        int start = 0;
        int end = 0;
        int ret = 0;

        while (end < chars.length) {
            char c = chars[end];
            cnt[c]++;
            end++;

            // == 3
            while (cnt[c] > 2) {
                cnt[chars[start]]--;
                start++;
            }

            ret = Math.max(ret, end - start);
        }

        return ret;
    }


    /**
     * @return int
     * @Author 强仔不强
     * @Description 1493. 删掉一个元素以后全为 1 的最长子数组
     * 最多包含 1 个 0 的子数组的最大长度
     * @Date 10:36 2025/6/15
     * @Param [nums]
     **/
    public int longestSubarray(int[] nums) {
        int start = 0;
        int end = 0;
        // 判断当前窗口有没有 0
        boolean flg = false;
        int ret = 0;

        while (end < nums.length) {
            if (flg && nums[end] == 0) {
                if (nums[start] == 0) {
                    flg = false;
                }
                start++;
                continue;
            }

            if (nums[end] == 0) {
                flg = true;
            }
            end++;
            ret = Math.max(ret, end - start);
        }

        return ret - 1;
    }

    public int longest_Subarray(int[] nums) {
        int start = 0;
        int end = 0;
        // cnt 统计当前窗口 0 的个数.
        int cnt = 0;
        int ret = 0;

        while (end < nums.length) {
            if (nums[end] == 0) {
                cnt++;
            }
            end++;

            while (cnt > 1) {
                if (nums[start] == 0) {
                    cnt--;
                }
                start++;
            }

            ret = Math.max(ret, end - start);
        }
        return ret - 1;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1208. 尽可能使字符串相等
     * @Date 10:56 2025/6/15
     * @Param [s, t, maxCost]
     **/
    public int equalSubstring(String s, String t, int maxCost) {
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();

        int start = 0;
        int end = 0;
        int cost = 0;
        int ret = 0;

        while (end < charsT.length) {
            int val = Math.abs(charsT[end] - charsS[end]);
            if (cost + val > maxCost) {
                if (start == end) {
                    end++;
                } else {
                    int cnt = Math.abs(charsT[start] - charsS[start]);
                    cost -= cnt;
                }
                start++;
                continue;
            }

            cost += val;
            end++;
            ret = Math.max(end - start, ret);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1208. 尽可能使字符串相等
     * @Date 16:00 2025/6/16
     * @Param [s, t, maxCost]
     **/
    public int equal_Substring(String s, String t, int maxCost) {
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();

        int start = 0;
        int end = 0;
        int cost = 0;
        int ret = 0;

        while (end < charsT.length) {
            cost += Math.abs(charsS[end] - charsT[end]);
            end++;

            while (cost > maxCost) {
                cost -= Math.abs(charsS[start] - charsT[start]);
                start++;
            }

            ret = Math.max(ret, end - start);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 904. 水果成篮
     * 最多包含 2 种不同元素的最长子字符串.
     * @Date 11:13 2025/6/15
     * @Param [fruits]
     **/
    public int totalFruit(int[] fruits) {
        // map 统计当前窗口的元素出现次数.
        HashMap<Integer, Integer> map = new HashMap<>();

        // 滑动窗口 [start, end).
        int start = 0;
        int end = 0;
        int ret = 0;

        while (end < fruits.length) {
            if (map.size() == 2 && map.getOrDefault(fruits[end], 0) == 0) {
                int val = map.get(fruits[start]);
                map.put(fruits[start], val - 1);
                start++;
                continue;
            }

            int val = map.getOrDefault(fruits[end], 0);
            map.put(fruits[end], val + 1);
            end++;

            ret = Math.max(end - start, ret);
        }

        return ret;
    }

    public int total_Fruit(int[] fruits) {
        int n = fruits.length;
        int[] cnt = new int[n];
        int type = 0;

        // 滑动窗口 [start, end).
        int start = 0;
        int end = 0;
        int ret = 0;

        while (end < fruits.length) {
            if (type == 2 && cnt[fruits[end]] == 0) {
                cnt[fruits[start]]--;
                if (cnt[fruits[start]] == 0) {
                    type--;
                }
                start++;
                continue;
            }

            if (cnt[fruits[end]] == 0) {
                type++;
            }
            cnt[fruits[end]]++;
            end++;

            ret = Math.max(end - start, ret);
        }

        return ret;
    }

    public int total__Fruit(int[] fruits) {
        int n = fruits.length;
        int[] cnt = new int[n];
        int type = 0;

        // 滑动窗口 [start, end).
        int start = 0;
        int end = 0;
        int ret = 0;

        while (end < n) {
            if (cnt[fruits[end]] == 0) {
                type++;
            }
            cnt[fruits[end]]++;
            end++;

            while (type > 2) {
                cnt[fruits[start]]--;
                if (cnt[fruits[start]] == 0) {
                    type--;
                }
                start++;
            }

            ret = Math.max(ret, end - start);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1695. 删除子数组的最大得分
     * 含有不同元素的子数组的最大元素和
     * @Date 14:43 2025/6/15
     * @Param [nums]
     **/
    public int maximumUniqueSubarray(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        int sum = 0;
        int ret = 0;

        while (end < nums.length) {
            if (set.contains(nums[end])) {
                set.remove(nums[start]);
                sum -= nums[start];
                start++;
                continue;
            }

            set.add(nums[end]);
            sum += nums[end];
            end++;

            ret = Math.max(sum, ret);
        }

        return ret;
    }


    public int maximum_UniqueSubarray(int[] nums) {
        int[] cnt = new int[10001];

        int start = 0;
        int end = 0;
        int sum = 0;
        int ret = 0;

        while (end < nums.length) {
            if (cnt[nums[end]] == 1) {
                cnt[nums[start]]--;
                sum -= nums[start];
                start++;
                continue;
            }

            cnt[nums[end]]++;
            sum += nums[end];
            end++;

            ret = Math.max(sum, end);
        }
        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2958. 最多 K 个重复元素的最长子数组
     * 所有元素出现次数 <= k 的最长子数组的长度
     * @Date 15:12 2025/6/15
     * @Param [nums, k]
     **/
    public static int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int start = 0;
        int end = 0;
        int ret = 0;

        while (end < nums.length) {
            int val = map.getOrDefault(nums[end], 0);
            if (val == k) {
                int cnt = map.get(nums[start]);
                map.put(nums[start], cnt - 1);
                start++;
                continue;
            }

            map.put(nums[end], val + 1);
            end++;

            ret = Math.max(end - start, ret);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2024. 考试的最大困扰度
     * @Date 15:31 2025/6/15
     * @Param [answerKey, k]
     **/
    public static int maxConsecutiveAnswers(String answerKey, int k) {
        char[] chars = answerKey.toCharArray();

        int start = 0;
        int end = 0;
        int cntT = 0;
        int cntF = 0;
        int ret = 0;

        while (end < answerKey.length()) {
            int operation = 0;
            if (chars[end] == 'T') {
                operation = Math.min(cntT + 1, cntF);
            }
            if (chars[end] == 'F') {
                operation = Math.min(cntT, cntF + 1);
            }

            if (operation > k) {
                if (chars[start] == 'T') {
                    cntT--;
                } else {
                    cntF--;
                }
                start++;
                continue;
            }

            if (chars[end] == 'T') {
                cntT++;
            } else {
                cntF++;
            }
            end++;

            ret = Math.max(end - start, ret);
        }
        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1004. 最大连续1的个数 III
     * 最多有 k 个 0 的最长子数组.
     * @Date 17:09 2025/6/15
     * @Param [nums, k]
     **/
    public int longestOnes(int[] nums, int k) {
        int start = 0;
        int end = 0;
        int cnt = 0;
        int ret = 0;

        while (end < nums.length) {
            if (nums[end] == 0 && cnt == k) {
                if (nums[start] == 0) {
                    cnt--;
                }
                start++;
                continue;
            }

            if (nums[end] == 0) {
                cnt++;
            }
            end++;

            ret = Math.max(end - start, ret);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1658. 将 x 减到 0 的最小操作数
     * 元素和为 x 的最少元素数.
     * 数组元素和为 sum, 元素和为 sum - x 的最长子数组.
     * @Date 17:16 2025/6/15
     * @Param [nums, x]
     **/
    public static int minOperations(int[] nums, int x) {
        int sum = 0;
        for (int val : nums) {
            sum += val;
        }
        int k = sum - x;


        if (k < 0) {
            return -1;
        }
        if (k == 0) {
            return nums.length;
        }

        int start = 0;
        int end = 0;
        int cnt = 0;
        int ret = -1;

        while (end < nums.length) {
            if (start < end && cnt + nums[end] > k) {
                cnt -= nums[start];
                start++;
                continue;
            }
            cnt += nums[end];
            end++;
            if (cnt == k) {
                ret = Math.max(ret, end - start);
            }
        }

        return ret == -1 ? -1 : nums.length - ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2730. 找到最长的半重复子字符串
     * @Date 11:21 2025/6/18
     * @Param [s]
     **/
    public int longestSemiRepetitiveSubstring(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        int end = 0;
        int cnt = 0;
        int ret = 0;

        while (end < chars.length) {
            if (start < end && chars[end] == chars[end - 1]) {
                cnt++;
            }
            end++;

            while (cnt > 1) {
                if (chars[start] == chars[start + 1]) {
                    cnt--;
                }
                start++;
            }

            ret = Math.max(ret, end - start);
        }

        return ret;
    }


    public int longest_SemiRepetitiveSubstring(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        int end = 0;
        int cnt = 0;
        int ret = 0;

        while (end < chars.length) {
            if (cnt == 1 && chars[end] == chars[end - 1]) {
                if (chars[start] == chars[start + 1]) {
                    cnt--;
                }
                start++;
                continue;
            }

            if (start < end && chars[end] == chars[end - 1]) {
                cnt++;
            }
            end++;

            ret = Math.max(ret, end - start);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2779. 数组的最大美丽值
     * 将各个元素替换后的得到的相同元素的最大数量.  看各个元素的扩展区间覆盖范围, 即扩展区间有共同覆盖范围的元素的最大数量.
     * 将原数组排序, 各个元素扩展区间有共同覆盖范围的最长子字符串.
     * @Date 16:27 2025/6/18
     * @Param [nums, k]
     **/
    public static int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        // 滑动窗口 [start, end)
        int start = 0;
        int end = 1;
        int ret = 1;

        // 当前窗口各元素共同覆盖范围 [left, right]
        int left = nums[start] - k;
        int right = nums[start] + k;

        while (end < nums.length) {
            int end_left = nums[end] - k;
            int end_right = nums[end] + k;
            if (end_right < left || end_left > right) {
                left = end_left;
                right = end_right;
            } else {
                left = Math.max(left, end_left);
                right = Math.min(right, end_right);
            }
            end++;

            while (nums[start] + k < left || nums[start] - k > right) {
                start++;
            }

            ret = Math.max(ret, end - start);
        }
        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 接上面思路优化.
     * 将原数组排序, 各个元素的扩展区间有共同覆盖范围的最长子数组.
     * 将原数组排序, 若第 i 个元素与第 j 个元素的扩展区间有共同覆盖范围, 则它们之间元素的扩展区间有共同覆盖范围.
     * 将原数组排序, 找 (首元素 + k) >= (尾元素 - k) 的最长子字符串. 即 尾元素 - 首元素 <= 2k 的最长子数组.
     * @Date 17:55 2025/7/1
     * @Param [nums, k]
     **/
    public static int maximum_Beauty(int[] nums, int k) {
        Arrays.sort(nums);
        final int CNT = k * 2;

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 1;
        // val 滑动窗口中 尾元素 - 首元素.
        int val = 0;
        int ret = 1;

        while (end < nums.length) {
            val = nums[end] - nums[start];
            end++;

            while (val > CNT) {
                val += nums[start] - nums[start + 1];
                start++;
            }

            ret = Math.max(ret, end - start);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1838. 最高频元素的频数
     * 将 k 分配到不同的元素上, 得到相同元素的最大数量.
     * 元素位置不会影响结果, 将原数组排序, 找到 （尾元素 - 各个元素） 的和 <= k 的最长子数组.
     * @Date 20:11 2025/7/1
     * @Param [nums, k]
     **/
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 1;
        // val 当前窗口中 （尾元素 - 各个元素） 的和
        long val = 0;
        int ret = 1;

        while (end < nums.length) {
            val += (long) (nums[end] - nums[end - 1]) * (end - start);
            end++;

            while (val > k) {
                val -= nums[end - 1] - nums[start];
                start++;
            }

            ret = Math.max(ret, end - start);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 每种字符个数 >= k, 字符串首尾两端的最短长度.
     * 假设, 字符串中 a 的个数为 x;  b 的个数为 y;  c 的个数为 z;
     * 即找 a 的个数 <= x - k 且 b 的个数 <= y - k 且 c 的个数 <= z - k 的最长子字符串.
     * @Date 00:26 2025/7/2
     * @Param [s, k]
     **/
    public int takeCharacters(String s, int k) {
        int[] cnt = new int[3];
        char[] chars = s.toCharArray();

        for (char c : chars) {
            cnt[c - 'a']++;
        }
        for (int i = 0; i < 3; i++) {
            if (cnt[i] < k) {
                return -1;
            }
            cnt[i] -= k;
        }
        // cnt 规定窗口中各个字符的最大个数.

        int start = 0;
        int end = 0;
        // nums 记录当前窗口中各个字符的个数.
        int[] nums = new int[3];
        int ret = 0;

        while (end < chars.length) {
            char ch = chars[end];
            nums[ch - 'a']++;
            end++;

            while (nums[ch - 'a'] > cnt[ch - 'a']) {
                nums[chars[start] - 'a']--;
                start++;
            }

            ret = Math.max(ret, end - start);
        }
        return chars.length - ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2831. 找出最长等值子数组
     * 某个子数组中, 删除元素个数 <= k, 使剩余元素相同, 相同元素的最大个数.
     * 某个子数组中, 保留出现次数最多的元素, 删除其他元素, 可以使删除次数最小, 使保留下来的相同元素最多.
     * 即 找到某个子数组中, 除出现次数最多的元素（设出现次数 m）外, 其余元素个数 <= k, m 的最大值.
     * @Date 17:14 2025/7/15
     * @Param [nums, k]
     **/
    public int longestEqualSubarray(List<Integer> nums, int k) {
        // 滑动窗口为 [start, end)
        int start = 0;
        int end = 0;
        // cnt 统计当前窗口中各个元素的出现次数.
        int[] cnt = new int[100001];

        // max 当前窗口中出现次数最多的元素个数.
        int max = 0;

//        // ret 最长等值子数组的长度.
//        int ret = 0;

        while (end < nums.size()) {
            int eVal = nums.get(end);
            cnt[eVal]++;
            max = Math.max(max, cnt[eVal]);
            end++;

            while (end - start - max > k) {
                int sVal = nums.get(start);
                cnt[sVal]--;

                // 此处 max 无法更新, 是个问题.
//                max = 0;
//                for (int i = 0; i < cnt.length; i++) {
//                    max = Math.max(max, cnt[i]);
//                }

                start++;
            }

//            ret = Math.max(max, ret);
        }

        return max;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2831. 找出最长等值子数组
     * 分组 + 滑动窗口
     * @Date 19:29 2025/7/15
     * @Param [nums, k]
     **/
    public int longest_EqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();

        // 分组  cnt[i] 存放数组 nums 中 nums[i] 对应的下标.
        List<Integer>[] cnt = new ArrayList[n + 1];
        // ret 最长等值子数组的长度.
        int ret = 1;

        for (int i = 0; i < n; i++) {
            int val = nums.get(i);

            if (cnt[val] == null) {
                cnt[val] = new ArrayList<>();
            }
            cnt[val].add(i);
        }

        for (List<Integer> list : cnt) {
            if (list == null) {
                continue;
            }

            // 滑动窗口 [start, end], 在 nums 数组某个元素的下标列表 list 上滑动.
            int start = 0;
            int end = 0;

            int size = list.size();
//            while (end < size - 1){
//                end++;
//
//                int posRight = list.get(end);
//                int posLeft = list.get(start);
//                while (posRight - posLeft - (end - start) > k){
//                    start++;
//                    posLeft = list.get(start);
//                }
//
//                ret = Math.max(ret, end - start + 1);
//            }

            while (end < list.size()) {
                int posRight = list.get(end);
                int posLeft = list.get(start);
                while ((posRight - posLeft + 1) - (end - start + 1) > k) {
                    start++;
                    posLeft = list.get(start);
                }

                ret = Math.max(ret, end - start + 1);
                end++;
            }
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2271. 毯子覆盖的最多白色砖块数
     * 排序 + 滑动窗口 (毯子的右端点位于区间的右端点)
     * @Date 10:36 2025/7/16
     * @Param [tiles, carpetLen]
     **/
    public static int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        int n = tiles.length;
        Arrays.sort(tiles, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });


        // 滑动窗口 [left, right), 表示毯子覆盖的完整区间. left 指向毯子覆盖的第 1 个区间; right - 1 指向毯子覆盖的最后一个区间, 毯子的右端点位于此区间的右端点.
        // 因为在一个区间内, 毯子的右端点位于区间的右端点时, 其覆盖的瓷砖数 <= 毯子的右端点位于区间的右端点.
        int left = 0;
        int right = 0;
        // cover 当前窗口中完整区间的长度和.
        int cover = 0;
        // ret 毯子覆盖的最多瓷砖数
        int ret = 0;

        while (right < n) {
            cover += tiles[right][1] - tiles[right][0] + 1;

            // carpetLeft 毯子的左端点.
            int carpetLeft = tiles[right][1] - carpetLen + 1;
            // left 指向的区间完全没有被毯子覆盖
            while (tiles[left][1] < carpetLeft) {
                cover -= tiles[left][1] - tiles[left][0] + 1;
                left++;
            }
            right++;

            // left 指向的区间部分或全部被毯子覆盖
            // uncover left 指向的区间中没有被毯子覆盖的瓷砖数.
            int uncover = 0;
            if (carpetLeft > tiles[left][0]) {
                uncover = carpetLeft - tiles[left][0];
            }

            ret = Math.max(ret, cover - uncover);
        }

        return ret;
    }


    /**
     * @return int
     * @Author 强仔不强
     * @Description 2271. 毯子覆盖的最多白色砖块数
     * 排序 + 滑动窗口 （毯子的左端点位于区间的左端点）
     * @Date 10:42 2025/7/18
     * @Param [tiles, carpetLen]
     **/
    public static int maximum_WhiteTiles(int[][] tiles, int carpetLen) {
        int n = tiles.length;

        Arrays.sort(tiles, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 滑动窗口 [start, end),  start 指向毯子覆盖的第一个区间, end - 1 指向毯子覆盖的最后一个区间.
        // 毯子的左端点与第一个区间的左端点重合.
        int start = 0;
        int end = 0;

        // cover 当前窗口中完整区间的瓷砖数.
        int cover = 0;

        // ret 毯子能覆盖的最多瓷砖数.
        int ret = 0;

        while (start < n) {
            int carpetRight = tiles[start][0] + carpetLen - 1;

            while (end < n && carpetRight >= tiles[end][0]) {
                cover += tiles[end][1] - tiles[end][0] + 1;
                end++;
            }

            int uncover = 0;
            if (carpetRight < tiles[end - 1][1]) {
                uncover = tiles[end - 1][1] - carpetRight;
            }

            ret = Math.max(ret, cover - uncover);

            cover -= tiles[start][1] - tiles[start][0] + 1;
            start++;
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 2555. 两个线段获得的最多奖品
     * @Date 23:14 2025/7/18
     * @Param [prizePositions, k]
     * @return int
     **/

    /**
     * @return int
     * @Author 强仔不强
     * @Description 一个线段获得的最多奖品数
     * 若线段右端点的位置无奖品, 将线段左移, 其奖品数不变或增加, 所以不考虑线段右端点的位置无奖品的情况. 线段右端点均位于有奖品处.
     * 滑动窗口 [left, right), left、right 指向 pos, 线段左端点可能位于 pos[left] （线段左端点还可能位于 无奖品处）, 右端点一定位于 pos[right - 1], 要求 pos[right - 1] - pos[left] <= k;
     * 覆盖的奖品数为 right - left.
     * @Date 19:16 2025/7/23
     * @Param [prizePositions, k]
     **/
    public static int oneMaxWin(int[] prizePositions, int k) {
        // [start, end) 指向 pos.
        int start = 0;
        int end = 1;
        int ret = 1;

        // 先入后出
        while (end < prizePositions.length) {
            while (prizePositions[end] - prizePositions[start] > k) {
                start++;
            }
            end++;
            ret = Math.max(ret, end - start);
        }
        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 两个线段所覆盖的最多奖品
     * 贪心思想: 两个线段不相交, 覆盖的奖品数最多.
     * 右线段 [start, end), 指向 pos; 左线段 [left, right) 指向 pos; arr[i] 表示左线段的右端点 < pos[i] 时, 覆盖的最大奖品数.
     * @Date 15:08 2025/9/19
     * @Param [prizePositions, k]
     **/
    public static int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;

        if (prizePositions[n - 1] - prizePositions[0] <= 2 * k + 1) {
            return n;
        }

        int start = 0;
        int end = 1;
        int second_len = 1;

        int[] arr = new int[n + 1];
        int ret = 1;

        while (end < n) {
            while (prizePositions[end - 1] - prizePositions[start] > k) {
                start++;
            }
            end++;

            second_len = end - start;
            arr[end] = Math.max(arr[end - 1], second_len);

            ret = Math.max(ret, arr[start] + second_len);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 两个线段所覆盖的最多奖品
     * 右线段 [start, end), 指向 pos, 滑动窗口枚举 start, 维护 end; 要求 pos[end - 1] - pos[start] <= k
     * 左线段 [left, right), 指向 pos, 滑动窗口枚举 right, 维护 left; 要求 pos[right - 1] - pos[left] <= k
     * 同时滑动 左右线段, 枚举 start(left), 维护 end 与 left;  max 记录左线段覆盖的最多奖品数; 不断更新 ret
     * @Date 16:32 2025/9/19
     * @Param [prizePositions, k]
     **/
    public static int maximizeWin_Two(int[] prizePositions, int k) {
        int n = prizePositions.length;
        if (prizePositions[n - 1] - prizePositions[0] <= 2 * k + 1) {
            return n;
        }

        // 右线段 [mid, end);  左线段 [left, mid)
        int mid = 0;
        int end = 1;
        int left = 0;
        int mx = 0;
        int ret = 1;

        while (end < n) {
            // 右线段
            while (prizePositions[end - 1] - prizePositions[mid] < k && end < n) {
                end++;
            }
            // 左线段
            while (mid > 0 && prizePositions[mid - 1] - prizePositions[left] > k) {
                left++;
            }

            mx = Math.max(mx, mid - left);
            ret = Math.max(ret, end - mid + mx);
            mid++;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2106. 摘水果
     * @Date 15:33 2025/9/21
     * @Param [fruits, startPos, k]
     **/
    public static int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int left = findLeft(fruits, startPos, k);
        if (left == -1) {
            return 0;
        }

        int right = left;
        int sum = 0;
        int ret = 0;

        while (right < n && fruits[right][0] <= startPos + k) {
            sum += fruits[right][1];

            if (fruits[left][0] < startPos && fruits[right][0] > startPos) {
                while (startPos - fruits[left][0] + fruits[right][0] - fruits[left][0] > k &&
                        fruits[right][0] - startPos + fruits[right][0] - fruits[left][0] > k) {
                    sum -= fruits[left][1];
                    left++;
                }
            }

            ret = Math.max(ret, sum);
            right++;
        }

        return ret;
    }

    // O(n)
    public static int findLeft(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int left = -1;

        for (int i = 0; i < n; i++) {
            if (fruits[i][0] >= startPos - k && fruits[i][0] <= startPos + k) {
                left = i;
                break;
            }
        }
        return left;
    }

    // 二分
    public static int findLeftPos(int[][] fruits, int startPos, int k) {
        int left = 0;
        int right = fruits.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;

            if (left == right) {
                break;
            }

            if (fruits[mid][0] <= startPos + k && fruits[mid][0] >= startPos - k) {
                right = mid;
            }
            if (fruits[mid][0] < startPos - k) {
                left = mid + 1;
            }
            if (fruits[mid][0] > startPos + k) {
                right = mid - 1;
            }
        }

        if (fruits[mid][0] < startPos - k || fruits[mid][0] > startPos + k) {
            mid = -1;
        }

        return mid;
    }

    public static int func(int[] arr, int target) {
        int mid = 0;
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            }

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2009. 使数组连续的最少操作数
     * @Date 15:21 2025/9/24
     * @Param [nums]
     **/
    public static int minOperations(int[] nums) {
        int n = nums.length;
        // 排序
        Arrays.sort(nums);

        // 去重
        ArrayList<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                list.add(nums[i]);
            }
        }

        // 滑动窗口 [start, end)
        // 枚举 end, 维护 start.
        int start = 0;
        int end = 1;

        int ret = 0;

        while (end < list.size()) {
            while (list.get(start) < list.get(end) - n + 1) {
                start++;
            }
            end++;
            ret = Math.max(ret, end - start);
        }

        return n - ret;
    }


    /**
     * @return int
     * @Author 强仔不强
     * @Description 2781. 最长合法子字符串的长度
     * @Date 17:24 2025/9/24
     * @Param [word, forbidden]
     **/
    public int longestValidSubstring(String word, List<String> forbidden) {
        HashSet<String> set = new HashSet<>();
        set.addAll(forbidden);

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        int ret = 0;

        while (end < word.length()) {

            int stop_step = Math.max(start, (end - 9));
            for (int i = end; i >= stop_step; i--) {
                String s = word.substring(i, end + 1);
                if (set.contains(s)) {
                    start = i + 1;
                    break;
                }
            }

            end++;

            ret = Math.max(ret, end - start);
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 3411. 最长乘积等价子数组
     * @Date 17:45 2025/9/24
     * @Param [nums]
     * @return int
     **/
//    public int maxLength(int[] nums) {
//
//    }


    /**
     * @return long
     * @Author 强仔不强
     * @Description
     * @Date 17:49 2025/9/24
     * @Param [coins, k]
     **/
    public static long maximumCoins(int[][] coins, int k) {
        Arrays.sort(coins, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 滑动窗口 [start_pos, end_pos) 指向 坐标
        //        [start, end] 指向 coins
        int start = 0;
        int end = 0;
        int start_pos = coins[start][0];
        int end_pos = start_pos;
        long num = 0;
        long ret = 0;

        while (end < coins.length) {
            if (end_pos <= coins[end][1]) {
                num += coins[end][2];
                end_pos++;
            } else {
                end++;
                if (end == coins.length) {
                    break;
                }
                end_pos = coins[end][0];
            }

            while (end_pos - start_pos > k) {
                num -= coins[start][2];
                if (start_pos < coins[start][1]) {
                    start_pos++;
                } else {
                    start++;
                    start_pos = coins[start][0];
                }
            }

            ret = Math.max(ret, num);
        }

        return ret;
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description
     * @Date 16:43 2025/9/25
     * @Param [coins, k]
     **/
    public static long maximum_Coins(int[][] coins, int k) {
        Arrays.sort(coins, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 滑动窗口 [start, end) 指向 coins
        int start = 0;
        int end = 0;
        // [ coins[start][0], coins[end - 1][1] ] 区间内的所有奖品数.
        long total_num = 0;
        long ret = 0;

        while (end < coins.length) {
            total_num += (coins[end][1] - coins[end][0] + 1) * coins[end][2];

            // 维护 start, 保证 线段左右端点分别位于 [ coins[start][0], coins[start][1] ],  [ coins[end][0], coins[end][1] ] 区间内
            while (coins[end][0] - coins[start][1] + 1 > k) {
                total_num -= (coins[start][1] - coins[start][0] + 1) * coins[start][2];
                start++;
            }

            // [ coins[start][0], coins[end][1] ] 区间内长度 > k, 需要将此区间缩小, remove_step 缩小的长度
            long remove_step = Math.max(coins[end][1] - coins[start][0] + 1 - k, 0);
            // 此区间缩小的长度 应该为覆盖的奖品数最少
            long step_prize = Math.min(coins[end][2], coins[start][2]);
            long num = total_num - remove_step * step_prize;

            ret = Math.max(ret, num);
            end++;
        }

        return ret;
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description 3413. 收集连续 K 个袋子可以获得的最多硬币数量
     * @Date 15:40 2025/9/26
     * @Param [coins, k]
     **/
    public static long maximum__Coins(int[][] coins, int k) {
        Arrays.sort(coins, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        long ret_right = maximumCoinsRight(coins, k);
        long ret_left = maximumCoinsLeft(coins, k);

        return Math.max(ret_left, ret_right);
    }

    public static long maximumCoinsRight(int[][] coins, int k) {
        // 滑动窗口 [start, end)
        // 枚举 end, 维护 start
        int start = 0;
        int end = 0;
        long total_num = 0;
        long ret = 0;

        while (end < coins.length) {
            total_num += (long) (coins[end][1] - coins[end][0] + 1) * coins[end][2];

            int bag_left = coins[end][1] - k + 1;
            end++;

            while (bag_left > coins[start][1]) {
                total_num -= (long) (coins[start][1] - coins[start][0] + 1) * coins[start][2];
                start++;
            }

            long uncover = Math.max(bag_left - coins[start][0], 0);
            long num = total_num - uncover * coins[start][2];
            ret = Math.max(ret, num);

        }

        return ret;
    }

    public static long maximumCoinsLeft(int[][] coins, int k) {
        // [start, end)
        // 枚举 start, 维护 end
        int start = 0;
        int end = 0;
        long total_num = 0;
        long ret = 0;

        while (start < coins.length) {
            int bag_right = coins[start][0] + k - 1;
            while (end < coins.length && coins[end][0] <= bag_right) {
                total_num += (long) (coins[end][1] - coins[end][0] + 1) * coins[end][2];
                end++;
            }

            long uncover = Math.max(coins[end - 1][1] - bag_right, 0);
            long num = total_num - uncover * coins[end - 1][2];
            ret = Math.max(ret, num);

            total_num -= (long) (coins[start][1] - coins[start][0] + 1) * coins[start][2];
            start++;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 209. 长度最小的子数组
     * @Date 17:08 2025/9/26
     * @Param [target, nums]
     **/
    public int minSubArrayLen(int target, int[] nums) {
        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        int sum = 0;
        int ret = nums.length + 1;

        while (end < nums.length) {
            sum += nums[end];
            end++;

            while (sum - nums[start] >= target) {
                sum -= nums[start];
                start++;
            }

            if (sum >= target) {
                ret = Math.min(ret, end - start);
            }
        }

        return ret <= nums.length ? ret : 0;
    }

    public int min_SubArrayLen(int target, int[] nums) {
        // 滑动窗口 [start, end)
        // 枚举 end, 维护 start
        int start = 0;
        int end = 0;
        int ret = nums.length + 1;
        int sum = 0;

        while (end < nums.length) {
            sum += nums[end];
            end++;

            while (sum >= target) {
                ret = Math.min(ret, end - start);
                sum -= nums[start];
                start++;
            }
        }

        return ret == nums.length + 1 ? 0 : ret;
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 2904. 最短且字典序最小的美丽子字符串
     * @Date 16:50 2025/9/27
     * @Param [s, k]
     **/
    public static String shortestBeautifulSubstring(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        int start = 0;
        int end = 0;
        int one_count = 0;

        int left = 0;
        int right = n + 1;

        while (end < chars.length) {
            if (chars[end] == '1') {
                one_count++;
            }
            end++;

            while (one_count == k) {
                if (end - start < right - left) {
                    left = start;
                    right = end;
                } else if (end - start == right - left) {
                    for (int i = 0; i < right - left; i++) {
                        if (chars[start + i] != chars[left + i]) {
                            if (chars[start + i] < chars[left + i]) {
                                left = start;
                                right = end;
                            } else {
                                break;
                            }
                        }
                    }
                }

                if (chars[start] == '1') {
                    one_count--;
                }
                start++;
            }
        }

        String ret = "";
        if (right == n + 1) {
            return ret;
        }

        System.out.println(left + "," + right);
        return s.substring(left, right);
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1234. 替换子串得到平衡字符串
     * 正难则反
     * 待替换的最短子串  ——>  不替换的最长子串（即 Q W E R 的次数 <= n / 4 的最长子串（可能环形子串））
     * @Date 10:10 2025/9/28
     * @Param [s]
     **/
    public static int balancedString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        final int TARGET = n / 4;

        // 滑动窗口 [start, end) 表示不替换子串.
        int start = 0;
        int end = 0;
        int[] counts = new int[128];
        int ret = 0;

        while (start < n) {
            counts[chars[end % n]]++;

            while (counts[chars[end % n]] > TARGET) {
                counts[chars[start % n]]--;
                start++;
            }
            end++;
            ret = Math.max(ret, end - start);

            if (ret == n) {
                break;
            }
        }

        return n - ret;
    }

    // 字符串长度为 4 的倍数, 替换完之后的平衡字符串中 每个字符的个数为 n/4.
    // 不替换部分中 每个字符的个数应该 <= n/4.   将其作为替换子串的滑动窗口的滑动条件
    public static int balanced_String(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        final int TARGET = n / 4;

        // 滑动窗口 [start, end) 表示替换的子串
        int start = 0;
        int end = 0;
        // map 记录不替换部分, 每个字符的个数.
        HashMap<Character, Integer> map = new HashMap<>();
        // ret 替换子串的最小长度.
        int ret = n + 1;


        boolean a = true;
        for (int i = 0; i < n; i++) {
            int val = map.getOrDefault(chars[i], 0);
            map.put(chars[i], val + 1);
            if (val + 1 > TARGET) {
                a = false;
            }
        }

        // 完整字符串中每个字符的个数 <= target, 不用替换.
        if (a) {
            return 0;
        }


        while (end < n) {
            map.put(chars[end], map.get(chars[end]) - 1);
            end++;

            while (true) {
                // flg 表示 是否满足不替换部分每个字符个数 <= TARGET
                boolean flg = true;
                for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                    if (entry.getValue() > TARGET) {
                        flg = false;
                        break;
                    }
                }

                // flg 为 true, 则不替换部分每个字符的个数 <= TARGET, 当前子串可以作为替换子串.
                // flg 为 false, 当前子串不能作为替换子串.
                if (flg) {
                    ret = Math.min(ret, end - start);
                } else {
                    break;
                }

                map.put(chars[start], map.get(chars[start]) + 1);
                start++;
            }
        }

        return ret;
    }

    public static int balanced__String(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        final int TARGET = n / 4;

        // 滑动窗口 [start, end) 表示替换的子串
        int start = 0;
        int end = 0;
        // arr 记录不替换部分, 每个字符的个数.
        int[] arr = new int[128];
        // ret 替换子串的最小长度.
        int ret = n + 1;


        for (int i = 0; i < n; i++) {
            arr[chars[i]]++;
        }
        // 该字符串本身就是一个平衡字符串.
        if (arr['Q'] == TARGET && arr['W'] == TARGET && arr['E'] == TARGET && arr['R'] == TARGET) {
            return 0;
        }

        while (end < n) {
            arr[chars[end]]--;
            end++;

            while (arr['Q'] <= TARGET && arr['W'] <= TARGET && arr['E'] <= TARGET && arr['R'] <= TARGET) {
                ret = Math.min(ret, end - start);
                arr[chars[start]]++;
                start++;
            }
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2875. 无限数组的最短子数组
     * @Date 14:22 2025/9/29
     * @Param [nums, target]
     **/
    public static int minSizeSubarray(int[] nums, int target) {
        int n = nums.length;
        // 滑动窗口 [start, end) 表示元素和为 target 的子数组
        int start = 0;
        int end = 0;
        // sum 表示当前窗口的元素和
        int sum = 0;
        int ret = Integer.MAX_VALUE;

        while (start < n) {
            sum += nums[end % n];
            end++;

            while (start < n && sum > target) {
                sum -= nums[start];
                start++;
            }

            if (sum == target) {
                ret = Math.min(ret, end - start);
            }
        }

        return ret == Integer.MAX_VALUE ? -1 : ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2875. 无限数组的最短子数组
     * @Date 14:53 2025/9/29
     * @Param [nums, target]
     **/
    public static int minSize_Subarray(int[] nums, int target) {
        int n = nums.length;

        int total_nums = 0;
        for (int i = 0; i < n; i++) {
            total_nums += nums[i];
        }

        int rem = target % total_nums;
        int count_nums = target / total_nums;
        // 在 nums + nums 中找元素和为 rem 的最短子数组. (该子数组中元素数量一定 < n)
        // [start, end) 表示元素和为 rem 的子数组.
        int start = 0;
        int end = 0;
        int sum = 0;
        int ret = n;

        while (start < n) {
            sum += nums[end % n];
            end++;

            while (start < n && sum > rem) {
                sum -= nums[start];
                start++;
            }

            if (sum == rem) {
                ret = Math.min(ret, end - start);
            }
        }

        if (ret == n) {
            return -1;
        }

        return count_nums * n + ret;
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 76. 最小覆盖子串
     * @Date 15:17 2025/9/29
     * @Param [s, t]
     **/
    public String minWindow(String s, String t) {
        char[] chars_s = s.toCharArray();
        char[] chars_t = t.toCharArray();

        // 记录 t 中的每个字符的数量
        int[] arr_t = new int[128];

        // 滑动窗口 [start, end) 表示包含 t 中所有字符的子字符串.
        int start = 0;
        int end = 0;
        int[] arr_windows = new int[128];

        int left = 0;
        int right = s.length() + 1;

        for (int i = 0; i < t.length(); i++) {
            arr_t[chars_t[i]]++;
        }

        while (end < s.length()) {
            arr_windows[chars_s[end]]++;
            end++;

            // 也可以写个  iscover() 方法判断 [start, end) 是否包含 t 中所有字符, 作为 while() 的循环条件.
            while (true) {
                // flg 表示 [start, end) 是否包含 t 中所有字符
                boolean flg = true;
                for (int i = 0; i < 128; i++) {
                    if (arr_windows[i] < arr_t[i]) {
                        flg = false;
                        break;
                    }
                }

                if (flg) {
                    // 更新最短子字符串 [left, right)
                    if (end - start < right - left) {
                        left = start;
                        right = end;
                    }
                    // start 右移
                    arr_windows[chars_s[start]]--;
                    start++;
                } else {
                    break;
                }
            }
        }

        String ret = "";
        if (right - left <= s.length()) {
            ret = s.substring(left, right);
        }
        return ret;
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 76. 最小覆盖子串 （更快）
     * @Date 16:42 2025/9/29
     * @Param [s, t]
     **/
    public static String min_Window(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        char[] chars_s = s.toCharArray();
        char[] chars_t = t.toCharArray();

        int[] arr_t = new int[128];

        // 滑动窗口 [start, end) 表示包含 t 中所有字符的子字符串.
        int start = 0;
        int end = 0;
        int[] arr_windows = new int[128];

        // 记录 t 中的每个字符的数量
        for (int i = 0; i < t.length(); i++) {
            arr_t[chars_t[i]]++;
        }

        // flg 表示 [0, end) 子字符串是否包含 t 中所有字符.
        boolean flg = true;
        while (end < s.length()) {
            arr_windows[chars_s[end]]++;
            end++;

            if (end < t.length()) {
                continue;
            }

            flg = true;
            for (int i = 0; i < 128; i++) {
                if (arr_windows[i] < arr_t[i]) {
                    flg = false;
                    break;
                }
            }
            if (flg) {
                break;
            }

        }

        // s 中不包含 t 的所有字符.
        if (!flg) {
            return "";
        }

        // [left, right) 表示包含 t 中所有字符的最短子字符串.
        // [0, end) 并非最短, start 还未移动.
        int left = 0;
        int right = end;

        // 先出后进, 一定要注意 end == s.length 时, 进入循环, start 移动.
        while (end <= s.length()) {
            while (arr_windows[chars_s[start]] > arr_t[chars_s[start]]) {
                arr_windows[chars_s[start]]--;
                start++;
            }

            if (end - start < right - left) {
                left = start;
                right = end;
            }

            if (end == s.length()) {
                break;
            }
            arr_windows[chars_s[end]]++;
            end++;
        }
        return s.substring(left, right);
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 76. 最小覆盖子串 （更更快）
     * @Date 11:13 2025/10/1
     * @Param [s, t]
     **/
    public static String min__Window(String s, String t) {
        String ret = "";
        if (s.length() < t.length()) {
            return ret;
        }

        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();

        int[] cntT = new int[128];

        // 滑动窗口 [start, end) 表示包含 t 中所有字符的子字符串.
        int start = 0;
        int end = 0;
        int[] cntS = new int[128];

        Set<Character> set = new HashSet<>();
        // 记录 t 中的每个字符的数量
        for (int i = 0; i < t.length(); i++) {
            cntT[charsT[i]]++;
            set.add(charsT[i]);
        }
        // count 表示 t 字符串中出现的不同字符的个数
        int count = set.size();

        // [left, right) 表示包含 t 中所有字符串的最短子字符串.
        int left = -1;
        int right = s.length() + 1;
        // temp 表示当前窗口中字符的出现次数 >= t 字符串中字符的出现次数的个数 （不包括 t 字符串中没有的字符）
        // 即 cntT[i] <= cntS[i] 的个数
        int temp = 0;

        while (end < s.length()) {
            char ch = charsS[end];
            cntS[ch]++;
            if (cntS[ch] == cntT[ch]) {
                temp++;
            }
            end++;

            while (temp == count) {
                if (end - start < right - left) {
                    left = start;
                    right = end;
                }

                char c = charsS[start];
                if (cntS[c] == cntT[c]) {
                    temp--;
                }
                cntS[c]--;
                start++;
            }
        }

        if (right - left <= s.length()) {
            ret = s.substring(left, right);
        }

        return ret;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 632. 最小区间
     * @Date 18:43 2025/9/29
     * @Param [nums]
     **/
    public static int[] smallestRange(List<List<Integer>> nums) {
        // count 表示坐标点的总数. 即 array 的大小.
        int count = 0;
        for (int i = 0; i < nums.size(); i++) {
            count += nums.get(i).size();
        }

        // array 存放坐标
        int[][] array = new int[count][2];
        int l = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                int[] arr = new int[2];
                arr[0] = nums.get(i).get(j);
                arr[1] = i;
                array[l++] = arr;
            }
        }
        // array 排序
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 滑动窗口 [start, end) 表示每个列表中至少包含一个坐标的区间.
        int start = 0;
        int end = 0;
        // temp 记录当前窗口中 每个列表内的坐标的个数.
        int[] temp = new int[nums.size()];
        int[] ret = {array[0][0], array[count - 1][0]};

        while (end < array.length) {
            temp[array[end][1]]++;

            while (true) {
                boolean flg = true;
                for (int i = 0; i < nums.size(); i++) {
                    if (temp[i] == 0) {
                        flg = false;
                        break;
                    }
                }

                if (!flg) {
                    break;
                }

                if (ret[1] - ret[0] > array[end][0] - array[start][0]) {
                    ret[0] = array[start][0];
                    ret[1] = array[end][0];
                }
                temp[array[start][1]]--;
                start++;
            }

            end++;
        }

        return ret;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 632. 最小区间
     * @Date 19:59 2025/9/29
     * @Param [nums]
     **/
    public static int[] smallest_Range(List<List<Integer>> nums) {
        // count 表示数轴上值的总数（坐标点的总数） 即 array 的大小.
        int count = 0;
        for (int i = 0; i < nums.size(); i++) {
            count += nums.get(i).size();
        }

        // array 存放坐标, array[i][0] 表示数轴上的值, array[i][1] 表示其所属的列表.
        int[][] array = new int[count][2];
        int l = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                int[] arr = new int[2];
                arr[0] = nums.get(i).get(j);
                arr[1] = i;
                array[l++] = arr;
            }
        }
        // array 排序
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 滑动窗口 [start, end) 表示至少包含每个列表中一个值的区间. [start, end) 指向 array 的坐标;
        // 而需要返回的区间为数轴上的值, 是 [ array[start][1], array[end - 1][0] ]
        int start = 0;
        int end = 0;
        // temp 记录当前窗口中 每个列表内的值的个数.
        int[] temp = new int[nums.size()];
        // ret 至少包含每个列表中一个值的区间, 此区间为数轴上的值
        int[] ret = {array[0][0], array[count - 1][0]};

        // 先找到 至少包含每个列表中的一个值的区间.  [0, end), 由于 start 还未移动, 此区间不是最短的.
        while (end < count) {
            temp[array[end][1]]++;
            end++;

            boolean flg = true;
            for (int i = 0; i < nums.size(); i++) {
                if (temp[i] == 0) {
                    flg = false;
                    break;
                }
            }

            if (flg) {
                break;
            }
        }
        ret[0] = array[start][0];
        ret[1] = array[end - 1][0];

        // 先出后进, 一定要注意 end == count 时, 也要进入循环, start 移动.
        while (end <= count) {
            while (temp[array[start][1]] > 1) {
                temp[array[start][1]]--;
                start++;
            }

            // 更新 ret
            if (ret[1] - ret[0] > array[end - 1][0] - array[start][0]) {
                ret[0] = array[start][0];
                ret[1] = array[end - 1][0];
            }

            if (end == count) {
                break;
            }
            temp[array[end][1]]++;
            end++;
        }

        return ret;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 632. 最小区间
     * @Date 11:04 2025/10/1
     * @Param [nums]
     **/
    public static int[] smallest__Range(List<List<Integer>> nums) {
        // count 表示数轴上值的总数（坐标点的总数） 即 array 的大小.
        int count = 0;
        for (List<Integer> list : nums) {
            count += list.size();
        }

        // array 存放坐标, array[i][0] 表示数轴上的值, array[i][1] 表示其所属的列表.
        int[][] array = new int[count][2];
        int l = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                array[l][0] = nums.get(i).get(j);
                array[l][1] = i;
                l++;
            }
        }

        // array 排序
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 滑动窗口 [start, end) 表示至少包含每个列表中一个值的区间. [start, end) 指向 array 的坐标;
        // 而需要返回的区间为数轴上的值, 是 [ array[start][1], array[end - 1][0] ]
        int start = 0;
        int end = 0;
        // temp 记录当前窗口中 每个列表内的值的个数.
        int[] temp = new int[nums.size()];
        // ret 至少包含每个列表中一个值的区间, 此区间为数轴上的值
        int[] ret = {array[0][0], array[count - 1][0]};
        // get_num 表示当前窗口中 temp[i] != 0 的列表数.
        int get_num = 0;

        while (end < count) {
            if (temp[array[end][1]] == 0) {
                get_num++;
            }
            temp[array[end][1]]++;


            while (get_num == temp.length) {
                if (ret[1] - ret[0] > array[end][0] - array[start][0]) {
                    ret[0] = array[start][0];
                    ret[1] = array[end][0];
                }

                temp[array[start][1]]--;
                if (temp[array[start][1]] == 0) {
                    get_num--;
                }
                start++;
            }

            end++;
        }
        return ret;
    }


    /**
     * @return int
     * @Author 强仔不强
     * @Description 713. 乘积小于 K 的子数组
     * @Date 15:38 2025/10/2
     * @Param [nums, k]
     **/
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 0) {
            return 0;
        }
        // 滑动窗口 [start, end) 表示元素乘积 < k 的子数组
        int start = 0;
        int end = 0;
        int mul = 1;
        int ret = 0;

        while (end < nums.length) {
            mul *= nums[end];
            end++;

            while (start < end && mul >= k) {
                mul /= nums[start];
                start++;
            }

            ret += end - start;
        }
        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 3258. 统计满足 K 约束的子字符串数量 I
     * @Date 15:58 2025/10/2
     * @Param [s, k]
     **/
    public int countKConstraintSubstrings(String s, int k) {
        char[] chars = s.toCharArray();
        // 滑动窗口 [start, end) 表示 0 的数量 <= k 或者 1 的数量 <= k 的子字符串.
        int start = 0;
        int end = 0;
        int cntZero = 0;
        int cntOne = 0;
        int ret = 0;

        while (end < s.length()) {
            if (chars[end] == '1') {
                cntOne++;
            } else {
                cntZero++;
            }
            end++;

            while (cntOne > k && cntZero > k) {
                if (chars[start] == '1') {
                    cntOne--;
                } else {
                    cntZero--;
                }
                start++;
            }

            ret += end - start;
        }

        return ret;
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description 2302. 统计得分小于 K 的子数组数目
     * @Date 16:13 2025/10/2
     * @Param [nums, k]
     **/
    public long countSubarrays(int[] nums, long k) {
        // 滑动窗口 [start, end) 表示 分数 < k 的子数组.
        int start = 0;
        int end = 0;
        long sum = 0;
        long ret = 0;
        while (end < nums.length) {
            sum += nums[end];
            end++;

            //判断条件 start < end && sum * (end - start) >= k
            // 也可以不用写 start < end, 因为如果 start == end, sum * (end - start) == 0, 而 k >= 1
            while (sum * (end - start) >= k) {
                sum -= nums[start];
                start++;
            }

            ret += end - start;
        }
        return ret;
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description 2762. 不间断子数组
     * @Date 16:36 2025/10/2
     * @Param [nums]
     **/
    public long continuousSubarrays(int[] nums) {
        // 滑动窗口 [start, end) 表示 任意两数的差的绝对值 <= 2（即 最大值 - 最小值 <= 2）的子数组
        int start = 0;
        int end = 0;
        long ret = 0;

        PriorityQueue<Integer> MaxQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        PriorityQueue<Integer> MinQueue = new PriorityQueue<>();

        while (end < nums.length) {
            MaxQueue.offer(nums[end]);
            MinQueue.offer(nums[end]);
            end++;

            // start >= end 不可能, 堆不可能为空, 因为 [start, start + 1) 时, 最大值和最小值为同一个数, 差为 0.
            while (MaxQueue.peek() - MinQueue.peek() > 2) {
                MaxQueue.remove(nums[start]);
                MinQueue.remove(nums[start]);
                start++;
            }

            ret += end - start;
        }
        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description LCP 68. 美观的花束
     * @Date 17:16 2025/10/2
     * @Param [flowers, cnt]
     **/
    public int beautifulBouquet(int[] flowers, int cnt) {
        // 滑动窗口 [start, end) 表示每个数字的出现次数 <= cnt 的子数组
        int start = 0;
        int end = 0;
        // num 记录 当前窗口中 每个数字的出现次数.
        int[] num = new int[100001];
        // temp 表示 num[i] > cnt 的数量.
        int temp = 0;
        int ret = 0;

        while (end < flowers.length) {
            int val_end = flowers[end];
            if (num[val_end] == cnt) {
                temp++;
            }
            num[val_end]++;
            end++;

            // 不会出现 start >= end
            while (temp > 0) {
                int val_start = flowers[start];
                if (num[val_start] == cnt + 1) {
                    temp--;
                }
                num[val_start]--;
                start++;
            }

            ret += end - start;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1358. 包含所有三种字符的子字符串数目
     * @Date 14:09 2025/10/10
     * @Param [s]
     **/
    public int numberOfSubstrings(String s) {
        char[] chars = s.toCharArray();
        // 滑动窗口 [start, end) 表示 a、b、c 出现次数 >= 1 的子字符串.
        int start = 0;
        int end = 0;
        // array 记录当前窗口 a、b、c 的出现次数.
        int[] array = new int[128];
        // flg 表示当前窗口中 a、b、c 出现次数为 0 的个数.
        int flg = 3;
        // ret 表示 a、b、c 出现次数 >= 1 的子字符串的数目.
        int ret = 0;

        while (end < s.length()) {
            char ch = chars[end];
            if (array[ch] == 0) {
                flg--;
            }
            array[ch]++;
            end++;

            while (flg == 0) {
                char c = chars[start];
                if (array[c] == 1) {
                    flg++;
                }
                array[c]--;
                start++;
            }

            ret += start;
        }

        return ret;
    }


    /**
     * @return long
     * @Author 强仔不强
     * @Description 2962. 统计最大元素出现至少 K 次的子数组
     * @Date 14:37 2025/10/10
     * @Param [nums, k]
     **/
    public long countSubarrays(int[] nums, int k) {
        int max = nums[0];
        for (int val : nums) {
            max = Math.max(max, val);
        }

        // 滑动窗口 [start, end) 表示 max 出现次数 >= k 的子数组.
        int start = 0;
        int end = 0;
        // temp 记录当前窗口中 max 出现次数.
        long temp = 0;
        // ret 记录 max 出现次数 >= k 的子数组的数量
        long ret = 0;

        while (end < nums.length) {
            if (nums[end] == max) {
                temp++;
            }
            end++;

            while (temp >= k) {
                if (nums[start] == max) {
                    temp--;
                }
                start++;
            }

            ret += start;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 3325. 字符至少出现 K 次的子字符串 I
     * @Date 14:53 2025/10/10
     * @Param [s, k]
     **/
    public int numberOfSubstrings(String s, int k) {
        char[] chars = s.toCharArray();
        // 滑动窗口 [start, end) 表示 >= 1 个字母的出现次数 >= k 的子字符串.
        int start = 0;
        int end = 0;
        // cnt 记录当前窗口中字母的出现次数.
        int[] cnt = new int[128];
        // num 表示当前窗口中 出现次数 >= k 的字母的个数.
        int num = 0;
        // ret 记录 子字符串的数量.
        int ret = 0;

        while (end < chars.length) {
            char ce = chars[end];
            if (cnt[ce] == k - 1) {
                num++;
            }
            cnt[ce]++;
            end++;

            while (num >= 1) {
                char cs = chars[start];
                if (cnt[cs] == k) {
                    num--;
                }
                cnt[cs]--;
                start++;
            }

            ret += start;
        }

        return ret;
    }


    /**
     * @return int
     * @Author 强仔不强
     * @Description
     * @Date 15:07 2025/10/10
     * @Param [nums]
     **/
    public int countCompleteSubarrays(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int val : nums) {
            set.add(val);
        }
        int k = set.size();

        // 滑动窗口 [start, end) 表示不同元素个数 == k 的子数组.
        int start = 0;
        int end = 0;
        // cnt 记录当前窗口中 元素的出现次数.
        int[] cnt = new int[2001];
        // flg 表示当前窗口中不同元素的个数.
        int flg = 0;
        // ret 表示完全子数组的数量.
        int ret = 0;

        while (end < nums.length) {
            if (cnt[nums[end]] == 0) {
                flg++;
            }
            cnt[nums[end]]++;
            end++;

            while (flg == k) {
                if (cnt[nums[start]] == 1) {
                    flg--;
                }
                cnt[nums[start]]--;
                start++;
            }

            ret += start;
        }

        return ret;
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description 3298. 统计重新排列后包含另一个字符串的子字符串数目 II
     * 找 word1 中满足 每个字母的出现次数均 >= word2 中每个字母的出现次数 的子字符串的数目.
     * @Date 15:22 2025/10/10
     * @Param [word1, word2]
     **/
    public static long validSubstringCount(String word1, String word2) {
        if (word1.length() < word2.length()) {
            return 0;
        }
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();

        // set 找到 word2 中不同字母的个数.
        HashSet<Character> set = new HashSet<>();
        // cnt2 记录 word2 中每个字母的出现次数.
        int[] cnt2 = new int[128];
        for (char ch : chars2) {
            cnt2[ch]++;
            set.add(ch);
        }

        // 滑动窗口 [start, end) 表示 每个字母的出现次数均 >= word2 中每个字母的出现次数 （cnt1[i] 均 >= cnt2[i]）的子字符串.
        int start = 0;
        int end = 0;
        // cnt1 记录当前窗口中 每个字母的出现次数.
        int[] cnt1 = new int[128];
        // num 表示 cnt1[i] < cnt2[i] 的个数.
        int num = set.size();
        // ret 记录 子字符串的数量.
        long ret = 0;

        while (end < chars1.length) {
            cnt1[chars1[end]]++;
            if (cnt1[chars1[end]] == cnt2[chars1[end]]) {
                num--;
            }
            end++;

            while (num == 0) {
                if (cnt1[chars1[start]] == cnt2[chars1[start]]) {
                    num++;
                }
                cnt1[chars1[start]]--;
                start++;
            }

            ret += start;
        }

        return ret;
    }


    /**
     * @return long
     * @Author 强仔不强
     * @Description 2537. 统计好子数组的数目
     * @Date 16:20 2025/10/10
     * @Param [nums, k]
     **/
    public long countGood(int[] nums, int k) {
        // 滑动窗口 [start, end) 表示 好子数组.
        int start = 0;
        int end = 0;
        // map 记录当前窗口中 每个数字出现的次数.
        HashMap<Integer, Integer> map = new HashMap<>();
        // cnt 表示 i < j 且 arr[i] == arr[j] 的 （i, j）的数量.
        int cnt = 0;
        // ret 表示 好子数组的数量.
        long ret = 0;

        while (end < nums.length) {
            int val1 = map.getOrDefault(nums[end], 0);
            map.put(nums[end], val1 + 1);
            cnt += val1;
            end++;

            while (cnt >= k) {
                int val2 = map.get(nums[start]);
                map.put(nums[start], val2 - 1);
                cnt -= val2 - 1;
                start++;
            }

            ret += start;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 930. 和相同的二元子数组
     * （元素和 >= goal 的子数组数量） - （元素和 >= goal + 1 的子数组数量）
     * 越长越合法
     * @Date 17:20 2025/10/10
     * @Param [nums, goal]
     **/
    public int numSubarraysWithSum(int[] nums, int goal) {
        return numSubarraysWithSumFunc(nums, goal) - numSubarraysWithSumFunc(nums, goal + 1);
    }

    // 找元素和 >= k 的子数组数量
    public int numSubarraysWithSumFunc(int[] nums, int k) {
        // 滑动窗口 [start, end) 表示元素和 >= k 的子数组.
        int start = 0;
        int end = 0;
        // sum 表示当前窗口的元素和.
        int sum = 0;
        // ret 表示子数组数量.
        int ret = 0;

        while (end < nums.length) {
            sum += nums[end];
            end++;

            while (start < end && sum >= k) {
                sum -= nums[start];
                start++;
            }

            ret += start;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1248. 统计「优美子数组」
     * （奇数个数 <= k 的非空子数组数量） - （奇数个数 <= k - 1 的非空子数组的数量）
     * @Date 17:39 2025/10/10
     * @Param [nums, k]
     **/
    public int numberOfSubarrays(int[] nums, int k) {
        return numberOfSubarraysFunc(nums, k) - numberOfSubarraysFunc(nums, k - 1);
    }

    // 求奇数个数 <= k 的非空子数组数量
    public int numberOfSubarraysFunc(int[] nums, int k) {
        int start = 0;
        int end = 0;
        int cnt = 0;
        int ret = 0;

        while (end < nums.length) {
            if (nums[end] % 2 != 0) {
                cnt++;
            }
            end++;

            while (cnt > k) {
                if (nums[start] % 2 != 0) {
                    cnt--;
                }
                start++;
            }

            ret += end - start;
        }

        return ret;
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description 3306. 元音辅音字符串计数 II
     * 辅音字母个数 >= k 的字符串数 - 辅音字母个数 >= k + 1 的字符串数 （'a', 'e', 'i', 'o', 'u' 出现次数 >= 1）
     * @Date 21:04 2025/10/10
     * @Param [word, k]
     **/
    public long countOfSubstrings(String word, int k) {
        char[] chars = word.toCharArray();
        return countOfSubstringsFunc(chars, k) - countOfSubstringsFunc(chars, k + 1);
    }

    // 'a', 'e', 'i', 'o', 'u' 出现次数均 >= 1 且 辅音字母出现次数 >= k 的字符串数
    public long countOfSubstringsFunc(char[] chars, int k) {
        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        // cnt 记录当前窗口中 'a', 'e', 'i', 'o', 'u' 的出现次数.
        int[] cnt = new int[128];
        // num 记录当前窗口中 出现次数为 0 的元音字母 的个数.
        int num = 5;
        // flg 记录当前窗口中 辅音字母的出现次数.
        int flg = 0;
        // ret 记录所有的子字符串数
        long ret = 0;

        while (end < chars.length) {
            if (chars[end] == 'a' || chars[end] == 'e' || chars[end] == 'i' || chars[end] == 'o' || chars[end] == 'u') {
                if (cnt[chars[end]] == 0) {
                    num--;
                }
                cnt[chars[end]]++;
            } else {
                flg++;
            }
            end++;

            while (num == 0 && flg >= k) {
                if (chars[start] == 'a' || chars[start] == 'e' || chars[start] == 'i' || chars[start] == 'o' || chars[start] == 'u') {
                    cnt[chars[start]]--;
                    if (cnt[chars[start]] == 0) {
                        num++;
                    }
                } else {
                    flg--;
                }
                start++;
            }

            ret += start;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 992. K 个不同整数的子数组
     * 元素种类数 <= k 的子数组数 - 元素种类数 <= k - 1 的子数组数.
     * @Date 21:38 2025/10/10
     * @Param [nums, k]
     **/
    public int subarraysWithKDistinct(int[] nums, int k) {
        return subarraysWithKDistinctFunc(nums, k) - subarraysWithKDistinctFunc(nums, k - 1);
    }

    // 找元素种类数 <= k 的非空子数组数
    public int subarraysWithKDistinctFunc(int[] nums, int k) {
        int n = nums.length;
        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        // cnt 记录当前窗口中各个元素的出现次数.
        int[] cnt = new int[n + 1];
        // ant 记录当前窗口中有几种不同的元素.
        int ant = 0;
        // ret 记录所有的子数组数.
        int ret = 0;

        while (end < nums.length) {
            if (cnt[nums[end]] == 0) {
                ant++;
            }
            cnt[nums[end]]++;
            end++;

            while (ant > k) {
                cnt[nums[start]]--;
                if (cnt[nums[start]] == 0) {
                    ant--;
                }
                start++;
            }

            ret += end - start;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 825. 适龄的朋友
     * @Date 15:03 2025/10/12
     * @Param [ages]
     **/
    public static int numFriendRequests(int[] ages) {
        Arrays.sort(ages);

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        // cnt 记录当前窗口中元素的出现次数.
        int[] cnt = new int[121];
        // ret 记录总数.
        int ret = 0;

        while (end < ages.length) {
            cnt[ages[end]]++;

            while (start < end && (0.5 * ages[end] + 7) >= ages[start]) {
                cnt[ages[start]]--;
                start++;
            }

            ret += (end - start) + (cnt[ages[end]] - 1);
            end++;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 825. 适龄的朋友II
     * @Date 16:03 2025/10/12
     * @Param [ages]
     **/
    public static int numFriendRequestsFunc(int[] ages) {
        // cnt 表示每个年龄的人数.
        int[] cnt = new int[121];
        for (int val : ages) {
            cnt[val]++;
        }

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        // num 表示当前窗口中总人数.
        int num = 0;
        // ret 表示请求总数.
        int ret = 0;

        while (end < cnt.length) {
            num += cnt[end];

            while (start <= end && (end * 0.5 + 7) >= start) {
                num -= cnt[start];
                start++;
            }

            if (start <= end) {
                ret += cnt[end] * num - cnt[end];
            }
            end++;
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 2401. 最长优雅子数组
     * @Date 16:46 2025/10/12
     * @Param [nums]
     * @return int
     **/
//    public int longestNiceSubarray(int[] nums) {
//
//    }


    /**
     * @return int
     * @Author 强仔不强
     * @Description 1156. 单字符重复子串的最大长度
     * @Date 16:48 2025/10/12
     * @Param [text]
     **/
    public static int maxRepOpt1(String text) {
        char[] chars = text.toCharArray();

        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            int val = map.getOrDefault(c, 0);
            map.put(c, val + 1);
        }

        int start = 0;
        int end = 0;
        HashMap<Character, Integer> cnt = new HashMap<>();
        int ret = 0;

        while (end < chars.length) {
            int val = cnt.getOrDefault(chars[end], 0);
            cnt.put(chars[end], val + 1);
            end++;

            while (isMove(end - start, cnt, map)) {
                int val2 = cnt.get(chars[start]);
                if (val2 == 1) {
                    cnt.remove(chars[start]);
                } else {
                    cnt.put(chars[start], val2 - 1);
                }
                start++;
            }

            ret = Math.max(ret, end - start);
        }

        return ret;
    }

    public static boolean isMove(int len, HashMap<Character, Integer> cnt, HashMap<Character, Integer> map) {
        if (cnt.size() == 1) {
            return false;
        }

        if (cnt.size() > 2) {
            return true;
        }

        char[] k = new char[2];
        int[] v = new int[2];
        int i = 0;

        for (Map.Entry<Character, Integer> entry : cnt.entrySet()) {
            k[i] = entry.getKey();
            v[i] = entry.getValue();
            i++;
        }

        for (int j = 0; j < 2; j++) {
            if (v[j] == 1 && map.get(k[1 - j]) >= len) {
                return false;
            }
        }

        return true;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 424. 替换后的最长重复字符
     * @Date 18:11 2025/10/12
     * @Param [s, k]
     **/
    public static int characterReplacement(String s, int k) {
        if (k >= s.length()) {
            return s.length();
        }

        char[] chars = s.toCharArray();

        List<Integer>[] cnt = new List[26];
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (cnt[c - 'A'] == null) {
                cnt[c - 'A'] = new ArrayList<>();
            }
            cnt[c - 'A'].add(i);
        }

        int ret = 0;

        for (List<Integer> list : cnt) {
            if (list == null) {
                continue;
            }
            int start = 0;
            int end = 0;

            while (end < list.size()) {
                int posRight = list.get(end);
                int posLeft = list.get(start);

                while ((posRight - posLeft + 1) - (end - start + 1) > k) {
                    start++;
                    posLeft = list.get(start);
                }

                int replace = (posRight - posLeft + 1) - (end - start + 1);
                int len = (posRight - posLeft + 1) + (k - replace);
                ret = Math.max(ret, len);
                if (ret >= s.length()) {
                    ret = s.length();
                    break;
                }

                end++;
            }
        }

        return ret;
    }

    public static int characterReplacementII(String s, int k) {
        int n = s.length();
        if (n < 2) {
            return n;
        }

        char[] chars = s.toCharArray();

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        int[] cnt = new int[26];
        int maxCnt = 0;
        int ret = 0;

        while (end < chars.length) {
            while (end < chars.length && maxCnt + k > end - start) {
                cnt[chars[end] - 'A']++;
                maxCnt = Math.max(maxCnt, cnt[chars[end] - 'A']);
                end++;
            }

            ret = Math.max(ret, end - start);

            cnt[chars[start] - 'A']--;
            start++;
        }

        return ret;
    }

    /**
     * @return void
     * @Author 强仔不强
     * @Description 344. 反转字符串
     * @Date 17:10 2025/10/13
     * @Param [s]
     **/
    public void reverseString(char[] s) {
        int n = s.length;

        int left = 0;
        int right = n - 1;

        while (left < right) {
            char c = s[left];
            s[left] = s[right];
            s[right] = c;
            left++;
            right--;
        }

        System.out.println(Arrays.toString(s));
    }

    /**
     * @return int[][]
     * @Author 强仔不强
     * @Description 3643. 垂直翻转子矩阵
     * @Date 17:20 2025/10/13
     * @Param [grid, x, y, k]
     **/
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        int left = x;
        int right = x + k - 1;

        while (left < right) {
            for (int i = y; i < y + k; i++) {
                int temp = grid[left][i];
                grid[left][i] = grid[right][i];
                grid[right][i] = temp;
            }
            left++;
            right--;
        }
        return grid;
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 345. 反转字符串中的元音字母
     * @Date 17:25 2025/10/13
     * @Param [s]
     **/
    public String reverseVowels(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');

        int left = 0;
        int right = n - 1;
        while (left < right) {
            while (left < right && !set.contains(chars[left])) {
                left++;
            }
            while (left < right && !set.contains(chars[right])) {
                right--;
            }

            if (left < right) {
                char c = chars[left];
                chars[left] = chars[right];
                chars[right] = c;
                left++;
                right--;
            }
        }

        return new String(chars);
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 125. 验证回文串
     * 0 -- 9  对应 48 -- 57
     * A -- Z  对应 65 -- 90
     * a -- z  对应 97 -- 122
     * @Date 14:49 2025/10/14
     * @Param [s]
     **/
    public static boolean isPalindrome(String s) {
        int n = s.length();

        s = s.toLowerCase();
        char[] chars = s.toCharArray();


        Set<Character> set = new HashSet<>();
        for (char i = '0'; i <= '9'; i++) {
            set.add(i);
        }
        for (char i = 'a'; i <= 'z'; i++) {
            set.add(i);
        }

        int left = 0;
        int right = n - 1;
        while (left < right) {
            while (left < right && !set.contains(chars[left])) {
                left++;
            }
            while (left < right && !set.contains(chars[right])) {
                right--;
            }

            if (left < right) {
                if (chars[left] != chars[right]) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1750. 删除字符串两端相同字符后的最短长度
     * @Date 10:57 2025/10/15
     * @Param [s]
     **/
    public int minimumLength(String s) {
        char[] chars = s.toCharArray();
        int n = s.length();

        int left = 0;
        int right = n - 1;

        while (left < right) {
            if (chars[left] != chars[right]) {
                break;
            }
            while (left < right && chars[left + 1] == chars[left]) {
                left++;
            }
            while (left < right && chars[right] == chars[right - 1]) {
                right--;
            }
            left++;
            right--;
        }

        return left > right ? 0 : right - left + 1;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2105. 给植物浇水 II
     * @Date 11:18 2025/10/15
     * @Param [plants, capacityA, capacityB]
     **/
    public static int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int n = plants.length;
        if (n == 1) {
            if (plants[0] > Math.max(capacityA, capacityB)) {
                return 1;
            }
            return 0;
        }

        int left = 0;
        int right = n - 1;
        // Alice 所需要的水.
        int waterA = 0;
        // Bob 所需要的水.
        int waterB = 0;
        int ret = 0;

        while (left < right) {
            waterA += plants[left];
            waterB += plants[right];
            left++;
            right--;
        }


        // Alice 需要 totalA 个整桶的水.
        int totalA = waterA / capacityA;
        ret += totalA - 1;
        if (waterA % capacityA != 0) {
            ret++;
        }


        int totalB = waterB / capacityB;
        ret += totalB - 1;
        if (waterB % capacityB != 0) {
            ret++;
        }

        if (left == right) {
            int leaveA = waterA % capacityA == 0 ? 0 : capacityA - (waterA % capacityA);
            int leaveB = waterB % capacityB == 0 ? 0 : capacityB - (waterB % capacityB);

            int leave = Math.max(leaveA, leaveB);
            if (leave < plants[left]) {
                ret++;
            }
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2105. 给植物浇水 II
     * @Date 12:00 2025/10/15
     * @Param [plants, capacityA, capacityB]
     **/
    public static int minimumRefillII(int[] plants, int capacityA, int capacityB) {
        int n = plants.length;
        int left = 0;
        int right = n - 1;
        // Alice 在浇 plants[i] 之前, 桶里剩余的水.
        int waterA = capacityA;
        int waterB = capacityB;
        int ret = 0;

        while (left < right) {
            if (waterA < plants[left]) {
                ret++;
                waterA = capacityA;
            }
            waterA -= plants[left];
            left++;

            if (waterB < plants[right]) {
                ret++;
                waterB = capacityB;
            }
            waterB -= plants[right];
            right--;
        }

        if (left == right) {
            int leave = Math.max(waterA, waterB);
            if (leave < plants[left]) {
                ret++;
            }
        }

        return ret;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 977. 有序数组的平方
     * @Date 15:34 2025/10/15
     * @Param [nums]
     **/
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];

        int left = 0;
        int right = n - 1;

        int i = n - 1;
        while (left <= right) {
            if (Math.abs(nums[right]) > Math.abs(nums[left])) {
                ret[i--] = (int) Math.pow(nums[right], 2);
                right--;
            } else {
                ret[i--] = (int) Math.pow(nums[left], 2);
                left++;
            }
        }

        return ret;
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @Author 强仔不强
     * @Description 658. 找到 K 个最接近的元素
     * @Date 11:20 2025/10/16
     * @Param [arr, k, x]
     **/
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        List<Integer> ret = new ArrayList<>();

        if (x < arr[0]) {
            for (int i = 0; i < k; i++) {
                ret.add(arr[i]);
            }
            return ret;
        }

        if (x > arr[n - 1]) {
            for (int i = n - k; i < n; i++) {
                ret.add(arr[i]);
            }
            return ret;
        }

        // index 最靠近 x 的元素的下标.
        int index = binarySearch(arr, x);
        // [left, right] 最靠近 x 的元素。
        int left = Math.max(index - k + 1, 0);
        int right = Math.min(index + k - 1, n - 1);

        // 缩小区间.
        while (right - left + 1 > k) {
            if (arr[right] - x >= x - arr[left]) {
                right--;
            } else {
                left++;
            }
        }

        for (int i = left; i <= right; i++) {
            ret.add(arr[i]);
        }

        return ret;
    }

    public static int findIndex(int[] arr, int x) {
        int n = arr.length;

        int index = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == x) {
                index = i;
                break;
            }

            if (arr[i] < x && arr[i + 1] > x) {
                index = x - arr[i] <= arr[i + 1] - x ? i : i + 1;
                break;
            }
        }

        return index;
    }

    public static int findIndexII(int[] arr, int x) {
        int n = arr.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == x) {
                return mid;
            }
            if (arr[mid] > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    // 二分查找
    public static int binarySearch(int[] arr, int x) {
        int n = arr.length;

        int left = 0;
        int right = n - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }


    public static int findIndexI(int[] arr, int x) {
        int n = arr.length;

        int left = 0;
        int right = n - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        if (right == 0) {
            return right;
        }

        return arr[right] - x < x - arr[right - 1] ? right : right - 1;
    }


    /**
     * @Author 强仔不强
     * @Description 1471. 数组中的 k 个最强值
     * @Date 13:42 2025/10/16
     * @Param [arr, k]
     * @return int[]
     **/
//    public int[] getStrongest(int[] arr, int k) {
//        int n = arr.length;
//        Arrays.sort(arr);
//
//        int[] ret = new int[k];
//        int m = arr[(n - 1) / 2];
//
//    }


    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 167. 两数之和 II - 输入有序数组
     * @Date 13:55 2025/10/16
     * @Param [numbers, target]
     **/
    public int[] twoSumII(int[] numbers, int target) {
        int n = numbers.length;
        int[] ret = new int[2];

        int left = 0;
        int right = n - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                ret[0] = left;
                ret[1] = right;
                break;
            }

            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return ret;
    }


    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 633. 平方数之和
     * @Date 14:04 2025/10/16
     * @Param [c]
     **/
    public boolean judgeSquareSum(int c) {
        int left = 0;
        int right = (int) Math.sqrt(c);
        while (left < right) {
            double sum = Math.pow(left, 2) + Math.pow(right, 2);
            if (sum == c) {
                return true;
            }

            if (sum < c) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2824. 统计和小于目标的下标对数目
     * @Date 14:17 2025/10/16
     * @Param [nums, target]
     **/
    public int countPairs(List<Integer> nums, int target) {
        int n = nums.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums.get(i);
        }

        Arrays.sort(arr);

        int left = 0;
        int right = n - 1;

        int ret = 0;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum < target) {
                ret += right - left;
                left++;
            } else {
                right--;
            }
        }

        return ret;
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description 2563. 统计公平数对的数目
     * @Date 16:02 2025/10/17
     * @Param [nums, lower, upper]
     **/
    public static long countFairPairs(int[] nums, int lower, int upper) {
        int n = nums.length;
        long total = (long) n * (n - 1) / 2;
        Arrays.sort(nums);

        long ret1 = countFairPairsFuncI(nums, lower);
        long ret2 = countFairPairsFuncII(nums, upper);

        return total - ret1 - ret2;

    }

    // 找 num[i] + num[j] < lower 的数对的数量.
    public static long countFairPairsFuncI(int[] nums, int lower) {
        int n = nums.length;

        int left = 0;
        int right = n - 1;
        long ret = 0;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < lower) {
                ret += right - left;
                left++;
            } else {
                right--;
            }
        }

        return ret;
    }

    // 找 num[i] + num[j] > upper的数对的数量.
    public static long countFairPairsFuncII(int[] nums, int upper) {
        int n = nums.length;

        int left = 0;
        int right = n - 1;
        long ret = 0;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum > upper) {
                ret += right - left;
                right--;
            } else {
                left++;
            }
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description LCP 28. 采购方案
     * @Date 16:31 2025/10/17
     * @Param [nums, target]
     **/
    public int purchasePlans(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);

        int left = 0;
        int right = n - 1;
        long ret = 0;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum <= target) {
                ret += right - left;
                left++;
            } else {
                right--;
            }
        }

        return (int) (ret % (1000000007));
    }

    /**
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @Author 强仔不强
     * @Description 15. 三数之和
     * @Date 16:52 2025/10/17
     * @Param [nums]
     **/
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) {
                break;
            }

            if (nums[i] + nums[n - 1] + nums[n - 2] < 0) {
                continue;
            }

            int left = i + 1;
            int right = n - 1;
            int target = nums[i] * (-1);

            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    ret.add(list);

                    left++;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }

                } else if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 16. 最接近的三数之和
     * @Date 17:37 2025/10/17
     * @Param [nums, target]
     **/
    public static int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);

        int ret = Integer.MAX_VALUE;

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int val = find(nums, i + 1, n - 1, target - nums[i]) + nums[i];

            ret = Math.abs(target - val) < Math.abs(target - ret) ? val : ret;

        }

        return ret;
    }

    public static int find(int[] nums, int left, int right, int tag) {
        if (nums[left] + nums[left + 1] > tag) {
            return nums[left] + nums[left + 1];
        }

        if (nums[right] + nums[right - 1] < tag) {
            return nums[right] + nums[right - 1];
        }

        long front = Integer.MIN_VALUE;
        long back = Integer.MAX_VALUE;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == tag) {
                return sum;
            }

            while (left < right && sum > tag) {
                back = Math.min(back, sum);
                right--;
                sum = nums[left] + nums[right];
            }

            while (left < right && sum < tag) {
                front = Math.max(front, sum);
                left++;
                sum = nums[left] + nums[right];
            }
        }

        return tag - front < back - tag ? (int) front : (int) back;
    }

    public static int threeSumClosestII(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);

        int minDiff = 13001;
        int ret = 0;

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int sum = nums[i] + nums[i + 1] + nums[i + 2];
            if (sum > target) {
                if (sum - target < minDiff) {
                    ret = sum;
                }
                break;
            }

            sum = nums[i] + nums[n - 2] + nums[n - 1];
            if (sum < target) {
                if (target - sum < minDiff) {
                    minDiff = target - sum;
                    ret = sum;
                }
                continue;
            }

            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return target;
                }

                if (Math.abs(sum - target) < minDiff) {
                    minDiff = Math.abs(sum - target);
                    ret = sum;
                }

                if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return ret;
    }


    /**
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @Author 强仔不强
     * @Description 18. 四数之和
     * @Date 14:42 2025/10/19
     * @Param [nums, target]
     **/
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> ret = new ArrayList<>();

        if (n < 4) {
            return ret;
        }

        Arrays.sort(nums);

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            if (((long) nums[i]) + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }

            if (((long) nums[i]) + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) {
                continue;
            }

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                if (((long) nums[i]) + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }

                if (((long) nums[i]) + nums[j] + nums[n - 1] + nums[n - 2] < target) {
                    continue;
                }


                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    long sum = nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        ret.add(list);

                        left++;
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }

                        right--;
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (sum > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 611. 有效三角形的个数
     * @Date 15:28 2025/10/19
     * @Param [nums]
     **/
    public static int triangleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int ret = 0;
        for (int i = 0; i < n - 2; i++) {
            ret += triangle(nums, i + 1, n - 1, nums[i]);
        }
        return ret;
    }

    public static int triangle(int[] nums, int left, int right, int target) {
        if (left >= right) {
            return 0;
        }

        if (target + nums[left] > nums[right]) {
            return ((right - left) * 2 - 1) + triangle(nums, left + 1, right - 1, target);
        }
        return triangle(nums, left + 1, right, target) + triangle(nums, left, right - 1, target) -
                triangle(nums, left + 1, right - 1, target);
    }

    public static int triangleNumberII(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int ret = 0;
        // 枚举最长边 + 相向双指针.
        for (int i = 2; i < n; i++) {

            if (nums[i - 1] + nums[i - 2] <= nums[i]) {
                continue;
            }

            if (nums[0] + nums[1] > nums[i]) {
                ret += i * (i - 1) / 2;
                continue;
            }

            int left = 0;
            int right = i - 1;

            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    ret += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ret;
    }

    public static int triangleNumberI(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int ret = 0;
        // 枚举最短边 + 滑动窗口.
        for (int i = 0; i < n - 2; i++) {
            if (nums[n - 1] - nums[i + 1] < nums[i]) {
                ret += (n - i - 2) * 2 - 1;
                continue;
            }

            // [left, right)
            int left = i + 1;
            int right = i + 1;

            while (right < n) {
                while (nums[right] - nums[left] >= nums[i]) {
                    left++;
                }

                ret += right - left;
                right++;
            }
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1577. 数的平方等于两数乘积的方法数
     * @Date 21:05 2025/10/19
     * @Param [nums1, nums2]
     **/
    public int numTriplets(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int ret = numTripletsI(nums1, nums2) + numTripletsI(nums2, nums1);
        return ret;
    }

    public static int numTripletsI(int[] numsF, int[] numsB) {
        int n1 = numsF.length;
        int n2 = numsB.length;
        int ret = 0;
        int curRet = 0;

        if (n2 < 2) {
            return ret;
        }

        for (int i = 0; i < n1; i++) {
            if (i > 0 && numsF[i] == numsF[i - 1]) {
                ret += curRet;
                continue;
            }
            curRet = 0;

            long target = (long) numsF[i] * numsF[i];

            if ((long) numsB[n2 - 1] * numsB[n2 - 2] < target) {
                break;
            }

            if ((long) numsB[0] * numsB[1] > target) {
                continue;
            }

            int left = 0;
            int right = n2 - 1;

            while (left < right) {
                long mul = (long) numsB[left] * numsB[right];
                if (mul == target) {

                    if (numsB[left] == numsB[right]) {
                        int cnt = right - left + 1;
                        curRet += cnt * (cnt - 1) / 2;
                        break;
                    }

                    int cntL = 0;
                    int valL = numsB[left];
                    while (numsB[left] == valL) {
                        cntL++;
                        left++;
                    }

                    int cntR = 0;
                    int valR = numsB[right];
                    while (numsB[right] == valR) {
                        cntR++;
                        right--;
                    }

                    curRet += cntL * cntR;

                } else if (mul > target) {
                    right--;
                } else {
                    left++;
                }
            }

            ret += curRet;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 923. 三数之和的多种可能
     * @Date 14:58 2025/10/22
     * @Param [arr, target]
     **/
    public static int threeSumMulti(int[] arr, int target) {
        int n = arr.length;
        Arrays.sort(arr);

        long ret = 0;

        for (int i = 0; i < n - 2; i++) {

            if (arr[i] + arr[i + 1] + arr[i + 2] > target) {
                break;
            }

            if (arr[i] + arr[n - 1] + arr[n - 2] < target) {
                continue;
            }

            int left = i + 1;
            int right = n - 1;


            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == target) {
                    boolean flg = left == i + 1;

                    if (arr[left] == arr[right]) {
                        int cnt = right - left + 1;
                        ret += (long) cnt * (cnt - 1) / 2;

                        break;
                    }

                    int valL = arr[left];
                    int cntL = 0;
                    while (arr[left] == valL) {
                        cntL++;
                        left++;
                    }

                    int valR = arr[right];
                    int cntR = 0;
                    while (arr[right] == valR) {
                        cntR++;
                        right--;
                    }

                    ret += (long) cntL * cntR;
                } else if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }

        }

        return (int) (ret % 1000000007);
    }


    /**
     * @return int
     * @Author 强仔不强
     * @Description 948. 令牌放置
     * @Date 16:33 2025/10/23
     * @Param [tokens, power]
     **/
    public int bagOfTokensScore(int[] tokens, int power) {
        int n = tokens.length;
        Arrays.sort(tokens);

        int score = 0;
        int left = 0;
        int right = n - 1;

        while (left < right) {
            if (tokens[left] <= power) {
                power -= tokens[left];
                score++;
                left++;
            } else if (score > 0) {
                power += tokens[right];
                score--;
                right--;
            } else {
                break;
            }
        }

        if (left < n && tokens[left] <= power) {
            score++;
        }

        return score;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 11. 盛最多水的容器
     * @Date 21:44 2025/10/23
     * @Param [height]
     **/
    public int maxArea(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n - 1;

        int h = -1;
        int ret = Integer.MIN_VALUE;

        while (left < right) {
            h = Math.min(height[left], height[right]);
            int area = (right - left) * h;
            ret = Math.max(ret, area);

            while (left < right && height[left] <= h) {
                left++;
            }

            while (left < right && height[right] <= h) {
                right--;
            }
        }

        return ret;
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 1616. 分割两个字符串得到回文串
     * @Date 22:00 2025/10/23
     * @Param [a, b]
     **/
    public boolean checkPalindromeFormation(String a, String b) {
        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();

        return check(charsA, charsB) || check(charsB, charsA);
    }

    public boolean check(char[] front, char[] back) {
        int n = front.length;

        int left = 0;
        int right = n - 1;

        while (left < right) {
            if (front[left] == back[right]) {
                left++;
                right--;
            } else {
                break;
            }
        }

        int start = left;
        int end = right;

        boolean bookFront = true;
        while (left < right) {
            if (front[left] == front[right]) {
                left++;
                right--;
            } else {
                bookFront = false;
                break;
            }
        }

        if (bookFront) {
            return true;
        }

        while (start < end) {
            if (back[start] == back[end]) {
                start++;
                end--;
            } else {
                return false;
            }
        }

        return true;

    }

    public boolean checkII(char[] front, char[] back) {
        int n = front.length;

        // 找前后缀的最长匹配.
        int left = 0;
        int right = n - 1;
        while (left < right && front[left] == back[right]) {
            left++;
            right--;
        }

        return isPalindrome(front, left, right) || isPalindrome(back, left, right);
    }

    public boolean isPalindrome(char[] chars, int left, int right) {
        while (left < right && chars[left] == chars[right]) {
            left++;
            right--;
        }
        return left >= right;
    }


    /**
     * @return int
     * @Author 强仔不强
     * @Description 1498. 满足条件的子序列数目
     * @Date 17:44 2025/10/24
     * @Param [nums, target]
     **/
    public static int numSubseq(int[] nums, int target) {
        final int MOD = 1000000007;
        // F[i] 表示 2^i % MOD.
        final int[] F = new int[100000];
        F[0] = 1;
        for (int i = 1; i < 100000; i++) {
            F[i] = F[i - 1] * 2 % MOD;
        }

        int n = nums.length;
        Arrays.sort(nums);

        int left = 0;
        int right = n - 1;
        long ret = 0;

        while (left <= right) {
            int sum = nums[left] + nums[right];
            if (sum > target) {
                right--;
            } else {
                ret += F[left - right];
                left++;
            }
        }

        return (int) (ret % MOD);
    }


    /**
     * @return double
     * @Author 强仔不强
     * @Description 50. Pow(x, n)
     * @Date 16:50 2025/10/25
     * @Param [x, n]
     **/
    public static double myPow(double x, int n) {
        boolean flg = false;
        if (n < 0) {
            flg = true;
            n = -n;
        }

        double ret = 1;
        for (int i = 0; i < n; i++) {
            ret *= x;
        }

        if (flg) {
            ret = 1.0 / ret;
        }
        return ret;
    }

    /**
     * @return double
     * @Author 强仔不强
     * @Description 快速幂.
     * @Date 17:06 2025/10/25
     * @Param [x, n]
     **/
    public static double myPowI(double x, int n) {
        double ret = 1;

        // 防止 n = -2^31 时, int 类型（-2^31 ~ 2^31 - 1）中, -n = -2^31
        long N = n;

        if (N < 0) {
            x = 1.0 / x;
            N = -N;
        }

        while (N > 0) {
            if ((N & 1) == 1) {
                ret *= x;
            }
            x *= x;
            N >>= 1;
        }

        return ret;
    }

    /**
     * @return double
     * @Author 强仔不强
     * @Description 快速幂.
     * @Date 17:26 2025/10/25
     * @Param [x, n]
     **/
    public static double myPowII(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1.0 / x;
            N = -N;
        }

        return pow(x, N);
    }

    public static double pow(double x, long n) {
        if (n == 0) {
            return 1;
        }

        double val = pow(x, n / 2);
        val *= val;

        if (n % 2 == 1) {
            val *= x;
        }

        return val;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 42. 接雨水
     * @Date 21:21 2025/10/25
     * @Param [height]
     **/
    public static int trap(int[] height) {
        int n = height.length;

        int ret = 0;
        int left = 0;
        int right = n - 1;

        int floor = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int val = map.getOrDefault(height[i], 0);
            map.put(height[i], val + 1);
        }

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int totalArea = (h - floor) * (right - left + 1);
            int removeArea = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int key = entry.getKey();
                int val = entry.getValue();

                if (key >= h) {
                    removeArea += (h - floor) * val;
                } else if (key > floor) {
                    removeArea += (key - floor) * val;
                }
            }

            floor = h;
            ret += totalArea - removeArea;


            while (left < right && height[left] <= floor) {
                map.put(height[left], map.get(height[left]) - 1);
                left++;
            }
            while (left < right && height[right] <= floor) {
                map.put(height[right], map.get(height[right]) - 1);
                right--;
            }
        }

        return ret;
    }


    public static int trapII(int[] height) {
        int n = height.length;

        int ret = 0;
        int left = 0;
        int right = n - 1;

        int floor = 0;

        // 超过 floor 的 高度及出现次数.
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (height[i] > floor) {
                int val = map.getOrDefault(height[i], 0);
                map.put(height[i], val + 1);
            }
        }


        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int totalArea = (h - floor) * (right - left + 1);
            int removeArea = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int key = entry.getKey();
                int val = entry.getValue();

                if (key > h) {
                    removeArea += (h - floor) * val;
                } else {
                    removeArea += (key - floor) * val;
                    entry.setValue(0);
                }
            }

            floor = h;
            ret += totalArea - removeArea;

            while (left < right && height[left] <= floor) {
                left++;
            }
            while (left < right && height[right] <= floor) {
                right--;
            }
        }

        return ret;
    }

    public static int trapIII(int[] height) {
        int n = height.length;
        int[] pre_max = new int[n];
        int[] suf_max = new int[n];

        pre_max[0] = height[0];
        for (int i = 1; i < n; i++) {
            pre_max[i] = Math.max(pre_max[i - 1], height[i]);
        }

        suf_max[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suf_max[i] = Math.max(suf_max[i + 1], height[i]);
        }

        int ret = 0;
        for (int i = 1; i < n - 1; i++) {
            int h = Math.min(pre_max[i - 1], suf_max[i + 1]);
            int area = h - height[i];
            ret += Math.max(area, 0);
        }

//        for (int i = 0; i < n; i++) {
//            int h = Math.min(pre_max[i], suf_max[i]);
//            ret += h - height[i];
//        }


        return ret;
    }


    public static int trapIV(int[] height) {
        int n = height.length;
        int pre_max = height[0];
        int suf_max = height[n - 1];

        int ret = 0;

        int left = 0;
        int right = n - 1;

        while (left <= right){
            if(pre_max <= suf_max){
                ret += pre_max - height[left];
                left++;
                pre_max = Math.max(pre_max, height[left]);
            } else {
                ret += suf_max - height[right];
                right--;
                suf_max = Math.max(suf_max, height[right]);
            }
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 3649. 完美对的数目
     * @Date 19:47 2025/10/27
     * @Param [nums]
     * @return long
     **/
//    public long perfectPairs(int[] nums) {
//
//    }

    /**
     * @Author 强仔不强
     * @Description 1574. 删除最短的子数组使剩余数组有序
     * @Date 19:51 2025/10/27
     * @Param [arr]
     * @return int
     **/
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;

        // (left, right) 表示删掉的子数组.
        int left = 0;
        int right = n - 1;
        while (right > 0 && arr[right - 1] <= arr[right]){
            right--;
        }
        if(right == 0){
            return 0;
        }

        int ret = right;
        while (left == 0 || arr[left - 1] <= arr[left]){
            while (right < n && arr[left] > arr[right]) {
                right++;
            }

            ret = Math.min(ret, right - left - 1);
            left++;
        }

        return ret;
    }

    public int findLengthOfShortestSubarrayII(int[] arr) {
        int n = arr.length;

        int left = 0;
        int right = n - 1;
        while (left < n - 1 && arr[left + 1] > arr[left]){
            left++;
        }
        if(left == n - 1){
            return 0;
        }

        int ret = n - left - 1;
        while (right == n - 1 || arr[right] <= arr[right + 1]){
            while (left >= 0 && arr[right] < arr[left]){
                left--;
            }

            ret = Math.min(ret, right - left - 1);
            right--;
        }

        return ret;

    }

    /**
     * @Author 强仔不强
     * @Description 2972. 统计移除递增子数组的数目 II
     * @Date 16:27 2025/10/28
     * @Param [nums]
     * @return long
     **/
    public long incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        int left = 0;

        int right = n - 1;
        while (right > 0 && nums[right - 1] < nums[right]){
            right--;
        }

        if(right == 0){
            return (long) n * (n + 1) / 2;
        }

        long ret = n - right + 1;
        while (left == 0 || nums[left] > nums[left - 1]){
            while (right < n && nums[left] >= nums[right]){
                right++;
            }

            ret += n - right + 1;
            left++;
        }

        return ret;
    }

    public long incremovableSubarrayCountII(int[] nums) {
        int n = nums.length;

        int right = n - 1;
        int left = 0;

        while (left < n - 1 && nums[left + 1] > nums[left]){
            left++;
        }
        if(left == n - 1){
            return (long) n * (n + 1) / 2;
        }

        long ret = left + 1 + 1;

        while (right == n - 1 || nums[right] < nums[right + 1]){
            while (left >= 0 && nums[left] >= nums[right]){
                left--;
            }

            ret += left + 1 + 1;
            right--;
        }

        return ret;
    }


    /**
     * @Author 强仔不强
     * @Description 581. 最短无序连续子数组
     * @Date 17:12 2025/10/28
     * @Param [nums]
     * @return int
     **/
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int[] copy = Arrays.copyOf(nums, n);

        Arrays.sort(nums);

        int left = 0;
        int right = n - 1;

        while (left <= right && nums[left] == copy[left]) {
            left++;
        }
        while (left <= right && nums[right] == copy[right]) {
            right--;
        }

        return right - left + 1;
    }


    /**
     * @Author 强仔不强
     * @Description 581. 最短无序连续子数组
     * @Date 17:43 2025/10/28
     * @Param [nums]
     * @return int
     **/
    public static int findUnsortedSubarrayII(int[] nums) {
        int n = nums.length;
        int left = 0;
        while (left == 0 || (left < n) && (nums[left] >= nums[left - 1])){
            left++;
        }
        if(left == n){
            return 0;
        }

        int right = n - 1;
        while (right == n - 1 || nums[right] <= nums[right + 1]){
            right--;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        if(left >= right){
            max = nums[right];
            min = nums[left];
        } else {
            for (int i = left; i <= right; i++) {
                max = Math.max(nums[i], max);
                min = Math.min(nums[i], min);
            }
        }

        // [start, end] 表示更新的数组.
        int start = 0;
        int end = n - 1;

        while (start < end && nums[start] <= min){
            start++;
        }
        while (start < end && nums[end] >= max){
            end--;
        }

        return end - start + 1;
    }

    /**
     * @Author 强仔不强
     * @Description 
     * @Date 14:19 2025/10/31
     * @Param [nums]
     * @return int
     **/
    public static int findUnsortedSubarrayIII(int[] nums){
        int n = nums.length;

        // [left, right] 为排序的最短子数组.
        // 找 right.
        int right = n - 1;
        while (right > 0 && nums[right - 1] <= nums[right]){
            right--;
        }
        if(right == 0){
            return 0;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= right; i++) {
            max = Math.max(max, nums[i]);
        }

        while (right < n - 1 && nums[right + 1] < max){
            right++;
        }

        // 找 left.
        int left = 0;
        while (left < n - 1 && nums[left + 1] >= nums[left]){
            left++;
        }

        int min = Integer.MAX_VALUE;
        for (int i = n - 1; i >= left; i--) {
            min = Math.min(min, nums[i]);
        }

        while (left >= 1 && nums[left - 1] > min){
            left--;
        }

        return right - left + 1;
    }

    public static int findUnsortedSubarrayIV(int[] nums){
        int n = nums.length;

        // [left, right] 表示修改的最短子数组.
        int right = -1;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            if(nums[i] < max){
                right = i;
            }
        }

        if(right == -1){
            return 0;
        }

        // 找 left.
        int left = n;
        int min = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            if(nums[i] > min){
                left = i;
            }
        }

        return right - left + 1;
    }

    /**
     * @Author 强仔不强
     * @Description 27. 移除元素
     * @Date 16:14 2025/10/31
     * @Param [nums, val]
     * @return int
     **/
    public int removeElement(int[] nums, int val) {
        int n = nums.length;

        int left = 0;
        int right = n - 1;


        while (left < right){
            while (left < right && nums[left] != val){
                left++;
            }

            while (left < right && nums[right] == val){
                right--;
            }

            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }

        if(left == right && nums[left] != val){
            left++;
        }

        return left;
    }

    public int removeElementII(int[] nums, int val) {
        int stackSize = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != val){
                nums[stackSize] = nums[i];
                stackSize++;
            }
        }
        return stackSize;
    }

    public int removeElementIII(int[] nums, int val) {
        int n = nums.length;

        int left = 0;
        int right = n - 1;

        while (left < right){
            if(nums[left] == val){
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }

        if(left <= right && nums[left] != val){
            left++;
        }

        return left;
    }
    
    /**
     * @Author 强仔不强
     * @Description 26. 删除有序数组中的重复项
     * @Date 17:10 2025/10/31
     * @Param [nums]
     * @return int
     **/
    public int removeDuplicates(int[] nums) {
        int stackSize = 1;
        for (int i = 1; i < nums.length; i++) {
            // 将 nums 看作栈, 判断条件可以写作 nums[i] != nums[stackSize - 1]
            if(nums[i] != nums[i - 1]){
                nums[stackSize++] = nums[i];
            }
        }

        return stackSize;
    }
    
    /**
     * @Author 强仔不强
     * @Description 80. 删除有序数组中的重复项 II
     * @Date 17:24 2025/10/31
     * @Param [nums]
     * @return int
     **/
    public static int removeDuplicatesII(int[] nums) {
        if(nums.length <= 2){
            return nums.length;
        }

        int stackSize = 2;
        int val1 = nums[0];
        int val2 = nums[1];

        for (int i = 2; i < nums.length; i++) {
            if(nums[i] != val1){
                nums[stackSize++] = nums[i];
            }

            val1 = val2;
            val2 = nums[i];
        }

        return stackSize;
    }

    // 将 nums 看作栈.
    public static int removeDuplicatesIII(int[] nums) {
        if(nums.length <= 2){
            return nums.length;
        }

        int stackSize = 2;
        for (int i = 2; i < nums.length; i++) {
            if(nums[i] != nums[stackSize - 2]){
                nums[stackSize++] = nums[i];
            }
        }

        return stackSize;
    }

    /**
     * @Author 强仔不强
     * @Description 2273. 移除字母异位词后的结果数组
     * @Date 17:18 2025/11/1
     * @Param [words]
     * @return java.util.List<java.lang.String>
     **/
    public static List<String> removeAnagrams(String[] words) {
        int stackSize = 1;
        int[] cnt = new int[26];
        for (char ch : words[0].toCharArray()){
            cnt[ch - 'a']++;
        }

        for (int i = 1; i < words.length; i++) {
            int[] nums = new int[26];
            for (char c : words[i].toCharArray()){
                nums[c - 'a']++;
            }


            for (int j = 0; j < 26; j++) {
                if(nums[j] != cnt[j]){
                    words[stackSize++] = words[i];
                    cnt = nums;
                }
            }

        }

        List<String> ret = new ArrayList<>();
        for (int i = 0; i < stackSize; i++) {
            ret.add(words[i]);
        }

        return ret;
    }

    public static List<String> removeAnagramsII(String[] words) {
        List<String> ret = new ArrayList<>();
        ret.add(words[0]);

        char[] chars = words[0].toCharArray();
        Arrays.sort(chars);

        for (int i = 1; i < words.length; i++) {
            char[] ch = words[i].toCharArray();
            Arrays.sort(ch);

            if(!Arrays.equals(chars, ch)){
                ret.add(words[i]);
                chars = ch;
            }
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 3684. 至多 K 个不同元素的最大和
     * @Date 17:52 2025/11/1
     * @Param [nums, k]
     * @return int[]
     **/
    public int[] maxKDistinct(int[] nums, int k) {
        int n = nums.length;
        // 数组排序（降序）
        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[j + 1]){
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }

        int stackSize = 1;
        for (int i = 1; i < n; i++) {
            if(stackSize == k){
                break;
            }

            if(nums[i] != nums[stackSize - 1]){
                nums[stackSize++] = nums[i];
            }
        }

        return Arrays.copyOf(nums, stackSize);
    }


    public int[] maxKDistinctII(int[] nums, int k) {
        // 排序
        Arrays.sort(nums);

        // 去重
        int stackSize = 1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[stackSize - 1]){
                nums[stackSize++] = nums[i];
            }
        }

        int size = Math.min(stackSize, k);
        int[] ret = new int[size];
        for (int i = 0; i < size; i++) {
            ret[i] = nums[stackSize - 1 - i];
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 283. 移动零
     * @Date 20:25 2025/11/1
     * @Param [nums]
     * @return void
     **/
    public void moveZeroes(int[] nums) {
        int n = nums.length;

        int stackSize = 0;
        for (int i = 0; i < n; i++) {
            if(nums[i] != 0){
                nums[stackSize++] = nums[i];
            }
        }

        for (int i = stackSize; i < n; i++) {
            nums[i] = 0;
        }

    }


    public void moveZeroesII(int[] nums) {
        int n = nums.length;

        int left = 0;
        int right = 1;

        while (right < n){
            while (left < n && nums[left] != 0){
                left++;
            }
            if(left >= right) {
                right = left + 1;
            }

            while (right < n && nums[right] == 0){
                right++;
            }

            if(right < n){
                nums[left] = nums[right];
                nums[right] = 0;
            }
        }
    }

    public void moveZeroesIII(int[] nums) {
        int n = nums.length;
        // [left, right) 表示全为 0 的子数组.
        int left = 0;
        int right = 0;

        while (right < n){
            if(nums[right] != 0){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }
    }

    /**
     * @Author 强仔不强
     * @Description 905. 按奇偶排序数组
     * @Date 21:37 2025/11/1
     * @Param [nums]
     * @return int[]
     **/
    public int[] sortArrayByParity(int[] nums) {
        int n = nums.length;

        int left = 0;
        int right = n - 1;

        while (left < right){
            while (left < right && nums[left] % 2 == 0){
                left++;
            }
            while (left < right && nums[right] % 2 != 0){
                right--;
            }

            if(left < right){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }

        return nums;
    }

    // II III 同理.
    public int[] sortArrayByParityII(int[] nums) {
        int n = nums.length;

        int stackSize = 0;
        for (int i = 0; i < n; i++) {
            if(nums[i] % 2 == 0){
                int temp = nums[stackSize];
                nums[stackSize] = nums[i];
                nums[i] = temp;
                stackSize++;
            }
        }

        return nums;
    }


    public int[] sortArrayByParityIII(int[] nums) {
        int n = nums.length;

        // [left, right) 表示全为奇数的子数组.
        int left = 0;
        int right = 0;

        while (right < n){
            if(nums[right] % 2 == 0){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }

        return nums;
    }

    /**
     * @Author 强仔不强
     * @Description 922. 按奇偶排序数组 II
     * @Date 21:59 2025/11/1
     * @Param [nums]
     * @return int[]
     **/
    public int[] sortArrayByParityIIV(int[] nums) {
        int n = nums.length;

        int left = 0;
        int right = 1;

        while (left < n && right < n){
            if(nums[left] % 2 == 0){
                left += 2;
                continue;
            }
            if(nums[right] % 2 != 0){
                right += 2;
                continue;
            }

            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

        }

        return nums;
    }

    /**
     * @Author 强仔不强
     * @Description 3467. 将数组按照奇偶性转化
     * @Date 10:23 2025/11/4
     * @Param [nums]
     * @return int[]
     **/
    public int[] transformArray(int[] nums) {
        int n = nums.length;

        int stackSize = 0;
        for (int i = 0; i < n; i++) {
            if(nums[i] % 2 == 0){
                nums[i] = nums[stackSize];
                nums[stackSize] = 0;
            } else {
                nums[i] = 1;
            }

        }

        return nums;
    }

    /**
     * @Author 强仔不强
     * @Description 2460. 对数组执行操作
     * @Date 10:45 2025/11/4
     * @Param [nums]
     * @return int[]
     **/
    public int[] applyOperations(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            if(nums[i] == nums[i + 1]) {
                nums[i] = nums[i] * 2;
                nums[i + 1] = 0;
            }
        }

        int stackSize = 0;
        for (int i = 0; i < n; i++) {
            if(nums[i] != 0){
                int temp = nums[stackSize];
                nums[stackSize++] = nums[i];
                nums[i] = temp;
            }
        }

        return nums;
    }

    /**
     * @Author 强仔不强
     * @Description 75. 颜色分类
     * @Date 11:01 2025/11/4
     * @Param [nums]
     * @return void
     **/
    public void sortColors(int[] nums) {
        int n = nums.length;

        int stcakSize = 0;
        for (int i = 0; i < n; i++) {
            if(nums[i] == 0){
                int temp = nums[stcakSize];
                nums[stcakSize++] = 0;
                nums[i] = temp;
            }
        }

        int size = stcakSize;
        for (int i = stcakSize; i < n; i++) {
            if(nums[i] == 1){
                int temp = nums[size];
                nums[size++] = 1;
                nums[i] = temp;
            }
        }

    }


    // 插入排序
    public void sortColorsII(int[] nums) {
        int n = nums.length;

        int p0 = 0;
        int p1 = 0;
        for (int i = 1; i < n; i++) {
            int val = nums[i];

            nums[i] = 2;
            if(val == 1){
                nums[p1++] = 1;
            }
            if(val == 0){
                nums[p0++] = 0;
                nums[p1++] = 1;
            }
        }
    }

    /**
     * @Author 强仔不强
     * @Description 1089. 复写零
     * @Date 17:13 2025/11/4
     * @Param [arr]
     * @return void
     **/
    public void duplicateZeros(int[] arr) {
        int n = arr.length;

        int left = 0;
        int right = n - 1;
        while (left < right){
            if(arr[left] == 0){
                right--;
            }
            left++;
        }

        int k = n - 1;
        if(left == right && arr[left] == 0){
            arr[k--] = 0;
            right--;
        }

        while (right >= 0){
            if(arr[right] == 0){
                arr[k--] = 0;
            }
            arr[k--] = arr[right];
            right--;
        }

    }

    /**
     * @Author 强仔不强
     * @Description 373. 查找和最小的 K 对数字
     * @Date 21:46 2025/11/7
     * @Param [nums1, nums2, k]
     * @return java.util.List<java.util.List<java.lang.Integer>>
     **/
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        PriorityQueue<List<Integer>> queue = new PriorityQueue<>(k, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                int val1 = o1.get(0) + o1.get(1);
                int val2 = o2.get(0) + o2.get(1);
                return val2 - val1;
            }
        });

        for (int i = 0; i < n1; i++) {
            if(queue.size() >= k){
                List<Integer> l = queue.peek();
                int val1 = l.get(0) + l.get(1);

                if(nums1[i] + nums2[0] >= val1){
                    break;
                }
            }


            for (int j = 0; j < n2; j++) {
                if(queue.size() < k){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums1[i]);
                    list.add(nums2[j]);
                    queue.offer(list);
                    continue;
                }

                List<Integer> l = queue.peek();
                int val1 = l.get(0) + l.get(1);

                int val2 = nums1[i] + nums2[j];
                if(val2 < val1){
                    queue.poll();

                    List<Integer> list = new ArrayList<>();
                    list.add(nums1[i]);
                    list.add(nums2[j]);
                    queue.offer(list);
                } else {
                    break;
                }
            }
        }

        List<List<Integer>> ret = new ArrayList<>();
        while (!queue.isEmpty()){
            ret.add(queue.poll());
        }

        return ret;
    }

    // 373. 查找和最小的 K 对数字
    public List<List<Integer>> kSmallestPairsII(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        // 小根堆, 放 元素的位置下标 （i, j）
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int val1 = nums1[o1[0]] + nums2[o1[1]];
                int val2 = nums1[o2[0]] + nums2[o2[1]];
                return val1 - val2;
            }
        });

        // （0, 0） 是和最小的数对, 入堆.
        int[] first = {0, 0};
        queue.offer(first);

        // book 记录已经入堆的数对.  内存过大.
//        boolean[][] book = new boolean[n1][n2];
//        book[0][0] = true;

        // set 记录已经入堆的数对.  （i, j） 用 i * n2 + j 表示.
        Set<Long> set = new HashSet<>();
        set.add((long)0);

        List<List<Integer>> ret = new ArrayList<>(k);

        while (k > 0){
            // 取出当前 和最小 的数对 （i, j）;
            int[] arr = queue.poll();
            int i = arr[0];
            int j = arr[1];

            List<Integer> list = new ArrayList<>();
            list.add(nums1[i]);
            list.add(nums2[j]);
            ret.add(list);
            k--;

            // 和最小的数对 可能是 （i + 1, j） 或 （i, j + 1）, 入堆.
            long book1 = (long)(i + 1) * n2  + j;
            if(i + 1 < n1 && !set.contains(book1)){
                int[] a = {i + 1, j};
                queue.offer(a);
                set.add(book1);
            }

            long book2 = (long)(i) * n2 + (j + 1);
            if(j + 1 < n2 && !set.contains(book2)){
                int[] a = {i, j + 1};
                queue.offer(a);
                set.add(book2);
            }

        }

        return ret;
    }

    public List<List<Integer>> kSmallestPairsIII(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        // 小根堆, 放 元素的位置下标 （i, j）
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int val1 = nums1[o1[0]] + nums2[o1[1]];
                int val2 = nums1[o2[0]] + nums2[o2[1]];
                return val1 - val2;
            }
        });

        // 将 （0, i） 全部入堆.
        for (int i = 0; i < n2; i++) {
            queue.offer(new int[]{0, i});
        }

        List<List<Integer>> ret = new ArrayList<>(k);
        while (k > 0){
            int[] arr = queue.poll();
            int i = arr[0];
            int j = arr[1];
            List<Integer> list = new ArrayList<>();
            list.add(nums1[i]);
            list.add(nums2[j]);
            ret.add(list);
            k--;

            if(i + 1 < n1){
                queue.offer(new int[]{i + 1, j});
            }
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 2109. 向字符串添加空格  双指针.
     * @Date 20:16 2025/11/10
     * @Param [s, spaces]
     * @return java.lang.String
     **/
    public String addSpaces(String s, int[] spaces) {
        char[] chars = s.toCharArray();

        int k = 0;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            if(k < spaces.length && i == spaces[k]){
                buffer.append(' ');
                k++;
            }
            buffer.append(chars[i]);

        }

        return buffer.toString();
    }

    // 拼接字符串.
    public String addSpacesII(String s, int[] spaces) {
        int n = s.length();
        char[] chars = s.toCharArray();

        StringBuffer buffer = new StringBuffer();

        buffer.append(chars, 0, spaces[0]);
        buffer.append(' ');

        for (int i = 1; i < spaces.length; i++) {
            buffer.append(chars, spaces[i - 1], spaces[i] - spaces[i - 1]);
            buffer.append(' ');
        }

        buffer.append(chars, spaces[spaces.length - 1], n - spaces[spaces.length - 1]);

        return buffer.toString();
    }


    /**
     * @Author 强仔不强
     * @Description 2540. 最小公共值
     * @Date 20:32 2025/11/10
     * @Param [nums1, nums2]
     * @return int
     **/
    public int getCommon(int[] nums1, int[] nums2) {
        int s1 = 0;
        int n1 = nums1.length;

        int s2 = 0;
        int n2 = nums2.length;

        int ret = -1;
        while (s1 < n1 && s2 < n2){
            if(nums1[s1] == nums2[s2]){
                ret = nums1[s1];
                break;
            }

            if(nums1[s1] < nums2[s2]){
                s1++;
            } else {
                s2++;
            }

        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 88. 合并两个有序数组
     * @Date 19:59 2025/11/12
     * @Param [nums1, m, nums2, n]
     * @return void
     **/
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] ret = new int[m + n];
        int s1 = 0;
        int s2 = 0;

        int k = 0;
        while (s1 < m && s2 < n){
            if(nums1[s1] <= nums2[s2]){
                ret[k++] = nums1[s1++];
            } else {
                ret[k++] = nums2[s2++];
            }
        }

        while (s1 < m){
            ret[k++] = nums1[s1++];
        }
        while (s2 < n){
            ret[k++] = nums2[s2++];
        }

        for (int i = 0; i < m + n; i++) {
            nums1[i] = ret[i];
        }

    }

    // 双序列 倒序双指针
    public void mergeII(int[] nums1, int m, int[] nums2, int n) {
        int s1 = m - 1;
        int s2 = n - 1;
        int k = m + n - 1;
        while (s1 >= 0 && s2 >= 0){
            if(nums1[s1] > nums2[s2]){
                nums1[k--] = nums1[s1--];
            } else {
                nums1[k--] = nums2[s2--];
            }
        }

        while (s2 >= 0){
            nums1[k--] = nums2[s2--];
        }
    }

    public void mergeIII(int[] nums1, int m, int[] nums2, int n) {
        int s1 = m - 1;
        int s2 = n - 1;
        int k = m + n - 1;
        while (s2 >= 0){
            if(s1 >= 0 && nums1[s1] > nums2[s2]){
                nums1[k--] = nums1[s1--];
            } else {
                nums1[k--] = nums2[s2--];
            }
        }
    }

    // o（m + n）
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        List<int[]> list = new ArrayList<>();

        int s1 = 0;
        int s2 = 0;

        while (s1 < n1 && s2 < n2){
            if(nums1[s1][0] == nums2[s2][0]){
                nums1[s1][1] += nums2[s2][1];
                list.add(nums1[s1]);
                s1++;
                s2++;
            } else if(nums1[s1][0] < nums2[s2][0]){
                list.add(nums1[s1]);
                s1++;
            } else {
                list.add(nums2[s2]);
                s2++;
            }
        }

        while (s1 < n1){
            list.add(nums1[s1++]);
        }

        while (s2 < n2){
            list.add(nums2[s2++]);
        }

        int[][] ret = new int[list.size()][2];
        list.toArray(ret);
        return ret;
    }
    
    /**
     * @Author 强仔不强
     * @Description 350. 两个数组的交集 II
     * @Date 21:05 2025/11/12
     * @Param [nums1, nums2]
     * @return int[]
     **/
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] cnt = new int[1001];
        for (int val : nums2){
            cnt[val]++;
        }

        List<Integer> list = new ArrayList<>();

        for (int num : nums1){
            if(cnt[num] > 0){
                list.add(num);
                cnt[num]--;
            }
        }

        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    // 假设给定的数组已经有序.
    public int[] intersectII(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        int s1 = 0;
        int s2 = 0;
        int n1 = nums1.length;
        int n2 = nums2.length;

        while (s1 < n1 && s2 < n2){
            if(nums1[s1] == nums2[s2]){
                list.add(nums1[s1]);
                s1++;
                s2++;
            } else if(nums1[s1] < nums2[s2]){
                s1++;
            } else {
                s2++;
            }
        }

        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description LCP 18. 早餐组合
     * @Date 21:37 2025/11/12
     * @Param [staple, drinks, x]
     * @return int
     **/
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        Arrays.sort(staple);
        Arrays.sort(drinks);

        final int MOD = 1000000007;

        int sn = staple.length;
        int dn = drinks.length;

        int left = 0;
        int right = dn - 1;
        long ret = 0;
        while (left < sn && right >= 0){
            if(staple[left] + drinks[right] <= x){
                ret = (ret + right + 1) % MOD;
                left++;
            } else {
                right--;
            }
        }

        return (int) ret % MOD;
    }

    /**
     * @Author 强仔不强
     * @Description 1855. 下标对中的最大距离
     * @Date 21:59 2025/11/12
     * @Param [nums1, nums2]
     * @return int
     **/
    public int maxDistance(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        int s1 = 0;
        int s2 = 0;

        int ret = 0;

        while (s1 < n1 && s2 < n2){
            if(nums1[s1] <= nums2[s2]){
                ret = Math.max(ret, s2 - s1);
                s2++;
            } else {
                s1++;
            }
        }

        return ret;
    }
    
    /**
     * @Author 强仔不强
     * @Description 1385. 两个数组间的距离值
     * @Date 11:16 2025/11/13
     * @Param [arr1, arr2, d]
     * @return int
     **/
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int j = 0;
        int ret = 0;
        for (int val : arr1){
            while (j < arr2.length && arr2[j] < val - d){
                j++;
            }
            if(j == arr2.length || arr2[j] > val + d){
                ret++;
            }
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 925. 长按键入
     * @Date 11:38 2025/11/13
     * @Param [name, typed]
     * @return boolean
     **/
    // 需要注意字母顺序.
    public static boolean isLongPressedName(String name, String typed) {
        char[] chars_name = name.toCharArray();
        char[] char_typed = typed.toCharArray();

        int[] cnt = new int[26];
        for (char c : char_typed){
            cnt[c - 'a']++;
        }

        for (char ch : chars_name){
            cnt[ch - 'a']--;
            if(cnt[ch - 'a'] < 0){
                return false;
            }
        }

        return true;
    }

    public static boolean isLongPressedNameII(String name, String typed) {
        int n1 = name.length();
        int n2 = typed.length();

        char[] cn = name.toCharArray();
        char[] ct = typed.toCharArray();

        int s1 = 0;
        int s2 = 0;
        while (s1 < n1 && s2 < n2){

            if(cn[s1] != ct[s2]){
                return false;
            }

            int cnt1 = 0;
            char c1 = cn[s1];
            while (s1 < n1 && cn[s1] == c1){
                cnt1++;
                s1++;
            }

            int cnt2 = 0;
            char c2 = ct[s2];
            while (s2 < n2 && ct[s2] == c2){
                cnt2++;
                s2++;
            }

            if(cnt1 > cnt2){
                return false;
            }
        }

        if(s1 < n1){
            return false;
        }

        while (s2 < n2){
            if(ct[s2] != cn[n1 - 1]){
                return false;
            }
        }

        return true;
    }

    /**
     * @Author 强仔不强
     * @Description 809. 情感丰富的文字
     * @Date 15:37 2025/11/18
     * @Param [s, words]
     * @return int
     **/
    public int expressiveWords(String s, String[] words) {
        int ret = 0;
        for (String word : words){
            if(express(s, word)){
                ret++;
            }
        }
        return ret;
    }

    public boolean express(String s, String word){
        char[] chars1 = s.toCharArray();
        char[] chars2 = word.toCharArray();

        int n1 = s.length();
        int n2 = word.length();

        int s1 = 0;
        int s2 = 0;

        while (s1 < n1 && s2 < n2){
            if(chars1[s1] != chars2[s2]){
                return false;
            }

            char c = chars1[s1];

            int cnt1 = 0;
            while (s1 < n1 && chars1[s1] == c){
                cnt1++;
                s1++;
            }

            int cnt2 = 0;
            while (s2 < n2 && chars2[s2] == c){
                cnt2++;
                s2++;
            }
            if(cnt1 < cnt2 || cnt1 > cnt2 && cnt1 < 3){
                return false;
            }
        }

        return s1 == n1 && s2 == n2;
    }


    public boolean canChange(String start, String target) {
        char[] chars1 = start.toCharArray();
        char[] chars2 = target.toCharArray();

        int i = 0;
        int j = 0;
        while (i < chars1.length && j < chars2.length){
            if(chars1[i] == '_'){
                i++;
                continue;
            }
            if(chars2[j] == '_'){
                j++;
                continue;
            }

            if(chars1[i] != chars2[j]){
                return false;
            }
            if(chars1[i] == 'L' && i < j){
                return false;
            }
            if(chars1[i] == 'R' && i > j){
                return false;
            }

            i++;
            j++;
        }

        while (i < chars1.length && chars1[i] == '_'){
            i++;
        }

        while (j < chars2.length && chars2[j] == '_'){
            j++;
        }

        return i == chars1.length && j == chars2.length;
    }

    /**
     * @Author 强仔不强
     * @Description 844. 比较含退格的字符串
     * @Date 17:07 2025/11/18
     * @Param [s, t]
     * @return boolean
     **/
    public static boolean backspaceCompare(String s, String t) {
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();

        int n1 = s.length();
        int n2 = t.length();

        int i = n1 - 1;
        int j = n2 - 1;

        int cnt1 = 0;
        int cnt2 = 0;
        while (i >= 0 && j >= 0) {
            if (chars1[i] != '#' && chars2[j] != '#') {
                if (chars1[i] != chars2[j]) {
                    return false;
                }
                i--;
                j--;
            }

            while (i >= 0 && chars1[i] == '#') {
                cnt1++;
                i--;
            }
            while (i >= 0 && cnt1 > 0) {
                if (chars1[i] == '#') {
                    break;
                }
                i--;
                cnt1--;
            }

            while (j > 0 && chars2[j] == '#') {
                cnt2++;
                j--;
            }
            while (j >= 0 && cnt2 > 0) {
                if (chars2[j] == '#') {
                    break;
                }
                j--;
                cnt2--;
            }

        }

        int k1 = 0;
        int num1 = 0;
        while (k1 <= i){
            if(chars1[k1] != '#'){
                num1++;
            } else {
                num1--;
            }

            k1++;
            num1 = Math.max(0, num1);
        }

        int k2 = 0;
        int num2 = 0;
        while (k2 <= j){
            if(chars2[k2] != '#'){
                num2++;
            } else {
                num2--;
            }

            k2++;
            num2 = Math.max(0, num2);
        }

        return num1 == 0 && num2 == 0;
    }

    public boolean backspaceCompareII(String s, String t) {
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();

        for (char ch : s.toCharArray()){
            if(ch == '#'){
                if(!stack1.isEmpty()){
                    stack1.pop();
                }
            } else {
                stack1.push(ch);
            }
        }

        for (char ch : t.toCharArray()){
            if(ch == '#'){
                if(!stack2.isEmpty()){
                    stack2.pop();
                }
            } else {
                stack2.push(ch);
            }
        }

        if(stack1.size() != stack2.size()){
            return false;
        }

        while (!stack1.isEmpty()){
            if(stack1.pop() != stack2.pop()){
                return false;
            }
        }

        return true;
    }

    public static boolean backspaceCompareIII(String s, String t) {
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();


        int i = chars1.length - 1;
        int j = chars2.length - 1;

        // 字符串中需要删除的字符的数量.
        int cnt1 = 0;
        int cnt2 = 0;

        while (i >= 0 || j >= 0){
            while (i >= 0){
                if(chars1[i] == '#'){
                    cnt1++;
                } else {
                    if(cnt1 == 0){
                        break;
                    } else {
                        cnt1--;
                    }
                }
                i--;
            }

            while (j > 0){
                if(chars2[j] == '#'){
                    cnt2++;
                } else {
                    if(cnt2 == 0){
                        break;
                    } else {
                        cnt2--;
                    }
                }
                j--;
            }

            if(i >= 0 && j >= 0) {
                if (chars1[i] != chars2[j]) {
                    return false;
                }
            } else {
                if(i >= 0 || j >= 0) {
                    return false;
                }
            }

            i--;
            j--;
        }

        return true;
    }

    /**
     * @Author 强仔不强
     * @Description 986. 区间列表的交集
     * @Date 20:57 2025/11/18
     * @Param [firstList, secondList]
     * @return int[][]
     **/
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int n1 = firstList.length;
        int n2 = secondList.length;

        if(n1 == 0 || n2 == 0){
            return new int[0][];
        }

        List<int[]> list = new ArrayList<>();
        int s1 = 0;
        int s2 = 0;

        while (s1 < n1 && s2 < n2){
            int start1 = firstList[s1][0];
            int end1 = firstList[s1][1];
            int start2 = secondList[s2][0];
            int end2 = secondList[s2][1];

            if(end1 < start2){
                s1++;
                continue;
            }
            if(start1 > end2){
                s2++;
                continue;
            }

            int right = Math.min(end1, end2);
            int left = Math.max(start1, start2);
            list.add(new int[]{left, right});

            if(end1 <= end2){
                s1++;
            } else {
                s2++;
            }
        }

        int[][] ret = new int[list.size()][2];
        return list.toArray(ret);
    }


    /**
     * @Author 强仔不强
     * @Description 面试题 16.06. 最小差
     *                    找两数之差最靠近 0.
     * @Date 21:43 2025/11/18
     * @Param [a, b]
     * @return int
     **/
    public int smallestDifference(int[] a, int[] b) {
        if(a.length == 1 && b.length == 1){
            return Math.abs(a[0] - b[0]);
        }

        Arrays.sort(a);
        Arrays.sort(b);

        long ret = Long.MAX_VALUE;
        int s1 = 0;
        int s2 = 0;

        while (s1 < a.length && s2 < b.length){
            if(a[s1] == b[s2]){
                return 0;
            }

            ret = Math.min( Math.abs((long) a[s1] - b[s2]), ret);
            if(a[s1] > b[s2]){
                s2++;
            } else {
                s1++;
            }
        }

        return (int) ret;
    }

    /**
     * @Author 强仔不强
     * @Description 
     * @Date 21:59 2025/11/18
     * @Param [s, t]
     * @return boolean
     **/
    // o(n)   n 是字符串 t 的长度.
    public boolean isSubsequence(String s, String t) {
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();

        if(chars1.length > chars2.length){
            return false;
        }

        int s1 = 0;
        int s2 = 0;
        while (s1 < chars1.length && s2 < chars2.length){
            if(chars1[s1] == chars2[s2]){
                s1++;
            }
            s2++;
        }

        return s1 == chars1.length;
    }

    // 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。
    // k * o(m)  m 是字符串 s 的长度.
    public static boolean isSubsequenceII(String s, String t) {
        int n = t.length();

        // nxt[i][c] 表示 t 中下标 >= i 的最近字符串 c 的下标.
        int[][] nxt = new int[n + 1][26];
        for (int i = 0; i < 26; i++) {
            nxt[n][i] = n;
        }

        for (int i = n - 1; i >= 0; i--) {
            nxt[i] = Arrays.copyOf(nxt[i + 1], 26);

            char c = t.charAt(i);
            nxt[i][c - 'a'] = i;
        }

        if(s.isEmpty()){
            return true;
        }
        if(s.length() > t.length()){
            return false;
        }

        int pos = 0;
        for (char ch : s.toCharArray()){
            if(nxt[pos][ch - 'a'] == n){
                return false;
            }

            pos = nxt[pos][ch - 'a'] + 1;
        }

        return true;
    }

    /**
     * @Author 强仔不强
     * @Description 524. 通过删除字母匹配到字典里最长单词
     * @Date 16:23 2025/11/19
     * @Param [s, dictionary]
     * @return java.lang.String
     **/
    public String findLongestWord(String s, List<String> dictionary) {
        String ret = "";

        int n = s.length();
        // nxt[i][c] 表示 s 中下标 >= i 的最近字符串 c 的下标.
        int[][] nxs = new int[n + 1][26];
        for (int i = 0; i < 26; i++) {
            nxs[n][i] = n;
        }
        for (int i = n - 1; i >= 0; i--) {
            nxs[i] = Arrays.copyOf(nxs[i + 1], 26);

            char c = s.charAt(i);
            nxs[i][c - 'a'] = i;
        }


        for (String word : dictionary){
            // 判断 字符串 word 是不是 字符串 s 的子序列.
            if(isSubsequenceII(word, s, nxs)){
                ret = find(ret, word);
            }
        }

        return ret;
    }

    // 判断 字符串 s 是不是 字符串 t（生成 nxt）的子序列.
    public static boolean isSubsequenceII(String s, String t, int[][] nxt) {
        int n = t.length();
        if(s.isEmpty()){
            return true;
        }
        if(s.length() > n){
            return false;
        }

        int pos = 0;
        for (char ch : s.toCharArray()){
            if(nxt[pos][ch - 'a'] == n){
                return false;
            }

            pos = nxt[pos][ch - 'a'] + 1;
        }

        return true;
    }

    // 返回 长度较长且字典序小 的字符串.
    public String find(String s, String word){
        if(s.length() != word.length()){
            return s.length() > word.length() ? s : word;
        }

        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == word.charAt(i)){
                continue;
            }

            return s.charAt(i) < word.charAt(i) ? s : word;
        }

        return s;
    }

    
    /**
     * @Author 强仔不强
     * @Description 2486. 追加字符以获得子序列
     * @Date 17:42 2025/11/19
     * @Param [s, t]
     * @return int
     **/
    public int appendCharacters(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();

        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();

        int s1 = 0;
        int s2 = 0;

        while (s1 < n1 && s2 < n2){
            if(chars1[s1] == chars2[s2]){
                s2++;
            }
            s1++;
        }

        return n2 - s2;
    }


    /**
     * @Author 强仔不强
     * @Description 2825. 循环增长使字符串子序列等于另一个字符串
     * @Date 18:28 2025/11/19
     * @Param [str1, str2]
     * @return boolean
     **/
    public boolean canMakeSubsequence(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();

        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        int s1 = 0;
        int s2 = 0;

        while (s1 < n1 && s2 < n2){
            if(chars1[s1] == chars2[s2] || chars1[s1] + 1 == chars2[s2] || (chars1[s1] == 'z' && chars2[s2] == 'a')){
                s2++;
            }
            s1++;
        }

        return s2 == n2;
    }
    
    /**
     * @Author 强仔不强
     * @Description 1023. 驼峰式匹配
     * @Date 18:39 2025/11/19
     * @Param [queries, pattern]
     * @return java.util.List<java.lang.Boolean>
     **/
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ret = new ArrayList<>();

        char[] chars = pattern.toCharArray();

        for (String query : queries){
            ret.add(match(chars, query.toCharArray()));
        }

        return ret;
    }

    // 判断 pattern 是不是 query 的子系列
    public boolean match(char[] pattern, char[] query) {
        int n1 = pattern.length;
        int n2 = query.length;

        int s1 = 0;
        int s2 = 0;

        while (s1 < n1 && s2 < n2){
            if(pattern[s1] == query[s2]){
                s1++;
                s2++;
            } else {
                if(query[s2] >= 'a' && query[s2] <= 'z'){
                    s2++;
                } else {
                    return false;
                }
            }
        }

        if(s1 < n1){
            return false;
        }

        while (s2 < n2 && (query[s2] >= 'a'&& query[s2] <= 'z')){
            s2++;
        }

        return s2 == n2;
    }


    /**
     * @Author 强仔不强
     * @Description 3132. 找出与数组相加的整数 II
     * @Date 19:00 2025/11/19
     * @Param [nums1, nums2]
     * @return int
     **/
    public static int minimumAddedInteger(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int ret = Integer.MAX_VALUE;

        // 找 nums1 的开头（nums1 中保留的最小的元素下标.）
        for (int i = 0; i <= 2; i++) {
            int s1 = i;
            int s2 = 0;
            int gap = nums2[s2] - nums1[s1];

            // 看 nums2 是不是 nums[1] （nums[i] + gap） 的子序列.
            while (s1 < n1 && s2 < n2){
                if(nums2[s2] - nums1[s1] == gap){
                    s2++;
                }
                s1++;
            }

            if(s2 < n2){
                continue;
            }

            ret = gap;
        }

        return ret;
    }

    public static int minimumAddedIntegerII(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        for (int i = 2; i >= 0; i--) {
            int x = nums2[0] - nums1[i];

            int s1 = i;
            int s2 = 0;
            while (s1 < n1 && s2 < n2){
                if(nums1[s1] + x == nums2[s2]){
                    s2++;
                }
                s1++;
            }

            if(s2 == n2){
                return x;
            }
        }

        return -1;
    }

    /**
     * @Author 强仔不强
     * @Description 522. 最长特殊序列 II
     * @Date 17:50 2025/11/20
     * @Param [strs]
     * @return int
     **/
    public int findLUSlength(String[] strs) {
        int n = strs.length;

        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() == o2.length()){
                    for (int i = 0; i < o1.length(); i++) {
                        if(o1.charAt(i) != o2.charAt(i)){
                            return o1.charAt(i) - o2.charAt(i);
                        }
                    }
                }
                return o1.length() - o2.length();
            }
        });

        int ret = -1;

        for (int i = 0; i < n; i++) {
            if(strs[i].length() == ret){
                continue;
            }

            if(i > 0 && strs[i].equals(strs[i - 1])){
                continue;
            }

            int j = i + 1;
            for (; j < n; j++) {
                boolean flg = isSubsequence(strs[i], strs[j]);

                if(flg){
                    break;
                }
            }

            if(j == n){
                ret = Math.max(ret, strs[i].length());
            }

        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 2367. 等差三元组的数目
     * @Date 18:32 2025/11/20
     * @Param [nums, diff]
     * @return int
     **/
    // 三指针法.
    public static int arithmeticTriplets(int[] nums, int diff) {
        int n = nums.length;
        int ret = 0;

        int i = 0;
        int j = 1;
        int k = 2;

        for ( ; i < n - 2; i++){
            while (j < n - 1 && nums[j] - nums[i] < diff){
                j++;
            }
            if(j == n - 1){
                break;
            }
            if(nums[j] - nums[i] > diff){
                continue;
            }


            while (k < n && nums[k] - nums[j] < diff) {
                k++;
            }
            if(k == n){
                break;
            }
            if(nums[k] - nums[j] == diff){
                ret++;
            }
        }

        return ret;
    }

    // 哈希表
    public static int arithmeticTripletsII(int[] nums, int diff) {
        Set<Integer> set = new HashSet<>();
        for (int val : nums){
            set.add(val);
        }

        int ret = 0;
        for (int j = 1; j < nums.length - 1; j++) {
            if(set.contains(nums[j] - diff) && set.contains(nums[j] + diff)){
                ret++;
            }
        }

        return ret;
    }


    /**
     * @Author 强仔不强
     * @Description 795. 区间子数组个数
     * @Date 20:13 2025/11/20
     * @Param [nums, left, right]
     * @return int
     **/
    // 双指针.
    public static int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int ret = 0;
        // 枚举 nums[i] 作为 区间最大元素.
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] < left || nums[i] > right){
                continue;
            }

            int start = i - 1;
            int end = i + 1;

            while (start >= 0 && nums[start] <= nums[i]){
                start--;
            }

            while (end < nums.length && nums[end] < nums[i]){
                end++;
            }

            ret += (i - start) * (end - i);
        }

        return ret;
    }

    // 双指针
    public static int numSubarrayBoundedMaxII(int[] nums, int left, int right) {
        // [0, i] 中 最后一个 > right 的元素的下标
        int i0 = -1;
        // [0, i] 中 最后一个 >= left && <= right 的元素的下标
        int i1 = -1;
        int ret = 0;

        // 枚举 nums[i] 作为 区间右端点.
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > right){
                i0 = i;
                continue;
            }

            if(nums[i] >= left && nums[i] <= right){
                i1 = i;
            }

            if(i1 > i0){
                ret += i1 - i0;
            }
        }

        return ret;
    }

    // 计数.
    // 最大元素 >= left && <= right 的子数组数 = 所有元素均 <= right 的子数组数 - 所有元素均 < left 的子数组数.
    public int numSubarrayBoundedMaxIII(int[] nums, int left, int right) {
        return count(nums, right) - count(nums, left - 1);
    }

    // 所有元素均 <= k 的子数组数.
    public int count(int[] nums, int k){
        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;

        int ret = 0;

        while (end < nums.length){

            end++;
            if(nums[end - 1] > k){
                start = end;
            }


            ret += end - start;
        }

        return ret;
    }
    
    /**
     * @Author 强仔不强
     * @Description 485. 最大连续 1 的个数
     * @Date 11:15 2025/12/1
     * @Param [nums]
     * @return int
     **/
    public int findMaxConsecutiveOnes(int[] nums) {
        int ret = 0;

        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 1){
                cnt++;
            } else {
                ret = Math.max(ret, cnt);
                cnt = 0;
            }
        }

        return Math.max(ret, cnt);
    }


    /**
     * @Author 强仔不强
     * @Description 1446. 连续字符
     * @Date 11:25 2025/12/1
     * @Param [s]
     * @return int
     **/
    public int maxPower(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int ret = 0;
        int cnt = 0;
        char c = chars[0];

        for (int i = 0; i < n; i++) {
            if(chars[i] == c){
                cnt++;
            } else {
                ret = Math.max(ret, cnt);
                cnt = 1;
                c = chars[i];
            }
        }

        return Math.max(ret, cnt);
    }

    public int maxPowerII(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int ret = 0;
        int cnt = 1;

        for (int i = 1; i < n; i++) {
            if(chars[i] == chars[i - 1]){
                cnt++;
            } else {
                ret = Math.max(ret, cnt);
                cnt = 1;
            }
        }

        return Math.max(ret, cnt);
    }


    /**
     * @Author 强仔不强
     * @Description 1869. 哪种连续子字符串更长
     * @Date 11:31 2025/12/1
     * @Param [s]
     * @return boolean
     **/
    public boolean checkZeroOnes(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int ret0 = 0;
        int ret1 = 0;
        int cnt0 = 0;
        int cnt1 = 0;

        for (int i = 0; i < n; i++) {
            if(chars[i] == '0'){
                cnt0++;

                ret1 = Math.max(ret1, cnt1);
                cnt1 = 0;
            } else {
                cnt1++;

                ret0 = Math.max(ret0, cnt0);
                cnt0 = 0;
            }
        }

        return Math.max(ret1, cnt1) > Math.max(ret0, cnt0);
    }


    /**
     * @Author 强仔不强
     * @Description 3456. 找出长度为 K 的特殊子字符串
     * @Date 11:37 2025/12/1
     * @Param [s, k]
     * @return boolean
     **/
    public boolean hasSpecialSubstring(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int cnt = 1;
        for (int i = 1; i < n; i++) {
            if(chars[i] == chars[i - 1]){
                cnt++;
            } else {
                if(cnt == k){
                    return true;
                }

                cnt = 1;
            }
        }

        return cnt == k;
    }
    
    
    /**
     * @Author 强仔不强
     * @Description 2348. 全 0 子数组的数目
     * @Date 12:05 2025/12/1
     * @Param [nums]
     * @return long
     **/
    public long zeroFilledSubarray(int[] nums) {
        int n = nums.length;

        int cnt = 0;
        long ret = 0;
        for (int i = 0; i < n; i++) {
            if(nums[i] == 0){
                cnt++;
            } else {
                ret += (long) cnt * (cnt + 1) / 2;
                cnt = 0;
            }
        }
        ret += (long) cnt * (cnt + 1) / 2;

        return ret;
    }


    public long zeroFilledSubarrayII(int[] nums) {
        int n = nums.length;

        int cnt = 0;
        long ret = 0;
        for (int i = 0; i < n; i++) {
            if(nums[i] == 0){
                cnt++;
                ret += cnt;
            } else {
                cnt = 0;
            }
        }

        return ret;
    }

    // 滑动窗口
    public long zeroFilledSubarrayIII(int[] nums) {
        int n = nums.length;

        // [start, end)
        int start = 0;
        int end = 0;
        long ret = 0;

        while (end < n){
            if(nums[end] == 0){
                ret += end - start + 1;
            } else {
                start = end + 1;
            }

            end++;
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 1513. 仅含 1 的子串数
     * @Date 13:00 2025/12/1
     * @Param [s]
     * @return int
     **/
    public int numSub(String s) {
        final int MOD = 1000000007;

        int n = s.length();
        char[] chars = s.toCharArray();

        long ret = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if(chars[i] == '1'){
                cnt++;
                ret = (ret + cnt) % MOD;
            } else {
                cnt = 0;
            }
        }

        return (int) (ret % MOD);
    }

    /**
     * @Author 强仔不强
     * @Description 1957. 删除字符使字符串变好
     * @Date 16:00 2025/12/1
     * @Param [s]
     * @return java.lang.String
     **/

    // 原地修改.
    public String makeFancyString(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int stackSize = 1;
        int cnt = 1;

        for (int i = 1; i < n; i++) {
            if(chars[i] == chars[i - 1]){
                if(cnt < 2){
                    chars[stackSize++] = chars[i];
                    cnt++;
                }
            } else {
                chars[stackSize++] = chars[i];
                cnt = 1;
            }
        }

        return new String(chars, 0, stackSize);
    }


    public String makeFancyStringII(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        StringBuffer buffer = new StringBuffer();
        buffer.append(chars[0]);
        int cnt = 1;

        for (int i = 1; i < n; i++) {
            if(chars[i] == chars[i - 1]){
                if(cnt < 2){
                    buffer.append(chars[i]);
                    cnt++;
                }
            } else {
                buffer.append(chars[i]);
                cnt = 1;
            }
        }

        return buffer.toString();
    }


    /**
     * @Author 强仔不强
     * @Description 674. 最长连续递增序列
     * @Date 16:48 2025/12/1
     * @Param [nums]
     * @return int
     **/
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;

        int ret = 1;
        int cnt = 1;

        for (int i = 1; i < n; i++) {
            if(nums[i] > nums[i + 1]){
                cnt++;
            } else {
                ret = Math.max(ret, cnt);
                cnt = 1;
            }
        }

        return Math.max(ret, cnt);
    }

    // 分组循环
    public int findLengthOfLCISII(int[] nums) {
        int n = nums.length;
        int ret = 0;

        int start = 0;
        while (start < n){
            int end = start + 1;
            while (end < n && nums[end] > nums[end - 1]){
                end++;
            }

            ret = Math.max(ret, end - start);

            start = end;
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 3708. 最长斐波那契子数组
     * @Date 16:53 2025/12/1
     * @Param [nums]
     * @return int
     **/
    public int longestSubarray3708(int[] nums) {
        int n = nums.length;

        int ret = 2;
        int cnt = 2;

        for (int i = 2; i < n; i++) {
            if(nums[i] == nums[i - 1] + nums[i - 2]){
                cnt++;
            } else {
                ret = Math.max(ret, cnt);

                cnt = 2;
            }
        }

        return Math.max(ret, cnt);
    }

    // 分组循环
    public int longestSubarray3708II(int[] nums) {
        int n = nums.length;
        int ret = 0;

        int start = 0;
        while (start < n){
            int end = start + 2;
            while (end < n && nums[end] == nums[end - 1] + nums[end - 2]){
                end++;
            }

            ret = Math.max(ret, end - start);

            start = end - 1;
        }

        return ret;
    }
    
    /**
     * @Author 强仔不强
     * @Description 696. 计数二进制子串
     * @Date 17:05 2025/12/1
     * @Param [s]
     * @return int
     **/
    public int countBinarySubstringsII(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int ret = 0;
        int pre = 0;
        int cnt = 1;

        for (int i = 1; i < n; i++) {
            if(chars[i] == chars[i - 1]){
                cnt++;
                if(cnt <= pre){
                    ret++;
                }
            } else {
                pre = cnt;
                cnt = 1;

                ret++;
            }
        }

        return ret;
    }

    // 分组循环
    public int countBinarySubstrings(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int ret = 0;
        int preCnt = 0;

        int start = 0;
        while (start < n){
            int end = start + 1;
            while (end < n && chars[end] == chars[end - 1]){
                end++;
            }

            int curCnt = end - start;
            ret += Math.min(curCnt, preCnt);
            preCnt = curCnt;

            start = end;
        }

        return ret;
    }


    /**
     * @Author 强仔不强
     * @Description 978. 最长湍流子数组
     * @Date 17:32 2025/12/1
     * @Param [arr]
     * @return int
     **/
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;

        int cnt = 1;
        int ret = 0;

        for (int i = 1; i < n; i++) {
            if(cnt == 1){
                if(arr[i] != arr[i - 1]){
                    cnt++;
                }
                continue;
            }

            if( (arr[i] > arr[i - 1] && arr[i - 2] > arr[i - 1]) || (arr[i] < arr[i - 1] && arr[i - 1] > arr[i - 2]) ){
                cnt++;
            } else {
                ret = Math.max(ret, cnt);

                if(arr[i] != arr[i - 1]){
                    cnt = 2;
                } else {
                    cnt = 1;
                }
            }
        }

        return Math.max(ret, cnt);
    }

    // 分组循环
    public int maxTurbulenceSizeII(int[] arr) {
        // n >= 1
        int n = arr.length;

        int ret = 1;
        int start = 0;

        while (start < n - 1){
            int end = start + 1;

            if(arr[end] == arr[start]){
                start = end;
                continue;
            }
            end++;

            while (end < n && ( arr[end - 1] < arr[end] && arr[end - 1] < arr[end - 2] || arr[end - 1] > arr[end] && arr[end - 1 ] > arr[end - 2]) ){
                end++;
            }

            ret = Math.max(ret, end - start);

            start = end - 1;
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 2110. 股票平滑下跌阶段的数目
     * @Date 18:02 2025/12/1
     * @Param [prices]
     * @return long
     **/
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;

        long ret = 1;
        int cnt = 1;

        for (int i = 1; i < n; i++) {
            if(prices[i - 1] - prices[i] == 1){
                cnt++;
                ret += cnt;
            } else {
                ret++;
                cnt = 1;
            }
        }

        return ret;
    }

    // 分组循环
    public long getDescentPeriodsII(int[] prices) {
        int n = prices.length;

        long ret = 0;
        int start = 0;

        while (start < n){
            int end = start + 1;
            while (end < n && prices[end] == prices[end - 1] - 1){
                end++;
            }

            int cnt = end - start;
            ret += (long) cnt * (cnt + 1) / 2;

            start = end;
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 228. 汇总区间
     * @Date 21:34 2025/12/1
     * @Param [nums]
     * @return java.util.List<java.lang.String>
     **/
    public List<String> summaryRanges(int[] nums) {
        int n = nums.length;
        List<String> ret = new ArrayList<>();

        if (n == 0) {
            return ret;
        }

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 1;

        while (end < nums.length) {
            if (nums[end] > nums[end - 1] + 1) {
                StringBuffer s = new StringBuffer(Integer.toString(nums[start]));
                if (end - start > 1) {
                    s.append("->");
                    s.append(nums[end - 1]);
                }
                ret.add(s.toString());

                start = end;
            }
            end++;
        }

        StringBuffer s = new StringBuffer(Integer.toString(nums[start]));
        if (end - start > 1) {
            s.append("->");
            s.append(nums[end - 1]);
        }
        ret.add(s.toString());

        return ret;
    }

    // 分组循环
    public List<String> summaryRangesII(int[] nums) {
        int n = nums.length;
        List<String> ret = new ArrayList<>();

        int start = 0;
        while (start < n){
            int end = start + 1;
            while (end < n && nums[end] == nums[end - 1] + 1){
                end++;
            }

            StringBuffer buffer = new StringBuffer(Integer.toString(nums[start]));
            if(end - start > 1){
                buffer.append("->");
                buffer.append(nums[end - 1]);
            }
            ret.add(buffer.toString());

            start = end;
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 2760. 最长奇偶子数组
     * @Date 22:00 2025/12/1
     * @Param [nums, threshold]
     * @return int
     **/
    // 滑动窗口
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int n = nums.length;

        int ret = 0;

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;

        while (end < n) {
            ret = Math.max(ret, end - start);

            if (nums[end] <= threshold) {
                if (end - start == 0) {
                    if (nums[end] % 2 == 0) {
                        end++;
                        continue;
                    }
                } else {
                    if (nums[end] % 2 != nums[end - 1] % 2) {
                        end++;
                    } else {
                        start = end;
                    }
                    continue;
                }
            }

            end++;
            start = end;

        }

        return Math.max(ret, end - start);
    }

    // 分组循环
    public int longestAlternatingSubarrayII(int[] nums, int threshold) {
        int n = nums.length;
        int ret = 0;

        int start = 0;

        while (start < n){
            if(nums[start] > threshold || nums[start] % 2 != 0){
                start++;
                continue;
            }

            int end = start + 1;
            while (end < n && nums[end] <= threshold && nums[end] % 2 != nums[end - 1] % 2){
                end++;
            }

            ret = Math.max(ret, end - start);
            start = end;
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 1887. 使数组元素相等的减少操作次数
     * @Date 16:43 2025/12/2
     * @Param [nums]
     * @return int
     **/
    public int reductionOperations(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int ret = 0;

        // cnt 当前子数组中 某个元素 转变到底 所需的次数.
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if(i > 0 && nums[i] != nums[i - 1]) {
                cnt++;
            }
            ret += cnt;
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 845. 数组中的最长山脉
     * @Date 17:49 2025/12/2
     * @Param [arr]
     * @return int
     **/
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int ret = 0;

        int start = 0;
        while (start < n - 2){
            int end = start + 1;

            // 上坡
            while (end < n && arr[end] > arr[end - 1]){
                end++;
            }
            int high = end - 1;

            // 没有上坡的情况
            if(high == start){
                start = end;
                continue;
            }

            // 下坡
            while (end < n && arr[end] < arr[end - 1]){
                end++;
            }
            // 可能没有下坡
            if(end - high > 1){
                ret = Math.max(ret, end - start);
            }

            start = end - 1;
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 2038. 如果相邻两个颜色均相同则删除当前颜色
     * @Date 18:16 2025/12/2
     * @Param [colors]
     * @return boolean
     **/
    public boolean winnerOfGame(String colors) {
        int n = colors.length();
        char[] chars = colors.toCharArray();

        int cntA = 0;
        int cntB = 0;
        int cnt = 1;

        for (int i = 1; i < n; i++) {
            if(chars[i] == chars[i - 1]){
                cnt++;
                if(cnt >= 3){
                    if(chars[i] == 'A'){
                        cntA++;
                    } else {
                        cntB++;
                    }
                }
            } else {
                cnt = 1;
            }
        }

        return cntA > cntB;
    }

    // 分组循环
    public boolean winnerOfGameII(String colors) {
        int n = colors.length();
        char[] chars = colors.toCharArray();

        int cntA = 0;
        int cntB = 0;

        // 找长度 >= 3 的连续子字符串.
        int start = 0;
        while (start < n){
            int end = start + 1;
            while (end < n && chars[end] == chars[end - 1]){
                end++;
            }

            int len = end - start;
            if(len >= 3){
                if(chars[start] == 'A'){
                    cntA += len - 2;
                } else {
                    cntB += len - 2;
                }
            }

            start = end;
        }

        return cntA > cntB;
    }

    /**
     * @Author 强仔不强
     * @Description 2900. 最长相邻不相等子序列 I
     * @Date 18:31 2025/12/2
     * @Param [words, groups]
     * @return java.util.List<java.lang.String>
     **/
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        int n = groups.length;
        List<String> ret = new ArrayList<>();

        ret.add(words[0]);
        for (int i = 1; i < n; i++) {
            if(groups[i] != groups[i - 1]){
                ret.add(words[i]);
            }
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 1759. 统计同质子字符串的数目
     * @Date 18:49 2025/12/2
     * @Param [s]
     * @return int
     **/
    public int countHomogenous(String s) {
        final int MOD = 1000000007;

        int n = s.length();
        char[] chars = s.toCharArray();

        long ret = 1;
        int cnt = 1;
        for (int i = 1; i < n; i++) {
            if(chars[i] == chars[i - 1]){
                cnt++;
            } else {
                cnt = 1;
            }

            ret = (ret + cnt) % MOD;
        }

        return (int) ret;
    }

    /**
     * @Author 强仔不强
     * @Description 3011. 判断一个数组是否可以变为有序
     * @Date 19:22 2025/12/2
     * @Param [nums]
     * @return boolean
     **/
    public static boolean canSortArray(int[] nums) {
        int n = nums.length;

        // 上个可交换区间中的 最大元素.
        int preMax = 0;
        // 此时可交换区间中的 最大元素.
        int max = 0;

        // 上个元素的二进制中 1 的个数.
        int preCnt = -1;

        for (int i = 0; i < n; i++) {
            int curCnt = 0;
            int val = nums[i];
            while (val > 0){
                curCnt += val & 1;
                val >>= 1;
            }

            if(i == 0 || curCnt == preCnt){
                max = Math.max(max, nums[i]);
            } else {
                preMax = max;
                max = nums[i];
            }

            if(nums[i] < preMax){
                return false;
            }

            preCnt = curCnt;
        }

        return true;
    }

    /**
     * @Author 强仔不强
     * @Description 1578. 使绳子变成彩色的最短时间
     * @Date 20:06 2025/12/2
     * @Param [nums]
     * @return boolean
     **/
    public int minCost(String colors, int[] neededTime) {
        int n = colors.length();
        char[] chars = colors.toCharArray();

        int ret = 0;

        int cnt = 1;
        int maxTime = neededTime[0];
        int totalTime = neededTime[0];

        for (int i = 1; i < n; i++) {
            if(chars[i] == chars[i - 1]){
                cnt++;
                maxTime = Math.max(neededTime[i], maxTime);
                totalTime += neededTime[i];
            } else {
                if(cnt > 1){
                    ret += totalTime - maxTime;
                }
                cnt = 1;
                maxTime = neededTime[i];
                totalTime = neededTime[i];
            }
        }

        if(cnt > 1){
            ret += totalTime - maxTime;
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 1839. 所有元音按顺序排布的最长子字符串
     * @Date 20:38 2025/12/2
     * @Param [word]
     * @return int
     **/
    public int longestBeautifulSubstring(String word) {
        int n = word.length();
        char[] chars = word.toCharArray();

        int ret = 0;

        String s = "aeiou";
        char[] letters = new char[5];
        for (int i = 0; i < 5; i++) {
            letters[i] = s.charAt(i);
        }

        int cnt = 0;
        // 表示最后一个字母是 letters[index]
        int index = -1;

        for (int i = 0; i < n; i++) {
            if(cnt == 0){
                if(chars[i] == 'a'){
                    cnt++;
                    index = 0;
                }
                continue;
            }

            if(chars[i] == chars[i - 1]){
                cnt++;
                continue;
            }
            if(index < 4 && chars[i] == letters[index + 1]){
                cnt++;
                index++;
                continue;
            }

            if(index == 4){
                ret = Math.max(ret, cnt);
            }

            cnt = 0;
            index = -1;
            i--;
        }

        if(index == 4){
            ret = Math.max(ret, cnt);
        }

        return ret;
    }

    // 分组循环
    public int longestBeautifulSubstringII(String word) {
        int n = word.length();
        char[] chars = word.toCharArray();

        char[] letters = {'a', 'e', 'i', 'o', 'u'};
        int ret = 0;

        int start = 0;
        while (start < n){
            if(chars[start] != 'a'){
                start++;
                continue;
            }

            int end = start + 1;
            int index = 0;
            while (end < n){
                if(chars[end] == chars[end - 1]){
                    end++;
                    continue;
                }
                if(index < 4 && chars[end] == letters[index + 1]){
                    end++;
                    index++;
                    continue;
                }

                break;
            }

            if(index == 4){
                ret = Math.max(ret, end - start);
            }

            start = end;
        }

        return ret;
    }


    /**
     * @Author 强仔不强
     * @Description 2765. 最长交替子数组
     * @Date 09:03 2025/12/3
     * @Param [nums]
     * @return int
     **/
    public static int alternatingSubarray(int[] nums) {
        int n = nums.length;

        int cnt = 1;
        int ret = -1;

        for (int i = 1; i < n; i++) {
            if(cnt == 1){
                if(nums[i] == nums[i - 1] + 1){
                    cnt++;
                }
            } else {
                if(nums[i] == nums[i - 2]){
                    cnt++;
                } else {
                    ret = Math.max(ret, cnt);
                    cnt = 1;
                    i--;
                }
            }
        }

        return cnt > 1 ? Math.max(ret, cnt) : ret;
    }

    // 分组循环
    public int alternatingSubarrayII(int[] nums) {
        int n = nums.length;

        int ret = -1;

        int start = 0;
        while (start < n) {
            if(start == n - 1 || nums[start + 1] != nums[start] + 1){
                start++;
                continue;
            }

            int end = start + 2;
            while (end < n && nums[end] == nums[end - 2]){
                end++;
            }

            ret = Math.max(ret, end - start);

            start = end - 1;
        }

        return ret;
    }


    /**
     * @Author 强仔不强
     * @Description 3255. 长度为 K 的子数组的能量值 II
     * @Date 20:31 2025/12/3
     * @Param [nums, k]
     * @return int[]
     **/

    public int[] resultsArray(int[] nums, int k) {
        if(k == 1){
            return nums;
        }

        int n = nums.length;
        int[] ret = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            ret[i] = -1;
        }

        int cnt = 1;
        for (int i = 1; i < n; i++) {
            if(nums[i] == nums[i - 1] + 1){
                cnt++;
            } else {
                cnt = 1;
            }

            if(cnt >= k){
                ret[i - k + 1] = nums[i];
            }
        }

        return ret;
    }

    // 分组循环
    public int[] resultsArrayII(int[] nums, int k) {
        int n = nums.length;
        int[] ret = new int[n - k + 1];

        if(k == 1){
            return nums;
        }

        for (int i = 0; i < ret.length; i++) {
            ret[i] = -1;
        }

        int start = 0;
        while (start < n){
            int end = start + 1;
            while (end < n && nums[end] == nums[end - 1] + 1){
                end++;
                if(end - start >= k){
                    ret[end - k] = nums[end - 1];
                }
            }

            start = end;
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 3350. 检测相邻递增子数组 II
     * @Date 21:19 2025/12/3
     * @Param [nums]
     * @return int
     **/
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();

        int ret = 0;

        int preCnt = 0;

        int start = 0;
        while (start < n){
            int end = start + 1;
            while (end < n && nums.get(end) > nums.get(end - 1)){
                end++;
            }

            int twoLen = Math.min(preCnt, end - start);
            int singleLen = (end - start) / 2;

            int maxlen = Math.max(twoLen, singleLen);
            ret = Math.max(ret, maxlen);

            preCnt = end - start;
            start = end;
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 3105. 最长的严格递增或递减子数组
     * @Date 21:34 2025/12/3
     * @Param [nums]
     * @return int
     **/
    public static int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;

        int ret = 1;

        int start = 0;
        while (start < n - 1){
            if(start < n - 1 && nums[start] == nums[start + 1]){
                start++;
                continue;
            }

            int end = start + 1;
            while (end < n && nums[end] > nums[end - 1]){
                end++;
            }
            ret = Math.max(ret, end - start);
            start = end - 1;

            while (end < n && nums[end] < nums[end - 1]){
                end++;
            }
            ret = Math.max(ret, end - start);
            start = end - 1;

        }

        return ret;
    }


    /**
     * @Author 强仔不强
     * @Description 838. 推多米诺
     * @Date 22:00 2025/12/3
     * @Param [dominoes]
     * @return java.lang.String
     **/
    //L..L..L..R...R..L..R
    public String pushDominoes(String dominoes) {
        dominoes = 'L' + dominoes + 'R';
        int n = dominoes.length();
        char[] chars = dominoes.toCharArray();

        int start = 0;
        while (start < n - 1){
            int end = start + 1;
            while (end < n && chars[end] == '.'){
                end++;
            }

            if(chars[end] == chars[start]){
                Arrays.fill(chars, start, end, chars[start]);
            } else {
                if(chars[end] == 'L'){
                    int mid = (start + end) / 2;

                    int last = mid;
                    if( (end - start - 1) % 2 == 0) {
                        last++;
                    }

                    Arrays.fill(chars, start, last, 'R');
                    Arrays.fill(chars, mid + 1, end, 'L');
                }
            }

            start = end;
        }

        return new String(chars, 1, n - 2);
    }

    /**
     * @Author 强仔不强
     * @Description 467. 环绕字符串中唯一的子字符串
     * @Date 11:26 2025/12/4
     * @Param [s]
     * @return int
     **/
    // 重复统计了相同的子字符串.
    public int findSubstringInWraproundString(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int ret = 1;
        int cnt = 1;

        for (int i = 1; i < n; i++) {
            if(chars[i] == chars[i - 1] + 1 || chars[i - 1] == 'z' && chars[i] == 'a'){
                cnt++;
            } else {
                cnt = 1;
            }
            ret += cnt;
        }

        return ret;
    }


    public static int findSubstringInWraproundStringII(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int ret = 0;
        // 以当前字母结尾的连续子字符串的最大长度.
        int[] cnt = new int[26];

        int start = 0;
        while (start < n){
            int i = chars[start] - 'a';
            cnt[i] = Math.max(cnt[i], 1);

            int end = start + 1;
            while (end < n && (chars[end] == chars[end - 1] + 1 || (chars[end] == 'a' && chars[end - 1] == 'z'))){
                int j = chars[end] - 'a';
                cnt[j] = Math.max(cnt[j], end - start + 1);
                end++;
            }

            start = end;
        }

        for (int val : cnt){
            ret += val;
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 3499. 操作后最大活跃区段数 I
     * @Date 17:03 2025/12/4
     * @Param [s]
     * @return int
     **/
    public static int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int max0 = 0;
        int total1 = 0;
        int cnt = 0;
        int preCnt0 = 0;

        for (int i = 0; i < n; i++) {
            if(chars[i] == '1'){
                total1++;
            }

            if(i == 0 || chars[i] == chars[i - 1]){
                cnt++;
                continue;
            }

            if(chars[i - 1] == '0'){
                if(preCnt0 > 0){
                    max0 = Math.max(max0, preCnt0 + cnt);
                }
                preCnt0 = cnt;
            }

            cnt = 1;
        }

        if(chars[n - 1] == '0' && preCnt0 > 0){
            max0 = Math.max(max0, preCnt0 + cnt);
        }

        return max0 + total1;
    }

    public static int maxActiveSectionsAfterTradeII(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int total1 = 0;
        int max0 = 0;
        int preCnt0 = 0;

        int start = 0;
        while (start < n){
            if(chars[start] == '1'){
                total1++;
                start++;
                continue;
            }

            int end = start + 1;
            while (end < n && chars[end] == '0'){
                end++;
            }

            if(preCnt0 > 0){
                max0 = Math.max(max0, preCnt0 + end - start);
            }

            preCnt0 = end - start;
            start = end;
        }

        return total1 + max0;
    }

    /**
     * @Author 强仔不强
     * @Description 413. 等差数列划分
     * @Date 18:28 2025/12/4
     * @Param [nums]
     * @return int
     **/
    public static int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if(n < 3){
            return 0;
        }

        int ret = 0;
        int cnt = 2;

        for (int i = 2; i < n; i++) {
            if(nums[i] + nums[i - 2] == nums[i - 1] * 2){
                cnt++;
                ret += cnt - 2;
            } else {
                cnt = 2;
            }
        }

        return ret;
    }

    
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(numberOfArithmeticSlices(nums));
    }
}


