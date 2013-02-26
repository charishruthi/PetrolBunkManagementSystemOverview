/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library_management.user;

import Swings.UserLogin;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import lib_admin.BookTitleTree;
import lib_admin.Book;
import lib_admin.Title;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.Container;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Shrinidhi
 */
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//used for borrowing.
public class UserBorrow implements Serializable {

    User firstUser, existing_customer;//firstUser contains the list of users.Existing_customer checks if a cus. exists
    String book_title, customer_name;//book_title contains the title name.
    BookTitleTree bt;//In user simulate you are passing the original tree as the parameter. you accept is here as bt.
    Scanner in = new Scanner(System.in);
    int book_no_borrowed;
    private Book last_occ;
    private int num;
    public JFrame frame;
    private JTextField nameprint;
    private JPanel p1;
    private JButton done;
    private JLabel name;
    private User new_user;
    private JButton borrow;
    private JButton returnb;
    private JButton displaybooks;
    private JTextField title_enter;
    private JButton check_availability;
    private JTextField status_print;
    private JTextArea dispborrow;
    private JButton back;
    public static Title present_in_tree;
    public UserBorrow(BookTitleTree bt) {
        this.firstUser = null;
        this.bt = bt;
    }
    public void titleformframecreate(JPanel p)
    {
            JFrame.setDefaultLookAndFeelDecorated(true);
            frame = new JFrame("User Borrower");
            frame.setDefaultCloseOperation(1);
            frame.setLocationRelativeTo(null);
            frame.setSize(500, 500);
            frame.add(p);
            frame.setVisible(true);

    }
    public JPanel formusercreate()
    {
        p1 = new JPanel();
        p1.setLayout(new FlowLayout(100));
        name = new JLabel("Username is");
        nameprint = new JTextField(50);
        nameprint.setEditable(false);
        nameprint.setText(UserLogin.name);
        p1.add(name);
        p1.add(nameprint);
        done = new JButton("Done");
        p1.add(done);
        p1.setSize(100,100);
        p1.setAlignmentX(50);
        p1.setAlignmentY(100);
        done.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(search_name(UserLogin.name) != null)
                    p1.add(new JLabel("Ëxisting customer"));
                    frame.setVisible(false);
                    endins(UserLogin.name);
                    useroptionscreator();
                    
            }
        });        
        return p1;
    }
    public User account_create(String name)//In client class you call borrow. borrow checks if you account exists.
    {
        
                if(search_name(name) == null)
                {
                    new_user = new User(name);
                }
                else
                {
                    new_user = search_name(name);
                }


                
        existing_customer = new_user;
        return existing_customer;

    }

    public void endins(String name)//client calls borrow. borrow calls login_mem
    {
        User working_on = account_create(name);
        if (firstUser == null) {
            firstUser = working_on;
        } else {
            User temp = firstUser;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = working_on;
        }
        

    }

    public void populateList(User user) {
        User user1 = (User) user.clone();
        user1.next = null;
        System.out.println("in populateList");
        if (firstUser == null) {
            firstUser = user1;
        } else {
            User temp = firstUser;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = user1;
        }

    }

    public User search_name(String name) {
        User add = firstUser;
        if (firstUser != null) {
            if (add.user_name.compareTo(name) == 0) {
                return add;
            } else {
                while ((add != null) && (add.user_name.compareTo(name) != 0)) {
                    add = add.next;
                }
                if (add != null && add.user_name.compareTo(name) == 0) {
                    return add;
                }
            }
        }
        return null;

    }
    public void useroptionscreator()
    {
        p1 = new JPanel();
        p1.setLayout(new FlowLayout(100));
        borrow = new JButton("Borrow");
        returnb = new JButton("Return");
        displaybooks = new JButton("Display");
        back = new JButton("Back");
        p1.add(borrow);
        p1.add(returnb);
        p1.add(displaybooks);
        p1.add(back);
        p1.setSize(100,100);
        p1.setAlignmentX(50);
        p1.setAlignmentY(100);
        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        borrow.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                eborrowpanel();
                //frame.setVisible(false);
                
            }
        });
        returnb.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ereturnpanel();
                //frame.setVisible(false);
            }
        });
        displaybooks.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                edispbookspanel();
                display();
                //frame.setVisible(false);
            }
        });
        titleformframecreate(p1);
    }
    public void edispbookspanel()
    {
        p1 = new JPanel();
        p1.setLayout(new FlowLayout(100));
        dispborrow = new JTextArea(5, 5);
        dispborrow.setEditable(false);
        dispborrow.setAutoscrolls(true);
        p1.add(new JLabel("List of borrowed books are"));
        p1.add(dispborrow);
        back = new JButton("Back");
        p1.add(back);
        p1.setSize(100,100);
        p1.setAlignmentX(50);
        p1.setAlignmentY(40);
        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        
        titleformframecreate(p1);
    }
    public void ereturnpanel()
    {
        p1 = new JPanel();
        p1.setLayout(new FlowLayout(100));
        JLabel title_label = new JLabel("Enter name of book to return");
        JButton back = new JButton("Back");
        title_enter = new JTextField(50);
        check_availability = new JButton("Return");
        status_print = new JTextField(50);
        status_print.setEditable(false);        
        p1.add(title_label);
        p1.add(title_enter);
        p1.add(check_availability);
        p1.add(status_print);
        p1.add(back);
        p1.setSize(100, 100);
        p1.setAlignmentX(50);
        p1.setAlignmentY(100);
        check_availability.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
                book_title = title_enter.getText();
                return_book();
                
                 
                
            }
        });
        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        titleformframecreate(p1);
        
    }
    public void eborrowpanel()
    {
        p1 = new JPanel();
        p1.setLayout(new FlowLayout(100));
        JLabel title_label = new JLabel("Enter title");
        JButton back = new JButton("Back");
        title_enter = new JTextField(50);
        check_availability = new JButton("Check available");
        status_print = new JTextField(50);
        status_print.setEditable(false);        
        p1.add(title_label);
        p1.add(title_enter);
        p1.add(check_availability);
        p1.add(status_print);
        p1.add(back);
        p1.setSize(100, 100);
        p1.setAlignmentX(50);
        p1.setAlignmentY(100);
        check_availability.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
                book_title = title_enter.getText();
                borrow();
                
                 
                
            }
        });
        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        titleformframecreate(p1);
        
                
    }
    public void borrow() {
        /*System.out.println("Enter 1. Guest \n 2. Member");
        int choice = in.nextInt();*/
        
        
        
        if (bt.root == null) 
        {//no book in library
            status_print.setText("Book not available");
        }
        /*else if(bt.root.title.equals(book_title))
        {
        if(bt.root.first.borrowed==true)
        {//when all the books are borrowed , first's borrow flag will be set to true .
        System.out.println("Book not available");
        }
        else
        {
        Book temp= bt.root.first.prev;//last book check with it
        if(temp == bt.root.first)//only when 1 book copy available
        {
        if(temp.borrowed == false)
        {
        existing_customer.borrowed_book = temp;
        temp.borrowed = true;
        System.out.println("Your data of issue is: 1 we he");
        }
        else
        {
        System.out.println("The only copy of this book is borrowed");
        }
        }
        else
        {
        while(temp != bt.root.first && temp.borrowed == true)
        {
        temp = temp.prev;
        }
        existing_customer.borrowed_book = temp;
        temp.borrowed = true;

        }

        }
        }*/
        if (existing_customer.book_no_borrowed < 3) {
            
           bt.title_found = false;
           bt.search_for_borrow(book_title, bt.root);
           //present_in_tree = bt.ifTitleExists(book_title);//returns title position. if title doesnt exist it returns null.
            if (bt.title_found == true) {

                //if(present_in_tree.first.borrowed==true)
                if (present_in_tree.first.borrowed == true) {
                    status_print.setText("Book not available; all copies are borrowed");
                } else {
                    Book temp = present_in_tree.first;//last book check with it
                    if (temp.next == null)//chcecking for only one copy of the book
                    {

                        if (temp.borrowed == false) {
                            //existing_customer.book_no_borrowed++
                            for (int i = 0; i < 3; i++) {
                                if (existing_customer.borrowed_book[i] == null) {
                                    existing_customer.borrowed_book[i] = temp;
                                    temp.borrowed = true;
                                    present_in_tree.no_of_books_available--;
                                    existing_customer.book_no_borrowed++;
                                    status_print.setText("Your book has been issued");
                                    break;
                                } else {
                                }
                            }
                        }

                        /* else
                        {
                        System.out.println("The only copy of the book is borrowed");
                        }*/

                    } else {
                        //System.out.println("entering first else");
                        //present_in_tree.first instead of null
                        Book prev = null;
                        while ((temp.next != null) && (temp.borrowed != true)) {   //System.out.println("entering while");
                            prev = temp;
                            temp = temp.next;//going back to front
                        }
                        if (temp.borrowed == true) {   //System.out.println("finding first book thats not borrowed");
                            prev.borrowed = true;
                            //existing_customer.book_no_borrowed++
                            for (int i = 0; i < 3; i++) {
                                //System.out.println("entering for loop");
                                if (existing_customer.borrowed_book[i] == null) {
                                    existing_customer.borrowed_book[i] = prev;
                                    existing_customer.book_no_borrowed++;
                                    present_in_tree.no_of_books_available--;
                                    status_print.setText("Your book has been isued");
                                    break;
                                } else {  //System.out.println("entering else condition");
                                }

                            }
                        }
                        if (temp.next == null && temp.borrowed != true)//exception case: checks for availability of last copy
                        {
                            /*if(temp.borrowed == true)
                            {
                            prev.borrowed = true;
                            existing_customer.borrowed_book = prev;
                            present_in_tree.no_of_books_available--;
                            System.out.println("Your data of issue of the second last copy is: he he");
                            }
                            else*/

                            temp.borrowed = true;
                            /*existing_customer.borrowed_book[existing_customer.book_no_borrowed++] = temp;
                            present_in_tree.no_of_books_available--;
                            System.out.println("Your data of issue of the last copy is: <date of issue>");*/
                            for (int i = 0; i < 3; i++) {
                                //System.out.println("entering for loop");
                                if (existing_customer.borrowed_book[i] == null) {
                                    existing_customer.borrowed_book[i] = temp;
                                    existing_customer.book_no_borrowed++;
                                    present_in_tree.no_of_books_available--;
                                    status_print.setText("Your book has been issued");
                                    break;
                                } else {  //System.out.println("entering else condition");
                                }

                            }

                        }



                    }
                }



            }
            else
            {
                status_print.setText("Tilte not found!!");
            }
        } else {
            status_print.setText("Please return your books first!");
        }
        
    


    }

    public void display() {
        //System.out.println("Enter customer name:");
        //String input_name = in.nextLine();
        User check_existing_user = existing_customer;
        if (check_existing_user != null) {
            int i = 0;
            if (check_existing_user.book_no_borrowed == 0) {
                dispborrow.append("No books borrowed\n");
            } else {//i <= (check_existing_user.book_no_borrowed - 1) &&
                while (i < 3) { //System.out.println("entering while in display");
                    if (check_existing_user.borrowed_book[i] == null) {
                    } else {
                        dispborrow.append(check_existing_user.borrowed_book[i].book_name + "\n");
                    }
                    i++;
                }

            }
        } else {
            dispborrow.append("Unidentified user :(");
        }
    }

    public void return_book() {
        //System.out.println("Enter your name:");
        //String returning_user = in.nextLine();
        User check_user_for_return = existing_customer;
        if (check_user_for_return != null) {
            //System.out.println("Existing User");
            //System.out.println("Enter the name of the book you want to return:");
            String return_book_title = book_title;
            boolean flag = false;
            //check_user_for_return.book_no_borrowed
            for (int i = 0; i < 3; i++) {
                if (check_user_for_return.borrowed_book[i] == null) {
                } else {
                    if (check_user_for_return.borrowed_book[i].book_name.equals(return_book_title)) {
                        last_occ = check_user_for_return.borrowed_book[i];
                        num = i;
                        flag = true;
                    }
                    /* check_user_for_return.borrowed_book[i].borrowed=false;
                    check_user_for_return.borrowed_book[i]=null;
                    check_user_for_return.book_no_borrowed--;
                    flag=true;
                    break;
                    }*/
                }
            }
            if (flag == true) {
                last_occ.borrowed = false;
                check_user_for_return.borrowed_book[num] = null;
                check_user_for_return.book_no_borrowed--;
                status_print.setText("Your book has been returned");
                //flag=true;
            }
            //break;
            if (flag == false) {
                status_print.setText("Book not borrowed by user");
            }
        } else {
            status_print.setText("Account Mismatch");
        }

    }

    public void displayUsers() {
        if (firstUser == null) {
            System.out.println("No members!");
        } else {
            User temp = firstUser;
            int i = 1;
            while (temp != null) {
                System.out.println((i++) + ". " + temp.user_name);
                temp = temp.next;
            }
        }
    }
}
