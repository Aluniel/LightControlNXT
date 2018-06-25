package miage.lightControlNXT.system;

import java.util.Timer;
import java.util.TimerTask;

import static miage.lightControlNXT.persistence.Data.getData;

import static miage.lightControlNXT.system.ControlSystem.getControlSystem;
public class Minuteur {
	
	//
	// FIELDS
	//
	
	/** T1 */
	private static Timer timerT1 = new Timer();
	/** T2 */
	private static Timer timerT2 = new Timer();
	/** T3 */
	private static Timer timerT3 = new Timer();
	
	/** Tache à effectuer après T1 minutes */
	private static TimerTask taskT1 = new TimerTask() {
		@Override
		public void run() {
			getControlSystem().keepCurrentConfiguration = false;
		}
	};
	
	/** Tache à effectuer après T2 minutes */
	private static TimerTask taskT2 = new TimerTask() {
		@Override
		public void run() {
			getControlSystem().setEtatPiece(EtatPiece.empty);
		}
	};
	
	/** Tache à effectuer après T3 minutes */
	private static TimerTask taskT3 = new TimerTask() {
		@Override
		public void run() {
			getControlSystem().setEtatPiece(EtatPiece.empty);
			cancelT2();
		}
	};
	
	//
	// PROPERTIES
	//
	
	/** @return T1 en millisecondes */
	private static int getT1InMs() {
		return getData().getT1() * 1000;
	}
	
	/** @return T2 en millisecondes */
	private static int getT2InMs() {
		return getData().getT1() * 1000;
	}
	
	/** @return T3 en millisecondes */
	private static int getT3InMs() {
		return getData().getT1() * 1000;
	}
	
	//
	// METHOD
	//
	
	/** Démarre ou redémarre le minuteur T1 */
	public static void startT1() {
		timerT1.cancel();
		timerT1.schedule(taskT1, getT1InMs());
	}
	
	/** Annule le minuteur T1 */
	public static void cancelT1() {
		timerT1.cancel();
	}
	
	/** Démarre ou redémarre le minuteur T2 */
	public static void startT2() {
		timerT2.cancel();
		timerT2.schedule(taskT2, getT2InMs());
	}
	
	/** Annule le minuteur T3 */
	public static void cancelT2() {
		timerT2.cancel();
	}
	
	/** Démarre ou redémarre le minuteur T3 */
	public static void startT3() {
		timerT3.cancel();
		timerT3.schedule(taskT3, getT3InMs());
	}
	
	/** Annule le minuteur T3 */
	public static void cancelT3() {
		timerT3.cancel();
	}
}
