public class Rational implements Comparable<Rational> {
    private int a;
    private int q;

    public Rational(int a, int q){
        if (q==0) throw new NumberFormatException ("Знаменатель 0");
        else if (q < 0){
            q=-q;
            a=-a;
        }
        int nod = nod(Math.abs(a),q);
            this.a=a/nod;
            this.q=q/nod;
    }
    private int nod(int a, int b){           //вычисление наибольшего общего делителя
        int ost;
        do {
            ost = a % b;
            a = b;
            b = ost;
        }
        while (ost != 0);
        return a;
    }
    public int compareTo(Rational number){    //сравнение
        return a * number.q - q * number.a;
    }
    public String toString(){
        String s="";
        if (a == 0) s = s + 0;
        else {
            if (a < q) s = s + a + "/" + q;
            else
                if (a == q) s = s + a / q + "";
                else s = s + a / q + " " + Math.abs(a) % q + "/" + q;//оптимизировать
        }
            return s;
    }
}









//вернуть число с целой частью