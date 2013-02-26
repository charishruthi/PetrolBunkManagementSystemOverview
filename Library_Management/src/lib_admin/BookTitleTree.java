/*
changed as on 20.11.2012 by Swathi
1. changed "title' to "newtitle" in title_ins
2. changed "catagory" to "category" everywhere
3. renamed "search" to "ifTitleExists"
4. removed unique_code_set
5. modified wherever unique_code_set was used to use ifTitleExists only
6. removed variables "find" and "flag"
7. removed "existing_checker" method 
8. modified wherever existing_checker was used to use ifTitleExists only
*/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lib_admin;

import Swings.AdminLogin;
import lib_admin.Title;
import lib_admin.Book;
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
import library_management.user.UserBorrow;

/**
 *
 * @author shruthi
 */
public class BookTitleTree extends JFrame//this class creates your tree.
{
	public Title root;//points to first title in the tree
        public Title newTitle;//adds the existing title into your tree.
	public int adder = 0;
	public int unique_setter; //initially 0000. stores code for every title. increments by 1000 for every new title.
        public int find = 0;
       // String title;//takes the title as the input in create class
  // int add = 0;
  //boolean flag = false;
  // for recursive. Chari will explain later!!!
   /* constructor
	*/
        public JPanel p1,p2,p3;
        public JLabel isbn_1,author_l,cat_l,title_2,title_1;
        public JComboBox cat_choose;
       public JButton enter_title,enter_author,enter_category;
        public JTextField isbn_set,author_set,title2_set,title_set;
        String author,title,category,title2,isbn;
        private boolean read;        
        public JFrame frame;
    private JTextArea disp_title;
    private JLabel category_l;
    private JTextField category_set;
    private JTextField text;
    private JButton back;
    public boolean title_found;
    String []cata={"Adventure","Fantasy","Fiction","Horror","Science Fiction"};
	public BookTitleTree()
        {
   	root = null;
        unique_setter = 0000;
        title = author = category = "Blah";
        //titleformframecreate();
	}
   /* title_create creates a new Title node and performs all appropriate checks on
		the book's existence, and generation of BookID
		*/
        public JPanel formtitlecreate()
        {
                p1 = new JPanel();

                //p1.setLayout(new FlowLayout(100));
                isbn_1 = new JLabel("Enter ISBN number");
                isbn_set = new JTextField(30);

                p1.add(isbn_1);
                p1.add(isbn_set);
                JButton check = new JButton("Check");
                p1.add(check);
                check.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    isbn = isbn_set.getText();
                    if(ifIsbnExists(isbn) != null)
                    {
                        System.out.println("title exists");
                        p1.add(new JLabel("Title exists"));
                        Title temp=ifIsbnExists(isbn);
                        author=temp.author;
                        title2=temp.title;
                        category=temp.category;
                        titleins();
                        frame.setVisible(false);
                    }
                    else
                    {
                        frame.setVisible(false);
                        catpanel();
                    }
                }
            });
               
            return p1;

        } 
        public void populateTree(Title newtitle)
        {
            {

             if(root==null)
             {
                 //if the tree does not exist
                     //Book copy = newtitle.endins(temp1);
                     //copy.book_name = newtitle.title;//everytime you create a node you call end ins. Endins is just insertion.
                      root=newtitle;
             }
             else
             {
                Title temp=root;
                boolean flag_loop=true;
                while(flag_loop)
                {
                   if(newtitle.title.compareTo(temp.title) < 0)
                   {
                    if(temp.left==null)
                      {//if a title exists. and you create a copy then you do an endinsertion .
                         //Book copy = newtitle.endins(temp1);
                         //copy.book_name = newtitle.title;
                         if(temp==null)
                         {
                            temp.left=newtitle;
                         }
                         flag_loop=false;
                      }
                       else
                       {
                               temp=temp.left;
                       }
                   }
                    else
                    {
                          if(temp.right==null)
                          {
                              //Book copy = newtitle.endins(temp1);
                              //copy.book_name = newtitle.title;
                              if(temp==null)
                              {
                                     temp.right=newtitle;
                               }
                               flag_loop =false;
                         }
                         else
                           temp=temp.right;
                                            }
                                    }
                            }
        }
       }
        public void catpanel()
        {
            System.out.println("In catpanel");
            p2 = new JPanel();
            System.out.println("created panel");
            p2.setLayout(new  FlowLayout(100));
            JButton submit = new JButton("Submit");
                        title_2=new JLabel("Enter title");
                        title2_set=new JTextField(50);
                        author_l = new JLabel("Enter Author");
                        author_set = new JTextField(50);
                        p2.add(title_2);
                        p2.add(title2_set);
                        p2.add(author_l);
                        //p2.add(author_set);
                        
                        p2.add(author_set);
                        
                        cat_choose = new JComboBox(cata);
                        cat_l =new JLabel("Choose Category");
                        p2.add((cat_l));
                        p2.add(cat_choose);
                        p2.add(submit);
                        add(p2,BorderLayout.CENTER);
                        
                         submit.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                title2=title2_set.getText();
                author = author_set.getText();
                category = cat_choose.getSelectedItem().toString();
                AdminLogin.submit = true;
                titleins();
                frame.setVisible(false);
            }
        });
            titleformframecreate(p2);
        }
        public void titleformframecreate(JPanel p)
        {
            JFrame.setDefaultLookAndFeelDecorated(true);
            frame = new JFrame("Title adder");
            frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setSize(500, 500);
            frame.add(p);
            frame.setVisible(true);

        }
	public Title title_create()
        {
		//String category=null;
		//String author;
                /*JFrame.setDefaultLookAndFeelDecorated(true);
                JFrame frame = new JFrame("Title adder");
                frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
                
                p1 = new JPanel();
                
                //p1.setLayout(new FlowLayout(100));
                title_l = new JLabel("Enter Title");
                title_set = new JTextField(30);
                
                p1.add(title_l);
                p1.add(title_set);
                
               JButton submit = new JButton("Submit");
                
                        author_l = new JLabel("Enter Author");
                        author_set = new JTextField(50);
                        p1.add(author_l);
                        p1.add(author_set);
                        String []cata={"Adventure","Fantasy","Fiction","Horror","Science Fiction"};
                        cat_choose = new JComboBox(cata);
                        cat_l =new JLabel("Choose Category");
                        p1.add((cat_l));
                        p1.add(cat_choose);
                        p1.add(submit);
                        add(p1,BorderLayout.CENTER);
                        frame.add(p1);
                        frame.setVisible(true);
                        submit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                title = title_set.getText();
                author = author_set.getText();
                category = cat_choose.getSelectedItem().toString();
            }
        });
                       // p2.setAlignmentX(TOP_ALIGNMENT);
                        //p2.setAlignmentY(CENTER_ALIGNMENT + 100);

                   */
            
                int unique_code=0;
		//frame.add(p2);
                //frame.add(p3);
		Title title_toAdd = null;//reference of the title to be assed. see below the Title constructor is called.
                Scanner in = new Scanner(System.in);
                //System.out.println("Enter Title: \n");
                
                
                {
                        System.out.println(isbn);
                    //frame.setLocationRelativeTo(null);
                    Title temp1=ifIsbnExists(isbn);
                    //ifTitleExists returns the address of the first copy.it returns null if there is no such book existing.
                    //frame.setSize(1000, 1000);

                    if(temp1==null)
                    {



                            //p2.add(author_l);
                            //p2.add(author_set);
                            //add(p3,BorderLayout.SOUTH);

                            /*int choice = 0;
                            System.out.println("Enter 1.Adventure\n2.Fantasy\n3.Fiction\n4.Horror\n5.Science Fiction");
                            choice=in.nextInt();
                            for (int i=0;i<cata.length;i++)
                            {
                                if(i==choice-1)
                                {
                                        category=cata[i];
                                 }
                            }*/

                            unique_setter+=1000;
                            unique_code=unique_setter;
                            title_toAdd=new Title(title2,author,category,isbn,unique_code);

                            //frame.setVisible(true);
                    }
                    else
                    {

                            title_toAdd=temp1;//title_toAdd takes the title from the user.
                            //frame.add(p2);
                            //frame.setVisible(true);
                    }
            }
                
                return title_toAdd;
	}        
	/*
		public Title title_create()
   {   
		String category=null;
      Scanner in = new Scanner(System.in);
      System.out.println("Enter Title \n Author \n");
      String title = in.nextLine();
      String author = in.nextLine();
      int choice = 0;
      System.out.println("Enter 1.Adventure\n2.Fantasy\n3.Fiction\n4.Horror\n5.Science Fiction");
      String []cata={"Adventure","Fantasy","Fiction","Horror","Science Fiction"};
      choice=in.nextInt();
      Title title_toAdd;
      for (int i=0;i<cata.length;i++)
      {
      	if(i==choice-1)
         {
         	category=cata[i];
			}
		}
      int unique_code = 0;
		Title temp1=ifTitleExists(title);
		if(temp1==null)
		{
			unique_setter+=1000;
			unique_code=unique_setter;
		}
		else
		{
			unique_code=temp1.unique_code;
		}
      newTitle = null;
      find = 0;
		if(temp1==null)
		{
			title_toAdd=new Title(title,author,category,unique_code);
		}
  		else
		{
			title_toAdd=temp1;
			System.out.println("Existing");
		}
      return title_toAdd;
	}        
*/
   /* ifTitleExists checks for the existence of a given title and returns the reference to the node
		if it exists and returns a null if the title doesnot exist.
	*/
	public Title ifTitleExists(String title)//returns the address of the title if it exists. if it doesnt itll return null.
        {
		if(root == null)
                {
                    return null;//if there is no book at all. then it returns null.
                }
                else if(root.title.compareTo(title) == 0)
               {
                    return root;//this is when the root itself is the title.
               }
              else
              {
                boolean flag = true;
                 Title temp = root;//used to traverse the Tree.
                 while(flag)
                 {
                    if(temp == null)
                    {
                        return null;
                    }
                    if(title.compareTo(temp.title) < 0)
                    {
                        temp = temp.left;
                    }
                    else if(title.compareTo(temp.title) > 0)
                    {
                        temp = temp.right;
                    }
                    else
                    {
                        return temp;
                    }
                }
            }
              return null;
	}
        /*public Title ifIsbnExists(String isbn)//returns the address of the title if it exists. if it doesnt itll return null.
        {
		if(root == null)
                {
                    return null;//if there is no book at all. then it returns null.
                }
                else if(root.title.compareTo(title) == 0)
               {
                    return root;//this is when the root itself is the title.
               }
              else
              {
                boolean flag = true;
                 Title temp = root;//used to traverse the Tree.
                 while(flag)
                 {
                    if(temp == null)
                    {
                        return null;
                    }
                    if(title.compareTo(temp.title) < 0)
                    {
                        temp = temp.left;
                    }
                    else if(title.compareTo(temp.title) > 0)
                    {
                        temp = temp.right;
                    }
                    else
                    {
                        return temp;
                    }
                }
            }
              return null;
	}*/
	/* titleins inserts a given title appropriaely in a binary search tree
	*/
        public Title ifIsbnExists(String isbn)//returns the address of the title if it exists. if it doesnt itll return null.
        {
		if(root == null)
                {
                    return null;//if there is no book at all. then it returns null.
                }
                else if(root.isbn.compareTo(isbn) == 0)
               {
                    return root;//this is when the root itself is the title.
               }
              else
              {
                boolean flag = true;
                 Title temp = root;//used to traverse the Tree.
                 while(flag)
                 {
                    if(temp == null)
                    {
                        return null;
                    }
                    if(isbn.compareTo(temp.isbn) < 0)
                    {
                        temp = temp.left;
                    }
                    else if(isbn.compareTo(temp.isbn) > 0)
                    {
                        temp = temp.right;
                    }
                    else
                    {
                        return temp;
                    }
                }
            }
            return null;
	}
        public void search_types()
        {
            System.out.println("Enter 1. Search by title \n 2. Search by category \n 3. Search by author");
            Scanner in1 = new Scanner(System.in);
            int choice = in1.nextInt();
            Scanner in = new Scanner(System.in);
            switch(choice)
            {
                case 1:
                {
                    System.out.println("Enter the title you want to view");
                    String title = in.nextLine();
                    if(ifTitleExists(title) != null)
                    {
                        System.out.println(ifTitleExists(title).title);
                    }
                    else
                    {
                        System.out.println("The title doesn't exist");
                    }
                    break;
                }
                case 2:
                {
                    System.out.println("Enter the category");
                    System.out.println("Printing titles in this catgeory");
                    String cat = in.nextLine();
                    search_cat(cat,root);
                    break;
                }
                case 3:
                {
                    System.out.println("Enter the author");
                    String author = in.nextLine();
                    search_author(author,root);
                    break;
                }
            }
            
        }        
	public void titleins()
        {
          
          Title newtitle= title_create();//returns a title that is taken from the user.
          Title temp1=ifIsbnExists(newtitle.isbn);
          {

             if(root==null)
             {
                 //if the tree does not exist
                     Book copy = newtitle.endins(temp1);
                     copy.book_name = newtitle.title;//everytime you create a node you call end ins. Endins is just insertion.
                      root=newtitle;
             }
             else
             {
                Title temp=root;
                boolean flag_loop=true;
                while(flag_loop)
                {
                   if(newtitle.isbn.compareTo(temp.isbn) < 0)
                   {
                    if(temp.left==null)
                      {//if a title exists. and you create a copy then you do an endinsertion .
                         Book copy = newtitle.endins(temp1);
                         copy.book_name = newtitle.title;
                         if(temp1==null)
                         {
                            temp.left=newtitle;
                         }
                         flag_loop=false;
                      }
                       else
                       {
                               temp=temp.left;
                       }
                   }
                    else
                    {
                          if(temp.right==null)
                          {
                              Book copy = newtitle.endins(temp1);
                              copy.book_name = newtitle.title;
                              if(temp1==null)
                              {
                                     temp.right=newtitle;
                               }
                               flag_loop =false;
                         }
                         else
                           temp=temp.right;
                                            }
                                    }
                            }
                    }
	}
	/*inorder travels the tree in inorder fashion. used to display the contents of the tree.
	*/
	public void inorder(Title root)
        {
		if(root!=null)
		{
			inorder(root.left);
			display(root);
			inorder(root.right);
		}
                
	}
   /*display prints the contents of a particular book
	*/
        public void displaycreator()
        {
            p3 = new JPanel();
            p3.setLayout(new FlowLayout(800));
            back = new JButton("Back");            
             disp_title = new JTextArea(100, 100);
             disp_title.setEditable(false);
             disp_title.setAutoscrolls(true);
             
             p3.add(back);
              p3.add(disp_title);
             back.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                }
            });
             titleformframecreate(p3);
             disp_title.setLocation(frame.getHeight()/3, frame.getWidth()/3);
            
             back.setLocation(2*(frame.getHeight()/3), frame.getWidth()/3);
            
        }
        public void searchcreator()
        {
            p3 = new JPanel();
            p3.setLayout(new FlowLayout(1000));
            enter_title = new JButton("Enter Title");
            enter_author = new JButton("Enter Author");
            enter_category = new JButton("Enter Category");
            p3.add(enter_title);
            p3.add(enter_author);
            p3.add(enter_category);
            add(p3,BorderLayout.CENTER);
            titleformframecreate(p3);
            enter_title.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    etitlepanel();
                    //frame.setVisible(false);
                }
            });
            enter_author.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                eauthorpanel();
                //frame.setVisible(false);
            }


        });
        enter_category.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ecategorypanel();
                //frame.setVisible(false);
            }
        });
            
        }
        public void ecategorypanel()
        {
            p3 = new JPanel();
            p3.setLayout(new  FlowLayout(100));
            JButton submit = new JButton("Submit");
            back = new JButton("Back");
                        category_l = new JLabel("Enter Category");
                        cat_choose = new JComboBox(cata);
                        p3.add(category_l);
                        p3.add(cat_choose);
                        p3.add(submit);
                        p3.add(back);
                        add(p3,BorderLayout.CENTER);
                        titleformframecreate(p3);
                         submit.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {

                category = cat_choose.getSelectedItem().toString();
                displaycreator();
                search_cat(category, root);
                //frame.setVisible(false);
            }
        });
                         back.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                }
            });
            
        }
        public void eauthorpanel()
        {
             p3 = new JPanel();
            p3.setLayout(new  FlowLayout(100));
            JButton submit = new JButton("Submit");
            back = new JButton("Back");
                        author_l = new JLabel("Enter Author");
                        author_set = new JTextField(50);
                        p3.add(author_l);
                        p3.add(author_set);
                        p3.add(submit);
                        p3.add(back);
                        add(p3,BorderLayout.CENTER);
                        
                         submit.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {

                author = author_set.getText();
                displaycreator();
                search_author(author, root);
                //frame.setVisible(false);
            }
        });
                         back.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                }
            });

                         titleformframecreate(p3);
        }
        public void etitlepanel()
        {
            p3 = new JPanel();
            p3.setLayout(new  FlowLayout(100));
            JButton submit = new JButton("Submit");

                        title_1 = new JLabel("Enter title");
                        title_set = new JTextField(50);
                        p3.add(title_1);
                        p3.add(title_set);
                        p3.add(submit);
                        text = new JTextField(50);
                        back = new JButton("Back");
                text.setEditable(false);
                        p3.add(text);
                        p3.add(back);
                        add(p3,BorderLayout.CENTER);
                        titleformframecreate(p3);

                         submit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
                title = title_set.getText();
                
               /* if(ifTitleExists(title) != null)
                {
                    
                    text.setText("Title found" + ifTitleExists(title).title);
                }
                else
                {
                    p3.add(text);
                    text.setText("Title doesn't exist");
                }*/
                displaycreator();
                search_title(title,root);
                
            }
        });
            back.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                }
            });
            
        
        }
	public void display(Title print)
	{
		/*System.out.println("Book Title: " + print.title);
		System.out.println("Book Author: " + print.author);
		System.out.println("Book Category: " + print.category);*/
                disp_title.append("\n New Title \n Book title: " + print.title + "\n Book Author: " + print.author + "\n Category: " + print.category+"\n ISBN number: "+print.isbn);
		Book temp = print.first;
		int i = 0;
		while(temp != null)
		{
			i++;
			disp_title.append("\n Copy Number: " + i + "Book ID: " + temp.id);
			temp = temp.next;
		}
                disp_title.append("\n ************** \n");
	}

    private void search_cat(String cat, Title root) 
    {
        if(root != null)
        {
            search_cat(cat,root.left);
            if(cat.compareTo(root.category) == 0)
                disp_title.append("\nTitle: " + root.title + "\nAuthor: " + root.author + "\nCategory: " + root.category + "\nISBN number: "+root.isbn);
            search_cat(cat,root.right);
        }
    }

    private void search_author(String author,Title root)
    {
        if(root != null)
        {
            search_author(author,root.left);
            if(author.compareTo(root.author) == 0)
                disp_title.append("\nTitle: " + root.title + "\nAuthor: " + root.author + "\nCategory" + root.category + "\nISBN number: "+root.isbn);
            search_author(author,root.right);
        }
        
    }
    private void search_title(String title,Title root)
    {
        if(root != null)
        {
            search_title(title,root.left);
            if(title.compareTo(root.title) == 0)
                disp_title.append("\nTitle: " + root.title + "\nAuthor: " + root.author + "\nCategory" + root.category + "\nISBN number: "+root.isbn);
            search_title(title,root.right);
        }

    }
    public void search_for_borrow(String title,Title root)
    {
        if(root != null)
        {
            search_for_borrow(title,root.left);
            if(title.compareTo(root.title) == 0)
            {
                title_found = true;
                UserBorrow.present_in_tree = root;
            }
            search_for_borrow(title,root.right);
        }
        //return null;

    }
    
}