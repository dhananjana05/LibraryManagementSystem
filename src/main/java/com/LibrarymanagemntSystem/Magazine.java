package com.LibrarymanagemntSystem;

public class Magazine extends LibraryItem{
    public Magazine(String title, String author, String serialNumber) {
        super(title, author, serialNumber);
    }

    @Override
    public void borrowedItem(User user) {
       if(!isBorrowed){
           isBorrowed=true;
           System.out.println(user+"Borrowed the magazine"+getTitle());
       }
       else{
           System.out.println("The magazine "+getTitle()+" is already borrowed.");
       }
    }
}
