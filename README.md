# [Fern Wallet: money manager](https://github.com/mage1k99/fern-wallet)

[![Latest Release](https://img.shields.io/github/v/release/mage1k99/fern-wallet)](https://github.com/mage1k99/fern-wallet/releases)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![GitHub Repo stars](https://img.shields.io/github/stars/mage1k99/fern-wallet?style=social)](https://github.com/mage1k99/fern-wallet/stargazers)


Fern Wallet is a free and open source **money management android app**. It's written using **100% Kotlin and Jetpack Compose**. It's designed to help you keep track of your personal finances with ease.

Think of Fern Wallet as a manual expense tracker that tries to replace the good old spreadsheet for managing your finances.

Fern Wallet (formerly Ivy Wallet) is a fork of the original Ivy Wallet project, aiming to continue its legacy of simplicity and powerful financial tracking.

**Do you know? Ask yourself.**

1) How much money do I have in total?

2) How much did I spend this month and what did I spend it on?

3) How much can I spend and still meet my financial goals?

A money management app can help you answer these questions.

Fern Wallet shines in its user interface and experience, as well as its simplicity and customization options.

## Project Requirements

- Java 17+
- The **latest stable** Android Studio (for easy install use [JetBrains Toolbox](https://www.jetbrains.com/toolbox-app/))

### Initialize the project

**1. Clone the repo**

```bash
git clone https://github.com/mage1k99/fern-wallet.git
```

## Tech Stack

### Core

- 100% [Kotlin](https://kotlinlang.org/)
- 100% [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Material3 design](https://m3.material.io/) (UI components)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) (structured concurrency)
- [Kotlin Flow](https://kotlinlang.org/docs/flow.html) (reactive data stream)
- [Hilt](https://dagger.dev/hilt/) (DI)
- [ArrowKt](https://arrow-kt.io/) (functional programming)


### Testing
- [JUnit4](https://github.com/junit-team/junit4) (test framework, compatible with Android)
- [Kotest](https://kotest.io/) (unit test assertions)
- [Paparazzi](https://github.com/cashapp/paparazzi) (screenshot testing)

### Local Persistence
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) (key-value storage)
- [Room DB](https://developer.android.com/training/data-storage/room) (SQLite ORM)

### Networking
- [Ktor client](https://ktor.io/docs/getting-started-ktor-client.html) (HTTP client)
- [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) (JSON serialization)

### Build & CI
- [Gradle KTS](https://docs.gradle.org/current/userguide/kotlin_dsl.html) (Kotlin DSL)
- [Gradle convention plugins](https://docs.gradle.org/current/samples/sample_convention_plugins.html) (build logic)
- [Gradle version catalogs](https://developer.android.com/build/migrate-to-catalogs) (dependencies versions)
- [GitHub Actions](https://github.com/mage1k99/fern-wallet/actions) (CI/CD)
- [Fastlane](https://fastlane.tools/) (uploads the app to the Google Play Store)

### Other
- [Firebase Crashlytics](https://firebase.google.com/products/crashlytics) (stability monitoring)
- [Timber](https://github.com/JakeWharton/timber) (logging)
- [Detekt](https://github.com/detekt/detekt) (linter)
- [Ktlint](https://github.com/pinterest/ktlint) (linter)
- [Slack's compose-lints](https://slackhq.github.io/compose-lints/) (linter)

## License

This project is licensed under the GPL-3.0 License - see the [LICENSE](LICENSE) file for details.
