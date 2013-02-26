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
import lib_admin.BookTitleTree;
import sun.awt.HorizBagLayout;

/**
 *
 * @author rekha_c
 */
public class AdminLogin extends JFrame
{
    private JButton add,search,display;
    private JPanel p1,p2,p3;
    private final BookTitleTree bt;
    public static boolean submit = false;
    public AdminLogin(final BookTitleTree bt)
    {
        this.bt = bt;
        p1 = new JPanel();
        p1.setLayout(new FlowLayout(100));
        p1.add(new JLabel("Add new Titles"));
        add = new JButton("Add Title/Copy");
        p1.add(add);
       p2 = new JPanel();
        p2.setLayout(new FlowLayout(100));
        p2.add(new JLabel("Search Inventory"));
        search = new JButton("Search");
        p2.add(search);
        p3 = new JPanel();
        p3.setLayout(new FlowLayout(100));
        p3.add(new JLabel("Displaying all titles in Library"));
        display = new JButton("Display");
        p3.add(display);
        add(p1,BorderLayout.EAST);
        add(p2,BorderLayout.CENTER);
        add(p3,BorderLayout.SOUTH);
        add.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                bt.titleformframecreate(bt.formtitlecreate());
                /*if(submit == true)
                    bt.titleins();
                submit = false;*/
            }
        });
        search.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                bt.searchcreator();;
            }
        });
        display.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
                    bt.displaycreator();
                    bt.inorder(bt.root);
                    
                
            }
        });
    }
    public void display(final BookTitleTree bt,LibraryStart main)
    {
        AdminLogin frame = new AdminLogin(bt);
        frame.pack();
        frame.setTitle("Admin Interface");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(close(main));
        frame.setVisible(true);

    }
    private int close(LibraryStart main)
    {
        main.setVisible(true);
        return 1;
    }


}
