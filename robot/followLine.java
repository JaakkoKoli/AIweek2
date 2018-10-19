package org.lejos.example;
import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

public class FollowLine {
    static DifferentialPilot pilot = new DifferentialPilot(5.6, 17.5f, Motor.A, Motor.B);
    static UltrasonicSensor ultra = new UltrasonicSensor(SensorPort.S2);
    static LightSensor light = new LightSensor(SensorPort.S1);
    static int limit = 5;
    public static void main(String[] args) {
        double line = light.readValue();
        System.out.println(line);
        Button.waitForAnyPress();
		pilot.forward();
		while(true){
			if(Math.abs(line-light.readValue())>limit){
				pilot.stop();
				while(Math.abs(line-light.readValue())>limit){
					pilot.rotateLeft();
				}
				pilot.stop();
				pilot.forward();
			}
		}
    }
}
