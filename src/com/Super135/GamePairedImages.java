package com.Super135;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePairedImages extends JFrame {

    private static GamePairedImages game_window;
    private static Image background;
    private static final String[] imgsFinal = {"src/com/Super135/Animals/bear.png","src/com/Super135/Animals/cat.png","src/com/Super135/Animals/dog.png",
            "src/com/Super135/Animals/elephant.png","src/com/Super135/Animals/fox.png", "src/com/Super135/Animals/giraffe.png",
            "src/com/Super135/Animals/lion.png", "src/com/Super135/Animals/mouse.png", "src/com/Super135/Animals/rabbit.png"};
    public static void main(String[] args) throws IOException {
//        background = ImageIO.read(GamePairedImages.class.getResourceAsStream("background.png"));
        game_window = new GamePairedImages();
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_window.setLocation(400,200);
        game_window.setSize(856,906);
        JPanel panelLeft = new JPanel(new BorderLayout());
        panelLeft.add(new JButton("Супер игра начинается"));//
        game_window.add(panelLeft,BorderLayout.WEST);
        int r = 3, c = 2;
        RandomPlayingField(r*c);
        JButton[][] buttons = new JButton[6][5];
        JPanel panelRight = new JPanel(new GridLayout(r,c));
        ImageIcon imageIcon = new ImageIcon("src/com/Super135/Animals/dog.png");
//        System.out.println(imageIcon);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setIcon(imageIcon);
                panelRight.add(buttons[i][j]); //
            }
        }
        game_window.add(panelRight,BorderLayout.CENTER);
        game_window.setResizable(false);
        game_window.setVisible(true);

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
//        for (int j = 0; j <imags.length ; j++) {
//            System.out.println(imags[j]);
//        }
        return imags;
    }

    private static class GameField extends JPanel {
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
//            g.drawImage(background,0,0,null);
        }
    }
}
