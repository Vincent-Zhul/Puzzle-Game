package ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    public RegisterJFrame() {
        //Set the size of the window
        this.setSize(488, 500);
        this.setVisible(true);

        //Set the title of the window
        this.setTitle("User Register - Puzzle Game Standalone v1.0");
        this.setAlwaysOnTop(true); //Set the window to always on top(界面置顶)
        this.setLocationRelativeTo(null); //Set the window to the center of the screen(界面居中)

        //Set the default close operation of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit the program when the window is closed(关闭窗口时退出程序)

        //Set the layout of the window
        this.setVisible(true);
    }
}
