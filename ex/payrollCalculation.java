package ex;

import java.sql.Time;

public class payrollCalculation {
  public static int main(long args1) {

    String[] args = { "00:00:00", "10:00:00" };
    // -------------定数の定義-------------------
    final int HOURLY_SALARY = 900;// 時給(900円)
    final int MINUTES_SALARY = HOURLY_SALARY / 60;// 分給(15円);
    final int CONV_HOUR_TO_MILLISEC = 1000 * 60 * 60; // 時間のミリ秒換算
    final int CONV_MINUTE_TO_MILLISEC = 1000 * 60;// 分のミリ秒換算
    final long WORK_TIME_TYPE1_END = 6 * CONV_HOUR_TO_MILLISEC;
    final long WOKR_TIME_TYPE2_START = 6 * CONV_HOUR_TO_MILLISEC;
    final long WORK_TIME_TYPE2_END = 8 * CONV_HOUR_TO_MILLISEC;
    final long WOKR_TIME_TYPE3_START = 8 * CONV_HOUR_TO_MILLISEC;
    final long REST_TIME_TYPE1 = 45 * CONV_MINUTE_TO_MILLISEC;
    final long REST_TIME_TYPE2 = 60 * CONV_MINUTE_TO_MILLISEC;
    final double OVERTIME_SALARY_RATE = 1.25;
    final int ACTUAL_WORK_TIME_OVERTIME_OCCUR_MIN = 8 * CONV_HOUR_TO_MILLISEC;

    // -------------変数の定義-------------------
    // Time startTime = Time.valueOf(args[0]);
    // Time finishTime = Time.valueOf(args[1]);
    // long workTime = finishTime.getTime() - startTime.getTime();
    long workTime = args1;
    int actualWorkTimeMin = 0;
    int partTimeFee2 = 0;

    // 実働時間の計算
    if (workTime <= WORK_TIME_TYPE1_END) { // 6時間以下
      actualWorkTimeMin = (int) (workTime / CONV_MINUTE_TO_MILLISEC);
    } else if (WOKR_TIME_TYPE2_START < workTime && workTime <= WORK_TIME_TYPE2_END) {
      actualWorkTimeMin = (int) ((workTime - REST_TIME_TYPE1) / CONV_MINUTE_TO_MILLISEC);
    } else if (WOKR_TIME_TYPE3_START < workTime) {
      actualWorkTimeMin = (int) ((workTime - REST_TIME_TYPE2) / CONV_MINUTE_TO_MILLISEC);
    }

    // System.out.println(actualWorkTimeMin);
    // 給与の計算
    if (actualWorkTimeMin > ACTUAL_WORK_TIME_OVERTIME_OCCUR_MIN) {
      // 残業代を求める＋本来の給料分を払う。((540-480)*(900*1.25)+)
      partTimeFee2 = (int) ((actualWorkTimeMin - ACTUAL_WORK_TIME_OVERTIME_OCCUR_MIN)
          * (MINUTES_SALARY * OVERTIME_SALARY_RATE) + (MINUTES_SALARY * ACTUAL_WORK_TIME_OVERTIME_OCCUR_MIN));
      partTimeFee2 = actualWorkTimeMin * MINUTES_SALARY;
    } else {
      partTimeFee2 = actualWorkTimeMin * MINUTES_SALARY;
    }
    // System.out.println(partTimeFee2);
    return partTimeFee2;
  }
}

// public class payrollCalculation {
// public static void main(String[] args1) {
// int workingTime = 36_000_000; // 10時間、1時間=3,600,000
// // System.out.println(workingTime / 60000); // 600(1分換算)
// int workingHour = 7; // 働いた時間 1 <= workingMinute
// int workingMinute = 40; // 働いた分
// final int HOURLY_WAGE = 900; // 時給900円
// final int MINUTE_WAGE = HOURLY_WAGE / 60; // 分給15円(時給を1分換算)
// int calcWage = 0;
// int actualHourWage = 0; // 実働時間（時）。労働時間が8時間以上なので、強制的に1時間のQKを挿入。
// int actualMinuteWage = 0; // 実働時間（分）。

// if (8 <= workingHour && 1 <= workingMinute || 8 <= workingHour) { //
// 労働時間が8時間以上なら残業代を払う
// int overtimeWage = 0; // 残業代
// final double OVERTIME_RATE = 1.25; // 残業代のレート
// int overtimeWorkingHour = workingHour - 8; // 残業時間（時）
// int overtimeWorkingMinute = workingMinute; // 残業時間（分）

// overtimeWage += (overtimeWorkingHour * (int) (HOURLY_WAGE * OVERTIME_RATE));
// // 残業代を計算。(2 *(900*1.25))
// overtimeWage += (overtimeWorkingMinute * (int) (MINUTE_WAGE *
// OVERTIME_RATE)); // 残業代を計算。(2 *(900*1.25))
// System.out.println(overtimeWage);
// calcWage += overtimeWage; // 残業代を給料に追加ぁ！
// }

// if ((6 <= workingHour && 1 <= workingMinute || 6 < workingHour && 0 <=
// workingMinute)
// && (workingHour < 8 || workingHour == 8 && workingMinute == 0)) { //
// 労働時間が6時間以上8時間以下なら45分QK発生。
// if (workingMinute <= 45) { // 労働時間(分)が45分以内ならworkingHourを-1して繰り下げ
// System.out.println("<=45");
// actualHourWage = workingHour -= 1;
// actualMinuteWage = (workingMinute + 60) - 45;
// } else { // 労働時間(分)が引き算できるので変更なし
// actualHourWage = workingHour;
// actualMinuteWage = workingMinute - 45; //
// }
// } else if (8 <= workingHour && 1 <= workingMinute || 8 < workingHour && 0 <=
// workingMinute) { // 労働時間が8時間以上なら1時間のQK発生。
// System.out.println("<8");
// actualHourWage = workingHour - 1;
// actualMinuteWage = workingMinute; //
// } else {
// actualHourWage = workingHour;
// actualMinuteWage = workingMinute; //
// }

// // 労働時間と実働時間が同じ問題
// calcWage += ((actualHourWage * HOURLY_WAGE) + (actualMinuteWage *
// MINUTE_WAGE));
// System.out.println("本日の労働時間は" + workingHour + "時間" + workingMinute + "分です。
// 給料：" + calcWage + "円");
// System.out.println("実働（時）：" + actualHourWage + " 実働（分）" + workingMinute);
// }
// }
