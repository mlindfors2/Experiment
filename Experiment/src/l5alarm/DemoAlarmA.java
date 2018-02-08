package l5alarm;

public class DemoAlarmA {
	public DemoAlarmA(int ms) {
		AlarmThreadA at = new AlarmThreadA(ms);
		at.startAlarm();
	}
	
	public static void main(String[] args) {
		DemoAlarmA da = new DemoAlarmA(4000);
	}
}
