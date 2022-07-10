public class Main {
    public static void main(String args[]) throws Exception {
        final int maxCachedItems = 3;

        Cache cache = new Cache(maxCachedItems);

        cache.set("A", "A value");
        cache.set("B", "B value");
        cache.set("C", "C value");

        cache.get("B");
        cache.get("C");
        cache.get("B");
        cache.get("A");

        cache.set("D", "D value");

        cache.toObject();
    }
}
