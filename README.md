# FloraLens

FloraLens is an advanced, offline-first botanical diagnostic engine and digital garden management application for Android. Built entirely with Jetpack Compose and powered by on-device TensorFlow Lite edge inference, FloraLens provides instantaneous, privacy-preserving plant disease classification without requiring network connectivity.

## Table of Contents
1. [Overview](#overview)
2. [Key Features](#key-features)
3. [Technical Architecture](#technical-architecture)
4. [Machine Learning Engine](#machine-learning-engine)
5. [Getting Started](#getting-started)
6. [Building from Source](#building-from-source)
7. [License](#license)
8. [Citation](#citation)

---

## Overview

FloraLens bridges the gap between botanical sciences and modern mobile software engineering. By heavily utilizing local compute capabilities, the application processes camera frames in real-time, applying algorithmic organic-matter heuristics before executing a deep learning diagnostic pipeline. This architecture ensures high-fidelity results while completely preserving user data privacy.

## Key Features

* **Real-Time Edge Diagnostics:** Scans and analyzes botanical specimens locally with near-zero latency.
* **Offline-First Architecture:** The core diagnostic engine functions autonomously without requiring active internet connectivity.
* **Algorithmic Heuristic Filtering:** Pre-filters camera frames for organic pixel density (HSV modeling) to prevent false-positive classifications on non-botanical objects.
* **Infinite Scalable Timeline:** A sophisticated, dynamically rendered S-Curve spline timeline that meticulously logs diagnostic history across an unbounded number of plant profiles.
* **Fluid Adaptive UI:** Micro-interaction driven interfaces, utilizing constraint-based layouts and physics-backed animations (springs, tweens) for a premium tactile experience.
* **PDF Report Generation:** Automated compilation of diagnostic data, visual records, and historical context into exportable, structured PDF documents.
* **Adaptive Vector Branding:** Hardware-accelerated, mathematically perfect SVG scaling across all Android 8.0+ launcher masks.

## Technical Architecture

The application is structured around modern Android development paradigms:

* **Language:** Kotlin
* **UI Toolkit:** Jetpack Compose (Material Design 3)
* **Architecture:** Unidirectional Data Flow (UDF) / MVVM
* **Concurrency:** Kotlin Coroutines & StateFlow
* **Machine Learning:** TensorFlow Lite (TFLite)
* **Local Storage:** Context-bound structured persistence

## Machine Learning Engine

FloraLens utilizes a highly optimized MobileNet-based convolutional neural network (CNN) trained extensively on diverse botanical disease datasets. 

To guarantee precision in varied lighting conditions, the classification pipeline features:
* Pre-classification organic matter isolation.
* Artificial confidence thresholds to heavily bias toward healthy baselines, mitigating false-positive disease flags.
* Hardware-accelerated tensor parsing (NNAPI compatible).

## Getting Started

Pre-compiled APKs are available in the repository. 

1. Navigate to the **Releases** section.
2. Download the latest `FloraLens-vX.X.X.apk`.
3. Install the application on an Android device running API Level 26 (Android 8.0) or higher.

## Building from Source

To compile the application locally:

1. Clone the repository.
2. Open the project in Android Studio (Giraffe or newer recommended).
3. Synchronize Gradle dependencies.
4. Execute `./gradlew assembleDebug` to build the testing APK, or `./gradlew bundleRelease` for production deployment.

## License

Copyright (c) 2026 Priyanshu

This software is provided "as is", without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose and noninfringement. In no event shall the authors or copyright holders be liable for any claim, damages or other liability, whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the software.

## Citation

If you utilize the architecture, UI paradigms, or machine learning integration methodology found in this repository for academic or commercial research, please provide attribution:

```text
Author: Priyanshu
Project: FloraLens
Year: 2026
```
