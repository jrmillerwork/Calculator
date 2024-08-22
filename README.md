# Calculator 

This is the code for the Calculator assignment. A central requirement of
the project was that new arithmetic operations be added without changing
Calculator.java but that the enum Operation.java could be changed.

This requirement was met by associating a lambda expression which implements
the operation with the corresponding enum member.

Another requirement was graceful recovery from unsupported operations, but the
approach taken means that unsupported operations can't occur at run time.
For example, in order to add two numbers using the Calculator, one would
invoke the static calculate method: Calculator.calculate(Operation.ADD, 2, 3)
and it is clear that only members of the Operation enum can be specified or
a compiler error will occur.

However, the approach taken allows variable number of arguments to be provided in both
the calculate case and when chaining operations (which is supported as per requirements).
If for example Calculator were used in a command-line utility, a user might specify the
wrong number of arguments or perhaps zero as a divisor. The exception thrown in these
cases is IllegalOperationArgumentException which is a checked exception thrown by
the lambda expressions. Calculator itself re-throws this exception: I visualize
a user of Calculator (such as command-line utility) would catch the exception and
allow the user to re-enter the arguments.

The requirement to allow chaining operations was met (using something like the Builder Pattern) and here is an example which requires a Calculator instance:

double result = calculator.init(0).apply(Operation.ADD,100).apply(Operation.DIVIDE,2).apply(Operation.MULTIPLY,3).
    	apply(Operation.SUBTRACT, 120).apply(Operation.SQUARE).getResult();
    	
The init method allows the calculation to begin with an initial value. Any number of operations may be chained
and while ADD requires two arguments in the basic calculation approach, it takes just one explicit argument
when used in a chain, with the earlier operations providing the second argument. SQUARE takes a single argument
but zero explicit arguments when used in a chain. The getResult() method simply returns a value which was
holding the current value for the chain of operations.

A method was provided in Calculator which provides a list of strings representing all available operations. This
might be used in the "Help" of a command-line utility version of Calculator.

There are two separate Junit test classes, one for Calculator and one for the enum Operation -- the latter was
necessary due to the large amount of functionality incorporated into the enum.

## Note on Dependency Injection and a "Road not Taken":

Calculator is a very simple program, with only two major files plus a custom exception. No need for dependency
injection arose. However, an idea I found intriguing was considered: What if it was possible for a user
to specify a string representing a lambda and the name of a new operation that would be implemented by that
lambda? This would be a way of adding new operations without changing any source code at all!

Central to this is a way to convert a string into a lambda and a library for this does exist and it appears to work.

I envisioned an OperationManager class which kept a HashMap<String,String> of operation names and strings
representing lambdas. When a given operation was requested, the String would be converted to a lambda and
applied to arguments supplied.

Had this approach, clearly both more sophisticated and much more complex to implement, been taken, Spring's IoC/DI would have been a good choice. Spring JPA could have been used to persist the lambda Strings in a DB table. The 
OperationManager would have been a service which could have been switched out for testing and mocked.

Note that since the operations are represented by Strings, the need for an UnsupportedOperationException would
have arisen.

The Operation enum might have been changed for each new operation, although that would have detracted from the elegance of the approach since each dynamically added Operation would have required a code change, but dispensing
with the enum could have been considered.

In the interest of time and simplicity, the previously described approach was taken.

## Installation

git clone https://github.com/jrmillerwork/Calculator.git
