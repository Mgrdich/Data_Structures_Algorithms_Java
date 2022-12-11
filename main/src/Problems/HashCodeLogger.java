package Problems;

import adt.Entry;
import data_structure.SimpleEntry;
import data_structure.SinglyLinkedList;

public class HashCodeLogger {
    public static void main(String[] args) {
        String[] arr = {"top", "tree", "AVL", "exam", "pass", "fail", "DS", "PQ", "stack", "pot", "spot", "post", "queue"};

        DummySeperateChainHashMap<Boolean> map = new DummySeperateChainHashMap<>(11, new HashFnV1());

        DummyLinearProbHashMap<Boolean> mapL = new DummyLinearProbHashMap<>(17, new HashFnV2(33));

        DummyLinearProbHashMap<Boolean> mapL2 = new DummyLinearProbHashMap<>(17, new HashFnV2(31));

        System.out.println("*********************Start**************************");
        for (String item : arr) {
            map.put(item, true);
        }

        System.out.println("-----------N=11 Separate chain Array-------------");
        System.out.println(map);
        System.out.println("-----------N=11 Separate chain Array-------------");

        System.out.println("***********************End************************");


        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("*********************Start**************************");

        for (String item : arr) {
            mapL.put(item, true);
        }

        System.out.println("-----------N=17, a=33 Linear Prob Array-------------");
        System.out.println(mapL);
        System.out.println("-----------N=17, a=33 Linear Prob Array-------------");

        System.out.println();
        System.out.println("***********************End************************");


        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("*********************Start**************************");
        for (String item : arr) {
            mapL2.put(item, true);
        }

        System.out.println();

        System.out.println("-----------N=17, a=31 Linear Prob Array-------------");
        System.out.println(mapL2);
        System.out.println("-----------N=17, a=31 Linear Prob Array-------------");
        System.out.println("***********************End************************");
    }

    private static int mathModulo(int number, int mod) {
        int result = number % mod;
        if (result >= 0) return result;

        return result + mod;
    }

    public static class DummyLinearProbHashMap<V> {
        private final Entry<String, V>[] arr;
        private final hashFunction hashFn;

        private final int size;

        @SuppressWarnings("unchecked")
        public DummyLinearProbHashMap(int i, hashFunction hashFn) {
            arr = new SimpleEntry[i];
            size = i;
            this.hashFn = hashFn;
        }

        public void put(String key, V value) {
            Entry<String, V> entry = new SimpleEntry<>(key, value);
            int hashCode = hashFn.hashCode(entry.getKey());
            int compression = hashFn.compressionFn(hashCode, size);

            if (arr[compression] == null) {
                System.out.println("key: " + key + " | hashCode: " + hashCode + " | compression: " + compression + " | probbed index: " + compression);
                arr[compression] = entry;
                return;
            }

            int index = compression + 1;
            while (index != compression) {
                if (arr[index] == null) {
                    arr[index] = entry;
                    System.out.println("key: " + key + " | hashCode: " + hashCode + " | compression: " + compression + " | probbed index: " + index);
                    break;
                }
                index = (index + 1) % size;
            }
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("{");
            for (Entry<String, V> entrySinglyLinkedList : arr) {
                String s = entrySinglyLinkedList == null ? null : entrySinglyLinkedList.getKey();
                stringBuilder.append(s).append(", ");
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append("}");

            return stringBuilder.toString();
        }
    }

    public static class DummySeperateChainHashMap<V> {
        private final SinglyLinkedList<Entry<String, V>>[] arr;
        private final hashFunction hashFn;

        private final int size;

        @SuppressWarnings("unchecked")
        public DummySeperateChainHashMap(int i, hashFunction hashFn) {
            arr = new SinglyLinkedList[i];
            size = i;
            this.hashFn = hashFn;
        }

        public void put(String key, V value) {
            Entry<String, V> entry = new SimpleEntry<>(key, value);
            int hashCode = hashFn.hashCode(entry.getKey());
            int compression = hashFn.compressionFn(hashCode, size);
            System.out.println(key + " | hashCode: " + hashCode + " | compression: " + compression);
            if (arr[compression] == null) {
                arr[compression] = new SinglyLinkedList<>();
            }
            arr[compression].addLast(entry);
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("{");
            for (SinglyLinkedList<Entry<String, V>> entrySinglyLinkedList : arr) {
                stringBuilder.append(entrySinglyLinkedList).append(", ");
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append("}");

            return stringBuilder.toString();
        }
    }

    interface hashFunction {
        int hashCode(String i);

        int compressionFn(int i, int size);
    }

    private static class HashFnV1 implements hashFunction {

        @Override
        public int hashCode(String str) {
            int code = str.charAt(0);
            for (int j = 1; j < str.length(); j++) {
                code = code ^ str.charAt(j);
            }
            return code;
        }

        @Override
        public int compressionFn(int i, int size) {
            return mathModulo(i, size);
        }
    }

    private static class HashFnV2 implements hashFunction {

        private final int horn;

        public HashFnV2(int a) {
            horn = a;
        }

        @Override
        public int hashCode(String str) {

            int n = str.length();

            int hashCode = str.charAt(0);

            for (int i = 1; i < n; i++) {
                hashCode = horn * hashCode + str.charAt(i);
            }
            return hashCode;
        }

        @Override
        public int compressionFn(int i, int size) {
            int a = 5;
            int b = 40;
            int p = 40;
            int result = mathModulo((a * i + b), p);
            return mathModulo(result, size);
        }
    }
}
