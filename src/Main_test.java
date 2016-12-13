import lejos.nxt.*;
import Navigation.Navigation;
import Odometer.Odometer;




public class Main_test {

	public static void main(String[] args) {
		
		int buttonChoice;
		
		
		//wait for button press
		do {
			buttonChoice = Button.waitForAnyPress();
		} while (buttonChoice != Button.ID_LEFT
				&& buttonChoice != Button.ID_RIGHT);
		do {
		
				
				//test navigation and odometer
				Odometer odo = new Odometer(); 
				
				odo.setTheta(90);
			
			char [] s = {'N', 'N', 'E', 'E', 'S', 'S', 'W', 'W'};
				odo.start();
			
			Navigation nav = new Navigation (odo, s);
			Display dis = new Display(odo, nav);
				nav.start();
				dis.start();
				//nav.travelTo(0, 30);
				//nav.travelTo(0, 60);
			
				
				
		}while (Button.waitForAnyPress() != Button.ID_ESCAPE);
		System.exit(0);
	}

}
