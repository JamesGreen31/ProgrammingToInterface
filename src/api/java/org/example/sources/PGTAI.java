package org.example.sources;
// This is our source code that the public will see. Notice that it is the ONLY
// interface visible to the public.

public interface PGTAI { //this is our main api interface.
    // The public would use this to access our Internal (proprietary) classes

    //it is assumed the public knows the difference between A and B class

    InternalClass getInternalClassTypeA();
    InternalClass getInternalClassTypeB();
}
