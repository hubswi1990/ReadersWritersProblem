package pl.hubswi90.ReadersWritersProblem;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class Reader extends Thread {

	private int id;
	private long millis;
	private Semaphore sem;
	private AtomicBoolean stop;

	public Reader(int millis, Semaphore sem, int id, AtomicBoolean stop) {
		this.millis = millis;
		this.sem = sem;
		this.id = id;
		this.stop = stop;
	}

	@Override
	public void run() {

		try {
			while (!stop.get()) {
				sem.acquire();
				System.out.println("Reader " +id +" read the book");
				Thread.sleep(millis);
				System.out.println("Reader " +id +"  no longer read the book");
				sem.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
