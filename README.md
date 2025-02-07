Mobile Test Automation Framework
A robust and scalable mobile test automation framework built with Java, Appium, and Cucumber, supporting both Android and iOS platforms.

🚀 Features
Cross-Platform Support: Run tests on both Android and iOS devices
BDD Approach: Cucumber integration for behavior-driven development
Page Object Model: Maintainable and reusable page objects
Reporting: Comprehensive test reports with Allure
Logging: Detailed logging with Log4j2
Retry Mechanism: Automatic retry for flaky tests
Configuration Management: Flexible configuration handling
Parallel Execution: Support for parallel test execution

🏗️ Architecture
automation_framework/
├── src/
│   ├── main/
│   │   └── java/com/example/
│   │       ├── base/           # Base classes
│   │       ├── core/           # Core functionality
│   │       ├── pages/          # Page objects
│   │       └── utils/          # Utilities
│   └── test/
│       ├── java/com/example/
│       │   ├── listeners/      # Test listeners
│       │   ├── runners/        # Test runners
│       │   └── steps/          # Step definitions
│       └── resources/
│           └── features/       # Cucumber feature files

🛠️ Tech Stack
Java 11
Maven
Appium 9.1.0
Cucumber 7.15.0
TestNG 7.8.0
Allure 2.24.0
Log4j2 2.22.1

📋 Prerequisites
Java JDK 11 or higher
Maven 3.8+
Appium Server
Android SDK / Xcode
Real devices or emulators/simulators

🚀 Getting Started

Clone the repository
git clone https://github.com/yourusername/mobile-automation-framework.git

Install dependencies
mvn clean install

Configure test environment
Update config.properties with your device details
Set up Appium server
Prepare test data

Run tests

mvn test -Dplatform=android
# or
mvn test -Dplatform=ios


📊 Test Reports
Generate Allure reports after test execution:
mvn allure:serve

🔧 Configuration
Device Configuration

# config.properties
platform=android
appPath=/path/to/app

Test Configuration
<!-- testng.xml -->
<suite name="Test Suite">
    <test name="Android Tests">
        <parameter name="platform" value="android"/>
        ...
    </test>
</suite>

📝 Writing Tests
Feature File
Feature: Login Functionality
  Scenario: Successful login
    Given I am on login screen
    When I enter valid credentials
    Then I should see the home screen

Step Definition
@Given("I am on login screen")
public void iAmOnLoginScreen() {
    // Implementation
}

✨ Best Practices
Use Page Object Model for better maintainability
Keep test data separate from test logic
Follow BDD principles for feature files
Implement proper logging
Use wait strategies instead of hard waits
Maintain cross-platform compatibility

🎯 Advantages
Maintainability: Clear separation of concerns
Reusability: Modular components
Readability: BDD approach with Cucumber
Reliability: Robust wait strategies
Scalability: Easy to extend and modify
Reporting: Comprehensive test reports

⚠️ Limitations
Requires proper Appium setup
Initial setup complexity
Platform-specific implementations needed
Device/emulator dependencies

🤝 Contributing
Fork the repository
Create your feature branch
Commit your changes
Push to the branch
Create a Pull Request

Happy Testing! 🚀
