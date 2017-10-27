@WIP
Feature: Search by Content

  As a TheGuardian API user
  I want to search items by content
  So that I can find quickly relevant information

  Scenario: Search items by content
    Given John has access to TheGuardian API app
    When he searches items with 'cars' content
    Then he receives all the items with 'cars' content

  Scenario: Search items using complex queries
    Given John has access to TheGuardian API app
    When he searches items with 'sausages AND (mash OR chips) AND NOT (unpleasant OR sadly)' content
    Then he receives all the items with 'sausages AND (mash OR chips) AND NOT (unpleasant OR sadly)' content