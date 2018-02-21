package l5alarm;

import java.util.Observable;
import java.util.Observer;

public class DemoAlarmA {
	public DemoAlarmA(int ms) {
		AlarmThreadA at = new AlarmThreadA(ms);
		at.startAlarm();
		at.addObserver(new AlarmPrinter());
		at.addObserver(new WakeUpPrinter());
		at.addObserver(new ConsolePrinter("consolealarm"));
	}

	public static void main(String[] args) {
		DemoAlarmA da = new DemoAlarmA(4000);
	}

	private class AlarmPrinter implements Observer {
		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("AlarmPrinter alarm!");
		}

	}

	private class WakeUpPrinter implements Observer {
		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("Wake up printer alarm!");
		}
	}

	private class ConsolePrinter implements Observer {

		String str;

		public ConsolePrinter(String str) {
			this.str = str;
		}

		@Override
		public void update(Observable o, Object arg) {
			System.out.println(str);
		}

	}
}
