package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	Entry<K, V>[] table;

	public SimpleHashMap() {
		table = (Entry<K, V>[]) new Entry[16];
	}

	public SimpleHashMap(int capacity) {
		table = (Entry<K, V>[]) new Entry[capacity];
	}

	public String show() {
		StringBuilder s = new StringBuilder();

		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				s.append(i + " " + table[i].toString());
				Entry<K, V> temp = table[i];
				while (temp.next != null) {
					s.append(" " + temp.getNext().toString());
					temp = temp.getNext();
				}
				s.append("\n");
			} else {
				s.append(i + "  null\n");
			}
		}
		return s.toString();
	}

	@Override
	public V get(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public V put(K arg0, V arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		int c = 0;

		for (int i = 0; i < table.length; i++) {
			Entry<K, V> temp = table[i];

			while (temp != null) {
				temp = temp.next;
				c++;
			}
		}
		return c;
	}

	public static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public Entry<K, V> getNext() {
			return next;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			this.value = value;
			return this.value;
		}

		@Override
		public String toString() {
			return key + " = " + value;
		}

	}

}
