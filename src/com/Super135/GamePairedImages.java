package com.Super135;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePairedImages extends JFrame {

    private static final String[] imgsFinal = {"src/com/Super135/Animals/bear.jpg","src/com/Super135/Animals/cat.jpg","src/com/Super135/Animals/dog.jpg",
            "src/com/Super135/Animals/elephant.jpg","src/com/Super135/Animals/fox.jpg", "src/com/Super135/Animals/giraffe.jpg",
            "src/com/Super135/Animals/lion.jpg", "src/com/Super135/Animals/mouse.jpg", "src/com/Super135/Animals/rabbit.jpg"};
    private static JButton copy1 = null;
    private static JButton copy2 = null;
    private static Boolean Flag = false;
    private static int cnt = 0;
    private int row = 0,col = 0;

    public JPanel getPanelGrid(int row, int col){
        JPanel panel = new JPanel(new GridLayout(row,col));
        return panel;
    }

    public GamePairedImages(int level) throws HeadlessException {

        this.setTitle("Игра парные картинки  -->  Уровень "+level);
        if (level == 1) {row = 2;col = 2;}
        else if(level == 2) {row = 3; col = 2;}
        else if(level == 3) {row = 4; col = 3;}
        else if(level == 4) {row = 4; col = 4;}

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(500,100);
        setSize(806,806);
        setResizable(false);
        int k = 0;
        cnt = 0;
        String[] rpf = RandomPlayingField(row*col);
        JButton[][] buttons = new JButton[row][col];
        JPanel panel = new JPanel(new GridLayout(row,col));
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ImageIcon imageIcon = new ImageIcon(rpf[k]);
                k++;
                buttons[i][j] = new JButton();
                JButton copy = buttons[i][j];
                // Тут конечно костыль
                copy.addActionListener(action->{
                    if (Flag && copy1 != null && copy2 != null) {
                        copy2.setIcon(null);
                        copy2.setEnabled(true);
                        copy2.setDisabledIcon(null);
                        copy1.setIcon(null);
                        copy1.setEnabled(true);
                        copy1.setDisabledIcon(null);
                        copy1 = null;
                        copy2 = null;
                    }
                    copy.setIcon(imageIcon);
                    copy.setEnabled(false);
                    copy.setDisabledIcon(imageIcon);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (copy1 != null) {
                            if (copy.getIcon().toString() != copy1.getIcon().toString()){
                                Flag = true;
                                copy2 = copy;
                            } else {
                                copy1 = null;
                                copy2 = null;
                                cnt +=2;
                            }
                        } else {
                            copy1 = copy;
                        }
                    if (cnt == row*col){
                        JFrame alert = new JFrame("Ура у тебя получилось.");
                        alert.setLocation(700,400);
                        alert.setSize(350,100);
                        JPanel alertPanel = new JPanel(new FlowLayout());
                        JButton newGamme = new JButton("Уровень "+(level+1));
                        JButton close = new JButton("Закончить");
                        if (level != 4) {
                            newGamme.addActionListener(a -> {
                                this.dispose();
                                new GamePairedImages(level + 1);
                                alert.dispose();
                            });
                            close.addActionListener(a->{
                                this.dispose();
                                alert.dispose();
                            });
                            alertPanel.add(newGamme);
                            alertPanel.add(close);
                        } else {
                            close.addActionListener(a->{
                                this.dispose();
                                alert.dispose();
                            });
                            alertPanel.add(close);
                        }
                        alert.add(alertPanel);
                        alert.setResizable(false);
                        alert.setVisible(true);
                    }
                });
                panel.add(buttons[i][j]);
            }
        }
        add(panel);
        setVisible(true);

    }

    public static void main(String[] args) throws IOException {

        new GamePairedImages(1);
    }

    public static String[] RandomPlayingField(int count){
        String[] imags = new String[count];
        String[] copyimgsFinal = imgsFinal;
        int i = 0;
        int r;
        int c = 0;
        while (i != count-1) {
            if (imags[i] == null){
                imags[i] = copyimgsFinal[c];
                c++;
                boolean end = true;
                if (i != count-1){
                    while (end) {
                        r = (int) (Math.random() * (count));
                        if (imags[r] == null){
                            imags[r] = imags[i];
                            end = false;
                        }
                    }
                }
            }
            i++;
        }
        //Для проверки
        for (int j = 0; j <imags.length ; j++) {
            System.out.println(imags[j]);
        }
        return imags;
    }
}
