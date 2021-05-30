package com.company;

public class HashTable<K, V> {
    private static final int DEFAULT_INITAL_CAPACITY = 10;

    private static final float LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] table;

    private int size = 0;

    private int use = 0;

    public HashTable() {
        table = (Entry<K, V>[]) new Entry[DEFAULT_INITAL_CAPACITY];
    }

    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] != null) {
        } else {
            table[index] = new Entry<>(null, null, null);
        }

        Entry<K, V> tmp = table[index];
        if (tmp.next != null) {
            do {
                tmp = tmp.next;
                if (tmp.key == key) {
                    tmp.value = value;
                    return;
                }
            } while (tmp.next != null);
            Entry<K, V> temp = table[index].next;
            table[index].next = new Entry<>(key, value, temp);
            size++;
        } else {
            tmp.next = new Entry<>(key, value, null);
            size++;
            use++;
            if (use >= table.length * LOAD_FACTOR) {
                resize();
            }
        }
    }

    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : ((h = key.hashCode()) ^ (h >>> 16)) % table.length;
    }

    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = (Entry<K, V>[]) new Entry[table.length * 2];
        use = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null && oldTable[i].next != null) {
                Entry<K, V> e = oldTable[i];
                while (e.next != null) {
                    e = e.next;
                    int index = hash(e.key);
                    if (table[index] == null) {
                        use++;
                        table[index] = new Entry<>(null, null, null);
                    }
                    table[index].next = new Entry<>(e.key, e.value, table[index].next);
                }
            }
        }
    }

    public void remove(K key) {
        int index = hash(key);
        Entry e = table[index];
        if (e != null && e.next != null) {
            Entry pre;
            Entry<K, V> headNode = table[index];
            do {
                pre = e;
                e = e.next;
                if (key == e.key) {
                    pre.next = e.next;
                    size--;
                    if (headNode.next == null) use--;
                    return;
                }
            } while (e.next != null);
        } else {
            return;
        }
    }

    public V get(K key) {
        int index = hash(key);
        Entry<K, V> e = table[index];
        if (e != null && e.next != null) {
            while (e.next != null) {
                e = e.next;
                if (key == e.key) {
                    return e.value;
                }
            }
            return null;
        } else {
            return null;
        }
    }
}
