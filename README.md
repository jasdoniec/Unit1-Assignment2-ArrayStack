# Unit 1 - Assignment 2: Generic Array Stack

## Overview
In this assignment, you will implement a **Stack** Abstract Data Type (ADT) using a dynamically resizing 1D array. A Stack is a **LIFO** (Last-In, First-Out) data structure where elements are added and removed from the same end (the "top").

Your implementation must satisfy `StackInterface<T>` and pass all unit tests in `ArrayStackTest`.

---

## Working with Generics (`<T>`)

The `<T>` syntax in `StackInterface<T>` and `ArrayStack<T>` represents a **Generic Type Placeholder**. Instead of creating separate stack implementations for `Integer`, `String`, or `Double`, generics allow one stack implementation to store any reference object type.

### Generic Array Instantiation
In Java, you **cannot** instantiate a generic array directly due to type erasure:
```java
// THIS WILL NOT COMPILE:
T[] stack = new T[1]; 
```

To create a generic array, instantiate an array of `Object` (the parent class of all Java objects) and explicitly cast it to type `T[]`:
```java
// USE THIS EXACT SYNTAX:
stack = (T[]) new Object[capacity];
```
Use this exact pattern in your `ArrayStack` constructor and inside your dynamic resizing helper method.

---

## Method Implementation Details

You must complete the following methods inside `ArrayStack.java`:

| Method | Return Type | Description / Requirements |
| :--- | :--- | :--- |
| **`ArrayStack()`** | Constructor | Initializes the internal array to an initial capacity of `1` (`(T[]) new Object[1]`) and sets `size` to `0`. |
| **`size()`** | `int` | Returns the logical size (number of elements currently stored). |
| **`empty()`** | `boolean` | Returns `true` if `size == 0`, `false` otherwise. |
| **`peek()`** | `T` | Returns the top element without removing it. **Must throw `StackUnderflowException`** if the stack is empty. |
| **`pop()`** | `T` | Removes and returns the top element. Clears the reference (`null`) to prevent memory leaks, decrements `size`, checks capacity, and returns the element. **Must throw `StackUnderflowException`** if the stack is empty. |
| **`push(T item)`** | `T` | Checks array capacity, places `item` at the top of the stack, increments `size`, and returns `item`. |
| **`clear()`** | `void` | Removes all elements from the stack, resetting `size` to `0` and clearing array references. |
| **`search(Object o)`** | `int` | Returns the 1-based position of object `o` relative to the top of the stack. Returns `-1` if not found. **The stack state must remain unchanged after search ends.** |

---

## Dynamic Resizing Rules

To prevent array overflows and minimize wasted memory, implement a private helper method (e.g., `checkSize()`) to dynamically resize the backing array based on capacity:

* **Doubling Capacity:** When pushing an element into an array that is completely full (`size == stack.length`), double the capacity (`stack.length * 2`).
* **Halving Capacity:** When popping an element leaves the stack less than 1/4 full (`size < stack.length / 4`), cut the capacity in half (`stack.length / 2`).

---

## 1-Based Search Position Logic

The `search(Object o)` method does not return a 0-based array index. Instead, it returns a 1-based position counting downward from the top element of the stack:

```text
[ Top Element ]    <-- Position 1 
[ Second Element ] <-- Position 2 
[ Third Element ]  <-- Position 3 
```

* If elements `"A"`, `"B"`, and `"C"` are pushed in order, `"C"` is at position `1`, `"B"` is at position `2`, and `"A"` is at position `3`.
* If the item is not present on the stack, return `-1`.

---

## Testing Your Implementation

Run `ArrayStackTest.java` in VS Code to execute the unit test suite. The tester verifies:

1. **Interface Compliance:** Ensures `StackInterface<T>` signatures are preserved.
2. **Encapsulation:** Verifies all instance variables in `ArrayStack` are marked `private`.
3. **Exception Handling:** Confirms `StackUnderflowException` is thrown correctly when calling `pop()` or `peek()` on an empty stack.
4. **Logical Size & Emptiness:** Tests state updates after operations.
5. **Dynamic Resizing:** Stress-tests pushing up to 1,000 items and popping them back down to check capacity bounds.
6. **Search Operations:** Verifies 1-based indexing accuracy and ensures `search()` leaves the stack elements in their original positions.
