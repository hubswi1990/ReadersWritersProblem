package pl.hubswi90.ReadersWritersProblem;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;


public class Writer extends Thread {

	private int readers, id;
	private long millis;
	private Semaphore sem;
	private AtomicBoolean stop;
	
	/**
	 * @param readers
	 * @param millis
	 * @param sem
	 */
	public Writer(int readers, long millis, Semaphore sem, int id, AtomicBoolean stop) {
		this.readers = readers;
		this.millis = millis;
		this.sem = sem;
		this.id = id;
		this.stop = stop;
	}
	
	@Override
	public void run() {

		try {
			while (!stop.get()) {
				sem.acquire(readers);
				System.out.println("Writer " +id +" add a new chapter to the book ...");
				Thread.sleep(millis);
				System.out.println("Writer " +id +" finished writing");
				sem.release(readers);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
