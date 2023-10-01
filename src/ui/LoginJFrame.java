package ui;
import domain.User;
import util.CodeUtil;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements MouseListener {

    static ArrayList<User> allUsers = new ArrayList<>();
    static {
        allUsers.add(new User("Vincent","123456"));
        allUsers.add(new User("Christina","33445566"));
    }

    String path = "image/login/";


    JButton login = new JButton();
    JButton register = new JButton();

    JTextField username = new JTextField();
    //JTextField password = new JTextField();
    JPasswordField password = new JPasswordField();
    JTextField code = new JTextField();

    //正确的验证码
    JLabel rightCode = new JLabel();


    public LoginJFrame() {
        //Initialize the JFrame
        initJFrame();

        //Add components to the JFrame
        initView();


        //Set the layout of the window
        this.setVisible(true);
    }

    public void initView() {
        //1. Add the username notice
        JLabel usernameText = new JLabel(new ImageIcon(path + "User Name.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

        //2.Add the username input box

        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        //3.Add the password notice
        JLabel passwordText = new JLabel(new ImageIcon(path + "Password.png"));
        passwordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordText);

        //4.Add the password input box
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);


        //Verify code notice
        JLabel codeText = new JLabel(new ImageIcon(path + "Verification Code.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        //Verify code input box
        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);


        String codeStr = CodeUtil.getCode();
        //Set Content
        rightCode.setText(codeStr);
        //Set mouse listener
        rightCode.addMouseListener(this);
        //Set the position and size
        rightCode.setBounds(300, 256, 50, 30);
        //Add to the JFrame
        this.getContentPane().add(rightCode);

        //5.Add the login button
        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon(path + "Login Button.png"));
        //Remove the border of the button
        login.setBorderPainted(false);
        //Remove the background of the button
        login.setContentAreaFilled(false);
        //Bind the mouse event to the login button
        login.addMouseListener(this);
        this.getContentPane().add(login);

        //6.Add the register button
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon(path + "Sign-up Button.png"));
        //Remove the border of the button
        register.setBorderPainted(false);
        //Remove the background of the button
        register.setContentAreaFilled(false);
        //Bind the mouse event to the register button
        register.addMouseListener(this);
        this.getContentPane().add(register);


        //7.Add the background image
        JLabel background = new JLabel(new ImageIcon(path + "background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);

    }


    public void initJFrame() {
        this.setSize(488, 430);//Set the size of the window
        this.setTitle("Puzzle Game Standalone v1.0 Login");//Set the title of the window
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Set the default close operation of the window
        this.setLocationRelativeTo(null);//Set the window to the center of the screen
        this.setAlwaysOnTop(true);//Set the window to always on top
        this.setLayout(null);//Set the layout of the window
    }



    //点击
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == login) {
            //Obtain the username and password
            String usernameInput = username.getText();
            String passwordInput = password.getText();
            //Obtain the code
            String codeInput = code.getText();

            //Create a user object
            User userInfo = new User(usernameInput, passwordInput);

            if (codeInput.isEmpty()) {
                showJDialog("The code should not be empty");

            } else if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
                //Use the showJDialog method to show the dialog
                showJDialog("The username or password should not be empty");

            } else if (!codeInput.equalsIgnoreCase(rightCode.getText())) {
                showJDialog("The verification code is incorrect");
            } else if (contains(userInfo)) {
                System.out.println("Login success");
                //Set the current window to invisible
                this.setVisible(false);
                //Open the game window
                //Set the username and password to the game window
                new GameJFrame();
            } else {
                showJDialog("Username or password is incorrect");
            }
        } else if (e.getSource() == register) {
            showJDialog("The register function is not available currently");
        } else if (e.getSource() == rightCode) {
            //Refresh the code
            String code = CodeUtil.getCode();
            rightCode.setText(code);
        }
    }


    public void showJDialog(String content) {
        //Create a JDialog object
        JDialog jDialog = new JDialog();
        //Set the size of the JDialog
        jDialog.setSize(400, 200);
        //make the JDialog always on top
        jDialog.setAlwaysOnTop(true);
        //Make the JDialog to the center of the screen
        jDialog.setLocationRelativeTo(null);
        //Set the dialog to modal(模态)
        jDialog.setModal(true);

        //Create a JLabel object to organize the texts
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 400, 200);
        jDialog.getContentPane().add(warning);

        //Show the JDialog
        jDialog.setVisible(true);
    }

    //Press the button
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon(path + "Login Pressed.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon(path + "Sign-up Button Pressed.png"));
        }
    }


    //Release the button
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon(path + "Login Button.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon(path + "Sign-up Button.png"));
        }
    }

    //mouse enter
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    //mouse exit
    @Override
    public void mouseExited(MouseEvent e) {

    }

    //Judge whether the user exists
    public boolean contains(User userInput){
        for (int i = 0; i < allUsers.size(); i++) {
            User rightUser = allUsers.get(i);
            if(userInput.getUsername().equals(rightUser.getUsername()) && userInput.getPassword().equals(rightUser.getPassword())){
                //Have found the user
                return true;
            }
        }
        //Have not found the user
        return false;
    }
}