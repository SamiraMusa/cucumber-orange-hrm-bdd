Feature: Employee Leaves


  Scenario: add new employee
    Given A new employee joins the company
      | John  |
      | Smith |
    When I add an employee
    Then Employee should be added successfully


  Scenario: add leave entitlement for an employee
    Given A new employee joins the company
      | John  |
      | Smith |
    When  I add leave entitlement of 30 days
    Then  Leave Entitlements should be updated


  Scenario: assign leave to new employee
    Given Employee requests a leave from date to date
      | 2023-11-24 |
      | 2023-12-04 |
    When  I assign leave to employee
    Then  Leave should be assigned successfully