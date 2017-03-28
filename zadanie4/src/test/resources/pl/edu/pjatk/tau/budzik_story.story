
Scenario:  budzik dzwoni o wybranej porze

Given nadchodzi godzina <czas>
When na budziku byla ustawiona godzina <godzina>
Then budzik powinien zadzwonic o ustawionej godzinie <wynik>

Examples:
|czas|godzina|wynik|
|09.00|09.00|true|
|10.00|12.00|false|


