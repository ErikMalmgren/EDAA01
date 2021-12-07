package map;

import java.util.Random;

public class SimpleHashMap<K, V> implements Map<K, V> {
	Entry<K, V>[] table;

	public SimpleHashMap() {
		table = (Entry<K, V>[]) new Entry[16];
	}

	public SimpleHashMap(int capacity) {
		table = (Entry<K, V>[]) new Entry[capacity];
	}

	public static void main(String[] args) {
		Random rand = new Random();
		SimpleHashMap<Integer, Integer> test = new SimpleHashMap<>(10);
		for (int i = 0; i < 100; i++) {
			int rndNumb = rand.nextInt(2000) - 800;
			test.put(rndNumb, rndNumb);
		}
		System.out.println(test.show());
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
	public V get(Object key) {
		@SuppressWarnings("unchecked")
		Entry<K, V> temp = find(index((K) key), (K) key);

		if (temp != null) {
			return temp.getValue();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public V put(K key, V value) {
		Entry<K, V> temp = find(index(key), key);

		if (temp != null) {
			V oldValue = temp.getValue();
			temp.setValue(value);

			if (size() >= 0.75 * table.length) {
				rehash();
			}
			return oldValue;
		} else {

			if (table[index(key)] == null) {
				table[index(key)] = new Entry<K, V>((K) key, (V) value);
			} else {
				temp = table[index(key)];

				while (temp.getNext() != null) {
					temp = temp.getNext();
				}
				temp.setNext(new Entry<K, V>((K) key, (V) value));
			}

		}

		if (size() >= 0.75 * table.length) {
			rehash();
		}
		return null;
	}

	@Override
	public V remove(Object key) {
		if(!isEmpty()) {
			Entry<K ,V> temp = find(index((K) key), (K) key);
			Entry<K, V> oldTemp = table[index((K) key)];
			
			if(temp != null) {
				V oldValue = temp.getValue();
				if(temp.getKey().equals(oldTemp.getKey())) {
					if(temp.next != null) {
						table[index((K) key)] = temp.getNext();
						return oldValue;
					} else {
						table[index((K) key)] = null;
						return oldValue;
					}
				}
				while(oldTemp.getNext() != null) {
					if(oldTemp.getNext().getKey().equals(temp.getKey())) {
						oldTemp.setNext(temp.getNext());
						return oldValue;
					} else if(oldTemp.getNext().getNext() == null) {
						oldTemp.getNext().setNext(null);
						return oldValue;
					} else {
						oldTemp = oldTemp.getNext();
					}
					
				}
			}
		}
		return null;
	}

	@Override
	public int size() { //Detta kan göras som attribut istället
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

	public int index(K key) {
		return Math.abs(key.hashCode() % table.length);
	}

	private Entry<K, V> find(int index, K key) {
		Entry<K, V> temp = table[index];

		while (temp != null) {
			if (temp.getKey().equals(key)) {
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}

	private void rehash() {
		Entry<K, V>[] tempTable = (Entry<K, V>[]) new Entry[table.length * 2];
		Entry<K, V>[] oldTable = table;
		table = tempTable;

		for (int i = 0; i < oldTable.length; i++) {
			Entry<K, V> temp = oldTable[i];
			if (temp != null) {
				while (temp.getNext() != null) {
					put(temp.getKey(), temp.getValue());
					temp = temp.getNext();
				}
				put(temp.getKey(), temp.getValue());
			}
		}
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

		public Entry<K, V> setNext(Entry<K, V> next) {
			this.next = next;
			return this.next;
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
