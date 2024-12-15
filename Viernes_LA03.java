import java.util.Scanner;

class Stack {
    private int maxSize;
    private int top;
    private int[] stackArray;

    public Stack(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1;
    }

    public void push(int value) {
        if (top >= maxSize - 1) {
            System.out.println("Stack is full. Can't push " + value);
            return;
        }
        stackArray[++top] = value;
    }

    public int pop() {
        if (top < 0) {
            System.out.println("Stack is empty. Can't pop.");
            return -1;
        }
        return stackArray[top--];
    }

    public void display() {
        System.out.print("Stack: ");
        for (int i = 0; i <= top; i++) {
            System.out.print(stackArray[i] + " ");
        }
        System.out.println();
    }
}

class Queue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] queueArray;

    public Queue(int size) {
        maxSize = size;
        queueArray = new int[maxSize];
        front = 0;
        rear = 0;
    }

    public void enqueue(int value) {
        if ((rear + 1) % maxSize == front) {
            System.out.println("Queue is full. Can't enqueue " + value);
            return;
        }
        queueArray[rear] = value;
        rear = (rear + 1) % maxSize;
    }

    public int dequeue() {
        if (front == rear) {
            System.out.println("Queue is empty. Can't dequeue.");
            return -1;
        }
        int value = queueArray[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public void display() {
        System.out.print("Queue: ");
        int i = front;
        while (i!= rear) {
            System.out.print(queueArray[i] + " ");
            i = (i + 1) % maxSize;
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Stack stack = new Stack(5);
        Queue queue = new Queue(5);

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Stack Operations");
            System.out.println("2. Queue Operations");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            if (choice == 1) {
                while (true) {
                    System.out.println("\nStack Operations:");
                    System.out.println("1. Push");
                    System.out.println("2. Pop");
                    System.out.println("3. Display");
                    System.out.println("4. Back");

                    int stackChoice = scanner.nextInt();

                    if (stackChoice == 1) {
                        System.out.println("Enter an item to push:");
                        int item = scanner.nextInt();
                        stack.push(item);
                        stack.display();
                    } else if (stackChoice == 2) {
                        int item = stack.pop();
                        if (item!= -1) {
                            System.out.println("Popped item: " + item);
                        }
                        stack.display();
                    } else if (stackChoice == 3) {
                        stack.display();
                    } else if (stackChoice == 4) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else if (choice == 2) {
                while (true) {
                    System.out.println("\nQueue Operations:");
                    System.out.println("1. Enqueue");
                    System.out.println("2. Dequeue");
                    System.out.println("3. Display");
                    System.out.println("4. Back");

                    int queueChoice = scanner.nextInt();

                    if (queueChoice == 1) {
                        System.out.println("Enter an item to enqueue:");
                        int item = scanner.nextInt();
                        queue.enqueue(item);
                        queue.display();
                    } else if (queueChoice == 2) {
                        int item = queue.dequeue();
                        if (item!= -1) {
                            System.out.println("Dequeued item: " + item);
                        }
                        queue.display();
                    } else if (queueChoice == 3) {
                        queue.display();
                    } else if (queueChoice == 4) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}