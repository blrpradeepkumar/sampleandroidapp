# sampleandroidapp
A sample android automation project

# Some information regarding the framework.
The framework is developed using Page Object Model. 
The test data is read from an excel file. 
The test suite is maintained in a testNg.xml file.
It generates an extent report after all the tests have been completed. It captures screenshots for all the failed tests.

# OOPS implementation

- Encapsulation : All the pages have been encapsulated in a class and the variables inside them have been declared as private and can be accessed from outside the class using getters.

- Inheritance : The BaseClass has been declared as the parent class and all the other classes inherit the driver instance from the BaseClass.

- Abstraction and Polymorphism: The Product details like title and price have been declared in an interface and it is implemented in the Cart Page. The child class overrides the methods and implements them.

Extent reports can be found at - androidapp/target/surefire-reports/html/extent.html
TestNG reports can be found at - androidapp/test-output/emailable-report.html
