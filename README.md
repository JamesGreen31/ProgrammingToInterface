# Programming To An Interface
A small project illustrating Programming to an interface

## Overview
Programming to an interface is a complicated topic that has many facuets, ideas, and strategies. 
In this repository, I have outlined a few classes which demonstrate what this concept is, how it can be useful (to enterprises and to hobbyists), and how to effectively implement it.

## What is programming to an interface?
The concept is actually quite simple: We should generalize the relationship between classes via an interface style object rather than a direct relationship. I find that even
simple statements like these are quite confusing and require a real world example. After reading online, I found a descent description of the concept [in this article](https://link.medium.com/XdLXtnsc0qb) by introdev.io.
The language in the linked article can be quite confusing, so I will attempt to retell the contents of it here. We then can apply this logic to our repository classes.
  
  ### Wheels and Mechanics
  Suppose you are an unfortunate soul who blew a tire on the way to work. You will need a replacement tire as the stock tire is totally destroyed. You spot a mechanic's repair shop and take your car in.
  The mechanic agrees to work on your car, and as he is removing the old tire, you find some new awesome race tires online. These new tires are rated to go 50x farther than your standard stock tires and are totally a steal for the price. But
  but you have a problem: _How do you know that these tires will function the same as your old ones?_ Will they roll? Are they made of rubber? Of course they will, and of course they are! But you already knew this. How?
  How does the mechanic know how to balance these tires? How does he know how to put them on? How does the car know the tire pressure in these tires? In fact, we should ask **_Why do non stock tires work on cars at all?_**
  
  This answer is painfully obvious in the real world. The wheels attach to an axel in a standardized way (for most cars anyway). The car doesn't care what brand the wheels are, only that they are wheels and meet the basic 
  requirements of a wheel. In essence, all tire brands must have the basic requirments of a wheel. Thus, you could say that the tires use the wheel _interface_ to function properly with the car.
  
  Okay, so what does this mean? In the context of this example, the mechanic is a user of the car _API_. He knows that the _car_ object requires objects of type _wheel_ in order to function. The _tire_ manufacturers know that 
  _tires_ must implement the _wheel_ interface as the _car_ object is looking to move by using _wheels_. In short, the _car_ object interfaces with _wheel_ objects in order to move. By making _tire_ implement the _wheel_ interface, we bridge the connection between _car_ and _tire_ and allow _tire_ to be used for motion.
  
  ## Abstract vs Interface
  When I was a first year computer science student learning the difference between abstract classes and interfaces, I never understood the purpose of the interface or why a programmer would use it over an abstract class. However, when exploring the mechanic example,
  the meaning became clear. **_Interfaces were meant to make code adaptable_**. 
  
  In our mechanic example, we could change how the car works entirely. But if it still moves using the (unchanged)_wheel_ interface, then we know that all _tire_ objects will still function. Similarly,
  we can change anything we want about tire objects; so long as they implement the wheel interface properly, they will still function. This makes our mechanics job much easier and protects the intellectual property of your car manufacturer
  as the mechanic does not need to know how the cars firmware handles all the movement. Instead, he needs only know how to properly connect the tire to the car using the cars api. 
  
  Let's take a look at a basic code example.
  
  ```java
  public class SedanTire{
    void roll(){
      //tire roll code
    }
  }
  
  public class car{
    private SedanTire tire;
    public car(SedanTire tire){
      this.tire = tire;
    }
  }
  ```
  In this example, the SedanTire and the wheel object have a built in, direct relationship instead of a generic one. That can lead to problems down the line as we will shortly see. By re-writing this as an interface,
  we generalize this relationship thus avoiding these pitfalls.
  
  ```java
  public class SedanTire impmements wheel{
    @Override
   void roll(){
      //tire roll code
    }
  }
  
  public class car{
    private Wheel tire;
    public wheel(Wheel tire){
      this.tire = tire;
    }
  }

  interface wheel{
     void roll();
   }
  ```

 The first code block is how I would have done this back when I was first learning. On the surface, it makes perfect sense. However, this has a few flaws. First, only objects of type SedanTire
  will be able to connect. This means that all other tire objects will not be able to connect to our car object. While we could make Tire an abstract class and implement several kinds of children
  (such as SedanTire), that doesn't do much for us in the way of API use. We don't really care about the relationship between Tires and their children. We only need to make sure that the Tire object does what the wheel interface requires.
  If we made Tire an abstract class, then if we wanted to change how cars used Tires, the burden of development would be on the Tire and not the car, since the car cannot tell the old tire object from the new tire object. To visualize this, lets say that in the future, we develop a car that is able to fly using a special kind of tire.
  To standardize the industry the FAA has required that all be able to fly. If we use abstract classes, then the car manufacturers would be waiting on Tire manufacturers to update the required abstract class, and old tires would still conect to cars(but function incorrectly). In essence, **_We would be waiting for your car to be compatible with the tires_**
  . If we used interfaces, then the car manufactueres can update the wheel interface, which would prevent all old tires from functioning as they do not have a "fly" method implemented. This puts the burden of updating tires on Tire manufactuers (users) instead of on car manufactuerers(developers). With this structure, **_We would be waiting for tires to be compatible with your car_**
  
  It may have been unclear in the above example what that means, so I will be plain. When users are implementing an API, they usually access the methods inside via an interface object. If this api instead relied on abstract objects, then anytime the developers of that API
  needed to update the API, they would need to tell you exactly what needed to change in your usage of their API and it would be on you to figure it out (and old implementations would not cause compile errors). By programming to an interface, these developers can update that interface on their end which would then simply require you to add the new methods they created.
  
  So, in summary: Programming to an interface is designing your code such that each component is defined outside of implementation, with a generalized relationship between objects instead of explicit ones. This allows you to take better control over the implementation of your classes
  by requiring a generic object which fits requirments, rather than a direct, rigid connection between each object.
  
## Benefits of Interface based programming (Enterprise)
If you have worked in the industry before, you may have noticed that there are a ton of classes in projects, and that the naming convention usually results in a very verbose codebase. There is a reason for that: Programming more generalized relatinoships usually involves a lot more code.
This is a downside of programming to an interface as well, however, the benefits are much more rewarding than the cost of being verbose. The benefits of programming to an interface are immense for proprietary code. For example, let's presume that you are a game studio and want to add mod support to your game. 
Your users will need a way to interface with the game, but you also don't want to reveal all the source code for the game as doing so may make cheat development much easier. One way to solve this is to program to an interface. In this context, your api for your game would consist solely of interfaces, which detail to mod developers how certain components interact with each other. With these tools available, it is actually *nearly* irrelevant what your game code actually does, as the interfaes you provided the mod developers describe all the public relationships between the classes(Anything not exposed in the interface should be a private method, or package private at least). This achieves the compromise between intellectual property and community engagement.  But protecting your intellectual property isn't the only thing programming to an interface can do for you.
  
 One of the most difficult tasks for any programmer when joining a new progamming team is becoming familiar with the codebase. Often times, they find themselves taking considerable time just learning the naming convention for everything. This is so
 common that many enterprises in the industry will alot a 90 day training window for new developers, where they can spend their entire day shift just studying and becoming familiar with the codebase. Such training windows are time consuming
 and expensive, not to mention usually quite stressful for the new developer. The best way to combat this is to have a codebase that is easy to read and understand, and while programming to an interface usually does result in a lot 
 more classes than would otherwise be present in an application, it ultimatly acheives a higher level of organization and makes writing their first few contributions much simpler. This design pattern also forces developers to
 design their classes completely before implementing them. The net result of this is smaller refactoring times, easier to understand relationships, and less time spent hashing out all the intricate details in coordination meetings.
 But these benefits are also not unique for enterprises. Hobbyists and small scale developers can also utlitize this design structure.
 
## Benefits of Interface based programming (Hobbyists)
> When you're building a video game world, you're building with ideas, And that can be like working with quick-set cement. You mold your ideas into a certain shape that can be played with, and in the process of playing with them, they begin to harden and set until they are immutable like rock. At that point, you can't change the world. Not without breaking it into pieces and starting fresh with new ideas.<br>-- Bennett Foddy (quote from his game "getting over it"

Bennett was correct when he was discussing the design of a video game becoming cement over time. I cannot tell you how many times I have had to restart a project because I got too lost down a rabbit hole of a particular idea. 
I often found myself in these situations staring back at the surface from the very deep hole that I had dug, only to realize that it was exactly that -- a hole -- good for nothing but to be filled in afterwards. What I mean here is 
that as I explored a particular idea in my code, the classes that I would create as I attempted to acheive my goal would often have a direct relationship with my idea, and when it came time for that idea to be pulled from the project,
all those classes had to go with it. This usually involved a considerable amount of re-writing. One way to help prevent this loss of time is to "slow the hardening of the cemet". The best way to do that is to write adaptable classes that are 
able to easily be re-purposed for other things. Let's a explore an example from the repository.
```java
interface InternalClass{
	String getContents();
}

class InternalClassTypeA implements InternalClass{
    @Override
    public String getContents() {
        return "This is A class";
    }
}
```
In this case, `InternalClassTypeA` implements the `InternalClass` interface. Typically, if I were tunnel visioned on a particular idea and were **not** using the _Programming To An Interface_ strategy, I would not use a structure
like the above. Instead, `InternalClassTypeA` would return its contents directly, and whatever class was using it would require an object of type `InternalClassTypeA`. As a result, when the implementing class is removed, `InternalClassTypeA` would
also become near useless. However, if I instead were using the _Programming to an interface_ design pattern, then my implementing class would take advantage of the `InternalClass` type as opposed to `InternalClassTypeA`. This way, if the implementing
class is deleted, only the methods pretaining to the implementing class need removed. In a multifacted project where many classes utilize the `InternalClass` interface, it makes it much easier to refactor useless code. This is like mining out a quarry instead
of just digging a hole. While it may be more effort upfront, the result is a wider space to work in that ultimatly allows your ideas to be more manipulatable, avoiding the "cement" that Bennett mentions. 

It is also true that as games become more complicated, more individuals may be added to a project. The benefits of readability were already discussed in the Enterprise section of this article, but the equally apply here as well -- better organization equals
a better end product.

## The Repository
In this repository, you will see a few sample classes. Each of these has been layed out in a way to demosntrate a basic system that is programmed to an interface. To further capture this idea, I have also organized it in a fashion that 
an enterprise with proprietary code may wish to organize their code. You will notice that the gradle script allows for a sources jar to be generated. This jar incldues only the interfaces -- not the source code for our InternalClass types.
If you intend for your code to be open source though, you can disregard the api section of this repo entirely and just focus on the four classes located under `src/main/java`

## Important Notes
Throughout this article, we have referenced the term _interface_ many times. It is important to realize though that the _interface_ object type isn't necesarilly needed to achieve this concept however. In fact, the _interface_ type was 
created for the purposes of programming to an interface, but this concept exists beyond that. In general, classes should interact with generalized relationships rather than direct ones. Most of the time, the _interface_ class type helps
achieve this, but you should still keep your eyes open for instances _interface_ class type may not be the best option. Just because you cannot use an interface type does not mean you cannot program to an interface.

## Final thoughts
Programming to an interface is the mark of a developer who truly wishes to have their code in the best possible shape possible. It creates an environment that allows for rapid change; an environment which fosters both hobbyists and enterprises. 
It improves the readability of code and allows for an simple API to prospective developers who wish to utilize codebases. Unforunatly, it is also a concept that is surrounded by misunderstanding and misinformation. I hope that throughout your
reading of this article, and study of this repository, you have achieved a broader understanding of what it means to program to an interface, and hopefully, your programs may evolve from using simple direct relationships to the
stronger, more intelligent design offered by programming to an interface.

 > “I'm not a great programmer; I'm just a good programmer with great habits.”<br>― Kent Beck
 
