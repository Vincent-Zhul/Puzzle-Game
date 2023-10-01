package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    //Create a double dimension array to store the disorganized numbers
    int[][] picNum2D = new int[4][4];
    int[][] win = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    //Define two variables to store the position of the blank picture
    int x = 0;
    int y = 0;

    //Create a variable to count the number of the steps
    int step = 0;

    //Create a path to store the path of the image
    String path = "image/animal/animal3/";


    //Create the sub-options of the "Function" option
    JMenuItem beautyItem = new JMenuItem("Beauty");
    JMenuItem animalItem = new JMenuItem("Animal");
    JMenuItem sportItem = new JMenuItem("Sport");
    JMenuItem replayItem = new JMenuItem("Replay");
    JMenuItem reLoginItem = new JMenuItem("Re-login");
    JMenuItem exitItem = new JMenuItem("Exit");
    JMenuItem authorItem = new JMenuItem("About the author");

    //create the Game JFrame
    public GameJFrame() {
        //Create the window
        initJFrame();

        //Create the menu
        initJMenuBar();

        //Disorganize the order of the pictures
        initDisorganize();

        //Add pictures to the window
        initImage();


        //Set the layout of the window
        this.setVisible(true);
    }

    public boolean isWin() {
        for(int i = 0; i < picNum2D.length; i++) {
            for(int j = 0; j < picNum2D[i].length; j++) {
                if(picNum2D[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    private void initDisorganize() {
        //Create an array to store the numbers of the pictures
        int[] picNum = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //Disorganize the order of the numbers
        Random r = new Random();
        for (int i = 0; i < picNum.length; i++) {
            //Generate a random number between 0 and 14
            int randomIndex = r.nextInt(picNum.length);
            //Swap the number at index i with the number at index randomIndex
            int temp = picNum[i];
            picNum[i] = picNum[randomIndex];
            picNum[randomIndex] = temp;
        }

        //Convert the 1D array to 2D array
        for (int i = 0; i < picNum.length; i++) {
            if(picNum[i] == 0) {

                x = i / 4;
                y = i % 4;
            }
            picNum2D[i / 4][i % 4] = picNum[i];
        }
    }

    private void initImage() {
        //Remove all the components from the window
        this.getContentPane().removeAll();

        //Judge whether the player wins
        if(isWin()) {
            JLabel winJLabel = new JLabel(new ImageIcon("image/win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        //Create a JLabel to show the number of the steps
        JLabel stepJLabel = new JLabel("Step: " + step);
        stepJLabel.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepJLabel);

        //Create 16 JLabels to hold the 16 images
        //The outer loop is for the rows, and the inner loop is for the columns
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //Get the number of the picture (获取图片的编号)
                int picNum = picNum2D[i][j];
                //Create a JLabel to hold the image (创建一个JLabel作为管理容器来存放图片)
                JLabel jLabel = new JLabel(new ImageIcon(path + picNum + ".jpg"));
                //Set the position of the JLabel (设置JLabel的位置)
                jLabel.setBounds(j * 105 + 83, i * 105 + 134, 105, 105);
                //Set the border of the JLabel (设置JLabel的边框)
                jLabel.setBorder(new BevelBorder((BevelBorder.RAISED))); //Set the border raised(设置凸起边框), lowered(设置凹陷边框)
                //Add the JLabel to the window (将JLabel添加到窗口中)
                this.getContentPane().add(jLabel);
            }
        }

        //The prior added picture will be at the top, so we need to add the background image at the end
        //Load the background image
        JLabel bgLabel = new JLabel(new ImageIcon("image/background.png"));
        //Set the position of the JLabel (设置JLabel的位置)
        bgLabel.setBounds(40, 40, 508, 560);
        //Add the JLabel to the window (将JLabel添加到窗口中)
        this.getContentPane().add(bgLabel);

        //Refresh the window
        this.getContentPane().repaint();
    }

    private void initJMenuBar() {
        //Create the whole menu bar
        JMenuBar menuBar = new JMenuBar();

        //Create two options
        JMenu FunctionJMenu = new JMenu("Function");
        JMenu AboutJMenu = new JMenu("About");
        JMenu changePicJMenu = new JMenu("Change Picture");

        //Add the sub-options to the "Change Picture" option
        changePicJMenu.add(beautyItem);
        changePicJMenu.add(animalItem);
        changePicJMenu.add(sportItem);

        //Add the sub-options to the "Function" option
        FunctionJMenu.add(changePicJMenu);
        FunctionJMenu.add(replayItem);
        FunctionJMenu.add(reLoginItem);
        FunctionJMenu.add(exitItem);


        //Add the sub-options to the "About" option
        AboutJMenu.add(authorItem);

        //Add the action listener to the sub-options
        beautyItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        exitItem.addActionListener(this);
        authorItem.addActionListener(this);

        //Add the options to the menu bar
        menuBar.add(FunctionJMenu);
        menuBar.add(AboutJMenu);

        //Add the menu bar to the window
        this.setJMenuBar(menuBar);
    }

    private void initJFrame() {
        //Set the size of the window
        this.setSize(603, 680);

        //Set the title of the window
        this.setTitle("Puzzle Game Standalone v1.0");
        this.setAlwaysOnTop(true); //Set the window to always on top(界面置顶)
        this.setLocationRelativeTo(null); //Set the window to the center of the screen(界面居中)

        //Set the layout of the window
        this.setLayout(null);

        //Set the default close operation of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit the program when the window is closed(关闭窗口时退出程序)

        //Add the key listener to the window
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode(); //Get the key code
        if(keyCode == 65){ //65: A
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            JLabel bgLabel = new JLabel(new ImageIcon("image/background.png"));
            //Set the position of the JLabel (设置JLabel的位置)
            bgLabel.setBounds(40, 40, 508, 560);
            //Add the JLabel to the window (将JLabel添加到窗口中)
            this.getContentPane().add(bgLabel);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //When the player wins, the game is over
        if(isWin()) {
            return;
        }
        //Get the key code
        //37: left arrow, 38: up arrow, 39: right arrow, 40: down arrow
        int keyCode = e.getKeyCode();
        switch(keyCode){
            case 37: //left arrow
                if(y < 3) {
                    picNum2D[x][y] = picNum2D[x][y + 1];
                    picNum2D[x][y + 1] = 0;
                    y++;
                    //Increase the number of the steps
                    step++;
                }
                break;
            case 38: //up arrow
                if(x < 3) {
                    picNum2D[x][y] = picNum2D[x + 1][y];
                    picNum2D[x + 1][y] = 0;
                    x++;
                    //Increase the number of the steps
                    step++;
                }
                break;
            case 39: //right arrow
                if(y > 0) {
                    picNum2D[x][y] = picNum2D[x][y - 1];
                    picNum2D[x][y - 1] = 0;
                    y--;
                    //Increase the number of the steps
                    step++;
                }
                break;
            case 40: //down arrow
                if(x > 0) {
                    picNum2D[x][y] = picNum2D[x - 1][y];
                    picNum2D[x - 1][y] = 0;
                    x--;
                    //Increase the number of the steps
                    step++;
                }
                break;
            case 87: { //87: W -----> 通关
                picNum2D = new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 0}
                };
                break;
            }
            case 65: break;
        }
        initImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == replayItem) {
            //Reset the number of the steps
            step = 0;
            //Disorganize the order of the pictures
            initDisorganize();
            //Add pictures to the window
            initImage();
        } else if(source == reLoginItem) {
            //Close the current window
            this.dispose();
            //Open the login window
            new LoginJFrame();
        } else if(source == exitItem) {
            //Exit the program
            System.exit(0);
        } else if(source == authorItem) {
            //Show the author information
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("image/Profile.png"));
            jLabel.setBounds(0, 0, 330, 330);
            jDialog.add(jLabel);
            jDialog.setSize(400, 400);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);//Set the window to modal(模态),
            // which means that the user cannot interact with other windows until the user closes the current window
            jDialog.setVisible(true);
        } else if(source == beautyItem) {
            step = 0;
            int[] fileNum = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
            Random r = new Random();
            int randomIndex = r.nextInt(fileNum.length);
            path = "image/girl/girl" + fileNum[randomIndex] + "/";
            initDisorganize();
            initImage();
        } else if(source == animalItem) {
            step = 0;
            int[] fileNum = {1, 2, 3, 4, 5, 6, 7, 8};
            Random r = new Random();
            int randomIndex = r.nextInt(fileNum.length);
            path = "image/animal/animal" + fileNum[randomIndex] + "/";
            initDisorganize();
            initImage();
        } else if(source == sportItem) {
            step = 0;
            int[] fileNum = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            Random r = new Random();
            int randomIndex = r.nextInt(fileNum.length);
            path = "image/sport/sport" + fileNum[randomIndex] + "/";
            initDisorganize();
            initImage();
        }
    }
}
