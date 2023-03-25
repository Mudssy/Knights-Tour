# Knight's Tour Solver
This program is written in Scala and solves the famous Knight's Tour problem. Given a chessboard of any size and a starting position for the knight, the program outputs a list of positions that the knight can take to visit every square on the board exactly once.

# Requirements
Scala 2.12


# Algorithm
The program uses a backtracking recursive algorithm to solve the Knight's Tour problem. At each step, the program considers all possible moves for the knight. If a move leads to a dead end, the program backtracks and tries a different move.
