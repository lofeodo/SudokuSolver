#!/bin/bash
javac src/*.java -d bin
java -cp bin SudokuApp
echo Sudokus solved!
read -n 1 -s -r -p "Press any key to continue..."
echo