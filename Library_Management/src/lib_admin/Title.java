/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 /* changed as on 20.11.2012 by Swathi
 1.endins takes in an object of class "Title" and this is used to set a boolean flag
 "isExisting" which lets us know if the title already exists or not.
 2. renamed "found" to "TitleExists" which tells us if title is present or not. Its value is boolean
 */

package lib_admin;
import java.io.*;
import java.util.Scanner;
import lib_admin.Book;

/**
 *
 * @author shruthi
 */
//Book holds the cpoy. Title holds every individual copy of the book create. The tree is made of titles. within the title you'll create a node.
public class Title implements Serializable
{
    public String title,author,category,isbn;//Fields that take in the title,author,categiry.
    public Title left;
    public Title right;
    public int unique_code; //stores the code corresponding to a particular "Title"
    public Book first;//this is the list that contains the cpoy of a certain title
    public int no_of_books_available;//stores reference to a list containg all copies of a particular title
    //constructor
     public Title(String title,String author,String cat,String isbn,int unique_code)
    {
        this.title = title;
        this.author = author;
        this.category = cat;
        this.isbn=isbn;
        this.unique_code = unique_code;
        first = null;
        no_of_books_available=0;
    }
    //set_id sets the ID for every COPY of a given book using the unique_code for every title
	 public int set_id(boolean TitleExists,int incrementer)//set_id sets the id for the book.
    {
        int id = unique_code;
        if(TitleExists == true)
        {
			id = unique_code + incrementer;
        }
        else if(TitleExists == false)
        {
            id = unique_code;
        }
        return id;
    }
	 /* create creates a new node in the list of copies of a given title
	 */
    public Book create(boolean TitleExists,int incrementer)
    {
        Book booknode=new Book(incrementer);
        booknode.id = set_id(TitleExists,incrementer);
        return booknode;
    }
	 /* endins appends a newcopy to the end of the list of given copies
	 */
	 public Book endins(Title temp1)//dont get confused. it does front insertion only.
	 {
	 	boolean isExisting;//if the book exists it sets it to 1.
		if(temp1!=null)
		isExisting=true;
		else
		isExisting=false;
		Book nextcopy = first;//a variable to traverse.
                //no_of_books_available=1;
      int incre = 0;
      if(first != null)
      {
              while(nextcopy != null)
              {
                    incre++;
                    nextcopy = nextcopy.next;
                    no_of_books_available++;
              }
             
      }
      Book newcopy = create(isExisting,incre);
      if(first == null)
      {
			first = newcopy;                        
                        //first.prev = first;
      }
      else
      {
          newcopy.next=first;
          first=newcopy;
      }
      return newcopy;
	}
}
