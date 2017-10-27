@WIP
Feature: Search Sections

  As a TheGuardian API user
  I want to search available sections
  So that I can find relevant domains of interest quickly

  Scenario: Search relevant sections
    Given John has access to TheGuardian API app
    When he searches available 'business' sections
    Then he receives all sections related to 'business'