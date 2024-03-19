Feature: Login

# Background:
#     Given I am on the sign in page
#     When deciding not to sign in
#     Then the main page should be opened

# Scenario: Default values displayed
#     Then destination message should be displayed
#     And default date range should be set
#     And default guest options should be set

# Scenario: Search without entering data
#     When search button is clicked
#     Then an Enter Destination validation message should be displayed

# Scenario: Search with a non-existent destination
#     When enter a non-existent destination
#     Then a sorry message should be displayed

# Scenario: Search does not start with only one character
#     When entering a destination with one character
#     Then the search should not start

# Scenario: Search starts with at least two characters
#     And when entering a destination with only two characters
#     Then the search should start

# Scenario: Search with a valid destination
#     When entering a valid destination
#     Then the list of places should correspond to the search

# Scenario: Calendar validations
#     When date field is selected
#     Then the calendar should be displayed
#     And previous days should be disabled
#     And posterior days should be enabled
#     And default date range should be set
#     And The number of nights should be correct
#     And the calendar should be scrollable
    
# Scenario: Selecting an inverse date range
#     When selecting a day
#     Then the Select Dates button should be disabled
#     When then selecting a previous day
#     Then Select Checkout message should be displayed
#     And the Select dates button should be disabled
    
# Scenario: Select a range of dates more than thirty days apart
#     When selecting a day
#     And then selecting a day more than thirty days apart
#     Then a Checkout more than 30 nights message should be displayed
#     And the Select dates button should be disabled

# Scenario: Selecting a date range
#     When selecting a day
#     Then the Select dates button should be disabled
#     When then selecting a posterior day
#     Then The number of nights should be correct
#     And the Select dates button should be enabled
#     And the selected date range should be set

# Scenario: Opening the guests field
#     When clicking on the guests field
#     Then a modal should open
#     And the modal should display the default number of rooms, adults, and children

# Scenario: Interacting with the number of rooms
#     Given the number of rooms is set to 1
#     Then the "-" button for rooms should be disabled
#     When clicking the "+" button for rooms until the number of rooms is equal to adults
#     And clicking the "+" button for rooms once more
#     Then the number of adults should increase at the same time
#     When clicking the "-" button for rooms
#     Then the number of adults stay the same value

# Scenario: Interacting with the number of adults
#     Given the number of adults is set to 2
#     Then the "-" button for adults should be enabled
#     When clicking the "-" button for adults
#     Then the number of adults should decrease by 1
#     And the "-" button for adults should be disabled
#     When clicking the "+" button for adults
#     Then the number of rooms should stay the same value

# Scenario: Interacting with the number of children
#     Given the number of children is set to 0
#     Then the "-" button for children should be disabled
#     When clicking the "+" button for children
#     Then a modal opens to select the age of the children by scrolling
#     When select a children age
#     Then the children age section opens showing the quantity and age selected
#     When clicking the "+" button for children once more
#     Then a modal opens to select the age of the children by scrolling
#     When select a children age
#     Then the children age section shows the new quantity and ages selected
#     When clicking the "-" button for children
#     Then the number of children should decrease by 1
#     And the children age section should be updated
#     When clicking the "-" button for children
#     Then the children age section disappears
#     When clicking the "+" button for children
#     And select a children age
#     And scroll-up the modal
#     Then the entire modal disappears and the main page should displayed


# Scenario Outline: Booking a room without logging in
#     Given the login page is displayed
#     When the login page is closed
#     And the destination "<destination>" is entered
#     And the first option from the results is selected
#     And a date range from "<check_in_date>" to "<check_out_date>" is selected
#     And a child aged 5 is added
#     And the second property from the list is selected
#     And the property details are viewed
#     And the room type is selected
#     And personal details are entered
#     And the next step button is clicked
#     Then a summary of the booking should be displayed
#     When card number "<card_number>" and expiration date "<expiration_date>" are entered
#     And the book button is clicked
#     Then a confirmation of the booking should be displayed

# Examples:
#     | dataFile  | dataUser | dataReservation| 
#     | data.json | user     | reservation    |
    

Scenario: Successful login
    Given I am on the sign in page
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
    And I extract the original amount text if available
    And I extract the taxes and charges text
    And I select the second option
    Then The select room modal should be opened
    And The total amount in property details should be correct
    And The total original amount in property details should be correct if available
    And The taxes amount in property details should be correct
    And I select the room
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