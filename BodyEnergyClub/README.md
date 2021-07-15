# Body Wellness Club

## *Your Ultimate Smoothie Store*

**What this application does**
This is a **console driven application** that provides the user with a *real world experience* as if they were actually 
inside a store ordering a smoothie.  

**Who will use it**
Anyone who loves smoothies and/or is a fitness freak who reads nutrition labels before they consume anything.
To make a smoothie, the user has two options: to choose smoothies on the menu or make their own.
The application let's the customer build their smoothie, based on their personal preferences. Users will be able to 
choose the fruits they want, the type of milk they prefer and even the kind of protein powder that they like.

**Why is the project of interest to me**
I really love smoothies (haha!) but at the same time I like staying fit and eating healthy. The ultimate goal of such 
an app is to promote wellness and a healthy life style.
Also, in designing the app, I made some critical design decisions which gave me an opportunity to apply the concepts 
I've learnt in class.

Here are few examples of the options we provide our customers:  
     
     
    1. Types of milk:
    - Cow's Milk
    - Almond Milk (Sweetened and Unsweetened)
    - Oat Milk (Sweetened and Unsweetened)
            
    2.Types of fruits:  
    - Banana  
    - Mango     
    - Cherry  
    - Apple  
    - Custom fruit by user
    
    3.Types of proteins:
    - Whey
    - Vegan
   
USER STORIES FOR PHASE 1:
 - As a user, I want to see pre-made smoothies in the menu.
 - As a user, I want to customise and build my own smoothie.
 - As a user, I want to able to see the different nutritional facts about each smoothie.
 - As a user, I want to be able to add a fruit not in listed in the menu. 

USER STORIES FOR PHASE 2:
 - As a user, I want to save the last customized smoothie made by user    
 - As a user, I want to load the last customized smoothie in the menu    
 
PHASE 4 Task 2: 
- Made smoothie constructor Robust. Now, user cannot build a smoothie in the ui if they enter a zero length name.
  Error window which prompts user to enter an appropriate name shows the erorr message. Test cases corresponding to
  both cases have been added.
  
  Classes effected in Ui Package:
  SmoothieStoreApp,
  CustomSmoothieMenu 
  
PHASE 4 Task 3:
- From the UML diagram, we can see that almost all classes depend on Smoothie.
- Fruit, Milk, Smoothie and protein all extend and implement Food and Writable, respectively.
- For refactoring, there is redundant code in initializing buttons in various classes, I could create a method that
  relevant arguments and does the job. 
       
