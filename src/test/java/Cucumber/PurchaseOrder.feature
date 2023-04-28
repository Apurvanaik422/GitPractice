Feature:Purchase Order Functionality

  Background:
    Given I landed on the Ecommerce Web Page

  Scenario Outline: Positive Test to Add product to Cart
    Given an user enters the <username> and <password>
    When adds product to the cart <productName>
    And clicks on checkout with product Selected
    Then "THANKYOU FOR THE ORDER." message will be displayed to the user


    Examples:

    |username                 |password     |productName |

    | apurvanaik42@academy.com| Improve@1234| ZARA COAT 3|