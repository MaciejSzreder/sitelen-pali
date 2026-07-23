# Sitelen Pali

**Sitelen Pali** ("action characters") is a planned Toki Pona interpreter prototype to be implemented in Java. The project aims to map Toki Pona statements to programming constructs so the language can express ideas in a human-friendly form.

The core paradigms targeted by Sitelen Pali are ⬜ logical, ⬜ imperative, ⬜ procedural, and ⬜ object-oriented programming.

This repository captures the design, specifications, and examples for Sitelen Pali. Implementation work and tests are ongoing; the project is an active work in progress and should not be assumed complete.

## Overview

Sitelen Pali treats different Toki Pona statement types as programming constructs:

- [ ] Informational statements → facts (declarative knowledge)
- [ ] Conditional informational statements → logical rules
- [ ] Questions → queries (inference and search)
- [ ] Imperative statements → commands that mutate state
- [ ] Conditional imperative statements → conditional control flow

### Supported Paradigms
- [ ] Logical programming
- [ ] Imperative programming
- [ ] Procedural programming
- [ ] Object-oriented programming

## Features

- [ ] Human-readable syntax based on Toki Pona grammar
- [ ] Sitelen Lasina input validation
- [ ] Unification and logical inference
- [ ] Imperative control flow primitives
- [ ] Procedural abstractions
- [ ] Simple OOP constructs
- [ ] Interactive REPL and example programs

## Development

The project is primarily developed by AI agents. Human roles are intentionally limited to:
- Generating prompts and high-level specifications
- Committing changes to version control
- Managing branches (creating, deleting, pulling, pushing)

AI agents perform implementation, testing, and iterative refinement under those human directions.

This repository will not store AI data.

## Sitelen Pali compilation

Compile Sitelen Pali with:

```powershell
javac IloPiSitelenPali.java
```

## Sitelen Pali execution

Run Sitelen Pali with:

```powershell
java IloPiSitelenPali
```

To read from a file instead of standard input:

```powershell
java IloPiSitelenPali --input input.txt
```

To enable logging and send warnings and errors to a log file:

```powershell
java IloPiSitelenPali --log sitelen-pali.log
```

When a log file is provided, warning and error messages are written there in English, while standard output remains in Toki Pona.

Ensure a JDK is installed and that the `java` and `javac` commands are available in your environment.

## Technologies

- Java SDK: 26.0.1
- Public Toki Pona text corpuses for validation testing

## Links

- Official Toki Pona site: [tokipona.org](https://tokipona.org)
- Dictionary: [linku.la](https://linku.la)

---

**Sitelen Pali** brings the minimalist philosophy of Toki Pona to programming language design; the repository documents the project's ongoing design and development.
