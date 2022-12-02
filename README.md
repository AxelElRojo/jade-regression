# jade-regression
Artificial Intelligence agents implemented in JADE (Java Agent Development framework).

# Algorithms
The implemented algorithms are the following:
- Linear Regression.
- Multiple Linear Regression (via matrix optimization).
- Linear Regression (via gradient descent).
- Multiple Logistic Regression (via gradient descent).

# Packages
All packages contained in the repo start with `dev.moralestorres`, there's a total of 4 packages:
- `dev.moralestorres.util`: Utility classes for the algorithms, contains a GUI for the agents, a Matrix class and two records that function as dataset
holders.
- `dev.moralestorres.algorithms`: Contains the algorithm implementations.
- `dev.moralestorres.behaviours`: Contains the behaviours for the agents, there's one for each algorithm.
- `dev.moralestorres.agents`: Contains the agents executed with JADE, there's also one for each algorithm.

# Compilation & Execution
The agents were compiled and ran with OpenJDK version 19.0.1, same version as the Java runtime environment. The Eclipse IDE was used for all development.
