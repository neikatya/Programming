import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
class DrawComponent extends JComponent {
    private int three = 5;       //количество троек
    private int four = 23;       //количество четвёрок
    private int five = 8;        //количество пятёрок
    public void paintComponent(Graphics g) {
        SimpleFrame frame = new SimpleFrame();
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D r;
        int start = 0;
        int total = three + four + five;     //всего оценок
        int d =frame.fheight-37;      //диаметр диаграммы
        int size = frame.fheight/24;       //размер стороны квадрата
        g2.setFont(new Font("TimesRoman", Font.BOLD, 2*size));   //шрифт
        g2.translate((frame.fwidth-13)/2, (frame.fheight-37)/2);    //делаем начало координат в середине окна

        int step = frame.getStep(three, total);
        g2.setColor(Color.BLUE);
        g2.fillArc(-d/2, -d/2, d, d, start, step);          //рисуем сектор
        start += step;         //сдвигаем чтобы рисовать след. дугу
        step = frame.getStep(four, total);
        g2.setColor(Color.GREEN);
        g2.fillArc(-d/2, -d/2, d, d, start, step);
        start += step;
        g2.setColor(Color.RED);
        g2.fillArc(-d/2, -d/2, d, d, start, 360-start);

        r = new Rectangle2D.Double(3*d/4, -d/4, size , size );   //создаём квадрат
        g2.setColor(Color.BLUE);
        g2.fill(r);           //устанавливаем квадрату цвет
        g2.draw(r);           //рисуем квадрат
        g2.drawString(" - 3", 3*d/4+2*size, -d/4+size);

        r = new Rectangle2D.Double(3*d/4, 0, size, size);
        g2.setColor(Color.GREEN);
        g2.fill(r);
        g2.draw(r);
        g2.drawString(" - 4", 3*d/4+2*size, size);

        r = new Rectangle2D.Double(3*d/4, d/4, size, size);
        g2.setColor(Color.RED);
        g2.fill(r);
        g2.draw(r);
        g2.drawString(" - 5", 3*d/4+2*size, d/4+size);
    }
}
