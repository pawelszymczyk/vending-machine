# vending-machine SPOCK kata
------------

1. clone vending-machine project
   <p><code>git clone https://spocky_all@github.com/pawelszymczyk/vending-machine.git
   </code></p>
1. Checkout proper branch (team01..10)
1. If you use IntelliJ, run <code>gradle idea</code> or simply import Gradle project
1. Start playing with tests:
    <p> <code>gradle test</code></p>
    <p> <code>gradle cleanTest test</code> - to run again when nothing changed (gradle works incrementally) </p>
1. Kata - implement 4 user stories


# User stories

Based on
http://github.com/guyroyse/vending-machine-kata

------------
### 1. Accept Coins

_As a vendor_  
_I want a vending machine that accepts coins_  
_So that I can collect money from the customer_  

The vending machine will accept valid coins (nickels, dimes, and quarters) and reject invalid one (pennies).  When a
valid coin is inserted the amount of the coin will be added to the current amount and the display will be updated.
When there are no coins inserted, the machine displays INSERT COIN.  Rejected coins are placed in the coin return.

NOTE: The temptation here will be to create Coin objects that know their value.  However, this is not how a real
  vending machine works.  Instead, it identifies coins by their weight and size and then assigned a value to what
  was inserted.  You will need to do something similar.  This can be simulated using strings, constants, enums,
  symbols, or something of that nature.

--------------
### 2. Select Product

_As a vendor_  
_I want customers to select products_  
_So that I can give them an incentive to put money in the machine_  

There are three products: cola for $1.00, chips for $0.50, and candy for $0.65.  When the respective button is pressed
and enough money has been inserted, the product is dispensed and the machine displays THANK YOU.  If the display is
checked again, it will display INSERT COINS and the current amount will be set to $0.00.  If there is not enough money
inserted then the machine displays PRICE and the price of the item and subsequent checks of the display will display
either INSERT COINS or the current amount as appropriate.

-----------
### 3. Make Change

_As a vendor_  
_I want customers to receive correct change_  
_So that they will use the vending machine again_  

When a product is selected that costs less than the amount of money in the machine, then the remaining amount is placed
in the coin return.

-----------
### 4. Storage & eMails
_As a venodr_  
_I want to be notified via email when machine runs out of product_  

Vending machine has one shelve per product, each with 10 items capacity. Full machine contains 10x cola, 10x chips and 10x candy.

Each time when user selects sold out product machine should do two things:

1. display NO ${productName}, SORRY
1. send email to vendor with supply request


# See

1. http://docs.spockframework.org
1. Pawel intro - http://github.com/pcierpiatka/groovy_workshop
1. http://groovy.codehaus.org/Groovy+style+and+language+feature+guidelines+for+Java+developers