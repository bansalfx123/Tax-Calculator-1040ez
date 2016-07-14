Problem/Requirements: 
•	Design and code an implementation of a small tax engine module that can process the 1040 EZ form represented in the picture below. The business rules are stated within the picture. 
•	Think of this module as an engine/framework that would be scaled to process thousands of different forms yearly while the forms and rules are being maintained by non-engineers / Tax experts. Tax Experts should be able to change data to change the makeup of a given form’s presentation, model, and business rules. 
•	To iterate, KEY considerations are: 
o	The form makeup and business rules can change yearly or more. 
o	Business tax experts, non-Engineers, will be the ones that change the forms. 
o	The engine that you build should be able to be used to scale out and process thousands of different forms, not just the 1040EZ. 
o	Consider that some of those forms may depend on each other. For example, I may need to Process Form A before Form B because Form B has a section that needs the values from Form A. 
•	Complex UI is not required. Inputs and computed outputs could be implemented through the command-line. 

See the following example command-line.

Example Input line 1: Please enter your first name and initial: 
> Jane M.
Example Input line 2: Please enter your last name.
> Doe

Example Output: 

First Name and Initial: Jane M. 
Last Name: Doe

Wages, Salary, Tips:   $1495
Taxable Interest: $100
…
…
Refund : $xx.xx
Amount You Owe: $0
