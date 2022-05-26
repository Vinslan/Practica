import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class JMenuTest extends JFrame
{
    public JMenuTest()
    {
        super("Тестовое меню");
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createViewMenu());
        add(new TestPane());
        setJMenuBar(menuBar);
        setSize(400, 400);
        setVisible(true);
    }


    JButton b1 = new JButton("добавить столбец");// создание кнопки
    JButton b2 = new JButton("добавить ccccстолбец");// создание кнопки

    int sum = 5;
    private JMenu createViewMenu()
    {
        JMenu viewMenu = new JMenu("Действия");

        viewMenu.add(b1);// добавление кнопки к бару
        viewMenu.add(b2);// добавление кнопки к бару

        b1.addActionListener(new ButtonListener());

        viewMenu.add( new JSeparator());// линия в баре

        b2.addActionListener(new ButtonListener());
        return viewMenu;

    }

    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==b1) {sum=sum+1;  System.out.println(sum);}
            if(e.getSource()==b2) {sum=sum+5;  System.out.println(sum);}
        }

    }

  
    public class TestPane extends JPanel {

        public TestPane() {
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    gbc.gridx = col;
                    gbc.gridy = row;

                    CellPane cellPane = new CellPane();
                    Border border = null;
                    if (row < 4) {
                        if (col < 4) {
                            border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                        }
                    } else {
                        if (col < 4) {
                            border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                        }
                    }
                    cellPane.setBorder(border);
                    add(cellPane, gbc);
                }
            }
        }
    }

    public class CellPane extends JPanel {

        private Color defaultBackground;

        public CellPane() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    defaultBackground = getBackground();
                    setBackground(Color.BLUE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(defaultBackground);
                }
            });
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(50, 50);
        }
    }

    public static void main(String[] args)  // стилизация
    {
        JFrame.setDefaultLookAndFeelDecorated(false);
        new JMenuTest();
    }

}

