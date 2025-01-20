CCWC (Coding Challenges Word Count)

CCWC is a simple Java-based command-line tool inspired by the Unix wc command. It allows users to count lines, words, and bytes in a file or from standard input. This tool supports flexible options for detailed analysis, making it a helpful utility for text processing tasks.

Features

Default Mode: Outputs the number of lines, words, and bytes in the provided file.

Option Mode:

-c: Count only bytes.

-l: Count only lines.

-w: Count only words.

Standard Input Support: Reads from standard input when no filename is provided.

Usage

Prerequisites

Java Development Kit (JDK) 8 or higher installed on your system.

Compilation

Navigate to the directory containing Ccwc.java.

Compile the program:

javac Ccwc.java

Run the Program

1. Default Mode

Counts lines, words, and bytes in a file:

java Ccwc <filename>

Example:

java Ccwc test.txt

Output:

   7145   58164  342190 test.txt

2. Option Mode

Count specific metrics with options:

Count bytes:

java Ccwc -c <filename>

Count lines:

java Ccwc -l <filename>

Count words:

java Ccwc -w <filename>

Example:

java Ccwc -l test.txt

Output:

   7145 test.txt

3. Standard Input Mode

Pipe input from another command or manually:

echo -e "Hello world!\nThis is a test." | java Ccwc

Output:

       2       5      25 stdin

Error Handling

Displays an error if the file does not exist.

Handles invalid options and provides usage instructions.

Example:

java Ccwc -z test.txt

Output:

Invalid option. Usage: ccwc [-c|-l|-w] <filename>

Code Structure

Main Features:

Default Behavior: Handles no-options mode to count lines, words, and bytes.

Option Handling: Supports -c, -l, and -w.

Standard Input Support: Reads piped input from System.in.

Methods:

processFile(String filePath): Processes the file for default behavior.

processContent(String content, String source): Computes line, word, and byte counts.

countBytes(String filePath): Counts bytes in a file.

countLines(String filePath): Counts lines in a file.

countWords(String filePath): Counts words in a file.

processInputFromStdin(): Handles input from standard input.

Limitations

Designed for text-based files; may not handle binary files accurately.

Limited to single-threaded execution.

Author

Developed as part of a coding challenge to replicate and enhance the functionality of the wc Unix command.
