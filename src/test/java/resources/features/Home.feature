Feature: Login

Background:
    Given I am on the sign in page
    When deciding not to sign in
    Then the main page should be opened

Scenario: Default values displayed
    Then destination message should be displayed
    And default date range should be set
    And default guest options should be set

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
    
