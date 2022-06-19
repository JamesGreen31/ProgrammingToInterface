package org.example;

class InternalClassTypeB implements InternalClass{
    // B class is an internal class.
    // It is package private. The public cannot see this code, but they can access the
    // utility of this class via PGTAI
    @Override
    public String getContents() {
        return "This is B Class";
    }
}
