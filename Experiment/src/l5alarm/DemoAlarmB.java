package l5alarm;

public class DemoAlarmB {
	public DemoAlarmB(int ms) {
		AlarmThreadB bt = new AlarmThreadB(ms);
		bt.startAlarm();
		bt.addAlarmListener(new AlarmPrinter());
		bt.addAlarmListener(new WakeUpPrinter());
		bt.addAlarmListener(new ConsolePrinter("ConsolePrinter alarm"));

	}

	public static void main(String[] args) {
		DemoAlarmB da = new DemoAlarmB(4000);
	}

	private class AlarmPrinter implements AlarmListener {
		public void alarm() {
			System.out.println("AlarmPrinter alarm");
		}
	}

	private class WakeUpPrinter implements AlarmListener {

		public void alarm() {
			System.out.println("WakeUpPrinter alarm");

		}
	}

	private class ConsolePrinter implements AlarmListener {

		String str;

		public ConsolePrinter(String str) {
			this.str = str;
		}
		public void alarm() {
			System.out.println(str);
		}
	}
}
