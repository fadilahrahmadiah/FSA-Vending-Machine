# FSA-Vending-Machine
This project is a Java-based simulation of a vending machine using Finite State Automata (FSA) to model state transitions for product selection, payment processing, and transaction completion. Designed for educational purposes to demonstrate state-based logic without physical hardware.

## Transition Table
  
| δ   | a   | b   | c   | d   | e   | f   | g   | h   | i   | j   |
|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|
| q0  | q1  | q2  | q3  | q4  | -   | -   | -   | -   | -   | -   | 
| q1  | -   | -   | -   | -   | q5  | q6  | -   | -   | -   | -   |
| q2  | -   | -   | -   | -   | q5  | q6  | -   | -   | -   | -   | 
| q3  | -   | -   | -   | -   | q5  | q6  | -   | -   | -   | -   |
| q4  | -   | -   | -   | -   | q5  | q6  | -   | -   | -   | -   |
| q5  | -   | -   | -   | -   | -   | -   | q7  | q8  | -   | -   |
| q6  | -   | -   | -   | -   | -   | -   | -   | -   | q9  | -   |
| q7  | -   | -   | -   | -   | -   | -   | -   | -   | -   | q10 |
| q8  | -   | -   | -   | -   | -   | -   | -   | -   | -   | q10 | 
| q9  | -   | -   | -   | -   | -   | -   | -   | -   | -   | q10 |
| q10 | -   | -   | -   | -   | -   | -   | -   | -   | -   | -   |

## Diagram
<p align="center">
  <img width="260" height="260" alt="image" src="https://github.com/user-attachments/assets/65acf235-b390-435c-a831-3ac3af07fdd3" />
</p>

## Documentation
<p align="center">
  <img src="https://github.com/user-attachments/assets/f1f7368a-9d09-4d0d-b61e-414182691236" alt="image" width="600" />
  <img src="https://github.com/user-attachments/assets/6d5ec73f-550a-44e2-ba8b-f2ec8951fb46" alt="image" width="600" />
</p>

## License
This project is licensed under the MIT License. See the [MIT License](https://choosealicense.com/licenses/mit/).

## Notes

- This project focuses on logic flow using FSA, not physical vending machine mechanics.
- Can be extended to include more states, and error handling for more complex simulations.

## Tags
[![Java](https://img.shields.io/badge/Java-007396?style=flat&logo=java&logoColor=white)](https://github.com/search?q=Java) [![Simulation](https://img.shields.io/badge/Simulation-00C853?style=flat)](https://github.com/search?q=Simulation) [![FSA](https://img.shields.io/badge/FSA-blue?style=flat)](https://github.com/search?q=FSA) [![Finite State Machine](https://img.shields.io/badge/Finite_State_Machine-2196F3?style=flat)](https://github.com/search?q=Finite+State+Machine) [![Vending Machine](https://img.shields.io/badge/Vending_Machine-FF5722?style=flat)](https://github.com/search?q=Vending+Machine)  
