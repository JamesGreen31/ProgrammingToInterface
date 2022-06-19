package org.example.sources;//this interface is package private as we do not need the public using it.
// However, it is helpful to know the contents of what an InternalClass is.
// In general, it doens't hurt to publish the interfaces, even if the actual code is proprietary

interface InternalClass {
    //Internal class is package private. The public cannot see this.

    /*this is the interface that all our components will implement.
    * Since everything here is an instance of "Internal Class", we know that changes to either to
    * either A class or B class would not break our public API.
    */
    String getContents();
}
