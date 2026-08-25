# huffman-coding

# Huffman Coding — Text Compression

A Java implementation of Huffman Coding for lossless text compression, developed as part of a Data Structures and Algorithms assignment.

The application constructs a Huffman coding tree from a predefined character-frequency table and provides a graphical interface for encoding and decoding messages.

## Overview

Huffman coding is a lossless compression technique that assigns shorter binary codes to more frequently occurring characters and longer codes to less frequently occurring characters.

The application implements the three main stages of Huffman coding:

1. Building the Huffman tree
2. Encoding text into binary Huffman codes
3. Decoding binary Huffman codes back into text

A graphical user interface allows the user to enter a message, encode it, decode a bit sequence, and view the resulting output.

## Features

- Generate a Huffman coding tree from a character-frequency table
- Encode messages using variable-length Huffman codes
- Decode binary Huffman codes back into their original text
- Calculate the compression ratio of encoded messages
- Graphical user interface built with Java
- Input validation for encoding and decoding
- Test cases demonstrating application behaviour

## Huffman Coding

The Huffman tree is a reference-based binary tree in which:

- A left branch represents `0`
- A right branch represents `1`
- Leaf nodes represent characters
- The path from the root to a leaf determines that character's Huffman code

Characters with higher frequencies receive shorter codes, while less frequent characters receive longer codes.

For example, traversing:

```text
Root → Left → Right → Left

produces the Huffman code:

010

for the character stored at that leaf.

Data Structures

The implementation uses a custom binary tree structure consisting of TreeNode objects.

The main components are:

TreeNode

Represents an individual node in the Huffman tree.

Nodes store information such as:

Character/symbol
Frequency
Left child
Right child
HuffmanTree

Responsible for:

Constructing the Huffman tree
Traversing the tree
Generating character codes
Encoding messages
Decoding binary strings
HuffmanGUI

Provides the graphical user interface through which users can:

Enter text
Encode messages
Decode Huffman bit sequences
View the resulting output
View compression information
Algorithm

The Huffman tree is constructed from the supplied frequency table.

The general process is:

Create a tree node for each character and its frequency.
Identify the two nodes with the lowest frequencies.
Combine them into a new parent node.
Assign the combined frequency to the parent.
Repeat until a single root node remains.
Traverse the resulting tree to determine the binary code for each character.

The implementation was designed using fundamental data structures rather than relying on Java's built-in priority queue or hashmap implementations.

Encoding

To encode a message, each character is located in the Huffman tree.

The tree is traversed from the root toward the corresponding character:

Going left appends 0
Going right appends 1

The resulting binary strings are concatenated to produce the encoded message.

Decoding

Decoding uses the Huffman tree to reconstruct the original message.

Starting from the root:

0 moves to the left child
1 moves to the right child
Reaching a leaf produces the corresponding character
The traversal then returns to the root for the next character

This process continues until the complete bit sequence has been decoded.

Compression Ratio

The application calculates the compression ratio by comparing the Huffman encoded representation against a fixed-length representation of 7 bits per character, corresponding to the ASCII assumption specified by the assignment.

The compression ratio provides an indication of how effectively Huffman coding reduces the size of the message.

Project Structure
huffman-coding/
│
├── src/
│   ├── HuffmanGUI.java
│   ├── HuffmanTree.java
│   └── TreeNode.java
│
├── docs/
│   └── HuffmanProjectReport.pdf
│
└── screenshots/
    ├── test-01.png
    ├── test-02.png
    ├── test-03.png
    ├── test-04.png
    ├── test-05.png
    ├── test-06.png
    └── test-07.png
Testing

The application was tested using a collection of encoding and decoding test cases.

Testing covered:

Encoding valid messages
Decoding valid Huffman bit sequences
Encoding and subsequently decoding messages to verify that the original message is recovered
Compression ratio calculations
GUI input and output behaviour
Handling of invalid input

Screenshots of the test cases are available in the screenshots directory.

Technologies
Java
Object-Oriented Programming
Binary Trees
Recursion
Huffman Coding
Graphical User Interface development
Academic Project

This project was developed as part of the Data Structures and Algorithms module.

The assignment focused on implementing Huffman Coding while applying fundamental data structures and algorithms without using Java's built-in PriorityQueue or HashMap classes.

Documentation

The docs directory contains the full project report, including the program design, implementation discussion and testing documentation.
