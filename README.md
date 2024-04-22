# Basket Splitter

The basket-splitter program is a Java-based application that is designed to manipulate basket data. The main task of the application is to divide the items in the customer's basket into delivery groups. 
The application is capable of validating JSON structures, specifically checking if a given field exists within the structure. The application uses a configuration file that contains possible delivery methods for all products offered in the store. 
This file is read by the library implementation. Products in the basket are passed to the library API in the form of a list with product names. 
To perform the division, a definition of possible delivery methods for products offered in our store is also needed, which is stored in the configuration file. The key in the map is the product name, and the value is a list with possible delivery methods for a given product.

## Description

The `basket-splitter` application is designed to handle and manipulate basket data. The application is capable of validating JSON structures, specifically checking if a given field exists within the structure.
Aim of the project is to make a library that will divide the items in the customer's basket into delivery groups. For the library to work correctly, it must read a configuration file that contains possible delivery methods for all products offered in the store. 
Since this configuration does not change often, it is stored in a file that is to be read by library implementation.
- Input data: The program is to divide the products in the basket. They will be passed to the library API in the form of a list with product names. An example of an algorithm parameter (List<String> items) is:

```json
[
  "Steak (300g)",
  "Carrots (1kg)",
  "Soda (24x330ml)",
  "AA Battery (4 Pcs.)",
  "Espresso Machine",
  "Garden Chair"
]
```
- Example json config file:
```json
{
 "Carrots (1kg)": ["Express Delivery", "Click&Collect"],
 "Cold Beer (330ml)": ["Express Delivery"],
 "Steak (300g)": ["Express Delivery", "Click&Collect"],
 "AA Battery (4 Pcs.)": ["Express Delivery", "Courier"],
 "Espresso Machine": ["Courier", "Click&Collect"],
 "Garden Chair": ["Courier"]
}
```
## The algorithm should divide products to delivery methods following this conditions:
 - Products should be divided into smallest possible number of deliveries.
 - The biggest group should contain as many products as possible.

Expected result:
```json
 {
 "Express Delivery": ["Steak (300g)", "Carrot (1kg)",
  "Cold Beer (330ml)", "AA Battery (4 Pcs.)"],
 "Courier": ["Espresso Machine", "Garden Chair"]
 }
```
# Building the project
To build the project, navigate to the project directory and run the following command:
```bash
mvn clean install
```
This will compile the source code, run the tests, and package the compiled code into a JAR file.

# Running the Project
After building the project, you can run the application using the following command:
```bash
java -jar target/basket-splitter-1.0-SNAPSHOT-jar-with-dependencies.jar
```
Please replace basket-splitter-1.0-SNAPSHOT-jar-with-dependencies.jar with the actual name of the JAR file if it's different
## Prerequisites

- Java 17
- Maven

## Dependencies

The project uses the following dependencies:

- JUnit 4.11 for testing
- AssertJ 3.25.2 for fluent assertions in tests
- Jackson Databind 2.16.0 for JSON processing
- JUnit Jupiter 5.10.2 for running tests on the JUnit 5 platform
