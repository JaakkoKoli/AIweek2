package org.lejos.example;
import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

public class Park {
    static DifferentialPilot pilot = new DifferentialPilot(5.6, 17.5f, Motor.A, Motor.B);
    static UltrasonicSensor ultra = new UltrasonicSensor(SensorPort.S2);
    static LightSensor light = new LightSensor(SensorPort.S1);
    public static void main(String[] args) {
        Button.waitForAnyPress();
		int emptySpace = 0;
		while(emptySpace<40){
			pilot.travel(1,true);
			while(pilot.isMoving()){
				if(ultra.getDistance()<40) emptySpace=0;
			}
			if(ultra.getDistance()>=40) emptySpace++;
		}
		pilot.travel(-40,false);
		pilot.rotate(-90,false;);
		pilot.travel(-40,false);
		pilot.rotate(90);
    }
}