/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package library_management.user;

/**
 *
 * @author shruthi
 */
import lib_admin.BookTitleTree;
import java.util.Scanner;
import java.io.*;
import lib_admin.Title;
public class User_Simulate
{
    public static BookTitleTree title_tree = new BookTitleTree();
    public static UserBorrow ub=new UserBorrow(title_tree);
    public static User first;
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        
        
        int choice = 0;
        while(choice <= 5 && choice >= 0)
        {
            System.out.println("Enter 1. Adding a new book/title \n 2. Displaying the list of various book titles currently available with us \n 3. Borrow\n4.Display books borrowed\n5.Search\n6.Exit");
            choice = in.nextInt();
            switch(choice)
            {
                case 1:
                {
                    System.out.println("Adding your title");
                    title_tree.titleins();
                    break;
                }
                case 2:
                {
                    System.out.println("Displaying available titles in Alphabetical order");
                    title_tree.inorder(title_tree.root);
                    break;
                }
                case 3:
                {
                    System.out.println("Boorowing in process");

                    
                    ub.borrow();break;
                }

                case 4:
                {
                    ub.display();break;
                }
                case 5:
                {
                    title_tree.search_types();
                    break;
                }
            }
        }

    }
    public void Admin()
    {
        int choice = 0;
        Scanner in = new Scanner(System.in);
        while(choice <= 3 && choice >= 0)
        {
            System.out.println("Enter 1. Adding a new book/title \n 2. Displaying the list of various book titles currently available with us \n 3.Search \n4.Exit");
            choice = in.nextInt();
            switch(choice)
            {
                case 1:
                {
                    System.out.println("Adding your title");
                    title_tree.titleins();
                    break;
                }
                case 2:
                {
                    System.out.println("Displaying available titles in Alphabetical order");
                    title_tree.inorder(title_tree.root);
                    break;
                }
                case 3:
                {
                     title_tree.search_types();
                    break;
                }
                case 4:
                {
                    System.exit(0);
                    break;
                }

            }
        }
    }
    public void User()
    {
        ub.borrow();
        int choice = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Do you want see your list of borrowed books,if yes enter 1");
        choice = in.nextInt();
        if(choice == 1)
        {
            ub.display();
        }

        System.exit(0);


    }

    public void save() 
    {
        saveTree(title_tree);
        saveUserList(ub);
        try{
            FileOutputStream fout=new FileOutputStream("C:\\Users\\Shruthi Chari\\Desktop\\treeNodes.ser");
            ObjectOutputStream oos=new ObjectOutputStream(fout);
            preorder(oos,title_tree.root);
            //oos.writeObject(node1);
            oos.close();
        }
        catch(Exception ex)
        {
                ex.printStackTrace();
        }
    }
    public void update()
    {
        first=null;
       
        title_tree=createTree(title_tree);
        createUserList();
        while(first!=null)
        {
            System.out.println("creating "+first.user_name );
            ub.populateList(first);
            first=first.next;
        }
    }
    public static BookTitleTree createTree(BookTitleTree title_tree)
    {
        try
        {
            FileInputStream fin=new FileInputStream("C:\\Users\\Shruthi Chari\\Desktop\\treeNodes.ser");
            ObjectInputStream ois=new ObjectInputStream(fin);
            while(fin.available()!=0)
            {
                Title node = (Title) ois.readObject();
                title_tree.populateTree(node);
            }
            ois.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("error caught" );
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return title_tree;
    }
    public static void saveTree(BookTitleTree title_tree)
    {
        try{
            FileOutputStream fout=new FileOutputStream("C:\\Users\\Shruthi Chari\\Desktop\\treeNodes.ser");
            ObjectOutputStream oos=new ObjectOutputStream(fout);
            preorder(oos,title_tree.root);
            oos.close();
        }
        catch(Exception ex)
        {
                ex.printStackTrace();
        }
    }
    public static void saveUserList(UserBorrow ub)
    {//was testinmg here!
        try{
            FileOutputStream fout=new FileOutputStream("C:\\Users\\Shruthi Chari\\Desktop\\user.ser");
            ObjectOutputStream oos=new ObjectOutputStream(fout);
            if(ub.firstUser == null)
            return;
            else
            {
                User temp = ub.firstUser;
                while(temp!= null)
                {
                    oos.writeObject(temp);
                    temp = temp.next;
                }
            }
            oos.close();
        }
        catch(Exception ex)
        {
                ex.printStackTrace();
        }
    }
    public static void preorder(ObjectOutputStream oos, Title root1)
    {
        try
        {
            if(root1!=null)
            {
                oos.writeObject(root1);
                preorder(oos,root1.left);
                preorder(oos,root1.right);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void createUserList()
    {
        //first=null;
        try
        {
            System.out.println("in createUserList");
            FileInputStream fin=new FileInputStream("C:\\Users\\Shruthi Chari\\Desktop\\user.ser");
            ObjectInputStream ois=new ObjectInputStream(fin);
            User temp=null;
            while(fin.available()!=0)
            {
                temp=first;
                System.out.println("File not empty");
                User node =(User)ois.readObject();
                node.next=null;
                if(first==null)
                {
                    System.out.println("create first");
                    first=node;
                }
                else
                {
                    while(temp.next!=null)
                    {
                        temp=temp.next;
                        //ub.populateList(node);
                    }
                    temp.next=node;
                }
            }
            ois.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("error caught" );
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
     }
   

}
