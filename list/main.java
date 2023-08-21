public class main {
    public static void main(String[] args) {

        List<String> list1 = new List<>(new String[10]);
        list1.insert("один",list1.endlist());
        list1.insert("два",list1.endlist());
        list1.insert("три",list1.endlist());
        list1.insert("четыре",list1.endlist());
        list1.insert("пять",list1.endlist());
        list1.insert("шесть",list1.endlist());
        list1.insert("пять",list1.endlist());
        list1.insert("восемь",list1.endlist());
        list1.insert("девять",list1.endlist());
        list1.insert("десять",list1.endlist());

        list1.printList();
        removedub(list1);         //удаление дубликатов
        list1.printList();

        List<Integer> list2 = new List<>(new Integer[10]);
        list2.insert(1,list2.endlist());
        list2.insert(2,list2.endlist());
        list2.insert(4,list2.endlist());
        list2.insert(5,list2.endlist());
        list2.insert(1,list2.endlist());
        list2.insert(9,list2.endlist());
        list2.insert(3,list2.endlist());
        list2.insert(6,list2.endlist());
        list2.insert(5,list2.endlist());
        list2.insert(8,list2.endlist());

        list2.printList();
        removedub(list2);         //удаление дубликатов
        list2.printList();

        List<Rational> list3= new List<>(new Rational[10]);
        list3.insert(new Rational(0,2), list3.endlist());
        list3.insert(new Rational(3,4), list3.endlist());
        list3.insert(new Rational(-1,-2), list3.endlist());
        list3.insert(new Rational(-9,2), list3.endlist());
        list3.insert(new Rational(1,2), list3.endlist());

        list3.printList();
        removedub(list3);        //удаление дубликатов
        list3.printList();
    }
    public static <T extends Comparable> void removedub(List list){
        int i = list.first(), j;
        while(i != list.endlist()){
            j = list.next(i);
            while (j != list.endlist()){
                if (list.retrieve(i).compareTo(list.retrieve(j))==0) list.delete(j);
                else j=list.next(j);
            }
            i=list.next(i);
        }
    }

}






















