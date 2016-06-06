/**
 * @author Gal Weinstock
 */
public class ExtendedMaxHeap {
	HeapElement[] heap;
	int capacity;
	int heapSize;
	int sumOfKeys;
	HeapElement minElement;

	/**
	 * ExtendedMaxHeap Constructor - builds an instance of ExtendedMaxHeap from
	 * an integer that indicate the maximum size of the heap.
	 * 
	 * @param capacity
	 */
	public ExtendedMaxHeap(int capacity) {
		if (capacity < 1) {
			throw new HeapException("The capacity is bad");
		}
		this.capacity = capacity;
		this.heapSize = 0;
		heap = new HeapElement[capacity + 1];
		heap[0] = null;
		sumOfKeys = 0;
		minElement = new HeapElement(Integer.MAX_VALUE, new PersonWithMoney(
				"Poorest", Integer.MAX_VALUE));
	}

	/**
	 * ExtendedMaxHeap Constructor - builds an instance of ExtendedMaxHeap from
	 * an array of HeapElement objects and an integer that indicate the maximum
	 * size of the heap.
	 * 
	 * @param elementsArray
	 * @param capacity
	 */
	public ExtendedMaxHeap(HeapElement[] elementsArray, int capacity) {
		this(capacity);
		if ((elementsArray == null || elementsArray.length == 0)
				&& capacity < elementsArray.length) {
			throw new HeapException("The array length is incorect");
		}
		int n = elementsArray.length;
		for (int i = n / 2; i > 1; i--) {
			percDown(i, elementsArray[i], n);
			sumOfKeys += elementsArray[i].getKey();
			if (elementsArray[i].getKey() < minElement.getKey()) {
				minElement.setKey(elementsArray[i].getKey());
			}
		}
	}

	/**
	 * Insert a HeapElement object to the heap. This method will insert the
	 * object to it's correct place by the object's key.
	 * 
	 * @param e
	 */
	public void insert(HeapElement e) {
		if (heapSize + 1 > capacity) {
			throw new HeapException("Overload");
		}
		heap[++heapSize] = e;
		percUp(heapSize, e);
		sumOfKeys += e.getKey();
		if (e.getKey() < minElement.getKey()) {
			minElement.setKey(e.getKey());
			minElement.setData((PersonWithMoney) e.getData());
		}
	}

	/**
	 * Delete the element with the largest key from the heap.
	 * 
	 * @return HeapElement
	 * @throws HeapException
	 */
	public HeapElement deleteMax() throws HeapException {
		if (heapSize == 0) {
			throw new HeapException("the queue is empty");
		}
		HeapElement tmp = heap[1];
		heap[1] = heap[heapSize--];
		sumOfKeys -= tmp.getKey();
		percDown(1, heap[1], heapSize);
		return tmp;
	}

	/**
	 * This method return the average of all the keys of this instance of
	 * ExtendedMaxHeap.
	 * 
	 * @return long
	 */
	public long getKeysAverage() {
		return sumOfKeys / heapSize;
	}

	/**
	 * This method return the element with the minimal key of this ExtendedMaxHeap.
	 * 
	 * @return HeapElement
	 */
	public HeapElement getElementWithMinKey() {
		if (heapSize == 0) {
			throw new HeapException("the queue is empty");
		}
		return minElement;
	}

	/**
	 * Percolate the HeapElement e down the tree to it's right location (by the keys). 
	 * 
	 * @param i
	 * @param e
	 * @param length
	 */
	private void percDown(int i, HeapElement e, int length) {
		int left;
		int thisKey = e.getKey();

		if (i * 2 > length) {
			heap[i] = e;
		} else if (i * 2 == length) {
			left = heap[i * 2].getKey();
			if (left > thisKey) {
				heap[i] = heap[i * 2];
				heap[i * 2] = e;
			} else {
				heap[i] = e;
			}
		} else {
			int j;
			int right = heap[(i * 2) + 1].getKey();
			if (heap[i * 2].getKey() > right) {
				j = i * 2;
			} else {
				j = (i * 2) + 1;
			}
			if (heap[j].getKey() > thisKey) {
				heap[i] = heap[j];
				heap[j] = e;
				percDown(j, e, length);
			} else {
				heap[i] = e;
			}
		}
	}

	/**
	 * Percolate the HeapElement e up the tree to it's right location (by the keys). 
	 * 
	 * @param i
	 * @param e
	 */
	private void percUp(int i, HeapElement e) {
		if (i == 1) {
			heap[1] = e;
		} else if (heap[i / 2].getKey() > e.getKey()) {
			heap[i] = e;
		} else {
			heap[i] = heap[i / 2];
			heap[i / 2] = e;
			percUp(i / 2, e);
		}
	}
}