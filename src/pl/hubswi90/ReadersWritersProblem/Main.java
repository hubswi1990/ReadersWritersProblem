package pl.hubswi90.ReadersWritersProblem;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;


public class Main {
	
	public static void main(String[] args) {

		int readers = 4;
		int writers = 2;
		Semaphore sem = new Semaphore(readers, true);
		AtomicBoolean stop = new AtomicBoolean(false);
		
		Reader[] readersTab = new Reader[readers];
		Writer[] writersTab = new Writer[writers];

		for (int i = 0; i < readersTab.length; i++) {
			readersTab[i] = new Reader(5000, sem, i+1, stop);
			readersTab[i].start();
		}
		
		for(int i = 0; i < writersTab.length; i ++){
			writersTab[i] = new Writer(readers, 5000, sem, i+1, stop);
			writersTab[i].start();
		}
		
		try {
			Thread.sleep(29000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
		stop.set(true);
	}

}
