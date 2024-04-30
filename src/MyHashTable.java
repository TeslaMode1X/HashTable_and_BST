public class MyHashTable<K, V> {
    private static class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<?, ?> next;
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }
    private HashNode<?, ?>[] chainArray;
    private int M = 11;
    private int size;
    private double loadFactor = 0.75;

    public MyHashTable() {
        chainArray = new HashNode[M];
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
    }

    public MyHashTable(int M, double loadFactor) {
        this.M = M;
        chainArray = new HashNode[M];
        this.loadFactor = loadFactor;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    @SuppressWarnings("unchecked")
    public void put(K key, V value) {
        double loadFactor = (double) M / size;
        if (loadFactor <= this.loadFactor) {
            rehash();
        }

        int index = hash(key);

        if (chainArray[index] == null) {
            chainArray[index] = new HashNode<>(key, value);
        } else {
            HashNode<K, V> current = (HashNode<K, V>) chainArray[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = (HashNode<K, V>) current.next;
            }
            current.next = new HashNode<>(key, value);
        }
        size++;
    }

    @SuppressWarnings("unchecked")
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> current = (HashNode<K, V>) chainArray[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = (HashNode<K, V>) current.next;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public V remove(K key) {
        int index = hash(key);

        HashNode<K, V> current = (HashNode<K, V>) chainArray[index];
        HashNode<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev != null) {
                    prev.next = current.next;
                } else {
                    chainArray[index] = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = (HashNode<K, V>) current.next;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public boolean contains (V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> temp = (HashNode<K, V>) chainArray[i];

            while (temp != null) {
                if (temp.value.equals(value)) {
                    return true;
                }
                temp = (HashNode<K, V>) temp.next;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> temp = (HashNode<K, V>) chainArray[i];

            while (temp != null) {
                if (temp.value.equals(value)) {
                    return temp.key;
                }
                temp = (HashNode<K, V>) temp.next;
            }
        }
        return null;
    }

    public int length() {
        return size;
    }
    @SuppressWarnings("unchecked")
    private void rehash() {
        size = 0;
        M = M * 2 + 1;
        HashNode<K, V>[] old = (HashNode<K, V>[]) chainArray;
        chainArray = new HashNode[M];

        for (HashNode<K, V> kvHashNode : old) {
            HashNode<K, V> temp = kvHashNode;
            while (temp != null) {
                put(temp.key, temp.value);
                temp = (HashNode<K, V>) temp.next;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void search() {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> temp = (HashNode<K, V>) chainArray[i];
            System.out.print("Bucket " + i +": ");
            while (temp != null) {
                System.out.print(temp + " -> ");
                temp = (HashNode<K, V>) temp.next;
            }
            System.out.println("null");
        }
    }

}


