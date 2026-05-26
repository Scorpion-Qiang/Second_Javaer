import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.w3c.dom.ls.LSInput;

import javax.jnlp.ClipboardService;
import java.util.*;

class Structure{
    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 1. 两数之和
     * @Date 20:02 2025/12/21
     * @Param [nums, target]
     **/
    // 枚举右维护左.
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;

        int[] ret = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int index = map.getOrDefault(target - nums[i], -1);
            if (index != -1) {
                ret[0] = index;
                ret[1] = i;
                break;
            }
            map.put(nums[i], i);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1512. 好数对的数目
     * @Date 20:29 2025/12/21
     * @Param [nums]
     **/
    // 枚举右, 维护左.
    public int numIdenticalPairs(int[] nums) {
        int n = nums.length;

        int ret = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int val : nums) {
            int cnt = map.getOrDefault(val, 0);
            ret += cnt;

            map.put(val, cnt + 1);
        }

        return ret;
    }

    // 排序 + 分组循环.
    public int numIdenticalPairsII(int[] nums) {
        int n = nums.length;

        int ret = 0;
        Arrays.sort(nums);
        int cnt = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) {
                ret += cnt;
                cnt++;
            } else {
                cnt = 1;
            }
        }

        return ret;
    }


    public int numIdenticalPairsIII(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int ret = 0;
        int start = 0;
        while (start < n - 1) {
            int end = start + 1;
            while (end < n && nums[end] == nums[end - 1]) {
                end++;
            }

            int size = end - start;

            if (size >= 2) {
                ret += size * (size - 1) / 2;
            }

            start = end;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2441. 与对应负数同时存在的最大正整数
     * @Date 20:45 2025/12/21
     * @Param [nums]
     **/
    public int findMaxK(int[] nums) {
        int n = nums.length;

        int ret = -1;
        Set<Integer> set = new HashSet<>();
        for (int val : nums) {
            if (set.contains(-val)) {
                ret = Math.max(ret, Math.abs(val));
            }

            set.add(val);
        }

        return ret;
    }

    // 排序 + 双指针
    public int findMaxKII(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);
        int left = 0;
        int right = n - 1;
        while (left < right) {
            if (nums[left] + nums[right] == 0) {
                return nums[right];
            }

            if (nums[left] + nums[right] < 0) {
                left++;
            } else {
                right++;
            }
        }

        return -1;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 121. 买卖股票的最佳时机
     * @Date 22:01 2025/12/21
     * @Param [prices]
     **/
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int ret = 0;
        int preMin = prices[0];

        for (int i = 1; i < n; i++) {
            ret = Math.max(ret, prices[i] - preMin);
            preMin = Math.min(preMin, prices[i]);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2016. 增量元素之间的最大差值
     * @Date 22:13 2025/12/21
     * @Param [nums]
     **/
    public int maximumDifference(int[] nums) {
        int n = nums.length;

        int ret = -1;
        int preMin = nums[0];
        for (int j = 1; j < n; j++) {
            if (nums[j] <= preMin) {
                preMin = nums[j];
            } else {
                ret = Math.max(ret, nums[j] - preMin);
            }

//            ret = Math.max(ret, nums[j] - preMin);
//            preMin = Math.min(preMin, nums[j]);
        }

//        return ret <= 0 ? -1 : ret;

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 624. 数组列表中的最大距离
     * @Date 22:32 2025/12/21
     * @Param [arrays]
     **/
    public int maxDistance(List<List<Integer>> arrays) {
        int m = arrays.size();

        int ret = 0;
        List<Integer> arr0 = arrays.get(0);
        int preMin = arr0.get(0);
        int preMax = arr0.get(arr0.size() - 1);

        for (int i = 1; i < m; i++) {
            List<Integer> list = arrays.get(i);
            int n = list.size();

            int len = Math.max(list.get(n - 1) - preMin, preMax - list.get(0));
            ret = Math.max(ret, len);

            preMin = Math.min(preMin, list.get(0));
            preMax = Math.max(preMax, list.get(n - 1));
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2342. 数位和相等数对的最大和
     * @Date 15:15 2025/12/22
     * @Param [nums]
     **/
    public int maximumSum(int[] nums) {
        int ret = -1;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int val : nums) {
            int sum = 0;
            int v = val;
            while (v > 0) {
                sum += v % 10;
                v /= 10;
            }

            int num = map.getOrDefault(sum, 0);
            if (num > 0) {
                ret = Math.max(ret, num + val);
            }

            if (val > num) {
                map.put(sum, val);
            }
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1128. 等价多米诺骨牌对的数量
     * @Date 15:31 2025/12/22
     * @Param [dominoes]
     **/
    public static int numEquivDominoPairs(int[][] dominoes) {
        int ret = 0;

        // cnt[i][j] 表示骨牌 [i, j] 的数量.
        int[][] cnt = new int[10][10];
        for (int[] d : dominoes) {
            int x = d[0];
            int y = d[1];

            ret += cnt[x][y];
            if (x != y) {
                ret += cnt[y][x];
            }

            cnt[x][y]++;
        }

        return ret;
    }

    // 把 [1, 2], [2, 1] 看作一样的进行统计.
    public static int numEquivDominoPairsII(int[][] dominoes) {
        int ret = 0;

        // cnt[i][j] 表示骨牌 [i, j] 的数量.
        int[][] cnt = new int[10][10];
        for (int[] d : dominoes) {
            int x = Math.min(d[0], d[1]);
            int y = Math.max(d[0], d[1]);

            ret += cnt[x][y];

            cnt[x][y]++;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1679. K 和数对的最大数目
     * @Date 15:55 2025/12/22
     * @Param [nums, k]
     **/
    // 枚举右, 维护左.
    public int maxOperations(int[] nums, int k) {
        int ret = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int val : nums) {
            int cnt = map.getOrDefault(k - val, 0);
            if (cnt > 0) {
                map.put(k - val, cnt - 1);
                ret++;
            } else {
                int cnt2 = map.getOrDefault(val, 0);
                map.put(val, cnt2 + 1);
            }
        }

        return ret;
    }

    // 排序 + 双指针.
    public int maxOperationsII(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int ret = 0;

        int left = 0;
        int right = n - 1;
        while (left < right) {
            if (nums[left] + nums[right] == k) {
                ret++;
                left++;
                right--;
            } else if (nums[left] + nums[right] < k) {
                left++;
            } else {
                right--;
            }
        }

        return ret;
    }

    /**
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @Author 强仔不强
     * @Description 面试题 16.24. 数对和
     * @Date 16:06 2025/12/22
     * @Param [nums, target]
     **/
    public List<List<Integer>> pairSums(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int val : nums) {
            int cnt = map.getOrDefault(target - val, 0);
            if (cnt > 0) {
                List<Integer> list = new ArrayList<>();
                list.add(target - val);
                list.add(val);
                ret.add(list);

                map.put(target - val, cnt - 1);
            } else {
                int cnt2 = map.getOrDefault(val, 0);
                map.put(val, cnt2 + 1);
            }
        }

        return ret;
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 219. 存在重复元素 II
     * @Date 16:18 2025/12/22
     * @Param [nums, k]
     **/
    // 枚举右, 维护左.
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int index = map.getOrDefault(nums[i], -1);
            if (index != -1 && i - index <= k) {
                return true;
            }

            map.put(nums[i], i);
        }

        return false;
    }

    // 定长滑动窗口.
    // 长度为 min（k + 1, n） 的子数组中, 是否包含相同元素.
    public boolean containsNearbyDuplicateII(int[] nums, int k) {
        int n = nums.length;
        // 滑动窗口长度。
        k = Math.min(k + 1, n);

        Set<Integer> set = new HashSet<>();
        int start = 0;
        int end = 0;

        while (end < n) {
            if (end - start >= k) {
                set.remove(nums[start]);
                start++;
            }

            if (set.contains(nums[end])) {
                return true;
            }
            set.add(nums[end]);
            end++;
        }

        return false;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2260. 必须拿起的最小连续卡牌数
     * @Date 16:41 2025/12/22
     * @Param [cards]
     **/
    // 枚举右, 维护左.
    public int minimumCardPickup(int[] cards) {
        int n = cards.length;

        int ret = n + 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int index = map.getOrDefault(cards[i], -1);
            if (index != -1) {
                ret = Math.min(ret, i - index + 1);
            }

            map.put(cards[i], i);
        }

        return ret == n + 1 ? -1 : ret;
    }

    // 不定长滑动窗口
    // 包含两个相同元素的最短子数组.
    public int minimumCardPickupII(int[] cards) {
        int n = cards.length;

        int ret = n + 1;

        Set<Integer> set = new HashSet<>();
        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;

        while (end < n) {
            while (set.contains(cards[end])) {
                ret = Math.min(ret, end - start + 1);

                set.remove(cards[start]);
                start++;
            }

            set.add(cards[end]);
            end++;
        }

        return ret == n + 1 ? -1 : ret;
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description 2001. 可互换矩形的组数
     * @Date 19:35 2025/12/22
     * @Param [rectangles]
     **/
    public long interchangeableRectangles(int[][] rectangles) {
        HashMap<Double, Integer> map = new HashMap<>();
        long ret = 0;

        for (int[] rect : rectangles) {
            double val = (double) rect[0] / rect[1];

            int cnt = map.getOrDefault(val, 0);
            ret += cnt;

            map.put(val, cnt + 1);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2815. 数组中的最大数对和
     * @Date 19:39 2025/12/22
     * @Param [nums]
     **/
    public int maxSum(int[] nums) {
        int ret = -1;
        int[] array = new int[10];

        for (int val : nums) {
            int max = 0;
            int v = val;
            while (v > 0) {
                max = Math.max(max, v % 10);
                v /= 10;
            }

            if (array[max] > 0) {
                ret = Math.max(ret, array[max] + val);
            }

            array[max] = Math.max(array[max], val);
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 3623. 统计梯形的数目 I
     * @Date 20:06 2025/12/22
     * @Param [points]
     * @return int
     **/
    private final static int MOD = 1000000007;

    public int countTrapezoids(int[][] points) {

        // < y, 点的数量 >
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] p : points) {
            int cnt = map.getOrDefault(p[1], 0);
            map.put(p[1], cnt + 1);
        }

        long ret = 0;

        // 枚举右, 维护左.

        // s 表示当前枚举过的 y 坐标（行）的所有边的数量.
        long s = 0;

        // 枚举当前 y 坐标（行）处的边, 作为矩形的一条边.
        for (int val : map.values()) {
            // 当前 y 坐标处, 边的数量.
            long nums = (long) val * (val - 1) / 2;
            ret += nums * s;

            s += nums;
        }

        return (int) (ret % MOD);
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description
     * @Date 20:50 2025/12/22
     * @Param [nums]
     **/
    public long countBadPairs(int[] nums) {
        int n = nums.length;

        long ret = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int cnt = map.getOrDefault(nums[i] - i, 0);
            ret += i - cnt;

            map.put(nums[i] - i, cnt + 1);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description
     * @Date 21:08 2025/12/22
     * @Param [nums]
     **/
    public int getLargestOutlier(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int val : nums) {
            sum += val;

            int cnt = map.getOrDefault(val, 0);
            map.put(val, cnt + 1);
        }

        int ret = -1001;

        for (int specialSum : nums) {
            int expNum = sum - specialSum * 2;

            int cnt = map.getOrDefault(expNum, 0);
            // (cnt > 0 && (expNum != specialSum || cnt > 1))
            if (cnt > 1 || cnt == 1 && expNum != specialSum) {
                ret = Math.max(ret, expNum);
            }
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 3761. 镜像对之间最小绝对距离
     * @Date 15:53 2025/12/23
     * @Param [nums]
     **/
    public static int minMirrorPairDistance(int[] nums) {
        int n = nums.length;

        int ret = n;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {

            int index = map.getOrDefault(nums[i], -1);
            if (index != -1) {
                ret = Math.min(ret, i - index);
            }

            map.put(reverse(nums[i]), i);
        }

        return ret == n ? -1 : ret;
    }

    public static int reverse(int target) {
        int ret = 0;
        while (target > 0) {
            ret = ret * 10 + target % 10;
            target /= 10;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1014. 最佳观光组合
     * @Date 16:18 2025/12/23
     * @Param [values]
     **/
    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        int ret = Integer.MIN_VALUE;

        int max = 0;
        for (int j = 0; j < n; j++) {
            ret = Math.max(ret, max + values[j] - j);
            max = Math.max(max, values[j] + j);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1814. 统计一个数组中好对子的数目
     * @Date 16:32 2025/12/23
     * @Param [nums]
     **/
    public int countNicePairs(int[] nums) {
        long ret = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int val : nums) {
            int reverseVal = reverse(val);
            int cnt = map.getOrDefault(val - reverseVal, 0);
            ret += cnt;

            map.put(val - reverseVal, cnt + 1);
        }

        return (int) (ret % MOD);
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 3584. 子序列首尾元素的最大乘积
     * @Date 16:47 2025/12/23
     * @Param [nums]
     **/
    // 若子序列尾元素是 i, 则首元素 <= i - m + 1.
    // 枚举子序列尾元素, 维护 [0, i - m + 1] 的最大值和最小值.
    public long maximumProduct(int[] nums, int m) {
        int n = nums.length;

        long ret = Long.MIN_VALUE;

        long max = nums[0];
        long min = nums[0];
        int lastIndex = 0;

        for (int i = m - 1; i < n; i++) {
            if (m == 1) {
                ret = Math.max(ret, (long) nums[i] * nums[i]);
                continue;
            }

            long mul = Math.max(nums[i] * max, nums[i] * min);
            ret = Math.max(ret, mul);

            lastIndex++;
            max = Math.max(max, nums[lastIndex]);
            min = Math.min(min, nums[lastIndex]);
        }

        return ret;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description
     * @Date 17:23 2025/12/23
     * @Param [nums, indexDifference, valueDifference]
     **/
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        int max = nums[0];
        int min = nums[0];

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 0);

        int lastIndex = 0;

        for (int j = indexDifference; j < n; j++) {
            int distance = Math.max(nums[j] - min, max - nums[j]);

            if (distance >= valueDifference) {
                int i = -1;
                if (nums[j] - min == distance) {
                    i = map.get(min);
                } else {
                    i = map.get(max);
                }

                return new int[]{i, j};
            }

            lastIndex++;
            if (lastIndex == n) {
                break;
            }

            max = Math.max(max, nums[lastIndex]);
            min = Math.min(min, nums[lastIndex]);
            map.put(nums[lastIndex], lastIndex);
        }

        return new int[]{-1, -1};
    }

    public int[] findIndicesII(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        int minIndex = 0;
        int maxIndex = 0;

        for (int j = indexDifference; j < n; j++) {
            if (nums[minIndex] > nums[j - indexDifference]) {
                minIndex = j - indexDifference;
            }
            if (nums[maxIndex] < nums[j - indexDifference]) {
                maxIndex = j - indexDifference;
            }

            if (nums[maxIndex] - nums[j] >= valueDifference) {
                return new int[]{maxIndex, j};
            }
            if (nums[j] - nums[minIndex] >= valueDifference) {
                return new int[]{minIndex, j};
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1010. 总持续时间可被 60 整除的歌曲
     * @Date 20:32 2025/12/23
     * @Param [time]
     **/
    public int numPairsDivisibleBy60(int[] time) {
        int ret = 0;

        int[] cnt = new int[60];
        for (int val : time) {
            int num = val % 60;
            if (num == 0) {
                ret += cnt[0];
            } else {
                ret += cnt[60 - num];
            }

            cnt[num]++;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2748. 美丽下标对的数目
     * @Date 20:41 2025/12/23
     * @Param [nums]
     **/
    public int countBeautifulPairs(int[] nums) {
        int n = nums.length;
        int ret = 0;

        int[] cnt = new int[10];
        for (int j = 0; j < n; j++) {
            // nums[j] 的最后一位.
            int val = nums[j] % 10;

            for (int i = 1; i <= 9; i++) {
                if (cnt[i] > 0 && gcd(i, val) == 1) {
                    ret += cnt[i];
                }
            }

            while (nums[j] >= 10) {
                nums[j] /= 10;
            }

            cnt[nums[j]]++;
        }

        return ret;
    }

    // 辗转相除法求最大公约数.
    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2506. 统计相似字符串对的数目
     * @Date 21:11 2025/12/23
     * @Param [words]
     **/
    public int similarPairs(String[] words) {
        int ret = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : words) {
            char[] chars = new char[26];
            for (char c : s.toCharArray()) {
                chars[c - 'a']++;
            }

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (chars[i] > 0) {
                    builder.append(i + 'a');
                }
            }

            String string = builder.toString();
            int cnt = map.getOrDefault(string, 0);
            ret += cnt;

            map.put(string, cnt + 1);
        }

        return ret;
    }

    // 位运算.
    public int similarPairsII(String[] words) {
        int ret = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (String s : words) {
            int mask = 0;
            for (char c : s.toCharArray()) {
                mask |= 1 << (c - 'a');
            }

            int cnt = map.getOrDefault(mask, 0);
            ret += cnt;

            map.put(mask, cnt + 1);
        }

        return ret;
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description 2874. 有序三元组中的最大值 II
     * @Date 14:58 2025/12/24
     * @Param [nums]
     **/
    // 枚举 k
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;

        long ret = 0;
        int maxI = nums[0];
        long maxDiff = Long.MIN_VALUE;

        for (int j = 1; j < n - 1; j++) {
            maxDiff = Math.max(maxDiff, maxI - nums[j]);
            ret = Math.max(ret, maxDiff * nums[j + 1]);

            maxI = Math.max(maxI, nums[j]);
        }

        return ret;
    }

    public long maximumTripletValueII(int[] nums) {
        long ret = 0;
        int maxDiff = 0;
        int maxI = 0;

        for (int k : nums) {
            ret = Math.max(ret, (long) maxDiff * k);
            maxDiff = Math.max(maxDiff, maxI - k);
            maxI = Math.max(maxI, k);
        }

        return ret;
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 1497. 检查数组对是否可以被 k 整除
     * @Date 15:41 2025/12/24
     * @Param [arr, k]
     **/
    public static boolean canArrange(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            int remain = a % k;
            if (remain < 0) {
                remain += k;
            }

            int key = (k - remain) % k;
            int cnt = map.getOrDefault(key, 0);
            if (cnt == 0) {
                int cnt2 = map.getOrDefault(remain, 0);
                map.put(remain, cnt2 + 1);
                continue;
            }

            cnt--;
            if (cnt == 0) {
                map.remove(key);
            } else {
                map.put(key, cnt);
            }
        }

        return map.isEmpty();
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1031. 两个无重叠子数组的最大和
     * @Date 17:55 2025/12/24
     * @Param [nums, firstLen, secondLen]
     **/
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        return Math.max(maxSumTwoNoOverlapII(nums, firstLen, secondLen), maxSumTwoNoOverlapII(nums, secondLen, firstLen));
    }

    public int maxSumTwoNoOverlapII(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;

        int sumFirst = 0;
        int sumSecond = 0;

        for (int i = 0; i < firstLen; i++) {
            sumFirst += nums[i];
        }
        for (int i = firstLen; i < firstLen + secondLen; i++) {
            sumSecond += nums[i];
        }

        int maxSumFirst = sumFirst;
        int ret = sumFirst + sumSecond;

        for (int i = firstLen + secondLen; i < n; i++) {
            sumSecond += nums[i] - nums[i - secondLen];
            sumFirst += nums[i - secondLen] - nums[i - (firstLen + secondLen)];
            maxSumFirst = Math.max(maxSumFirst, sumFirst);

            ret = Math.max(ret, maxSumFirst + sumSecond);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2555. 两个线段获得的最多奖品
     * @Date 18:27 2025/12/24
     * @Param [prizePositions, k]
     **/
    // 一条线段能获得的最大礼品数.
    public static int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;

        int ret = 0;
        int start = 0;
        int end = 0;

        while (end < n) {
            while (prizePositions[end] - prizePositions[start] > k) {
                start++;
            }
            end++;
            ret = Math.max(ret, end - start);
        }

        return ret;
    }

    // 两条线段.
    public static int maximizeWinII(int[] prizePositions, int k) {
        int n = prizePositions.length;

        if (k * 2 >= prizePositions[n - 1] - prizePositions[0]) {
            return n;
        }

        int ret = 0;
        int[] max = new int[n];

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;

        while (end < n) {
            while (prizePositions[end] - prizePositions[start] > k) {
                start++;
            }

            int secondSize = end - start + 1;
            max[end] = end == 0 ? 1 : Math.max(secondSize, max[end - 1]);
            end++;

            int firstSize = start == 0 ? 0 : max[start - 1];
            ret = Math.max(ret, secondSize + firstSize);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 454. 四数相加 II
     * @Date 20:44 2025/12/24
     * @Param [nums1, nums2, nums3, nums4]
     **/
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {

        int ret = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int val1 : nums1) {
            for (int val2 : nums2) {
                int sum = val1 + val2;
                int cnt = map.getOrDefault(sum, 0);
                map.put(sum, cnt + 1);
            }
        }

        for (int val3 : nums3) {
            for (int val4 : nums4) {
                int sum = val3 + val4;
                int cnt = map.getOrDefault(-sum, 0);
                ret += cnt;
            }
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2909. 元素和最小的山形三元组 II
     * @Date 14:21 2025/12/28
     * @Param [nums]
     **/
    public int minimumSum(int[] nums) {
        int n = nums.length;
        int ret = Integer.MAX_VALUE;

        int[] backMin = new int[n];
        backMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            backMin[i] = Math.min(backMin[i + 1], nums[i]);
        }

        int frontMin = nums[0];
        for (int j = 1; j < n - 1; j++) {
            if (frontMin < nums[j] && backMin[j + 1] < nums[j]) {
                ret = Math.min(frontMin + nums[j] + backMin[j + 1], ret);
            }

            frontMin = Math.min(nums[j], frontMin);
        }

        return ret == Integer.MAX_VALUE ? -1 : ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 3583. 统计特殊三元组
     * @Date 14:35 2025/12/28
     * @Param [nums]
     **/
    public int specialTriplets(int[] nums) {
        int n = nums.length;

        HashMap<Integer, Integer> backMap = new HashMap<>();
        HashMap<Integer, Integer> frontMap = new HashMap<>();

        for (int val : nums) {
            int cnt = backMap.getOrDefault(val, 0);
            backMap.put(val, cnt + 1);
        }

        long ret = 0;
        for (int j = 0; j < n - 1; j++) {
            backMap.compute(nums[j], (k, cnt) -> cnt - 1);

            int frontCnt = frontMap.getOrDefault(nums[j] * 2, 0);
            int backCnt = backMap.getOrDefault(nums[j] * 2, 0);
            ret += (long) frontCnt * backCnt;

            frontMap.put(nums[j], frontMap.getOrDefault(nums[j], 0) + 1);
        }

        return (int) (ret % MOD);
    }

    // 用数组.
    public int specialTripletsII(int[] nums) {
        int n = nums.length;

        int[] front = new int[100001];
        int[] back = new int[100001];

        for (int val : nums) {
            back[val]++;
        }

        long ret = 0;
        for (int j = 0; j < n - 1; j++) {
            back[nums[j]]--;

            if (nums[j] * 2 <= 100000) {
                ret += (long) front[nums[j] * 2] * back[nums[j] * 2];
            }

            front[nums[j]]++;
        }

        return (int) (ret % MOD);
    }

    // 枚举 k
    public int specialTripletsIII(int[] nums) {
        int n = nums.length;

        long[] cnt12 = new long[100001];
        int[] cnt1 = new int[100001];
        long cnt123 = 0;

        for (int val : nums) {
            if (val % 2 == 0) {
                cnt123 += cnt12[val / 2];
            }

            if (val * 2 <= 100000) {
                cnt12[val] += cnt1[val * 2];
            }
            cnt1[val]++;
        }

        return (int) (cnt123 % MOD);
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description
     * @Date 15:59 2025/12/28
     * @Param [s]
     **/
    // 枚举中间元素.
    public static int countPalindromicSubsequence(String s) {
        char[] chars = s.toCharArray();
        int[] backCnt = new int[26];
        boolean[] frontHas = new boolean[26];

        for (char c : chars) {
            backCnt[c - 'a']++;
        }

        int ret = 0;
        // 标记数组. 记录已经统计的回文子序列.
        boolean[][] book = new boolean[26][26];

        for (char ch : chars) {
            backCnt[ch - 'a']--;

            for (int i = 0; i < 26; i++) {
                if (frontHas[i] && backCnt[i] > 0 && !book[i][ch - 'a']) {
                    book[i][ch - 'a'] = true;
                    ret++;
                }
            }

            frontHas[ch - 'a'] = true;
        }

        return ret;
    }

    // 位运算优化.
    public static int countPalindromicSubsequenceII(String s) {
        char[] chars = s.toCharArray();

        int[] backCnt = new int[26];
        int backHas = 0;
        for (char c : chars) {
            backCnt[c - 'a']++;
            backHas |= 1 << (c - 'a');
        }

        int frontHas = 0;

        int[] has = new int[26];
        for (char ch : chars) {
            int c = ch - 'a';
            backCnt[c]--;
            if (backCnt[c] == 0) {
                backHas ^= 1 << c;
            }

            has[c] |= backHas & frontHas;
            frontHas |= 1 << c;
        }

        int ret = 0;
        for (int val : has) {
            ret += getBitCnt(val);
        }

        return ret;
    }

    // 获取一个正整数的二进制中 1 的个数.
    public static int getBitCnt(int val) {
        int ret = 0;
        while (val > 0) {
            ret += val & 1;
            val >>= 1;
        }

        return ret;
    }


    // 枚举两侧字母  a ---> z
    public static int countPalindromicSubsequenceIII(String s) {
        char[] chars = s.toCharArray();

        int ret = 0;

        for (char alpha = 'a'; alpha <= 'z'; alpha++) {
            int firstIndex = s.indexOf(alpha);
            if (firstIndex == -1) {
                continue;
            }
            int lastIndex = s.lastIndexOf(alpha);

            int book = 0;
            for (int i = firstIndex + 1; i < lastIndex; i++) {
                if ((book & 1 << chars[i] - 'a') == 0) {
                    ret++;
                }
                book |= 1 << chars[i] - 'a';
            }
        }

        return ret;
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description 3128. 直角三角形
     * @Date 17:15 2025/12/28
     * @Param [grid]
     **/
    public long numberOfRightTriangles(int[][] grid) {
        long ret = 0;

        int row = grid.length;
        int col = grid[0].length;

        int[] colCnt = new int[col];
        for (int[] rowArr : grid) {
            for (int j = 0; j < col; j++) {
                colCnt[j] += rowArr[j];
            }
        }

        for (int[] rowArr : grid) {
            int rowCnt = 0;
            for (int x : rowArr) {
                rowCnt += x;
            }

            for (int j = 0; j < col; j++) {
                if (rowArr[j] == 1) {
                    ret += (long) (rowCnt - 1) * (colCnt[j] - 1);
                }
            }
        }

        return ret;
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description 2874. 有序三元组中的最大值 II
     * @Date 19:48 2025/12/28
     * @Param [nums]
     **/
    // 枚举 j
    public long maximumTripletValueVI(int[] nums) {
        int n = nums.length;
        long ret = 0;

        int[] backMax = new int[n];
        backMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            backMax[i] = Math.max(backMax[i + 1], nums[i]);
        }

        int maxI = 0;
        for (int j = 0; j < n - 1; j++) {
            ret = Math.max(ret, (long) (maxI - nums[j]) * backMax[j + 1]);
            maxI = Math.max(maxI, nums[j]);
        }

        return ret;
    }

    // 枚举 k
    public long maximumTripletValueVII(int[] nums) {
        long ret = 0;
        int maxI = 0;
        int maxDiff = Integer.MIN_VALUE;

        for (int k : nums) {
            ret = Math.max(ret, (long) maxDiff * k);
            maxDiff = Math.max(maxDiff, maxI - k);
            maxI = Math.max(maxI, k);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 447. 回旋镖的数量
     * @Date 20:26 2025/12/28
     * @Param [points]
     **/
    public int numberOfBoomerangs(int[][] points) {
        int ret = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int[] p : points) {
            map.clear();

            for (int[] pk : points) {
                int distance = (int) (Math.pow(pk[0] - p[0], 2) + Math.pow(pk[1] - p[1], 2));
                int cnt = map.getOrDefault(distance, 0);
                ret += cnt * 2;
                map.put(distance, cnt + 1);
            }
        }

        return ret;
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 456. 132 模式
     * @Date 20:35 2025/12/28
     * @Param [nums]
     **/
    // O(n^2) 超时.
    public static boolean find132pattern(int[] nums) {
        int n = nums.length;

        for (int k = 0; k < n; k++) {
            int minI = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                if (nums[j] > nums[k] && minI < nums[k]) {
                    return true;
                }
                minI = Math.min(minI, nums[j]);
            }
        }

        return false;
    }

    /**
     * @return int[][]
     * @Author 强仔不强
     * @Description 3446. 按对角线进行矩阵排序
     * @Date 21:08 2025/12/28
     * @Param [grid]
     **/
    // 令 i - j + n = k,  k = 1、2、...m + n - 1, 右上角对角线 --> 左下角对角线. 针对于某一条对角线而言, k 是定值.
    // 针对于 k1 这条对角线而言, minJ = max(0, n - k1), maxJ = min(n - 1, m + n - 1 - k1).
    public int[][] sortMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int k = 1; k < m + n; k++) {
            int minJ = Math.max(0, n - k);
            int maxJ = Math.min(n - 1, m + n - 1 - k);

            List<Integer> list = new ArrayList<>(maxJ - minJ + 1);
            for (int j = minJ; j <= maxJ; j++) {
                list.add(grid[k + j - n][j]);
            }

            // 右上三角.
            if (minJ > 0) {
                list.sort(null);
            } else {
                list.sort(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2 - o1;
                    }
                });
            }

            for (int j = minJ; j <= maxJ; j++) {
                grid[k + j - n][j] = list.get(j - minJ);
            }
        }


        return grid;
    }

    /**
     * @return int[][]
     * @Author 强仔不强
     * @Description 2711. 对角线上不同值的数量差
     * @Date 15:34 2025/12/29
     * @Param [grid]
     **/
    public static int[][] differenceOfDistinctValues(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int k = 1; k <= m + n - 1; k++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            int minJ = Math.max(0, n - k);
            int maxJ = Math.min(n - 1, n + m - k - 1);
            for (int j = minJ; j <= maxJ; j++) {
                int cnt = map.getOrDefault(grid[k - n + j][j], 0);
                map.put(grid[k - n + j][j], cnt + 1);
            }

            Set<Integer> set = new HashSet<>();
            for (int j = minJ; j <= maxJ; j++) {
                int i = k - n + j;

                int cnt = map.get(grid[i][j]);
                if (cnt == 1) {
                    map.remove(grid[i][j]);
                } else {
                    map.put(grid[i][j], cnt - 1);
                }
                int lastCnt = map.size();
                int frontCnt = set.size();
                set.add(grid[i][j]);

                grid[i][j] = Math.abs(frontCnt - lastCnt);
            }
        }

        return grid;
    }

    public static int[][] differenceOfDistinctValuesII(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Set<Integer> set = new HashSet<>();
        int[][] answer = new int[m][n];
        for (int k = 1; k <= m + n - 1; k++) {
            int minJ = Math.max(0, n - k);
            int maxJ = Math.min(n - 1, n + m - k - 1);

            set.clear();
            for (int j = minJ; j <= maxJ; j++) {
                int i = k - n + j;
                answer[i][j] = set.size();
                set.add(grid[i][j]);
            }

            set.clear();
            for (int j = maxJ; j >= minJ; j--) {
                int i = k - n + j;
                answer[i][j] = Math.abs(answer[i][j] - set.size());
                set.add(grid[i][j]);
            }
        }

        return answer;
    }

    // 位运算.
    public static int[][] differenceOfDistinctValuesIII(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m][n];

        for (int k = 1; k <= m + n - 1; k++) {
            int minJ = Math.min(0, n - k);
            int maxJ = Math.max(n - 1, n - k + m - 1);

            long set = 0;
            for (int j = minJ; j <= maxJ; j++) {
                int i = k - n + j;
                ans[i][j] = Long.bitCount(set);
                set |= 1L << grid[i][j];
            }

            set = 0;
            for (int j = maxJ; j >= minJ; j--) {
                int i = k - n + j;
                ans[i][j] = Math.abs(ans[i][j] - Long.bitCount(set));
                set |= 1L << grid[i][j];
            }
        }

        return ans;
    }


    /**
     * @return int[][]
     * @Author 强仔不强
     * @Description 1329. 将矩阵按对角线排序
     * @Date 16:48 2025/12/29
     * @Param [mat]
     **/
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        for (int k = 1; k <= m + n - 1; k++) {
            int minJ = Math.max(0, n - k);
            int maxJ = Math.min(n - 1, n - k + m - 1);

            // 插入排序
            for (int j = minJ + 1; j <= maxJ; j++) {
                int val = mat[k - n + j][j];

                int l = j - 1;
                for (; l >= minJ; l--) {
                    int i = k - n + l;
                    if (mat[i][l] > val) {
                        mat[i + 1][l + 1] = mat[i][l];
                    } else {
                        mat[i + 1][l + 1] = val;
                        break;
                    }
                }

                if (l < minJ) {
                    mat[k - n + minJ][minJ] = val;
                }

            }
        }

        return mat;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 498. 对角线遍历
     * @Date 17:37 2025/12/29
     * @Param [mat]
     **/
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] ret = new int[m * n];
        int l = 0;

        boolean book = true;
        for (int k = 0; k <= m + n - 2; k++) {
            int maxJ = Math.min(n - 1, k);
            int minJ = Math.max(0, k - m + 1);

            if (book) {
                for (int j = minJ; j <= maxJ; j++) {
                    ret[l++] = mat[k - j][j];
                }
            } else {
                for (int j = maxJ; j >= minJ; j--) {
                    ret[l++] = mat[k - j][j];
                }
            }

            book = !book;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 3427. 变长子数组求和
     * @Date 20:44 2025/12/29
     * @Param [nums]
     **/
    public int subarraySum(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        int ret = 0;
        for (int i = 1; i < n + 1; i++) {
            sum[i] = nums[i - 1] + sum[i - 1];

            int start = Math.max(0, (i - 1) - nums[i - 1]);
            ret += sum[i] - sum[start];
        }

        return ret;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 2559. 统计范围内的元音字符串数
     * @Date 21:02 2025/12/29
     * @Param [words, queries]
     **/
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] s = new int[n + 1];

        String s1 = "aeiou";
        Set<Character> set = new HashSet<>();
        for (char ch : s1.toCharArray()) {
            set.add(ch);
        }

        for (int i = 1; i < n + 1; i++) {
            s[i] = s[i - 1];

            String string = words[i - 1];
            if (set.contains(string.charAt(0)) && set.contains(string.charAt(string.length() - 1))) {
                s[i]++;
            }
        }

        int m = queries.length;
        int[] ret = new int[m];
        for (int i = 0; i < m; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            ret[i] = s[end + 1] - s[start];
        }

        return ret;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 1310. 子数组异或查询
     * @Date 21:22 2025/12/29
     * @Param [arr, queries]
     **/
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] s = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            s[i] = s[i - 1] ^ arr[i - 1];
        }

        int m = queries.length;
        int[] ret = new int[m];
        for (int i = 0; i < m; i++) {
            int start = queries[i][0];
            int end = queries[i][1];

            ret[i] = s[end + 1] ^ s[start];
        }

        return ret;
    }

    /**
     * @return boolean[]
     * @Author 强仔不强
     * @Description 3152. 特殊数组 II
     * @Date 21:30 2025/12/29
     * @Param [nums, queries]
     **/
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;

        int[] a = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] % 2 != nums[i + 1] % 2) {
                a[i] = 0;
            } else {
                a[i] = 1;
            }
        }

        int[] s = new int[n];
        for (int i = 1; i < n; i++) {
            s[i] = s[i - 1] + a[i - 1];
        }

        int m = queries.length;
        boolean[] ret = new boolean[m];
        for (int i = 0; i < m; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            if (start == end) {
                ret[i] = true;
                continue;
            }

            ret[i] = s[end] - s[start] == 0;
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 53. 最大子数组和
     * @Date 22:03 2025/12/29
     * @Param [nums]
     **/
    // 子数组（不为空）元素和 = 前缀和（s[j]） - 前缀和（s[i]） j > i.  此时的子数组就是以 i 开头，j - 1 结尾的子数组,
    public int maxSubArray(int[] nums) {
        int n = nums.length;

        int[] s = new int[n + 1];

        int min = 0;
        int ret = Integer.MIN_VALUE;
        for (int i = 1; i < n + 1; i++) {
            s[i] = s[i - 1] + nums[i - 1];
            ret = Math.max(ret, s[i] - min);
            min = Math.min(min, s[i]);
        }

        return ret;
    }

    public int maxSubArrayII(int[] nums) {
        int n = nums.length;

        int preSum = 0;
        int minPreSum = 0;
        int ret = Integer.MIN_VALUE;

        for (int x : nums) {
            preSum += x;
            ret = Math.max(ret, preSum - minPreSum);
            minPreSum = Math.min(minPreSum, preSum);
        }

        return ret;
    }


    /**
     * @return int
     * @Author 强仔不强
     * @Description 1749. 任意子数组和的绝对值的最大值
     * @Date 12:28 2025/12/30
     * @Param [nums]
     **/
    public int maxAbsoluteSum(int[] nums) {
        int n = nums.length;
        int ret = 0;
        int[] s = new int[n + 1];

        int min = 0;
        int max = 0;

        for (int i = 1; i < n + 1; i++) {
            s[i] = s[i - 1] + nums[i - 1];
            int val = Math.max(Math.abs(s[i] - min), Math.abs(s[i] - max));
            ret = Math.max(ret, val);

            min = Math.min(min, s[i]);
            max = Math.max(max, s[i]);
        }

        return ret;
    }


    public int maxAbsoluteSumII(int[] nums) {
        int n = nums.length;
        int ret = 0;

        int minPreSum = 0;
        int maxPreSum = 0;
        int preSum = 0;

        for (int x : nums) {
            preSum += x;
            ret = Math.max(ret, Math.max(preSum - minPreSum, maxPreSum - preSum));

            minPreSum = Math.min(minPreSum, preSum);
            maxPreSum = Math.max(maxPreSum, preSum);
        }

        return ret;
    }

    public int maxAbsoluteSumIII(int[] nums) {
        int n = nums.length;
        int minPreSum = 0;
        int maxPreSum = 0;
        int preSum = 0;

        for (int x : nums) {
            preSum += x;

            minPreSum = Math.min(preSum, minPreSum);
            maxPreSum = Math.max(preSum, maxPreSum);
        }

        return maxPreSum - minPreSum;
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description 3652. 按策略买卖股票的最佳时机
     * @Date 13:47 2025/12/30
     * @Param [prices, strategy, k]
     **/
    // 前缀和
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long[] c = new long[n + 1];
        long[] s = new long[n + 1];

        for (int i = 1; i < n + 1; i++) {
            c[i] = c[i - 1] + prices[i - 1] * strategy[i - 1];
            s[i] = s[i - 1] + prices[i - 1];
        }

        // 不修改.
        long ret = c[n];

        for (int j = 0; j <= n - k; j++) {
            long profit = c[j] + (c[n] - c[j + k]) + (s[j + k] - s[j + k / 2]);
            ret = Math.max(ret, profit);
        }

        return ret;
    }

    // 定长滑动窗口.
    public static long maxProfitII(int[] prices, int[] strategy, int k) {
        int n = prices.length;

        long profit = 0;
        for (int i = 0; i < n; i++) {
            profit += (long) prices[i] * strategy[i];
        }

        long ret = profit;

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;

        while (end < k) {
            profit -= (long) prices[end] * strategy[end];
            if (end >= k / 2) {
                profit += prices[end];
            }
            end++;
        }
        ret = Math.max(ret, profit);

        while (end < n) {
            profit -= (long) prices[end] * strategy[end];
            profit += prices[end];

            profit += (long) prices[start] * strategy[start];
            profit -= prices[start + k / 2];

            start++;
            end++;
            ret = Math.max(ret, profit);
        }

        return ret;
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description 3652. 按策略买卖股票的最佳时机
     * @Date 15:20 2025/12/30
     * @Param [prices, strategy, k]
     **/
    public static long maxProfitIII(int[] prices, int[] strategy, int k) {
        int n = prices.length;

        long total = 0;
        long maxAdd = 0;
        long add = 0;

        int start = 0;
        int end = 0;
        while (end < n) {
            total += (long) prices[end] * strategy[end];

            if (end < k) {
                if (end < k / 2) {
                    add += (long) prices[end] * (-strategy[end]);
                } else {
                    add += (long) prices[end] * (1 - strategy[end]);
                }

                end++;

                if (end == k) {
                    maxAdd = Math.max(add, maxAdd);
                }

                continue;
            }

            add += (long) prices[end] * (1 - strategy[end]);
            add -= (long) prices[start] * (-strategy[start]);
            add -= (long) prices[start + k / 2];

            maxAdd = Math.max(add, maxAdd);

            end++;
            start++;
        }

        return total + maxAdd;
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description 3361. 两个字符串的切换距离
     * @Date 16:22 2025/12/30
     * @Param [s, t, nextCost, previousCost]
     **/
    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        long[] nextS = new long[27];
        long[] preS = new long[27];
        for (int i = 1; i < 27; i++) {
            nextS[i] = nextS[i - 1] + nextCost[i - 1];
            preS[i] = preS[i - 1] + previousCost[i - 1];
        }

        long ret = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int s1 = s.charAt(i) - 'a';
            int t1 = t.charAt(i) - 'a';

            if (s1 == t1) {
                continue;
            }

            // 向后转.
            long nextCnt = nextS[t1] - nextS[s1];
            if (t1 < s1) {
                nextCnt += nextS[26];
            }

            // 向前转
            long preCnt = preS[s1 + 1] - preS[t1 + 1];
            if (t1 > s1) {
                preCnt += preS[26];
            }

            ret += Math.min(preCnt, nextCnt);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description
     * @Date 20:09 2025/12/30
     * @Param [nums, k]
     **/
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;

        int[] s = new int[n + 1];
        HashMap<Integer, Integer> map = new HashMap<>();
        int ret = 0;

        map.put(0, 1);
        for (int i = 1; i < n + 1; i++) {
            s[i] = s[i - 1] + nums[i - 1];
            int cnt = map.getOrDefault(s[i] - k, 0);
            ret += cnt;

            int cnt1 = map.getOrDefault(s[i], 0);
            map.put(s[i], cnt1 + 1);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 930. 和相同的二元子数组
     * @Date 20:34 2025/12/30
     * @Param [nums, goal]
     **/
    public int numSubarraysWithSum(int[] nums, int goal) {
        int ret = 0;

        int s = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int x : nums) {
            s += x;
            ret += map.getOrDefault(s - goal, 0);

            int cnt1 = map.getOrDefault(s, 0);
            map.put(s, cnt1 + 1);
        }

        return ret;
    }


    public int numSubarraysWithSumII(int[] nums, int goal) {
        return solve(nums, goal + 1) - solve(nums, goal);
    }

    // 元素和 >= goal 的子数组的个数.
    public int solve(int[] nums, int goal) {
        int n = nums.length;
        int ret = 0;

        int sum = 0;
        int start = 0;
        int end = 0;
        while (end < n) {
            sum += nums[end];
            end++;

            while (start < end && sum >= goal) {
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
     * @Description 1524. 和为奇数的子数组数目
     * @Date 21:06 2025/12/30
     * @Param [arr]
     **/
    public int numOfSubarrays(int[] arr) {
        long ret = 0;

        int s = 0;
        int[] cnt = new int[2];
        cnt[0] = 1;

        for (int x : arr) {
            s += x;
            int index = s % 2;
            ret += cnt[(index + 1) % 2];

            cnt[index]++;
        }

        return (int) (ret % MOD);
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 974. 和可被 K 整除的子数组
     * @Date 21:16 2025/12/30
     * @Param [nums, k]
     **/
    public int subarraysDivByK(int[] nums, int k) {
        int ret = 0;

        int s = 0;
        int[] cnt = new int[k];
        cnt[0] = 1;

        for (int x : nums) {
            s += x;
            int val = (s % k + k) % k;
            ret += cnt[(k - val) % k];

            cnt[val]++;
        }

        return ret;
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 523. 连续的子数组和
     * @Date 15:09 2026/1/6
     * @Param [nums, k]
     **/
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;

        Set<Integer> set = new HashSet<>();
        set.add(0);
        int preSum = nums[0];

        for (int i = 1; i < n; i++) {
            int sum = preSum;

            preSum += nums[i];

            int val = preSum % k;
            if (set.contains(val)) {
                return true;
            }

            set.add(sum % k);
        }

        return false;
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description 2588. 统计美丽子数组数目
     * @Date 15:30 2026/1/6
     * @Param [nums]
     **/
    public long beautifulSubarrays(int[] nums) {
        int n = nums.length;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        long ret = 0;
        int preSum = 0;

        for (int i = 0; i < n; i++) {
            preSum ^= nums[i];
            int cnt = map.getOrDefault(preSum, 0);
            ret += cnt;

            map.put(preSum, cnt + 1);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 525. 连续数组
     * @Date 15:55 2026/1/6
     * @Param [nums]
     **/
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap();
        map.put(0, 0);

        int ret = 0;
        int preSum = 0;
        for (int i = 1; i < n + 1; i++) {
            preSum += nums[i - 1];
            int key = preSum * 2 - i;
            int index = map.getOrDefault(key, n + 1);

            ret = Math.max(ret, i - index);

            if (index == n + 1) {
                map.put(key, i);
            }
        }

        return ret;
    }


    public int findMaxLengthII(int[] nums) {
        int n = nums.length;

        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int x = nums[i] == 0 ? -1 : 1;
            sum[i + 1] = sum[i] + x;
        }

        int ret = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n + 1; i++) {
            if (map.containsKey(sum[i])) {
                ret = Math.max(ret, i - map.get(sum[i]));
            } else {
                map.put(sum[i], i);
            }
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 3755. 最大平衡异或子数组的长度
     * @Date 17:29 2026/1/6
     * @Param [nums]
     **/
    public int maxBalancedSubarray(int[] nums) {
        int n = nums.length;

        int preSum = 0;
        int[] sum = new int[n + 1];

        int ret = 0;
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        List<Integer> list = new ArrayList<>();
        list.add(-1);
        map.put(0, list);

        for (int i = 0; i < n; i++) {
            preSum ^= nums[i];
            sum[i + 1] = sum[i];
            if (nums[i] % 2 == 0) {
                sum[i + 1]++;
            }

            List<Integer> l = map.getOrDefault(preSum, new ArrayList<>());
            for (int index : l) {
                if ((sum[i + 1] - sum[index + 1]) * 2 == i - index) {
                    ret = Math.max(ret, i - index);
                    break;
                }
            }

            l.add(i);
            map.put(preSum, l);
        }

        return ret;
    }

    static class Pair {
        int xor;
        int diff;

        public Pair(int xor, int diff) {
            this.xor = xor;
            this.diff = diff;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return xor == pair.xor && diff == pair.diff;
        }

        @Override
        public int hashCode() {
            return Objects.hash(xor, diff);
        }

    }

    public int maxBalancedSubarrayII(int[] nums) {
        int n = nums.length;
        HashMap<Pair, Integer> map = new HashMap<>();
        map.put(new Pair(0, 0), -1);

        int ret = 0;
        int xor = 0;
        int diff = 0;

        for (int i = 0; i < n; i++) {
            xor ^= nums[i];
            diff += nums[i] % 2 == 0 ? -1 : 1;


            Pair p = new Pair(xor, diff);
            if (map.containsKey(p)) {
                int index = map.get(p);
                ret = Math.max(ret, i - index);
            } else {
                map.put(p, i);
            }
        }

        return ret;
    }

    // 位运算 表示 Pair
    public int maxBalancedSubarrayIII(int[] nums) {
        int n = nums.length;

        HashMap<Long, Integer> map = new HashMap<>();

        long xor = 0;
        // 保证 diff 非负.
        int diff = n;
        map.put((long) diff, -1);

        int ret = 0;

        for (int i = 0; i < n; i++) {
            xor ^= nums[i];
            diff += nums[i] % 2 == 0 ? -1 : 1;
            long val = xor << 32 | diff;

            if (map.containsKey(val)) {
                ret = Math.max(ret, i - map.get(val));
            } else {
                map.put(val, i);
            }
        }

        return ret;
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description 3026. 最大好子数组和
     * @Date 20:34 2026/1/6
     * @Param [nums, k]
     **/
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;

        long preSum = 0;
        HashMap<Integer, Long> map = new HashMap<>();
        long ret = Long.MIN_VALUE;

        for (int x : nums) {

            if (map.containsKey(x + k) || map.containsKey(x - k)) {
                long sum = Math.min(map.getOrDefault(x + k, Long.MAX_VALUE), map.getOrDefault(x - k, Long.MAX_VALUE));
                ret = Math.max(ret, preSum + x - sum);
            }

            long val = map.getOrDefault(x, Long.MAX_VALUE);
            if (preSum < val) {
                map.put(x, preSum);
            }

            preSum += x;
        }

        return ret == Long.MIN_VALUE ? 0 : ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1477. 找两个和为目标值且不重叠的子数组
     * @Date 21:18 2026/1/6
     * @Param [arr, target]
     **/
    public static int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        // minLen[i] 表示 [0, i - 1] 范围内 元素和为 target 的最短子数组.
        int[] minLen = new int[n + 1];
        Arrays.fill(minLen, Integer.MAX_VALUE);

        int preSum = 0;
        int ret = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            preSum += arr[i];
            if (map.containsKey(preSum - target)) {
                int begin = map.get(preSum - target);
                int length = i - begin;

                if (begin >= 0) {
                    ret = (int) Math.min(ret, (long) length + minLen[begin + 1]);
                }

                minLen[i + 1] = Math.min(minLen[i], length);
            } else {
                minLen[i + 1] = minLen[i];
            }

            map.put(preSum, i);
        }

        return ret == Integer.MAX_VALUE ? -1 : ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1546. 和为目标值且不重叠的非空子数组的最大数目
     * @Date 12:51 2026/1/7
     * @Param [nums, target]
     **/
    // 前缀和 + 枚举左, 维护右 + 动态规划.
    public int maxNonOverlappingII(int[] nums, int target) {
        int n = nums.length;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int preSum = 0;

        // maxCnt[i + 1] 表示 [0, i] 范围内 和为 target 的子数组的最大数量.
        int[] maxCnt = new int[n + 1];


        for (int i = 0; i < n; i++) {
            preSum += nums[i];

            if (map.containsKey(preSum - target)) {
                int begin = map.get(preSum - target);
                maxCnt[i + 1] = Math.max(maxCnt[i], maxCnt[begin + 1] + 1);
            } else {
                maxCnt[i + 1] = maxCnt[i];
            }

            map.put(preSum, i);
        }

        return maxCnt[n + 1];
    }

    // 前缀和 + 枚举左, 维护右 + 贪心.
    public int maxNonOverlapping(int[] nums, int target) {
        int n = nums.length;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int preSum = 0;
        int last = -1;
        int ret = 0;

        for (int i = 0; i < n; i++) {
            preSum += nums[i];

            if (map.containsKey(preSum - target)) {
                int begin = map.get(preSum - target);

                if (last <= begin) {
                    ret++;
                    last = i;
                }
            }

            map.put(preSum, i);
        }

        return ret;
    }

    /**
     * @return java.util.List<java.lang.Long>
     * @Author 强仔不强
     * @Description 2602. 使数组元素全部相等的最少操作次数
     * @Date 14:30 2026/1/7
     * @Param [nums, queries]
     **/
    // 超时.
    public List<Long> minOperations(int[] nums, int[] queries) {
        List<Long> ret = new ArrayList<>();
        for (int x : queries) {
            long cnt = 0;
            for (int y : nums) {
                cnt += Math.abs(x - y);
            }
            ret.add(cnt);
        }

        return ret;
    }

    public List<Long> minOperationsII(int[] nums, int[] queries) {
        int n = nums.length;
        Arrays.sort(nums);

        long[] s = new long[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }

        List<Long> ret = new ArrayList<>();
        for (int target : queries) {
            int j = binarySearch(nums, target);

            long cnt1 = (long) j * target - s[j];
            long cnt2 = (s[n] - s[j]) - (long) (n - j) * target;

            ret.add(cnt1 + cnt2);
        }

        return ret;
    }

    // 有序数组 nums 中, 找到 >= target 的 第一个元素的下标.
    public int binarySearch(int[] nums, int target) {
        int n = nums.length;

        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 1685. 有序数组中差绝对值之和
     * @Date 15:00 2026/1/7
     * @Param [nums]
     **/
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;

        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }

        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            int area1 = i * nums[i] - s[i];
            int area2 = (s[n] - s[i + 1]) - (n - 1 - i) * nums[i];

            ret[i] = area1 + area2;
        }

        return ret;
    }

    /**
     * @return long[]
     * @Author 强仔不强
     * @Description 2615. 等值距离和
     * @Date 15:11 2026/1/7
     * @Param [nums]
     **/
    public long[] distance(int[] nums) {
        int n = nums.length;

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);

            map.put(nums[i], list);
        }

        long[] ret = new long[n];

        for (List<Integer> list : map.values()) {
            if (list.size() == 1) {
                continue;
            }
            getDistanceSum(ret, list);
        }

        return ret;
    }

    // 前缀和
    public void getDistanceSum(long[] ret, List<Integer> list) {
        int n = list.size();

        long[] s = new long[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + list.get(i);
        }

        for (int i = 0; i < n; i++) {
            int target = list.get(i);
            long area1 = (long) i * target - s[i];
            long area2 = (s[n] - s[i + 1]) - (long) (n - 1 - i) * target;
            ret[target] = area1 + area2;
        }
    }

    // 增量
    public void getDistanceSumII(long[] ret, List<Integer> list) {
        int n = list.size();

        long s = 0;
        int val0 = list.get(0);
        for (int i = 1; i < n; i++) {
            s += (long) list.get(i) - val0;
        }
        ret[val0] = s;

        for (int i = 1; i < n; i++) {
            int val = list.get(i);
            s += (long) (2 * i - n) * (val - list.get(i - 1));
            ret[val] = s;
        }

    }

    /**
     * @return java.util.List<java.lang.Boolean>
     * @Author 强仔不强
     * @Description 1177. 构建回文串检测
     * @Date 16:41 2026/1/7
     * @Param [s, queries]
     **/
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int n = s.length();

        List<Boolean> ret = new ArrayList<>(n);
        char[] chars = s.toCharArray();

        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] ^ (1 << chars[i] - 'a');
        }

        for (int[] q : queries) {
            int left = q[0];
            int right = q[1];

            int val = preSum[right + 1] ^ preSum[left];
            ret.add(Integer.bitCount(val) <= q[2] * 2 + 1);
        }

        return ret;
    }

    //
    public List<Boolean> canMakePaliQueriesII(String s, int[][] queries) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int[][] sum = new int[n + 1][26];
        sum[0] = new int[26];

        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i].clone();
            sum[i + 1][chars[i] - 'a'] ^= 1;
        }

        List<Boolean> ret = new ArrayList<>();
        for (int[] q : queries) {
            int left = q[0];
            int right = q[1];

            int cnt = 0;
            for (int i = 0; i < 26; i++) {
//                cnt += (sum[right + 1][i] - sum[left][i]) % 2;
//                cnt += sum[right + 1][i] != sum[left][i] ? 1 : 0;
                cnt += sum[right + 1][i] ^ sum[left][i];
            }

            ret.add(cnt / 2 <= q[2]);
        }

        return ret;
    }

    public List<Boolean> canMakePaliQueriesIII(String s, int[][] queries) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int[] sum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int bit = 1 << (chars[i] - 'a');
            sum[i + 1] = sum[i] ^ bit;
        }

        List<Boolean> ret = new ArrayList<>();
        for (int[] q : queries) {
            int left = q[0];
            int right = q[1];

            int cnt = Integer.bitCount(sum[right + 1] ^ sum[left]);
            ret.add(cnt / 2 <= q[2]);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1371. 每个元音包含偶数次的最长子字符串
     * @Date 19:53 2026/1/7
     * @Param [s]
     **/
    public int findTheLongestSubstring(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        String words = "aeiou";

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int ret = 0;

        for (int i = 0; i < n; i++) {
            int index = words.indexOf(chars[i]);
            int bit = index == -1 ? 0 : 1 << index;
            sum ^= bit;

            if (map.containsKey(sum)) {
                int j = map.get(sum);
                ret = Math.max(ret, i - j);
            } else {
                map.put(sum, i);
            }
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1542. 找出最长的超赞子字符串
     * @Date 20:23 2026/1/7
     * @Param [s]
     **/
    public int longestAwesome(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;

        int ret = 0;
        for (int i = 0; i < n; i++) {
            sum ^= 1 << (chars[i] - '0');

            if (map.containsKey(sum)) {
                int j = map.get(sum);
                ret = Math.max(ret, i - j);
            } else {
                map.put(sum, i);
            }

            for (int k = 0; k <= 9; k++) {
                int bit = 1 << k;

                if (map.containsKey(sum ^ bit)) {
                    int j = map.get(sum ^ bit);
                    ret = Math.max(ret, i - j);
                }
            }
        }

        return ret;
    }

    // 数组代替 map
    public int longestAwesomeII(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int[] pos = new int[1 << 10];
        Arrays.fill(pos, n);
        pos[0] = -1;

        int sum = 0;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            sum ^= 1 << (chars[i] - '0');

            for (int k = 0; k <= 9; k++) {
                int bit = 1 << k;
                int j = pos[sum ^ bit];

                ret = Math.max(ret, i - j);
            }

            ret = Math.max(ret, i - pos[sum]);
            if (pos[sum] == n) {
                pos[sum] = i;
            }
        }

        return ret;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 2389. 和有限的最长子序列
     * @Date 21:18 2026/1/7
     * @Param [nums, queries]
     **/
    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Arrays.sort(nums);

        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        int m = queries.length;
        int[] ret = new int[m];
        for (int i = 0; i < m; i++) {
            ret[i] = binarySearch(nums, queries[i] + 1) - 1;
        }

        return ret;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 2055. 蜡烛之间的盘子
     * @Date 15:01 2026/1/8
     * @Param \
     **/
    public static int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        char[] chars = s.toCharArray();

        List<Integer> pos = new ArrayList<>();

        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i];
            if (chars[i] == '*') {
                sum[i + 1]++;
            } else {
                pos.add(i);
            }
        }

        int m = queries.length;
        int[] ret = new int[m];

        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            int start = binarySearch(pos, q[0]);
            int end = binarySearch(pos, q[1] + 1) - 1;

            if (start >= end) {
                continue;
            }

            ret[i] = sum[pos.get(end)] - sum[pos.get(start)];
        }

        return ret;
    }

    // 有序数组中, 找到 >= target 的第一个元素的下标.
    public static int binarySearch(List<Integer> list, int target) {
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

    /**
     * @return boolean[]
     * @Author 强仔不强
     * @Description 1744. 你能在你最喜欢的那天吃到你最喜欢的糖果吗？
     * @Date 19:47 2026/1/8
     * @Param [candiesCount, queries]
     **/
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = candiesCount.length;

        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + candiesCount[i];
        }

        int m = queries.length;
        boolean[] ret = new boolean[m];

        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            int candy = q[0];
            int day = q[1];
            int maxCnt = q[2];

            ret[i] = ((long) (day + 1) * maxCnt > sum[candy]) && (day < sum[candy + 1]);
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1895. 最大的幻方
     * @Date 20:30 2026/1/8
     * @Param [grid]
     **/
    public static int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] rowSum = new int[m][n + 1];
        int[][] colSum = new int[m + 1][n];
        int[][] diagSum = new int[m + 1][n + 1];
        int[][] antiSum = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                rowSum[i][j + 1] = rowSum[i][j] + val;
                colSum[i + 1][j] = colSum[i][j] + val;
                diagSum[i + 1][j + 1] = diagSum[i][j] + val;
                antiSum[i + 1][j] = antiSum[i][j + 1] + val;
            }
        }


        for (int k = Math.min(m, n); ; k--) {
            for (int i = k; i <= m; i++) {
                for (int j = k; j <= n; j++) {
                    int sum = diagSum[i][j] - diagSum[i - k][j - k];

                    if (antiSum[i][j - k] - antiSum[i - k][j] != sum) {
                        continue;
                    }

                    boolean bookRow = true;
                    for (int l = i - k; l < i; l++) {
                        if (rowSum[l][j] - rowSum[l][j - k] != sum) {
                            bookRow = false;
                            break;
                        }
                    }

                    if (!bookRow) {
                        continue;
                    }

                    boolean bookCol = true;
                    for (int l = j - k; l < j; l++) {
                        if (colSum[i][l] - colSum[i - k][l] != sum) {
                            bookCol = false;
                            break;
                        }
                    }

                    if (bookCol) {
                        return k;
                    }
                }
            }
        }
    }


    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 3756. 连接非零数字并乘以其数字和 II
     * @Date 15:13 2026/1/10
     * @Param [s, queries]
     **/
    // 不会处理大数.
    public static int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int[] sum1 = new int[n + 1];
        long[] sumX = new long[n + 1];
        int[] sumZeroCnt = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int val = chars[i] - '0';
            sum1[i + 1] = sum1[i] + val;

            sumZeroCnt[i + 1] = sumZeroCnt[i];
            if (val == 0) {
                sumX[i + 1] = sumX[i];
                sumZeroCnt[i + 1]++;
                continue;
            }

            sumX[i + 1] = sumX[i] * 10 + val;
        }

        int m = queries.length;
        int[] ret = new int[m];

        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            int left = q[0];
            int right = q[1];

            int sum = sum1[right + 1] - sum1[left];

            int zeroCnt = sumZeroCnt[right + 1] - sumZeroCnt[left];
            int length = right - left + 1;
            long x = sumX[right + 1] - sumX[left] * (long) Math.pow(10, (length - zeroCnt));

            ret[i] = (int) (sum * x % MOD);
        }

        return ret;
    }

    /**
     * @return int[][]
     * @Author 强仔不强
     * @Description 1314. 矩阵区域和
     * @Date 17:10 2026/1/10
     * @Param [mat, k]
     **/
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + mat[i][j];
            }
        }

        int[][] ret = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int row1 = Math.max(i - k, 0);
                int row2 = Math.min(i + k, m);
                int col1 = Math.max(j - k, 0);
                int col2 = Math.min(j + k, n);

                ret[i][j] = sum[row2 + 1][col2 + 1] - sum[row2 + 1][col1] - sum[row1][col2 + 1] + sum[row1][col1];
            }
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 3070. 元素和小于等于 k 的子矩阵的数目
     * @Date 17:40 2026/1/10
     * @Param [grid, k]
     **/
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int ret = 0;

        int[][] sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + grid[i][j];
                if (sum[i + 1][j + 1] <= k) {
                    ret++;
                }
            }
        }

        return ret;
    }

    public int countSubmatricesII(int[][] grid, int k) {
        int n = grid[0].length;
        int ret = 0;
        int[] colSum = new int[n];

        for (int[] row : grid) {
            int s = 0;
            for (int j = 0; j < n; j++) {
                colSum[j] += row[j];
                s += colSum[j];

                if (s > k) {
                    break;
                }

                ret++;
            }
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1738. 找出第 K 大的异或坐标值
     * @Date 18:02 2026/1/10
     * @Param [matrix, k]
     **/
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;

        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] ^ sum[i][j + 1] ^ sum[i][j] ^ matrix[i][j];
                if (queue.size() < k) {
                    queue.offer(sum[i + 1][j + 1]);
                    continue;
                }

                if (sum[i + 1][j + 1] > queue.peek()) {
                    queue.poll();
                    queue.offer(sum[i + 1][j + 1]);
                }
            }
        }

        return queue.peek();
    }

    public int kthLargestValueII(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] colSum = new int[n];
        int[] ret = new int[m * n];
        int index = 0;

        for (int[] row : matrix) {
            int s = 0;
            for (int j = 0; j < n; j++) {
                colSum[j] ^= row[j];
                s ^= colSum[j];

                ret[index++] = s;
            }
        }

        Arrays.sort(ret);
        return ret[index - k];
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 3212. 统计 X 和 Y 频数相等的子矩阵数量
     * @Date 20:37 2026/1/10
     * @Param [grid]
     **/
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int ret = 0;
        int[][] sum = new int[m + 1][n + 1];
        boolean[][] book = new boolean[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = 0;
                if (grid[i][j] == 'X') {
                    val = -1;
                } else if (grid[i][j] == 'Y') {
                    val = 1;
                }

                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + val;

                book[i + 1][j + 1] = book[i + 1][j] || book[i][j + 1] || grid[i][j] == 'X';
                if (book[i + 1][j + 1] && sum[i + 1][j + 1] == 0) {
                    ret++;
                }
            }
        }

        return ret;
    }

    public int numberOfSubmatricesII(char[][] grid) {
        int n = grid[0].length;

        int[] colSum = new int[n];
        boolean[] colBook = new boolean[n];
        int ret = 0;

        for (char[] row : grid) {
            int s = 0;
            boolean book = false;

            for (int j = 0; j < n; j++) {
                int val = 0;
                if (row[j] == 'X') {
                    val = -1;
                } else if (row[j] == 'Y') {
                    val = 1;
                }

                colSum[j] += val;
                s += colSum[j];

                if (row[j] == 'X') {
                    colBook[j] = true;
                }
                if (!book) {
                    book = colBook[j];
                }

                if (s == 0 && book) {
                    ret++;
                }
            }
        }

        return ret;
    }

    public int numberOfSubmatricesIII(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int ret = 0;
        // sum[i + 1][j + 1][0] 表示 左上角 [0, 0] 到 右下角 [i, j] 的矩阵中 x 的个数.
        // sum[i + 1][j + 1][1] 表示 左上角 [0, 0] 到 右下角 [i, j] 的矩阵中 y 的个数.
        int[][][] sum = new int[m + 1][n + 1][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1][0] = sum[i + 1][j][0] + sum[i][j + 1][0] - sum[i][j][0];
                sum[i + 1][j + 1][1] = sum[i + 1][j][1] + sum[i][j + 1][1] - sum[i][j][1];

                if (grid[i][j] != '.') {
                    sum[i + 1][j + 1][grid[i][j] & 1]++;
                }

                if (sum[i + 1][j + 1][0] > 0 && sum[i + 1][j + 1][0] == sum[i + 1][j + 1][1]) {
                    ret++;
                }
            }
        }

        return ret;
    }

    public int numberOfSubmatricesIV(char[][] grid) {
        int n = grid[0].length;

        int[][] colSum = new int[n][2];
        int ret = 0;

        for (char[] row : grid) {
            int sx = 0;
            int sy = 0;

            for (int j = 0; j < n; j++) {
                if (row[j] != '.') {
                    colSum[j][row[j] & 1]++;
                }

                sx += colSum[j][0];
                sy += colSum[j][1];

                if (sx > 0 && sx == sy) {
                    ret++;
                }
            }
        }

        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1292. 元素和小于等于阈值的正方形的最大边长
     * @Date 21:45 2026/1/10
     * @Param [mat, threshold]
     **/
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + mat[i][j];
            }
        }

        // 暴力枚举.
        // 枚举右下角端点.
//        for (int k = Math.min(m, n); ; k--) {
//            for (int i = k; i <= m; i++) {
//                for (int j = k; j <= n; j++) {
//                    int s = sum[i][j] - sum[i][j - k] - sum[i - k][j] + sum[i - k][j - k];
//                    if(s <= threshold){
//                        return k;
//                    }
//                }
//            }
//        }

        // 二分答案.
        int left = 0;
        int right = Math.min(m, n);
        while (left <= right) {
            int mid = left + (right - left) / 2;
            boolean book = check(sum, mid, threshold);
            if (book) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public boolean check(int[][] sum, int k, int threshold) {
        // 枚举左上角端点.
        int row = sum.length - 1;
        int col = sum[0].length - 1;

        for (int i = 0; i <= row - k; i++) {
            for (int j = 0; j <= col - k; j++) {
                int s = sum[i + k][j + k] - sum[i + k][j] - sum[i][j + k] + sum[i][j];
                if (s <= threshold) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 1094. 拼车
     * @Date 16:01 2026/1/11
     * @Param [trips, capacity]
     **/
    public boolean carPooling(int[][] trips, int capacity) {
        int[] d = new int[1001];
        for (int[] t : trips) {
            int from = t[1];
            int to = t[2];
            int cnt = t[0];

            d[from] += cnt;
            d[to] += cnt;
        }

        int sum = 0;
        for (int i = 0; i < 1001; i++) {
            sum += d[i];
            if (sum > capacity) {
                return false;
            }
        }

        return true;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2848. 与车相交的点
     * @Date 16:14 2026/1/11
     * @Param [nums]
     **/
    // 差分数组.
    public int numberOfPoints(List<List<Integer>> nums) {
        int[] d = new int[102];
        for (List<Integer> pos : nums) {
            int start = pos.get(0);
            int end = pos.get(1);

            d[start]++;
            d[end + 1]--;
        }

        int ret = 0;
        int sum = 0;
        for (int val : d) {
            sum += val;
            if (sum > 0) {
                ret++;
            }
        }

        return ret;
    }

    // 合并区间.
    public int numberOfPointsII(List<List<Integer>> nums) {
        nums.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0) - o2.get(0);
            }
        });

        int ret = 0;
        int end = -1;

        for (List<Integer> pos : nums) {
            int left = pos.get(0);
            int right = pos.get(1);

            if (right <= end) {
                continue;
            }

            if (left <= end) {
                ret += right - end;
            } else {
                ret += right - left + 1;
            }

            end = right;
        }

        return ret;
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 1893. 检查是否区域内所有整数都被覆盖
     * @Date 16:44 2026/1/11
     * @Param [ranges, left, right]
     **/
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] d = new int[52];
        for (int[] r : ranges) {
            d[r[0]]++;
            d[r[1] + 1]--;
        }

        int sum = 0;
        for (int i = 0; i <= right; i++) {
            sum += d[i];

            if (i >= left && sum == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1854. 人口最多的年份
     * @Date 17:11 2026/1/11
     * @Param [logs]
     **/
    public int maximumPopulation(int[][] logs) {
        int maxD = 0;
        int minB = Integer.MAX_VALUE;
        for (int[] l : logs) {
            maxD = Math.max(maxD, l[1]);
            minB = Math.min(minB, l[0]);
        }

        int[] d = new int[maxD + 1];
        for (int[] l : logs) {
            d[l[0]]++;
            d[l[1]]++;
        }

        int sum = 0;

        int maxCnt = 0;
        int year = -1;
        for (int i = minB; i < maxD; i++) {
            sum += d[i];

            if (sum > maxCnt) {
                maxCnt = sum;
                year = i;
            }
        }

        return year;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2960. 统计已测试设备
     * @Date 17:26 2026/1/11
     * @Param [batteryPercentages]
     **/
    public int countTestedDevices(int[] batteryPercentages) {
        int n = batteryPercentages.length;

        int[] d = new int[n + 1];
        d[0] = batteryPercentages[0];
        for (int i = 1; i < n; i++) {
            d[i] = batteryPercentages[i] - batteryPercentages[i - 1];
        }

        int ret = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += d[i];
            if (sum <= 0) {
                continue;
            }

            ret++;
            d[i + 1]--;
        }

        return ret;
    }

    // 差分思想.
    public int countTestedDevicesII(int[] batteryPercentages) {
        int dec = 0;
        for (int x : batteryPercentages) {
            if (x - dec > 0) {
                dec++;
            }
        }

        return dec;
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 1109. 航班预订统计
     * @Date 19:38 2026/1/11
     * @Param [bookings, n]
     **/
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ret = new int[n];
        int[] d = new int[n + 1];
        for (int[] book : bookings) {
            d[book[0] - 1] += book[2];
            d[book[1]] -= book[2];
        }


        ret[0] = d[0];
        for (int i = 1; i < n; i++) {
            ret[i] = ret[i - 1] + d[i];
        }

        return ret;
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 3355. 零数组变换 I
     * @Date 19:47 2026/1/11
     * @Param [nums, queries]
     **/
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;

        int[] d = new int[n + 1];
        d[0] = nums[0];
        for (int i = 1; i < n; i++) {
            d[i] = nums[i] - nums[i - 1];
        }

        for (int[] q : queries) {
            d[q[0]]--;
            d[q[1] + 1]++;
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += d[i];
            if (sum > 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 2381. 字母移位 II
     * @Date 20:11 2026/1/11
     * @Param [s, shifts]
     **/
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int[] d = new int[n + 1];
        for (int[] shift : shifts) {
            int val = shift[2] == 0 ? -1 : 1;
            d[shift[0]] += val;
            d[shift[1] + 1] -= val;
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += d[i];
            char c = (char) (((chars[i] - 'a') + sum % 26 + 26) % 26 + 'a');
            chars[i] = c;
        }

        return new String(chars);
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2132. 用邮票贴满网格图
     * @Date 21:10 2026/1/11
     * @Param [nums, k]
     **/
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + grid[i][j];
            }
        }

        int[][] d = new int[m + 2][n + 2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int r2 = i + stampHeight;
                int c2 = j + stampWidth;
                if (r2 > m || c2 > n) {
                    continue;
                }
                int val = sum[r2][c2] - sum[r2][j] - sum[i][c2] + sum[i][j];
                if (val > 0) {
                    continue;
                }

                d[i + 1][j + 1]++;
                d[i + 1][c2 + 1]--;
                d[r2 + 1][j + 1]--;
                d[r2 + 1][c2 + 1]++;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                d[i + 1][j + 1] += d[i][j + 1] + d[i + 1][j] - d[i][j];
                if (d[i + 1][j + 1] == 0 && grid[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * @return int[][]
     * @Author 强仔不强
     * @Description 2536. 子矩阵元素加 1
     * @Date 15:44 2026/1/12
     * @Param [n, queries]
     **/
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] d = new int[n + 2][n + 2];
        for (int[] q : queries) {
            int r1 = q[0];
            int c1 = q[1];
            int r2 = q[2];
            int c2 = q[3];

            d[r1 + 1][c1 + 1]++;
            d[r1 + 1][c2 + 2]--;
            d[r2 + 2][c1 + 1]--;
            d[r2 + 2][c2 + 2]++;
        }

        int[][] ret = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i + 1][j + 1] = d[i + 1][j] + d[i][j + 1] - d[i][j];
                ret[i][j] = d[i + 1][j + 1];
            }
        }

        return ret;
    }

    /**
     * @return java.util.List<java.lang.String>
     * @Author 强仔不强
     * @Description
     * @Date 17:06 2026/1/12
     * @Param [target, n]
     **/
    public List<String> buildArray(int[] target, int n) {
        Stack<Integer> stack = new Stack<>();
        List<String> ret = new ArrayList<>();

        int k = 1;
        int i = 0;

        while (i < target.length) {
            stack.push(k++);
            ret.add("Push");

            if (stack.peek() == target[i]) {
                i++;
            } else {
                stack.pop();
                ret.add("Pop");
            }

        }

        return ret;
    }

    //
    public List<String> buildArrayII(int[] target, int n) {
        List<String> ret = new ArrayList<>();

        int max = target[target.length - 1];
        int i = 0;

        for (int j = 1; j <= max; j++) {
            ret.add("Push");
            if (j == target[i]) {
                i++;
            } else {
                ret.add("Pop");
            }
        }

        return ret;
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 844. 比较含退格的字符串
     * @Date 17:41 2026/1/12
     * @Param [s, t]
     **/
    public static boolean backspaceCompare(String s, String t) {
        Stack<Character> stack1 = getString(s);
        Stack<Character> stack2 = getString(t);

        if (stack1.size() != stack2.size()) {
            return false;
        }
        while (!stack1.isEmpty()) {
            if (stack1.pop() != stack2.pop()) {
                return false;
            }
        }

        return true;
    }

    public static Stack<Character> getString(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }

        return stack;
    }

    // 双指针.
    public static boolean backspaceCompareII(String s, String t) {
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();

        int i = s.length() - 1;
        int j = t.length() - 1;

        int cnt1 = 0;
        int cnt2 = 0;

        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (chars1[i] == '#') {
                    cnt1++;
                } else {
                    if (cnt1 > 0) {
                        cnt1--;
                    } else {
                        break;
                    }
                }

                i--;
            }

            while (j >= 0) {
                if (chars2[j] == '#') {
                    cnt2++;
                } else {
                    if (cnt2 > 0) {
                        cnt2--;
                    } else {
                        break;
                    }
                }

                j--;
            }

            if (i >= 0 && j >= 0) {
                if (chars1[i] != chars2[j]) {
                    return false;
                }
            } else {
                if (i >= 0 || j >= 0) {
                    return false;
                }
            }

            i--;
            j--;
        }

        return true;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 682. 棒球比赛
     * @Date 21:28 2026/1/12
     * @Param [operations]
     **/
    public static int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();
        int ret = 0;
        for (String s : operations) {
            int score = 0;
            if (s.equals("C")) {
                score = stack.pop();
                ret -= score;
                continue;
            }
            if (s.equals("D")) {
                score = stack.peek() * 2;
            } else if (s.equals("+")) {
                int lastScore = stack.pop();
                score = lastScore + stack.peek();
                stack.push(lastScore);
            } else {
                score = change(s);
//                score = Integer.parseInt(s);
            }

            stack.push(score);
            ret += score;
        }

        return ret;
    }

    // 字符串 转 数字
    public static int change(String s) {
        int ret = 0;
        int book = 1;
        for (char c : s.toCharArray()) {
            if (c == '-') {
                book = -1;
                continue;
            }
            ret *= 10;
            ret += c - '0';
        }

        return ret * book;
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 2390. 从字符串中移除星号
     * @Date 18:53 2026/1/13
     * @Param [s]
     **/
    public String removeStars(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '*') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        int n = stack.size();
        char[] chars = new char[n];
        for (int i = n - 1; i >= 0; i--) {
            chars[i] = stack.pop();
        }

        return new String(chars);
    }

    public String removeStarsII(String s) {
        int n = s.length();

        List<Character> list = new ArrayList<>();
        int cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '*') {
                cnt++;
            } else {
                if (cnt > 0) {
                    cnt--;
                } else {
                    list.add(c);
                }
            }
        }

        int m = list.size();
        char[] chars = new char[m];
        for (int i = 0; i < m; i++) {
            chars[i] = list.get(m - 1 - i);
        }

        return new String(chars);
    }

    public String removeStarsIII(String s) {
        StringBuilder st = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '*') {
                st.deleteCharAt(st.length() - 1);
            } else {
                st.append(c);
            }
        }

        return st.toString();
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 946. 验证栈序列
     * @Date 20:32 2026/1/13
     * @Param [pushed, popped]
     **/
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int j = 0;

        while (j < n) {
            if (stack.isEmpty() || stack.peek() != popped[j]) {
                if (i < n) {
                    stack.push(pushed[i]);
                } else {
                    break;
                }
                i++;
            } else {
                stack.pop();
                j++;
            }
        }

        return stack.isEmpty();
    }

    public boolean validateStackSequencesII(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;

        for (int val : pushed) {
            stack.push(val);
            while (stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        return stack.isEmpty();
    }

    /**
     * @return long
     * @Author 强仔不强
     * @Description 3412. 计算字符串的镜像分数
     * @Date 21:12 2026/1/13
     * @Param [s]
     **/
    // map
    public long calculateScore(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        long ret = 0;
        HashMap<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char ch = (char) (25 - (chars[i] - 'a') + 'a');
            List<Integer> list = map.getOrDefault(ch, new ArrayList<>());
            if (!list.isEmpty()) {
                int j = list.get(list.size() - 1);
                ret += i - j;
                list.remove(list.size() - 1);
            } else {
                List<Integer> l = map.getOrDefault(chars[i], new ArrayList<>());
                l.add(i);
                map.put(chars[i], l);
            }
        }

        return ret;
    }

    // 栈
    public long calculateScoreII(String s) {
        int n = s.length();

        Stack<Integer>[] stk = new Stack[26];
        for (int i = 0; i < 26; i++) {
            stk[i] = new Stack<>();
        }

        long ret = 0;
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (!stk[25 - ch].isEmpty()) {
                ret += i - stk[25 - ch].pop();
            } else {
                stk[ch].push(i);
            }
        }

        return ret;
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 71. 简化路径
     * @Date 21:45 2026/1/13
     * @Param [path]
     **/
    public String simplifyPath(String path) {
        int n = path.length();
        char[] chars = path.toCharArray();

        StringBuffer str = new StringBuffer();
        str.append('/');
        for (int i = 1; i < n; i++) {
            char last = str.charAt(str.length() - 1);

            if (chars[i] == '/' && last == '/') {
                continue;
            }

            // "/hello.."           hello..作为文件名
            // "/hello/..world"     ..world 是文件名
            // "/hello/.../world"   ...是文件名
            // "/hello/../world"    .. 是切换目录
            if (chars[i] == '.' && last == '/') {
                // "/hello/."
                // "/hello/./world"
                if (i == n - 1 || chars[i + 1] == '/') {
                    continue;
                }

                // "/hello/.."
                // "/hello/../world/"
                if (chars[i + 1] == '.' && (i == n - 2 || chars[i + 2] == '/')) {
                    // "/../"  --> "/"
                    // "/hello/world/../" --> "/hello/"
                    if (str.length() > 1) {
                        int k = str.length() - 1;
                        str.deleteCharAt(k--);
                        while (str.charAt(k) != '/') {
                            str.deleteCharAt(k--);
                        }
                    }

                    i++;
                    continue;
                }
            }

            str.append(chars[i]);
        }

        // "/" 或者 "/hello/world"  不变
        // "/hello/world/" --> "/hello/world"
        int m = str.length();
        if (m > 1 && str.charAt(m - 1) == '/') {
            str.deleteCharAt(m - 1);
        }

        return str.toString();
    }

    public String simplifyPathII(String path) {
        List<String> list = new ArrayList<>();

        for (String s : path.split("/")) {
            if (s.length() == 0 || s.equals(".")) {
                continue;
            }

            if (s.equals("..")) {
                if (list.size() > 1) {
                    list.remove(list.size() - 1);
                }
            } else {
                list.add(s);
            }
        }

        String ret = "/" + String.join("/", list);
        return ret;
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 3170. 删除星号以后字典序最小的字符串
     * @Date 17:34 2026/1/14
     * @Param [s]
     **/
    public String clearStars(String s) {
        int n = s.length();

        char[] chars = s.toCharArray();
        List<Integer>[] arr = new List[26];

        for (int i = 0; i < 26; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != '*') {
                arr[c - 'a'].add(i);
                continue;
            }

            for (int j = 0; j < 26; j++) {
                int m = arr[j].size();
                if (m > 0) {
                    int val = arr[j].remove(m - 1);
                    chars[val] = '*';
                    break;
                }
            }
        }

        // 原地修改.
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] != '*') {
                chars[j++] = chars[i];
            }
        }

        return new String(chars, 0, j);
    }

    // 位运算实现 快速找到 arr 中 list 不为空的 下标.
    public String clearStarsII(String s) {
        int n = s.length();

        char[] chars = s.toCharArray();
        List<Integer>[] arr = new List[26];
        int mask = 0;

        for (int i = 0; i < 26; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != '*') {
                arr[c - 'a'].add(i);
                mask |= 1 << (c - 'a');
                continue;
            }

            int k = Integer.numberOfTrailingZeros(mask);
            int val = arr[k].remove(arr[k].size() - 1);
            chars[val] = '*';

            if (arr[k].size() == 0) {
                mask ^= 1 << k;
            }
        }

        // 原地修改.
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] != '*') {
                chars[j++] = chars[i];
            }
        }

        return new String(chars, 0, j);
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2696. 删除子串后的字符串最小长度
     * @Date 21:24 2026/1/14
     * @Param [s]
     **/
    public int minLength(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && ((ch == 'B' && stack.peek() == 'A') || (ch == 'D' && stack.peek() == 'C'))) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        return stack.size();
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 1047. 删除字符串中的所有相邻重复项
     * @Date 21:33 2026/1/14
     * @Param [s]
     **/
    public String removeDuplicates(String s) {
        StringBuffer str = new StringBuffer();
        for (char ch : s.toCharArray()) {
            if (str.length() > 0 && ch == str.charAt(str.length() - 1)) {
                str.deleteCharAt(str.length() - 1);
            } else {
                str.append(ch);
            }
        }

        return str.toString();
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 1544. 整理字符串
     * @Date 21:39 2026/1/14
     * @Param [s]
     **/
    public String makeGood(String s) {
        StringBuffer str = new StringBuffer();
        for (char ch : s.toCharArray()) {
            if (str.length() > 0 && Math.abs(ch - str.charAt(str.length() - 1)) == 32) {
                str.deleteCharAt(str.length() - 1);
            } else {
                str.append(ch);
            }
        }

        return str.toString();
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 3561. 移除相邻字符
     * @Date 08:21 2026/1/21
     * @Param [s]
     **/
    public String resultingString(String s) {
        StringBuffer str = new StringBuffer();

        for (char c : s.toCharArray()) {
            int length = str.length();
            if (length > 0) {
                int val = Math.abs(str.charAt(length - 1) - c);
                if (val == 1 || val == 25) {
                    str.deleteCharAt(length - 1);
                    continue;
                }
            }

            str.append(c);
        }

        return str.toString();
    }

    // 数组
    public String resultingStringII(String s) {
        int n = s.length();

        char[] chars = new char[n];

        // 栈顶元素的下标.
        int top = -1;

        for (char c : s.toCharArray()) {
            if (top >= 0) {
                int val = Math.abs(chars[top] - c);
                if (val == 1 || val == 25) {
                    top--;
                    continue;
                }
            }

            top++;
            chars[top] = c;
        }

        return new String(chars, 0, top + 1);
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 1003. 检查替换后的词是否有效
     * @Date 23:16 2026/1/21
     * @Param [s]
     **/
    // 判断 s --> t
    public boolean isValid(String s) {
        int n = s.length();

        char[] chars = new char[n];
        int top = -1;

        for (char c : s.toCharArray()) {
            if (top >= 1 && chars[top - 1] == 'a' && chars[top] == 'b' && c == 'c') {
                top -= 2;
            } else {
                top++;
                chars[top] = c;
            }
        }

        return top < 0;
    }

    public boolean isValidII(String s) {
        int n = s.length();

        char[] chars = new char[n];
        int top = -1;

        for (char c : s.toCharArray()) {
            if (c > 'a' && (top == -1 || c - chars[top] != 1)) {
                return false;
            }

            if (c < 'c') {
                top++;
                chars[top] = c;
            }
        }

        return top < 0;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 2216. 美化数组的最少删除数
     * @Date 23:30 2026/1/26
     * @Param [nums]
     **/
    public int minDeletion(int[] nums) {
        int size = 0;

        for (int val : nums) {
            if (size % 2 == 0 || val != nums[size - 1]) {
                nums[size++] = val;
            }
        }

        if (size % 2 != 0) {
            size--;
        }

        return nums.length - size;
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 1209. 删除字符串中的所有相邻重复项 II
     * @Date 00:13 2026/1/27
     * @Param [s, k]
     **/
    public static String removeDuplicates(String s, int k) {
        char[] chars = s.toCharArray();
        int size = 0;

        for (char c : chars) {
            int i = size - 1;
            while (i >= 0 && chars[i] == c) {
                i--;
            }

            if (size - i == k) {
                size = i + 1;
            } else {
                chars[size++] = c;
            }
        }

        return new String(chars, 0, size);
    }

    public static String removeDuplicatesII(String s, int k) {
        StringBuilder str = new StringBuilder(s);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (i == 0 || str.charAt(i) != str.charAt(i - 1)) {
                stack.push(1);
                continue;
            }

            int cnt = stack.peek() + 1;
            stack.pop();
            if (cnt == k) {
                str.delete(i - k + 1, i + 1);
                i -= k;
            } else {
                stack.push(cnt);
            }
        }

        return str.toString();
    }

    class Pos {
        char c;
        int cnt;

        public Pos(char c, int cnt) {
            this.c = c;
            this.cnt = cnt;
        }
    }

    public String removeDuplicatesIII(String s, int k) {
        Stack<Pos> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (stack.isEmpty() || ch != stack.peek().c) {
                stack.push(new Pos(ch, 1));
                continue;
            }

            int cnt = stack.peek().cnt + 1;
            if (cnt == k) {
                stack.pop();
            } else {
                stack.peek().cnt++;
            }
        }

        StringBuilder str = new StringBuilder();
        while (!stack.isEmpty()) {
            Pos pos = stack.pop();
            while (pos.cnt > 0) {
                str.insert(0, pos.c);
                pos.cnt--;
            }
        }

        return str.toString();
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 3703. 移除 K-平衡子字符串
     * @Date 23:53 2026/1/27
     * @Param [s, k]
     **/
    public String removeSubstring(String s, int k) {
        List<int[]> stack = new ArrayList<>();
        int size = 0;

        for (char ch : s.toCharArray()) {
            if (size > 0 && stack.get(size - 1)[0] == ch) {
                stack.get(size - 1)[1]++;
            } else {
                stack.add(new int[]{ch, 1});
                size++;
            }

            if (ch == ')' && size >= 2 && stack.get(size - 1)[1] == k && stack.get(size - 2)[1] >= k) {
                stack.remove(size - 1);
                size--;

                stack.get(size - 1)[1] -= k;
                if (stack.get(size - 1)[1] == 0) {
                    stack.remove(size - 1);
                    size--;
                }
            }
        }

        StringBuilder str = new StringBuilder();
        for (int[] arr : stack) {
            while (arr[1] > 0) {
                str.append((char) arr[0]);
                arr[1]--;
            }
        }

        return str.toString();
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 20. 有效的括号
     * @Date 13:49 2026/1/28
     * @Param [s]
     **/
    public boolean isValidI(String s) {
        StringBuilder str = new StringBuilder(s);

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                continue;
            }

            if (i == 0) {
                return false;
            }

            char ch = str.charAt(i - 1);
            if ((ch == '(' && c == ')') || (ch == '[' && c == ']') || (ch == '{' && c == '}')) {
                str.delete(i - 1, i + 1);
                i -= 2;
            } else {
                return false;
            }
        }

        return str.length() == 0;
    }


    public boolean isValidIII(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char ch = stack.pop();
                if ((ch == '(' && c == ')') || (ch == '[' && c == ']') || (ch == '{' && c == '}')) {

                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }


    public boolean isValidIV(String s) {
        int n = s.length();

        if (n % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        for (char ch : s.toCharArray()) {
            if (map.containsKey(ch)) {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char c = stack.pop();
                if (map.get(c) != ch) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public boolean isValidIIV(String s) {
        int n = s.length();

        if (n % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (char ch : s.toCharArray()) {
            if (!map.containsKey(ch)) {
                stack.push(ch);
            } else if (stack.isEmpty() || stack.pop() != map.get(ch)) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 921. 使括号有效的最少添加
     * @Date 17:43 2026/1/29
     * @Param [s]
     **/
    public int minAddToMakeValid(String s) {
        StringBuilder str = new StringBuilder(s);

        for (int i = 0; i < str.length(); i++) {
            if (i > 0 && str.charAt(i) == ')' && str.charAt(i - 1) == '(') {
                str.delete(i - 1, i + 1);
                i -= 2;
            }
        }

        return str.length();
    }

    public int minAddToMakeValidII(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        return stack.size();
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 1021. 删除最外层的括号
     * @Date 17:56 2026/1/29
     * @Param [s]
     **/
    public String removeOuterParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == ')') {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                str.append(ch);
            }

            if (ch == '(') {
                stack.push(ch);
            }
        }

        return str.toString();
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1614. 括号的最大嵌套深度
     * @Date 20:41 2026/1/29
     * @Param [s]
     **/
    public int maxDepth(String s) {
        int size = 0;
        int ret = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                size++;
                ret = Math.max(ret, size);
            } else if (ch == ')') {
                size--;
            }
        }

        return ret;
    }


    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 1190. 反转每对括号间的子串
     * @Date 21:00 2026/1/29
     * @Param [s]
     **/
    // 错误.
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        int size = 0;

        StringBuilder str = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                StringBuilder builder = new StringBuilder();

                char c = stack.pop();
                while (c >= 'a' && c <= 'z') {
                    builder.append(c);
                    c = stack.pop();
                }

                if (size % 2 == 0) {
                    builder.reverse();
                }

                str.append(builder);
                size--;

                continue;
            }

            stack.push(ch);
            if (ch == '(') {
                size++;
            }

        }

        return str.toString();
    }


    public String reverseParenthesesII(String s) {
        StringBuilder str = new StringBuilder();
        Stack<StringBuilder> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(str);
                str = new StringBuilder();
            } else if (ch == ')') {
                str.reverse();
                str = stack.pop().append(str);
            } else {
                str.append(ch);
            }
        }

        return str.toString();
    }


    public String reverseParenthesesIV(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int[] p = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (chars[i] == '(') {
                stack.push(i);
            } else if (chars[i] == ')') {
                int j = stack.pop();
                p[i] = j;
                p[j] = i;
            }
        }

        StringBuilder str = new StringBuilder();
        int index = 0;
        int step = 1;
        while (index < n) {
            if (chars[index] == '(' || chars[index] == ')') {
                index = p[index];
                step = -step;
            } else {
                str.append(chars[index]);
            }

            index += step;
        }

        return str.toString();
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 856. 括号的分数
     * @Date 15:27 2026/1/30
     * @Param [s]
     **/
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(0);
            } else {
                int score = Math.max(stack.pop() * 2, 1);
                int top = stack.pop() + score;
                stack.push(top);
            }
        }

        return stack.peek();
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 1249. 移除无效的括号
     * @Date 16:01 2026/1/30
     * @Param [s]
     **/
    public static String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ')' && !stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                stack.pop();
            } else if (ch == '(' || ch == ')') {
                stack.push(i);
            }
        }

        StringBuilder str = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (!stack.isEmpty() && stack.peek() == i) {
                stack.pop();
            } else {
                str.append(s.charAt(i));
            }
        }

        return str.reverse().toString();
    }

    public static String minRemoveToMakeValidII(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {

            if (chars[i] >= 'a' && chars[i] <= 'z') {
                continue;
            }

            if (chars[i] == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    chars[i] = '*';
                } else {
                    stack.pop();
                }
            }
        }


        while (!stack.isEmpty()) {
            chars[stack.pop()] = '*';
        }

        // 原地修改.
        int size = 0;
        for (char ch : chars) {
            if (ch != '*') {
                chars[size++] = ch;
            }
        }

        return new String(chars, 0, size);
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 1963. 使字符串平衡的最小交换次数
     * @Date 21:10 2026/1/30
     * @Param [s]
     **/
    public boolean checkValidString(String s) {
        char[] chars = s.toCharArray();
        Stack<Integer> left = new Stack<>();
        Stack<Integer> star = new Stack<>();

        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '('){
                left.push(i);
            } else if(chars[i] == '*'){
                star.push(i);
            } else {
                if(!left.isEmpty()){
                    left.pop();
                } else if(!star.isEmpty()){
                    star.pop();
                } else {
                    return false;
                }
            }
        }

        while (!left.isEmpty() && !star.isEmpty()){
            if(star.pop() < left.pop()){
                return false;
            }
        }

        return left.isEmpty();
    }
    
    /**
     * @Author 强仔不强
     * @Description 1006. 笨阶乘
     * @Date 16:49 2026/2/10
     * @Param [n]
     * @return int
     **/
    public static int clumsy(int n) {
        List<Integer> list = new ArrayList<>();

        String str = "*/+-";
        int i = 0;

        int val = n;

        while (--n > 0){
            char ch = str.charAt(i);
            i = (i + 1) % 4;


            switch (ch){
                case '*':
                    val *= n;
                    break;
                case '/':
                    val /= n;
                    break;
                case '+':
                    list.add(val);
                    val = n;
                    break;
                case '-':
                    list.add(val);
                    val = -n;
            }
        }

        list.add(val);

        int ret = 0;
        for (int num : list){
            ret += num;
        }

        return ret;
    }


    public int clumsyII(int n) {
        Stack<Integer> stack = new Stack<>();
        stack.push(n);

        String str = "*/+-";
        int i = 0;

        while (--n > 0){
            char ch = str.charAt(i);
            i = (i + 1) % 4;

            switch(ch){
                case '*':
                    stack.push(stack.pop() * n);
                    break;
                case '/':
                    stack.push(stack.pop() / n);
                    break;
                case '+':
                    stack.push(n);
                    break;
                case '-':
                    stack.push(-n);
                    break;
            }
        }

        int ret = 0;
        while (!stack.isEmpty()){
            ret += stack.pop();
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 150. 逆波兰表达式求值
     * @Date 18:10 2026/2/10
     * @Param [tokens]
     * @return int
     **/
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String str : tokens){
            if(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")){
                int val1 = stack.pop();
                int val2 = stack.pop();

                switch (str.charAt(0)){
                    case '+':
                        stack.push(val2 + val1);
                        break;
                    case '-':
                        stack.push(val2 - val1);
                        break;
                    case '*':
                        stack.push(val2 * val1);
                        break;
                    case '/':
                        stack.push(val2 / val1);
                }


            } else {
                stack.push(Integer.parseInt(str));
            }
        }

        return stack.pop();
    }

    /**
     * @Author 强仔不强
     * @Description 394. 字符串解码
     * @Date 22:56 2026/2/10
     * @Param [s]
     * @return java.lang.String
     **/
    public String decodeString(String s) {
        if(s.isEmpty()){
            return s;
        }

        char ch = s.charAt(0);
        if(ch >= 'a' && ch <= 'z'){
            return ch + decodeString(s.substring(1));
        }

        int i = s.indexOf('[');
        int book = 1;
        for (int j = i + 1; ; j++) {
            char c = s.charAt(j);
            if(c == '['){
                book++;
            } else if(c == ']') {
                book--;
            }

            if(book == 0){
                int k = Integer.parseInt(s.substring(0, i));

                String s1 = decodeString(s.substring(i + 1, j));
                StringBuilder sb = new StringBuilder();
                while (k > 0){
                    sb.append(s1);
                    k--;
                }

                String s2 = decodeString(s.substring(j + 1));

                return sb.toString() + s2;
            }
        }
    }

    private int i = 0;
    public String decode(String s) {
        StringBuilder str = new StringBuilder();

        int k = 0;
        for (; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch >= 'a' && ch <= 'z'){
                str.append(ch);
            } else if (ch >= '0' && ch <= '9'){
                k = k * 10 + (ch - '0');
            } else if(ch == '['){
                i++;
                String string = decode(s);

                while (k > 0){
                    str.append(string);
                    k--;
                }
            } else {
                break;
            }
        }

        return str.toString();
    }


    class Code{
        String s;
        int k;

        public Code(String s, int k){
            this.s = s;
            this.k = k;
        }
    }

    // 栈 模拟 递归
    public String decodeStringII(String s) {
        Stack<Code> stack = new Stack<>();
        StringBuilder str = new StringBuilder();
        int k = 0;

        for (char ch : s.toCharArray()){
            if(Character.isAlphabetic(ch)){
                str.append(ch);
            } else if(Character.isDigit(ch)){
                k = k * 10 + (ch - '0');
            } else if(ch == '['){
                Code code = new Code(str.toString(), k);
                stack.push(code);

                str.setLength(0);
                k = 0;
            } else {
                Code c = stack.pop();

                StringBuilder sb = new StringBuilder(c.s);
                while (c.k > 0){
                    sb.append(str);
                    c.k--;
                }

                str = sb;
            }
        }

        return str.toString();
    }
    
    /**
     * @Author 强仔不强
     * @Description 8. 字符串转换整数 (atoi)
     * @Date 17:55 2026/2/11
     * @Param [s]
     * @return int
     **/
    public static int myAtoi(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int i = 0;

        // 跳过前导空格.
        while (i < n && chars[i] == ' '){
            i++;
        }

        // 判断正负.
        int sign = 1;
        if(i < n && (chars[i] == '-' || chars[i] == '+')){
            sign = (chars[i] == '-') ? -1 : 1;
            i++;
        }

        int ret = 0;
        while (i < n){
            if(!Character.isDigit(chars[i])){
                break;
            }

            int d = chars[i] - '0';
            if (ret > Integer.MAX_VALUE / 10 || ret * 10 > Integer.MAX_VALUE - d) { // 避免溢出
                return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            ret = ret * 10 + d;

            // ret 为 long 类型.
//            if(ret > Integer.MAX_VALUE){
//                return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
//            }

            i++;
        }

        return sign * ret;
    }

    /**
     * @Author 强仔不强
     * @Description 224. 基本计算器
     * @Date 21:41 2026/2/11
     * @Param [s]
     * @return int
     **/
    static private int j = 0;
    public static int calculate(String s) {
        // 通过测试案例;
        j = 0;
        return calculate(s.toCharArray());
    }
    public static int calculate(char[] chars) {
        int ret = 0;

        int sign = 1;
        int k = 0;

        for (; j < chars.length; j++){
            if(chars[j] == '+' || chars[j] == '-'){
                sign = chars[j] == '-' ? -1 : 1;
            } else if(Character.isDigit(chars[j])){
                k = k * 10 + (chars[j] - '0');

                if(j == chars.length - 1 || !Character.isDigit(chars[j + 1])){
                    ret += sign * k;
                    k = 0;
                }
            } else if(chars[j] == '('){
                j++;
                int val = calculate(chars);
                ret += sign * val;

            } else if(chars[j] == ')'){
                return ret;
            }
        }

        return ret;
    }

    class Cal{
        int num;
        int book;

        public Cal(int num, int book){
            this.num = num;
            this.book = book;
        }
    }

    public int calculateII(String s) {
        Stack<Cal> stack = new Stack<>();

        int ret = 0;

        int sign = 1;
        int k = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(Character.isDigit(ch)){
                k = k * 10 + (ch - '0');

                if(i == s.length() - 1 || !Character.isDigit(s.charAt(i + 1))){
                    ret += sign * k;
                    k = 0;
                }
            } else if(ch == '+' || ch == '-'){
                sign = ch == '-' ? -1 : 1;
            } else if(ch == '('){
                Cal cal = new Cal(ret, sign);
                stack.push(cal);

                ret = 0;
                sign = 1;
            } else if(ch == ')'){
                Cal cal = stack.pop();
                ret = cal.num + cal.book * ret;
            }
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 227. 基本计算器 II
     * @Date 21:48 2026/2/12
     * @Param [s]
     * @return int
     **/
    public static int calculateIV(String s) {
        Stack<Integer> stack = new Stack<>();

        int k = 0;
        char sign = '+';

        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);

            if(ch == ' '){
                continue;
            }

            if(Character.isDigit(ch)){
                k = k * 10 + (ch - '0');

                if(i == s.length() - 1 || !Character.isDigit(s.charAt(i + 1))){
                    if(sign == '+') {
                        stack.push(k);
                    } else if(sign == '-'){
                        stack.push(-k);
                    } else {
                        int val = stack.pop();
                        if(sign == '*'){
                            stack.push(val * k);
                        } else {
                            stack.push(val / k);
                        }
                    }

                    k = 0;
                }
            } else {
                sign = ch;
            }
        }

        int ret = 0;
        while (!stack.isEmpty()){
            ret += stack.pop();
        }

        return ret;
    }
}

/**
 * @Author 强仔不强
 * @Description 303. 区域和检索 - 数组不可变
 * @Date 20:45 2025/12/29
 * @Param
 * @return
 **/
class NumArray {
    // s[i] 表示 前 i 个元素的和 （即 [0, i-1] 元素的和）.
    int[] sum;
    public NumArray(int[] nums) {
        int n = nums.length;
        sum = new int[n + 1];
        for (int i = 1; i < n; i++) {
            sum[i] = nums[i - 1] + sum[i - 1];
        }
    }

    public int sumRange(int left, int right) {
        return sum[right + 1] - sum[left];
    }
}

/**
 * @Author 强仔不强
 * @Description 3709. 设计考试分数记录器
 * @Date 14:19 2026/1/8
 * @Param
 * @return 
 **/
class ExamTracker {
    List<Integer> times;
    int size = 0;

    List<Long> scoreSum;

    public ExamTracker() {
        times = new ArrayList<>();
        scoreSum = new ArrayList<>();
        scoreSum.add((long) 0);
    }

    public void record(int time, int score) {
        times.add(time);
        scoreSum.add(scoreSum.get(size) + score);
        size++;
    }

    public long totalScore(int startTime, int endTime) {
        int start = binarySearch(times, startTime - 1);
        int end = binarySearch(times, endTime);

        return scoreSum.get(end + 1) - scoreSum.get(start + 1);

    }

    // 有序数组中, 找到 <= target 的最后一个元素的下标.
    public int binarySearch(List<Integer> list, int target){
        int left = 0;
        int right = this.size - 1;
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

/**
 * @Author 强仔不强
 * @Description 304. 二维区域和检索 - 矩阵不可变
 * @Date 16:30 2026/1/10
 * @Param
 * @return 
 **/
class NumMatrix {
    int[][] sum;
    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        sum = new int[m][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i][j + 1] = sum[i][j] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int ret = 0;
        for (int i = row1; i <= row2; i++) {
            ret += sum[i][col2 + 1] - sum[i][col1];
        }

        return ret;
    }
}


class NumMatrixII {
    int[][] sum;
    public NumMatrixII(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row2 + 1][col2 + 1] - sum[row2 + 1][col1] - sum[row1][col2 + 1] + sum[row1][col1];
    }
}

/**
 * @Author 强仔不强
 * @Description 1472. 设计浏览器历史记录
 * @Date 20:31 2026/1/13
 * @Param
 * @return 
 **/
class BrowserHistory {
    List<String> urls = new ArrayList<>();
    // 记录的个数.
    int size = 0;
    // 当前记录的位置.
    int curPos = -1;

    public BrowserHistory(String homepage) {
        urls.add(homepage);
        size++;
        curPos = 0;
    }

    public void visit(String url) {
        curPos++;
        if(curPos == urls.size()){
            urls.add(url);
        } else {
            urls.set(curPos, url);
        }
        size = curPos + 1;
    }

    public String back(int steps) {
        curPos = Math.max(curPos - steps, 0);
        return urls.get(curPos);
    }

    public String forward(int steps) {
        curPos = Math.min(curPos + steps, size - 1);
        return urls.get(curPos);
    }
}

/**
 * @Author 强仔不强
 * @Description 155. 最小栈
 * @Date 20:44 2026/1/14
 * @Param
 * @return 
 **/
class MinStack {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();
    public MinStack() {
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        if(val <= minStack.peek()){
            minStack.push(val);
        }
    }

    public void pop() {
        int val = stack.pop();
        if(minStack.peek() == val){
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

class MinStackII {
    // int[] 长度为 2, int[0] 为栈顶元素; int[1] 为从栈底到栈顶的最小元素.
    Stack<int[]> stack = new Stack<>();

    public MinStackII() {
        stack.push(new int[]{10086, Integer.MAX_VALUE});
    }

    public void push(int val) {
        int min = Math.min(val, stack.peek()[1]);
        stack.push(new int[]{val, min});
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }
}

/**
 * @Author 强仔不强
 * @Description 1381. 设计一个支持增量操作的栈
 * @Date 20:59 2026/1/14
 * @Param
 * @return 
 **/
class CustomStack {
    List<Integer> list = new ArrayList<>();
    int maxSize = 0;

    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
    }

    public void push(int x) {
        if(list.size() == maxSize){
            return;
        }

        list.add(x);

    }

    public int pop() {
        if(list.isEmpty()){
            return -1;
        }

        return list.remove(list.size() - 1);
    }

    public void increment(int k, int val) {
        k = Math.min(k, list.size());
        for (int i = 0; i < k; i++) {
            list.set(i, list.get(i) + val);
        }
    }
}

class MonotonicStack {
    /**
     * @Author 强仔不强
     * @Description 739. 每日温度
     * @Date 09:30 2026/2/13
     * @Param [temperatures]
     * @return int[]
     **/
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        Stack<Integer> stack = new Stack<>();

        int[] ret = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
                stack.pop();
            }

            if(!stack.isEmpty()){
                ret[i] = stack.peek() - i;
            }
            stack.push(i);
        }

        return ret;
    }

    public int[] dailyTemperaturesII(int[] temperatures) {
        int n = temperatures.length;
        Stack<Integer> stack = new Stack<>();

        int[] ret = new int[n];

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                int index = stack.pop();
                ret[index] = i - index;
            }

            stack.push(i);
        }

        return ret;
    }


    /**
     * @Author 强仔不强
     * @Description 1475. 商品折扣后的最终价格
     * @Date 10:15 2026/2/13
     * @Param [prices]
     * @return int[]
     **/
    // 从左向右
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && prices[i] <= prices[stack.peek()]){
                int j = stack.pop();
                prices[j] -= prices[i];
            }

            stack.push(i);
        }

        return prices;
    }

    // 从右向左
    public int[] finalPricesII(int[] prices) {
        int n = prices.length;
        Stack<Integer> stack = new Stack<>();

        int[] ret = new int[n];
        stack.push(0);  // 哨兵.

        for (int i = n - 1; i >= 0; i--) {
            while (prices[i] < stack.peek()) {
                stack.pop();
            }
            ret[i] = prices[i] - stack.peek();

            stack.push(prices[i]);
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 496. 下一个更大元素 I
     * @Date 11:37 2026/2/13
     * @Param [nums1, nums2]
     * @return int[]
     **/
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        // 从左向右
//        for (int val : nums2){
//            while (!stack.isEmpty() && val > stack.peek()){
//                map.put(stack.pop(), val);
//            }
//            stack.push(val);
//        }

        // 从右向左.
        for (int i = nums2.length; i >= 0; i--) {
            while (!stack.isEmpty() && nums2[i] >= stack.peek()) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                map.put(nums2[i], stack.peek());
            }

            stack.push(nums2[i]);
        }


        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.getOrDefault(nums1[i], -1);
        }

        return nums1;
    }
    
    /**
     * @Author 强仔不强
     * @Description 503. 下一个更大元素 II
     * @Date 16:30 2026/2/13
     * @Param [nums]
     * @return int[]
     **/
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();

        int[] ret = new int[n];
        Arrays.fill(ret, -1);

        int first = -1;

        for (int i = 0; ; i = (i + 1) % n) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                ret[stack.pop()] = nums[i];
            }

            if (first == i) {
                break;
            }


            if(first < i) {
                if (stack.isEmpty()) {
                    first = i;
                }
                stack.push(i);
            }
        }

        return ret;
    }

    public static int[] nextGreaterElementsII(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();

        int[] ret = new int[n];
        Arrays.fill(ret, -1);

        int first = -1;

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                ret[stack.pop()] = nums[i];
            }
            if (stack.isEmpty()) {
                first = i;
            }
            stack.push(i);
        }

        for (int j = 0; j <= first; j++) {
            while (!stack.isEmpty() && nums[j] > nums[stack.peek()]) {
                ret[stack.pop()] = nums[j];
            }
        }

        return ret;
    }

    // 从左向右
    public int[] nextGreaterElementsIII(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();

        int[] ret = new int[n];
        Arrays.fill(ret, -1);

        for (int i = 0; i < n * 2; i++) {
            int val = nums[i % n];
            while (!stack.isEmpty() && val > nums[stack.peek()]){
                int j = stack.pop();
                ret[j] = val;
            }

            if(i < n) {
                stack.push(i);
            }
        }

        return ret;
    }

    public int[] nextGreaterElementsIV(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();

        int[] ret = new int[n];
        Arrays.fill(ret, -1);

        for (int i = n * 2 - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i % n] >= stack.peek()){
                stack.pop();
            }

            if(!stack.isEmpty() && i < n){
                ret[i] = stack.peek();
            }

            stack.push(nums[i % n]);
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 1019. 链表中的下一个更大节点
     * @Date 17:13 2026/2/14
     * @Param
     * @return 
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
    public int[] nextLargerNodes(ListNode head) {
        Stack<ListNode> stack = new Stack<>();

        int size = 0;
        ListNode cur = head;
        while (cur != null){
            while (!stack.isEmpty() && cur.val > stack.peek().val){
                stack.pop().val = cur.val;
            }

            stack.push(cur);
            cur = cur.next;

            size++;
        }

        while (!stack.isEmpty()){
            stack.pop().val = 0;
        }

        int[] ret = new int[size];
        for (int i = 0; i < size; i++) {
            ret[i] = head.val;
            head = head.next;
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 84. 柱状图中最大的矩形
     * @Date 11:51 2026/3/1
     * @Param [heights]
     * @return int
     **/
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;

        // back[i] 表示 heights[i] 右侧 < 它 的最近的下标.
        Stack<Integer> stack = new Stack<>();
        int[] back = new int[n];
        Arrays.fill(back, n);

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]){
                int index = stack.pop();
                back[index] = i;
            }

            stack.push(i);
        }

        stack.clear();
        // front[i] 表示 heights[i] 左侧 < 它 的最近的下标.
        int[] front = new int[n];
        Arrays.fill(front, -1);

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                front[i] = stack.peek();
            }

            stack.push(i);
        }


        int ret = 0;
        for (int i = 0; i < n; i++) {
            ret = Math.max(ret, heights[i] * (back[i] - front[i] - 1));
        }

        return ret;
    }


    public static int largestRectangleAreaII(int[] heights) {
        int n = heights.length;

        Stack<Integer> stack = new Stack<>();
        // back[i] 表示 heights[i] 右侧 < 它 的最近的下标.
        int[] back = new int[n];

        // front[i] 表示 height[i] 左侧 <= 它的最近下标.
        int[] front = new int[n];

        Arrays.fill(back, n);

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]){
                int index = stack.pop();
                back[index] = i;
            }

            front[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        int ret = 0;
        for (int i = 0; i < n; i++) {
            ret = Math.max(ret, heights[i] * (back[i] - front[i] - 1));
        }

        return ret;
    }


    public static int largestRectangleAreaIII(int[] heights) {
        int n = heights.length;

        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        int ret = 0;

        for (int right = 0; right <= n; right++) {
            int h = right < n ? heights[right] : -1;

            while (stack.size() > 1 && h <= heights[stack.peek()]){
                int i = stack.pop();
                int left = stack.peek();
                ret = Math.max(ret, heights[i] * (right - left - 1));
            }

            stack.push(right);
        }

        return ret;
    }
}

/**
 * @Author 强仔不强
 * @Description 901. 股票价格跨度
 * @Date 23:37 2026/2/13
 * @Param
 * @return 
 **/
class StockSpanner {
    Stack<Integer> stack = new Stack<>();
    List<Integer> list = new ArrayList<>();

    public StockSpanner() {

    }

    public int next(int price) {
        list.add(price);

        while (!stack.isEmpty() && price >= list.get(stack.peek())){
            stack.pop();
        }

        int ret = list.size();
        if(!stack.isEmpty()){
            ret = list.size() - stack.peek() - 1;
        }

        stack.push(list.size() - 1);
        return ret;
    }
}

class StockSpan {
    Stack<int[]> stack = new Stack<>();
    int size = 0;

    public StockSpan() {
        // 设置哨兵.
        int[] array = new int[]{-1, Integer.MAX_VALUE};
        stack.push(array);
    }

    public int next(int price) {
        while (!stack.isEmpty() && price >= stack.peek()[1]){
            stack.pop();
        }

        int ret = size - stack.peek()[0];

        stack.push(new int[]{size, price});
        size++;

        return ret;
    }
}

class QueueStructure{

    /**
     * @Author 强仔不强
     * @Description 950. 按递增顺序显示卡牌
     * @Date 16:07 2026/3/1
     * @Param [deck]
     * @return int[]
     **/
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        int[] ret = new int[n];

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.offer(i);
        }

        Arrays.sort(deck);
        for (int card : deck){
            ret[queue.poll()] = card;

            if(!queue.isEmpty()){
                queue.offer(queue.poll());
            }
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 649. Dota2 参议院
     * @Date 17:20 2026/3/1
     * @Param [senate]
     * @return java.lang.String
     **/
    public String predictPartyVictory(String senate) {
        Queue<Character> queue = new LinkedList<>();

        int cntR = 0;
        int cntD = 0;
        for (char ch : senate.toCharArray()){
            if(ch == 'R'){
                cntR++;
            } else {
                cntD++;
            }

            queue.offer(ch);
        }

        int removeR = 0;
        int removeD = 0;
        while (cntR > 0 && cntD > 0){
            char ch = queue.poll();
            if(ch == 'R'){
                if(removeR == 0){
                    queue.offer(ch);
                    removeD++;
                } else {
                    removeR--;
                    cntR--;
                }
            } else {
                if(removeD == 0){
                    queue.offer(ch);
                    removeR++;
                } else {
                    removeD--;
                    cntD--;
                }
            }
        }

        String ret = "";
        if(cntR > 0){
            ret = "Radiant";
        } else {
            ret = "Dire";
        }

        return ret;
    }


    public String predictPartyVictoryII(String senate) {
        int n = senate.length();

        Queue<Integer> queueR = new LinkedList<>();
        Queue<Integer> queueD = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if(senate.charAt(i) == 'R'){
                queueR.offer(i);
            } else {
                queueD.offer(i);
            }
        }

        while (!queueR.isEmpty() && !queueD.isEmpty()){
            int numR = queueR.poll();
            int numD = queueD.poll();

            if(numR < numD){
                queueR.offer(numR + n);
            } else {
                queueD.offer(numD + n);
            }
        }

        String ret = "";
        if(!queueR.isEmpty()){
            ret = "Radiant";
        } else {
            ret = "Dire";
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 239. 滑动窗口最大值
     * @Date 16:56 2026/3/15
     * @Param [nums, k]
     * @return int[]
     **/
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ret = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(nums[i]);

            int start = i - k + 1;
            if (start - 1 >= 0 && nums[start - 1] == deque.peekFirst()) {
                deque.pollFirst();
            }

            if (start >= 0) {
                ret[start] = deque.peekFirst();
            }
        }

        return ret;
    }

    public int[] maxSlidingWindowII(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offerLast(i);

            int left = i - k + 1;
            if(deque.peekFirst() < left){
                deque.pollFirst();
            }

            if(left >= 0){
                ans[left] = nums[deque.peekFirst()];
            }

        }

        return ans;
    }
    
    /**
     * @Author 强仔不强
     * @Description 1438. 绝对差不超过限制的最长连续子数组
     * @Date 16:39 2026/3/16
     * @Param [nums, limit]
     * @return int
     **/
    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();
        int ret = 0;

        // 滑动窗口 [start, end)
        int start = 0;
        int end = 0;
        while (end < n){
            while (!maxDeque.isEmpty() && nums[end] >= nums[maxDeque.peekLast()]){
                maxDeque.pollLast();
            }
            maxDeque.offerLast(end);

            while (!minDeque.isEmpty() && nums[end] <= nums[minDeque.peekLast()]){
                minDeque.pollLast();
            }
            minDeque.offerLast(end);

            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit){
                start++;
                if(start > maxDeque.peekFirst()){
                    maxDeque.pollFirst();
                }
                if(start > minDeque.peekFirst()){
                    minDeque.pollFirst();
                }
            }

            end++;
            ret = Math.max(ret, end - start);
        }

        return ret;
    }
}

/**
 * @Author 强仔不强
 * @Description 933. 最近的请求次数
 * @Date 14:42 2026/3/1
 * @Param
 * @return
 **/
class RecentCounter {
    Queue<Integer> queue;

    public RecentCounter() {
        queue = new LinkedList<>();
    }

    public int ping(int t) {
        queue.offer(t);
        while (queue.peek() < t - 3000){
            queue.poll();
        }

        return queue.size();
    }
}


/**
 * @Author 强仔不强
 * @Description 3829. 设计共享出行系统
 * @Date 15:14 2026/3/1
 * @Param
 * @return
 **/

class RideSharingSystem {
    Queue<Integer> driver = new ArrayDeque<>();
    Queue<Integer> rider;

    Set<Integer> exist;

    public RideSharingSystem() {
        driver = new LinkedList<>();
        rider = new LinkedList<>();
        exist = new HashSet<>();
    }

    public void addRider(int riderId) {
        rider.offer(riderId);
        exist.add(riderId);
    }

    public void addDriver(int driverId) {
        driver.offer(driverId);
    }

    public int[] matchDriverWithRider() {
        while (!rider.isEmpty() && !exist.contains(rider.peek())){
            rider.poll();
        }

        int[] ret = {-1, -1};
        if(!rider.isEmpty() && !driver.isEmpty()){
            ret[0] = driver.poll();
            ret[1] = rider.poll();
        }

        return ret;
    }

    public void cancelRider(int riderId) {
        exist.remove(riderId);
    }
}

/**
 * @Author 强仔不强
 * @Description 1670. 设计前中后队列
 * @Date 18:03 2026/3/1
 * @Param
 * @return 
 **/
class FrontMiddleBackQueue {
    Deque<Integer> front = new LinkedList<>();
    Deque<Integer> back = new LinkedList<>();

    public void pushFront(int val) {
        front.offerFirst(val);
        if (front.size() > back.size()) {
            back.offerFirst(front.pollLast());
        }
    }

    public void pushMiddle(int val) {
        if (front.size() < back.size()) {
            front.offerLast(val);
        } else {
            back.offerFirst(val);
        }
    }

    public void pushBack(int val) {
        if (front.size() < back.size()) {
            front.offerLast(back.pollFirst());
        }

        back.offerLast(val);
    }

    public int popFront() {
        if (back.size() == 0) {
            return -1;
        }
        if (front.size() < back.size()) {
            front.offerLast(back.pollFirst());
        }

        return front.pollFirst();
    }

    public int popMiddle() {
        if (back.size() == 0) {
            return -1;
        }

        if (front.size() < back.size()) {
            return back.pollFirst();
        }

        return front.pollLast();
    }

    public int popBack() {
        if (back.size() == 0) {
            return -1;
        }

        int ret = back.pollLast();
        if (front.size() > back.size()) {
            back.offerFirst(front.pollLast());
        }

        return ret;
    }
}


/**
 * @Author 强仔不强
 * @Description 3508. 设计路由器
 * @Date 21:39 2026/3/1
 * @Param
 * @return 
 **/
class Router {
    class Packet{
        int source;
        int destination;
        int timestamp;

        public Packet(int source, int destination, int timestamp){
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

    int limit = 0;
    List<Packet> list = new ArrayList<>();

    // [first, size) 存在的数据.
    int first = -1;
    int end = 0;

    Set<Packet> set = new HashSet<>();

    public Router(int memoryLimit) {
        limit = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        Packet packet = new Packet(source, destination, timestamp);
        if(set.contains(packet)){
            return false;
        }

        if(first < 0 || end - first + 1 > limit){
            first++;
        }

        list.add(packet);
        set.add(packet);
        end++;

        return true;
    }

    public int[] forwardPacket() {
        if(first < 0 || first == end){
            return new int[]{};
        }

        int[] ret = new int[3];

        Packet packet = list.get(first);
        ret[0] = packet.source;
        ret[1] = packet.destination;
        ret[2] = packet.timestamp;

        first++;
        set.remove(packet);

        return ret;
    }

    public int getCount(int destination, int startTime, int endTime) {
        int left = binarySearch(list, startTime - 1) + 1;
        int right = binarySearch(list, endTime);

        int cnt = 0;
        for (int i = left; i <= right; i++) {
            if(list.get(i).destination == destination){
                cnt++;
            }
        }

        return cnt;
    }

    public int binarySearch(List<Packet> list, int time){
        int left = first;
        int right = end - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;

            Packet packet = list.get(mid);
            if(packet.timestamp <= time){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }
}


class RouTer {
    class Packet{
        int source;
        int destination;
        int timestamp;

        public Packet(int source, int destination, int timestamp){
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
        // head 表示有效的数据包.
        int head = 0;
        // list 只放时间.
        List<Integer> list;

        public Pair(List<Integer> list){
            this.list = list;
        }
    }

    int limit = 0;
    Queue<Packet> queue = new LinkedList<>();
    Set<Packet> set = new HashSet<>();
    HashMap<Integer, Pair> map = new HashMap<>();

    public RouTer(int memoryLimit) {
        this.limit = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        Packet packet = new Packet(source, destination, timestamp);
        if(set.contains(packet)){
            return false;
        }

        if(queue.size() + 1 > limit){
            forwardPacket();
        }
        queue.offer(packet);
        set.add(packet);

        if(map.containsKey(destination)){
            map.get(destination).list.add(timestamp);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(timestamp);
            Pair pair = new Pair(list);

            map.put(destination, pair);
        }

        return true;
    }

    public int[] forwardPacket() {
        if(queue.isEmpty()){
            return new int[]{};
        }

        Packet packet = queue.poll();
        set.remove(packet);
        map.get(packet.destination).head++;

        int[] ret = new int[3];
        ret[0] = packet.source;
        ret[1] = packet.destination;
        ret[2] = packet.timestamp;

        return ret;
    }

    public int getCount(int destination, int startTime, int endTime) {
        if(!map.containsKey(destination)){
            return 0;
        }

        Pair pair = map.get(destination);
        List<Integer> list = pair.list;
        int head = pair.head;

        int left = binarySearch(list, startTime - 1, head) + 1;
        int right = binarySearch(list, endTime, head);

        return right - left + 1;
    }



    public int binarySearch(List<Integer> list, int time, int left){
        int right = list.size() - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;

            if(list.get(mid) <= time){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }
}

/**
 * @Author 强仔不强
 * @Description 225. 用队列实现栈
 * @Date 16:51 2026/3/3
 * @Param
 * @return 
 **/
class MyStack {
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    public MyStack() {

    }

    public void push(int x) {
        queue1.offer(x);
    }

    public int pop() {
        while (queue1.size() > 1) {
            queue2.offer(queue1.poll());
        }

        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;

        return queue2.poll();
    }

    public int top() {
        int ret = 0;
        while (!queue1.isEmpty()) {
            ret = queue1.poll();
            queue2.offer(ret);
        }

        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;

        return ret;
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}

class MyStackII {
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    public void push(int x) {
        queue2.offer(x);
        while (!queue1.isEmpty()){
            queue2.offer(queue1.poll());
        }

        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public int pop() {
        return queue1.poll();
    }

    public int top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}

/**
 * @Author 强仔不强
 * @Description 232. 用栈实现队列
 * @Date 19:12 2026/3/3
 * @Param
 * @return
 **/
class MyQueue {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int x) {
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        stack1.push(x);
    }

    public int pop() {
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }

        return stack2.pop();
    }

    public int peek() {
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }

        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}


class MyQueueII {
    Stack<Integer> inStack = new Stack<>();
    Stack<Integer> outStack = new Stack<>();

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if(outStack.isEmpty()) {
            move();
        }
        return outStack.pop();
    }

    public int peek() {
        if(outStack.isEmpty()) {
            move();
        }
        return outStack.peek();
    }

    public void move(){
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}


/**
 * @Author 强仔不强
 * @Description 622. 设计循环队列
 * @Date 20:36 2026/3/3
 * @Param 
 * @return 
 **/
class MyCircularQueue {
    // [start, end)  为队内元素.
    int start = 0;
    int end = 0;
    int cnt = 0;

    List<Integer> list = new ArrayList<>();
    int limit = 0;

    public MyCircularQueue(int k) {
        this.limit = k;
    }

    public boolean enQueue(int value) {
        if(cnt == limit){
            return false;
        }

        if(list.size() < limit){
            list.add(value);
        } else {
            list.set(end, value);
        }

        end = (end + 1) % limit;
        cnt++;

        return true;
    }


    public boolean deQueue() {
        if(cnt == 0){
            return false;
        }
        start = (start + 1) % limit;
        cnt--;

        return true;
    }

    public int Front() {
        if(cnt == 0){
            return -1;
        }

        return list.get(start);
    }

    public int Rear() {
        if(cnt == 0){
            return -1;
        }

        return list.get((end - 1 + limit) % limit);
    }

    public boolean isEmpty() {
        return cnt == 0;
    }

    public boolean isFull() {
        return cnt == limit;
    }
}

class CircularQueue {
    int[] elements;
    int capacity = 0;
    int front = 0;
    int rear= 0;

    public CircularQueue(int k) {
        capacity = k + 1;
        elements = new int[capacity];
    }

    public boolean enQueue(int value) {
        if((rear + 1) % capacity == front){
            return false;
        }

        elements[rear] = value;
        rear = (rear + 1) % capacity;
        return true;
    }

    public boolean deQueue() {
        if(front == rear){
            return false;
        }

        front = (front + 1) % capacity;
        return true;
    }

    public int Front() {
        if(front == rear){
            return -1;
        }

        return elements[front];
    }

    public int Rear() {
        if(front == rear){
            return -1;
        }

        return elements[(rear + capacity - 1) % capacity];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }
}

class MyCirQueue {

    class ListNode{
        int val;
        ListNode next;

        public ListNode(int val){
            this.val = val;
        }
    }

    ListNode head;
    ListNode last;
    int capacity;
    int size = 0;

    public MyCirQueue(int k) {
        capacity = k;
    }

    public boolean enQueue(int value) {
        if(size == capacity){
            return false;
        }

        ListNode node = new ListNode(value);
        if(size == 0){
            head = node;
        } else {
            last.next = node;
        }
        last = node;
        size++;

        return true;
    }

    public boolean deQueue() {
        if(size == 0){
            return false;
        }

        head = head.next;
        size--;

        return true;
    }

    public int Front() {
        if(size == 0){
            return -1;
        }

        return head.val;
    }

    public int Rear() {
        if(size == 0){
            return -1;
        }

        return last.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}

class MyCircularDeque {
    class ListNode{
        int val;
        ListNode pre;
        ListNode next;

        public ListNode(int value){
            val = value;
        }
    }

    ListNode head;
    ListNode last;
    int capacity;
    int size = 0;

    public MyCircularDeque(int k) {
        capacity = k;
    }

    public boolean insertFront(int value) {
        if(size == capacity){
            return false;
        }

        ListNode node = new ListNode(value);
        if(size == 0){
            last = node;
        } else {
            head.pre = node;
            node.next = head;
        }
        head = node;
        size++;

        return true;
    }

    public boolean insertLast(int value) {
        if(size == capacity){
            return false;
        }

        ListNode node = new ListNode(value);
        if(size == 0){
            head = node;
        } else {
            last.next = node;
            node.pre = last;
        }
        last = node;
        size++;

        return true;
    }

    public boolean deleteFront() {
        if(size == 0){
            return false;
        }

        head = head.next;
        size--;

        return true;
    }

    public boolean deleteLast() {
        if(size == 0){
            return false;
        }

        last = last.pre;
        size--;

        return true;
    }

    public int getFront() {
        if(size == 0){
            return -1;
        }

        return head.val;
    }

    public int getRear() {
        if(size == 0){
            return -1;
        }

        return last.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}


class MyCirDeque {
    int[] elements;
    int front;
    int rear;
    int capacity;

    public MyCirDeque(int k) {
        capacity = k + 1;
        elements = new int[capacity];
    }

    public boolean insertFront(int value) {
        if((rear + 1) % capacity == front){
            return false;
        }

        front = (front + capacity - 1) % capacity;
        elements[front] = value;

        return true;
    }

    public boolean insertLast(int value) {
        if((rear + 1) % capacity == front){
            return false;
        }

        elements[rear] = value;
        rear = (rear + 1) % capacity;

        return true;
    }

    public boolean deleteFront() {
        if(front == rear){
            return false;
        }

        front = (front + 1) % capacity;

        return true;
    }

    public boolean deleteLast() {
        if(front == rear){
            return false;
        }

        rear = (rear + capacity - 1) % capacity;

        return true;
    }

    public int getFront() {
        if(front == rear){
            return -1;
        }

        return elements[front];
    }

    public int getRear() {
        if(front == rear){
            return -1;
        }

        return elements[(rear + capacity - 1) % capacity];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }
}

/**
 * @Author 强仔不强
 * @Description LCR 184. 设计自助结算系统
 * @Date 19:45 2026/3/15
 * @Param
 * @return 
 **/
class Checkout {
    Deque<Integer> products = new ArrayDeque<>();
    Deque<Integer> deque = new ArrayDeque<>();

    public int get_max() {
        if(products.isEmpty()){
            return -1;
        }

        return deque.peekFirst();
    }

    public void add(int value) {
        products.offer(value);
        while (!deque.isEmpty() && deque.peekLast() < value){
            deque.pollLast();
        }
        deque.offerLast(value);
    }

    public int remove() {
        if(products.isEmpty()){
            return -1;
        }

        int val = products.poll();
        if(deque.peekFirst() == val){
            deque.pollFirst();
        }

        return val;
    }
}

class DataPriorityQueue{
    /**
     * @Author 强仔不强
     * @Description 1046. 最后一块石头的重量
     * @Date 20:38 2026/3/16
     * @Param [stones]
     * @return int
     **/
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int weight : stones){
            queue.offer(weight);
        }

        while (queue.size() > 1){
            int w1 = queue.poll();
            int w2 = queue.poll();
            if(w1 != w2){
                queue.offer(Math.abs(w1 - w2));
            }
        }

        int ret = 0;
        if(!queue.isEmpty()){
            ret = queue.peek();
        }

        return ret;
    }

    /**
     * @Author 强仔不强
     * @Description 3264. K 次乘运算后的最终数组 I
     * @Date 20:44 2026/3/16
     * @Param [nums, k, multiplier]
     * @return int[]
     **/

    static class Pair{
        int val;
        int index;

        public Pair(int value, int i){
            val = value;
            index = i;
        }
    }

    public static int[] getFinalState(int[] nums, int k, int multiplier) {
        int n = nums.length;
        PriorityQueue<Pair> queue = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if(o1.val == o2.val){
                    return o1.index - o2.index;
                }
                return o1.val - o2.val;
            }
        });

        for (int i = 0; i < n; i++) {
            Pair pair = new Pair(nums[i], i);
            queue.offer(pair);
        }

        for (int i = 0; i < k; i++) {
            Pair pair = queue.poll();
            pair.val *= multiplier;
            queue.offer(pair);

            nums[pair.index] = pair.val;
        }

        return nums;
    }

    /**
     * @Author 强仔不强
     * @Description 2558. 从数量最多的堆取走礼物
     * @Date 20:49 2026/3/17
     * @Param [gifts, k]
     * @return long
     **/
    public long pickGifts(int[] gifts, int k) {
        long sum = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int val : gifts){
            sum += val;
            queue.offer(val);
        }

        for (int i = 0; i < k; i++) {
            int val = queue.poll();
            int num = (int) Math.sqrt(val);
            sum -= val - num;

            queue.offer(num);
        }


        return sum;
    }

    // 原地堆化.
    public long pickGiftsII(int[] gifts, int k) {
        heapify(gifts);

        for (int i = 0; i < k; i++) {
            gifts[0] = (int) Math.sqrt(gifts[0]);
            adjustDown(gifts, 0);
        }

        long ans = 0;
        for (int val : gifts){
            ans += val;
        }

        return ans;
    }

    // 原地堆化（大根堆.）
    // 从下往上 依次向下调整.
    public void heapify(int[] h){
        int n = h.length;
        int parent = n - 1;
        while (parent >= 0){
            adjustDown(h, parent);
            parent--;
        }
    }

    public void adjustDown(int[] h, int parent){
        int n = h.length;
        int child = parent * 2 + 1;
        while (child < n){
            if(child + 1 < n && h[child + 1] > h[child]){
                child++;
            }

            if(h[parent] < h[child]){
                swap(h, parent, child);
                parent = child;
                child = parent * 2 + 1;
            } else {
                break;
            }
        }
    }

    public void swap(int[] h, int pos1, int pos2){
        int temp = h[pos1];
        h[pos1] = h[pos2];
        h[pos2] = temp;
    }

    /**
     * @Author 强仔不强
     * @Description 2530. 执行 K 次操作后的最大分数
     *                    也可以原地堆化.
     * @Date 22:03 2026/3/17
     * @Param [nums, k]
     * @return long
     **/
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int val : nums){
            heap.offer(val);
        }

        long ans = 0;
        for (int i = 0; i < k; i++) {
            int val = heap.poll();
            ans += val;

            heap.offer((val + 2) / 3);
        }

        return ans;
    }

    /**
     * @Author 强仔不强
     * @Description 3066. 超过阈值的最少操作数 II
     * @Date 17:01 2026/3/18
     * @Param [nums, k]
     * @return int
     **/
    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> heap = new PriorityQueue<>();
        for (int val : nums){
            heap.offer((long) val);
        }

        int ans = 0;
        while (heap.peek() < k){
            long x = heap.poll();
            long y = heap.poll();
            heap.offer(Math.min(x, y) * 2 + Math.max(x, y));
            ans++;
        }

        return ans;
    }


    /**
     * @Author 强仔不强
     * @Description 1962. 移除石子使总数最小
     * @Date 17:24 2026/3/18
     * @Param [piles, k]
     * @return int
     **/
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int sum = 0;
        for (int val : piles){
            heap.offer(val);
            sum += val;
        }

        for (int i = 0; i < k; i++) {
            int val = heap.poll();
            int p = val / 2;
            sum -= p;

            heap.offer(val - p);
        }

        return sum;
    }

    /**
     * @Author 强仔不强
     * @Description 3275. 第 K 近障碍物查询
     * @Date 18:28 2026/3/18
     * @Param [queries, k]
     * @return int[]
     **/
    public int[] resultsArray(int[][] queries, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int n = queries.length;
        int[] ret = new int[n];

        for (int i = 0; i < n; i++) {
            int distance = Math.abs(queries[i][0]) + Math.abs(queries[i][1]);
            heap.offer(distance);

            if(heap.size() < k){
                ret[i] = -1;
                continue;
            }

            if(heap.size() > k){
                heap.poll();
            }
            ret[i] = heap.peek();
        }

        return ret;
    }

    
    /**
     * @Author 强仔不强
     * @Description 2208. 将数组和减半的最少操作次数
     * @Date 14:23 2026/3/19
     * @Param [nums]
     * @return int
     **/
    public int halveArray(int[] nums) {
        PriorityQueue<Double> heap = new PriorityQueue<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o1 > o2 ? -1 : 1;
            }
        });

        long sum = 0;
        for (int val : nums){
            heap.offer((double) val);
            sum += val;
        }

        int ans = 0;
        double remove = 0;
        while (remove * 2 < sum){
            double val = heap.poll();
            double p = val * 1.0 / 2;
            heap.offer(p);

            remove += val - p;
            ans++;
        }

        return ans;
    }

    /**
     * @Author 强仔不强
     * @Description 2233. K 次增加后的最大乘积
     * @Date 15:03 2026/3/19
     * @Param [nums, k]
     * @return int
     **/
    private int MOD = 1000000007;
    public int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        long mul = 1;
        int cnt = 0;
        for (int val : nums){
            if(val == 0){
                cnt++;
                heap.offer(1);
                continue;
            }

            heap.offer(val);
            mul = mul * val % MOD;
        }

        if(cnt > k){
            return 0;
        }

        k -= cnt;
        for (int i = 0; i < k; i++) {
            int val = heap.poll();

            // error : 乘积取模后再除以因子, 数值错误.
            mul /= val;
            val++;

            heap.offer(val);
            mul = mul * val % MOD;
        }

        return (int) mul;
    }

    public int maximumProductII(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int val : nums){
            heap.offer(val);
        }

        for (int i = 0; i < k; i++) {
            int val = heap.poll();
            heap.offer(val + 1);
        }

        long ans = 1;
        while (!heap.isEmpty()){
            ans = ans * heap.poll() % MOD;
        }

        return (int) ans;
    }


    /**
     * @Author 强仔不强
     * @Description 3296. 移山所需的最少秒数
     * @Date 16:31 2026/3/19
     * @Param [mountainHeight, workerTimes]
     * @return long
     **/
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        int min = Integer.MAX_VALUE;
        for (int time : workerTimes){
            min = Math.min(min, time);
        }

        long left = min;
        long right = (long) min * mountainHeight * (mountainHeight + 1) / 2;

        while (left <= right){
            long mid = left + (right - left) / 2;

            if(!check(mountainHeight, workerTimes, mid)){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public boolean check(int mountainHeight, int[] workTimes, long totalTime){
        int totalHeight = 0;

        for (int time : workTimes){
            long spendTime = 0;


            int height = 0;
            while (spendTime <= totalTime){
                height++;
                spendTime += (long) height * time;
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
     * @Description 1942. 最小未被占据椅子的编号
     * @Date 20:09 2026/3/19
     * @Param [times, targetFriend]
     * @return int
     **/
    public int smallestChair(int[][] times, int targetFriend) {
        // available 表示空闲椅子.
        PriorityQueue<Integer> available = new PriorityQueue<>();

        // int[] 表示事件, {朋友编号, arrive/leave时间, flag}    flag = 0, 表示 arrive; flag = 1, 表示 leave
        PriorityQueue<int[]> events = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]){
                    return o2[2] - o1[2];
                }

                return o1[1] - o2[1];
            }
        });

        // <朋友编号, 所占据的椅子编号>
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = times.length;

        for (int i = 0; i < n; i++) {
            available.offer(i);
        }

        for (int i = 0; i < n; i++) {
            int arrive = times[i][0];
            int leave = times[i][1];

            int[] e1 = new int[]{i, arrive, 0};
            int[] e2 = new int[]{i, leave, 1};
            events.offer(e1);
            events.offer(e2);
        }

        while (!events.isEmpty()){
            int[] event = events.poll();
            int friend = event[0];
            int flag = event[2];

            // 有人来
            if(flag == 0){
                int chair = available.poll();
                map.put(friend, chair);

                if(friend == targetFriend){
                    return chair;
                }
            } else {
                // 有人离开.
                int chair = map.get(friend);

                // 删不删也无所谓.
                map.remove(friend);

                available.offer(chair);
            }
        }

        return -1;
    }

    /**
     * @Author 强仔不强
     * @Description 23. 合并 K 个升序链表
     * @Date 19:55 2026/3/22
     * @Param [lists]
     * @return ListNode
     **/

    static class ListNode {
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


    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (ListNode node : lists){
            if(node != null) {
                heap.offer(node);
            }
        }

        // 哨兵节点, 不是头节点
        ListNode head = new ListNode();
        ListNode cur = head;
        while (!heap.isEmpty()){
            ListNode node = heap.poll();

            cur.next = node;
            cur = cur.next;

            if(node.next != null) {
                heap.offer(node.next);
            }
        }

        return head.next;
    }


    /**
     * @Author 强仔不强
     * @Description 264. 丑数 II
     *              丑数是由更小的丑数 *2, *3, *5 得来的, 最小的丑数是 1
     * @Date 21:00 2026/3/22
     * @Param [n]
     * @return int
     **/
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> heap = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        heap.offer((long)1);

        int[] muls = {2, 3, 5};
        for (int i = 0; i < n - 1; i++) {
            long val = heap.poll();
            set.remove(val);

            for (int mul : muls){
                long num = val * mul;
                if(set.contains(num)){
                   continue;
                }

                heap.offer(num);
                set.add(num);
            }
        }

        long ret = heap.poll();
        return (int) ret;
    }


    /**
     * @Author 强仔不强
     * @Description 378. 有序矩阵中第 K 小的元素
     * @Date 21:42 2026/3/22
     * @Param [matrix, k]
     * @return int
     **/
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = matrix[i][j];
                heap.offer(val);

                if(heap.size() > k){
                    heap.poll();
                }
            }
        }

        return heap.poll();
    }

    // 二分答案
    public int kthSmallestII(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];

        while (left <= right){
            int mid = left + (right - left) / 2;

            if(!check(matrix, mid, k)){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public boolean check(int[][] matrix, int target, int k){
        int n = matrix.length;
        int i = 0;
        int j = n - 1;

        int cnt = 0;
        while (i < n && j >= 0){
            int val = matrix[i][j];
            if(val <= target){
                cnt += n;
                i++;
            } else {
                j--;
            }
        }

        return cnt >= k;
    }

    /**
     * @Author 强仔不强
     * @Description 240. 搜索二维矩阵 II
     * @Date 20:52 2026/3/23
     * @Param [matrix, target]
     * @return boolean
     **/
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int i = 0;
        int j = n - 1;

        while (i < m && j >= 0){
            int val = matrix[i][j];
            if(val == target){
                return true;
            }

            if(val < target){
                i++;
            } else {
                j--;
            }
        }

        return false;
    }

    /**
     * @Author 强仔不强
     * @Description 373. 查找和最小的 K 对数字
     * @Date 20:57 2026/3/23
     * @Param [nums1, nums2, k]
     * @return java.util.List<java.util.List<java.lang.Integer>>
     **/
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                long sum1 = o1[0] + o1[1];
                long sum2 = o2[0] + o2[1];
                return (int) (sum2 - sum1);
            }
        });

        int n1 = nums1.length;
        int n2 = nums2.length;

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                int[] a = new int[]{nums1[i], nums2[j]};
                heap.offer(a);

                if(heap.size() > k){
                    heap.poll();
                }
            }
        }

        List<List<Integer>> ret = new ArrayList<>();
        while (!heap.isEmpty()){
            int[] a = heap.poll();
            List<Integer> list = new ArrayList<>();
            list.add(a[0]);
            list.add(a[1]);

            ret.add(list);
        }

        return ret;
    }

    public List<List<Integer>> kSmallestPairsII(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int num1 = nums1[o1[0]] + nums2[o1[1]];
                int num2 = nums1[o2[0]] + nums2[o2[1]];

                return num1 - num2;
            }
        });

        int n1 = nums1.length;
        int n2 = nums2.length;

        // 规定 (i, j) 只让 (i + 1, j) 入堆.
        for (int i = 0; i < n2; i++) {
            heap.offer(new int[]{0, i});
        }

        List<List<Integer>> ans = new ArrayList<>();
        while (k > 0){
            int[] a = heap.poll();

            List<Integer> list = new ArrayList<>();
            list.add(nums1[a[0]]);
            list.add(nums2[a[1]]);
            ans.add(list);
            k--;

            if(a[0] + 1 < n1){
                heap.offer(new int[]{a[0] + 1, a[1]});
            }
        }

        return ans;
    }

    /**
     * @Author 强仔不强
     * @Description 984. 不含 AAA 或 BBB 的字符串
     * @Date 15:19 2026/3/24
     * @Param [a, b]
     * @return java.lang.String
     **/
    // 贪心, 谁多就放谁.
    public String strWithout3a3b(int a, int b) {
        StringBuilder ans = new StringBuilder();

        while (a > 0 || b > 0){
            char letter = a >= b ? 'a' : 'b';

            int len = ans.length();
            if(len >= 2 && ans.charAt(len - 1) == ans.charAt(len - 2)){
                letter = ans.charAt(len - 1) == 'a' ? 'b' : 'a';
            }

            ans.append(letter);
            if(letter == 'a'){
                a--;
            } else {
                b--;
            }
        }

        return ans.toString();
    }

    /**
     * @Author 强仔不强
     * @Description 767. 重构字符串
     * @Date 16:12 2026/3/24
     * @Param [s]
     * @return java.lang.String
     **/
    public static String reorganizeString(String s) {
        int[] cnt = new int[126];
        for (char ch : s.toCharArray()) {
            cnt[ch]++;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return cnt[o2] - cnt[o1];
            }
        });

        for (int i = 'a'; i <= 'z'; i++) {
            if(cnt[i] > 0){
                heap.offer(i);
            }
        }

        StringBuilder ans = new StringBuilder();
        while (!heap.isEmpty()) {
            int ch = heap.poll();
            int letter = ch;

            int len = ans.length();
            if (len > 0 && ans.charAt(len - 1) == letter) {
                if (heap.isEmpty()) {
                    return "";
                }

                letter = heap.poll();
                heap.offer(ch);
            }

            ans.append((char) letter);
            cnt[letter]--;

            if (cnt[letter] > 0) {
                heap.offer(letter);
            }
        }

        return ans.toString();
    }

    // 排序
    public static String reorganizeStringII(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()){
            int cnt = map.getOrDefault(ch, 0);
            cnt++;
            map.put(ch, cnt);
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        int m = list.get(0).getValue();
        int n = s.length();
        if(m - 1 > n - m){
            return "";
        }

        char[] ans = new char[n];
        int i = 0;
        for (Map.Entry<Character, Integer> entry : list){
            char ch = entry.getKey();
            int cnt = entry.getValue();

            while (cnt > 0){
                ans[i] = ch;
                cnt--;
                i += 2;

                if(i >= n){
                    i = 1;
                }
            }
        }

        return new String(ans);
    }
}

// 大根堆.
// 任意节点的值 > 其孩子节点的值（左右孩子的值大小比较无要求）.
class MyPriorityQueue{
    int[] elements;
    int capacity;
    int size = 0;

    public MyPriorityQueue(int k){
        capacity = k;
        elements = new int[capacity];
    }

    // 向上调整
    public void adjustUp(){
        int child = size - 1;
        while (child > 0){
            int parent = (child - 1) / 2;
            if(elements[parent] < elements[child]){
                swap(parent, child);
                child = parent;
            } else {
                break;
            }
        }
    }

    // 向下调整
    // 直接覆盖, 不是标准.
//    public void adjustDownII(int parent) {
//        int child = parent * 2 + 1;
//        while (child < size) {
//            if (child + 1 < size && elements[child + 1] > elements[child]){
//                child++;
//            }
//
//            elements[parent] = elements[child];
//            parent = child;
//            child = parent * 2 + 1;
//        }
//
//        for (int i = parent; i < size - 1; i++) {
//            elements[i] = elements[i + 1];
//        }
//    }

    // 向下调整
    public void adjustDown(int parent){
        int child = parent * 2 + 1;
        while (child < size){
            if(child + 1 < size && elements[child + 1] > elements[child]){
                child++;
            }

            if(elements[parent] < elements[child]){
                swap(parent, child);
                parent = child;
                child = parent * 2 + 1;
            } else {
                break;
            }
        }
    }

    public void swap(int pos1, int pos2){
        int temp = elements[pos1];
        elements[pos1] = elements[pos2];
        elements[pos2] = temp;
    }


    public boolean offer(int value){
        if(size == capacity){
            return false;
        }

        elements[size] = value;
        size++;
        adjustUp();

        return true;
    }

    public int poll(){
        if(size == 0){
            return -1;
        }

        swap(0, size - 1);
        size--;
        adjustDown(0);

        return elements[size];
    }

    public int peek(){
        if(size == 0){
            return -1;
        }

        return elements[0];
    }
}


class SmallestInfiniteSet {
    // set 放 addBack 的元素.
    Set<Integer> set = new HashSet<>();
    // queue 放能取到的元素.
    PriorityQueue<Integer> queue = new PriorityQueue<>();

    public SmallestInfiniteSet() {
        queue.offer(1);
    }

    public int popSmallest() {
        int val = queue.poll();
        set.add(val);

        if(queue.isEmpty()) {
            queue.offer(val + 1);
        }

        return val;
    }

    public void addBack(int num) {
        if(!set.contains(num)){
            return;
        }

        set.remove(num);
        queue.offer(num);
    }
}

class SmallestInfinite {
    // set, queue 放 addBack 的元素.
    Set<Integer> set = new HashSet<>();
    PriorityQueue<Integer> queue = new PriorityQueue<>();

    int idx = 1;

    public int popSmallest() {
        int ans = 0;
        if(queue.isEmpty()){
            ans = idx;
            idx++;
        } else {
            ans = queue.poll();
            set.remove(ans);
        }

        return ans;
    }

    public void addBack(int num) {
        if(num >= idx || set.contains(num)){
            return;
        }

        set.add(num);
        queue.offer(num);
    }
}

/**
 * @Author 强仔不强
 * @Description 703. 数据流中的第 K 大元素
 * @Date 17:34 2026/3/18
 * @Param
 * @return
 **/
class KthLargest {
    PriorityQueue<Integer> heap;
    int capacity;

    public KthLargest(int k, int[] nums) {
        capacity = k;
        heap = new PriorityQueue<>(capacity);

        for (int val : nums){
            if(heap.size() < capacity){
                heap.offer(val);
                continue;
            }

            if(heap.peek() < val){
                heap.poll();
                heap.offer(val);
            }
        }
    }

    public int add(int val) {
        if(heap.size() == capacity - 1){
            heap.offer(val);
            return heap.peek();
        }

        if(heap.peek() < val){
            heap.poll();
            heap.offer(val);
        }

        return heap.peek();
    }
}


class Largest {
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    int capacity;

    public Largest(int k, int[] nums) {
        capacity = k;
        for (int val : nums){
            add(val);
        }
    }

    public int add(int val) {
        heap.offer(val);

        if(heap.size() > capacity){
            heap.poll();
        }

        return heap.peek();
    }
}

/**
 * @Author 强仔不强
 * @Description 1845. 座位预约管理系统
 * @Date 18:39 2026/3/18
 * @Param
 * @return 
 **/
class SeatManager {
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    int thres = 1;

    public SeatManager(int n) {

    }

    public int reserve() {
        int ans = 0;
        if(!heap.isEmpty()){
            ans = heap.poll();
        } else {
            ans = thres;
            thres++;
        }

        return ans;
    }

    public void unreserve(int seatNumber) {
        heap.offer(seatNumber);
    }
}

/**
 * @Author 强仔不强
 * @Description 2349. 设计数字容器系统
 * @Date 17:55 2026/3/24
 * @Param
 * @return 
 **/
class NumberContainers {
    HashMap<Integer, Integer> indexToNumber = new HashMap<>();
    HashMap<Integer, PriorityQueue<Integer>> numberToindices = new HashMap<>();

    public void change(int index, int number) {
        // 删除.
        if(indexToNumber.containsKey(index)) {
            int val = indexToNumber.get(index);
            PriorityQueue<Integer> heap = numberToindices.get(val);
            // 时间复杂度太大.
            heap.remove(index);

            if(heap.isEmpty()){
                numberToindices.remove(val);
            }
        }

        // 添加.
        PriorityQueue<Integer> heap = numberToindices.getOrDefault(number, new PriorityQueue<>());
        heap.offer(index);
        numberToindices.put(number, heap);

        indexToNumber.put(index, number);
    }

    public int find(int number) {
        if(!numberToindices.containsKey(number)){
            return -1;
        }

        return numberToindices.get(number).peek();

    }
}

// 懒删除.
class NumContainers {
    HashMap<Integer, Integer> indexToNumber = new HashMap<>();
    HashMap<Integer, PriorityQueue<Integer>> numberToindices = new HashMap<>();

    public void change(int index, int number) {
        // 添加
        PriorityQueue<Integer> heap = numberToindices.getOrDefault(number, new PriorityQueue<>());
        heap.offer(index);
        numberToindices.put(number, heap);

        indexToNumber.put(index, number);
    }

    public int find(int number) {
        PriorityQueue<Integer> heap = numberToindices.get(number);
        if(heap == null){
            return -1;
        }

        while (!heap.isEmpty() && indexToNumber.get(heap.peek()) != number){
            heap.poll();
        }

        return heap.isEmpty() ? -1 : heap.peek();
    }
}

/**
 * @Author 强仔不强
 * @Description 208. 实现 Trie (前缀树)
 * @Date 21:44 2026/3/24
 * @Param
 * @return 
 **/
class Trie {
    private class Node{
        Node[] sons = new Node[26];
        boolean end = false;
    }

    Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node cur = root;

        for (char ch : word.toCharArray()){
            ch -= 'a';
            if(cur.sons[ch] == null){
                cur.sons[ch] = new Node();
            }
            cur = cur.sons[ch];
        }

        cur.end = true;
    }

    public boolean search(String word) {
        return find(word) == 2;
    }

    public boolean startsWith(String prefix) {
        return find(prefix) > 0;
    }

    // 0 -- 无法匹配; 1 -- 部分匹配; 2 -- 完全匹配;
    public int find(String word){
        Node cur = root;

        for (char ch : word.toCharArray()){
            ch -= 'a';
            if(cur.sons[ch] == null){
                return 0;
            }

            cur = cur.sons[ch];
        }

        return cur.end ? 2 : 1;
    }
}

class SubTree{
    /**
     * @Author 强仔不强
     * @Description 3597. 分割字符串
     * @Date 22:20 2026/3/24
     * @Param [s]
     * @return java.util.List<java.lang.String>
     **/
    public List<String> partitionString(String s) {
        class Node{
            Node[] sons = new Node[26];
        }

        int n = s.length();

        Node root = new Node();
        Node cur = root;
        List<String> ans = new ArrayList<>();

        int left = 0;
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';
            if(cur.sons[index] == null){
                cur.sons[index] = new Node();
                ans.add(s.substring(left, i + 1));
                left = i + 1;
                cur = root;
            } else {
                cur = cur.sons[index];
            }
        }

        return ans;
    }

    /**
     * @Author 强仔不强
     * @Description 648. 单词替换
     * @Date 16:46 2026/3/29
     * @Param [dictionary, sentence]
     * @return java.lang.String
     **/
    public String replaceWords(List<String> dictionary, String sentence) {

        // 创建字典树.
        class Node{
            Node[] sons = new Node[26];
            boolean end = false;
        }

        Node root = new Node();
        for (String s : dictionary){
            Node cur = root;
            for (char ch : s.toCharArray()){
                ch -= 'a';

                if(cur.sons[ch] == null){
                    cur.sons[ch] = new Node();
                }
                cur = cur.sons[ch];
            }

            cur.end = true;
        }

        StringBuilder ans = new StringBuilder();
        for (String s : sentence.split(" ")){
            Node cur = root;
            StringBuilder sb = new StringBuilder();

            for (char ch : s.toCharArray()){
                ch -= 'a';

                if(cur.sons[ch] == null){
                    break;
                }
                cur = cur.sons[ch];
                sb.append((char) (ch + 'a'));

                if(cur.end){
                    break;
                }
            }

            if(ans.length() > 0) {
                ans.append(' ');
            }

            if(cur.end){
                ans.append(sb);
            } else {
                ans.append(s);
            }
        }

        return ans.toString();
    }

    /**
     * @Author 强仔不强
     * @Description 720. 词典中最长的单词
     * @Date 17:58 2026/3/29
     * @Param [words]
     * @return java.lang.String
     **/
    public String longestWord(String[] words) {
        // 按字典序排
//        Arrays.sort(words, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                int s1 = 0;
//                int s2 = 0;
//                while (s1 < o1.length() && s2 < o2.length()){
//                    char c1 = o1.charAt(s1);
//                    char c2 = o2.charAt(s2);
//
//                    if(c1 != c2){
//                        return c1 - c2;
//                    }
//                    s1++;
//                    s2++;
//                }
//
//                return o1.length() - o2.length();
//            }
//        });

        // 长度升序; 长度相同, 则字典序降序.
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() != o2.length()){
                    return o1.length() - o2.length();
                }

                int i = 0;
                while (i < o1.length()){
                    char c1 = o1.charAt(i);
                    char c2 = o2.charAt(i);

                    if(c1 != c2){
                        return c2 - c1;
                    }

                    i++;
                }

                return -1;
            }
        });

        class Node{
            Node[] sons = new Node[26];
        }

        Node root = new Node();

        String ans = "";
        for (String s : words){
            Node cur = root;
            int n = s.length();

            int i = 0;
            for (; i < n - 1; i++) {
                int index = s.charAt(i) - 'a';
                if(cur.sons[index] == null){
                    break;
                }

                cur = cur.sons[index];
            }

            if(i == n - 1){
                int j = s.charAt(n - 1) - 'a';
                cur.sons[j] = new Node();
                ans = s;
            }
        }

        return ans;
    }

    // 排序 + 哈希表.
    public String longestWordII(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() != o2.length()){
                    return o1.length() - o2.length();
                }

                int i = 0;
                while (i < o1.length()){
                    char c1 = o1.charAt(i);
                    char c2 = o2.charAt(i);

                    if(c1 != c2){
                        return c2 - c1;
                    }

                    i++;
                }

                return -1;
            }
        });

        Set<String> set = new HashSet<>();
        set.add("");
        String ans = "";

        int n = words.length;
        for (int i = 0; i < n; i++) {
            String s = words[i];

            if(set.contains(s.substring(0, s.length() - 1))){
                ans = s;
                set.add(s);
            }
        }

        return ans;
    }

    // 字典树, 不排序
    public String longestWordIII(String[] words) {
        class Trie{
            Trie[] children = new Trie[26];
            boolean end = false;
        }

        // 创建字典树.
        Trie root = new Trie();
        for (String word : words){
            Trie cur = root;

            for (char ch : word.toCharArray()){
                ch -= 'a';

                if(cur.children[ch] == null){
                    cur.children[ch] = new Trie();
                }
                cur = cur.children[ch];
            }

            cur.end = true;
        }

        // 找.
        String ans = "";
        for (String s : words){
            Trie cur = root;

            int i = 0;
            for ( ; i < s.length(); i++) {
                int index = s.charAt(i) - 'a';

                if(cur.children[index] == null || !cur.children[index].end){
                    break;
                }
                cur = cur.children[index];
            }

            if(i < s.length()){
                continue;
            }

            if(s.length() > ans.length() || (s.length() == ans.length() && s.compareTo(ans) < 0)){
                ans = s;
            }
        }

        return ans;
    }
}

class UnionFind{
    int[] p;  // p[i] 表示 元素 i 所在集合的 根节点.
    int[] size;  // size[i] 表示 元素 i 所在集合的 节点数.
    int cc;      // 连通图的数量.

    public UnionFind(int n){
        // 开始的集合 {0}, {1}, {2}, ..., {n - 1}
        p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }

        size = new int[n];
        Arrays.fill(size, 1);
        cc = n;
    }

    public int find(int x){
        if(p[x] == x){
            return x;
        }

        return p[x] = find(p[x]);
    }

    public boolean isSame(int x, int y){
        int rootX = find(x);
        int rootY = find(y);

        return rootX == rootY;
    }

    public boolean merge(int from, int to){
        int rootF = find(from);
        int rootT = find(to);

        if(rootF == rootT){
            return false;
        }
        p[rootF] = rootT;
        size[rootT] += size[rootF];
        cc--;

        return true;
    }

    public int getSize(int x){
        int root = find(x);
        return size[root];
    }
}

public class Main{
    /**
     * @Author 强仔不强
     * @Description 684. 冗余连接
     * @Date 20:48 2026/4/1
     * @Param [edges]
     * @return int[]
     **/
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] p = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            p[i] = i;
        }

        int[] ans = null;
        for (int i = 0; i < n; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];

            if(find(p, node1) == find(p, node2)){
                ans = edges[i];
            } else {
                merge(p, node1, node2);
            }
        }

        return ans;
    }

    public static int find(int[] p, int x){
        if(p[x] == x){
            return x;
        }

        return p[x] = find(p, p[x]);
    }

    public void merge(int[] p, int x, int y){
        int rootX = find(p, x);
        int rootY = find(p, y);
        p[rootX] = p[rootY];
    }
    
    /**
     * @Author 强仔不强
     * @Description 3493. 属性图
     * @Date 21:18 2026/4/1
     * @Param [properties, k]
     * @return int
     **/
    public int numberOfComponents(int[][] properties, int k) {
        int m = properties.length;
        
        int[] p = new int[m];
        for (int i = 0; i < m; i++) {
            p[i] = i;
        }
        int cc = m;

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if(intersect(properties[i], properties[j]) >= k){
                    int rootI = find(p, i);
                    int rootJ = find(p ,j);

                    if(rootI != rootJ){
                        p[rootI] = rootJ;
                        cc--;
                    }
                }
            }
        }

        return cc;
    }

    public int intersect(int[] a, int[] b){
        Set<Integer> set = new HashSet<>();
        for (int v1 : a){
            set.add(v1);
        }

        int ans = 0;
        for (int v2 : b){
            if(set.remove(v2)){
                ans++;
            }
        }

        return ans;
    }

    /**
     * @Author 强仔不强
     * @Description 990. 等式方程的可满足性
     * @Date 21:42 2026/4/1
     * @Param [equations]
     * @return boolean
     **/
    public boolean equationsPossible(String[] equations) {
        int[] p = new int[26];
        for (int i = 0; i < 26; i++) {
            p[i] = i;
        }

        // 构造并查集
        for (String s : equations){
            char book = s.charAt(1);
            if(book == '!'){
                continue;
            }

            int c1 = s.charAt(0) - 'a';
            int c2 = s.charAt(3) - 'a';

            int root1 = find(p, c1);
            int root2 = find(p, c2);

            if(root1 != root2){
                p[root1] = root2;
            }
        }

        // 查
        for (String s : equations){
            char book = s.charAt(1);
            if(book == '='){
                continue;
            }

            int c1 = s.charAt(0) - 'a';
            int c2 = s.charAt(3) - 'a';

            int root1 = find(p, c1);
            int root2 = find(p, c2);

            if(root1 == root2){
                return false;
            }
        }

        return true;
    }
    
    /*
     * @Author 强仔不强
     * @Description 721. 账户合并
     * @Date 10:47 2026/4/2
     * @Param [accounts]
     * @return java.util.List<java.util.List<java.lang.String>>
     **/
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if(isSame(accounts.get(i), accounts.get(j))){
                    int rootI = find(p, i);
                    int rootJ = find(p, j);

                    if(rootI == rootJ){
                        continue;
                    }

                    p[rootJ] = rootI;
                }
            }
        }

        HashMap<Integer, Set<String>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(p, i);

            Set<String> set = map.getOrDefault(root, new HashSet<>());
            List<String> message = accounts.get(i);
            for (int j = 1; j < message.size(); j++) {
                set.add(message.get(j));
            }
            map.put(root, set);
        }

        List<List<String>> ans = getLists(accounts, map);

        return ans;
    }

    private static List<List<String>> getLists(List<List<String>> accounts, HashMap<Integer, Set<String>> map) {
        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<Integer, Set<String>> entry : map.entrySet()){
            int key = entry.getKey();
            Set<String> val = entry.getValue();

            List<String> l = new ArrayList<>();
            List<String> message = accounts.get(key);
            l.add(message.get(0));

            List<String> emails = new ArrayList<>(val);
            emails.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            l.addAll(emails);

            ans.add(l);
        }
        return ans;
    }

    public static boolean isSame(List<String> l1, List<String> l2){

        if(!l1.get(0).equals(l2.get(0))){
            return false;
        }

        Set<String> s1 = new HashSet<>();
        for (int i = 1; i < l1.size(); i++) {
            s1.add(l1.get(i));
        }

        for (String s : l2){
            if(s1.remove(s)){
                return true;
            }
        }

        return false;
    }

    public List<List<String>> accountsMergeII(List<List<String>> accounts) {
        HashMap<String, Integer> emailToIndex = new HashMap<>();
        HashMap<String, String> emailToName = new HashMap<>();
        int emailId = 0;

        for (List<String> list : accounts) {
            String name = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String email = list.get(i);
                if (emailToIndex.containsKey(email)) {
                    continue;
                }

                emailToIndex.put(email, emailId);
                emailId++;
                emailToName.put(email, name);
            }
        }

        int n = emailToIndex.size();
        UnionFind uf = new UnionFind(n);

        for (List<String> list : accounts){
            String firstEmail = list.get(1);
            int firstId = emailToIndex.get(firstEmail);

            for (int i = 2; i < list.size(); i++) {
                String email = list.get(i);
                int id = emailToIndex.get(email);

                uf.union(firstId, id);
            }
        }

        HashMap<Integer, List<String>> unionIdToEmail = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailToIndex.entrySet()){
            String email = entry.getKey();
            int id = entry.getValue();

            int unionId = uf.find(id);

            List<String> list = unionIdToEmail.getOrDefault(unionId, new ArrayList<>());
            list.add(email);
            unionIdToEmail.put(unionId, list);
        }

        List<List<String>> ans = new ArrayList<>();
        for (List<String> emails : unionIdToEmail.values()){
            List<String> list = new ArrayList<>();

            String name = emailToName.get(emails.get(0));
            list.add(name);

            emails.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            list.addAll(emails);

            ans.add(list);
        }

        return ans;
    }

    class UnionFind{
        int[] p;

        public UnionFind(int n){
            p = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
        }

        public int find(int x){
            if(p[x] != x){
                p[x] = find(p[x]);
            }

            return p[x];
        }

        public boolean isSame(int x, int y){
            int rootX = find(x);
            int rootY = find(y);

            return rootX == rootY;
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);

            p[rootX] = rootY;
        }
    }

    /**
     * @Author 强仔不强
     * @Description 3532. 针对图的路径存在性查询 I
     * @Date 11:28 2026/4/3
     * @Param [n, nums, maxDiff, queries]
     * @return boolean[]
     **/
    // 超时.
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if(nums[j] - nums[i] > maxDiff){
                    break;
                }

                uf.union(i, j);
            }
        }

        int m = queries.length;
        boolean[] ans = new boolean[m];

        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            int root1 = uf.find(q[0]);
            int root2 = uf.find(q[1]);
            ans[i] = root1 == root2;
        }

        return ans;
    }

    public boolean[] pathExistenceQueriesII(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] p = new int[n];
        for (int i = 1; i < n; i++) {
            if(nums[i] - nums[i - 1] <= maxDiff){
                p[i] = p[i - 1];
            } else {
                p[i] = p[i - 1] + 1;
            }
        }

        int m = queries.length;
        boolean[] ans = new boolean[m];

        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            ans[i] = p[q[0]] == p[q[1]];
        }

        return ans;
    }

    public static boolean[] pathExistenceQueriesIII(int n, int[] nums, int maxDiff, int[][] queries) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            if(nums[i + 1] - nums[i] > maxDiff){
                list.add(i);
            }
        }

        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];

            int min = Math.min(q[0], q[1]);
            int max = Math.max(q[0], q[1]);

            int gap = binarySearch(list, min, n);

            ans[i] = gap >= max;
        }

        return ans;
    }

    public static int binarySearch(List<Integer> list, int target, int n){
        int m = list.size();
        int left = 0;
        int right = m - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if(list.get(mid) < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left == m ? n : list.get(left);
    }

    public static void main(String[] args) {
        int n = 4;
        int[] nums = {2, 5, 6, 8};
    }
}
