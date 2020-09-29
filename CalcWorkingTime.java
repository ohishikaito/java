import java.sql.Time;

public class CalcWorkingTime {

	public static void main(String[] args1) {

		// 計算用の数値を定数で用意
		final long ONE_HOUR_BY_MILLI_SEC = 1000 * 60 * 60; // 1時間のミリ秒換算
		final long ONE_MIN_BY_MILLI_SEC = 1000 * 60; // 1分のミリ秒換算
		final int ONE_HOUR_BY_MIN = 60; // 1時間の分換算

		// バイトの開始時間と終了時間をコマンドライン引数から受け取る
		String[] args = { "08:00:00", "18:00:00" };
		Time startTime = Time.valueOf(args[0]);
		Time finishTime = Time.valueOf(args[1]);
		System.out.println(startTime + " " + finishTime);

		// getTimeメソッドを使って労働時間をミリ秒（0.001秒単位）で取得する
		// ※getTime()メソッドの戻り値はlong型であることに注意
		long workingTime = finishTime.getTime() - startTime.getTime();
		System.out.println(workingTime / 60000);

		// ミリ秒で取得した労働時間を○時間△分の形式に直す
		int workingHour = (int) (workingTime / ONE_HOUR_BY_MILLI_SEC); // 時間に換算
		int workingMin = (int) ((workingTime / ONE_MIN_BY_MILLI_SEC) % ONE_HOUR_BY_MIN); // 分に換算
		// (360000000 / 600000 ) 600 % 60

		// 出力
		System.out.println("本拾の働時間は" + workingHour + "時間" + workingMin + "分です。");
	}
}
