Scenario:  can't login to page

Given user is on main page
When user clicks the login tab with wrong attributes
Then login failed

Scenario:  login to page

Given user is on main page
When user clicks the login tab
Then login success

Scenario:  login to page and refresh page to check session

Given user is on main page
When user clicks the login tab
Then reload page to check session



