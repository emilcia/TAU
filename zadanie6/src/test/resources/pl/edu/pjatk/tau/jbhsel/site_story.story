Scenario:  Tab on helpdesk page works as expected when clicked

Given user is on helpdesk page
When user clicks the login tab
Then reload page to check session

Scenario:  can't login to page

Given user is on helpdesk page
When user clicks the login tab with wrong attributes
Then login failed



