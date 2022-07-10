import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Cache {
    private final Map<String, CachedItem> cachedItems;
    private final LinkedList<CachedItem> cachedItemsLinkedList;
    private final int maxCachedItems;
    private int totalCachedItems;

    public Cache(int maxCachedItems) {
        this.cachedItems = new HashMap<String, CachedItem>();
        this.cachedItemsLinkedList = new LinkedList<>();
        this.maxCachedItems = maxCachedItems;
    }

    public String get(String key) {
        if (cachedItems.containsKey(key)) {
            CachedItem cachedItem = cachedItems.get(key);

            moveCachedItemToFrontOfLinkedList(cachedItem);

            return cachedItems.get(key).getValue();
        } else {
            return null;
        }
    }

    public void set(String key, String value) {
        if (!cachedItems.containsKey(key)) {
            if (totalCachedItems == maxCachedItems) {
                CachedItem oldestCachedItem = cachedItemsLinkedList.getLast();

                /*
                 * Oldest item is removed at O(1)
                 * */
                cachedItems.remove(oldestCachedItem.getKey());
                cachedItemsLinkedList.removeLast();
            } else {
                totalCachedItems++;
            }

            CachedItem cachedItem = new CachedItem(key, value);

            cachedItemsLinkedList.addFirst(cachedItem);
            cachedItems.put(key, cachedItem);
        } else {
            CachedItem cachedItem = cachedItems.get(key);

            moveCachedItemToFrontOfLinkedList(cachedItem);
        }
    }

    public void toObject() {
        for (Map.Entry<String, CachedItem> entry: cachedItems.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().getValue());
        }
    }

    private void moveCachedItemToFrontOfLinkedList(CachedItem cachedItem) {
        cachedItemsLinkedList.remove(cachedItem);
        cachedItemsLinkedList.addFirst(cachedItem);
    }

    private class CachedItem {
        private String key;
        private String value;

        public CachedItem(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
}
