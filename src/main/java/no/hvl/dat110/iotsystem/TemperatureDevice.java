package no.hvl.dat110.iotsystem;

import java.util.concurrent.TimeUnit;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		// simulated / virtual temperature sensor
		TemperatureSensor sn = new TemperatureSensor();

		// create a client object and use it to
		// - connect to the broker - user "sensor" as the user name
		Client client = new Client("sensor", Common.BROKERHOST, Common.BROKERPORT);
		client.connect();
		
		// - publish the temperature(s)
		for (int i = 0; i < COUNT; i++) {
			try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) {}
			int h = sn.read();
			client.publish(Common.TEMPTOPIC, Integer.toString(h));
		}
		
		// - disconnect from the broker
		client.disconnect();

		System.out.println("Temperature device stopping ... ");

	}
}
