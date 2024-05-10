package com.tc.alg.backtracking;


import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个长度为4的整数数组 cards 。你有 4 张卡片，每张卡片上都包含一个范围在 [1,9] 的数字。您应该使用运算符 ['+', '-', '*', '/'] 和括号 '(' 和 ')' 将这些卡片上的数字排列成数学表达式，以获得值24。
 *
 * 你须遵守以下规则:
 *
 * 除法运算符 '/' 表示实数除法，而不是整数除法。
 * 例如， 4 /(1 - 2 / 3)= 4 /(1 / 3)= 12 。
 * 每个运算都在两个数字之间。特别是，不能使用 “-” 作为一元运算符。
 * 例如，如果 cards =[1,1,1,1] ，则表达式 “-1 -1 -1 -1” 是 不允许 的。
 * 你不能把数字串在一起
 * 例如，如果 cards =[1,2,1,2] ，则表达式 “12 + 12” 无效。
 * 如果可以得到这样的表达式，其计算结果为 24 ，则返回 true ，否则返回 false 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: cards = [4, 1, 8, 7]
 * 输出: true
 * 解释: (8-4) * (7-1) = 24
 * 示例 2:
 *
 * 输入: cards = [1, 2, 1, 2]
 * 输出: false
 *
 *
 * 提示:
 *
 * cards.length == 4
 * 1 <= cards[i] <= 9
 */
public class ListNumCalcEqTargetVal {

    public static final int TARGET_RESULT_VAL = 24;
    // 浮点数中误差小于 1e-6 (10的负十六次方)可以认为是相等。
    static final double EPSILON = 1e-6;


    public static void main(String[] args) {
        int[] nums = new int[]{4, 1, 8, 7};
//        int[] nums = new int[]{1, 2, 1, 2};
        boolean flag = judgePoint24_backtracking(nums);
        System.out.printf("是否能够计算出目标值%s,结果：%s%n", TARGET_RESULT_VAL, flag);
    }

    /**
     * 方法一：回溯
     * 一共有 4 个数和 3 个运算操作，因此可能性非常有限。一共有多少种可能性呢？
     * 首先从 4 个数字中有序地选出 2 个数字，共有 4×3=12 种选法，并选择加、减、乘、除 4 种运算操作之一，用得到的结果取代选出的 2 个数字，剩下 3 个数字。
     * 然后在剩下的 3 个数字中有序地选出 2 个数字，共有 3×2=6 种选法，并选择 4 种运算操作之一，用得到的结果取代选出的 2 个数字，剩下 2 个数字。
     * 最后剩下 2 个数字，有 2 种不同的顺序，并选择 4 种运算操作之一。
     * 因此，一共有 12×4×6×4×2×4=921612 种不同的可能性。
     *
     * 可以通过回溯的方法遍历所有不同的可能性。具体做法是，使用一个列表存储目前的全部数字，每次从列表中选出 2 个数字，再选择一种运算操作，用计算得到的结果取代
     * 选出的 2 个数字，这样列表中的数字就减少了 1 个。重复上述步骤，直到列表中只剩下 1 个数字，这个数字就是一种可能性的结果，如果结果等于 24
     * 则说明可以通过运算得到 24。如果所有的可能性的结果都不等于 24，则说明无法通过运算得到 24。
     *
     * 实现时，有一些细节需要注意。
     *
     * 除法运算为实数除法，因此结果为浮点数，列表中存储的数字也都是浮点数。在判断结果是否等于 24 时应考虑精度误差，这道题中，误差小于 1e-6 (10的负十六次方)可以认为是相等。
     * 进行除法运算时，除数不能为 0，如果遇到除数为 0 的情况，则这种可能性可以直接排除。由于列表中存储的数字是浮点数，因此判断除数是否为 0 时应考虑精度误差，这道题中，当一个数字的绝对值小于 1e-6
     * 时，可以认为该数字等于 0。
     *
     * 还有一个可以优化的点。
     *      加法和乘法都满足交换律，因此如果选择的运算操作是加法或乘法，则对于选出的 222 个数字不需要考虑不同的顺序，在遇到第二种顺序时可以不进行运算，直接跳过。
     */
    public static boolean judgePoint24_backtracking(int[] nums) {
        Double[] list = new Double[nums.length];
        for (int i = 0; i < nums.length; i++) {
            list[i] = (double) nums[i];
        }
        return solve_backtracking(list);
    }

    /**
     * 使用回溯法解决给定列表中数字的组合问题，尝试通过加减乘除四种运算符找到一个组合，
     * 使得结果接近目标值TARGET_RESULT_VAL。
     *
     * @param nums 包含一系列双精度浮点数的列表。
     * @return 如果找到一个组合，其计算结果与目标值的差小于EPSILON，则返回true；否则返回false。
     */
    private static boolean solve_backtracking(Double[] nums) {
        // 当列表中只有一个元素时，检查该元素是否接近目标结果
        if (nums.length == 1) {
            return Math.abs(nums[0] - TARGET_RESULT_VAL) < EPSILON;
        }

        // 遍历列表中的每对元素（不包括相同的元素），尝试所有可能的运算操作
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    Double[] nextNums = new Double[nums.length - 1];
                    // 从原列表中去除当前选择的两个元素
                    for (int k = 0, pos = 0; k < nums.length; k++) {
                        if (k != i && k != j) {
                            nextNums[pos++] = nums[k];
                        }
                    }
                    for (double num : calculate(nums[i], nums[j])) {
                        nextNums[nextNums.length - 1] = num;
                        if (solve_backtracking(nextNums)) return true;
                    }
                }
            }
        }
        // 如果无法找到任何组合满足条件，则返回false
        return false;
    }

    private static List<Double> calculate(double a, double b) {
        List<Double> list = new ArrayList<>();
        list.add(a + b);
        list.add(a - b);
        list.add(b - a);
        list.add(a * b);
        if (!(Math.abs(b) < EPSILON)) list.add(a / b);
        if (!(Math.abs(a) < EPSILON)) list.add(b / a);
        return list;
    }
}
