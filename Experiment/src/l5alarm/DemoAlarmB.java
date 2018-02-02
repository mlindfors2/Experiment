package l5alarm;

public class DemoAlarmB {
	public DemoAlarmB(int ms) {
		AlarmThreadB bt = new AlarmThreadB(ms);
		bt.startAlarm();
	}
	
	public static void main(String[] args) {
		DemoAlarmB da = new DemoAlarmB(4000);
	}
}
