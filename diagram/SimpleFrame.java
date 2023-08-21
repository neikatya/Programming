import javax.swing.*;
import java.awt.*;
class SimpleFrame extends JFrame {
    int fheight;    //высота окна
    int fwidth;    //ширина окна
    public SimpleFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit(); //получение размеров графического экрана
        Dimension screenSize = kit.getScreenSize();
        fheight = screenSize.height/3;
        fwidth = screenSize.width/3;
        setTitle("Диаграмма оценок");
        setSize(fwidth, fheight);     //установка размеров окна
        DrawComponent comp = new DrawComponent();
        add(comp);
        setLocation(5* fwidth / 2, 5 * fheight / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        }
        int getStep(Integer value, int total) {
        return (360 * value) / total;
    }
}