package org.example;

interface InternalClass {
    //Internal class is package private. The public cannot see this.

    /*this is the interface that all our components will implement.
    * Since everything here is an instance of "Internal Class", we know that changes to either to
    * either A class or B class would not break our public API.
    */
    String getContents();
}
