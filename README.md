# Food-Delivery-Management-System
I implemented a food delivery management system for a catering company. The client can order products from the company's menu. The
system have three types of users that log in using a username and a password: administrator, regular employee and client.

### The administrator can:
1. **Import** the initial set of products which will populate the menu from a .csv file.     
2. Manage the products from the menu: **add/delete/modify products** and **create new products** composed of several products from the menu (an example of composed product could be named “daily menu 1” composed of a soup, a steak, a garnish, and a dessert).    
3. **Generate reports** about the performed orders considering the following criteria:   
   - *time interval of the orders* – a report should be generated with the orders performed between a given start hour and a given end hour regardless the date.   
   - *the products ordered more than a specified number of times so far*.   
   - *the clients that have ordered more than a specified number of times so far and the value of the order was higher than a specified amount*.   
   - *the products ordered within a specified day with the number of times they have been ordered*.   

![ss](https://user-images.githubusercontent.com/101935675/224327360-fdcd9bed-d565-4074-a6e9-87d3ec45e22e.png)

### The client can:
1. **Register** and use the registered username and password to log in within the system.  
2. **View** the list of products from the menu.  
3. **Search for products** based on one or multiple criteria such as keyword (e.g., “soup”), rating, number of calories/proteins/fats/sodium/price.   
4. **Create an order consisting of several products** – for each order the date and time will be persisted and a bill will be generated that will list the ordered products and the total price of the order.    

![ss2](https://user-images.githubusercontent.com/101935675/224329322-e5ef7fa6-18ef-4625-b4a0-740eeb971743.png)

### The employee:
1. Is notified each time a new order is performed by a client so that it can prepare the  delivery of the ordered dishes.   

![ss3](https://user-images.githubusercontent.com/101935675/224329751-a1150806-8c6e-449a-afc5-c115293c7292.png)

I used ***Composite Design Pattern*** for defining the classes MenuItem, BaseProduct and CompositeProduct.     
The ***Observer Design Pattern*** is used to notify the employee each time a new order is created.    
I used ***lambda expressions***, ***stream processing*** and ***serialization*** in this project.

