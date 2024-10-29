Feature: Create an account on Magento

  Scenario Outline: User creates a new account
    Given the user is on the Magento homepage
    When the user clicks on "Create an Account" hyperlink
    And the user enters details with "<firstname>" , "<lastname>" , "<Email Address>" , "<password>"
    And the user submits the form
    Then the account should be created successfully
    Then user should see the message "Thank you for registering with Main Website Store."

    Examples:
      | firstname | lastname | Email Address       | password  |
      | John      | Doe      | johndoe@exnpp83.com | Secure123 |

  Scenario: User creates an account with missing required fields
    Given the user is on the Magento homepage
    When the user clicks on "Create an Account" hyperlink
    And the user submits the form
    Then an error message "This is a required field." is displayed for each input fields.

  Scenario Outline: User create an account with an invalid email format
    Given the user is on the Magento homepage
    When the user clicks on "Create an Account" hyperlink
    And the user enters details with "<firstname>" , "<lastname>" , "<email_address>" , "<password>"
    And the user submits the form
    Then an error message "Please enter a valid email address (Ex: johndoe@domain.com)." is displayed.

    Examples:
      | firstname | lastname | email_address       | password  |
      | John      | Doe      | johndoe.com         | Secure123 |

  Scenario Outline: User create an account with a weak password
    Given the user is on the Magento homepage
    When the user clicks on "Create an Account" hyperlink
    And the user enters details with "<firstname>" , "<lastname>" , "<email_address>" , "<password>"
    And the user submits the form
    Then validation error for password strength is displayed

    Examples:
      | firstname | lastname | email_address         | password  |
      | John      | Doe      | johndoe@examp13.com   | 123       |