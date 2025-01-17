# About

This app solves sudokus that have a dimension $n^2 \times n^2$ where $n \in \mathbf{R}^+$ (e.g. a typical sudoku is 9x9, $n = 3$). 

The application uses a tree recursion algorithm  to generate all possible solutions at a given position and continues until a generated board is completely full. The algorithm backtracks if the generated board is invalid.

# How to use

1. In SudokuApp.java, add or modify test cases to input your sudoku.
2. Use run_linux.sh if using a Linux distro or Mac and run_windows.bat if using windows to compile and run the program.
