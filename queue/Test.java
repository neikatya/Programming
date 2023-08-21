public class Test {
    public static void main(String[] args) {
        sklad Sklad = new sklad();
        new consumer(Sklad, "Потребитель 1");
        new consumer(Sklad, "Потребитель 2");
        new producer(Sklad, "Производитель");
    }
}
