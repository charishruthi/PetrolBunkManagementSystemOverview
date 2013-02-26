/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Swings;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lib_admin.BookTitleTree;
import library_management.user.UserBorrow;

/**
 *
 * @author Shrinidhi
 */
public class UserLogin extends JFrame
{
    private JPanel p1;
    private JTextField username;
    private JLabel user_n;
    private JButton sign_in;
     private final UserBorrow ub;
     public static String name;
    private UserLogin frame;
    private boolean hider;
     public UserLogin(final UserBorrow ub)
    {
         this.ub = ub;
         p1 = new JPanel();
         p1.setLayout(new FlowLayout(100));
         username = new JTextField(50);
         user_n = new JLabel("Enter username");
         sign_in = new JButton("Sign In");
         p1.add(user_n);
         p1.add(username);         
         p1.add(sign_in);
         add(p1,BorderLayout.CENTER);
         sign_in.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                name = username.getText();
                hider = true;
                ub.titleformframecreate(ub.formusercreate()); 
               
                //ub.titleformframecreate(ub.useroptionscreator());
            }
        });

     }
     public void display(final UserBorrow ub,LibraryStart main)
    {
           frame = new UserLogin(ub);
        frame.pack();
        frame.setTitle("User Interface");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(close(main));
        frame.setVisible(true);
        if(hider == true)
        {
            frame.setVisible(false);
        }

    }
    private int close(LibraryStart main)
    {
        main.setVisible(true);
        return 1;
    }

}
