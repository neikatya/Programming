public class List <T extends Comparable<T>> {
    private int size;
    private T[]array;
    private int last=-1; //последний заполненный
    public List (T[] array){     //передает массив с указанным типом
        this.array = array;
    }
    public void insert(T t, int p){   //вставка элемента
        for (int i = last; i >= p; i--)
            array[p+1] = array[p];
        array[p]=t;
        last++;
    }
    public int locate(T t){  //определение позиции элемента
        for (int i=0; i<=last; i++){
            if (array[i].compareTo(t)==0)
                return i;
        }
        return last+1;
    }
    public T retrieve(int p){    //возвращаем элемент данной позиции
        if (p >= 0 && p < last+1) return array[p];
        else throw new IndexOutOfBoundsException();
    }
    public void delete(int p) {   //удаление элемента
        if (p >= last+1 || p < 0) return;
        for (int i = p; i<last; i++)
            array[i]=array[i+1];
        last--;
    }
    public int next(int p){   //получение следующей позиции
        if (p >= last+1 || p < 0) throw new IndexOutOfBoundsException();
        return ++p;
    }
    public int previous(int p){  //получение предыдущей позиции
        if (p >= last+1 || p <= 0) throw new IndexOutOfBoundsException();
        return --p;
    }
    public void makeNull(){    //делает список пустым
        last = -1;
    }
    public int first(){    //возвращает первую позицию
        return 0;
    }
    public int endlist(){  //возвращает позицию после последнего
        return last+1;
    }

    public void printList(){
        System.out.println(this);
    }
    public String toString(){
        String s = "";
        for (int i = 0; i <= last; i++)
            s = s+ array[i] + " ";
        return s;
    }
}















 // if (p>size || (p<0)) return;