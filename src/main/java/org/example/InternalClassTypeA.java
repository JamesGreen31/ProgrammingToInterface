package org.example;

class InternalClassTypeA implements InternalClass{
    // A class is an internal class.
    // It is package private. The public cannot see this code, but they can access the
    // utility of this class via PGTAI
    @Override
    public String getContents() {
        return "This is A class";
    }
}
