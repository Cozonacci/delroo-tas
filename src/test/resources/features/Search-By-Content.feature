@WIP
Feature: Search by Content

  As a TheGuardian API user
  I want to search items by content
  So that I can find quickly relevant information

  Scenario: Search items by content
    Given John has access to TheGuardian API app
    When he searches items with 'car' content
    Then he receives all the items with 'car' content