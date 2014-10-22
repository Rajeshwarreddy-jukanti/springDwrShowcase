springDwrShowcase
=================

This demo project showcases the difference in using Spring MVC and DWR for web development. 
DWR is an Ajax framework for the Java platform that allows the Javascript to access Java controllers directly in an RMI fashion.

When running the application you can switch between two pages, both showing exactly the same information, but the first uses Spring MVC and the other one uses DWR.
Looking at the source code will reveal the real difference and how easy to use is DWR comparing to Spring MVC.  

The code to look at is:
DwrEmployeeController.java	 	( shows DWR style)
SpringEmployeeController.java	( shows Spring style)


The project uses AngularJS as a front end framework for simplicity. 
