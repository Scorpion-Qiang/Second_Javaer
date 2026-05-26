/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 强仔
 * Date: 2025-04-09
 * Time: 22:28
 */
package test;

import java.io.PrintStream;
import java.util.*;
import java.util.jar.JarOutputStream;

public class TestDemo {
    public static void main1(String[] args) {
        int a = 399;
        byte b = (byte) a;
        System.out.println(b);

        Scanner scanner = new Scanner(System.in);
//        int year = scanner.nextInt();
//        if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
//            System.out.println(year + " is a leap year");
//        } else {
//            System.out.println(year + " is not a leap year");
//        }

//        int c = scanner.nextInt();
//        switch (c){
//            case 1:
//                System.out.println("qust");
//                break;
//            case 2:
//                System.out.println("neu");
//                break;
//            case 3:
//                System.out.println("hit");
//                break;
//            default:
//                System.out.println("sdu");
//                break;
//        }

        int val = 1;
        int num = 0;
        while (val <= 100) {
            num += val;
            val++;
        }
        System.out.println(num);
        val = 1;

        int numJ = 0;
        int numO = 0;
        while (val <= 100) {
            if (val % 2 == 0) {
                numO += val;
            } else {
                numJ += val;
            }
            val++;
        }
        System.out.println("奇数和是：" + numJ);
        System.out.println("偶数和是：" + numO);

        // 求 1 - 100 之间偶数的和
        numO = 0;
        int i = 2;
        while (i <= 100) {
            numO += i;
            // 很巧妙
            i += 2;
        }
        System.out.println("偶数的和为：" + numO);

        // 求一个数的阶乘
//        int arr = scanner.nextInt();
//        int ret = 1;
//        while (arr > 0){
//            ret *= arr;
//            arr--;
//        }
//        System.out.println("阶乘为：" + ret);

        // 求阶乘的和
        int l = scanner.nextInt();
        int r = 0;
        // 双重 for 循环
        for (int j = 1; j <= l; j++) {
            int v = 1;
            for (int k = 1; k <= j; k++) {
                v *= k;
            }
            System.out.println(j + "的阶乘为：" + v);
            r += v;
        }

        // 双重 while 循环
        int j = 1;
        while (j <= l) {
            int ret = 1;
            int v = 1;
            while (v <= j) {
                ret *= v;
                v++;
            }
            r += ret;
            j++;
        }
        System.out.println("阶乘的和为：" + r);
    }

    /*
     * 找到 1——100之前既能被3整除，又能被5整除的数字
     *
     * */
    public static void main2(String[] args) {
//        for (int i = 1; i <= 100; i++) {
//            if(i % 3 == 0 && i % 5 == 0){
//                System.out.print(i + " ");
//            }
//        }
//
//        int i = 1;
//        while (i <= 100){
//            if(i % 3 == 0 && i % 5 == 0){
//                System.out.print(i + " ");
//            }
//            i++;
//        }

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine(); // 读一行，最好放到其他输入前面
        System.out.println(str);

        float val = scanner.nextFloat();
        System.out.println(val);

        String s = scanner.next(); // 遇到空格结束
    }

    public static void main3(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextInt()){
//            int a = scanner.nextInt();
//            System.out.println(a);
//        }

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            System.out.println(str);
        }
    }

    // 猜数字游戏
    public static void main5(String[] args) {
        // 加上随机种子，每次随机生成的数相同
        Random random = new Random();
        int randNum = random.nextInt(100) + 1;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请输入你的答案: ");
            int num = scanner.nextInt();
            if (num == randNum) {
                System.out.println("答案正确！！！");
                break;
            } else if (num > randNum) {
                System.out.println("答案过大");
            } else {
                System.out.println("答案过小");
            }
        }
    }

    // 1.根据年龄，判断身份
    public static void func1(int age) {
        if (age < 18) {
            System.out.println("少年");
        } else if (age >= 19 && age <= 28) {
            System.out.println("青年");
        } else if (age >= 29 && age <= 55) {
            System.out.println("中年");
        } else {
            System.out.println("老年");
        }
    }

    // 2.判断一个数是否是素数
    // 如果 num 为素数的话，则 num = a * b. a <= num 开平方根，b >= num 开平方根，所以只需要判断小于 num 开平方根 的数即可
    public static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 4. 打印闰年
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static void leapYear() {
        for (int i = 1000; i <= 2000; i++) {
            if (isLeapYear(i)) {
                System.out.print(i + " ");
            }
        }
    }

    // 5.打印乘法口诀表
    public static void multiplicationTable() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + i * j + " ");
            }
            System.out.println();
        }
    }

    // 6.两个正整数的最大公约数
    public static int func6(int num1, int num2) {
        int ret = 1;
        for (int i = 2; i <= num1 && i <= num2; i++) {
            if (num1 % i == 0 && num2 % i == 0) {
                ret = i;
            }
        }
        return ret;
    }

    // 6.辗转相除法求最大公约数
    public static int greatestCommonDivisor(int a, int b) {
        int c = a % b;
        while (c != 0) {
            a = b;
            b = c;
            c = a % b;
        }
        return b;
    }

    // 辗转相除法求最大公约数（递归）
    // 两个数的最大公约数等于除数和两数相除余数的最大公约数。  即 gcd(a, b) = gcd(b, a % b)
    public static int largestCommonDivisor(int a, int b) {
        int c = a % b;
        if (c == 0) {
            return b;
        }
        return largestCommonDivisor(b, c);
    }


    // 7.计算
    public static double calculate() {
        int val = 1;
        double ret = 0;
        for (int i = 1; i <= 100; i++) {
            ret += 1.0 / i * val;
            val = -val;
        }
        return ret;
    }

    // 8.一个数中 9 的个数
    public static int nineCount(int num) {
        int ret = 0;
        while (num > 0) {
            if (num % 10 == 9) {
                ret++;
            }
            num /= 10;
        }
        return ret;
    }

    public static int Func8(int n) {
        int ret = 0;
        for (int i = 1; i <= n; i++) {
            ret += nineCount(i);
        }
        return ret;
    }

    // 9.判断这个数是不是自幂数（假设一个四位数，其各个数位的4次方的和等于这个数）
    // 三位自幂数就是水仙花数
    public static boolean isSelfPowerNumber(int num) {
        // 判断这个数是几位数（即幂指数）
        int val = num;
        int index = 0;
        while (val > 0) {
            index++;
            val /= 10;
        }

        int ret = 0;
        val = num;
        while (val > 0) {
            int a = val % 10;
            ret += Math.pow(a, index);
            val /= 10;
        }
        return ret == num;
    }

    public static void selfPowerNumberCount(int n) {
        int ret = 0;
        for (int i = 1; i <= n; i++) {
            if (isSelfPowerNumber(i)) {
                System.out.print(i + " ");
            }
        }
    }

    // 10.模拟三次密码输入的场景
    public static void login(String password) {
        System.out.println("请输入密码: ");
        Scanner scanner = new Scanner(System.in);
        int val = 3;
        while (val > 0) {
            String ret = scanner.nextLine();
            val--;
            if (ret.equals(password)) {
                System.out.println("密码正确！");
                break;
            } else {
                if (val == 0) {
                    System.out.println("密码错误，退出程序!");
                }
                System.out.println("密码错误, 你还有 " + val + " 次机会");
            }
        }
    }

    // 11.返回一个数的二进制（补码）中 1 的个数
    // num 不能为负数
    public static int oneCount(int num) {
        int ret = 0;
        while (num != 0) {
            ret += num % 2;
            num /= 2;
        }
        return ret;
    }

    // oc(num) 表示数字 num 的二进制中 1 的个数
    // oc(num) = oc(num & (num - 1)) + 1; (num != 0)
    // num 可以为负数
    public static int oneCounts(int num) {
        int ret = 0;
        while (num != 0) {
            ret++;
            num = num & (num - 1);
        }
        return ret;
    }

    public static int oneNum(int num) {
        if (num == 0) {
            return 0;
        }
        return oneNum(num & (num - 1)) + 1;
    }

    // 百度题目: 判断一个数字 n 是不是 2 的 k 次方（不需要求 k 是多少）
    // 解法: 即判断这个数字 n 的二进制中 1 的个数是否为 1 ?
    public static boolean isTwoPow(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    // 12. 输出二进制序列
    public static void func12(int num) {
        List<Integer> list = new ArrayList<>();
        while (num > 0) {
            list.add(num % 2);
            num /= 2;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i));
        }
    }

    // 13.输出整数中的每一位
    public static void func13(int num) {
        while (num > 0) {
            int val = num % 10;
            System.out.print(val + " ");
            num /= 10;
        }
    }

    public static void main6(String[] args) {
        int a = 10;
        int b = 20;
        func(a, b);
        System.out.println(a + " " + b);
    }

    public static void func(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }


    public static void main8(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            System.out.println(isTwoPow(n));
        }
    }

    // 方法重载
    // 1.方法名相同
    // 2.参数列表不同（参数个数 & 参数类型）
    // 3.返回值类型不作要求
    // 4.重载可以不在一个类中（继承）
    public static int add(int a, int b) {
        return a + b;
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    // 递归
    // 找递推公式和终止条件
    // 1.顺序打印一个数字的每一位，如 print(1024)
    // print(102) + '4'
    // print(10) + '2'
    // ....
    // print(1) 直接打印 '1'
    // print(n) = print(n / 10) + n % 10
    public static void print(int n) {
        if (n < 10) {
            System.out.print(n + " ");
            return;
        }
        print(n / 10);
        System.out.print(n % 10 + " ");
    }

    // 2.求 1 + 2 + 3 + .... + (n - 1） + n
    // sum(n) = n + sum(n - 1)
    public static int sum(int n) {
        if (n == 0) {
            return 0;
        }
        return n + sum(n - 1);
    }

    // 3.输入一个非负整数，返回各位数字之和
    // sumSplit(n) = sumSplit(n / 10) + n % 10
    public static int sumSplit(int n) {
        if (n == 0) {
            return 0;
        }
        return sumSplit(n / 10) + n % 10;
    }

    // 4.求斐波那契数列的第 n 项
    // f(n) = f(n - 1) + f(n - 2)
    // 递归不好，重复计算太多
    public static int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    // 迭代（循环）
    public static int fib2(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int f1 = 1;
        int f2 = 1;
        int f3 = 0;
        for (int i = 3; i <= n; i++) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }

    public static int fib3(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int f1 = 1;
        int f2 = 1;
        int f3 = 0;

        int i = 3;
        while (i <= n) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
            i++;
        }
        return f3;
    }

    // 5.求 n 的阶乘
    // f(n) = n * f(n - 1)
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }


    // 6.青蛙跳台阶
    // j(n) = j(n - 1) + j(n - 2)
    // 类似 Fibonacci数列
    public static int jumpMethod(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return jumpMethod(n - 1) + jumpMethod(n - 2);
    }

    public static int jump(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int f1 = 1;
        int f2 = 2;
        int f3 = 0;

        int i = 3;
        while (i <= n) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
            i++;
        }
        return f3;
    }

    /**
     * @return void
     * @Author 强仔不强
     * @Description 移动操作
     * @Date 16:31 2025/5/27
     * @Param [pos, des]
     **/
    public static void move(char pos, char des) {
        System.out.print(pos + "->" + des + " ");
    }

    /**
     * @return void
     * @Author 强仔不强
     * @Description 汉诺塔问题
     * 将 A 上的 n 个圆盘，以 B 为过渡站，移动到 C 上。
     * 可以先将 A 上的 n-1 个圆盘，以 C 为过渡站，移动到 B 上，将 A 上的最底端的一个圆盘移动到 C 上。
     * 再将 B 上的 n-1 个圆盘以 A 为过渡站，移动到 C 上。
     * Ht(n, A, B, C) = Ht(n-1, A, C, B) + A->C + Ht(n-1, B, A, C)
     * @Date 16:35 2025/5/27
     * @Param n 圆盘总数
     * @Param origin 起点
     * @Param middle 过渡站
     * @Param des 终点
     **/

    public static void hanoiTower(int n, char origin, char middle, char des) {
        if (n == 1) {
            move(origin, des);
            return;
        }
        hanoiTower(n - 1, origin, des, middle);
        move(origin, des);
        hanoiTower(n - 1, middle, origin, des);
    }

    public static void main9(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 创建数组
        int[] arr1 = {1, 2, 3, 4, 5, 6};
        // 默认元素为 0
        int[] arr2 = new int[6];
        int[] arr3 = new int[]{1, 2, 3, 4, 5, 6};

        // 这个引用 不指向任何对象
        int[] arr4 = null;

        // 打印数组
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        for (int val : arr1) {
            System.out.print(val + " ");
        }

        String ret = Arrays.toString(arr2);
        System.out.println(ret);

        System.out.println(arr1 + " " + arr3);

    }

    // 数组作为方法的参数
    public static void main12(String[] args) {
        int[] array = {1, 2, 3};
        System.out.println("交换前: ");
        show(array);
        swap(array);
        System.out.println("交换后: ");
        show(array);

        int[] arr = null;
        System.out.println(arr.length);
        System.out.println(arr[0]);

        int[] ret = func(array);
    }

    public static void swap(int[] array) {
        int temp = array[0];
        array[0] = array[1];
        array[1] = temp;
    }

    public static void show(int[] array) {
        for (int x : array) {
            System.out.print(x + " ");
        }
    }

    // 数组作为方法的返回值
    public static int[] func(int[] array) {
        int[] ret = new int[array.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = array[i] * 2;
        }
        return ret;
    }

    /**
     * @return java.lang.String
     * @Author 强仔不强
     * @Description 数组转字符串
     * @Date 17:38 2025/5/29
     * @Param [arr]
     **/
    public static String myToString(int[] arr) {
        if (arr == null) {
            return null;
        }
        if (arr.length == 0) {
            return "";
        }
        StringBuffer ret = new StringBuffer("[");
        for (int i = 0; i < arr.length; i++) {
            ret.append(arr[i]);
            if (i == arr.length - 1) {
                break;
            }
            ret.append(", ");
        }
        ret.append(']');
        return ret.toString();
    }

    /**
     * @return int[]
     * @Author 强仔不强
     * @Description 数组拷贝
     * @Date 17:40 2025/5/29
     * @Param [arr]
     **/
    public static int[] copy(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] ret = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ret[i] = arr[i];
        }
        return ret;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 找数组中的最大元素
     * @Date 17:42 2025/5/29
     * @Param [arr]
     **/
    public static int findLargest(int[] arr) {
        int ret = arr[0];
        for (int x : arr) {
            if (ret < x) {
                ret = x;
            }
        }
        return ret;
    }

    /**
     * @return double
     * @Author 强仔不强
     * @Description 求数组中各元素的平均值
     * @Date 17:44 2025/5/29
     * @Param [arr]
     **/
    public static double average(int[] arr) {
        double sum = 0;
        for (int x : arr) {
            sum += x;
        }
        return sum / arr.length;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 查找数组中指定元素（顺序查找）
     * @Date 17:47 2025/5/29
     * @Param [arr, val]
     **/
    public static int find(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return int
     * @Author 强仔不强
     * @Description 二分查找数组中指定元素（必须是有序数组）
     * @Date 17:59 2025/5/29
     * @Param [arr, val]
     **/
    public static int binarySearch(int[] arr, int val) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == val) {
                return mid;
            } else if (arr[mid] < val) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // 递归写法
    public static int binarySearch2(int[] arr, int val) {
        return search(arr, 0, arr.length - 1, val);
    }
    public static int search(int[] arr, int left, int right, int val){
        if(left > right){
            return -1;
        }
        int mid = (left + right) / 2;
        if(arr[mid] == val){
            return mid;
        }
        if(arr[mid] < val){
            left = mid + 1;
        } else {
            right = mid - 1;
        }
        return search(arr, left, right, val);
    }

    /**
     * @return boolean
     * @Author 强仔不强
     * @Description 判断数组是否是升序的
     * @Date 18:02 2025/5/29
     * @Param [arr]
     **/
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i <= arr.length - 2; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return void
     * @Author 强仔不强
     * @Description 冒牌排序（升序）
     * @Date 18:15 2025/5/29
     * @Param [arr]
     **/
    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            boolean flg = false;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flg = true;
                }
            }
            // 如果 flg 为 false，说明这一趟没有交换，数组已经是有序的了
            if(!flg){
                break;
            }
        }
    }

    public static void bubbleSort2(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int maxVal = arr[0];
            int index = 0;
            for (int j = 1; j <= i; j++) {
                if (arr[j] > maxVal) {
                    index = j;
                    maxVal = arr[j];
                }
            }
            if (index != i) {
                swap(arr, index, i);
            }
        }
    }


    // 递归
    public static void bubble(int[] arr){
        bubbleSort(arr, arr.length - 1);
    }
    public static void bubbleSort(int[] arr, int end){
        if(end == 0){
            return;
        }

//        int maxVal = arr[0];
//        int index = 0;
//        for (int i = 1; i <= end; i++) {
//            if(arr[i] > maxVal){
//                maxVal = arr[i];
//                index = i;
//            }
//        }
//        if(index != end){
//            swap(arr, index, end);
//        }

        boolean flg = false;
        for (int i = 0; i < end; i++) {
            if(arr[i] > arr[i + 1]){
                swap(arr, i, i + 1);
                flg = true;
            }
        }
        if(!flg){
            return;
        }

        bubbleSort(arr, end - 1);
    }


    // 交换数组中的元素
    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    /**
     * @return
     * @Author 强仔不强
     * @Description 数组逆序
     * @Date 18:17 2025/5/29
     * @Param
     **/
    public static void reverse(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    /**
     * @Author 强仔不强
     * @Description 将数组中的偶数放在前半部分，奇数放在后半部分
     * @Date 17:35 2025/5/30
     * @Param [arr]
     * @return void
     **/
    public static void exchange(int[] arr){
        int left = 0;
        int right = arr.length - 1;
        while (left < right){
            if(arr[left] % 2 == 0){
                left++;
                continue;
            }
            if(arr[right] % 2 != 0){
                right--;
                continue;
            }
            swap(arr, left, right);
        }


//        while (left < right){
//            while (arr[left] % 2 == 0 && left < right){
//                left++;
//            }
//            while (arr[right] % 2 != 0 && left < right){
//                right--;
//            }
//            if(left < right) {
//                swap(arr, left, right);
//            }
//        }
        
    }

    public static void main18(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int[] values = new int[arr.length];

        // 拷贝1
        int[] ret = Arrays.copyOf(arr, arr.length);
        // 拷贝2
        int[] array = Arrays.copyOfRange(arr, 0, arr.length);
        // 拷贝3，使用本地方法（Native方法）
        System.arraycopy(arr, 0, values, 0, arr.length);
        // 拷贝4
        int[] a = arr.clone();

        System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int len = scanner.nextInt();
            int[] arr = new int[len];

            for (int i = 0; i < len; i++) {
                int val = scanner.nextInt();
                arr[i] = val;
            }
            System.out.println(Arrays.toString(arr));
            exchange(arr);
            System.out.println(Arrays.toString(arr));

        }
    }
}







