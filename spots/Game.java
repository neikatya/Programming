import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Game extends JFrame {
    private JPanel panel = new JPanel();
    private JButton[] pole = new JButton[16];
    private int empty;  //пустая клетка

    public Game() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension scrsize = kit.getScreenSize();
        int frameSize = scrsize.width / 5;
        setSize(frameSize, frameSize);
        setTitle("Пятнашки");
        panel.setLayout(new GridLayout(4, 4, 3, 3));
        panel.setBackground(new Color(255, 171, 231));
        createmenu();
        createpole();
        paintpole();
        add(panel);
        setLocationRelativeTo(panel);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_UP && empty <= 11) changeplace(empty + 4); //проверяем, не в нижней ли строке пустая клетка
                else if (key == KeyEvent.VK_DOWN && empty>=4 ) changeplace(empty - 4); //проверяем, не в верхней ли строке пустая клетка
                else if (key == KeyEvent.VK_RIGHT && empty % 4 != 0) changeplace(empty - 1); //проверяем, не в 1 ли столбце пустая клетка
                else if (key == KeyEvent.VK_LEFT && empty % 4 != 3)  changeplace(empty + 1);  //проверяем, не в 4 ли столбце пустая клетка
            }
        });
        setFocusable(true); //управление с помощью стрелок клавиатуры
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createmenu() {          //создаём меню
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("Файл");
        file.setDisplayedMnemonicIndex(0);

        JMenuItem newgame = new JMenuItem("Новая игра", KeyEvent.VK_N);
        newgame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        newgame.addActionListener(new NewMenuListener());
        file.add(newgame);

        JMenuItem exit = new JMenuItem("Выход", KeyEvent.VK_E);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        exit.addActionListener(new NewMenuListener());
        file.add(exit);

        JMenu help = new JMenu("Справка");
        JMenuItem author = new JMenuItem("Автор", KeyEvent.VK_A);
        author.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        author.addActionListener(new NewMenuListener());
        help.add(author);

        file.insertSeparator(1);
        menu.add(file);
        menu.add(help);
        setJMenuBar(menu);
    }

    private class NewMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if ("Выход".equals(command)) {
                System.exit(0);
            }
            else if ("Новая игра".equals(command)) {
                createpole();
                paintpole();
            }
            else if ("Автор".equals(command)) {
                JOptionPane.showMessageDialog(null, "Автор: Неизвестная Екатерина", "Автор", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }


    private void createpole() {
        List<Integer> list = new ArrayList<>(15);
        for (int i = 1; i <= 15; i++) list.add(i);
        Collections.shuffle(list);  //перемешиваем значения кнопок
        Integer[] array =  list.toArray(new Integer[15]);
        for (int i = 0; i <= 14; i++) {
            pole[i]=new JButton(Integer.toString(array[i]));
            pole[i].setActionCommand(Integer.toString(i));
            pole[i].addActionListener(new PressListener());
            pole[i].setFocusable(false);
            pole[i].setBackground(new Color(122, 10, 77));
            pole[i].setForeground(new Color(250, 210, 234));
            pole[i].setFont(new Font("Blackadder ITC", Font.BOLD, 35));
        }
        pole[15] = new JButton("0");
        pole[15].setActionCommand("15");
        pole[15].setActionCommand(Integer.toString(15));
        pole[15].addActionListener(new PressListener());
        pole[15].setFocusable(false);
        pole[15].setBackground(new Color(122, 10, 77));
        pole[15].setForeground(new Color(250, 210, 234));
        pole[15].setFont(new Font("Blackadder ITC", Font.BOLD, 35));
    }
    private void paintpole() {
        panel.removeAll();
        panel.repaint();
        empty = 15;
        for (int i=0; i<=15; i++) {
            panel.add(pole[i]);
        }
        pole[15].setVisible(false);
    }

    private void changeplace (int p) {        //смена положения кости
        pole[empty].setText(pole[p].getText());
        pole[empty].setVisible(true);
        pole[p].setVisible(false);
        empty = p;
        Win();
    }

    private class PressListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();     //источник события
            int p = Integer.parseInt(button.getActionCommand());
            if (Math.abs(empty - p) == 4 || (Math.abs(empty - p) == 1 && Math.abs(empty % 4 - p % 4) != 3)) changeplace(p);    //проверяем, есть ли рядом с костью пустое место и меняет местами
        }
    }

    private void Win() {
        for (int i=0; i<15; i++)
            if (Integer.parseInt(pole[i].getText()) != i+1) return;   //проверяем, где находится кость (на своем ли она месте)
        JOptionPane.showMessageDialog(null, "Вы выиграли!", "Ура", JOptionPane.INFORMATION_MESSAGE);
    }
}