Feature: Login

Scenario: Successful login
    Given I am on the login page
    When I click on the X button
    Then The main page should be opened
    When I enter my destination
    Then The destination modal should be opened
    When I enter Cusco text
    And All items contain the search word
    And I select the first item
    Then The calendar should be opened
    And I scroll in the calendar
    And I select the start date
    Then The select date button should be disabled
    And I select the end date
    Then The select date button should be enabled
    And The date range should be correct
    And The number of nights should be correct
    And I click on the select dates button
    Then The destination should be set
    And The range date should be set
    And I click on the room field
    Then The room and guest modal should be opened
    And I select the children quantity
    Then The age of child modal should be opened
    And I scroll the age
    And I click on the 5 years old option
    And I click on the OK button
    Then The childrens age at check out should be displayed
    And The number of children should be correct
    And The childrens age selected should be correct
    And The apply button should be enabled
    And I click on the apply button
    Then The number of guests should be set
    And I click on the search button
    Then The properties list should be displayed
    And I scroll until the item number
    And I extract the stay details text
    And I extract the amount text
    And I extract the taxes and charges text
    And I select the second option
    Then The select room modal should be opened
    And The total amount in property details should be correct
    And The taxes amount in property details should be correct
    And I select the room
    Then The total amount in room details should be correct
    And The taxes amount in room details should be correct
    And I click on the reserve button
    And I enter the first name
    Then The add missing details button should be displayed
    And I enter the last name
    Then The add missing details button should be displayed
    And I enter the email
    Then The add missing details button should be displayed
    And I enter the address
    Then The add missing details button should be displayed
    And I enter the zip code
    Then The add missing details button should be displayed
    And I enter the city
    Then The add missing details button should be displayed
    And I scroll the form
    And I select the country
    Then The add missing details button should be displayed
    And I enter the phone number
    Then The next step button should be displayed
    And I select the purpose option
    Then The total amount in fill info details should be correct
    And The taxes amount in fill info details should be correct
    And The next step button should be enabled
    And I click on the next step button
    Then The check in date should be correct
    And The check out date should be correct
    And The booking overview total amount should be correct
    And The booking overview taxes amount should be correct
    And The total amount should be correct
    And The taxes amount should be correct
    And I click on the final step button
    And I enter the card number
    Then The holder name should be correct
    And I enter the expiration date
    Then The total amount footer should be correct
    And The taxes amount footer should be correct
    And I scroll to dates
    Then The check in date 2 should be correct
    And The check out date 2 should be correct
    And I scroll the finish booking details
    Then The name should be correct
    And The email should be correct
    And The address should be correct
    And The city and country should be correct
    And The phone number should be correct
    And The booking overview total 2 amount should be correct
    And The booking overview taxes 2 amount should be correct
    And The book now button should be enabled
    And I click on the book now button
    Then The error message should be "Card number is invalid"