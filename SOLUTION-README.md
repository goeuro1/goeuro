GoEuro Problem Set
==================

Build Instructions
------------------

My solution is built with apache ant using the create-run-jar target.

    ant create-run-jar
    
Run Instructions
----------------

My solution will run as specified by the problem statement.

    java -jar GoEuroTest.jar "STRING"
    
Assumptions
--------------

The problem statement is underspecified (perhaps on purpose). 

* FILE NAME

It states that:

> The program should query the API with the user
> input and create a CSV file from it.
    
However it does not say the name the file should take.

My assumption was that actually creating a file was
not important and printing the output to System.out
is sufficient. That said, users are able to specify
a file name themselves as such:

    java -jar GoEuroTest.jar ber > ber.txt
    
Alternate solutions could be to generate a name based on the input
or add a second parameter to the input specifying a name. My solution
could accommodate either with minimal change.

* POURPOUSE

I assume this will only be used as a stand alone application
and is not meant to be included as a jar in a larger project.
