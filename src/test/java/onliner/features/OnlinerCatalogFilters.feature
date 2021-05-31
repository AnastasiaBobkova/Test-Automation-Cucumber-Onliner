Feature: Filter Onliner Catalog products
  This feature is for verifying that search results displayed
  on the Catalog Products Page meet specified search criteria

  Background:
    Given Onliner Home Page is opened
    And User clicks 'Каталог' on Onliner Home Page
    And selects 'Электроника' on Onliner Category Page
    And moves to 'Телевидение' and 'Телевизоры' on Onliner Category Page


  Scenario: Check several filters
    Given User is on Onliner Products Page
    When User checks the following filters
      | PRODUCER    | Samsung   |
      | MAXPRICE    | 1000      |
      | RESOLUTION  | 1920x1080 |
      | MINDIAGONAL | 40        |
      | MAXDIAGONAL | 50        |
    Then correct search results are displayed