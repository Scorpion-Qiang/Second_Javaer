import java.util.*;

public class Main {
    /**
     * @Author 强仔不强
     * @Description 34. 在排序数组中查找元素的第一个和最后一个位置
     *                  有序数组中, 找到 >= target 的第一个元素下标;  找到 <= target 的最后一个元素的下标.
     * @Date 13:33 2025/12/5
     * @Param [nums, target]
     * @return int[]
     **/
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int[] ret = {-1, -1};

        int start = lowerBound(nums, target);
        if(start >= n || nums[start] != target){
            return ret;
        }
        ret[0] = start;

        int end = lowerBound(nums, target + 1) - 1;
        ret[1] = end;

        return ret;
    }


    // 找到有序数组中第一个 >= target 的元素的下标.
    // 二分查找,
    // 未查找区间 [left, right].
    // 如果不存在 >= target 的元素, left = n.
    public static int lowerBound(int[] nums, long target){
        int n = nums.length;

        int left = 0;
        int right = n - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            // int mid = (left + right) / 2;

            if(nums[mid] < target){
                left = mid + 1;                   // [0, left - 1] 所有元素 < target.
            } else {
                right = mid - 1;                  // [right + 1, n - 1] 所有元素 >= target.
            }
        }

        return left;
    }

    // 未查找区间 [left, right)
    public static int lowerBoundII(int[] nums, int target){
        int n = nums.length;

        int left = 0;
        int right = n;

        while (left < right){
            int mid = left + (right - left) / 2;
            // int mid = (left + right) / 2;

            if(nums[mid] < target){
                left = mid + 1;                   // [0, left - 1] 所有元素 < target
            } else {
                right = mid;                      // [right, n - 1] 所有元素 >= target.
            }
        }

        return left;
        // return right;
    }

    // 未查找区间 (left, right).
    public static int lowerBoundIII(int[] nums, int target){
        int n = nums.length;

        int left = -1;
        int right = n;

        while (left + 1 < right){
            int mid = left + (right - left) / 2;

            if(nums[mid] < target){
                left = mid;                     // [0, left] 所有元素 < target.
            } else {
                right = mid;                    // [right, n - 1] 所有元素 >= target.
            }
        }

        return right;
    }

    // 有序数组中, 找到第一个 >= target 的元素的下标.
    //              第一个 > target 的元素的下标.      ---> 第一个 >= target + 1 的元素的下标.
    //              最后一个 <= target 的元素的下标.   ---> 第一个 >= target + 1 的元素的下标 - 1.
    //              最后一个 < target 的元素的下标.    ---> 第一个 >= target 的元素的下标 - 1.

    /**
     * @Author 强仔不强
     * @Description 704. 二分查找
     * @Date 16:14 2025/12/5
     * @Param [nums, target]
     * @return int
     **/
    public int search(int[] nums, int target) {
        int n = nums.length;

        int left = 0;
        int right = n - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return mid;
            }

            if(nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid + 1;
            }

        }

        return -1;
    }

    /**
     * @Author 强仔不强
     * @Description 744. 寻找比目标字母大的最小字母
     * @Date 16:20 2025/12/5
     * @Param [letters, target]
     * @return char
     **/
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        target += 1;

        int left = 0;
        int right = n - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if(letters[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left == n ? letters[0] : letters[left];
    }

    /**
     * @Author 强仔不强
     * @Description 2529. 正整数和负整数的最大计数
     * @Date 16:31 2025/12/5
     * @Param [nums]
     * @return int
     **/
    public int maximumCount(int[] nums) {
        int n = nums.length;
        int index1 = lowerBound(nums, 1);
        int index2 = lowerBound(nums, 0) - 1;

        return Math.max(n - index1, index2 + 1);

    }


    /**
     * @Author 强仔不强
     * @Description 2300. 咒语和药水的成功对数
     * @Date 16:37 2025/12/5
     * @Param [spells, potions, success]
     * @return int[]
     **/
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);

        int m = potions.length;
        int n = spells.length;
        int[] ret = new int[n];

        for (int i = 0; i < n; i++) {
            long target = success / spells[i];
            if(success % spells[i] > 0){
                target++;
            }

            int index = lowerBound(potions, target);
            ret[i] = m - index;
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 1385. 两个数组间的距离值
     * @Date 17:05 2025/12/5
     * @Param [arr1, arr2, d]
     * @return int
     **/

    // 若 arr2 中没有元素存在于 [val - d, val + d] 区间内, 则 val 符合条件.
    // arr2 排序, 找到 arr2 中第一个 >= val - d 的（最小的）元素 y, 若 y 不存在 或者 y > val + d, 则 arr2 中没有元素存在于 [val - d, val + d] 区间内
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int n2 = arr2.length;
        Arrays.sort(arr2);

        int ret = 0;

        for (int val : arr1) {
            // 找 第一个 >= arr[i] - d 的元素下标.
            int index = lowerBound(arr2, val - d);

            if (index == n2 || arr2[index] > val + d) {
                ret++;
            }
        }

        return ret;
    }

    // 双序列双指针
    // 若 arr2 中所有的元素 均 < val - d 或者 > val + d, 则 val 符合条件.
    public int findTheDistanceValueII(int[] arr1, int[] arr2, int d) {
        int n2 = arr2.length;

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int ret = 0;
        int j = 0;

        for (int val : arr1){
            while (j < n2 && arr2[j] < val - d){
                j++;
            }
            if(j == n2 || arr2[j] > val + d){
               ret++;
            }
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 2389. 和有限的最长子序列
     * @Date 19:44 2025/12/5
     * @Param [nums, queries]
     * @return int[]
     **/
    // 暴力
    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Arrays.sort(nums);

        int m = queries.length;
        for (int i = 0; i < m; i++) {
            int sum = 0;
            int j = 0;
            for ( ; j < n; j++) {
                sum += nums[j];
                if(sum > queries[i]){
                    break;
                }
            }
            queries[i] = j;
        }

        return queries;
    }

    // 前缀和 + 二分查找
    public static int[] answerQueriesII(int[] nums, int[] queries) {
        Arrays.sort(nums);

        int n = nums.length;
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }

        int m = queries.length;
        for (int i = 0; i < m; i++) {
            // nums 中, 找到 <= queries[i] 的最后一个元素的下标.
            int index = lowerBound(nums, queries[i] + 1) - 1;
            queries[i] = index;
        }

        return queries;
    }

    /**
     * @Author 强仔不强
     * @Description 1170. 比较字符串最小字母出现频次
     * @Date 20:26 2025/12/5
     * @Param [queries, words]
     * @return int[]
     **/
    // 二分查找
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = words.length;

        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            String s = words[i];
            cnt[i] = fun(s);
        }
        Arrays.sort(cnt);

        int m = queries.length;
        int[] ret = new int[m];
        for (int i = 0; i < m; i++) {
            String s = queries[i];
            int num = fun(s);

            ret[i] = n - lowerBound(cnt, num + 1);
        }

        return ret;
    }

    public int fun(String s){
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        int cnt = 0;
        for (int i = 1; i < chars.length; i++) {
            if(chars[i] == chars[i - 1]){
                cnt++;
            } else {
                break;
            }
        }

        return cnt;
    }

    // 前缀和
    public int[] numSmallerByFrequencyII(String[] queries, String[] words) {
        int[] counts = new int[12];

        for (String s : words){
            counts[funII(s)]++;
        }

        for (int i = 10; i >= 0; i--) {
            counts[i] += counts[i + 1];
        }

        int n = queries.length;
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            int val = funII(queries[i]);
            ret[i] = counts[val + 1];
        }

        return ret;
    }

    public int funII(String s){
        char[] chars = s.toCharArray();
        int n = chars.length;

        // 当前最小的字符.
        char ch = 'z';
        // 当前最小的字符的数量.
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if(chars[i] == ch){
                cnt++;
            } else if(chars[i] < ch){
                ch = chars[i];
                cnt = 1;
            }
        }

        return cnt;
    }

    /**
     * @Author 强仔不强
     * @Description
     * @Date 16:31 2025/12/6
     * @Param [nums, queries]
     * @return java.util.List<java.lang.Integer>
     **/
    public static List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;

        List<Integer>[] cnt = new List[1000001];
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            if(cnt[val] == null){
                cnt[val] = new ArrayList<>();
            }
            cnt[val].add(i);
        }


        int m = queries.length;
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int val = nums[queries[i]];

            List<Integer> list = cnt[val];
            if(list.size() == 1){
                ret.add(-1);
                continue;
            }

            // 不要重复添加.
            if(list.get(0) >= 0) {
                // 添加哨兵.
                int size = list.size();
                int start = list.get(0);
                int end = list.get(size - 1);

                list.add(0, end - n);
                list.add(start + n);
            }

            int k = lowerBound(list, queries[i]);

            int j1 = list.get(k - 1);
            int j2 = list.get(k + 1);


            ret.add(Math.min(queries[i] - j1, j2 - queries[i]));
        }
        return ret;
    }

    // 二分查找
    // 未查找区间 [left, right)
    public static int lowerBound(List<Integer> list, int target){
        int n = list.size();

        int left = 0;
        int right = n;

        while (left < right){
            int mid = left + (right - left) / 2;
            if(list.get(mid) < target){
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    /**
     * @Author 强仔不强
     * @Description 2563. 统计公平数对的数目
     * @Date 22:06 2025/12/6
     * @Param [nums, lower, upper]
     * @return long
     **/
    public long countFairPairs(int[] nums, int lower, int upper) {
        int n = nums.length;
        Arrays.sort(nums);

        long ret = 0;
        for (int i = 0; i < n - 1; i++) {
            int j = binarySearch(nums, lower - nums[i], i + 1, n - 1);
            int k = binarySearch(nums, upper - nums[i] + 1, i + 1, n - 1) - 1;

            ret += k - j + 1;
        }

        return ret;
    }

    public int binarySearch(int[] nums, int target, int left, int right){
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * @Author 强仔不强
     * @Description 2070. 每一个查询的最大美丽值
     * @Date 22:25 2025/12/6
     * @Param [items, queries]
     * @return int[]
     **/
    public int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // item[i][j] 表示价格 <= item[i] 的最大美丽值
        int n = items.length;
        for (int i = 1; i < n; i++) {
            items[i][1] = Math.max(items[i - 1][1], items[i][1]);
        }

        int m = queries.length;
        for (int i = 0; i < m; i++) {
            int index = binarySearchII(items, queries[i] + 1) - 1;
            if(index < 0){
                queries[i] = 0;
            } else {
                queries[i] = items[index][1];
            }
        }

        return queries;
    }

    public int binarySearchII(int[][] items, int target){
        int n = items.length;

        int left = 0;
        int right = n - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(items[mid][0] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }


    /**
     * @Author 强仔不强
     * @Description 658. 找到 K 个最接近的元素
     * @Date 15:03 2025/12/8
     * @Param [arr, k, x]
     * @return java.util.List<java.lang.Integer>
     **/
    // 二分 + 背向双指针.
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        int i = lowerBound(arr, x);

        // 区间 （left, right）
        int left = i - 1;
        int right = i;
        while (k > 0){

//            if(Math.abs(x - arr[left]) > Math.abs(x - arr[right]) ){
//                left++;
//            } else {
//                right--;
//            }

            if(left < 0){
                right++;
                k--;
                continue;
            }
            if(right >= n){
                left--;
                k--;
                continue;
            }

            if(x - arr[left] <= arr[right] - x){
                left--;
            } else {
                right++;
            }
            k--;
        }

        List<Integer> list = new ArrayList<>();
        for (int j = left + 1; j < right; j++) {
            list.add(arr[j]);
        }

        return list;
    }

    // 相向双指针.
    public List<Integer> findClosestElementsII(int[] arr, int k, int x) {
        int n = arr.length;

        // 区间 [left, right]
        int left = 0;
        int right = n - 1;

        while (right - left + 1 > k){
            if(arr[left] >= x){
                right--;
                continue;
            }
            if(arr[right] <= x){
                left++;
                continue;
            }

            if(x - arr[left] > arr[right] - x){
                left++;
            } else {
                right--;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int j = left; j <= right; j++) {
            list.add(arr[j]);
        }

        return list;
    }


    /**
     * @Author 强仔不强
     * @Description 1818. 绝对差值和
     * @Date 15:47 2025/12/8
     * @Param [nums1, nums2]
     * @return int
     **/
    public static int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        final int MOD = 1000000007;
        int n = nums1.length;

        int[] copy1 = Arrays.copyOf(nums1, n);
        Arrays.sort(copy1);

        int max = 0;
        for (int i = 0; i < n; i++) {
            int val1 = Math.abs(nums1[i] - nums2[i]);

            int j = lowerBound(copy1, nums2[i]);

            // 最靠近 nums2[i] 的数 与 nums2[i] 的最小的差值
            int val2 = 0;
            if(j == 0){
                val2 = copy1[j] - nums2[i];
            }
            if(j == n){
                val2 = nums2[i] - copy1[j - 1];
            }
            if(j > 0 && j < n){
                val2 = Math.min(copy1[j] - nums2[i], nums2[i] - copy1[j - 1]);
            }

            max = Math.max(val1 - val2, max);
        }

        long ret = 0;
        for (int i = 0; i < n; i++) {
            ret += Math.abs(nums1[i] - nums2[i]);
        }

        ret -= max;
        return (int) (ret % MOD);
    }

    
    /**
     * @Author 强仔不强
     * @Description LCP 08. 剧情触发时间
     * @Date 20:10 2025/12/8
     * @Param [increase, requirements]
     * @return int[]
     **/
    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int n = requirements.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);

        int m = increase.length;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                increase[i][j] += increase[i - 1][j];
            }
        }

        for (int i = 0; i < n; i++) {
            if(requirements[i][0] == 0 && requirements[i][1] == 0 && requirements[i][2] == 0){
                ret[i] = 0;
                continue;
            }

            int j1 = binarySearch(increase, 0,0, requirements[i][0]);
            if(j1 == m){
                continue;
            }

            int j2 = binarySearch(increase, j1,1, requirements[i][1]);
            if(j2 == m){
                continue;
            }

            int j3 = binarySearch(increase, j2,2, requirements[i][2]);
            if(j3 == m){
                continue;
            }

            ret[i] = j3 + 1;
        }

        return ret;
    }

    public int binarySearch(int[][] nums, int left, int index, int target){
        int n = nums.length;
        int right = n - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid][index] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    // 一次二分.
    public int[] getTriggerTimeII(int[][] increase, int[][] requirements) {
        int n = requirements.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);

        int m = increase.length;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                increase[i][j] += increase[i - 1][j];
            }
        }

        for (int i = 0; i < n; i++) {
            int[] require = requirements[i];
            if(require[0] == 0 && require[1] == 0 && require[2] == 0){
                ret[i] = 0;
                continue;
            }

            int left = 0;
            int right = m - 1;

            while (left <= right){
                int mid = left + (right - left) / 2;
                int[] arr = increase[mid];
                if(arr[0] < require[0] || arr[1] < require[1] || arr[2] < require[2]){
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            if(left == m){
                ret[i] = -1;
            } else {
                ret[i] = left + 1;
            }
        }

        return ret;
    }

    /** 二分答案 求最小 **/

    // 答案在 [left, right] 中.  [check(left), ... check(i - 1), check(i), check(i + 1), ... check(right)]  为 [false, ... false, true, true, ... true]
    // 找到答案最小值, 即 [left, right] 第一个 check（i）为 true 的值 i.
    public int binarySearch(int left, int right){

        while (left <= right){
            int mid = left + (right - left) / 2;

            boolean book = check(mid);
            if(!book){
                left = mid + 1;                    // i <= left - 1 , check（i） 均为 false;
            } else {
                right = mid - 1;                   // i >= right + 1 check（i） 均为 true;
            }
        }

        return left;
    }
    // 检查 答案 val 是否正确.
    public boolean check(int val){
        return false;
    }


    /**
     * @Author 强仔不强
     * @Description 2187. 完成旅途的最少时间
     * @Date 19:50 2025/12/9
     * @Param [time, totalTrips]
     * @return long
     **/
    public long minimumTime(int[] time, int totalTrips) {
        // 答案范围: [min{ time[i] }, min{ time[i] } * totalTrips]

        int n = time.length;
        int minTime = time[0];
        for (int t : time){
            minTime = Math.min(t, minTime);
        }

        long left = minTime;
        long right = (long) minTime * totalTrips;

        while (left <= right){
            long mid = left + (right - left) / 2;
            boolean book = check(time, totalTrips, mid);
            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public static boolean check(int[] time, int totalTrips, long totalTime){
        int n = time.length;
        long trips = 0;
        for (int i = 0; i < n; i++) {
            trips += totalTime / time[i];
            if(trips >= totalTrips){
                return true;
            }
        }

        return false;
    }


    /**
     * @Author 强仔不强
     * @Description 1011. 在 D 天内送达包裹的能力
     * @Date 19:49 2025/12/9
     * @Param [weights, days]
     * @return int
     **/
    public int shipWithinDays(int[] weights, int days) {
        int maxWeight = weights[0];
        int sum = 0;
        for (int weight : weights){
            maxWeight = Math.max(weight, maxWeight);
            sum += weight;
        }

        int left = maxWeight;
        int right = sum;

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = check(weights, days, mid);

            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public boolean check(int[] weights, int days, int limitWeight){
        int n = weights.length;

        int curDay = 1;
        int curWeight = 0;
        for (int weight : weights) {
            if (curWeight + weight > limitWeight) {
                curDay++;
                curWeight = 0;
            }
            curWeight += weight;
        }

        return curDay <= days;
    }


    /**
     * @Author 强仔不强
     * @Description 875. 爱吃香蕉的珂珂
     * @Date 20:19 2025/12/9
     * @Param [piles, h]
     * @return int
     **/
    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int maxCnt = 0;
        for (int cnt : piles){
            maxCnt = Math.max(maxCnt, cnt);
        }

        int left = 1;
        int right = maxCnt;

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = checkII(piles, h, mid);
            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public boolean checkII(int[] piles, int h, int speed){
        long curHour = 0;

        for (int cnt : piles){
            curHour += cnt / speed;
            if(cnt % speed > 0){
                curHour++;
            }

            if(curHour > h){
                return false;
            }
        }

        return true;
    }

    /**
     * @Author 强仔不强
     * @Description 3296. 移山所需的最少秒数
     * @Date 21:00 2025/12/9
     * @Param [mountainHeight, workerTimes]
     * @return long
     **/
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        int n = workerTimes.length;

        long left = workerTimes[0];
        for (int i = 0; i < n; i++) {
            left = Math.min(left, workerTimes[i]);
        }

        long right = left * (mountainHeight + 1) * mountainHeight / 2;

        while (left <= right){
            long mid = left + (right - left) / 2;
            boolean book = checkIII(workerTimes, mountainHeight, mid);
            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public boolean checkIII(int[] workTimes, int mountainHeight, long time){
        int totalHeight = 0;
        int n = workTimes.length;

        for (int i = 0; i < n; i++) {
            int height = 0;
            long speedTime = 0;

            while (speedTime <= time){
                height++;
                speedTime += (long) workTimes[i] * height;
            }

            totalHeight += height - 1;

            if(totalHeight >= mountainHeight){
                return true;
            }
        }

        return false;
    }


    /**
     * @Author 强仔不强
     * @Description 3639. 变为活跃状态的最小时间
     * @Date 15:44 2025/12/11
     * @Param [s, order, k]
     * @return int
     **/
    public static int minTime(String s, int[] order, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int left = 0;
        int right = n - 1;

        // 用 star 标记 chars 某处改为 “*“. 省去了每次查找时 复制 chars.
        int[] star = new int[n];
        Arrays.fill(star,-1);

        while (left <= right){
            int mid = left + (right - left) / 2;

            boolean book = check(star, order, k, mid);

            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if(left == n){
            left = -1;
        }
        return left;
    }

    public static boolean check(int[] star, int[] order, int k, int time){
        long ret = 0;

        // 由于每次 check 时, mid（time） 都不同. 若 star[i] == time, 则说明 chars[i] 处为 "*".
        for (int i = 0; i <= time; i++) {
            star[order[i]] = time;
        }

        // 以 chars[i - 1] 为末尾的 有效子字符串 的数量.
        int preCnt = 0;

        // 枚举 chars[i] 作为有效子字符串的末尾.
        for (int i = 0; i < star.length; i++) {
            int cnt = 0;
            if(star[i] == time){
                cnt = i + 1;
            } else {
                cnt = preCnt;
            }

            ret += cnt;
            preCnt = cnt;

            if(ret >= k){
                return true;
            }
        }

        return false;
    }



    /**
     * @Author 强仔不强
     * @Description 475. 供暖器
     * @Date 16:56 2025/12/11
     * @Param [houses, heaters]
     * @return int
     **/
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int n = houses.length;

        int left = 0;
        int right = Math.max(Math.abs(heaters[0] - houses[0]), Math.abs(heaters[0] - houses[n - 1]));

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = check(houses, heaters, mid);

            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return left;
    }

    public boolean check(int[] houses, int[] heaters, int len) {
        int n = houses.length;
        int m = heaters.length;

        int i = 0;
        int j = 0;

        while (i < n && j < m) {
            int distance = Math.abs(houses[i] - heaters[j]);
            if (distance > len) {
                j++;
            } else {
                i++;
            }
        }

        return i == n;
    }

    /**
     * @Author 强仔不强
     * @Description 2594. 修车的最少时间
     * @Date 17:22 2025/12/11
     * @Param [ranks, cars]
     * @return long
     **/
    public long repairCars(int[] ranks, int cars) {
        int n = ranks.length;


        long left = 1;
        long right = ranks[0];
        for (int val : ranks){
            right = Math.min(val, right);
        }
        right *= (long) cars * cars;

        while (left <= right){
            long mid = left + (right - left) / 2;
            boolean book = checkIII(ranks, mid, cars);

            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return left;
    }

    public boolean checkIII(int[] rank, long time, int cars){
        long totalCnt = 0;
        for (int val : rank){
            totalCnt += (long) Math.sqrt((double) time / val);
            if(totalCnt >= cars){
                return true;
            }
        }

        return false;
    }


    /**
     * @Author 强仔不强
     * @Description 1482. 制作 m 束花所需的最少天数
     * @Date 17:52 2025/12/11
     * @Param [bloomDay, m, k]
     * @return int
     **/
    public static int minDays(int[] bloomDay, int m, int k) {

        int left = bloomDay[0];
        int maxTime = 0;
        for (int val : bloomDay){
            left = Math.min(left, val);
            maxTime = Math.max(maxTime, val);
        }

        int right = maxTime;
        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = check(bloomDay, m, k, mid);

            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        if(left > maxTime){
            left = -1;
        }
        return left;
    }

    public static boolean check(int[] bloomDay, int m, int k, int time){
        int n = bloomDay.length;
        long totalCnt = 0;

        int start = 0;
        while (start < n - k + 1){
            if(bloomDay[start] > time){
                start++;
                continue;
            }

            int end = start + 1;
            while (end < n && bloomDay[end] <= time){
                end++;
            }

            totalCnt += (end - start) / k;
            if(totalCnt >= m){
                return true;
            }
            start = end;
        }

        return false;
    }

    /** 二分答案 求最大 **/
    // 答案在 [left, right] 中.  [check(left), ... check(i - 1), check(i), check(i + 1), ... check(right)]  为 [true, ... true, true, false, ... false]
    // 找到答案最大值, 即 [left, right] 最后一个 check（i）为 true 的值 i.

    public int binarySearchMax(int left, int right){
        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = check(mid);

            if(book){
                left = mid + 1;                    // i <= left - 1, check（i） 均为 true.
            } else {
                right = mid - 1;                   // i >= right + 1, check（i） 均为 false.
            }
        }

        return right;
    }


    /**
     * @Author 强仔不强
     * @Description 275. H 指数 II
     * @Date 21:04 2025/12/11
     * @Param [citations]
     * @return int
     **/
    public int hIndex(int[] citations) {
        int n = citations.length;

        int left = 0;
        int right = n;

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = check(citations, mid);
            if(book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    // citations 中, >= h 的元素的个数 是否 >= h.
    public boolean check(int[] citations, int h){
        int n = citations.length;

        // 二分找到 第一个 >= h 的元素.
        int left = 0;
        int right = n - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if(citations[mid] < h){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return n - left >= h;
    }


    /**
     * @Author 强仔不强
     * @Description 2226. 每个小孩最多能分到多少糖果
     * @Date 21:40 2025/12/11
     * @Param [candies, k]
     * @return int
     **/
    public int maximumCandies(int[] candies, long k) {
        long sum = 0;

        int left = 1;
        int right = 0;
        for (int val : candies){
            right = Math.max(right, val);
            sum += val;
        }

        if(sum < k){
            return 0;
        }

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = check(candies, k, mid);
            if(book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public boolean check(int[] candies, long k, int givenCnt){
        long cnt = 0;

        for (int val : candies){
            cnt += val / givenCnt;
            if(cnt >= k){
                return true;
            }
        }

        return false;
    }

    /**
     * @Author 强仔不强
     * @Description 2982. 找出出现至少三次的最长特殊子字符串 II
     * @Date 13:39 2025/12/12
     * @Param [s]
     * @return int
     **/
    public int maximumLength(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int left = 1;
        int right = n;

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = check(chars, mid);

            if(book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if(right == 0){
            right = -1;
        }
        return right;
    }

    public boolean check(char[] chars, int length){
        int n = chars.length;
        int[] cnt = new int[26];

        int start = 0;
        while (start < n - length + 1){
            int end = start + 1;
            while (end < n && chars[end] == chars[end - 1]){
                end++;
            }

            if(end - start >= length){
                cnt[chars[start] - 'a'] += (end - start) - (length - 1);

                if(cnt[chars[start] - 'a'] >= 3){
                    return true;
                }
            }

            start = end;
        }

        return false;
    }


    /**
     * @Author 强仔不强
     * @Description 2576. 求出最多标记下标
     * @Date 14:12 2025/12/12
     * @Param [nums]
     * @return int
     **/
    public int maxNumOfMarkedIndices(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int left = 0;
        int right = n / 2;

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = checkII(nums, mid);

            if(book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right * 2;
    }

    public boolean checkIII(int[] nums, int cnt){
        int n = nums.length;
        int left = cnt;
        for (int i = 0; i < cnt; i++) {
            int target = nums[i] * 2;

            int right = n - 1;

            while (left <= right){
                int mid = left + (right - left) / 2;
                if(nums[mid] < target){
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            if(left == n){
                return false;
            }

            left++;
        }

        return true;
    }


    public boolean checkII(int[] nums, int cnt){
        int n = nums.length;
        for (int i = 0; i < cnt; i++) {
            if(nums[i] * 2 > nums[n - cnt + i]){
                return false;
            }
        }

        return true;
    }

    /**
     * @Author 强仔不强
     * @Description 1898. 可移除字符的最大数目
     * @Date 14:55 2025/12/12
     * @Param [s, p, removable]
     * @return int
     **/
    public static int maximumRemovals(String s, String p, int[] removable) {
        int n = s.length();
        char[] chars1 = s.toCharArray();
        char[] chars2 = p.toCharArray();

        int left = 0;
        int right = removable.length;

        int[] star = new int[n];
        Arrays.fill(star, -1);

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = check(chars1, chars2, star, removable, mid);

            if(book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public static boolean check(char[] chars1, char[] chars2, int[] star, int[] removable, int k){
        for (int i = 0; i < k; i++) {
            star[removable[i]] = k;
        }

        int n = chars1.length;
        int m = chars2.length;

        int s1 = 0;
        int s2 = 0;

        while (s1 < n && s2 < m){
            if(star[s1] == k){
                s1++;
                continue;
            }

            if(chars1[s1] == chars2[s2]){
                s2++;
            }
            s1++;
        }

        return s2 == m;
    }

    /**
     * @Author 强仔不强
     * @Description 1802. 有界数组中指定下标处的最大值
     * @Date 15:20 2025/12/12
     * @Param [n, index, maxSum]
     * @return int
     **/
    public int maxValue(int n, int index, int maxSum) {
        return -1;
    }


    /**
     * @Author 强仔不强
     * @Description 1642. 可以到达的最远建筑
     * @Date 15:56 2025/12/12
     * @Param [heights, bricks, ladders]
     * @return int
     **/
    // 先用砖块, 再用绳子 不行
    // 设 m 个绳子, n 个砖块, 则前 m 大的高度差用绳子, 剩余的高度差用砖块.
    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        int n = heights.length;
        int i = 0;
        for( ; i < n - 1; i++) {
            if(heights[i] >= heights[i + 1]){
                continue;
            }

            int gap = heights[i + 1] - heights[i];
            if(bricks < gap && ladders == 0){
                break;
            }

            if(bricks >= gap){
                bricks -= gap;
            } else {
                ladders--;
            }
        }

        return i;
    }


    public int furthestBuildingII(int[] heights, int bricks, int ladders) {
        int n = heights.length;

        if(n == 1){
            return 0;
        }

        int left = 0;
        int right = n - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = checkII(heights, bricks, ladders, mid);

            if(book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public boolean checkII(int[] heights, int bricks, int ladders, int destination){
        long leaveGap = 0;

        PriorityQueue<Integer> queue = null;
        if(ladders > 0){
            queue = new PriorityQueue<>(ladders);
        }
        
        for (int i = 1; i <= destination; i++) {
            int gap = heights[i] - heights[i - 1];
            if(gap <= 0){
                continue;
            }
            
            if(queue != null){
                if(queue.size() < ladders){
                    queue.offer(gap);
                    continue;
                }
                
                if(gap > queue.peek()){
                    int gap1 = queue.poll();
                    queue.offer(gap);
                    gap = gap1;
                }
            }
            
            leaveGap += gap;
            
            if(leaveGap > bricks){
                return false;
            }
        }

        return true;
    }

    /**
     * @Author 强仔不强
     * @Description 2861. 最大合金数
     * @Date 17:24 2025/12/12
     * @Param [n, k, budget, composition, stock, cost]
     * @return int
     **/
    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int index = 0;
        int minLeave = stock.get(0);
        for (int i = 0; i < n; i++) {
            if(stock.get(i) < minLeave){
                minLeave = stock.get(i);
                index = i;
            }
        }

        long left = 0;
        long right = minLeave + budget / (cost.get(index));

        while (left <= right){
            long mid = left + (right - left) / 2;
            boolean book = check(composition, stock, cost, budget, n, mid);

            if(book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return (int) right;
    }

    public boolean check(List<List<Integer>> composition, List<Integer> stock, List<Integer> cost, int budget, int n, long totalCnt){
        for (List<Integer> list : composition){
            long totalCost = 0;
            for (int i = 0; i < n; i++) {
                // i 金属需要采购的数量.
                long cnt = Math.max(0, list.get(i) * totalCnt - stock.get(i));
                totalCost += cnt * cost.get(i);
            }

            if(totalCost <= budget){
                return true;
            }
        }

        return false;
    }

    /**
     * @Author 强仔不强
     * @Description
     * @Date 20:51 2025/12/12
     * @Param [points, s]
     * @return int
     **/
    public int maxPointsInsideSquare(int[][] points, String s) {
        // 先找 正方形 最大的边长.
        int left = 0;
        int right = 0;
        for (int[] p : points) {
            int distance = Math.max(Math.abs(p[0]), Math.abs(p[1]));
            right = Math.max(right, distance);
        }

        char[] chars = s.toCharArray();

        int[] star = new int[26];
        Arrays.fill(star, -1);

        while (left <= right) {
            int mid = left + (right - left) / 2;
            boolean book = check(points, chars, mid, star);

            if (book) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        int ret = 0;
        for (int[] p : points) {
            if (Math.abs(p[0]) <= right && Math.abs(p[1]) <= right) {
                ret++;
            }
        }

        return ret;
    }


    public boolean check(int[][] points, char[] chars, int length, int[] star){
        int n = points.length;
        for (int i = 0; i < n; i++) {
            int[] p = points[i];
            if(Math.abs(p[0]) > length || Math.abs(p[1]) > length){
                continue;
            }

            if(star[chars[i] - 'a'] == length){
                return false;
            }

            star[chars[i] - 'a'] = length;
        }

        return true;
    }

    /**
     * @Author 强仔不强
     * @Description 1648. 销售价值减少的颜色球
     * @Date 21:32 2025/12/12
     * @Param [inventory, orders]
     * @return int
     **/
    public int maxProfit(int[] inventory, int orders) {
        final int MOD = 1000000007;

        long ret = 0;
        int n = inventory.length;
        for (int i = 1; i <= orders; i++) {
            Arrays.sort(inventory);
            if(inventory[n - 1] == 0){
                break;
            }

            ret = (ret + inventory[n - 1]) % MOD;
            inventory[n - 1]--;
        }

        return (int)(ret % MOD);
    }


    /**
     * @Author 强仔不强
     * @Description 410. 分割数组的最大值
     * @Date 17:47 2025/12/16
     * @Param [nums, k]
     * @return int
     **/
    public int splitArray(int[] nums, int k) {
        int left = 0;
        int right = 0;
        for (int val : nums){
            left = Math.max(left, val);
            right += val;
        }

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = check(nums, mid, k);

            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public boolean checkIII(int[] nums, int max, int k){
        int cnt = 1;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if(sum + nums[i] <= max){
                sum += nums[i];
                continue;
            }

            cnt++;
            sum = 0;
            if(cnt > k){
                return false;
            }
        }

        return true;
    }

    /**
     * @Author 强仔不强
     * @Description 2064. 分配给商店的最多商品的最小值
     * @Date 19:24 2025/12/16
     * @Param [n, quantities]
     * @return int
     **/
    public int minimizedMaximum(int n, int[] quantities) {
        int left = 1;
        int right = 0;
        for (int val : quantities){
            right = Math.max(right, val);
        }

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = check(n, quantities, mid);

            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return left;
    }

    // 向上取整 a / b = (a + b - 1) / b
    public boolean check(int n, int[] quantities, int max){
        int totalCnt = 0;
        for (int val : quantities){
            // 向上取整
            int cnt = (val + max - 1) / max;

            totalCnt += cnt;
            if(totalCnt > n){
                return false;
            }
        }

        return true;
    }


    /**
     * @Author 强仔不强
     * @Description 3613. 最小化连通分量的最大成本
     * @Date 20:00 2025/12/16
     * @Param [n, edges, k]
     * @return int
     **/

    public static int minCost(int n, int[][] edges, int k) {
        int left = 0;
        int right = 0;

        for (int[] arr : edges){
            right = Math.max(arr[2], right);
        }

        while (left <= right){
            int mid = left + (right - left) / 2;

            HashMap<Integer, Integer> map = getMap(edges);
            boolean book = check(map, edges, k, mid);

            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public static HashMap<Integer, Integer> getMap(int[][] edges){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] arr : edges){
            for (int i = 0; i < 2; i++) {
                int cnt = map.getOrDefault(arr[i], 0);
                map.put(arr[i], cnt + 1);
            }
        }

        return map;
    }

    public static boolean check(HashMap<Integer, Integer> map, int[][] edges, int k, int max){
        int pageCnt = 1;
        for (int[] arr : edges){
            if(arr[2] <= max){
                continue;
            }

            for (int i = 0; i < 2; i++) {
                int cnt = map.get(arr[i]);
                if(cnt == 1){
                    map.remove(arr[i]);
                    if(!map.isEmpty()){
                        pageCnt++;
                    }

                } else {
                    map.put(arr[i], cnt - 1);
                }
            }

            if(pageCnt > k){
                return false;
            }
        }

        return true;
    }
    
    /**
     * @Author 强仔不强
     * @Description 1760. 袋子里最少数目的球
     * @Date 22:15 2025/12/16
     * @Param [nums, maxOperations]
     * @return int
     **/
    public int minimumSize(int[] nums, int maxOperations) {
        int left = 1;
        int right = 0;
        for (int val : nums){
            right = Math.max(right, val);
        }

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = checkIV(nums, maxOperations, mid);

            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public boolean checkIV(int[] nums, int maxOperations, int max){
        int cnt = 0;
        for (int val : nums){
            cnt += (val + max - 1) / max - 1;
            if(cnt > maxOperations){
                return false;
            }
        }
        
        return true;
    }


    /**
     * @Author 强仔不强
     * @Description 1631. 最小体力消耗路径
     * @Date 22:21 2025/12/16
     * @Param [heights]
     * @return int
     **/

    static int[][] next = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int minimumEffortPath(int[][] heights) {
        int left = 0;
        int right = 0;

        int row = heights.length;
        int column = heights[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                right = Math.max(heights[i][j], right);
            }
        }

        int[][] star = new int[row][column];
        for (int[] arr : star){
            Arrays.fill(arr, -1);
        }

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = checkIII(heights, row, column, mid, star);

            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    // DFS
    public static boolean check(int[][] height, int row, int column, int limit, int x, int y, int preX, int preY, int[][] star){
        if(x < 0 || x >= row || y < 0 || y >= column){
            return false;
        }

        if(Math.abs(height[x][y] - height[preX][preY]) > limit){
            return false;
        }

        if(x == row - 1 && y == column - 1){
            return true;
        }

        if(star[x][y] == limit){
            return false;
        }

        star[x][y] = limit;

        return check(height, row, column, limit, x - 1, y, x, y, star) ||
                check(height, row, column, limit, x + 1, y, x, y, star) ||
                check(height, row, column, limit, x, y + 1, x, y, star) ||
                check(height, row, column, limit, x, y - 1, x, y, star);

    }

    // DFS
    public boolean checkII(int[][] height, int row, int column, int limit, int x, int y, int[][] star){
        if(x == row - 1 && y == column - 1) {
            return true;
        }
        star[x][y] = limit;

        for (int[] direction : next) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            if(newX < 0 || newX >= row || newY < 0 || newY >= column){
                continue;
            }

            if(star[newX][newY] == limit){
                continue;
            }

            if(Math.abs(height[newX][newY] - height[x][y]) > limit){
                continue;
            }

            boolean book = checkII(height, row, column, limit, newX, newY, star);
            if(book){
                return true;
            }
        }

        return false;
    }

    static class Pos{
        int x;
        int y;

        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    // BFS
    public boolean checkIII(int[][] height, int row, int column, int limit, int[][] star){
        if(row == 1 && column == 1){
            return true;
        }

        Queue<Pos> queue = new LinkedList<>();

        Pos start = new Pos(0, 0);
        queue.offer(start);
        star[0][0] = limit;

        while (!queue.isEmpty()){
            Pos curPos = queue.poll();

            for (int[] direction : next){
                int newX = curPos.x + direction[0];
                int newY = curPos.y + direction[1];

                if(newX < 0 || newX >= row || newY < 0 || newY >= column){
                    continue;
                }

                if(Math.abs(height[newX][newY] - height[curPos.x][curPos.y]) > limit){
                    continue;
                }

                if(star[newX][newY] == limit){
                    continue;
                }

                if(newX == row - 1 && newY == column - 1){
                    return true;
                }

                star[newX][newY] = limit;
                Pos pos = new Pos(newX, newY);
                queue.offer(pos);
            }
        }

        return false;
    }


    /**
     * @Author 强仔不强
     * @Description 2439. 最小化数组中的最大值
     * @Date 16:40 2025/12/17
     * @Param [nums]
     * @return int
     **/
    public static int minimizeArrayValue(int[] nums) {
        int n = nums.length;

        int left = 0;
        int right = 0;
        for (int val : nums){
            right = Math.max(val, right);
        }

        while (left <= right){
            int mid = left + (right - left) / 2;

            long[] copy = new long[n];
            for (int i = 0; i < n; i++) {
                copy[i] = nums[i];
            }

            boolean book = checkIV(copy, mid);

            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    // copy 数组.
    public static boolean checkIV(long[] nums, int max){
        int n = nums.length;

        for (int i = n - 1; i >= 1; i--) {
            if(nums[i] <= max){
                continue;
            }

            nums[i - 1] += nums[i] - max;
        }

        return nums[0] <= max;
    }

    // 无需 copy 数组.
    public static boolean checkIIV(int[] nums, int max){
        int n = nums.length;

        long pre = nums[n - 1];

        for (int i = n - 1; i >= 1; i--) {
            long cur = pre;
            pre = nums[i - 1];

            if(cur <= max){
                continue;
            }

            pre += cur - max;
        }

        return pre <= max;
    }

    public boolean checkVI(int[] nums, int max){
        int n = nums.length;

        long extra = 0;
        for (int i = n - 1; i >= 1; i--) {
            long newNum = nums[i] + extra;
            extra = Math.max(newNum - max, 0);
        }

        return nums[0] + extra <= max;
    }

    /**
     * @Author 强仔不强
     * @Description 2560. 打家劫舍 IV
     * @Date 19:52 2025/12/17
     * @Param [nums, k]
     * @return int
     **/
    public int minCapability(int[] nums, int k) {
        int left = nums[0];
        int right = 0;
        for (int val : nums){
            left = Math.min(val, left);
            right = Math.max(val, right);
        }

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = checkVI(nums, k, mid);
            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    // DP
    // f[i] 表示 [0, i] 中最多能偷多少间房.
    // 若 nums[i] > max, nums[i] 不能被偷, 则 f[i] = f[i - 1].
    // 若 nums[i] <= max, nums[i] 可以被偷:
    //                                  选择偷 num[i], 则 f[i] = f[i - 2] + 1;   选择不偷 nums[i], 则 f[i] = f[i - 1].
    //                                  所以 f[i] = Math.max( f[i - 2] + 1, f[i - 1] ).
    public boolean checkVII(int[] nums, int k, int max){
        int n = nums.length;
        int f0 = 0;
        int f1 = 0;
        for (int val : nums){
            if(val > max){
                f0 = f1;
            } else {
                int temp = f1;
                f1 = Math.max(f0 + 1, f1);
                f0 = temp;
            }
        }

        return f1 >= k;
    }

    // 贪心
    // 遇到能偷的直接偷, 并跳过下一家.
    public boolean checkVI(int[] nums, int k, int max){
        int n = nums.length;

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if(nums[i] <= max){
                cnt++;
                i++;
            }
        }

        return cnt >= k;
    }

    /**
     * @Author 强仔不强
     * @Description 3281. 范围内整数的最大得分
     * @Date 15:11 2025/12/18
     * @Param [start, d]
     * @return int
     **/
    public int maxPossibleScore(int[] start, int d) {
        int n = start.length;
        Arrays.sort(start);

        int left = 0;
        int right = (start[n - 1] + d - start[0]) / (n - 1);


        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = checkVIII(start, d, mid);
            if(book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public boolean checkVIII(int[] start, int d, int min){
        int n = start.length;
        long pre = start[0];

        for (int i = 1; i < n; i++) {
            if(pre + min > start[i] + d){
                return false;
            }

            pre = Math.max(pre + min, start[i]);
        }

        return true;
    }

    /**
     * @Author 强仔不强
     * @Description 2517. 礼盒的最大甜蜜度
     * @Date 16:10 2025/12/18
     * @Param [price, k]
     * @return int
     **/
    public int maximumTastiness(int[] price, int k) {
        int n = price.length;
        Arrays.sort(price);

        int left = 0;
        int right = (price[n - 1] - price[0]) / (k - 1);

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = checkVV(price, k, mid);

            if(book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public boolean checkVV(int[] price, int k, int min){
        int n = price.length;

        int cnt = 1;
        int pre = price[0];

        for (int i = 1; i < n; i++) {
            if(price[i] >= pre + min){
                cnt++;
                pre = price[i];
            }

            if(cnt == k){
                return true;
            }
        }

        return false;
    }

    /**
     * @Author 强仔不强
     * @Description 2812. 找出最安全路径
     * @Date 17:18 2025/12/18
     * @Param [grid]
     * @return int
     **/
    // 超时.
    public static int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();

        int left = 0;
        int right = (n - 1) * 2;

        int[][] star = new int[n][n];
        for (int[] arr : star) {
            Arrays.fill(arr, -1);
        }

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = check(grid, n, 0, 0, mid, star);

            if(book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public static boolean check(List<List<Integer>> grid, int n, int x, int y, int min, int[][] star){
        star[x][y] = min;

        int step = min - 1;
        for (int i = x + step; i >= x - step; i--) {
            int stepY = step - Math.abs(i - x);
            for (int j = y - stepY; j <= y + stepY; j++) {
                if(i < 0 || i >= n || j < 0 || j >= n){
                    continue;
                }

                if(grid.get(i).get(j) == 1){
                    return false;
                }
            }
        }

        if(x == n - 1 && y == n - 1){
            return true;
        }

        for (int[] direction : next){
            int newX = x + direction[0];
            int newY = y + direction[1];

            if(newX < 0 || newX >= n || newY < 0 || newY >= n){
                continue;
            }
            if(star[newX][newY] == min){
                continue;
            }

            boolean book = check(grid, n, newX, newY, min, star);
            if(book){
                return true;
            }
        }

        return false;
    }

    /**
     * @Author 强仔不强
     * @Description 668. 乘法表中第k小的数
     * @Date 19:51 2025/12/18
     * @Param [m, n, k]
     * @return int
     **/
    public int findKthNumber(int m, int n, int k) {
        int left = 1;
        int right = m * n;

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = check(m, n, k, mid);

            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public boolean check(int m, int n, int k, int target){
        int cnt = 0;

        int i = 1;
        int j = n;
        while (i <= m && j >= 1){
            int val = i * j;
            if(val <= target){
                cnt += j;
                i++;
            } else {
                j--;
            }

            if(cnt >= k){
                return true;
            }
        }

        return false;
    }

    public boolean checkII(int m, int n, int k, int target){
        int cnt = 0;
        for (int i = 1; i <= m; i++) {
            cnt += Math.min(target / i, n);
            if(cnt >= k){
                return true;
            }
        }

        return false;
    }

    /**
     * @Author 强仔不强
     * @Description 378. 有序矩阵中第 K 小的元素
     * @Date 21:02 2025/12/18
     * @Param [matrix, k]
     * @return int
     **/
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];

        while(left <= right){
            int mid = left + (right - left) / 2;
            boolean book = check(matrix, k, mid);

            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    // 两个 二分查找.
    public boolean check(int[][] matrix, int k, int target){
        int cnt = 0;

        int n = matrix.length;

        int left = 0;
        int right = n - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if(matrix[mid][0] <= target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        for (int i = 0; i <= right; i++) {
            int start = 0;
            int end = n - 1;

            while (start <= end){
                int mid = start + (end - start) / 2;
                if(matrix[i][mid] <= target){
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

            cnt += end + 1;

            if(cnt >= k){
                return true;
            }
        }

        return false;
    }

    // 双指针, 从右上角元素开始看, 每次可以划掉 1 行 或 1 列.
    public boolean checkII(int[][] matrix, int k, int target){
        int n = matrix.length;

        int cnt = 0;
        int i = 0;
        int j = n - 1;

        while (i < n && j >= 0 && cnt < k){
            if(matrix[i][j] > target){
                j--;
            } else {
                cnt += j + 1;
                i++;
            }
        }

        return cnt >= k;
    }

    /**
     * @Author 强仔不强
     * @Description 240. 搜索二维矩阵 II
     * @Date 21:33 2025/12/18
     * @Param [matrix, target]
     * @return boolean
     **/
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int i = 0;
        int j = n - 1;

        while (i < m && j >= 0){
            if(matrix[i][j] == target){
                return true;
            }
            if(matrix[i][j] > target){
                j--;
            } else {
                i++;
            }
        }

        return false;
    }

    /**
     * @Author 强仔不强
     * @Description 
     * @Date 15:28 2025/12/19
     * @Param [x]
     * @return int
     **/
    public int mySqrt(int x) {
        if(x == 0 || x == 1){
            return x;
        }

        int left = 0;
        int right = x / 2;

        while (left <= right){
            int mid = left + (right - left) / 2;

            long val = (long) mid * mid;
            if(val <= x){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    /**
     * @Author 强仔不强
     * @Description 74. 搜索二维矩阵
     * @Date 15:54 2025/12/19
     * @Param [matrix, target]
     * @return boolean
     **/

    // 排除法.
    public boolean searchMatrixII(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int i = 0;
        int j = n - 1;

        while (i < m && j >= 0){
            if(matrix[i][j] == target){
                return true;
            }

            if(matrix[i][j] > target){
                j--;
            } else {
                i++;
            }
        }

        return false;
    }

    // 拉长数组, 二分查找.
    public static boolean searchMatrixIII(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = m * n - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;

            int i = mid / n;
            int j = mid % n;

            if(matrix[i][j] == target){
                return true;
            }

            if(matrix[i][j] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
    
    /**
     * @Author 强仔不强
     * @Description 278. 第一个错误的版本
     * @Date 16:23 2025/12/19
     * @Param [n]
     * @return int
     **/
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;

        while (left <= right){
            int mid = left + (right - left) / 2;
            boolean book = isBadVersion(mid);

            if(!book){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public boolean isBadVersion(int version){
        return true;
    }

    /**
     * @Author 强仔不强
     * @Description 374. 猜数字大小
     * @Date 16:30 2025/12/19
     * @Param [n]
     * @return int
     **/
    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        while (left <= right){
            int mid = left + (right - left) / 2;
            int book = guess(mid);

            if(book == 0){
                return mid;
            }

            if(book == 1){
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return 0;
    }

    public int guess(int num){
        return -1;
    }

    /**
     * @Author 强仔不强
     * @Description 162. 寻找峰值
     * @Date 17:04 2025/12/19
     * @Param [nums]
     * @return int
     **/
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if(n == 1){
            return 0;
        }

        if(nums[1] > nums[0]){
            return 0;
        }

        if(nums[n - 1] > nums[n - 2]){
            return n - 1;
        }

        int left = 1;
        int right = n - 2;

        while (left <= right){
            int mid = left + (right - left) / 2;

            if(nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1] ){
                return mid;
            }

            if(nums[mid] > nums[mid - 1]){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    public int findPeakElementII(int[] nums) {
        int n = nums.length;

        // 查找范围 [left, right],   峰值不一定在查找范围中.
        int left = 0;
        int right = n - 2;

        while (left <= right){
            int mid = left + (right - left) / 2;

            if(nums[mid] < nums[mid + 1]){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
    
    /**
     * @Author 强仔不强
     * @Description 1901. 寻找峰值 II
     * @Date 15:49 2025/12/20
     * @Param [mat]
     * @return int[]
     **/
    public static int[] findPeakGrid(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int left = 0;
        int right = m - 2;

        while (left <= right){
            int mid = left + (right - left) / 2;

            int maxIndex = 0;
            for (int i = 0; i < n; i++) {
                if(mat[mid][maxIndex] < mat[mid][i]){
                    maxIndex = i;
                }
            }

            if(mat[mid][maxIndex] < mat[mid + 1][maxIndex]){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if(mat[left][maxIndex] < mat[left][i]){
                maxIndex = i;
            }
        }

        return new int[]{left, maxIndex};
    }

    /**
     * @Author 强仔不强
     * @Description 852. 山脉数组的峰顶索引
     * @Date 18:05 2025/12/20
     * @Param [arr]
     * @return int
     **/
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;

        int left = 0;
        int right = n - 2;

        while (left <= right){
            int mid = left + (right - left) / 2;

            if(arr[mid] < arr[mid + 1]){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    /**
     * @Author 强仔不强
     * @Description
     * @Date 14:24 2025/12/21
     * @Param [target, mountainArr]
     * @return int
     **/
    public static int findInMountainArray(int target, MountainArray mountainArr) {
        int n = mountainArr.length();

        int left = 0;
        int right = n - 2;

        while (left <= right){
            int mid = left + (right - left) / 2;
            int val = mountainArr.get(mid);
            int valNext = mountainArr.get(mid + 1);
            if(val < valNext){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        int peakIndex = left;

        int index1 = getBinarySearch1(mountainArr, 0, peakIndex, target);
        int index2 = getBinarySearch2(mountainArr, peakIndex + 1, n - 1, target);

        return index1 != -1 ? index1 : index2;
    }

    // 数组顺序.
    public static int getBinarySearch1(MountainArray mountainArray, int left, int right, int target){
        while (left <= right){
            int mid = left + (right - left) / 2;
            int val = mountainArray.get(mid);

            if(val == target){
                return mid;
            }

            if(val < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    // 数组逆序.
    public static int getBinarySearch2(MountainArray mountainArray, int left, int right, int target){
        while (left <= right){
            int mid = left + (right - left) / 2;
            int val = mountainArray.get(mid);

            if(val == target){
                return mid;
            }

            if(val > target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * @Author 强仔不强
     * @Description 153. 寻找旋转排序数组中的最小值
     * @Date 15:35 2025/12/21
     * @Param [nums]
     * @return int
     **/
    // 找最大值.
    public int findMin(int[] nums) {
        int n = nums.length;

        int left = 0;
        int right = n - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] >= nums[0]){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return nums[right];
    }

    // 找最小值, 和最后一个元素比较.
    public int findMinII(int[] nums) {
        int n = nums.length;

        int left = 0;
        int right = n - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] <= nums[n - 1]){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return nums[left];
    }

    // 找最小值, 和第一个元素比较.
    public int findMinIII(int[] nums) {
        int n = nums.length;

        int left = 0;
        int right = n - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;

            if(nums[mid] >= nums[0]){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return nums[left % n];
    }

    /**
     * @Author 强仔不强
     * @Description 154. 寻找旋转排序数组中的最小值 II
     * @Date 17:13 2025/12/21
     * @Param [nums]
     * @return int
     **/
    public int findMinVII(int[] nums) {
        int n = nums.length;

        int left = 0;
        int right = n - 2;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == nums[right + 1]){
                right--;

            }

            if(nums[mid] < nums[right + 1]){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        System.out.println("hello, world!");
    }
}

class MountainArray{
    int[] array = new int[100];
    int size = 0;

    public MountainArray(int[] nums){
        for (int val : nums){
            array[size] = val;
            size++;
        }
    }

    public int get(int k){
        return array[k];
    }

    public int length(){
        return size;
    }
}

/**
 * @Author 强仔不强
 * @Description 2080. 区间内查询数字的频率
 * @Date 15:55 2025/12/6
 * @Param
 * @return 
 **/
class RangeFreqQuery {

    List<Integer>[] cnt = new List[10001];

    public RangeFreqQuery(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int val = arr[i];

            if (cnt[val] == null) {
                cnt[val] = new ArrayList<>();
            }
            cnt[val].add(i);
        }
    }

    public int query(int left, int right, int value) {
        List<Integer> list = cnt[value];
        if (list == null) {
            return 0;
        }

        return binarySearch(list, right + 1) - binarySearch(list, left);
    }

    // 有序数组中, 找到第一个 >= target 的元素的下标.
    // 未查找区间 [left, right]
    public int binarySearch(List<Integer> list, int target) {
        int n = list.size();

        int left = 0;
        int right = n - 1;

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
}


class SnapshotArray {
    int[] arr;
    List<int[]> list = new ArrayList<>();

    public SnapshotArray(int length) {
        this.arr = new int[length];
    }

    public void set(int index, int val) {
        this.arr[index] = val;
    }

    public int snap() {
        int[] copy = Arrays.copyOf(arr, arr.length);
        this.list.add(copy);

        return this.list.size() - 1;
    }

    public int get(int index, int snap_id) {
        return this.list.get(snap_id)[index];
    }
}

class SnapshotArrayII {
    // 当前照片的 snap_id.
    int curSnapId = 0;
    // 记录每一个 index 的修改记录.
    Map<Integer, List<int[]>> map = new HashMap<>();

//    // 用数组记录.
//    List<int[]>[] records;


    public SnapshotArrayII(int length) {

    }

    public void set(int index, int val) {
        List<int[]> list = map.getOrDefault(index, null);
        if(list == null){
            list = new ArrayList<>();
        }

        int[] record = new int[2];
        record[0] = curSnapId;
        record[1] = val;

        list.add(record);

        map.put(index, list);
    }

    public int snap() {
        curSnapId++;
        return curSnapId - 1;
    }

    public int get(int index, int snap_id) {
        List<int[]> list = map.getOrDefault(index, null);
        if(list == null){
            return 0;
        }

        // 找到 最后一个 int[0] <= snap_id 的 修改记录 的下标.
        int i = binarySearch(list, snap_id + 1) - 1;
        if(i < 0){
            return 0;
        }

        return list.get(i)[1];
    }

    // 找到 有序的 list（按照 int[0] 有序）中, int[0] >= target 的最后一个 "修改记录" 的下标.
    public int binarySearch(List<int[]> list, int target){
        int n = list.size();

        int left = 0;
        int right = n - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if(list.get(mid)[0] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}

/**
 * @Author 强仔不强
 * @Description 981. 基于时间的键值存储
 * @Date 19:09 2025/12/7
 * @Param
 * @return
 **/
class TimeMap {
    Map<String, List<String[]>> map = new HashMap<>();

    public TimeMap() {

    }

    public void set(String key, String value, int timestamp) {
        List<String[]> list = map.getOrDefault(key, null);
        if(list == null){
            list = new ArrayList<>();
        }

        String[] record = new String[2];
        record[0] = Integer.toString(timestamp);
        record[1] = value;

        list.add(record);

        map.put(key, list);
    }

    public String get(String key, int timestamp) {
        List<String[]> list = map.getOrDefault(key, null);
        if(list == null){
            return "";
        }

        // 找到 最后一个 String[0] <= timestamp 的 "记录" 的下标
        int i = binarySearch(list, timestamp + 1) - 1;
        if(i < 0){
            return "";
        }

        return list.get(i)[1];
    }

    // 找到 第一个 String[0] >= timestamp 的 "记录" 的下标
    public int binarySearch(List<String[]> list, int target){
        int n = list.size();

        String t = Integer.toString(target);
        int left = 0;
        int right = n - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            String m = list.get(mid)[0];

            if(m.length() < t.length() || (m.length() == t.length()  && m.compareTo(t) < 0) ){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}

/**
 * @Author 强仔不强
 * @Description 3508. 设计路由器
 * @Date 19:09 2025/12/7
 * @Param
 * @return
 **/
class Router {
    int limit = 0;
    int size = 0;

    Queue<int[]> queue = new LinkedList<>();
    HashMap<Integer, List<int[]>> map = new HashMap<>();


    public Router(int memoryLimit) {
        limit = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        List<int[]> list = map.getOrDefault(destination, null);
        if(list != null){
            // 找到 第一个 int[0] >= timestamp 的 "数据" 的下标.
            int i = binarySearch(list, timestamp);
            for (int j = i; j < list.size(); j++) {
                if(list.get(j)[0] > timestamp){
                    break;
                }
                if(list.get(j)[1] == source){
                    return false;
                }
            }
        } else {
            list = new ArrayList<>();
        }

        int[] data = new int[2];
        data[0] = timestamp;
        data[1] = source;

        list.add(data);
        map.put(destination, list);

        int[] dataQueue = new int[3];
        dataQueue[0] = source;
        dataQueue[1] = destination;
        dataQueue[2] = timestamp;

        queue.offer(dataQueue);
        size++;

        if(size > limit){
            forwardPacket();
        }

        return true;
    }

    public int[] forwardPacket() {
        if(size == 0){
            return new int[0];
        }

        int[] dataQueue = queue.poll();
        size--;

        int destination = dataQueue[1];
        List<int[]> list = map.get(destination);
        list.remove(0);
        if(list.size() == 0){
            map.remove(destination);
        }

        return dataQueue;
    }

    public int getCount(int destination, int startTime, int endTime) {
        List<int[]> list = map.getOrDefault(destination, null);
        if(list == null){
            return 0;
        }

        // 有序 list 中, 第一个 int[0] >= startTime 的 "数据" 的下标.
        int i = binarySearch(list, startTime);
        // 有序 list 中, 最后一个 int[0] <= endTime 的 "数据" 的下标.
        int j = binarySearch(list, endTime + 1) - 1;

        return j - i + 1;
    }

    // 在有序 list 中（int[0] 递增）, 找到 第一个 int[0] >= timestamp 的 "数据" 的下标.
    public int binarySearch(List<int[]> list, int target){
        int n = list.size();

        int left = 0;
        int right = n - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if(list.get(mid)[0] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}

class RouterII {
    class Packet{
        int source;
        int destination;
        int timestamp;

        public Packet(int source, int destination, int timestamp) {
            this.source = source;
            this.destination = destination;
            this.timestamp = timestamp;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Packet packet = (Packet) o;
            return source == packet.source && destination == packet.destination && timestamp == packet.timestamp;
        }

        @Override
        public int hashCode() {
            return Objects.hash(source, destination, timestamp);
        }
    }

    class Pair{
        int head;
        List<Integer> list;

        public Pair(int head, List<Integer> list) {
            this.head = head;
            this.list = list;
        }
    }

    int limit;
    Queue<Packet> queue = new LinkedList<>();
    Set<Packet> set = new HashSet<>();
    Map<Integer, Pair> map = new HashMap<>();

    public RouterII(int memoryLimit) {
        limit = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        Packet packet = new Packet(source, destination, timestamp);
        if(set.contains(packet)){
            return false;
        }
        queue.offer(packet);
        set.add(packet);

        Pair pair = map.getOrDefault(destination, null);
        if(pair == null){
            pair = new Pair(0, new ArrayList<>());
        }
        pair.list.add(timestamp);
        map.put(destination, pair);

        if(queue.size() > limit){
            forwardPacket();
        }

        return true;
    }

    public int[] forwardPacket() {
        if(queue.isEmpty()){
            return new int[0];
        }

        Packet packet = queue.poll();
        set.remove(packet);

        Pair pair = map.get(packet.destination);
        pair.head++;

        int[] ret = new int[3];
        ret[0] = packet.source;
        ret[1] = packet.destination;
        ret[2] = packet.timestamp;

        return ret;
    }

    public int getCount(int destination, int startTime, int endTime) {
        Pair pair = map.getOrDefault(destination, null);
        if(pair == null || pair.head == pair.list.size()){
            return 0;
        }


        // 有序 list 中, 从 head 开始, 第一个 >= startTime 的 "数据" 的下标.
        int i = binarySearch(pair.list, startTime, pair.head);
        // 有序 list 中, 从 head 开始, 最后一个 <= endTime 的 "数据" 的下标.
        int j = binarySearch(pair.list, endTime + 1, pair.head) - 1;

        return j - i + 1;
    }

    // 在有序 list 中, 从 left 开始, 找到 第一个 >= timestamp 的 "数据" 的下标.
    public int binarySearch(List<Integer> list, int target, int left){
        int n = list.size();

        int right = n - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if(list.get(mid) < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}

/**
 * @Author 强仔不强
 * @Description 911. 在线选举
 * @Date 16:39 2025/12/8
 * @Param
 * @return 
 **/
class TopVotedCandidate {

    Map<Integer, List<Integer>> map = new HashMap<>();

    public TopVotedCandidate(int[] persons, int[] times) {
        int n = persons.length;
        for (int i = 0; i < n; i++) {
            int person = persons[i];

            List<Integer> list = map.getOrDefault(person, null);
            if(list == null){
                list = new ArrayList<>();
            }
            list.add(times[i]);

            map.put(person, list);
        }
    }

    public int q(int t) {
        int winner = -1;
        int maxVoteCnt = 0;
        int voteTime = -1;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()){
            int person = entry.getKey();
            List<Integer> list = entry.getValue();

            if(maxVoteCnt > list.size()){
                continue;
            }

            // 找到有序的 list 中, 最后一个 <= t 的元素的下标.
            int i = binarySearch(list, t);

            int vote = i + 1;
            if(vote == 0){
                continue;
            }

            if(vote > maxVoteCnt || (vote == maxVoteCnt && voteTime < list.get(i))){
                maxVoteCnt = vote;
                winner = person;
                voteTime = list.get(i);
            }
        }

        return winner;
    }

    // 找到有序的 list 中, 最后一个 <= t 的元素的下标.
    public int binarySearch(List<Integer> list, int target){
        int n = list.size();

        int left = 0;
        int right = n - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if(list.get(mid) <= target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }
}

class TopVotedCandidateII {
    int[] winners;
    int[] times;

    public TopVotedCandidateII(int[] persons, int[] times) {
        int n = times.length;

        winners = new int[n];
        this.times = times;

        Map<Integer, Integer> map = new HashMap<>();
        winners[0] = persons[0];
        map.put(persons[0], 1);

        for (int i = 1; i < n; i++) {
            int person = persons[i];
            int votes = map.getOrDefault(person, 0);
            votes++;
            map.put(person, votes);

            if(votes >= map.get(winners[i - 1])){
                winners[i] = person;
            } else {
                winners[i] = winners[i - 1];
            }
        }
    }

    public int q(int t) {
        // 在有序数组 times 中, 找到 最后一个 <= t 的元素 的下标.
        int n = winners.length;

        int left = 0;
        int right = n - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if(times[mid] <= t){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return winners[right];
    }
}

