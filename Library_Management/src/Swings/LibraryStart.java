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
import library_management.user.User_Simulate;
import library_management.user.User_Simulate;
import sun.awt.HorizBagLayout;
import sun.awt.VerticalBagLayout;

/**
 *
 * @author rekha_c
 */
public class LibraryStart extends JFrame
{
        private JButton Admin_Login,User_Login;
        private JPanel p1,p2;
        User_Simulate user = new User_Simulate();
        static LibraryStart frame = new LibraryStart();        
        AdminLogin admin;
        UserLogin ulogin;
    private final JButton Save;
        public LibraryStart()
        {
             user.update();
             admin = new AdminLogin(user.title_tree);
             ulogin = new UserLogin(user.ub);
             p1 = new JPanel();
            p1.setLayout(new FlowLayout(100));
            p1.add(new JLabel("Click on the button to login as Admin"));
            Admin_Login = new JButton("Admin Login");
            p1.add(Admin_Login);
             p2 = new JPanel();
            p2.setLayout(new FlowLayout(100));
            p2.add(new JLabel("Click on the button to login as a Guest/Member"));
            User_Login =new JButton("User Login");
            Save = new JButton("Save and Exit");
            p2.add(User_Login);
            p2.add(Save);
            add(p1,BorderLayout.CENTER);
            add(p2,BorderLayout.SOUTH);

            Admin_Login.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                admin.display(user.title_tree, frame);
            }
        });
        User_Login.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ulogin.display(user.ub, frame);
            }
        });
        Save.addActionListener((new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    user.save();
                    frame.setVisible(false);
                }
            }));
            

        }
        public static void main(String[] args)
        {
            
            frame.pack();
            frame.setTitle("Library Automator");
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

}
