/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lib_admin;
import java.io.*;
/**
 *
 * @author shruthi
 */
/*book holds copy of the book.Title holds a new book of that title. */
public class Book implements Serializable
{
    public boolean borrowed;
    public String doi;
    public String doex;
    public int id;
    public int copy; //tells us which numbered copy a particular book is
    public Book next;
    public String book_name;//used for book title
    //public Book prev;
    public Book(int incrementer)
    {
        copy =  incrementer;
        borrowed = false;
    }
}
