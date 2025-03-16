## For Reader

I have written comments and kept the code simple and understandable. If you have any questions, feel free to contact me:

<p align="center">
    <a href="https://github.com/Beka121">
        <img src="https://img.shields.io/badge/GitHub-090909?style=for-the-badge&logo=github&logoColor=white">
    </a>
    <a href="mailto:bekturemilev@gmail.com">
        <img src="https://img.shields.io/badge/Email-090909?style=for-the-badge&logo=gmail&logoColor=red">
    </a>
    <a href="https://t.me/Kaka_short">
        <img src="https://img.shields.io/badge/Telegram-090909?style=for-the-badge&logo=telegram&logoColor=26A5E4">
    </a>
</p>

---

## Features

- Performs basic arithmetic operations: `+`, `-`, `*`, `/`, `%`
- Supports advanced mathematical functions:
    - `pow(base, exponent)`: Raises a number to a given power
    - `sqrt(number)`: Computes the square root of a number
    - `abs(number)`: Returns the absolute value
    - `round(number)`: Rounds the number to the nearest integer
- Handles invalid inputs and provides error messages
- Prevents division by zero
- Maintains a history of calculations
- User-friendly interface with clear prompts

---

## Data Structures

- `List<String> history` → Stores previous calculations for review
- `Map<String, BiFunction<Double, Double, Double>> functions` → Stores predefined mathematical functions
- `Scanner scanner` → Handles user input
- `String expression` → Stores user-entered arithmetic expressions
- `Set<Character> operators` → Stores basic arithmetic operators for quick lookup
- `int pos, ch` → Tracks the position and current character in the input expression
- `boolean loop` → Controls the main execution loop
- `double memory` → Stores the last calculated result for recall

---

## Problems & Limitations

1. The program does not yet support variables or storing multiple past expressions.
2. The interface is text-based and does not include a graphical user interface (GUI).
3. I wanted to add it, but I didn't have time to create the frontend for the calculator.
3. Complex expressions with multiple nested parentheses might cause errors in some edge cases.
4. I also specifically added instructions to avoid such issues.
5. I had to spend more time than I originally planned—I didn't expect my knowledge to be insufficient.
6. Evulate file don't need it i tried to create a new class and use it in the main, but in my code it will doesn't work for some isusses. 

---

## Sample Usage

```plaintext
Welcome to the Calculator!
Do you need instructions? (Y/N): Y
Operators:
  Pow = pow(a, b) -> a^b
  Sqrt = sqrt(a) -> √a
  Abs = abs(a) -> |a|
  Round = round(a) -> nearest integer
Basic operators:
  Addition (+), Subtraction (-), Multiplication (*), Division (/), Remainder (%)

Please enter your arithmetic expression: 34 + (76 - 45) * 2 - abs(-5)
Result: 91
Do you want to continue? (Y/N): N
Thank you for using the Calculator!
```

---

## Test

C:\Users\user\.jdks\openjdk-23.0.1\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2024.3.2\lib\idea_rt.jar=54110:C:\Program Files\JetBrains\IntelliJ IDEA 2024.3.2\bin" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath C:\Users\user\Calculator\out\production\Calculator Calculator

Welcome to the Calculator!

Do you need instructions? (Y/N)

y

Operators:
Pow = pow(a, b) -> a^b
Sqrt = sqrt(a) -> √a
Abs = abs(a) -> |a|
Round = round(a) -> nearest integer
Basic operators:
Addition (+), Subtraction (-), Multiplication (*), Division (/), Remainder (%)

Please enter your arithmetic expression: 34+(76-45)*2 - abs(-5)

Result: 91.0
Do you want to view history? (Y/N)

y
Calculation History:

34+(76-45)*2 - abs(-5) = 91.0

Do you want to continue? (Y/N)

y
Please enter your arithmetic expression: 2+2

Result: 4.0

Do you want to view history? (Y/N)

n

Do you want to continue? (Y/N)

n

Thank you for using the Calculator!


Process finished with exit code 0

---

## Submission Requirements

- Source code with proper comments and documentation
- At least three different sample input files
- Example outputs and screenshots
- A short report explaining the project, design choices, and encountered challenges

## Thank you for your attention
