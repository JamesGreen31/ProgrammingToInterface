# ProgrammingToInterface
A small project illustrating Programming to an interface

## Overview
Programming to an interface is a complicated topic that has many interprotations. 
In this repository, I have outlined a few classes which demonstrate what this concept is, how it can be useful (to enterprises and to hobbyists), and how to effectively implement it.

## What is programming to an interface?
The concept is actually quite simple: We should generalize the relationship between classes via an interface style object rather than a direct relationship. I found that even
simple statements like these were quite confusing and longed for a real world example. After reading online, I found a descent description of the concept <LINK TO WHEELS THING HERE>.
The language in this article can be quite confusing, so I will attempt to retell the contents of it here. We then can apply this logic to our repository classes.
  
  ### Wheels and Mechanics
  Suppose you are an unfortunate soul who blew a tire on the way to work. You need replacement tires as the stock tires are wearing thin. You spot a mechanic's repair shop and take your car in.
  The mechanic agrees to work on your car, and as he is removing the old tires, you find some new awesome race tires online. These new tires are rated to go 50x farther than your standard stock tires,
  but you have a problem. _How do you know if that these tires will function the same as your old ones?_ That is a serious question. Will the tires roll? Of course they will! But you already knew this. How come?
  How does the mechanic know how to balance these tires? How does he know how to apply them? Indeed we may ask **_Why do non stock tires work on cars?_**
  
  This answer is painfully obvious in the real world. The wheels attach to an axel in a standardized way (for most cars anyway). The car doesn't car what brand the wheels are, only that they are wheels and meet the basic 
  requirements as such. In essence, all tire brands must have the basic requirments of a wheel. You could say that the tires use the wheel _interface_ to function properly with the car.
  
  Okay, so what does this mean? In the context of this example, the mechanic is a user of the car _API_. He knows that the _car_ object requires objects of type _wheel_ in order to function. The _tire_ manufacturers know that 
  _tires_ must implement the _wheel_ interface as the _car_ object is looking to move by using _wheel_ objects. In short, the _car_ object uses _wheel_ object in order to move. By making _tires_ implement the _wheel_ interface,
  we can insure that _tire_ objects will always work on _car_ objects. 
  
  When I was a first year computer science student learning the difference between abstract classes and interfaces, I never understood the purpose of an interface. However, when exploring this example
  the meaning became clear. **_Interfaces were meant to make code adaptable_**. 
  
  In our mechanic example, we could change how the car works entirely. But if it still implements the (unchanged)_wheel_ interface, then we know that all _tire_ objects will still function. Similarly,
  we can change anything we want about tire objects; so long as they implement the wheel interface properly, they will still function. This makes our mechanics job much easier and protects the intellectual property of your car manufacturer
  as the mechanic does not need to know how the cars firmware handles all the movement. Instead, he needs only know how to properly connect the tire to the car using the cars api. 
  
  Let's take a look at a basic code example.
  
  ```java
  public class SedanTire{
    void roll(){
      //tire roll code
    }
  }
  
  public class wheel{
    private SedanTire tire;
    public wheel(SedanTire tire){
      this.tire = tire;
    }
  }
  ```
  In this exabple, the SedanTire and the wheel object have a built in functionality, instead of a generic one. That can lead to problems down the line as we will shortly see. By re-writing this as an interface,
  we generalize this relationship thus avoiding these pitfalls.
  
  The above is how I would have done this back when I was first learning. On the surface, it makes perfect sense. However, this has a few flaws. First, only objects of type SedanTire
  will be able to connect. This means that all other tire objects will not be able to connect to our car object. While we could make Tire an abstract class and implement several kinds of children
  (such as SedanTire), that doesn't do much for us in the way of API use. We don't really care about the relationship between Tires and their children. We only need to make sure that the Tire object does what the wheel interface requires.
  If we made Tire an abstract class, then if we wanted to change how cars used Tires, the burden of development would be on the Tire and not the car. To visualize this, lets say that in the future, we develop a car that is able to fly using a special kind of tire.
  To standardize the industry the FAA has required that all car manufacturers require tires to be able to fly. If we use abstract classes, then the car manufacturers would be waiting on Tire manufacturers to update the required interface. In essence, **_We would be waiting for your car to be compatible with the tires_**
  . If we used interfaces, then the car manufactueres can update the wheel interface, which would prevent all old tires from functioning as they do not have a "fly" method implemented. This puts the burden of updating on Tire manufactuers (users) instead of on car manufactuerers(developers).
  
  It may have been unclear in the above example what that means, so I will be plain. When users are implementing an API, they usually access the methods inside via an interface object. If somehow they relied on abstract objects, then anytime the developers of that API
  developers needed to update the API, they would need to tell you exactly what needed to change in your usage of their API and it would be on you to figure it out. By using an interface, they can update that interface on their end which would then simply require you to add the new methods they created.
  
  So, in summary: Programming to an interface is designing your code such that each component is defined outside of implementation, with a generalized relationship with the API. This allows you to take better control over the implementation of your classes
  by requiring a generic object which fits requirments, rather than a custom built relationship between each object.
  
## Benefits of Interface based programming (Enterprise)
  ...
