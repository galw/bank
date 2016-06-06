/**
 * @author Gal Weinstock
 */
public class BankQueue {

	ExtendedMaxHeap heap;

	/**
	 * BankQueue Constructor - builds an instance of BankQueue from an integer
	 * that indicate the maximum size of the queue.
	 * 
	 * @param maxQueueSize
	 */
	public BankQueue(int maxQueueSize) {
		try {
			heap = new ExtendedMaxHeap(maxQueueSize);
		} catch (HeapException e) {
			System.out.println("Try again");
		}
	}

	/**
	 * BankQueue Constructor - builds an instance of BankQueue from an array of
	 * PersonWithMoney objects and an integer that indicate the maximum size of
	 * the queue.
	 *
	 * @param people
	 * @param maxQueueSize
	 */
	public BankQueue(PersonWithMoney[] people, int maxQueueSize) {
		this(maxQueueSize);

		try {
			for (int i = people.length - 1; i >= 0; i--) {
				enqueue(people[i]);
			}
		} catch (NullPointerException e) {
			System.out.println("Error!");
		} catch (HeapException e1) {
			System.out.println("Error!");
		}
	}

	/**
	 * Enqueue one PersonWithMoney object to the line. He will be added to it's
	 * correct place by using insert method from ExtendedMaxHeap class.
	 * 
	 * @param p
	 */
	public void enqueue(PersonWithMoney p) {
		try {
			heap.insert(new HeapElement(p.getMoney(), p));
		} catch (HeapException e) {
			System.out.println("Try again");
		}
	}

	/**
	 * Dequeue one PersonWithMoney object from the line.
	 * 
	 * @return PersonWithMoney
	 */
	public PersonWithMoney dequeue() {
		try {
			return (PersonWithMoney) heap.deleteMax().getData();
		} catch (HeapException e) {
			System.out.println("Try again");
		}
		return null;
	}

	/**
	 * Steal money from the richest PersonWithMoney in the line. The amount is
	 * set by the ammount parameter.
	 * 
	 * @param ammount
	 */
	public void stealFromFirstInQueue(int ammount) {
		PersonWithMoney richest = dequeue();
		richest.setMoney(richest.getMoney() - ammount);

		enqueue(richest);
	}

	/**
	 * Get the average money of all the people in the line.
	 * 
	 * @return int
	 */
	public int getAverageMoneyInQueue() {
		return (int) heap.getKeysAverage();
	}

	/**
	 * Get PersonWithMoney object of the the person with the least amount of
	 * money.
	 * 
	 * @return PersonWithMoney
	 */
	public PersonWithMoney getPoorestPerson() {
		try {
			return (PersonWithMoney) heap.getElementWithMinKey().getData();
		} catch (HeapException e) {
			System.out.println("Try again");
		}
		return null;
	}
}