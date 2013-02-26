/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package library_management.user;
//import library_management;
import lib_admin.Book;

import java.io.*;

/**
 *
 * @author Shrinidhi
 */
import java.util.*;
//User infomation such as name borrowed book etc is stored here. Its a user node class.IT is a NODE CLASS
public class User implements Serializable,Cloneable {
    String user_name;
    Book []borrowed_book;
    User next,prev;
    int book_no_borrowed = 0;
    public User(String user_name)
    {
        this.user_name=user_name;
        this.borrowed_book=new Book[3];
        this.next=null;
        this.prev=null;
    }
    public Object clone()
    {
        try
        {
            User cloned=(User)super.clone();
            return cloned;
        }
        catch(CloneNotSupportedException e)
                {
                    System.out.println(e);
                    return null;
                }
    }
}

