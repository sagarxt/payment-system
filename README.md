# Payment System

In the Payment System, there are some service providers and customers who can buy their services and manage all the details.
This is a Payment System application built using the Spring Boot framework, MySQL for database management, JSP for server-side rendering of HTML templates, and Bootstrap for styling. The project includes session tracking for secure logins and usage.
## Project Flow
### Home (Register and Login)
**Home Page :** When you start the spring boot application you will see the homepage of Payment System. On the home page, there are 2 options `Register` for new user registration and `Login` for existing users.
<img src="https://i.postimg.cc/Pqk8NgmS/home.png" alt="Payment System Home Page">

**Register Page :** When you click on Register it will show **Customer Registration** form. In the navigation bar, you have 2 options `Register as Biller` for biller registration and `Login` for existing users.
After registration, it will redirect to the login page wilt showing the message *'Customer registered successfully'* and the same goes for the biller.
<img src="https://i.postimg.cc/JnrD5jYJ/register.png" alt="Register Page">

**Login Page :** When you click on Login, it will show the **Login (Customer & Biller)** form. In the navigation bar, you have two options: `Register as Biller` for biller registration and `Register as Customer` for customer registration.
Here, it asks for a registered email and password. If the email or password is invalid, it will show an error message *'Invalid email or password'*. Otherwise, it will check if the user is a Biller or Customer and redirect to their dashboard accordingly.
<img src="https://i.postimg.cc/3xGv88Dy/login.png" alt="Login Page">

### Customer Functionalities
**Customer Dashboard :** In the customer dashboard there are 3 components.
1. One is the nav-bar, which displays the registered customer's name and a Logout option to log out the current user and redirect to the home page.
2. Another is the sidebar, where we have all the options like View Profile, My Orders, All Billers, All Products, and Registered Billers.
3. The third is the content area, which displays all the available products on the platform by different billers. Each product shows the product details (name, description, category, and seller) and the price of the product, along with a Buy Now button to purchase that product. If you click on Buy Now, an order will be placed for that product, and you will be redirected to the My Orders section.
<img src="https://i.postimg.cc/7hQ26bVz/c-dashboard.png" alt="Customer Dashboard Page">

**View Profile :** In the profile, the customer will see their name and email address. The customer has two options: one is for `Update Profile` to update their profile, and the other is `Change Password` to change their password by providing the existing password.
<img src="https://i.postimg.cc/y6f9nrHN/c-profile.png" alt="Customer Profile Page">

**My Orders :** In the My Orders section, the customer can see all their placed orders and the status of each order, whether it is *Pending*, *Approved*, or *Rejected*. At the top, there are three options: `Approved Orders`, `Pending Orders`, and `Rejected Orders`, to view the orders by their status.
For each order, there is a `View Details` button to display the receipt or a detailed view of that order.
<img src="https://i.postimg.cc/J4GXFcr5/c-orders.png" alt="Customer Orders Page">

**Order Receipt :** In the Order Receipt section, customers can view a detailed summary of the order, including information such as Product Details, Biller Details, Customer Details, Order Bill ID, Order Status, Order Date, Product Price, and Payment Status.
<img src="https://i.postimg.cc/g0qLhgsm/order-receipt.png" alt="Order Receipt Page">

**All Billers :** In the All Billers section, customers can view all available billers. For each biller listed, there is an option labeled "View Products" which allows customers to check the available products offered by that particular biller.
<img src="https://i.postimg.cc/G2KDWXTq/c-all-billers.png" alt="All Billers Page">

**Registered Billers :** In the Registered Billers section, customer can see all registered billers (If a customer has purchased from a biller it will be registered automatically). For each biller listed, there is an option labeled "View Products" which allows customers to check the available products offered by that particular biller.
<img src="https://i.postimg.cc/dtpCckDP/c-reg-billers.png" alt="Registered Billers Page">

### Biller Functionalities
**Biller Dashboard :** In the Biller dashboard there are 3 components.
1. The nav-bar, which displays the registered biller's name and a Logout option to log out the current user and redirect to the home page.
2. The sidebar, where we have all the options like View Profile, Add Product, View Products, Pending Orders, All Orders, Registered Customers.
3. The third is is the content area, which displays All Orders, Pending Orders, Total Products, and Registered Customers. Every option has an option to redirect to the corresponding page.
<img src="https://i.postimg.cc/3wgmB88M/b-dashboard.png" alt="Biller Dashboard Page">

**View Profile :** In the profile, the biller will see their name, email address, about, and category. The biller has two options: one is for `Update Profile` to update their profile, and the other is `Change Password` to change their password by providing the existing password.

**Add Product :** In the Add Product section, biller can add new products to their inventory by providing the product name, price, description and category.
<img src="https://i.postimg.cc/7Y61krV7/add-product.png" alt="Add Product Page">

**View Products :** In the View Products section, biller can view a list of all products they have added. For each product listed, there is an option labeled `Edit` & `Delete` which allows biller to update or disable products. If the product is disabled, biller can enable it again by clicking on the `Active` button.
<img src="https://i.postimg.cc/fTrcWq2P/b-all-products.png" alt="Add Product Page">

**Pending Orders :** In the Pending Orders section, biller can see all orders that are pending approval. For each order, there are two options: `Accept` & `Reject` which allows biller to accept or reject the order.
<img src="https://i.postimg.cc/sXTB61bL/pending-orders.png" alt="Add Product Page">

**All Orders :** In the All Orders section, biller can view a list of all orders. For each order, there is a `View Details` button to display the receipt or a detailed view of that order.
<img src="https://i.postimg.cc/bvX1kDjH/b-all-orders.png" alt="Add Product Page">

**Registered Customers :** In the Registered Customers section, biller can see a list of customers who have made purchases. For each customer, there is a `Order History` button to view all their orders of that particular customer.

**Customer Orders :** In the Customer Orders section, biller can view all orders placed by that particular customer.
<img src="https://i.postimg.cc/T35rKjB3/b-cust-orders.png" alt="Customer Orders">


## Technologies Used
**Spring Boot:** Provides a framework for developing Java applications quickly and easily, with built-in support for dependency injection and web development.

**JPA (Java Persistence API):** An object-relational mapping (ORM) library that simplifies database interactions by mapping Java objects to database tables.

**MySQL:** All the data from the application will be stored in the MySQL database.

**JSP (JavaServer Pages):** Used for server-side rendering of HTML templates.

**Bootstrap:** A front-end framework for responsive web design and styling.

## Folder Structure
- src/main/java/com/techm: Contains the Java source code for the application.

  -- controller: Spring MVC controllers.

  -- entity: JPA entities.

  -- enums: Enum classes.

  -- repository: JPA repositories.

  -- service: Service layer interfaces.

  -- service/impl: Service implementation classes.

- src/main/webapp/WEB-INF/views: Contains JSP files.
- src/main/resources/application.properties: Application configuration.

## ER Diagram from Database
<img src="https://i.postimg.cc/mkLSH26j/er-diagram.png" alt="ER Diagram">

## Getting Started
**Clone the repository:**
```bash
git clone https://github.com/sagarxt/payment-system.git
```
**Navigate to the project directory:**

```bash
cd paymentsystem
```
**Configure the database:**

Open the `application.properties` file located in the `src/main/resources` directory.
Update the database connection properties to match your MySQL setup, including schema name, username, and password.

**Build and run the application:**

```bash
mvn spring-boot:run
```

The application will start running on http://localhost:2000

Usage
Open a web browser and go to http://localhost:2000

### Contributing
Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request on the repository.
