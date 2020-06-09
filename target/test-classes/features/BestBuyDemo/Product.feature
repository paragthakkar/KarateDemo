Feature: Create And Verify Product Entry

  Background:
    Given url baseUrl
    * configure logPrettyRequest = true
    * configure logPrettyResponse = true

  Scenario: Create and Verify a Product
    * def item = 'Tubelight'
    Given path '/products'
    And request { "name": '#(item)', "type": "HardGood", "price": 5.49, "shipping": 0, "upc": "test", "description": "test", "manufacturer": "test", "model": "test", "url": "test", "image": "test" }
    When method POST
    Then status 201

    * def id = $.id

    Given path '/products',id
    When method GET
    Then status 200

    Given path '/products',id
    When method DELETE
    Then status 200

    Given path '/products',id
    When method GET
    Then status 404
    And match $.name == 'NotFound'