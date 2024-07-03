Feature: Amazon Search


  Scenario: Search a Product Apple Mackbook
    Given I have a Search field on a Amazomn Page
    When I search for a Product with name "Apple Mackbook pro" and price 10000
    Then Product with name "Apple Mackbook pro" is displayed

  Scenario: Search a Product Iphone
    Given I have a Search field on a Amazomn Page
    When I search for a Product with name "Iphone" and price 10000
    Then Product with name "Iphone" is displayed