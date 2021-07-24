# language: en
Feature: City weather params is correct

  Scenario Outline: Get <city> request params and they are correct
    When get <city> weather
    Then check request parameters <type> <query> <language> <unit>

    Examples:
      | city       | type   | query                                | language | unit |
      | "New York" | "City" | "New York, United States of America" | "en"     | "m"  |
      | "Moscow"   | "City" | "Moscow, Russia"                     | "en"     | "m"  |


  Scenario Outline: Get <city> location params and they are correct
    When get <city> weather

    Then check location parameters <city> <country> <region>

    Examples:
      | city       | country                    | region        |
      | "New York" | "United States of America" | "New York"    |
      | "Moscow"   | "Russia"                   | "Moscow City" |